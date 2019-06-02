// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   EomsLoader.java

package com.boco.eoms.crm.bo;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.crm.util.SheetMapingSchema;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import java.util.HashMap;
import java.util.List;

public class IrsmLoader
{

	public IrsmLoader()
	{
	}

	public IInterfaceServiceManage getCrmService(String sheetType, String serviceType)
		throws Exception
	{
		String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType, serviceType);
		IInterfaceServiceManage mgr = (IInterfaceServiceManage)ApplicationContextHolder.getInstance().getBean(beanId);
		return mgr;
	}

	private IInterfaceServiceManage getCrmService(HashMap sheetMap)
		throws Exception
	{
		String cityName = StaticMethod.nullObject2String(sheetMap.get("accountManagerCity"));
		String zxType = StaticMethod.nullObject2String(sheetMap.get("zxType"));
		String sheetType = StaticMethod.nullObject2String(sheetMap.get("sheetType"));
		String serialNo = StaticMethod.nullObject2String(sheetMap.get("serialNo"));
		String beanId = "";
		if ((cityName.equals("榆林") || cityName.equals("汉中")) && (zxType.equals("1") || zxType.equals("3") || zxType.equals("4")) && serialNo.indexOf("CRM") >= 0)
			if (sheetType.equals("31"))
				beanId = "iResourceConfirmCrmService";
			else
			if (sheetType.equals("32"))
				beanId = "iBusinessImplementCrmService";
		if (beanId.equals(""))
		{
			String serviceType = StaticMethod.nullObject2String(sheetMap.get("serviceType"));
			beanId = SheetMapingSchema.getInstance().getBeanId(sheetType, serviceType);
		}
		IInterfaceServiceManage mgr = (IInterfaceServiceManage)ApplicationContextHolder.getInstance().getBean(beanId);
		return mgr;
	}

	public String newWorkSheet(HashMap map, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
		String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
		IInterfaceServiceManage mgr = getCrmService(map);
		if (mgr == null)
			throw new Exception("未找到相应工单");
		else
			return mgr.newWorkSheet(map, attach);
	}

	public String renewWorkSheet(HashMap map, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
		String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
		IInterfaceServiceManage mgr = getCrmService(map);
		if (mgr == null)
			throw new Exception("未找到相应工单");
		else
			return mgr.renewWorkSheet(map, attach);
	}

	public String suggestWorkSheet(HashMap map, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
		String serialNo = StaticMethod.nullObject2String(map.get("serialNo"));
		String beanId = "";
		IMainService iMainService = null;
		if (sheetType.equals("31"))
		{
			beanId = "iResourceAffirmCrmService";
			iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iResourceAffirmMainManager");
			List list = iMainService.getMainListByParentSheetId(serialNo);
			if (list == null || list.size() == 0)
				beanId = "iResourceConfirmCrmService";
		} else
		if (sheetType.equals("32"))
		{
			beanId = "iBusinessDredgeCrmService";
			iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iBusinessDredgeMainManager");
			List list = iMainService.getMainListByParentSheetId(serialNo);
			if (list == null || list.size() == 0)
				beanId = "iBusinessImplementCrmService";
		}
		if (beanId.equals(""))
		{
			String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
			beanId = SheetMapingSchema.getInstance().getBeanId(sheetType, serviceType);
		}
		IInterfaceServiceManage mgr = (IInterfaceServiceManage)ApplicationContextHolder.getInstance().getBean(beanId);
		return mgr.suggestWorkSheet(map, attach);
	}

	public String untreadWorkSheet(HashMap map, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
		String serialNo = StaticMethod.nullObject2String(map.get("serialNo"));
		String beanId = "";
		IMainService iMainService = null;
		if (sheetType.equals("31"))
		{
			beanId = "iResourceAffirmCrmService";
			iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iResourceAffirmMainManager");
			List list = iMainService.getMainListByParentSheetId(serialNo);
			if (list == null || list.size() == 0)
				beanId = "iResourceConfirmCrmService";
		} else
		if (sheetType.equals("32"))
		{
			beanId = "iBusinessDredgeCrmService";
			iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iBusinessDredgeMainManager");
			List list = iMainService.getMainListByParentSheetId(serialNo);
			if (list == null || list.size() == 0)
				beanId = "iBusinessImplementCrmService";
		}
		if (beanId.equals(""))
		{
			String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
			beanId = SheetMapingSchema.getInstance().getBeanId(sheetType, serviceType);
		}
		IInterfaceServiceManage mgr = (IInterfaceServiceManage)ApplicationContextHolder.getInstance().getBean(beanId);
		return mgr.untreadWorkSheet(map, attach);
	}

	public String checkinWorkSheet(HashMap map, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
		String serialNo = StaticMethod.nullObject2String(map.get("serialNo"));
		String beanId = "";
		IMainService iMainService = null;
		if (sheetType.equals("31"))
		{
			beanId = "iResourceAffirmCrmService";
			iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iResourceAffirmMainManager");
			List list = iMainService.getMainListByParentSheetId(serialNo);
			if (list == null || list.size() == 0)
				beanId = "iResourceConfirmCrmService";
		} else
		if (sheetType.equals("32"))
		{
			beanId = "iBusinessDredgeCrmService";
			iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iBusinessDredgeMainManager");
			List list = iMainService.getMainListByParentSheetId(serialNo);
			if (list == null || list.size() == 0)
				beanId = "iBusinessImplementCrmService";
		}
		if (beanId.equals(""))
		{
			String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
			beanId = SheetMapingSchema.getInstance().getBeanId(sheetType, serviceType);
		}
		IInterfaceServiceManage mgr = (IInterfaceServiceManage)ApplicationContextHolder.getInstance().getBean(beanId);
		return mgr.checkinWorkSheet(map, attach);
	}

	public String cancelWorkSheet(HashMap map, List attach)
		throws Exception
	{
		String sheetType = StaticMethod.nullObject2String(map.get("sheetType"));
		String serialNo = StaticMethod.nullObject2String(map.get("serialNo"));
		String beanId = "";
		IMainService iMainService = null;
		if (sheetType.equals("31"))
		{
			beanId = "iResourceAffirmCrmService";
			iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iResourceAffirmMainManager");
			List list = iMainService.getMainListByParentSheetId(serialNo);
			if (list == null || list.size() == 0)
				beanId = "iResourceConfirmCrmService";
		} else
		if (sheetType.equals("32"))
		{
			beanId = "iBusinessDredgeCrmService";
			iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean("iBusinessDredgeMainManager");
			List list = iMainService.getMainListByParentSheetId(serialNo);
			if (list == null || list.size() == 0)
				beanId = "iBusinessImplementCrmService";
		}
		if (beanId.equals(""))
		{
			String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
			beanId = SheetMapingSchema.getInstance().getBeanId(sheetType, serviceType);
		}
		IInterfaceServiceManage mgr = (IInterfaceServiceManage)ApplicationContextHolder.getInstance().getBean(beanId);
		if (mgr == null)
			throw new Exception("未找到相应工单");
		else
			return mgr.cancelWorkSheet(map);
	}
}