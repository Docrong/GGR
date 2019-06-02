package com.boco.eoms.commons.statistic.circuitdispatch.vo;

import java.util.Date;

public class StatDetailVO {

	private String sheetid;
	private String title;
	private Date linkExecuteEndDate;
	private Date endtime;
	private String operateuserid;
	private int mainid=0;
	public Date getLinkExecuteEndDate() {
		return linkExecuteEndDate;
	}
	public void setLinkExecuteEndDate(Date linkExecuteEndDate) {
		this.linkExecuteEndDate = linkExecuteEndDate;
	}
	public int getMainid() {
		return mainid;
	}
	public void setMainid(int mainid) {
		this.mainid = mainid;
	}
	
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getOperateuserid() {
		return operateuserid;
	}
	public void setOperateuserid(String operateuserid) {
		this.operateuserid = operateuserid;
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
	
	
}
