package com.boco.eoms.testcard.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.testcard.util.TestCardMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

public class TawTestMsg {

	
	/**
	 * 增加短信催辦功能
	 * 
	 */
	public static void sendSMS(String _cruser, String _returndate, String _id) {
		// TODO 发送短信
		String serverid = TestCardMgrLocator.getAttributes().getServerId();
		int returnDay = Integer.parseInt(TestCardMgrLocator.getAttributes().getReturnDay());
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
		.getInstance().getBean("ItawSystemUserSaveManagerFlush");
//		MsgService msgService = MsgMgrLocator.getMsgMgr();
		MsgServiceImpl   msgService = new MsgServiceImpl();
		if (msgService.hasService(serverid).equals(
				"true")) {
			// 拼写发送人 orgIds 格式：1,admin#,1,sunshengtai#2,151
			StringBuffer orgIds = new StringBuffer();
			orgIds.append("1," + _cruser + "#");
			String userName =  userManager.getUserByuserid(_cruser).getUsername();
			String sendDate = getDateAddString(_returndate.split(" ")[0],returnDay)+" "+_returndate.split(" ")[1];
			String _content = userName+"您好:卡号为"+_id+"的测试卡归还时间为"+_returndate+".请及时归还!";
			// 获得当前时间 
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
		 

			msgService.sendMsg(serverid, _content,
					_id, orgIds.toString(), sendDate);
			
			 
		}

		
	}
	
	/**
	 * @param old_date
	 * @param date_add
	 * @return
	 */
	public static String getDateAddString(String old_date, int date_add) {
		String new_date = "";
		GregorianCalendar cal = new GregorianCalendar();
		Vector time_vector = StaticMethod.getVector(old_date, " ");
		Vector date_vector = StaticMethod.getVector(String.valueOf(
				time_vector.elementAt(0)).trim(), "-");
		int year = Integer.parseInt(String.valueOf(date_vector.elementAt(0))
				.trim());
		int month = Integer.parseInt(String.valueOf(date_vector.elementAt(1))
				.trim());
		int day = Integer.parseInt(String.valueOf(date_vector.elementAt(2))
				.trim());
		java.util.Date date = new java.util.Date(year - 1900, month - 1,
				day - 0);
		cal.setTime(date);
		cal.add(5, date_add);
		year = cal.get(cal.YEAR);
		month = cal.get(cal.MONTH) + 1;
		day = cal.get(cal.DATE);
		new_date = String.valueOf(year).trim() + "-"
				+ String.valueOf(month).trim() + "-"
				+ String.valueOf(day).trim();
		if (time_vector.size() > 1) {
			new_date = new_date + " " + time_vector.elementAt(1);
		}
		// null
		cal = null;
		time_vector = null;
		date_vector = null;
		date = null;
		return new_date;
	}
}
