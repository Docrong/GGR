package com.boco.eoms.commons.system.user.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;

public interface TawSystemUserRefRoleDao extends Dao {

	/**
	 * Retrieves all of the tawSystemUserRefRoles
	 */
	public List getTawSystemUserRefRoles(
			TawSystemUserRefRole tawSystemUserRefRole);

	/**
	 * Gets tawSystemUserRefRole's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
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

	public List getRoleidByuserid(String userid);

	/**
	 * 删除某用户的所有角色
	 * 
	 * @param userid
	 */
	public void removeRoleidByUserid(String userid, String roleType);

	/**
	 * 得到某子角色的所有用户
	 * 
	 * @param roleid
	 * @return Map(TawSystemUser,groupType)
	 */
	public Map getGroupUserbySubRoleid(String roleid);

	/**
	 * 得到某子角色的所有用户
	 * 
	 * @param roleid
	 * @return TawSystemUser
	 */
	public List getUserbySubRoleid(String roleid);

	/**
	 * 得到某角色的所有用户
	 * 
	 * @param roleid
	 * @return TawSystemUserRefRole
	 */
	public List getUserRefRoleBySubRoleid(String subRoleid);

	/**
	 * 得到某子角色,某在线状态的所有用户
	 * 
	 * @param roleid
	 * @return
	 */
	public List getUserbySubRoleidUserstatus(String roleid, String userstatus);

	/**
	 * 得到某用户的所有子角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getSubRoleByuserid(String userid, String roleType);

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

	public boolean isExist(String subRoleid, String userId);

	public void removeUserid(String userid, String subRolei);

	/**
	 * 得到某角色的当前用户信息
	 * 
	 * @param subRoleid
	 * @param userId
	 * @return
	 * @throws HibernateException
	 */
	public TawSystemUserRefRole getUserRefRoleByUserid(String subRoleid,
			final String userId) throws HibernateException;

	/**
	 * 获取当前子角色的组长
	 * 
	 * @param subRoleid
	 * @return
	 * @throws HibernateException
	 */
	public TawSystemUserRefRole getRoleLeaderBySubRoleid(String subRoleid)
			throws HibernateException;

	public List getRoleLeaderlistBySubRoleid(String subRoleid)
	throws HibernateException;
	
	/**
	 * 通过version（版本）删除
	 * 
	 * @param version
	 *            版本
	 */
	public void removeTawSystemUserRefRoleByVersion(String version);

	/**
	 * 获取多人的所有子角色
	 * 
	 * @param userId
	 *            用户ID数组
	 * @param roleType
	 *            子角色类型， RoleConstants.flowRole=流程角色，
	 *            RoleConstants.systemRole=系统角色， RoleConstants.ALL_ROLE=全部角色
	 * @param delFlag
	 *            删除标记位,Constants.DELETED_FLAG=删除,Constants.NOT_DELETED_FLAG=未删除
	 * @return 某人员对应类型子角色集合
	 */
	public List getSubrolesByUserId(String userIds[], String roleType,
			String delFlag);

	/**
	 * 得到某用户的所有角色 by leo
	 * 
	 * @param userid
	 *            用户id
	 * @return
	 */
	public List getRoleByUserId(String userId);
	public String getRoleTypeBySubRoleId(String subRoleId);
	
	public List getSubRoleByCondition(String condition);

}
