package com.boco.eoms.commons.system.user.service.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserRefRoleBo;

public class TawSystemUserRoleBo {

	private TawSystemUserRoleBo() {

	}

	private static TawSystemUserRoleBo instance = null;

	public static TawSystemUserRoleBo getInstance() {
		if (instance == null) {
			instance = init();
		}
		return instance;
	}

	private static TawSystemUserRoleBo init() {

		instance = new TawSystemUserRoleBo();

		return instance;
	}

	/**
	 * 得到部门的所有USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptids(String deptid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserBydeptids(deptid);

		return list;
	}

 
	/**
	 * 2008-12-2 wangsixuan 
	 * 得到部门的所有USER,只取是代维的USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidsPartner(String deptid){
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
		.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserBydeptidsPartner(deptid);
			return list;

	}
 
	

	/**
	 * 得到部门的所有USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidsNoSelf(String deptid,String userid) {
 
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserBydeptidsNoSelf(deptid,userid);

		return list;
	}
 
	/**
	 * 根据userid得到用户的信息
	 * 
	 * @param userid
	 * @return
	 */
	public TawSystemUser getUserByuserid(String userid) {

		TawSystemUser systemuser = new TawSystemUser();
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		systemuser = sysuserbo.getUserByuserid(userid);
		return systemuser;

	}

	/**
	 * 获取系统管理员的用户列表
	 * 
	 * @param userdegree
	 * @return
	 */
	public List getUserbyUserdegrees(String userdegree) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserbyUserdegrees(userdegree);

