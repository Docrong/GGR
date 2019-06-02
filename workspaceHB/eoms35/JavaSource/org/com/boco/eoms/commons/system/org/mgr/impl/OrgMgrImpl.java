package com.boco.eoms.commons.system.org.mgr.impl;

import java.util.List;

import com.boco.eoms.commons.system.org.mgr.IOrgMgr;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

/**
 * <p>
 * Title:组织管理API
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Oct 22, 2008 2:52:19 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class OrgMgrImpl implements IOrgMgr {

	/**
	 * 用户与角色关联dao
	 */
	private TawSystemUserRefRoleDao tawSystemUserRefRoleDao;

	/**
	 * 用户dao
	 */
	private TawSystemUserDao tawSystemUserDao;

	/**
	 * @param tawSystemUserRefRoleDao
	 *            the tawSystemUserRefRoleDao to set
	 */
	public void setTawSystemUserRefRoleDao(
			TawSystemUserRefRoleDao tawSystemUserRefRoleDao) {
		this.tawSystemUserRefRoleDao = tawSystemUserRefRoleDao;
	}

	/**
	 * @param tawSystemUserDao
	 *            the tawSystemUserDao to set
	 */
	public void setTawSystemUserDao(TawSystemUserDao tawSystemUserDao) {
		this.tawSystemUserDao = tawSystemUserDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.org.mgr.IOrgMgr#getSubrolesOfDept(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List getSubrolesOfDept(String deptId, String roleType, String delFlag) {
		// 根据部门取出所有用户
		List users = tawSystemUserDao.getUserBydeptids(deptId);
		// 构建要使用的用户id数组
		String userIds[] = null;
		if (users != null) {
			userIds = new String[users.size()];
			for (int i = 0; i < users.size(); i++) {
				TawSystemUser user = (TawSystemUser) users.get(i);
				userIds[i] = user.getUserid();
			}
		}
		// 查询部门下的所有用户的子角色
		return tawSystemUserRefRoleDao.getSubrolesByUserId(userIds, roleType,
				delFlag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.org.mgr.IOrgMgr#getSubrolesOfUser(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List getSubrolesOfUser(String userId, String roleType, String delFlag) {
		return tawSystemUserRefRoleDao.getSubrolesByUserId(
				new String[] { userId }, roleType, delFlag);
	}

}
