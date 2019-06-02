package com.boco.eoms.duty.model;

import com.boco.eoms.base.model.BaseObject;

public class TawRmVisitRecordStat extends BaseObject {

	private String roomId; // 机房ID

	private String companyStat; // 来访者公司

	private String dutydate; // 值班日期

	private String timeDefined; // 值班时段

	private String dutymaster; // 值班长

	private String visiterCount; // 来访人数

	private String timeDefinedStart; // 值班起始时间

	private String timeDefinedEnd; // 值班终止时间

	public String getTimeDefinedEnd() {
		return timeDefinedEnd;
	}

	public void setTimeDefinedEnd(String timeDefinedEnd) {
		this.timeDefinedEnd = timeDefinedEnd;
	}

	public String getTimeDefinedStart() {
		return timeDefinedStart;
	}

	public void setTimeDefinedStart(String timeDefinedStart) {
		this.timeDefinedStart = timeDefinedStart;
	}

	public String getVisiterCount() {
		return visiterCount;
	}

	public void setVisiterCount(String visiterCount) {
		this.visiterCount = visiterCount;
	}

	public String getCompanyStat() {
		return companyStat;
	}

	public void setCompanyStat(String companyStat) {
		this.companyStat = companyStat;
	}

	public String getDutydate() {
		return dutydate;
	}

	public void setDutydate(String dutydate) {
		this.dutydate = dutydate;
	}

	public String getDutymaster() {
		return dutymaster;
	}

	public void setDutymaster(String dutymaster) {
		this.dutymaster = dutymaster;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getTimeDefined() {
		return timeDefined;
	}

	public void setTimeDefined(String timeDefined) {
		this.timeDefined = timeDefined;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
