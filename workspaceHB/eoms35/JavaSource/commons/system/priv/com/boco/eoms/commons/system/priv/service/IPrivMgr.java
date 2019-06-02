package com.boco.eoms.commons.system.priv.service;

import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;

/**
 * <p>
 * Title:权限管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 20, 2008 5:29:57 PM
 * </p>
 * 
 * @author 龚玉峰，曲静波
 * @version 0.1
 * 
 */
public interface IPrivMgr {

	/**
	 * 判断是否为管理员
	 * 
	 * @param userId
	 *            用户id
	 * @return 是否为管理员
	 * @since 0.1
	 */
	public boolean isAdmin(String userId);

	/**
	 * 保存组织的权限 StaticVariable.PRIV_ASSIGNTYPE_ROLE,
	 * StaticVariable.PRIV_ASSIGNTYPE_DEPT, StaticVariable.PRIV_ASSIGNTYPE_USER
	 * 
	 * @param 权限实例
	 *            TawSystemPrivAssign
	 * 
	 * @return 保存成功
	 * @since 0.1
	 */
	public boolean savePrivAssign(TawSystemPrivAssign tawSystemPrivAssign);

	/**
	 * 根据id得到权限的实例
	 * 
	 * @param id
	 *            权限id
	 * 
	 * @return 实例
	 * @since 0.1
	 */
	public TawSystemPrivAssign getTawSystemPrivAssign(String id);

	/**
	 * 根据id删除权限
	 * 
	 * @param id
	 *            权限id
	 * 
	 * @return 成功
	 * @since 0.1
	 */
	public void remove(String id);

	/**
	 * 获取operationId的下层菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @param
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @prama operationId 父菜单id
	 * @return 获取合并后的最上层菜单
	 * @since 0.1
	 */
	public List listOpertionwap(String userId, String deptId, List roleIds,
			String type, String operationId);
	
	/**
	 * 获取operationId的下层菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @param
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @prama operationId 父菜单id
	 * @return 获取合并后的最上层菜单
	 * @since 0.1
	 */
	public List listOpertion(String userId, String deptId, List roleIds,
			String type, String operationId);
	
	
	/**
	 * 获取operationId的所有下层菜单。如：流程管理->任务工单->派发工单，取流程管理、任务工单、派发工单
	 * 
	 * @param userId
	 *            用户id
	 * @prama operationId 父菜单id
	 * @param
	 *        查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @return 所有菜单项
	 * @since 0.1
	 */
	public List listOperationAll(String userId, String deptId, List roleIds,
			String type, String operationId);
	
	/**
	 * 获取operationId的下层菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色id（可能多个）
	 * @param
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @prama operationId 父菜单id 
	 * @return 获取合并后的最上层菜单
	 * @since 0.2
	 * add by gongyufeng 添加wap登陆菜单显示
	 */
	public List listOpertion(String userId, String deptId, List roleIds,
			String type, String operationId,String loginNature);


	/**
	 * 获取operationId的下层菜单，供登陆时显示用。如：流程管理->任务工单->派发工单，则取流程管理
	 * 
	 * @param userId
	 *            用户id
	 * @prama operationId 父菜单id
	 * @param
	 *        查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @return 获取合并后的最上层菜单
	 * @since 0.1
	 */
	public List listOpertion(String userId, String type, String opertionId);

	/**
	 * 取某角色、人员、部门菜单方案
	 * 
	 * @param orgId
	 *            组织结构id，部门id,角色id，用户id,若为角色id，请使用逗号分隔多个角色id，如：1234,2345,3456
	 * @param orgType
	 *            StaticVariable.PRIV_ASSIGNTYPE_ROLE,
	 *            StaticVariable.PRIV_ASSIGNTYPE_DEPT,
	 *            StaticVariable.PRIV_ASSIGNTYPE_USER
	 * @return 某角色、人员、部门菜单方案集合
	 * @since 0.1
	 */
	public List listMenu(String orgId, String orgType);

	/**
	 * 取某人员的所有菜单方案
	 * 
	 * @param userId
	 *            用户id
	 * @return 取某人员的所有菜单方案
	 * @since 0.1
	 */
	public List listMenu(String userId);

	/**
	 * 取某人员的所有菜单方案
	 * 
	 * @param userId
	 *            用户id
	 * @return 取某人员的所有菜单方案
	 * @since 0.1
	 */
	public boolean hasAssigned(String userId, String priv);

