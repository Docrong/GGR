package com.boco.eoms.datum.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.sms.WorkSheetSmsServices;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;

public class DatumWorkSheetSmsServices {
	private static final String CONFIG_FILEPATH = "classpath:config/worksheet-sms-service-info.xml";//短信服务信息配置文件
	 /**
	    * @see  此方法用于非流程操作时派发短信，例如：催办、阶段回复、阶段建议、抄送等
	    * @param workflowName 流程名称
	    * @param sheetKey 工单主健ID
	    * @param sheetId  工单流水号
	    * @param title 工单主题
	    * @param receiveType 短信接受者类型
	    * @param receiverId 短信接收者Id
	    */
	   public void workHLRM_NON_T (String workflowName,String sheetKey,String sendContent,int receiveType,
			                     String receiverId) throws Exception {	   
	     try {
	    	 MsgServiceImpl   msgService = new MsgServiceImpl();
	    	 
	    	 //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
	    	 String nodeCnName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_PROCESS_CN_NAME;
	    	 String nodeInstantName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_SERVICE_INSTANT;
	    	 String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
	    	 String processCnName=SheetStaticMethod.getNodeName(filePath, nodeCnName);
	    	 String instantServiceId=SheetStaticMethod.getNodeName(filePath, nodeInstantName);
	    	 //拼接短信接受者
	    	 String receivers="";
	    	 if(receiveType==Constants.SMS_RECEIVE_TYPE_USER){
	    		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_USER+","+receiverId+"#";
	    	 }
	    	 else if(receiveType==Constants.SMS_RECEIVE_TYPE_DEPT){
	    		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_DEPT+","+receiverId+"#";
	    	 }
	    	 else if(receiveType==Constants.SMS_RECEIVE_TYPE_ROLE){
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
//	    	   String sendContent= "请你及时处理" + processCnName +":"+ sheetId + "。";
	    	   msgService.sendMsg(instantServiceId, sendContent,
	    		     sheetKey, receivers, sendTime);
	    	   
	    	 }   	    
	      }
	     catch (Exception e){
	    	 throw new Exception("send message exception,error info is"+e.getMessage()); 
	     }    
	   } 
}
