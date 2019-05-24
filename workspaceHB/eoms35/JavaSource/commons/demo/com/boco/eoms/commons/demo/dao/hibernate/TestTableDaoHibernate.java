package com.boco.eoms.commons.demo.dao.hibernate;

import java.util.List;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.demo.model.TestTable;
import com.boco.eoms.commons.demo.dao.TestTableDao;
import com.boco.eoms.commons.loging.BocoLog;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TestTableDaoHibernate extends BaseDaoHibernate implements
		TestTableDao {

	/**
	 * @see com.boco.eoms.commons.demo.dao.TestTableDao#getTestTables(com.boco.eoms.commons.demo.model.TestTable)
	 */
	public List getTestTables(final TestTable testTable) {
		return getHibernateTemplate().find("from TestTable");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (testTable == null) { return
		 * getHibernateTemplate().find("from TestTable"); } else { // filter on
		 * properties set in the testTable HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(testTable).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TestTable.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TestTableDao#getTestTable(Integer id)
	 */
	public TestTable getTestTable(final Integer id) {
		TestTable testTable = (TestTable) getHibernateTemplate().get(
				TestTable.class, id);
		if (testTable == null) {
			BocoLog.warn(this,"uh oh, testTable with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(TestTable.class, id);
		}

		return testTable;
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TestTableDao#saveTestTable(TestTable
	 *      testTable)
	 */
	public void saveTestTable(final TestTable testTable) {
		if ((testTable.getId() == null) || (testTable.getId().equals("")))
			getHibernateTemplate().save(testTable);
		else
			getHibernateTemplate().saveOrUpdate(testTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.dao.TestTableDao#removeTestTable(Integer
	 *      id)
	 */
	public void removeTestTable(final Integer id) {
		getHibernateTemplate().delete(getTestTable(id));
	}
}
