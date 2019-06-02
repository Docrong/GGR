package com.boco.eoms.commons.system.priv.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.priv.exception.InvalidSelItemsException;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;

public interface ITawSystemPrivMenuItemManager extends Manager {
	/**
	 * Retrieves all of the tawSystemPrivMenuItems
	 */
	public List getTawSystemPrivMenuItems(
			TawSystemPrivMenuItem tawSystemPrivMenuItem);

	/**
	 * Gets tawSystemPrivMenuItem's information based on id.
	 * 
	 * @param id
	 *            the tawSystemPrivMenuItem's id
	 * @return tawSystemPrivMenuItem populated tawSystemPrivMenuItem object
	 */
	public TawSystemPrivMenuItem getTawSystemPrivMenuItem(final String id);

	/**
	 * Saves a tawSystemPrivMenuItem's information
	 * 
	 * @param tawSystemPrivMenuItem
	 *            the object to be saved
	 */
	public void saveTawSystemPrivMenuItem(
			TawSystemPrivMenuItem tawSystemPrivMenuItem);

	/**
	 * Removes a tawSystemPrivMenuItem from the database by id
	 * 
	 * @param id
	 *            the tawSystemPrivMenuItem's id
	 */
	public void removeTawSystemPrivMenuItem(final String id);

	public Map getTawSystemPrivMenuItems(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivMenuItems(final Integer curPage,
			final Integer pageSize, final String whereStr);

	// /////////////////////////////////
	// // 自定义外部接口
	/**
	 * 获取全体菜单方案的集合
	 * 
	 * @return List 菜单方案对象集合
	 */
	public List getAllMenus();

	/**
	 * 获取指定用户创建的菜单方案集合
	 * 
	 * @param _strOwnerId
	 *            创建者的id
	 * @return List 菜单方案对象集合
	 */
	public List getSelfMenus(String _strOwnerId);

	/**
	 * 根据菜单方案ID 查询指定菜单项
	 * 
	 * @param privid
	 *            菜单方案编码
	 * @return List 菜单项VO对象集合
	 */
	public List getSpecMenuItems(String privid);

	/**
	 * 获取指定菜单方案的一级菜单项（模块）集合
	 * 
	 * @param privid
	 *            菜单方案编号
	 * @return List 菜单项对象集合
	 */
	public List getFirLevelMenus(String privid);

	/**
	 * 获取特定菜单方案中指定的菜单项下一级包含的菜单项集合
	 * 
	 * @param privid
	 *            菜单方案编号
	 * @param _strItemId
	 *            菜单项id
	 * @return List 菜单项对象集合
	 */
	public List getNextLevelMenus(String privid, String code);

	/**
	 * 根据菜单方案编号获取菜单方案对象
	 * 
	 * @param privid
	 *            菜单方案编号
	 * @return Object 菜单方案对象
	 */
	public Object getMenuName(String privid);

	/**
	 * 获取两个集合的差集
	 * 
	 * @param _objTotals
	 *            List object 全体集合
	 * @param _objExists
	 *            List object 已存在集合
	 * @param List
	 *            返回差集（菜单项对象集合）
	 */
	public List getSubMenuItems(List _objTotals, List _objExists)
			throws InvalidSelItemsException;

	/**
	 * 获取指定用户权限分配表中，特定节点下包含的一级子节点集合
	 * 
	 * @param _strUserId
	 *            用户id
	 * @param __strParentcode
	 *            指定节点
	 * @return List
	 */
	public List getValidSubMenuItems(String _strUserId, String _strParentcode);

	/**
	 * 菜单项配置过程中，如果选择为中间或叶子节点， 则连同其父节点保存在对应的菜单方案中。
	 * 
	 * @param String
	 *            privid
	 * @param String
	 *            code
	 */
	public void saveParentMenuItems(String privid, String code);

	/**
	 * 判断某模块或功能项是否已经配置在某菜单方案上
	 * 
	 * @param String
	 *            privid
	 * @param String
	 *            _code
	 * 
	 * @return boolean
	 */
	public boolean checkIsExists(String privid, String code);

	/**
	 * 判断某菜单方案下面某节点是否有子节点
	 * 
	 * @param String
	 *            privid
	 * @param String
	 *            _strItemId
	 * 
	 * @return boolean
	 */
	public boolean hasChild(String privid, String code);

	/**
	 * 根据PRIVID和CODE查询某菜单记录
	 * 
	 * @param privid
	 * @param code
	 * @return
	 */
	public TawSystemPrivMenuItem getMenuItemByPrividAndCode(String privid,
			String code);

	/**
	 * 保存菜单项并更新父的ISLEAF为0
	 * 
	 * @param tawSystemPrivMenuItem
	 */
	public void saveMenuItemAndParentMenu(
			TawSystemPrivMenuItem tawSystemPrivMenuItem);

	/**
	 * 根据PRIVID和CODE删除CODE的属于子节点
	 * 
	 * @param privid
	 * @param code
	 */
	public void removeMenuItemAndSon(String privid, String code);

	/**
	 * 通过菜单方案id取其所有菜单
	 * 
	 * @param menuId
	 *            菜单方案id
	 * @return 返回菜单方案下的所有菜单
	 */
	public List listTawSystemPrivMenuItems(String menuId);

}
