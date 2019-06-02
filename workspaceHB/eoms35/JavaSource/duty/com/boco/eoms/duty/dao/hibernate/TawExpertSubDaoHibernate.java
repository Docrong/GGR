
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawExpertSub;
import com.boco.eoms.duty.model.TawRmArticle;
import com.boco.eoms.duty.dao.ITawExpertSubDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawExpertSubDaoHibernate extends BaseDaoHibernate implements ITawExpertSubDao{

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticles(com.boco.eoms.duty.model.TawRmArticle)
     */
    public List getTawExpertSubs() {
        return getHibernateTemplate().find("from TawExpertSub");
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticle(String id)
     */
    public TawExpertSub getTawExpertSub(final String id) {
    	TawExpertSub tawExpertSub = (TawExpertSub) getHibernateTemplate().get(TawExpertSub.class, id);
        if (tawExpertSub == null) {
            throw new ObjectRetrievalFailureException(TawExpertSub.class, id);
        }

        return tawExpertSub;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#saveTawRmArticle(TawRmArticle tawRmArticle)
     */    
    public void saveTawExpertSub(final TawExpertSub tawExpertSub) {
        if ((tawExpertSub.getId() == null) || (tawExpertSub.getId().equals("")))
			getHibernateTemplate().save(tawExpertSub);
		else
			getHibernateTemplate().saveOrUpdate(tawExpertSub);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#removeTawRmArticle(String id)
     */
    public void removeTawExpertSub(final String id) {
        getHibernateTemplate().delete(getTawExpertSub(id));
    }
    /**
     * @see com.boco.eoms.duty.daoTawRmArticleDao#getTawRmArticles(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawExpertSubs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmLoanRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawExpertSub";
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
    public Map getTawExpertSubs(final Integer curPage, final Integer pageSize) {
			return this.getTawExpertSubs(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawExpertSub obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getTawExpertSubByCondition(String condition) {
		String hql ="from TawExpertSub where 1=1 "+ condition;
		return getHibernateTemplate().find(hql);	
		
	}
	public List getTawExpertSubByMainId(String mainId) {
		String hql ="from TawExpertSub where mainId = '"+ mainId+"' order by addTime";
		return getHibernateTemplate().find(hql);	
		
	}
}
