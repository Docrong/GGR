package com.boco.eoms.commons.statistic.urgentfault.vo;

import java.util.Date;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;

public class UrgentfaultIntimeDetailVO extends StatDetailVO {
	
	private String mainnetsortone;
	
	private String todeptid;
	private String senduserid;
	private String operateuserid;
	private String sendroleid;
	private String operateroleid;
	private Date mainreportlimit;
	private String linkFaultAvoidTime;
	
	public String getLinkFaultAvoidTime() {
		return linkFaultAvoidTime;
	}

	public void setLinkFaultAvoidTime(String linkFaultAvoidTime) {
		this.linkFaultAvoidTime = linkFaultAvoidTime;
	}

	public String getMainnetsortone() {
		return mainnetsortone;
	}

	public void setMainnetsortone(String mainnetsortone) {
		this.mainnetsortone = mainnetsortone;
	}

	public String getTodeptid() {
		return todeptid;
	}

	public void setTodeptid(String todeptid) {
		this.todeptid = todeptid;
	}

	public Date getMainreportlimit() {
		return mainreportlimit;
	}

	public void setMainreportlimit(Date mainreportlimit) {
		this.mainreportlimit = mainreportlimit;
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

}
