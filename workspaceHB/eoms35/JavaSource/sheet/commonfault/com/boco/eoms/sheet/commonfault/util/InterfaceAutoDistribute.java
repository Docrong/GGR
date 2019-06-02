// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   InterfaceAutoDistribute.java

package com.boco.eoms.sheet.commonfault.util;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.autodistribute.util.AutoDistributeUtil;
import com.boco.eoms.sheet.commonfault.service.*;
import java.util.*;

public class InterfaceAutoDistribute
{

	public InterfaceAutoDistribute()
	{
	}

	public static String getAutoDistributeUser(String processTemplateName, String dealPerformer)
		throws Exception
	{
		String userId = dealPerformer;
		ICommonFaultMainManager iCommonFaultMainManager = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
		ICommonFaultLinkManager iCommonFaultLinkManager = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
		ICommonFaultTaskManager iCommonFaultTaskManager = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
		int autoDistributeType = AutoDistributeUtil.AutoDistributeType(processTemplateName);
		if (autoDistributeType == 1)
			userId = AutoDistributeUtil.getAutoDistributeUserAve(processTemplateName, dealPerformer, iCommonFaultMainManager, iCommonFaultLinkManager, iCommonFaultTaskManager, new ArrayList(), "FirstExcuteHumTask");
		else
		if (autoDistributeType == 2)
			userId = AutoDistributeUtil.getAutoDistributeUserThr(processTemplateName, dealPerformer, iCommonFaultMainManager, iCommonFaultLinkManager, iCommonFaultTaskManager, "FirstExcuteHumTask");
		return userId;
	}

	public static String getAutoDistributeUser(String processTemplateName, List userList)
		throws Exception
	{
		String userId = "";
		if (userList != null && userList.size() > 0)
		{
			Random rnd = new Random();
			int i = rnd.nextInt(userList.size());
			userId = StaticMethod.nullObject2String(userList.get(i));
		}
		return userId;
	}
}
