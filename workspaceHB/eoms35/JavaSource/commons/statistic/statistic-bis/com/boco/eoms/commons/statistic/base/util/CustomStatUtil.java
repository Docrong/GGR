package com.boco.eoms.commons.statistic.base.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.config.excel.Excel;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.mgr.IStatManager;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

/**
 * 订制统计工具类
 * 
 * @author lizhenyou
 *
 */
public class CustomStatUtil {
	
	public static String typeToName(String reportType)
	{
		String reportType_CH = "";
		if(reportType.equalsIgnoreCase("yearReport"))
		{
			reportType_CH = "年报";
		}
		else if(reportType.equalsIgnoreCase("seasonReport"))
		{
			reportType_CH = "季报";
		}
		else if(reportType.equalsIgnoreCase("monthReport"))
		{
			reportType_CH = "月报";
		}
		else if(reportType.equalsIgnoreCase("weekReport"))
		{
			reportType_CH = "周报";
		}
		else if(reportType.equalsIgnoreCase("dailyReport"))
		{
			reportType_CH = "日报";
		}
		
		return reportType_CH;
	}
	
	/**
	 * 添加统计条件 beginTime和endTime
	 * 
	 * @param conditionMap
	 * @param currentDate
	 * @param type
	 */
	public static void modConditionMap(Map conditionMap,Calendar currentCanlender,String type)
	{
		conditionMap.put("saveTime", currentCanlender);
		
		if("yearReport".equalsIgnoreCase(type) )
		{
			addYearBeginTimeANDEndTime(conditionMap,currentCanlender);
		}
		else if("seasonReport".equalsIgnoreCase(type))
		{
			addSeasonBeginTimeANDEndTime(conditionMap,currentCanlender);
		}
		else if("monthReport".equalsIgnoreCase(type))
		{
			addMonthBeginTimeANDEndTime(conditionMap,currentCanlender);
		}
		else if("weekReport".equalsIgnoreCase(type))
		{
			addWeekBeginTimeANDEndTime(conditionMap,currentCanlender);
		}
		else if("dailyReport".equalsIgnoreCase(type))
		{
			addDailyBeginTimeANDEndTime(conditionMap,currentCanlender);
		}
	}
	
	private static void addYearBeginTimeANDEndTime(Map conditionMap,Calendar currentCanlender)
	{
		int currentYear = currentCanlender.get(Calendar.YEAR);
		
		GregorianCalendar bdate = new GregorianCalendar(currentYear, 1 - 1, 1, 00, 00, 00);
		GregorianCalendar edate = new GregorianCalendar(currentYear, 12 - 1, 1, 23, 59, 59);

		addConditionMap(conditionMap,bdate,edate);
	}
	
	private static void addSeasonBeginTimeANDEndTime(Map conditionMap,Calendar currentCanlender)
	{
		int currentYear = currentCanlender.get(Calendar.YEAR);
		int currentMonth = currentCanlender.get(Calendar.MONTH) + 1;
		
		GregorianCalendar bdate = null;
		GregorianCalendar edate = null;
		//春
		if((currentMonth <= 4) && (currentMonth >= 2))
		{
			bdate = new GregorianCalendar(currentYear, 2-1, 1, 00, 00, 00);
			edate = new GregorianCalendar(currentYear, 4-1, 1, 23, 59, 59);
		}
		//夏
		else if((currentMonth <= 7) && (currentMonth >= 5))
		{
			bdate = new GregorianCalendar(currentYear, 5-1, 1, 00, 00, 00);
			edate = new GregorianCalendar(currentYear, 7-1, 1, 23, 59, 59);
		}
		//秋
		else if((currentMonth <= 10) && (currentMonth >= 8))
		{
			bdate = new GregorianCalendar(currentYear, 8-1, 1, 00, 00, 00);
			edate = new GregorianCalendar(currentYear, 10-1, 1, 23, 59, 59);
		}
		//冬
		else
		{
			bdate = new GregorianCalendar(currentYear - 1, 11-1, 1, 00, 00, 00);
			edate = new GregorianCalendar(currentYear, 1-1, 1, 23, 59, 59);
		}
		
		addConditionMap(conditionMap,bdate,edate);
	}
	
