package com.boco.eoms.commons.system.priv.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;

public interface ITawSystemPrivAssignManager extends Manager {
	/**
	 * Retrieves all of the tawSystemPrivAssigns
	 */
	public List getTawSystemPrivAssigns(TawSystemPrivAssign tawSystemPrivAssign);

	/**
	 * Gets tawSystemPrivAssign's information based on id.
	 * 
	 * @param id
	 *            the tawSystemPrivAssign's id
	 * @return tawSystemPrivAssign populated tawSystemPrivAssign object
	 */
	public TawSystemPrivAssign getTawSystemPrivAssign(final String id);

	/**
	 * Saves a tawSystemPrivAssign's information
	 * 
	 * @param tawSystemPrivAssign
	 *            the object to be saved
	 */
	public void saveTawSystemPrivAssign(TawSystemPrivAssign tawSystemPrivAssign);

	/**
	 * Removes a tawSystemPrivAssign from the database by id
	 * 
	 * @param id
	 *            the tawSystemPrivAssign's id
	 */
	public void removeTawSystemPrivAssign(final String id);

	public Map getTawSystemPrivAssigns(final Integer curPage,
			final Integer pageSize);

	public Map getTawSystemPrivAssigns(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 查询某用户分配的权限信息
	 * 
	 * @param operuserid
	 * @return
	 */
	public List getUserCreatePrivs(final String operuserid);

	/**
	 * 查询OBJECT 对应的权限
	 * 
	 * @param objectid
	 * @return
	 */
	public List getObjectPriv(final String objectid);

	/**
	 * 查询某权限方案被分配的情况
	 * 
	 * @return
	 */
	public List getPrivassigninfos(final String privid);

	/**
	 * 删除某个对象的权限
	 * 
	 * @param objectid
	 */
	public void removePrivassign(final String objectid);

	/**
	 * 更新某个对象的权限信息
	 * 
	 * @param objectid
	 */
	public void updateObjectPriv(final String objectid,
			TawSystemPrivAssign assign);

	/**
	 * 判断某GROUP是否已经被分配权限
	 * 
	 * @param userid
	 * @return
	 */
	public boolean hasAssign(String objectid);

	/**
	 * 判断某菜单方案是否被分配过
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isMenuHasAssign(String menuid);

	/**
	 * 通过url取赋权列表
	 * 
	 * @param url
	 *            唯一标识
	 * @return 赋权列表
	 */
	public List getAssignsByUrl(String url);

	/**
	 * 判断组织是否拥有菜单方案
	 * 
	 * @param orgId
	 *            组织id
	 * @param type
	 *            组织类型
	 * @param menuId
	 *            菜单方案id
	 * @return 返回组织是否拥有菜单方案
	 */
	public boolean hasAssign(String orgId, String type, String menuId);

	/**
	 * 获取最上层菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @return 获取合并后的最上层菜单
	 */
	public List listModuleMenu(String userId, String deptId, List roleIds);

	/**
	 * 通过url取拥有赋权
	 * 
	 * @param url
	 *            url权限
	 * @return 取拥有赋权信息
	 */
	public List listAssign(String url);

}
