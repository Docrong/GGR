package com.boco.eoms.commons.statistic.base.config.model.report;


public class ReportDefine {
	private String name;
	private String caption;
	private String kpiDefineName;
	
	private String useJspHead="";
	private int viewRowSpanMax;
	
	private boolean enableSummarySpan = true;
	private ReportSummary[] reportSummaries;
	private ReportField[] reportFields;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public boolean isEnableSummarySpan() {
		return enableSummarySpan;
	}
	public void setEnableSummarySpan(boolean enableSummarySpan) {
		this.enableSummarySpan = enableSummarySpan;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ReportField[] getReportFields() {
		return reportFields;
	}
	public void setReportFields(ReportField[] reportFields) {
		this.reportFields = reportFields;
	}
	public ReportSummary[] getReportSummaries() {
		return reportSummaries;
	}
	public void setReportSummaries(ReportSummary[] reportSummaries) {
		this.reportSummaries = reportSummaries;
	}
	public int getViewRowSpanMax() {
		return viewRowSpanMax;
	}
	public void setViewRowSpanMax(int viewRowSpanMax) {
		this.viewRowSpanMax = viewRowSpanMax;
	}
	public String getKpiDefineName() {
		return kpiDefineName;
	}
	public void setKpiDefineName(String kpiDefineName) {
		this.kpiDefineName = kpiDefineName;
	}
	public String getUseJspHead() {
		return useJspHead;
	}
	public void setUseJspHead(String useJspHead) {
		this.useJspHead = useJspHead;
	}


	
	
}
