package com.boco.eoms.sheet.safetyproblem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;

public class SafetyProblemGMCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage{

	public String getLinkBeanId() {
		// TODO Auto-generated method stub
		return "iSafetyProblemLinkManager";
	}

	public String getMainBeanId() {
		// TODO Auto-generated method stub
		return "iSafetyProblemMainManager";
	}

	public String getOperateName() {
		// TODO Auto-generated method stub
		return "newWorksheet";
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "SafetyProblemMainFlowProcess";
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
	public String getSendUser(Map map) {
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/SafetyProblemGM-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}

	public String getTaskBeanId() {
		// TODO Auto-generated method stub
		return "isafetyproblemTaskManager";
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		try{
			map = this.loadDefaultMap(map, "config/SafetyProblemGM-crm.xml", type);
			if(type.equals(InterfaceConstants.WORKSHEET_NEW)||type.equals(InterfaceConstants.WORKSHEET_RENEW)){
				String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/SafetyProblemGM-crm.xml").getProperty("base.SendRoleId"));
				map.put("sendRoleId", sendRoleId);
				if(type.equals(InterfaceConstants.WORKSHEET_NEW)){
					String subRoleId = "";
					String mainTaskDeptId = StaticMethod.nullObject2String(map.get("mainTaskDeptId"));
					System.out.println("lizhi:mainTaskDeptId="+mainTaskDeptId);
					if (null!=mainTaskDeptId&&!"".equals(mainTaskDeptId)) {
						ITawSystemDeptManager itawsystemdeptmanager = (ITawSystemDeptManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
						TawSystemDept TawSystemDept = (TawSystemDept)itawsystemdeptmanager.getDeptinfobydeptid(mainTaskDeptId,"0");
						String deptid = StaticMethod.nullObject2String(TawSystemDept.getDeptId());
						System.out.println("lizhi:deptid="+deptid);
						if (null != deptid&&!"".equals(deptid)) {
							subRoleId = mainTaskDeptId;
							
							map.put("dealPerformerLeader",subRoleId);
							map.put("dealPerformer",subRoleId);
							map.put("dealPerformerType","dept");
							map.put("toOrgRoleId",subRoleId);
							
						}else{
							throw new Exception("没有找到对应的部门");
						}
						
//						保存附件
						String attachId = getAttach(attach);
						System.out.println("attachId=" + attachId);
						if (attachId != null && attachId.length() > 0){
							map.put("sheetAccessories", attachId);
						}
					}
				}
			}
			return map;
		}catch(Exception err){
			err.printStackTrace();
			throw err;
		}		
	}

	public String getSheetAttachCode() {
		return "safetyproblem";
	}
}
