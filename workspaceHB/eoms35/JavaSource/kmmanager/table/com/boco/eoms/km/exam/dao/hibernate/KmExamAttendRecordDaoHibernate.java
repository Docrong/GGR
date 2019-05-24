package com.boco.eoms.km.exam.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.exam.dao.KmExamAttendRecordDao;
import com.boco.eoms.km.exam.model.KmExamAttendRecord;

/**
 * <p>
 * Title:在线参加考试状态记录 dao的hibernate实现
 * </p>
 * <p>
 * Description:在线参加考试状态记录
 * </p>
 * <p>
 * Fri Aug 28 10:31:42 CST 2009
 * </p>
 * 
 * @author wangzhiyong
 * @version 1.0
 * 
 */
public class KmExamAttendRecordDaoHibernate extends BaseDaoHibernate implements KmExamAttendRecordDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAttendRecordDao#getKmExamAttendRecords()
	 *      
	 */
	public List getKmExamAttendRecords() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamAttendRecord kmExamAttendRecord";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.exam.KmExamAttendRecordDao#getKmExamAttendRecord(java.lang.String)
	 *
	 */
	public KmExamAttendRecord getKmExamAttendRecord(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamAttendRecord kmExamAttendRecord where kmExamAttendRecord.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmExamAttendRecord) result.iterator().next();
				} else {
					return new KmExamAttendRecord();
				}
			}
		};
		return (KmExamAttendRecord) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAttendRecordDao#saveKmExamAttendRecords(com.boco.eoms.km.exam.KmExamAttendRecord)
	 *      
	 */
	public void saveKmExamAttendRecord(final KmExamAttendRecord kmExamAttendRecord) {
		if ((kmExamAttendRecord.getId() == null) || (kmExamAttendRecord.getId().equals("")))
			getHibernateTemplate().save(kmExamAttendRecord);
		else
			getHibernateTemplate().saveOrUpdate(kmExamAttendRecord);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAttendRecordDao#removeKmExamAttendRecords(java.lang.String)
	 *      
	 */
    public void removeKmExamAttendRecord(final String id) {
		getHibernateTemplate().delete(getKmExamAttendRecord(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmExamAttendRecord kmExamAttendRecord = this.getKmExamAttendRecord(id);
		if(kmExamAttendRecord==null){
			return "";
		}
		//TODO 请修改代码
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAttendRecordDao#getKmExamAttendRecords(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExamAttendRecords(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamAttendRecord kmExamAttendRecord";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public KmExamAttendRecord getKmExamAttendRecord(final String userId,
			final String ipAddress, final String testID) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria crit = session.createCriteria(KmExamAttendRecord.class);
				crit.add(Expression.eq("userId", userId));
				crit.add(Expression.eq("macAddress", ipAddress));
				crit.add(Expression.eq("testId", testID));
				return crit.uniqueResult();
			}
		};
		return (KmExamAttendRecord) getHibernateTemplate().execute(callback);
	}

	public KmExamAttendRecord getKmExamAttendRecordByUser(final String userId,final String ipAddress) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Date currentDate = new Date();
				Criteria crit = session.createCriteria(KmExamAttendRecord.class);
				crit.add(Expression.eq("userId", userId));
				crit.add(Expression.le("inTime", currentDate));
				crit.add(Expression.ge("overTime", currentDate));
				crit.add(Expression.eq("isOut", "0"));
				crit.add(Expression.ne("macAddress", ipAddress));
				return crit.uniqueResult();
			}
		};
		return (KmExamAttendRecord) getHibernateTemplate().execute(callback);
	}
	
	public KmExamAttendRecord getKmExamAttendNoOtherRecord(final String userId,
			final String ipAddress, final String testID) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria crit = session.createCriteria(KmExamAttendRecord.class);
				Date currentDate = new Date();
				crit.add(Expression.eq("userId", userId));
				crit.add(Expression.eq("macAddress", ipAddress));
				crit.add(Expression.le("inTime", currentDate));
				crit.add(Expression.ge("overTime", currentDate));
				crit.add(Expression.eq("isOut", "0"));
				crit.add(Expression.ne("testId", testID));
				return crit.uniqueResult();
			}
		};
		return (KmExamAttendRecord) getHibernateTemplate().execute(callback);
	}
}