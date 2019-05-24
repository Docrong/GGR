
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmReplace;
import com.boco.eoms.duty.dao.ITawRmReplaceDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmReplaceDaoHibernate extends BaseDaoHibernate implements ITawRmReplaceDao {

    /**
     * @see com.boco.eoms.duty.dao.TawRmReplaceDao#getTawRmReplaces(com.boco.eoms.duty.model.TawRmReplace)
     */
    public List getTawRmReplaces(final TawRmReplace tawRmReplace) {
        return getHibernateTemplate().find("from TawRmReplace");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmReplace == null) {
            return getHibernateTemplate().find("from TawRmReplace");
        } else {
            // filter on properties set in the tawRmReplace
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmReplace).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmReplace.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmReplaceDao#getTawRmReplace(String id)
     */
    public TawRmReplace getTawRmReplace(final String id) {
        TawRmReplace tawRmReplace = (TawRmReplace) getHibernateTemplate().get(TawRmReplace.class, id);
        if (tawRmReplace == null) {
            throw new ObjectRetrievalFailureException(TawRmReplace.class, id);
        }

        return tawRmReplace;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmReplaceDao#saveTawRmReplace(TawRmReplace tawRmReplace)
     */    
    public void saveTawRmReplace(final TawRmReplace tawRmReplace) {
        if ((tawRmReplace.getId() == null) || (tawRmReplace.getId().equals("")))
			getHibernateTemplate().save(tawRmReplace);
		else
			getHibernateTemplate().saveOrUpdate(tawRmReplace);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmReplaceDao#removeTawRmReplace(String id)
     */
    public void removeTawRmReplace(final String id) {
        getHibernateTemplate().delete(getTawRmReplace(id));
    }
    /**
     * @see com.boco.eoms.duty.dao.TawRmReplaceDao#getTawRmReplaces(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmReplaces(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmReplace
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmReplace";
              if(whereStr!=null && whereStr.length()>0)
            		     queryStr += whereStr;
         					Query query = session.createQuery(queryStr);
						
							List result = query.list();
							HashMap map = new HashMap();
							
							map.put("result", result);
							return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }
    /**
     * @see com.boco.eoms.duty.dao.TawRmReplaceDao#getTawRmReplaces(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmReplaces(final Integer curPage, final Integer pageSize) {
			return this.getTawRmReplaces(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmReplaceDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmReplace obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
	
	
}
