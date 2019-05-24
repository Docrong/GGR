// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintSheetSmsUtils.java

package com.boco.eoms.sheet.complaint.sms;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.tool.complaintmsgconfig.mgr.IComplaintSheetMsgConfigMgr;

import commonj.sdo.DataObject;

public class ComplaintSheetSmsUtils
{

	public ComplaintSheetSmsUtils()
	{
	}

	public String changeSender(String receivers, DataObject mainObject, String workflowName, String sheetKey, String sheetId, String title, Integer receiveType, 
			String receiverId, String acceptLimitTime, String dealLimitTime, String taskCnName)
	{
		if ("一级处理".equals(taskCnName))
		{
			String faultArea = StaticMethod.nullObject2String(mainObject.get("customAttribution"));
			String complaintType = StaticMethod.nullObject2String(mainObject.get("bdeptContactPhone"));
			System.out.println("=====faultArea=======" + faultArea + "&&&" + complaintType);
			IComplaintSheetMsgConfigMgr mgr = (IComplaintSheetMsgConfigMgr)ApplicationContextHolder.getInstance().getBean("complaintSheetMsgConfigMgr");
			String tempUser = mgr.getNotifyUser(faultArea, complaintType);
			System.out.println("====tempUser====" + tempUser);
			if (!"".equals(tempUser))
			{
				tempUser = tempUser.replaceAll(",", "#1,");
				receivers = receivers + "#" + 1 + "," + tempUser;
				System.out.println("=====taskCnName=======" + taskCnName + "&&&" + receivers);
			}
		}
		System.out.println("=====一级处理=======" + taskCnName + receivers);
		return receivers;
	}
}
