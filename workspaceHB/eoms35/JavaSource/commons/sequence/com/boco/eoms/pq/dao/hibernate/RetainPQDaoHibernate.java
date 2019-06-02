package com.boco.eoms.pq.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.pq.dao.IRetainPQDao;
import com.boco.eoms.pq.model.RetainPQ;
import com.boco.eoms.pq.util.Constants;

public class RetainPQDaoHibernate extends BaseDaoHibernate implements
		IRetainPQDao {

	public void delRetainPQ(RetainPQ retainPQ) {
		getHibernateTemplate().delete(retainPQ);
	}

	public RetainPQ getRetainPQ(String jobId) {
		RetainPQ retainPQ = (RetainPQ) getHibernateTemplate().get(
				RetainPQ.class, jobId);
		if (null == retainPQ) {
			throw new ObjectRetrievalFailureException(RetainPQ.class, jobId);
		}
		return retainPQ;
	}

	public List listRetainPQs(String status, String deleted) {
		return getHibernateTemplate().find(
				"from RetainPQ retainPQ where retainPQ.status='" + status
						+ "' and retainPQ.deleted='" + deleted
						+ "' order by retainPQ.insertTime asc");
	}

	public void removeRetainPQ(RetainPQ retainPQ) {
		retainPQ.setDeleted(Constants.DELETED);
		saveRetainPQ(retainPQ);
	}

	public String saveRetainPQ(RetainPQ retainPQ) {
		if (null == retainPQ || "".equals(retainPQ.getJobId())) {
			getHibernateTemplate().save(retainPQ);
		} else {
			getHibernateTemplate().saveOrUpdate(retainPQ);
		}
		return retainPQ.getJobId();
	}

}