	private static void addMonthBeginTimeANDEndTime(Map conditionMap,Calendar currentCanlender)
	{
		int currentYear = currentCanlender.get(Calendar.YEAR);
		int currentMonth = currentCanlender.get(Calendar.MONTH) + 1;
		
		GregorianCalendar bdate = new GregorianCalendar(currentYear, currentMonth - 1, 1, 00, 00, 00);
		GregorianCalendar edate = new GregorianCalendar(currentYear, currentMonth - 1, 1, 23, 59, 59);
		
		addConditionMap(conditionMap,bdate,edate);
	}
	
	private static void addWeekBeginTimeANDEndTime(Map conditionMap,Calendar currentCanlender)
	{
		int currentYear = currentCanlender.get(Calendar.YEAR);
		int currentMonth = currentCanlender.get(Calendar.MONTH) + 1;
		int currentDaily = currentCanlender.get(Calendar.DAY_OF_MONTH);
		
		GregorianCalendar bdate = new GregorianCalendar(currentYear, currentMonth - 1, currentDaily, 00, 00, 00);
		GregorianCalendar edate = new GregorianCalendar(currentYear, currentMonth - 1, currentDaily, 23, 59, 59);
		
		bdate.roll(GregorianCalendar.DAY_OF_YEAR , -6);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dfbdate = sdf.format(bdate.getTime());
		String dfedate = sdf.format(edate.getTime());
		System.out.println(dfbdate);
		System.out.println(dfedate);
		conditionMap.put("beginTime", dfbdate);
		conditionMap.put("endTime", dfedate);
	}
	
	public static void main(String[] args)
	{
		Map map = new HashMap();
		addWeekBeginTimeANDEndTime(map,new GregorianCalendar(2008, 2 - 1, 3, 00, 00, 00));
		
	}
	
	private static void addDailyBeginTimeANDEndTime(Map conditionMap,Calendar currentCanlender)
	{
		int currentYear = currentCanlender.get(Calendar.YEAR);
		int currentMonth = currentCanlender.get(Calendar.MONTH) + 1;
		int currentDaily = currentCanlender.get(Calendar.DAY_OF_MONTH);
		
		GregorianCalendar bdate = new GregorianCalendar(currentYear, currentMonth - 1, currentDaily, 00, 00, 00);
		GregorianCalendar edate = new GregorianCalendar(currentYear, currentMonth - 1, currentDaily, 23, 59, 59);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dfbdate = sdf.format(bdate.getTime());
		String dfedate = sdf.format(edate.getTime());
		
		conditionMap.put("beginTime", dfbdate);
		conditionMap.put("endTime", dfedate);
	}
	
