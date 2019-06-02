package com.boco.eoms.message.util;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.service.impl.MsgServiceImpl;

public class TestSave {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		MsgService msgService = MsgMgrLocator.getMsgMgr();
//		msgService.sendEmail("8a9a8ae41dd1564e011dd15d78920005", "fasongneirong",
//				"buizid", "bizheng", "1,bizheng","2008-11-26 09:00:01","E:\\a.txt");
//		String sers = "1,一级模块标题#1,二级模块标题#1,N级标题#2,服务1#2,服务2#2,服务3";
//		String a = TestSave.saveServiceByIf(sers,"admin","1","5","5","true","false","0","1","30","2","false","","0","0","0","false","","");
//		System.out.println(a);
	}
	public static String saveServiceByIf(String serName, String userId, String msgType, String count, String interval, String isSendImediat, String isSendNight, String sendDay, String sendHour, String sendMin, String sendStatus, String isCycleSend, String cycleStatus, String cycleMonth, String cycleDay, String cycleHour, String regetData, String regetAddr, String regetProtocol) {
		ISmsServiceManager smsServiceManager = (ISmsServiceManager) ApplicationContextHolder.getInstance().getBean("IsmsServiceManager");
		String returnStr = "true";		
		String parentId = "-1";
		List serList = MsgServiceImpl.diciseService(serName);
		Iterator it = serList.iterator();
		String id = "";
		String name = "";
		try {
			while(it.hasNext()) {
				SmsService mService = new SmsService();
				String pieceSer = (String)it.next();
				String[] pieSer = pieceSer.split(","); 
				id = pieSer[0];
				name = pieSer[1];
				if(!id.equals("") && MsgConstants.MODULE.equals(id)) {				
					mService.setName(name);
					mService.setUserId(userId);
					mService.setDeleted(MsgConstants.UNDELETED);
					mService.setLeaf("0");
					mService.setParentId(parentId);
					mService.setStatus(MsgConstants.MODULE);
					smsServiceManager.saveSmsService(mService);
					parentId = mService.getId();
				} else {
					mService.setName(name);
					mService.setUserId(userId);
					mService.setMsgType(msgType);
					mService.setCount(new Integer(count));
					mService.setInterval(interval);
					mService.setIsSendImediat(isSendImediat);
					mService.setIsSendNight(isSendNight);
					mService.setCycleStatus(cycleStatus);
					mService.setDeleted(MsgConstants.UNDELETED);
					mService.setLeaf("1");
					mService.setParentId(parentId);
					mService.setStatus(MsgConstants.SERVICE);
					smsServiceManager.saveSmsService(mService);
					parentId = mService.getParentId();
				}
			}
			returnStr = "true";
		} catch(Exception e) {
			returnStr = "false";
			e.printStackTrace();
		}	
		
		return returnStr;
	}
}
