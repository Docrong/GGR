package com.boco.eoms.commons.statistic.base.config.model.report;

public class ReportField {
	private String id;
	
	private String expression;
	
	private String prefix; //前缀
	private String postfix; //后缀
	
	private int sequence;

	private String viewName;

	private String viewWidth;

	private int viewRowSpan;
	
	private int viewRowIndex;
	
	private int viewColumnSpan;

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

	public int getViewColumnSpan() {
		return viewColumnSpan;
	}

	public void setViewColumnSpan(int viewColumnSpan) {
		this.viewColumnSpan = viewColumnSpan;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public int getViewRowIndex() {
		return viewRowIndex;
	}

	public void setViewRowIndex(int viewRowIndex) {
		this.viewRowIndex = viewRowIndex;
	}

	public int getViewRowSpan() {
		return viewRowSpan;
	}

	public void setViewRowSpan(int viewRowSpan) {
		this.viewRowSpan = viewRowSpan;
	}

	public String getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(String viewWidth) {
		this.viewWidth = viewWidth;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
}
