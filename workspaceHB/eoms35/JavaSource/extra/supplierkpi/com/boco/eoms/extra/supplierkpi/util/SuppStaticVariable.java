package com.boco.eoms.extra.supplierkpi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:供应商实例生成组件常量类

 * </p>
 * <p>Apr 10, 2007 10:55:44 AM</p> 
 *
 * @author
 * @version 1.0
 *
 */
public class SuppStaticVariable {
	
	/**
	 * 月份为01月时分别生成月度、季度、年度实例考核表
	 */
	public static final String INSTANCE_JAN = "01";
	
	/**
	 * 月份为04月时分别生成月度、季度实例考核表
	 */	
	public static final String INSTANCE_APR = "04";
	
	/**
	 * 月份为07月时分别生成月度、季度实例考核表
	 */		
	public static final String INSTANCE_JUL = "07";
	
	/**
	 * 月份为10月时分别生成月度、季度实例考核表
	 */		
	public static final String INSTANCE_OCT = "10";
	
	/**
	 * 统计周期_生成月度实例
	 */
	public static final String STATICTS_CYCLE_MONTH = "1";
	
	/**
	 * 统计周期_生成季度实例
	 */
	public static final String STATICTS_CYCLE_QUARTER = "2";
	
	/**
	 * 统计周期_生成年度实例
	 */
	public static final String STATICTS_CYCLE_YEAR = "3";
	
	/**
	 * 时间转换方法
	 * 
	 * @param disday
	 * @param hour
	 * @return
	 */
	public static String getLocalString(int disday, int hour) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		if(disday>10){
			int temp = disday - 10;
			realtime += 86400000 * (-temp);
			realtime += 86400000 * (-10);
		}else{
			realtime += 86400000 * (-disday);
		}		
		c.setTimeInMillis(realtime);
		String _mmstr = "", _ddstr = "", _hourstr = "";
		int _yy = c.get(Calendar.YEAR);
		int _mm = c.get(Calendar.MONTH) + 1;
		_mmstr = _mm + "";
		if (_mm < 10) {
			_mmstr = "0" + _mm;
		}
		int _dd = c.get(Calendar.DATE);
		_ddstr = _dd + "";
		if (_dd < 10) {
			_ddstr = "0" + _dd;
		}
		if (hour < 10) {
			_hourstr = "0" + hour;
		} else {
			_hourstr = "" + hour;

		}
		ls_display = _yy + "-" + _mmstr + "-" + _ddstr + " " + _hourstr
				+ ":00:00";
		return ls_display;
	}
	
	/**
	 * 取得当前月份的最后一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthEnd() {
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -getDay(date));
		return formatDateByFormat(calendar.getTime());
	}

	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDateByFormat(java.util.Date date) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}	
	
	/**
	 * 
	 * 时间转换方法：根据输入的格式("yyyy-MM-dd HH:mm:ss")得到当前时间格式上月最后一天 Add By WangSixuan
	 * 
	 * @param null
	 * @return String
	 */
	public static String getLastMouthLastDay() {
		String currentdatetime = "";
		try {
			Date date = new Date(System.currentTimeMillis());
			Calendar calendar=Calendar.getInstance();   
			calendar.setTime(date);     
			calendar.set(Calendar.DAY_OF_MONTH,1); 
			calendar.add(Calendar.DAY_OF_MONTH,-1);
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			currentdatetime = dtFormat.format(calendar.getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return currentdatetime;
	}
	/**
	 * 
	 * 时间转换方法：根据输入的格式("yyyy-MM-dd HH:mm:ss")得到当前时间格式上月第一天 Add By WangSixuan
	 * 
	 * @param null
	 * @return String
	 */
	public static String getLastMouthFirstDay() {
		String currentdatetime = "";
		try {
			Date date = new Date(System.currentTimeMillis());
			Calendar calendar=Calendar.getInstance();   
			calendar.setTime(date);   
			int month=calendar.get(Calendar.MONTH);   
			calendar.set(Calendar.MONTH,month-1); 
			calendar.set(Calendar.DAY_OF_MONTH,1);
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			currentdatetime = dtFormat.format(calendar.getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return currentdatetime;
	}

}
