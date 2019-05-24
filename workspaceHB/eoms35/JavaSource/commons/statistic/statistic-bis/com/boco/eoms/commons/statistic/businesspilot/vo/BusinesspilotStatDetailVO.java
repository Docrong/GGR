package com.boco.eoms.commons.statistic.businesspilot.vo;

import java.util.Date;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;
public class BusinesspilotStatDetailVO extends StatDetailVO {
	private String mainProductCode;
	
	private Date mainEndTime;
	
	private Date endTime;

	public String getMainProductCode() {
		return mainProductCode;
	}

	public void setMainProductCode(String mainProductCode) {
		this.mainProductCode = mainProductCode;
	}

	public Date getMainEndTime() {
		return mainEndTime;
	}

	public void setMainEndTime(Date mainEndTime) {
		this.mainEndTime = mainEndTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
