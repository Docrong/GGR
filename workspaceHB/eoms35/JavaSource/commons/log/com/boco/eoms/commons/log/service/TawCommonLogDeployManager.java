package com.boco.eoms.commons.log.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.log.model.TawCommonLogDeploy;

public interface TawCommonLogDeployManager extends Manager {
	/**
	 * Retrieves all of the tawCommonLogDeploys
	 */
	public List getTawCommonLogDeploys(TawCommonLogDeploy tawCommonLogDeploy);

	/**
	 * Gets tawCommonLogDeploy's information based on id.
	 * 
	 * @param id
	 *            the tawCommonLogDeploy's id
	 * @return tawCommonLogDeploy populated tawCommonLogDeploy object
	 */
	public TawCommonLogDeploy getTawCommonLogDeploy(final String id);

	/**
	 * Saves a tawCommonLogDeploy's information
	 * 
	 * @param tawCommonLogDeploy
	 *            the object to be saved
	 */
	public void saveTawCommonLogDeploy(TawCommonLogDeploy tawCommonLogDeploy);

	/**
	 * Removes a tawCommonLogDeploy from the database by id
	 * 
	 * @param id
	 *            the tawCommonLogDeploy's id
	 */
	public void removeTawCommonLogDeploy(final String id);

	/**
	 * 
	 * @param operlevelID
	 * @return
	 */
	public TawCommonLogDeploy getDeployByOperID(String operlevelID);
	
	/**
	 * 查询某用户的配置
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getDeoloyByuseridandmodelid(String userid,String modelid);
	
	/**
	 * 查询某用户的配置
	 * @param userid
	 * @param modelid
	 * @return
	 */
	public List getDeoloyByuseridormodelid(String userid,String modelid);
}
