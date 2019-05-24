package com.boco.eoms.commons.message.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;
import com.boco.eoms.commons.message.dao.TawCommonMessageMonitorDao;
import com.boco.eoms.commons.message.service.TawCommonMessageMonitorManager;

public class TawCommonMessageMonitorManagerImpl extends BaseManager implements
		TawCommonMessageMonitorManager {
	private TawCommonMessageMonitorDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonMessageMonitorDao(TawCommonMessageMonitorDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageMonitorManager#getTawCommonMessageMonitors(com.boco.eoms.commons.message.model.TawCommonMessageMonitor)
	 */
	public List getTawCommonMessageMonitors(
			final TawCommonMessageMonitor tawCommonMessageMonitor) {
		return dao.getTawCommonMessageMonitors(tawCommonMessageMonitor);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageMonitorManager#getTawCommonMessageMonitor(String
	 *      monitorId)
	 */
	public TawCommonMessageMonitor getTawCommonMessageMonitor(
			final String monitorId) {
		return dao.getTawCommonMessageMonitor(monitorId);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageMonitorManager#saveTawCommonMessageMonitor(TawCommonMessageMonitor
	 *      tawCommonMessageMonitor)
	 */
	public void saveTawCommonMessageMonitor(
			TawCommonMessageMonitor tawCommonMessageMonitor) {
		dao.saveTawCommonMessageMonitor(tawCommonMessageMonitor);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageMonitorManager#removeTawCommonMessageMonitor(String
	 *      monitorId)
	 */
	public void removeTawCommonMessageMonitor(final String monitorId) {
		dao.removeTawCommonMessageMonitor(monitorId);
	}
}
