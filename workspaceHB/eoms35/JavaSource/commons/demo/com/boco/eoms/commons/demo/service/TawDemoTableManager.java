package com.boco.eoms.commons.demo.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.demo.model.TawDemoTable;

public interface TawDemoTableManager extends Manager {
	/**
	 * Retrieves all of the tawDemoTables
	 */
	public List getTawDemoTables(TawDemoTable tawDemoTable);

	/**
	 * Gets tawDemoTable's information based on id.
	 * 
	 * @param id
	 *            the tawDemoTable's id
	 * @return tawDemoTable populated tawDemoTable object
	 */
	public TawDemoTable getTawDemoTable(final String id);

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
	public void removeTawDemoTable(final String id);
}
