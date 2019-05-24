
package com.boco.eoms.commons.system.code.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.code.model.TawSystemCode;
import com.boco.eoms.commons.system.code.dao.ITawSystemCodeDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.boco.eoms.commons.system.code.dao.hibernate.TawSystemCodeDaoHibernate;

public class TawSystemCodeDaoHibernate extends BaseDaoHibernate implements ITawSystemCodeDao {

    /**
     * @see com.boco.eoms.commons.system.code.dao.TawSystemCodeDao#getTawSystemCodes(com.boco.eoms.commons.system.code.model.TawSystemCode)
     */
    public List getTawSystemCodes(final TawSystemCode tawSystemCode) {
        return getHibernateTemplate().find("from TawSystemCode");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSystemCode == null) {
            return getHibernateTemplate().find("from TawSystemCode");
        } else {
            // filter on properties set in the tawSystemCode
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSystemCode).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSystemCode.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.system.code.dao.TawSystemCodeDao#getTawSystemCode(String id)
     */
    public TawSystemCode getTawSystemCode(final String id) {
        TawSystemCode tawSystemCode = (TawSystemCode) getHibernateTemplate().get(TawSystemCode.class, id);
        if (tawSystemCode == null) {
            throw new ObjectRetrievalFailureException(TawSystemCode.class, id);
        }

        return tawSystemCode;
    }

    /**
     * @see com.boco.eoms.commons.system.code.dao.TawSystemCodeDao#saveTawSystemCode(TawSystemCode tawSystemCode)
     */    
    public void saveTawSystemCode(final TawSystemCode tawSystemCode) {
        if ((tawSystemCode.getId() == null) || (tawSystemCode.getId().equals("")))
			getHibernateTemplate().save(tawSystemCode);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemCode);
    }

    /**
     * @see com.boco.eoms.commons.system.code.dao.TawSystemCodeDao#removeTawSystemCode(String id)
     */
    public void removeTawSystemCode(final String id) {
        getHibernateTemplate().delete(getTawSystemCode(id));
    }
    /**
     * @see com.boco.eoms.commons.system.code.dao.TawSystemCodeDao#getTawSystemCodes(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawSystemCodes(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawSystemCode
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawSystemCode";
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
     * @see com.boco.eoms.commons.system.code.dao.TawSystemCodeDao#getTawSystemCodes(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawSystemCodes(final Integer curPage, final Integer pageSize) {
			return this.getTawSystemCodes(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.commons.system.code.dao.TawSystemCodeDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawSystemCode obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getTawSystemCodes(String whereStr) {
		String queryStr = "from TawSystemCode";
		if (whereStr != null && whereStr.length() > 0) {
			queryStr += " " + whereStr;
		}
		return (ArrayList) getHibernateTemplate().find(queryStr);
	}
}
