package com.boco.eoms.commons.system.user.service.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserRefRoleBo;

public class TawSystemUserRefRoleBoImpl implements ITawSystemUserRefRoleBo {

	ITawSystemUserRefRoleManager rolemanager;

	ITawSystemUserBo userbo;

	public ITawSystemUserRefRoleManager getRolemanager() {
		return rolemanager;
	}

	public void setRolemanager(ITawSystemUserRefRoleManager rolemanager) {
		this.rolemanager = rolemanager;
	}

	public ITawSystemUserBo getUserbo() {
		return userbo;
	}

	public void setUserbo(ITawSystemUserBo userbo) {
		this.userbo = userbo;
	}

	// /**
	// * 得到某角色的所有用户
	// *
	// * @param roleid
	// * @return
	// * @throws TawSystemUserException
	// */
	//
	// public List getUseridbyroleid(String roleid) throws
	// TawSystemUserException {
	//
	// List list = new ArrayList();
	// try {
	// list = rolemanager.getUseridbyroleid(roleid);
	// } catch (Exception ex) {
	// throw new TawSystemUserException("查找的角色 " + roleid + " 不存在!");
	// }
	// return list;
	// }

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return TawSystemSubPost
	 * @throws TawSystemUserException
	 */
	public List getRoleidByuserids(String userid) {
		List roleidlist = new ArrayList();
		roleidlist = rolemanager.getRoleidByuserid(userid);
		return roleidlist;
	}

	/**
	 * 删除某角色的所有用户
	 * 
	 * @param roleid
	 * @throws TawSystemUserException
	 */
	public void removeUseridByroleid(String roleid)
			throws TawSystemUserException {

		try {

			rolemanager.removeUseridByroleid(roleid);

		} catch (Exception ex) {
			throw new TawSystemUserException("删除的角色 " + roleid + " 不存在!");
		}

	}

	/**
	 * 删除某用户的所有角色
	 * 
	 * @param userid
	 * @throws TawSystemUserException
	 */
	public void removeRoleidByUserid(String userid, String roleType)
			throws TawSystemUserException {

		try {

			rolemanager.removeRoleidByUserid(userid, roleType);

		} catch (Exception ex) {
			throw new TawSystemUserException("删除的用户 " + userid + " 不存在!");
		}

	}

	// /**
	// * 判断某角色的某用户是否存在
	// *
	// * @param userid
	// * @return
	// */
	// public boolean getRoleidByuserid(String roleid, String userid) {
	//
	// boolean falg = rolemanager.getRoleidByuserid(roleid, userid);
	//
	// return falg;
	//
	// }

	/**
	 * 根据ID 删除记录
	 * 
	 * @param id
	 * @throws TawSystemUserException
	 */
	public void removeRolebyid(String id) throws TawSystemUserException {

		try {

			rolemanager.removeTawSystemUserRefRole(id);

		} catch (Exception ex) {
			throw new TawSystemUserException("删除记录 " + id + " 不存在!");
		}
	}

	// /**
	// * 查询某角色的某用户的信息
	// *
	// * @param roleid
	// * @param userid
	// * @return
	// * @throws TawSystemUserException
	// */
	// public TawSystemUser getUserbyroleidanduserids(String roleid, String
	// userid)
	// throws TawSystemUserException {
	//
	// TawSystemUser user = new TawSystemUser();
	//
	// try {
	//
	// if (getRoleidByuserid(roleid, userid)) {
	//
	// user = userbo.getUserByuserid(userid);
	// }
	//
	// } catch (Exception ex) {
	// throw new TawSystemUserException(" 角色" + roleid + " 的" + userid
	// + " 不存在!");
	// }
	//
	// return user;
	// }

	/**
	 * 查询属于某角色的所有用户信息
	 * 
	 * @param roleid
	 * @return
	 * @throws TawSystemUserException
	 */
	public List getUsersbyroleids(String roleid) throws TawSystemUserException {
		return rolemanager.getUserbyroleid(roleid);
	}

	// /**
	// * 更新某个角色的某个用户的信息
	// *
	// * @param roleid
	// * @param userid
	// * @throws TawSystemUserException
	// */
	// public boolean saveUpdate(String roleid, String userid,
	// TawSystemUser systemuser) throws TawSystemUserException {
	//
	// boolean flag = true;
	// try {
	// if (getRoleidByuserid(roleid, userid)) {
	//
	// userbo.saveOrUpdateuser(userid, systemuser);
	// }
	// } catch (Exception ex) {
	// flag = false;
	// throw new TawSystemUserException(" 角色" + roleid + " 的" + userid
	// + " 不存在!");
	// }
	// return flag;
	// }

	/**
	 * 保存用户信息
	 * 
	 * @param refrole
	 * @param sysuser
	 */
	public void saveUserandrole(TawSystemUserRefRole refrole) {

		rolemanager.saveTawSystemUserRefRole(refrole);
	}

	// /**
	// * 得到某用户创建的所有角色
	// *
	// * @param userid
	// * @return
	// */
	// public List getRoleidByremark(String remark) {
	//
	// List list = new ArrayList();
	//
	// list = rolemanager.getRoleidByremark(remark);
	//
	// return list;
	// }

	// /**
	// * 更新用户关联的角色表信息
	// *
	// * @param oldroleid
	// * @param newroleid
	// * @param userid
	// * @param username
	// * @param operuserid
	// * @throws TawSystemUserException
	// */
	// public boolean saveUpdaterole(String oldroleid, String userid,
	// String newroleid, String operuserid) throws TawSystemUserException {
	//
	// boolean flag = true;
	// try {
	//
	// TawSystemUserRefRole refrole = new TawSystemUserRefRole();
	// refrole = rolemanager.getRoleidByuserids(oldroleid, userid);
	// refrole.setRoleid(newroleid);
	// refrole.setRemark(operuserid);
	// refrole.setRoleid(newroleid);
	//
	// rolemanager.saveTawSystemUserRefRole(refrole);
	// } catch (Exception ex) {
	// flag = false;
	// throw new TawSystemUserException(" 角色" + oldroleid + " 的" + userid
	// + " 不存在!");
	// }
	// return flag;
	// }

	// /**
	// * 删除某用户的某角色
	// *
	// * @param userid
	// * @param roleid
	// * @throws TawSystemUserException
	// */
	// public void removeRolebyuseridandroleid(String userid, String roleid)
	// throws TawSystemUserException {
	// try {
	//
	// TawSystemUserRefRole refrole = new TawSystemUserRefRole();
	// refrole = rolemanager.getRoleidByuserids(roleid, userid);
	//
	// rolemanager.removeTawSystemUserRefRole(refrole.getId());
	// } catch (Exception ex) {
	//
	// throw new TawSystemUserException(" 角色" + roleid + " 的" + userid
	// + " 不存在!");
	// }
	//
	// }
	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getRoleByuserid(String userid, String roleType) {
		return rolemanager.getSubRoleByUserId(userid, roleType);
	}

}