	/**
	 * 判断某组织结构是否已分配菜单方案
	 * 
	 * @param orgId
	 *            组织结构id，部门id,角色id，用户id；注：角色id以逗号相隔
	 * @param orgType
	 *            StaticVariable.PRIV_ASSIGNTYPE_ROLE,
	 *            StaticVariable.PRIV_ASSIGNTYPE_DEPT,
	 *            StaticVariable.PRIV_ASSIGNTYPE_USER
	 * @param menuId
	 *            菜单方案id
	 * @return 是否已分配菜单方案
	 * @since 0.1
	 */
	public boolean hasAssigned(String orgId, String orgType, String menuId);

	public JSONObject getJSONObjectPriv(String type, String objectid);

	/**
	 * 判断某角色列表是否拥有某采单方案
	 * 
	 * @param roleIds
	 *            角色列表
	 * @param menuId
	 *            菜单id
	 * @return 某角色是否拥有菜单方案
	 * @since 0.1
	 */
	public boolean hasAssigned(List roleIds, String menuId);

	/**
	 * 将菜单转为json数据，供登陆页面使用
	 * 
	 * @param operations
	 *            菜单列表
	 * @return 菜单列表json数据
	 * @since 0.1
	 */
	public List operations2json(List operations);

	/**
	 * 判断某个用户（所属角色，部门）是否拥有某权限
	 * 
	 * @param userId
	 *            用户id
	 * @param priv
	 *            权限，如：链接或某操作权限
	 * @return 某用户是否拥有某权限
	 * @since 0.1
	 */
	public boolean hasPriv(String userId, String priv);

	/**
	 * 判断某个用户（所属角色，部门）是否拥有某权限
	 * 
	 * @param userId
	 *            用户id
	 * @param roleIds
	 *            角色id
	 * @param deptId
	 *            部门id
	 * @param priv
	 *            权限，如：链接或某操作权限
	 * @return 某用户是否拥有某权限
	 * @since 0.1
	 */
	public boolean hasPriv(String userId, List roleIds, String deptId,
			String priv);

	/**
	 * 查询OBJECT 对应的权限
	 * 
	 * @param objectid
	 *            用户，角色，部门ID
	 * @return 权限
	 * @since 0.1
	 */
	public List getObjectPriv(String objectid);

	/**
	 * 取某用户（包括其所属角色、部门）某菜单方案下所有可操作的菜单
	 * 
	 * @param userId
	 *            用户id
	 * @param deptId
	 *            部门id
	 * @param roleIds
	 *            角色列表
	 * @param menuId
	 *            菜单id
	 * @param type
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @param parentCode
	 *            父节点
	 * @return 某用户（包括其所属角色、部门）某菜单方案下所有可操作的菜单列表
	 * @since 0.1
	 */
	public List listOperation(String userId, String deptId, List roleIds,
			String menuId, String type, String parentCode);

	/**
	 * 取某用户（包括其所属角色、部门）某菜单方案下所有可操作的菜单
	 * 
	 * @param userId
	 *            用户id
	 * 
	 * @param menuId
	 *            菜单id
	 * @param parentCode
	 *            父节点
	 * @param type
	 *            查询菜单类型,PrivConstants.LIST_OPERATION_TYPE_ALL,PrivConstants,PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION
	 * @return 某用户（包括其所属角色、部门）某菜单方案下所有可操作的菜单列表
	 * @since 0.1
	 */
	public List listOperation(String userId, String menuId, String type,
			String parentCode);

	/**
	 * 查出拥有该url(权限)的赋权信息
	 * 
	 * @param priv
	 *            url（权限）
	 * @return 返回拥有该url的赋权信息
	 * @since 0.1
	 */
	public List listAssign(String priv);

	/**
	 * 查出拥有该url(权限)的大角色Id
	 * 
	 * @param priv
	 *            url（权限）
	 * @return 返回拥有该url的大角色Id列表
	 * @since 0.1
	 */
	public List listRoleIdByUrl(String priv);

	/**
	 * 查出拥有该url(权限)的子角色
	 * 
	 * @param priv
	 *            url（权限）
	 * @return 返回拥有该url的子角色列表
	 * @since 0.1
	 */
	public List listSubRoleByUrl(String priv);

	/**
	 * 查询出拥有该url（权限）的赋权人员
	 * 
	 * @param priv
	 *            url（权限）
	 * @return 返回拥有url（权限）的人员信息
	 * @since 0.1
	 */
	public Set listUserByUrl(String priv);

}
