package com.boco.eoms.commons.message.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef;

public interface TawCommonMessageMonitorRefManager extends Manager {
	/**
	 * Retrieves all of the tawCommonMessageMonitorRefs
	 */
	public List getTawCommonMessageMonitorRefs(
			TawCommonMessageMonitorRef tawCommonMessageMonitorRef);

	/**
	 * Gets tawCommonMessageMonitorRef's information based on id.
	 * 
	 * @param id
	 *            the tawCommonMessageMonitorRef's id
	 * @return tawCommonMessageMonitorRef populated tawCommonMessageMonitorRef
	 *         object
	 */
	public TawCommonMessageMonitorRef getTawCommonMessageMonitorRef(
			final String id);

	/**
	 * Saves a tawCommonMessageMonitorRef's information
	 * 
	 * @param tawCommonMessageMonitorRef
	 *            the object to be saved
	 */
	public void saveTawCommonMessageMonitorRef(
			TawCommonMessageMonitorRef tawCommonMessageMonitorRef);

	/**
	 * Removes a tawCommonMessageMonitorRef from the database by id
	 * 
	 * @param id
	 *            the tawCommonMessageMonitorRef's id
	 */
	public void removeTawCommonMessageMonitorRef(final String id);
}
