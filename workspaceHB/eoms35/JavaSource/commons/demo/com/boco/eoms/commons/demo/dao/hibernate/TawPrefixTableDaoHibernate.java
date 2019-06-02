package com.boco.eoms.commons.demo.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.demo.model.TawPrefixTable;
import com.boco.eoms.commons.demo.dao.TawPrefixTableDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawPrefixTableDaoHibernate extends BaseDaoHibernate implements
		TawPrefixTableDao {

	/**
	 * @see com.boco.eoms.commons.demo.dao.TawPrefixTableDao#getTawPrefixTables(com.boco.eoms.commons.demo.model.TawPrefixTable)
	 */
	public List getTawPrefixTables(final TawPrefixTable tawPrefixTable) {
		return getHibernateTemplate().find("from TawPrefixTable");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawPrefixTable == null) {
		 * return getHibernateTemplate().find("from TawPrefixTable"); } else { //
		 * filter on properties set in the tawPrefixTable HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawPrefixTable).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawPrefixTable.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TawPrefixTableDao#getTawPrefixTable(Long
	 *      id)
	 */
	public TawPrefixTable getTawPrefixTable(final Long id) {
		TawPrefixTable tawPrefixTable = (TawPrefixTable) getHibernateTemplate()
				.get(TawPrefixTable.class, id);
		if (tawPrefixTable == null) {
			BocoLog.warn(this,"uh oh, tawPrefixTable with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(TawPrefixTable.class, id);
		}

		return tawPrefixTable;
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TawPrefixTableDao#saveTawPrefixTable(TawPrefixTable
	 *      tawPrefixTable)
	 */
	public void saveTawPrefixTable(final TawPrefixTable tawPrefixTable) {
		if ((tawPrefixTable.getId() == null)
				|| (tawPrefixTable.getId().equals("")))
			getHibernateTemplate().save(tawPrefixTable);
		else
			getHibernateTemplate().saveOrUpdate(tawPrefixTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TawPrefixTableDao#removeTawPrefixTable(Long
	 *      id)
	 */
	public void removeTawPrefixTable(final Long id) {
		getHibernateTemplate().delete(getTawPrefixTable(id));
	}
}
