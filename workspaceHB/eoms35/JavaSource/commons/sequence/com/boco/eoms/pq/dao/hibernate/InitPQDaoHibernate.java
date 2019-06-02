package com.boco.eoms.pq.dao.hibernate;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.pq.dao.IInitPQDao;
import com.boco.eoms.pq.model.InitPQ;
import com.boco.eoms.pq.util.Constants;

public class InitPQDaoHibernate extends BaseDaoHibernate implements IInitPQDao {

	public void delInitPQ(InitPQ initPQ) {
		getHibernateTemplate().delete(initPQ);
	}

	public InitPQ getInitPQ(String jobId) {
		InitPQ initPQ = (InitPQ) getHibernateTemplate()
				.get(InitPQ.class, jobId);
		if (null == initPQ) {
			throw new ObjectRetrievalFailureException(InitPQ.class, jobId);
		}
		return initPQ;
	}

	public List listInitPQs(String status, String deleted) {
		return getHibernateTemplate().find(
				"from InitPQ initPQ where initPQ.status='" + status
						+ "' and initPQ.deleted='" + deleted
						+ "' order by initPQ.insertTime asc");
	}

	public void removeInitPQ(InitPQ initPQ) {
		initPQ.setDeleted(Constants.DELETED);
		saveInitPQ(initPQ);
	}

	public String saveInitPQ(InitPQ initPQ) {
		if (null == initPQ || "".equals(initPQ.getJobId())) {
			getHibernateTemplate().save(initPQ);
		} else {
			getHibernateTemplate().saveOrUpdate(initPQ);
		}
		return initPQ.getJobId();
	}

}
