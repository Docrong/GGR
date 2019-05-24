package com.boco.eoms.commons.job.test;

import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.commons.job.service.ITawCommonsJobsubscibeManager;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:任务执行测试类
 * </p>
 * <p>
 * Apr 10, 2007 10:55:10 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 *  
 */
public class TawCommonsJobTest extends ConsoleTestCase {
	private ITawCommonsJobmonitorManager monitorMgr;

	private ITawCommonsJobsubscibeManager subMgr;

	protected void onSetUpInTransaction() throws Exception {
		monitorMgr = (ITawCommonsJobmonitorManager) this
				.getBean("ItawCommonsJobmonitorManager");
		subMgr = (ITawCommonsJobsubscibeManager) this
				.getBean("ItawCommonsJobsubscibeManager");
		super.setUp();
	}

	/**
	 * 测试任务调度器初始化
	 * 
	 * @throws Exception
	 */
	public void testInstance() throws Exception {
		monitorMgr.removeRunningJob();
		TawCommonsJobmonitor monitor = new TawCommonsJobmonitor();
		monitor.setStatus(new Integer(1));
		monitorMgr.saveTawCommonsJobmonitor(monitor);
		List results = monitorMgr.getRunningJob();
		assertTrue(results.size() == 1);

		monitorMgr.instance();
		results = monitorMgr.getRunningJob();
		assertTrue(results.size() == 0);
	}

	/**
	 * 测试任务的装载
	 * 
	 * @throws Exception
	 */
	public void testJobLoad() throws Exception {
		List results = subMgr.getTawCommonsJobsubscibes();
		monitorMgr.instance();
		monitorMgr.run();
		Scheduler sched = monitorMgr.getSched();
		String[] jobs = sched.getJobNames("defaultgroup");
		assertTrue(jobs.length == results.size());
	}

	/**
	 * 测试任务的添加与删除
	 * 
	 * @throws Exception
	 */
	public void testAddAndRemoveJobLoad() throws Exception {
		// 模拟数据
		TawCommonsJobsubscibe temp = new TawCommonsJobsubscibe();
		temp.setJobCycle("30000");
		temp.setJobSortId(new Integer(3));
		temp.setJobType("simple");
		temp.setDeleted(new Integer(0));
		String subId = subMgr.newSubId(String.valueOf(temp.getJobSortId()));
		//增加任务
		subMgr.saveTawCommonsJobsubscibe(temp, true);
		JobDetail job = monitorMgr.getSched().getJobDetail(subId,
				"realtimeGroup");
		String triggername = "trigg_" + subId;
		SimpleTrigger trigger = (SimpleTrigger) monitorMgr.getSched()
				.getTrigger(triggername, "realtimeGroup");
		String jobName = job.getName();
		assertNotNull(job);
		assertNotNull(trigger);
		assertEquals(subId, jobName);
		//修改任务
		temp.setJobType("cron");
		temp.setJobCycle("0 0 10 * * ?");
		subMgr.saveTawCommonsJobsubscibe(temp, false);
		job = monitorMgr.getSched().getJobDetail(subId, "realtimeGroup");
		CronTrigger cronTrigger = (CronTrigger) monitorMgr.getSched()
				.getTrigger(triggername, "realtimeGroup");
		assertNotNull(job);
		assertNotNull(cronTrigger);
		assertEquals(subId, jobName);

		List results = subMgr.getTawCommonsJobsubscibes();
		String subscibeId = "";
		if (results != null) {
			for (int i = 0; i < results.size(); i++) {
				TawCommonsJobsubscibe subscibe = (TawCommonsJobsubscibe) results
						.get(i);
				if (subscibe.getSubId() != null
						&& subscibe.getSubId().equals(subId)) {
					subscibeId = subscibe.getId();
					break;
				}
			}
		}
		//删除任务
		subMgr.removeTawCommonsJobsubscibe(subscibeId);
		job = monitorMgr.getSched().getJobDetail(subId, "realtimeGroup");
		assertNull(job);
	}

}