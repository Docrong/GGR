package com.boco.eoms.sheet.connectTest.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.connectTest.bo.ConnectTestBo;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceJobStatic;


public class ConnectTestSchedule implements Job {

    private ConnectTestBo connectBo = ConnectTestBo.getInstance();

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // TODO 自动生成方法存根
        //屏蔽 by lyg
//		boolean ifExceuteJob=InterfaceJobStatic.isIfExcuteJob();
//		if (!ifExceuteJob) {
//			BocoLog.info(ConnectTestSchedule.class, "Job is running wait for next Schedule");
//		} else {
//			InterfaceJobStatic.setIfExcuteJob(false);
//			try{
//				System.out.println("开始轮巡");
//				connectBo.connectInfo();				
//			 } catch(Exception e){
//				
//			}
//			BocoLog.info(ConnectTestSchedule.class, "Job is running over");
//			InterfaceJobStatic.setIfExcuteJob(true);
//		}
    }

}
