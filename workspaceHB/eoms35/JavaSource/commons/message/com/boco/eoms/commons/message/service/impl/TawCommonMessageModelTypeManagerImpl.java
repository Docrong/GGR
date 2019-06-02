package com.boco.eoms.commons.message.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.message.model.TawCommonMessageModelType;
import com.boco.eoms.commons.message.dao.TawCommonMessageModelTypeDao;
import com.boco.eoms.commons.message.service.TawCommonMessageModelTypeManager;

public class TawCommonMessageModelTypeManagerImpl extends BaseManager implements
		TawCommonMessageModelTypeManager {
	private TawCommonMessageModelTypeDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonMessageModelTypeDao(TawCommonMessageModelTypeDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageModelTypeManager#getTawCommonMessageModelTypes(com.boco.eoms.commons.message.model.TawCommonMessageModelType)
	 */
	public List getTawCommonMessageModelTypes(
			final TawCommonMessageModelType tawCommonMessageModelType) {
		return dao.getTawCommonMessageModelTypes(tawCommonMessageModelType);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageModelTypeManager#getTawCommonMessageModelType(String
	 *      id)
	 */
	public TawCommonMessageModelType getTawCommonMessageModelType(
			final String id) {
		return dao.getTawCommonMessageModelType(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageModelTypeManager#saveTawCommonMessageModelType(TawCommonMessageModelType
	 *      tawCommonMessageModelType)
	 */
	public void saveTawCommonMessageModelType(
			TawCommonMessageModelType tawCommonMessageModelType) {
		dao.saveTawCommonMessageModelType(tawCommonMessageModelType);
	}

	/**
	 * @see com.boco.eoms.commons.message.service.TawCommonMessageModelTypeManager#removeTawCommonMessageModelType(String
	 *      id)
	 */
	public void removeTawCommonMessageModelType(final String id) {
		dao.removeTawCommonMessageModelType(new String(id));
	}

	/**
	 * 添加服务类型
	 * 
	 * @param userid
	 * @param modelid
	 * @param modelname
	 * @param remark
	 */
	public void saveMessageType(String userid, String modelid,
			String modelname, String remark) {
		TawCommonMessageModelType modeltype = new TawCommonMessageModelType();
		modeltype.setModelid(modelid);
		modeltype.setModelname(modelname);
		modeltype.setModelremark(remark);
		modeltype.setUserid(userid);
		dao.saveTawCommonMessageModelType(modeltype);
	}

	/**
	 * 删除服务类型
	 * 
	 * @param modelid
	 */
	public void removeMessageType(String modelid) {
		dao.removeMessageType(modelid);
	}

	/**
	 * 根据modelid修改记录
	 * 
	 * @param modelid
	 */
	public void saveAndUpdatemodeltype(String modelid, String udmodelid,
			String modeluserid, String modelname, String modelremark) {
		dao.saveAndUpdatemodeltype(modelid, udmodelid, modeluserid, modelname,
				modelremark);
	}

	/**
	 * 根据modelid 取得服务模块类型
	 * 
	 * @param modelid
	 * @return
	 */
	public TawCommonMessageModelType getModeltype(String modelid) {
		TawCommonMessageModelType modeltype = null;

		modeltype = dao.getMessageType(modelid);

		return modeltype;
	}
}
