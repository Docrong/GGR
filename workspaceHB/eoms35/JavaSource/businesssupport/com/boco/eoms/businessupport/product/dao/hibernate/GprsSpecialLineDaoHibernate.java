
package com.boco.eoms.businessupport.product.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.businessupport.product.dao.IGprsSpecialLineDao;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.sheet.base.dao.hibernate.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class GprsSpecialLineDaoHibernate extends BaseSheetDaoHibernate implements IGprsSpecialLineDao {

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.GprsSpecialLineDao#getGprsSpecialLines(com.boco.eoms.sheet.gprsspecialline.model.GprsSpecialLine)
     */
    public List getGprsSpecialLines() {
        return getHibernateTemplate().find("from GprsSpecialLine as nbp where nbp.deleted = 0");

    }

    public List getGprsSpecialLinesDeleted() {
        return getHibernateTemplate().find("from GprsSpecialLine as nbp where nbp.deleted = 1");

    }

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.GprsSpecialLineDao#getGprsSpecialLine(String id)
     */
    public GprsSpecialLine getGprsSpecialLine(final String id) {
        GprsSpecialLine gprsspecialline = (GprsSpecialLine) getHibernateTemplate().get(GprsSpecialLine.class, id);
        if (gprsspecialline == null) {
            throw new ObjectRetrievalFailureException(GprsSpecialLine.class, id);
        }
        return gprsspecialline;
    }

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.GprsSpecialLineDao#saveGprsSpecialLine(GprsSpecialLine gprsspecialline)
     */
    public void saveGprsSpecialLine(final GprsSpecialLine gprsspecialline) {
        if ((gprsspecialline.getId() == null) || (gprsspecialline.getId().equals("")))
            getHibernateTemplate().save(gprsspecialline);
        else
            getHibernateTemplate().saveOrUpdate(gprsspecialline);
    }

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.GprsSpecialLineDao#removeGprsSpecialLine(String id)
     */
    public void removeGprsSpecialLine(final String id) {
        GprsSpecialLine nbp = getGprsSpecialLine(id);
        //nbp.setDeleted(new Integer(1));
        getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.GprsSpecialLineDao#removeGprsSpecialLine(String id)
     */
    public void restoreGprsSpecialLine(final String id) {
        GprsSpecialLine nbp = getGprsSpecialLine(id);
        //nbp.setDeleted(new Integer(0));
        getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(nbp);
    }

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.GprsSpecialLineDao#getGprsSpecialLines(final Integer curPage, final Integer pageSize, final String whereStr)
     */
    public Map getGprsSpecialLines(final Integer curPage, final Integer pageSize, final String whereStr) {
        // filter on properties set in the gprsspecialline
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from GprsSpecialLine nbp";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                Integer total = (Integer) session.createQuery(queryCountStr).iterate()
                        .next();
                Query query = session.createQuery(queryStr + "");
                query.setFirstResult(pageSize.intValue()
                        * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.GprsSpecialLineDao#getGprsSpecialLines(final Integer curPage, final Integer pageSize)
     */
    public Map getGprsSpecialLines(final Integer curPage, final Integer pageSize) {
        return this.getGprsSpecialLines(curPage, pageSize, null);
    }

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.GprsSpecialLineDao#getChildList(String parentId)
     */
    public ArrayList getChildList(String parentId) {
        String hql = " from GprsSpecialLine obj where obj.parentId='"
                + parentId + "' order by obj.name";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    /**
     * @see com.boco.eoms.sheet.gprsspecialline.dao.IGprsSpecialLineDao#getGprsSpecialLinesByCondition(java.lang.String)
     */
    public List getGprsSpecialLinesByHql(String hql) {
        return getHibernateTemplate().find(hql);
    }

    public List getSpecialLines(String id) {
        String sql = "from GprsSpecialLine gprsSpecialLine where gprsSpecialLine.gprsSpecialLine_Id ='"
                + id + "'";
        return getHibernateTemplate().find(sql);

    }

    public Map getQueryGprsSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
                                        final String whereStr) {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStrItem = " where 1=1 and gprsSpecialLine.deleted<>1";
                String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
                Set keySet = queryMap.keySet();
                Iterator it = keySet.iterator();
                if (hqlDialect.equals("org.hibernate.dialect.OracleDialect")) {
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        if (queryMap.get(key) != null && !queryMap.get(key).equals("")) {
                            if (key.equals("creatTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.creatTime>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("creatTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.creatTime<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("completeLimitStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.completeLimit>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("completeLimitEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.completeLimit<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("changeTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.changeTime>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("changeTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.changeTime<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("cancelTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.cancelTime>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("cancelTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.cancelTime<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("endTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.endTime>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("endTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.endTime<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else {
                                queryStrItem = queryStrItem + " and gprsSpecialLine." + key + " like" + " '%" + queryMap.get(key) + "%'";
                            }
                        }
                    }
                } else {
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        if (queryMap.get(key) != null && !queryMap.get(key).equals("")) {
                            if (key.equals("creatTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.creatTime>='" + queryMap.get(key) + "'";
                            } else if (key.equals("creatTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.creatTime<='" + queryMap.get(key) + "'";
                            } else if (key.equals("completeLimitStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.completeLimit>='" + queryMap.get(key) + "'";
                            } else if (key.equals("completeLimitEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.completeLimit<='" + queryMap.get(key) + "'";
                            } else if (key.equals("changeTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.changeTime>='" + queryMap.get(key) + "'";
                            } else if (key.equals("changeTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.changeTime<='" + queryMap.get(key) + "'";
                            } else if (key.equals("cancelTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.cancelTime>='" + queryMap.get(key) + "'";
                            } else if (key.equals("cancelTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.cancelTime<='" + queryMap.get(key) + "'";
                            } else if (key.equals("endTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.endTime>='" + queryMap.get(key) + "'";
                            } else if (key.equals("endTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and gprsSpecialLine.endTime<='" + queryMap.get(key) + "'";
                            } else {
                                queryStrItem = queryStrItem + " and gprsSpecialLine." + key + " like" + " '%" + queryMap.get(key) + "%'";
                            }
                        }
                    }
                }

                String queryStr = "from GprsSpecialLine gprsSpecialLine " + queryStrItem;
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                int total = ((Integer) session.createQuery(queryCountStr)
                        .iterate().next()).intValue();
                Query query = session.createQuery(queryStr);
                query
                        .setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));
                query.setMaxResults(pageSize.intValue());
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", new Integer(total));
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public void saveOrUpdate(GprsSpecialLine gprsspecialline) throws HibernateException {
        getHibernateTemplate().saveOrUpdate(gprsspecialline);
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    /**
     * 通过A端站点名称和Z端站点名称查找电路
     *
     * @param siteNameA
     * @param siteNameZ
     * @return
     */
    public List getSpecialLinesBySiteName(String siteNameA, String siteNameZ) throws Exception {
        String sql = "from GprsSpecialLine gprsSpecialLine where gprsSpecialLine.siteNameA ='" + siteNameA + "' and gprsSpecialLine.siteNameZ='" + siteNameZ + "'";
        return getHibernateTemplate().find(sql);
    }

    /**
     * 通过Z端业务设备端口查找电路
     */
    public List getSpecialLineByZPort(String portZBDeviceName, String portZBDevicePort) throws Exception {
        String sql = "from GprsSpecialLine t where t.portZBDevicePort ='" + portZBDevicePort + "' and t.portZBDeviceName='" + portZBDeviceName + "'";
        return getHibernateTemplate().find(sql);
    }
}
