package com.boco.eoms.check.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.check.bo.schedulerbo.*;

public class TawSystemScheduler implements Job {
    public void execute(JobExecutionContext context) throws org.quartz.JobExecutionException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("回收开始!" + "空闲内存：" + runtime.freeMemory() / (1024 * 1024) + "M:");
        System.gc();
        System.out.println("回收结束!" + "空闲内存：" + runtime.freeMemory() / (1024 * 1024) + "M:" + com.boco.eoms.common.util.StaticMethod.getLocalString());
    }

}
