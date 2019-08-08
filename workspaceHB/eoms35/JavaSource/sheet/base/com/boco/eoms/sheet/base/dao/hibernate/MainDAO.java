package com.boco.eoms.sheet.base.dao.hibernate;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.util.Iterator;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.QuerySqlInit;

/**
 * <p>
 * Title:mainDao做适配
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-18 10:38:32
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public abstract class MainDAO extends BaseSheetDaoHibernate implements IMainDAO {


    public BaseMain loadSinglePO(String id, Object obj) throws HibernateException {
        BaseMain main = (BaseMain) getHibernateTemplate().get(obj.getClass(), id);
        return main;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
     */
    public BaseMain loadSinglePOByProcessId(String processId, Object obj)
            throws HibernateException {
        BaseMain main = new BaseMain();
        String sql = "from " + obj.getClass().getName() + " as main where main.piid ='"
                + processId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            main = null;
        } else {
            main = (BaseMain) list.get(0);
        }
        return main;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
     */
    public void deleteMain(String id, Object mainObject) throws HibernateException {
        BaseMain main = (BaseMain) getHibernateTemplate().get(BaseMain.class, id);
        main.setDeleted(new Integer(1));
        getHibernateTemplate().save(main);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getHolds()
     */
    public List getHolds(final Map condition, final Integer curPage, final Integer pageSize, final Object mainObject)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
                //增加条件alias.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
                //添加查询的变量
                String beanName = StaticMethod.nullObject2String(condition.get("beanName"));
                String variables = QuerySqlInit.getAllDictItemsName(beanName);
                String queryStr = "select " + variables + " from " + mainObject.getClass().getName() + " main where "
                        + "main.templateFlag <> 1 and (main.status=:status or main.status=:status1) and main.deleted<>'1'";
                String orderCondition = StaticMethod.nullObject2String(condition.get("orderCondition"));
                StringBuffer hql = new StringBuffer(queryStr);
                if (pageSize.intValue() != -1) {
                    if (!orderCondition.equals("")) {
                        hql.append(" order by " + orderCondition);
                    } else {
                        hql.append(" order by main.sendTime desc");
                    }
                }
                System.out.println("beanName==" + beanName);
                System.out.println("hql==hold=" + hql);
                Query query = session.createQuery(hql.toString());
                //设置归档标记
                query.setInteger("status", Constants.SHEET_HOLD.intValue());
                query.setInteger("status1", Constants.ACTION_FORCE_HOLD);

                //分页查询条
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));

                    query.setMaxResults(pageSize.intValue());
                }
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 根据查询条件查询任务信息, 不进行分页处理
     *
     * @param hsql 查询语句
     * @throws HibernateException
     */
    public List getMainListBySql(String hql) throws HibernateException {
        return this.getHibernateTemplate().find(hql);
    }


    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getHoldsCount()
     */
    public Integer getHoldsCount(final Object mainObject) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
