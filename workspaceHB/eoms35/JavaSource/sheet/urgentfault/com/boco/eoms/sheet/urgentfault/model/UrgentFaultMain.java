/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.urgentfault.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UrgentFaultMain extends BaseMain {

    private String mainSendContentType;
    private String mainIfGreatFault;
    private String mainGreatFaultId;
    private String mainFaultSheetId;
    private Date mainFaultGenerantTime;
    private String mainFaultGenerantPlace;
    private String mainNetSortOne;
    private String mainNetSortTwo;
    private String mainNetSortThree;
    private String mainEquipmentFactory;
    private String mainFaultDesc;
    private String mainFaultReason;
    private String mainTrafficLoss;
    private String mainImpactUserNum;
    private String mainIfAffectOperation;
    private String mainAffectArea;
    private String mainAffectOperationDesc;
    private Date mainAffectStartTime;
    private String mainAffectOperationLevel;
    private String mainAffectOperationSort;
    private String mainTriggerUserComplaint;
    private String mainIfReport;

    private String mainIfAchieveProjectStartup;

    private String mainUrgentLeve;
    private String mainFaultSpeciality;
    private String mainEquipIntegrator;
    private Date mainFaultAvoidTime;
    private String mainAffectTimeLength;
    private String mainFaultSubReason;
    private String mainFaultLocation;
    private Date mainReportLimit;
    private Integer mainIfRecord;


    public Integer getMainIfRecord() {
        return mainIfRecord;
    }

    public void setMainIfRecord(Integer mainIfRecord) {
        this.mainIfRecord = mainIfRecord;
    }

    public Date getMainReportLimit() {
        return mainReportLimit;
    }

    public void setMainReportLimit(Date mainReportLimit) {
        this.mainReportLimit = mainReportLimit;
    }

    public String getMainFaultLocation() {
        return mainFaultLocation;
    }

    public void setMainFaultLocation(String mainFaultLocation) {
        this.mainFaultLocation = mainFaultLocation;
    }

    public String getMainFaultSubReason() {
        return mainFaultSubReason;
    }

    public void setMainFaultSubReason(String mainFaultSubReason) {
        this.mainFaultSubReason = mainFaultSubReason;
    }

    public String getMainSendContentType() {
        return mainSendContentType;
    }

    public void setMainSendContentType(String mainSendContentType) {
        this.mainSendContentType = mainSendContentType;
    }

    public String getMainAffectArea() {
        return mainAffectArea;
    }

    public void setMainAffectArea(String mainAffectArea) {
        this.mainAffectArea = mainAffectArea;
    }

    public String getMainAffectOperationDesc() {
        return mainAffectOperationDesc;
    }

    public void setMainAffectOperationDesc(String mainAffectOperationDesc) {
        this.mainAffectOperationDesc = mainAffectOperationDesc;
    }

    public String getMainAffectOperationLevel() {
        return mainAffectOperationLevel;
    }

    public void setMainAffectOperationLevel(String mainAffectOperationLevel) {
        this.mainAffectOperationLevel = mainAffectOperationLevel;
    }

    public String getMainAffectOperationSort() {
        return mainAffectOperationSort;
    }

    public void setMainAffectOperationSort(String mainAffectOperationSort) {
        this.mainAffectOperationSort = mainAffectOperationSort;
    }

    public Date getMainAffectStartTime() {
        return mainAffectStartTime;
    }

    public void setMainAffectStartTime(Date mainAffectStartTime) {
        this.mainAffectStartTime = mainAffectStartTime;
    }

    public String getMainAffectTimeLength() {
        return mainAffectTimeLength;
    }

    public void setMainAffectTimeLength(String mainAffectTimeLength) {
        this.mainAffectTimeLength = mainAffectTimeLength;
    }

    public String getMainEquipIntegrator() {
        return mainEquipIntegrator;
    }

    public void setMainEquipIntegrator(String mainEquipIntegrator) {
        this.mainEquipIntegrator = mainEquipIntegrator;
    }

    public String getMainEquipmentFactory() {
        return mainEquipmentFactory;
    }

    public void setMainEquipmentFactory(String mainEquipmentFactory) {
        this.mainEquipmentFactory = mainEquipmentFactory;
    }

    public Date getMainFaultAvoidTime() {
        return mainFaultAvoidTime;
    }

    public void setMainFaultAvoidTime(Date mainFaultAvoidTime) {
        this.mainFaultAvoidTime = mainFaultAvoidTime;
    }

    public String getMainFaultDesc() {
        return mainFaultDesc;
    }

    public void setMainFaultDesc(String mainFaultDesc) {
        this.mainFaultDesc = mainFaultDesc;
    }

    public String getMainFaultGenerantPlace() {
        return mainFaultGenerantPlace;
    }

    public void setMainFaultGenerantPlace(String mainFaultGenerantPlace) {
        this.mainFaultGenerantPlace = mainFaultGenerantPlace;
    }

    public Date getMainFaultGenerantTime() {
        return mainFaultGenerantTime;
    }

    public void setMainFaultGenerantTime(Date mainFaultGenerantTime) {
        this.mainFaultGenerantTime = mainFaultGenerantTime;
    }

    public String getMainFaultReason() {
        return mainFaultReason;
    }

    public void setMainFaultReason(String mainFaultReason) {
        this.mainFaultReason = mainFaultReason;
    }

    public String getMainFaultSheetId() {
        return mainFaultSheetId;
    }

    public void setMainFaultSheetId(String mainFaultSheetId) {
        this.mainFaultSheetId = mainFaultSheetId;
    }

    public String getMainFaultSpeciality() {
        return mainFaultSpeciality;
    }

    public void setMainFaultSpeciality(String mainFaultSpeciality) {
        this.mainFaultSpeciality = mainFaultSpeciality;
    }

    public String getMainGreatFaultId() {
        return mainGreatFaultId;
    }

    public void setMainGreatFaultId(String mainGreatFaultId) {
        this.mainGreatFaultId = mainGreatFaultId;
    }

    public String getMainIfAchieveProjectStartup() {
        return mainIfAchieveProjectStartup;
    }

    public void setMainIfAchieveProjectStartup(String mainIfAchieveProjectStartup) {
        this.mainIfAchieveProjectStartup = mainIfAchieveProjectStartup;
    }

    public String getMainIfAffectOperation() {
        return mainIfAffectOperation;
    }

    public void setMainIfAffectOperation(String mainIfAffectOperation) {
        this.mainIfAffectOperation = mainIfAffectOperation;
    }

    public String getMainIfGreatFault() {
        return mainIfGreatFault;
    }

    public void setMainIfGreatFault(String mainIfGreatFault) {
        this.mainIfGreatFault = mainIfGreatFault;
    }

    public String getMainIfReport() {
        return mainIfReport;
    }

    public void setMainIfReport(String mainIfReport) {
        this.mainIfReport = mainIfReport;
    }

    public String getMainImpactUserNum() {
        return mainImpactUserNum;
    }

    public void setMainImpactUserNum(String mainImpactUserNum) {
        this.mainImpactUserNum = mainImpactUserNum;
    }

    public String getMainTrafficLoss() {
        return mainTrafficLoss;
    }

    public void setMainTrafficLoss(String mainTrafficLoss) {
        this.mainTrafficLoss = mainTrafficLoss;
    }

    public String getMainTriggerUserComplaint() {
        return mainTriggerUserComplaint;
    }

    public void setMainTriggerUserComplaint(String mainTriggerUserComplaint) {
        this.mainTriggerUserComplaint = mainTriggerUserComplaint;
    }

    public String getMainUrgentLeve() {
        return mainUrgentLeve;
    }

    public void setMainUrgentLeve(String mainUrgentLeve) {
        this.mainUrgentLeve = mainUrgentLeve;
    }

    public String getMainNetSortTwo() {
        return mainNetSortTwo;
    }

    public void setMainNetSortTwo(String mainNetSortTwo) {
        this.mainNetSortTwo = mainNetSortTwo;
    }

    public String getMainNetSortOne() {
        return mainNetSortOne;
    }

    public void setMainNetSortOne(String mainNetSortOne) {
        this.mainNetSortOne = mainNetSortOne;
    }

    public String getMainNetSortThree() {
        return mainNetSortThree;
    }

    public void setMainNetSortThree(String mainNetSortThree) {
        this.mainNetSortThree = mainNetSortThree;
    }


}
