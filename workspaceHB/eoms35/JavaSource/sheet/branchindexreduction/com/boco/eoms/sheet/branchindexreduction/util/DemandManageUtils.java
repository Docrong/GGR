package com.boco.eoms.sheet.branchindexreduction.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.exception.SheetException;

public class DemandManageUtils {
	private static String isWeekend = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekend");
	private static String isWeekday = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekday");
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static Date findTheEndtime(Date linkPlanStartTimeDate, int linkPlanProgramLen) throws Exception {
		Date date = linkPlanStartTimeDate;
		for (int i = 1; i < linkPlanProgramLen; i++) {
			date = getNextDate(date, 1); // 获取下一天的日期
		}
		return date;
	}

	/**
	 * 判断当前是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int dayOfWeek(Date date) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		return aCalendar.get(Calendar.DAY_OF_WEEK);
	}
	


	public static Date getNextDate(Date date, int index) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);// 获得当前时间
		cal.add(Calendar.DAY_OF_YEAR, index);

		Date now = cal.getTime();
		int weekDay = dayOfWeek(now);
		if (weekDay == 1 || weekDay == 7) { // 判断周末是不是工作日
			boolean flag = isWeekday.contains(df.format(now));
			if (!flag) {
				return getNextDate(now, 1);
			} else {
				return now;
			}
		} else {// 判断周中是不是休假日
			boolean flag = isWeekend.contains(df.format(now));
			if (flag) {
				return getNextDate(now, 1);
			} else {
				return now;
			}
		}
	}

	public static Date stringToDate(String s) throws SheetException {
		Date date = new Date();
		try {
			if (!s.equals("")) {
				date = df.parse((String) s);
			} else {
				date = null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String date2String(Date date) {
		String ret = "";
		try {
			ret = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static int convert2Int(String[] taskHours) {
		int m = 0;
		for (int i = 0; i < taskHours.length; i++) {
			int x = StaticMethod.nullObject2int(taskHours[i]);
			m += x;
		}
		return m;
	}

	public static Date getCurrentTime() {
		Date date = new Date();
		try {
			String dateString = date2String(date);
			date = df.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算两个时间段之间的工作日
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public static String getHoursBetweenHours(Date startTime, Date endTime) {
		Date sdate = DemandManageUtils.getSpecailDate(startTime);
		Date edate = DemandManageUtils.getSpecailDate(endTime);
		int i = 0;
		// 1、先计算直接隔了多少工作日
		i = getDayLens(sdate, edate, i);
		// 2、再计算小时差，大于5算一个工作日，小于5不计算工作日
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);

		int start = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.clear();
		calendar.setTime(endTime);
		int end = calendar.get(Calendar.HOUR_OF_DAY);
		if (Math.abs(end - start) > 5) {
			i++;
		}
		return StaticMethod.nullObject2String(i+"");
	}

	private static int getDayLens(Date sdate, Date edate, int i) {
		i++;
		sdate = getNextDate(sdate, 1); // 获取下一天的日期
		if (sdate.equals(edate) || sdate.after(edate)) {
			return i;
		} else {
			return getDayLens(sdate, edate, i);
		}
	}

	/**
	 * 获取格式为yyyy-MM-dd格式Date格式时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSpecailDate(Date date) {
		try {
			String dateString = date2String(date);
			date = df.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String map2String(Map taskMap) {
		StringBuffer sb = new StringBuffer();
		for(Iterator it = taskMap.keySet().iterator() ; it.hasNext();){
            String key = it.next().toString();
            Object value = taskMap.get(key);
            sb.append(value.toString());
            sb.append("@");
           // finalMap.put(key, map.get(key));
        }
		
		return sb.toString();
	}

	public static Map string2Map(String mapText) {
		if (mapText == null || mapText.equals("")) {
			return null;
		}

		Map map = new HashMap();
		String[] text = mapText.split("@"); // 转换为数组
		for (int i=0;i<text.length;i++) {
			String str = text[i];
			String[] keyText = str.split(","); // 转换key与value的数组
			if (keyText.length < 1) {
				continue;
			}
			
			String key = keyText[0]; // key
			String va = StaticMethod.nullObject2String(map.get(key));
			String value = keyText[1] + "," + keyText[2] + "," + keyText[3]; // value
			if("".equals(va)){
				va = value;
			}else{
				va+="|"+value;
			}
			map.put(key, va);

		}
		return map;
	}

	public static Date stringToDateFromChinese(String s) {
		DateFormat dff = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = new Date();
		try {
			if (!s.equals("")) {
				date = dff.parse((String) s);
			} else {
				date = null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
