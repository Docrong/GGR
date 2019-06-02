package com.boco.eoms.commons.statistic.base.excelutil.mgr.impl;

public class DyTableInfo {
	
	private String[] headNames;// = {"第一列","及时数","超时数"};//表头文字信息
	
	private String[] bodyNames;// = {"stat1","stat2","stat3"};//表体配置信息
	
	private String[] footNames;// = {"stat1","stat2","stat3"};//表尾配置信息
	
	private String id;
	
	public DyTableInfo(){}
	
	public DyTableInfo(String id ,String[] headNames,String[] bodyNames,String[] footNames)
	{
		this.id=id;
		this.headNames=headNames;
		this.bodyNames=bodyNames;
		this.footNames=footNames;
	}

	public String[] getHeadNames() {
		return headNames;
	}

	public void setHeadNames(String[] headNames) {
		this.headNames = headNames;
	}

	public String[] getBodyNames() {
		return bodyNames;
	}

	public void setBodyNames(String[] bodyNames) {
		this.bodyNames = bodyNames;
	}

	public String[] getFootNames() {
		return footNames;
	}

	public void setFootNames(String[] footNames) {
		this.footNames = footNames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
