package com.boco.eoms.commons.statistic.task.model;

import java.io.Serializable;
import java.util.Date;

public class EstTask implements Serializable {

	private String mainid;
	private String linkid;
	private Date nodeacceptlimit;// DATETIME YEAR to SECOND
	private Date nodecompletelimit;// DATETIME YEAR to SECOND
	private Date operatetime;// DATETIME YEAR to SECOND
	private String operateuserid;// VARCHAR(30)
	private String operatedeptid;// VARCHAR(100)
	private String operateroleid;// VARCHAR(32)
	private int operateorgtype;// INTEGER
	private int toorgtype;// INTEGER
	private String toorgdeptid;// VARCHAR(32)
	private String toorguserid;// VARCHAR(50)
	private String toroleid;// CHAR(1000)
	private int completeflag;// INTEGER
	private Date completetime;// DATETIME YEAR to SECOND
	private int completeusetime;// INTEGER
	private String prelinkid;// VARCHAR(50)
	private String activitydefid;// VARCHAR(30)
	private double workitemid;// DECIMAL(180)
	private double activityinstid;// DECIMAL(18,0)
	private int operatetype;// INTEGER
	private String sheetid;// VARCHAR(50)
	private String title;// VARCHAR(100)
	private Date sheetcompletelimit;// DATETIME YEAR to SECOND
	private Date sendtime;// DATETIME YEAR to SECOND
	private int status;// INTEGER
	private int holdstatisfied;// INTEGER
	private String senduserid;//           VARCHAR(30) DEFAULT 0,
	private String senddeptid ;//          VARCHAR(32) DEFAULT 0,
	private String sendroleid ;//          VARCHAR(32) DEFAULT 0,
	private Date endtime     ;//         DATETIME YEAR TO SECOND DEFAULT 0,
	private String endresult ;//           VARCHAR(30) DEFAULT 0,
	private String enduserid ;//           VARCHAR(50) DEFAULT 0,
	private String enddeptid ;//           VARCHAR(32) DEFAULT 0,
	private String endroleid ;//           VARCHAR(32) DEFAULT 0,
	private int deleted;// INTEGER
	private String sheettype;// VARCHAR(50)
	private String orgid;// VARCHAR(64)
	private int orgtype;// INTEGER
	private String mainapptime;// VARCHAR(50)
	private String mainmajorid;// VARCHAR(50)
	private String mainapptype;// VARCHAR(50)
	private String mainapplycause;// VARCHAR(50)
	private String maindescription;// CHAR(1000)
	private String linkauditopinion;// VARCHAR(255)
	private String linkpassopinion;// VARCHAR(255)
	private String linkispass;// VARCHAR(10)
	private String issongshen;// VARCHAR(10)
	private String linkreplycontent;// CHAR(1000)
	private String linkmemo;// CHAR(1000)
	private String linkreason;// CHAR(1000)
	private int linkstatus;// INTEGER
	private String deptlevel;// VARCHAR(30)

