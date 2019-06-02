package com.boco.eoms.commons.message.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;

public interface TawCommonMessageMonitorManager extends Manager {
	/**
	 * Retrieves all of the tawCommonMessageMonitors
	 */
	public List getTawCommonMessageMonitors(
			TawCommonMessageMonitor tawCommonMessageMonitor);

	/**
	 * Gets tawCommonMessageMonitor's information based on monitorId.
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
