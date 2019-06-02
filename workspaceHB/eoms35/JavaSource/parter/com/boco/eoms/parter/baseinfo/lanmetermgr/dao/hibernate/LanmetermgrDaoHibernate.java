
package com.boco.eoms.parter.baseinfo.lanmetermgr.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr;
import com.boco.eoms.parter.baseinfo.lanmetermgr.dao.ILanmetermgrDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class LanmetermgrDaoHibernate extends BaseDaoHibernate implements ILanmetermgrDao {

    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.dao.LanmetermgrDao#getLanmetermgrs(com.boco.eoms.parter.baseinfo.lanmetermgr.model.Lanmetermgr)
     */
    public List getLanmetermgrs(final Lanmetermgr lanmetermgr) {
        return getHibernateTemplate().find("from Lanmetermgr");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (lanmetermgr == null) {
            return getHibernateTemplate().find("from Lanmetermgr");
        } else {
            // filter on properties set in the lanmetermgr
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(lanmetermgr).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Lanmetermgr.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.dao.LanmetermgrDao#getLanmetermgr(String id)
     */
    public Lanmetermgr getLanmetermgr(final String id) {
        Lanmetermgr lanmetermgr = (Lanmetermgr) getHibernateTemplate().get(Lanmetermgr.class, id);
        if (lanmetermgr == null) {
            throw new ObjectRetrievalFailureException(Lanmetermgr.class, id);
        }

        return lanmetermgr;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.dao.LanmetermgrDao#saveLanmetermgr(Lanmetermgr lanmetermgr)
     */    
    public void saveLanmetermgr(final Lanmetermgr lanmetermgr) {
        if ((lanmetermgr.getId() == null) || (lanmetermgr.getId().equals("")))
			getHibernateTemplate().save(lanmetermgr);
		else
			getHibernateTemplate().saveOrUpdate(lanmetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.dao.LanmetermgrDao#removeLanmetermgr(String id)
     */
    public void removeLanmetermgr(final String id) {
        getHibernateTemplate().delete(getLanmetermgr(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.dao.LanmetermgrDao#getLanmetermgrs(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getLanmetermgrs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the lanmetermgr
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from Lanmetermgr ";
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
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.dao.LanmetermgrDao#getLanmetermgrs(final Integer curPage, final Integer pageSize)
     */    
    public Map getLanmetermgrs(final Integer curPage, final Integer pageSize) {
			return this.getLanmetermgrs(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.parter.baseinfo.lanmetermgr.dao.LanmetermgrDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from Lanmetermgr obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
