package com.boco.eoms.commons.system.priv.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivAssignManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

/**
 * 处理权限分配和其它模块的逻辑业务
 * 
 * @author panlong May 31, 2007 10:14:12 AM
 */
public class TawSystemPrivAssignOut {

	private TawSystemPrivAssignOut() {

	}

	private static TawSystemPrivAssignOut instance = null;

	private static TawSystemPrivAssignOut init() {
		instance = new TawSystemPrivAssignOut();
		return instance;
	}

	public static TawSystemPrivAssignOut getInstance() {
		if (instance == null) {
			instance = init();
		}
		return instance;
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	public List getRoleLists() {
		List list = new ArrayList();
		ITawSystemRoleManager mgr = (ITawSystemRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPostManager");
		list = mgr.getTawSystemRoles();
		TawSystemRole role = null;
		List resultList = null;
		if (list != null) {
			if (list.size() > 0) {
				role = new TawSystemRole();
				resultList = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					role = null;
					role = (TawSystemRole) list.get(i);
					resultList.add(role);
				}
			}
		}
		return resultList;
	}

	/**
	 * 查询某角色下的所有角色信息
	 * 
	 * @param roleid
	 * @return
	 */
	public List getSonRoleLists(String roleid) {

		List list = new ArrayList();
		ITawSystemRoleManager mgr = (ITawSystemRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPostManager");
		list = mgr.getChildrenByRoleId(Long.valueOf(roleid).longValue());
		TawSystemRole role = null;
		List resultList = null;
		if (list != null) {
			if (list.size() > 0) {
				role = new TawSystemRole();
				resultList = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					role = null;
					role = (TawSystemRole) list.get(i);
					resultList.add(role);
				}
			}
		}
		return resultList;
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	public List getUserLists() {
		List list = new ArrayList();
		ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		TawSystemUser tawSystemUser = new TawSystemUser();
		list = mgr.getTawSystemUsers(tawSystemUser);
		List resultList = null;
		if (list != null) {
			if (list.size() > 0) {
				resultList = new ArrayList();

				for (int i = 0; i < list.size(); i++) {
					tawSystemUser = null;
					tawSystemUser = (TawSystemUser) list.get(i);
					resultList.add(tawSystemUser);
				}
			}
		}
		return resultList;
	}

	/**
	 * 部门列表
	 * 
	 * @return
	 */
	public List getDeptLists() {
		List list = new ArrayList();
		ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		TawSystemDept tawSystemDept = new TawSystemDept();
		List resultList = null;
		list = mgr.getTawSystemDepts(tawSystemDept);
		if (list != null) {
			if (list.size() > 0) {
				resultList = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					tawSystemDept = null;
					tawSystemDept = (TawSystemDept) list.get(i);
					resultList.add(tawSystemDept);
				}
			}
		}
		return resultList;
	}

	/**
	 * 得到某部门的下级部门
	 * 
	 * @return
	 */
	public List getSonDepts(String deptid) {
		List list = new ArrayList();
		ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		TawSystemDept tawSystemDept = null;
		List resultList = null;
		list = mgr.getNextLevecDepts(deptid, "0");
		if (list != null) {
			if (list.size() > 0) {
				resultList = new ArrayList();
				tawSystemDept = new TawSystemDept();
				for (int i = 0; i < list.size(); i++) {
					tawSystemDept = null;
					tawSystemDept = (TawSystemDept) list.get(i);
					resultList.add(tawSystemDept);
				}
			}
		}
		return resultList;
	}

	/**
	 * 取某部门下的所有用户
	 * 
	 * @param deptid
	 * @return
	 */
	public List getDeptUsers(String deptid) {
		List list = new ArrayList();
		ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		TawSystemUser tawSystemUser = null;
		list = mgr.getUserBydeptids(deptid);
		List resultList = null;
		if (list != null) {
			if (list.size() > 0) {
				resultList = new ArrayList();
				tawSystemUser = new TawSystemUser();
				for (int i = 0; i < list.size(); i++) {
					tawSystemUser = null;
					tawSystemUser = (TawSystemUser) list.get(i);
					resultList.add(tawSystemUser);
				}
			}
		}
		return resultList;
	}

	/**
	 * 查询某角色的所有用户
	 * 
	 * @param roleid
	 * @return
	 * @throws TawSystemUserException
	 */
	public List getRoleUsers(String roleid) throws TawSystemUserException {
		TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
		List list = new ArrayList();
		list = rolebo.getUsersbyroleids(roleid);
		List resultList = null;
		TawSystemUser sysuser = null;
		if (list != null) {
			if (list.size() > 0) {
				resultList = new ArrayList();
				sysuser = new TawSystemUser();
				for (int i = 0; i < list.size(); i++) {
					sysuser = null;
					sysuser = (TawSystemUser) list.get(i);
					resultList.add(sysuser);
				}
			}
		}
		return resultList;
	}

	/**
	 * 权限列表
	 * 
	 * @return
	 */
	public List getMenuLists() {
		List list = new ArrayList();
		ITawSystemPrivMenuItemManager menuitem = (ITawSystemPrivMenuItemManager) ApplicationContextHolder
				.getInstance().getBean("ITawSystemPrivMenuItemManager");
		list = menuitem.getAllMenus();
		TawSystemPrivMenuItem item = null;
		List resultlist = null;
		if (list != null) {

			if (list.size() > 0) {
				item = new TawSystemPrivMenuItem();
				resultlist = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					item = null;
					item = (TawSystemPrivMenuItem) list.get(i);
					resultlist.add(item);
				}
			}

		}
		return list;
	}

