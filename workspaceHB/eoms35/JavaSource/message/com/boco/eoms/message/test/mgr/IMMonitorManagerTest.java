package com.boco.eoms.message.test.mgr;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.message.mgr.IIMMonitorManager;

import junit.framework.TestCase;

public class IMMonitorManagerTest extends TestCase {

	public static void testUserSendIMMsg() {
		IIMMonitorManager imMonitorMgr = (IIMMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IimMonitorManager");
		imMonitorMgr.userSendIMMsg("zhangyong", "I'm zhangyong.", "1,zhanghao");
	}

	public static void testUserSendIMFile() {
		IIMMonitorManager imMonitorMgr = (IIMMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IimMonitorManager");
		imMonitorMgr.userSendIMFile("zhangyong", "d:\\abc.txt", "1,zhanghao");
	}

	public static void testSystemSendIMMsg() {
		IIMMonitorManager imMonitorMgr = (IIMMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IimMonitorManager");
		imMonitorMgr.systemSendIMMsg("I'm webmaster.", "1,zhanghao");
	}

	public static void testSystemSendIMFile() {
		IIMMonitorManager imMonitorMgr = (IIMMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IimMonitorManager");
		imMonitorMgr.systemSendIMFile("d:\\abc.txt", "1,zhanghao");
	}

	public static void testSendIMScheduler() {
		IIMMonitorManager imMonitorMgr = (IIMMonitorManager) ApplicationContextHolder
				.getInstance().getBean("IimMonitorManager");
		imMonitorMgr.sendIMScheduler("8a03be6c22105575012210e9110f0013", "123",
				"您有工单需要处理", "d:\\abc.txt", StaticMethod.getCurrentDateTime(),
				"1,zhanghao");
	}

}
