
package com.boco.eoms.otherwise.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.otherwise.model.TawRmRenewal;
import com.boco.eoms.otherwise.dao.ITawRmRenewalDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmRenewalDaoHibernate extends BaseDaoHibernate implements ITawRmRenewalDao {

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmRenewalDao#getTawRmRenewals(com.boco.eoms.otherwise.model.TawRmRenewal)
     */
    public List getTawRmRenewals(final TawRmRenewal tawRmRenewal) {
        return getHibernateTemplate().find("from TawRmRenewal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmRenewal == null) {
            return getHibernateTemplate().find("from TawRmRenewal");
        } else {
            // filter on properties set in the tawRmRenewal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmRenewal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmRenewal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmRenewalDao#getTawRmRenewal(String id)
     */
    public TawRmRenewal getTawRmRenewal(final String id) {
        TawRmRenewal tawRmRenewal = (TawRmRenewal) getHibernateTemplate().get(TawRmRenewal.class, id);
        if (tawRmRenewal == null) {
            throw new ObjectRetrievalFailureException(TawRmRenewal.class, id);
        }

        return tawRmRenewal;
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmRenewalDao#saveTawRmRenewal(TawRmRenewal tawRmRenewal)
     */    
    public void saveTawRmRenewal(final TawRmRenewal tawRmRenewal) {
        if ((tawRmRenewal.getId() == null) || (tawRmRenewal.getId().equals("")))
			getHibernateTemplate().save(tawRmRenewal);
		else
			getHibernateTemplate().saveOrUpdate(tawRmRenewal);
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmRenewalDao#removeTawRmRenewal(String id)
     */
    public void removeTawRmRenewal(final String id) {
        getHibernateTemplate().delete(getTawRmRenewal(id));
    }
    /**
     * @see com.boco.eoms.otherwise.dao.TawRmRenewalDao#getTawRmRenewals(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmRenewals(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmRenewal
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmRenewal";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
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
     * @see com.boco.eoms.otherwise.dao.TawRmRenewalDao#getTawRmRenewals(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmRenewals(final Integer curPage, final Integer pageSize) {
			return this.getTawRmRenewals(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.otherwise.dao.TawRmRenewalDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmRenewal obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
