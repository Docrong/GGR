
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawNetTransfer;
import com.boco.eoms.duty.model.TawRmArticle;
import com.boco.eoms.duty.dao.ITawNetTransferDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawNetTransferDaoHibernate extends BaseDaoHibernate implements ITawNetTransferDao{

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticles(com.boco.eoms.duty.model.TawRmArticle)
     */
    public List getTawNetTransfers() {
        return getHibernateTemplate().find("from TawNetTransfer");
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticle(String id)
     */
    public TawNetTransfer getTawNetTransfer(final String id) {
    	TawNetTransfer tawNetTransfer = (TawNetTransfer) getHibernateTemplate().get(TawNetTransfer.class, id);
        if (tawNetTransfer == null) {
            throw new ObjectRetrievalFailureException(TawNetTransfer.class, id);
        }

        return tawNetTransfer;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#saveTawRmArticle(TawRmArticle tawRmArticle)
     */    
    public void saveTawNetTransfer(final TawNetTransfer tawNetTransfer) {
        if ((tawNetTransfer.getId() == null) || (tawNetTransfer.getId().equals("")))
			getHibernateTemplate().save(tawNetTransfer);
		else
			getHibernateTemplate().saveOrUpdate(tawNetTransfer);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#removeTawRmArticle(String id)
     */
    public void removeTawNetTransfer(final String id) {
        getHibernateTemplate().delete(getTawNetTransfer(id));
    }
    /**
     * @see com.boco.eoms.duty.daoTawRmArticleDao#getTawRmArticles(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawNetTransfers(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmLoanRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawNetTransfer";
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
    public Map getTawNetTransfers(final Integer curPage, final Integer pageSize) {
			return this.getTawNetTransfers(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawNetTransfer obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getTawNetTransferByCondition(String condition) {
		String hql ="from TawNetTransfer where 1=1 "+ condition;
		return getHibernateTemplate().find(hql);	
		
	}
}
