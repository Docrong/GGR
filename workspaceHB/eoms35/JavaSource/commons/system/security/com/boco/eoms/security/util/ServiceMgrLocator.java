package com.boco.eoms.security.util;

import com.boco.eoms.base.util.ApplicationContextHolder;


public class ServiceMgrLocator {

	/**
	 * 
	 * @author 刘高胜
	 * @version 3.5.1
	 * 
	 */

	/**
	 * 用户相关配置参数
	 * 
	 * @return 配置参数类
	 */
	public static ServiceAttributes getServiceAttributes() {
		
		return (ServiceAttributes) ApplicationContextHolder.getInstance()
				.getBean("serviceAttributes");
	}
	

}
