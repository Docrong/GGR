package com.boco.eoms.commons.message.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;

public interface TawCommonMessageAddServiceManager extends Manager {
	/**
	 * Retrieves all of the tawCommonMessageAddServices
	 */
	public List getTawCommonMessageAddServices(
			TawCommonMessageAddService tawCommonMessageAddService);

	/**
	 * Gets tawCommonMessageAddService's information based on id.
	 * 
	 * @param id
	 *            the tawCommonMessageAddService's id
	 * @return tawCommonMessageAddService populated tawCommonMessageAddService
	 *         object
	 */
	public TawCommonMessageAddService getTawCommonMessageAddService(
			final String id);

	/**
	 * Saves a tawCommonMessageAddService's information
	 * 
	 * @param tawCommonMessageAddService
	 *            the object to be saved
	 */
	public void saveTawCommonMessageAddService(
			TawCommonMessageAddService tawCommonMessageAddService);

	/**
	 * Removes a tawCommonMessageAddService from the database by id
	 * 
	 * @param id
	 *            the tawCommonMessageAddService's id
	 */
	public void removeTawCommonMessageAddService(final String id);

	/**
	 * 根据userid得到消息服务
	 * 
	 * @param userid
	 * @return
	 */
	public List getMesageServicebyuserids(String userid);

	/**
	 * 得到消息服务 根据MODELID 和OPERID
	 * 
	 * @param modelid
	 * @param operid
	 * @return
	 */
	public TawCommonMessageAddService getMessageService(String modelid,
			String operid);

}
