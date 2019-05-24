package com.boco.eoms.commons.statistic.customstat.config;

import java.io.Serializable;

public class ReportConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217464796811782577L;
	
	private Report[] Reports = null;

	public Report[] getReports() {
		return Reports;
	}

	public void setReports(Report[] reports) {
		Reports = reports;
	}
}
