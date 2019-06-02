package com.boco.eoms.commons.demo.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.demo.model.TestTable;

public interface TestTableManager extends Manager {
	/**
	 * Retrieves all of the testTables
	 */
	public List getTestTables(TestTable testTable);

	/**
	 * Gets testTable's information based on id.
	 * 
	 * @param id
	 *            the testTable's id
	 * @return testTable populated testTable object
	 */
	public TestTable getTestTable(final String id);

	/**
	 * Saves a testTable's information
	 * 
	 * @param testTable
	 *            the object to be saved
	 */
	public void saveTestTable(TestTable testTable);

	/**
	 * Removes a testTable from the database by id
	 * 
	 * @param id
	 *            the testTable's id
	 */
	public void removeTestTable(final String id);
}
