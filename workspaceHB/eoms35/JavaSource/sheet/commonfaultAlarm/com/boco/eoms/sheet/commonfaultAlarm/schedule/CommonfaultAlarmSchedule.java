package com.boco.eoms.sheet.commonfaultAlarm.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.commonfaultAlarm.bo.CommonfaultAlarmInfoBo;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceJobStatic;
//import com.boco.eoms.sheet.sheetdelete.bo.WfSheetDeleteInfoBo;

public class CommonfaultAlarmSchedule implements Job {
	private CommonfaultAlarmInfoBo wfinfo = CommonfaultAlarmInfoBo.getInstance();
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//屏蔽by lyg
//		boolean ifExceuteJob=InterfaceJobStatic.isIfExcuteJob();
//		if (!ifExceuteJob) {
//			BocoLog.info(CommonfaultAlarmSchedule.class, "Job is running wait for next Schedule");
//		} else {
//			InterfaceJobStatic.setIfExcuteJob(false);
//			try{
//				wfinfo.sendInfo();
//			 } catch(Exception e){
//				
//			}
//			BocoLog.info(CommonfaultAlarmSchedule.class, "Job is running over");
//			InterfaceJobStatic.setIfExcuteJob(true);
//		}
	

	}

}
