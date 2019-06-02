package com.boco.eoms.commons.system.user.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.system.user.model.TawSystemUser;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Oct 31, 2008 2:45:55 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface IUserMgr {
	/**
	 * 通过用户id取用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return userId的用户
	 * @since 0.1
	 */
	public TawSystemUser getUser(String userId);

	/**
	 * 通过用户id及密码取用户
	 * 
	 * @param userId
	 *            用户id
	 * @param passwd
	 *            密码（未加密）
	 * @return 通过用户密码及id取用户
	 * @since 0.1
	 */
	public TawSystemUser getUser(String userId, String passwd);

	/**
	 * 判断是用户（userId）是否存在
	 * 
	 * @param userId
	 *            用户id
	 * @return 若存在返回true，否则返回false
	 * @since 0.1
	 */
	public boolean isExistUser(String userId);

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            用户信息
	 * @deprecated 目前不可用
	 */
	public void createUser(TawSystemUser user);

	/**
	 * 更改用户信息
	 * 
	 * @param user
	 *            用户信息
	 * @deprecated 不可用
	 */
	public void modifyUser(TawSystemUser user);

	/**
	 * 根据条件取所有用户，若condition=null则查询所有用户
	 * 
	 * @param condition
	 *            查询条件
	 * @return 根据条件返回用户，若condition=null则返回所有用户
	 * @since 0.1
	 */
	public List listUser(String condition);

	/**
	 * 取虚拟组组长
	 * 
	 * @param groupId
	 * @return
	 * @since 0.1
	 */
	public TawSystemUser getLeaderOfGroup(String groupId);

	/**
	 * 是否为值班长
	 * 
	 * @param userId
	 * @param roomId
	 * @param workSerilId
	 *            班次id
	 * @return 是否为值班长
	 * @deprecated 不可用
	 */
	public boolean isDutyMaster(String userId, String roomId, String workSerilId);

	/**
	 * 是否为机房管理员
	 * 
	 * @param roomId
	 * @param userId
	 * @return 是否为机房管理员
	 * @deprecated 不可用
	 */
	public boolean isRoomMgr(String roomId, String userId);

	/**
	 * 由（userId）代理某模块的人员列表
	 * 
	 * @param userId
	 *            代理人
	 * @param moduleId
	 *            可为模块id，
	 * @param type
	 *            部门、角色代理方式
	 * @return 申请人列表
	 * @since 0.1
	 */
	public List listApplyUser(String userId, String moduleId, String type);

	/**
	 * 取出userId申请的全部代理(userId)的全部代理人员
	 * 
	 * @param userId
	 * @param moduleId
	 * @return 代理人列表
	 * @since 0.1
	 */
	public List listProxyUser(String userId, String moduleId);

	/**
	 * 通过一个（多个）部门ID取其部门ID下的所有用户
	 * 
	 * @param deptIds
	 *            部门ID数组
	 * @return 部门ID下的所有用户
	 * @since 0.1
	 */
	public List listUserOfDept(String[] deptIds);

	/**
	 * 通过一个（多个）子角色ID取其下的所有用户
	 * 
	 * @param subRoleIds
	 *            子角色ID数组
	 * @return 子角色下的所有用户
	 * @since 0.1
	 */
	public List listUserOfsubrole(String[] subRoleIds);

	/**
	 * 分页取用户列表
	 * 
	 * @param curPage
	 *            要取的页码
	 * @param pageSize
	 *            每页多少条
	 * @param condition
	 *            查询条件
	 * @return total=总条数,result=当前页的用户列表
	 * @since 0.1
	 */
	public Map mapUser(final Integer curPage, final Integer pageSize,
			final String condition);

	/**
	 * 取某（多个）机房下的所有用户
	 * 
	 * @param roomIds
	 *            机房id数组
	 * @return 某（多个）机房下的所有用户
	 * @since 0.1
	 */
	public List listUserOfRoom(String[] roomIds);

	/**
	 * 删除某（userIds）用户
	 * 
	 * @param userIds
	 *            用户id数组
	 * @deprecated 不可用
	 */
	public void removeUsers(String[] userIds);

	/**
	 * 删除用户列表（users)
	 * 
	 * @param users
	 *            用户list
	 * @deprecated 不可用
	 */
	public void removeUsers(List users);

	/**
	 * 删除用户
	 * 
	 * @param user
	 *            用户
	 * @deprecated 不可用
	 */
	public void removeUser(TawSystemUser user);

	/**
	 * 删除某（多个）部门（deptIds）下的所有用户
	 * 
	 * @param deptIds
	 *            部门id数组
	 * @deprecated 不可用
	 */
	public void removeUsersOfDept(String[] deptIds);

	/**
	 * 删除某（多个）子角色ID（subRoleIds）下的所有用户
	 * 
	 * @param subRoleIds
	 *            子角色ID数组
	 * @deprecated 不可用
	 */
	public void removeUsersOfSubrole(String[] subRoleIds);

	/**
	 * 取某地域下的所有人
	 * 
	 * @param areaId
	 *            地域id
	 * @return 某地域下的所有人
	 * @since 0.1
	 */
	public List listUserOfArea(String areaId);

	/**
	 * 取某部下的负责人
	 * 
	 * @param deptId
	 * @return 负责人
	 * @since 0.1
	 */
	public TawSystemUser getLeaderOfDept(String deptId);

	/**
	 * 子角色是否拥有某用户
	 * 
	 * @param userId
	 *            用户id
	 * @param subRoleId
	 *            子角色id
	 * @return 子角色若拥有此用户返回true否则返回false
	 * @since 0.1
	 */
	public boolean isSubroleHasUser(String userId, String subRoleId);
	
	public abstract List listByNameQuery(String s);
}
