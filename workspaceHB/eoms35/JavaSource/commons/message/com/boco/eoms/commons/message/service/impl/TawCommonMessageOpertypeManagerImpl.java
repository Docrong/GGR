package com.boco.eoms.commons.message.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;
import com.boco.eoms.commons.message.dao.TawCommonMessageOpertypeDao;
import com.boco.eoms.commons.message.service.TawCommonMessageOpertypeManager;

public class TawCommonMessageOpertypeManagerImpl extends BaseManager implements
		TawCommonMessageOpertypeManager {
	private TawCommonMessageOpertypeDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonMessageOpertypeDao(TawCommonMessageOpertypeDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageOpertypeManager#getTawCommonMessageOpertypes(com.boco.eoms.commons.message.model.TawCommonMessageOpertype)
	 */
	public List getTawCommonMessageOpertypes(
			final TawCommonMessageOpertype tawCommonMessageOpertype) {
		return dao.getTawCommonMessageOpertypes(tawCommonMessageOpertype);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageOpertypeManager#getTawCommonMessageOpertype(String
	 *      id)
	 */
	public TawCommonMessageOpertype getTawCommonMessageOpertype(final String id) {
		return dao.getTawCommonMessageOpertype(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageOpertypeManager#saveTawCommonMessageOpertype(TawCommonMessageOpertype
	 *      tawCommonMessageOpertype)
	 */
	public void saveTawCommonMessageOpertype(
			TawCommonMessageOpertype tawCommonMessageOpertype) {
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageOpertypeManager#removeTawCommonMessageOpertype(String
	 *      id)
	 */
	public void removeTawCommonMessageOpertype(final String id) {
		dao.removeTawCommonMessageOpertype(new String(id));
	}

	/**
	 * 根据模块ID 业务ID获取 消息
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageOpertype getOpertype(String modelid, String operid) {
		TawCommonMessageOpertype opertype = null;
		opertype = dao.getOpertype(modelid, operid);
		return opertype;
	}

	/**
	 * 根据模块ID 业务ID 删除消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void removeOpertype(String modelid, String operid) {
		dao.removeOpertype(modelid, operid);
	}

	/**
	 * 根据模块ID 业务ID 修改消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void saveAndUpdateopertype(String modelid, String operid,
			String newmodelid, String newoperid, String modelname,
			String opername, String userid, String operremark,
			String operrefmodelid) {
		dao.saveAndUpdateopertype(modelid, operid, newmodelid, newoperid,
				modelname, opername, userid, operremark, operrefmodelid);
	}
}
