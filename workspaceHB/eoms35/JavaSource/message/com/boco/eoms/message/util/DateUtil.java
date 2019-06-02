package com.boco.eoms.message.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 鏃ユ湡杞崲绫? 杞崲涓?釜 java.util.Date 瀵硅薄鍒颁竴涓瓧绗︿覆浠ュ強 涓?釜瀛楃涓插埌涓?釜 java.util.Date
 * 瀵硅薄.
 */
public class DateUtil {

	private static Log log = LogFactory.getLog(DateUtil.class);

	/**
	 * yyyy/MM/dd
	 */
	public final static String DATE_PATTERN = "yyyy/MM/dd";

	/**
	 * yyyy-MM-dd
	 */
	public final static String DATE_PATTERN1 = "yyyy-MM-dd HH:mm:ss.SSSSS";

	/**
	 * HH:mm
	 */
	public final static String TIME_PATTERN = "HH:mm";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public final static String DATE_PATTERN2 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Return 缂虹渷鐨勬棩鏈熸牸寮?(yyyy/MM/dd)
	 * 
	 * @return 鍦ㄩ〉闈腑鏄剧ず鐨勬棩鏈熸牸寮?
	 */
	public static String getDatePattern() {
		return DATE_PATTERN;
	}

	/**
	 * 鏍规嵁鏃ユ湡鏍煎紡锛岃繑鍥炴棩鏈熸寜datePattern鏍煎紡杞崲鍚庣殑瀛楃涓?
	 * 
	 * @param aDate
	 *            鏃ユ湡瀵硅薄
	 * @return 鏍煎紡鍖栧悗鐨勬棩鏈熺殑椤甸潰鏄剧ず瀛楃涓?
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(DATE_PATTERN);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final Date convertStringToDateReturnNull(String aMask,
			String strDate) throws ParseException {
		if (null == strDate || "".equals(strDate))
			return null;
		return convertStringToDate(aMask, strDate);
	}

	/**
	 * 鎸夌収鏃ユ湡鏍煎紡锛屽皢瀛楃涓茶В鏋愪负鏃ユ湡瀵硅薄
	 * 
	 * @param aMask
	 *            杈撳叆瀛楃涓茬殑鏍煎紡
	 * @param strDate
	 *            涓?釜鎸塧Mask鏍煎紡鎺掑垪鐨勬棩鏈熺殑瀛楃涓叉弿杩?
	 * @return Date 瀵硅薄
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {

		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: yyyy/MM/dd HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * This method returns the current date in the format: yyyy/MM/dd
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 鏍规嵁鏃ユ湡鏍煎紡锛岃繑鍥炴棩鏈熸寜datePattern鏍煎紡杞崲鍚庣殑瀛楃涓?
	 * 
	 * @param aDate
	 * @return
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(DATE_PATTERN1, aDate);
	}

	/**
	 * 
	 * @param amsk
	 * @param aDate
	 * @return
	 */
	public static final String convertDateToString(String amsk, Date aDate) {
		return getDateTime(amsk, aDate);
	}

