
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmArticle;
import com.boco.eoms.duty.dao.ITawRmArticleDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmArticleDaoHibernate extends BaseDaoHibernate implements ITawRmArticleDao {

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticles(com.boco.eoms.duty.model.TawRmArticle)
     */
    public List getTawRmArticles() {
        return getHibernateTemplate().find("from TawRmArticle");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmLoanRecord == null) {
            return getHibernateTemplate().find("from TawRmLoanRecord");
        } else {
            // filter on properties set in the tawRmLoanRecord
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmLoanRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmLoanRecord.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticle(String id)
     */
    public TawRmArticle getTawRmArticle(final String id) {
    	TawRmArticle tawRmArticle = (TawRmArticle) getHibernateTemplate().get(TawRmArticle.class, id);
        if (tawRmArticle == null) {
            throw new ObjectRetrievalFailureException(TawRmArticle.class, id);
        }

        return tawRmArticle;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#saveTawRmArticle(TawRmArticle tawRmArticle)
     */    
    public void saveTawRmArticle(final TawRmArticle tawRmArticle) {
        if ((tawRmArticle.getId() == null) || (tawRmArticle.getId().equals("")))
			getHibernateTemplate().save(tawRmArticle);
		else
			getHibernateTemplate().saveOrUpdate(tawRmArticle);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#removeTawRmArticle(String id)
     */
    public void removeTawRmArticle(final String id) {
        getHibernateTemplate().delete(getTawRmArticle(id));
    }
    /**
     * @see com.boco.eoms.duty.daoTawRmArticleDao#getTawRmArticles(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmArticles(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmLoanRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmArticle";
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
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getTawRmLoanRecords(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmArticles(final Integer curPage, final Integer pageSize) {
			return this.getTawRmArticles(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmArticle obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getTawRmArticleByCondition(String condition) {
		String hql ="from TawRmArticle where 1=1 "+ condition;
		return getHibernateTemplate().find(hql);	
		
	}
}
