// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CrmServiceManageImpl.java

package com.boco.eoms.sheet.complaint.service.bo;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.complaint.model.ComplaintAutoT2;
import com.boco.eoms.sheet.complaint.model.ComplaintLink;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.service.*;
import com.boco.eoms.sheet.complaint.task.impl.ComplaintTask;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;
import com.boco.eoms.sheet.limit.util.SheetLimitUtil;


import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.xml.rpc.ServiceException;

public class CrmServiceManageImpl extends InterfaceServiceManageAbstract
	implements IInterfaceServiceManage
{

	public CrmServiceManageImpl()
	{
	}

	public String getLinkBeanId()
	{
		return "iComplaintLinkManager";
	}

	public String getMainBeanId()
	{
		return "iComplaintMainManager";
	}

	public String getOperateName()
	{
		return "newWorksheet";
	}

	public String getProcessTemplateName()
	{
		return "ComplaintProcess";
	}

	public String getSendUser(Map map)
	{
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}

	
	private Map getlimit(HashMap specialsMap,Map map)throws Exception{
		ISheetDealLimitManager mgr = (ISheetDealLimitManager) ApplicationContextHolder.getInstance().getBean("iSheetDealLimitManager");
		List sheetLimitList = mgr.getlevelLimitBySpecials(specialsMap);
		Date sheetCompleteLimit =StaticMethod.nullObject2Timestamp(map.get("sheetCompleteLimit"));
		if(sheetLimitList!=null&&sheetLimitList.size()>0){
			LevelLimit levelLimit = (LevelLimit) sheetLimitList.get(0);
			String leveid = levelLimit.getId();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, StaticMethod.null2int(levelLimit.getReplyLimit()));
			
			if(sheetCompleteLimit!=null){
				Calendar calendarlimit = Calendar.getInstance();
				calendarlimit.setTime(sheetCompleteLimit);
				calendarlimit.add(Calendar.MINUTE, -120);
				if(sheetCompleteLimit.after(calendar.getTime())){
					ISheetDealLimitManager mgrsheet = (ISheetDealLimitManager) ApplicationContextHolder.getInstance().getBean("iSheetDealLimitManager");
					List listsheet = mgrsheet.getStepLimitByLevel(leveid);
					Calendar calendarsheet = Calendar.getInstance();
					for (int i = 0; i < listsheet.size(); i++) {
						StepLimit stepLimit = (StepLimit) listsheet.get(i);
						if(stepLimit.getTaskName()!=null&&stepLimit.getTaskName().equals("FirstExcuteHumTask")){
							calendarsheet.add(Calendar.MINUTE, StaticMethod.null2int(stepLimit.getCompleteLimit()));
							map.put("mainCompleteLimitT1",calendarsheet.getTime() );
						}
						if(stepLimit.getTaskName()!=null&&stepLimit.getTaskName().equals("SecondExcuteHumTask")){
							calendarsheet.add(Calendar.MINUTE, StaticMethod.null2int(stepLimit.getCompleteLimit()));
							map.put("mainCompleteLimitT2",calendarsheet.getTime() );
						}
					}
				}else{
					map.put("mainCompleteLimitT1",calendarlimit.getTime() );
					map.put("mainCompleteLimitT2",calendarlimit.getTime() );
				}
			}
			
			
		}else{
			Calendar calendarlimit = Calendar.getInstance();
			calendarlimit.setTime(sheetCompleteLimit);
			calendarlimit.add(Calendar.MINUTE, -120);
			map.put("mainCompleteLimitT1",calendarlimit.getTime() );
			map.put("mainCompleteLimitT2",calendarlimit.getTime() );
		}
		return map;
	}
	public Map initMap(Map map, List attach, String type)
		throws Exception
	{
		map = loadDefaultMap(map, "config/complaint-crm.xml", type);
		if (type.equals("newWorkSheet"))
		{
			String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendRoleId"));
			String complaintType = StaticMethod.nullObject2String(map.get("complaintType"));
			map = setComplaintType(map, complaintType);
			map.put("sendRoleId", sendRoleId);
			String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.AcceptGroupId"));
			String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
			WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
			String subRoleId = "";
			StringBuffer where = new StringBuffer();
			where.append("where colseSwitch = 'yes' and remark2 = 'autoT2' ");
			Object type1 = map.get("complaintType1");
			Object type2 = map.get("complaintType2");
			Object type3 = map.get("complaintType");
			Object type4 = map.get("complaintType4");
			if (type1 != null && !((String)type1).trim().equals(""))
				where.append(" and complaintType1 = '").append(((String)type1).trim()).append("'");
			else
				where.append(" and complaintType1 is null ");
			if (type2 != null && !((String)type2).trim().equals(""))
				where.append(" and complaintType2 = '").append(((String)type2).trim()).append("'");
			else
				where.append(" and complaintType2 is null ");
			
			if (type3 != null && !((String)type3).trim().equals(""))
				where.append(" and complaintType3 = '").append(((String)type3).trim()).append("'");
			else
				where.append(" and complaintType3 is null ");
			
			if (type4 != null && !((String)type4).trim().equals(""))
				where.append(" and complaintType4 = '").append(((String)type4).trim()).append("'");
			else
				where.append(" and complaintType4 is null ");
//			Object faultSite = map.get("customAttribution");
			Object faultSite = map.get("mainFaultCity");
			if(faultSite == null || ((String)faultSite).trim().equals("")){
				faultSite = map.get("customAttribution");
			}
			if (faultSite != null && !((String)faultSite).trim().equals(""))
			{
				String faultSiteformate[] = ((String)faultSite).trim().split(":");
				where.append(" and faultSite like '").append(faultSiteformate[0]).append("'");
			} else
			{
				where.append(" and faultSite is null ");
			}
			Map condition = new HashMap();
			condition.put("where", where.toString());
			Integer pageSize = new Integer(-1);
			int aTotal[] = new int[1];
			IComplaintAutoT2Manager complaintAutoT2Manager = (IComplaintAutoT2Manager)ApplicationContextHolder.getInstance().getBean("iComplaintAutoT2Manager");
			List list = complaintAutoT2Manager.getObjectsByCondtion(new Integer(0), pageSize, aTotal, condition, "recoder");
			if (list.size() > 0 && StaticMethod.nullObject2String(map.get("title")).indexOf("预警") == -1 && (((String)map.get("bdeptContactPhone")).trim().equals("1030801") || ((String)map.get("bdeptContactPhone")).trim().equals("1030804") || ((String)map.get("bdeptContactPhone")).trim().equals("0")))
			{
				System.out.println("已经匹配到T1自动流转规则！");
				ComplaintAutoT2 autoT2 = (ComplaintAutoT2)list.iterator().next();
				String dealPerformer = autoT2.getDealRoleT2();
				String autoacceptrole = autoT2.getAutoAcceptRoleT1();
				String autoacceptuser = autoT2.getRemark1();
				String transfermark = autoT2.getRemark2();
				subRoleId = dealPerformer;
				map.put("phaseId", "SecondExcuteHumTask");
				map.put("t2dealrole", dealPerformer);
				map.put("t1acceptrole", autoacceptrole);
				map.put("t1acceptuser", autoacceptuser);
				map.put("transfermark", transfermark);
				map.put("preLinkId", UUIDHexGenerator.getInstance().getID());
				map.put("t1TaskId", UUIDHexGenerator.getInstance().getID());
	//			map.put("firstExcutePerformer", autoacceptuser);
			}
			if (subRoleId.equals(""))
			{
				StringBuffer where1 = new StringBuffer();
				where1.append(" roleid = '214' ");
				String areaid = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("customAttribution", StaticMethod.nullObject2String(map.get("customAttribution")).toString());
				if (areaid != null && areaid != "")
					where1.append(" and area ='").append(areaid.trim()).append("'");
				else
					where1.append("and area is null ");
				where1.append(" and (type1 = '").append(((String)type1).trim()).append(" ' or type2 is null )");
				where1.append(" and (type2 = '").append(((String)type2).trim()).append("' or type3 is null )");
				where1.append(" and (type3 = '").append(((String)type3).trim()).append("' or type4 is null )");
				System.out.println("------------------where1:sql----------" + where1);
				ITawSystemSubRoleManager tawsubrole = (ITawSystemSubRoleManager)ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
				List subroles = tawsubrole.getTawSystemSubRoles(where1.toString());
				if( subroles.size()>0&& "101062502".equals(((String)type1).trim())){
					TawSystemSubRole subrolet = (TawSystemSubRole) subroles.get(0);
					String subRoleT1 = subrolet.getId();
			     	System.out.println("----------------subRoleT1-------------"+subRoleT1);
					subRoleId = subRoleT1;
					}
					else 
					{
						System.out.println("未找到匹配角色，使用默认角色");
						subRoleId = XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.AcceptRoleId");
					} 
			}
			map = sm.setAcceptRole(subRoleId, map);
			//保存附件
			String attachId = getAttach(attach);
			System.out.println("attachId=" + attachId);
			if (attachId != null && attachId.length() > 0){
				map.put("sheetAccessories", attachId);
			}
			//处理时限
			HashMap specialsMap = new HashMap();
			specialsMap.put("flowName", "ComplaintProcess");
			specialsMap.put("specialty1", type1);
			specialsMap.put("specialty2", type2);
			specialsMap.put("specialty3", type3);
			specialsMap.put("specialty4", type4);
			map = this.getlimit(specialsMap, map);
			
			
			
			
		} else if (type.equals("renewWorkSheet"))
		{
			String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
			if (sheetId == null || sheetId.equals(""))
				throw new Exception("sheetId为空");
			BaseMain main = null;
			IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
			ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(getTaskBeanId());
			List list = iMainService.getMainListByParentSheetId(sheetId);
			if (list.size() > 0)
			{
				boolean b = false;
				main = (BaseMain)list.get(0);
				List taskList = iTaskService.getCurrentUndoTask(main.getId());
				if (taskList != null)
				{
					for (int i = 0; i < taskList.size(); i++)
					{
						ITask task = (ITask)taskList.get(i);
						if (!task.getTaskOwner().equals(main.getSendRoleId()) && !task.getTaskOwner().equals(main.getSendUserId()) && !task.getTaskOwner().equals(main.getSendDeptId()))
							continue;
						b = true;
						break;
					}

				}
				if (!b)
					throw new Exception("工单未被驳回，不能重派");
			} else
			{
				throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
			}
			IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
			String linkSql = "SELECT l.operateroleid,l.activetemplateid FROM complaint_main m,complaint_link l,(SELECT l.mainid,MAX(l.operatetime) operatetime  FROM complaint_main m,complaint_link l WHERE m.sheetid='"+main.getSheetId()+"'AND m.ID=l.mainid AND l.operatetype=46  GROUP BY l.mainid  ) t WHERE m.sheetid='"+main.getSheetId()+"' AND m.ID=l.mainid AND l.operatetype=46 AND l.mainid=t.mainid AND l.operatetime=t.operatetime ";
			List linkList = (List) sqlMgr.getSheetAccessoriesList(linkSql);
			WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
			String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendRoleId"));
			if(linkList != null && linkList.size()>0){
				String operateroleId = StaticMethod.nullObject2String(((Map)linkList.get(0)).get("operateroleid"));
				String activetemplateid = StaticMethod.nullObject2String(((Map)linkList.get(0)).get("activetemplateid"));
				map = sm.setAcceptRole(operateroleId, map);
				map.put("dealPerformer",operateroleId);
				map.put("dealPerformerType","subrole");
				map.put("dealPerformerLeader",operateroleId);
				map.put("toOrgRoleId",operateroleId);
				map.put("phaseId", activetemplateid);
			}else{
				map.put("dealPerformer",sendRoleId);
				map.put("dealPerformerType","subrole");
				map.put("dealPerformerLeader",sendRoleId);
				map.put("toOrgRoleId",sendRoleId);
				map.put("sendRoleId", sendRoleId);
				map.put("phaseId", "FirstExcuteHumTask");
			}
//			map = sm.setAcceptRole("", map);
			String complaintType = StaticMethod.nullObject2String(map.get("complaintType"));
			map = setComplaintType(map, complaintType);
			
//			保存附件
			String attachId = getAttach(attach);
			System.out.println("attachId renewWorkSheet=" + attachId);
			if (attachId != null && attachId.length() > 0){
				map.put("sheetAccessories", attachId);
			}
		} else
			if (type.equals("untreadWorkSheet"))
		{
		String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
		if (sheetId == null || sheetId.equals(""))
			throw new Exception("sheetId为空");
		BaseMain main = null;
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
		ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(getTaskBeanId());
		List list = iMainService.getMainListByParentSheetId(sheetId);
		if (list.size() > 0)
		{
			boolean b = false;
			main = (BaseMain)list.get(0);
			List taskList = iTaskService.getCurrentUndoTask(main.getId());
			String preLinkId = "";
			if (taskList != null)
			{
				for (int i = 0; i < taskList.size(); i++)
				{
					ITask task = (ITask)taskList.get(i);
					if (!task.getTaskOwner().equals(main.getSendRoleId()) && !task.getTaskOwner().equals(main.getSendUserId()) && !task.getTaskOwner().equals(main.getSendDeptId()))
						continue;
					b = true;
					preLinkId = task.getPreLinkId();
					break;
				}

			}
			if (!b)
				throw new Exception("工单未回复，不能退回");
			BocoLog.info(this, "taskPreLinkId====" + preLinkId);
			if (preLinkId != null && !"".equals(preLinkId))
			{
				ILinkService linkService = (ILinkService)ApplicationContextHolder.getInstance().getBean(getLinkBeanId());
				BaseLink link = linkService.getSingleLinkPO(preLinkId);
				String activeTemplateId = link.getActiveTemplateId();
				String operateRoleId = "";
				String phaseId = "";
				if ("CheckingHumTask".equals(activeTemplateId))
				{
					BocoLog.info(this, "reply from CheckingHumTask");
					String aiid = link.getAiid();
					ITask task = iTaskService.getSinglePO(aiid);
					String prelinid = task.getPreLinkId();
					BaseLink huifulink = linkService.getSingleLinkPO(prelinid);
					operateRoleId = huifulink.getOperateRoleId();
					phaseId = huifulink.getActiveTemplateId();
				} else
				{
					BocoLog.info(this, "reply from FirstExcuteHumTask or second");
					operateRoleId = link.getOperateRoleId();
					phaseId = link.getActiveTemplateId();
				}
				IComplaintFlowManager bfs = (IComplaintFlowManager)ApplicationContextHolder.getInstance().getBean("iComplaintFlowManager");
				Map control = new HashMap();
				HashMap sessionMap = new HashMap();
				String variableName = "control";
				String processId = main.getPiid();
				sessionMap.put("userId", main.getSendUserId());
				sessionMap.put("password", "111");
				control = bfs.getVariable(processId, variableName, sessionMap);
				if ("FirstExcuteHumTask".equals(phaseId))
				{
					BocoLog.info(this, "reply from FirstExcuteHumTask");
					control.put("preExcutePerformer", operateRoleId);
					control.put("firstExcutePerformer", operateRoleId);
					control.put("secondExcutePerformer", "dddd");
				} else
				{
					BocoLog.info(this, "reply from FirstExcuteHumTask");
					control.put("preExcutePerformer", operateRoleId);
					control.put("firstExcutePerformer", "ddd");
					control.put("secondExcutePerformer", operateRoleId);
				}
				bfs.setVariable(processId, variableName, control, sessionMap);
			}

		} else
		{
			throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
		}
	}
	return map;
	}

	public String getTaskBeanId()
	{
		return "iComplaintTaskManager";
	}

	public String getSheetAttachCode()
	{
		return "complaint";
	}

	public static void main(String arg[])
	{
		try
		{
			String serviceType = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("yesOrNo", "是");
			System.out.println(serviceType);
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
	}

	public Map setComplaintType(Map map, String code)
	{
		try
		{
			System.out.println("complaintType=" + code);
			ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
			String parentDictId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.ComplaintTypeDictId"));
			for (int i = 1; i * 2 <= (code.length()-4); i++)
			{
				System.out.println("parentDictId" + i + "=" + parentDictId);
				String subcode = code.substring(0, i * 2+4);
				System.out.println("subcode" + i + "=" + subcode);
				TawSystemDictType dict = dictMgr.getDictByDictType(subcode, parentDictId);
				if (dict != null && dict.getDictId() != null)
				{
					String dictId = dict.getDictId();
					System.out.println("complaintType" + i + "=" + dictId);
					if (i == 3)
						map.put("complaintType", dictId);
					else
						map.put("complaintType" + i, dictId);
					parentDictId = dictId;
					continue;
				}
				if (i != 1)
					continue;
				String complaintType1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.ComplaintType1"));
				String complaintType2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.ComplaintType2"));
				map.put("complaintType1", complaintType1);
				map.put("complaintType2", complaintType2);
				break;
			}

		}
		catch (Exception err)
		{
			System.out.println("没有找到映射的投诉分类");
			err.printStackTrace();
		}
		return map;
	}

	public void finishNew(BaseMain main, Map interfaceMap)
	{
		super.finishNew(main, interfaceMap);
		Object t2dealroleObj = interfaceMap.get("t2dealrole");
		if (t2dealroleObj != null && !((String)t2dealroleObj).trim().equals(""))
		{
//			Object type1 = interfaceMap.get("complaintType1");
//			Object type2 = interfaceMap.get("complaintType2");
//			Object type3 = interfaceMap.get("complaintType");
//			Object type4 = interfaceMap.get("complaintType4");
//			
//			
//			HashMap specialsMap = new HashMap();
//			specialsMap.put("flowName", "ComplaintProcess");
//			specialsMap.put("specialty1", type1);
//			specialsMap.put("specialty2", type2);
//			specialsMap.put("specialty3", type3);
//			specialsMap.put("specialty4", type4);
//			try {
//				interfaceMap = this.getlimit(specialsMap, interfaceMap);
//			} catch (Exception e4) {
//				e4.printStackTrace();
//			}
			
			System.out.println("T1自动流转规则已经匹配到，开始补充T1的link记录和task记录");
			ITawSystemUserManager userManager = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");
			IComplaintLinkManager linkservice = (IComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
			IComplaintTaskManager taskservice = (IComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
			String linkId = (String)interfaceMap.get("preLinkId");
			String t1TaskId = (String)interfaceMap.get("t1TaskId");
			Calendar calendar = Calendar.getInstance();
			String t1acceptrole = "";
			String userdeptid = "";
			String userphone = "";
			String transfermark = "";
			Object t1acceptroleobj = interfaceMap.get("t1acceptrole");
			if (t1acceptroleobj != null)
				t1acceptrole = ((String)t1acceptroleobj).trim();
			String t1acceptuser = "";
			Object t1acceptuserobj = interfaceMap.get("t1acceptuser");
			if (t1acceptuserobj != null)
				t1acceptuser = ((String)t1acceptuserobj).trim();
			if (!t1acceptuser.equals(""))
			{
				TawSystemUser user = userManager.getUserByuserid(t1acceptuser);
				userdeptid = user.getDeptid();
				userphone = user.getMobile();
			}
			String t2dealroleId = (String)interfaceMap.get("t2dealrole");
			Object transfermarkObj = interfaceMap.get("transfermark");
			if (transfermarkObj != null)
				transfermark = ((String)transfermarkObj).trim();
			ComplaintTask task = new ComplaintTask();
			try
			{
				task.setId(t1TaskId);
			}
			catch (Exception e3)
			{
				e3.printStackTrace();
			}
			task.setTaskName("FirstExcuteHumTask");
			task.setTaskDisplayName("一级处理");
			task.setFlowName("ComplaintProcess");
			task.setSendTime(new Date());
			task.setSheetKey(main.getId());
			task.setTaskStatus("5");
			task.setSheetId(main.getSheetId());
			task.setTitle(main.getTitle());
			task.setOperateType("subrole");
			task.setCreateTime(main.getSendTime());
			task.setCreateYear(calendar.get(1));
			task.setCreateMonth(calendar.get(2) + 1);
			task.setCreateDay(calendar.get(5));
			task.setOperateRoleId(t1acceptrole);
			task.setTaskOwner(t1acceptuser);
			task.setIfWaitForSubTask("false");
			task.setParentTaskId("_AI:" + (new Date()).getTime());
			task.setPreLinkId(linkId);
			try
			{
				taskservice.addTask(task);
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
			ComplaintLink confirmBean = new ComplaintLink();
			confirmBean.setActiveTemplateId("FirstExcuteHumTask");
			try
			{
				String confirmBeanId = UUIDHexGenerator.getInstance().getID();
				confirmBean.setId(confirmBeanId);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			confirmBean.setMainId(main.getId());
			confirmBean.setOperateType(new Integer(61));
			confirmBean.setOperateDay(calendar.get(5));
			confirmBean.setOperateMonth(calendar.get(2) + 1);
			confirmBean.setOperateYear(calendar.get(1));
			confirmBean.setOperateUserId(t1acceptuser);
			confirmBean.setOperateDeptId(userdeptid);
			confirmBean.setOperateRoleId(t1acceptrole);
			confirmBean.setOperaterContact(userphone);
			confirmBean.setToOrgRoleId(t1acceptrole);
			confirmBean.setToOrgType(new Integer(0));
			confirmBean.setAiid(task.getId());
			confirmBean.setNodeCompleteLimit(main.getSheetCompleteLimit());
			calendar.add(13, 20);
			confirmBean.setOperateTime(calendar.getTime());
			confirmBean.setAcceptFlag(new Integer(1));
			confirmBean.setCompleteFlag(new Integer(1));
			try
			{
				linkservice.addLink(confirmBean);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			ComplaintLink linkbean = new ComplaintLink();
			linkbean.setId(linkId);
			linkbean.setMainId(main.getId());
			calendar.add(13, 23);
			linkbean.setOperateTime(calendar.getTime());
			linkbean.setOperateType(new Integer(1));
			linkbean.setOperateDay(calendar.get(5));
			linkbean.setOperateMonth(calendar.get(2) + 1);
			linkbean.setOperateYear(calendar.get(1));
			linkbean.setOperateUserId(t1acceptuser);
			linkbean.setOperateDeptId(userdeptid);
			linkbean.setOperateRoleId(t1acceptrole);
			linkbean.setOperaterContact(userphone);
			linkbean.setToOrgRoleId(t2dealroleId);
			linkbean.setToOrgType(new Integer(0));
			linkbean.setAcceptFlag(new Integer(2));
			linkbean.setNodeAcceptLimit(main.getSheetAcceptLimit());
			linkbean.setNodeCompleteLimit(main.getSheetCompleteLimit());
			linkbean.setCompleteFlag(new Integer(2));
			linkbean.setActiveTemplateId("FirstExcuteHumTask");
			linkbean.setLinkTransmitReason("T1自动移交");
			try
			{
				linkservice.addLink(linkbean);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public void finishNew(BaseMain main, Map interfaceMap, Map wpsMap)
	{
		super.finishNew(main, interfaceMap);
		Object t2dealroleObj = interfaceMap.get("t2dealrole");
		if (t2dealroleObj != null
				&& !((String) t2dealroleObj).trim().equals("")) {
			// Object type1 = interfaceMap.get("complaintType1");
			// Object type2 = interfaceMap.get("complaintType2");
			// Object type3 = interfaceMap.get("complaintType");
			// Object type4 = interfaceMap.get("complaintType4");
			//			
			//			
			// HashMap specialsMap = new HashMap();
			// specialsMap.put("flowName", "ComplaintProcess");
			// specialsMap.put("specialty1", type1);
			// specialsMap.put("specialty2", type2);
			// specialsMap.put("specialty3", type3);
			// specialsMap.put("specialty4", type4);
			// try {
			// interfaceMap = this.getlimit(specialsMap, interfaceMap);
			// } catch (Exception e4) {
			// e4.printStackTrace();
			// }

			System.out.println("T1自动流转规则已经匹配到，开始补充T1的link记录和task记录");
			ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemUserSaveManagerFlush");
			IComplaintMainManager mainservice = (IComplaintMainManager) ApplicationContextHolder
					.getInstance().getBean("iComplaintMainManager");
			IComplaintLinkManager linkservice = (IComplaintLinkManager) ApplicationContextHolder
					.getInstance().getBean("iComplaintLinkManager");
			IComplaintTaskManager taskservice = (IComplaintTaskManager) ApplicationContextHolder
					.getInstance().getBean("iComplaintTaskManager");
			String linkId = (String) interfaceMap.get("preLinkId");
			String t1TaskId = (String) interfaceMap.get("t1TaskId");
			Calendar calendar = Calendar.getInstance();
			String t1acceptrole = "";
			String userdeptid = "";
			String userphone = "";
			String transfermark = "";
			Object t1acceptroleobj = interfaceMap.get("t1acceptrole");
			if (t1acceptroleobj != null)
				t1acceptrole = ((String) t1acceptroleobj).trim();
			String t1acceptuser = "";
			Object t1acceptuserobj = interfaceMap.get("t1acceptuser");
			if (t1acceptuserobj != null)
				t1acceptuser = ((String) t1acceptuserobj).trim();
			if (!t1acceptuser.equals("")) {
				TawSystemUser user = userManager.getUserByuserid(t1acceptuser);
				userdeptid = user.getDeptid();
				userphone = user.getMobile();
			}
			String t2dealroleId = (String) interfaceMap.get("t2dealrole");
			Object transfermarkObj = interfaceMap.get("transfermark");
			if (transfermarkObj != null)
				transfermark = ((String) transfermarkObj).trim();
			ComplaintTask task = new ComplaintTask();
			try {
				task.setId(t1TaskId);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			task.setTaskName("FirstExcuteHumTask");
			task.setTaskDisplayName("一级处理");
			task.setFlowName("ComplaintProcess");
			task.setSendTime(new Date());
			task.setSheetKey(main.getId());
			task.setTaskStatus("5");
			task.setSheetId(main.getSheetId());
			task.setTitle(main.getTitle());
			task.setOperateType("subrole");
			task.setCreateTime(main.getSendTime());
			task.setCreateYear(calendar.get(1));
			task.setCreateMonth(calendar.get(2) + 1);
			task.setCreateDay(calendar.get(5));
			task.setOperateRoleId(t1acceptrole);
			task.setTaskOwner(t1acceptuser);
			task.setIfWaitForSubTask("false");
			task.setParentTaskId("_AI:" + (new Date()).getTime());
			task.setPreLinkId(linkId);
			try {
				taskservice.addTask(task);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			ComplaintLink confirmBean = new ComplaintLink();
			confirmBean.setActiveTemplateId("FirstExcuteHumTask");
			try {
				String confirmBeanId = UUIDHexGenerator.getInstance().getID();
				confirmBean.setId(confirmBeanId);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			confirmBean.setMainId(main.getId());
			confirmBean.setOperateType(new Integer(61));
			confirmBean.setOperateDay(calendar.get(5));
			confirmBean.setOperateMonth(calendar.get(2) + 1);
			confirmBean.setOperateYear(calendar.get(1));
			confirmBean.setOperateUserId(t1acceptuser);
			confirmBean.setOperateDeptId(userdeptid);
			confirmBean.setOperateRoleId(t1acceptrole);
			confirmBean.setOperaterContact(userphone);
			confirmBean.setToOrgRoleId(t1acceptrole);
			confirmBean.setToOrgType(new Integer(0));
			confirmBean.setAiid(task.getId());
			confirmBean.setNodeCompleteLimit(main.getSheetCompleteLimit());
			calendar.add(13, 20);
			confirmBean.setOperateTime(calendar.getTime());
			confirmBean.setAcceptFlag(new Integer(1));
			confirmBean.setCompleteFlag(new Integer(1));
			try {
				linkservice.addLink(confirmBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ComplaintLink linkbean = new ComplaintLink();
			linkbean.setId(linkId);
			linkbean.setMainId(main.getId());
			calendar.add(13, 23);
			linkbean.setOperateTime(calendar.getTime());
			linkbean.setOperateType(new Integer(1));
			linkbean.setOperateDay(calendar.get(5));
			linkbean.setOperateMonth(calendar.get(2) + 1);
			linkbean.setOperateYear(calendar.get(1));
			linkbean.setOperateUserId(t1acceptuser);
			linkbean.setOperateDeptId(userdeptid);
			linkbean.setOperateRoleId(t1acceptrole);
			linkbean.setOperaterContact(userphone);
			linkbean.setToOrgRoleId(t2dealroleId);
			linkbean.setToOrgType(new Integer(0));
			linkbean.setAcceptFlag(new Integer(2));
			linkbean.setNodeAcceptLimit(main.getSheetAcceptLimit());
			linkbean.setNodeCompleteLimit(main.getSheetCompleteLimit());
			linkbean.setCompleteFlag(new Integer(2));
			linkbean.setActiveTemplateId("FirstExcuteHumTask");
			linkbean.setLinkTransmitReason("T1自动移交");
			try {
				linkservice.addLink(linkbean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HashMap mainMap = (HashMap) wpsMap.get("main");
			ComplaintMain complaintmain = null;
			try {
				complaintmain = (ComplaintMain) mainservice
						.getMainObject().getClass().newInstance();//这里必须newInstance 否则一直是同一个main对象
			} catch (IllegalAccessException e2) {
				e2.printStackTrace();
			} catch (InstantiationException e2) {
				e2.printStackTrace();
			}
			try {
				SheetBeanUtils.populate(complaintmain, mainMap);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			System.out.println("----------eomsinfo --t2dealroleId-"
					+ t2dealroleId);
			StringBuffer where1 = new StringBuffer();
			where1.append(" roleid = '215' and type5 = '5' ");
			where1.append("and id ='" + t2dealroleId + "'");
			System.out.println(complaintmain.getSheetId()+";----eomsinfo ---where +" + where1.toString());
			ITawSystemSubRoleManager tawsubrole = (ITawSystemSubRoleManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemSubRoleManager");
			List subroles = tawsubrole.getTawSystemSubRoles(where1.toString());
			final ComplaintMain finalMain = complaintmain;//线程里静态方法入参有要求 这里重新定义用final修饰
			if (subroles.size() > 0) {

				System.out.println("--serialNo-" + finalMain.getSheetId()
						+ "-complaintDesc-" + finalMain.getComplaintDesc());
				System.out
						.println("-faultSite-" + finalMain.getFaultSite());
				System.out.println("--customAttribution-"
						+ finalMain.getCustomAttribution()
						+ "-customPhone-" + finalMain.getCustomPhone());
				System.out.println("--startDealCity-"
						+ finalMain.getStartDealCity() + "-bdeptContact-"
						+ finalMain.getBdeptContact());

				Runnable invokeAutoSplit = new Runnable() {

					public void run() {

						try {
							int result = EomsReplyTicketClient
									.setEomsInfo(finalMain);
							System.out.println("----setEomsTicket---return---"
									+ result);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				Thread t = new Thread(invokeAutoSplit);
				t.start();
				try {
					Thread.sleep(5000L);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String subroleid = StaticMethod.nullObject2String(XmlManage
					.getFile("/config/netOptComplainSheet.xml").getProperty(
							"interfaceType.subroleId"));
			if (t2dealroleId != null && subroleid.equals(t2dealroleId)) {
				try {

					Runnable invokeAutoSplitSend = new Runnable() {

						public void run() {
							System.out.println("runnable=sheetId="+finalMain.getSheetId());
							try {
								Thread.sleep(10000L);
							} catch (Exception e) {
								e.printStackTrace();
							}

							try {
								IComplaintMainManager mainservice1 = (IComplaintMainManager) ApplicationContextHolder
										.getInstance().getBean(
												"iComplaintMainManager");
								String netResult = EomsReplyNetClinet
										.sendEomsInfo(finalMain);
								System.out
										.println("----setEomsNet---netResult---"
												+ netResult);
								if ("0".equals(netResult)) {
									finalMain
											.setOtherUserDesc("netcomplaint");
									mainservice1
											.saveOrUpdateMain(finalMain);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
					System.out.println("start runnable sheetid="+finalMain.getSheetId());
					Thread t = new Thread(invokeAutoSplitSend);
					t.start();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		}
	}
	public void finishNetDeal(BaseMain main, Map map, String dealType)
	{
		 String sheetid = main.getSheetId();
		 IComplaintMainManager mainservice = (IComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iComplaintMainManager");				
	    ComplaintMain mainObject = (ComplaintMain) mainservice.getMainBySheetId(sheetid);			
		 String otheruser = mainObject.getOtherUserDesc();
			String operateuser = StaticMethod.nullObject2String(map.get("operateUserId"));
		 if(!"".equals(otheruser)){
		if (dealType.equals("untreadWorkSheet")){
			String untreaddesc = StaticMethod.nullObject2String(map.get("remark"));
		try {
			String untreadResult = EomsReplyNetClinet.withdrawWorkSheet(sheetid, operateuser, untreaddesc);
			System.out.println("--sheetid--"+sheetid+"---untreadResult----"+untreadResult);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else if (dealType.equals("suggestWorkSheet")){
		String suggestdesc = StaticMethod.nullObject2String(map.get("remark"));
		try {
			String suggestResult  = EomsReplyNetClinet.reminderWorkSheet(sheetid, operateuser, suggestdesc);
			System.out.println("--sheetid--"+sheetid+"---suggestResult----"+suggestResult);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else if (dealType.equals("checkinWorkSheet")){
		String  fileDesc = StaticMethod.nullObject2String(map.get("endResult"));
		String  satisfactdegree = StaticMethod.nullObject2String(map.get("holdStatisfied"));
		String  isFile = "1030101";
		  try {
			String checkinResult  = EomsReplyNetClinet.fileWorkSheet(sheetid, operateuser, satisfactdegree,isFile,fileDesc);
			System.out.println("--sheetid--"+sheetid+"---checkinResult----"+checkinResult);
		  } catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
		 }
	}
	
	
	public HashMap wpsMapDeal(HashMap wpsMap, Map paraMap)
	{
		Object t2dealroleObj = paraMap.get("t2dealrole");
		if (t2dealroleObj != null && !((String)t2dealroleObj).trim().equals(""))
		{
			System.out.println("=====进入子类，最终的处理=====");
			Map link = (HashMap)wpsMap.get("link");
			link.put("activeTemplateId", "FirstExcuteHumTask");
			link.put("operateType", new Integer(1));
			link.put("toOrgType", new Integer(1));
			link.put("operateUserId", paraMap.get("t1acceptuser"));
			link.put("operateDeptId", paraMap.get("userdeptid"));
			link.put("operateRoleId", paraMap.get("t1acceptrole"));
			link.put("toOrgRoleId", paraMap.get("t2dealroleId"));
			link.put("linkTransmitReason", paraMap.get("transfermark"));
			link.put("aiid", paraMap.get("t1TaskId"));
			Calendar calendar = Calendar.getInstance();
			calendar.add(13, 30);
			link.put("operateTime", calendar.getTime());
			wpsMap.put("link", link);
			return wpsMap;
		} else
		{
			return wpsMap;
		}
	}
}
