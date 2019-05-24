
package com.boco.eoms.parter.baseinfo.mainmetermgr.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr;
import com.boco.eoms.parter.baseinfo.mainmetermgr.dao.IMainmetermgrDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class MainmetermgrDaoHibernate extends BaseDaoHibernate implements IMainmetermgrDao {

    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.dao.MainmetermgrDao#getMainmetermgrs(com.boco.eoms.parter.baseinfo.mainmetermgr.model.Mainmetermgr)
     */
    public List getMainmetermgrs(final Mainmetermgr mainmetermgr) {
        return getHibernateTemplate().find("from Mainmetermgr");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (mainmetermgr == null) {
            return getHibernateTemplate().find("from Mainmetermgr");
        } else {
            // filter on properties set in the mainmetermgr
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(mainmetermgr).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Mainmetermgr.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.dao.MainmetermgrDao#getMainmetermgr(String id)
     */
    public Mainmetermgr getMainmetermgr(final String id) {
        Mainmetermgr mainmetermgr = (Mainmetermgr) getHibernateTemplate().get(Mainmetermgr.class, id);
        if (mainmetermgr == null) {
            throw new ObjectRetrievalFailureException(Mainmetermgr.class, id);
        }

        return mainmetermgr;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.dao.MainmetermgrDao#saveMainmetermgr(Mainmetermgr mainmetermgr)
     */    
    public void saveMainmetermgr(final Mainmetermgr mainmetermgr) {
        if ((mainmetermgr.getId() == null) || (mainmetermgr.getId().equals("")))
			getHibernateTemplate().save(mainmetermgr);
		else
			getHibernateTemplate().saveOrUpdate(mainmetermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.dao.MainmetermgrDao#removeMainmetermgr(String id)
     */
    public void removeMainmetermgr(final String id) {
        getHibernateTemplate().delete(getMainmetermgr(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.dao.MainmetermgrDao#getMainmetermgrs(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getMainmetermgrs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the mainmetermgr
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from Mainmetermgr";
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
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.dao.MainmetermgrDao#getMainmetermgrs(final Integer curPage, final Integer pageSize)
     */    
    public Map getMainmetermgrs(final Integer curPage, final Integer pageSize) {
			return this.getMainmetermgrs(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.parter.baseinfo.mainmetermgr.dao.MainmetermgrDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from Mainmetermgr obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