	public String getMainid() {
		return mainid;
	}
	public void setMainid(String mainid) {
		this.mainid = mainid;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public Date getNodeacceptlimit() {
		return nodeacceptlimit;
	}
	public void setNodeacceptlimit(Date nodeacceptlimit) {
		this.nodeacceptlimit = nodeacceptlimit;
	}
	public Date getNodecompletelimit() {
		return nodecompletelimit;
	}
	public void setNodecompletelimit(Date nodecompletelimit) {
		this.nodecompletelimit = nodecompletelimit;
	}
	public Date getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	public String getOperateuserid() {
		return operateuserid;
	}
	public void setOperateuserid(String operateuserid) {
		this.operateuserid = operateuserid;
	}
	public String getOperatedeptid() {
		return operatedeptid;
	}
	public void setOperatedeptid(String operatedeptid) {
		this.operatedeptid = operatedeptid;
	}
	public String getOperateroleid() {
		return operateroleid;
	}
	public void setOperateroleid(String operateroleid) {
		this.operateroleid = operateroleid;
	}
	public int getOperateorgtype() {
		return operateorgtype;
	}
	public void setOperateorgtype(int operateorgtype) {
		this.operateorgtype = operateorgtype;
	}
	public int getToorgtype() {
		return toorgtype;
	}
	public void setToorgtype(int toorgtype) {
		this.toorgtype = toorgtype;
	}
	public String getToorgdeptid() {
		return toorgdeptid;
	}
	public void setToorgdeptid(String toorgdeptid) {
		this.toorgdeptid = toorgdeptid;
	}
	public String getToorguserid() {
		return toorguserid;
	}
	public void setToorguserid(String toorguserid) {
		this.toorguserid = toorguserid;
	}
	public String getToroleid() {
		return toroleid;
	}
	public void setToroleid(String toroleid) {
		this.toroleid = toroleid;
	}
	public int getCompleteflag() {
		return completeflag;
	}
	public void setCompleteflag(int completeflag) {
		this.completeflag = completeflag;
	}
	public Date getCompletetime() {
		return completetime;
	}
	public void setCompletetime(Date completetime) {
		this.completetime = completetime;
	}
	public int getCompleteusetime() {
		return completeusetime;
	}
	public void setCompleteusetime(int completeusetime) {
		this.completeusetime = completeusetime;
	}
	public String getPrelinkid() {
		return prelinkid;
	}
	public void setPrelinkid(String prelinkid) {
		this.prelinkid = prelinkid;
	}
	public String getActivitydefid() {
		return activitydefid;
	}
	public void setActivitydefid(String activitydefid) {
		this.activitydefid = activitydefid;
	}
	public double getWorkitemid() {
		return workitemid;
	}
	public void setWorkitemid(double workitemid) {
		this.workitemid = workitemid;
	}
	public double getActivityinstid() {
		return activityinstid;
	}
	public void setActivityinstid(double activityinstid) {
		this.activityinstid = activityinstid;
	}
	public int getOperatetype() {
		return operatetype;
	}
	public void setOperatetype(int operatetype) {
		this.operatetype = operatetype;
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
	public Date getSheetcompletelimit() {
		return sheetcompletelimit;
	}
	public void setSheetcompletelimit(Date sheetcompletelimit) {
		this.sheetcompletelimit = sheetcompletelimit;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getHoldstatisfied() {
		return holdstatisfied;
	}
	public void setHoldstatisfied(int holdstatisfied) {
		this.holdstatisfied = holdstatisfied;
	}
	public String getSenduserid() {
		return senduserid;
	}
	public void setSenduserid(String senduserid) {
		this.senduserid = senduserid;
	}
	public String getSenddeptid() {
		return senddeptid;
	}
	public void setSenddeptid(String senddeptid) {
		this.senddeptid = senddeptid;
	}
	public String getSendroleid() {
		return sendroleid;
	}
	public void setSendroleid(String sendroleid) {
		this.sendroleid = sendroleid;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getEndresult() {
		return endresult;
	}
	public void setEndresult(String endresult) {
		this.endresult = endresult;
	}
	public String getEnduserid() {
		return enduserid;
	}
	public void setEnduserid(String enduserid) {
		this.enduserid = enduserid;
	}
	public String getEnddeptid() {
		return enddeptid;
	}
	public void setEnddeptid(String enddeptid) {
		this.enddeptid = enddeptid;
	}
	public String getEndroleid() {
		return endroleid;
	}
	public void setEndroleid(String endroleid) {
		this.endroleid = endroleid;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getSheettype() {
		return sheettype;
	}
	public void setSheettype(String sheettype) {
		this.sheettype = sheettype;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public int getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(int orgtype) {
		this.orgtype = orgtype;
	}
	public String getMainapptime() {
		return mainapptime;
	}
	public void setMainapptime(String mainapptime) {
		this.mainapptime = mainapptime;
	}
	public String getMainmajorid() {
		return mainmajorid;
	}
	public void setMainmajorid(String mainmajorid) {
		this.mainmajorid = mainmajorid;
	}
	public String getMainapptype() {
		return mainapptype;
	}
	public void setMainapptype(String mainapptype) {
		this.mainapptype = mainapptype;
	}
	public String getMainapplycause() {
		return mainapplycause;
	}
	public void setMainapplycause(String mainapplycause) {
		this.mainapplycause = mainapplycause;
	}
	public String getMaindescription() {
		return maindescription;
	}
	public void setMaindescription(String maindescription) {
		this.maindescription = maindescription;
	}
	public String getLinkauditopinion() {
		return linkauditopinion;
	}
	public void setLinkauditopinion(String linkauditopinion) {
		this.linkauditopinion = linkauditopinion;
	}
	public String getLinkpassopinion() {
		return linkpassopinion;
	}
	public void setLinkpassopinion(String linkpassopinion) {
		this.linkpassopinion = linkpassopinion;
	}
	public String getLinkispass() {
		return linkispass;
	}
	public void setLinkispass(String linkispass) {
		this.linkispass = linkispass;
	}
	public String getIssongshen() {
		return issongshen;
	}
	public void setIssongshen(String issongshen) {
		this.issongshen = issongshen;
	}
	public String getLinkreplycontent() {
		return linkreplycontent;
	}
	public void setLinkreplycontent(String linkreplycontent) {
		this.linkreplycontent = linkreplycontent;
	}
	public String getLinkmemo() {
		return linkmemo;
	}
	public void setLinkmemo(String linkmemo) {
		this.linkmemo = linkmemo;
	}
	public String getLinkreason() {
		return linkreason;
	}
	public void setLinkreason(String linkreason) {
		this.linkreason = linkreason;
	}
	public int getLinkstatus() {
		return linkstatus;
	}
	public void setLinkstatus(int linkstatus) {
		this.linkstatus = linkstatus;
	}
	public String getDeptlevel() {
		return deptlevel;
	}
	public void setDeptlevel(String deptlevel) {
		this.deptlevel = deptlevel;
	}

}
