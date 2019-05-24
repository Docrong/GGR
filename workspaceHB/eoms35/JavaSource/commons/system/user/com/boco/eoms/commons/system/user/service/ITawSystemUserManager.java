package com.boco.eoms.commons.system.user.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

public interface ITawSystemUserManager extends Manager {
	/**
	 * Retrieves all of the tawSystemUsers
	 */
	public List getTawSystemUsers(TawSystemUser tawSystemUser);

	/**
	 * Gets tawSystemUser's information based on id.
	 * 
	 * @param id
	 *            the tawSystemUser's id
	 * @return tawSystemUser populated tawSystemUser object
	 */
	public TawSystemUser getTawSystemUser(final String id);
	/**
	 * Saves a tawSystemUser's information
	 * 
	 * @param tawSystemUser
	 *            the object to be saved
	 */
	public void saveTawSystemUser(TawSystemUser tawSystemUser, String olduseridy);

	/**
	 * Removes a tawSystemUser from the database by id
	 * 
	 * @param id
	 *            the tawSystemUser's id
	 */
	public void removeTawSystemUser(final String id);
	
	
	/**
	 * 回收站用户恢复
	 * 
	 * @return List
	 */
	public void resumeTawSystemUser(final String id);
	
	/**
	 * 得到部门的所有USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptids(String deptid);
 
	
	/**
	 * 得到部门的所有USER,不包括用户自己
	 * 
	 * @param deptid
	 * @param userid
	 * @return
	 */
	public List getUserBydeptidsNoSelf(String deptid,String userid);

 
	/**
	 * 2008-12-2 wangsixuan 
	 * 得到部门的所有USER,只取是代维的USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidsPartner(String deptid);
	/**
	 * 根据userid得到用户的信息
	 * 
	 * @param userid
	 * @return
	 */
	public TawSystemUser getUserByuserid(String userid);
	
	/**
	 * 根据userid得到所有用户的信息（包括已删除，查询不到返回为空）
	 * 
	 * @param userid
	 * @return
	 */
	public TawSystemUser getTawSystemUserByuserid(String userid);

	/**
	 * 根据名称得到用户列表
	 * 
	 * @param userName
	 * @return List
	 */
	public List getUsersByName(String userName);
	public List getUsersDelByName(String userName);
	/**
	 * 根据名称，部门得到用户列表
	 * 
	 * @param userName
	 * @return List
	 */
	public List getUsersByName(String userName,String deptName);
	/**
	 * 用户删除回收站
	 * 
	 * @return List
	 */
	public List getUsersByDeleted();
	/**
	 * 根据名称得到用户列表
	 * 
	 * @param 用户名，地市、部门、电话、邮箱
	 * @return List
	 */
	public List getUsersByInfo(String userName,String cptroomname,String deptname,String mobile,String email);

	/**
	 * 根据userId判断用户是否存在
	 * 
	 * @param userId
	 *            用户id
	 * @return 存在返回true，反则返回false
	 */
	public boolean isUserExist(String userId);

	/**
	 * 获取系统管理员的用户列表
	 * 
	 * @param userdegree
	 * @return
	 */
	public List getUserbyUserdegrees(String userdegree);

	/**
	 * 得到某机房的所有用户列表
	 * 
	 * @param cptid
	 * @return
	 */
	public List getUserbyCptids(String cptid);

	/**
	 * 查询某用户添加的所有用户
	 * 
	 * @param operuserid
	 * @return
	 */
	public List getUserbyOperuserids(String operuserid);

	/**
	 * 根据性别查询用户
	 * 
	 * @param sexid
	 * @return
	 */
	public List getUserbysexs(String sexid);

	/**
	 * 取得某部门下的所有管理员的userid
	 * 
	 * @param deptid
	 * @param degreeid
	 * @return
	 */
	public List getUserbyDeptidandDegids(String deptid, String degreeid);

	/**
	 * 取得某机房的所有管理员
	 * 
	 * @param cptid
	 * @param degid
	 * @return
	 */
	public List getUserBycptidAndDegid(String cptid, String degid);

	/**
	 * 删除某部门的所有用户
	 * 
	 * @param deptid
	 */
	public void removeUserbydeptid(String deptid);

	/**
	 * 删除某机房的所有用户
	 * 
	 * @param cptid
	 */
	public void removeUserbycptid(String cptid);

	/**
	 * 根据部门id 和子部门id 查询用户
	 * 
	 * @param deptid
	 * @param sondeptid
	 * @return
	 */
	public List getUserbydeptidanson(String deptids);

	/**
	 * 查询某部门所有的EMAIL
	 * 
	 * @return
	 */
	public List getAllEmalibyDeptid(String deptid);

	/**
	 * 查询某部门所有的手机号
	 * 
	 * @return
	 */
	public List getAllMobileBydeptid(String deptid);

