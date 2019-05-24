package com.boco.eoms.commons.system.role.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.role.model.TawSystemRoleImport;

public interface ITawSystemRoleImportDao extends Dao {

	/**
	 * Retrieves all of the tawSystemRoleImports
	 */
	public List getTawSystemRoleImports(TawSystemRoleImport tawSystemRoleImport);

	/**
	 * Gets tawSystemRoleImport's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawSystemRoleImport's id
	 * @return tawSystemRoleImport populated tawSystemRoleImport object
	 */
	public TawSystemRoleImport getTawSystemRoleImport(final String id);

	/**
	 * Saves a tawSystemRoleImport's information
	 * 
	 * @param tawSystemRoleImport
	 *            the object to be saved
	 */
	public void saveTawSystemRoleImport(TawSystemRoleImport tawSystemRoleImport);

	/**
	 * Removes a tawSystemRoleImport from the database by id
	 * 
	 * @param id
	 *            the tawSystemRoleImport's id
	 */
	public void removeTawSystemRoleImport(final String id);

	/**
	 * 用于分页显示
	 * 
	 * @param curPage
	 *            the current page number
	 * @param pageSize
	 *            the size number per page
	 */
	public Map getTawSystemRoleImports(final Integer curPage,
			final Integer pageSize);

	/**
	 * 用于分页显示
	 * 
	 * @param curPage
	 *            the current page number
	 * @param pageSize
	 *            the size number per page
	 * @param whereStr
	 *            the "where.." conditional statement,must start with "where",
	 *            can be blank
	 */
	public Map getTawSystemRoleImports(final Integer curPage,
			final Integer pageSize, final String whereStr);
	/**
	 * 根据父节点查询下级子节点
	 * 
	 * @param parentId
	 *            子节点中parentId字段即父节点id
	 */
	public ArrayList getChildList(String parentId);

}
