package com.boco.eoms.km.ask.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.boco.eoms.base.util.StaticMethod;

/**
 * 时间转换
 * @author lvweihua
 *
 */
public class Common {
	private static final int MINUTE = 60 * 1000;

	private static final int HOUR = 60 * MINUTE;

	private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 计算过期时间
	 * @param disday 有效期 
	 * @paramp askTime 开始计算时间
	 * @return
	 * @throws ParseException 
	 */
	public static Date getTimeDate(int disday,Date askTime) throws ParseException {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		//设置计算开始时间
		c.setTime(askTime);
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		int _yy = c.get(Calendar.YEAR);
		int _mm = c.get(Calendar.MONTH) + 1;
		int _dd = c.get(Calendar.DATE);
		int _hh = c.get(Calendar.HOUR);
		int _mi = c.get(Calendar.MINUTE);
		int _se = c.get(Calendar.SECOND);
		ls_display = _yy + "-" + _mm + "-" + _dd + " "+_hh+":"+_mi+":"+_se;
		//将时间格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(ls_display);
		return date;
	}
	
	/**
	 * 倒计时时间
	 * @param pastDate
	 * @return
	 */
	public static String getPastTime(Date pastDate) {
		if (pastDate == null) {
			return null;
		}
		String time = "";
		try {
			Calendar currentCal = GregorianCalendar.getInstance();
			currentCal.setTime(FORMAT
					.parse(FORMAT.format(currentCal.getTime())));
			Calendar pastCal = GregorianCalendar.getInstance();
			pastCal.setTime(FORMAT.parse(FORMAT.format(pastDate)));
			pastCal.add(Calendar.DATE, 2);
			if (pastCal.before(currentCal)) {
				time = FORMAT.format(pastDate);
			} else {
				pastCal.add(Calendar.DATE, -1);
				if (pastCal.before(currentCal)) {
					time = "前天";
				} else {
					pastCal.add(Calendar.DATE, -1);
					if (pastCal.before(currentCal)) {
						time = "昨天";
					} else {
						currentCal = GregorianCalendar.getInstance();
						pastCal.setTime(pastDate);
						long timeLong = currentCal.getTime().getTime()
								- pastDate.getTime();
						pastCal.add(Calendar.MINUTE, 59);
						if (timeLong < 0) {
							time = "";
						} else if (pastCal.before(currentCal)) {
							time = String.valueOf(timeLong / HOUR) + "小时前";
						} else {
							pastCal.add(Calendar.MINUTE, -59);
							pastCal.add(Calendar.MINUTE, 4);
							pastCal.add(Calendar.SECOND, 59);
							if (pastCal.before(currentCal)) {
								time = String.valueOf(timeLong / MINUTE)
										+ "分钟前";
							} else {
								time = "刚刚";
							}
						}
					}
				}
			}
		} catch (ParseException e) {
			time = "";
		}
		return time;
	}
}