	/**
	 * 根据某菜单ID 查询菜单信息
	 * 
	 * @return
	 */
	public List getMenubyCodeids(String _strMenuCode) {
		List list = new ArrayList();
		ITawSystemPrivMenuItemManager menuitem = (ITawSystemPrivMenuItemManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivMenuItemManager");
		list = menuitem.getSpecMenuItems(_strMenuCode);
		TawSystemPrivUserAssign userassign = null;
		List resultList = null;
		if (list != null) {

			if (list.size() > 0) {
				// userassign = new TawSystemPrivUserAssign();
				resultList = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					userassign = (TawSystemPrivUserAssign) list.get(i);
					resultList.add(userassign);
				}

			}
		}
		return resultList;
	}

	/**
	 * 当更新一个权限的时候需要修改对应的用户权限记录 这里的处理方法是将用户本来拥有的权限先删除然后再进行权限合并
	 * 
	 * @param _strMenuCode
	 * @throws TawSystemUserException
	 */
	public void UpdateUserpriv(String _strMenuCode)
			throws TawSystemUserException {
		ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivAssignManager");
		TawSystemUserAssignBo userassignbo = TawSystemUserAssignBo
				.getInstance();

		List list = new ArrayList();
		list = assignmanager.getPrivassigninfos(_strMenuCode);
		List userprivlist = null;

		if (list != null && list.size() > 0) {
			TawSystemPrivAssign privassign = new TawSystemPrivAssign();
			userprivlist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {

				privassign = (TawSystemPrivAssign) list.get(i);
				if (privassign.getAssigntype().equals(
						StaticVariable.PRIV_ASSIGNTYPE_USER)) {
					userprivlist.add(privassign.getObjectid());
				}
			}
			if (userprivlist != null) {
				String userid = "";

				for (int j = 0; j < userprivlist.size(); j++) {
					userid = (String) userprivlist.get(j);
					userassignbo.removeUsermenu(userid);
					saveuserMenu(userid, _strMenuCode);
				}
			}
		}
	}

