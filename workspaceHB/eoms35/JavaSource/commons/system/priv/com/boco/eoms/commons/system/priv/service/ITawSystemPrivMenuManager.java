package com.boco.eoms.commons.system.priv.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;

public interface ITawSystemPrivMenuManager extends Manager {
	/**
	 * Retrieves all of the tawSystemPrivMenus
	 */
	public List getTawSystemPrivMenus(TawSystemPrivMenu tawSystemPrivMenu);

	/**
	 * Gets tawSystemPrivMenu's information based on id.
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
	 * 查询用户所拥有的菜单方案
	 * 
	 * @param userid
	 * @return
	 */
	public List getPrivMenubyUserid(String userid);

	/**
	 * Removes a tawSystemPrivMenu from the database by id
	 * 
	 * @param id
	 *            the tawSystemPrivMenu's id
	 */
	public void removeTawSystemPrivMenu(final String code);

	public Map getTawSystemPrivMenus(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivMenus(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 验证菜单名称是否已经存在
	 * 
	 * @param menuname
	 * @return
	 */
	public boolean isExits(String menuname);

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
