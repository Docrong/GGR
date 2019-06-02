package com.boco.eoms.commons.interfaceMonitoring.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceMonitoringDao;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceMonitoring;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;
import com.boco.eoms.workbench.infopub.model.Forums;
import com.boco.eoms.workbench.netdisk.dao.ITawWorkbenchNetDiskDao;

public class InterfaceMonitoringDaoHibernate extends BaseDaoHibernate implements
		InterfaceMonitoringDao {

	public Map getMonitoringLog(final Integer curPage, final Integer pageSize,
			InterfaceMonitoringForm form) {
		// String callingTime = form.getCallingTime();
		String hql = "from InterfaceMonitoring interfaceMonitoring";
		if (form != null) {
			String callingSide = form.getCallingSide();
			String provider = form.getProvider();
			String interFaceType = form.getInterFaceType();
			String interFaceMethod = form.getInterFaceMethod();
			String callingStartTime = form.getStartTime();
			String callingEndTime = form.getEndTime();
			String callDirection = form.getCallDirection();
			String success=form.getSuccess();

			hql += " where interfaceMonitoring.callDirection='" + callDirection
					+ "'";
			if (!"".equals(callingSide) && callingSide != null) {
				hql += " and interfaceMonitoring.callingSide='" + callingSide
						+ "'";
			}
			if (!"".equals(provider) && provider != null) {
				hql += " and interfaceMonitoring.provider='" + provider + "'";
			}
			if (!"".equals(interFaceType) && interFaceType != null) {

				hql += " and interfaceMonitoring.interFaceType='"
						+ interFaceType + "'";

			}
			if (!"".equals(interFaceMethod) && interFaceMethod != null) {
				hql += " and interfaceMonitoring.interFaceMethod='"
						+ interFaceMethod + "'";

			}
			if (!"".equals(callingStartTime) && callingStartTime != null) {

				hql += " and  interfaceMonitoring.callingTime>'"
						+ callingStartTime + "'";

			}
			if (!"".equals(callingEndTime) && callingEndTime != null) {
				hql += " and interfaceMonitoring.callingTime<'"
						+ callingEndTime + "'";

			}
			if (!"".equals(success) && success != null) {
				hql += " and interfaceMonitoring.success='"
						+ success + "'";

			}
		}
		return search(curPage, pageSize, hql);

	}

	public Map search(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		// filter on properties set in the forums
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "";

				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + whereStr;

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

	public InterfaceMonitoring getInterfaceMonitoring(final String id) {
		InterfaceMonitoring interfaceMonitoring = (InterfaceMonitoring) getHibernateTemplate()
				.get(InterfaceMonitoring.class, id);
		if (interfaceMonitoring == null) {
			throw new ObjectRetrievalFailureException(Forums.class, id);
		}

		return interfaceMonitoring;
	}

	public void save(InterfaceMonitoring interfaceMonitoring) {
		if (interfaceMonitoring.getId() == null
				|| "".equals(interfaceMonitoring.getId())) {
			getHibernateTemplate().save(interfaceMonitoring);
		} else {
			getHibernateTemplate().saveOrUpdate(interfaceMonitoring);
		}

	}
}
