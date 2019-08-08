/**
 *
 */
package com.boco.eoms.sheet.sheetkpi.dao.hibernate;

import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.oro.text.regex.StringSubstitution;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.sheet.base.dao.hibernate.*;
import com.boco.eoms.sheet.base.util.XMLPropertyFile;
import com.boco.eoms.sheet.sheetkpi.dao.ISheetKpiBaseDao;

/**
 * @author Administrator
 *
 */
public class SheetKpiBaseDao extends BaseSheetDaoHibernate implements
        ISheetKpiBaseDao {

    public List getReportByDept(final String hsql, String filename) {
        System.out.println("RepportSql:" + hsql);

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Connection con = null;
                Statement sta = null;
                ResultSet rs = null;
                PreparedStatement ps = null;
                ResultSetMetaData rsmd = null;
                List list = new ArrayList();
                StringBuffer sql = new StringBuffer(hsql);
                try {
                    con = session.connection();
                    sta = con.createStatement();
                    rs = sta.executeQuery(sql.toString());
                    rsmd = rs.getMetaData();
                    int column = rsmd.getColumnCount();
                    Map map = new HashMap();
                    while (rs.next()) {
                        map = new HashMap(column);
                        for (int i = 1; i <= column; i++) {
                            map.put(rsmd.getColumnName(i), rs.getObject(i));
                        }
                        list.add(map);
                    }


                } catch (DataAccessResourceFailureException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (sta != null) {
                            sta.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /* （非 Javadoc）
     * @see com.boco.eoms.sheet.kpi.dao.IKpiBaseDAO#getQuerySheetByCondition(java.lang.String, java.lang.Integer, java.lang.Integer, int[], java.lang.String)
     */
    public List getQuerySheetByCondition(final String hsql,
                                         final Integer curPage, final Integer pageSize, int[] aTotal,
                                         String dept) {
        System.out.println("sql:" + hsql);
        if (aTotal[0] <= 0) {
            aTotal[0] = count(hsql).intValue();
            if (aTotal[0] <= 0) {
                return null;
            }
        }
//		if(aTotal[0]>=7000){
//			return null;
//		}

        final int total = aTotal[0];
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                Connection con = null;
                Statement sta = null;
                ResultSet rs = null;
                PreparedStatement ps = null;
                ResultSetMetaData rsmd = null;
                List list = new ArrayList();
                StringBuffer sql = new StringBuffer(hsql);
                sql.insert(6, " rownum rn,");
                sql.insert(0, "select * from (").append(") where rn >? and rn<=?");
                try {
                    con = session.connection();
                    ps = con.prepareStatement(sql.toString());
//					ps
//					sta = con.createStatement(
//							ResultSet.TYPE_SCROLL_INSENSITIVE,
//							ResultSet.CONCUR_READ_ONLY);
//					// con.c
//					rs = sta.executeQuery(hsql);
                    System.out.println(sql.toString());
                    // 获取返回结果集的总列数
                    int index = pageSize.intValue();
                    System.out.println("index" + index);
                    if (curPage.intValue() * index > 0) {
//						rs.absolute(curPage.intValue() * pageSize.intValue());
                        ps.setInt(1, curPage.intValue() * index);
                        System.out.println("最小值" + curPage.intValue() * index);
                    } else {
//						rs.absolute(1);
                        ps.setInt(1, 0);
                    }
                    if (index == -1) {
                        ps.setInt(2, total);
                    } else {
                        ps.setInt(2, (curPage.intValue() + 1) * index);
                        System.out.println("最大值" + (curPage.intValue() + 1) * index);
                    }
                    rs = ps.executeQuery();
                    rsmd = rs.getMetaData();
                    int size = rsmd.getColumnCount();
                    while (rs.next()) {
                        Map map = new HashMap();
                        for (int i = 1; i <= size; i++) {
                            String column = rsmd.getColumnName(i);
                            int type = rsmd.getColumnType(i);
                            if (type != java.sql.Types.DATE) {
                                map.put(column, rs.getString(column));
                            } else {
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Timestamp date = rs.getTimestamp(column);
                                if (date != null) {
                                    map.put(column, df.format(date));
                                } else {
                                    map.put(column, "");
                                }
                            }
                        }
                        list.add(map);
                    }

                } catch (DataAccessResourceFailureException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    try {
                        if (rsmd != null) {
                            rs.close();
                        }
                        if (sta != null) {
                            sta.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return list;
            }
        };
        return (List) getHibernateTemplate().execute(callback);

    }


    public Integer count(final String hsql) throws HibernateException {

        final String countStr = "select count(*) counts from (" + hsql + ")";
        System.out.println("countsql:" + countStr);
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                Connection con = null;
                Statement sta = null;
                ResultSet rs = null;
                Integer count = new Integer(0);
                try {
                    con = session.connection();
                    sta = con.createStatement();
                    rs = sta.executeQuery(countStr);
                    if (rs.next()) {
                        count = new Integer(rs.getInt("counts"));
                    }

                } catch (DataAccessResourceFailureException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (sta != null) {
                            sta.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return count;
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /* （非 Javadoc）
     * @see com.boco.eoms.sheet.kpi.dao.IKpiBaseDAO#getXMLList(java.lang.String)
     */
    public List getXMLList(String xmlName) throws HibernateException {

        List map = new ArrayList();
        URL resourceUrl = ISheetKpiBaseDao.class.getClassLoader().getResource(
                "com/boco/eoms/sheet/sheetkpi/xmlFile/" + xmlName + ".xml");
        // XMLPropertyFile xml = XMLPropertyFile
        // .getInstance(resourceUrl.getPath());
        XMLProperties xml = new XMLProperties();
        System.out.println("url:" + resourceUrl.getPath());
        xml.loadFile(resourceUrl.getPath());
        String cnName = StaticMethod.null2String(xml.getProperty("cnname"), "");
        String enName = StaticMethod.null2String(xml.getProperty("enname"), "");
        String sql = StaticMethod.null2String(xml.getProperty("sql"), "");
        String url = StaticMethod.null2String(xml.getProperty("a"), "");
        String[] cnNames = cnName.split(",");
        String[] enNames = enName.split(",");
        String[] urls = url.split(",");
        map.add(sql);
        if (cnNames.length == enNames.length && enNames.length > 0) {
            for (int i = 0; i < cnNames.length; i++) {
                if (i >= urls.length) {
                    map.add(cnNames[i].trim() + "," + "," + enNames[i].toUpperCase().trim());
                } else {
                    map.add(cnNames[i].trim() + "," + urls[i].trim() + ","
                            + enNames[i].toUpperCase().trim());
                }
            }
        }
        return map;
    }


}
