package com.boco.eoms.commons.statistic.flowcontrol.vo;

import java.util.Date;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;

public class FlowcontrolDetailVO extends StatDetailVO {
	private String sheettype;
	private Date completetimelimit;
//	   String sheetkey=vo.getSheetkey();
//	   String taskid=vo.getTaskid();
//	   String taskname=vo.getTaskname();
//	   String operateroleid=vo.getOperateroleid();
//	   String piid=vo.getPiid();
//	   String taskstatus=vo.getTaskstatus();
//	   String prelinkid=vo.getPrelinkid();
	
	private String sheetkey;
	private String taskid;
	private String taskname;
	private String operateroleid;
	private String piid;
	private String taskstatus;
	private String prelinkid;

	public Date getCompletetimelimit() {
		return completetimelimit;
	}

	public void setCompletetimelimit(Date completetimelimit) {
		this.completetimelimit = completetimelimit;
	}

	public String getSheettype() {
		return sheettype;
	}

	public void setSheettype(String sheettype) {
		this.sheettype = sheettype;
	}

	public String getSheetkey() {
		return sheetkey;
	}

	public void setSheetkey(String sheetkey) {
		this.sheetkey = sheetkey;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getOperateroleid() {
		return operateroleid;
	}

	public void setOperateroleid(String operateroleid) {
		this.operateroleid = operateroleid;
	}

	public String getPiid() {
		return piid;
	}

	public void setPiid(String piid) {
		this.piid = piid;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public String getPrelinkid() {
		return prelinkid;
	}

	public void setPrelinkid(String prelinkid) {
		this.prelinkid = prelinkid;
	}
	
}
