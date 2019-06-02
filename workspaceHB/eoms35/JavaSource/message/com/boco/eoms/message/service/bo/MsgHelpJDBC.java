package com.boco.eoms.message.service.bo;

import java.sql.Connection;
import java.text.ParseException;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

 
import com.boco.eoms.base.util.ApplicationContextHolder;
 
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.MsgConstants;
import com.boco.eoms.message.util.TimeHelp;
import com.boco.eoms.prm.exceptions.PRMException;
import com.boco.eoms.prm.service.IPojo2PojoService;
import com.boco.eoms.message.dao.jdbc.MsgJdbc;

public class MsgHelpJDBC {
	public static List getUserList(Connection connection, String userString) {
		List outList = new ArrayList();
		String[] users = userString.split("#");
		String[] user = null;
		/*
		 * ITawSystemUserManager uMgr =
		 * (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		 * ITawSystemUserRefRoleManager rMgr =
		 * (ITawSystemUserRefRoleManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
		 */try {
			if (users != null && users.length != 0) {
				int usersLength = users.length;
				for (int i = 0; i < usersLength; i++) {
					user = users[i].split(",");
					if (user[0].equals("1")) {
						/** 人员 */
						outList.add(MsgJdbc
								.getUserByuserid(connection, user[1]));
					} else if (user[0].equals("2")) {
						/** 部门 */
						outList.addAll(MsgJdbc.getUserBydeptids(connection,
								user[1]));
					} else if (user[0].equals("3")) {
						/** 角色 */
						outList.addAll(MsgJdbc.getUserbySubRoleid(connection,
								user[1]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return outList;
	}

	public static List getMobileList(String orgsId) {
		List outList = new ArrayList();
		String[] users = orgsId.split("#");
		String[] user = null;
		if (users != null && users.length != 0) {
			int usersLength = users.length;
			for (int i = 0; i < usersLength; i++) {
				user = users[i].split(",");
				outList.add(user[1]);
			}
		}
		return outList;
	}

	/**
	 * 生成短信轮询数据
	 * 
	 * @param apply
	 * @param msg
	 * @param mobile
	 * @param buizId
	 * @param dispatchTime
	 * @throws ParseException
	 * @throws PRMException
	 */
	public static void genMonitor(Connection connection, SmsApply apply,
			String msg, String mobile, String buizId, String dispatchTime)
			throws ParseException, PRMException {

		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder
				.getInstance().getBean("Monitor2Log");
		Date finalTime = TimeHelp.getFinalDate(apply, dispatchTime);
		List dateList = TimeHelp.caculateDate(apply, finalTime);
		Iterator it = dateList.iterator();
		// 允许发送

		while (it.hasNext()) {
			// 短信超过60字就分割短信
			List msgList = inciseMsg(msg);
			Iterator msgIt = msgList.iterator();
			Date curDate = (Date) it.next();
			while (msgIt.hasNext()) {
				SmsMonitor monitor = new SmsMonitor();
				SmsLog log = new SmsLog();
				monitor.setBuizid(buizId);
				monitor.setMobile(mobile);
				monitor.setServiceId(apply.getServiceId());
				monitor.setApplyId(apply.getId());
				monitor.setContent((String) msgIt.next());
				monitor.setDispatchTime(curDate);
				monitor.setReceiverId(apply.getReceiverId());
				monitor.setIsSendImediat(apply.getIsSendImediat());
				monitor.setRegetData(apply.getRegetData());
				MsgJdbc.saveSmsMonitor(connection, monitor);
				pojo2pojo.p2p(monitor, log);
				log.setMobile(mobile);
				log.setMsgType("1");
				MsgJdbc.saveSmsLog(connection, log);
			}
			// 不足60字
			if (msgList != null && msgList.size() == 0) {
				SmsMonitor monitor = new SmsMonitor();
				SmsLog log = new SmsLog();
				monitor.setBuizid(buizId);
				monitor.setMobile(mobile);
				monitor.setServiceId(apply.getServiceId());
				monitor.setApplyId(apply.getId());
				monitor.setContent(msg);
				monitor.setDispatchTime(curDate);
				monitor.setReceiverId(apply.getReceiverId());
				monitor.setIsSendImediat(apply.getIsSendImediat());
				monitor.setRegetData(apply.getRegetData());
				MsgJdbc.saveSmsMonitor(connection, monitor);
				pojo2pojo.p2p(monitor, log);
				log.setMobile(mobile);
				log.setMsgType("1");
				MsgJdbc.saveSmsLog(connection, log);
			}
		}
	}

	 
	/**
	 * 将长短信分割成每条60字的短信，并在短信头进行提示短信是哪条如：(1/3)
	 * 
	 * @param msg
	 *            整体的消息
	 * @return 被分割的多条短信的list
	 */
	public static List inciseMsg(String msg) {
		int msgLength = msg.length();
		int total = (msg.length() / 55) + 1;
		int sigle = 55;
		List msgList = new ArrayList();
		if (msgLength > 60) {
			for (int i = 0; i < total; i++) {
				String piece = "";
				if (i == total - 1) {
					piece = msg.substring(i * sigle);
				} else {
					piece = msg.substring(i * sigle, (i + 1) * sigle);
				}
				piece = "(" + (i + 1) + "/" + total + ")" + piece;
				msgList.add(piece);
			}
		}
		return msgList;
	}

	public static SmsApply getApply(Connection connection, String serviceId,
			String userId) {
		IPojo2PojoService pojo2pojo = (IPojo2PojoService) ApplicationContextHolder
				.getInstance().getBean("Service2Apply");
		/*
		 * ISmsApplyManager applyMgr = (ISmsApplyManager)
		 * ApplicationContextHolder.getInstance().getBean("IsmsApplyJDBC");
		 * ISmsServiceManager serviceMgr = (ISmsServiceManager)
		 * ApplicationContextHolder.getInstance().getBean("IsmsServiceJDBC");
		 */
		SmsService diyService = null;
		SmsApply apply = null;
		diyService = MsgJdbc.getSmsService(connection, serviceId);
		String selStatus = diyService.getSelStatus();

		apply = MsgJdbc.getSimpleApply(connection, serviceId, userId,
				MsgConstants.DIYED);
		if (apply == null) {
			// 订阅表中无订阅（包括反选个性化的和正选的）
			if ("false".equals(selStatus)) {
				// 该服务为反选
				try {
					apply = new SmsApply();
					pojo2pojo.p2p(diyService, apply);// 将服务信息转换成订阅信息
				} catch (PRMException e) {
					e.printStackTrace();
				}
			}
		}
		return apply;
	}

	public static boolean closeMsg(Connection connection, String serviceId,
			String id) {
		boolean bool = false;
		SmsMonitor smsMonitor = null;
		List list = MsgJdbc.getSmsMonitor(connection, serviceId, id);
		for (Iterator it = list.iterator(); it.hasNext();) {
			smsMonitor = (SmsMonitor) it.next();
			bool = MsgJdbc.closeMsg(connection, smsMonitor.getId());
		}
		return bool;
	}
}