//				String queryStr = "from " + getMainName() + " alias where "
//						+ "alias.status=:status ";
                //取列表数量
                //增加条件alias.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
                String queryCountStr = "select count(main.id) from " + mainObject.getClass().getName()
                        + " main where main.templateFlag <> 1 and (main.status=:status or main.status=:status1) and main.deleted<>'1'";
                Query query = session.createQuery(queryCountStr);
                //TODO 归档标记，需确认
                query.setInteger("status", Constants.SHEET_HOLD.intValue());
                query.setInteger("status1", Constants.ACTION_FORCE_HOLD);
                Integer total = (Integer) query.iterate().next();
                return total;
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getStarterCount(java.lang.String)
     */
    public Integer getStarterCount(final String userId, final Object mainObject)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，取userId启动流程的数量
                //增加条件main.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
                String queryStr = "select count(main.id) from " + mainObject.getClass().getName()
                        + " main where " + " main.sendUserId=:sendUserId and main.status=:status and main.templateFlag <> 1 and (title is not null or title='') and main.deleted<>'1'";
                Query query = session.createQuery(queryStr);
                query.setString("sendUserId", userId);
                query.setInteger("status", Constants.SHEET_RUN.intValue());
                Integer total = (Integer) query.iterate().next();
                return total;
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getStarterList(java.lang.String)
     */
    public HashMap getStarterList(final String userId, final Integer curPage,
                                  final Integer pageSize, final Object mainObject, final HashMap condition) throws HibernateException {
        //增加条件main.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
        //增加查询变量
        String beanName = StaticMethod.nullObject2String(condition.get("beanName"));
        String variables = QuerySqlInit.getAllDictItemsName(beanName);
        String sqlstr = "select " + variables + " from " + mainObject.getClass().getName() + " main where main.templateFlag <> 1 and "
                + " main.sendUserId='" + userId + "' and status=0 and (title is not null or title='') and main.deleted<>'1'";
        String orderCondition = StaticMethod.nullObject2String(condition.get("orderCondition"));
        StringBuffer hql = new StringBuffer(sqlstr);
        if (pageSize.intValue() != -1) {
            if (!orderCondition.equals("")) {
                hql.append(" order by " + orderCondition);
            } else {
                hql.append(" order by main.sendTime desc");
            }
        }
        return getMainListBySql(hql.toString(), curPage, pageSize);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getQuerySheetByCondition(java.lang.String,
     *      java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public List getQuerySheetByCondition(final String hsql,
                                         final Integer curPage, final Integer pageSize, int[] aTotal, String queryType)
            throws HibernateException {
        // TODO Auto-generated method stub
        if (aTotal[0] <= 0) {
            aTotal[0] = ((Integer) count(hsql)).intValue();
            if (aTotal[0] <= 0) {
                return null;
            }
        }
        if (queryType != null && queryType.equals("number")) {
            return null;
        }
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                Query query = session.createQuery(hsql);
                //分页查询条
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));
                    query.setMaxResults(pageSize.intValue());
                }
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public Integer count(final String hsql) throws HibernateException {
        int sql_distinct = hsql.indexOf("distinct");
        int sql_index = hsql.indexOf("from");
        int sql_orderby = hsql.indexOf("order by");

        String countStrTmp;
        if (sql_distinct > 0) {
            int sql_comma = (hsql.substring(sql_distinct, sql_index)).indexOf(",");
            if (sql_comma > 0) {
                countStrTmp = "select count("
                        + hsql.substring(sql_distinct, sql_distinct + sql_comma) + ") ";
            } else {
                countStrTmp = "select count("
                        + hsql.substring(sql_distinct, sql_index) + ") ";
            }
        } else {
            countStrTmp = "select count(*) ";
        }

        if (sql_orderby > 0)
            countStrTmp += hsql.substring(sql_index, sql_orderby);
        else
            countStrTmp += hsql.substring(sql_index);

        final String countStr = countStrTmp;

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Integer amount;
                Query query = session.createQuery(countStr);
                List resultList = query.list();
                if (resultList != null && !resultList.isEmpty()) {
                    amount = (Integer) resultList.get(0);
                } else
                    amount = new Integer(0);
                return amount;
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /* 根据工单流水号获取当前系统该类工单的最大值
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getXYZ(java.lang.String)
     */
//	public int getXYZ(String presheetId, Object mainObject) throws HibernateException {
//		// TODO Auto-generated method stub
//		int sheetMaxSize=0;
//		String sql="from "+ mainObject.getClass().getName() +" as main where main.sheetId like '"+
//		           presheetId+"%' order by main.sheetId desc";
//		List sheetList=getHibernateTemplate().find(sql);
//		if(sheetList.size()>0){
//			BaseMain main =(BaseMain)sheetList.get(0);
//			String localSheetId=main.getSheetId();
//			sheetMaxSize=new Integer(
//					localSheetId.substring(localSheetId.lastIndexOf("-")+2,localSheetId.length())).intValue();
//		}
//        return sheetMaxSize;
//	}
    public int getXYZ(final String presheetId, final Object mainObject) throws HibernateException {
        int sheetMaxSize = 0;
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String sql = "select main.sheetId from " + mainObject.getClass().getName() + " as main where main.sheetId like '" +
                        presheetId + "%' order by main.sheetId desc";
                Query query = session.createQuery(sql);
                Integer maxSize = null;
                Iterator iterate = query.iterate();
                if (iterate.hasNext()) {
                    String localSheetId = StaticMethod.nullObject2String(iterate.next()).trim();
                    maxSize = new Integer(
                            localSheetId.substring(localSheetId.lastIndexOf("-") + 2, localSheetId.length()));
                }
                return maxSize;
            }
        };
        Integer maxSheet = (Integer) getHibernateTemplate().execute(callback);
        if (maxSheet != null) sheetMaxSize = maxSheet.intValue();
        return sheetMaxSize;
    }

    public BaseMain getMain(String id, String className) throws Exception {
        return null;
    }

    /**
     * 实现根据用户查找出他的所有模板
     *
     * @author wangjianhua
     * @date 2008-7-22
     */
    public List getTemplatesByUserIds(String userId, final Integer curPage, final Integer pageSize, int[] aTotal, Object mainObject) throws HibernateException {

        final String hql = "from " + mainObject.getClass().getName() + " as main where main.templateFlag=1 and main.sendUserId='" + userId + "' order by sendTime desc";
        String countHql = "select count(*) from " + mainObject.getClass().getName() + " as main where main.templateFlag=1 and main.sendUserId='" + userId + "'";

        aTotal[0] = ((Integer) getHibernateTemplate().find(countHql).listIterator().next()).intValue();

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(hql);
                //分页查询条
                query.setFirstResult(pageSize.intValue()
                        * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                return query.list();
            }
        };

        return (List) getHibernateTemplate().execute(callback);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getAllAttachmentsBySheet(java.lang.String)
     */
    public List getAllAttachmentsBySheet(String where) throws HibernateException {
        String hql = "from TawCommonsAccessories s where s.accessoriesName in (" + where + ") order by accessoriesUploadTime";
        return (List) getHibernateTemplate().find(hql);
    }

    /**
     * 根据查询条件查询任务信息, 并进行分页处理
     *
     * @param hsql     查询语句
     * @param curPage  分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
    public HashMap getMainListBySql(final String queryStr,
                                    final Integer curPage, final Integer pageSize)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                //获取任务总数的查询sql
                HashMap map = new HashMap();
                try {
                    int sql_distinct = queryStr.indexOf("distinct");

                    int sql_index = queryStr.indexOf("from");
                    int sql_orderby = queryStr.indexOf("order by");

                    String queryCountStr;
                    if (sql_distinct > 0)
                        queryCountStr = "select count("
                                + queryStr.substring(sql_distinct, sql_index) + ") ";
                    else
                        queryCountStr = "select count(*) ";

                    if (sql_orderby > 0)
                        queryCountStr += queryStr.substring(sql_index, sql_orderby);
                    else
                        queryCountStr += queryStr.substring(sql_index);

                    Integer totalCount;

                    Query totalQuery = session.createQuery(queryCountStr);
                    List result = totalQuery.list();
                    if (result != null && !result.isEmpty()) {
                        totalCount = (Integer) result.get(0);
                    } else {
                        totalCount = new Integer(0);

                    }
                    System.out.println("=====" + queryStr);
                    Query query = session.createQuery(queryStr);
                    if (pageSize.intValue() != -1) {
                        query.setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                        query.setMaxResults(pageSize.intValue());
                    }
                    List resultList = query.list();

                    map.put("sheetTotal", totalCount);
                    map.put("sheetList", resultList);
                } catch (Exception e) {
                    System.out.println("-------main list error!---------");
                    e.printStackTrace();
                }
                return map;
            }
        };
        return (HashMap) getHibernateTemplate().execute(callback);
    }


    public void saveOrUpdateMain(Object main) throws HibernateException {
        BaseMain baseMain = (BaseMain) main;
        if ((baseMain.getId() == null)) {
            getHibernateTemplate().save(baseMain);
            getHibernateTemplate().flush();
            getHibernateTemplate().clear();
        } else {
            getHibernateTemplate().flush();
            getHibernateTemplate().clear();
            getHibernateTemplate().saveOrUpdate(baseMain);
        }
    }

    /*撤销工单列表数量
     * (non-Javadoc)
     * zhangying
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getHoldsCount()
     */
    public Integer getCancelCount(final Object mainObject) throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                //取列表数量
                //增加条件alias.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
                String queryCountStr = "select count(main.id) from " + mainObject.getClass().getName()
                        + " main where (main.status=:status or main.status=:status1 or main.status=:status2) and main.deleted<>'1' ";

                Query query = session.createQuery(queryCountStr);
                //TODO 归档标记，需确认
                query.setInteger("status", Constants.ACTION_CANCEL);
                query.setInteger("status1", Constants.ACTION_FORCE_NULLITY);
                query.setInteger("status2", Constants.ACTION_NULLITY);
                Integer total = (Integer) query.iterate().next();

                return total;

            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /*撤销工单列表
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#getHolds()
     */
    public List getCancelList(final Integer curPage, final Integer pageSize, final Object mainObject, final HashMap condition)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                //hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
                //增加条件main.deleted<>'1' ，过滤已经被删除或隐藏的工单 add by jialei
                //增加查询变量
                String beanName = StaticMethod.nullObject2String(condition.get("beanName"));
                String variables = QuerySqlInit.getAllDictItemsName(beanName);
                String queryStr = "select " + variables + " from " + mainObject.getClass().getName() + " main where "
                        + "(main.status=:status or main.status=:status1 or main.status=:status2) and main.deleted<>'1' ";
                String orderCondition = StaticMethod.nullObject2String(condition.get("orderCondition"));
                StringBuffer hql = new StringBuffer(queryStr);
                if (pageSize.intValue() != -1) {
                    if (!orderCondition.equals("")) {
                        hql.append(" order by " + orderCondition);
                    } else {
                        hql.append(" order by main.sendTime desc");
                    }
                }
                Query query = session.createQuery(hql.toString());
                //设置归档标记
                query.setInteger("status", Constants.ACTION_CANCEL);
                query.setInteger("status1", Constants.ACTION_NULLITY);
                query.setInteger("status2", Constants.ACTION_FORCE_NULLITY);

                //分页查询条
                if (pageSize.intValue() != -1) {
                    query.setFirstResult(pageSize.intValue()
                            * (curPage.intValue()));

                    query.setMaxResults(pageSize.intValue());
                }
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public BaseMain getMainBySheetId(String sheetId, Object object) throws HibernateException {
        BaseMain main = null;
        String sql = "from " + object.getClass().getName() + " main where main.sheetId ='"
                + sheetId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() > 0) {
            main = (BaseMain) list.get(0);
        }
        return main;
    }

    public void removeMain(Object mainObject) throws HibernateException {
        getHibernateTemplate().delete(mainObject);
    }

    /**
     * 整合用
     */
    public List getDraftListByUserIds(String userId, final Integer curPage, final Integer pageSize, int[] aTotal, Object object) throws HibernateException {
        final String hql = " from " + object.getClass().getName() + " as main where main.status = " + Constants.SHEET_DRAFT
                + " and main.sendUserId = '" + userId + "' order by main.sendTime desc";

        String countHql = "select count(*) from " + object.getClass().getName() + " as main where main.status = " + Constants.SHEET_DRAFT
                + " and main.sendUserId = '" + userId + "'";

        aTotal[0] = ((Integer) getHibernateTemplate().find(countHql).listIterator().next()).intValue();

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(hql);
                //分页查询条
                query.setFirstResult(pageSize.intValue()
                        * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                return query.list();
            }
        };

        return (List) getHibernateTemplate().execute(callback);
    }

}
