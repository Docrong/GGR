package com.boco.eoms.sheet.daiweiindexreduction.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.boco.eoms.commons.util.xml.XmlManage;
/**
 * 自25日起的2个工作日  包含上个月的25号起的2个工作日
 * 
 * @author wmm
 *
 */
public class DateIfEqualShow {

	private static String isWeekend = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekend");/* 周中是放假的时间 */
	private static String isWeekday = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("isWeekday");/*周末上上班时间的*/
	
	/**
	 * 获取当月25号日期工作日日期
	 * @return
	 */
	public static long addDate01ByHours(Date date ,int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01 00:00:00");  // 25日日期格式
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 正常的日期格式
		String time = sdf.format(date);  // 获取格式化后的日期
		Date date01 = null;;
		try {
			date01 = sdf1.parse(time); // 这里一定要异常处理才能运行
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date01);
		boolean holidayFlag = false;
	    for (int i = 0; i < days; i++){  
	    	/*当前时间加一天*/
	    	cal.add(Calendar.DAY_OF_YEAR, 1); 
	        holidayFlag = checkHoliday(cal);
	        if(holidayFlag){/* 如果日期不合法*/
	        	i--;
            }
	    }
	    return cal.getTimeInMillis(); // 返回 毫秒
	 }
	
	/**
	 * 校验日期是否符合
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
