package com.boco.eoms.commons.statistic.base.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;

public class BaseStatFileListDisplaytagDecoratorHelper extends TableDecorator {
	
	public String getShowDetail()
	{
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";
		
			reURL = "<a onclick=openSheet('" + url
			+ "?method=showSatatistFileResult&htmlFilePath=" +vo.getHtmlFilePath() + "&excelFilePath=" + vo.getExcelFilePath()
			+ "'); href='#'>" + "查看" + "</a>";
		
		return reURL;
	}
	
	public String getReportType() {
		StatisticFileInfo vo = (StatisticFileInfo) getCurrentRowObject();
		return CustomStatUtil.typeToName(vo.getReportType());
	}
}
