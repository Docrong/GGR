
package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.TawsuCheckModule;
import com.boco.eoms.extra.supplierkpi.dao.TawsuCheckModuleDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawsuCheckModuleDaoHibernate extends BaseDaoHibernate implements TawsuCheckModuleDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawsuCheckModuleDao#getTawsuCheckModules(com.boco.eoms.commons.sample.model.TawsuCheckModule)
     */
    public List getTawsuCheckModules(final TawsuCheckModule tawsuCheckModule) {
        return getHibernateTemplate().find("from TawsuCheckModule");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawsuCheckModule == null) {
            return getHibernateTemplate().find("from TawsuCheckModule");
        } else {
            // filter on properties set in the tawsuCheckModule
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawsuCheckModule).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawsuCheckModule.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawsuCheckModuleDao#getTawsuCheckModule(String id)
     */
    public TawsuCheckModule getTawsuCheckModule(final String id) {
        TawsuCheckModule tawsuCheckModule = (TawsuCheckModule) getHibernateTemplate().get(TawsuCheckModule.class, id);
        if (tawsuCheckModule == null) {
            throw new ObjectRetrievalFailureException(TawsuCheckModule.class, id);
        }

        return tawsuCheckModule;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawsuCheckModuleDao#saveTawsuCheckModule(TawsuCheckModule tawsuCheckModule)
     */    
    public void saveTawsuCheckModule(final TawsuCheckModule tawsuCheckModule) {
        if ((tawsuCheckModule.getId() == null) || (tawsuCheckModule.getId().equals("")))
			getHibernateTemplate().save(tawsuCheckModule);
		else
			getHibernateTemplate().saveOrUpdate(tawsuCheckModule);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawsuCheckModuleDao#removeTawsuCheckModule(String id)
     */
    public void removeTawsuCheckModule(final String id) {
        getHibernateTemplate().delete(getTawsuCheckModule(id));
    }
    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawsuCheckModules(final int curPage, final int pageSize,final String whereStr) {
        // filter on properties set in the tawsuCheckModule
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawsuCheckModule";
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
    public Map getTawsuCheckModules(final int curPage, final int pageSize) {
			return this.getTawsuCheckModules(curPage,pageSize,null);
		}

}
