package com.boco.eoms.duty.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.duty.cache.TawDutyCacheBean;

public class DutyFlushCacheSchedule implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		TawDutyCacheBean tawDutyCacheBean = new TawDutyCacheBean();
		tawDutyCacheBean.addDutyCache();
	}
}
