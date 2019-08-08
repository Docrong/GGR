// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PreAllocatedNewDaoHibernate.java

package com.boco.eoms.sheetflowline.dao.hibernate;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheetflowline.dao.IPreAllocatedNewDao;
import com.boco.eoms.sheetflowline.model.PreAllocatedNew;
import com.boco.eoms.sheetflowline.model.PreAllocatedSepcail;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class PreAllocatedNewDaoHibernate extends BaseDaoHibernate
        implements IPreAllocatedNewDao {

    public PreAllocatedNewDaoHibernate() {
    }

    public void deleteObject(PreAllocatedNew object)
            throws HibernateException {
        getHibernateTemplate().delete(object);
    }

    public PreAllocatedNew getPreAllocated(String id)
            throws HibernateException {
        return (PreAllocatedNew) getHibernateTemplate().get(com.boco.eoms.sheetflowline.model.PreAllocatedNew.class, id);
    }

    public Map listPreAllocated(final Integer curPage, final Integer pageSize)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                HashMap map = new HashMap();
                try {
                    String queryStr = "from PreAllocatedNew order by startTime desc";
                    String queryCountStr = "select count(*) from PreAllocatedNew";
                    Query totalQuery = session.createQuery(queryCountStr);
                    List result = totalQuery.list();
                    Integer totalCount;
                    if (result != null && !result.isEmpty())
                        totalCount = (Integer) result.get(0);
                    else
                        totalCount = new Integer(0);
                    Query query = session.createQuery(queryStr);
                    if (pageSize.intValue() != -1) {
                        query.setFirstResult(pageSize.intValue() * curPage.intValue());
                        query.setMaxResults(pageSize.intValue());
                    }
                    List resultList = query.list();
                    map.put("taskTotal", totalCount);
                    map.put("taskList", resultList);
                } catch (Exception e) {
                    System.out.println("-------task list error!---------");
                    e.printStackTrace();
                    throw new HibernateException("task list error");
                }
                return map;
            }

        };
        return (HashMap) getHibernateTemplate().execute(callback);
    }

    public void saveObject(PreAllocatedNew object)
            throws HibernateException {
        getHibernateTemplate().save(object);
    }

    public void updateObject(PreAllocatedNew object)
            throws HibernateException {
        getHibernateTemplate().saveOrUpdate(object);
    }

    public Integer executeHsql(final String hsql)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query totalQuery = session.createQuery(hsql);
                return new Integer(totalQuery.executeUpdate());
            }

        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    public Map listPreAllocated(final Map map, final Integer pageIndex, final Integer pageSize)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String queryStr = "from PreAllocatedNew where id is not null ";
                    String queryCountStr = "select count(*) from PreAllocatedNew where id is not null";
                    for (Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
                        String key = StaticMethod.nullObject2String(iter.next());
                        if ("count".equals(key) || "alreadyCount".equals(key)) {
                            Integer intvalue = new Integer(StaticMethod.nullObject2String(map.get(key)));
                            if (!(new Integer("0")).equals(intvalue)) {
                                queryStr = queryStr + " and " + key + "=" + intvalue;
                                queryCountStr = queryCountStr + " and " + key + "=" + intvalue;
                            }
                        } else if ("startTime".equals(key)) {
                            if (map.get(key) != null) {
                                String startTime = StaticMethod.nullObject2String(dateFormat.format(map.get(key)));
                                queryStr = queryStr + " and " + key + ">=to_date('" + startTime + "','yyyy-MM-dd hh24:mi:ss')";
                                queryCountStr = queryCountStr + " and " + key + ">=to_date('" + startTime + "','yyyy-MM-dd hh24:mi:ss')";
                            }
                        } else if ("endTime".equals(key)) {
                            if (map.get(key) != null) {
                                String endTime = StaticMethod.nullObject2String(dateFormat.format(map.get(key)));
                                queryStr = queryStr + " and " + key + "<=to_date('" + endTime + "','yyyy-MM-dd hh24:mi:ss')";
                                queryCountStr = queryCountStr + " and " + key + "<=to_date('" + endTime + "','yyyy-MM-dd hh24:mi:ss')";
                            }
                        } else if ("dutyLeader".equals(key) || "dutyUserId".equals(key)) {
                            String value = StaticMethod.nullObject2String(map.get(key));
                            if (!"".equals(value)) {
                                value = value.replaceAll(",", "','");
                                queryStr = queryStr + " and " + key + " in('" + value + "')";
                                queryCountStr = queryCountStr + " and " + key + " in('" + value + "')";
                            }
                        } else if ("faultResponseLevel".equals(key)) {
                            String value = StaticMethod.nullObject2String(map.get(key));
                            if (!"".equals(value)) {
                                queryStr = queryStr + " and " + key + "='" + value + "'";
                                queryCountStr = queryCountStr + " and " + key + "='" + value + "'";
                            }
                        }
                    }

                    queryStr = queryStr + " order by startTime desc";
                    Query totalQuery = session.createQuery(queryCountStr);
                    List result = totalQuery.list();
                    Integer totalCount;
                    if (result != null && !result.isEmpty())
                        totalCount = (Integer) result.get(0);
                    else
                        totalCount = new Integer(0);
                    Query query = session.createQuery(queryStr);
                    if (pageSize.intValue() != -1) {
                        query.setFirstResult(pageSize.intValue() * pageIndex.intValue());
                        query.setMaxResults(pageSize.intValue());
                    }
                    List resultList = query.list();
                    map.put("taskTotal", totalCount);
                    map.put("taskList", resultList);
                } catch (Exception e) {
                    System.out.println("-------task list error!---------");
                    e.printStackTrace();
                    throw new HibernateException("task list error");
                }
                return map;
            }

        };
        return (HashMap) getHibernateTemplate().execute(callback);
    }

    public List search(String mainFaultResponseLevel, String currentTime)
            throws HibernateException {
        StringBuffer hsql = new StringBuffer();
        hsql.append("from PreAllocatedNew p where ");
        hsql.append(" (p.faultResponseLevel='" + mainFaultResponseLevel + "' or p.faultResponseLevel='' or p.faultResponseLevel is null)");
        hsql.append(" and p.startTime<=to_date('" + currentTime + "','yyyy-MM-dd hh24:mi:ss') and ( p.endTime>=to_date('" + currentTime + "','yyyy-MM-dd hh24:mi:ss') or p.endTime is null )");
        hsql.append(" order by p.faultResponseLevel desc ");
        List list = getHibernateTemplate().find(hsql.toString());
        return list;
    }

    public List getLists(String hsql)
            throws HibernateException {
        return getHibernateTemplate().find(hsql);
    }

    public List searchSpecal(String mainNetSortOne, String mainNetSortTwo)
            throws HibernateException {
        StringBuffer hsql = new StringBuffer();
        hsql.append("from PreAllocatedSepcail p where ");
        hsql.append(" (p.netTypeOne='" + mainNetSortOne + "' or p.netTypeOne='' or p.netTypeOne is null)");
        hsql.append(" order by p.netTypeOne desc ");
        List list = getHibernateTemplate().find(hsql.toString());
        return list;
    }

    public Map listNetPreAllocated(final Integer curPage, final Integer pageSize)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                HashMap map = new HashMap();
                try {
                    String queryStr = "from PreAllocatedSepcail order by createTime desc";
                    String queryCountStr = "select count(*) from PreAllocatedSepcail";
                    Query totalQuery = session.createQuery(queryCountStr);
                    List result = totalQuery.list();
                    Integer totalCount;
                    if (result != null && !result.isEmpty())
                        totalCount = (Integer) result.get(0);
                    else
                        totalCount = new Integer(0);
                    Query query = session.createQuery(queryStr);
                    if (pageSize.intValue() != -1) {
                        query.setFirstResult(pageSize.intValue() * curPage.intValue());
                        query.setMaxResults(pageSize.intValue());
                    }
                    List resultList = query.list();
                    map.put("taskTotal", totalCount);
                    map.put("taskList", resultList);
                } catch (Exception e) {
                    System.out.println("-------task list error!---------");
                    e.printStackTrace();
                    throw new HibernateException("task list error");
                }
                return map;
            }

        };
        return (HashMap) getHibernateTemplate().execute(callback);
    }

    public void saveNetObject(PreAllocatedSepcail object)
            throws HibernateException {
        getHibernateTemplate().saveOrUpdate(object);
    }

    public List searchNetRule(String specailType, String netTypeOne, String netTypeTwo)
            throws HibernateException {
        StringBuffer hsql = new StringBuffer();
        hsql.append("from PreAllocatedSepcail p where ");
        hsql.append(" p.specailType='" + specailType + "' ");
        hsql.append(" and p.netTypeOne='" + netTypeOne + "'");
        List list = getHibernateTemplate().find(hsql.toString());
        return list;
    }

    public PreAllocatedSepcail getNetPreAllocated(String id)
            throws HibernateException {
        return (PreAllocatedSepcail) getHibernateTemplate().get(com.boco.eoms.sheetflowline.model.PreAllocatedSepcail.class, id);
    }
}
