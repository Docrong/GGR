package com.boco.eoms.crm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * 
 */
public class CrmWaitInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7731848434172310037L;

	private String id;

	private Integer sheetType;

	private Integer serviceType;

	private String serialNo;

	private Date sendtime;

	private Integer isSended;

	private String ndeptContact;

	private String ndeptContactPhone;

	private String compProp;

	private String isReplied;

	private String isCorrect;

	private String issueEliminatTime;

	private String affectedAreas;

	private String issueEliminatReason;

	private String dealDesc;

	private String dealResult;

	private String apnid;

	private String loginUserName;

	private String loginUserPassWord;

	private String netResCapacity;

	private String clientPgmCapacity;

	private String expectFinishdays;

	private String circuitCode;

	private String phaseReployDesc;

	private String isDeferReploy;

	private String opType;

	private String opDesc;

	private String interfaceType;

	private Date createTime;
	
	private String returnType;
	
	private String attachRef;

	public String getAffectedAreas() {
		return affectedAreas;
	}

	public void setAffectedAreas(String affectedAreas) {
		this.affectedAreas = affectedAreas;
	}

	public String getApnid() {
		return apnid;
	}

	public void setApnid(String apnid) {
		this.apnid = apnid;
	}

	public String getCircuitCode() {
		return circuitCode;
	}

	public void setCircuitCode(String circuitCode) {
		this.circuitCode = circuitCode;
	}

	public String getClientPgmCapacity() {
		return clientPgmCapacity;
	}

	public void setClientPgmCapacity(String clientPgmCapacity) {
		this.clientPgmCapacity = clientPgmCapacity;
	}

	public String getCompProp() {
		return compProp;
	}

	public void setCompProp(String compProp) {
		this.compProp = compProp;
	}

	public String getDealDesc() {
		return dealDesc;
	}

	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public String getExpectFinishdays() {
		return expectFinishdays;
	}

	public void setExpectFinishdays(String expectFinishdays) {
		this.expectFinishdays = expectFinishdays;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getIsDeferReploy() {
		return isDeferReploy;
	}

	public void setIsDeferReploy(String isDeferReploy) {
		this.isDeferReploy = isDeferReploy;
	}

	public String getIsReplied() {
		return isReplied;
	}

	public void setIsReplied(String isReplied) {
		this.isReplied = isReplied;
	}

	public Integer getIsSended() {
		return isSended;
	}

	public void setIsSended(Integer isSended) {
		this.isSended = isSended;
	}

	public String getIssueEliminatReason() {
		return issueEliminatReason;
	}

	public void setIssueEliminatReason(String issueEliminatReason) {
		this.issueEliminatReason = issueEliminatReason;
	}

	public String getIssueEliminatTime() {
		return issueEliminatTime;
	}

	public void setIssueEliminatTime(String issueEliminatTime) {
		this.issueEliminatTime = issueEliminatTime;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getLoginUserPassWord() {
		return loginUserPassWord;
	}

	public void setLoginUserPassWord(String loginUserPassWord) {
		this.loginUserPassWord = loginUserPassWord;
	}

	public String getNdeptContact() {
		return ndeptContact;
	}

	public void setNdeptContact(String ndeptContact) {
		this.ndeptContact = ndeptContact;
	}

	public String getNdeptContactPhone() {
		return ndeptContactPhone;
	}

	public void setNdeptContactPhone(String ndeptContactPhone) {
		this.ndeptContactPhone = ndeptContactPhone;
	}

	public String getNetResCapacity() {
		return netResCapacity;
	}

	public void setNetResCapacity(String netResCapacity) {
		this.netResCapacity = netResCapacity;
	}

	public String getOpDesc() {
		return opDesc;
	}

	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getPhaseReployDesc() {
		return phaseReployDesc;
	}

	public void setPhaseReployDesc(String phaseReployDesc) {
		this.phaseReployDesc = phaseReployDesc;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getSheetType() {
		return sheetType;
	}

	public void setSheetType(Integer sheetType) {
		this.sheetType = sheetType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getAttachRef() {
		return attachRef;
	}

	public void setAttachRef(String attachRef) {
		this.attachRef = attachRef;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

}
