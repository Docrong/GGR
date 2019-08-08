// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultPackMain.java

package com.boco.eoms.sheet.commonfaultpack.model;

import com.boco.eoms.sheet.base.model.BaseMain;

import java.util.Date;

public class CommonFaultPackMain extends BaseMain {

    private String mainId;
    private String title;
    private String mainApplySheetId;
    private String mainIfUrgentFault;
    private String mainFaultResponseLevel;
    private String mainAlarmNum;
    private String mainAlarmState;
    private String mainAlarmDesc;
    private String mainAlarmSolveDate;
    private String mainEquipmentFactory;
    private String mainEquipmentName;
    private String mainEquipmentModel;
    private String mainFaultGenerantTime;
    private String mainIfAffectOperation;
    private String mainFaultDiscoverableMode;
    private String mainAlarmId;
    private String mainAlarmSource;
    private String mainAlarmLogicSort;
    private String mainAlarmLogicSortSub;
    private String mainFaultSpecialty;
    private String mainEquipmentType;
    private String mainNetName;
    private String mainAlarmLevel;
    private String mainFaultGenerantProvince;
    private String mainFaultGenerantCity;
    private String mainNetSortOne;
    private String mainNetSortTwo;
    private String mainNetSortThree;
    private String replyFaultDealResult;
    private String replyFaultAvoidTime;
    private String replyOperRenewTime;
    private String replyAffectTimeLength;
    private String replyDealStep;
    private String replyFaultReasonSort;
    private String replyFaultReasonSubsection;
    private String replyIfGreatFault;
    private String replyIfExcuteNetChange;
    private String replyIfFinallySolveProject;
    private Date mainCheckTime;
    private String mainCheckStatus;
    private int checkCount;

    private String mainLocatinf;//定位信息

