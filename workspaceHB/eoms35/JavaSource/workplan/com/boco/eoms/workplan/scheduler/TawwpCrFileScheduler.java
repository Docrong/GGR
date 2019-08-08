package com.boco.eoms.workplan.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.util.TawwpUtil;

public class TawwpCrFileScheduler implements Job {

    public TawwpCrFileScheduler() {

    }

    public void execute(JobExecutionContext context)
            throws org.quartz.JobExecutionException {

        BocoLog.info(this, 0, "作业计划接口轮巡开始");
        // 调用生成附加表的程序
        String day = TawwpUtil.getPreDay();
        // TawwpExecuteBO tawwpExecuterBO = new TawwpExecuteBO();

        ITawwpExecuteMgr mgr = (ITawwpExecuteMgr) ApplicationContextHolder
                .getInstance().getBean("tawwpExecuteMgr");
        try {
            mgr.reportDayExecute(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        BocoLog.info(this, 0, "作业计划接口轮巡结束");

    }

}
