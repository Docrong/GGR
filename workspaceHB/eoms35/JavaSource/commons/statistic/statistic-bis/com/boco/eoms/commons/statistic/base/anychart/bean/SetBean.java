package com.boco.eoms.commons.statistic.base.anychart.bean;

public class SetBean {
	
	private String piecount = "";//饼图统计值
	private String color = "";
	private String value = "";
	private String argument = "";
	private String name = "";
	private String fdid = "";
	
	private String blockname = "";
	
	public SetBean(){
		
	}	
	public String getBlockname() {
		return blockname;
	}
	public void setBlockname(String blockname) {
		this.blockname = blockname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getArgument() {
		return argument;
	}
	public void setArgument(String argument) {
		this.argument = argument;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPiecount() {
		return piecount;
	}
	public void setPiecount(String piecount) {
		this.piecount = piecount;
	}
	public String getFdid() {
		return fdid;
	}
	public void setFdid(String fdid) {
		this.fdid = fdid;
	}
	
	
}
