package com.boco.eoms.commons.system.role.service;

import java.util.List;

import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;

/**
 * <p>
 * Title:角色管理API
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Oct 31, 2008 2:35:23 PM
 * </p>
 * 
 * @author 曲静波
 * @version 0.1
 * 
 */
public interface IRoleMgr {
	/**
	 * 取某流程的角色
	 * 
	 * @param workflowId
	 *            流程id
	 * @since 0.1
	 */
	public List listRoleOfWorkflow(String workflowId);

	/**
	 * 取角色的所属角色
	 * 
	 * @param roleId
	 *            角色id
	 * @return 所属角色
	 * @since 0.1
	 */
	public List listChildRole(String roleId);

	/**
	 * 返回大角色类型
	 * 
	 * @param roleId
	 *            角色id
	 * @return 大角色类型
	 * @since 0.1
	 */
	public String getRoleType(String roleId);

	/**
	 * 获取某人的所有子角色
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleType
	 *            子角色类型， RoleConstants.flowRole=流程角色，
	 *            RoleConstants.systemRole=系统角色， RoleConstants.ALL_ROLE=全部角色
	 * 
	 * @return 某人员对应类型子角色集合
	 * @since 0.1
	 */
	public List listSubroleOfUser(String userId, String roleType);

	/**
	 * 得到某部门下所有人员的子角色
	 * 
	 * @param deptId
	 *            用户ID
	 * @param roleType
	 *            子角色类型， RoleConstants.flowRole=流程角色，
	 *            RoleConstants.systemRole=系统角色， RoleConstants.ALL_ROLE=全部角色
	 * 
	 * @return 某人员对应类型子角色集合
	 * @since 0.1
	 */
	public List listSubroleOfDeptUsers(String deptId, String roleType);

	/**
	 * 将用户赋予某个（多个）子角色
	 * 
	 * @param userId
	 *            用户id
	 * @param subRoleIds
	 *            子角色id数组
	 * @deprecated 不可用
	 */
	public void user2Subroles(String userId, String[] subRoleIds);

	/**
	 * 创建子角色
	 * 
	 * @param tawSystemSubRole
	 *            子角色对象
	 * @deprecated 不可用
	 */
	public void createSubrole(TawSystemSubRole tawSystemSubRole);

	/**
	 * 删除某（多个）（subroleIds）子角色
	 * 
	 * @param subroleIds
	 *            子角色id数组
	 * @deprecated 不可用
	 */
	public void removeSubrole(String[] subroleIds);

	/**
	 * 删除子角色
	 * 
	 * @param tawSystemSubRole
	 *            待删除的子角色
	 * @deprecated 不可用
	 */
	public void removeSubrole(TawSystemSubRole tawSystemSubRole);

	/**
	 * 删除列表中（subRoles）的子角色
	 * 
	 * @param subRoles
	 *            子角色列表
	 * @deprecated 不可用
	 */
	public void removeSubroles(List subRoles);

	/**
	 * 复制某用户的所有角色到另一用户
	 * 
	 * @param originUserId
	 *            源用户id
	 * @param targetUserId
	 *            目的用户id
	 * @deprecated 不可用
	 */
	public void copySubRoleOfUser(String originUserId, String targetUserId);

	/**
	 * 取某子角色
	 * 
	 * @param subroleId
	 *            子角色id
	 * @return subroleId的某子角色
	 * @since 0.1
	 */
	public TawSystemSubRole getSubRole(String subroleId);

	/**
	 * 过滤子角色
	 * 
	 * @param roleId
	 *            角色id
	 * @param condition
	 *            过滤条件
	 * @return 子角色列表
	 * @since 0.1
	 */
	public List listSubroleByCon(String roleId, String condition);

	/**
	 * 取某子角色所属的大角色
	 * 
	 * @param subroleId
	 *            子角色id
	 * @return 大角色
	 * @since 0.1
	 */
	public TawSystemRole getRoleBySubrole(String subroleId);

	/**
	 * 取某大角色
	 * 
	 * @param roleId
	 *            角色id
	 * @return roleid的某大角色
	 * @since 0.1
	 */
	public TawSystemRole getRole(String roleId);

