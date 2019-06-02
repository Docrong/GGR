package com.boco.eoms.workplan.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.workplan.mgr.ITawwpTaskManageInterfaceMgr;

public class TawwpSMSScheduler implements Job {
	public TawwpSMSScheduler() {
	}

	public void execute(JobExecutionContext context)
			throws org.quartz.JobExecutionException {
		BocoLog.info(this, 0, "作业计划获取网元信息");
		// TawwpTaskManageInterfaceExtendBO tawwpTaskManageInterfaceExtendBO =
		// new TawwpTaskManageInterfaceExtendBO();
		ITawwpTaskManageInterfaceMgr mgr = (ITawwpTaskManageInterfaceMgr) ApplicationContextHolder
				.getInstance().getBean("tawwpTaskManageInterfaceMgr");
		mgr.saveNetInfor();
	}

}
