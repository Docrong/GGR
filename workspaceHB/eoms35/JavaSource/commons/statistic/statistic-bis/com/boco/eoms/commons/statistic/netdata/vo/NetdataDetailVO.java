package com.boco.eoms.commons.statistic.netdata.vo;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;

public class NetdataDetailVO extends StatDetailVO {
	
	private String mainNetSortOne;

	private String todeptid;
	private String sendroleid;
	private String operateroleid;
	private String senduserid;
	private String operateuserid;
	private String operatetime;

	public String getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	public String getTodeptid() {
		return todeptid;
	}
	public void setTodeptid(String todeptid) {
		this.todeptid = todeptid;
	}
	public String getSendroleid() {
		return sendroleid;
	}
	public void setSendroleid(String sendroleid) {
		this.sendroleid = sendroleid;
	}
	public String getOperateroleid() {
		return operateroleid;
	}
	public void setOperateroleid(String operateroleid) {
		this.operateroleid = operateroleid;
	}
	
	
	public String getMainNetSortOne() {
		return mainNetSortOne;
	}
	public void setMainNetSortOne(String mainNetSortOne) {
		this.mainNetSortOne = mainNetSortOne;
	}
	public String getSenduserid() {
		return senduserid;
	}
	public void setSenduserid(String senduserid) {
		this.senduserid = senduserid;
	}
	public String getOperateuserid() {
		return operateuserid;
	}
	public void setOperateuserid(String operateuserid) {
		this.operateuserid = operateuserid;
	}
	


	

}
