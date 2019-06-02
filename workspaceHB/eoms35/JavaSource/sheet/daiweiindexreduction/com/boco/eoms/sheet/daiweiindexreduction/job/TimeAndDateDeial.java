package com.boco.eoms.sheet.daiweiindexreduction.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.boco.eoms.commons.util.xml.XmlManage;


/**
 * 首次初审环节
 * 受理时间 建单+4个工作日
 * 处理时间 建单+6个工作日
 * 初审受理时间 建单+4个工作日
 * @author wmm
 *
 */
public class TimeAndDateDeial {

	private static String isWeekend = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekend");/* 周中是放假的时间 */
	private static String isWeekday = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekday");/*周末上上班时间的*/
	
	/**
	 * 获取前n个工作日日期
	 * @return
	 */
	public static Date addDateByHour(int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		boolean holidayFlag = false;
	    for (int i = 0; i < days; i++){  // ?????
	    	/*当前时间减一天*/
	    	cal.add(Calendar.DAY_OF_YEAR, 1); 
	        holidayFlag =checkHoliday(cal);
	        if(holidayFlag){/* 如果日期不合法*/
	        	i--;
            }
	    }
	    return cal.getTime();

	 }

	/**
	 * 校验是否符合
	 * @param src
	 * @return
	* @throws ParseException 
	 */
	public static boolean checkHoliday(Calendar cal) {
		 //.getTime();
		 String nowtime =(new SimpleDateFormat("yyyy-MM-dd")).format( cal.getTime());
		 boolean result = false;
		 int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
	     if(isWeekend.contains(nowtime)){ /* 周中是放假的时间 */
	    	 System.out.println("当前日期："+nowtime +",不上班!");
	    	 return true;
	     }
		 if((week_index==0||week_index==6)&& !isWeekday.contains(nowtime)){ /*是周六或周末 并不在周末上班日期中 不合法*/
	    	 result = true;
	     }
	     return result;
	
	}
}
