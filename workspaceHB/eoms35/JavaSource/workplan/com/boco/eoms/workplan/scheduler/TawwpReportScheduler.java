package com.boco.eoms.workplan.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.workplan.util.TawwpUtil;

public class TawwpReportScheduler implements Job {

    public TawwpReportScheduler() {
    }

    public void execute(JobExecutionContext context)
            throws org.quartz.JobExecutionException {

        BocoLog.info(this, 0, "作业计划接口轮巡开始");
        // 调用生成附加表的程序
        String day = TawwpUtil.getPreDay();
        // TawwpExecuteBO tawwpExecuterBO = new TawwpExecuteBO();

        try {
            // tawwpExecuterBO.reportExcel(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        BocoLog.info(this, 0, "作业计划接口轮巡结束");

    }

}
