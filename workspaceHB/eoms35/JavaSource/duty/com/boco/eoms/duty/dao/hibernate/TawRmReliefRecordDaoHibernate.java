
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmReliefRecord;
import com.boco.eoms.duty.dao.ITawRmReliefRecordDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmReliefRecordDaoHibernate extends BaseDaoHibernate implements ITawRmReliefRecordDao {

    /**
     * @see com.boco.eoms.duty.dao.TawRmReliefRecordDao#getTawRmReliefRecords(com.boco.eoms.duty.model.TawRmReliefRecord)
     */
    public List getTawRmReliefRecords(final TawRmReliefRecord tawRmReliefRecord) {
        return getHibernateTemplate().find("from TawRmReliefRecord");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmReliefRecord == null) {
            return getHibernateTemplate().find("from TawRmReliefRecord");
        } else {
            // filter on properties set in the tawRmReliefRecord
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmReliefRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmReliefRecord.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmReliefRecordDao#getTawRmReliefRecord(String id)
     */
    public TawRmReliefRecord getTawRmReliefRecord(final String id) {
        TawRmReliefRecord tawRmReliefRecord = (TawRmReliefRecord) getHibernateTemplate().get(TawRmReliefRecord.class, id);
        if (tawRmReliefRecord == null) {
            throw new ObjectRetrievalFailureException(TawRmReliefRecord.class, id);
        }

        return tawRmReliefRecord;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmReliefRecordDao#saveTawRmReliefRecord(TawRmReliefRecord tawRmReliefRecord)
     */    
    public void saveTawRmReliefRecord(final TawRmReliefRecord tawRmReliefRecord) {
        if ((tawRmReliefRecord.getId() == null) || (tawRmReliefRecord.getId().equals(""))){
			getHibernateTemplate().save(tawRmReliefRecord);
        }
		else
			getHibernateTemplate().saveOrUpdate(tawRmReliefRecord);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmReliefRecordDao#removeTawRmReliefRecord(String id)
     */
    public void removeTawRmReliefRecord(final String id) {
        getHibernateTemplate().delete(getTawRmReliefRecord(id));
    }
    /**
     * @see com.boco.eoms.duty.dao.TawRmReliefRecordDao#getTawRmReliefRecords(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmReliefRecords(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmReliefRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmReliefRecord";
              if(whereStr!=null && whereStr.length()>0)
            		queryStr += whereStr;
            	String queryCountStr = "select count(*) " + queryStr;

							Integer total = (Integer) session.createQuery(queryCountStr).iterate()
									.next();
							Query query = session.createQuery(queryStr);
							query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
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
     * @see com.boco.eoms.duty.dao.TawRmReliefRecordDao#getTawRmReliefRecords(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmReliefRecords(final Integer curPage, final Integer pageSize) {
			return this.getTawRmReliefRecords(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmReliefRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmReliefRecord obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
