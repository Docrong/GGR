package com.boco.eoms.crm.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.crm.bo.CrmWaitInfoBo;

public class CrmWaitInfoSchedule implements Job {

    private CrmWaitInfoBo crmBo = CrmWaitInfoBo.getInstance();

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("开始发送crm接口数据：");
        crmBo.sendInfo();
    }


    public static void main(String args[]) {
        CrmWaitInfoSchedule crmSchedule = new CrmWaitInfoSchedule();

    }

}
