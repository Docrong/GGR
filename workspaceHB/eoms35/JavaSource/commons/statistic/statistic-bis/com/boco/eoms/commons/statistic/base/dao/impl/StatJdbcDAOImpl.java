package com.boco.eoms.commons.statistic.base.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.JdbcUtils;

import com.boco.eoms.commons.statistic.base.config.model.ConditionParam;
import com.boco.eoms.commons.statistic.base.config.model.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.config.model.QueryDefine;
import com.boco.eoms.commons.statistic.base.config.model.ResultSort;
import com.boco.eoms.commons.statistic.base.config.model.SummaryDefine;
import com.boco.eoms.commons.statistic.base.dao.IStatJdbcDAO;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.util.SqlParamCache;
import com.boco.eoms.commons.statistic.base.util.StatType;
import com.boco.eoms.commons.statistic.base.util.StatUtil;

/**
 * @author liuxy
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StatJdbcDAOImpl extends JdbcDaoSupport implements IStatJdbcDAO {

    public Map getCondSqlByMap(KpiDefine kpiDefine, Map condMap, String statID) {
        final Map params = new HashMap();

//		String condSql = "";
        ConditionParam[] conditionParams = kpiDefine.getConditionParams();
        if (conditionParams == null) {
            this.logger.info("\n算法没有输入条件");
            SqlParamCache.Instance().putParams(statID, params);
            return params;
        }

        for (int iParam = 0; iParam < conditionParams.length; iParam++) {
            ConditionParam condParam = conditionParams[iParam];
            String key = condParam.getPageName();
            Object mapObj = condMap.get(key);
            //String value = StaticMethod.nullObject2String(mapObj!=null?((String[])mapObj)[0]:"");
            String value = mapObj != null ? (String) mapObj : "";

//			if (!value.equals("")) 
            {
                String paramValue = "";
                String[] paramValues = value.split(",");
                for (int i = 0; i < paramValues.length; i++) {
//					if ("string".equalsIgnoreCase(condParam.getDbtype())
//							|| "datetime".equalsIgnoreCase(condParam.getDbtype())
//							|| "date".equalsIgnoreCase(condParam.getDbtype())
//							|| "time".equalsIgnoreCase(condParam.getDbtype())) 
                    if (condParam.isMarkflg() && !"".equalsIgnoreCase(paramValues[i])) {
                        paramValues[i] = "'" + paramValues[i] + "'";
                    }

                    paramValue += paramValues[i];
                    if (i < paramValues.length - 1)
                        paramValue += ",";
                }
                params.put(key, paramValue);
                //				else if(condParam.getDbtype().equals("int")){
                //				}else if(condParam.getDbtype().equals("datetime")){

//				if ("in".equals(condParam.getOperate()))
//					paramValue = "(" + paramValue + ")";

//				String sql = condParam.getQueryName() + " " + condParam.getOperate()
//						+ " " + paramValue;
//				String sql = paramValue;

//				if (condSql.length()>0) condSql+=" and ";

//				condSql += sql;
            }
        }
//		params.putAll(condMap);
        params.put("dynamiccolumparam", condMap.get("dynamiccolumparam"));
        SqlParamCache.Instance().putParams(statID, params);
        return params;
    }

    /**
     * 根据ID获取最后统计的时间 add by lizhenyou
     *
     * @param id
     */
    public String getKpiInfo(int id) {
        String asName = "lasttime";
        String tablename = "est_last_oper";
        String sql = "select last_operate_time as " + asName + " from " + tablename + " where id=" + id;

        String sqldate = "";
        try {
            sqldate = String.valueOf(getJdbcTemplate().queryForObject(sql, String.class));
            sqldate = sqldate.substring(0, sqldate.indexOf('.'));
        } catch (Exception e) {
            this.logger.info("\n获取最后采集时间 sql：" + sql);
            this.logger.info("\n请查看数据库中" + tablename + "是否正确");
        }

        return sqldate;
    }

    /**
     * @param kpiDefine
     * @param condSql
     * @return
     * @deprecated 停止使用
     */
    private Map getKpiResult(KpiDefine kpiDefine, String condSql) {
        //最终返回结果Map
        final Map mapResult = new HashMap();
        //汇总字段结果集
        final List listSummaryResult = new ArrayList();
        //查询汇总条件,汇总字段定义
        final SummaryDefine[] summaryDefines = kpiDefine.getSummaryDefines();

        //根据queryDefine定义,查询多次
        for (int iQueryDefine = 0; iQueryDefine < kpiDefine.getQueryDefines().length; iQueryDefine++) {

            //一次查询的结果集,多次查询的结果集将合并为最终的统计表结果
            final QueryDefine queryDefine = kpiDefine.getQueryDefines()[iQueryDefine];

            //计算百分比的分母, 分母计算的sql
            final int percentCount;
            if (!queryDefine.isPercentCountNeedSumary()) {
                percentCount = queryDefine.getPercentCountSql().equals("") ? 0
                        : getJdbcTemplate().queryForInt(queryDefine.getPercentCountSql());
            } else percentCount = 0;
            //开始拼装报表的查询sql
            StringBuffer sqlBuf = new StringBuffer();

//			select 后面先写汇总字段
            sqlBuf.append("select ");
            for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
                SummaryDefine summaryDefine = summaryDefines[iSummary];

                if (iSummary > 0) sqlBuf.append(",");//
                sqlBuf.append(summaryDefine.getColumn());//汇总字段
            }

            //每个统计字段
            for (int iFieldDefine = 0; iFieldDefine < queryDefine.getFieldDefines().length; iFieldDefine++) {
                FieldDefine fieldDefine = queryDefine.getFieldDefines()[iFieldDefine];
                sqlBuf.append(",");
                sqlBuf.append(fieldDefine.getStatSql());
                sqlBuf.append(" as ");
                sqlBuf.append(fieldDefine.getId());
            }

            //加入from
            sqlBuf.append(" from ");
            sqlBuf.append(queryDefine.getFrom());


            boolean haveWhere = false;
            if (!queryDefine.getWhere().equals("")) {
                sqlBuf.append(" where ");
                sqlBuf.append(queryDefine.getWhere());
                haveWhere = true;
            }

            if (!condSql.equals("")) {
                if (haveWhere) {
                    sqlBuf.append(" and ");
                    sqlBuf.append(condSql);
                } else {
                    sqlBuf.append(" where ");
                    sqlBuf.append(condSql);
                }
            }

            //加入group by
            sqlBuf.append(" group by ");
            for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
                SummaryDefine summaryDefine = summaryDefines[iSummary];

                if (iSummary > 0) sqlBuf.append(",");//
                sqlBuf.append(summaryDefine.getColumn());
            }


            //加入order by
            if (!queryDefine.getOrderBy().equals("")) {
                sqlBuf.append(" order by ");
                sqlBuf.append(queryDefine.getOrderBy());
            }

            String sql = sqlBuf.toString();
            String queryName = "<query-define> name ：" + queryDefine.getName();
            logger.info("\n统计查询queryName : " + queryName);
            logger.info("\n统计查询sql: " + sql);

            final List listRowData = new ArrayList();
            getJdbcTemplate().query(sql, new RowCallbackHandler() {

                public void processRow(ResultSet rs) throws SQLException {
                    //查询定义字段的结果.
                    Map rowData = new HashMap();

                    int sumData = 0;
/*
					//得到汇总字段结果数据,存入list,每个query得到的汇总字段结果
					//一样,只存一次.
					if (!mapResult.containsKey("listSummaryResult")) {
						List summaryData = new ArrayList();
						String lastData ="";
						for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
							SummaryDefine summaryDefine = summaryDefines[iSummary];
							
							String field = summaryDefine.getColumn().substring(summaryDefine.getColumn().lastIndexOf('.')+1);
							String newData = rs.getString(field);
							
//							if(summaryDefines.length==2 && iSummary==0){
//								if (lastData.equals(""))lastData=newData;
//								if (!lastData.equals(newData)){
//									
//								}
//							}
							
							//if ((!data.equals(""))&&iSummarysummaryDefines.length-1)
							summaryData.add(newData );
						}
						listSummaryResult.add(summaryData);
					}*/

                    /* 生成一个 [汇总字段] 列表,要求合并所有的query结果集,生成一个key
                     * 标识一行,同时在data列表中也增加key标识,在jsp呈现时根据key填充
                     * 表格.
                     * listSummaryResult需要重新排序,因为数据库查询排序可能打乱*/
                    String summaryKey = "";
                    {
                        List summaryData = new ArrayList();
                        for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
                            SummaryDefine summaryDefine = summaryDefines[iSummary];

                            String field = summaryDefine.getColumn().substring(summaryDefine.getColumn().lastIndexOf('.') + 1);
                            String newData = rs.getString(field);
                            summaryData.add(newData);
                            summaryKey += newData;
                        }
                        //判断结果中是否有key,如果没有add汇总字段
                        checkListSummaryResult(listSummaryResult, summaryKey, summaryData);

                    }

                    rowData.put("summaryKey", summaryKey);
                    for (int iFieldDefine = 0; iFieldDefine < queryDefine
                            .getFieldDefines().length; iFieldDefine++) {
                        FieldDefine fieldDefine = queryDefine.getFieldDefines()[iFieldDefine];

                        rowData.put(fieldDefine.getViewName(), rs.getString(fieldDefine
                                .getId()));

                        // TODO 计算分母, 每行查询一次, 以后需要重构
                        if (1 > 0 /*fieldDefine.isNeedPercent()*/) {
                            int percentCountData = 0;
                            if (queryDefine.isPercentCountNeedSumary()) {
                                String sql = queryDefine.getPercentCountSql();
                                String summaryData = "";
                                for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
                                    SummaryDefine summaryDefine = summaryDefines[iSummary];
                                    String tempfield = summaryDefine.getColumn().substring(
                                            summaryDefine.getColumn().lastIndexOf('.') + 1);
                                    String tempData = rs.getString(tempfield);
                                    summaryData += "'" + tempData + "',";

                                }
                                percentCountData = getJdbcTemplate().queryForInt(
                                        getPercentSql(summaryDefines, summaryData, sql));
                            } else {
                                percentCountData = percentCount;
                            }
                            rowData.put("percentCount", String.valueOf(percentCountData));

                        }

                        // TODO 汇总计算
                        if (iFieldDefine == 0) {
                            sumData += Integer
                                    .parseInt(rs.getString(fieldDefine.getId()));
                        }
                    }
                    listRowData.add(rowData);


                }

                private boolean checkListSummaryResult(List listSummaryResult, String summaryKey, Object summaryData) {
                    for (Iterator iter = listSummaryResult.iterator(); iter.hasNext(); ) {
                        Object[] summaryDataItem = (Object[]) iter.next();
                        if (((String) summaryDataItem[1]).equals(summaryKey)) return false;
                    }
                    Object[] summaryDataItem = new Object[2];
                    summaryDataItem[0] = summaryData;
                    summaryDataItem[1] = summaryKey;

                    listSummaryResult.add(summaryDataItem);

                    return true;
                }
            });
            //用Map封装查询结果集,包括计算百分比的分母和一般数据.
            Map queryResultMap = new HashMap();
