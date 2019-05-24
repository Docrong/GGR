package com.boco.eoms.commons.system.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleDao;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.IRoleMgr;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

/**
 * 
 * <p>
 * Title:角色管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 11, 2008 11:31:23 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RoleMgrImpl implements IRoleMgr {
	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listGroupOfUser(String)
	 * 
	 */
	public TawSystemSubRole listGroupOfUser(String userId) {

		return null;
	}

	/**
	 * 角色mgr
	 */
	private ITawSystemRoleManager tawSystemRoleManager;

	/**
	 * 用户角色关联
	 */
	private ITawSystemUserRefRoleManager tawSystemUserRefRoleManager;

	/**
	 * 用户角色关联 dao
	 */
	private TawSystemUserRefRoleDao tawSystemUserRefRoleDao;

	/**
	 * 用户dao
	 */
	private TawSystemUserDao tawSystemUserDao;

	/**
	 * 子角色mgr
	 */
	private ITawSystemSubRoleManager tawSystemSubRoleManager;

	/**
	 * 子角色dao
	 */
	private TawSystemSubRoleDao tawSystemSubRoleDao;

	/**
	 * 大角色dao
	 */
	private TawSystemRoleDao tawSystemRoleDao;

	public void setTawSystemRoleManager(
			ITawSystemRoleManager tawSystemRoleManager) {
		this.tawSystemRoleManager = tawSystemRoleManager;
	}

	public void setTawSystemSubRoleManager(
			ITawSystemSubRoleManager tawSystemSubRoleManager) {
		this.tawSystemSubRoleManager = tawSystemSubRoleManager;
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#copySubRoleOfUser(String,
	 *      String)
	 */
	public void copySubRoleOfUser(String originUserId, String targetUserId) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#createRole(TawSystemRole)
	 */
	public void createRole(TawSystemRole tawSystemRole) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#createSubrole(TawSystemSubRole)
	 */
	public void createSubrole(TawSystemSubRole tawSystemSubRole) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#getRole(String)
	 */
	public TawSystemRole getRole(String roleId) {
		if (roleId == null) {
			return new TawSystemRole();
		}
		return this.tawSystemRoleManager.getTawSystemRole(Long
				.parseLong(roleId));
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#getRoleBySubrole(String)
	 */
	public TawSystemRole getRoleBySubrole(String subroleId) {
		TawSystemSubRole subrole = this.getSubRole(subroleId);
		return this.getRole(subrole.getRoleId() + "");
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#getRoleType(String)
	 */
	public String getRoleType(String roleId) {
		return this.getRole(roleId + "").getRoleTypeId() + "";
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#getSubRole(String)
	 */
	public TawSystemSubRole getSubRole(String subroleId) {
		return this.tawSystemSubRoleManager.getTawSystemSubRole(subroleId);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#isGroup(String,
	 *      String)
	 */
	public boolean isGroup(String groupId) {
		TawSystemRole role = this.getRole(groupId);

		// 返回是否为虚护组
		return RoleConstants.ROLETYPE_VIRTUAL.equals(role.getRoleTypeId() + "");

	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listChildRole(String)
	 */
	public List listChildRole(String roleId) {
		if (roleId == null) {
			return new ArrayList();
		}
		return this.tawSystemRoleManager.getChildrenByRoleId(Long
				.parseLong(roleId));
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listOtherSubroleOfRole(String)
	 */
	public List listOtherSubroleOfRole(String subroleId) {

		TawSystemSubRole subrole = this.getSubRole(subroleId);
		if (subrole == null) {
			return new ArrayList();
		}
		return this.tawSystemSubRoleDao.getOtherSubroles(subrole.getRoleId(),
				subroleId);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listRoleOfArea(String)
	 */
	public List listRoleOfArea(String areaId) {
		return this.tawSystemRoleDao.getRolesOfArea(areaId);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listRoleOfUser(String)
	 */
	public List listRoleOfUser(String userId) {
		return this.tawSystemUserRefRoleDao.getRoleByUserId(userId);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listRoleOfWorkflow(String)
	 */
	public List listRoleOfWorkflow(String workflowId) {
		if (workflowId == null) {
			return new ArrayList();
		}
		return this.tawSystemRoleManager.getFlwRolesByWorkflowFlag(Integer
				.parseInt(workflowId));
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubrole(String,
	 *      String)
	 */
	public List listSubroleByCon(String roleId, String condition) {
		return this.tawSystemSubRoleDao.getSubrolesByCon(roleId, condition);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubRole(String,
	 *      String)
	 */
	public List listSubrole(String areaId, String roleId) {
		return this.tawSystemSubRoleDao.getSubRolesByAreaIdAndRoleId(areaId,
				roleId);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubroleOfDeptUsers(String,
	 *      String)
	 * 
	 */
	public List listSubroleOfDeptUsers(String deptId, String roleType) {
		// 根据部门取出所有用户
		List users = tawSystemUserDao.getUserBydeptids(deptId);
		// 构建要使用的用户id数组
		String userIds[] = null;
		if (users != null) {
			userIds = new String[users.size()];
			for (int i = 0; i < users.size(); i++) {
				TawSystemUser user = (TawSystemUser) users.get(i);
				userIds[i] = user.getUserid();
			}
		}
		// 查询部门下的所有用户的子角色
		return tawSystemUserRefRoleDao.getSubrolesByUserId(userIds, roleType,
				Constants.NOT_DELETED_FLAG);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubroleOfUser(String,
	 *      String, String)
	 * 
	 */
	public List listSubroleOfUser(String userId, String roleType) {
		return tawSystemUserRefRoleDao.getSubrolesByUserId(
				new String[] { userId }, roleType, Constants.NOT_DELETED_FLAG);
	}

	public void modifyRole(TawSystemRole role) {
		// TODO Auto-generated method stub

	}

	public void modifySubRole(TawSystemSubRole subrole) {
		// TODO Auto-generated method stub

	}

	public void removeRole(TawSystemRole tawSystemRole) {
		// TODO Auto-generated method stub

	}

	public void removeRoles(List roles) {
		// TODO Auto-generated method stub

	}

	public void removeRoles(String[] roleIds) {
		// TODO Auto-generated method stub

	}

	public void removeSubrole(String[] subroleIds) {
		// TODO Auto-generated method stub

	}

	public void removeSubrole(TawSystemSubRole tawSystemSubRole) {
		// TODO Auto-generated method stub

	}

	public void removeSubroles(List subRoles) {
		// TODO Auto-generated method stub

	}

	public void removeSubrolesOfRole(String roleId) {
		// TODO Auto-generated method stub

	}

	public void subroles2Role(String[] subroleIds, String roleId) {
		// TODO Auto-generated method stub

	}

	public void user2Subroles(String userId, String[] subRoleIds) {
		// TODO Auto-generated method stub

	}

	public void setTawSystemSubRoleDao(TawSystemSubRoleDao tawSystemSubRoleDao) {
		this.tawSystemSubRoleDao = tawSystemSubRoleDao;
	}

	public void setTawSystemRoleDao(TawSystemRoleDao tawSystemRoleDao) {
		this.tawSystemRoleDao = tawSystemRoleDao;
	}

	public void setTawSystemUserRefRoleManager(
			ITawSystemUserRefRoleManager tawSystemUserRefRoleManager) {
		this.tawSystemUserRefRoleManager = tawSystemUserRefRoleManager;
	}

	public void setTawSystemUserDao(TawSystemUserDao tawSystemUserDao) {
		this.tawSystemUserDao = tawSystemUserDao;
	}

	public void setTawSystemUserRefRoleDao(
			TawSystemUserRefRoleDao tawSystemUserRefRoleDao) {
		this.tawSystemUserRefRoleDao = tawSystemUserRefRoleDao;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubRole(java.lang.String,
	 *      int)
	 */
	public List listSubRole(String areaId, int roleId) {
		return this.tawSystemSubRoleDao.listSubRole(areaId, roleId);
	}

	public List listSubRoleWithType1NotNull(String areaId, String roleId) {
		return this.tawSystemSubRoleDao.listSubRoleWithType1NotNull(areaId, roleId);
	}
	
	public List listSubRoleWithType1Null(String areaId, String roleId) {
		return this.tawSystemSubRoleDao.listSubRoleWithType1Null(areaId, roleId);
	}
	
	/**
	 * 获取虚拟组的组长
	 * 
	 * @param subroleid
	 *            虚拟组或子角色ID
	 * @return String[]{userid,username},不是虚拟组或找不到组长时，返回null
	 */
	public String[] getRoleLeaderBySubRoleid(String subroleid) {
		TawSystemUserRefRole leaderUser = tawSystemUserRefRoleDao.getRoleLeaderBySubRoleid(subroleid);
		if (leaderUser == null) {
			return null;
		}
		String userId = leaderUser.getUserid();
		if (userId == null || userId.trim().equals("")) {
			return null;
		}
		String userName = "";
		try {
			userName = tawSystemUserDao.id2Name(userId);
		} catch(Exception e) {
		}
		return new String[]{userId,userName};
	}

}
