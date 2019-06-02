package com.boco.eoms.interfaces.EOMSAgentServer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.product.service.bo.SpecialLineBO;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.crm.bo.EomsLoader;
import com.boco.eoms.crm.util.SheetMapingSchema;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceLink;
import com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceMain;
import com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceTask;
import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceLinkManager;
import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceMainManager;
import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceTaskManager;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;

import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.interfaceInfo.util.IODate;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.XmlDom4jSX;

public class EOMSAgentSheet {
	/**
	 * 联通测试
	 * @param serSupplier
	 * @param callTime
	 * @return 
	 */
	public String isAlive(String serSupplier,String callTime) {
		System.out.println("收到crm握手信号");
		String isAliveResult = "0";

		return isAliveResult;
	}
	/**
	 * 工单派发
	 * @param sheetType
	 * @param serviceType
	 * @param serialNo
	 * @param serSupplier
	 * @param serCaller
	 * @param callerPwd
	 * @param callTime
	 * @param attachRef xml
	 * @param opPerson
	 * @param opCorp
	 * @param opDepart
	 * @param opContact
	 * @param opTime
	 * @param opDetail xml
	 * @return
	 */
	public String newWorkSheet(Integer sheetType,Integer serviceType,String serialNo,String serSupplier,String serCaller,String callerPwd,String callTime,String attachRef,String opPerson,String opCorp,String opDepart,String opContact,String opTime,String opDetail){
		System.out.println("新建工单");
		
		//生成一个时间戳，作为文件名
		//sheettype,serviceType,serialNo,opDetail存到一个单独的文件中。
		
		
		//调用FTP客户端程序，将该文件上传到对方的服务器
		//记录我们的日志到一个单独的文件
		
		System.out.println("sheetType="+sheetType);
		System.out.println("serviceType="+serviceType);
		System.out.println("serialNo="+serialNo);
		System.out.println("opDetail="+opDetail);
		System.out.println("attachRef="+attachRef);
		
		System.out.println("serSupplier="+serSupplier);
		System.out.println("serCaller="+serCaller);
		System.out.println("callerPwd="+callerPwd);
		System.out.println("callTime="+callTime);
		System.out.println("opPerson="+opPerson);
		System.out.println("opCorp="+opCorp);
		System.out.println("opDepart="+opDepart);
		System.out.println("opContact="+opContact);
		System.out.println("opTime="+opTime);	

		HashMap sheetMap = new HashMap();
		sheetMap.put("sheetType", sheetType);
		sheetMap.put("serviceType", serviceType);
		sheetMap.put("serialNo", serialNo);

		sheetMap.put("serSupplier", serSupplier);
		
		sheetMap.put("serCaller", serCaller);
		sheetMap.put("callerPwd", callerPwd);
		sheetMap.put("callTime", callTime);
		sheetMap.put("opDetail", opDetail);
		sheetMap.put("attachRef", attachRef);
		
		sheetMap.put("opPerson", opPerson);
		sheetMap.put("opCorp", opCorp);
		sheetMap.put("opDepart", opDepart);
		sheetMap.put("opContact", opContact);
		sheetMap.put("opTime", opTime);
		
		
		Map monitorMap = new HashMap();
		monitorMap.putAll(sheetMap);
		
		String result = "0";
		try{
			//保存报文至本地文件夹
			if ((31 == sheetType.intValue()&&4 == serviceType.intValue()) || (32 == sheetType.intValue()&&4 == serviceType.intValue())) {
				IODate.WriteDate(sheetType.toString(), serviceType.toString(), serialNo, opDetail);
			}
//			保存报文至本地文件夹
			 InterfaceUtil interfaceUtil = new InterfaceUtil();
			 XmlDom4jSX d = new XmlDom4jSX();
				List mapList = d.getList(opDetail);
				sheetMap.putAll((HashMap)mapList.get(0));
	        List attach = interfaceUtil.getAttachRefFromXml(attachRef);		
	        
	        try{
				SpecialLineBO bo = new SpecialLineBO();
				String id = bo.save(sheetType.toString() ,serviceType.toString(), mapList);

				if(id!=null)
					sheetMap.put("orderSheetId", id);
			}catch(Exception err){
				err.printStackTrace();
			}
	
	        EomsLoader loader = new EomsLoader();
	        sheetMap.put("sheetType", "61");
	        sheetMap.put("serviceType", "1");
	        String sheetId = loader.newWorkSheet(sheetMap, attach);
	        monitorMap.put("sheetKey", sheetId);
			result = "0,"+sheetId;	        
	        String beanId = SheetMapingSchema.getInstance().getBeanId(sheetType+"", serviceType+"");
	        
	        monitorMap.put("beanId", beanId);
			
		}catch(Exception err){
			err.printStackTrace();
			result =err.getMessage();
		}
		System.out.println("newWorkSheet " + serialNo + ":" + result);
		
		
		
//		InterfaceDataMonitor monitor = new InterfaceDataMonitor();
//		monitor.saveMonitor(monitorMap, result, "crm", "newWorkSheet");
		
		return result;
	}
	
