/*
 * Created on 2008-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.sheet.base.kpi.service.IBaseKPIManager;


/**
 * @author xqz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseKPIJob implements Job{

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(" KPI job start...");
		
		String[] subid = arg0.getJobDetail().getJobListenerNames();
		this.init(subid[0]);
		
		System.out.println(" KPI job end...");
	}

	public abstract String getBeanId();
	
	public IBaseKPIManager loadBean(){
		return (IBaseKPIManager)ApplicationContextHolder.getInstance().getBean(this.getBeanId());
	}
	
	private void init(String subId){
		try{
			ITawCommonsJobmonitorManager tawCommonsJobmonitorManager = (ITawCommonsJobmonitorManager)ApplicationContextHolder.getInstance().getBean("ItawCommonsJobmonitorManager");
			TawCommonsJobmonitor tawCommonsJobmonitor = tawCommonsJobmonitorManager.getLastJobmonitorBySubId(subId);
			String startTime = tawCommonsJobmonitor.getExecuteEndTime();
			IBaseKPIManager baseKPI = this.loadBean();
			baseKPI.run(startTime);
		}catch(Exception err){
			err.printStackTrace();
		}
	}
}
