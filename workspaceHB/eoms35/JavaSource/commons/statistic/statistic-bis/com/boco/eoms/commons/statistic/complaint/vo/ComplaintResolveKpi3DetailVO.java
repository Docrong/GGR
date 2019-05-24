package com.boco.eoms.commons.statistic.complaint.vo;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;
public class ComplaintResolveKpi3DetailVO extends StatDetailVO {
	
	private String sheetid;
	private String complainttype1;
	
	private String todeptid;
	private String sendroleid;
	private String operateroleid;
	private String senduserid;
	private String operateuserid;
	private String maxoperatetime;
	private String operatetime;
	//private String mainNetSortOne;
	public String getMaxoperatetime() {
		return maxoperatetime;
	}
	public void setMaxoperatetime(String maxoperatetime) {
		this.maxoperatetime = maxoperatetime;
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
	
	public String getComplainttype1() {
		return complainttype1;
	}
	public void setComplainttype1(String complainttype1) {
		this.complainttype1 = complainttype1;
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
	public String getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	


	

}
