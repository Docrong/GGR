package com.boco.eoms.sheet.limit.util;

import java.util.List;

public class TimeWorktime {
	private String type;
	private String cumulativeType;
	private List workTime;
	private List dayOff;
	
	public List getDayOff() {
		return dayOff;
	}
	public void setDayOff(List dayOff) {
		this.dayOff = dayOff;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List getWorkTime() {
		return workTime;
	}
	public void setWorkTime(List workTime) {
		this.workTime = workTime;
	}
	public String getCumulativeType() {
		return cumulativeType;
	}
	public void setCumulativeType(String cumulativeType) {
		this.cumulativeType = cumulativeType;
	}
}
