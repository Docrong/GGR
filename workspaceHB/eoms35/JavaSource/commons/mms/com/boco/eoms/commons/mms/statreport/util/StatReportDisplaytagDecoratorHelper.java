package com.boco.eoms.commons.mms.statreport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.mms.base.util.MMSUtil;
import com.boco.eoms.commons.mms.statreport.model.Statreport;

public class StatReportDisplaytagDecoratorHelper extends TableDecorator{
	
	public String getShowDetail()
	{
		Statreport vo = (Statreport) getCurrentRowObject();
		
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";
		
			reURL = "<a onclick=window.open('" + url
			+ "?method=detail&id=" + vo.getId()
			+ "'); href='#'>" + "查看" + "</a>";
		
		return reURL;
	}
	
	public String getReportType()
	{
		Statreport vo = (Statreport) getCurrentRowObject();
		if(vo.getCreateTime() == null)
		{
			return "";
		}
		
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(vo.getCreateTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		//判断是属于月，周，日的情况
		String displayStr = MMSUtil.getDateString(calendar,vo.getReportType());
		return displayStr;
	}
}
