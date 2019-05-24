package com.boco.eoms.commons.message.service.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;
import com.boco.eoms.commons.message.service.TawCommonMessageSubscribeManager;

import com.boco.eoms.commons.message.service.bo.ITawCommonMessageSubscirbeBo;

public class TawCommonMessageSubscirbeBoImpl implements
		ITawCommonMessageSubscirbeBo {

	TawCommonMessageSubscribeManager subscribeDao;

	/**
	 * 查找所有的定制服务
	 * 
	 * @return
	 */
	public List getSubscribes() {
		List list = null;
		TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();
		subscribeDao = (TawCommonMessageSubscribeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageSubscribeManager");
		list = subscribeDao
				.getTawCommonMessageSubscribes(tawCommonMessageSubscribe);
		return list;
	}

	/**
	 * 根据ID查找定制的服务
	 * 
	 * @param id
	 * @return
	 */
	public TawCommonMessageSubscribe getSubscribe(String id) {

		TawCommonMessageSubscribe subsc = null;
		subscribeDao = (TawCommonMessageSubscribeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageSubscribeManager");
		subsc = subscribeDao.getTawCommonMessageSubscribe(id);
		return subsc;
	}

	/**
	 * 根据用户ID查找定制的服务类型
	 * 
	 * @param userid
	 * @return
	 */
	public List getSubscByUserids(String userid) {
		List list = null;

		subscribeDao = (TawCommonMessageSubscribeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageSubscribeManager");
		list = (ArrayList) subscribeDao.getMessageScByuserid(userid);
		return list;
	}

	/**
	 * 根据modelid operid 查找消息服务
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getMessageScByModelidAndOperids(String modelid, String operid) {
		subscribeDao = (TawCommonMessageSubscribeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageSubscribeManager");
		List list = null;
		list = (ArrayList) subscribeDao.getMessageScByModelidAndOperid(modelid,
				operid);
		return list;
	}

	/**
	 * 根据ID 删除定制的服务
	 * 
	 * @param id
	 */
	public void removeSubscBy(String id) {
		subscribeDao = (TawCommonMessageSubscribeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageSubscribeManager");
		subscribeDao.removeTawCommonMessageSubscribe(id);
	}

	/**
	 * 新增服务订阅
	 * 
	 * @param subsc
	 */
	public void saveSubsc(TawCommonMessageSubscribe subsc) {
		subscribeDao = (TawCommonMessageSubscribeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageSubscribeManager");

		subscribeDao.saveTawCommonMessageSubscribe(subsc);
	}

	/**
	 * 修改定制的服务
	 * 
	 * @param beforesubsc
	 * @param aftersubsc
	 */
	public void saveAndUpdateSubsc(TawCommonMessageSubscribe beforesubsc,
			TawCommonMessageSubscribe aftersubsc) {
		subscribeDao = (TawCommonMessageSubscribeManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageSubscribeManager");
		String id = beforesubsc.getId();
		aftersubsc.setId(id);
		subscribeDao.saveTawCommonMessageSubscribe(aftersubsc);

	}

	public TawCommonMessageSubscribeManager getSubscribeDao() {
		return subscribeDao;
	}

	public void setSubscribeDao(TawCommonMessageSubscribeManager subscribeDao) {
		this.subscribeDao = subscribeDao;
	}

}
