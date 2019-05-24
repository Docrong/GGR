package com.boco.eoms.commons.message.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.message.model.TawCommonMessageModelType;

public interface TawCommonMessageModelTypeManager extends Manager {
	/**
	 * Retrieves all of the tawCommonMessageModelTypes
	 */
	public List getTawCommonMessageModelTypes(
			TawCommonMessageModelType tawCommonMessageModelType);

	/**
	 * Gets tawCommonMessageModelType's information based on id.
	 * 
	 * @param id
	 *            the tawCommonMessageModelType's id
	 * @return tawCommonMessageModelType populated tawCommonMessageModelType
	 *         object
	 */
	public TawCommonMessageModelType getTawCommonMessageModelType(
			final String id);

	/**
	 * Saves a tawCommonMessageModelType's information
	 * 
	 * @param tawCommonMessageModelType
	 *            the object to be saved
	 */
	public void saveTawCommonMessageModelType(
			TawCommonMessageModelType tawCommonMessageModelType);

	/**
	 * Removes a tawCommonMessageModelType from the database by id
	 * 
	 * @param id
	 *            the tawCommonMessageModelType's id
	 */
	public void removeTawCommonMessageModelType(final String id);

	/**
	 * 添加服务类型
	 * 
	 * @param userid
	 * @param modelid
	 * @param modelname
	 * @param remark
	 */
	public void saveMessageType(String userid, String modelid,
			String modelname, String remark);

	/**
	 * 删除服务类型
	 * 
	 * @param modelid
	 */
	public void removeMessageType(String modelid);

	/**
	 * 根据modelid修改记录
	 * 
	 * @param modelid
	 */
	public void saveAndUpdatemodeltype(String modelid, String udmodelid,
			String modeluserid, String modelname, String modelremark);

	/**
	 * 根据modelid 取得服务模块类型
	 * 
	 * @param modelid
	 * @return
	 */
	public TawCommonMessageModelType getModeltype(String modelid);
}
