package com.boco.eoms.commons.system.priv.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;

public interface TawSystemPrivMenuDao extends Dao {

	/**
	 * Retrieves all of the tawSystemPrivMenus
	 */
	public List getTawSystemPrivMenus(TawSystemPrivMenu tawSystemPrivMenu);

	/**
	 * Gets tawSystemPrivMenu's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawSystemPrivMenu's id
	 * @return tawSystemPrivMenu populated tawSystemPrivMenu object
	 */
	public TawSystemPrivMenu getTawSystemPrivMenu(final String id);

	/**
	 * Saves a tawSystemPrivMenu's information
	 * 
	 * @param tawSystemPrivMenu
	 *            the object to be saved
	 */
	public void saveTawSystemPrivMenu(TawSystemPrivMenu tawSystemPrivMenu);

	/**
	 * Removes a tawSystemPrivMenu from the database by id
	 * 
	 * @param id
	 *            the tawSystemPrivMenu's id
	 */
	public void removeTawSystemPrivMenu(final String id);

	/**
	 * 用于分页显示 curPage 当前页数 pageSize 每页显示数
	 */
	public Map getTawSystemPrivMenus(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivMenus(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 获取合并后的菜单方案
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @return 获取合并后的菜单单方案
	 */
	public List listMenu(String userId, String deptId, List roleIds);
	/**
	 * 获取合并后的菜单方案
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @return 获取合并后的菜单单方案
	 */
	public List listMenu(String userId, String deptId, List roleIds,String privid);

	/**
	 * 取某角色、人员、部门菜单方案
	 * 
	 * @param orgId
	 *            组织结构id，部门id,角色id，用户id
	 * @param orgType
	 *            StaticVariable.PRIV_ASSIGNTYPE_ROLE,
	 *            StaticVariable.PRIV_ASSIGNTYPE_DEPT,
	 *            StaticVariable.PRIV_ASSIGNTYPE_USER
	 * @return 某角色、人员、部门菜单方案集合
	 */
	public List listMenu(String orgId, String orgType);

}
