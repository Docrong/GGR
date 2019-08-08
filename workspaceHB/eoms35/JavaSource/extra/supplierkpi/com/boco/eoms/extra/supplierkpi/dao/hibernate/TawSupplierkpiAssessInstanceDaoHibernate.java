
package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiAssessInstanceDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiAssessInstanceDaoHibernate extends BaseDaoHibernate implements TawSupplierkpiAssessInstanceDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiAssessInstanceDao#getTawSupplierkpiAssessInstances(com.boco.eoms.commons.sample.model.TawSupplierkpiAssessInstance)
     */
    public List getTawSupplierkpiAssessInstances(final TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance) {
        return getHibernateTemplate().find("from TawSupplierkpiAssessInstance");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSupplierkpiAssessInstance == null) {
            return getHibernateTemplate().find("from TawSupplierkpiAssessInstance");
        } else {
            // filter on properties set in the tawSupplierkpiAssessInstance
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSupplierkpiAssessInstance).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSupplierkpiAssessInstance.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiAssessInstanceDao#getTawSupplierkpiAssessInstance(String id)
     */
    public TawSupplierkpiAssessInstance getTawSupplierkpiAssessInstance(final String id) {
        TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = (TawSupplierkpiAssessInstance) getHibernateTemplate().get(TawSupplierkpiAssessInstance.class, id);
        if (tawSupplierkpiAssessInstance == null) {
            throw new ObjectRetrievalFailureException(TawSupplierkpiAssessInstance.class, id);
        }

        return tawSupplierkpiAssessInstance;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiAssessInstanceDao#saveTawSupplierkpiAssessInstance(TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance)
     */
    public String saveTawSupplierkpiAssessInstance(final TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance) {
        if ((tawSupplierkpiAssessInstance.getId() == null) || (tawSupplierkpiAssessInstance.getId().equals("")))
            getHibernateTemplate().save(tawSupplierkpiAssessInstance);
        else
            getHibernateTemplate().saveOrUpdate(tawSupplierkpiAssessInstance);
        return tawSupplierkpiAssessInstance.getId();
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiAssessInstanceDao#removeTawSupplierkpiAssessInstance(String id)
     */
    public void removeTawSupplierkpiAssessInstance(final String id) {
        getHibernateTemplate().delete(getTawSupplierkpiAssessInstance(id));
    }

    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSupplierkpiAssessInstances(final int curPage, final int pageSize, final String whereStr) {
        // filter on properties set in the tawSupplierkpiAssessInstance
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from TawSupplierkpiAssessInstance";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                Integer total = (Integer) session.createQuery(queryCountStr).iterate()
                        .next();
                Query query = session.createQuery(queryStr);
                query.setFirstResult(pageSize
                        * (curPage));
                query.setMaxResults(pageSize);
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getTawSupplierkpiAssessInstances(final int curPage, final int pageSize) {
        return this.getTawSupplierkpiAssessInstances(curPage, pageSize, null);
    }

    public List getSupplierkpi(final String supplierId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from TawSupplierkpiItem" +
                        " where id in " +
                        " (select kpiItemId from TawSupplierkpiRelation " +
                        " where assessInstanceId in" +
                        "(select id from TawSupplierkpiAssessInstance " +
                        "where supplierId='" + supplierId + "'))";

                Query query = session.createQuery(queryStr);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public String getIdBySpecialAndSupplier(final String specialType, final String supplierId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "select id from TawSupplierkpiAssessInstance where supplierid='" + supplierId + "'";
                if ((specialType != null) && (!"".equals(specialType))) {
                    queryStr += " and specialtype='" + specialType + "'";
                }
                Query query = session.createQuery(queryStr);
                List result = query.list();
                String assessInstanceId = "";
                if (result.size() > 0) {
                    assessInstanceId = result.get(0).toString();
                }
                return assessInstanceId;
            }
        };
        return getHibernateTemplate().execute(callback).toString();
    }

    public List getCustomSuppliers(final String specialType) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from TawSupplierkpiInfo where id in (select distinct(supplierId) from TawSupplierkpiAssessInstance where specialType='" + specialType + "')";
                Query query = session.createQuery(queryStr);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public List getSpecialsBySupplierId(final String supplierId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "select distinct(specialType) from TawSupplierkpiAssessInstance where supplierId='" + supplierId + "' and id in(select distinct(assessInstanceId) from TawSupplierkpiRelation)";
                Query query = session.createQuery(queryStr);
                List result = query.list();
                return result;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

}
