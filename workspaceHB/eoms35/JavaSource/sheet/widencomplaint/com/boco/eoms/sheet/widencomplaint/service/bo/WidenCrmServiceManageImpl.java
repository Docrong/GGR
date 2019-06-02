// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CrmServiceManageImpl.java

package com.boco.eoms.sheet.widencomplaint.service.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;

public class WidenCrmServiceManageImpl extends InterfaceServiceManageAbstract
	implements IInterfaceServiceManage
{

	public WidenCrmServiceManageImpl()
	{
	}

	public String getLinkBeanId()
	{
		return "iWidenComplaintLinkManager";
	}

	public String getMainBeanId()
	{
		return "iWidenComplaintMainManager";
	}

	public String getOperateName()
	{
		return "newWorkSheet";
	}

	public String getProcessTemplateName()
	{
		return "WidenComplaint";
	}

	public String getSendUser(Map map)
	{
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.InterfaceUser"));
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
						if(stepLimit.getTaskName()!=null&&stepLimit.getTaskName().equals("ExcuteHumTask")){
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
		map = loadDefaultMap(map, "config/widencomplaint-crm.xml", type);
		if (type.equals("newWorkSheet"))
		{
			String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.SendRoleId"));
			String complaintType = StaticMethod.nullObject2String(map.get("complaintType"));
			map = setComplaintType(map, complaintType);
			map.put("sendRoleId", sendRoleId);
			WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
			String maindealrolename = StaticMethod.nullObject2String(map.get("maindealrolename"));
			if (!"".equals(maindealrolename)) {
				maindealrolename = "1742" + maindealrolename;
				map.put("maindealrolename", maindealrolename);
			}
			String subRoleId = "";
			String cccustomAttributionValue = "";
			String localcomplaintType = StaticMethod.nullObject2String(map.get("complaintType4"));
			String perceptualclass = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("perceptualclass"));
			boolean ifperceptualclass = (perceptualclass.indexOf(localcomplaintType) != -1);
			if (ifperceptualclass && !"".equals(localcomplaintType)) {//感知类工单
				subRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("perceptualsubroleid"));
			} else {
				ITawSystemSubRoleManager tawSystemSubRoleManager = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManagerFlush");
				boolean flag = tawSystemSubRoleManager.getSubRoleByRole("1742",maindealrolename);
				String customAttribution = StaticMethod.nullObject2String(map.get("customAttribution"));
				String customAttributionValue = "";
				if (!"".equals(customAttribution)) {
					customAttribution = customAttribution.substring(3);
					customAttributionValue = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty(customAttribution));
					cccustomAttributionValue = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("CC" + customAttribution));
				}
				if (flag) {
					subRoleId = maindealrolename;
				} else if (!"".equals(customAttributionValue)) {
					subRoleId = customAttributionValue;
				} else {
					throw new Exception("工单生成失败,网格与归属地无法匹配");
					//subRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.AcceptRoleId"));
				}
			}
			Object type1 = map.get("complaintType1");
			Object type2 = map.get("complaintType2");
			Object type3 = map.get("complaintType");
			Object type4 = map.get("complaintType4");
			map = sm.setAcceptRole(subRoleId, map);
			IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
			if (!ifperceptualclass) {
				String mainCountycode = StaticMethod.nullObject2String(map.get("mainCountycode"));
				String ccsubroleid = "";
				if (!"".equals(mainCountycode)) {
					String sql = "select subroleid from WComplaintCountycode where countycode = '" + mainCountycode + "'";
					List taskList = services.getSheetAccessoriesList(sql);
					if (taskList.size() > 0) {
						Map subroleidMap = (Map)taskList.get(0);
						ccsubroleid = StaticMethod.nullObject2String(subroleidMap.get("subroleid"));
					}
				}
				String copyPerformer = "";
				String copyPerformerType = "";
				if (!"".equals(cccustomAttributionValue) && !"".equals(ccsubroleid)) {
					copyPerformer = cccustomAttributionValue + "," + ccsubroleid;
					copyPerformerType = "subrole,subrole";
				} else if (!"".equals(cccustomAttributionValue)) {
					copyPerformer = cccustomAttributionValue;
					copyPerformerType = "subrole";
				} else if (!"".equals(ccsubroleid)) {
					copyPerformer = ccsubroleid;
					copyPerformerType = "subrole";
				}
				if (!"".equals(copyPerformer)) {
					map.put("copyPerformerLeader",copyPerformer);
					map.put("copyPerformer",copyPerformer);
					map.put("copyPerformerType",copyPerformerType);
				}
			}
			
			//判断“是否重复投诉” by lyg Start
			String mainJkaccount = StaticMethod.nullObject2String(map.get("mainJkaccount"));//账号
			String customPhone = StaticMethod.nullObject2String(map.get("customPhone"));//客户电话
			String title = StaticMethod.nullObject2String(map.get("title"));//工单主题
