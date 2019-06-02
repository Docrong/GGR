package com.boco.eoms.commons.demo.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.demo.model.AppfuseSample;
import com.boco.eoms.commons.demo.dao.AppfuseSampleDao;
import com.boco.eoms.commons.demo.service.AppfuseSampleManager;

public class AppfuseSampleManagerImpl extends BaseManager implements
		AppfuseSampleManager {
	private AppfuseSampleDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setAppfuseSampleDao(AppfuseSampleDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.AppfuseSampleManager#getAppfuseSamples(com.boco.eoms.commons.demo.model.AppfuseSample)
	 */
	public List getAppfuseSamples(final AppfuseSample appfuseSample) {
		return dao.getAppfuseSamples(appfuseSample);
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.AppfuseSampleManager#getAppfuseSample(String
	 *      id)
	 */
	public AppfuseSample getAppfuseSample(final String id) {
		return dao.getAppfuseSample(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.AppfuseSampleManager#saveAppfuseSample(AppfuseSample
	 *      appfuseSample)
	 */
	public void saveAppfuseSample(AppfuseSample appfuseSample) {
		dao.saveAppfuseSample(appfuseSample);
	}

	/**
	 * @see com.boco.eoms.commons.demo.service.AppfuseSampleManager#removeAppfuseSample(String
	 *      id)
	 */
	public void removeAppfuseSample(final String id) {
		dao.removeAppfuseSample(new String(id));
	}
}
