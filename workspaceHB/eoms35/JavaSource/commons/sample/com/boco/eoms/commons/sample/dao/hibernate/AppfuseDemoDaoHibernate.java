
package com.boco.eoms.commons.sample.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.sample.model.AppfuseDemo;
import com.boco.eoms.commons.sample.dao.AppfuseDemoDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class AppfuseDemoDaoHibernate extends BaseDaoHibernate implements AppfuseDemoDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.AppfuseDemoDao#getAppfuseDemos(com.boco.eoms.commons.sample.model.AppfuseDemo)
     */
    public List getAppfuseDemos(final AppfuseDemo appfuseDemo) {
        return getHibernateTemplate().find("from AppfuseDemo");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (appfuseDemo == null) {
            return getHibernateTemplate().find("from AppfuseDemo");
        } else {
            // filter on properties set in the appfuseDemo
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(appfuseDemo).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(AppfuseDemo.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.AppfuseDemoDao#getAppfuseDemo(String id)
     */
    public AppfuseDemo getAppfuseDemo(final String id) {
        AppfuseDemo appfuseDemo = (AppfuseDemo) getHibernateTemplate().get(AppfuseDemo.class, id);
        if (appfuseDemo == null) {
            throw new ObjectRetrievalFailureException(AppfuseDemo.class, id);
        }

        return appfuseDemo;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.AppfuseDemoDao#saveAppfuseDemo(AppfuseDemo appfuseDemo)
     */    
    public void saveAppfuseDemo(final AppfuseDemo appfuseDemo) {
        if ((appfuseDemo.getId() == null) || (appfuseDemo.getId().equals("")))
			getHibernateTemplate().save(appfuseDemo);
		else
			getHibernateTemplate().saveOrUpdate(appfuseDemo);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.AppfuseDemoDao#removeAppfuseDemo(String id)
     */
    public void removeAppfuseDemo(final String id) {
        getHibernateTemplate().delete(getAppfuseDemo(id));
    }

    public Map getAppfuseDemos(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the appfuseDemo
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from AppfuseDemo";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							int total = ((Integer) session.createQuery(queryCountStr).iterate()
									.next()).intValue();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()
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
    public Map getAppfuseDemos(final Integer curPage, final Integer pageSize) {
			return this.getAppfuseDemos(curPage,pageSize,null);
		}

}
