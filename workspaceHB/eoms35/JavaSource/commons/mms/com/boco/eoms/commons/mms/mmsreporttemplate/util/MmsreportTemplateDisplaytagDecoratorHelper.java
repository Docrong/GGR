package com.boco.eoms.commons.mms.mmsreporttemplate.util;

import java.io.FileNotFoundException;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.mms.base.config.Reports;
import com.boco.eoms.commons.mms.base.config.Sheet;
import com.boco.eoms.commons.mms.base.util.MMSConstants;
import com.boco.eoms.commons.mms.mmsreporttemplate.model.MmsreportTemplate;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;

public class MmsreportTemplateDisplaytagDecoratorHelper extends TableDecorator {
	public String getShowDetail() {
		MmsreportTemplate vo = (MmsreportTemplate) getCurrentRowObject();

		String url = (String) this.getPageContext().getAttribute("url");
		this.getPageContext().getRequest().getContentType();

		String reURL = "";

		reURL = "<a onclick=window.open('" + url + "?method=find&id="
				+ vo.getId() + "'); href='#'>" + "查看" + "</a>";

		return reURL;
	}

	public String getShowDelete() {

		MmsreportTemplate vo = (MmsreportTemplate) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";

		reURL = "<a onclick=openSheet('" + url + "?method=remove&id="
				+ vo.getId() + "'); href='#'>" + "删除" + "</a>";

		return reURL;
	}

	public String getShowModify() {

		MmsreportTemplate vo = (MmsreportTemplate) getCurrentRowObject();
		String url = (String) this.getPageContext().getAttribute("url");

		String reURL = "";

		reURL = "<a onclick=window.open('" + url + "?method=edit&id="
				+ vo.getId() + "'); href='#'>" + "修改" + "</a>";

		return reURL;
	}

	public String getExecuteCycle() {
		MmsreportTemplate vo = (MmsreportTemplate) getCurrentRowObject();
		String cycle = vo.getExecuteCycle();
		String displayStr = "";
		if ("weekReport".equals(cycle)) {
			displayStr = "周报";
		}
		if ("monthReport".equals(cycle)) {
			displayStr = "月报";
		}
		if ("dailyReport".equals(cycle)) {
			displayStr = "日报";
		}
		return displayStr;
	}
	                 
	public String getStatReportId() {
		String configpath = MMSConstants.REPORT_CONFIG;
		Reports reports = null;
		String statReportStr = null;
		try {
			reports = (Reports) ParseXmlService.create().xml2object(
					Reports.class, StaticMethod.getFilePathForUrl(configpath));
			
			MmsreportTemplate vo = (MmsreportTemplate) getCurrentRowObject();
			statReportStr = vo.getStatReportId();
			String[] statReports = statReportStr.split(",");
			String reportId = "";
			String reportName = "";
			for (int i = 0; i < reports.getAllSheet().size(); i++) {
				reportId = ((Sheet) (reports.getAllSheet().get(i))).getId();
				for(int n=0;n<statReports.length;n++){
					if(statReports[n].equals(reportId)){
						reportName = ((Sheet) (reports.getAllSheet().get(i))).getName();
						//用报表名称替换报表id
						statReportStr = statReportStr.replace(statReports[n], reportName);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statReportStr;
	}
	
	public static String getStatReportId(String statReportStr) {
		String configpath = MMSConstants.REPORT_CONFIG;
		Reports reports = null;
		try {
			reports = (Reports) ParseXmlService.create().xml2object(
					Reports.class, StaticMethod.getFilePathForUrl(configpath));
			
			String[] statReports = statReportStr.split(",");
			String reportId = "";
			String reportName = "";
			for (int i = 0; i < reports.getAllSheet().size(); i++) {
				reportId = ((Sheet) (reports.getAllSheet().get(i))).getId();
				for(int n=0;n<statReports.length;n++){
					if(statReports[n].equals(reportId)){
						reportName = ((Sheet) (reports.getAllSheet().get(i))).getName();
						//用报表名称替换报表id
						statReportStr = statReportStr.replace(statReports[n], reportName);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statReportStr;
	}
}
