// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AgentMaintenanceMain.java

package com.boco.eoms.sheet.agentmaintenance.model;

import com.boco.eoms.sheet.base.model.BaseMain;

import java.util.Date;

public class AgentMaintenanceMain extends BaseMain {

    private String mainFaultApplySheetId;
    private String mainFaultIfUrgentFault;
    private String mainFaultUrgentFaultLog;
    private String mainFaultIfSafe;
    private String mainFaultIfMutualCommunication;
    private String mainFaultResponseLevel;
    private String mainFaultAlarmNum;
    private String mainFaultAlarmState;
    private String mainFaultAlarmDesc;
    private Date mainFaultAlarmSolveDate;
    private String mainFaultNetSortOne;
    private String mainFaultNetSortTwo;
    private String mainFaultNetSortThree;
    private String mainFaultEquipmentFactory;
    private String mainFaultEquipmentName;
    private String mainFaultEquipmentModel;
    private Date mainFaultGenerantTime;
    private String mainFaultGenerantPriv;
    private String mainFaultGenerantCity;
    private String mainFaultIfAffectOperation;
    private String mainFaultAffectArea;
    private Date mainFaultAffectStartTime;
    private String mainFaultDiscoverableMode;
    private String mainFaultFirstDealDesc;
    private String mainFaultDesc;
    private String mainFaultSendMode;
    private String mainFaultAlarmId;
    private String mainFaultAlarmSource;
    private String mainFaultAlarmLogicSort;
    private String mainFaultAlarmLogicSortSub;
    private String mainFaultSpecialty;
    private String mainFaultEquipmentType;
    private String mainFaultNetName;
    private Date mainFaultCompleteLimitT1;
    private Date mainFaultCompleteLimitT2;
    private Date mainFaultCompleteLimitT3;
    private Integer mainFaultRejectCount;
    private String mainFaultDelayFlag;
    private Integer mainFaultIfRecord;
    private String mainFaultIfCheck;
    private String mainFaultCheckResult;
    private String mainFaultCheckIdea;
    private String mainFaultIfAlarmSend;
    private String mainFaultAlarmLevel;
    private String mainFaultPretreatment;
    private String linkFaultIfMutualCommunication;
    private String linkFaultIfSafe;
    private String linkFaultDesc;
    private String linkFaultFirstDealDesc;
    private String linkFaultIfGreatFault;
    private String firstFaultNodeAccessories;
    private int mainFaultreportFlag;
    private String mainTaskNetSort1;
    private String mainTaskNetSort2;
    private String mainTaskNetSort3;
    private String mainTaskType;
    private String mainTaskDescription;
    private String mainTaskRemark;
    private Date mainComdealTime1;
    private Date mainComdealTime2;
    private String mainComurgentDegree;
    private String mainCombtype1;
    private String mainCombdeptContact;
    private String mainComcustomerName;
    private String mainComcustomPhone;
    private Date mainComTime;
    private Date mainComfaultTime;
    private String mainComAdd;
    private String mainComDesc;
    private String mainCombdeptContactPhone;
    private String mainrepeatComTimes;
    private String mainComType1;
    private String mainComType2;
    private String mainComType;
    private String mainComReason1;
    private String mainComReason2;
    private String mainComReason;
    private String mainComcustomType;
    private String mainComcustomBrand;
    private String mainComcustomLevel;
    private String mainComcustomAttribution;
    private String mainComstartDealCity;
    private String mainComcallerNo;
    private String mainComcallerRegistVLR;
    private String mainComcallerDialUpType;
    private String mainComcallerFault;
    private String mainComcallerCallOtherDesc;
    private String mainComaroundUserDesc;
    private String mainComcallerIsIntelligentUser;
    private String mainComcalledPartyNo;
    private String mainComcalledPartyRegistVLR;
    private String mainComotherUserDesc;
    private String mainComcalledPartyCallC;
    private String mainComdealAdvice;
    private String mainCommainIfDeferResolve;
    private Date mainComCompleteLimitT1;
    private Date mainComCompleteLimitT2;
    private Integer mainComQcRejectTimes;
    private Integer mainComIfRecord;
    private String mainComDelayFlag;
    private Date mainComLastRepeatTime;
    private String mainComIfCheck;
    private String mainComInterfaceSheetType;
    private String mainComfaultDesc;
    private String mainComfaultArea;
    private String mainComfaultRoad;
    private String mainComfaultNo;
    private String mainComfaultRoad1;
    private String mainComfaultRoad2;
    private String mainComfaultVill;
    private String mainComisVisit;
    private String mainComCheckResult;
    private String mainComCheckIdea;
    private String mainComType4;
    private String mainComType5;
    private String mainComType6;
    private String mainComType7;
    private int mainComreportFlag;
    private String mainComTypeKf;
    private String mainComIfManualcheck;
    private String mainComterminalType;
    private String mainCompreDealResult;
    private String mainComtNum;
    private String mainComfaultSite;
    private String type;
    private String sourceId;
    private String mainSheetState;
    private String mainSheetType;

