package com.boco.eoms.commons.statistic.commonstat.vo;

import java.util.Date;

public class StatDetailVO {

	private String sheetid;
	private String title;
	private String senduserid;
	private String senddeptid;
	private String status;
	private Date sendtime;
	
	
	private String completeflag;
	
	private String mainid="";
	

	/**
	 * @return the mainid
	 */
	public String getMainid() {
		return mainid;
	}

	/**
	 * @param mainid the mainid to set
	 */
	public void setMainid(String mainid) {
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
