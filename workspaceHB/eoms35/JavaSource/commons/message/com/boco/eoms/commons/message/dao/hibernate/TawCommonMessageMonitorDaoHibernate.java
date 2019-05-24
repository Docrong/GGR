package com.boco.eoms.commons.message.dao.hibernate;

import java.util.List;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;
import com.boco.eoms.commons.message.dao.TawCommonMessageMonitorDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageMonitorDaoHibernate extends BaseDaoHibernate
		implements TawCommonMessageMonitorDao {

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageMonitorDao#getTawCommonMessageMonitors(com.boco.eoms.commons.message.model.TawCommonMessageMonitor)
	 */
	public List getTawCommonMessageMonitors(
			final TawCommonMessageMonitor tawCommonMessageMonitor) {
		return getHibernateTemplate().find("from TawCommonMessageMonitor");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonMessageMonitor ==
		 * null) { return getHibernateTemplate().find("from
		 * TawCommonMessageMonitor"); } else { // filter on properties set in
		 * the tawCommonMessageMonitor HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawCommonMessageMonitor).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonMessageMonitor.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageMonitorDao#getTawCommonMessageMonitor(Integer
	 *      monitorId)
	 */
	public TawCommonMessageMonitor getTawCommonMessageMonitor(
			final String monitorId) {
		TawCommonMessageMonitor tawCommonMessageMonitor = (TawCommonMessageMonitor) getHibernateTemplate()
				.get(TawCommonMessageMonitor.class, monitorId);
		if (tawCommonMessageMonitor == null) {
			BocoLog.warn(this,
					"uh oh, tawCommonMessageMonitor with monitorId '"
							+ monitorId + "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonMessageMonitor.class, monitorId);
		}

		return tawCommonMessageMonitor;
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageMonitorDao#saveTawCommonMessageMonitor(TawCommonMessageMonitor
	 *      tawCommonMessageMonitor)
	 */
	public void saveTawCommonMessageMonitor(
			final TawCommonMessageMonitor tawCommonMessageMonitor) {
		if ((tawCommonMessageMonitor.getMonitorId() == null)
				|| (tawCommonMessageMonitor.getMonitorId().equals("")))
			getHibernateTemplate().save(tawCommonMessageMonitor);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonMessageMonitor);
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageMonitorDao#removeTawCommonMessageMonitor(Integer
	 *      monitorId)
	 */
	public void removeTawCommonMessageMonitor(final String monitorId) {
		getHibernateTemplate().delete(getTawCommonMessageMonitor(monitorId));
	}
}
