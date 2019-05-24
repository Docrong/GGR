package com.boco.eoms.sheet.businessbackout.service.impl;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.autoCrm.model.Autot1Crm;
import com.boco.eoms.sheet.autoCrm.service.IAutoT1CrmManager;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.businessbackout.model.BusinessBackoutLink;
import com.boco.eoms.sheet.businessbackout.service.IBusinessBackoutLinkManager;
import com.boco.eoms.sheet.businessbackout.service.IBusinessBackoutTaskManager;
import com.boco.eoms.sheet.businesschange.service.IBusinessChangeLinkManager;
import com.boco.eoms.sheet.businesschange.service.IBusinessChangeTaskManager;
import com.boco.eoms.sheet.businessdredge.model.BusinessDredgeLink;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class BusinessBackoutCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage{

	public String getLinkBeanId() {
		// TODO Auto-generated method stub
		return "iBusinessBackoutLinkManager";
	}

	public String getMainBeanId() {
		// TODO Auto-generated method stub
		return "iBusinessBackoutMainManager";
	}

	public String getOperateName() {
		// TODO Auto-generated method stub
		return "newSheet";
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "BusinessBackoutMainFlowProcess";
	}

	public String getSendUser(Map map) {
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessBackout-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}

	public String getTaskBeanId() {
		// TODO Auto-generated method stub
		return "ibusinessbackoutTaskManager";
	}
	public HashMap addPara(HashMap hashMap){
		try{
			System.out.println("star corrKey...");
			hashMap.put("corrKey",UUIDHexGenerator.getInstance().getID());
			System.out.println("corrKey="+hashMap.get("corrKey").toString());
		}catch(Exception err){
			err.printStackTrace();
		}
		return hashMap;
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		try{
			Integer sheetType = new Integer(0);
			Object stobj = map.get("sheetType");
			if(stobj!=null){
				sheetType = (Integer) stobj;
			}
			map = this.loadDefaultMap(map, "config/businessBackout-crm.xml", type);
			map.put("corrKey", UUIDHexGenerator.getInstance().getID());
			if(type.equals(InterfaceConstants.WORKSHEET_NEW)||type.equals(InterfaceConstants.WORKSHEET_RENEW)){
				String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessBackout-crm.xml").getProperty("base.SendRoleId"));
				map.put("sendRoleId", sendRoleId);
				map.put("extendKey1", UUIDHexGenerator.getInstance().getID());
				map.put("operateTime", new java.util.Date());
//				map.put("extendKey2", Boolean.TRUE);
				
				map.put("extendKey2", Boolean.TRUE+","+sheetType.toString());
				
				String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessBackout-crm.xml").getProperty("base.AcceptGroupId"));
				String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
				
				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				String subRoleId = "";
				TawSystemSubRole subRole = sm.getMatchRoles("BusinessBackoutProcess", toDeptId, bigRole, map);
				if(subRole==null||subRole.getId()==null||subRole.getId().length()==0){
					System.out.println("未找到匹配角色，使用默认角色");
					subRoleId = XmlManage.getFile("/config/businessBackout-crm.xml").getProperty("base.AcceptRoleId");
				}else{
					subRoleId = subRole.getId();
				}
				map = sm.setAcceptRole(subRoleId, map);
				
				String serviceType = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("serviceType",StaticMethod.nullObject2String(map.get("serviceType")).toString());
				System.out.println("业务类型:"+serviceType);
				map.put("btype1", serviceType);//业务类型
			}
			if(type.equals(InterfaceConstants.WORKSHEET_RENEW)||type.equals(InterfaceConstants.WORKSHEET_UNTREAD)){
				String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
				if(sheetId==null || sheetId.equals(""))
					throw new Exception("sheetId为空");
				
				BaseMain main = null;
				IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());
				ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(this.getTaskBeanId());
				
				List list = iMainService.getMainListByParentSheetId(sheetId);
				if(list.size()>0){
					boolean b = false;
					main = (BaseMain)list.get(0);
					List taskList = iTaskService.getCurrentUndoTask(main.getId());
					if(taskList!=null){
						for(int i=0;i<taskList.size();i++){
							ITask task = (ITask) taskList.get(i);
							if(task.getTaskOwner().equals(main.getSendRoleId())||task.getTaskOwner().equals(main.getSendUserId())||task.getTaskOwner().equals(main.getSendDeptId())){
									b = true;
									break;
							}
						}
					}
					if(!b){
						if(type.equals(InterfaceConstants.WORKSHEET_RENEW))
							throw new Exception("工单未被驳回，不能重派");
						else if(type.equals(InterfaceConstants.WORKSHEET_UNTREAD))
							throw new Exception("工单未回复，不能退回");
					}
						
				}else
					throw new Exception("没找到sheetNo＝"+sheetId+"对应的工单");
			}
			return map;
		}catch(Exception err){
			err.printStackTrace();
			throw err;
		}
	}
	public void finishNew(BaseMain main, Map interfaceMap) {

		try {
			Object obj = interfaceMap.get("extendKey2");
			String sheetTypes = "";
			if(obj!=null){
				sheetTypes = (String) obj;
			}
			System.out.println("开始受理任务:");
			IBusinessBackoutTaskManager taskservice = (IBusinessBackoutTaskManager) ApplicationContextHolder
			.getInstance().getBean("ibusinessbackoutTaskManager");
			
			StringBuffer where = new StringBuffer();
			String cityName = StaticMethod.nullObject2String(interfaceMap
					.get("provinceName"), "");
//			String  templateName = (String) interfaceMap.get("sheetType");
			where.append("where FAULTSITE like '%" + cityName.substring(0,5) + "%' and templateName like '%"+sheetTypes.split(",")[1]+"%'");
			Map condition = new HashMap();
			condition.put("where", where.toString());
			Integer pageSize = new Integer(-1);
			int aTotal[] = new int[1];
			IAutoT1CrmManager iAutoT1CrmManagerImpl = (IAutoT1CrmManager) ApplicationContextHolder
					.getInstance().getBean("iAutoT1CrmManagerImpl");
			List listAuto = iAutoT1CrmManagerImpl.getObjectsByCondtion(
					new Integer(0), pageSize, aTotal, condition, "recoder");
			
			String toOwnerUserId ="";//任务被移交者人员ID
			String toOwnerRoleId="";//任务被移交者角色ID
			if (listAuto != null && listAuto.size() > 0) {
				System.out.println("已经匹配到T1自动流转规则！");
				Autot1Crm autoT2 = (Autot1Crm)listAuto.iterator().next();
				toOwnerRoleId = autoT2.getDealrolet2();
				toOwnerUserId = autoT2.getRemark1();
			
			}
			ITawSystemUserManager userManager = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");
			String userdeptid = "";
			String userphone = "";
			if (!toOwnerUserId.equals(""))
			{
				TawSystemUser user = userManager.getUserByuserid(toOwnerUserId);
				userdeptid = user.getDeptid();
				userphone = user.getMobile();
			}
			Calendar calendar = Calendar.getInstance();
			IBusinessBackoutLinkManager linkservice = (IBusinessBackoutLinkManager)ApplicationContextHolder.getInstance().getBean("iBusinessBackoutLinkManager");
//			确认受理
			BusinessBackoutLink confirmBean = new BusinessBackoutLink();
			confirmBean.setActiveTemplateId("ExcuteHumTask");
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
			confirmBean.setOperateUserId(toOwnerUserId);
			confirmBean.setOperateDeptId(userdeptid);
			confirmBean.setOperateRoleId(toOwnerRoleId);
			confirmBean.setOperaterContact(userphone);
			confirmBean.setToOrgRoleId(toOwnerRoleId);
			confirmBean.setToOrgType(new Integer(0));
			confirmBean.setAiid("");
			confirmBean.setNodeCompleteLimit(main.getSheetCompleteLimit());
			calendar.add(13, 20);
			confirmBean.setOperateTime(calendar.getTime());
			confirmBean.setAcceptFlag(new Integer(1));
			confirmBean.setCompleteFlag(new Integer(1));
			try
			{
				linkservice.addLink(confirmBean);
				//linkservice.addLink(linkbean);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getSheetAttachCode() {
		return "businessbackout";
	}

}
