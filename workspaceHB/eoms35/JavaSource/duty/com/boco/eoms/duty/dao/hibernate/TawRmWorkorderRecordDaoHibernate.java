
package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmWorkorderRecord;
import com.boco.eoms.duty.dao.ITawRmWorkorderRecordDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmWorkorderRecordDaoHibernate extends BaseDaoHibernate implements ITawRmWorkorderRecordDao {

    /**
     * @see com.boco.eoms.duty.dao.TawRmWorkorderRecordDao#getTawRmWorkorderRecords(com.boco.eoms.duty.model.TawRmWorkorderRecord)
     */
    public List getTawRmWorkorderRecords(final TawRmWorkorderRecord tawRmWorkorderRecord) {
        return getHibernateTemplate().find("from TawRmWorkorderRecord");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmWorkorderRecord == null) {
            return getHibernateTemplate().find("from TawRmWorkorderRecord");
        } else {
            // filter on properties set in the tawRmWorkorderRecord
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmWorkorderRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmWorkorderRecord.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmWorkorderRecordDao#getTawRmWorkorderRecord(String id)
     */
    public TawRmWorkorderRecord getTawRmWorkorderRecord(final String id) {
        TawRmWorkorderRecord tawRmWorkorderRecord = (TawRmWorkorderRecord) getHibernateTemplate().get(TawRmWorkorderRecord.class, id);
        if (tawRmWorkorderRecord == null) {
            throw new ObjectRetrievalFailureException(TawRmWorkorderRecord.class, id);
        }

        return tawRmWorkorderRecord;
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmWorkorderRecordDao#saveTawRmWorkorderRecord(TawRmWorkorderRecord tawRmWorkorderRecord)
     */    
    public void saveTawRmWorkorderRecord(final TawRmWorkorderRecord tawRmWorkorderRecord) {
        if ((tawRmWorkorderRecord.getId() == null) || (tawRmWorkorderRecord.getId().equals("")))
			getHibernateTemplate().save(tawRmWorkorderRecord);
		else
			getHibernateTemplate().saveOrUpdate(tawRmWorkorderRecord);
    }

    /**
     * @see com.boco.eoms.duty.dao.TawRmWorkorderRecordDao#removeTawRmWorkorderRecord(String id)
     */
    public void removeTawRmWorkorderRecord(final String id) {
        getHibernateTemplate().delete(getTawRmWorkorderRecord(id));
    }
    /**
     * @see com.boco.eoms.duty.dao.TawRmWorkorderRecordDao#getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmWorkorderRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmWorkorderRecord";
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
     * @see com.boco.eoms.duty.dao.TawRmWorkorderRecordDao#getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmWorkorderRecords(final Integer curPage, final Integer pageSize) {
			return this.getTawRmWorkorderRecords(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.duty.dao.TawRmWorkorderRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmWorkorderRecord obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
