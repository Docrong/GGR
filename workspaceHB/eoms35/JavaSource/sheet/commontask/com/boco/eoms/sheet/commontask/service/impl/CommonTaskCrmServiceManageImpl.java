package com.boco.eoms.sheet.commontask.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.interfaces.EOMSCommonTaskServer.EOMSCommonTaskLoader;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class CommonTaskCrmServiceManageImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage{

	public String getLinkBeanId() {
		// TODO Auto-generated method stub
		return "iCommonTaskLinkManager";
	}

	public String getMainBeanId() {
		// TODO Auto-generated method stub
		return "iCommonTaskMainManager";
	}

	public String getOperateName() {
		// TODO Auto-generated method stub
		return "newWorksheet";
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "CommonTaskMainFlowProcess";
	}

	public String getSheetAttachCode() {
		// TODO Auto-generated method stub
		return "commontask";
	}

	public String getTaskBeanId() {
		// TODO Auto-generated method stub
		return "icommontaskTaskManager";
	}
    
	public String getSendUser(Map map) {
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/CommonTask-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}
	
	public Map initMap(Map map, List attach, String type) throws Exception {
		try{
			map = this.loadDefaultMap(map, "config/CommonTask-crm.xml", type);
			if(type.equals(InterfaceConstants.WORKSHEET_NEW)){
				String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/CommonTask-crm.xml").getProperty("base.SendRoleId"));
				map.put("sendRoleId", sendRoleId);
				
				String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/CommonTask-crm.xml").getProperty("base.AcceptGroupId"));
				String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
				
				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				String subRoleId = "";
				TawSystemSubRole subRole = sm.getMatchRoles("commontask", toDeptId, bigRole, map);
				if(subRole==null||subRole.getId()==null||subRole.getId().length()==0){
					System.out.println("未找到匹配角色，使用默认角色");
					subRoleId = XmlManage.getFile("/config/CommonTask-crm.xml").getProperty("base.AcceptRoleId");
				}else{
					subRoleId = subRole.getId();
				}
				map = sm.setAcceptRole(subRoleId, map);
			}
			if(type.equals(EOMSCommonTaskLoader.WORKSHEET_REPLY)){
				String sheetId = StaticMethod.nullObject2String(map.get("sheetId"));
				if(sheetId==null || sheetId.equals(""))
					throw new Exception("sheetId为空");
				IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());
				ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(this.getTaskBeanId());
				
				BaseMain main = iMainService.getMainBySheetId(sheetId);
				if(main!=null){
					boolean b = false;
					List taskList = iTaskService.getCurrentUndoTask(main.getId());
					if(taskList!=null){
						for(int i=0;i<taskList.size();i++){
							ITask task = (ITask) taskList.get(i);
							if(task.getTaskName().equals("ExcuteHumTask")){
									b = true;
									break;
							}
						}
					}
					if(!b){
						if(type.equals(EOMSCommonTaskLoader.WORKSHEET_REPLY))
							throw new Exception("工单不在执行任务环节，不能回复");
					}
						
				}else
					throw new Exception("没找到sheetNo＝"+sheetId+"对应的工单");
			}
			return map;
		}catch(Exception err){
			//err.printStackTrace();
			throw err;
		}
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
}