	private static void addConditionMap(Map conditionMap,GregorianCalendar bdate,GregorianCalendar edate)
	{
		Date datee = StatUtil.getMonthEndDate(edate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dfbdate = sdf.format(bdate.getTime());
		String dfedate = sdf.format(datee);
		
		conditionMap.put("beginTime", dfbdate);
		conditionMap.put("endTime", dfedate);
	}
	
	/**
	 * 解析条件字段为一个Map
	 * 
	 * @param conditionString
	 * @return
	 */
	public static Map getConditionMap(String conditionString)
	{
		//解析条件字段（condition）
		Map conditionMap = new HashMap();
		String[] conditionArray = conditionString.split("#");
		
		for(int j=0; j<conditionArray.length - 1; j++)
		{
			String[] split = conditionArray[j].split("=");
			if(split.length == 2)
			{
				String key = split[0].trim();
				String value = split[1].trim();
				conditionMap.put(key, value);
			}
		}
		
		return conditionMap;
	}

	private static String getReportTime(String reportType,String beginTime)
	{
		String reTime = "";
		if("年报".equalsIgnoreCase(reportType))
		{
			reTime = yearReportExcelTime(beginTime);
		}
		else if("月报".equalsIgnoreCase(reportType))
		{
			reTime = monthReportExcelTime(beginTime);
		}
		else if("周报".equalsIgnoreCase(reportType))
		{
			reTime = weekhReportExcelTime(beginTime);
		}
		else if("日报".equalsIgnoreCase(reportType))
		{
			reTime = dailyhReportExcelTime(beginTime);
		}
		
		return reTime;
	}
	
	public static String foundExcelToFile(Map conditionMap,String excelConfigURL,String reportName,List listResult ,Map infoMap, KpiDefine kpiDefine,String exclePartURL,IStatManager statManager) throws Exception
	{
		String ExcelConfigFilePath = StaticMethod.getFilePathForUrl(excelConfigURL);
		OutputStream fileStream = statManager.exportExcelKpiStream(ExcelConfigFilePath, reportName, listResult, infoMap,kpiDefine);
		
		String beginTime  = String.valueOf(conditionMap.get("beginTime"));
		String saveTime =  dateToString(((Calendar)conditionMap.get("saveTime")).getTime(),"yyyyMMddHHmmss");
		//String saveTime =  dateToString(Calendar.getInstance().getTime(),"yyyyMMddHHmmss");
		
		String reportType = CustomStatUtil.typeToName(String.valueOf(conditionMap.get("reportType")));
		
		String ExcelFilePath = ExcelConfigFilePath.substring(0, ExcelConfigFilePath.indexOf(Constants.WEB_INF)) + exclePartURL + reportName + "-" + getReportTime(reportType, beginTime) + reportType + "-" + saveTime + ".xls";
		ExcelConverterUtil.writeFile((ByteArrayOutputStream)fileStream, ExcelFilePath);
		System.out.println("执行订制保存Excel到磁盘路径为： " + ExcelFilePath);
		return ExcelFilePath;
	}
	
	private  static String yearReportExcelTime(String beginTime)
	{
		String timeString = dateFormat(beginTime,"yyyy-MM-dd HH:mm:ss","yy");
		
		return timeString;
	}
	
	private  static String monthReportExcelTime(String beginTime)
	{
		String timeString = dateFormat(beginTime,"yyyy-MM-dd HH:mm:ss","yyMM");
		
		return timeString;
	}
	
	private  static String weekhReportExcelTime(String beginTime)
	{
		String timeString = dateFormat(beginTime,"yyyy-MM-dd HH:mm:ss","yyMMdd");
		
		return timeString;
	}
	
	private  static String dailyhReportExcelTime(String beginTime)
	{
		String timeString = dateFormat(beginTime,"yyyy-MM-dd HH:mm:ss","yyMMdd");
		
		return timeString;
	}
	
	
	public static String dateFormat(String srsuceString ,String startFormat,String endFormat)
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat(startFormat);
		SimpleDateFormat sdf2 = new SimpleDateFormat(endFormat);
		
		String reString = "";
		try {
			reString = sdf2.format(sdf1.parse(srsuceString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reString;
	}
	
	private static String dateToString(Date srsuceDate,String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(srsuceDate);
	}
	
	/**
	 * 根据Excel显示订制的统计
	 * @param excelConfigURL
	 * @param statManager
	 * @return
	 */
	public static String getCustomStatisticCcndtion(String excelConfigURL,IStatManager statManager)
	{
		Excel excelConfig = null;
		try {
			excelConfig = statManager.getStatConfigManager().getExcelConfig(excelConfigURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sheet[] sheets = excelConfig.getSheets();
		String condtion = " where statname='";
		for(int i=0 ; i<sheets.length; i++ )
		{
			if(i!=0)
			{
				condtion+=" or statname='";
			}
			condtion += sheets[i].getSheetName() + "'";
		}
		
		return condtion;
	}

}
