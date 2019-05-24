package com.boco.eoms.commons.system.user.service.bo;

import java.util.List;

import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;

public interface ITawSystemUserRefRoleBo {

	// /**
	// * 得到某角色的所有用户
	// *
	// * @param roleid
	// * @return
	// * @throws TawSystemUserException
	// */
	// public List getUseridbyroleid(String roleid) throws
	// TawSystemUserException;

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 * @throws TawSystemUserException
	 */
	public List getRoleidByuserids(String userid) throws TawSystemUserException;

	// public TawSystemUserRefRole getRoleidByuserids(String roleid, String
	// userid);

	/**
	 * 删除某角色的所有用户
	 * 
	 * @param roleid
	 * @throws TawSystemUserException
	 */
	public void removeUseridByroleid(String roleid)
			throws TawSystemUserException;

	/**
	 * 删除某用户的所有角色
	 * 
	 * @param userid
	 * @throws TawSystemUserException
	 */
	public void removeRoleidByUserid(String userid, String roleType)
			throws TawSystemUserException;

	/**
	 * 根据ID 删除记录
	 * 
	 * @param id
	 * @throws TawSystemUserException
	 */
	public void removeRolebyid(String id) throws TawSystemUserException;

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
	// throws TawSystemUserException;

	/**
	 * 查询属于某角色的所有用户信息
	 * 
	 * @param roleid
	 * @return
	 * @throws TawSystemUserException
	 */
	public List getUsersbyroleids(String roleid) throws TawSystemUserException;

	// /**
	// * 判断某角色的某用户是否存在
	// *
	// * @param userid
	// * @return
	// */
	// public boolean getRoleidByuserid(String roleid, String userid);

	// /**
	// * 得到某用户创建的所有角色
	// *
	// * @param userid
	// * @return
	// */
	// public List getRoleidByremark(String remark);

	// /**
	// * 更新某个角色的某个用户的信息
	// *
	// * @param roleid
	// * @param userid
	// * @throws TawSystemUserException
	// */
	// public boolean saveUpdate(String roleid, String userid,
	// TawSystemUser systemuser) throws TawSystemUserException;

	/**
	 * 保存角色
	 * 
	 * @param refrole
	 * @param sysuser
	 */
	public void saveUserandrole(TawSystemUserRefRole refrole);

	// /**
	// * 更行用户关联的角色表信息
	// *
	// * @param oldroleid
	// * @param newroleid
	// * @param userid
	// * @param username
	// * @param operuserid
	// * @throws TawSystemUserException
	// */
	// public boolean saveUpdaterole(String oldroleid, String userid,
	// String newroleid, String operuserid) throws TawSystemUserException;

	// /**
	// * 删除某用户的某角色
	// *
	// * @param userid
	// * @param roleid
	// * @throws TawSystemUserException
	// */
	// public void removeRolebyuseridandroleid(String userid, String roleid)
	// throws TawSystemUserException;

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getRoleByuserid(String userid, String roleType);

}
