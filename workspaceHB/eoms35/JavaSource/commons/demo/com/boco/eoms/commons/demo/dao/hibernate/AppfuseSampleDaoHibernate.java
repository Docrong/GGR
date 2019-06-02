package com.boco.eoms.commons.demo.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.demo.model.AppfuseSample;
import com.boco.eoms.commons.demo.dao.AppfuseSampleDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.orm.ObjectRetrievalFailureException;

public class AppfuseSampleDaoHibernate extends BaseDaoHibernate implements
		AppfuseSampleDao {

	/**
	 * @see com.boco.eoms.commons.demo.dao.AppfuseSampleDao#getAppfuseSamples(com.boco.eoms.commons.demo.model.AppfuseSample)
	 */
	public List getAppfuseSamples(final AppfuseSample appfuseSample) {
		return getHibernateTemplate().find("from AppfuseSample");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (appfuseSample == null) {
		 * return getHibernateTemplate().find("from AppfuseSample"); } else { //
		 * filter on properties set in the appfuseSample HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(appfuseSample).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(AppfuseSample.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.AppfuseSampleDao#getAppfuseSample(String
	 *      id)
	 */
	public AppfuseSample getAppfuseSample(final String id) {
		AppfuseSample appfuseSample = (AppfuseSample) getHibernateTemplate()
				.get(AppfuseSample.class, id);
		if (appfuseSample == null) {
			BocoLog.warn(this,"uh oh, appfuseSample with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(AppfuseSample.class, id);
		}

		return appfuseSample;
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.AppfuseSampleDao#saveAppfuseSample(AppfuseSample
	 *      appfuseSample)
	 */
	public void saveAppfuseSample(final AppfuseSample appfuseSample) {
		if ((appfuseSample.getId() == null)
				|| (appfuseSample.getId().equals("")))
			getHibernateTemplate().save(appfuseSample);
		else
			getHibernateTemplate().saveOrUpdate(appfuseSample);
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.AppfuseSampleDao#removeAppfuseSample(String
	 *      id)
	 */
	public void removeAppfuseSample(final String id) {
		getHibernateTemplate().delete(getAppfuseSample(id));
	}
}
