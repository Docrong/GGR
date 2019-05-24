package com.boco.eoms.extra.supplierkpi.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawSuppKpiInsMonomerQueryForm extends BaseForm implements
java.io.Serializable{
	protected String id;
	protected String fillStratTime;    //开始时间
	protected String fillEndTime;      //结束时间
	protected String year;             //年度
	protected String timeLatitude;     //月度 或 季度
	protected String month;            //月度
	protected String quarter;          //季度
	protected String manufacturerName;   //厂商Id
	protected String serviceType;      //服务类型
	protected String specialType;      //专业类型
	protected String isPass;           //审核状态
	protected String fillFlag;         //填写状态	


	public String getFillFlag() {
		return fillFlag;
	}
	public void setFillFlag(String fillFlag) {
		this.fillFlag = fillFlag;
	}
	public String getFillEndTime() {
		return fillEndTime;
	}
	public void setFillEndTime(String fillEndTime) {
		this.fillEndTime = fillEndTime;
	}
	public String getFillStratTime() {
		return fillStratTime;
	}
	public void setFillStratTime(String fillStratTime) {
		this.fillStratTime = fillStratTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
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

}
