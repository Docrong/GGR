
package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiRelationDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiRelationDaoHibernate extends BaseDaoHibernate implements TawSupplierkpiRelationDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiRelationDao#getTawSupplierkpiRelations(com.boco.eoms.commons.sample.model.TawSupplierkpiRelation)
     */
    public List getTawSupplierkpiRelations(final TawSupplierkpiRelation tawSupplierkpiRelation) {
        return getHibernateTemplate().find("from TawSupplierkpiRelation");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSupplierkpiRelation == null) {
            return getHibernateTemplate().find("from TawSupplierkpiRelation");
        } else {
            // filter on properties set in the tawSupplierkpiRelation
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSupplierkpiRelation).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSupplierkpiRelation.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiRelationDao#getTawSupplierkpiRelation(String id)
     */
    public TawSupplierkpiRelation getTawSupplierkpiRelation(final String id) {
        TawSupplierkpiRelation tawSupplierkpiRelation = (TawSupplierkpiRelation) getHibernateTemplate().get(TawSupplierkpiRelation.class, id);
        if (tawSupplierkpiRelation == null) {
            throw new ObjectRetrievalFailureException(TawSupplierkpiRelation.class, id);
        }

        return tawSupplierkpiRelation;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiRelationDao#saveTawSupplierkpiRelation(TawSupplierkpiRelation tawSupplierkpiRelation)
     */    
    public void saveTawSupplierkpiRelation(final TawSupplierkpiRelation tawSupplierkpiRelation) {
        if ((tawSupplierkpiRelation.getId() == null) || (tawSupplierkpiRelation.getId().equals("")))
			getHibernateTemplate().save(tawSupplierkpiRelation);
		else
			getHibernateTemplate().saveOrUpdate(tawSupplierkpiRelation);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiRelationDao#removeTawSupplierkpiRelation(String id)
     */
    public void removeTawSupplierkpiRelation(final String id) {
        getHibernateTemplate().delete(getTawSupplierkpiRelation(id));
    }
    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSupplierkpiRelations(final int curPage, final int pageSize,final String whereStr) {
        // filter on properties set in the tawSupplierkpiRelation
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSupplierkpiRelation";
              if(whereStr!=null && whereStr.length()>0)
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
    public Map getTawSupplierkpiRelations(final int curPage, final int pageSize) {
			return this.getTawSupplierkpiRelations(curPage,pageSize,null);
		}
    public List getTawSupplierkpiRelationsByAssessInstanceId(final String assessInstanceId) {
    	HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSupplierkpiRelation where assessinstanceid='" + assessInstanceId + "'";
              Query query = session.createQuery(queryStr);
			  List result = query.list();
              return result;
            }
        };
        return (List)getHibernateTemplate().execute(callback);
    }

    public String getIdByAssessInstanceIdAndKpiId(final String assessInstanceId, final String kpiId) {
    	HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSupplierkpiRelation where assessInstanceId='" + assessInstanceId + "' and kpiItemId='" + kpiId + "'";
              Query query = session.createQuery(queryStr);
              query.setFirstResult(0);
              query.setMaxResults(1);
			  List list = query.list();
			  TawSupplierkpiRelation tawSupplierkpiRelation = null;
			  if (list != null && !list.isEmpty()) {
              	tawSupplierkpiRelation = (TawSupplierkpiRelation) list.iterator().next();
              }
              return tawSupplierkpiRelation.getId();
            }
        };
        return getHibernateTemplate().execute(callback).toString();
    }
    
    public List getTawSupplierkpiRelationsByKpiId(final String kpiId) {
    	HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSupplierkpiRelation where kpiItemId='" + kpiId + "'";
              Query query = session.createQuery(queryStr);
			  List result = query.list();
              return result;
            }
        };
        return (List)getHibernateTemplate().execute(callback);
    }
}
