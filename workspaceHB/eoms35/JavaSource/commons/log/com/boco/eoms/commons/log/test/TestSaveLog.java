package com.boco.eoms.commons.log.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.log.service.TawCommonLogOperatorManager;
import com.boco.eoms.commons.log.webapp.bo.impl.TawCommonLog;

public class TestSaveLog extends TestCase {

	public void testSaveLog() {

		SimpleDateFormat dateformat = new SimpleDateFormat();
		String str = dateformat.format(new Date());
		TawCommonLog.saveLog(this, str, "192.168.0.1", "1102", "测试");
		TawCommonLogOperatorManager logopermanager=(TawCommonLogOperatorManager) ApplicationContextHolder
		.getInstance().getBean("tawCommonLogOperatorManager");
		List list = logopermanager.getAllByUserIDs(str, "sucess");
		assertTrue(list.size()>0);
	}

}
