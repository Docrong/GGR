package com.boco.eoms.sheet.resourceaffirm.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.resourceaffirm.model.ResourceAffirmMain;
import com.boco.eoms.sheet.resourceaffirm.service.IResourceAffirmMainManager;
import com.boco.eoms.sheet.resourceaffirm.service.IResourceAffirmTaskManager;

	public class UndoTaskAdviceSchedule implements Job{
		
		public UndoTaskAdviceSchedule(){
			
		}
		
		public void execute(JobExecutionContext context)
				throws JobExecutionException {
			
			boolean ifDayJob=InterfaceJobStatic.isIfExecuteJob();
			if (!ifDayJob) { 
				BocoLog.info(UndoTaskAdviceSchedule.class, "Resourceaffirm UndoTaskAdviceSchedule is running wait for next Schedule");
			} else {
			   InterfaceJobStatic.setIfExecuteJob(false);
			   try{
				   IResourceAffirmMainManager mainMgr = (IResourceAffirmMainManager) ApplicationContextHolder
					   .getInstance().getBean("iResourceAffirmMainManager");	
				   IResourceAffirmTaskManager taskMgr = (IResourceAffirmTaskManager) ApplicationContextHolder
				        .getInstance().getBean("iresourceaffirmTaskManager");
				   BaseMain mainObject = (BaseMain) mainMgr.getMainObject()
					      .getClass().newInstance();
				   
			       Map condition = new HashMap();
			       condition.put("mainObject", mainObject);
				   List undoTaskList=taskMgr.getAllUndoTaskList(condition); //查询当前系统中所有未处理的工单
				   //获取当前系统时间
				   java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("GMT+08:00"));
				   Calendar c=Calendar.getInstance(java.util.TimeZone.getDefault());
				   Date localTime=c.getTime();
				   SimpleDateFormat formatter = new SimpleDateFormat(
		    	     "yyyy-MM-dd HH:mm:ss");
		    	   String messageSendTime = formatter.format(localTime);
		    	   
				   MsgServiceImpl   msgService = new MsgServiceImpl();
				   XMLProperties file=XmlManage.getFile("/config/resourceAffirm-crm.xml");
				   String messageServiceId=StaticMethod.nullObject2String(file.getProperty("base.messageServiceId"));
				   
				   for (int i=0;undoTaskList!=null&&i<undoTaskList.size();i++){
					   Object[] tmpObjArr=(Object[])undoTaskList.get(i);
					   ITask tmptask = (ITask) tmpObjArr[0];
					   int mainAdviceNum=StaticMethod.nullObject2int(tmpObjArr[1],0);
					   Date sendTime=tmptask.getSendTime();
					   int diffDate=StaticMethod.diffDate(localTime, sendTime);
					   if((diffDate>=((mainAdviceNum+1)*3)-1)&&(diffDate<=((mainAdviceNum+1)*3))){
						   //短信督办
						   String sheetKey=StaticMethod.nullObject2String(tmptask.getSheetKey());
						   String sheetId=StaticMethod.nullObject2String(tmptask.getSheetId());
						   String title=StaticMethod.nullObject2String(tmptask.getTitle());
						   String taskId=StaticMethod.nullObject2String(tmptask.getId());
						   String taskOwner=StaticMethod.nullObject2String(tmptask.getTaskOwner());
						   String taskStatus=StaticMethod.nullObject2String(tmptask.getTaskStatus());
						   String operateType=StaticMethod.nullObject2String(tmptask.getOperateType());
						   String receivers="";
						   //拼写短信接受者
						   if(Constants.TASK_STATUS_CLAIMED.equals(taskStatus)){ 
							  //工单已接单，催办接单人员
							   receivers=Constants.SMS_RECEIVE_TYPE_USER+","+taskOwner+"#";						   
						   }else {
							 //工单未接单，按照派往对象类型来催办
							  if("user".equals(operateType)){
								  receivers=Constants.SMS_RECEIVE_TYPE_USER+","+taskOwner+"#";	  
							  }else if("dept".equals(operateType)){
								  receivers=Constants.SMS_RECEIVE_TYPE_DEPT+","+taskOwner+"#";	
							  }else if("subrole".equals(operateType)){
								  receivers=Constants.SMS_RECEIVE_TYPE_ROLE+","+taskOwner+"#";	
							  }
						   }
						   //拼写发送信息
				    	   String sendContent= "当前有CRM接口流程下资源确认流程" + sheetId +"已经" +diffDate+"天未处理，主题为"+title+",请处理回复";
				    	   msgService.sendMsg(messageServiceId, sendContent,taskId, receivers,messageSendTime);
						   BocoLog.info(UndoTaskAdviceSchedule.class, "资源确认工单短信督办，sendContent="+sendContent);
						   
						   //修改main表中工单催办次数
						   ResourceAffirmMain main=(ResourceAffirmMain)mainMgr.getSingleMainPO(sheetKey);
						   main.setMainAdviceNum(mainAdviceNum+1);
						   main.setMainAdviceContent("当前工单已经催办"+main.getMainAdviceNum()+"次");
						   mainMgr.saveOrUpdateMain(main);
					   }
				   }	   
			   
				 } catch(Exception e){
					
				}
				BocoLog.info(UndoTaskAdviceSchedule.class, "Resourceaffirm UndoTaskAdviceSchedule is running over");
				InterfaceJobStatic.setIfExecuteJob(true);
			}
	  }
}
