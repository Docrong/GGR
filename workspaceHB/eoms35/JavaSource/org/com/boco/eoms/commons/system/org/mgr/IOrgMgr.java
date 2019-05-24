package com.boco.eoms.commons.system.org.mgr;

import java.util.List;

/**
 * <p>
 * Title:组织管理api
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Oct 22, 2008 2:49:31 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface IOrgMgr {

	/**
	 * 获取某人的所有子角色
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleType
	 *            子角色类型， RoleConstants.flowRole=流程角色，
	 *            RoleConstants.systemRole=系统角色， RoleConstants.ALL_ROLE=全部角色
	 * @param delFlag
	 *            删除标记位,Constants.DELETED_FLAG=删除,Constants.NOT_DELETED_FLAG=未删除
	 * @return 某人员对应类型子角色集合
	 */
	public List getSubrolesOfUser(String userId, String roleType, String delFlag);

	/**
	 * 得到某部门下所有子角色
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleType
	 *            子角色类型， RoleConstants.flowRole=流程角色，
	 *            RoleConstants.systemRole=系统角色， RoleConstants.ALL_ROLE=全部角色
	 * @param delFlag
	 *            删除标记位,Constants.DELETED_FLAG=删除,Constants.NOT_DELETED_FLAG=未删除
	 * @return 某人员对应类型子角色集合
	 */
	public List getSubrolesOfDept(String deptId, String roleType, String delFlag);
}
