package com.boco.eoms.sheet.interfaceBase.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.interfaceBase.bo.WfInterfaceInfoBo;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceJobStatic;

public class WfInterfaceInfoSchedule implements Job {

	private WfInterfaceInfoBo wfBo = WfInterfaceInfoBo.getInstance();

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//屏蔽 by lyg
//		boolean ifExceuteJob=InterfaceJobStatic.isIfExcuteJob();
//		if (!ifExceuteJob) {
//			BocoLog.info(WfInterfaceInfoSchedule.class, "Job is running wait for next Schedule");
//		} else {
//			InterfaceJobStatic.setIfExcuteJob(false);
//			try{
//				System.out.println("开始轮巡");
//				wfBo.sendInfo();				
//			 } catch(Exception e){
//				
//			}
//			BocoLog.info(WfInterfaceInfoSchedule.class, "Job is running over");
//			InterfaceJobStatic.setIfExcuteJob(true);
//		}
	}

//	public static void main(String args[]) {
//		for(int i =0;i<100;i++){
//		WfInterfaceInfoBo.getInstance().sendInfo();
//		}
//	}

}
