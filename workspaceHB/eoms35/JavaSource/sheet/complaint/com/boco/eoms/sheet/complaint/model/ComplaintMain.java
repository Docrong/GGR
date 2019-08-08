// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintMain.java

package com.boco.eoms.sheet.complaint.model;

import com.boco.eoms.sheet.base.model.BaseMain;

import java.util.Date;

public class ComplaintMain extends BaseMain {

    private Date dealTime1;
    private Date dealTime2;
    private String urgentDegree;
    private String btype1;
    private String bdeptContact;
    private String customerName;
    private String customPhone;
    private Date complaintTime;
    private Date faultTime;
    private String complaintAdd;
    private String complaintDesc;
    private String bdeptContactPhone;
    private String repeatComplaintTimes;
    private String complaintType1;
    private String complaintType2;
    private String complaintType;
    private String complaintReason1;
    private String complaintReason2;
    private String complaintReason;
    private String customType;
    private String customBrand;
    private String customLevel;
    private String customAttribution;
    private String startDealCity;
    private String callerNo;
    private String callerRegistVLR;
    private String callerDialUpType;
    private String callerFault;
    private String callerCallOtherDesc;
    private String aroundUserDesc;
    private String callerIsIntelligentUser;
    private String calledPartyNo;
    private String calledPartyRegistVLR;
    private String otherUserDesc;
    private String calledPartyCallC;
    private String dealAdvice;
    private String mainIfDeferResolve;
    private Date mainCompleteLimitT1;
    private Date mainCompleteLimitT2;
    private Integer mainQcRejectTimes;
    private Integer mainIfRecord;
    private String mainDelayFlag;
    private Date mainLastRepeatTime;
    private String mainIfCheck;
    private String mainInterfaceSheetType;
    private String faultDesc;
    private String faultArea;
    private String faultRoad;
    private String faultNo;
    private String faultRoad1;
    private String faultRoad2;
    private String faultVill;
    private String isVisit;
    private String mainCheckResult;
    private String mainCheckIdea;
    private String complaintType4;
    private String complaintType5;
    private String complaintType6;
    private String complaintType7;
    private int reportFlag;
    private String complaintTypeKf;
    private String mainIfManualcheck;
    private String terminalType;
    private String preDealResult;
    private String complaintNum;
    private String faultSite;
    private String ifAgent;
    private String revert;
    private Integer mainSleepStatus;

    private Date mainT2ApplyTime;


    private Date mainT1DealTime;


    private Date mainActivateTime;


    private Date mainOldCompleteLimit;

    private String mainActivateDealer;
    /**
     * 休眠时间 add by weichao 20121225
     */
    private String mainSleepTime;
    /**
     * 申请休眠的task记录Id
     */
    private String mainSleepTkid;
    /**
     * T1最后的一个处理人
     */
    private String mainT1Dealer;
    /**
     * 休眠申请人
     */
    private String mainSleepUser;
    /**
     * 休眠原因
     */
    private String mainSleepReason;

    /**
     * 故障地市
     */
    private String mainFaultCity;

    /**
     * 故障区县
     */
    private String mainFaultCounty;

    public ComplaintMain() {
    }

    public String getIfAgent() {
        return ifAgent;
    }

    public void setIfAgent(String ifAgent) {
        this.ifAgent = ifAgent;
    }

    public String getComplaintNum() {
        return complaintNum;
    }

    public String getMainActivateDealer() {
        return mainActivateDealer;
    }

    public void setMainActivateDealer(String mainActivateDealer) {
        this.mainActivateDealer = mainActivateDealer;
    }

    public Date getMainActivateTime() {
        return mainActivateTime;
    }

    public void setMainActivateTime(Date mainActivateTime) {
        this.mainActivateTime = mainActivateTime;
    }

    public Date getMainOldCompleteLimit() {
        return mainOldCompleteLimit;
    }

    public void setMainOldCompleteLimit(Date mainOldCompleteLimit) {
        this.mainOldCompleteLimit = mainOldCompleteLimit;
    }

    public Date getMainT1DealTime() {
        return mainT1DealTime;
    }

    public void setMainT1DealTime(Date mainT1DealTime) {
        this.mainT1DealTime = mainT1DealTime;
    }

    public Date getMainT2ApplyTime() {
        return mainT2ApplyTime;
    }

    public void setMainT2ApplyTime(Date mainT2ApplyTime) {
        this.mainT2ApplyTime = mainT2ApplyTime;
    }

    public String getMainSleepReason() {
        return mainSleepReason;
    }

    public void setMainSleepReason(String mainSleepReason) {
        this.mainSleepReason = mainSleepReason;
    }

    public Integer getMainSleepStatus() {
        return mainSleepStatus;
    }

    public void setMainSleepStatus(Integer mainSleepStatus) {
        this.mainSleepStatus = mainSleepStatus;
    }

    public String getMainSleepTime() {
        return mainSleepTime;
    }

    public void setMainSleepTime(String mainSleepTime) {
        this.mainSleepTime = mainSleepTime;
    }

