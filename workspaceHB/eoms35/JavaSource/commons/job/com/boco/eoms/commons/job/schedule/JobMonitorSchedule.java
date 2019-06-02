package com.boco.eoms.commons.job.schedule;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.log.webapp.bo.impl.TawCommonLog;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description: 任务执行情况监控
 * </p>
 * <p>Apr 10, 2007 10:40:31 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class JobMonitorSchedule implements Job {

	/**
	 * 监控任务执行情况，若超时则通知系统管理员
	 * @author 秦敏
	 * @param  JobExecutionContext 任务信息
	 */
  public void execute(JobExecutionContext context)
	throws JobExecutionException {
   
   try {
      ITawCommonsJobmonitorManager tawCommonsJobmonitorManager = (ITawCommonsJobmonitorManager) ApplicationContextHolder
					.getInstance().getBean("ItawCommonsJobmonitorManager");
	  List listJob = tawCommonsJobmonitorManager.getRunningJob();
	  for (int i = 0; i < listJob.size(); i++) {
	     TawCommonsJobmonitor tawCommonsJobmonitor = (TawCommonsJobmonitor) listJob.get(i);
		 String startTime = tawCommonsJobmonitor.getExecuteStartTime();
		 int maxExecuteTime = tawCommonsJobmonitor.getMaxExecuteTime().intValue();
		 String currentTime = StaticMethod.getLocalString();
		 int realExecuteTime = StaticMethod.getTimeDistance(startTime,currentTime);
		 if (realExecuteTime > 0 && maxExecuteTime > 0
			 && realExecuteTime > maxExecuteTime) {
			  TawCommonLog.saveLog(this, "", "", "1201",
				 "JobListener says: Job <"
				  + tawCommonsJobmonitor.getJobSubId()+ "> execute overtime.");
		      }
			}
	 } 
      catch (Exception e) {
    	 BocoLog.error(this, e.toString());
		 throw new JobExecutionException("任务执行错误");
	   }
	}

}
