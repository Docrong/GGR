package com.boco.eoms.commons.message.msg.bo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.quartz.JobExecutionContext;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.message.service.TawCommonMessageMonitorManager;

import com.boco.eoms.commons.message.util.ITawCommonMessageStaticBL;

/**
 * 消息轮循MONITOR表
 * 
 * @author panlong
 * @Apr 4, 2007 10:25:34 AM
 */
public class TawCommonMessageCycleMonitor {

	public void execute(JobExecutionContext context)
			throws org.quartz.JobExecutionException, ParseException {
		excuteSent();
	}

	public void excuteSent() throws ParseException {

		TawCommonMessageMonitorManager momanager = (TawCommonMessageMonitorManager) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageMonitorManager");
		TawCommonMessageMethod messagemethod = (TawCommonMessageMethod) ApplicationContextHolder
				.getInstance().getBean("tawCommonMessageMethod");
		List list = new ArrayList();

		TawCommonMessageMonitor monitormd = new TawCommonMessageMonitor();

		list = momanager.getTawCommonMessageMonitors(monitormd);

		if (list != null) {
			TawCommonMessageMonitor cyclemon = null;
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					cyclemon = (TawCommonMessageMonitor) list.get(i);
					String starttime = cyclemon.getStartTime();
					String endtime = cyclemon.getEndTime();
					int issendnight = cyclemon.getNightAllow().intValue();
					if (allowSend(starttime, endtime, issendnight)) {
						int cycle = cyclemon.getCycle().intValue();
						int count = cyclemon.getHieCount().intValue();
						messagemethod.sendCycleMessage(
								cyclemon.getDispatcher(), cyclemon.getHieId(),
								cyclemon.getHieContent(), cyclemon.getTaskId());

						if (count > 1) {

							cyclemon.setHieCount(new Integer(count - 1));
							cyclemon.setStartTime(getNextCycletime(starttime,
									cycle));
							momanager.saveTawCommonMessageMonitor(cyclemon);

						} else if (count == 1) {
							momanager.removeTawCommonMessageMonitor(cyclemon
									.getMonitorId());
						}
					}
				}
			}
		}

	}

	private boolean allowSend(String starttime, String endtime, int issendnight)
			throws ParseException {

		boolean flag = false;
		if (getNightAllow(issendnight) && isBetween(starttime, endtime)) {
			flag = true;
		}

		return flag;

	}

	private boolean getNightAllow(int nightAllow) throws ParseException {
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		boolean ret = true;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (nightAllow == ITawCommonMessageStaticBL.SEND_NNIGHT) {
			String daystr = String.valueOf(df.format(new Date()));

			java.util.Date start = dateformat.parse(daystr + " 08:00:00");
			java.util.Date end = dateformat.parse(daystr + " 20:00:00");
			java.util.Date now = new java.util.Date();
			if (end.before(now) && now.before(start))
				ret = false;
		}
		return ret;
	}

	private boolean isBetween(String startTime, String endTime) {
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		boolean flag = true;
		try {
			String strcurrenttime = dateformat.format(new Date());
			Date currenttime = dateformat.parse(strcurrenttime);
			Date starttimes = dateformat.parse(startTime);
			Date endtimes = dateformat.parse(endTime);

			if (starttimes.before(currenttime) && currenttime.before(endtimes)) {
				flag = true;
			}

		} catch (Exception ex) {
			BocoLog.error(this, "轮循时间出错");
		}
		return flag;

	}

	private String getNextCycletime(String oldstarttime, int aftermin) {

		Calendar currentCalendars = Calendar.getInstance();

		int second = currentCalendars.get(Calendar.SECOND);

		SimpleDateFormat currentdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String endtimes = "";
		if (oldstarttime != null) {
			try {

				String[] oldtime = oldstarttime.split(" ");
				String[] ymd = oldtime[0].split("-");
				String[] hms = oldtime[1].split(":");

				int oldyear = Integer.valueOf(ymd[0]).intValue();
				int oldmonth = Integer.valueOf(ymd[1]).intValue();
				int oldday = Integer.valueOf(ymd[2]).intValue();

				int oldh = Integer.valueOf(hms[0]).intValue();
				int oldm = Integer.valueOf(hms[1]).intValue();

				GregorianCalendar calendar = new GregorianCalendar(oldyear,
						oldmonth, oldday, oldh, oldm);
				calendar.add(GregorianCalendar.MINUTE, aftermin);
				Date d = calendar.getTime();
				DateFormat df = DateFormat.getDateInstance();
				endtimes = df.format(d);
				int endhour = aftermin / 60;
				int endmin = ((aftermin % 60));

				StringBuffer sbs = new StringBuffer();
				sbs.append(endtimes + " ");

				if (endhour + oldh >= 24) {
					sbs.append(String.valueOf(endhour + oldh - 24));
				} else {
					sbs.append(String.valueOf(endhour + oldh));
				}
				if (endmin + oldm >= 60) {
					sbs.append(":" + String.valueOf(endmin + oldm - 60));
				} else {
					sbs.append(":" + String.valueOf(endmin + oldm));
				}
				sbs.append(":" + second);
				endtimes = sbs.toString();
				Date dateend = currentdf.parse(endtimes);
				endtimes = currentdf.format(dateend);
			} catch (ParseException e) {
				BocoLog.error(this, e.getMessage());
			}
		}
		return endtimes;
	}
}
