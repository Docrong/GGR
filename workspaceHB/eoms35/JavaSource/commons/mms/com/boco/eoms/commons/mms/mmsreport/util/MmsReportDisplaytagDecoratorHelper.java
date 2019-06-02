package com.boco.eoms.commons.mms.mmsreport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.mms.base.util.MMSUtil;
import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;

public class MmsReportDisplaytagDecoratorHelper extends TableDecorator{
	
	public String getShowDetail()
	{
		Mmsreport vo = (Mmsreport) getCurrentRowObject();
		
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";
		
			reURL = "<a onclick=window.open('" + url
			+ "?method=detail&id=" + vo.getId()
			+ "'); href='#'>" + "查看" + "</a>";
		
		return reURL;
	}
	
	
	public String getMmsreportType()
	{
		Mmsreport vo = (Mmsreport) getCurrentRowObject();
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(vo.getMmsReportCreateDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		//判断是属于月，周，日的情况
		String displayStr = MMSUtil.getDateString(calendar,vo.getMmsreportType());
		return displayStr;
	}
}