		return list;
	}

	/**
	 * 得到某机房的所有用户列表
	 * 
	 * @param cptid
	 * @return
	 */
	public List getUserbyCptids(String cptid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserbyCptids(cptid);

		return list;
	}

	/**
	 * 查询某用户添加的所有用户
	 * 
	 * @param operuserid
	 * @return
	 */
	public List getUserbyOperuserids(String operuserid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserbyOperuserids(operuserid);

		return list;
	}

	/**
	 * 根据性别查询用户
	 * 
	 * @param sexid
	 * @return
	 */
	public List getUserbysexs(String sexid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserbysexs(sexid);

		return list;
	}

	/**
	 * 取得某部门下的所有管理员的userid
	 * 
	 * @param deptid
	 * @param degreeid
	 * @return
	 */
	public List getUserbyDeptidandDegids(String deptid, String degreeid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserbyDeptidandDegids(deptid, degreeid);

		return list;
	}

	/**
	 * 取得某机房的所有管理员
	 * 
	 * @param cptid
	 * @param degid
	 * @return
	 */
	public List getUserBycptidAndDegid(String cptid, String degid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserBycptidAndDegids(cptid, degid);

		return list;
	}

	/**
	 * 删除某部门的所有用户
	 * 
	 * @param deptid
	 * @throws TawSystemUserException
	 */
	public void removeUserbydeptid(String deptid) throws TawSystemUserException {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		sysuserbo.removeUserbydeptid(deptid);
	}

	/**
	 * 删除某机房的所有用户
	 * 
	 * @param cptid
	 * @throws TawSystemUserException
	 */
	public void removeUserbycptid(String cptid) throws TawSystemUserException {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		sysuserbo.removeUserbycptid(cptid);
	}

	/**
	 * 修改某用户信息
	 * 
	 * @param userid
	 * @throws TawSystemUserException
	 */
	public void saveOrUpdateuser(String userid, TawSystemUser systemuser)
			throws TawSystemUserException {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		sysuserbo.saveOrUpdateuser(userid, systemuser);
	}

	/**
	 * 根据部门id 和子部门id 查询用户
	 * 
	 * @param deptid
	 * @param sondeptid
	 * @return
	 */
	public List getUserbydeptidanson(String deptids) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getUserbydeptidanson(deptids);

		return list;
	}

	/**
	 * 从关联表得到属于某角色的所有用户id 用户NAME
	 * 
	 * @param roleid
	 * @return
	 * @throws TawSystemUserException
	 */

	public List getUseridbyroleid(String roleid) throws TawSystemUserException {

		ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserRefRoleBo");
		List list = new ArrayList();
		list = refrolebo.getUsersbyroleids(roleid);

		return list;

	}

	/**
	 * 查询属于某角色的所有用户信息
	 * 
	 * @param roleid
	 * @return
	 * @throws TawSystemUserException
	 */
	public List getUsersbyroleids(String roleid) throws TawSystemUserException {

		ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserRefRoleBo");
		List list = new ArrayList();
		list = refrolebo.getUsersbyroleids(roleid);

		return list;

	}

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return TawSystemSubPost
	 * @throws TawSystemUserException
	 */
	public List getRoleidByuserid(String userid) throws TawSystemUserException {

		ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserRefRoleBo");
		List list = new ArrayList();
		list = refrolebo.getRoleidByuserids(userid);

		return list;
	}

	/**
	 * 删除某角色的所有用户
	 * 
	 * @param roleid
	 * @throws TawSystemUserException
	 */
	public void removeUseridByroleid(String roleid)
			throws TawSystemUserException {

		ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserRefRoleBo");
		refrolebo.removeUseridByroleid(roleid);

	}

	/**
	 * 删除某用户的所有角色
	 * 
	 * @param userid
	 * @throws TawSystemUserException
	 */
	public void removeRoleidByUserid(String userid, String roleType)
			throws TawSystemUserException {

		ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserRefRoleBo");
		refrolebo.removeRoleidByUserid(userid, roleType);
	}

	/**
	 * 根据ID 删除记录
	 * 
	 * @param id
	 * @throws TawSystemUserException
	 */
	public void removeRolebyid(String id) throws TawSystemUserException {

		ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserRefRoleBo");
		refrolebo.removeRolebyid(id);
	}

	// /**
	// * 查询某角色的某用户的信息
	// *
	// * @param roleid
	// * @param userid
	// * @return
	// * @throws TawSystemUserException
	// */
	// public void TawSystemUser getUserbyroleidanduserids(String roleid,
	// String userid) throws TawSystemUserException {
	// TawSystemUser systemuser = new TawSystemUser();
	// ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo)
	// ApplicationContextHolder
	// .getInstance().getBean("iTawSystemUserRefRoleBo");
	// systemuser = refrolebo.getUserbyroleidanduserids(roleid, userid);
	//
	// return systemuser;
	// }

	// /**
	// * 判断某角色的某用户是否存在
	// *
	// * @param userid
	// * @return
	// */
	// public boolean getRoleidByuserid(String roleid, String userid) {
	// boolean flag;
	// ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo)
	// ApplicationContextHolder
	// .getInstance().getBean("iTawSystemUserRefRoleBo");
	//
	// flag = refrolebo.getRoleidByuserid(roleid, userid);
	// return flag;
	// }

	// /**
	// * 更新某个角色的某个用户的信息
	// *
	// * @param roleid
	// * @param userid
	// * @throws TawSystemUserException
	// */
	// public boolean saveUpdateroleTouser(String roleid, String userid,
	// TawSystemUser systemuser) throws TawSystemUserException {
	//
	// ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo)
	// ApplicationContextHolder
	// .getInstance().getBean("iTawSystemUserRefRoleBo");
	// boolean flag = refrolebo.saveUpdate(roleid, userid, systemuser);
	// return flag;
	// }
	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getRoleByuserid(String userid, String roleType) {
		ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserRefRoleBo");
		return (ArrayList) refrolebo.getRoleByuserid(userid, roleType);
	}

	/**
	 * 创建一个用户并分配角色
	 * 
	 * @param refrole
	 * @param sysuser
	 */
	public void saveUserandrole(String roleid, TawSystemUser sysuser) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");

		sysuserbo.saveUser(sysuser);
		if (roleid != null && !roleid.equals("")) {
			TawSystemUserRefRole refrole = new TawSystemUserRefRole();

			ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
					.getInstance().getBean("iTawSystemUserRefRoleBo");
			refrole.setRoleid(roleid);
			refrole.setUserid(sysuser.getUserid());
			refrole.setRemark(sysuser.getOperuserid());
			refrole.setCurrentsheetcount(new Integer(0));
			refrole.setStatus("0");
			refrolebo.saveUserandrole(refrole);
		} else {
			BocoLog.info(TawSystemUserRoleBo.class, "角色ID 为空 用户成功创建但没有分配角色");
		}

	}

	/**
	 * 保存角色信息
	 * 
	 * @param roleid
	 * @param userid
	 * @param operuserid
	 */
	public void saveRole(String roleid, String userid, String status,
			String orgid, String operuserid, Integer currentsheetcount) {

		TawSystemUserRefRole refrole = new TawSystemUserRefRole();

		ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserRefRoleBo");
		refrole.setRoleid(roleid);
		refrole.setUserid(userid);
		refrole.setRemark(operuserid);
		refrole.setStatus(status);
		refrole.setCurrentsheetcount(currentsheetcount);
		refrolebo.saveUserandrole(refrole);

	}

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
	// boolean flag = true;
	//
	// ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo)
	// ApplicationContextHolder
	// .getInstance().getBean("iTawSystemUserRefRoleBo");
	// try {
	//
	// refrolebo.saveUpdaterole(oldroleid, userid, newroleid, operuserid);
	//
	// } catch (Exception ex) {
	//
	// flag = false;
	//
	// }
	// return flag;
	//
	// }

	// /**
	// * 得到某用户创建的所有角色
	// *
	// * @param userid
	// * @return
	// */
	// public List getRoleidByremark(String operuserid) {
	// ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo)
	// ApplicationContextHolder
	// .getInstance().getBean("iTawSystemUserRefRoleBo");
	// List list = new ArrayList();
	//
	// list = refrolebo.getRoleidByremark(operuserid);
	// return list;
	// }

	// /**
	// * 删除某用户的某角色
	// *
	// * @param userid
	// * @param roleid
	// * @throws TawSystemUserException
	// */
	// public void void removeRolebyuseridandroleid(String userid, String
	// roleid)
	// throws TawSystemUserException {
	//
	// ITawSystemUserRefRoleBo refrolebo = (ITawSystemUserRefRoleBo)
	// ApplicationContextHolder
	// .getInstance().getBean("iTawSystemUserRefRoleBo");
	// refrolebo.removeRolebyuseridandroleid(userid, roleid);
	//
	// }

	/**
	 * 删除某用户信息
	 * 
	 * @param userid
	 */
	public void removeUserbyuserid(String userid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		sysuserbo.removeUserbyuserid(userid);

	}

	/**
	 * 得到属于某角色的EMAIL
	 * 
	 * @param roleid
	 * @return
	 * @throws TawSystemUserException
	 */
	public List getAllEmailbyroleid(String roleid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");

		List useridlist = new ArrayList();
		List emalilist = new ArrayList();
		StringBuffer sbu = new StringBuffer();
		try {
			useridlist = getUseridbyroleid(roleid);
			if (useridlist != null) {

				for (int i = 0; i < useridlist.size(); i++) {
					TawSystemUser user = (TawSystemUser) useridlist.get(i);
					String str = "";
					str = user.getUserid();
					sbu.append(str);
					if (i + 1 != useridlist.size()) {
						sbu.append(",");
					}
				}

			}
			String userids = sbu.toString();
			emalilist = sysuserbo.getAllEmailbyuserids(userids);
		} catch (Exception ex) {
			BocoLog.error(TawSystemUserRoleBo.class, "查询属于角色 " + roleid
					+ " 的所有用户EMAIL出错 " + ex.getMessage());
		}
		return emalilist;
	}

	/**
	 * 得到属于某角色的手机号
	 * 
	 * @param roleid
	 * @return
	 */
	public List getAllMobilebyroleid(String roleid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");

		List useridlist = new ArrayList();
		List mobilelist = new ArrayList();
		StringBuffer sbu = new StringBuffer();
		try {
			useridlist = getUseridbyroleid(roleid);
			if (useridlist != null) {
				for (int i = 0; i < useridlist.size(); i++) {
					TawSystemUser user = (TawSystemUser) useridlist.get(i);
					String str = "";
					str = user.getUserid();
					sbu.append(str);
					if (i + 1 != useridlist.size()) {
						sbu.append(",");
					}
				}

			}
			String userids = sbu.toString();
			mobilelist = sysuserbo.getAllEmailbyuserids(userids);
		} catch (Exception ex) {
			BocoLog.error(TawSystemUserRoleBo.class, "查询属于角色 " + roleid
					+ " 的所有用户手机号出错 " + ex.getMessage());
		}
		return mobilelist;
	}

	/**
	 * 查询某部门所有的EMAIL
	 * 
	 * @return
	 * 
	 * @return
	 */
	public List getAllEmalibyDeptid(String deptid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getAllEmalibyDeptid(deptid);
		return list;
	}

	/**
	 * 查询某部门所有的手机号
	 * 
	 * @return
	 */
	public List getAllMobileBydeptid(String deptid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getAllMobileBydeptid(deptid);
		return list;
	}

	/**
	 * 查询某机房的所有EMAIL
	 * 
	 * @return
	 */
	public List getAllEmailbyCptid(String cptid) {
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getAllEmailbyCptid(cptid);
		return list;

	}

	/**
	 * 查询某机房的所有手机号
	 * 
	 * @return
	 */
	public List getAllMobilebyCptid(String cptid) {

		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List list = new ArrayList();
		list = sysuserbo.getAllMobilebyCptid(cptid);
		return list;
	}

	/**
	 * 得到正在休假的用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getRestByUserid(String userid) {
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		return sysuserbo.getRestByUserid(userid);
	}

	/**
	 * 得到未休假的用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getNoRestByUserid(String userid) {
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		return sysuserbo.getNoRestByUserid(userid);
	}

	/**
	 * 判断某用户是否正在休假
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isRestbyUserid(String userid) {
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		return sysuserbo.isRestbyUserid(userid);
	}

	/**
	 * 判断某用户是否是全职
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isFullemploybyUserid(String userid) {
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		return sysuserbo.isFullemploybyUserid(userid);
	}

	/**
	 * 查询所有全职用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getFullemploybyUserid(String userid) {
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		return sysuserbo.getFullemploybyUserid(userid);
	}

	/**
	 * 查询所有兼职用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getPartemployBuUserid(String userid) {
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		return sysuserbo.getPartemployBuUserid(userid);
	}

	public String getUsernameByUserid(String userid) {
		return (String) getUserByuserid(userid).getUsername();
	}

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getUserRefRoleByuserid(String userid) {
		ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		return mgr.getUserRefRoleByuserid(userid);
	}

	/**
	 * 根据角色ID和部门ID 取出这个角色下的用户 属于这个部门的所有用户
	 * 
	 * @param roleid
	 * @param deptid
	 * @return List<String> userid
	 */
	public List getUserByRoleidAndDeptid(String roleid, String deptid) {
		ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
				.getInstance().getBean("iTawSystemUserBo");
		List userlist = mgr.getUserbyRoleids(roleid);
		List rlist = new ArrayList();
		StringBuffer buffer = new StringBuffer();
		if (userlist != null && userlist.size() > 0) {

			for (int i = 1; i <= userlist.size(); i++) {
				buffer.append("'");
				TawSystemUserRefRole sysuser = new TawSystemUserRefRole();
				buffer.append(sysuser.getUserid());
				buffer.append("'");
				if (i != userlist.size()) {
					buffer.append(",");
				}
			}
		}
		rlist = sysuserbo.getUserBydeptidAndUserids(buffer.toString(), deptid);
		return rlist;
	}
}
