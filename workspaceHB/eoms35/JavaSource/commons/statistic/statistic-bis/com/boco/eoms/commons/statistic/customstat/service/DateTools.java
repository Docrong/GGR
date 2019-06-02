package com.boco.eoms.commons.statistic.customstat.service;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DateTools {
	
	private static  char   DAY_DELIMITER  = '-';
	public  static  int    YEAR           = Calendar.YEAR;
	public  static  int    MONTH          = Calendar.MONDAY;
	public  static  int    DAY            = Calendar.DAY_OF_MONTH;
	public  static  int    HOUR           = Calendar.HOUR_OF_DAY;
	public  static  int    MINUTE         = Calendar.MINUTE;
	public  static  int    SECOND         = Calendar.SECOND;
    
	  /**
	   * ȡ�õ�ǰ����
	   * @return YYYY-MM-DD
	   */
	  public static String getDate()
	  {
	    return getDateTime().substring(0,10);
	  }

	  /**
	   * ȡ�õ�ǰʱ��
	   * @return  HH:MM:SS
	   */
	  public static String getTime()
	  {
	    return getDateTime().substring(11,19);
	  }

	  /**
	   * �õ���ǰ�����ڼ�������һΪ1��������Ϊ7
	   * @return int
	   */
	  public static int getCurWeekDay()
	  {
	    GregorianCalendar gc=new GregorianCalendar();
	    int ret=gc.get(GregorianCalendar.DAY_OF_WEEK);
	    if(ret==1)
	    {
	      ret=7;
	    }else
	    {
	      ret=ret-1;
	    }
	    return ret;
	  }

	  /**
	  * ȡ�õ�ǰ������ʱ��
	  * @return YYYY-MM-DD HH:MM:DD
	*/
	  public static String getDateTime(){
	     return getDateTime(new GregorianCalendar());
	  }



	  /**
	   * ������������ʱ��
	   * @param calendar  ����
	   * @return YYYY-MM-DD HH:MM:DD
	   */
	  private  static String getDateTime(Calendar calendar)
	  {
	    StringBuffer buf = new StringBuffer("");

	    buf.append(calendar.get(calendar.YEAR));
	    buf.append(DAY_DELIMITER);
	    buf.append(calendar.get(calendar.MONTH)+1>9?calendar.get(calendar.MONTH)+1+"":"0"+(calendar.get(calendar.MONTH)+1));
	    buf.append(DAY_DELIMITER);
	    buf.append(calendar.get(calendar.DAY_OF_MONTH)>9?calendar.get(calendar.DAY_OF_MONTH)+"":"0"+calendar.get(calendar.DAY_OF_MONTH));
	    buf.append(" ");
	    buf.append(calendar.get(calendar.HOUR_OF_DAY)>9?calendar.get(calendar.HOUR_OF_DAY)+"":"0"+calendar.get(calendar.HOUR_OF_DAY));
	    buf.append(":");
	    buf.append(calendar.get(calendar.MINUTE)>9?calendar.get(calendar.MINUTE)+"":"0"+calendar.get(calendar.MINUTE));
	    buf.append(":");
	    buf.append(calendar.get(calendar.SECOND)>9?calendar.get(calendar.SECOND)+"":"0"+calendar.get(calendar.SECOND));
	    return buf.toString();
	  }

	  /**
	   * ��ָ���������а�ĳ��ʱ���������ָ������
	   * @param datetime YYYY-MM-DD HH:MM:SS
	   * @param type YEAR,MONTH,DAY,HOUR,MINUTE,SECOND
	   * @param step ���� �������������
	   * @return  �ı�������ʱ�� YYYY-MM-DD HH:MM:SS
	   */
	  public static  String getPreDateTime(String datetime,int type ,int step){



	  Calendar calendar = new GregorianCalendar(Integer.parseInt(datetime.substring(0,4)),
	                                            Integer.parseInt(datetime.substring(5,7))-1,
	                                            Integer.parseInt(datetime.substring(8,10)),
	                                            Integer.parseInt(datetime.substring(11,13)),
	                                            Integer.parseInt(datetime.substring(14,16)),
	                                            Integer.parseInt(datetime.substring(17,19))
	                                            );
	  calendar.add(type,step);
	  return getDateTime(calendar);
	}
	/**
	* ����ַ�����ȡ����������
	* @param datetime String  YYYY-MM-DD HH:MM:SS
	* @return Calendar
	*/
	public static Calendar getCalendar(String datetime){
	    return   new GregorianCalendar(Integer.parseInt(datetime.substring(0,4)),
	                                           Integer.parseInt(datetime.substring(5,7))-1,
	                                           Integer.parseInt(datetime.substring(8,10)),
	                                           Integer.parseInt(datetime.substring(11,13)),
	                                           Integer.parseInt(datetime.substring(14,16)),
	                                           Integer.parseInt(datetime.substring(17,19))
	                                           );
	  }

	/**
	* ��ָ���������а�ĳ��ʱ���������ָ������
	* @param datetime YYYY-MM-DD HH:MM:SS
	* @param type YEAR,MONTH,DAY,HOUR,MINUTE,SECOND
	* @param step ���� �������������
	* @return  �ı�������ʱ�� YYYY-MM-DD
	*/
	public static  String getPreDate(String date,int type ,int step){
	Calendar calendar = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),
	                                          Integer.parseInt(date.substring(5,7))-1,
	                                          Integer.parseInt(date.substring(8,10)),
	                                          0,
	                                          0,
	                                          0
	                                          );
	calendar.add(type,step);
	return getDateTime(calendar).substring(0,10);
	}


	/**
	* ȡ�õ�ǰ���ڵ�����
	* @return YYYYMMDD
	*/

	  public static int getDateInt()
	  {
	    String date = getDate();
	    return Integer.parseInt(date.substring(0,4)+date.substring(5,7)+date.substring(8,10));
	  }

	  public static String getDateTimeOfInt() {
	    String date = getDateTime();
	    return  date.substring(0, 4) + date.substring(5, 7) +
	                            date.substring(8, 10) +date.substring(11,13)+date.substring(14, 16)+date.substring(17, 19);
	  }


	  /**
	* ȡ�õ�ǰ���ڵ�����
	* @return YYYYMMDD
	*/

	  public static int getDateTimeInt()
	  {
	    String date = getDate();
	    return Integer.parseInt(date.substring(0,4)+date.substring(5,7)+date.substring(8,10));
	  }

	  /**
	   * ȡ��ָ��ʱ���ʱ���
	   * @return long  ʱ���
	   */
	  public static  long getTimeStamp(String datetime){
	      return getCalendar(datetime).getTime().getTime();
	  }

	  /**
	   * ȡ�õ�ǰ��ʱ���
	   * @return long  ʱ���
	   */
	  public static long getTimeStamp() {
	    return System.currentTimeMillis();
	  }



	/**
	  * ȡ�õ�ǰʱ�������
	  * @return HHMMSS
	*/
	  public static int getTimeInt()
	  {
	    String date = getDateTime();
	    return Integer.parseInt(date.substring(11,13)+date.substring(14,16)+date.substring(17,19));
	  }


}
