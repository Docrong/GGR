package com.boco.eoms.sheet.netownership.job;

import java.text.ParseException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public class NetOwnershipListener implements ServletContextListener {
    private static Scheduler sched;

    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            // TODO Auto-generated method stub
            //创建LzstoneTimeTask的定时任务
            JobDetail jobDetail = new JobDetail("netownershipJob", sched.DEFAULT_GROUP, NetOwnershipSyncSchedule.class);
            //目标 创建任务计划
            CronTrigger trigger = new CronTrigger("netownershipTrigger", "netownership", "0 0 12 * * ?");
            //0 0 12 * * ? 代表每天的中午12点触发
            sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();
            sched.scheduleJob(jobDetail, trigger);
            sched.start();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        try {
            sched.shutdown();
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
