package com.boco.eoms.extra.supplierkpi.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawSuppKpiInstReOrderQueryForm extends BaseForm implements
java.io.Serializable{
	protected String id;
	protected String manufacturerName; //厂商名称
	protected String reportName;       //报表名称
	protected String year;             //年度
	protected String timeLatitude;     //周期
	protected String month;            //月度
	protected String quarter;          //季度
	protected String serviceType;      //服务类型
	protected String specialType;      //专业类型
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	public String getTimeLatitude() {
		return timeLatitude;
	}
	public void setTimeLatitude(String timeLatitude) {
		this.timeLatitude = timeLatitude;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	




}
