package com.boco.eoms.remind.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.remind.RemindRegister;

/**
 * <p>
 * Title:提醒locator
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-10-6 上午10:10:35
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class RemindLocator {

	/**
	 * 获取提醒注册模块
	 * 
	 * @return 提醒注册模块
	 */
	public static RemindRegister getRemindRegister() {
		return (RemindRegister) ApplicationContextHolder.getInstance().getBean(
				"RemindRegister");
	}

}