    public String getMainSleepTkid() {
        return mainSleepTkid;
    }

    public void setMainSleepTkid(String mainSleepTkid) {
        this.mainSleepTkid = mainSleepTkid;
    }

    public String getMainSleepUser() {
        return mainSleepUser;
    }

    public void setMainSleepUser(String mainSleepUser) {
        this.mainSleepUser = mainSleepUser;
    }

    public String getMainT1Dealer() {
        return mainT1Dealer;
    }

    public void setMainT1Dealer(String mainT1Dealer) {
        this.mainT1Dealer = mainT1Dealer;
    }

    public void setComplaintNum(String complaintNum) {
        this.complaintNum = complaintNum;
    }

    public String getFaultSite() {
        return faultSite;
    }

    public void setFaultSite(String faultSite) {
        this.faultSite = faultSite;
    }

    public String getPreDealResult() {
        return preDealResult;
    }

    public void setPreDealResult(String preDealResult) {
        this.preDealResult = preDealResult;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getComplaintType4() {
        return complaintType4;
    }

    public void setComplaintType4(String complaintType4) {
        this.complaintType4 = complaintType4;
    }

    public String getComplaintType5() {
        return complaintType5;
    }

    public void setComplaintType5(String complaintType5) {
        this.complaintType5 = complaintType5;
    }

    public String getComplaintType6() {
        return complaintType6;
    }

    public void setComplaintType6(String complaintType6) {
        this.complaintType6 = complaintType6;
    }

    public String getComplaintType7() {
        return complaintType7;
    }

    public void setComplaintType7(String complaintType7) {
        this.complaintType7 = complaintType7;
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

    public String getFaultArea() {
        return faultArea;
    }

    public void setFaultArea(String faultArea) {
        this.faultArea = faultArea;
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    public String getFaultNo() {
        return faultNo;
    }

    public void setFaultNo(String faultNo) {
        this.faultNo = faultNo;
    }

    public String getFaultRoad() {
        return faultRoad;
    }

    public void setFaultRoad(String faultRoad) {
        this.faultRoad = faultRoad;
    }

    public String getFaultRoad1() {
        return faultRoad1;
    }

    public void setFaultRoad1(String faultRoad1) {
        this.faultRoad1 = faultRoad1;
    }

    public String getFaultRoad2() {
        return faultRoad2;
    }

    public void setFaultRoad2(String faultRoad2) {
        this.faultRoad2 = faultRoad2;
    }

    public String getFaultVill() {
        return faultVill;
    }

    public void setFaultVill(String faultVill) {
        this.faultVill = faultVill;
    }

    public String getIsVisit() {
        return isVisit;
    }

    public void setIsVisit(String isVisit) {
        this.isVisit = isVisit;
    }

    public String getMainIfCheck() {
        return mainIfCheck;
    }

    public void setMainIfCheck(String mainIfCheck) {
        this.mainIfCheck = mainIfCheck;
    }

    public String getMainInterfaceSheetType() {
        return mainInterfaceSheetType;
    }

    public void setMainInterfaceSheetType(String mainInterfaceSheetType) {
        this.mainInterfaceSheetType = mainInterfaceSheetType;
    }

    public Date getMainLastRepeatTime() {
        return mainLastRepeatTime;
    }

    public void setMainLastRepeatTime(Date mainLastRepeatTime) {
        this.mainLastRepeatTime = mainLastRepeatTime;
    }

    public String getMainDelayFlag() {
        return mainDelayFlag;
    }

    public void setMainDelayFlag(String mainDelayFlag) {
        this.mainDelayFlag = mainDelayFlag;
    }

    public Integer getMainIfRecord() {
        return mainIfRecord;
    }

    public void setMainIfRecord(Integer mainIfRecord) {
        this.mainIfRecord = mainIfRecord;
    }

    public Integer getMainQcRejectTimes() {
        return mainQcRejectTimes;
    }

    public void setMainQcRejectTimes(Integer mainQcRejectTimes) {
        this.mainQcRejectTimes = mainQcRejectTimes;
    }

    public String getAroundUserDesc() {
        return aroundUserDesc;
    }

    public void setAroundUserDesc(String aroundUserDesc) {
        this.aroundUserDesc = aroundUserDesc;
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

    public String getBtype1() {
        return btype1;
    }

    public void setBtype1(String btype1) {
        this.btype1 = btype1;
    }

    public String getCalledPartyCallC() {
        return calledPartyCallC;
    }

    public void setCalledPartyCallC(String calledPartyCallC) {
        this.calledPartyCallC = calledPartyCallC;
    }

    public String getCalledPartyNo() {
        return calledPartyNo;
    }

    public void setCalledPartyNo(String calledPartyNo) {
        this.calledPartyNo = calledPartyNo;
    }

    public String getCalledPartyRegistVLR() {
        return calledPartyRegistVLR;
    }

    public void setCalledPartyRegistVLR(String calledPartyRegistVLR) {
        this.calledPartyRegistVLR = calledPartyRegistVLR;
    }

    public String getCallerCallOtherDesc() {
        return callerCallOtherDesc;
    }

    public void setCallerCallOtherDesc(String callerCallOtherDesc) {
        this.callerCallOtherDesc = callerCallOtherDesc;
    }

    public String getCallerDialUpType() {
        return callerDialUpType;
    }

    public void setCallerDialUpType(String callerDialUpType) {
        this.callerDialUpType = callerDialUpType;
    }

    public String getCallerFault() {
        return callerFault;
    }

    public void setCallerFault(String callerFault) {
        this.callerFault = callerFault;
    }

    public String getCallerIsIntelligentUser() {
        return callerIsIntelligentUser;
    }

    public void setCallerIsIntelligentUser(String callerIsIntelligentUser) {
        this.callerIsIntelligentUser = callerIsIntelligentUser;
    }

    public String getCallerNo() {
        return callerNo;
    }

    public void setCallerNo(String callerNo) {
        this.callerNo = callerNo;
    }

    public String getCallerRegistVLR() {
        return callerRegistVLR;
    }

    public void setCallerRegistVLR(String callerRegistVLR) {
        this.callerRegistVLR = callerRegistVLR;
    }

    public String getComplaintAdd() {
        return complaintAdd;
    }

    public void setComplaintAdd(String complaintAdd) {
        this.complaintAdd = complaintAdd;
    }

    public String getComplaintDesc() {
        return complaintDesc;
    }

    public void setComplaintDesc(String complaintDesc) {
        this.complaintDesc = complaintDesc;
    }

    public String getComplaintReason() {
        return complaintReason;
    }

    public void setComplaintReason(String complaintReason) {
        this.complaintReason = complaintReason;
    }

    public String getComplaintReason1() {
        return complaintReason1;
    }

    public void setComplaintReason1(String complaintReason1) {
        this.complaintReason1 = complaintReason1;
    }

    public String getComplaintReason2() {
        return complaintReason2;
    }

    public void setComplaintReason2(String complaintReason2) {
        this.complaintReason2 = complaintReason2;
    }

    public Date getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplaintType1() {
        return complaintType1;
    }

    public void setComplaintType1(String complaintType1) {
        this.complaintType1 = complaintType1;
    }

    public String getComplaintType2() {
        return complaintType2;
    }

    public void setComplaintType2(String complaintType2) {
        this.complaintType2 = complaintType2;
    }

    public String getCustomAttribution() {
        return customAttribution;
    }

    public void setCustomAttribution(String customAttribution) {
        this.customAttribution = customAttribution;
    }

    public String getCustomBrand() {
        return customBrand;
    }

    public void setCustomBrand(String customBrand) {
        this.customBrand = customBrand;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomLevel() {
        return customLevel;
    }

    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel;
    }

    public String getCustomPhone() {
        return customPhone;
    }

    public void setCustomPhone(String customPhone) {
        this.customPhone = customPhone;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public String getDealAdvice() {
        return dealAdvice;
    }

    public void setDealAdvice(String dealAdvice) {
        this.dealAdvice = dealAdvice;
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

    public Date getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(Date faultTime) {
        this.faultTime = faultTime;
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

    public String getMainIfDeferResolve() {
        return mainIfDeferResolve;
    }

    public void setMainIfDeferResolve(String mainIfDeferResolve) {
        this.mainIfDeferResolve = mainIfDeferResolve;
    }

    public String getOtherUserDesc() {
        return otherUserDesc;
    }

    public void setOtherUserDesc(String otherUserDesc) {
        this.otherUserDesc = otherUserDesc;
    }

    public String getRepeatComplaintTimes() {
        return repeatComplaintTimes;
    }

    public void setRepeatComplaintTimes(String repeatComplaintTimes) {
        this.repeatComplaintTimes = repeatComplaintTimes;
    }

    public String getStartDealCity() {
        return startDealCity;
    }

    public void setStartDealCity(String startDealCity) {
        this.startDealCity = startDealCity;
    }

    public String getUrgentDegree() {
        return urgentDegree;
    }

    public void setUrgentDegree(String urgentDegree) {
        this.urgentDegree = urgentDegree;
    }

    public int getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(int reportFlag) {
        this.reportFlag = reportFlag;
    }

    public String getComplaintTypeKf() {
        return complaintTypeKf;
    }

    public void setComplaintTypeKf(String complaintTypeKf) {
        this.complaintTypeKf = complaintTypeKf;
    }

    public String getMainIfManualcheck() {
        return mainIfManualcheck;
    }

    public void setMainIfManualcheck(String mainIfManualcheck) {
        this.mainIfManualcheck = mainIfManualcheck;
    }

    public String getRevert() {
        return revert;
    }

    public void setRevert(String revert) {
        this.revert = revert;
    }

    public String getMainFaultCity() {
        return mainFaultCity;
    }

    public void setMainFaultCity(String mainFaultCity) {
        this.mainFaultCity = mainFaultCity;
    }

    public String getMainFaultCounty() {
        return mainFaultCounty;
    }

    public void setMainFaultCounty(String mainFaultCounty) {
        this.mainFaultCounty = mainFaultCounty;
    }
}