	public String affirmWorkSheet(String opDetail){
		System.out.println("工单处理回复");
		String result = "0";
		if ((opDetail != null) && (!"".equals(opDetail))){
		HashMap sheetMap = new HashMap();
		HashMap sheetMap1 = new HashMap();
	    HashMap columnMap = new HashMap();
	    Map valueMap = new HashMap();
	    Map linkMap = new HashMap();
	    IAgentMaintenanceMainManager mainservice = (IAgentMaintenanceMainManager)ApplicationContextHolder.getInstance().getBean("iAgentMaintenanceMainManager");
	    IAgentMaintenanceLinkManager linkservice = (IAgentMaintenanceLinkManager)ApplicationContextHolder.getInstance().getBean("iAgentMaintenanceLinkManager");
	    IAgentMaintenanceTaskManager taskservice = (IAgentMaintenanceTaskManager)ApplicationContextHolder.getInstance().getBean("iagentmaintenanceTaskManager");
	    WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
	//    AgentMaintenanceLink link = new AgentMaintenanceLink();

		try{
			InterfaceUtil interfaceUtil =new InterfaceUtil();
		    InterfaceUtilProperties properties = new InterfaceUtilProperties();
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");	
			String nodeName ="affirmWorkSheet";
	        String filePath = StaticMethod.getFilePathForUrl("classpath:config/agentmaintenance-crm.xml");
	        sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	        valueMap = properties.getMapFromXml(sheetMap, filePath, nodeName);
	        String sheetId = StaticMethod.nullObject2String(sheetMap.get("sheetId"));
	 //       String operateUserId = StaticMethod.nullObject2String(sheetMap.get("operateUserId"));
	    	String operateUserId = StaticMethod.nullObject2String(XmlManage.getFile("/config/agentmaintenance-crm.xml").getProperty("base.InterfaceUser"));
			 
	        String operateTime = StaticMethod.nullObject2String(sheetMap.get("operateTime"));
	        String operType = StaticMethod.nullObject2String(sheetMap.get("operateType"));
	        String opDesc = StaticMethod.nullObject2String(sheetMap.get("opDesc"));
	        String operateRoleId ="";
	        if(!"".equals(sheetId)&&sheetId!=null){
	        	AgentMaintenanceMain main =(AgentMaintenanceMain)mainservice.getMainBySheetId(sheetId);
	        	int status = StaticMethod.nullObject2int(main.getStatus());
	            if (status != 0) {
	              result = "Status=-1;sheetDetail=;Errlist=工单流水号为" + sheetId + "的工单未处于运行状态,无法进行回复处理操作！";
	              return result;
	        }
		      String sheetKey = StaticMethod.nullObject2String(main.getId());
		      String condition = " sheetKey = '" + sheetKey + "' and taskstatus ='2' and taskName ='AffirmHumTask' ";
	          List taskList = taskservice.getTasksByCondition(condition);
	          if ((taskList != null) && (taskList.size() > 0)) {
	        	  AgentMaintenanceTask task = (AgentMaintenanceTask)taskList.get(0);
	//              String operateType = StaticMethod.nullObject2String(task.getOperateType());
	                  ITawSystemUserManager userMgr = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	                  TawSystemUser user = userMgr.getUserByuserid(operateUserId);
	                  if (user != null) {
	                      valueMap.put("operateDeptId", user.getDeptid());
	                      valueMap.put("operaterContact", user.getMobile());	                      
	                    }
	                  valueMap.put("operateUserId", operateUserId);
	                  valueMap.put("operateRoleId", task.getOperateRoleId());
	                  valueMap.put("mainId", main.getId());
	                  valueMap.put("aiid", task.getId());
	                  valueMap.put("piid", task.getProcessId());
	                  valueMap.put("operateTime", operateTime);
	                  valueMap.put("acceptTimeLimit", task.getAcceptTimeLimit());
	                  valueMap.put("completeTimeLimit", task.getCompleteTimeLimit());                              
	                  IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	                  String sql = "select * from agentmaintenance_link link where link.mainid='" + 
	                    sheetKey + "' and link.operatetype='205' and link.activetemplateid='ExcuteHumTask' " + 
	                    "order by link.operatetime desc ";
	                  System.out.println("驳回操作查询T1操作人sql====" + sql);
	                  List linkList = services.getSheetAccessoriesList(sql);
	                  if ((linkList != null) && (linkList.size() > 0)) {
	                    linkMap = (Map)linkList.get(0);
	               //     link =(AgentMaintenanceLink)linkList.get(0);
	                    operateRoleId =StaticMethod.nullObject2String(linkMap.get("operateRoleId"));	                    
	                  }
	   		         if("0".equals(operType)){
	   		        	valueMap.put("phaseId", "ExcuteHumTask");
		                valueMap.put("operateType", "212");
		                valueMap.put("hasNextTaskFlag", "true");	
	                    valueMap.put("dealPerformer",  operateRoleId);
	                    valueMap.put("dealPerformerLeader", operateRoleId);
	                    valueMap.put("dealPerformerType", "subrole");
	                    valueMap.put("toOrgRoleId", operateRoleId);
	                    valueMap.put("linkTaskComplete", opDesc);
	                  }	else if ("1".equals(operType)){
	        	     valueMap.put("operateType", "211");
                     valueMap.put("hasNextTaskFlag", "true");
                     valueMap.put("linkRevertReason",opDesc);
                     valueMap.put("linkComContactUser",StaticMethod.nullObject2String(linkMap.get("linkComContactUser")));
                     valueMap.put("linkComContactPhone",StaticMethod.nullObject2String(linkMap.get("linkComContactPhone")));
                     valueMap.put("linkComcompProp",StaticMethod.nullObject2String(linkMap.get("linkComcompProp")));
                     valueMap.put("linkComIsContactUser",StaticMethod.nullObject2String(linkMap.get("linkComIsContactUser")));
                     valueMap.put("linkComFaultEndTime",StaticMethod.nullObject2String(linkMap.get("linkComFaultEndTime")));
                     valueMap.put("linkComTransmitReason",StaticMethod.nullObject2String(linkMap.get("linkComTransmitReason")));
                     valueMap.put("linkComLocalDealDesc",StaticMethod.nullObject2String(linkMap.get("linkComLocalDealDesc")));
                     valueMap.put("phaseId", "");
                     System.out.println("-------------自动归档---------------"); 
                     Calendar calendar = Calendar.getInstance();
                     AgentMaintenanceLink linkbean = (AgentMaintenanceLink)linkservice.getLinkObject().getClass().newInstance();
                     linkbean.setId(UUIDHexGenerator.getInstance().getID());
                     linkbean.setMainId(StaticMethod.nullObject2String(main.getId()));
                     linkbean.setOperateTime(calendar.getTime());
                     linkbean.setOperateType(new Integer(18));
                     linkbean.setOperateDay(calendar.get(5));
                     linkbean.setOperateMonth(calendar.get(2) + 1);
                     linkbean.setOperateYear(calendar.get(1));
                     linkbean.setOperateUserId(StaticMethod.nullObject2String(main.getSendUserId()));
                     linkbean.setOperateDeptId(StaticMethod.nullObject2String(main.getSendDeptId()));
                     linkbean.setOperateRoleId(StaticMethod.nullObject2String(main.getSendRoleId()));
                     linkbean.setOperaterContact(StaticMethod.nullObject2String(main.getSendContact()));
                     linkbean.setToOrgRoleId("");
                     linkbean.setToOrgType(new Integer(0));
                     linkbean.setAcceptFlag(new Integer(2));
                     linkbean.setCompleteFlag(new Integer(2));
                     linkbean.setActiveTemplateId("HoldHumTask");
                     linkservice.addLink(linkbean);
                     if (main != null)
                     {
                       main.setEndResult("ok");
                       main.setStatus(new Integer(1));
                       main.setHoldStatisfied(Integer.valueOf(1030301));
                       mainservice.addMain(main);
                     }
                     AgentMaintenanceTask taskhold = new AgentMaintenanceTask();
                     try
                     {
                       taskhold.setId(UUIDHexGenerator.getInstance().getID());
                     }
                     catch (Exception e3)
                     {
                       e3.printStackTrace();
                     }
                     taskhold.setTaskName("HoldHumTask");
                     taskhold.setTaskDisplayName("待归档");
                     taskhold.setFlowName("AgentMaintenanceMainFlowProcess");
                     taskhold.setSendTime(new Date());
                     taskhold.setSheetKey(StaticMethod.nullObject2String(main.getId()));
                     taskhold.setTaskStatus("5");
                     taskhold.setSheetId(StaticMethod.nullObject2String(main.getSheetId()));
                     taskhold.setTitle(StaticMethod.nullObject2String(main.getTitle()));
                     taskhold.setOperateType("subrole");
                     taskhold.setCreateTime(new Date());
                     taskhold.setCreateYear(calendar.get(1));
                     taskhold.setCreateMonth(calendar.get(2) + 1);
                     taskhold.setCreateDay(calendar.get(5));
                     taskhold.setOperateRoleId(StaticMethod.nullObject2String(main.getSendRoleId()));
                     taskhold.setTaskOwner(StaticMethod.nullObject2String(main.getSendUserId()));
                     taskhold.setOperateType("subrole");
                     taskhold.setIfWaitForSubTask("false");
                     taskhold.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
                     taskhold.setPreLinkId(linkbean.getId());
                     taskservice.addTask(taskhold);
                 }
		              Map sheetMainMap = SheetBeanUtils.bean2Map(main);
		              valueMap.putAll(sheetMainMap);
		              sheetMap1.put("main", main);
		              sheetMap1.put("link", linkservice.getLinkObject().getClass().newInstance());
		              sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
		              columnMap.put("selfSheet", sheetMap1);
		              valueMap.put("correlationKey", StaticMethod.nullObject2String(main.getCorrelationKey()));
		              sm.dealSheet(sheetKey, valueMap, columnMap, operateUserId, taskservice);
		              result = "Status=0;Errlist=工单流水号为" + sheetId + "的工单处理回复操作完成！";
		              return result;

	              }else {
	                  result = "Status=-1;Errlist=工单流水号为" + sheetId + "的工单未处于处理回复中状态,无法进行操作！";
	                  return result;}
		    }else  {
			  result = "Status=-1;Errlist=工单流水号不存在,无法进行操作！";
              return result;}
		}catch(Exception err){
			err.printStackTrace();
			result =err.getMessage();
		}
		System.out.println("untreadWorkSheet :" + result);

    	}else {
		  result = "Status=-1;Errlist=报文为空,无法进行操作！";
		return result;
	}
		return result;
	}
	
}
