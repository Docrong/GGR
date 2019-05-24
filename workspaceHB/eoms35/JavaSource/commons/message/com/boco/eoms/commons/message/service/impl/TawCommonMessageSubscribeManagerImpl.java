package com.boco.eoms.commons.message.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;
import com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao;
import com.boco.eoms.commons.message.dao.TawCommonMessageSubscribeDao;
import com.boco.eoms.commons.message.service.TawCommonMessageSubscribeManager;

public class TawCommonMessageSubscribeManagerImpl extends BaseManager implements
		TawCommonMessageSubscribeManager {
	private TawCommonMessageSubscribeDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonMessageSubscribeDao(TawCommonMessageSubscribeDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageSubscribeManager#getTawCommonMessageSubscribes(com.boco.eoms.commons.message.model.TawCommonMessageSubscribe)
	 */
	public List getTawCommonMessageSubscribes(
			final TawCommonMessageSubscribe tawCommonMessageSubscribe) {
		return dao.getTawCommonMessageSubscribes(tawCommonMessageSubscribe);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageSubscribeManager#getTawCommonMessageSubscribe(String
	 *      id)
	 */
	public TawCommonMessageSubscribe getTawCommonMessageSubscribe(
			final String id) {
		return dao.getTawCommonMessageSubscribe(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageSubscribeManager#saveTawCommonMessageSubscribe(TawCommonMessageSubscribe
	 *      tawCommonMessageSubscribe)
	 */
	public void saveTawCommonMessageSubscribe(
			TawCommonMessageSubscribe tawCommonMessageSubscribe) {
		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageSubscribeManager#removeTawCommonMessageSubscribe(String
	 *      id)
	 */
	public void removeTawCommonMessageSubscribe(final String id) {
		dao.removeTawCommonMessageSubscribe(new String(id));
	}

	/**
	 * 根据userid查找所定制的服务
	 * 
	 * @param userid
	 * @return
	 */
	public List getMessageScByuserid(String userid) {
		List list = null;
		list = dao.getMessageScByuserid(userid);
		return list;
	}

	/**
	 * 根据modelid operid 查找消息服务
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getMessageScByModelidAndOperid(String modelid, String operid) {
		List list = null;
		TawCommonMessageAddService addservice = null;
		TawCommonMessageAddServiceDao servicedao = (TawCommonMessageAddServiceDao) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageAddServiceDao");
		addservice = servicedao.getMessageService(modelid, operid);
		String serviceid = addservice.getId();
		list = dao.getMessageScByModelidAndOperid(serviceid);
		return list;
	}
}
