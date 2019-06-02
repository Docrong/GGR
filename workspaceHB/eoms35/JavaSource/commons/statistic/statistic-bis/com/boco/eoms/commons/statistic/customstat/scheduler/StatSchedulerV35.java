package com.boco.eoms.commons.statistic.customstat.scheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.customstat.mgr.IStSubscriptionManager;
import com.boco.eoms.commons.statistic.customstat.model.StSubscription;
import com.boco.eoms.commons.statistic.customstat.util.CustomStatUtil;
import com.boco.eoms.commons.statistic.customstat.webapp.action.CustomStatMethodImpl;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class StatSchedulerV35 implements Job {

	public Logger logger = Logger.getLogger(this.getClass());

	public StatSchedulerV35() {
	}

	private String getId(final String subId) {
		String id = "";

		DataSource ds = null;
		Connection connection = null;
		PreparedStatement pmts = null;
		ResultSet resultSet = null;
		try {
			final String selectSubId = "select id from taw_commons_jobsubscibe  where subid=?";
			ds = (DataSource) ApplicationContextHolder.getInstance().getBean(
					"dataSource");
			connection = ds.getConnection();
			pmts = connection.prepareStatement(selectSubId);
			pmts.setString(1, subId);
			resultSet = pmts.executeQuery();
			if (resultSet.next())
				id = resultSet.getString("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resultSet = null;

			try {
				if (pmts != null)
					pmts.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pmts = null;

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}

		return id;
	}

	public void execute(JobExecutionContext context)
			throws org.quartz.JobExecutionException {

		try {
			//如果没有系统没有com.boco.eoms.base.util.ApplicationContextHolder的情况需要把InitStaticBaseApplicationContextServlet配置到web.xml中
			if (ApplicationContextHolder.getInstance().getCtx() == null) 
			{
				ApplicationContextHolder.getInstance().setCtx(com.boco.eoms.base.util.ApplicationContextHolder.getInstance().getCtx());
			}

			String JobName = "";
			if (context != null) {
				JobName = context.getJobDetail().getName();
			}
			
			logger.info("\n执行定制统计的JobName是 ：" + JobName);

//			ITawCommonsJobsortManager jobSort = (ITawCommonsJobsortManager) ApplicationContextHolder
//					.getInstance().getBean("ItawCommonsJobsortManager");
//			ITawCommonsJobsubscibeManager jobsubscibe = (ITawCommonsJobsubscibeManager) ApplicationContextHolder
//					.getInstance().getBean("ItawCommonsJobsubscibeManager");
//
////			String id = this.getId(JobName);
//			
//			TawCommonsJobsubscibe tawCommonsJobsubscibe = jobsubscibe
//					.getTawCommonsJobsubscibeForSubID(JobName);
//
//			TawCommonsJobsort tawCommonsJobsort = jobSort
//					.getTawCommonsJobsort(String.valueOf(tawCommonsJobsubscibe
//							.getJobSortId()));

//			String remark = tawCommonsJobsort.getRemark();
			
			
			IStSubscriptionManager IstSubscriptionManager = (IStSubscriptionManager)ApplicationContextHolder.getInstance().getBean("IstSubscriptionManager");
			StSubscription stSubscription = IstSubscriptionManager.getStSubscriptionForSubId(JobName);
			
			String remark = stSubscription.getRemark();
			if (remark == null) {
				new Exception("\n轮训表中 remark 为空");
			}

			Map map = StatUtil.StringToMap(remark);

			logger.info("\n定制报表条件信息Map：" + map);
			String reportType = String.valueOf(map.get("reportType"));

			//自定义时间段统计
			Calendar currtenCanlendar = Calendar.getInstance();// 开始执行统计时间
			if(!"customReport".equalsIgnoreCase(reportType))
			{
				Calendar beginCalendar = CustomStatUtil.getCalendar(reportType,
						(Calendar)StatUtil.CloneObject(currtenCanlendar));
				
				map.put("beginTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(beginCalendar.getTime()));// 统计开始时间
				map.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(currtenCanlendar.getTime()));// 统计结束时间
			}
			else if(!map.containsKey("beginTime") || !map.containsKey("endTime") )
			{
				throw new Exception("请查看定制报表条件信息表中是否有 beginTime和endTime条件");
			}
			
			//报表信息（年.季.月.周.日）
			String reportYear = String.valueOf(currtenCanlendar.get(Calendar.YEAR));
			String reportSeason = String.valueOf(CustomStatUtil.getSeason(currtenCanlendar));
			String reportMonth = String.valueOf(currtenCanlendar.get(Calendar.MONTH) + 1);
			String reportWeek = String.valueOf(currtenCanlendar.get(Calendar.WEEK_OF_YEAR));
			String reportDate = String.valueOf(currtenCanlendar.get(Calendar.DAY_OF_MONTH));
			map.put("reportYear", reportYear);//报表年信息
			map.put("reportSeason", reportSeason);//报表季度信息
			map.put("reportMonth", reportMonth);//报表月信息
			map.put("reportWeek", reportWeek);//报表周信息
			map.put("reportDate", reportDate);//报表日信息
			
			map.put("subscriberId", stSubscription.getSubscriber());//订阅人id
			map.put("subscriberDeptId", String.valueOf(stSubscription.getSubscribeDept()));//订阅部门id
			map.put("subId", stSubscription.getSubId());
			
//			map.put("subscriberId", tawCommonsJobsubscibe.getSubscriberId());//订阅人id
//			map.put("subscriberDeptId", tawCommonsJobsubscibe.getSubscriberDeptId());//订阅部门id
//			map.put("subId", tawCommonsJobsubscibe.getSubId());

			// map.put("endTime", "2008-12-03 15:16:16");
			// map.put("beginTime", "2006-12-03 15:16:16");

			// map.put("mainNetSortOne", "101010408");
			// map.put("reportIndex", "0");
			// map.put("RequestURI", "/zy/statistic/customstat/stat.do");
			// map.put("excelConfigURL", "commonfault_T_resolve_KPI4_oracle");
			// map.put("findListForward", "T_resolve_statisticsheetlist");
			// map.put("reportFromType", "StatFrom");
			// map.put("reportType", "yearReport");

			CustomStatMethodImpl csmi = (CustomStatMethodImpl) ApplicationContextHolder
					.getInstance().getBean("customStatMethod");
			csmi.runCustomStatistic(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
