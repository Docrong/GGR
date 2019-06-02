package com.boco.eoms.commons.mms.base.util;

import java.util.Calendar;

public class MMSUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getDateString(Calendar calendar,String reportType)
	{
		String reportYear = String.valueOf(calendar.get(Calendar.YEAR));
//		String reportSeason = String.valueOf(CustomStatUtil.getSeason(calendar));
		String reportMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String reportWeek = String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
		String reportDate = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		
		String displayStr = "";
		if("monthReport".equalsIgnoreCase(reportType))
    	{
    		displayStr = " 月报表 " + " - " + reportYear + "年"  + reportMonth + "月" ;
    	}
    	else if("weekReport".equalsIgnoreCase(reportType))
    	{
    		displayStr = " 周报表 " + " - " + reportYear + "年"   + "第" + reportWeek + "周";
    	}
    	else if("dailyReport".equalsIgnoreCase(reportType))
    	{
    		displayStr = " 日报表 " + " - " + reportYear + "年"  + reportMonth + "月" + reportDate + "日";
    	}
		
		return displayStr;
	}

}
