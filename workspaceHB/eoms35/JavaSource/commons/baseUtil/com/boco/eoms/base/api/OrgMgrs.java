package com.boco.eoms.base.api;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dept.service.IDeptMgr;
import com.boco.eoms.commons.system.role.service.IRoleMgr;
import com.boco.eoms.commons.system.user.service.IUserMgr;

/**
 * 
 * <p>
 * Title:组织管理对外api暴露接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 12, 2008 4:57:54 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class OrgMgrs {
	/**
	 * 获取用户管理 mgr
	 * 
	 * @return 用户管理mgr
	 * @since 0.1
	 */
	public IUserMgr getUserMgr() {
		return (IUserMgr) ApplicationContextHolder.getInstance().getBean(
				"UserMgrImpl");
	}

	/**
	 * 获取角色管理mgr
	 * 
	 * @return 角色管理mgr
	 * @since 0.1
	 */
	public IRoleMgr getRoleMgr() {
		return (IRoleMgr) ApplicationContextHolder.getInstance().getBean(
				"RoleMgrImpl");
	}

	/**
	 * 获取部门管理mgr
	 * 
	 * @return 部门管理mgr
	 * @since 0.1
	 */
	public IDeptMgr getDeptMgr() {
		return (IDeptMgr) ApplicationContextHolder.getInstance().getBean(
				"DeptMgrImpl");
	}

}
