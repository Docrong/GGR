package com.boco.eoms.commons.system.myinfo.service;

import java.util.List;

import com.boco.eoms.base.service.Manager;
 
/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-7-30 10:04:17
 * </p>
 * 
 * @author 龚玉峰
 * @version 1.0
 */
public interface ITawSystemMyinfoManager extends Manager {
	
	/*
	 * 根据用户id得到所有的角色
	 */
	public List getRoleByUserid(String userid);
	
	/*
	 * 根据用户id得到所有的部门与
	 */
	public List getDeptByUserid(String userid);
	
	
	/*
	 * 添加用户的角色
	 * 
	 */
	public void addRole(String subRoleid,String userid,String roleType);
	
	
	
	public boolean isExist(String subRoleid, String userid);

	
	public void deleteRole(String subRoleid, String userid);
	
	
	public void addDept(String dept, String userid);
	
	
	public void deleteDept(String dept, String userid);
	
	
	public boolean isExistDept(String dept, String userid);
}
