package com.boco.eoms.im.adaptor.test;

import junit.framework.TestCase;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.im.adaptor.exception.IMAdaptorSendMsgErrorException;
import com.boco.eoms.im.adaptor.facade.IMAdaptorFacade;
import com.boco.eoms.im.adaptor.listener.IMFileListener;
import com.boco.eoms.im.adaptor.mgr.IMAdaptorMgr;

public class ImChatTest extends TestCase {

	/**
	 * @param args
	 */

	IMAdaptorFacade imAdaptorFacade = (IMAdaptorFacade) ApplicationContextHolder
			.getInstance().getBean("imAdaptorFacade");


	IMFileListener imFileListener = new IMFileListener();

	// imFileListener=null;

	// 系统发消息，参数为接受者的id和消息内容的接口
	public void testSysSendMsg() {
		String toOrgIds = "1,zhanghao";
		String body = "nice";
		try {
			imAdaptorFacade.sendMsg(toOrgIds, body);
		} catch (IMAdaptorSendMsgErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 系统发消息，参数为接受者的id和消息内容,监听的接口
//	public void testSysSendMsg(String toOrgIds, String body,
//			IMFileListener imFileListener) {
//		toOrgIds = "1,zhanghao";
//		body = "nice";
//		imFileListener = null;
//		try {
//			imAdaptorFacade.sendMsg(toOrgIds, body, null);
//		} catch (IMAdaptorSendMsgErrorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	// 个人发消息，参数为发送者的用户名和密码，接受者的id和消息内容的接口
	public void testUserSendMsg(String user, String passwd, String toOrgIds,
			String body) {
		toOrgIds = "1,zhanghao";
		body = "nice";
		user = "liqiuye";
		passwd = "111";
		try {
			imAdaptorFacade.sendMsg(user, passwd, toOrgIds, body);
		} catch (IMAdaptorSendMsgErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//			

	// 个人发消息，参数为发送者的用户名和密码，接受者的id和消息内容,监听的接口
//	public void testSendMsg(String user, String passwd, String toOrgIds,
//			String body, IMFileListener imFileListener) {
//		toOrgIds = "1,zhanghao";
//		body = "nice";
//		user = "liqiuye";
//		passwd = "111";
//		imFileListener = null;
//		try {
//			imAdaptorFacade.sendMsg(user, passwd, toOrgIds, body, null);
//		} catch (IMAdaptorSendMsgErrorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
