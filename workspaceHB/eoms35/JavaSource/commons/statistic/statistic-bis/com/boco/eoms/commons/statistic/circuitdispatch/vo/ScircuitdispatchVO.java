package com.boco.eoms.commons.statistic.circuitdispatch.vo;

import java.util.Date;

public class ScircuitdispatchVO {
	private String sheetid;
	private String title;
	private String senduserid;
	private String senddeptid;
	private String status;
	private Date sendtime;
	private String completeflag;
	private int mainid=0;
	private String endtime;
	private String operateuserid;
	private String operateroleid;
	
	
	
	/**
	 * @return the operateroleid
	 */
	public String getOperateroleid() {
		return operateroleid;
	}

	/**
	 * @param operateroleid the operateroleid to set
	 */
	public void setOperateroleid(String operateroleid) {
		this.operateroleid = operateroleid;
	}

	/**
	 * @return the operateuserid
	 */
	public String getOperateuserid() {
		return operateuserid;
	}

	/**
	 * @param operateuserid the operateuserid to set
	 */
	public void setOperateuserid(String operateuserid) {
		this.operateuserid = operateuserid;
	}

	/**
	 * @return the endtime
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the mainid
	 */
	public int getMainid() {
		return mainid;
	}

	/**
	 * @param mainid the mainid to set
	 */
	public void setMainid(int mainid) {
		this.mainid = mainid;
	}

	public String getSheetid() {
		return sheetid;
	}

	public void setSheetid(String sheetid) {
		this.sheetid = sheetid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSenduserid() {
		return senduserid;
	}

	public void setSenduserid(String senduserid) {
		this.senduserid = senduserid;
	}

	public String getSenddeptid() {
		return senddeptid;
	}

	public void setSenddeptid(String senddeptid) {
		this.senddeptid = senddeptid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	/**
	 * @return the completeflag
	 */
	public String getCompleteflag() {
		return completeflag;
	}

	/**
	 * @param completeflag the completeflag to set
	 */
	public void setCompleteflag(String completeflag) {
		this.completeflag = completeflag;
	}
}
