
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmDispatchRecord;
import com.boco.eoms.duty.dao.ITawRmDispatchRecordDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmDispatchRecordDaoHibernate extends BaseDaoHibernate implements ITawRmDispatchRecordDao {

    /**
     * @see com.boco.eoms.duty.dao.TawRmDispatchRecordDao#getTawRmDispatchRecords(com.boco.eoms.duty.model.TawRmDispatchRecord)
     */
    public List getTawRmDispatchRecords(final TawRmDispatchRecord tawRmDispatchRecord) {
        return getHibernateTemplate().find("from TawRmDispatchRecord");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmDispatchRecord == null) {
            return getHibernateTemplate().find("from TawRmDispatchRecord");
        } else {
            // filter on properties set in the tawRmDispatchRecord
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmDispatchRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmDispatchRecord.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmDispatchRecordDao#getTawRmDispatchRecord(String id)
     */
    public TawRmDispatchRecord getTawRmDispatchRecord(final String id) {
        TawRmDispatchRecord tawRmDispatchRecord = (TawRmDispatchRecord) getHibernateTemplate().get(TawRmDispatchRecord.class, id);
        if (tawRmDispatchRecord == null) {
            throw new ObjectRetrievalFailureException(TawRmDispatchRecord.class, id);
        }

        return tawRmDispatchRecord;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmDispatchRecordDao#saveTawRmDispatchRecord(TawRmDispatchRecord tawRmDispatchRecord)
     */    
    public void saveTawRmDispatchRecord(final TawRmDispatchRecord tawRmDispatchRecord) {
        if ((tawRmDispatchRecord.getId() == null) || (tawRmDispatchRecord.getId().equals("")))
			getHibernateTemplate().save(tawRmDispatchRecord);
		else
			getHibernateTemplate().saveOrUpdate(tawRmDispatchRecord);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmDispatchRecordDao#removeTawRmDispatchRecord(String id)
     */
    public void removeTawRmDispatchRecord(final String id) {
        getHibernateTemplate().delete(getTawRmDispatchRecord(id));
    }
    /**
     * @see com.boco.eoms.duty.dao.TawRmDispatchRecordDao#getTawRmDispatchRecords(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmDispatchRecords(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmDispatchRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmDispatchRecord";
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
     * @see com.boco.eoms.duty.dao.TawRmDispatchRecordDao#getTawRmDispatchRecords(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmDispatchRecords(final Integer curPage, final Integer pageSize) {
			return this.getTawRmDispatchRecords(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmDispatchRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmDispatchRecord obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
