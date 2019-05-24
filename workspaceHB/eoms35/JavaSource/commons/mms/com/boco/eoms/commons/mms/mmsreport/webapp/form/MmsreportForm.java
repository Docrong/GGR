package com.boco.eoms.commons.mms.mmsreport.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:彩信报实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 18:16:20 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李振友
 * @moudle.getVersion() 3.5
 * 
 */
public class MmsreportForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
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
	 * 彩信报名称
	 *
	 */
	private java.lang.String mmsReportName;
   
	public void setMmsReportName(java.lang.String mmsReportName){
		this.mmsReportName= mmsReportName;       
	}
   
	public java.lang.String getMmsReportName(){
		return this.mmsReportName;
	}
	/**
	 * 发送状态
	 */
	private String sendStatus;
	
	/**
	 * 发送时间
	 */
	
	private String sendTime;
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
	 * 彩信报创建人
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
	 * 彩信报类型
	 * 
	 */
	private java.lang.String mmsreportType;
	   
	public void setMmsreportType(java.lang.String mmsreportType){
		this.mmsreportType= mmsreportType;       
	}
   
	public java.lang.String getMmsreportType(){
		return this.mmsreportType;
	}
	
	/**
	 *
	 * 报表的id
	 *
	 */
	private java.lang.String statreport_id;
   
	public void setStatreport_id(java.lang.String statreport_id){
		this.statreport_id= statreport_id;       
	}
   
	public java.lang.String getStatreport_id(){
		return this.statreport_id;
	}

	/**
	 *
	 * 彩信报创建时间
	 *
	 */
	private java.lang.String mmsReportCreateDate;
   
	public void setMmsReportCreateDate(java.lang.String mmsReportCreateDate){
		this.mmsReportCreateDate= mmsReportCreateDate;       
	}
   
	public java.lang.String getMmsReportCreateDate(){
		return this.mmsReportCreateDate;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}