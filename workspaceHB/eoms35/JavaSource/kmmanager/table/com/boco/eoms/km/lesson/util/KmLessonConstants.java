package com.boco.eoms.km.lesson.util;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Title:课程创建
 * </p>
 * <p>
 * Description:课程创建
 * </p>
 * <p>
 * Wed Jul 01 15:12:52 CST 2009
 * </p>
 * 
 * @author mosquito
 * @version 1.0
 * 
 */
public class KmLessonConstants {
	
	/**
	 * list key
	 */
	public final static String KMLESSON_LIST = "kmLessonList";

	/**
	 * 计算两个任意时间之间的间隔小时数
	 * 
	 * @param startday 开始时间
	 * @param endday 结束时间
	 * @return 间隔小时
	 */
	public static long getIntervalDays(Date startday, Date endday) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startday);
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endday);
		
		if (startday.after(endday)) {
			Calendar cal = startCal;
			startCal = endCal;
			endCal = cal;
		}

		long sl = startCal.getTimeInMillis();
		long el = endCal.getTimeInMillis();
		long ei = el - sl;
		
		long hours = (long) (ei / (1000 * 60 * 60));
		return hours;
	}
}