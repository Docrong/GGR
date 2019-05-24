package com.boco.eoms.commons.statistic.base.anychart.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class TimeTool {
    
	static SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
	
	private int year,month,day,hour,minute,second;
    
    public TimeTool(){
        this.getDatetime();
    }
    
    /**
     * 检验输入是否为正确的日期格式,严格要求日期正确性,
     * @param sourceDate  指定日期
     * @param format指定格式 格式:yyyy/MM/dd HH:mm:ss
     * @return
     */
    public static boolean checkDate(String sourceDate,String format){
    	if(format==null || format=="") format ="yyyy/MM/dd";
        if(sourceDate==null){
            return false;
        }
        try {
           SimpleDateFormat dateFormat = new SimpleDateFormat();
           dateFormat.setLenient(false);
           dateFormat.parse(sourceDate);
           return true;
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }         
    }
    
    /**
     * init
     */
    public void getDatetime(){
       Calendar   c   =   Calendar.getInstance();
       c.setTime(new  java.util.Date());
       year   =   c.get(Calendar.YEAR);
       month  =   c.get(Calendar.MONTH)+1;
       day    =   c.get(Calendar.DAY_OF_MONTH);
       hour   =   c.get(Calendar.HOUR_OF_DAY);
       minute =   c.get(Calendar.MINUTE);
       second =   c.get(Calendar.SECOND);       
    }
    
    /**
     * get day of currentdatetime
     * @return
     */
    public int getDay(){
		return day;
	}
    
    /**
     * get nowdate format with flag
     * @return String
     */
    public String getDateFormate(String flag){
    	String yyyy=String.valueOf(year)+flag;
        String mm=String.valueOf(month)+flag;
        String dd=String.valueOf(day);
        String date=yyyy+mm+dd;
        return date;
    }

    /**
     * chinese's dateformat
     * @return String
     */
    public String getCDate(){
        String yyyy=String.valueOf(year)+"年";
        String mm=String.valueOf(month)+"月";
        String dd=String.valueOf(day)+"日";
        String date=yyyy+mm+dd;
        return date;
    }

    /**
     * chinese's timeformat?
     * @return String
     */
    public String getCTime(){
        String hh=String.valueOf(hour)+"時";
        String mm=String.valueOf(minute)+"分";
        String dd=String.valueOf(second)+"秒";
        String time=hh+mm+dd;;
        return time;
    }

    
    /**
     * formatdate with flag
     * @parm flag String ?
     * @return String
     */
    public String getC_date(String flag){
       String s;
       String yy = String.valueOf(year)+flag;
       String mm = String.valueOf(month)+flag;
       String dd = String.valueOf(day);
       s = yy + mm + dd;
       return s;
   }

   /**
    * format time with ":"
    * @return String
    */
   public String getC_time() {
       String s;
       String h = String.valueOf(hour) + ":";
       String m = String.valueOf(minute) + ":";
       String d = String.valueOf(second);
       s = h + m + d;
       return s;
   }
   /**
    * 將按特定標記分割日期
    * @param date String
    * @param flag String 
    * @return String
    */
   public String getDate(String date,String flag){
       String[] s=date.split(flag);
       String yy=s[0]+"年";
       String mm=s[1]+"月";
       String dd=s[2]+"日";
       return yy+mm+dd;
   }
   
   /**
    * 比較HTC的時間的分鐘間隔數
    * @param date1  String HTC
    * @param date2	String HTC
    * @return int
    */
   public static int betweenMinutes(String date1,String date2){
	   Date ymd1 = null;
	   Date ymd2 = null;
	   date1=date1.replaceAll("-","/");
	   date2=date2.replaceAll("-","/");
	   SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd/HH:mm");
	   try {
		   ymd1=sdf.parse(date1);
		   ymd2=sdf.parse(date2);
	   } catch (ParseException e) {
		   e.printStackTrace();
	   }
	   long beginTime=ymd1.getTime();
	   long endTime=ymd2.getTime();
	   long betweenMinutes = (long)((endTime - beginTime) / (1000 * 60));
	   return (int) betweenMinutes;
   }
   
   /**
    * 通過相隔分鐘,計算相隔天數
    * @param day
    * @param betweenminute
    * @return
    */
   public static int CountDaysWithMinutes(String day,int betweenminute){	   
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd/HH:mm");
	   GregorianCalendar cal = new GregorianCalendar();
	   day=day.replaceAll("-","/");
	   String tempday=day.substring(0,10);
	   int count=0;
	   try {
		   Date date1=sdf.parse(day);
		   cal.setTime(date1);	  
		   for(int i=1;i<=betweenminute;i++){
			   date1=sdf.parse(day);
			   cal.setTime(date1);
			   cal.add(12,1);
			   day=sdf.format(cal.getTime());
			   if(!tempday.equals(day.substring(0,10))){
				   tempday=day.substring(0,10);
				   count++;
				   //System.out.println(day);
			   }			   
		   }
		   return count=count-1;
	   } catch (ParseException e) {
		   e.printStackTrace();
		   return -1;
	   }
   }
   
	/**
	 * HH:MM getMinutes
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMinutes(String date1,String date2){
		   Date ymd1 = null;
		   Date ymd2 = null;
		   date1="1970/1/1/"+date1;
		   date2="1970/1/1/"+date2;
		   SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd/HH:mm");
		   try {
			   ymd1=sdf.parse(date1);
			   ymd2=sdf.parse(date2);
		   } catch (ParseException e) {
			   e.printStackTrace();
		   }
		   long beginTime=ymd1.getTime();
		   long endTime=ymd2.getTime();
		   long betweenMinutes = (long)((endTime - beginTime) / (1000 * 60));
		   return (int) betweenMinutes;
	 }
	
	/**
	 * 上個月日期
	 * @param date
	 * @return
	 */
	public static String getPreviousMonth(String date){
		date=date.replaceAll("-","/");
		//SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
		GregorianCalendar cal = new GregorianCalendar();
		try {
			Date dd=sdf.parse(date);
			cal.setTime(dd);
			cal.add(2,-1);
			date=sdf.format(cal.getTime());
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 下個月日期
	 * @param date
	 * @return
	 */
	public static String getNextMonth(String date){
		date=date.replaceAll("-","/");
		//SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
		GregorianCalendar cal = new GregorianCalendar();
		try {
			Date dd=sdf.parse(date);
			cal.setTime(dd);
			cal.add(2,1);
			date=sdf.format(cal.getTime());
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
   
	/**
	 * is include the date
	 * @param begindate
	 * @param enddate
	 * @param date
	 * @return
	 */
   public boolean innerDate(String begindate,String enddate,String date){
	   boolean isinner=false;
	   //轉變格式
	   begindate=begindate.replaceAll("-","/");
	   enddate=enddate.replaceAll("-","/");
	   date=date.replaceAll("-","/");
	   int sbetween= betweenMinutes(begindate,date);
	   int ebetween= betweenMinutes(date,enddate);
	   if(sbetween>=0 && ebetween>=0){
		   isinner=true;
	   }	   
	   return isinner;
   }
   
   /**
    * 计算下一周
    * @param date
    * @return
    */
   public static String getNextWeek(String date){
	   date=date.replaceAll("-","/");
		//SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
		GregorianCalendar cal = new GregorianCalendar();
		try {
			Date dd=sdf.parse(date);
			cal.setTime(dd);
			cal.add(4,1);
			date=sdf.format(cal.getTime());
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
   }
   
   /**
    * 计算下一天
    * @param date
    * @return
    */
   public static String getNextDay(String date){
		date=date.replaceAll("-","/");
		//SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
		GregorianCalendar cal = new GregorianCalendar();
		try {
			Date dd=sdf.parse(date);
			cal.setTime(dd);
			cal.add(6,1);
			date=sdf.format(cal.getTime());
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
   }
   
   /**
    * 计算相隔周数[不到一周按一周计算]
    * @param start
    * @param end
    * @return
    */
   public static int getWeeks(String start,String end){
	   start = start + "/00:00";
	   end = end + "/00:00";
	   int minutes = betweenMinutes(start,end);
	   int weeks = minutes/60/24/7;
	   if(minutes%(60*7*24)>0){
		   weeks++;
	   }
	   return weeks;
   }
   
   /**
    * 计算相隔月数
    * @param startdate
    * @param enddate
    * @return
    */
   public static int getMonths(String startdate, String enddate) {
	  
	   startdate = startdate.replaceAll("-", "/");	  
	   enddate = enddate.replaceAll("-", "/");
	  
	   //SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
	  
	   GregorianCalendar g1 = new GregorianCalendar();
	   GregorianCalendar g2 = new GregorianCalendar(); 
	  
	   Date start,end;
	  
	   try {
		  start = sdf.parse(startdate);
		  end = sdf.parse(enddate);
		  g1.setTime(start);
		  g2.setTime(end);
		   
		  int elapsed = 0;
	      GregorianCalendar gc1, gc2;

	      if (g2.after(g1)) {
	         gc2 = (GregorianCalendar) g2.clone();
	         gc1 = (GregorianCalendar) g1.clone();
	      }
	      else   {
	         gc2 = (GregorianCalendar) g1.clone();
	         gc1 = (GregorianCalendar) g2.clone();
	      }

	      gc1.clear(Calendar.MILLISECOND);
	      gc1.clear(Calendar.SECOND);
	      gc1.clear(Calendar.MINUTE);
	      gc1.clear(Calendar.HOUR_OF_DAY);
	      gc1.clear(Calendar.DATE);

	      gc2.clear(Calendar.MILLISECOND);
	      gc2.clear(Calendar.SECOND);
	      gc2.clear(Calendar.MINUTE);
	      gc2.clear(Calendar.HOUR_OF_DAY);
	      gc2.clear(Calendar.DATE);

	      while ( gc1.before(gc2) ) {
	         gc1.add(Calendar.MONTH, 1);
	         elapsed++;
	      }
	      return elapsed;
	  } catch (ParseException e) {
		  e.printStackTrace();
		  return 0;
	  }
   }
   
   /**  
    * 得到本月的第一天 
    * @param date 
    * @return  
    */  
   public static String getMonthFirstDay(String date) { 
	   date = date.replaceAll("-", "/");	  
	   //SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
	   Date datetime;
	   try {
			datetime = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(datetime);
		    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));	      
		    return sdf.format(calendar.getTime());
	   } catch (ParseException e) {
			e.printStackTrace();
			return null;
	   }	   
          
  }   
     
  /**
   * 得到本月的最后一天 
   * @param date
   * @return
   */
  public static String getMonthLastDay(String date) {
	   date = date.replaceAll("-", "/");
	   //SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
	   Date datetime;
	   try {
			datetime = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(datetime);
		    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));	      
		    return sdf.format(calendar.getTime());
	   } catch (ParseException e) {
			e.printStackTrace();
			return null;
	   }  
  }
  
  /**
   * 日期所在年的第几周
   * @param date
   * @return
   */
  public static int getWeekOfYear(String date){
	  date = date.replaceAll("-", "/");
	  //SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
	  Date datetime;
	  try {
			datetime = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(datetime);      
		    return calendar.get(Calendar.WEEK_OF_YEAR);
	   } catch (ParseException e) {
			e.printStackTrace();
			return 0;
	   }
  }
  
  /**
   * 判断是否在同一周
   * @param date1
   * @param date2
   * @return
   */
  public static boolean isSameWeekDate(String date1,String date2){
	   date1 = date1.replaceAll("-", "/");
	   date2 = date2.replaceAll("-", "/");
	   //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	   Date d1 = null;
	   Date d2 = null;
	   try{
		   d1 = sdf.parse(date1);
		   d2 = sdf.parse(date2);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   Calendar cal1 = Calendar.getInstance();
	   Calendar cal2 = Calendar.getInstance();
	   cal1.setTime(d1);
	   cal2.setTime(d2);
	   int subYear = cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR);
	   //subYear==0,说明是同一年
	   if(subYear == 0){
		   if(cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
			   return true;
	   }
	   //例子:cal1是"2005-1-1"，cal2是"2004-12-25"
	   //java对"2004-12-25"处理成第52周
	   // "2004-12-26"它处理成了第1周，和"2005-1-1"相同了
	   //大家可以查一下自己的日历
	   //处理的比较好
	   //说明:java的一月用"0"标识，那么12月用"11"
	   else if(subYear==1 && cal2.get(Calendar.MONTH)==11){
		   if(cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
			   return true;
	   }
	   //例子:cal1是"2004-12-31"，cal2是"2005-1-1"
	   else if(subYear==-1 && cal1.get(Calendar.MONTH)==11){
		   if(cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
			   return true;
	    
	   }
	   return false;
  }
  
  /**
   * 所在周的第一天
   * @param date
   * @return
   */
  public static String getWeeksFirstDay(String date){
	  date = date.replaceAll("-", "/");
	  //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	  Date datetime = null;
	  try {
			datetime = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(datetime);
		    int datInWeek = calendar.get(Calendar.DAY_OF_WEEK);
		    calendar.add(Calendar.DAY_OF_MONTH,-(datInWeek-1));
		    return sdf.format(calendar.getTime());
	   } catch (ParseException e) {
			e.printStackTrace();
			return null;
	   }
  }
  
  /**
   * 所在周的最后一天天
   * @param date
   * @return
   */
  public static String getWeeksLastDay(String date){
	  date = date.replaceAll("-", "/");
	  //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	  Date datetime = null;
	  try {
			datetime = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(datetime);
		    int datInWeek = calendar.get(Calendar.DAY_OF_WEEK);
		    calendar.add(Calendar.DAY_OF_MONTH, 7-datInWeek);  
		    return sdf.format(calendar.getTime());
	   } catch (ParseException e) {
			e.printStackTrace();
			return null;
	   }
  }
	
	/**
	 * 周期划分
	 * @param doc
	 * @param report
	 * @param request
	 */
	public static List getCycle(String starttime, String endtime,String cycletype){
		TimeTool tt = new TimeTool();
		if(starttime == null || "".equals(starttime)){
			starttime = tt.getC_date("/");
		}
		if(endtime==null || "".equals(endtime)){
			endtime = tt.getC_date("/");
		}
		starttime = starttime.replaceAll("-", "/");
		endtime = endtime.replaceAll("-", "/");
		
		//按月计算周期
		List cyclelst = new ArrayList();
		if("month".equals(cycletype)){
			int months = 0;
			String[] starts = starttime.split("/");
			String[] ends = endtime.split("/");
			//所在月相同
			if(starts[0]==ends[0] && starts[1]==ends[1]){
				months = 1;
			}else{
				//STARTDATE ->月头 ENDDATE ->月末
				String start = TimeTool.getMonthFirstDay(starttime);
				String end = TimeTool.getMonthLastDay(endtime);
				months = TimeTool.getMonths(start, end);
			}
			//String tmpstart = starttime;
			String tmpstart = TimeTool.getMonthFirstDay(starttime);
			for(int i=0;i<months;i++){
				//拆解时间段
				HashMap hmp = new HashMap();
				hmp.put("starttime", tmpstart);
				String tmpend = TimeTool.getMonthLastDay(tmpstart);
				hmp.put("endtime", tmpend);
				String[] tmps = tmpend.split("/");
				if(tmps[0]==ends[0] && tmps[1]==ends[1]){
					//hmp.put("endtime", endtime);
					hmp.put("endtime", tmpend);
				}else{
					tmpstart = TimeTool.getNextDay(tmpend);
				}
				cyclelst.add(hmp);
			}
		}
		//按周计算周期
		else{
			int weeks = 0;
			if(TimeTool.isSameWeekDate(starttime,endtime)){
				weeks = 1;
			}else{
				//STARTDATE ->周头 ENDDATE ->周末
				String start = TimeTool.getWeeksFirstDay(starttime);
				String end = TimeTool.getWeeksLastDay(endtime);
				weeks = TimeTool.getWeeks(start, end);
			}
			//String tmpstart = starttime;
			String tmpstart = TimeTool.getWeeksFirstDay(starttime);
			for(int i=0;i<weeks;i++){
				//拆解时间段
				HashMap hmp = new HashMap();
				hmp.put("starttime", tmpstart);
				String tmpend = TimeTool.getWeeksLastDay(tmpstart);
				hmp.put("endtime", tmpend);
				if(TimeTool.isSameWeekDate(starttime,endtime)){
					//hmp.put("endtime", endtime);
					hmp.put("endtime", tmpend);
				}else{
					tmpstart = TimeTool.getNextDay(tmpend);
				}
				cyclelst.add(hmp);				
			}
		}		
		return cyclelst;
	}

   
	public static void main(String[] args) throws Exception{
	   /*
	    计算2个日期的相隔时间-天-时-分
	    */
	    int temp = betweenMinutes("2008/09/21/08:15","2008/09/22/00:15");
	    System.out.println(temp);
	    if(temp/(60*24)>=1){
			System.out.print(temp/(60*24)+"天");
		}
		temp = temp%(60*24);
		if(temp/(60)>=1){
			System.out.print(temp/(60)+"小时");
		}
		temp = temp%(60);
		if(temp/(60)>=1){
			System.out.print(temp/60+"分");
		}
	}
}
