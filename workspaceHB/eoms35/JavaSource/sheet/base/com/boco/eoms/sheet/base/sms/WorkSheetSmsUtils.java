package com.boco.eoms.sheet.base.sms;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import com.boco.eoms.sheet.complaint.sms.ComplaintSheetSmsUtils;
//import com.boco.eoms.sheet.complaint.sms.ComplaintSheetSmsUtils;
//import com.boco.eoms.sheet.tool.msgconfig.mgr.SheetMsgConfigMgr;
import commonj.sdo.DataObject;

public class WorkSheetSmsUtils {
	private static final String CONFIG_FILEPATH = "classpath:config/worksheet-sms-service-info.xml";//短信服务信息配置文件
    
	public void sendMsg(DataObject mainObject,String workflowName,String sheetKey,String sheetId,String title,Integer receiveType,
            String receiverId,String acceptLimitTime,String dealLimitTime) throws Exception {
		
	 MsgServiceImpl   msgService = new MsgServiceImpl();
   	 String taskCnName="";
   	 //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
   	 String nodeCnName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_PROCESS_CN_NAME;
   	 String nodeInstantName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_SERVICE_INSTANT;
   	 String nodePreOvertimeAccpetName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_SERVICE_PREOVERTIME_ACCEPT;
   	 String nodePreOvertimeDealName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_SERVICE_PREOVERTIME_DEAL;
   	 String nodePostOvertimeAcceptName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_SERVICE_POSTOVERTIME_ACCPT;
   	 String nodePostOvertimeDealName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_SERVICE_POSTOVERTIME_DEAL;
   	 String changeSenderName= Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_CHANGE_SENDER; 
   	 
   	 String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
   	 String processCnName=SheetStaticMethod.getNodeName(filePath, nodeCnName);
   	 String instantServiceId=SheetStaticMethod.getNodeName(filePath, nodeInstantName);
   	 String preOvertimeAcceptServiceId=SheetStaticMethod.getNodeName(filePath, nodePreOvertimeAccpetName);
   	 String preOvertimeDealServiceId=SheetStaticMethod.getNodeName(filePath, nodePreOvertimeDealName);
   	 String postOvertimeAcceptServiceId=SheetStaticMethod.getNodeName(filePath, nodePostOvertimeAcceptName);
   	 String postOvertimeDealServiceId=SheetStaticMethod.getNodeName(filePath, nodePostOvertimeDealName);
   	 String changeSenderFlag=SheetStaticMethod.getNodeName(filePath, changeSenderName);
   	 
   	 //从sheetKey参数中拆分中环节中文名称，modify by qinmin 2009-08-10
   	 if(!sheetKey.equals("")&&sheetKey.indexOf("#")>0) {
   		 taskCnName=sheetKey.substring(sheetKey.indexOf("#")+1,sheetKey.length());
   		 sheetKey=sheetKey.substring(0,sheetKey.indexOf("#"));
   	 } 
     //   拼接短信接受者
	 String receivers="";
   	 if(receiveType.intValue()==Constants.SMS_RECEIVE_TYPE_USER){
		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_USER+","+receiverId+"#";
	 }
	 else if(receiveType.intValue()==Constants.SMS_RECEIVE_TYPE_DEPT){
		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_DEPT+","+receiverId+"#";
	 }
	 else if(receiveType.intValue()==Constants.SMS_RECEIVE_TYPE_ROLE){
		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_ROLE+","+receiverId+"#";
	 }
//   	bdeptContactPhone
   	
	 if(!receivers.equals("")) 
		  receivers=receivers.substring(0,receivers.lastIndexOf("#"));
	 System.out.println("receivers="+receivers);
	 
	 if(changeSenderFlag.equals("true")){
		 receivers=this.changeSender(receivers, mainObject, workflowName, sheetKey, sheetId, title,
				 receiveType, receiverId, acceptLimitTime, dealLimitTime, taskCnName);		 
	 }
	 System.out.println("receivers111="+receivers);
	 //派发通知（即时提醒）
	  if(!instantServiceId.equals("")){   	   
	   //获得当前时间 
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String sendTime = formatter.format(currentTime);
	   
	   //拼写发送信息
	   String sendContent="";
	   if("ComplaintProcess".equals(workflowName)&&"1030802".equals(mainObject.get("bdeptContactPhone"))){
		   sendContent=  sendTime + "，省内EOMS接收到客服派发的大面积投诉工单，工单号："+sheetId+"，投诉内容为:" + title;
	   }else if("ComplaintProcess".equals(workflowName)&&"1030803".equals(mainObject.get("bdeptContactPhone"))){
		   sendContent= "提醒您收取升级投诉工单:"+ sheetId + ",主题名:" + title;
	   }else if("ComplaintProcess".equals(workflowName)&&"1030804".equals(mainObject.get("bdeptContactPhone"))){
		   sendContent= "提醒您收取重复诉工单:"+ sheetId + ",主题名:" + title;
	   }else{
		   sendContent= "提醒您收取" + processCnName +":"+ sheetId + ",主题名:" + title;
		   if(!taskCnName.equals("")) sendContent=sendContent+",处理环节："+taskCnName;
		   if(!dealLimitTime.equals("")) sendContent=sendContent+",处理时限："+dealLimitTime;
	   }
	   msgService.sendMsg(instantServiceId, sendContent,
			   sheetKey, receivers, sendTime);
	 }
	 
	//接单超时前提醒
	 if(!preOvertimeAcceptServiceId.equals("")&&!acceptLimitTime.equals("")){    	        		
        String sendContent= "提醒您," + processCnName +":"+sheetId + "即将于"+acceptLimitTime+"超时，请及时接收！" ; 
		msgService.sendMsg(preOvertimeAcceptServiceId, sendContent,
	    		     sheetKey, receivers, acceptLimitTime);
		
	 }	
    //处理超时提醒	
	 if(!preOvertimeDealServiceId.equals("")&&!dealLimitTime.equals("")){
		   
	    	String sendContent= "提醒您," + processCnName +":"+sheetId + "即将于"+dealLimitTime+"超时，请及时处理！" ; 
		    msgService.sendMsg(preOvertimeDealServiceId, sendContent,
	    		     sheetKey, receivers, dealLimitTime);
		 
	 }
	
	 //接单超时后提醒
	 if(!postOvertimeAcceptServiceId.equals("")&&!acceptLimitTime.equals("")){    	     
 		   String sendContent= "提醒您," + processCnName +":"+sheetId + "已经超时，请及时接收！" ; 
 		   msgService.sendMsg(postOvertimeAcceptServiceId, sendContent,
 	    		     sheetKey, receivers, acceptLimitTime);
 	 }
	 if(!postOvertimeDealServiceId.equals("")&&!dealLimitTime.equals("")){
 		   //处理即将超时提醒	
 	    	String sendContent= "提醒您," + processCnName +":"+sheetId + "已经超时，请及时处理！" ; 
 		    msgService.sendMsg(postOvertimeDealServiceId, sendContent,
 	    		     sheetKey, receivers, dealLimitTime);
 		 }  
   }
	
	
	public void sendVoice(DataObject mainObject,String workflowName,String sheetKey,String sheetId,String title,Integer receiveType,
            String receiverId,String acceptLimitTime,String dealLimitTime) throws Exception {
		
	 MsgServiceImpl   msgService = new MsgServiceImpl();
   	 String taskCnName="";
     //   解析短信服务信息文件，获取工单中文名称，以及短信服务ID
	 String nodeCnName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_PROCESS_CN_NAME;
	 String nodeInstantName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.VOICE_SERVICE_INSTANT;
	 String nodePreOvertimeAccpetName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.VOICE_SERVICE_PREOVERTIME_ACCEPT;
	 String nodePreOvertimeDealName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.VOICE_SERVICE_PREOVERTIME_DEAL;
	 String nodePostOvertimeAcceptName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.VOICE_SERVICE_POSTOVERTIME_ACCPT;
	 String nodePostOvertimeDealName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.VOICE_SERVICE_POSTOVERTIME_DEAL;
	     	 
	 String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
	 String processCnName=SheetStaticMethod.getNodeName(filePath, nodeCnName);
	 String instantServiceId=SheetStaticMethod.getNodeName(filePath, nodeInstantName);
	 String preOvertimeAcceptServiceId=SheetStaticMethod.getNodeName(filePath, nodePreOvertimeAccpetName);
	 String preOvertimeDealServiceId=SheetStaticMethod.getNodeName(filePath, nodePreOvertimeDealName);
	 String postOvertimeAcceptServiceId=SheetStaticMethod.getNodeName(filePath, nodePostOvertimeAcceptName);
	 String postOvertimeDealServiceId=SheetStaticMethod.getNodeName(filePath, nodePostOvertimeDealName);
	
   	 //从sheetKey参数中拆分中环节中文名称，modify by qinmin 2009-08-10
   	 if(!sheetKey.equals("")&&sheetKey.indexOf("#")>0) {
   		 taskCnName=sheetKey.substring(sheetKey.indexOf("#"),sheetKey.length());
   		 sheetKey=sheetKey.substring(0,sheetKey.indexOf("#"));
   	 } 
     //   拼接短信接受者
	 String receivers="";
   	 if(receiveType.intValue()==Constants.SMS_RECEIVE_TYPE_USER){
		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_USER+","+receiverId+"#";
	 }
	 else if(receiveType.intValue()==Constants.SMS_RECEIVE_TYPE_DEPT){
		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_DEPT+","+receiverId+"#";
	 }
	 else if(receiveType.intValue()==Constants.SMS_RECEIVE_TYPE_ROLE){
		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_ROLE+","+receiverId+"#";
	 }
	 
	 if(!receivers.equals("")) 
		  receivers=receivers.substring(0,receivers.lastIndexOf("#"));
	 System.out.println("receivers="+receivers);
     
	 //派发通知（即时提醒）
	  if(!instantServiceId.equals("")){   	   
	   //获得当前时间 
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat(
	     "yyyy-MM-dd HH:mm:ss");
	   String sendTime = formatter.format(currentTime);
	   
	   //拼写发送信息
	   String sendContent= "提醒您收取" + processCnName +":"+ sheetId + ",主题名:" + title;
	   if(!taskCnName.equals("")) sendContent=sendContent+",处理环节："+taskCnName;
	   if(!dealLimitTime.equals("")) sendContent=sendContent+",处理时限："+dealLimitTime;
	 
	  ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
	       .getInstance().getBean("ITawSystemWorkflowManager");
      TawSystemWorkflow wf=wfManager.getTawSystemWorkflowByName(workflowName);
      if(wf!=null){
   	   IMainService mainService=(IMainService)ApplicationContextHolder
		    .getInstance().getBean(wf.getMainServiceBeanId());
   	   BaseMain main=mainService.getMainDAO().getMainBySheetId(sheetId, mainService.getMainObject());
   	   if(main!=null)
   	     msgService.sendVoice(instantServiceId, sheetKey, sendTime, main.getSendTime().toString(), dealLimitTime, sendContent, main.getSendUserId(), receivers);		    	 	        	   
      }	   
	 }	   
   }
	
	public String changeSender(String receivers,DataObject mainObject,String workflowName,String sheetKey,String sheetId,String title,Integer receiveType,
            String receiverId,String acceptLimitTime,String dealLimitTime,String taskCnName){
		if("CommonFaultMainFlowProcess".equals(workflowName)){
			
		}else if("ComplaintProcess".equals(workflowName)){
			ComplaintSheetSmsUtils util=new ComplaintSheetSmsUtils();
			receivers=util.changeSender(receivers, mainObject, workflowName, sheetKey, sheetId, title, receiveType, receiverId, acceptLimitTime, dealLimitTime,taskCnName);
		}
		return receivers;
		
	}
}
