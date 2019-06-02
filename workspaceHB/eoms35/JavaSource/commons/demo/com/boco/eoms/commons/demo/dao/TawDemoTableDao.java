package com.boco.eoms.commons.demo.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.demo.model.TawDemoTable;

public interface TawDemoTableDao extends Dao {

	/**
	 * Retrieves all of the tawDemoTables
	 */
	public List getTawDemoTables(TawDemoTable tawDemoTable);

	/**
	 * Gets tawDemoTable's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawDemoTable's id
	 * @return tawDemoTable populated tawDemoTable object
	 */
	public TawDemoTable getTawDemoTable(final Long id);

	/**
	 * Saves a tawDemoTable's information
	 * 
	 * @param tawDemoTable
	 *            the object to be saved
	 */
	public void saveTawDemoTable(TawDemoTable tawDemoTable);

	/**
	 * Removes a tawDemoTable from the database by id
	 * 
	 * @param id
	 *            the tawDemoTable's id
	 */
	public void removeTawDemoTable(final Long id);
}
