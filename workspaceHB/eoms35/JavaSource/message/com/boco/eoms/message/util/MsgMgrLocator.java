package com.boco.eoms.message.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.message.service.MsgCallbackService;
import com.boco.eoms.message.service.MsgService;

/**
 * 
 * @author User
 *
 */
public class MsgMgrLocator {

	/**
	 * 获取消息平台mgr
	 * 
	 * @return 消息相关操作mgr
	 */
	public static MsgService getMsgMgr() {
		return (MsgService) ApplicationContextHolder.getInstance().getBean(
				"MsgServiceImpl");
	}
	public static MsgCallbackService getMsgCallBackMgr() {
		return (MsgCallbackService) ApplicationContextHolder.getInstance().getBean(
				"MsgCallbackServiceImpl");
	}
}
