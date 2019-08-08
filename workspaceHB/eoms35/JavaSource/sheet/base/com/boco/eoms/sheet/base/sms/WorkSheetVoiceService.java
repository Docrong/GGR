package com.boco.eoms.sheet.base.sms;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import com.boco.eoms.sheet.base.model.BaseMain;

public class WorkSheetVoiceService {

    public WorkSheetVoiceService() {
    }

    private static final String CONFIG_FILEPATH = "classpath:config/worksheet-sms-service-info.xml";//短信服务信息配置文件

    /**
     * @param workflowName    流程名称
     * @param sheetKey        工单主健ID
     * @param sheetId         工单流水号
     * @param title           工单主题
     * @param receiveType     短信接受者类型
     * @param receiverId      短信接收者Id
     * @param acceptLimitTime 接受时限
     * @param dealLimitTime   处理时限
     * @see 工单派发的时候，进行语音提醒；包括即时提醒、超时前提醒以及超时后提醒
     * 此方法一般用于普通人工任务生成时调用
     */
    public void workSM_T(String workflowName, String sheetKey, String sheetId, String title, int receiveType,
                         String receiverId, String acceptLimitTime, String dealLimitTime) throws Exception {
        try {
            MsgServiceImpl msgService = new MsgServiceImpl();

            //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
            String nodeCnName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.SMS_PROCESS_CN_NAME;
            String nodeInstantName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_INSTANT;
            String nodePreOvertimeAccpetName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_PREOVERTIME_ACCEPT;
            String nodePreOvertimeDealName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_PREOVERTIME_DEAL;
            String nodePostOvertimeAcceptName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_POSTOVERTIME_ACCPT;
            String nodePostOvertimeDealName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_POSTOVERTIME_DEAL;

            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String processCnName = SheetStaticMethod.getNodeName(filePath, nodeCnName);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            String preOvertimeAcceptServiceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeAccpetName);
            String preOvertimeDealServiceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeDealName);
            String postOvertimeAcceptServiceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeAcceptName);
            String postOvertimeDealServiceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeDealName);


            //拼接短信接受者
            String receivers = "";
            if (receiveType == Constants.SMS_RECEIVE_TYPE_USER) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_USER + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_DEPT) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_DEPT + "," + receiverId + "#";
            } else if (receiveType == Constants.SMS_RECEIVE_TYPE_ROLE) {
                receivers = receivers + Constants.SMS_RECEIVE_TYPE_ROLE + "," + receiverId + "#";
            }

            if (!receivers.equals(""))
                receivers = receivers.substring(0, receivers.lastIndexOf("#"));

            System.out.println("receivers=" + receivers);

