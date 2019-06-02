package com.boco.eoms.commons.statistic.customstat.config;

import java.io.Serializable;

public class CustomConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 84032413362061080L;

	private ReportConfig reportConfig = null;

	public ReportConfig getReportConfig() {
		return reportConfig;
	}

	public void setReportConfig(ReportConfig reportConfig) {
		this.reportConfig = reportConfig;
	}
	
}
