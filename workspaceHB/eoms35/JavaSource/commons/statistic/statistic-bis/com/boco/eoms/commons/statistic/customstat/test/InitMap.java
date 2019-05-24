package com.boco.eoms.commons.statistic.customstat.test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class InitMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	  Calendar cal=Calendar.getInstance();
	  int year = cal.get(Calendar.YEAR);
	  int month = cal.get(Calendar.MONTH);
	  int date = cal.get(Calendar.DATE);
	  cal.set(year,month,date);
	  cal.add(Calendar.DATE,-1);
	  System.out.println("日报：" + cal);
	  printTime(cal);
	  
	  cal.set(year,month,date);
	  cal.add(Calendar.MONTH,-1);
	  System.out.println("月报：" + cal);  
	  printTime(cal);
	  
	  cal.set(year,month,date);
	  cal.add(Calendar.MONTH,-3);
	  System.out.println("季报：" + cal);
	  printTime(cal);
	  
	  cal.set(year,month,date);
	  cal.add(Calendar.WEEK_OF_YEAR,-1);
	  System.out.println("周报：" + cal);
	  printTime(cal);
	  
	  cal.set(year,month,date);
	  cal.add(Calendar.YEAR,-1);
	  System.out.println("年报：" + cal);
	  printTime(cal);
		
////		用add()代替roll()，roll是不会改变比较它大的单位的值的，下面是例子   
////		  //#1   
//		  Calendar cal=Calendar.getInstance();
//		  int year = cal.get(Calendar.DAY_OF_YEAR);
//		  cal.set(year,Calendar.MARCH,1);     //2000-03-01   
//		  cal.add(Calendar.DATE,-1);         //2000-02-29   
//		  //#2   
////		  Calendar cal=Calendar.getInstance();   
////		  cal.set(1999,Calendar.MARCH,1);     //1999-03-01   
////		  cal.add(Calendar.DATE,-1);         //1999-02-28   

	}
	
//	 {beginyear=2008, reportIndex=0, mainNetSortOne=101010408, 
//		 beginmonth=12, type=time, 
//		 RequestURI=/zy/statistic/commonfault/stat.do, graphicReportType=column, excelConfigURL=commonfault_T_resolve_KPI4_oracle, 
//		 endTime=2008-12-03 15:16:16, seasonselect=season_one, method.save=提交, 
//		 beginTime=2006-12-03 15:16:16, 
//		 ActionForm=com.boco.eoms.commons.statistic.base.webapp.form.StatForm@e56499[validatorResults=<null>page=0], 
//		 findListForward=T_resolve_statisticsheetlist, reportFromType=StatFrom, 
//		 HttpServletRequest=org.acegisecurity.wrapper.SavedRequestAwareWrapper@2fafa2, userByDeptName=, 
//		 method=performStatistic, subroleFromDeptName=, userByDeptid=, areaName=, subroleFromDeptid=, areaid=}

	
	private static void printTime(Calendar cal)
	{
		System.out.println("年：" + cal.get(Calendar.YEAR));
		System.out.println("月：" + (cal.get(Calendar.MONTH) + 1));
		System.out.println("日：" + cal.get(Calendar.DATE));
		System.out.println("=========================================");
	}
	public static Map initMap()
	{
		Map map = new HashMap();
		map.put("mainNetSortOne", "101010408");
		map.put("endTime", "2008-12-03 15:16:16");
		map.put("beginTime", "2006-12-03 15:16:16");
		
		map.put("reportIndex", "0");
		map.put("requestURI", "/zy/statistic/customstat/stat.do");
		map.put("excelConfigURL", "commonfault_T_resolve_KPI4_oracle");
		map.put("findListForward", "T_resolve_statisticsheetlist");
		map.put("reportFromType", "StatFrom");
		map.put("reportType", "yearReport");
		
		return map;
	}

}
