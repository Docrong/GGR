package com.boco.eoms.sheet.commontask.schedule;

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
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.commontask.model.CommonTaskMain;
import com.boco.eoms.sheet.commontask.service.ICommonTaskMainManager;
import com.boco.eoms.sheet.commontask.service.ICommonTaskTaskManager;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XMLProperties;


public class UndoTaskAdviceSchedule implements Job{
	
	public UndoTaskAdviceSchedule(){
		
	}
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		boolean ifDayJob=InterfaceJobStatic.isIfExecuteJob();
		if (!ifDayJob) { 
			BocoLog.info(UndoTaskAdviceSchedule.class, "CommonTask UndoTaskAdviceSchedule is running wait for next Schedule");
		} else {
		   InterfaceJobStatic.setIfExecuteJob(false);
		   try{
			   ICommonTaskTaskManager commontaskMgr = (ICommonTaskTaskManager) ApplicationContextHolder
				   .getInstance().getBean("icommontaskTaskManager");	
			   ICommonTaskMainManager commonmainMgr = (ICommonTaskMainManager) ApplicationContextHolder
			        .getInstance().getBean("iCommonTaskMainManager");
			   BaseMain mainObject = (BaseMain) commonmainMgr.getMainObject()
				      .getClass().newInstance();
			   
			   //获取网络三级分类过滤条件
			   XMLProperties file=XmlManage.getFile("/config/commontask-util.xml");
			   String mainNetSort1=StaticMethod.nullObject2String(file.getProperty("mainNetSort1"));
			   String mainNetSort2=StaticMethod.nullObject2String(file.getProperty("mainNetSort2"));
			   String mainNetSort3=StaticMethod.nullObject2String(file.getProperty("mainNetSort3"));
			   
		       Map condition = new HashMap();
		       condition.put("mainObject", mainObject);
		       condition.put("mainNetSort1", mainNetSort1);
		       condition.put("mainNetSort2", mainNetSort2);
		       condition.put("mainNetSort3", mainNetSort3);
			   List undoTaskList=commontaskMgr.getAllUndoTaskList(condition); //根据网络三级分类，查询满足条件的未处理的工单
			   //获取当前系统时间
			   java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("GMT+08:00"));
			   Calendar c=Calendar.getInstance(java.util.TimeZone.getDefault());
			   Date localTime=c.getTime();
			   SimpleDateFormat formatter = new SimpleDateFormat(
	    	     "yyyy-MM-dd HH:mm:ss");
	    	   String messageSendTime = formatter.format(localTime);
	    	   
			   MsgServiceImpl   msgService = new MsgServiceImpl();
			   String messageServiceId=StaticMethod.nullObject2String(file.getProperty("messageServiceId"));
			   
			   for (int i=0;undoTaskList!=null&&i<undoTaskList.size();i++){
				   Object[] tmpObjArr=(Object[])undoTaskList.get(i);
				   ITask tmptask = (ITask) tmpObjArr[0];
				   int mainAdviceNum=StaticMethod.nullObject2int(tmpObjArr[1],0);
				   Date sendTime=tmptask.getSendTime();
				   if (!(null == sendTime || sendTime.equals(""))) {
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
						   String sendContent= "当前有通用任务工单" + sheetId +"，主题为"+ title + ",已经" +diffDate+"天未处理，请处理回复";
						   msgService.sendMsg(messageServiceId, sendContent,taskId, receivers,messageSendTime);
						   BocoLog.info(UndoTaskAdviceSchedule.class, "任务工单短信督办，sendContent="+sendContent);
						   
						   //修改main表中工单催办次数
						   CommonTaskMain main=(CommonTaskMain)commonmainMgr.getSingleMainPO(sheetKey);
						   main.setMainAdviceNum(mainAdviceNum+1);
						   main.setMainAdviceContent("当前工单已经催办"+main.getMainAdviceNum()+"次");
						   commonmainMgr.saveOrUpdateMain(main);
					   }
				   }
			   }	   
		   
			 } catch(Exception e){
				
			}
			BocoLog.info(UndoTaskAdviceSchedule.class, "CommonTask UndoTaskAdviceSchedule is running over");
			InterfaceJobStatic.setIfExecuteJob(true);
		}
  }

}
