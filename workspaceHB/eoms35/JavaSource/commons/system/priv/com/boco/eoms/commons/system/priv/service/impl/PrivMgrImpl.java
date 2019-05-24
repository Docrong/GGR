package com.boco.eoms.commons.system.priv.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.util.DeptConstants;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.service.IPrivMgr;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.listitem.TawCommonsUIListItem;

/**
 * <p>
 * Title:权限管理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 20, 2008 5:34:41 PM
 * </p>
 * 
 * @author 龚玉峰，曲静波
 * @version 3.5.1
 * 
 */
public class PrivMgrImpl implements IPrivMgr {

	/**
	 * 用户管理mgr
	 */
	private ITawSystemUserManager tawSystemUserManager;

	/**
	 * 角色dao
	 */
	private TawSystemUserRefRoleDao tawSystemUserRefRoleDao;

	/**
	 * 分配权限mgr
	 */
	private ITawSystemPrivAssignManager tawSystemPrivAssignManager;

	/**
	 * 菜单方案mgr
	 */
	private ITawSystemPrivMenuManager tawSystemPrivMenuManager;

	/**
	 * 菜单dao
	 */
	private TawSystemPrivOperationDao tawSystemPrivOperationDao;

	/**
	 * @param tawSystemUserRefRoleDao
	 *            the tawSystemUserRefRoleDao to set
	 */
	public void setTawSystemUserRefRoleDao(
			TawSystemUserRefRoleDao tawSystemUserRefRoleDao) {
		this.tawSystemUserRefRoleDao = tawSystemUserRefRoleDao;
	}

	/**
	 * @param tawSystemPrivAssignManager
	 *            the tawSystemPrivAssignManager to set
	 */
	public void setTawSystemPrivAssignManager(
			ITawSystemPrivAssignManager tawSystemPrivAssignManager) {
		this.tawSystemPrivAssignManager = tawSystemPrivAssignManager;
	}

	/**
	 * @param tawSystemUserManager
	 *            the tawSystemUserManager to set
	 */
	public void setTawSystemUserManager(
			ITawSystemUserManager tawSystemUserManager) {
		this.tawSystemUserManager = tawSystemUserManager;
	}

	/**
	 * @param tawSystemPrivMenuManager
	 *            the tawSystemPrivMenuManager to set
	 */
	public void setTawSystemPrivMenuManager(
			ITawSystemPrivMenuManager tawSystemPrivMenuManager) {
		this.tawSystemPrivMenuManager = tawSystemPrivMenuManager;
	}