//			String mainsSql = "SELECT M.ID, M.MAINJKACCOUNT, M.SHEETID, M.COMPLAINTTIME, M.CUSTOMPHONE, M.COMPLAINTDESC, M.SENDTIME, L.DEALDESC  FROM WIDENCOMPLAINT_MAIN M, WIDENCOMPLAINT_LINK L ,(SELECT l.mainid,MAX(l.operatetime) operatetime  FROM WIDENCOMPLAINT_MAIN M, WIDENCOMPLAINT_LINK L WHERE M.DELETED = '0' AND M.SENDTIME >= TRUNC(SYSDATE - 25)  AND m.ID=l.mainid AND l.activetemplateid ='102'  GROUP BY l.mainid ) t WHERE M.DELETED = '0' AND m.MAINJKACCOUNT IS NOT NULL AND m.MAINJKACCOUNT NOT LIKE '无宽带账号' AND M.SENDTIME >= TRUNC(SYSDATE - 25) AND (M.MAINJKACCOUNT = '"+mainJkaccount+"' OR  M.CUSTOMPHONE = '"+customPhone+"') AND M.ID = L.MAINID(+) AND l.activetemplateid (+)='102' AND l.mainid=t.mainid(+) AND l.operatetime=t.operatetime(+)  ORDER BY M.SENDTIME";
			String mainsSql = "select *  from widencomplaint_main  where deleted = '0' and sendtime >= trunc(sysdate - 5) and (mainJkaccount = '" + mainJkaccount + "' or customPhone = '" + customPhone + "')";
			List mainList = (List)services.getSheetAccessoriesList(mainsSql);
			String mainRepeatNum = "";
			String mainIfrepeat = "";
			if(mainList != null && mainList.size()>0){
				 mainRepeatNum = ((int) mainList.size())+"";
				 mainIfrepeat = "是";
			}else{
				 mainIfrepeat = "否";
			}
			title = title +" 是否重复投诉:"+mainIfrepeat;
			map.put("title", title);
			map.put("mainRepeatNum", mainRepeatNum);
			map.put("mainIfrepeat", mainIfrepeat);
			
			//保存附件
			String attachId = getAttach(attach);
			System.out.println("attachId=" + attachId);
			if (attachId != null && attachId.length() > 0){
				map.put("sheetAccessories", attachId);
			}
			//处理时限
			HashMap specialsMap = new HashMap();
			specialsMap.put("flowName", "WidenComplaint");
			specialsMap.put("specialty1", type1);
			specialsMap.put("specialty2", type2);
			specialsMap.put("specialty3", type3);
			specialsMap.put("specialty4", type4);
			map = this.getlimit(specialsMap, map);
			
