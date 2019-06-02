package com.boco.eoms.commons.demo.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.demo.model.TawPrefixTable;

public interface TawPrefixTableDao extends Dao {

	/**
	 * Retrieves all of the tawPrefixTables
	 */
	public List getTawPrefixTables(TawPrefixTable tawPrefixTable);

	/**
	 * Gets tawPrefixTable's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawPrefixTable's id
	 * @return tawPrefixTable populated tawPrefixTable object
	 */
	public TawPrefixTable getTawPrefixTable(final Long id);

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
	public void removeTawPrefixTable(final Long id);
}
