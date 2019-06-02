package com.boco.eoms.commons.job.sample;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.commons.loging.BocoLog;

/**
 * 
 * <p>Title:任务调度组件sample类
 * </p>
 * <p>Description:
 * </p>
 * <p>Apr 23, 2007 10:32:52 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class JobSampleSchedule implements Job {
	
 public void execute(JobExecutionContext context)
	    throws JobExecutionException {
   try {
	 //以下代码主要完成任务的实现
   } 
   catch (Exception e) {
	   BocoLog.error(this, e.toString());
   }
 }
}
