package com.boco.eoms.commons.mms.base.config;

public class Sheet {

	private String id = null;
	
	private String index = null;
	
	private String name = null;
	
	private String type = "stat";
	
	private String excelPath = null;
	
	private String queryPath = null;
	
	private Foot foot = null;

	public Foot getFoot() {
		return foot;
	}

	public void setFoot(Foot foot) {
		this.foot = foot;
	}

	public String getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}

	public String getQueryPath() {
		return queryPath;
	}

	public void setQueryPath(String queryPath) {
		this.queryPath = queryPath;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	
	
}
