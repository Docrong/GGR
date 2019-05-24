package com.boco.eoms.commons.message.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;

public interface TawCommonMessageSubscribeDao extends Dao {

	/**
	 * Retrieves all of the tawCommonMessageSubscribes
	 */
	public List getTawCommonMessageSubscribes(
			TawCommonMessageSubscribe tawCommonMessageSubscribe);

	/**
	 * Gets tawCommonMessageSubscribe's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawCommonMessageSubscribe's id
	 * @return tawCommonMessageSubscribe populated tawCommonMessageSubscribe
	 *         object
	 */
	public TawCommonMessageSubscribe getTawCommonMessageSubscribe(
			final String id);

	/**
	 * Saves a tawCommonMessageSubscribe's information
	 * 
	 * @param tawCommonMessageSubscribe
	 *            the object to be saved
	 */
	public void saveTawCommonMessageSubscribe(
			TawCommonMessageSubscribe tawCommonMessageSubscribe);

	/**
	 * Removes a tawCommonMessageSubscribe from the database by id
	 * 
	 * @param id
	 *            the tawCommonMessageSubscribe's id
	 */
	public void removeTawCommonMessageSubscribe(final String id);

	/**
	 * 根据userid查找所定制的服务
	 * 
	 * @param userid
	 * @return
	 */
	public List getMessageScByuserid(String userid);

	/**
	 * 根据modelid operid 查找消息服务
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public List getMessageScByModelidAndOperid(String serviceid);
}