//	    	获得当前时间 
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String sendTime = formatter.format(currentTime);

            //派发通知（即时提醒）
            if (!instantServiceId.equals("")) {


                //拼写发送信息
                String sendContent = "提醒您收取" + processCnName + ":" + sheetId + ",主题名:" + title;

//	    	   ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
//			    .getInstance().getBean("ITawSystemWorkflowManager");
//	           TawSystemWorkflow wf=wfManager.getTawSystemWorkflowByName(workflowName);
//	           if(wf!=null){
//	        	   IMainService mainService=(IMainService)ApplicationContextHolder
//				    .getInstance().getBean(wf.getMainServiceBeanId());
//	        	   BaseMain main=mainService.getMainDAO().getMainBySheetId(sheetId, mainService.getMainObject());
//	        	   if(main!=null)
//	        	    msgService.sendVoice(instantServiceId, sheetKey, sendTime, main.getSendTime().toString(), dealLimitTime, sendContent, main.getSendUserId(), receivers);		    	 	        	   
//	           }
//	    	   msgService.sendVoice(instantServiceId, sheetKey, sendTime, sendTime, dealLimitTime, sendContent,"", receivers);		    	 	        	            
            }

            //接单超时前提醒
            if (!preOvertimeAcceptServiceId.equals("") && !acceptLimitTime.equals("")) {
                String sendContent = "提醒您," + processCnName + ":" + sheetId + "即将于" + acceptLimitTime + "超时，请及时接收！";

//	    		ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
//			    .getInstance().getBean("ITawSystemWorkflowManager");
//	           TawSystemWorkflow wf=wfManager.getTawSystemWorkflowByName(workflowName);
//	           if(wf!=null){
//	        	   IMainService mainService=(IMainService)ApplicationContextHolder
//				    .getInstance().getBean(wf.getMainServiceBeanId());
//	        	   BaseMain main=mainService.getMainDAO().getMainBySheetId(sheetId, mainService.getMainObject());
//	        	   if(main!=null)
//	        	   msgService.sendVoice(instantServiceId, sheetKey, sendTime, main.getSendTime().toString(), dealLimitTime, sendContent, main.getSendUserId(), receivers);		    	 	        	   
//	           }
//	            msgService.sendVoice(instantServiceId, sheetKey, sendTime, sendTime, dealLimitTime, sendContent,"", receivers);		    	 	        	            

            }
            //处理超时提醒
            else if (!preOvertimeDealServiceId.equals("") && !dealLimitTime.equals("")) {

                String sendContent = "提醒您," + processCnName + ":" + sheetId + "即将于" + acceptLimitTime + "超时，请及时处理！";
//	    	    	ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
//				    .getInstance().getBean("ITawSystemWorkflowManager");
//		           TawSystemWorkflow wf=wfManager.getTawSystemWorkflowByName(workflowName);
//		           if(wf!=null){
//		        	   IMainService mainService=(IMainService)ApplicationContextHolder
//					    .getInstance().getBean(wf.getMainServiceBeanId());
//		        	   BaseMain main=mainService.getMainDAO().getMainBySheetId(sheetId, mainService.getMainObject());
//		        	   if(main!=null)
//		        	   msgService.sendVoice(instantServiceId, sheetKey, sendTime, main.getSendTime().toString(), dealLimitTime, sendContent, main.getSendUserId(), receivers);		    	 	        	   
//		           }
//	    	    	 msgService.sendVoice(instantServiceId, sheetKey, sendTime, sendTime, dealLimitTime, sendContent,"", receivers);		    	 	        	            

            }

            //接单超时后提醒
            if (!postOvertimeAcceptServiceId.equals("") && !acceptLimitTime.equals("")) {
                String sendContent = "提醒您," + processCnName + ":" + sheetId + "已经超时，请及时接收！";
//	     		  ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
//				    .getInstance().getBean("ITawSystemWorkflowManager");
//		           TawSystemWorkflow wf=wfManager.getTawSystemWorkflowByName(workflowName);
//		           if(wf!=null){
//		        	   IMainService mainService=(IMainService)ApplicationContextHolder
//					    .getInstance().getBean(wf.getMainServiceBeanId());
//		        	   BaseMain main=mainService.getMainDAO().getMainBySheetId(sheetId, mainService.getMainObject());
//		        	   if(main!=null)
//		        	   msgService.sendVoice(instantServiceId, sheetKey, sendTime, main.getSendTime().toString(), dealLimitTime, sendContent, main.getSendUserId(), receivers);		    	 	        	   
//		           }
//	     		  msgService.sendVoice(instantServiceId, sheetKey, sendTime, sendTime, dealLimitTime, sendContent,"", receivers);		    	 	        	            

            } else if (!postOvertimeDealServiceId.equals("") && !dealLimitTime.equals("")) {
                //处理即将超时提醒
                String sendContent = "提醒您," + processCnName + ":" + sheetId + "已经超时，请及时处理！";
//	     	    	ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
//				    .getInstance().getBean("ITawSystemWorkflowManager");
//		           TawSystemWorkflow wf=wfManager.getTawSystemWorkflowByName(workflowName);
//		           if(wf!=null){
//		        	   IMainService mainService=(IMainService)ApplicationContextHolder
//					    .getInstance().getBean(wf.getMainServiceBeanId());
//		        	   BaseMain main=mainService.getMainDAO().getMainBySheetId(sheetId, mainService.getMainObject());
//		        	   if(main!=null)
//		        	   msgService.sendVoice(instantServiceId, sheetKey, sendTime, main.getSendTime().toString(), dealLimitTime, sendContent, main.getSendUserId(), receivers);		    	 	        	   
//		           }
//	     	    	 msgService.sendVoice(instantServiceId, sheetKey, sendTime, sendTime, dealLimitTime, sendContent,"", receivers);		    	 	        	            

            }
        } catch (Exception e) {
            throw new Exception("send message exception,error info is" + e.getMessage());
        }

    }

    /**
     * @param workflowName 流程名称
     * @param sheetKey     工单主健ID
     * @param sheetId      工单流水号
     * @param title        工单主题
     * @param receiveType  短信接受者类型
     * @param receiverId   短信接收者Id
     * @see 此方法用于非流程操作时派发短信，例如：催办、阶段回复、阶段建议、抄送等
     */
    public void workSM_NON_T(String workflowName, String sheetKey, String sheetId, String title, int receiveType,
                             String receiverId) throws Exception {
//	     try {
//	    	 MsgServiceImpl   msgService = new MsgServiceImpl();
//	    	 
//	    	 //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
//	    	 String nodeCnName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_PROCESS_CN_NAME;
//	    	 String nodeInstantName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.VOICE_SERVICE_INSTANT;
//	    	 String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
//	    	 String processCnName=SheetStaticMethod.getNodeName(filePath, nodeCnName);
//	    	 String instantServiceId=SheetStaticMethod.getNodeName(filePath, nodeInstantName);
//	    	 //拼接短信接受者
//	    	 String receivers="";
//	    	 if(receiveType==Constants.SMS_RECEIVE_TYPE_USER){
//	    		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_USER+","+receiverId+"#";
//	    	 }
//	    	 else if(receiveType==Constants.SMS_RECEIVE_TYPE_DEPT){
//	    		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_DEPT+","+receiverId+"#";
//	    	 }
//	    	 else if(receiveType==Constants.SMS_RECEIVE_TYPE_ROLE){
//	    		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_ROLE+","+receiverId+"#";
//	    	 }
//	    	 
//	    	 if(!receivers.equals("")) 
//	    		  receivers=receivers.substring(0,receivers.lastIndexOf("#"));
//	    	 
//	    	 System.out.println("receivers="+receivers);
//		        
//	    	 //派发通知（即时提醒）
//	    	  if(!instantServiceId.equals("")){   	   
//	    	   //获得当前时间 
//	    	   Date currentTime = new Date();
//	    	   SimpleDateFormat formatter = new SimpleDateFormat(
//	    	     "yyyy-MM-dd HH:mm:ss");
//	    	   String sendTime = formatter.format(currentTime);
//	    	   
//	    	   //拼写发送信息
//	    	   String sendContent= "请你及时处理" + processCnName +":"+ sheetId + "。";
//	    	   ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
//			    .getInstance().getBean("ITawSystemWorkflowManager");
//	           TawSystemWorkflow wf=wfManager.getTawSystemWorkflowByName(workflowName);
//	           if(wf!=null){
//	        	   IMainService mainService=(IMainService)ApplicationContextHolder
//				    .getInstance().getBean(wf.getMainServiceBeanId());
//	        	   BaseMain main=mainService.getMainDAO().loadSinglePO(sheetKey, mainService.getMainObject());
//	        	   if(main!=null)
//	        	    msgService.sendVoice(instantServiceId, sheetKey, sendTime, main.getSendTime().toString(), dealLimitTime, sendContent, main.getSendUserId(), receivers)		    	 	        	   
//	           }
//	    	   
//	    	 }   	    
//	      }
//	     catch (Exception e){
//	    	 throw new Exception("send message exception,error info is"+e.getMessage()); 
//	     }    
    }

    /**
     * @param sheetKey     工单主键ID
     * @param workflowName 流程名称
     * @param closeMsgType 关闭短信类型
     * @see 关闭短信提醒
     */
    public void closeMsg(String sheetKey, String workflowName, int closeMsgType) throws Exception {
        MsgServiceImpl msgService = new MsgServiceImpl();
        try {
            //解析短信服务信息文件，获取工单中文名称，以及短信服务ID

            String nodeInstantName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_INSTANT;
            String nodePreOvertimeAccpetName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_PREOVERTIME_ACCEPT;
            String nodePreOvertimeDealName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_PREOVERTIME_DEAL;
            String nodePostOvertimeAcceptName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_POSTOVERTIME_ACCPT;
            String nodePostOvertimeDealName = Constants.SMS_CONFIG + "." + workflowName + "." + Constants.VOICE_SERVICE_POSTOVERTIME_DEAL;

            String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
            String instantServiceId = SheetStaticMethod.getNodeName(filePath, nodeInstantName);
            String preOvertimeAcceptServiceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeAccpetName);
            String preOvertimeDealServiceId = SheetStaticMethod.getNodeName(filePath, nodePreOvertimeDealName);
            String postOvertimeAcceptServiceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeAcceptName);
            String postOvertimeDealServiceId = SheetStaticMethod.getNodeName(filePath, nodePostOvertimeDealName);

            switch (closeMsgType) {
                case Constants.SMS_TYPE_INSTANT: {
                    if (!instantServiceId.equals("")) msgService.closeMsg(instantServiceId, sheetKey);
                }
                case Constants.SMS_TYPE_ACCEPT: {
                    if (!preOvertimeAcceptServiceId.equals(""))
                        msgService.closeMsg(preOvertimeAcceptServiceId, sheetKey);
                    if (!postOvertimeAcceptServiceId.equals(""))
                        msgService.closeMsg(postOvertimeAcceptServiceId, sheetKey);
                }
                case Constants.SMS_TYPE_DEAL: {
                    if (!preOvertimeDealServiceId.equals("")) msgService.closeMsg(preOvertimeDealServiceId, sheetKey);
                    if (!postOvertimeDealServiceId.equals("")) msgService.closeMsg(postOvertimeDealServiceId, sheetKey);
                }
                default: {
                    if (!instantServiceId.equals("")) msgService.closeMsg(instantServiceId, sheetKey);
                    if (!preOvertimeAcceptServiceId.equals(""))
                        msgService.closeMsg(preOvertimeAcceptServiceId, sheetKey);
                    if (!preOvertimeDealServiceId.equals("")) msgService.closeMsg(preOvertimeDealServiceId, sheetKey);
                    if (!postOvertimeAcceptServiceId.equals(""))
                        msgService.closeMsg(postOvertimeAcceptServiceId, sheetKey);
                    if (!postOvertimeDealServiceId.equals("")) msgService.closeMsg(postOvertimeDealServiceId, sheetKey);
                }
            }


        } catch (Exception e) {
            throw new Exception("close instant message exception,error info is" + e.getMessage());
        }
    }

    /**
     * @param workflowName 流程名称
     * @param sheetKey     工单主健ID
     * @param sheetId      工单流水号
     * @param title        工单主题
     * @param receiveType  短信接受者类型
     * @param receiverId   短信接收者Id
     * @see 此方法用于非流程操作时派发短信，例如：催办、阶段回复、阶段建议、抄送等
     */
    public void workSM_NON_T(String workflowName, String sheetKey, String sheetId, int receiveType,
                             String receiverId) throws Exception {
//	     try {
//	    	 MsgServiceImpl   msgService = new MsgServiceImpl();
//	    	 
//	    	 //解析短信服务信息文件，获取工单中文名称，以及短信服务ID
//	    	 //下面是解析你调用是import com.boco.eoms.common.properties.XMLProperties.XMLPropertyFile；
//	    	 String nodeCnName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.SMS_PROCESS_CN_NAME;
//	    	 String nodeInstantName=Constants.SMS_CONFIG+"."+workflowName+"."+Constants.VOICE_SERVICE_INSTANT;
//	    	 String filePath = SheetStaticMethod.getFilePathForUrl(CONFIG_FILEPATH);
//	    	 String processCnName=SheetStaticMethod.getNodeName(filePath, nodeCnName);
//	    	 String instantServiceId=SheetStaticMethod.getNodeName(filePath, nodeInstantName);
//	    	 //拼接短信接受者
//	    	 String receivers="";
//	    	 if(receiveType==Constants.SMS_RECEIVE_TYPE_USER){
//	    		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_USER+","+receiverId+"#";
//	    	 }
//	    	 else if(receiveType==Constants.SMS_RECEIVE_TYPE_DEPT){
//	    		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_DEPT+","+receiverId+"#";
//	    	 }
//	    	 else if(receiveType==Constants.SMS_RECEIVE_TYPE_ROLE){
//	    		 receivers=receivers+ Constants.SMS_RECEIVE_TYPE_ROLE+","+receiverId+"#";
//	    	 }
//	    	 
//	    	 if(!receivers.equals("")) 
//	    		  receivers=receivers.substring(0,receivers.lastIndexOf("#"));
//	    	 
//	    	 System.out.println("receivers="+receivers);
//		        
//	    	 //派发通知（即时提醒）
//	    	  if(!instantServiceId.equals("")){   	   
//	    	   //获得当前时间 
//	    	   Date currentTime = new Date();
//	    	   SimpleDateFormat formatter = new SimpleDateFormat(
//	    	     "yyyy-MM-dd HH:mm:ss");
//	    	   String sendTime = formatter.format(currentTime);
//	    	   
//	    	   //拼写发送信息
//	    	  String sendContent="您启动的新业务试点工单" + sheetId + "调用的新产品编号尚没有启动相关的正式实施工单，请您核查!";
//	    	  System.out.println("sendContent====="+sendContent);
//	    	   msgService.sendMsg(instantServiceId, sendContent,
//	    		     sheetKey, receivers, sendTime);
//	    	   
//	    	 }   	    
//	      }
//	     catch (Exception e){
//	    	 throw new Exception("send message exception,error info is"+e.getMessage()); 
//	     }    
    }
}