    public CommonFaultPackMain() {
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getMainApplySheetId() {
        return mainApplySheetId;
    }

    public void setMainApplySheetId(String mainApplySheetId) {
        this.mainApplySheetId = mainApplySheetId;
    }

    public String getMainIfUrgentFault() {
        return mainIfUrgentFault;
    }

    public void setMainIfUrgentFault(String mainIfUrgentFault) {
        this.mainIfUrgentFault = mainIfUrgentFault;
    }

    public String getMainFaultResponseLevel() {
        return mainFaultResponseLevel;
    }

    public void setMainFaultResponseLevel(String mainFaultResponseLevel) {
        this.mainFaultResponseLevel = mainFaultResponseLevel;
    }

    public String getMainAlarmNum() {
        return mainAlarmNum;
    }

    public void setMainAlarmNum(String mainAlarmNum) {
        this.mainAlarmNum = mainAlarmNum;
    }

    public String getMainAlarmState() {
        return mainAlarmState;
    }

    public void setMainAlarmState(String mainAlarmState) {
        this.mainAlarmState = mainAlarmState;
    }

    public String getMainAlarmDesc() {
        return mainAlarmDesc;
    }

    public void setMainAlarmDesc(String mainAlarmDesc) {
        this.mainAlarmDesc = mainAlarmDesc;
    }

    public String getMainAlarmSolveDate() {
        return mainAlarmSolveDate;
    }

    public void setMainAlarmSolveDate(String mainAlarmSolveDate) {
        this.mainAlarmSolveDate = mainAlarmSolveDate;
    }

    public String getMainEquipmentFactory() {
        return mainEquipmentFactory;
    }

    public void setMainEquipmentFactory(String mainEquipmentFactory) {
        this.mainEquipmentFactory = mainEquipmentFactory;
    }

    public String getMainEquipmentName() {
        return mainEquipmentName;
    }

    public void setMainEquipmentName(String mainEquipmentName) {
        this.mainEquipmentName = mainEquipmentName;
    }

    public String getMainEquipmentModel() {
        return mainEquipmentModel;
    }

    public void setMainEquipmentModel(String mainEquipmentModel) {
        this.mainEquipmentModel = mainEquipmentModel;
    }

    public String getMainFaultGenerantTime() {
        return mainFaultGenerantTime;
    }

    public void setMainFaultGenerantTime(String mainFaultGenerantTime) {
        this.mainFaultGenerantTime = mainFaultGenerantTime;
    }

    public String getMainIfAffectOperation() {
        return mainIfAffectOperation;
    }

    public void setMainIfAffectOperation(String mainIfAffectOperation) {
        this.mainIfAffectOperation = mainIfAffectOperation;
    }

    public String getMainFaultDiscoverableMode() {
        return mainFaultDiscoverableMode;
    }

    public void setMainFaultDiscoverableMode(String mainFaultDiscoverableMode) {
        this.mainFaultDiscoverableMode = mainFaultDiscoverableMode;
    }

    public String getMainAlarmId() {
        return mainAlarmId;
    }

    public void setMainAlarmId(String mainAlarmId) {
        this.mainAlarmId = mainAlarmId;
    }

    public String getMainAlarmSource() {
        return mainAlarmSource;
    }

    public void setMainAlarmSource(String mainAlarmSource) {
        this.mainAlarmSource = mainAlarmSource;
    }

    public String getMainAlarmLogicSort() {
        return mainAlarmLogicSort;
    }

    public void setMainAlarmLogicSort(String mainAlarmLogicSort) {
        this.mainAlarmLogicSort = mainAlarmLogicSort;
    }

    public String getMainAlarmLogicSortSub() {
        return mainAlarmLogicSortSub;
    }

    public void setMainAlarmLogicSortSub(String mainAlarmLogicSortSub) {
        this.mainAlarmLogicSortSub = mainAlarmLogicSortSub;
    }

    public String getMainFaultSpecialty() {
        return mainFaultSpecialty;
    }

    public void setMainFaultSpecialty(String mainFaultSpecialty) {
        this.mainFaultSpecialty = mainFaultSpecialty;
    }

    public String getMainEquipmentType() {
        return mainEquipmentType;
    }

    public void setMainEquipmentType(String mainEquipmentType) {
        this.mainEquipmentType = mainEquipmentType;
    }

    public String getMainNetName() {
        return mainNetName;
    }

    public void setMainNetName(String mainNetName) {
        this.mainNetName = mainNetName;
    }

    public String getMainFaultGenerantProvince() {
        return mainFaultGenerantProvince;
    }

    public void setMainFaultGenerantProvince(String mainFaultGenerantProvince) {
        this.mainFaultGenerantProvince = mainFaultGenerantProvince;
    }

    public String getMainFaultGenerantCity() {
        return mainFaultGenerantCity;
    }

    public void setMainFaultGenerantCity(String mainFaultGenerantCity) {
        this.mainFaultGenerantCity = mainFaultGenerantCity;
    }

    public String getMainNetSortOne() {
        return mainNetSortOne;
    }

    public void setMainNetSortOne(String mainNetSortOne) {
        this.mainNetSortOne = mainNetSortOne;
    }

    public String getMainNetSortTwo() {
        return mainNetSortTwo;
    }

    public void setMainNetSortTwo(String mainNetSortTwo) {
        this.mainNetSortTwo = mainNetSortTwo;
    }

    public String getMainNetSortThree() {
        return mainNetSortThree;
    }

    public void setMainNetSortThree(String mainNetSortThree) {
        this.mainNetSortThree = mainNetSortThree;
    }

    public String getReplyFaultDealResult() {
        return replyFaultDealResult;
    }

    public void setReplyFaultDealResult(String replyFaultDealResult) {
        this.replyFaultDealResult = replyFaultDealResult;
    }

    public String getReplyFaultAvoidTime() {
        return replyFaultAvoidTime;
    }

    public void setReplyFaultAvoidTime(String replyFaultAvoidTime) {
        this.replyFaultAvoidTime = replyFaultAvoidTime;
    }

    public String getReplyOperRenewTime() {
        return replyOperRenewTime;
    }

    public void setReplyOperRenewTime(String replyOperRenewTime) {
        this.replyOperRenewTime = replyOperRenewTime;
    }

    public String getReplyAffectTimeLength() {
        return replyAffectTimeLength;
    }

    public void setReplyAffectTimeLength(String replyAffectTimeLength) {
        this.replyAffectTimeLength = replyAffectTimeLength;
    }

    public String getReplyDealStep() {
        return replyDealStep;
    }

    public void setReplyDealStep(String replyDealStep) {
        this.replyDealStep = replyDealStep;
    }

    public String getReplyFaultReasonSort() {
        return replyFaultReasonSort;
    }

    public void setReplyFaultReasonSort(String replyFaultReasonSort) {
        this.replyFaultReasonSort = replyFaultReasonSort;
    }

    public String getReplyFaultReasonSubsection() {
        return replyFaultReasonSubsection;
    }

    public void setReplyFaultReasonSubsection(String replyFaultReasonSubsection) {
        this.replyFaultReasonSubsection = replyFaultReasonSubsection;
    }

    public String getMainAlarmLevel() {
        return mainAlarmLevel;
    }

    public void setMainAlarmLevel(String mainAlarmLevel) {
        this.mainAlarmLevel = mainAlarmLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReplyIfExcuteNetChange() {
        return replyIfExcuteNetChange;
    }

    public void setReplyIfExcuteNetChange(String replyIfExcuteNetChange) {
        this.replyIfExcuteNetChange = replyIfExcuteNetChange;
    }

    public String getReplyIfFinallySolveProject() {
        return replyIfFinallySolveProject;
    }

    public void setReplyIfFinallySolveProject(String replyIfFinallySolveProject) {
        this.replyIfFinallySolveProject = replyIfFinallySolveProject;
    }

    public String getReplyIfGreatFault() {
        return replyIfGreatFault;
    }

    public void setReplyIfGreatFault(String replyIfGreatFault) {
        this.replyIfGreatFault = replyIfGreatFault;
    }

    public Date getMainCheckTime() {
        return mainCheckTime;
    }

    public void setMainCheckTime(Date mainCheckTime) {
        this.mainCheckTime = mainCheckTime;
    }


    public String getMainCheckStatus() {
        return mainCheckStatus;
    }

    public void setMainCheckStatus(String mainCheckStatus) {
        this.mainCheckStatus = mainCheckStatus;
    }

    public int getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public String getMainLocatinf() {
        return mainLocatinf;
    }

    public void setMainLocatinf(String mainLocatinf) {
        this.mainLocatinf = mainLocatinf;
    }

}
