package com.boco.eoms.commons.system.user.service.bo;

import java.util.List;

import com.boco.eoms.commons.system.user.exception.TawSystemUserException;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

public interface ITawSystemUserBo {

	/**
	 * 得到部门的所有USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptids(String deptid);

 
	/**
	 * 2008-12-2 wangsixuan 
	 * 得到部门的所有USER,只取是代维的USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidsPartner(String deptid);
 
	
	/**
	 * 得到部门的所有USER不包括用户自己
	 * 
	 * @param deptid
	 * @param userid
	 * @return
	 */
	public List getUserBydeptidsNoSelf(String deptid,String userid);
 


	/**
	 * 根据userid得到用户的信息
	 * 
	 * @param userid
	 * @return
	 */
	public TawSystemUser getUserByuserid(String userid);

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
	public List getUserBycptidAndDegids(String cptid, String degid);

	/**
	 * 删除某部门的所有用户
	 * 
	 * @param deptid
	 * @throws TawSystemUserException
	 */
	public void removeUserbydeptid(String deptid) throws TawSystemUserException;

	/**
	 * 删除某机房的所有用户
	 * 
	 * @param cptid
	 * @throws TawSystemUserException
	 */
	public void removeUserbycptid(String cptid) throws TawSystemUserException;

	/**
	 * 修改某用户信息
	 * 
	 * @param userid
	 * @throws TawSystemUserException
	 */
	public void saveOrUpdateuser(String userid, TawSystemUser systemuser)
			throws TawSystemUserException;

	/**
	 * 根据部门id 和子部门id 查询用户
	 * 
	 * @param deptid
	 * @param sondeptid
	 * @return
	 */
	public List getUserbydeptidanson(String deptids);

	/**
	 * 保存用户信息
	 * 
	 * @param systemuser
	 */
	public void saveUser(TawSystemUser systemuser);

	/**
	 * 删除某用户信息
	 * 
	 * @param userid
	 */
	public void removeUserbyuserid(String userid);

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

	/**
	 * 通过一些用户 查询这些用户中属于某部门的用户
	 * 
	 * @param userids
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidAndUserids(String userids, String deptid);
}
