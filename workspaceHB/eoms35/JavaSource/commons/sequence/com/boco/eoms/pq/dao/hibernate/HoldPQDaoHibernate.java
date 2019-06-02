package com.boco.eoms.pq.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.pq.dao.IHoldPQDao;
import com.boco.eoms.pq.model.HoldPQ;
import com.boco.eoms.pq.util.Constants;

public class HoldPQDaoHibernate extends BaseDaoHibernate implements IHoldPQDao {

	public void delHoldPQ(HoldPQ holdPQ) {
		getHibernateTemplate().delete(holdPQ);
	}

	public HoldPQ getHoldPQ(String jobId) {
		HoldPQ holdPQ = (HoldPQ) getHibernateTemplate()
				.get(HoldPQ.class, jobId);
		if (null == holdPQ) {
			throw new ObjectRetrievalFailureException(HoldPQ.class, jobId);
		}
		return holdPQ;
	}

	public List listHoldPQs(String status, String deleted) {
		return getHibernateTemplate().find(
				"from HoldPQ holdPQ where holdPQ.status='" + status
						+ "' and holdPQ.deleted='" + deleted
						+ "' order by holdPQ.insertTime asc");
	}

	public void removeHoldPQ(HoldPQ holdPQ) {
		holdPQ.setDeleted(Constants.DELETED);
		saveHoldPQ(holdPQ);
	}

	public String saveHoldPQ(HoldPQ holdPQ) {
		if (null == holdPQ || "".equals(holdPQ.getJobId())) {
			getHibernateTemplate().save(holdPQ);
		} else {
			getHibernateTemplate().saveOrUpdate(holdPQ);
		}
		return holdPQ.getJobId();
	}

	public Map listHoldPQs(final Integer curPage, final Integer pageSize,
			final String status, final String deleted) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from HoldPQ holdPQ where holdPQ.status='"
						+ status + "' and holdPQ.deleted='" + deleted
						+ "' order by holdPQ.insertTime asc";
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

}
