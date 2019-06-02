package com.boco.eoms.commons.system.user.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;

/**
 * <p>
 * Title:用户管理mgr
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 9:55:28 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class UserMgrLocator {
	/**
	 * 获取用户管理mgr
	 * 
	 */
	public static ITawSystemUserManager getTawSystemUserMgr() {
		return (ITawSystemUserManager) ApplicationContextHolder.getInstance()
				.getBean("itawSystemUserManager");
	}

	/**
	 * 用户相关配置参数
	 * 
	 * @return 配置参数类
	 */
	public static UserAttributes getUserAttributes() {
		return (UserAttributes) ApplicationContextHolder.getInstance().getBean(
				"userAttributes");
	}
	
}
