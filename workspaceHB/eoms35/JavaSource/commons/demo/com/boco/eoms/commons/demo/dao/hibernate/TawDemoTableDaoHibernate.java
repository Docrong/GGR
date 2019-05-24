package com.boco.eoms.commons.demo.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.demo.model.TawDemoTable;
import com.boco.eoms.commons.demo.dao.TawDemoTableDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawDemoTableDaoHibernate extends BaseDaoHibernate implements
		TawDemoTableDao {

	/**
	 * @see com.boco.eoms.commons.demo.dao.TawDemoTableDao#getTawDemoTables(com.boco.eoms.commons.demo.model.TawDemoTable)
	 */
	public List getTawDemoTables(final TawDemoTable tawDemoTable) {
		return getHibernateTemplate().find("from TawDemoTable");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawDemoTable == null) {
		 * return getHibernateTemplate().find("from TawDemoTable"); } else { //
		 * filter on properties set in the tawDemoTable HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawDemoTable).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawDemoTable.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TawDemoTableDao#getTawDemoTable(Long
	 *      id)
	 */
	public TawDemoTable getTawDemoTable(final Long id) {
		TawDemoTable tawDemoTable = (TawDemoTable) getHibernateTemplate().get(
				TawDemoTable.class, id);
		if (tawDemoTable == null) {
			BocoLog.warn(this,"uh oh, tawDemoTable with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(TawDemoTable.class, id);
		}

		return tawDemoTable;
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TawDemoTableDao#saveTawDemoTable(TawDemoTable
	 *      tawDemoTable)
	 */
	public void saveTawDemoTable(final TawDemoTable tawDemoTable) {
		if ((tawDemoTable.getId() == null) || (tawDemoTable.getId().equals("")))
			getHibernateTemplate().save(tawDemoTable);
		else
			getHibernateTemplate().saveOrUpdate(tawDemoTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TawDemoTableDao#removeTawDemoTable(Long
	 *      id)
	 */
	public void removeTawDemoTable(final Long id) {
		getHibernateTemplate().delete(getTawDemoTable(id));
	}
}