	/**
	 * 当删除一个权限方案的时候需要删除与这个权限相关用户权限
	 * 
	 * @param _strMenuCode
	 * @throws TawSystemUserException
	 */
	public void removeMenuid(String _strMenuCode) throws TawSystemUserException {
		ITawSystemPrivAssignManager assignmanager = (ITawSystemPrivAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivAssignManager");
		TawSystemUserAssignBo userassignbo = TawSystemUserAssignBo
				.getInstance();

		List list = new ArrayList();
		list = assignmanager.getPrivassigninfos(_strMenuCode);
		List userprivlist = null;

		if (list != null && list.size() > 0) {
			TawSystemPrivAssign privassign = new TawSystemPrivAssign();
			userprivlist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {

				privassign = (TawSystemPrivAssign) list.get(i);
				userprivlist.add(privassign.getObjectid());
				assignmanager.removeTawSystemPrivAssign(privassign.getId());
			}
			if (userprivlist != null) {
				String userid = "";

				for (int j = 0; j < userprivlist.size(); j++) {
					userid = (String) userprivlist.get(j);
					if (userid != null && userid != "") {
						userassignbo.removeUsermenu(userid);
						saveuserMenu(userid, _strMenuCode);
					}
				}
			}
		}
	}

	/**
	 * 删除一个角色的权限需要将其用户的权限全部删除
	 * 
	 * @param roleid
	 * @throws TawSystemUserException
	 */
	public void removeRolePriv(String roleid) throws TawSystemUserException {
		List list = new ArrayList();
		TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
		list = rolebo.getUsersbyroleids(roleid);
		TawSystemUser sysuser = new TawSystemUser();
		TawSystemUserAssignBo userassignbo = TawSystemUserAssignBo
				.getInstance();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String userid = "";
				sysuser = (TawSystemUser) list.get(i);
				userid = sysuser.getUserid();
				if (userid != null && userid != "") {
					userassignbo.removeUsermenu(userid);
				}
			}
		}
	}

	/**
	 * 保存合并用户权限
	 * 
	 * @param userid
	 * @param _strMenuCode
	 * @throws TawSystemUserException
	 */
	public void saveuserMenu(String userid, String _strMenuCode)
			throws TawSystemUserException {
		ITawSystemPrivUserAssignManager userAssignMgr = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List userassignlist = null;
		List userrolelist = new ArrayList();
		List menuid = new ArrayList();

		StringBuffer buffer = new StringBuffer();

		TawSystemPrivUserAssign userassign = null;
		String userrefrole = "";
		TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
		userrolelist = rolebo.getRoleidByuserid(userid);
		TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
		ITawSystemPrivUserAssignManager mgr = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");

		// 如果用户有对应的角色则先取角色对应的权限并合并
		if (userrolelist != null && userrolelist.size() > 0) {
			TawSystemPrivAssign privassign = new TawSystemPrivAssign();
			for (int i = 0; i < userrolelist.size(); i++) {

				userrefrole = (String) userrolelist.get(i);
				List roleprivlist = (ArrayList) assignbo
						.getObjectPriv(userrefrole);
				if (roleprivlist != null && roleprivlist.size() > 0) {
					for (int k = 0; k < roleprivlist.size(); k++) {
						privassign = (TawSystemPrivAssign) roleprivlist.get(k);
						menuid.add(privassign.getPrivid());// 根据角色ID查找对应的权限ID并保存到list里
					}
					menuid.add(_strMenuCode);
				}
			}

			if (menuid != null && menuid.size() > 0) {// 如果角色存在对应的权限则进行权限合并

				String menuids = "";
				for (int j = 0; j < menuid.size(); j++) {
					buffer.append("'");
					menuids = "";
					menuids = (String) menuid.get(j);
					buffer.append(menuids);
					buffer.append("'");
					if (j + 1 != menuid.size()) {
						buffer.append(",");
					}
				}

				userassignlist = new ArrayList();
				userassign = new TawSystemPrivUserAssign();

				userassignlist = getMenubyCodeids(buffer.toString());// 取合并权限并保存在list中

				for (int n = 0; n < userassignlist.size(); n++) {

					userassign = (TawSystemPrivUserAssign) userassignlist
							.get(n);
					userassign.setUserid(userid);
					// 新增判断，若已添加赋权信息，此时则不添加
					if (!userAssignMgr
							.isExistUserassign(userassign.getMenuid(),
									userassign.getCurrentprivid(), userassign
											.getUserid())) {
						userassign.setMenuid(_strMenuCode);
						userassign.setHrefTarget("mainFrame");
						userassign.setOrderby("100");
						mgr.saveTawSystemPrivUserAssign(userassign);
					}
				}

			} else {// 如果角色不存在对应的权限则将所选择的权限直接给用户不涉及合并
				buffer.append("'");
				buffer.append(_strMenuCode);
				buffer.append("'");
				userassignlist = new ArrayList();
				userassign = new TawSystemPrivUserAssign();
				userassignlist = getMenubyCodeids(buffer.toString());
				for (int n = 0; n < userassignlist.size(); n++) {
					userassign = (TawSystemPrivUserAssign) userassignlist
							.get(n);
					userassign.setUserid(userid);
					userassign.setHrefTarget("mainFrame");
					if (!userAssignMgr
							.isExistUserassign(userassign.getMenuid(),
									userassign.getCurrentprivid(), userassign
											.getUserid())) {
						userassign.setMenuid(_strMenuCode);
						userassign.setOrderby("100");
						mgr.saveTawSystemPrivUserAssign(userassign);
					}
				}
			}

		} else {// 如果用户没有对应的角色则直接将权限给用户
			buffer.append("'");
			buffer.append(_strMenuCode);
			buffer.append("'");
			userassignlist = new ArrayList();
			userassign = new TawSystemPrivUserAssign();
			userassignlist = getMenubyCodeids(buffer.toString());
			for (int n = 0; n < userassignlist.size(); n++) {
				userassign = (TawSystemPrivUserAssign) userassignlist.get(n);
				userassign.setUserid(userid);
				if (!userAssignMgr.isExistUserassign(userassign.getMenuid(),
						userassign.getCurrentprivid(), userassign.getUserid())) {
					userassign.setHrefTarget("mainFrame");
					userassign.setMenuid(_strMenuCode);
					userassign.setOrderby("100");
					mgr.saveTawSystemPrivUserAssign(userassign);
				}
			}
		}

	}

	/**
	 * 通过USERID查询某用户的所有权限
	 * 
	 * @param userid
	 * @return
	 */
	public List getUserAllPrivByuserid(String userid) {
		List userprivlist = new ArrayList();
		List userrolelist = new ArrayList();
		List menuid = new ArrayList();
		TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
		TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
		StringBuffer buffer = new StringBuffer();
		TawSystemPrivUserAssign userassign = null;
		try {
			userrolelist = rolebo.getRoleidByuserid(userid);// 查询用户对应的所有角色
			String userrefrole = "";
			if (userrolelist != null && userrolelist.size() > 0) {
				TawSystemPrivAssign privassign = new TawSystemPrivAssign();
				for (int i = 0; i < userrolelist.size(); i++) {

					userrefrole = (String) userrolelist.get(i);
					List roleprivlist = (ArrayList) assignbo
							.getObjectPriv(userrefrole);// 查询角色对应的所有权限

					if (roleprivlist != null && roleprivlist.size() > 0) {
						for (int k = 0; k < roleprivlist.size(); k++) {
							privassign = (TawSystemPrivAssign) roleprivlist
									.get(k);
							menuid.add(privassign.getPrivid());// 根据角色ID查找对应的权限ID并保存到list里
						}
					}
				}

				if (menuid != null && menuid.size() > 0) {// 如果角色存在对应的权限则进行权限合并

					String menuids = "";
					for (int j = 0; j < menuid.size(); j++) {
						buffer.append("'");
						menuids = "";
						menuids = (String) menuid.get(j);
						buffer.append(menuids);
						buffer.append("'");
						if (j + 1 != menuid.size()) {
							buffer.append(",");
						}
					}

					userassign = new TawSystemPrivUserAssign();

					userprivlist = getMenubyCodeids(buffer.toString());// 取合并权限并保存在list中
				}
			}

		} catch (TawSystemUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userprivlist;
	}

	/**
	 * 保存角色权限的时候需要将属于角色的所有用户权限进行修改
	 * 
	 * @param roleid
	 * @param _strMenuCode
	 * @throws TawSystemUserException
	 */
	public void saveRolemenu(String roleid, String _strMenuCode)
			throws TawSystemUserException {
		List list = new ArrayList();
		TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
		list = rolebo.getUsersbyroleids(roleid);
		TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
		List roleprivlist = assignbo.getObjectPriv(roleid);
		if (roleprivlist != null && roleprivlist.size() > 0) {
			removeRolePriv(roleid);
		}
		TawSystemUser sysuser = new TawSystemUser();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String userid = "";
				sysuser = (TawSystemUser) list.get(i);
				userid = sysuser.getUserid();
				if (userid != null && userid != "") {
					saveuserMenu(userid, _strMenuCode);
				}
			}
		}
	}

	/**
	 * 根据CODE取得对应的NAME
	 * 
	 * @param code
	 * @return
	 */
	public String getNameBycode(String code) {

		String codename = "";
		ITawSystemPrivOperationManager operationmanager = (ITawSystemPrivOperationManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivOperationManager");
		codename = operationmanager.getPrivOperationbyCode(code).getName();
		return codename;
	}
	
	public String getWholeNameByCode(String code) {
		String codeName = "";
		StringBuffer buffer = new StringBuffer();
		TawSystemPrivOperation tawSystemPrivOperation = null;
		List moduleList = new ArrayList();
		ITawSystemPrivOperationManager operationmanager = (ITawSystemPrivOperationManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemPrivOperationManager");
		moduleList = operationmanager.getAllFatherModules(code);
		int length = moduleList.size();
		for(int i=length-1; i>=0; i--) {
			tawSystemPrivOperation = (TawSystemPrivOperation)moduleList.get(i);
			buffer.append("<"+tawSystemPrivOperation.getName()+">");
			if(i!=0) {
				buffer.append("下");
			} else {
				buffer.append("操作。");
			}
		}
		codeName = buffer.toString();
		return codeName;
	}

	public boolean getIsleafbyCode(String code) {

		boolean flag = false;
		String isapp = "";
		ITawSystemPrivOperationManager operationmanager = (ITawSystemPrivOperationManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivOperationManager");
		TawSystemPrivOperation operation = operationmanager.getPrivOperationbyCode(code);
		if (null != operation.getId() && !"".equals(operation.getId())) {
			isapp = operationmanager.getPrivOperationbyCode(code).getIsApp();
			if (isapp.equals(StaticVariable.AREA_DEFAULT_LEAFONE)
					|| isapp.equals(StaticVariable.PRIV_TYPE_BUTTON)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 通过PRIVID查询NAME
	 * 
	 * @param privid
	 * @return
	 */
	public String getPrivNameByPrivid(String privid) {
		ITawSystemPrivMenuManager mgr = (ITawSystemPrivMenuManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivMenuManager");
		return (String) mgr.getTawSystemPrivMenu(privid).getName();

	}

}
