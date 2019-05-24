package com.boco.eoms.commons.message.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.message.model.TawCommonMessageModelType;

public interface TawCommonMessageModelTypeDao extends Dao {

	/**
	 * Retrieves all of the tawCommonMessageModelTypes
	 */
	public List getTawCommonMessageModelTypes(
			TawCommonMessageModelType tawCommonMessageModelType);

	/**
	 * Gets tawCommonMessageModelType's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
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
	public TawCommonMessageModelType getMessageType(String modelid);

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
}
