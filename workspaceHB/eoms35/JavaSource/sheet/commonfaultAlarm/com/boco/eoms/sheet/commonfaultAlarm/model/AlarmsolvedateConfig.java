package com.boco.eoms.sheet.commonfaultAlarm.model;

import java.io.Serializable;
import java.util.Date;

public class AlarmsolvedateConfig implements Serializable {
	private String id;

	private String alarmnum;          
	private String sheetkey ;         
	private Date mainalarmsolvedate;
	private Date acceptlimit ;      
	private String owneruser ;       
	private String ownerdept;         
	private String status   ;
	private String issended ;         
	public String getAlarmnum() {
		return alarmnum;
	}
	public void setAlarmnum(String alarmnum) {
		this.alarmnum = alarmnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getMainalarmsolvedate() {
		return mainalarmsolvedate;
	}
	public void setMainalarmsolvedate(Date mainalarmsolvedate) {
		this.mainalarmsolvedate = mainalarmsolvedate;
	}
	public String getOwnerdept() {
		return ownerdept;
	}
	public void setOwnerdept(String ownerdept) {
		this.ownerdept = ownerdept;
	}
	public String getOwneruser() {
		return owneruser;
	}
	public void setOwneruser(String owneruser) {
		this.owneruser = owneruser;
	}
	public String getSheetkey() {
		return sheetkey;
	}
	public void setSheetkey(String sheetkey) {
		this.sheetkey = sheetkey;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getAcceptlimit() {
		return acceptlimit;
	}
	public void setAcceptlimit(Date acceptlimit) {
		this.acceptlimit = acceptlimit;
	}
	public String getIssended() {
		return issended;
	}
	public void setIssended(String issended) {
		this.issended = issended;
	}  
}
