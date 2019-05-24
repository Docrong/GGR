/*
 *
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.groupcomplaint.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import java.util.Date;


/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupComplaintMain extends BaseMain {

	private Date dealTime1;
	private Date dealTime2;		
	private String customNo;
	private String customName;
	private String customLevel;
	private String provinceName;
	private String cityName;
	private String countyName;
	private String bdeptContact;
	private String bdeptContactPhone;		
	private String cmanagerPhone;
	private String cmanagerContactPhone;
	private String customContact;
	private String customContactPhone;
	private String productName;
	private String urgentDegree;
    //预留字段
	private String bservType;		
	
	private String affectedAreas;
	private String customAttribution;
	private Date complaintTime;
	private String complaintNum;	
	private Date faultTime;
	private String complaintLoca;		
	private String termType;
	private String complaintDesc;
	private String preDealResult;		
	private String enterpriseCode;
	private String serverCode;				
	private String apnName;							
	private String circuitCode;
	private String ecsiType;		
	private String connectType;		

	private String mainInterfaceSheetType;
	private String mainIfDeferResolve;
	private Date mainCompleteLimitT1;
	private Date mainCompleteLimitT2;
	private String mainQcRejectTimes;
	private String mainIfRecord;
	private String mainDelayFlag;
	private Date mainLastRepeatTime;
	private String mainCheckResult;
	private String mainCheckIdea;
	private String mainIfCheck;
	
	
	//是否自动发现
	private String mainIfSelf;
	
	//是否关联上告警
	private String mainIfMatch;
	
	//关联故障工单号
	private String mainCommSheetId;
	
	public String getAffectedAreas() {
		return affectedAreas;
	}
	public void setAffectedAreas(String affectedAreas) {
		this.affectedAreas = affectedAreas;
	}
	public String getApnName() {
		return apnName;
	}
	public void setApnName(String apnName) {
		this.apnName = apnName;
	}
	public String getBdeptContact() {
		return bdeptContact;
	}
	public void setBdeptContact(String bdeptContact) {
		this.bdeptContact = bdeptContact;
	}
	public String getBdeptContactPhone() {
		return bdeptContactPhone;
	}
	public void setBdeptContactPhone(String bdeptContactPhone) {
		this.bdeptContactPhone = bdeptContactPhone;
	}
	public String getBservType() {
		return bservType;
	}
	public void setBservType(String bservType) {
		this.bservType = bservType;
	}
	public String getCircuitCode() {
		return circuitCode;
	}
	public void setCircuitCode(String circuitCode) {
		this.circuitCode = circuitCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCmanagerContactPhone() {
		return cmanagerContactPhone;
	}
	public void setCmanagerContactPhone(String cmanagerContactPhone) {
		this.cmanagerContactPhone = cmanagerContactPhone;
	}
	public String getCmanagerPhone() {
		return cmanagerPhone;
	}
	public void setCmanagerPhone(String cmanagerPhone) {
		this.cmanagerPhone = cmanagerPhone;
	}
	public String getComplaintDesc() {
		return complaintDesc;
	}
	public void setComplaintDesc(String complaintDesc) {
		this.complaintDesc = complaintDesc;
	}
	public String getComplaintLoca() {
		return complaintLoca;
	}
	public void setComplaintLoca(String complaintLoca) {
		this.complaintLoca = complaintLoca;
	}
	public String getComplaintNum() {
		return complaintNum;
	}
	public void setComplaintNum(String complaintNum) {
		this.complaintNum = complaintNum;
	}
	public Date getComplaintTime() {
		return complaintTime;
	}
	public void setComplaintTime(Date complaintTime) {
		this.complaintTime = complaintTime;
	}
	public String getConnectType() {
		return connectType;
	}
	public void setConnectType(String connectType) {
		this.connectType = connectType;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getCustomAttribution() {
		return customAttribution;
	}
	public void setCustomAttribution(String customAttribution) {
		this.customAttribution = customAttribution;
	}
	public String getCustomContact() {
		return customContact;
	}
	public void setCustomContact(String customContact) {
		this.customContact = customContact;
	}
	public String getCustomContactPhone() {
		return customContactPhone;
	}
	public void setCustomContactPhone(String customContactPhone) {
		this.customContactPhone = customContactPhone;
	}
	public String getCustomLevel() {
		return customLevel;
	}
	public void setCustomLevel(String customLevel) {
		this.customLevel = customLevel;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getCustomNo() {
		return customNo;
	}
	public void setCustomNo(String customNo) {
		this.customNo = customNo;
	}
	public Date getDealTime1() {
		return dealTime1;
	}
	public void setDealTime1(Date dealTime1) {
		this.dealTime1 = dealTime1;
	}
	public Date getDealTime2() {
		return dealTime2;
	}
	public void setDealTime2(Date dealTime2) {
		this.dealTime2 = dealTime2;
	}
	public String getEcsiType() {
		return ecsiType;
	}
	public void setEcsiType(String ecsiType) {
		this.ecsiType = ecsiType;
	}
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	public Date getFaultTime() {
		return faultTime;
	}
	public void setFaultTime(Date faultTime) {
		this.faultTime = faultTime;
	}
	public String getMainCheckIdea() {
		return mainCheckIdea;
	}
	public void setMainCheckIdea(String mainCheckIdea) {
		this.mainCheckIdea = mainCheckIdea;
	}
	public String getMainCheckResult() {
		return mainCheckResult;
	}
	public void setMainCheckResult(String mainCheckResult) {
		this.mainCheckResult = mainCheckResult;
	}
	public String getMainDelayFlag() {
		return mainDelayFlag;
	}
	public void setMainDelayFlag(String mainDelayFlag) {
		this.mainDelayFlag = mainDelayFlag;
	}
	public String getMainIfCheck() {
		return mainIfCheck;
	}
	public void setMainIfCheck(String mainIfCheck) {
		this.mainIfCheck = mainIfCheck;
	}
	public String getMainIfDeferResolve() {
		return mainIfDeferResolve;
	}
	public void setMainIfDeferResolve(String mainIfDeferResolve) {
		this.mainIfDeferResolve = mainIfDeferResolve;
	}
	public String getMainIfRecord() {
		return mainIfRecord;
	}
	public void setMainIfRecord(String mainIfRecord) {
		this.mainIfRecord = mainIfRecord;
	}
	public String getMainInterfaceSheetType() {
		return mainInterfaceSheetType;
	}
	public void setMainInterfaceSheetType(String mainInterfaceSheetType) {
		this.mainInterfaceSheetType = mainInterfaceSheetType;
	}
	public String getMainQcRejectTimes() {
		return mainQcRejectTimes;
	}
	public Date getMainLastRepeatTime() {
		return mainLastRepeatTime;
	}
	public void setMainLastRepeatTime(Date mainLastRepeatTime) {
		this.mainLastRepeatTime = mainLastRepeatTime;
	}
	public void setMainQcRejectTimes(String mainQcRejectTimes) {
		this.mainQcRejectTimes = mainQcRejectTimes;
	}
	public String getPreDealResult() {
		return preDealResult;
	}
	public void setPreDealResult(String preDealResult) {
		this.preDealResult = preDealResult;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getServerCode() {
		return serverCode;
	}
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public String getUrgentDegree() {
		return urgentDegree;
	}
	public void setUrgentDegree(String urgentDegree) {
		this.urgentDegree = urgentDegree;
	}
	public Date getMainCompleteLimitT1() {
		return mainCompleteLimitT1;
	}
	public void setMainCompleteLimitT1(Date mainCompleteLimitT1) {
		this.mainCompleteLimitT1 = mainCompleteLimitT1;
	}
	public Date getMainCompleteLimitT2() {
		return mainCompleteLimitT2;
	}
	public void setMainCompleteLimitT2(Date mainCompleteLimitT2) {
		this.mainCompleteLimitT2 = mainCompleteLimitT2;
	}
	public String getMainCommSheetId() {
		return mainCommSheetId;
	}
	public void setMainCommSheetId(String mainCommSheetId) {
		this.mainCommSheetId = mainCommSheetId;
	}
	public String getMainIfMatch() {
		return mainIfMatch;
	}
	public void setMainIfMatch(String mainIfMatch) {
		this.mainIfMatch = mainIfMatch;
	}
	public String getMainIfSelf() {
		return mainIfSelf;
	}
	public void setMainIfSelf(String mainIfSelf) {
		this.mainIfSelf = mainIfSelf;
	}
	

	




	
	
	
		
	
}