	/**
	 * 取用户下的大角色
	 * 
	 * @param userId
	 *            用户id
	 * @return 大角色
	 * @since 0.1
	 */
	public List listRoleOfUser(String userId);

	/**
	 * 取某地域下的角色
	 * 
	 * @param areaId
	 *            地域id
	 * @return 地市下的角色列表
	 * @since 0.1
	 */
	public List listRoleOfArea(String areaId);

	/**
	 * 取某地域下的大角色下的子角色
	 * 
	 * @param areaId
	 *            地域id
	 * @param roleId
	 *            大角色id
	 * @return 子角色列表
	 * @since 0.1
	 * 
	 */
	public List listSubrole(String areaId, String roleId);

	/**
	 * 某个（多个）子角色赋予大角色
	 * 
	 * @param subroleIds
	 *            子角色id数组
	 * @param roleId
	 *            大角色id
	 * @deprecated 不可用
	 */
	public void subroles2Role(String[] subroleIds, String roleId);

	/**
	 * 创建大角色
	 * 
	 * @param tawSystemRole
	 *            大角色
	 * @deprecated 不可用
	 */
	public void createRole(TawSystemRole tawSystemRole);

	/**
	 * 删除某个（多个）大角色
	 * 
	 * @param roleIds
	 *            大角色id数组
	 * @deprecated 不可用
	 */
	public void removeRoles(String[] roleIds);

	/**
	 * 删除大角色
	 * 
	 * @param tawSystemRole
	 *            大角色
	 * @deprecated 不可用
	 */
	public void removeRole(TawSystemRole tawSystemRole);

	/**
	 * 删除所有（多个或一个）大角色
	 * 
	 * @param roles
	 *            大角色集合
	 * @deprecated 不可用
	 */
	public void removeRoles(List roles);

	/**
	 * 删除某大角色（roleId）下的所有子角色
	 * 
	 * @param roleId
	 *            大角色id
	 * @deprecated 不可用
	 */
	public void removeSubrolesOfRole(String roleId);

	/**
	 * 修改大角色
	 * 
	 * @param role
	 *            大角色
	 * @deprecated 不可用
	 */
	public void modifyRole(TawSystemRole role);

	/**
	 * 
	 * @param subrole
	 */
	public void modifySubRole(TawSystemSubRole subrole);

	/**
	 * 获取subroleId（子角色）的大角色下所属的其他子角色
	 * 
	 * @param subroleId
	 *            子角色id
	 * @return 子角色列表
	 * @since 0.1
	 */
	public List listOtherSubroleOfRole(String subroleId);

	/**
	 * 判断是否为虚拟组
	 * 
	 * @param groupId
	 *            角色ID，子角色ID
	 * 
	 * @return 是否虚拟组
	 * @since 0.1
	 */
	public boolean isGroup(String groupId);

	/**
	 * 取某用户的所有虚拟组
	 * 
	 * @param userId
	 *            用户id
	 * @return 虚拟组列表
	 * @since 0.1
	 */
	public TawSystemSubRole listGroupOfUser(String userId);
	/**
	 * 取与地域ID，角色ID匹配但是没有配置一级网络分类的子角色列表
	 * 
	 * @param areaId
	 *            地域id
	 * @param roleId
	 *            大角色id
	 * @return 与地域ID，角色ID匹配但是没有配置一级网络分类的子角色列表
	 */
	public List listSubRole(String areaId, int roleId);

	/**
	 * 取areaId,roleId，但type1 not为空的
	 * 
	 * @param areaId
	 *            地域id
	 * @param roleId
	 *            角色id
	 * @return 取areaId,roleId，但type1不为空的subrole列表
	 */
	public List listSubRoleWithType1NotNull(String areaId, String roleId);
	
	/**
	 * 取areaId,roleId，但type1 not为空的
	 * 
	 * @param areaId
	 *            地域id
	 * @param roleId
	 *            角色id
	 * @return 取areaId,roleId，但type1为空的subrole列表
	 */
	public List listSubRoleWithType1Null(String areaId, String roleId);
	
	/**
	 * 获取虚拟组的组长
	 * 
	 * @param subroleid
	 *            虚拟组或子角色ID
	 * @return String[]{userid,username},不是虚拟组或找不到组长时，返回null
	 */
	public String[] getRoleLeaderBySubRoleid(String subroleid);
}
