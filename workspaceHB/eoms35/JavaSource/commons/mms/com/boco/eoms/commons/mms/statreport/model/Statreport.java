package com.boco.eoms.commons.mms.statreport.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:报表实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 11:35:28 CST 2009
 * </p>
 * 
 * @author 李振友
 * @version 3.5
 * 
 */
public class Statreport extends BaseObject {

	/**
	 * 主键
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * 彩信报模板id
	 *
	 */
	private java.lang.String mmsreport_template_id;
   
	public void setMmsreport_template_id(java.lang.String mmsreport_template_id){
		this.mmsreport_template_id= mmsreport_template_id;       
	}
   
	public java.lang.String getMmsreport_template_id(){
		return this.mmsreport_template_id;
	}
	
	/**
	 * 彩信报实例id
	 */
	private String mmsreport_id;

	public String getMmsreport_id() {
		return mmsreport_id;
	}

	public void setMmsreport_id(String mmsreport_id) {
		this.mmsreport_id = mmsreport_id;
	}
	
	/**
	 * 报表类型 月，周，日
	 */
	private String reportType;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 报表名称
	 */
	private String reportName;
	
	/**
	 *
	 * 报表Excel文件保存的路径名称
	 *
	 */
	private java.lang.String ExcelID;
   
	public void setExcelID(java.lang.String ExcelID){
		this.ExcelID= ExcelID;       
	}
   
	public java.lang.String getExcelID(){
		return this.ExcelID;
	}

	/**
	 *
	 * 报表图片文件保存的路径名称
	 *
	 */
	private java.lang.String picID;
   
	public void setPicID(java.lang.String picID){
		this.picID= picID;       
	}
   
	public java.lang.String getPicID(){
		return this.picID;
	}
	/**
	 * 报表创建人
	 * 
	 */
	private java.lang.String userid;
	   
	public void setUserid(java.lang.String userid){
		this.userid= userid;       
	}
   
	public java.lang.String getUserid(){
		return this.userid;
	}
	/**
	 *
	 * 报表html片段文件保存的路径名称
	 *
	 */
	private java.lang.String htmlID;
   
	public void setHtmlID(java.lang.String htmlID){
		this.htmlID= htmlID;       
	}
   
	public java.lang.String getHtmlID(){
		return this.htmlID;
	}
	
	/**
	 * 表尾信息
	 */
	private java.lang.String footInfo;
	
	public java.lang.String getFootInfo() {
		return footInfo;
	}

	public void setFootInfo(java.lang.String footInfo) {
		this.footInfo = footInfo;
	}

	public boolean equals(Object o) {
		if( o instanceof Statreport ) {
			Statreport statreport=(Statreport)o;
			if (this.id != null || this.id.equals(statreport.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}