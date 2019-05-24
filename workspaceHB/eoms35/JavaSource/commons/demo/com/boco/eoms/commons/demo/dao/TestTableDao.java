package com.boco.eoms.commons.demo.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.demo.model.TestTable;

public interface TestTableDao extends Dao {

	/**
	 * Retrieves all of the testTables
	 */
	public List getTestTables(TestTable testTable);

	/**
	 * Gets testTable's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the testTable's id
	 * @return testTable populated testTable object
	 */
	public TestTable getTestTable(final Integer id);

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
	public void removeTestTable(final Integer id);
}
