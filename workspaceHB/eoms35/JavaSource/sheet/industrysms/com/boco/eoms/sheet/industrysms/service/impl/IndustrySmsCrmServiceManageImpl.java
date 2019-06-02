package com.boco.eoms.sheet.industrysms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.industrysms.interfaces.EOMSIndustrySmsLoader;
import com.boco.eoms.sheet.industrysms.model.IndustrySmsMain;
import com.boco.eoms.sheet.industrysms.util.BaseService;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;

public class IndustrySmsCrmServiceManageImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage{

	public String getLinkBeanId() {
		// TODO Auto-generated method stub
		return "iIndustrySmsLinkManager";
	}

	public String getMainBeanId() {
		// TODO Auto-generated method stub
		return "iIndustrySmsMainManager";
	}

	public String getOperateName() {
		// TODO Auto-generated method stub
		return "newWorksheet";
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "IndustrySmsMainFlowProcess";
	}

	public String getSheetAttachCode() {
		// TODO Auto-generated method stub
		return "industrysms";
	}

	public String getTaskBeanId() {
		// TODO Auto-generated method stub
		return "iIndustrySmsTaskManager";
	}
    
	public String getSendUser(Map map) {
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/industrysms-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}
	
	public Map initMap(Map map, List attach, String type) throws Exception {
		try{
			map = this.loadDefaultMap(map, "config/industrysms-crm.xml", type);

			if(type.equals(EOMSIndustrySmsLoader.WORKSHEET_REPLY)){
				String sheetId = StaticMethod.nullObject2String(map.get("sheetId"));
				if(sheetId==null || sheetId.equals(""))
					throw new Exception("sheetId为空");
				IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());
				ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(this.getTaskBeanId());
				
				BaseService bs = new BaseService();
				String ip = bs.getClientIp();
				String ipAddress = StaticMethod.nullObject2String(XmlManage.getFile("/config/industrysms-crm.xml").getProperty("base.IpAddress"));
				if (ipAddress.indexOf(ip) == -1) {
					throw new Exception("没有找到对应的IP地址，不能回复");
				}
				
				IndustrySmsMain main = (IndustrySmsMain)iMainService.getMainBySheetId(sheetId);
				if(main!=null){
					String serCaller = StaticMethod.nullObject2String(map.get("serCaller"));
					String callerPwd = StaticMethod.nullObject2String(map.get("callerPwd"));
					String userValue = StaticMethod.nullObject2String(main.getUserValue());
					String passwordValue = StaticMethod.nullObject2String(main.getPasswordValue());
//开启密码过滤
//					if (!serCaller.equals(userValue) || !callerPwd.equals(passwordValue)) {
//						throw new Exception("用户名或密码不正确!");
//					}
					boolean b = false;
					List taskList = iTaskService.getCurrentUndoTask(main.getId());
					if(taskList!=null){
						for(int i=0;i<taskList.size();i++){
							ITask task = (ITask) taskList.get(i);
							if(task.getTaskName().equals("DataHumTask")){
									b = true;
									break;
							}
						}
					}
					if(!b){
						if(type.equals(EOMSIndustrySmsLoader.WORKSHEET_REPLY))
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
