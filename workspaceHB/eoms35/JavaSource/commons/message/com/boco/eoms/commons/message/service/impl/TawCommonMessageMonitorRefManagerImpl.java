package com.boco.eoms.commons.message.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef;
import com.boco.eoms.commons.message.dao.TawCommonMessageMonitorRefDao;
import com.boco.eoms.commons.message.service.TawCommonMessageMonitorRefManager;

public class TawCommonMessageMonitorRefManagerImpl extends BaseManager
		implements TawCommonMessageMonitorRefManager {
	private TawCommonMessageMonitorRefDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonMessageMonitorRefDao(
			TawCommonMessageMonitorRefDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageMonitorRefManager#getTawCommonMessageMonitorRefs(com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef)
	 */
	public List getTawCommonMessageMonitorRefs(
			final TawCommonMessageMonitorRef tawCommonMessageMonitorRef) {
		return dao.getTawCommonMessageMonitorRefs(tawCommonMessageMonitorRef);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageMonitorRefManager#getTawCommonMessageMonitorRef(String
	 *      id)
	 */
	public TawCommonMessageMonitorRef getTawCommonMessageMonitorRef(
			final String id) {
		return dao.getTawCommonMessageMonitorRef(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageMonitorRefManager#saveTawCommonMessageMonitorRef(TawCommonMessageMonitorRef
	 *      tawCommonMessageMonitorRef)
	 */
	public void saveTawCommonMessageMonitorRef(
			TawCommonMessageMonitorRef tawCommonMessageMonitorRef) {
		dao.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageMonitorRefManager#removeTawCommonMessageMonitorRef(String
	 *      id)
	 */
	public void removeTawCommonMessageMonitorRef(final String id) {
		dao.removeTawCommonMessageMonitorRef(new String(id));
	}
}
