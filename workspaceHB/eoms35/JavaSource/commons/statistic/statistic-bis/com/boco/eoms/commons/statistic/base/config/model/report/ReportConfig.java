package com.boco.eoms.commons.statistic.base.config.model.report;


public class ReportConfig {
	private String title;
	
	private ReportDefine[] reportDefines;
	
	public ReportDefine getReportDefineByReportName(String reportName)
			throws Exception {
		ReportDefine reportDefine = null;
		boolean isDefined = false;
		for (int i = 0; i < reportDefines.length; i++) {
			if (reportDefines[i].getName().equals(reportName)) {
				if (isDefined == true) {
					throw new Exception("重复定义report-Name!");
				}
				isDefined = true;
				reportDefine = reportDefines[i];
			}
		}
		if (isDefined == false) {
			throw new Exception("没有定义report-Name!");
		}

		return reportDefine;
	}


	public ReportDefine[] getReportDefines() {
		return reportDefines;
	}
	public void setReportDefines(ReportDefine[] reportDefines) {
		this.reportDefines = reportDefines;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
