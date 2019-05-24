
package com.boco.eoms.parter.baseinfo.basemetermgr.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr;
import com.boco.eoms.parter.baseinfo.basemetermgr.dao.IBasemetermgrDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class BasemetermgrDaoHibernate extends BaseDaoHibernate implements IBasemetermgrDao {

    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.dao.BasemetermgrDao#getBasemetermgrs(com.boco.eoms.parter.baseinfo.basemetermgr.model.Basemetermgr)
     */
    public List getBasemetermgrs(final Basemetermgr basemetermgr) {
        return getHibernateTemplate().find("from Basemetermgr");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (basemetermgr == null) {
            return getHibernateTemplate().find("from Basemetermgr");
        } else {
            // filter on properties set in the basemetermgr
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(basemetermgr).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Basemetermgr.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.dao.BasemetermgrDao#getBasemetermgr(String id)
     */
    public Basemetermgr getBasemetermgr(final String id) {
        Basemetermgr basemetermgr = (Basemetermgr) getHibernateTemplate().get(Basemetermgr.class, id);
        if (basemetermgr == null) {
            throw new ObjectRetrievalFailureException(Basemetermgr.class, id);
        }

        return basemetermgr;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.dao.BasemetermgrDao#saveBasemetermgr(Basemetermgr basemetermgr)
     */    
    public void saveBasemetermgr(final Basemetermgr basemetermgr) {
        if ((basemetermgr.getId() == null) || (basemetermgr.getId().equals("")))
			getHibernateTemplate().save(basemetermgr);
		else
			getHibernateTemplate().saveOrUpdate(basemetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.dao.BasemetermgrDao#removeBasemetermgr(String id)
     */
    public void removeBasemetermgr(final String id) {
        getHibernateTemplate().delete(getBasemetermgr(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.dao.BasemetermgrDao#getBasemetermgrs(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getBasemetermgrs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the basemetermgr
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from Basemetermgr";
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
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.dao.BasemetermgrDao#getBasemetermgrs(final Integer curPage, final Integer pageSize)
     */    
    public Map getBasemetermgrs(final Integer curPage, final Integer pageSize) {
			return this.getBasemetermgrs(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.parter.baseinfo.basemetermgr.dao.BasemetermgrDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from Basemetermgr obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
