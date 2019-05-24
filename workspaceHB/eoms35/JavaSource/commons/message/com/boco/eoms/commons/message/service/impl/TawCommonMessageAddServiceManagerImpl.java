package com.boco.eoms.commons.message.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao;
import com.boco.eoms.commons.message.service.TawCommonMessageAddServiceManager;

public class TawCommonMessageAddServiceManagerImpl extends BaseManager
		implements TawCommonMessageAddServiceManager {
	private TawCommonMessageAddServiceDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonMessageAddServiceDao(
			TawCommonMessageAddServiceDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageAddServiceManager#getTawCommonMessageAddServices(com.boco.eoms.commons.message.model.TawCommonMessageAddService)
	 */
	public List getTawCommonMessageAddServices(
			final TawCommonMessageAddService tawCommonMessageAddService) {
		return dao.getTawCommonMessageAddServices(tawCommonMessageAddService);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageAddServiceManager#getTawCommonMessageAddService(String
	 *      id)
	 */
	public TawCommonMessageAddService getTawCommonMessageAddService(
			final String id) {
		return dao.getTawCommonMessageAddService(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageAddServiceManager#saveTawCommonMessageAddService(TawCommonMessageAddService
	 *      tawCommonMessageAddService)
	 */
	public void saveTawCommonMessageAddService(
			TawCommonMessageAddService tawCommonMessageAddService) {
		dao.saveTawCommonMessageAddService(tawCommonMessageAddService);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageAddServiceManager#removeTawCommonMessageAddService(String
	 *      id)
	 */
	public void removeTawCommonMessageAddService(final String id) {
		dao.removeTawCommonMessageAddService(new String(id));
	}

	/**
	 * 根据userid得到消息服务
	 * 
	 * @param userid
	 * @return
	 */
	public List getMesageServicebyuserids(String userid) {
		List list = null;
		list = (ArrayList) dao.getMesageServicebyuserids(userid);
		return list;
	}

	/**
	 * 得到消息服务 根据MODELID 和OPERID
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageAddService getMessageService(String modelid,
			String operid) {

		TawCommonMessageAddService addservice = null;
		addservice = dao.getMessageService(modelid, operid);

		return addservice;
	}
}
