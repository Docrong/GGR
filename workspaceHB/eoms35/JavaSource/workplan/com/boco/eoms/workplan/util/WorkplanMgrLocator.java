package com.boco.eoms.workplan.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
 
public class WorkplanMgrLocator {
	/**
	 * 获取属性类
	 * 
	 * @return 获取属性类
	 */
	public static WorkplanAttributes getAttributes() {
		return (WorkplanAttributes) ApplicationContextHolder.getInstance().getBean(
				"workplanAttributes");
	}
}
