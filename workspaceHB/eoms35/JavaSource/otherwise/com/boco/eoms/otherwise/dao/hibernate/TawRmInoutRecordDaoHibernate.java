
package com.boco.eoms.otherwise.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.otherwise.model.TawRmInoutRecord;
import com.boco.eoms.otherwise.dao.ITawRmInoutRecordDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmInoutRecordDaoHibernate extends BaseDaoHibernate implements ITawRmInoutRecordDao {

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmInoutRecordDao#getTawRmInoutRecords(com.boco.eoms.otherwise.model.TawRmInoutRecord)
     */
    public List getTawRmInoutRecords(final TawRmInoutRecord tawRmInoutRecord) {
        return getHibernateTemplate().find("from TawRmInoutRecord");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawRmInoutRecord == null) {
            return getHibernateTemplate().find("from TawRmInoutRecord");
        } else {
            // filter on properties set in the tawRmInoutRecord
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawRmInoutRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawRmInoutRecord.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmInoutRecordDao#getTawRmInoutRecord(String id)
     */
    public TawRmInoutRecord getTawRmInoutRecord(final String id) {
        TawRmInoutRecord tawRmInoutRecord = (TawRmInoutRecord) getHibernateTemplate().get(TawRmInoutRecord.class, id);
        if (tawRmInoutRecord == null) {
            throw new ObjectRetrievalFailureException(TawRmInoutRecord.class, id);
        }

        return tawRmInoutRecord;
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmInoutRecordDao#saveTawRmInoutRecord(TawRmInoutRecord tawRmInoutRecord)
     */    
    public void saveTawRmInoutRecord(final TawRmInoutRecord tawRmInoutRecord) {
        if ((tawRmInoutRecord.getId() == null) || (tawRmInoutRecord.getId().equals("")))
			getHibernateTemplate().save(tawRmInoutRecord);
		else
			getHibernateTemplate().saveOrUpdate(tawRmInoutRecord);
    }

    /**
     * @see com.boco.eoms.otherwise.dao.TawRmInoutRecordDao#removeTawRmInoutRecord(String id)
     */
    public void removeTawRmInoutRecord(final String id) {
        getHibernateTemplate().delete(getTawRmInoutRecord(id));
    }
    /**
     * @see com.boco.eoms.otherwise.dao.TawRmInoutRecordDao#getTawRmInoutRecords(final Integer curPage, final Integer pageSize,final String whereStr)
     */
    public Map getTawRmInoutRecords(final Integer curPage, final Integer pageSize,final String whereStr) {
        // filter on properties set in the tawRmInoutRecord
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
              String queryStr = "from TawRmInoutRecord";
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
     * @see com.boco.eoms.otherwise.dao.TawRmInoutRecordDao#getTawRmInoutRecords(final Integer curPage, final Integer pageSize)
     */    
    public Map getTawRmInoutRecords(final Integer curPage, final Integer pageSize) {
			return this.getTawRmInoutRecords(curPage,pageSize,null);
		}
    /**
     * @see com.boco.eoms.otherwise.dao.TawRmInoutRecordDao#getChildList(String parentId)
     */  
	public ArrayList getChildList(String parentId){	
		String hql = " from TawRmInoutRecord obj where obj.parentId='"
			+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public TawRmInoutRecord getOutRecordByTestCardId(String testCardId){
		String hql=" from TawRmInoutRecord obj where obj.testCardId='"+ testCardId + "' and inType='' order by obj.name";
		List list=(ArrayList) getHibernateTemplate().find(hql);
		if(list==null||list.size()==0){
			return null;
		}else{
			TawRmInoutRecord tawRmInoutRecord=(TawRmInoutRecord)list.get(0);
			return tawRmInoutRecord;
		}
	}
}
