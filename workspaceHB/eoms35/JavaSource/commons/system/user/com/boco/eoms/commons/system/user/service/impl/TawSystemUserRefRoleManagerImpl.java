package com.boco.eoms.commons.system.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

public class TawSystemUserRefRoleManagerImpl extends BaseManager implements
		ITawSystemUserRefRoleManager {
	private TawSystemUserRefRoleDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawSystemUserRefRoleDao(TawSystemUserRefRoleDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager#getTawSystemUserRefRoles(com.boco.eoms.commons.system.user.model.TawSystemUserRefRole)
	 */
	public List getTawSystemUserRefRoles(
			final TawSystemUserRefRole tawSystemUserRefRole) {
		return dao.getTawSystemUserRefRoles(tawSystemUserRefRole);
	}

	/**
	 * @see com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager#getTawSystemUserRefRole(String
	 *      id)
	 */
	public TawSystemUserRefRole getTawSystemUserRefRole(final String id) {
		return dao.getTawSystemUserRefRole(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager#saveTawSystemUserRefRole(TawSystemUserRefRole
	 *      tawSystemUserRefRole)
	 */
	public void saveTawSystemUserRefRole(
			TawSystemUserRefRole tawSystemUserRefRole) {
		dao.saveTawSystemUserRefRole(tawSystemUserRefRole);
	}

	/**
	 * @see com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager#removeTawSystemUserRefRole(String
	 *      id)
	 */
	public void removeTawSystemUserRefRole(final String id) {
		dao.removeTawSystemUserRefRole(new String(id));
	}

	public List getRoleidByuserid(String userid) {
		return dao.getRoleidByuserid(userid);
	}

	/**
	 * 删除某角色的所有用�?	 * 
	 * @param roleid
	 */
	public void removeUseridByroleid(String roleid) {

		dao.removeUseridByroleid(roleid);

	}

	/**
	 * 删除某用户的所有角�?	 * 
	 * @param userid
	 */
	public void removeRoleidByUserid(String userid, String roleType) {

		dao.removeRoleidByUserid(userid, roleType);
	}

	/**
	 * 得到某角色的所有用�?	 * 
	 * @param roleid
	 * @return Map(TawSystemUser,groupType)
	 */
	public Map getGroupUserbyroleid(String subroleid) {

		return dao.getGroupUserbySubRoleid(subroleid);
	}

	/**
	 * 得到某角色的所有用�?	 * 
	 * @param roleid
	 * @return TawSystemUser
	 */
	public List getUserbyroleid(String subroleid) {

		return dao.getUserbySubRoleid(subroleid);
	}

	/**
	 * 得到某角�?某线状态的所有用�?	 * 
	 * @param roleid
	 * @param userstatus
	 * @return
	 */
	public List getUserByRoleidUserstatus(String subRoleid, String userstatus) {
		return dao.getUserbySubRoleidUserstatus(subRoleid, userstatus);

	}

	/**
	 * 得到某用户的所有角�?	 * 
	 * @param userid
	 * @param roleType
	 *            RoleConstants.ROLETYPE_SUBROLE,RoleConstants.ROLETYPE_ROLE
	 * @return
	 */
	public List getSubRoleByUserId(String userid, String roleType) {

		List list = new ArrayList();

		list = (ArrayList) dao.getSubRoleByuserid(userid, roleType);
		return list;

	}

	/**
	 * 给角色添加人�?	 * 
	 * @param roleid
	 * @param userId
	 */
	public void addUsers2SubRole(String subRoleid, String userId,
			String roleType, String groupType) {
		String[] userList = userId.split(",");
		for (int i = 0; i < userList.length; i++) {
			TawSystemUserRefRole refrole = new TawSystemUserRefRole();
			refrole.setSubRoleid(String.valueOf(subRoleid));
			refrole.setUserid(userList[i]);
			refrole.setRoleType(roleType);
			refrole.setGroupType(groupType);
			dao.saveTawSystemUserRefRole(refrole);
		}
	}

	/**
	 * 更新角色人员
	 * 
	 * @param roleid
	 * @param userId
	 */
	public void updateUsers2SubRole(String subRoleid, String userId,
			String roleType, String groupType) {
		dao.removeUseridByroleid(String.valueOf(subRoleid));
		addUsers2SubRole(subRoleid, userId, roleType, groupType);

	}

	
	/**
	 * 更新角色人员
	 * 
	 * @param roleid
	 * @param userId
	 */
	public void updateUsers2SubRole(String subRoleid, String userId,
			String roleType) {
		List list = dao.getUserbyRoleids(subRoleid);
		List teamLeader = new ArrayList();
		for (int i = 0; i < list.size(); i++)
		{
			TawSystemUserRefRole role = (TawSystemUserRefRole)list.get(i);
			if (role.getGroupType().equals("2"))
				teamLeader.add(role);
		}

		dao.removeUseridByroleid(String.valueOf(subRoleid));
		addUsers2SubRole(subRoleid, userId, roleType, "1", teamLeader);

	}

	public void addUsers2SubRole(String subRoleid, String userId, String roleType, String groupType, List teamLeader)
	{
		String userList[] = userId.split(",");
		for (int i = 0; i < userList.length; i++)
		{
			TawSystemUserRefRole refrole = new TawSystemUserRefRole();
			refrole.setSubRoleid(String.valueOf(subRoleid));
			refrole.setUserid(userList[i]);
			refrole.setRoleType(roleType);
			if (teamLeader.size() > 0)
			{
				for (int j = 0; j < teamLeader.size(); j++)
				{
					TawSystemUserRefRole teamLeaderRole = (TawSystemUserRefRole)teamLeader.get(j);
					if (userList[i].equals(teamLeaderRole.getUserid()))
						refrole.setGroupType("2");
					else
						refrole.setGroupType(groupType);
				}

			} else
			{
				refrole.setGroupType(groupType);
			}
			dao.saveTawSystemUserRefRole(refrole);
		}

	}
	
	/**
	 * 得到某用户的所有角�?	 * 
	 * @param userid
	 * @return
	 */
	public List getUserRefRoleByuserid(String userid) {
		return dao.getUserRefRoleByuserid(userid);
	}

	/**
	 * 得到某角色的所有用�?	 * 
	 * @param roleid
	 * @return Map(TawSystemUser,groupType)
	 */
	public List getUserbyRoleids(String subRoleid) {
		return dao.getUserbyRoleids(subRoleid);
	}

	public List getUserIdBySubRoleids(String subRoleid) {
		return dao.getUserIdBySubRoleids(subRoleid);
	}

	/**
	 * 更新人员的角色给人员添加角色
	 * 
	 * @param roleid
	 * @param userId
	 */
	public void updateSubRole(String subRoleid, String userId, String roleType) {
		TawSystemUserRefRole refrole = new TawSystemUserRefRole();
		refrole.setSubRoleid(String.valueOf(subRoleid));
		refrole.setUserid(userId);
		refrole.setRoleType(roleType);
		refrole.setGroupType("1");
		dao.saveTawSystemUserRefRole(refrole);
	}

	/**
	 * 更新人员的角色给人员添加角色
	 * 
	 * @param roleid
	 * @param userId
	 */
	public boolean isExist(String subRoleid, String userId) {
		boolean bool = false;
		bool = dao.isExist(subRoleid, userId);
		return bool;
	}

	/**
	 * 删除某用户的某角�?	 * 
	 * @param roleid
	 * @param userId
	 */
	public void deleteRole(String subRoleid, String userId) {
		dao.removeUserid(userId, subRoleid);
	}

	public void saveAdujustUserRefRole(String userId,
			String[] selSubRoleIdArray, String[] addSubRoleIdArray) {
		// 删除用户所有子角色
		removeRoleidByUserid(userId, RoleConstants.ROLETYPE_SUBROLE);

		// 选择的子角色不空,增加新的关联
		if (null != selSubRoleIdArray) {
			for (int i = 0; i < selSubRoleIdArray.length; i++) {
				String selSubRoleId = selSubRoleIdArray[i];
				if (!isExist(selSubRoleId, userId)) {
					TawSystemUserRefRole userRefRole = new TawSystemUserRefRole();
					userRefRole.setSubRoleid(selSubRoleId);
					userRefRole.setUserid(userId);
					userRefRole.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
					userRefRole.setGroupType(RoleConstants.groupType_leader);
					saveTawSystemUserRefRole(userRefRole);
				}
			}
		}

		//从树图选择了新的子角色,增加新的关联
		if (null != addSubRoleIdArray && !"".equals(addSubRoleIdArray.toString()) && !"[]".equals(addSubRoleIdArray.toString())) {

			for (int i = 0; i < addSubRoleIdArray.length; i++) {
				String addSubRoleId = addSubRoleIdArray[i];
				if (!isExist(addSubRoleId, userId)) {
					TawSystemUserRefRole userRefRole = new TawSystemUserRefRole();
					userRefRole.setSubRoleid(addSubRoleId);
					userRefRole.setUserid(userId);
					userRefRole.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
					saveTawSystemUserRefRole(userRefRole);
				}
			}
		}
	}
	public String getRoleTypeBySubRoleId(String subRoleId) {
		return dao.getRoleTypeBySubRoleId(subRoleId);
	}
	
	public TawSystemUserRefRole getRoleLeaderBySubRoleid(String subRoleId)
	{
		return dao.getRoleLeaderBySubRoleid(subRoleId);
	}
	
	public List getRoleLeaderlistBySubRoleid(String subRoleId)
	{
		return dao.getRoleLeaderlistBySubRoleid(subRoleId);
	}

	public void saveAdujustUserRefRole1(String userId, String selSubRoleIdArray[], String addSubRoleIdArray[], ITawSystemSubRoleManager subRoleMgr)
	{
		removeRoleidByUserid(userId, "1");
		if (selSubRoleIdArray != null)
		{
			for (int i = 0; i < selSubRoleIdArray.length; i++)
			{
				String selSubRoleId = selSubRoleIdArray[i];
				if (!isExist(selSubRoleId, userId))
				{
					TawSystemSubRole tawSystemSubRole = subRoleMgr.getTawSystemSubRole(selSubRoleId);
					long roleId = tawSystemSubRole.getRoleId();
					TawSystemUserRefRole userRefRole = new TawSystemUserRefRole();
					userRefRole.setSubRoleid(selSubRoleId);
					userRefRole.setUserid(userId);
					userRefRole.setRoleid(String.valueOf(roleId));
					userRefRole.setRoleType("1");
					userRefRole.setGroupType("1");
					saveTawSystemUserRefRole(userRefRole);
				}
			}

		}
		if (addSubRoleIdArray != null && !"".equals(addSubRoleIdArray.toString()) && !"[]".equals(addSubRoleIdArray.toString()))
		{
			for (int i = 0; i < addSubRoleIdArray.length; i++)
			{
				String addSubRoleId = addSubRoleIdArray[i];
				if (!"".equals(addSubRoleId) && !isExist(addSubRoleId, userId))
				{
					TawSystemSubRole tawSystemSubRole = subRoleMgr.getTawSystemSubRole(addSubRoleId);
					long roleId = tawSystemSubRole.getRoleId();
					TawSystemUserRefRole userRefRole = new TawSystemUserRefRole();
					userRefRole.setSubRoleid(addSubRoleId);
					userRefRole.setUserid(userId);
					userRefRole.setRoleid(String.valueOf(roleId));
					userRefRole.setRoleType("1");
					userRefRole.setGroupType("1");
					saveTawSystemUserRefRole(userRefRole);
				}
			}

		}
	}
	
	public List getSubRoleByCondition(String condition)
	{
		return dao.getSubRoleByCondition(condition);
	}

}
