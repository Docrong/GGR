package com.boco.eoms.commons.system.user.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;

public interface ITawSystemUserRefRoleManager extends Manager {
	/**
	 * Retrieves all of the tawSystemUserRefRoles
	 */
	public List getTawSystemUserRefRoles(
			TawSystemUserRefRole tawSystemUserRefRole);

	/**
	 * Gets tawSystemUserRefRole's information based on id.
	 * 
	 * @param id
	 *            the tawSystemUserRefRole's id
	 * @return tawSystemUserRefRole populated tawSystemUserRefRole object
	 */
	public TawSystemUserRefRole getTawSystemUserRefRole(final String id);

	/**
	 * Saves a tawSystemUserRefRole's information
	 * 
	 * @param tawSystemUserRefRole
	 *            the object to be saved
	 */
	public void saveTawSystemUserRefRole(
			TawSystemUserRefRole tawSystemUserRefRole);

	/**
	 * Removes a tawSystemUserRefRole from the database by id
	 * 
	 * @param id
	 *            the tawSystemUserRefRole's id
	 */
	public void removeTawSystemUserRefRole(final String id);

	/**
	 * 删除某角色的所有用户
	 * 
	 * @param roleid
	 */
	public void removeUseridByroleid(String roleid);

	/**
	 * 删除某用户的所有角色
	 * 
	 * @param userid
	 */
	public void removeRoleidByUserid(String userid, String roleType);

	/**
	 * 得到某角色的所有用户
	 * 
	 * @param roleid
	 * @return Map(TawSystemUser,groupType)
	 */
	public Map getGroupUserbyroleid(String subRoleid);

	/**
	 * 得到某角色的所有用户
	 * 
	 * @param roleid
	 * @return TawSystemUser
	 */
	public List getUserbyroleid(String subRoleid);

	public List getRoleidByuserid(String userid);

	/**
	 * 得到某角色,某线状态的所有用户
	 * 
	 * @param roleid
	 * @param userstatus
	 * @return
	 */
	public List getUserByRoleidUserstatus(String subRoleid, String userstatus);

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getSubRoleByUserId(String userid, String roleType);

	/**
	 * 给角色添加人员
	 * 
	 * @param roleid
	 * @param userId
	 *            逗号分隔
	 */
	public void addUsers2SubRole(String subRoleid, String userId,
			String roleType, String groupType);

	/**
	 * 更新角色人员
	 * 
	 * @param roleid
	 * @param userId
	 *            逗号分隔
	 */
	public void updateUsers2SubRole(String subRoleid, String userId,
			String roleType, String groupType);

	/**
	 * 更新角色人员
	 * 
	 * @param roleid
	 * @param userId
	 *            逗号分隔
	 */
	public void updateUsers2SubRole(String subRoleid, String userId,
			String roleType);

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getUserRefRoleByuserid(String userid);

	/**
	 * 得到某角色的所有用户
	 * 
	 * @param roleid
	 * @return Map(TawSystemUser,groupType)
	 */
	public List getUserbyRoleids(String subRoleid);

	/**
	 * 得到某角色的所有用户userId
	 * 
	 * @param subRoleid
	 * @return
	 */
	public List getUserIdBySubRoleids(String subRoleid);

	/**
	 * 更新人员的角色给人员添加角色
	 * 
	 * @param roleid
	 * @param userId
	 */
	public void updateSubRole(String subRoleid, String userId, String roleType);

	/**
	 * 是否存在
	 * 
	 * @param roleid
	 * @param userId
	 */
	public boolean isExist(String subRoleid, String userId);

	/**
	 * 删除某用户的某角色
	 * 
	 * @param roleid
	 * @param userId
	 */
	public void deleteRole(String subRoleid, String userId);

	/**
	 * 保存调整后的结果,未勾选的已有子角色删除,从树图中新选择的子角色增加
	 * 
	 * @param userId
	 *            用户Id
	 * @param selSubRoleIdArray
	 *            选择的角色(已有)
	 * @param addSubRoleIdArray
	 *            增加的新角色(新选)
	 */
	public void saveAdujustUserRefRole(String userId,
			String[] selSubRoleIdArray, String[] addSubRoleIdArray);
	
	public abstract void saveAdujustUserRefRole1(String s, String as[], String as1[], ITawSystemSubRoleManager itawsystemsubrolemanager);
	
	/**
	 * 通过子角色id得到是不是虚拟组
	 * @param subRoleId 子角色id
	 * @return roletype   3为虚拟组
	 */
	public String getRoleTypeBySubRoleId(String subRoleId);
	
	public abstract TawSystemUserRefRole getRoleLeaderBySubRoleid(String s);
	
	public abstract List getRoleLeaderlistBySubRoleid(String s);
	
	public abstract List getSubRoleByCondition(String condition);
}
