package com.boco.eoms.commons.message.test.service;

import java.text.ParseException;

import junit.framework.TestCase;

import com.boco.eoms.commons.message.msg.bo.TawCommonMessageCycleMonitor;
import com.boco.eoms.commons.message.msg.bo.TawCommonMessageMethod;
public class TawCommonMessageTest extends TestCase {

	
	
	public void testMobile() throws ParseException{
		TawCommonMessageMethod method = TawCommonMessageMethod.getInstance();
		method.sendMessage("panlong", "99", "9901", "测试", "99999");
	}
//	public void testEmail() throws ParseException{
//		TawCommonMessageMethod method = TawCommonMessageMethod.getInstance();
//		method.sendMessage("panlong", "88", "8866", "sss", "99999");
//	}
//	public void testCycleMonitor() throws ParseException{
//		TawCommonMessageCycleMonitor cyclem = new TawCommonMessageCycleMonitor();
//		cyclem.excuteSent();
//	}
}