//			WidenComplaintMethod widenComplaintMethod = new WidenComplaintMethod();
//			widenComplaintMethod.newSaveNonFlowData("", map);
			
			
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
			String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.SendRoleId"));
			map.put("sendRoleId", sendRoleId);
			WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
			map = sm.setAcceptRole("", map);
			String complaintType = StaticMethod.nullObject2String(map.get("complaintType"));
			map = setComplaintType(map, complaintType);
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
					map.put("phaseId", phaseId);
					map.put("dealPerformer", operateRoleId);
					map.put("dealPerformerLeader", operateRoleId);
					map.put("dealPerformerType", "subrole");
					//map.put("operateRoleId", operateRoleId);
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
		return "iWidenComplaintTaskManager";
	}

	public String getSheetAttachCode()
	{
		return "widencomplaint";
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
			System.out.println("complaintType=" + code);//1001020204
			ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
			String parentDictId = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.ComplaintTypeDictId"));
			
			String codeFlag = code.substring(0,6);
			if("009001".equals(codeFlag)){//营销类 009001025008001007
				for(int i=1;i*3<=code.length();i++){
					System.out.println("parentDictId009001" + i + "=" + parentDictId);
					String subcode = code.substring(0, i * 3);
					System.out.println("subcode009001" + i + "=" + subcode);
					TawSystemDictType dict = dictMgr.getDictByDictType(subcode, parentDictId);
					if (dict != null && dict.getDictId() != null)
					{
						String dictId = dict.getDictId();
						System.out.println("complaintType009001" + i + "=" + dictId);
						if (i == 3)
							map.put("complaintType", dictId);
						else
							map.put("complaintType" + i, dictId);
						parentDictId = dictId;
						continue;
					}
					if (i != 1)
						continue;
					String complaintType1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.ComplaintType1"));
					String complaintType2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.ComplaintType2"));
					map.put("complaintType1", complaintType1);
					map.put("complaintType2", complaintType2);
					break;
				}
			}else{
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
					String complaintType1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.ComplaintType1"));
					String complaintType2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.ComplaintType2"));
					map.put("complaintType1", complaintType1);
					map.put("complaintType2", complaintType2);
					break;
				}
			}
			
			

		}
		catch (Exception err)
		{
			System.out.println("没有找到映射的投诉分类");
			err.printStackTrace();
		}
		return map;
	}

