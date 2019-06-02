package com.boco.eoms.commons.system.priv.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.priv.exception.AuthorizationException;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;

public interface ITawSystemPrivUserAssignManager extends Manager {
	/**
	 * Retrieves all of the tawSystemPrivUserAssigns
	 */
	public List getTawSystemPrivUserAssigns(
			TawSystemPrivUserAssign tawSystemPrivUserAssign);

	/**
	 * Gets tawSystemPrivUserAssign's information based on id.
	 * 
	 * @param id
	 *            the tawSystemPrivUserAssign's id
	 * @return tawSystemPrivUserAssign populated tawSystemPrivUserAssign object
	 */
	public TawSystemPrivUserAssign getTawSystemPrivUserAssign(final String id);

	/**
	 * Saves a tawSystemPrivUserAssign's information
	 * 
	 * @param tawSystemPrivUserAssign
	 *            the object to be saved
	 */
	public void saveTawSystemPrivUserAssign(
			TawSystemPrivUserAssign tawSystemPrivUserAssign);

	/**
	 * Removes a tawSystemPrivUserAssign from the database by id
	 * 
	 * @param id
	 *            the tawSystemPrivUserAssign's id
	 */
	public void removeTawSystemPrivUserAssign(final String id);

	public Map getTawSystemPrivUserAssigns(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivUserAssigns(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 更新某用户对应权限的信息
	 * 
	 * @param userid
	 * @param menuid
	 */
	public void removeUsermenu(final String userid);

	/**
	 * 删除某用户对应的权限
	 * 
	 * @param userid
	 * @param menuid
	 */
	public void removeUserRefmenu(final String userid, final String menuid);

	/**
	 * 根据用户ID获取用户当前的权限
	 * 
	 * @param userid
	 * @return
	 */
	public List getPrivUserMenu(final String userid);

	/**
	 * 更新某用户对应权限的信息
	 * 
	 * @param userid
	 * @param menuid
	 * @param tawSystemPrivUserAssign
	 */
	public void updateUsermenu(final String userid, final String menuid,
			TawSystemPrivUserAssign tawSystemPrivUserAssign);

	/**
	 * 根据用户ID 一级菜单标志获取一级菜单
	 * 
	 * @param userid
	 * @param isone
	 * @return
	 */
	public List getOnelevMenus(final String userid, final String isone);

	/**
	 * 根据用户ID 菜单ID 获取菜单的所有子菜单
	 * 
	 * @param userid
	 * @param menutid
	 * @return
	 */
	public List getSonMenus(final String userid, final String menutid);

	/**
	 * 根据用户ID 菜单ID 获取某菜单的所有父菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getFatherMenus(final String userid, final String menuid);

	/**
	 * 根据用户ID 菜单ID 获取上一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getSuperiorMenus(final String userid, final String menuid);

	/**
	 * 根据用户ID 菜单ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getLowerleveMenus(final String userid, final String menuid);

	/**
	 * IN查询某用户的一些权限
	 * 
	 * @param userid
	 * @param menuids
	 * @return
	 */
	public List getUserMenuInserachs(final String userid, final String menuids);

	/**
	 * 判断某用户是否已经被分配权限
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isExitsUserassign(String userid);

	/**
	 * 删除某用户的权限taw_system_priv_userassign
	 * 
	 * @param userid
	 */
	public void removeUserAssignByUserid(String userid);

	/**
	 * 根据用户ID 菜单ID 获取上一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public TawSystemPrivUserAssign getTawSystemUserAssign(final String userid,
			final String privid);

	/**
	 * 根据用户ID 菜单ID 父权限ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getNextUserPrivMenus(final String menuid, final String userid,
			String parentprivid);

	/**
	 * 删除某用户的权限taw_system_priv_userassign
	 * 
	 * @param userid
	 */
	public void removeUserAssignByUseridAndPrivid(String userid,
			String currentprivid);

	/**
	 * 根据权限ID 删除所有本身以及所有的子权限
	 * 
	 * @param privid
	 */
	public void removeMenuItemAndSonByPrivid(String privid, String menuid);

	/**
	 * 查询某菜单方案被分配给的用户
	 * 
	 * @param menuid
	 */
	public List getUserAssignByMenuid(String menuid);

	/**
	 * 根据USERID和是否一级菜单标志查询当前用户的一级菜单
	 * 
	 * @param userid
	 * @param isonepriv
	 * @return
	 */
	public List getOnePriv(String userid, String isonepriv);

	/**
	 * 查询是否有此url 目的用来判断按钮的url
	 * 
	 * @param url
	 * @return
	 */
	public boolean isHaveUrl(String url, String userid);

	/**
	 * 根据用户ID 菜单ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getLowerleveMenusforJdbc(final String userid,
			final String menuid);

	/**
	 * 判断某菜单项是否已经被分配
	 * 
	 * @param privid
	 * @return
	 */
	public boolean isExitsPrivid(String privid);

	/**
	 * 修改用户已经拥有的菜单项
	 * 
	 * @param privid
	 */
	public void updateUserAssignByPrivid(String privid, String hide);

	/**
	 * 修改用户已经拥有的菜单项 add by jiangcheng
	 * 
	 * @param _objOneOpt
	 */

	public void updateUserAssignByPrivid(TawSystemPrivOperation _objOneOpt);

	/**
	 * 更加菜单ID 和USERID 判断某用户是否拥有某功能项的权限
	 * 
	 * @param userid
	 * @param code
	 * @return
	 */
	public boolean hasCode(String userid, String code);

	/**
	 * 查询是否有此url 目的用来判断按钮的url
	 * 
	 * @param url
	 * @return
	 */
	public boolean isHaveUrl(String url, String userid, String urltype);

	/**
	 * 根据具体的菜单项获取人员的集合
	 * 
	 * @return
	 */
	public List getUserAssignByPrivid(String privId);

	/**
	 * 通过url取拥有权限的人员列表
	 * 
	 * @param url
	 *            唯一权限标识
	 * @return 拥有权限的人员列表
	 */
	public List getAssignsByUrl(String url);

	/**
	 * 根据条件取赋权记录
	 * 
	 * @param parentprivid
	 * @param userId
	 *            用户id
	 * @param hide
	 *            隐藏否
	 * @return 某人拥有父菜单项信合
	 */
	public List getAssigns(String parentprivid, String userId, String hide);

	/**
	 * 对某组织分配菜单方案
	 * 
	 * @param TawSystemPrivAssign
	 *            菜单方案赋权相关信息
	 * 
	 * @throws AuthorizationException
	 *             赋权出错抛出异常
	 */
	public void authorization(TawSystemPrivAssign tawSystemPrivAssign)
			throws AuthorizationException;

	/**
	 * 删除某组织的菜单方案
	 * 
	 * @param tawSystemPrivAssign
	 *            菜单方案赋权相关信息
	 * @throws AuthorizationException
	 *             赋权出错抛出异常
	 */
	public void removeAuthorization(TawSystemPrivAssign tawSystemPrivAssign)
			throws AuthorizationException;

	/**
	 * 根据用户id，菜单方案id，菜单项id判断某用户是否已被赋权
	 * 
	 * @param menuId
	 *            菜单方案id
	 * @param menuItemCode
	 *            菜单项code
	 * @param userId
	 *            用户id
	 * @return 返回是否某菜单方案的菜单被赋权
	 */
	public boolean isExistUserassign(String menuId, String menuItemCode,
			String userId);

	/**
	 * 判断某个组织是否已被赋予某个菜单id
	 * 
	 * @param menuId
	 *            菜单方案
	 * @param orgId
	 *            组织id，人员，部门，角色
	 * @param type
	 *            角色类型 人员，部门，角色
	 * @return
	 */
	public boolean hasAssigned(String menuId, String orgId, String type);
}
