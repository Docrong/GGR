
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmLoanRecord;
import com.boco.eoms.duty.dao.ITawRmLoanRecordDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmLoanRecordDaoHibernate extends BaseDaoHibernate implements ITawRmLoanRecordDao {

    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getTawRmLoanRecords(com.boco.eoms.duty.model.TawRmLoanRecord)
     */
    public List getTawRmLoanRecords(final TawRmLoanRecord tawRmLoanRecord) {
        return getHibernateTemplate().find("from TawRmLoanRecord");

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
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getTawRmLoanRecord(String id)
     */
    public TawRmLoanRecord getTawRmLoanRecord(final String id) {
        TawRmLoanRecord tawRmLoanRecord = (TawRmLoanRecord) getHibernateTemplate().get(TawRmLoanRecord.class, id);
        if (tawRmLoanRecord == null) {
            throw new ObjectRetrievalFailureException(TawRmLoanRecord.class, id);
        }

        return tawRmLoanRecord;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#saveTawRmLoanRecord(TawRmLoanRecord tawRmLoanRecord)
     */    
    public void saveTawRmLoanRecord(final TawRmLoanRecord tawRmLoanRecord) {
        if ((tawRmLoanRecord.getId() == null) || (tawRmLoanRecord.getId().equals("")))
			getHibernateTemplate().save(tawRmLoanRecord);
		else
			getHibernateTemplate().saveOrUpdate(tawRmLoanRecord);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#removeTawRmLoanRecord(String id)
     */
    public void removeTawRmLoanRecord(final String id) {
        getHibernateTemplate().delete(getTawRmLoanRecord(id));
    }
    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getTawRmLoanRecords(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmLoanRecords(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmLoanRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmLoanRecord";
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
    public Map getTawRmLoanRecords(final Integer curPage, final Integer pageSize) {
			return this.getTawRmLoanRecords(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmLoanRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmLoanRecord obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
