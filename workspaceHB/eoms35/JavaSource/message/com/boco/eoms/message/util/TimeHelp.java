package com.boco.eoms.message.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.message.model.SmsApply;
/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-5 下午03:40:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class TimeHelp {
	/**
	 * 根据消息发送时间、消息发送次数和消息间隔计算出多个时间
	 * @param dispatchTime 消息发送时间
	 * @param count 消息发送次数
	 * @param interval 消息发送间隔
	 * @return 根据条件生成的count个时间值
	 * @throws ParseException 
	 */
	public static List caculateDate(SmsApply apply, Date dispatchTime) throws ParseException {
		SimpleDateFormat dateformat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int interval = new Integer(apply.getInterval()).intValue();
		int count = apply.getCount().intValue();
		List dateList = new ArrayList();
		Calendar c = Calendar.getInstance();
		c.setTime(dispatchTime);
		//目前考虑到性能只判断第一条短信的时间点是否在20点-8点之间
		if(!isAllowSend(dateformat.format(dispatchTime),StaticMethod.nullObject2String(apply.getStartTime()),StaticMethod.nullObject2String(apply.getEndTime()),apply.getIsSendNight())) {
			//如果时间点不允许发送，把时间点设成第二天的早上8点
			c.add(c.DAY_OF_YEAR, 1);
			dispatchTime = dateformat.parse(dateformat.format(c.getTime()).substring(0, 10)+" 08:00:00");
			//并把c的初始时间生成第二天的8点
			c.setTime(dispatchTime);
		}
		//将初始时间放在list里
		dateList.add(dispatchTime);
		//根据发送数量和时间间隔生成多条时间
		if(count > 0) {
			for(int i=0; i<count-1; i++) {
				c.add(Calendar.MINUTE, interval);
				dateList.add(c.getTime());
			}
		}
		return dateList;
	}
	public static boolean isBetweenNight() {
		boolean status = false;
		return status;
	}
	/**
	 * 根据消息催办天数、催办小时数、催办分钟树和发送时间标志计算最终发送时间
	 * @param sendStatus 消息发送的时间标志
	 * @param sendDay 催办天数
	 * @param sendHour 催办小时数
	 * @param sendMin 催办分钟数
	 * @return 最终发送时间
	 * @throws ParseException 
	 */
	public static Date getFinalDate(String isCycleSend, String cycleStatus, String cycleMonth, String cycleDay, String cycleHour, String sendStatus, String sendDay, String sendHour, String sendMin,String dispatchTime) throws ParseException {
		int day = new Integer(sendDay).intValue();
		int hour = new Integer(sendHour).intValue();
		int min = new Integer(sendMin).intValue();
		
		//判断是否循环发送，如循环发送则生成当日的循环时间；如：按月循环，每月的25号13点发送，当前时间为2008-06-20 11:36:00,则拼成2008-06-25 13:00:00
		if(isCycleSend.equals("true")) {
			dispatchTime = getCycleTime(cycleStatus, cycleMonth, cycleDay, cycleHour);
		}		
	
		Date date = DateUtil.convertStringToDate(dispatchTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);			
	
		//根据消息发送的时间标志控制时间开始
		if(sendStatus.equals(MsgConstants.STATUS_FORWARD)) {
			//计算出提前发送最终时间
			calendar.add(Calendar.DAY_OF_YEAR, -day);
			calendar.add(Calendar.HOUR_OF_DAY, -hour);
			calendar.add(Calendar.MINUTE, -min);
		} else if(sendStatus.equals(MsgConstants.STATUS_LAG)) {
			//计算出滞后发送最终时间
			calendar.add(Calendar.DAY_OF_YEAR, day);
			calendar.add(Calendar.HOUR_OF_DAY, hour);
			calendar.add(Calendar.MINUTE, min);
		}
		
		//根据消息发送的时间标志控制时间结束
		return calendar.getTime();
	}
	
	public static String getCycleTime(String cycleStatus, String cycleMonth, String cycleDay, String cycleHour) {
		String dispatchTime = "";
		String curTime = DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");			
		if(cycleStatus.equals("1")) {
			dispatchTime = curTime.substring(0, 5)+cycleMonth+"-"+cycleDay+" "+cycleHour+":00:00";
		} else if(cycleStatus.equals("2")) {
			dispatchTime = curTime.substring(0, 8)+cycleDay+" "+cycleHour+":00:00";
		} else if(cycleStatus.equals("3")) {
			dispatchTime = curTime.substring(0, 11)+cycleHour+":00:00";
		}				
		return dispatchTime;
	}
	/**
	 * 根据消息催办天数、催办小时数、催办分钟树和发送时间标志计算最终发送时间
	 * @param apply 服务订阅对象
	 * @return 最终发送时间
	 * @throws ParseException 
	 */
	public static Date getFinalDate(SmsApply apply,String dispatchTime) throws ParseException {
		return getFinalDate(apply.getIsCycleSend(), apply.getCycleStatus(),apply.getCycleMonth(),apply.getCycleDay(),apply.getCycleHour(),apply.getSendStatus(),apply.getSendDay(),apply.getSendHour(),apply.getSendMin(),dispatchTime);
	}
	/**
	 * 根据发送时间点、服务开始时间、服务结束时间和是否允许晚上发送判断是否允许发送：
	 * 第一步判断发送时间点是否在服务允许时间内，第二步根据发送时间点和是否允许晚上发送判断是否在时间点是否属于晚上（20时至8时）
	 * @param dispatchTime 发送时间点
	 * @param startTime 服务开始时间
	 * @param endTime 服务结束时间
	 * @param isSendNight 是否允许晚上发送 true：允许；false:不允许
	 * @return 是否允许发送  true：；false：
	 * @throws ParseException
	 */
	public static boolean isAllowSend(String dispatchTime, String startTime, String endTime, String isSendNight) throws ParseException {
		boolean ret = false;
		if (isBetween(dispatchTime, startTime, endTime) && getNightAllow(dispatchTime,isSendNight))
			ret = true;
	    return ret;
	}
	/**
	 * 根据发送时间点、服务开始时间、服务结束时间判断发送点是否在服务允许时间内
	 * @param dispatchTime 时间发送点
	 * @param startTime 服务开始时间
	 * @param endTime 服务结束时间
	 * @return true:时间点在服务允许时间内
	 */
	private static boolean isBetween(String dispatchTime, String startTime, String endTime) {
	    SimpleDateFormat dateformat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    boolean ret = false;
	    java.util.Date start=null;
	    java.util.Date end=null;
	    java.util.Date pointTime=null;
	    try{
	      if (!startTime.equals(""))
	        start = dateformat.parse(startTime);
	      if (!endTime.equals(""))
	        end = dateformat.parse(endTime);
	      if (!dispatchTime.equals(""))
	    	  pointTime = dateformat.parse(dispatchTime);
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    if (startTime.equals("") && endTime.equals("")) {
	        ret = true;
	    }
	    else if (startTime.equals("") && !endTime.equals("")) {
	      if (pointTime.before(end))
	        ret = true;
	    }
	    else if (!startTime.equals("") && endTime.equals("")) {
	      if (start.before(pointTime))
	        ret = true;
	    }
	    else if (!startTime.equals("") && !endTime.equals("")) {
	      if (start.before(pointTime) && pointTime.before(end))
	        ret = true;
	    }
	    return ret;
	  }
	/**
	 * 判断当前时间是否是晚上  
	 * @param nightAllow
	 * @return false：时间点属于晚上20时至8时之间
	 * @throws ParseException
	 */
	private static boolean getNightAllow(String dispatchTime, String nightAllow) throws ParseException {
	    SimpleDateFormat dateformat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    boolean ret = true;
	    if (nightAllow.equals("false")) {
		  java.util.Date pTime = dateformat.parse(dispatchTime);
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(pTime);
		  calendar.add(calendar.DAY_OF_YEAR, 1);//不知道是用DAY_OF_YEAR还是DAY_OF_MONTH
	      String daystr = dispatchTime.substring(0, 10);
	      String days = dateformat.format(calendar.getTime()).substring(0,10);
	      java.util.Date start =dateformat.parse(days + " 08:00:00");
	      java.util.Date end = dateformat.parse(daystr + " 20:00:00");
	      if (end.before(pTime) && pTime.before(start))
	        ret = false;
	    }
	    return ret;
	  }
}
