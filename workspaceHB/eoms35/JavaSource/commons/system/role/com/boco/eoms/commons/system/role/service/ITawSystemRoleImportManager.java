package com.boco.eoms.commons.system.role.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.system.role.model.TawSystemRoleImport;

public interface ITawSystemRoleImportManager extends Manager {
	/**
	 * Retrieves all of the tawSystemRoleImports
	 */
	public List getTawSystemRoleImports(TawSystemRoleImport tawSystemRoleImport);

	/**
	 * Gets tawSystemRoleImport's information based on id.
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
	public List getChildList(String parentId);

	/**
	 * 根据父节点id查询下一级节点，返回子节点的JSON数据，一般用于树图的展现
	 * 
	 * @param parentId
	 */
	public JSONArray xGetChildNodes(String parentId);

	/**
	 * 角色excel 与pojo的mapping
	 * 
	 * @param excelPath
	 *            excel的路径
	 * @return pojo结果
	 * @throws FMException
	 *             导入出错
	 */
	public Map mappingRoleExcel(String excelPath) throws FMException;

	/**
	 * 导入角色
	 * 
	 * @param excel
	 *            mapping的角色列表 (tawSystemRoleImportExcel 与excel mapping的角色对象
	 * @param tawSystemRoleImport
	 *            角色导入版本控制
	 */
	public void importRole(List list, TawSystemRoleImport tawSystemRoleImport);

	/**
	 * 恢复致 version版本
	 * 
	 * @param version
	 *            版本号
	 */
	public void restoreRole(String version);
	
	//2009-5-13 修改导入方法，当子角色名已经存在，不导入给出提示
	public String importRole1(List list, TawSystemRoleImport tawSystemRoleImport);
}