//			queryResultMap.put("percentCount", String.valueOf(percentCount));
            queryResultMap.put("listRowData", listRowData);
            //把一个查询结果集存入return,遍历所有查询.
            mapResult.put(queryDefine.getName(), queryResultMap);

        }

        //把汇总字段的数据存入return,只存一次.
//		if (!mapResult.containsKey("listSummaryResult")) {
        // TODO listSummaryResult需要重新排序

        Collections.sort(listSummaryResult, new Comparator() {
            public int compare(Object o1, Object o2) {
                Object[] summaryDataItem1 = (Object[]) o1;
                Object[] summaryDataItem2 = (Object[]) o2;

                String s1 = ((String) summaryDataItem1[1]).toLowerCase(); //summaryKey
                String s2 = ((String) summaryDataItem2[1]).toLowerCase();
                return s1.compareTo(s2);
            }

        });

        mapResult.put("listSummaryResult", listSummaryResult);
//		}

        return mapResult;
    }

    public void exeSql(String a) {

        Connection c = getConnection();

        //		System.out.println("before lock !!!!!!!! " + c);
        //
        //		synchronized (this) {
        //			System.out.println("biegin lock !!!!!!!! " + c);
        //			try {
        //				int i = 20000;
        //				this.wait(i);
        //			} catch (InterruptedException e) {
        //				// TODO Auto-generated catch block
        //				e.printStackTrace();
        //			}
        //			System.out.println("end lock !!!!!!!!");
        //
        //		}

        try {
            boolean bb = c.getAutoCommit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // c.setAutoCommit(false);
        String s = "";
        JdbcTemplate t = getJdbcTemplate();
        //t.execute(s);
        int i = 0;
        try {
            Statement st = c.createStatement();
            st.execute(s);
            i = t.queryForInt("select count(*) from a");
            st.close();

            Statement st2 = c.createStatement();
            st2.execute(s);
            i = t.queryForInt("select count(*) from a");
            st2.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(i);
        t.execute(s);
    }

    /**
     * @param kpiDefine
     * @return
     * @throws SQLException
     * @author liuxy
     * @see 使用锁原因:由于在Informix中无法运行含子查询的sql,所有需要使用临时表协助处理,
     * 临时表在一个会话中有效,会话结束临时表由数据库自动回收,但是没有找到在jdbc中结束会话的办法(如果关闭数据库连接增大消耗),
     * 所以采用对数据库连接加锁,在一个锁中,使用唯一的数据库连接进行创建,查询,删除临时表操作,实现既不关闭数据库连接,
     * 又能保证临时表在一次统计查询中被创建和删除,在多线程并发时数据库中没有临时表名称重复冲突,并能及时回收. 征求更好的解决办法.
     */
    protected List getQueryUseTempTable(String creatTempTableSql, String querySql) {
        List listRS = new ArrayList();
        Connection con = getConnection();

//		根据conn对象地址生成临时表名,保证在每个conn锁中 临时表名称唯一,临时表使用后显式删除.
        String s_conn = con.toString();
        String tempTableName = "t_"
                + s_conn.substring(s_conn.lastIndexOf("@") + 1);

        creatTempTableSql += " into " + tempTableName;

        String dropTempTableSql = "drop table " + tempTableName;

        String checkTempTableSql = "";

        querySql = querySql.replaceFirst("?", tempTableName);


        //使用锁原因:由于在Informix中无法运行含子查询的sql,所有需要使用临时表协助处理,
        //临时表在一个会话中有效,会话结束临时表由数据库自动回收,但是没有找到在jdbc中结束会话的办法(如果关闭数据库连接增大消耗),
        //所以采用对数据库连接加锁,在一个锁中,使用唯一的数据库连接进行创建,查询,删除临时表操作,实现既不关闭数据库连接,又能保证
        //临时表在一次统计查询中被创建和删除,在多线程并发时数据库中没有临时表名称重复冲突,并能及时回收.
        //征求其他更好的解决办法.
        synchronized (con) {
            Statement stmt = null;

            try {

                stmt = con.createStatement();

                stmt.executeUpdate(creatTempTableSql);
                ResultSet rs = stmt.executeQuery(querySql);

                stmt.executeUpdate(dropTempTableSql);
                con.commit();
            } catch (SQLException ex) {
                JdbcUtils.closeStatement(stmt);
                stmt = null;
                releaseConnection(con);
                con = null;


            } finally {
                JdbcUtils.closeStatement(stmt);
                releaseConnection(con);
            }

        }
        return listRS;

    }

    private String getPercentSql(SummaryDefine[] summaryDefines, String summaryData,
                                 String sql) {

        String[] summaryDatas = summaryData.split(",");
        for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
            SummaryDefine summaryDefine = summaryDefines[iSummary];

            if (sql.indexOf("where") > 0)
                sql += " and ";
            else
                sql += " where ";
            sql += summaryDefine.getColumn() + "=" + summaryDatas[iSummary];

        }
        logger.info("统计百分比分母 sql:" + sql);

        return sql;
    }

    private boolean closeDBConnection(ResultSet rs, Statement stmt, Connection conn) {
        boolean secssed = true;
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                secssed = false;
                e.printStackTrace();
            }
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                secssed = false;
                e.printStackTrace();
            }
            stmt = null;
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                secssed = false;
                e.printStackTrace();
            }
            conn = null;
        }

        return secssed;
    }

    private void processRow(ResultSet rs, SummaryDefine[] summaryDefines, QueryDefine queryDefine, List listResult) throws SQLException {
        while (rs.next()) {
            int sumData = 0;

            /*
             * 生成一个 [汇总字段] 列表,要求合并所有的query结果集,生成一个key
             * 标识一行,同时在data列表中也增加key标识,在jsp呈现时根据key填充 表格.
             * listSummaryResult需要重新排序,因为数据库查询排序可能打乱
             */
            String summaryKey = "";
            // 查询定义字段的结果.
            Map rowData = new HashMap();
            for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
                SummaryDefine summaryDefine = summaryDefines[iSummary];

                String newData = rs.getString(summaryDefine.getId());
                summaryKey += newData;
                rowData.put(summaryDefine.getId(), newData);
            }
            // 判断结果中是否有key,如果没有add汇总字段, 返回最新的rowData
            rowData = checkListResult(listResult, summaryKey, rowData);
            // 处理汇总字段结束

            for (int iFieldDefine = 0; iFieldDefine < queryDefine.getFieldDefines().length; iFieldDefine++) {
                FieldDefine fieldDefine = queryDefine.getFieldDefines()[iFieldDefine];
                if (fieldDefine.getStatType().equals(StatType.FIELDSQL)) {

                    rowData.put(fieldDefine.getId(), rs.getString(fieldDefine
                            .getId()));

                    // TODO 汇总计算
                    if (iFieldDefine == 0) {
                        sumData += Integer.parseInt(rs.getString(fieldDefine
                                .getId()));
                    }
                }
            }
        }
    }

    private Map checkListResult(List listResult, String summaryKey, Map rowData) {
        for (Iterator iter = listResult.iterator(); iter.hasNext(); ) {
            Map dataItem = (Map) iter.next();
            if (((String) dataItem.get("summaryKey")).equals(summaryKey)) {
                // rowData = null;
                return dataItem;
            }
        }

        rowData.put("summaryKey", summaryKey);
        listResult.add(rowData);
        return rowData;
    }


    /**
     * 得到一个合并的结果集
     *
     * @param kpiDefine
     * @param condSql
     * @return
     */
    public List getListKpiResult(KpiDefine kpiDefine, String condSql, Map sqlParams) {
        //add by lizhenyou 获取数据库连接 08-11-13
        //数据库连接
        Connection conn = null;
        Statement stmt = null;
        DataSource ds = (DataSource) ApplicationContextHolder.getInstance().getBean("dataSource");//获取系统数据源

        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            //closeDBConnection(null,stmt,conn);
            e.printStackTrace();
            new Exception();
        } finally {

        }
        //add end


        // 最终返回结果List
        List listResult = new ArrayList();

        // 查询汇总条件,汇总字段定义
        final SummaryDefine[] summaryDefines = kpiDefine.getSummaryDefines();

        //根据queryDefine定义,查询多次
        for (int iQueryDefine = 0; iQueryDefine < kpiDefine.getQueryDefines().length; iQueryDefine++) {

            //一次查询的结果集,多次查询的结果集将合并为最终的统计表结果
            final QueryDefine queryDefine = kpiDefine.getQueryDefines()[iQueryDefine];

            //开始拼装报表的查询sql
            StringBuffer sqlBuf = new StringBuffer();

//			select 后面先写汇总字段
            sqlBuf.append("select ");
            for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
                SummaryDefine summaryDefine = summaryDefines[iSummary];
                String col = summaryDefine.getColumn();
//				if(((String)sqlParams.get(summaryDefine.getId())).equals("$disenable$")){
//					summaryDefine.setEnable(false);
//					col=summaryDefine.getDefValue();
//				}
                if (iSummary > 0) sqlBuf.append(",");//

                //汇总字段
                sqlBuf.append(col);
                sqlBuf.append(" as ");
                sqlBuf.append(summaryDefine.getId());
            }

            //每个统计字段
            for (int iFieldDefine = 0; iFieldDefine < queryDefine.getFieldDefines().length; iFieldDefine++) {
                FieldDefine fieldDefine = queryDefine.getFieldDefines()[iFieldDefine];
                if (fieldDefine.getStatType().equals(StatType.FIELDSQL)) {
                    sqlBuf.append(",");
                    sqlBuf.append(fieldDefine.getStatSql());
                    sqlBuf.append(" as ");
                    sqlBuf.append(fieldDefine.getId());
                }
            }

            //加入from
            sqlBuf.append(" from ");
            sqlBuf.append(queryDefine.getFrom());


            boolean haveWhere = false;
            if (!queryDefine.getWhere().equals("")) {
                sqlBuf.append(" where ");
                sqlBuf.append(queryDefine.getWhere());
                haveWhere = true;
            }
						
			/*更改参数传递方式
			  
			 if (!condSql.equals("")) {
				if (haveWhere) {
					sqlBuf.append(" and ");
					sqlBuf.append(condSql);
				} else {
					sqlBuf.append(" where ");
					sqlBuf.append(condSql);
				}
			}*/

            //加入group by
            if (kpiDefine.isAutoAppendGroupby()) {
                sqlBuf.append(" group by ");
                for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
                    SummaryDefine summaryDefine = summaryDefines[iSummary];
                    if (summaryDefine.isEnable()) {
                        if (iSummary > 0)
                            sqlBuf.append(",");//
                        sqlBuf.append(summaryDefine.getColumn());
                    }
                }
            }

            //加入order by
            if (!queryDefine.getOrderBy().equals("")) {
                sqlBuf.append(" order by ");
                sqlBuf.append(queryDefine.getOrderBy());
            }

            //贵州临时增加begin
