
package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplateAssess;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateAssessDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiTemplateAssessDaoHibernate extends BaseDaoHibernate implements TawSupplierkpiTemplateAssessDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiTemplateAssessDao#getTawSupplierkpiTemplateAssesss(com.boco.eoms.commons.sample.model.TawSupplierkpiTemplateAssess)
     */
    public List getTawSupplierkpiTemplateAssesss(final TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess) {
        return getHibernateTemplate().find("from TawSupplierkpiTemplateAssess");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSupplierkpiTemplateAssess == null) {
            return getHibernateTemplate().find("from TawSupplierkpiTemplateAssess");
        } else {
            // filter on properties set in the tawSupplierkpiTemplateAssess
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSupplierkpiTemplateAssess).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSupplierkpiTemplateAssess.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiTemplateAssessDao#getTawSupplierkpiTemplateAssess(String id)
     */
    public TawSupplierkpiTemplateAssess getTawSupplierkpiTemplateAssess(final String id) {
        TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = (TawSupplierkpiTemplateAssess) getHibernateTemplate().get(TawSupplierkpiTemplateAssess.class, id);
        if (tawSupplierkpiTemplateAssess == null) {
            throw new ObjectRetrievalFailureException(TawSupplierkpiTemplateAssess.class, id);
        }

        return tawSupplierkpiTemplateAssess;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiTemplateAssessDao#saveTawSupplierkpiTemplateAssess(TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess)
     */    
    public void saveTawSupplierkpiTemplateAssess(final TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess) {
        if ((tawSupplierkpiTemplateAssess.getId() == null) || (tawSupplierkpiTemplateAssess.getId().equals("")))
			getHibernateTemplate().save(tawSupplierkpiTemplateAssess);
		else
			getHibernateTemplate().saveOrUpdate(tawSupplierkpiTemplateAssess);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiTemplateAssessDao#removeTawSupplierkpiTemplateAssess(String id)
     */
    public void removeTawSupplierkpiTemplateAssess(final String id) {
        getHibernateTemplate().delete(getTawSupplierkpiTemplateAssess(id));
    }
    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSupplierkpiTemplateAssesss(final int curPage, final int pageSize,final String whereStr) {
        // filter on properties set in the tawSupplierkpiTemplateAssess
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSupplierkpiTemplateAssess";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize
									* curPage);
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
    public Map getTawSupplierkpiTemplateAssesss(final int curPage, final int pageSize) {
			return this.getTawSupplierkpiTemplateAssesss(curPage,pageSize,null);
		}

    public TawSupplierkpiTemplateAssess getTawSupplierkpiTemplateAssessByTemplateId(final String templateId) {
    	HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSupplierkpiTemplateAssess where templateId='" + templateId + "'";
				Query query = session.createQuery(queryStr);
				query.setFirstResult(0);
                query.setMaxResults(1);
				List list = query.list();
				TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = new TawSupplierkpiTemplateAssess();

                if (list != null && !list.isEmpty()) {
                	tawSupplierkpiTemplateAssess = (TawSupplierkpiTemplateAssess) list.iterator().next();
                }
                return tawSupplierkpiTemplateAssess;
            }
        };
        return (TawSupplierkpiTemplateAssess) getHibernateTemplate().execute(callback);
    }
    
    public List getNodesFromTemplateAssess(final String whereStr) {
    	return getHibernateTemplate().find(whereStr);
    }
}
