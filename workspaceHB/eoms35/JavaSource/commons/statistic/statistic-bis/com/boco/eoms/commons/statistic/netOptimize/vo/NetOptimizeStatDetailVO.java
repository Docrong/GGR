package com.boco.eoms.commons.statistic.netOptimize.vo;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;

public class NetOptimizeStatDetailVO extends StatDetailVO {
	
	private String overtimeDept="及时";

	private String mainid;
	
	private String sheetid;
	
	private String title;
	
	private String senddeptid;
	
	private String operateuserid;
	
	private String endtime;

	public String getOvertimeDept() {
		return overtimeDept;
	}

	public void setOvertimeDept(String overtimeDept) {
		this.overtimeDept = overtimeDept;
	}

	public String getMainid() {
		return mainid;
	}

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

	public String getSenddeptid() {
		return senddeptid;
	}

	public void setSenddeptid(String senddeptid) {
		this.senddeptid = senddeptid;
	}

	public String getOperateuserid() {
		return operateuserid;
	}

	public void setOperateuserid(String operateuserid) {
		this.operateuserid = operateuserid;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	

}
