package com.boco.eoms.commons.demo.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.demo.model.AppfuseSample;

public interface AppfuseSampleDao extends Dao {

	/**
	 * Retrieves all of the appfuseSamples
	 */
	public List getAppfuseSamples(AppfuseSample appfuseSample);

	/**
	 * Gets appfuseSample's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the appfuseSample's id
	 * @return appfuseSample populated appfuseSample object
	 */
	public AppfuseSample getAppfuseSample(final String id);

	/**
	 * Saves a appfuseSample's information
	 * 
	 * @param appfuseSample
	 *            the object to be saved
	 */
	public void saveAppfuseSample(AppfuseSample appfuseSample);

	/**
	 * Removes a appfuseSample from the database by id
	 * 
	 * @param id
	 *            the appfuseSample's id
	 */
	public void removeAppfuseSample(final String id);
}
