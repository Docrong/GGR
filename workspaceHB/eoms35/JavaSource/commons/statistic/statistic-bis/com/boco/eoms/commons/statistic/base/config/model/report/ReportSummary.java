package com.boco.eoms.commons.statistic.base.config.model.report;

public class ReportSummary {
	private String id;

	private int sequence;
	
	private String viewName;

	private String viewWidth;

	private String codeToName;

	public String getCodeToName() {
		return codeToName;
	}

	public void setCodeToName(String codeToName) {
		this.codeToName = codeToName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(String viewWidth) {
		this.viewWidth = viewWidth;
	}
}
