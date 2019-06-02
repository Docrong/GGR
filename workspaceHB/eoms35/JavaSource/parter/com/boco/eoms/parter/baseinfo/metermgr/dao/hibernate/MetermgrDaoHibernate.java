
package com.boco.eoms.parter.baseinfo.metermgr.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr;
import com.boco.eoms.parter.baseinfo.metermgr.dao.IMetermgrDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class MetermgrDaoHibernate extends BaseDaoHibernate implements IMetermgrDao {

    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.dao.MetermgrDao#getMetermgrs(com.boco.eoms.parter.baseinfo.metermgr.model.Metermgr)
     */
    public List getMetermgrs(final Metermgr metermgr) {
        return getHibernateTemplate().find("from Metermgr");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (metermgr == null) {
            return getHibernateTemplate().find("from Metermgr");
        } else {
            // filter on properties set in the metermgr
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(metermgr).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(Metermgr.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.dao.MetermgrDao#getMetermgr(String id)
     */
    public Metermgr getMetermgr(final String id) {
        Metermgr metermgr = (Metermgr) getHibernateTemplate().get(Metermgr.class, id);
        if (metermgr == null) {
            throw new ObjectRetrievalFailureException(Metermgr.class, id);
        }

        return metermgr;
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.dao.MetermgrDao#saveMetermgr(Metermgr metermgr)
     */    
    public void saveMetermgr(final Metermgr metermgr) {
        if ((metermgr.getId() == null) || (metermgr.getId().equals("")))
			getHibernateTemplate().save(metermgr);
		else
			getHibernateTemplate().saveOrUpdate(metermgr);
    }

    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.dao.MetermgrDao#removeMetermgr(String id)
     */
    public void removeMetermgr(final String id) {
        getHibernateTemplate().delete(getMetermgr(id));
    }
    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.dao.MetermgrDao#getMetermgrs(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getMetermgrs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the metermgr
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from Metermgr";
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
     * @see com.boco.eoms.parter.baseinfo.metermgr.dao.MetermgrDao#getMetermgrs(final Integer curPage, final Integer pageSize)
     */    
    public Map getMetermgrs(final Integer curPage, final Integer pageSize) {
			return this.getMetermgrs(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.parter.baseinfo.metermgr.dao.MetermgrDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from Metermgr obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
