package com.boco.eoms.extra.supplierkpi.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;

public interface ITawSupplierkpiInstanceManager extends Manager {
	/**
	 * Retrieves all of the tawSupplierkpiInstances
	 */
	public List getTawSupplierkpiInstances(
			TawSupplierkpiInstance tawSupplierkpiInstance);

	/**
	 * Gets tawSupplierkpiInstance's information based on id.
	 * 
	 * @param id
	 *            the tawSupplierkpiInstance's id
	 * @return tawSupplierkpiInstance populated tawSupplierkpiInstance object
	 */
	public TawSupplierkpiInstance getTawSupplierkpiInstance(final String id);

	/**
	 * Saves a tawSupplierkpiInstance's information
	 * 
	 * @param tawSupplierkpiInstance
	 *            the object to be saved
	 */
	public void saveTawSupplierkpiInstance(
			TawSupplierkpiInstance tawSupplierkpiInstance);

	/**
	 * Removes a tawSupplierkpiInstance from the database by id
	 * 
	 * @param id
	 *            the tawSupplierkpiInstance's id
	 */
	public void removeTawSupplierkpiInstance(final String id);

	public Map getTawSupplierkpiInstances(final int curPage, final int pageSize);

	public Map getTawSupplierkpiInstances(final int curPage,
			final int pageSize, final String whereStr, final String countStr);

	public Map getTawSupplierkpiInstances(final String fillStartTime,
			final String fillEndTime, final int curPage, final int pageSize,
			final String whereStr, final String countStr);

	public List getTawSupplierkpiItems(final String queryStr);

	public String getTawSupplierNameById(final String id);

	public void makeTawSupplierkpiInstance(
			TawSupplierkpiInstance tawSupplierkpiInstance);

	public List getTawSupplierkpiInstances(final String whereStr);
	
	public List getTawSupplierkpiInstances(final String whereStr, final String reportTime);

	public List getManufacturerName(final String whereStr);

	public List getManufacturerName();

	public List getItemType(final String whereStr);

	// 该方法取到的指标是售后的并且是非主观的，使用时请注意
	public List getTawSupplierkpiItemNames(final String specialType);

	public String getUserNamebySubRoleidUserstatus(String roleid);
}
