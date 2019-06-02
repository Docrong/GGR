
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmLogUnite;
import com.boco.eoms.duty.dao.ITawRmLogUniteDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmLogUniteDaoHibernate extends BaseDaoHibernate implements ITawRmLogUniteDao {

    /**
     * @see com.boco.eoms.duty.dao.TawRmLogUniteDao#getTawRmLogUnites(com.boco.eoms.duty.model.TawRmLogUnite)
     */
    public List getTawRmLogUnites(final TawRmLogUnite tawRmLogUnite) {
        return getHibernateTemplate().find("from TawRmLogUnite");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmLogUnite == null) {
            return getHibernateTemplate().find("from TawRmLogUnite");
        } else {
            // filter on properties set in the tawRmLogUnite
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmLogUnite).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmLogUnite.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmLogUniteDao#getTawRmLogUnite(String id)
     */
    public TawRmLogUnite getTawRmLogUnite(final String id) {
        TawRmLogUnite tawRmLogUnite = (TawRmLogUnite) getHibernateTemplate().get(TawRmLogUnite.class, id);
        if (tawRmLogUnite == null) {
            throw new ObjectRetrievalFailureException(TawRmLogUnite.class, id);
        }

        return tawRmLogUnite;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmLogUniteDao#saveTawRmLogUnite(TawRmLogUnite tawRmLogUnite)
     */    
    public void saveTawRmLogUnite(final TawRmLogUnite tawRmLogUnite) {
        if ((tawRmLogUnite.getId() == null) || (tawRmLogUnite.getId().equals("")))
			getHibernateTemplate().save(tawRmLogUnite);
		else
			getHibernateTemplate().saveOrUpdate(tawRmLogUnite);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmLogUniteDao#removeTawRmLogUnite(String id)
     */
    public void removeTawRmLogUnite(final String id) {
        getHibernateTemplate().delete(getTawRmLogUnite(id));
    }
    /**
     * @see com.boco.eoms.duty.dao.TawRmLogUniteDao#getTawRmLogUnites(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmLogUnites(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmLogUnite
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmLogUnite";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
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
     * @see com.boco.eoms.duty.dao.TawRmLogUniteDao#getTawRmLogUnites(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmLogUnites(final Integer curPage, final Integer pageSize) {
			return this.getTawRmLogUnites(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmLogUniteDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmLogUnite obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
