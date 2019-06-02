package com.boco.eoms.commons.statistic.customstat.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;
import com.boco.eoms.commons.statistic.base.util.Constants;
import com.boco.eoms.commons.statistic.base.util.StatUtil;

public class BaseStatFileListDisplaytagDecoratorHelper extends TableDecorator {
	
	public String getShowDetail()
	{
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";
		
			reURL = "<a onclick=window.open('" + url
			+ "?method=showSatatistFileResult&htmlFilePath=" +vo.getHtmlFilePath() + "&excelFilePath=" + vo.getExcelFilePath() + "&excelFileName=" + vo.getExcelFileName() + "&id=" + vo.getId()
			+ "'); href='#'>" + "查看" + "</a>";
		
		return reURL;
	}
	
	public String getShowDelete() {
		
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";
		
			reURL = "<a onclick=openSheet('" + url
			+ "?method=deleteSatatistFile&htmlFilePath=" +vo.getHtmlFilePath() + "&excelFilePath=" + vo.getExcelFilePath() + "&excelFileName=" + vo.getExcelFileName() + "&id=" + vo.getId()
			+ "'); href='#'>" + "删除" + "</a>";
			
		return reURL;
	}
	
	public String getSubscibeId()
	{
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		
		return StatUtil.id2Name(vo.getSubscibeId(), "statBaseUserId2name_v35");
	}
	
	public String getReportInfo() {
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		String displayStr = "";
		String reportType = vo.getReportType();
		
		if("yearReport".equalsIgnoreCase(reportType))
    	{
			displayStr = vo.getReportYear() + "年";
    	}
    	else if("seasonReport".equalsIgnoreCase(reportType))
    	{
    		displayStr = vo.getReportYear() + "年" + "第" + vo.getReportSeason() + "季度";
    	}
    	else if("monthReport".equalsIgnoreCase(reportType))
    	{
    		displayStr = vo.getReportYear() + "年"  + vo.getReportMonth() + "月";
    	}
    	else if("weekReport".equalsIgnoreCase(reportType))
    	{
    		displayStr = vo.getReportYear() + "年"   + "第" + vo.getReportWeek() + "周";
    	}
    	else if("dailyReport".equalsIgnoreCase(reportType))
    	{
    		displayStr = vo.getReportYear() + "年"  + vo.getReportMonth() + "月" + vo.getReportDate() + "日"; 
    	}
    	else if("customReport".equalsIgnoreCase(reportType))
    	{
    		displayStr = vo.getSaveTime();
    	}
		
		return displayStr;
	}
	
	public String getChecked() 
	{
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		String checked = "否";
		if("Y".equalsIgnoreCase(vo.getChecked()))
		{
			checked = "是";
		}
		return checked;
	}
	
	public String getReadedState() {
		
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		String readedState = "未阅读";
		if("Y".equalsIgnoreCase(vo.getReadedState()))
		{
			readedState = "已阅读";
		}
		return readedState;
	}
	
	public String getReportType() {
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		return CustomStatUtil.typeToName(vo.getReportType());
	}
}
