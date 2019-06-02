package com.boco.eoms.otherwise.util;

import com.boco.eoms.base.util.ApplicationContextHolder;

public class TestcardMgrLocator {
	/**
	 * 获取属性类
	 * 
	 * @return 获取属性类
	 */
	public static TestCardAttributes getAttributes() {
		return (TestCardAttributes) ApplicationContextHolder.getInstance().getBean("testCardAttributes");
	}

}