//	public void finishNew(BaseMain main, Map interfaceMap)
//	{
//		super.finishNew(main, interfaceMap);
//		Object t2dealroleObj = interfaceMap.get("t2dealrole");
//		if (t2dealroleObj != null && !((String)t2dealroleObj).trim().equals(""))
//		{
////			Object type1 = interfaceMap.get("complaintType1");
////			Object type2 = interfaceMap.get("complaintType2");
////			Object type3 = interfaceMap.get("complaintType");
////			Object type4 = interfaceMap.get("complaintType4");
////			
////			
////			HashMap specialsMap = new HashMap();
////			specialsMap.put("flowName", "ComplaintProcess");
////			specialsMap.put("specialty1", type1);
////			specialsMap.put("specialty2", type2);
////			specialsMap.put("specialty3", type3);
////			specialsMap.put("specialty4", type4);
////			try {
////				interfaceMap = this.getlimit(specialsMap, interfaceMap);
////			} catch (Exception e4) {
////				e4.printStackTrace();
////			}
//			
//			System.out.println("T1自动流转规则已经匹配到，开始补充T1的link记录和task记录");
//			ITawSystemUserManager userManager = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");
//			IComplaintLinkManager linkservice = (IComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iComplaintLinkManager");
//			IComplaintTaskManager taskservice = (IComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iComplaintTaskManager");
//			String linkId = (String)interfaceMap.get("preLinkId");
//			String t1TaskId = (String)interfaceMap.get("t1TaskId");
//			Calendar calendar = Calendar.getInstance();
//			String t1acceptrole = "";
//			String userdeptid = "";
//			String userphone = "";
//			String transfermark = "";
//			Object t1acceptroleobj = interfaceMap.get("t1acceptrole");
//			if (t1acceptroleobj != null)
//				t1acceptrole = ((String)t1acceptroleobj).trim();
//			String t1acceptuser = "";
//			Object t1acceptuserobj = interfaceMap.get("t1acceptuser");
//			if (t1acceptuserobj != null)
//				t1acceptuser = ((String)t1acceptuserobj).trim();
//			if (!t1acceptuser.equals(""))
//			{
//				TawSystemUser user = userManager.getUserByuserid(t1acceptuser);
//				userdeptid = user.getDeptid();
//				userphone = user.getMobile();
//			}
//			String t2dealroleId = (String)interfaceMap.get("t2dealrole");
//			Object transfermarkObj = interfaceMap.get("transfermark");
//			if (transfermarkObj != null)
//				transfermark = ((String)transfermarkObj).trim();
//			ComplaintTask task = new ComplaintTask();
//			try
//			{
//				task.setId(t1TaskId);
//			}
//			catch (Exception e3)
//			{
//				e3.printStackTrace();
//			}
//			task.setTaskName("FirstExcuteHumTask");
//			task.setTaskDisplayName("一级处理");
//			task.setFlowName("ComplaintProcess");
//			task.setSendTime(new Date());
//			task.setSheetKey(main.getId());
//			task.setTaskStatus("5");
//			task.setSheetId(main.getSheetId());
//			task.setTitle(main.getTitle());
//			task.setOperateType("subrole");
//			task.setCreateTime(main.getSendTime());
//			task.setCreateYear(calendar.get(1));
//			task.setCreateMonth(calendar.get(2) + 1);
//			task.setCreateDay(calendar.get(5));
//			task.setOperateRoleId(t1acceptrole);
//			task.setTaskOwner(t1acceptuser);
//			task.setIfWaitForSubTask("false");
//			task.setParentTaskId("_AI:" + (new Date()).getTime());
//			task.setPreLinkId(linkId);
//			try
//			{
//				taskservice.addTask(task);
//			}
//			catch (Exception e2)
//			{
//				e2.printStackTrace();
//			}
//			ComplaintLink confirmBean = new ComplaintLink();
//			confirmBean.setActiveTemplateId("FirstExcuteHumTask");
//			try
//			{
//				String confirmBeanId = UUIDHexGenerator.getInstance().getID();
//				confirmBean.setId(confirmBeanId);
//			}
//			catch (Exception e1)
//			{
//				e1.printStackTrace();
//			}
//			confirmBean.setMainId(main.getId());
//			confirmBean.setOperateType(new Integer(61));
//			confirmBean.setOperateDay(calendar.get(5));
//			confirmBean.setOperateMonth(calendar.get(2) + 1);
//			confirmBean.setOperateYear(calendar.get(1));
//			confirmBean.setOperateUserId(t1acceptuser);
//			confirmBean.setOperateDeptId(userdeptid);
//			confirmBean.setOperateRoleId(t1acceptrole);
//			confirmBean.setOperaterContact(userphone);
//			confirmBean.setToOrgRoleId(t1acceptrole);
//			confirmBean.setToOrgType(new Integer(0));
//			confirmBean.setAiid(task.getId());
//			confirmBean.setNodeCompleteLimit(main.getSheetCompleteLimit());
//			calendar.add(13, 20);
//			confirmBean.setOperateTime(calendar.getTime());
//			confirmBean.setAcceptFlag(new Integer(1));
//			confirmBean.setCompleteFlag(new Integer(1));
//			try
//			{
//				linkservice.addLink(confirmBean);
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//			ComplaintLink linkbean = new ComplaintLink();
//			linkbean.setId(linkId);
//			linkbean.setMainId(main.getId());
//			calendar.add(13, 23);
//			linkbean.setOperateTime(calendar.getTime());
//			linkbean.setOperateType(new Integer(1));
//			linkbean.setOperateDay(calendar.get(5));
//			linkbean.setOperateMonth(calendar.get(2) + 1);
//			linkbean.setOperateYear(calendar.get(1));
//			linkbean.setOperateUserId(t1acceptuser);
//			linkbean.setOperateDeptId(userdeptid);
//			linkbean.setOperateRoleId(t1acceptrole);
//			linkbean.setOperaterContact(userphone);
//			linkbean.setToOrgRoleId(t2dealroleId);
//			linkbean.setToOrgType(new Integer(0));
//			linkbean.setAcceptFlag(new Integer(2));
//			linkbean.setNodeAcceptLimit(main.getSheetAcceptLimit());
//			linkbean.setNodeCompleteLimit(main.getSheetCompleteLimit());
//			linkbean.setCompleteFlag(new Integer(2));
//			linkbean.setActiveTemplateId("FirstExcuteHumTask");
//			linkbean.setLinkTransmitReason("T1自动移交");
//			try
//			{
//				linkservice.addLink(linkbean);
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}

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
	
	public void finishNew(BaseMain main, Map WpsMap)
	{
		super.finishNew(main, WpsMap);
		IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("WidenComplaint");
		try {
			baseSheet.newSaveNonFlowData("", WpsMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
