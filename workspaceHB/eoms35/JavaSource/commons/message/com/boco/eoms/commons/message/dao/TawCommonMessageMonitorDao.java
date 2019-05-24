package com.boco.eoms.commons.message.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;

public interface TawCommonMessageMonitorDao extends Dao {

	/**
	 * Retrieves all of the tawCommonMessageMonitors
	 */
	public List getTawCommonMessageMonitors(
			TawCommonMessageMonitor tawCommonMessageMonitor);

	/**
	 * Gets tawCommonMessageMonitor's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param monitorId
	 *            the tawCommonMessageMonitor's monitorId
	 * @return tawCommonMessageMonitor populated tawCommonMessageMonitor object
	 */
	public TawCommonMessageMonitor getTawCommonMessageMonitor(
			final String monitorId);

	/**
	 * Saves a tawCommonMessageMonitor's information
	 * 
	 * @param tawCommonMessageMonitor
	 *            the object to be saved
	 */
	public void saveTawCommonMessageMonitor(
			TawCommonMessageMonitor tawCommonMessageMonitor);

	/**
	 * Removes a tawCommonMessageMonitor from the database by monitorId
	 * 
	 * @param monitorId
	 *            the tawCommonMessageMonitor's monitorId
	 */
	public void removeTawCommonMessageMonitor(final String monitorId);
}