	/**
	 * 鎸夌収鏃ユ湡鏍煎紡锛屽皢瀛楃涓茶В鏋愪负鏃ユ湡瀵硅薄
	 * 
	 * @param strDate
	 *            (鏍煎紡 yyyy/MM/dd)
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + DATE_PATTERN2);
			}

			aDate = convertStringToDate(DATE_PATTERN2, strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * 取年月日相同并秒相同的calendar
	 * 
	 * @param date
	 * @return calendar
	 */
	public static Calendar getYMDCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, 2006);
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DATE, 4);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	/**
	 * 添加num个天
	 * 
	 * @param date
	 *            要添加的日期
	 * @param num
	 *            添num天
	 * @return
	 */
	public static Date addDay(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.DATE, num);
		return cal.getTime();
	}

	public static Date convertStrToDate(String strDate, String pattern)
			throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + DATE_PATTERN);
			}

			aDate = convertStringToDate(pattern, strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	public static String getHour(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			return "0" + hour;
		} else {
			return "" + hour;
		}
	}

	public static String getMinute(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int minute = cal.get(Calendar.MINUTE);
		if (minute < 10) {
			return "0" + minute;
		} else {
			return "" + minute;
		}
	}

	/**
	 * date+day天数 +time
	 * 
	 * @param date
	 *            日期
	 * @param time
	 *            时间
	 * @param day
	 *            加几天
	 * @return 返回完整日期
	 */
	public static Date getFullDate(Date date, Date time, int day) {
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);
		calDate.add(Calendar.DATE, day);
		Calendar calTime = Calendar.getInstance();
		calTime.setTime(time);
		calDate.set(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
		calDate.set(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
		calDate.set(Calendar.SECOND, calTime.get(Calendar.SECOND));
		calDate.set(Calendar.MILLISECOND, 0);
		return calDate.getTime();
	}

	/**
	 * 判断afterDate时间是否在beforDate时间之前
	 * 
	 * @param beforeDate
	 *            之前时间
	 * @param afterDate
	 *            之后时间
	 * @return 若afterDate时间是在beforDate时间之前,则返回true
	 */
	public static boolean isBeforeDate(Date beforeDate, Date afterDate) {
		Calendar beforeCal = Calendar.getInstance();
		Calendar afterCal = Calendar.getInstance();
		return (beforeCal.before(afterCal));
	}
	
	/*
	   根据输入的格式(String _dtFormat)得到当前时间格式
	   */
	  public static String getCurrentDateTime(String _dtFormat) {
	    String currentdatetime = "";
	    try {
	      Date date = new Date(System.currentTimeMillis());
	      SimpleDateFormat dtFormat = new SimpleDateFormat(_dtFormat);
	      currentdatetime = dtFormat.format(date);
	    }
	    catch (Exception e) {
	      System.out.println("时间格式不正确");
	      e.printStackTrace();
	    }
	    return currentdatetime;
	  }
	  /**
	   * 获取当前时间
	   * @return
	   */
	  public static Date getCurrentDate() {
		  Calendar calendar = Calendar.getInstance();
		  return calendar.getTime();
	  }
	 /**
	  * 判断当前时间和传入的时间的判断月份、日和小时是否相同
	  * @param date 时间
	  * @return true代表相同
	  */
	  public static boolean getMonthDayHourRet(Date date) {
		  boolean ret = false;
		  Calendar calendar = Calendar.getInstance();
		  int curMonth = calendar.get(Calendar.MONTH)+1;
		  int curDay = calendar.get(Calendar.DAY_OF_MONTH);
		  int curHour = calendar.get(Calendar.HOUR_OF_DAY);
		  calendar.setTime(date);
		  int month = calendar.get(Calendar.MONTH)+1;
		  int day = calendar.get(Calendar.DAY_OF_MONTH);
		  int hour = calendar.get(Calendar.HOUR_OF_DAY);
		  if(curMonth == month && curDay == day && curHour == hour) {
			  ret = true;
		  }
		  return ret;
	  }
	  /**
	   * 判断当前时间和传入的时间的判断日和小时是否相同
	   * @param date
	   * @return
	   */
	  public static boolean getDayHourRet(Date date) {
		  boolean ret = false;
		  Calendar calendar = Calendar.getInstance();
		  int curDay = calendar.get(Calendar.DAY_OF_MONTH);
		  int curHour = calendar.get(Calendar.HOUR_OF_DAY);
		  calendar.setTime(date);
		  int day = calendar.get(Calendar.DAY_OF_MONTH);
		  int hour = calendar.get(Calendar.HOUR_OF_DAY);
		  if(curDay == day && curHour == hour) {
			  ret = true;
		  }
		  return ret;		  
	  }
	  
	  /**
	   * 判断当前时间和传入的时间的判断日和小时是否相同
	   * @param date
	   * @return
	   */
	  public static boolean getHourRet(Date date) {
		  boolean ret = false;
		  Calendar calendar = Calendar.getInstance();
		  int curHour = calendar.get(Calendar.HOUR_OF_DAY);
		  calendar.setTime(date);
		  int hour = calendar.get(Calendar.HOUR_OF_DAY);
		  if(curHour == hour) {
			  ret = true;
		  }
		  return ret;		  
	  }

}
