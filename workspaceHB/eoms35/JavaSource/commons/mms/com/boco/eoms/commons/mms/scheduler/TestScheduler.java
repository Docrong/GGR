package com.boco.eoms.commons.mms.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestScheduler implements Job {

	public Logger logger = Logger.getLogger(this.getClass());
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String classname = "com.boco.eoms.commons.mms.scheduler.TestScheduler";
		System.err.println("TestScheduler success");
		
		String JobName = "";
		if (context != null) {
			JobName = context.getJobDetail().getName();
		}
		
		logger.info("\n执行定制统计的JobName是 ：" + JobName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}

}
