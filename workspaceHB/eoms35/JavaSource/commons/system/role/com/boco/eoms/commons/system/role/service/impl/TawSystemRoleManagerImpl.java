package com.boco.eoms.commons.system.role.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleDao;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.ui.util.UIConstants;

public class TawSystemRoleManagerImpl extends BaseManager implements
		ITawSystemRoleManager {
	private TawSystemRoleDao dao;

	private TawSystemUserRefRoleDao userRoleDao;

	private TawSystemSubRoleDao subRoleDao;

	public void setSubRoleDao(TawSystemSubRoleDao dao) {
		this.subRoleDao = dao;
	}

	/**
	 * @param userRoleDao The userRoleDao to set.
	 */
	public void setUserRoleDao(TawSystemUserRefRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setTawSystemRoleDao(TawSystemRoleDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleManager#getTawSystemRoles(com.boco.eoms.commons.system.role.model.TawSystemRole)
	 */
	public List getTawSystemRoles() {
		return dao.getTawSystemRoles();
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleManager#getTawSystemRole(String roleId)
	 */
	public TawSystemRole getTawSystemRole(final long roleId) {
		return dao.getTawSystemRole(roleId);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleManager#saveTawSystemRole(TawSystemRole tawSystemRole)
	 */
	public void saveTawSystemRole(TawSystemRole tawSystemRole) {
		dao.saveTawSystemRole(tawSystemRole);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleManager#removeTawSystemRole(String roleId)
	 */
	public void removeTawSystemRole(final long roleId)
			throws TawSystemUserException {
		dao.removeTawSystemRole(roleId);
		userRoleDao.removeUseridByroleid(String.valueOf(roleId));
		subRoleDao.removeTawSystemSubRole(roleId);
	}

	/**
	 * 
	 */
	public Map getTawSystemRoles(final Integer curPage, final Integer pageSize) {
		return dao.getTawSystemRoles(curPage, pageSize, null);
	}

	public Map getTawSystemRoles(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getTawSystemRoles(curPage, pageSize, whereStr);
	}

	public TawSystemRole getTawSystemRole(long roleId, int deleted) {
		return dao.getTawSystemRole(roleId, deleted);
	}

	public List getRolesByStructureFlag(long roleId) {
		TawSystemRole role = dao.getTawSystemRole(roleId);
		List list = dao.getRolesByStructureFlag(role.getStructureFlag(),
				StaticVariable.UNDELETED);
		return list;
	}

	public List getChildRoleByRoleIdPirv(long roleId) {
		// TODO Auto-generated method stub
		List list = dao.getChildRoleByRoleIdPirv(roleId);
		return list;
	}

	public List getChildrenByRoleId(long roleId) {
		List list = dao.getChildRoleByRoleId(roleId);
		return list;
	}

	public String getRoleNameById(final long roleId) {
		return dao.getRoleNameById(roleId);
	}

	public void setLeaf(final long roleId, final Integer leaf) {
		dao.setLeaf(roleId, leaf);
	}

	public String getNewStructureFlag(long parentRoleId) throws Exception {
		return dao.getNewStructureFlag(parentRoleId);
	}

	public List getRolesByPostId(long postId) {
		return dao.getRolesByPostId(postId);
	}

	public List getRolesByWorkflowFlag(final int workflowFlag) {
		return dao.getRolesByWorkflowFlag(workflowFlag);
	}

	/**
	 * 获取大角色对应的所有部门
	 * @param roleId
	 * @return <TawSystemDept>
	 */
	public List getDeptByRoleId(final long roleId) {
		return dao.getDeptByRoleId(roleId);
	}

	/**
	 * 获取大角色对应的所有地域
	 * @param roleId
	 * @return <TawSystemDept>
	 */
	public List getAreaByRoleId(final long roleId) {
		return dao.getAreaByRoleId(roleId);
	}

	/**
	 * 获取角色对象
	 * @param workflowFlag 流程编号
	 * @param roleName 角色名称
	 * @return
	 */
	public TawSystemRole getTawSystemRole(Integer workflowFlag, String roleName) {
		return dao.getTawSystemRole(workflowFlag, roleName);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleManager#getSysRolesByRoleId
	 */
	public List getSysRolesByRoleId(final long roleId) {
		return dao.getSysRolesByRoleId(roleId);
	}
	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleManager#getFlwRolesByWorkflowFlag
	 */
	public List getFlwRolesByWorkflowFlag(final int workflowFlag) {
		return dao.getFlwRolesByWorkflowFlag(workflowFlag);
	}
	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleManager#getBaseJSON
	 */
	public JSONObject getJSONBase(TawSystemRole role) {
		JSONObject j = new JSONObject();
		j.put(UIConstants.JSON_ID, role.getRoleId());
		j.put(UIConstants.JSON_TEXT, String.valueOf(role.getRoleTypeId()).equals(
				RoleConstants.ROLETYPE_VIRTUAL) ? role.getRoleName()+RoleConstants.TEXT_VIRTUAL_SUFFIX : role.getRoleName());
		return j;
	}

	/**
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemRoleManager#getTreeNodeJSON
	 */
	public JSONObject getJSON4TreeNode(TawSystemRole role) {
		JSONObject j = this.getJSONBase(role);
		j.put(UIConstants.JSON_NAME, role.getRoleName());
		if (String.valueOf(role.getRoleTypeId()).equals(
				RoleConstants.ROLETYPE_VIRTUAL)) {
			j.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_VIRTUAL);
		} else {
			j.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_ROLE);
		}
		j.put("roleTypeId", role.getRoleTypeId());
		j.put("workflowFlag", role.getWorkflowFlag());
		j.put("leaf", role.getLeaf());
		return j;
	}

	public List listFirstLevelAreaByRoleId(long roleId) {
		// TODO 自动生成方法存根
		return null;
	}
}
