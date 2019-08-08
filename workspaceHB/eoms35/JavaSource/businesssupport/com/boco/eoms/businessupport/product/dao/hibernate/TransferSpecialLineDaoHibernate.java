
package com.boco.eoms.businessupport.product.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.businessupport.product.dao.ITransferSpecialLineDao;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.sheet.base.dao.hibernate.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TransferSpecialLineDaoHibernate extends BaseSheetDaoHibernate implements ITransferSpecialLineDao {

    /**
     * @see com.boco.eoms.sheet.ipspecialline.dao.TransferSpecialLineDao#getTransferSpecialLines(com.boco.eoms.sheet.ipspecialline.model.IPSpecialLine)
     */
    public List getTransferSpecialLines() {
        return getHibernateTemplate().find("from TransferSpecialLine as trans where trans.deleted = 0");

    }

    public List getTransferSpecialLinesDeleted() {
        return getHibernateTemplate().find("from TransferSpecialLine as trans where trans.deleted = 1");

    }

    public TransferSpecialLine getTransferSpecialLine(final String id) {
        TransferSpecialLine transferspecialline = (TransferSpecialLine) getHibernateTemplate().get(TransferSpecialLine.class, id);
        if (transferspecialline == null) {
            throw new ObjectRetrievalFailureException(TransferSpecialLine.class, id);
        }
        return transferspecialline;
    }

    public void saveTransferSpecialLine(final TransferSpecialLine transferspecialline) {
        if ((transferspecialline.getId() == null) || (transferspecialline.getId().equals("")))
            getHibernateTemplate().save(transferspecialline);
        else
            getHibernateTemplate().saveOrUpdate(transferspecialline);
    }

    public void removeTransferSpecialLine(final String id) {
        TransferSpecialLine transfer = getTransferSpecialLine(id);
        transfer.setDeleted(new Integer(1));
        getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(transfer);
    }

    public void restoreTransferSpecialLine(final String id) {
        TransferSpecialLine trans = getTransferSpecialLine(id);
        //nbp.setDeleted(new Integer(0));
        getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
        getHibernateTemplate().saveOrUpdate(trans);
    }

    public Map getTransferSpecialLines(final Integer curPage, final Integer pageSize, final String whereStr) {
        // filter on properties set in the ipspecialline
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from TransferSpecialLine trans";
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

    public Map getTransferSpecialLines(final Integer curPage, final Integer pageSize) {
        return this.getTransferSpecialLines(curPage, pageSize, null);
    }

    public ArrayList getChildList(String parentId) {
        String hql = " from TransferSpecialLine trans where trans.parentId='"
                + parentId + "' order by trans.name";
        return (ArrayList) getHibernateTemplate().find(hql);
    }

    public List getTransferSpecialLinesByHql(String hql) {
        return getHibernateTemplate().find(hql);
    }

    public List getSpecialLines(String id) {
        String sql = "from TransferSpecialLine trans where trans.ipSpecialLine_Id ='"
                + id + "'";
        return getHibernateTemplate().find(sql);

    }

    public Map getQueryTransferSpecialLines(final Map queryMap, final Integer curPage, final Integer pageSize,
                                            final String whereStr) {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStrItem = " where 1=1 and transSpecialLine.deleted<>1";
                String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
                Set keySet = queryMap.keySet();
                Iterator it = keySet.iterator();
                if (hqlDialect.equals("org.hibernate.dialect.OracleDialect")) {
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        if (queryMap.get(key) != null && !queryMap.get(key).equals("")) {
                            if (key.equals("creatTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.creatTime>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("creatTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.creatTime<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("completeLimitStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.completeLimit>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("completeLimitEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.completeLimit<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("changeTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.changeTime>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("changeTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.changeTime<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("cancelTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.cancelTime>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("cancelTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.cancelTime<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("endTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.endTime>=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else if (key.equals("endTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.endTime<=to_date('" + queryMap.get(key) + "'" + ",'yyyy-MM-dd hh24:mi:ss')";
                            } else {
                                queryStrItem = queryStrItem + " and ipSpecialLine." + key + " like" + " '%" + queryMap.get(key) + "%'";
                            }
                        }
                    }
                } else {
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        if (queryMap.get(key) != null && !queryMap.get(key).equals("")) {
                            if (key.equals("creatTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.creatTime>='" + queryMap.get(key) + "'";
                            } else if (key.equals("creatTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.creatTime<='" + queryMap.get(key) + "'";
                            } else if (key.equals("completeLimitStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.completeLimit>='" + queryMap.get(key) + "'";
                            } else if (key.equals("completeLimitEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.completeLimit<='" + queryMap.get(key) + "'";
                            } else if (key.equals("changeTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.changeTime>='" + queryMap.get(key) + "'";
                            } else if (key.equals("changeTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.changeTime<='" + queryMap.get(key) + "'";
                            } else if (key.equals("cancelTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.cancelTime>='" + queryMap.get(key) + "'";
                            } else if (key.equals("cancelTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.cancelTime<='" + queryMap.get(key) + "'";
                            } else if (key.equals("endTimeStartDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.endTime>='" + queryMap.get(key) + "'";
                            } else if (key.equals("endTimeEndDateExpression")) {
                                queryStrItem = queryStrItem + " and ipSpecialLine.endTime<='" + queryMap.get(key) + "'";
                            } else {
                                queryStrItem = queryStrItem + " and ipSpecialLine." + key + " like" + " '%" + queryMap.get(key) + "%'";
                            }
                        }
                    }
                }

                String queryStr = "from TransferSpecialLine transSpecialLine " + queryStrItem;
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

    public void saveOrUpdate(TransferSpecialLine transferspecialline) throws HibernateException {
        getHibernateTemplate().saveOrUpdate(transferspecialline);
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    /**
     * 根据电路名称得到该对象 modify by shichuangke
     */
    public TransferSpecialLine getTransferByCircuitName(String circuitName) throws HibernateException {
        String sql = "from TransferSpecialLine as trans where trans.deleted = 0 and trans.circuitName='" + circuitName + "'";
        List list = getHibernateTemplate().find(sql);
        if (list != null && list.size() > 0) {
            TransferSpecialLine TransferSpecialLine = (TransferSpecialLine) list.get(0);
            return TransferSpecialLine;
        } else {
            return null;
        }
    }

    /**
     * 通过A端站点名称和Z端站点名称查找电路
     *
     * @param siteNameA
     * @param siteNameZ
     * @return
     */
    public List getSpecialLinesBySiteName(String siteNameA, String siteNameZ) throws Exception {
        String sql = "from TransferSpecialLine t where t.siteNameA ='" + siteNameA + "' and t.siteNameZ='" + siteNameZ + "'";
        return getHibernateTemplate().find(sql);
    }

    /**
     * 通过Z端业务设备端口查找电路
     */
    public List getSpecialLineByZPort(String portZBDeviceName, String portZBDevicePort) throws Exception {
        String sql = "from TransferSpecialLine t where t.portZBDevicePort ='" + portZBDevicePort + "' and t.portZBDeviceName='" + portZBDeviceName + "'";
        return getHibernateTemplate().find(sql);
    }
}