	/**
	 * 查询某机房的所有EMAIL
	 * 
	 * @return
	 */
	public List getAllEmailbyCptid(String cptid);

	/**
	 * 查询某机房的所有手机号
	 * 
	 * @return
	 */
	public List getAllMobilebyCptid(String cptid);

	/**
	 * 得到某些用户的EMAIL in查询
	 * 
	 * @param userids
	 * @return
	 */
	public List getAllEmailbyuserids(String userids);

	/**
	 * 得到某些用户的手机号 in查询
	 * 
	 * @param userids
	 * @return
	 */
	public List getAllMobileByuerids(String userids);

	/**
	 * 查询未被删除的用户
	 * 
	 * @return
	 */
	public List getNoDelUser();

	/**
	 * 得到正在休假的用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getRestByUserid(String userid);

	/**
	 * 得到未休假的用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getNoRestByUserid(String userid);

	/**
	 * 判断某用户是否正在休假
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isRestbyUserid(String userid);

	/**
	 * 判断某用户是否是全职
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isFullemploybyUserid(String userid);

	/**
	 * 查询所有全职用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getFullemploybyUserid(String userid);

	/**
	 * 查询所有兼职用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getPartemployBuUserid(String userid);

	/*
	 * id2name，即用户id转为用户名称 added by leo
	 * 
	 * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
	 */
	public String id2Name(final String id) throws DictDAOException;

	/**
	 * 删除用户的时候需要熟悉缓存
	 * 
	 * @param sysuser
	 * @return
	 */
	public void removeTawSystemUserandcatch(String id, TawSystemUser sysuser);

	/**
	 * 如果被删除用户已经有权限则删除用户的同时需要删除用户的权限
	 * 
	 * @param id
	 * @param userid
	 */
	public void removeTawSystemUser(final String id, final String userid);

	// /**
	// * 查询某状态的所有用户
	// * @param userstatus
	// * @return
	// */
	// public List getUserByUserstatus(String userstatus);
	//
	// /**
	// * 设置用户状态
	// * @param userid
	// * @param userstatus
	// * @return
	// */
	// public void setUserstatus(TawSystemUser tawSystemUser,String userstatus);

	/**
	 * 通过一些用户 查询这些用户中属于某部门的用户
	 * 
	 * @param userids
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidAndUserids(String userids, String deptid);

	/**
	 * 通过用户ID获得用户手机号码
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户手机号码
	 */
	public String getMobilesByUserId(String userId);

	/**
	 * 验证用户密码是否为数字+字母组合并不少于6位
	 * 
	 * @param passwd
	 *            用户密码
	 * @return 验证通过否
	 */
	public boolean checkPasswd(String passwd);

	/**
	 * 修改系统用户
	 * 
	 * @param tawSystemUser
	 */
	public void saveTawSystemUser(TawSystemUser tawSystemUser);

	/**
	 * 查询部门下所有用户的userid
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserIdsBydeptid(String deptid);

	/**
	 * 验证用户id是否合法
	 * 
	 * @param userId
	 *            用户id
	 * @return 返回用户id合法否
	 */
	public boolean checkUserId(String userId);

	/**
	 * 通过deptId（部门ID）取其下的所有用户（包括其部门下的部门中的用户）
	 * 
	 * @param deptId
	 *            部门id
	 * @return 部门下（包括其部门下的部门中的用户）的所有用户id
	 */
	public List listUsersByDeptId(String deptId);

	/**
	 * 通过deptId（部门ID）取其下的所有用户（包括其部门下的部门中的用户）
	 * 
	 * @param deptId
	 *            部门id
	 * @return 部门下（包括其部门下的部门中的用户）的所有用户id
	 */
	public String updatePassword(TawSystemUser tawSystemUser, String password);

	public boolean getUser(String userId, String password);

	/**
	 * 通过子角色id取用户
	 * 
	 * @param subRoleId
	 *            子角色
	 * @return 子角色的用户列表
	 */
	public List getUsersBySubRoleid(String subRoleId);
	
	/**
	 * 导入用户
	 * @param excelpath
	 * @return
	 */
	public boolean importUser(String excelpath);

	public List getUserBydeptidAndSubs(String _deptId);
	public List getUserLike(String deptid);
	public List getUserLikePartner(String deptid);
	
	/**
	 * 根据用户名\手机号和密码校验
	 * @param userParam
	 * @param password
	 * @return
	 */
	public String getUserByUserIdOrMobile (String userParam, String password);
	
	/**
	 * 根据手机号获取user对象
	 * @param mobile
	 * @return
	 */
	public List getUserByMobile (String mobile);
	
	/**
	 * 根据手机号和userid获取user对象
	 * @param mobile
	 * @return
	 */
	public List getUserByIdMobile (String name);
}
