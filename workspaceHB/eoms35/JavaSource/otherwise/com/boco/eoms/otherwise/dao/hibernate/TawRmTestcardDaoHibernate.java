
package com.boco.eoms.otherwise.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.otherwise.model.TawRmTestcard;
import com.boco.eoms.otherwise.dao.ITawRmTestcardDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmTestcardDaoHibernate extends BaseDaoHibernate implements ITawRmTestcardDao {

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmTestcardDao#getTawRmTestcards(com.boco.eoms.otherwise.model.TawRmTestcard)
     */
    public List getTawRmTestcards(final TawRmTestcard tawRmTestcard) {
        return getHibernateTemplate().find("from TawRmTestcard");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmTestcard == null) {
            return getHibernateTemplate().find("from TawRmTestcard");
        } else {
            // filter on properties set in the tawRmTestcard
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmTestcard).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmTestcard.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmTestcardDao#getTawRmTestcard(String id)
     */
    public TawRmTestcard getTawRmTestcard(final String id) {
        TawRmTestcard tawRmTestcard = (TawRmTestcard) getHibernateTemplate().get(TawRmTestcard.class, id);
        if (tawRmTestcard == null) {
            throw new ObjectRetrievalFailureException(TawRmTestcard.class, id);
        }

        return tawRmTestcard;
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmTestcardDao#saveTawRmTestcard(TawRmTestcard tawRmTestcard)
     */    
    public void saveTawRmTestcard(final TawRmTestcard tawRmTestcard) {
        if ((tawRmTestcard.getId() == null) || (tawRmTestcard.getId().equals("")))
			getHibernateTemplate().save(tawRmTestcard);
		else
			getHibernateTemplate().saveOrUpdate(tawRmTestcard);
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmTestcardDao#removeTawRmTestcard(String id)
     */
    public void removeTawRmTestcard(final String id) {
        getHibernateTemplate().delete(getTawRmTestcard(id));
    }
    /**
     * @see com.boco.eoms.otherwise.dao.TawRmTestcardDao#getTawRmTestcards(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmTestcards(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmTestcard
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmTestcard";
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
     * @see com.boco.eoms.otherwise.dao.TawRmTestcardDao#getTawRmTestcards(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmTestcards(final Integer curPage, final Integer pageSize) {
			return this.getTawRmTestcards(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.otherwise.dao.TawRmTestcardDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmTestcard obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
