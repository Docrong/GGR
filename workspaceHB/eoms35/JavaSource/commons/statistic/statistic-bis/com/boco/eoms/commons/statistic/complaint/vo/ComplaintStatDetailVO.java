package com.boco.eoms.commons.statistic.complaint.vo;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;
public class ComplaintStatDetailVO extends StatDetailVO {
	
	private String overtimeDept="及时";
	private String complainttype;
	
	private String todeptid;
	
	private String sendroleid;
	
	private String endtime;

	public String getComplainttype() {
		return complainttype;
	}

	public void setComplainttype(String complainttype) {
		this.complainttype = complainttype;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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

	public String getOvertimeDept() {
		return overtimeDept;
	}

	public void setOvertimeDept(String overtimeDept) {
		this.overtimeDept = overtimeDept;
	}

}
