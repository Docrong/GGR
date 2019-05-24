package com.boco.eoms.commons.statistic.commonfault.vo;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;

public class CommonfaultDeplyKpi3DetailVO extends StatDetailVO {
	
	private String sheetid;
	private String mainnetsortone;
    
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
	public String getSheetid() {
		return sheetid;
	}
	public void setSheetid(String sheetid) {
		this.sheetid = sheetid;
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
	public String getMainnetsortone() {
		return mainnetsortone;
	}
	public void setMainnetsortone(String mainnetsortone) {
		this.mainnetsortone = mainnetsortone;
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
