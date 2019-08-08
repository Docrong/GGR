package com.boco.eoms.commons.job.listeners;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.dao.TawCommonsJobsortDao;
import com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao;
import com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao;
import com.boco.eoms.commons.job.util.JobStaticVariable;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:任务触发器
 * </p>
 * <p>
 * Apr 10, 2007 10:35:09 AM
 * </p>
 *
 * @author 秦敏
 * @version 1.0
 */
public class EomsJobListener implements JobListener {

    private TawCommonsJobmonitorDao tawCommonsJobmonitorDao;

    private TawCommonsJobsortDao tawCommonsJobsortDao;

    private TawCommonsJobsubscibeDao tawCommonsJobsubscibeDao;

    private String listenerName;

    public TawCommonsJobsubscibeDao getTawCommonsJobsubscibeDao() {
        return tawCommonsJobsubscibeDao;
    }

    public void setTawCommonsJobsubscibeDao(
            TawCommonsJobsubscibeDao tawCommonsJobsubscibeDao) {
        this.tawCommonsJobsubscibeDao = tawCommonsJobsubscibeDao;
    }

    public TawCommonsJobmonitorDao getTawCommonsJobmonitorDao() {
        return tawCommonsJobmonitorDao;
    }

    public void setTawCommonsJobmonitorDao(
            TawCommonsJobmonitorDao tawCommonsJobmonitorDao) {
        this.tawCommonsJobmonitorDao = tawCommonsJobmonitorDao;
    }

    public TawCommonsJobsortDao getTawCommonsJobsortDao() {
        return tawCommonsJobsortDao;
    }

    public void setTawCommonsJobsortDao(
            TawCommonsJobsortDao tawCommonsJobsortDao) {
        this.tawCommonsJobsortDao = tawCommonsJobsortDao;
    }

    public void setName(String listenerName) {
        this.listenerName = listenerName;
    }

    public String getName() {
        return this.listenerName;
    }

    /**
     * Called by the Scheduler when a JobDetail is about to be executed (an
     * associated Trigger has occured). 任务即将执行时调用此方法，将任务信息入库
     *
     * @param JobExecutionContext 任务信息
     * @author 秦敏
     */
    public void jobToBeExecuted(JobExecutionContext inContext) {

        JobDetail jobDetail = inContext.getJobDetail();
        String jobSubId = jobDetail.getName();
        BocoLog.debug(this, "JobListener says: Job <" + jobSubId
                + "> Is about to be executed.");
        if (jobSubId != null && jobSubId.indexOf("JOB") >= 0) {
            //暂时屏蔽任务monitor的日志记录
			/*
			TawCommonsJobsubscibe tawCommonsJobsubscibe = this.tawCommonsJobsubscibeDao
					.getSubscibeJobById(jobSubId);
			Integer jobSortId = tawCommonsJobsubscibe.getJobSortId();
			TawCommonsJobsort tawCommonsJobsort = this.tawCommonsJobsortDao
					.getTawCommonsJobsort(jobSortId);
			Integer maxExecuteTime = tawCommonsJobsort.getMaxExecuteTime();
			TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
			tawCommonsJobmonitor.setExecuteStartTime(StaticMethod
					.getLocalString());
			tawCommonsJobmonitor.setJobSortId(jobSortId);
			tawCommonsJobmonitor.setJobSubId(jobSubId);
			tawCommonsJobmonitor.setMaxExecuteTime(maxExecuteTime);
			tawCommonsJobmonitor.setStatus(new Integer(
					JobStaticVariable.STATUS_RUNNING));
			this.tawCommonsJobmonitorDao
					.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
			*/
        }
    }

    /**
     * 任务执行过程中捕获错误
     *
     * @param JobExecutionContext 任务信息
     * @author 秦敏
     */
    public void jobExecutionVetoed(JobExecutionContext inContext) {
        JobDetail jobDetail = inContext.getJobDetail();
        String jobSubId = jobDetail.getName();
        BocoLog.debug(this, "JobListener says: Job <" + jobSubId + "> Execution was vetoed.");

    }

    /**
     * 任务执行完成后调用此方法，更新任务执行情况
     *
     * @param JobExecutionContext   任务执行情况
     * @param JobExecutionException 任务执行异常类
     * @author 秦敏
     */
    public void jobWasExecuted(JobExecutionContext inContext,
                               JobExecutionException inException) {

        JobDetail jobDetail = inContext.getJobDetail();
        String jobSubId = jobDetail.getName();
        BocoLog.debug(this, "JobListener says: Job <" + jobSubId
                + "> was executed.");
        if (jobSubId != null && jobSubId.indexOf("JOB") >= 0) {
            //暂时屏蔽任务monitor的日志记录
			/*TawCommonsJobmonitor tawCommonsJobmonitor = this.tawCommonsJobmonitorDao
					.getJobMonitorBySubId(jobSubId);
			tawCommonsJobmonitor.setExecuteEndTime(StaticMethod
					.getLocalString());
			tawCommonsJobmonitor.setStatus(new Integer(
					JobStaticVariable.STATUS_END_NORMAL));
			this.tawCommonsJobmonitorDao
					.saveTawCommonsJobmonitor(tawCommonsJobmonitor);*/
        }
    }
}
