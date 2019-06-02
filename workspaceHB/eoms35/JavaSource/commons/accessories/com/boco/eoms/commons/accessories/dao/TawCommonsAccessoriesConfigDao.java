/*
 * Created on 2007-9-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.accessories.dao;

import java.util.List;
import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;

/**
 * @author IBM_USER
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TawCommonsAccessoriesConfigDao extends Dao {
	/**
	 * Retrieves all of the tawCommonsAccessoriesConfigs
	 */
	public List getTawCommonsAccessoriesConfigs();

	/**
	 * Gets tawCommonsAccessoriesConfig's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawCommonsAccessoriesConfig's id
	 * @return tawCommonsAccessoriesConfig populated tawCommonsAccessoriesConfig object
	 */
	public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(final Integer appId);

	/**
	 * Saves a tawCommonsAccessoriesConfig's information
	 * 
	 * @param tawCommonsAccessoriesConfig
	 *            the object to be saved
	 */
	public void saveTawCommonsAccessoriesConfig(TawCommonsAccessoriesConfig tawCommonsAccessoriesConfig);

	/**
	 * Removes a tawCommonsAccessoriesConfig from the database by id
	 * 
	 * @param id
	 *            the tawCommonsAccessoriesConfig's id
	 */
	public void removeTawCommonsAccessoriesConfig(final Integer id); 
	
	public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(final String id);
	
	public TawCommonsAccessoriesConfig getAccessoriesConfigByAppcode(final String appCode);
}
