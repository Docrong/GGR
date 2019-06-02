package com.boco.eoms.sheet.daiweiindexreduction.model;

public class dwcommonfaulthj {
	/**
	 * 自增长主键
	 */
	private String id;
	
	private String mainid;
	private String sheetid;


	private String creater;
	private String sendyear;
	private String sendmonth;
	private String sendday;
	private String remark;
	private String subtracttype;
	private java.util.Date savetime;

	private java.util.Date updatetime;
	
	public java.util.Date getupdatetime() {
		return updatetime;
	}
	public void setupdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	public java.util.Date getsavetime() {
		return savetime;
	}
	public void setsavetime(java.util.Date savetime) {
		this.savetime = savetime;
	}
	
	public String getsubtracttype() {
		return subtracttype;
	}
	public void setsubtracttype(String subtracttype) {
		this.subtracttype = subtracttype;
	}
	public String getsendday() {
		return sendday;
	}
	public void setsendday(String sendday) {
		this.sendday = sendday;
	}
	public String getsendmonth() {
		return sendmonth;
	}
	public void setsendmonth(String sendmonth) {
		this.sendmonth = sendmonth;
	}
	public String getsendyear() {
		return sendyear;
	}
	public void setsendyear(String sendyear) {
		this.sendyear = sendyear;
	}
	public String getcreater() {
		return creater;
	}
	public void setcreater(String creater) {
		this.creater = creater;
	}
	public String getsheetid() {
		return sheetid;
	}
	public void setsheetid(String sheetid) {
		this.sheetid = sheetid;
	}
	public String getmainid() {
		return mainid;
	}
	public void setmainid(String mainid) {
		this.mainid = mainid;
	}
	public String getremark() {
		return remark;
	}
	public void setremark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
