package com.boco.eoms.commons.mms.base.config;

public class Report {
	
	private String id = null;
	
	private String type = null;
	
	private Sheet sheet[] = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Sheet[] getSheet() {
		return sheet;
	}

	public void setSheet(Sheet[] sheet) {
		this.sheet = sheet;
	}

}
