package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.role.model.TawSystemRoleType;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleTypeDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemRoleTypeDaoHibernate extends BaseDaoHibernate implements TawSystemRoleTypeDao {

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleTypeDao#getTawSystemRoleTypes(com.boco.eoms.commons.system.role.model.TawSystemRoleType)
     */
    public List getTawSystemRoleTypes(final TawSystemRoleType tawSystemRoleType) {
        return getHibernateTemplate().find("from TawSystemRoleType");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSystemRoleType == null) {
            return getHibernateTemplate().find("from TawSystemRoleType");
        } else {
            // filter on properties set in the tawSystemRoleType
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSystemRoleType).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSystemRoleType.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleTypeDao#getTawSystemRoleType(Long roletype_id)
     */
    public TawSystemRoleType getTawSystemRoleType(final Long roletype_id) {
        TawSystemRoleType tawSystemRoleType = (TawSystemRoleType) getHibernateTemplate().get(TawSystemRoleType.class, roletype_id);
        if (tawSystemRoleType == null) {
            throw new ObjectRetrievalFailureException(TawSystemRoleType.class, roletype_id);
        }

        return tawSystemRoleType;
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleTypeDao#saveTawSystemRoleType(TawSystemRoleType tawSystemRoleType)
     */    
    public void saveTawSystemRoleType(final TawSystemRoleType tawSystemRoleType) {
        if (tawSystemRoleType.getRoletype_id()== new Long(0))
			getHibernateTemplate().save(tawSystemRoleType);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemRoleType);
    }

    /**
     * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleTypeDao#removeTawSystemRoleType(Long roletype_id)
     */
    public void removeTawSystemRoleType(final Long roletype_id) {
        getHibernateTemplate().delete(getTawSystemRoleType(roletype_id));
    }

    public Map getTawSystemRoleTypes(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawSystemRoleType
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSystemRoleType";
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
    public Map getTawSystemRoleTypes(final Integer curPage, final Integer pageSize) {
			return this.getTawSystemRoleTypes(curPage,pageSize,null);
		}

}