//			String tempsql = queryDefine.getSelect();
//			tempsql+=" and "+condSql+ " group by "+queryDefine.getGroupBy();

            //贵州临时增加end;

            String sql = sqlBuf.toString();
//			String sql = tempsql;

            //页面条件和替换字典字段
            //sql = "select 'a1' as s1, 'a2' as s2 ,'a3' as s3,count(distinct est.mainid) as f1 ,count(distinct est.mainid) as f2 from EST_COMMONFAULT est";
            try {
                sql = StatUtil.getRepString(sql, sqlParams, "@");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String queryName = "<query-define> name ：" + queryDefine.getName();
            logger.info("\n统计查询queryName : " + queryName);
            logger.info("\n统计查询sql:" + sql);

            //add by lizhenyou 08-11-13
            ResultSet rs = null;
            try {
                rs = stmt.executeQuery(sql);

                processRow(rs, summaryDefines, queryDefine, listResult);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeDBConnection(rs, null, null);
            }
            //add end

//			getJdbcTemplate().query(sql, new RowCallbackHandler() {
//
//				public void processRow(ResultSet rs) throws SQLException {
//
//					int sumData = 0;
//					
//					/* 生成一个 [汇总字段] 列表,要求合并所有的query结果集,生成一个key
//					 * 标识一行,同时在data列表中也增加key标识,在jsp呈现时根据key填充
//					 * 表格. 
//					 * listSummaryResult需要重新排序,因为数据库查询排序可能打乱*/
//					String summaryKey = "";
//					//查询定义字段的结果.
//					Map rowData = new HashMap();
//					for (int iSummary = 0; iSummary < summaryDefines.length; iSummary++) {
//						SummaryDefine summaryDefine = summaryDefines[iSummary];
//						
//						String newData = rs.getString(summaryDefine.getId());
//						summaryKey += newData;
//						rowData.put(summaryDefine.getId(), newData);
//					}
//					//判断结果中是否有key,如果没有add汇总字段, 返回最新的rowData
//					rowData=checkListResult(listResult,summaryKey,rowData);
//					//处理汇总字段结束
//					
//					
//					for (int iFieldDefine = 0; iFieldDefine < queryDefine
//							.getFieldDefines().length; iFieldDefine++) {
//						FieldDefine fieldDefine = queryDefine.getFieldDefines()[iFieldDefine];
//						if (fieldDefine.getStatType().equals(StatType.FIELDSQL)) {
//
//							rowData.put(fieldDefine.getId(), rs
//									.getString(fieldDefine.getId()));
//
//							// TODO 汇总计算
//							if (iFieldDefine == 0) {
//								sumData += Integer.parseInt(rs
//										.getString(fieldDefine.getId()));
//							}
//						}
//					}
//				}
//					
//				private Map checkListResult(List listResult, String summaryKey, Map rowData) {					
//					for (Iterator iter = listResult.iterator(); iter.hasNext();) {
//						Map dataItem = (Map)iter.next();
//						if (((String) dataItem.get("summaryKey")).equals(summaryKey)) {
////							rowData = null;
//							return dataItem;
//						}
//					}
//
//					rowData.put("summaryKey",summaryKey);
//					listResult.add(rowData);
//					return rowData;
//				}
//			});
        }

        //关闭数据库连接 add by lizhenyou 08-11-13
        this.closeDBConnection(null, stmt, conn);

        for (Iterator iter = listResult.iterator(); iter.hasNext(); ) {
            Map dataItem = (Map) iter.next();
            for (int iFieldDefine = 0; iFieldDefine < kpiDefine.getAllFieldDefines().size(); iFieldDefine++) {
                FieldDefine fieldDefine = (FieldDefine) kpiDefine.getAllFieldDefines().get(iFieldDefine);
                if (!dataItem.containsKey(fieldDefine.getId())) {
                    dataItem.put(fieldDefine.getId(), fieldDefine.getDefValue());
                }

                if (dataItem.get(fieldDefine.getId()) == null) {
                    dataItem.put(fieldDefine.getId(), fieldDefine.getDefValue());
                }

                if (fieldDefine.getStatType().equals(StatType.EXPRESSION)) {
                    dataItem.put(fieldDefine.getId(),
                            (String)
                                    StatUtil.getExpressionResult(dataItem, fieldDefine.getStatSql(), fieldDefine.getDefValue())
                    );
                }
            }
        }

        // TODO listSummaryResult需要重新排序
        Collections.sort(listResult, new Comparator() {
            public int compare(Object o1, Object o2) {
                String summaryKey_1 = (String) ((Map) o1).get("summaryKey");
                String summaryKey_2 = (String) ((Map) o2).get("summaryKey");

                return summaryKey_1.toLowerCase().compareTo(summaryKey_2.toLowerCase());
            }
        });

        ResultSort[] resultSort = kpiDefine.getResultSorts();
        if (resultSort != null) {
            for (int i = resultSort.length - 1; i >= 0; i--) {
                String id = resultSort[i].getId();
                String type = resultSort[i].getType();
                resultSort(listResult, id, type);
            }
        }

        return listResult;
    }

    private void resultSort(List listResult, final String field, final String type) {
        // TODO listSummaryResult需要重新排序
        Collections.sort(listResult, new Comparator() {
            public int compare(Object o1, Object o2) {
                String summaryKey_1 = (String) ((Map) o1).get(field);
                String summaryKey_2 = (String) ((Map) o2).get(field);

                if ("DESC".equalsIgnoreCase(type)) {
                    return summaryKey_2.toLowerCase().compareTo(summaryKey_1.toLowerCase());
                } else {
                    return summaryKey_1.toLowerCase().compareTo(summaryKey_2.toLowerCase());
                }

            }
        });
    }

    public List getListKpiResult(KpiDefine kpiDefine, Map param, String[] condSql, String statID) {
//		String CondSql = getCondSqlByMap(kpiDefine, param,statID);
        Map sqlParams = getCondSqlByMap(kpiDefine, param, statID);
        //sqlParams.putAll(param);
        condSql[0] = "";
        return getListKpiResult(kpiDefine, "", sqlParams);

    }

    public int queryForInt(String sql) {
        // TODO Auto-generated method stub
        return getJdbcTemplate().queryForInt(sql);
    }


}
