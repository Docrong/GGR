package com.boco.eoms.commons.statistic.commonfault.vo;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;


public class CommonfaultDetailVO extends StatDetailVO{
	
	private String overtimeDept="及时";
	
	private String operatetime;
	
	private String mainnetsortone;
	
	private String todeptid;
	
	private String sendroleid;
	
	
	
	//private String deptlevel;
	
	//private String completeflag;
	
	//private int mainid=0;
	

	/**
	 * @return the mainid
	 */
//	public int getMainid() {
//		return mainid;
//	}
//
//	/**
//	 * @param mainid the mainid to set
//	 */
//	public void setMainid(int mainid) {
//		this.mainid = mainid;
//	}
//
//	/**
//	 * @return the deptlevel
//	 */
//	public String getDeptlevel() {
//		return deptlevel;
//	}
//
//	/**
//	 * @param deptlevel the deptlevel to set
//	 */
//	public void setDeptlevel(String deptlevel) {
//		this.deptlevel = deptlevel;
//	}


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

	public String getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	public String getMainnetsortone() {
		return mainnetsortone;
	}

	public void setMainnetsortone(String mainnetsortone) {
		this.mainnetsortone = mainnetsortone;
	}

	public String getSendroleid() {
		return sendroleid;
	}

	public void setSendroleid(String sendroleid) {
		this.sendroleid = sendroleid;
	}

	public String getTodeptid() {
		return todeptid;
	}

	public void setTodeptid(String todeptid) {
		this.todeptid = todeptid;
	}

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Date getSendtime() {
//		return sendtime;
//	}
//
//	public void setSendtime(Date sendtime) {
//		this.sendtime = sendtime;
//	}

	/**
	 * @return the completeflag
	 */
//	public String getCompleteflag() {
//		return completeflag;
//	}
//
//	/**
//	 * @param completeflag the completeflag to set
//	 */
//	public void setCompleteflag(String completeflag) {
//		this.completeflag = completeflag;
//	}
}