    public String getMainSheetType() {
        return mainSheetType;
    }

    public void setMainSheetType(String mainSheetType) {
        this.mainSheetType = mainSheetType;
    }

    public AgentMaintenanceMain() {
    }

    public String getMainSheetState() {
        return mainSheetState;
    }

    public void setMainSheetState(String mainSheetState) {
        this.mainSheetState = mainSheetState;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstFaultNodeAccessories() {
        return firstFaultNodeAccessories;
    }

    public void setFirstFaultNodeAccessories(String firstFaultNodeAccessories) {
        this.firstFaultNodeAccessories = firstFaultNodeAccessories;
    }

    public String getLinkFaultDesc() {
        return linkFaultDesc;
    }

    public void setLinkFaultDesc(String linkFaultDesc) {
        this.linkFaultDesc = linkFaultDesc;
    }

    public String getLinkFaultFirstDealDesc() {
        return linkFaultFirstDealDesc;
    }

    public void setLinkFaultFirstDealDesc(String linkFaultFirstDealDesc) {
        this.linkFaultFirstDealDesc = linkFaultFirstDealDesc;
    }

    public String getLinkFaultIfGreatFault() {
        return linkFaultIfGreatFault;
    }

    public void setLinkFaultIfGreatFault(String linkFaultIfGreatFault) {
        this.linkFaultIfGreatFault = linkFaultIfGreatFault;
    }

    public String getLinkFaultIfMutualCommunication() {
        return linkFaultIfMutualCommunication;
    }

    public void setLinkFaultIfMutualCommunication(String linkFaultIfMutualCommunication) {
        this.linkFaultIfMutualCommunication = linkFaultIfMutualCommunication;
    }

    public String getLinkFaultIfSafe() {
        return linkFaultIfSafe;
    }

    public void setLinkFaultIfSafe(String linkFaultIfSafe) {
        this.linkFaultIfSafe = linkFaultIfSafe;
    }

    public String getMainComAdd() {
        return mainComAdd;
    }

    public void setMainComAdd(String mainComAdd) {
        this.mainComAdd = mainComAdd;
    }

    public String getMainComaroundUserDesc() {
        return mainComaroundUserDesc;
    }

    public void setMainComaroundUserDesc(String mainComaroundUserDesc) {
        this.mainComaroundUserDesc = mainComaroundUserDesc;
    }

    public String getMainCombdeptContact() {
        return mainCombdeptContact;
    }

    public void setMainCombdeptContact(String mainCombdeptContact) {
        this.mainCombdeptContact = mainCombdeptContact;
    }

    public String getMainCombdeptContactPhone() {
        return mainCombdeptContactPhone;
    }

    public void setMainCombdeptContactPhone(String mainCombdeptContactPhone) {
        this.mainCombdeptContactPhone = mainCombdeptContactPhone;
    }

    public String getMainCombtype1() {
        return mainCombtype1;
    }

    public void setMainCombtype1(String mainCombtype1) {
        this.mainCombtype1 = mainCombtype1;
    }

    public String getMainComcalledPartyCallC() {
        return mainComcalledPartyCallC;
    }

    public void setMainComcalledPartyCallC(String mainComcalledPartyCallC) {
        this.mainComcalledPartyCallC = mainComcalledPartyCallC;
    }

    public String getMainComcalledPartyNo() {
        return mainComcalledPartyNo;
    }

    public void setMainComcalledPartyNo(String mainComcalledPartyNo) {
        this.mainComcalledPartyNo = mainComcalledPartyNo;
    }

    public String getMainComcalledPartyRegistVLR() {
        return mainComcalledPartyRegistVLR;
    }

    public void setMainComcalledPartyRegistVLR(String mainComcalledPartyRegistVLR) {
        this.mainComcalledPartyRegistVLR = mainComcalledPartyRegistVLR;
    }

    public String getMainComcallerCallOtherDesc() {
        return mainComcallerCallOtherDesc;
    }

    public void setMainComcallerCallOtherDesc(String mainComcallerCallOtherDesc) {
        this.mainComcallerCallOtherDesc = mainComcallerCallOtherDesc;
    }

    public String getMainComcallerDialUpType() {
        return mainComcallerDialUpType;
    }

    public void setMainComcallerDialUpType(String mainComcallerDialUpType) {
        this.mainComcallerDialUpType = mainComcallerDialUpType;
    }

    public String getMainComcallerFault() {
        return mainComcallerFault;
    }

    public void setMainComcallerFault(String mainComcallerFault) {
        this.mainComcallerFault = mainComcallerFault;
    }

    public String getMainComcallerIsIntelligentUser() {
        return mainComcallerIsIntelligentUser;
    }

    public void setMainComcallerIsIntelligentUser(String mainComcallerIsIntelligentUser) {
        this.mainComcallerIsIntelligentUser = mainComcallerIsIntelligentUser;
    }

    public String getMainComcallerNo() {
        return mainComcallerNo;
    }

    public void setMainComcallerNo(String mainComcallerNo) {
        this.mainComcallerNo = mainComcallerNo;
    }

    public String getMainComcallerRegistVLR() {
        return mainComcallerRegistVLR;
    }

    public void setMainComcallerRegistVLR(String mainComcallerRegistVLR) {
        this.mainComcallerRegistVLR = mainComcallerRegistVLR;
    }

    public String getMainComCheckIdea() {
        return mainComCheckIdea;
    }

    public void setMainComCheckIdea(String mainComCheckIdea) {
        this.mainComCheckIdea = mainComCheckIdea;
    }

    public String getMainComCheckResult() {
        return mainComCheckResult;
    }

    public void setMainComCheckResult(String mainComCheckResult) {
        this.mainComCheckResult = mainComCheckResult;
    }

    public Date getMainComCompleteLimitT1() {
        return mainComCompleteLimitT1;
    }

    public void setMainComCompleteLimitT1(Date mainComCompleteLimitT1) {
        this.mainComCompleteLimitT1 = mainComCompleteLimitT1;
    }

    public Date getMainComCompleteLimitT2() {
        return mainComCompleteLimitT2;
    }

    public void setMainComCompleteLimitT2(Date mainComCompleteLimitT2) {
        this.mainComCompleteLimitT2 = mainComCompleteLimitT2;
    }

    public String getMainComcustomAttribution() {
        return mainComcustomAttribution;
    }

    public void setMainComcustomAttribution(String mainComcustomAttribution) {
        this.mainComcustomAttribution = mainComcustomAttribution;
    }

    public String getMainComcustomBrand() {
        return mainComcustomBrand;
    }

    public void setMainComcustomBrand(String mainComcustomBrand) {
        this.mainComcustomBrand = mainComcustomBrand;
    }

    public String getMainComcustomerName() {
        return mainComcustomerName;
    }

    public void setMainComcustomerName(String mainComcustomerName) {
        this.mainComcustomerName = mainComcustomerName;
    }

    public String getMainComcustomLevel() {
        return mainComcustomLevel;
    }

    public void setMainComcustomLevel(String mainComcustomLevel) {
        this.mainComcustomLevel = mainComcustomLevel;
    }

    public String getMainComcustomPhone() {
        return mainComcustomPhone;
    }

    public void setMainComcustomPhone(String mainComcustomPhone) {
        this.mainComcustomPhone = mainComcustomPhone;
    }

    public String getMainComcustomType() {
        return mainComcustomType;
    }

    public void setMainComcustomType(String mainComcustomType) {
        this.mainComcustomType = mainComcustomType;
    }

    public String getMainComdealAdvice() {
        return mainComdealAdvice;
    }

    public void setMainComdealAdvice(String mainComdealAdvice) {
        this.mainComdealAdvice = mainComdealAdvice;
    }

    public Date getMainComdealTime1() {
        return mainComdealTime1;
    }

    public void setMainComdealTime1(Date mainComdealTime1) {
        this.mainComdealTime1 = mainComdealTime1;
    }

    public Date getMainComdealTime2() {
        return mainComdealTime2;
    }

    public void setMainComdealTime2(Date mainComdealTime2) {
        this.mainComdealTime2 = mainComdealTime2;
    }

    public String getMainComDelayFlag() {
        return mainComDelayFlag;
    }

    public void setMainComDelayFlag(String mainComDelayFlag) {
        this.mainComDelayFlag = mainComDelayFlag;
    }

    public String getMainComDesc() {
        return mainComDesc;
    }

    public void setMainComDesc(String mainComDesc) {
        this.mainComDesc = mainComDesc;
    }

    public String getMainComfaultArea() {
        return mainComfaultArea;
    }

    public void setMainComfaultArea(String mainComfaultArea) {
        this.mainComfaultArea = mainComfaultArea;
    }

    public String getMainComfaultDesc() {
        return mainComfaultDesc;
    }

    public void setMainComfaultDesc(String mainComfaultDesc) {
        this.mainComfaultDesc = mainComfaultDesc;
    }

    public String getMainComfaultNo() {
        return mainComfaultNo;
    }

    public void setMainComfaultNo(String mainComfaultNo) {
        this.mainComfaultNo = mainComfaultNo;
    }

    public String getMainComfaultRoad() {
        return mainComfaultRoad;
    }

    public void setMainComfaultRoad(String mainComfaultRoad) {
        this.mainComfaultRoad = mainComfaultRoad;
    }

    public String getMainComfaultRoad1() {
        return mainComfaultRoad1;
    }

    public void setMainComfaultRoad1(String mainComfaultRoad1) {
        this.mainComfaultRoad1 = mainComfaultRoad1;
    }

    public String getMainComfaultRoad2() {
        return mainComfaultRoad2;
    }

    public void setMainComfaultRoad2(String mainComfaultRoad2) {
        this.mainComfaultRoad2 = mainComfaultRoad2;
    }

    public String getMainComfaultSite() {
        return mainComfaultSite;
    }

    public void setMainComfaultSite(String mainComfaultSite) {
        this.mainComfaultSite = mainComfaultSite;
    }

    public Date getMainComfaultTime() {
        return mainComfaultTime;
    }

    public void setMainComfaultTime(Date mainComfaultTime) {
        this.mainComfaultTime = mainComfaultTime;
    }

    public String getMainComfaultVill() {
        return mainComfaultVill;
    }

    public void setMainComfaultVill(String mainComfaultVill) {
        this.mainComfaultVill = mainComfaultVill;
    }

    public String getMainComIfCheck() {
        return mainComIfCheck;
    }

    public void setMainComIfCheck(String mainComIfCheck) {
        this.mainComIfCheck = mainComIfCheck;
    }

    public String getMainComIfManualcheck() {
        return mainComIfManualcheck;
    }

    public void setMainComIfManualcheck(String mainComIfManualcheck) {
        this.mainComIfManualcheck = mainComIfManualcheck;
    }

    public Integer getMainComIfRecord() {
        return mainComIfRecord;
    }

    public void setMainComIfRecord(Integer mainComIfRecord) {
        this.mainComIfRecord = mainComIfRecord;
    }

    public String getMainComInterfaceSheetType() {
        return mainComInterfaceSheetType;
    }

    public void setMainComInterfaceSheetType(String mainComInterfaceSheetType) {
        this.mainComInterfaceSheetType = mainComInterfaceSheetType;
    }

    public String getMainComisVisit() {
        return mainComisVisit;
    }

    public void setMainComisVisit(String mainComisVisit) {
        this.mainComisVisit = mainComisVisit;
    }

    public Date getMainComLastRepeatTime() {
        return mainComLastRepeatTime;
    }

    public void setMainComLastRepeatTime(Date mainComLastRepeatTime) {
        this.mainComLastRepeatTime = mainComLastRepeatTime;
    }

    public String getMainCommainIfDeferResolve() {
        return mainCommainIfDeferResolve;
    }

    public void setMainCommainIfDeferResolve(String mainCommainIfDeferResolve) {
        this.mainCommainIfDeferResolve = mainCommainIfDeferResolve;
    }

    public String getMainComotherUserDesc() {
        return mainComotherUserDesc;
    }

    public void setMainComotherUserDesc(String mainComotherUserDesc) {
        this.mainComotherUserDesc = mainComotherUserDesc;
    }

    public String getMainCompreDealResult() {
        return mainCompreDealResult;
    }

    public void setMainCompreDealResult(String mainCompreDealResult) {
        this.mainCompreDealResult = mainCompreDealResult;
    }

    public Integer getMainComQcRejectTimes() {
        return mainComQcRejectTimes;
    }

    public void setMainComQcRejectTimes(Integer mainComQcRejectTimes) {
        this.mainComQcRejectTimes = mainComQcRejectTimes;
    }

    public String getMainComReason() {
        return mainComReason;
    }

    public void setMainComReason(String mainComReason) {
        this.mainComReason = mainComReason;
    }

    public String getMainComReason1() {
        return mainComReason1;
    }

    public void setMainComReason1(String mainComReason1) {
        this.mainComReason1 = mainComReason1;
    }

    public String getMainComReason2() {
        return mainComReason2;
    }

    public void setMainComReason2(String mainComReason2) {
        this.mainComReason2 = mainComReason2;
    }

    public int getMainComreportFlag() {
        return mainComreportFlag;
    }

    public void setMainComreportFlag(int mainComreportFlag) {
        this.mainComreportFlag = mainComreportFlag;
    }

    public String getMainComstartDealCity() {
        return mainComstartDealCity;
    }

    public void setMainComstartDealCity(String mainComstartDealCity) {
        this.mainComstartDealCity = mainComstartDealCity;
    }

    public String getMainComterminalType() {
        return mainComterminalType;
    }

    public void setMainComterminalType(String mainComterminalType) {
        this.mainComterminalType = mainComterminalType;
    }

    public Date getMainComTime() {
        return mainComTime;
    }

    public void setMainComTime(Date mainComTime) {
        this.mainComTime = mainComTime;
    }

    public String getMainComtNum() {
        return mainComtNum;
    }

    public void setMainComtNum(String mainComtNum) {
        this.mainComtNum = mainComtNum;
    }

    public String getMainComType() {
        return mainComType;
    }

    public void setMainComType(String mainComType) {
        this.mainComType = mainComType;
    }

    public String getMainComType1() {
        return mainComType1;
    }

    public void setMainComType1(String mainComType1) {
        this.mainComType1 = mainComType1;
    }

    public String getMainComType2() {
        return mainComType2;
    }

    public void setMainComType2(String mainComType2) {
        this.mainComType2 = mainComType2;
    }

    public String getMainComType4() {
        return mainComType4;
    }

    public void setMainComType4(String mainComType4) {
        this.mainComType4 = mainComType4;
    }

    public String getMainComType5() {
        return mainComType5;
    }

    public void setMainComType5(String mainComType5) {
        this.mainComType5 = mainComType5;
    }

    public String getMainComType6() {
        return mainComType6;
    }

    public void setMainComType6(String mainComType6) {
        this.mainComType6 = mainComType6;
    }

    public String getMainComType7() {
        return mainComType7;
    }

    public void setMainComType7(String mainComType7) {
        this.mainComType7 = mainComType7;
    }

    public String getMainComTypeKf() {
        return mainComTypeKf;
    }

    public void setMainComTypeKf(String mainComTypeKf) {
        this.mainComTypeKf = mainComTypeKf;
    }

    public String getMainComurgentDegree() {
        return mainComurgentDegree;
    }

    public void setMainComurgentDegree(String mainComurgentDegree) {
        this.mainComurgentDegree = mainComurgentDegree;
    }

    public String getMainFaultAffectArea() {
        return mainFaultAffectArea;
    }

    public void setMainFaultAffectArea(String mainFaultAffectArea) {
        this.mainFaultAffectArea = mainFaultAffectArea;
    }

    public Date getMainFaultAffectStartTime() {
        return mainFaultAffectStartTime;
    }

    public void setMainFaultAffectStartTime(Date mainFaultAffectStartTime) {
        this.mainFaultAffectStartTime = mainFaultAffectStartTime;
    }

    public String getMainFaultAlarmDesc() {
        return mainFaultAlarmDesc;
    }

    public void setMainFaultAlarmDesc(String mainFaultAlarmDesc) {
        this.mainFaultAlarmDesc = mainFaultAlarmDesc;
    }

    public String getMainFaultAlarmId() {
        return mainFaultAlarmId;
    }

    public void setMainFaultAlarmId(String mainFaultAlarmId) {
        this.mainFaultAlarmId = mainFaultAlarmId;
    }

    public String getMainFaultAlarmLevel() {
        return mainFaultAlarmLevel;
    }

    public void setMainFaultAlarmLevel(String mainFaultAlarmLevel) {
        this.mainFaultAlarmLevel = mainFaultAlarmLevel;
    }

    public String getMainFaultAlarmLogicSort() {
        return mainFaultAlarmLogicSort;
    }

    public void setMainFaultAlarmLogicSort(String mainFaultAlarmLogicSort) {
        this.mainFaultAlarmLogicSort = mainFaultAlarmLogicSort;
    }

    public String getMainFaultAlarmLogicSortSub() {
        return mainFaultAlarmLogicSortSub;
    }

    public void setMainFaultAlarmLogicSortSub(String mainFaultAlarmLogicSortSub) {
        this.mainFaultAlarmLogicSortSub = mainFaultAlarmLogicSortSub;
    }

    public String getMainFaultAlarmNum() {
        return mainFaultAlarmNum;
    }

    public void setMainFaultAlarmNum(String mainFaultAlarmNum) {
        this.mainFaultAlarmNum = mainFaultAlarmNum;
    }

    public Date getMainFaultAlarmSolveDate() {
        return mainFaultAlarmSolveDate;
    }

    public void setMainFaultAlarmSolveDate(Date mainFaultAlarmSolveDate) {
        this.mainFaultAlarmSolveDate = mainFaultAlarmSolveDate;
    }

    public String getMainFaultAlarmSource() {
        return mainFaultAlarmSource;
    }

    public void setMainFaultAlarmSource(String mainFaultAlarmSource) {
        this.mainFaultAlarmSource = mainFaultAlarmSource;
    }

    public String getMainFaultAlarmState() {
        return mainFaultAlarmState;
    }

    public void setMainFaultAlarmState(String mainFaultAlarmState) {
        this.mainFaultAlarmState = mainFaultAlarmState;
    }

    public String getMainFaultApplySheetId() {
        return mainFaultApplySheetId;
    }

    public void setMainFaultApplySheetId(String mainFaultApplySheetId) {
        this.mainFaultApplySheetId = mainFaultApplySheetId;
    }

    public String getMainFaultCheckIdea() {
        return mainFaultCheckIdea;
    }

    public void setMainFaultCheckIdea(String mainFaultCheckIdea) {
        this.mainFaultCheckIdea = mainFaultCheckIdea;
    }

    public String getMainFaultCheckResult() {
        return mainFaultCheckResult;
    }

    public void setMainFaultCheckResult(String mainFaultCheckResult) {
        this.mainFaultCheckResult = mainFaultCheckResult;
    }

    public Date getMainFaultCompleteLimitT1() {
        return mainFaultCompleteLimitT1;
    }

    public void setMainFaultCompleteLimitT1(Date mainFaultCompleteLimitT1) {
        this.mainFaultCompleteLimitT1 = mainFaultCompleteLimitT1;
    }

    public Date getMainFaultCompleteLimitT2() {
        return mainFaultCompleteLimitT2;
    }

    public void setMainFaultCompleteLimitT2(Date mainFaultCompleteLimitT2) {
        this.mainFaultCompleteLimitT2 = mainFaultCompleteLimitT2;
    }

    public Date getMainFaultCompleteLimitT3() {
        return mainFaultCompleteLimitT3;
    }

    public void setMainFaultCompleteLimitT3(Date mainFaultCompleteLimitT3) {
        this.mainFaultCompleteLimitT3 = mainFaultCompleteLimitT3;
    }

    public String getMainFaultDelayFlag() {
        return mainFaultDelayFlag;
    }

    public void setMainFaultDelayFlag(String mainFaultDelayFlag) {
        this.mainFaultDelayFlag = mainFaultDelayFlag;
    }

    public String getMainFaultDesc() {
        return mainFaultDesc;
    }

    public void setMainFaultDesc(String mainFaultDesc) {
        this.mainFaultDesc = mainFaultDesc;
    }

    public String getMainFaultDiscoverableMode() {
        return mainFaultDiscoverableMode;
    }

    public void setMainFaultDiscoverableMode(String mainFaultDiscoverableMode) {
        this.mainFaultDiscoverableMode = mainFaultDiscoverableMode;
    }

    public String getMainFaultEquipmentFactory() {
        return mainFaultEquipmentFactory;
    }

    public void setMainFaultEquipmentFactory(String mainFaultEquipmentFactory) {
        this.mainFaultEquipmentFactory = mainFaultEquipmentFactory;
    }

    public String getMainFaultEquipmentModel() {
        return mainFaultEquipmentModel;
    }

    public void setMainFaultEquipmentModel(String mainFaultEquipmentModel) {
        this.mainFaultEquipmentModel = mainFaultEquipmentModel;
    }

    public String getMainFaultEquipmentName() {
        return mainFaultEquipmentName;
    }

    public void setMainFaultEquipmentName(String mainFaultEquipmentName) {
        this.mainFaultEquipmentName = mainFaultEquipmentName;
    }

    public String getMainFaultEquipmentType() {
        return mainFaultEquipmentType;
    }

    public void setMainFaultEquipmentType(String mainFaultEquipmentType) {
        this.mainFaultEquipmentType = mainFaultEquipmentType;
    }

    public String getMainFaultFirstDealDesc() {
        return mainFaultFirstDealDesc;
    }

    public void setMainFaultFirstDealDesc(String mainFaultFirstDealDesc) {
        this.mainFaultFirstDealDesc = mainFaultFirstDealDesc;
    }

    public String getMainFaultGenerantCity() {
        return mainFaultGenerantCity;
    }

    public void setMainFaultGenerantCity(String mainFaultGenerantCity) {
        this.mainFaultGenerantCity = mainFaultGenerantCity;
    }

    public String getMainFaultGenerantPriv() {
        return mainFaultGenerantPriv;
    }

    public void setMainFaultGenerantPriv(String mainFaultGenerantPriv) {
        this.mainFaultGenerantPriv = mainFaultGenerantPriv;
    }

    public Date getMainFaultGenerantTime() {
        return mainFaultGenerantTime;
    }

    public void setMainFaultGenerantTime(Date mainFaultGenerantTime) {
        this.mainFaultGenerantTime = mainFaultGenerantTime;
    }

    public String getMainFaultIfAffectOperation() {
        return mainFaultIfAffectOperation;
    }

    public void setMainFaultIfAffectOperation(String mainFaultIfAffectOperation) {
        this.mainFaultIfAffectOperation = mainFaultIfAffectOperation;
    }

    public String getMainFaultIfAlarmSend() {
        return mainFaultIfAlarmSend;
    }

    public void setMainFaultIfAlarmSend(String mainFaultIfAlarmSend) {
        this.mainFaultIfAlarmSend = mainFaultIfAlarmSend;
    }

    public String getMainFaultIfCheck() {
        return mainFaultIfCheck;
    }

    public void setMainFaultIfCheck(String mainFaultIfCheck) {
        this.mainFaultIfCheck = mainFaultIfCheck;
    }

    public String getMainFaultIfMutualCommunication() {
        return mainFaultIfMutualCommunication;
    }

    public void setMainFaultIfMutualCommunication(String mainFaultIfMutualCommunication) {
        this.mainFaultIfMutualCommunication = mainFaultIfMutualCommunication;
    }

    public Integer getMainFaultIfRecord() {
        return mainFaultIfRecord;
    }

    public void setMainFaultIfRecord(Integer mainFaultIfRecord) {
        this.mainFaultIfRecord = mainFaultIfRecord;
    }

    public String getMainFaultIfSafe() {
        return mainFaultIfSafe;
    }

    public void setMainFaultIfSafe(String mainFaultIfSafe) {
        this.mainFaultIfSafe = mainFaultIfSafe;
    }

    public String getMainFaultIfUrgentFault() {
        return mainFaultIfUrgentFault;
    }

    public void setMainFaultIfUrgentFault(String mainFaultIfUrgentFault) {
        this.mainFaultIfUrgentFault = mainFaultIfUrgentFault;
    }

    public String getMainFaultNetName() {
        return mainFaultNetName;
    }

    public void setMainFaultNetName(String mainFaultNetName) {
        this.mainFaultNetName = mainFaultNetName;
    }

    public String getMainFaultNetSortOne() {
        return mainFaultNetSortOne;
    }

    public void setMainFaultNetSortOne(String mainFaultNetSortOne) {
        this.mainFaultNetSortOne = mainFaultNetSortOne;
    }

    public String getMainFaultNetSortThree() {
        return mainFaultNetSortThree;
    }

    public void setMainFaultNetSortThree(String mainFaultNetSortThree) {
        this.mainFaultNetSortThree = mainFaultNetSortThree;
    }

    public String getMainFaultNetSortTwo() {
        return mainFaultNetSortTwo;
    }

    public void setMainFaultNetSortTwo(String mainFaultNetSortTwo) {
        this.mainFaultNetSortTwo = mainFaultNetSortTwo;
    }

    public String getMainFaultPretreatment() {
        return mainFaultPretreatment;
    }

    public void setMainFaultPretreatment(String mainFaultPretreatment) {
        this.mainFaultPretreatment = mainFaultPretreatment;
    }

    public Integer getMainFaultRejectCount() {
        return mainFaultRejectCount;
    }

    public void setMainFaultRejectCount(Integer mainFaultRejectCount) {
        this.mainFaultRejectCount = mainFaultRejectCount;
    }

    public int getMainFaultreportFlag() {
        return mainFaultreportFlag;
    }

    public void setMainFaultreportFlag(int mainFaultreportFlag) {
        this.mainFaultreportFlag = mainFaultreportFlag;
    }

    public String getMainFaultResponseLevel() {
        return mainFaultResponseLevel;
    }

    public void setMainFaultResponseLevel(String mainFaultResponseLevel) {
        this.mainFaultResponseLevel = mainFaultResponseLevel;
    }

    public String getMainFaultSendMode() {
        return mainFaultSendMode;
    }

    public void setMainFaultSendMode(String mainFaultSendMode) {
        this.mainFaultSendMode = mainFaultSendMode;
    }

    public String getMainFaultSpecialty() {
        return mainFaultSpecialty;
    }

    public void setMainFaultSpecialty(String mainFaultSpecialty) {
        this.mainFaultSpecialty = mainFaultSpecialty;
    }

    public String getMainFaultUrgentFaultLog() {
        return mainFaultUrgentFaultLog;
    }

    public void setMainFaultUrgentFaultLog(String mainFaultUrgentFaultLog) {
        this.mainFaultUrgentFaultLog = mainFaultUrgentFaultLog;
    }

    public String getMainrepeatComTimes() {
        return mainrepeatComTimes;
    }

    public void setMainrepeatComTimes(String mainrepeatComTimes) {
        this.mainrepeatComTimes = mainrepeatComTimes;
    }

    public String getMainTaskDescription() {
        return mainTaskDescription;
    }

    public void setMainTaskDescription(String mainTaskDescription) {
        this.mainTaskDescription = mainTaskDescription;
    }

    public String getMainTaskNetSort1() {
        return mainTaskNetSort1;
    }

    public void setMainTaskNetSort1(String mainTaskNetSort1) {
        this.mainTaskNetSort1 = mainTaskNetSort1;
    }

    public String getMainTaskNetSort2() {
        return mainTaskNetSort2;
    }

    public void setMainTaskNetSort2(String mainTaskNetSort2) {
        this.mainTaskNetSort2 = mainTaskNetSort2;
    }

    public String getMainTaskNetSort3() {
        return mainTaskNetSort3;
    }

    public void setMainTaskNetSort3(String mainTaskNetSort3) {
        this.mainTaskNetSort3 = mainTaskNetSort3;
    }

    public String getMainTaskRemark() {
        return mainTaskRemark;
    }

    public void setMainTaskRemark(String mainTaskRemark) {
        this.mainTaskRemark = mainTaskRemark;
    }

    public String getMainTaskType() {
        return mainTaskType;
    }

    public void setMainTaskType(String mainTaskType) {
        this.mainTaskType = mainTaskType;
    }
}
