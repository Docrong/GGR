// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   InterfaceServiceManageAbstract.java

package com.boco.eoms.sheet.interfaceBase.service.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.*;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class InterfaceServiceManageAbstract
	implements IInterfaceServiceManage
{

	public InterfaceServiceManageAbstract()
	{
	}

	public String checkinWorkSheet(HashMap interfaceMap, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(interfaceMap.get("sheetType"));
		String serviceType = StaticMethod.nullObject2String(interfaceMap.get("serviceType"));
		Map map = initMap(interfaceMap, attach, "checkinWorkSheet");
		if (map.get("phaseId") == null)
			throw new Exception("phaseId为空");
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
		map = setBaseMap(map);
		String sheetNo = StaticMethod.nullObject2String(map.get("serialNo"));
		System.out.println("serialNo=" + sheetNo);
		List list = iMainService.getMainListByParentSheetId(sheetNo);
		if (list.size() > 0)
		{
			BaseMain main = (BaseMain)list.get(0);
			map.put("id", main.getId());
			map.put("operateUserId", main.getSendUserId());
			map.put("endUserId", main.getSendUserId());
			map.put("endDeptId", main.getSendDeptId());
			map.put("endRoleId", main.getSendRoleId());
			map.put("status", Constants.SHEET_HOLD);
	     if("56".equals(sheetType) && "1".equals(serviceType)) {
	    	 finishNetDeal(main, map , "checkinWorkSheet");
         	 }
		
			return dealSheet(main, map, attach);
		} else
		{
			throw new Exception("没找到sheetNo＝" + sheetNo + "对应的工单");
		}
	}

	public String newWorkSheet(HashMap interfaceMap, List attach)
		throws Exception
	{
		HashMap columnMap = new HashMap();
		columnMap.put("selfSheet", setNewInterfacePara());
		Map map = initMap(interfaceMap, attach, "newWorkSheet");
		map = setBaseMap(map);
		System.out.println("setBaseMap complete");
		map.put("correlationKey", new String[] {
			UUIDHexGenerator.getInstance().getID()
		});
		String parentCorrelation = StaticMethod.nullObject2String(map.get("parentCorrelation"));
		String sheetType = StaticMethod.nullObject2String(interfaceMap.get("sheetType"));
		String serviceType = StaticMethod.nullObject2String(interfaceMap.get("serviceType"));
		System.out.println("crm侧 工单号为：" + parentCorrelation + " sheetType is:" + sheetType);
		if ("31".equals(sheetType) || "32".equals(sheetType))
			map.put("parentPhaseName", parentCorrelation + "," + sheetType + "," + serviceType);
		WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		String processTemplateName = getProcessTemplateName();
		String operateName = getOperateName();
		String userId = getSendUser(map);
		if (userId == null || userId.equals(""))
			throw new Exception("userId is null");
		System.out.println("userId=" + userId);
		map.put("sendUserId", userId);
		map.put("operateUserId", userId);
		map.put("operateRoleId", map.get("sendRoleId"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		map.put("sendTime", new String[] {
			dateFormat.format(date)
		});
		ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getUserByuserid(userId);
		if (user != null)
		{
			map.put("sendDeptId", user.getDeptid());
			map.put("sendContact", user.getMobile());
			map.put("operateDeptId", user.getDeptid());
			map.put("operaterContact", user.getMobile());
		}
		System.out.println("prepareMap start");
		HashMap WpsMap = sm.prepareMap(map, columnMap);
		System.out.println("start addpara");
		WpsMap = addPara(WpsMap);
		if (WpsMap.get("corrKey") != null)
			System.out.println("add corrKey:" + WpsMap.get("corrKey").toString());
		Map mainMap = sm.sendNewSheet(WpsMap, userId, processTemplateName, operateName);
		System.out.println("sendNewSheet over");
		BaseMain main = new BaseMain();
		SheetBeanUtils.populate(main, mainMap);
		if ("1".equals(sheetType) && "1".equals(serviceType)) {
			finishNew(main, interfaceMap, attach);
		} else if ("58".equals(sheetType) && "1".equals(serviceType)) {
			finishNew(main, WpsMap);
		} else if("56".equals(sheetType) && "1".equals(serviceType)) {
			finishNew(main, interfaceMap , WpsMap);
		} else if("57".equals(sheetType)) {
			map.put("groupType", "newWorkSheet");
			finishNew(main, interfaceMap , map);
		} else{
			finishNew(main, interfaceMap);
		}
		
		return mainMap.get("sheetId").toString();
	}

	public String renewWorkSheet(HashMap interfaceMap, List attach)
		throws Exception
	{
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
		Map map = initMap(interfaceMap, attach, "renewWorkSheet");
		String sheetType = StaticMethod.nullObject2String(interfaceMap.get("sheetType"));
		String serviceType = StaticMethod.nullObject2String(interfaceMap.get("serviceType"));
		map = setBaseMap(map);
		if (map.get("phaseId") == null)
			throw new Exception("phaseId为空");
		String sheetNo = StaticMethod.nullObject2String(map.get("serialNo"));
		List list = iMainService.getMainListByParentSheetId(sheetNo);
		if (list.size() > 0)
		{
			BaseMain main = (BaseMain)list.get(0);
			map.put("id", main.getId());
			map.put("operateRoleId", main.getSendRoleId());
			System.out.println("renew sendRoleId=" + main.getSendRoleId());
			System.out.println("renew operateRoleId=" + map.get("operateRoleId"));
			String sheetKey = dealSheet(main, map, attach);
			if("57".equals(sheetType) && "1".equals(serviceType)) {
				System.out.println("renewWorkSheet");
				map.put("groupType", "renewWorkSheet");
				finishNew(main, interfaceMap,map);
			}
			return sheetKey;
		} else
		{
			throw new Exception("没找到sheetNo＝" + sheetNo + "对应的工单");
		}
	}

	public String suggestWorkSheet(HashMap interfaceMap, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(interfaceMap.get("sheetType"));
		String serviceType = StaticMethod.nullObject2String(interfaceMap.get("serviceType"));
		Map map = initMap(interfaceMap, attach, "suggestWorkSheet");
		map = setBaseMap(map);
		map.put("operateType", "-11");
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
		ILinkService iLinkService = (ILinkService)ApplicationContextHolder.getInstance().getBean(getLinkBeanId());
		ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(getTaskBeanId());
		HashMap columnMap = new HashMap();
		HashMap sheetMap = new HashMap();
		String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
		if (sheetId == null || sheetId.equals(""))
			throw new Exception("sheetId为空");
		BaseMain main = null;
		List list = iMainService.getMainListByParentSheetId(sheetId);
		if (list.size() > 0)
			main = (BaseMain)list.get(0);
		if (main == null)
			throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
		map.put("title", main.getTitle());
		map.put("sheetId", main.getSheetId());
		sheetMap.put("main", main);
		sheetMap.put("link", iLinkService.getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		columnMap.put("selfSheet", sheetMap);
		map.put("operateRoleId", main.getSendRoleId());
		String operateUserId = getSendUser(map);
		map.put("operateUserId", operateUserId);
		WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		List taskList = iTaskService.getCurrentUndoTask(main.getId());
		for (int i = 0; taskList != null && i < taskList.size(); i++)
		{
			ITask task = (ITask)taskList.get(i);
			String roleId = task.getTaskOwner();
			map = sm.setAcceptRole(roleId, map);
			sm.performProcessEvent("", iMainService, iLinkService, iTaskService, sm.prepareMap(map, columnMap));
		    if("56".equals(sheetType) && "1".equals(serviceType)) {
			    finishNetDeal(main, map , "suggestWorkSheet");
		         	 }
		}

		return main.getSheetId();
	}

	public String untreadWorkSheet(HashMap interfaceMap, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(interfaceMap.get("sheetType"));
		String serviceType = StaticMethod.nullObject2String(interfaceMap.get("serviceType"));
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
		Map map = initMap(interfaceMap, attach, "untreadWorkSheet");
		map = setBaseMap(map);
		String sheetNo = StaticMethod.nullObject2String(map.get("serialNo"));
		BaseMain main = null;
		List list = iMainService.getMainListByParentSheetId(sheetNo);
		if (list.size() > 0)
			main = (BaseMain)list.get(0);
		if (main == null)
		{
			throw new Exception("没找到sheetNo＝" + sheetNo + "对应的工单");
		} else
		{
			map.put("id", main.getId());
			map.put("operateRoleId", main.getSendRoleId());
	    if("56".equals(sheetType) && "1".equals(serviceType)) {
		    finishNetDeal(main, map , "untreadWorkSheet");
	         	 }
			return dealSheet(main, map, attach);
		}
	}

	public String cancelWorkSheet(HashMap interfaceMap)
		throws Exception
	{
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
		List attach = null;
		Map map = initMap(interfaceMap, attach, "cancelWorkSheet");
		map = setBaseMap(map);
		String sheetNo = StaticMethod.nullObject2String(map.get("serialNo"));
		List list = iMainService.getMainListByParentSheetId(sheetNo);
		if (list.size() > 0)
		{
			BaseMain main = (BaseMain)list.get(0);
			map.put("id", main.getId());
			map.put("status", Constants.SHEET_CANCEL);
			map.put("operateRoleId", main.getSendRoleId());
			return dealSheet(main, map, attach);
		} else
		{
			throw new Exception("没找到sheetNo＝" + sheetNo + "对应的工单");
		}
	}

	public String getPageColumnName()
	{
		return "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String";
	}

	public Map loadDefaultMap(Map interfaceMap, String filePath, String nodePath)
		throws Exception
	{
		InterfaceUtilProperties properties = new InterfaceUtilProperties();
		filePath = StaticMethod.getFilePathForUrl("classpath:" + filePath);
		return properties.getMapFromXml(interfaceMap, filePath, nodePath);
	}

	public Map setBaseMap(Map map)
	{
		try
		{
			IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
			ILinkService iLinkService = (ILinkService)ApplicationContextHolder.getInstance().getBean(getLinkBeanId());
			String mainBeanId = getMainBeanId();
			map.put("beanId", new String[] {
				mainBeanId
			});
			System.out.println("mainClassName=" + iMainService.getMainObject().getClass().getName());
			System.out.println("linkClassName=" + iLinkService.getLinkObject().getClass().getName());
			map.put("mainClassName", new String[] {
				iMainService.getMainObject().getClass().getName()
			});
			map.put("linkClassName", new String[] {
				iLinkService.getLinkObject().getClass().getName()
			});
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		return map;
	}

	public String dealSheet(BaseMain main, Map map, List attach)
		throws Exception
	{
		System.out.println("start dealSheet");
		HashMap columnMap = new HashMap();
		HashMap sheetMap = new HashMap();
		ILinkService iLinkService = (ILinkService)ApplicationContextHolder.getInstance().getBean(getLinkBeanId());
		ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(getTaskBeanId());
		sheetMap.put("main", main);
		sheetMap.put("link", iLinkService.getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		columnMap.put("selfSheet", sheetMap);
		System.out.println("full columnMap");
		String operateUserId = StaticMethod.nullObject2String(map.get("operateUserId"));
		if (operateUserId == null || operateUserId.equals(""))
			operateUserId = main.getSendUserId();
		System.out.println("operateUserId=" + operateUserId);
		ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawSystemUser user = userMgr.getUserByuserid(operateUserId);
		if (user != null)
		{
			map.put("sendDeptId", user.getDeptid());
			map.put("sendContact", user.getMobile());
			map.put("operateDeptId", user.getDeptid());
			map.put("operaterContact", user.getMobile());
		}
		map.put("operateUserId", operateUserId);
		map.put("sheetId", main.getSheetId());
		map.put("correlationKey", main.getCorrelationKey());
		map.put("mainId", main.getId());
		System.out.println("full map");
		String sheetKey = main.getId();
		WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		return sm.dealSheet(sheetKey, map, columnMap, operateUserId, iTaskService);
	}

	public abstract String getMainBeanId();

	public abstract String getLinkBeanId();

	public abstract String getTaskBeanId();

	public abstract String getProcessTemplateName();

	public abstract String getOperateName();

	public String getSendUser(Map map)
	{
		return StaticMethod.nullObject2String(map.get("sendUserId"));
	}

	public abstract String getSheetAttachCode();

	public abstract Map initMap(Map map, List list, String s)
		throws Exception;

	public HashMap addPara(HashMap hashMap)
	{
		return hashMap;
	}

	public Map setNewInterfacePara()
		throws Exception
	{
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
		ILinkService iLinkService = (ILinkService)ApplicationContextHolder.getInstance().getBean(getLinkBeanId());
		System.out.println("mainClassName=" + iMainService.getMainObject().getClass().getName());
		System.out.println("linkClassName=" + iLinkService.getLinkObject().getClass().getName());
		HashMap sheetMap = new HashMap();
		sheetMap.put("main", iMainService.getMainObject().getClass().newInstance());
		sheetMap.put("link", iLinkService.getLinkObject().getClass().newInstance());
		System.out.println("newInstanceover");
		sheetMap.put("operate", getPageColumnName());
		return sheetMap;
	}

	public void notifyWorkSheet()
	{
	}

	public void withdrawWorkSheet()
		throws Exception
	{
	}

	public void replyWorkSheet()
	{
	}

	public void confirmWorkSheet()
	{
	}

	public String getAttach(List attachList)
	{
		WPSEngineServiceMethod wm = new WPSEngineServiceMethod();
		return wm.getAttach(attachList, getSheetAttachCode());
	}

	public void finishNew(BaseMain basemain, Map map)
	{
	}
	
	public void finishNew(BaseMain basemain, Map map, Map wpsMap)
	{
	}
	public void finishNew(BaseMain basemain, Map map, List attach)
	{
		
	}
	public void finishDeal(String s, Map map)
	{
	}
	public void finishNetDeal(BaseMain main, Map map, String dealType)
	{
	}
}
