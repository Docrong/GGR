package com.boco.eoms.workbench.commission.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.commission.dao.ITawWorkbenchCommissionInstanceDao;
import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionInstance;

public class TawWorkbenchCommissionInstanceDaoHibernate extends
		BaseDaoHibernate implements ITawWorkbenchCommissionInstanceDao {

	public TawWorkbenchCommissionInstance getCommissionInstance(String id) {
		TawWorkbenchCommissionInstance commissionInstance = (TawWorkbenchCommissionInstance) getHibernateTemplate()
				.get(TawWorkbenchCommissionInstance.class, id);
		if (commissionInstance == null) {
			throw new ObjectRetrievalFailureException(
					TawWorkbenchCommissionInstance.class, id);
		}
		return commissionInstance;
	}

	public Map listCommissionInstances(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawWorkbenchCommissionInstance";
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

	public List listCommissionInstances(String userId, String moduleId) {
		String hql = " from TawWorkbenchCommissionInstance instance where userId='"
				+ userId + "' and moduleId='" + moduleId + "'";
		return getHibernateTemplate().find(hql);
	}
	
	public List listCommissionInstancesByTrustorId(String trustorId, String moduleId) {
		String hql = " from TawWorkbenchCommissionInstance instance where trustorId='"
				+ trustorId + "' and moduleId='" + moduleId + "'" + " and  endTime > to_char(sysdate,'YYYY-MM-DD HH:MM:SS') and startTime <  to_char(sysdate,'YYYY-MM-DD HH:MM:SS') ";
		return getHibernateTemplate().find(hql);
	}
	//2009-01-16 
	public List listCommissionInstancesByUserId(String userId, String moduleId) {
		String hql = " from TawWorkbenchCommissionInstance instance where userId='"
				+ userId + "' and moduleId='" + moduleId + "'" + " and  endTime > to_char(sysdate,'YYYY-MM-DD HH:MM:SS') and startTime <  to_char(sysdate,'YYYY-MM-DD HH:MM:SS') ";
		return getHibernateTemplate().find(hql);
	}

	public List listCommissionInstances(String userId) {
		String hql = " from TawWorkbenchCommissionInstance instance where userId='"
				+ userId + "'";
		return getHibernateTemplate().find(hql);
	}
	
	public List listCommissionInstancesByTrustorId(String trustorId) {
		String hql = " from TawWorkbenchCommissionInstance instance where trustorId='"
				+ trustorId + "'" + " and  endTime > to_char(sysdate,'YYYY-MM-DD HH:MM:SS') and startTime <  to_char(sysdate,'YYYY-MM-DD HH:MM:SS') ";
		return getHibernateTemplate().find(hql);
	}
	//2009-01-16
	public List listCommissionInstancesByUserId(String userId) {
		String hql = " from TawWorkbenchCommissionInstance instance where userId='"
				+ userId + "'" + " and  endTime > to_char(sysdate,'YYYY-MM-DD HH:MM:SS') and startTime <  to_char(sysdate,'YYYY-MM-DD HH:MM:SS') ";
		return getHibernateTemplate().find(hql);
	}
	
	public List listCommissionInstancesByPeriod(String startTime, String endTime) {
		String hql = " from TawWorkbenchCommissionInstance instance where startTime>'"
				+ startTime + "' and endTime<'" + endTime + "'";
		return getHibernateTemplate().find(hql);
	}

	public void saveCommissionInstance(
			TawWorkbenchCommissionInstance commissionInstance) {
		if (commissionInstance.getId() == null
				|| "".equals(commissionInstance.getId())) {
			getHibernateTemplate().save(commissionInstance);
		} else {
			getHibernateTemplate().saveOrUpdate(commissionInstance);
		}
	}

	public void delCommissionInstance(
			TawWorkbenchCommissionInstance commissionInstance) {
		getHibernateTemplate().delete(commissionInstance);
	}

}
