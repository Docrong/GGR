package com.boco.eoms.commons.job.service.impl;

import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao;
import com.boco.eoms.commons.job.dao.TawCommonsJobsortDao;
import com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao;
import com.boco.eoms.commons.job.exception.ScheduleRunException;
import com.boco.eoms.commons.job.listeners.EomsJobListener;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.commons.loging.BocoLog;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务监控service实现类
 * </p>
 * <p>Apr 10, 2007 10:42:23 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsJobmonitorManagerImpl extends BaseManager implements
		ITawCommonsJobmonitorManager {
	private TawCommonsJobmonitorDao tawCommonsJobmonitorDao;

	private String defaultGroup = "defaultgroup";

	private String realtimeGroup = "realtimeGroup";

	private TawCommonsJobsubscibeDao tawCommonsJobsubscibeDao;

	private TawCommonsJobsortDao tawCommonsJobsortDao;

	private StdSchedulerFactory sf = null;

	private static Scheduler sched = null;

	public TawCommonsJobsortDao getTawCommonsJobsortDao() {
		return tawCommonsJobsortDao;
	}

	public void setTawCommonsJobsortDao(
			TawCommonsJobsortDao tawCommonsJobsortDao) {
		this.tawCommonsJobsortDao = tawCommonsJobsortDao;
	}

	public TawCommonsJobsubscibeDao getTawCommonsJobsubscibeDao() {
		return tawCommonsJobsubscibeDao;
	}

	public Scheduler getSched() {
		return sched;
	}

	public void setTawCommonsJobsubscibeDao(
			TawCommonsJobsubscibeDao tawCommonsJobsubscibeDao) {
		this.tawCommonsJobsubscibeDao = tawCommonsJobsubscibeDao;
	}

	public TawCommonsJobmonitorDao getTawCommonsJobmonitorDao() {
		return tawCommonsJobmonitorDao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawCommonsJobmonitorDao(TawCommonsJobmonitorDao dao) {
		this.tawCommonsJobmonitorDao = dao;
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager#getTawCommonsJobmonitors(com.boco.eoms.commons.job.model.TawCommonsJobmonitor)
	 */
	public List getTawCommonsJobmonitors(
			final TawCommonsJobmonitor tawCommonsJobmonitor) {
		return tawCommonsJobmonitorDao
				.getTawCommonsJobmonitors(tawCommonsJobmonitor);
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager#getTawCommonsJobmonitor(String
	 *      id)
	 */
	public TawCommonsJobmonitor getTawCommonsJobmonitor(final String id) {
		return tawCommonsJobmonitorDao.getTawCommonsJobmonitor(new String(id));
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager#saveTawCommonsJobmonitor(TawCommonsJobmonitor
	 *      tawCommonsJobmonitor)
	 */
	public void saveTawCommonsJobmonitor(
			TawCommonsJobmonitor tawCommonsJobmonitor) {
		tawCommonsJobmonitorDao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
	}

	/**
	 * @see com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager#removeTawCommonsJobmonitor(String
	 *      id)
	 */
	public void removeTawCommonsJobmonitor(final String id) {
		tawCommonsJobmonitorDao.removeTawCommonsJobmonitor(new String(id));
	}
	/**
	 *任务调度器初始化
	 * 
	 * @author 秦敏
	 */
	public void instance() throws ScheduleRunException {
	  if (sched == null) {
		    init();
	    }
	}

	/**
	 * 任务调度器初始化
	 * @author 秦敏
	 */
	private void init() throws ScheduleRunException {
		try{
		 sf = new StdSchedulerFactory();
		 sched = sf.getScheduler();
		 String[] groups = sched.getTriggerGroupNames();
		 for (int i = 0; i < groups.length; i++) {
		   String[] names = sched.getTriggerNames(groups[i]);
		   for (int j = 0; j < names.length; j++) {
			  sched.unscheduleJob(names[j], groups[i]);
			}
		}

		groups = sched.getJobGroupNames();
		for (int i = 0; i < groups.length; i++) {
			String[] names = sched.getJobNames(groups[i]);
			for (int j = 0; j < names.length; j++) {
				sched.deleteJob(names[j], groups[i]);
			}
		}

		// 删除
		this.tawCommonsJobmonitorDao.removeRunningJob();
		}
	   catch(SchedulerException e){
		   BocoLog.error(this, e.toString());
		   throw new ScheduleRunException("任务初始化失败");
	   }
	}

	/**
	 * 系统启动时装载任务
	 * 
	 * @author 秦敏
	 */
	public void run() throws ScheduleRunException {
		Date firstRunTime = TriggerUtils.getNextGivenMinuteDate(null, 5);
		try {
			List listAllSubscribe = tawCommonsJobsubscibeDao.getTawCommonsJobsubscibes();
			for (int i = 0; i < listAllSubscribe.size(); i++) {
				TawCommonsJobsubscibe tawJobSubscription = (TawCommonsJobsubscibe) listAllSubscribe
						.get(i);
				String subId = tawJobSubscription.getSubId();
				String type = tawJobSubscription.getJobType();
				String frequency = tawJobSubscription.getJobCycle();
				Integer jobSortId = tawJobSubscription.getJobSortId();
				TawCommonsJobsort tawCommonsJobsort = tawCommonsJobsortDao
						.getTawCommonsJobsort(jobSortId);
				String classname = tawCommonsJobsort.getJobClassName();
				JobDetail job = new JobDetail(subId, defaultGroup, Class
						.forName(classname).newInstance().getClass());

				String triggername = "trigg_" + subId;
				if (type.equalsIgnoreCase("cron")) {
					CronTrigger trigger = new CronTrigger(triggername,
							defaultGroup, subId, defaultGroup, frequency);
					// Set up the listener
					EomsJobListener listener = (EomsJobListener) ApplicationContextHolder
							.getInstance().getBean("eomsJobListener");
					listener.setName(subId);
					sched.addJobListener(listener);
					// make sure the listener is associated with the job
					job.addJobListener(listener.getName());
					Date ft = sched.scheduleJob(job, trigger);
					BocoLog.debug(this,  "任务<"	+ subId + ">开始执行时间" + ft);
				} else if (type.equalsIgnoreCase("simple")) {

					long interval = StaticMethod.getLongValue(frequency);
					if (interval > 0) {
						SimpleTrigger trigger = new SimpleTrigger(triggername,
								defaultGroup, subId, defaultGroup,
								firstRunTime, null,
								SimpleTrigger.REPEAT_INDEFINITELY, interval);
						// Set up the listener
						EomsJobListener listener = (EomsJobListener) ApplicationContextHolder.getInstance().getBean("eomsJobListener");
						listener.setName(subId);
						sched.addJobListener(listener);
						// make sure the listener is associated with the job
						job.addJobListener(listener.getName());
						Date ft = sched.scheduleJob(job, trigger);
						BocoLog.debug(this,  "任务<"	+ subId + ">开始执行时间" + ft);
					}
				}
			}
			sched.start();
		} catch (Exception e) {
			BocoLog.error(this, e.toString());
			throw new ScheduleRunException("任务装载错误");
		}

	}

	/**
	 * 添加任务
	 * @author 秦敏
	 * @param tawCommonsJobsubscibe
	 * @throws Exception
	 */
	public void addJob(TawCommonsJobsubscibe tawCommonsJobsubscibe)
			throws ScheduleRunException {
		try {
			Date date = TriggerUtils.getNextGivenMinuteDate(null, 5);
			String subId = tawCommonsJobsubscibe.getSubId();
			String jobType = tawCommonsJobsubscibe.getJobType();
			String frequency = tawCommonsJobsubscibe.getJobCycle();
			Integer jobSortId = tawCommonsJobsubscibe.getJobSortId();
			TawCommonsJobsort tawCommonsJobsort = tawCommonsJobsortDao
					.getTawCommonsJobsort(jobSortId);
			String className = tawCommonsJobsort.getJobClassName();
			JobDetail job = new JobDetail(subId, realtimeGroup, Class.forName(
					className).newInstance().getClass());
			String triggername = "trigg_" + subId;
			if (jobType.equalsIgnoreCase("cron")) {
				CronTrigger trigger = new CronTrigger(triggername,
						realtimeGroup, subId, realtimeGroup, frequency);
				// Set up the listener
				EomsJobListener listener = (EomsJobListener) ApplicationContextHolder
						.getInstance().getBean("eomsJobListener");
				listener.setName(subId);
				sched.addJobListener(listener);
				// make sure the listener is associated with the job
				job.addJobListener(listener.getName());
				Date ft = sched.scheduleJob(job, trigger);
				BocoLog.debug(this,  "任务<"	+ subId + ">开始执行时间" + ft);
			} else if (jobType.equalsIgnoreCase("simple")) {
				long interval = StaticMethod.getLongValue(frequency);
				if (interval > 0) {
					SimpleTrigger trigger = new SimpleTrigger(triggername,
							realtimeGroup, subId, realtimeGroup, date, null,
							SimpleTrigger.REPEAT_INDEFINITELY, interval);
					// Set up the listener
					EomsJobListener listener = (EomsJobListener) ApplicationContextHolder
							.getInstance().getBean("eomsJobListener");
					listener.setName(subId);
					sched.addJobListener(listener);
					// make sure the listener is associated with the job
					job.addJobListener(listener.getName());
					Date ft = sched.scheduleJob(job, trigger);
					BocoLog.debug(this,  "任务<"	+ subId + ">开始执行时间" + ft);
				}
			}
			sched.start();
		} catch (Exception e) {
			BocoLog.error(this, e.toString());
			throw new ScheduleRunException("任务添加报错");
		}
	}

	/**
	 * 删除任务
	 * @author 秦敏
	 * @param job_name 任务名称
	 */
	public void deleteJob(String job_name) throws ScheduleRunException {
		try {
			sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
			String[] groups = { "defaultGroup", "realtimeGroup" };
			String trigname = "trigg_" + job_name;
			for (int i = 0; i < groups.length; i++) {
				sched.unscheduleJob(trigname, groups[i]);
			}
			for (int j = 0; j < groups.length; j++) {
				sched.deleteJob(job_name, groups[j]);
			}
		} catch (Exception e) {
			BocoLog.error(this, e.toString());
			throw new ScheduleRunException("任务删除报错");
		}
	}

	/**
	 * 修改任务
	 * @author 秦敏
	 * @param tawCommonsJobsubscibe 任务信息
	 */
	public void modifyJob(TawCommonsJobsubscibe tawCommonsJobsubscibe)
			throws ScheduleRunException {
		try {
			String jobSubId = tawCommonsJobsubscibe.getSubId();
			this.deleteJob(jobSubId);
			this.addJob(tawCommonsJobsubscibe);
		} catch (Exception e) {
			BocoLog.error(this, e.toString());
			throw new ScheduleRunException("任务修改报错");
		}
	}
	/**
	 * 获取当前所有的正在运行的任务
	 * @return
	 * @author 秦敏
	 */
	public List getRunningJob() throws Exception {
		return tawCommonsJobmonitorDao.getRunningJob();
	}
	/**
	 * 删除当前所有正在运行的任务
	 * 
	 * @author 秦敏
	 */
	public void removeRunningJob() throws Exception {
		tawCommonsJobmonitorDao.removeRunningJob();
	}
	/**
	 * 获取最近一次执行job的时间
	 * @param subId 订阅号
	 * @return
	 */
	public TawCommonsJobmonitor getLastJobmonitorBySubId(String subId){
		return tawCommonsJobmonitorDao.getLastJobmonitorBySubId(subId);
	}
}
