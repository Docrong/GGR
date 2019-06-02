// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LocalUtils.java

package com.boco.eoms.sheet.base.util.local;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LocalUtils
{

	public LocalUtils()
	{
	}

	public static String getMonthBegin(String beginyear, String beginmonth)
	{
		Calendar c = Calendar.getInstance();
		c.set(1, Integer.parseInt(beginyear));
		c.set(2, Integer.parseInt(beginmonth) - 1);
		String begintime = beginyear + "-" + beginmonth + "-" + c.getActualMinimum(5) + " 00:00:00";
		return begintime;
	}

	public static String getMonthEnd(String beginyear, String beginmonth)
	{
		Calendar c = Calendar.getInstance();
		c.set(1, Integer.parseInt(beginyear));
		c.set(2, Integer.parseInt(beginmonth) - 1);
		String endtime = beginyear + "-" + beginmonth + "-" + c.getActualMaximum(5) + " 23:59:59";
		return endtime;
	}

	public static long getResumeTimeSlot(String beginTime, String endTime)
	{
		long timeSlot = 0L;
		try
		{
			if (beginTime != null && endTime != null)
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar begin = Calendar.getInstance();
				begin.setTime(dateFormat.parse(beginTime));
				Calendar end = Calendar.getInstance();
				end.setTime(dateFormat.parse(endTime));
				timeSlot = (end.getTimeInMillis() - begin.getTimeInMillis()) / 60000L;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return timeSlot;
	}
}