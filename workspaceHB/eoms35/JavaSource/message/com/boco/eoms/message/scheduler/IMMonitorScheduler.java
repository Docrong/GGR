package com.boco.eoms.message.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.message.mgr.IIMMonitorManager;

public class IMMonitorScheduler implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
	
		IIMMonitorManager immgr=(IIMMonitorManager)ApplicationContextHolder.getInstance().getBean("IimMonitorManager");
		immgr.sendScheduler();
	}
}
