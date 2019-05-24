package com.boco.eoms.commons.demo.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.demo.model.TawPrefixTable;

public interface TawPrefixTableManager extends Manager {
	/**
	 * Retrieves all of the tawPrefixTables
	 */
	public List getTawPrefixTables(TawPrefixTable tawPrefixTable);

	/**
	 * Gets tawPrefixTable's information based on id.
	 * 
	 * @param id
	 *            the tawPrefixTable's id
	 * @return tawPrefixTable populated tawPrefixTable object
	 */
	public TawPrefixTable getTawPrefixTable(final String id);

	/**
	 * Saves a tawPrefixTable's information
	 * 
	 * @param tawPrefixTable
	 *            the object to be saved
	 */
	public void saveTawPrefixTable(TawPrefixTable tawPrefixTable);

	/**
	 * Removes a tawPrefixTable from the database by id
	 * 
	 * @param id
	 *            the tawPrefixTable's id
	 */
	public void removeTawPrefixTable(final String id);
}
