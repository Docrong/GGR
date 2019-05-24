
package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy;
import com.boco.eoms.commons.system.role.dao.TawSystemOrganizationProxyDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemOrganizationProxyDaoHibernate extends BaseDaoHibernate implements TawSystemOrganizationProxyDao {

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemOrganizationProxyDao#getTawSystemOrganizationProxys(com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy)
     */
    public List getTawSystemOrganizationProxys(final TawSystemOrganizationProxy tawSystemOrganizationProxy) {
        return getHibernateTemplate().find("from TawSystemOrganizationProxy");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSystemOrganizationProxy == null) {
            return getHibernateTemplate().find("from TawSystemOrganizationProxy");
        } else {
            // filter on properties set in the tawSystemOrganizationProxy
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSystemOrganizationProxy).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSystemOrganizationProxy.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemOrganizationProxyDao#getTawSystemOrganizationProxy(String id)
     */
    public TawSystemOrganizationProxy getTawSystemOrganizationProxy(final String id) {
        TawSystemOrganizationProxy tawSystemOrganizationProxy = (TawSystemOrganizationProxy) getHibernateTemplate().get(TawSystemOrganizationProxy.class, id);
        if (tawSystemOrganizationProxy == null) {
            throw new ObjectRetrievalFailureException(TawSystemOrganizationProxy.class, id);
        }

        return tawSystemOrganizationProxy;
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemOrganizationProxyDao#saveTawSystemOrganizationProxy(TawSystemOrganizationProxy tawSystemOrganizationProxy)
     */    
    public void saveTawSystemOrganizationProxy(final TawSystemOrganizationProxy tawSystemOrganizationProxy) {
        if ((tawSystemOrganizationProxy.getId() == null) || (tawSystemOrganizationProxy.getId().equals("")))
			getHibernateTemplate().save(tawSystemOrganizationProxy);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemOrganizationProxy);
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemOrganizationProxyDao#removeTawSystemOrganizationProxy(String id)
     */
    public void removeTawSystemOrganizationProxy(final String id) {
        getHibernateTemplate().delete(getTawSystemOrganizationProxy(id));
    }

    public Map getTawSystemOrganizationProxys(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawSystemOrganizationProxy
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSystemOrganizationProxy";
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
    public Map getTawSystemOrganizationProxys(final Integer curPage, final Integer pageSize) {
			return this.getTawSystemOrganizationProxys(curPage,pageSize,null);
		}
    
	public List getMain(){
		String hql = "from SeSpecificationMain m,zhuoyue_hlj:SeSpecificationLink k where m.id=k.id";
		return getHibernateTemplate().find(hql);
	}

}
