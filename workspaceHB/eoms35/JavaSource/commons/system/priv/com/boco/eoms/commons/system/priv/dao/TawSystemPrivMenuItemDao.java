package com.boco.eoms.commons.system.priv.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;

public interface TawSystemPrivMenuItemDao extends Dao {

	/**
	 * Retrieves all of the tawSystemPrivMenuItems
	 */
	public List getTawSystemPrivMenuItems(
			TawSystemPrivMenuItem tawSystemPrivMenuItem);

	/**
	 * Gets tawSystemPrivMenuItem's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
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

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getTawSystemPrivMenuItems(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivMenuItems(final Integer curPage,
			final Integer pageSize, final String whereStr);

	// /////////////////////////
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
	 * 获取指定菜单方案的一级菜单项（模块）集合
	 * 
	 * @param _strMenuCode
	 *            菜单方案编号
	 * @return List 菜单项对象集合
	 */
	public List getFirLevelMenus(String _strMenuCode);

	/**
	 * 获取特定菜单方案中指定的菜单项下一级包含的菜单项集合
	 * 
	 * @param _strMenuCode
	 *            菜单方案编号
	 * @param _strItemId
	 *            菜单项id
	 * @return List 菜单项对象集合
	 */
	public List getNextLevelMenus(String _strMenuCode, String _strItemId);

	/**
	 * 根据菜单方案编号获取菜单方案对象
	 * 
	 * @param _strMenuCode
	 *            菜单方案编号
	 * @return Object 菜单方案对象
	 */
	public Object getMenuName(String _strMenuCode);

	/**
	 * 获取两个集合的差集
	 * 
	 * @param _objTotals
	 *            List object 全体集合
	 * @param _objExists
	 *            List object 已存在集合
	 * @return List 返回差集（菜单项对象集合）
	 */
	public List getSubMenuItems(List _objTotals, List _objExists);

	/**
	 * 获取指定用户权限分配表中，特定节点下包含的一级子节点集合
	 * 
	 * @param _strUserId
	 *            用户id
	 * @param _strParentItemId
	 *            指定节点
	 * @return List
	 */
	public List getValidSubMenuItems(String _strUserId, String _strParentItemId);

	/**
	 * 菜单项配置过程中，如果选择为中间或叶子节点， 则连同其父节点保存在对应的菜单方案中。
	 * 
	 * @param String
	 *            _strMenuCode
	 * @param String
	 *            _strMenuId
	 */
	public void saveParentMenuItems(String _strMenuCode, String _strMenuId);

	/**
	 * 判断某模块或功能项是否已经配置在某菜单方案上
	 * 
	 * @param String
	 *            _strMenuCode
	 * @param String
	 *            _strMenuId
	 * 
	 * @return boolean
	 */
	public boolean checkIsExists(String _strMenuCode, String _strMenuId);

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
	 * 通过菜单方案id取其所有菜单
	 * 
	 * @param menuId
	 *            菜单方案id
	 * @return 返回菜单方案下的所有菜单
	 */
	public List listTawSystemPrivMenuItems(String menuId);
	
	
	
	

}
