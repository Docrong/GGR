
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawNetTransferSub;
import com.boco.eoms.duty.model.TawRmArticle;
import com.boco.eoms.duty.dao.ITawNetTransferSubDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawNetTransferSubDaoHibernate extends BaseDaoHibernate implements ITawNetTransferSubDao{

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticles(com.boco.eoms.duty.model.TawRmArticle)
     */
    public List getTawNetTransferSubs() {
        return getHibernateTemplate().find("from TawNetTransferSub");
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#getTawRmArticle(String id)
     */
    public TawNetTransferSub getTawNetTransferSub(final String id) {
    	TawNetTransferSub tawNetTransferSub = (TawNetTransferSub) getHibernateTemplate().get(TawNetTransferSub.class, id);
        if (tawNetTransferSub == null) {
            throw new ObjectRetrievalFailureException(TawNetTransferSub.class, id);
        }

        return tawNetTransferSub;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#saveTawRmArticle(TawRmArticle tawRmArticle)
     */    
    public void saveTawNetTransferSub(final TawNetTransferSub tawNetTransferSub) {
        if ((tawNetTransferSub.getId() == null) || (tawNetTransferSub.getId().equals("")))
			getHibernateTemplate().save(tawNetTransferSub);
		else
			getHibernateTemplate().saveOrUpdate(tawNetTransferSub);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmArticleDao#removeTawRmArticle(String id)
     */
    public void removeTawNetTransferSub(final String id) {
        getHibernateTemplate().delete(getTawNetTransferSub(id));
    }
    /**
     * @see com.boco.eoms.duty.daoTawRmArticleDao#getTawRmArticles(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawNetTransferSubs(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmLoanRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawNetTransferSub";
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
    public Map getTawNetTransferSubs(final Integer curPage, final Integer pageSize) {
			return this.getTawNetTransferSubs(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawNetTransfer obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getTawNetTransferSubByCondition(String condition) {
		String hql ="from TawNetTransferSub where 1=1 "+ condition;
		return getHibernateTemplate().find(hql);	
		
	}
	public List getTawNetTransferSubByMainId(String mainId) {
		String hql ="from TawNetTransferSub where mainId = '"+ mainId+"' order by addTime";
		return getHibernateTemplate().find(hql);	
		
	}
}
