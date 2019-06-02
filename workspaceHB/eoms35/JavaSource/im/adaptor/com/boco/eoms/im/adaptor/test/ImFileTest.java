package com.boco.eoms.im.adaptor.test;

import java.io.File;

import junit.framework.TestCase;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.im.adaptor.listener.IMFileListener;
import com.boco.eoms.im.adaptor.mgr.IMAdaptorMgr;

public class ImFileTest extends TestCase {

	/**
	 * @param args
	 */

	IMAdaptorMgr imadaptorMgr = (IMAdaptorMgr) ApplicationContextHolder
			.getInstance().getBean("imMgr");

	IMFileListener imFileListener = new IMFileListener();

	// 系统发文件，接口参数为file和接受者的id
	public void testSysFileSendFile() {
		File file = new File("d:\\abc.txt");
		String toOrgIds = "1,zhanghao";
		imadaptorMgr.sendFile(file, toOrgIds);
	}

	// 系统发文件，接口参数为文件路径和接受者的id
	public void testSysFilePathSendFile() {
		String filePath = "d:\\abc.txt";
		String toOrgIds = "1,zhanghao";
		imadaptorMgr.sendFile(filePath, toOrgIds);
	}

	// 系统发文件，接口参数为文件和接受者的id,监听
	// public void testSendFile(File file, String toOrgIds,
	// IMFileListener imFileListener) {
	// file = new File("d:\\abc.txt");
	// toOrgIds = "1,zhanghao";
	// imFileListener = null;
	// }

	// 系统发文件，接口参数为文件的路径和接受者的id,监听
	// public void testSendFile(String filePath, String toOrgIds,
	// IMFileListener imFileListener) {
	// filePath = "d:\\abc.txt";
	// toOrgIds = "1,zhanghao";
	// imFileListener = null;
	// imadaptorMgr.sendFile(filePath, toOrgIds, imFileListener);
	// }

	// 个人发文件，接口参数为发送者id，密码，文件，接受者id，监听
	// public void testSendFile(String user, String passwd, File file,
	// String toOrgIds, IMFileListener imFileListener) {
	// user = "liqiuye";
	// passwd = "111";
	// file = new File("d:\\abc.txt");
	// toOrgIds = "1,zhanghao";
	// imFileListener = null;
	// imadaptorMgr.sendFile(user, passwd, file, toOrgIds, imFileListener);
	// }

	// 个人发文件，接口参数为发送者的id，密码，文件路径和接受者的id,监听
	// public void testSendFile(String user, String passwd, String filePath,
	// String toOrgIds, IMFileListener imFileListener) {
	// user = "liqiuye";
	// passwd = "111";
	// filePath = "d:\\abc.txt";
	// toOrgIds = "1,zhanghao";
	// imFileListener = null;
	// imadaptorMgr.sendFile(filePath, toOrgIds, imFileListener);
	// }

	// 个人发文件，接口参数为发送者id，密码，文件，接受者id
	public void testUserFileSendFile() {
		String user = "liqiuye";
		String passwd = "111";
		File file = new File("d:\\abc.txt");
		String toOrgIds = "1,zhanghao";
		imadaptorMgr.sendFile(user, passwd, file, toOrgIds);
	}

	// 个人发文件，接口参数为发送者id，密码，文件路径，接受者id
	public void testUserPathSendFile() {
		String user = "liqiuye";
		String passwd = "111";
		String filePath = "d:\\abc.txt";
		String toOrgIds = "1,zhanghao";
		imadaptorMgr.sendFile(user, passwd, filePath, toOrgIds);
	}

}
