package com.boco.eoms.commons.statistic.customstat.model;

import com.boco.eoms.base.model.BaseObject;

public class CustomstatRemind extends BaseObject{
	
	private String id;    //订阅发送模型唯一标识
	
	private String subId;   //定制表SUB_ID
	private String statName;//定制表名称
	
	private String statType;   //报表类型
	private String year;    //报表生成年份
	private String quarter; //报表生成季度
	private String month;   //报表生成月份
	private String date;    //报表生成日期
	private String week;    //报表生成周
	
	private String saveUser;  //接收人
	
	private String acceptDate;//接受时间
	
	private String excelUrl;  //报表地址
	
	private String accepttype;    //接收提醒方式
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getSaveUser() {
		return saveUser;
	}

	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}

	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getExcelUrl() {
		return excelUrl;
	}

	public void setExcelUrl(String excelUrl) {
		this.excelUrl = excelUrl;
	}

	public String getAccepttype() {
		return accepttype;
	}

	public void setAccepttype(String accepttype) {
		this.accepttype = accepttype;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
