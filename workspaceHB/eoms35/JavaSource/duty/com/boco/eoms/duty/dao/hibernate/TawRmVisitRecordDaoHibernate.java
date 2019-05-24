package com.boco.eoms.duty.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.duty.model.TawRmVisitRecord;
import com.boco.eoms.duty.model.TawRmVisitRecordStat;
import com.boco.eoms.duty.dao.ITawRmVisitRecordDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawRmVisitRecordDaoHibernate extends BaseDaoHibernate implements
		ITawRmVisitRecordDao {

	/**
	 * @see com.boco.eoms.duty.dao.TawRmVisitRecordDao#getTawRmVisitRecords(com.boco.eoms.duty.model.TawRmVisitRecord)
	 */
	public List getTawRmVisitRecords(final TawRmVisitRecord tawRmVisitRecord) {
		return getHibernateTemplate().find("from TawRmVisitRecord");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawRmVisitRecord == null) {
		 * return getHibernateTemplate().find("from TawRmVisitRecord"); } else { //
		 * filter on properties set in the tawRmVisitRecord HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawRmVisitRecord).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawRmVisitRecord.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmVisitRecordDao#getTawRmVisitRecord(String
	 *      id)
	 */
	public TawRmVisitRecord getTawRmVisitRecord(final String id) {
		TawRmVisitRecord tawRmVisitRecord = (TawRmVisitRecord) getHibernateTemplate()
				.get(TawRmVisitRecord.class, id);
		if (tawRmVisitRecord == null) {
			throw new ObjectRetrievalFailureException(TawRmVisitRecord.class,
					id);
		}

		return tawRmVisitRecord;
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmVisitRecordDao#saveTawRmVisitRecord(TawRmVisitRecord
	 *      tawRmVisitRecord)
	 */
	public void saveTawRmVisitRecord(final TawRmVisitRecord tawRmVisitRecord) {
		if ((tawRmVisitRecord.getId() == null)
				|| (tawRmVisitRecord.getId().equals("")))
			getHibernateTemplate().save(tawRmVisitRecord);
		else
			getHibernateTemplate().saveOrUpdate(tawRmVisitRecord);
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmVisitRecordDao#removeTawRmVisitRecord(String
	 *      id)
	 */
	public void removeTawRmVisitRecord(final String id) {
		getHibernateTemplate().delete(getTawRmVisitRecord(id));
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmVisitRecordDao#getTawRmVisitRecords(final
	 *      Integer curPage, final Integer pageSize,final String whereStr)
	 */
	public Map getTawRmVisitRecords(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawRmVisitRecord
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawRmVisitRecord";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
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
	 * @see com.boco.eoms.duty.dao.TawRmVisitRecordDao#getTawRmVisitRecords(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawRmVisitRecords(final Integer curPage,
			final Integer pageSize) {
		return this.getTawRmVisitRecords(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.duty.dao.TawRmVisitRecordDao#getChildList(String
	 *      parentId)
	 */
	public ArrayList getChildList(String parentId) {
		String hql = " from TawRmVisitRecord obj where obj.parentId='"
				+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}
}
