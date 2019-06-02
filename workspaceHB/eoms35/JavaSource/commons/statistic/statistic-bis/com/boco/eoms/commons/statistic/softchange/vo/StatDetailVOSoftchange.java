package com.boco.eoms.commons.statistic.softchange.vo;

import java.util.Date;

public class StatDetailVOSoftchange {

	private String sheetid;
	private String title;
	private String senduserid;
	private String senddeptid;
	private String status;
	private Date sendtime;
	
	private String overtimeDept="及时";
	private String deptlevel;
	
	private String completeflag;
	
	private String mainid;
	

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

	/**
	 * @return the deptlevel
	 */
	public String getDeptlevel() {
		return deptlevel;
	}

	/**
	 * @param deptlevel the deptlevel to set
	 */
	public void setDeptlevel(String deptlevel) {
		this.deptlevel = deptlevel;
	}

	/**
	 * @return the overtimeDept
	 */
	public String getOvertimeDept() {
		return overtimeDept;
	}

	/**
	 * @param overtimeDept the overtimeDept to set
	 */
	public void setOvertimeDept(String overtimeDept) {
		this.overtimeDept = overtimeDept;
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
