package com.boco.eoms.commons.message.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;

public interface TawCommonMessageOpertypeManager extends Manager {
	/**
	 * Retrieves all of the tawCommonMessageOpertypes
	 */
	public List getTawCommonMessageOpertypes(
			TawCommonMessageOpertype tawCommonMessageOpertype);

	/**
	 * Gets tawCommonMessageOpertype's information based on id.
	 * 
	 * @param id
	 *            the tawCommonMessageOpertype's id
	 * @return tawCommonMessageOpertype populated tawCommonMessageOpertype
	 *         object
	 */
	public TawCommonMessageOpertype getTawCommonMessageOpertype(final String id);

	/**
	 * Saves a tawCommonMessageOpertype's information
	 * 
	 * @param tawCommonMessageOpertype
	 *            the object to be saved
	 */
	public void saveTawCommonMessageOpertype(
			TawCommonMessageOpertype tawCommonMessageOpertype);

	/**
	 * Removes a tawCommonMessageOpertype from the database by id
	 * 
	 * @param id
	 *            the tawCommonMessageOpertype's id
	 */
	public void removeTawCommonMessageOpertype(final String id);

	/**
	 * 根据模块ID 业务ID获取 消息
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageOpertype getOpertype(String modelid, String operid);

	/**
	 * 根据模块ID 业务ID 删除消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void removeOpertype(String modelid, String operid);

	/**
	 * 根据模块ID 业务ID 修改消息服务
	 * 
	 * @param modelid
	 * @param operid
	 */
	public void saveAndUpdateopertype(String modelid, String operid,
			String newmodelid, String newoperid, String modelname,
			String opername, String userid, String operremark,
			String operrefmodelid);
}
