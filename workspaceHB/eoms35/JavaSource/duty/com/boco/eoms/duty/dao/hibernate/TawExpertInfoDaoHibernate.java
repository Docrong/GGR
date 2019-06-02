
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawExpertInfo;
import com.boco.eoms.duty.model.TawRmArticle;
import com.boco.eoms.duty.dao.ITawExpertInfoDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawExpertInfoDaoHibernate extends BaseDaoHibernate implements ITawExpertInfoDao{

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticles(com.boco.eoms.duty.model.TawRmArticle)
     */
    public List getTawExpertInfos() {
        return getHibernateTemplate().find("from TawExpertInfo");
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticle(String id)
     */
    public TawExpertInfo getTawExpertInfo(final String id) {
    	TawExpertInfo tawExpertInfo = (TawExpertInfo) getHibernateTemplate().get(TawExpertInfo.class, id);
        if (tawExpertInfo == null) {
            throw new ObjectRetrievalFailureException(TawExpertInfo.class, id);
        }

        return tawExpertInfo;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#saveTawRmArticle(TawRmArticle tawRmArticle)
     */    
    public void saveTawExpertInfo(final TawExpertInfo tawExpertInfo) {
        if ((tawExpertInfo.getId() == null) || (tawExpertInfo.getId().equals("")))
			getHibernateTemplate().save(tawExpertInfo);
		else
			getHibernateTemplate().saveOrUpdate(tawExpertInfo);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#removeTawRmArticle(String id)
     */
    public void removeTawExpertInfo(final String id) {
        getHibernateTemplate().delete(getTawExpertInfo(id));
    }
    /**
     * @see com.boco.eoms.duty.daoTawRmArticleDao#getTawRmArticles(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawExpertInfos(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmLoanRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawExpertInfo";
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
    public Map getTawExpertInfos(final Integer curPage, final Integer pageSize) {
			return this.getTawExpertInfos(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawExpertInfo obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getTawExpertInfosByCondition(String condition) {
		String hql ="from TawExpertInfo where 1=1 "+ condition;
		return getHibernateTemplate().find(hql);	
		
	}
}
