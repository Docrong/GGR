package com.boco.eoms.commons.demo.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.demo.model.TestTable;
import com.boco.eoms.commons.demo.dao.TestTableDao;
import com.boco.eoms.commons.demo.service.TestTableManager;

public class TestTableManagerImpl extends BaseManager implements
		TestTableManager {
	private TestTableDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTestTableDao(TestTableDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TestTableManager#getTestTables(com.boco.eoms.commons.demo.model.TestTable)
	 */
	public List getTestTables(final TestTable testTable) {
		return dao.getTestTables(testTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TestTableManager#getTestTable(String
	 *      id)
	 */
	public TestTable getTestTable(final String id) {
		return dao.getTestTable(new Integer(id));
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TestTableManager#saveTestTable(TestTable
	 *      testTable)
	 */
	public void saveTestTable(TestTable testTable) {
		dao.saveTestTable(testTable);
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.TestTableManager#removeTestTable(String
	 *      id)
	 */
	public void removeTestTable(final String id) {
		dao.removeTestTable(new Integer(id));
	}
}