	/**
	 * 根据orgid 判断是部门，角色还是人员，在根据各自的id入库
	 */
	public boolean savePrivAssign(TawSystemPrivAssign tawSystemPrivAssign) {
		boolean bool = false;
		try {

			if (StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(tawSystemPrivAssign
					.getAssigntype())) {
				String menuid = tawSystemPrivAssign.getPrivid();
				String deptId = tawSystemPrivAssign.getObjectid();
				String orgType = tawSystemPrivAssign.getAssigntype();
				// 判断权限是否存在
				if (!this.hasAssigned(deptId, orgType, menuid)) {

					this.tawSystemPrivAssignManager
							.saveTawSystemPrivAssign(tawSystemPrivAssign);
					bool = true;
				} else {
					bool = false;
				}

			}
			// 权限类型为用户
			else if (StaticVariable.PRIV_ASSIGNTYPE_USER
					.equals(tawSystemPrivAssign.getAssigntype())) {
				String menuid = tawSystemPrivAssign.getPrivid();
				String userid = tawSystemPrivAssign.getObjectid();
				String orgType = tawSystemPrivAssign.getAssigntype();
				// 若已存在菜单方案赋给某组织，则不做操作
				if (!this.hasAssigned(userid, orgType, menuid)) {
					this.tawSystemPrivAssignManager
							.saveTawSystemPrivAssign(tawSystemPrivAssign);
					bool = true;
				} else {
					bool = false;
				}

			}
			// 权限类型为角色
			else if (StaticVariable.PRIV_ASSIGNTYPE_ROLE
					.equals(tawSystemPrivAssign.getAssigntype())) {
				String roleid = tawSystemPrivAssign.getObjectid();
				String menuid = tawSystemPrivAssign.getPrivid();
				String orgType = tawSystemPrivAssign.getAssigntype();
				// 若已存在菜单方案赋给某组织，则不做操作
				if (!this.hasAssigned(roleid, orgType, menuid)) {

					this.tawSystemPrivAssignManager
							.saveTawSystemPrivAssign(tawSystemPrivAssign);
					bool = true;
				} else {
					bool = false;
				}

			}
			return bool;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// 取实例
	public TawSystemPrivAssign getTawSystemPrivAssign(String id) {
		TawSystemPrivAssign tawSystemPrivAssign = this.tawSystemPrivAssignManager
				.getTawSystemPrivAssign(id);
		return tawSystemPrivAssign;
	}

	// 删除
	public void remove(String id) {
		this.tawSystemPrivAssignManager.removeTawSystemPrivAssign(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPermissionMgr#isAdmin(java.lang.String)
	 */
	public boolean isAdmin(String userId) {
		TawSystemUser user = tawSystemUserManager.getUserByuserid(userId);
		return PrivConstants.ADMIN_DEGREE.equals(user.getUserdegree());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listModuleMenu(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List listOpertion(String userId, String deptId, List roleIds,
			String type, String operationId) {
		return this.tawSystemPrivOperationDao.listOperation(userId, deptId,
				roleIds, type, operationId);
	}
	public List listOpertionwap(String userId, String deptId, List roleIds,
			String type, String operationId) {
		return this.tawSystemPrivOperationDao.listOperationwap(userId, deptId,
				roleIds, type, operationId);
	}
	public List listOpertion(String userId, String deptId, List roleIds,
			String type, String operationId,String loginNature){
		return this.tawSystemPrivOperationDao.listOperationWap(userId, deptId,
				roleIds, type, operationId,loginNature);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listModuleMenu(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List listOperationAll(String userId, String deptId, List roleIds,
			String type, String operationId) {
		return this.tawSystemPrivOperationDao.listOperationAll(userId, deptId,
				roleIds, type, operationId);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listModuleMenu(java.lang.String)
	 */
	public List listOpertion(String userId, String type, String operationId) {
		// 取用户所有角色
		List roleIds = this.tawSystemUserRefRoleDao.getSubRoleByuserid(userId,
				RoleConstants.ROLETYPE_SUBROLE);
		// 取用户所在部门
		TawSystemUser user = tawSystemUserManager.getUserByuserid(userId);
		String deptId = user != null ? user.getDeptid()
				: DeptConstants.NODEPT_DEPTID;
		return listOpertion(userId, deptId, roleIds, type, operationId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#hasAssigned(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public boolean hasAssigned(String orgId, String orgType, String menuId) {
		return this.tawSystemPrivAssignManager
				.hasAssign(orgId, orgType, menuId);
	}

	/**
	 * 查询角色或者用户的权限以JSON的方式返回
	 * 
	 * @param type
	 * @param objectid
	 * @return
	 */
	public JSONObject getJSONObjectPriv(String type, String objectid) {
		JSONObject jsonroot = new JSONObject();

		List list = null;

		JSONArray asg = new JSONArray();
		JSONArray roleAsg = new JSONArray();
		JSONArray deptAsg = new JSONArray();
		JSONArray allAsg = new JSONArray();
		JSONArray alldeptAsg = new JSONArray();
		list = getObjectPriv(objectid);
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				JSONObject jitem = new JSONObject();
				TawSystemPrivAssign assign = (TawSystemPrivAssign) list.get(i);
				jitem.put("value", assign.getId());
				jitem.put("text", getPrivNameByPrivid(assign.getPrivid()));
				asg.put(jitem);
			}

		}
		if (type.equals(DeptConstants.USER)) {
			List roleIds = this.tawSystemUserRefRoleDao.getSubRoleByuserid(
					objectid, RoleConstants.ROLETYPE_SUBROLE);
			if (roleIds != null && roleIds.size() > 0) {
				for (Iterator it = roleIds.iterator(); it.hasNext();) {
					JSONObject jitem = new JSONObject();
					TawSystemSubRole role = (TawSystemSubRole) it.next();
					List rolePrivList = getObjectPriv(role.getId());
					jitem.put("value", role.getId());
					jitem.put("text", role.getSubRoleName() + "("
							+ rolePrivList.size() + "条)");
					roleAsg.put(jitem);

					for (Iterator roleit = rolePrivList.iterator(); roleit
							.hasNext();) {
						JSONObject jitemrole = new JSONObject();
						TawSystemPrivAssign assign = (TawSystemPrivAssign) roleit
								.next();
						jitemrole.put("value", assign.getId());
						jitemrole.put("text", "--------"
								+ getPrivNameByPrivid(assign.getPrivid()));
						roleAsg.put(jitemrole);
					}

				}
			}

			TawSystemUser user = tawSystemUserManager.getUserByuserid(objectid);
			String deptid = user.getDeptid();
			List deptPrivlist = getObjectPriv(deptid);
			for (Iterator it = deptPrivlist.iterator(); it.hasNext();) {
				JSONObject jitem = new JSONObject();
				TawSystemPrivAssign assign = (TawSystemPrivAssign) it.next();
				jitem.put("value", assign.getId());
				jitem.put("text", getPrivNameByPrivid(assign.getPrivid()));
				deptAsg.put(jitem);
			}
			List deptAllList = this.listMenu(deptid,
					StaticVariable.PRIV_ASSIGNTYPE_DEPT);
			for (Iterator it = deptAllList.iterator(); it.hasNext();) {
				JSONObject jitem = new JSONObject();
				TawSystemPrivMenu menu = (TawSystemPrivMenu) it.next();
				// TawSystemPrivAssign assign = (TawSystemPrivAssign) it.next();
				jitem.put("value", menu.getPrivid());
				jitem.put("text", menu.getName());
				alldeptAsg.put(jitem);
			}
			List allPrivlist = this.listMenu(objectid);
			for (Iterator it = allPrivlist.iterator(); it.hasNext();) {
				JSONObject jitem = new JSONObject();
				TawSystemPrivMenu menu = (TawSystemPrivMenu) it.next();
				 
				jitem.put("value", menu.getPrivid());
				jitem.put("text", menu.getName());
				allAsg.put(jitem);
			}

		}

		jsonroot.put("asg", asg);
		jsonroot.put("roleAsg", roleAsg);
		jsonroot.put("deptAsg", deptAsg);
		jsonroot.put("alldeptAsg", alldeptAsg);

		jsonroot.put("allAsg", allAsg);

		return jsonroot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listMenu(java.lang.String,
	 *      java.lang.String)
	 */
	public List listMenu(String orgId, String orgType) {
		return this.tawSystemPrivMenuManager.listMenu(orgId, orgType);
	}

	/**
	 * 查询OBJECT 对应的权限
	 * 
	 * @param objectid
	 * @return
	 */
	public List getObjectPriv(String objectid) {
		return (ArrayList) tawSystemPrivAssignManager.getObjectPriv(objectid);
	}

	/**
	 * 通过PRIVID查询NAME
	 * 
	 * @param privid
	 * @return
	 */
	public String getPrivNameByPrivid(String privid) {
		return (String) tawSystemPrivMenuManager.getTawSystemPrivMenu(privid)
				.getName();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listMenu(java.lang.String)
	 */
	public List listMenu(String userId) {
		// 取用户所有角色
		List roleIds = this.tawSystemUserRefRoleDao.getSubRoleByuserid(userId,
				RoleConstants.ROLETYPE_SUBROLE);
		// 取用户所在部门
		TawSystemUser user = tawSystemUserManager.getUserByuserid(userId);
		String deptId = user != null ? user.getDeptid()
				: DeptConstants.NODEPT_DEPTID;
		// 取所有菜单方案
		return this.tawSystemPrivMenuManager.listMenu(userId, deptId, roleIds);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#operations2json(java.util.List)
	 */
	public List operations2json(List operations) {
		List menus = new ArrayList();
		if (operations == null || operations.isEmpty()) {
			return menus;
		}
		// 遍历菜单列表
		for (Iterator it = operations.iterator(); it.hasNext();) {
			TawSystemPrivOperation operation = (TawSystemPrivOperation) it
					.next();
			TawCommonsUIListItem item = new TawCommonsUIListItem();
			item.setItemId(operation.getCode());
			item.setText(operation.getName());
			item.setUrl(operation.getUrl());
			menus.add(item);
		}
		return menus;
	}

	/**
	 * @param tawSystemPrivOperationDao
	 *            the tawSystemPrivOperationDao to set
	 */
	public void setTawSystemPrivOperationDao(
			TawSystemPrivOperationDao tawSystemPrivOperationDao) {
		this.tawSystemPrivOperationDao = tawSystemPrivOperationDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#hasAssigned(java.util.List,
	 *      java.lang.String)
	 */
	public boolean hasAssigned(List roleIds, String menuId) {
		if (roleIds == null || roleIds.isEmpty()) {
			return false;
		}
		String roleStr = "";
		// 将角色id列表写成以逗号分隔形式
		for (Iterator it = roleIds.iterator(); it.hasNext();) {
			TawSystemSubRole role = (TawSystemSubRole) it.next();
			roleStr += role.getId() + ",";
		}
		roleStr = StaticMethod.removeLastStr(roleStr, ",");
		return this.hasAssigned(roleStr, StaticVariable.PRIV_ASSIGNTYPE_ROLE,
				menuId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#hasPriv(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean hasPriv(String userId, List roleIds, String deptId,
			String priv) {

		List list = this.tawSystemPrivOperationDao.listOperation(userId,
				deptId, roleIds, priv, new Integer(1));

		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#hasPriv(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean hasPriv(String userId, String priv) {
		// 取用户所有角色
		List roleIds = this.tawSystemUserRefRoleDao.getSubRoleByuserid(userId,
				RoleConstants.ROLETYPE_SUBROLE);
		// 取用户所在部门
		TawSystemUser user = tawSystemUserManager.getUserByuserid(userId);
		String deptId = user != null ? user.getDeptid()
				: DeptConstants.NODEPT_DEPTID;
		return hasPriv(userId, roleIds, deptId, priv);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listOperation(java.lang.String,
	 *      java.lang.String, java.util.List, java.lang.String,
	 *      java.lang.String)
	 */
	public List listOperation(String userId, String deptId, List roleIds,
			String menuId, String type, String parentCode) {
		return this.tawSystemPrivOperationDao.listOperation(userId, deptId,
				roleIds, menuId, type, parentCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listOperation(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List listOperation(String userId, String menuId, String type,
			String parentCode) {
		// 取用户所有角色
		List roleIds = this.tawSystemUserRefRoleDao.getSubRoleByuserid(userId,
				RoleConstants.ROLETYPE_SUBROLE);
		// 取用户所在部门
		TawSystemUser user = tawSystemUserManager.getUserByuserid(userId);
		String deptId = user != null ? user.getDeptid()
				: DeptConstants.NODEPT_DEPTID;
		return listOperation(userId, deptId, roleIds, menuId, type, parentCode);
	}

	/**
	 * 取某人员的所有菜单方案
	 * 
	 * @param userId
	 *            用户id
	 * @return 取某人员的所有菜单方案
	 */
	public boolean hasAssigned(String userId, String privid) {

		// 取用户所有角色
		List roleIds = this.tawSystemUserRefRoleDao.getSubRoleByuserid(userId,
				RoleConstants.ROLETYPE_SUBROLE);
		// 取用户所在部门
		TawSystemUser user = tawSystemUserManager.getUserByuserid(userId);
		String deptId = user != null ? user.getDeptid()
				: DeptConstants.NODEPT_DEPTID;
		// 取所有菜单方案
		List menulist = this.tawSystemPrivMenuManager.listMenu(userId, deptId,
				roleIds, privid);

		if (menulist.size() > 0)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listAssign(java.lang.String)
	 */
	public List listAssign(String priv) {
		return this.tawSystemPrivAssignManager.listAssign(priv);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listRoleIdByUrl(java.lang.String)
	 */
	public List listRoleIdByUrl(String priv) {
		//大角色Id列表
		List roleIds = new ArrayList();
		//子角色Id列表
		List subRoles = new ArrayList();
		//过滤重复大角色用
		HashMap hashMap = new HashMap();
		List assigns = this.tawSystemPrivAssignManager.listAssign(priv);
		ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemSubRoleManager");
		for (Iterator it = assigns.iterator(); it.hasNext();) {
			TawSystemPrivAssign assign = (TawSystemPrivAssign) it.next();
			// 若为角色
			if (com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_ROLE
					.equals(assign.getAssigntype())) {
				subRoles.add(roleMgr.getTawSystemSubRole(assign
								.getObjectid()));
			}
		}
		for (Iterator iter = subRoles.iterator(); iter.hasNext();) {
			TawSystemSubRole subRole = (TawSystemSubRole) iter.next();
			String roleIdStr = String.valueOf(subRole.getRoleId());
			//hashmap中取不到说明之前未取到过此大角色
			if (null == hashMap.get(roleIdStr) || "".equals(hashMap.get(roleIdStr))) {
				roleIds.add(roleIdStr);
				hashMap.put(roleIdStr, roleIdStr);
			}
		}
		return roleIds;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listSubRoleIdByUrl(java.lang.String)
	 */
	public List listSubRoleByUrl(String priv) {
		//子角色列表
		List subRoles = new ArrayList();
		List assigns = this.tawSystemPrivAssignManager.listAssign(priv);
		ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemSubRoleManager");
		for (Iterator it = assigns.iterator(); it.hasNext();) {
			TawSystemPrivAssign assign = (TawSystemPrivAssign) it.next();
			// 若为角色
			if (com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_ROLE
					.equals(assign.getAssigntype())) {
				TawSystemSubRole subRole = roleMgr.getTawSystemSubRole(assign
						.getObjectid());
				if (null != subRole.getId() && !"".equals(subRole.getId())) {
					subRoles.add(subRole);
				}
			}
		}
		return subRoles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.service.IPrivMgr#listUserByUrl(java.lang.String)
	 */
	public Set listUserByUrl(String priv) {
		List list = this.listAssign(priv);
		Set users = new HashSet();
		if (list != null) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				TawSystemPrivAssign assign = (TawSystemPrivAssign) it.next();
				// 若为用户则直接加入集合中
				if (StaticVariable.PRIV_ASSIGNTYPE_USER.equals(assign
						.getAssigntype())) {
					users.add(this.tawSystemUserManager.getUserByuserid(assign
							.getObjectid()));
				}
				// 若为角色则取角色下的人员加入到set中
				else if (StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(assign
						.getAssigntype())) {
					List rusers = this.tawSystemUserManager
							.getUsersBySubRoleid(assign.getObjectid());
					if (rusers != null) {
						users.addAll(rusers);
					}

				}
				// 右为部门则取部门下的人员加入到set中
				else if (StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(assign
						.getAssigntype())) {
					List dusers = this.tawSystemUserManager
							.getUserBydeptids(assign.getObjectid());
					if (dusers != null) {
						users.addAll(dusers);
					}
				}

			}
		}
		return users;
	}

}
