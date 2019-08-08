package com.boco.eoms.message.scheduler;

import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.message.mgr.ISchedulerManager;
import com.boco.eoms.message.mgr.ISmsMonitorManager;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.message.util.MsgConstants;

public class MonitorScheduler implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        ISmsMonitorManager monitorMgr = (ISmsMonitorManager) ApplicationContextHolder
                .getInstance().getBean("IsmsMonitorManager");
        ISchedulerManager schedulerMgr = (ISchedulerManager) ApplicationContextHolder
                .getInstance().getBean("IschedulerManager");
        List monitorList = monitorMgr.listNeedSendMsg();
        //ISmsOuterConfig smsOuter = new SmsOuterConfigImpl();

        Iterator it = monitorList.iterator();
        ISmsServiceManager serviceMgr = (ISmsServiceManager) ApplicationContextHolder
                .getInstance().getBean("IsmsServiceManager");
        String content = "";
        SmsService service = null;
        BocoLog.info(this, "ready to update current scheduler messages to deleted status!");
        while (it.hasNext()) {
            SmsMonitor monitor = new SmsMonitor();
            monitor = (SmsMonitor) it.next();
            monitor.setDeleted(MsgConstants.DELETED);
            monitorMgr.saveSmsMonitor(monitor);
        }
        BocoLog.info(this, "update successful!");
        Iterator removeIt = monitorList.iterator();
        while (removeIt.hasNext()) {
            BocoLog.info(this, "prepare to delete the messages what deleted status is 1");
            SmsMonitor monitor = new SmsMonitor();
            monitor = (SmsMonitor) removeIt.next();
            content = monitor.getContent().trim();
            String mobiles = monitor.getMobile().trim();

            if (monitor.getRegetData() != null
                    && monitor.getRegetData().equals("true")) {
                service = serviceMgr.getSmsService(monitor.getServiceId());
                // 通过webservice实时获取要发送的内容开始
                // MsgWebService rService =
                // (MsgWebService)ApplicationContextHolder.getInstance().getBean("MsgRemoteService");

                // 通过webservice实时获取要发送的内容结束

                // 发送成功删除轮训表中数据，记录日志
            } else {
                // 直接调用短信网关发送
                try {
                    // 某些用户可能有两个手机号,用逗号分隔
                    if (mobiles.indexOf(MsgConstants.MOBILE_SEPARATOR) >= 0) {
                        String[] mobileArray = mobiles
                                .split(MsgConstants.MOBILE_SEPARATOR);
                        if (mobileArray != null) {
                            for (int i = 0; i < mobileArray.length; i++) {
                                String mobile = mobileArray[i];
                                BocoLog.info(this, "user has one more mobile,prepare to get in the netgate send method");
                                if (schedulerMgr.smsMonitorScheduler(mobile, content)) {
                                    BocoLog.info(this, "send successful,prepare to removeSmsMonitor!");
                                    //发送成功则从eoms数据库轮询中删除待发送内容

                                } else {
                                    BocoLog.info(this, "send failure,set deleted status from 1 to 1");
                                    monitor.setDeleted(MsgConstants.UNDELETED);
                                    monitorMgr.saveSmsMonitor(monitor);
                                }
                            }
                            monitorMgr.removeSmsMonitor(monitor.getId());
                        }
                    } else {
                        BocoLog.info(this, "user has one mobile,prepare to get in the netgate send method");
                        if (schedulerMgr.smsMonitorScheduler(mobiles, content)) {
                            BocoLog.info(this, "send successful,prepare to removeSmsMonitor!");
                            //发送成功则从eoms数据库轮询中删除待发送内容
                            monitorMgr.removeSmsMonitor(monitor.getId());
                            BocoLog.info(this, "delete successful!");
                        } else {
                            BocoLog.info(this, "send failure,set deleted status from 1 to 1");
                            monitor.setDeleted(MsgConstants.UNDELETED);
                            monitorMgr.saveSmsMonitor(monitor);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
