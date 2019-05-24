package com.boco.eoms.commons.message.dao.hibernate;

import java.util.List;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef;
import com.boco.eoms.commons.message.dao.TawCommonMessageMonitorRefDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageMonitorRefDaoHibernate extends BaseDaoHibernate
		implements TawCommonMessageMonitorRefDao {

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageMonitorRefDao#getTawCommonMessageMonitorRefs(com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef)
	 */
	public List getTawCommonMessageMonitorRefs(
			final TawCommonMessageMonitorRef tawCommonMessageMonitorRef) {
		return getHibernateTemplate().find("from TawCommonMessageMonitorRef");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawCommonMessageMonitorRef ==
		 * null) { return getHibernateTemplate().find("from
		 * TawCommonMessageMonitorRef"); } else { // filter on properties set in
		 * the tawCommonMessageMonitorRef HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawCommonMessageMonitorRef).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawCommonMessageMonitorRef.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageMonitorRefDao#getTawCommonMessageMonitorRef(String
	 *      id)
	 */
	public TawCommonMessageMonitorRef getTawCommonMessageMonitorRef(
			final String id) {
		TawCommonMessageMonitorRef tawCommonMessageMonitorRef = (TawCommonMessageMonitorRef) getHibernateTemplate()
				.get(TawCommonMessageMonitorRef.class, id);
		if (tawCommonMessageMonitorRef == null) {
			BocoLog.warn(this,"uh oh, tawCommonMessageMonitorRef with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					TawCommonMessageMonitorRef.class, id);
		}

		return tawCommonMessageMonitorRef;
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageMonitorRefDao#saveTawCommonMessageMonitorRef(TawCommonMessageMonitorRef
	 *      tawCommonMessageMonitorRef)
	 */
	public void saveTawCommonMessageMonitorRef(
			final TawCommonMessageMonitorRef tawCommonMessageMonitorRef) {
		if ((tawCommonMessageMonitorRef.getId() == null)
				|| (tawCommonMessageMonitorRef.getId().equals("")))
			getHibernateTemplate().save(tawCommonMessageMonitorRef);
		else
			getHibernateTemplate().saveOrUpdate(tawCommonMessageMonitorRef);
	}

	/**
	 * @see com.boco.eoms.commons.message.dao.TawCommonMessageMonitorRefDao#removeTawCommonMessageMonitorRef(String
	 *      id)
	 */
	public void removeTawCommonMessageMonitorRef(final String id) {
		getHibernateTemplate().delete(getTawCommonMessageMonitorRef(id));
	}
}
