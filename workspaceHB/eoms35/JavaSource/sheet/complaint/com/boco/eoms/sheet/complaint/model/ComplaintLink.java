// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ComplaintLink.java

package com.boco.eoms.sheet.complaint.model;

import com.boco.eoms.sheet.base.model.BaseLink;

import java.util.Date;

public class ComplaintLink extends BaseLink {

    private String linkTransmitReason;
    private String linkIfInvokeChange;
    private Date linkFaultStartTime;
    private Date linkFaultEndTime;
    private String linkFaultGenerantPlace;
    private String linkPlaceDesc;
    private String ndeptContact;
    private String ndeptContactPhone;
    private String dealResult;
    private String dealDesc;
    private Date issueEliminatTime;
    private String issueEliminatReason;
    private String linkCheckResult;
    private String linkCheckIdea;
    private String linkUntreadReason;
    private String linkTransmitContent;
    private String linkExamineContent;
    private String linkIfDeferResolve;
    private String linkIfInvokeCaseDatabase;
    private String linkChangeSheetId;
    private String isSubTransmit;
    private String parLinkId;
    private String isReplied;
    private String compProp;
    private String isDeferReploy;
    private String issueReasonTypeOne;
    private String issueReasonTypeTwo;
    private String issueReasonTypeThree;
    private String issueReasonTypeFour;

    private String linkReplyPerson;
    private String linkReplayPhone;
    private String linkDealDesc;
    private String linkBusinessType;
    private String linkFaultType;
    private String linkReasonType;
    private String linkIsReciveFaultId;
    private String linkReciveFaultId;
    private String linkIsComplaintSolve;
    private Date linkComplaintSolveDate;
    private String linkPlanSolveTypeparent;
    private String linkPlanSolveType;
    private Date linkPlanSolveDate;
    private String linkIsUserConfirm;
    private String linkNoConfirmReason;
    private String linkIsRepeatComplaint;
    private String linkRepeatComplaintType;
    private String linkIsUserStatisfied;
    private String linkUserNoStatisfied;
    private String linkIsContactUser;
    private Date linkContactDate;
    private String linkContactship;
    private String linkContactUser;
    private String linkContactPhone;
    private String linkNoContactReason;
    private String linkAddressCI;
    private String linkAddressName;
    private String linkEquipmentType;
    private String linkEquipmentFactory;
    private String linkIsAlarm;
    private String linkAlarmDetail;
    private String linkCommonfaultNumber;
    private String linkCoverDian;
    private String linkCoverLian;
    private String linkSpecialty;
    private String linkQuality;
    private String aiNetReasonDesc;
    private String aiNetResult;
    private String selectAiNetReason;
    private String linkIfAiNet;
    private String linkIfMobile;

    public ComplaintLink() {
    }

    public String getIsDeferReploy() {
        return isDeferReploy;
    }

    public void setIsDeferReploy(String isDeferReploy) {
        this.isDeferReploy = isDeferReploy;
    }

    public String getCompProp() {
        return compProp;
    }

    public void setCompProp(String compProp) {
        this.compProp = compProp;
    }

    public String getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(String isReplied) {
        this.isReplied = isReplied;
    }

    public String getIsSubTransmit() {
        return isSubTransmit;
    }

    public void setIsSubTransmit(String isSubTransmit) {
        this.isSubTransmit = isSubTransmit;
    }

    public String getParLinkId() {
        return parLinkId;
    }

    public void setParLinkId(String parLinkId) {
        this.parLinkId = parLinkId;
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

    public String getIssueEliminatReason() {
        return issueEliminatReason;
    }

    public void setIssueEliminatReason(String issueEliminatReason) {
        this.issueEliminatReason = issueEliminatReason;
    }

    public Date getIssueEliminatTime() {
        return issueEliminatTime;
    }

    public void setIssueEliminatTime(Date issueEliminatTime) {
        this.issueEliminatTime = issueEliminatTime;
    }

    public String getLinkCheckIdea() {
        return linkCheckIdea;
    }

    public void setLinkCheckIdea(String linkCheckIdea) {
        this.linkCheckIdea = linkCheckIdea;
    }

    public String getLinkCheckResult() {
        return linkCheckResult;
    }

    public void setLinkCheckResult(String linkCheckResult) {
        this.linkCheckResult = linkCheckResult;
    }

    public String getLinkExamineContent() {
        return linkExamineContent;
    }

    public void setLinkExamineContent(String linkExamineContent) {
        this.linkExamineContent = linkExamineContent;
    }

    public Date getLinkFaultEndTime() {
        return linkFaultEndTime;
    }

    public void setLinkFaultEndTime(Date linkFaultEndTime) {
        this.linkFaultEndTime = linkFaultEndTime;
    }

    public String getLinkFaultGenerantPlace() {
        return linkFaultGenerantPlace;
    }

    public void setLinkFaultGenerantPlace(String linkFaultGenerantPlace) {
        this.linkFaultGenerantPlace = linkFaultGenerantPlace;
    }

    public Date getLinkFaultStartTime() {
        return linkFaultStartTime;
    }

    public void setLinkFaultStartTime(Date linkFaultStartTime) {
        this.linkFaultStartTime = linkFaultStartTime;
    }

    public String getLinkIfDeferResolve() {
        return linkIfDeferResolve;
    }

    public void setLinkIfDeferResolve(String linkIfDeferResolve) {
        this.linkIfDeferResolve = linkIfDeferResolve;
    }

    public String getLinkIfInvokeCaseDatabase() {
        return linkIfInvokeCaseDatabase;
    }

    public void setLinkIfInvokeCaseDatabase(String linkIfInvokeCaseDatabase) {
        this.linkIfInvokeCaseDatabase = linkIfInvokeCaseDatabase;
    }

    public String getLinkIfInvokeChange() {
        return linkIfInvokeChange;
    }

    public void setLinkIfInvokeChange(String linkIfInvokeChange) {
        this.linkIfInvokeChange = linkIfInvokeChange;
    }

    public String getLinkPlaceDesc() {
        return linkPlaceDesc;
    }

    public void setLinkPlaceDesc(String linkPlaceDesc) {
        this.linkPlaceDesc = linkPlaceDesc;
    }

    public String getLinkTransmitContent() {
        return linkTransmitContent;
    }

    public void setLinkTransmitContent(String linkTransmitContent) {
        this.linkTransmitContent = linkTransmitContent;
    }

    public String getLinkTransmitReason() {
        return linkTransmitReason;
    }

    public void setLinkTransmitReason(String linkTransmitReason) {
        this.linkTransmitReason = linkTransmitReason;
    }

    public String getLinkUntreadReason() {
        return linkUntreadReason;
    }

    public void setLinkUntreadReason(String linkUntreadReason) {
        this.linkUntreadReason = linkUntreadReason;
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

    public String getLinkChangeSheetId() {
        return linkChangeSheetId;
    }

    public void setLinkChangeSheetId(String linkChangeSheetId) {
        this.linkChangeSheetId = linkChangeSheetId;
    }

    public String getIssueReasonTypeFour() {
        return issueReasonTypeFour;
    }

    public void setIssueReasonTypeFour(String issueReasonTypeFour) {
        this.issueReasonTypeFour = issueReasonTypeFour;
    }

    public String getIssueReasonTypeOne() {
        return issueReasonTypeOne;
    }

    public void setIssueReasonTypeOne(String issueReasonTypeOne) {
        this.issueReasonTypeOne = issueReasonTypeOne;
    }

    public String getIssueReasonTypeThree() {
        return issueReasonTypeThree;
    }

    public void setIssueReasonTypeThree(String issueReasonTypeThree) {
        this.issueReasonTypeThree = issueReasonTypeThree;
    }

    public String getIssueReasonTypeTwo() {
        return issueReasonTypeTwo;
    }

    public void setIssueReasonTypeTwo(String issueReasonTypeTwo) {
        this.issueReasonTypeTwo = issueReasonTypeTwo;
    }

    public String getLinkAddressCI() {
        return linkAddressCI;
    }

    public void setLinkAddressCI(String linkAddressCI) {
        this.linkAddressCI = linkAddressCI;
    }

    public String getLinkAddressName() {
        return linkAddressName;
    }

    public void setLinkAddressName(String linkAddressName) {
        this.linkAddressName = linkAddressName;
    }

    public String getLinkAlarmDetail() {
        return linkAlarmDetail;
    }

    public void setLinkAlarmDetail(String linkAlarmDetail) {
        this.linkAlarmDetail = linkAlarmDetail;
    }

    public String getLinkBusinessType() {
        return linkBusinessType;
    }

    public void setLinkBusinessType(String linkBusinessType) {
        this.linkBusinessType = linkBusinessType;
    }

    public String getLinkCommonfaultNumber() {
        return linkCommonfaultNumber;
    }

    public void setLinkCommonfaultNumber(String linkCommonfaultNumber) {
        this.linkCommonfaultNumber = linkCommonfaultNumber;
    }

    public Date getLinkComplaintSolveDate() {
        return linkComplaintSolveDate;
    }

    public void setLinkComplaintSolveDate(Date linkComplaintSolveDate) {
        this.linkComplaintSolveDate = linkComplaintSolveDate;
    }

    public String getLinkContactPhone() {
        return linkContactPhone;
    }

    public void setLinkContactPhone(String linkContactPhone) {
        this.linkContactPhone = linkContactPhone;
    }

    public String getLinkContactship() {
        return linkContactship;
    }

    public void setLinkContactship(String linkContactship) {
        this.linkContactship = linkContactship;
    }

    public String getLinkContactUser() {
        return linkContactUser;
    }

    public void setLinkContactUser(String linkContactUser) {
        this.linkContactUser = linkContactUser;
    }

    public String getLinkCoverDian() {
        return linkCoverDian;
    }

    public void setLinkCoverDian(String linkCoverDian) {
        this.linkCoverDian = linkCoverDian;
    }

    public String getLinkCoverLian() {
        return linkCoverLian;
    }

    public void setLinkCoverLian(String linkCoverLian) {
        this.linkCoverLian = linkCoverLian;
    }

    public String getLinkDealDesc() {
        return linkDealDesc;
    }

    public void setLinkDealDesc(String linkDealDesc) {
        this.linkDealDesc = linkDealDesc;
    }

    public String getLinkEquipmentFactory() {
        return linkEquipmentFactory;
    }

    public void setLinkEquipmentFactory(String linkEquipmentFactory) {
        this.linkEquipmentFactory = linkEquipmentFactory;
    }

    public String getLinkEquipmentType() {
        return linkEquipmentType;
    }

    public void setLinkEquipmentType(String linkEquipmentType) {
        this.linkEquipmentType = linkEquipmentType;
    }

    public String getLinkFaultType() {
        return linkFaultType;
    }

    public void setLinkFaultType(String linkFaultType) {
        this.linkFaultType = linkFaultType;
    }

    public String getLinkIsAlarm() {
        return linkIsAlarm;
    }

    public void setLinkIsAlarm(String linkIsAlarm) {
        this.linkIsAlarm = linkIsAlarm;
    }

    public String getLinkIsComplaintSolve() {
        return linkIsComplaintSolve;
    }

    public void setLinkIsComplaintSolve(String linkIsComplaintSolve) {
        this.linkIsComplaintSolve = linkIsComplaintSolve;
    }

    public String getLinkIsContactUser() {
        return linkIsContactUser;
    }

    public void setLinkIsContactUser(String linkIsContactUser) {
        this.linkIsContactUser = linkIsContactUser;
    }

    public String getLinkIsReciveFaultId() {
        return linkIsReciveFaultId;
    }

    public void setLinkIsReciveFaultId(String linkIsReciveFaultId) {
        this.linkIsReciveFaultId = linkIsReciveFaultId;
    }

    public String getLinkIsRepeatComplaint() {
        return linkIsRepeatComplaint;
    }

    public void setLinkIsRepeatComplaint(String linkIsRepeatComplaint) {
        this.linkIsRepeatComplaint = linkIsRepeatComplaint;
    }

    public String getLinkIsUserConfirm() {
        return linkIsUserConfirm;
    }

    public void setLinkIsUserConfirm(String linkIsUserConfirm) {
        this.linkIsUserConfirm = linkIsUserConfirm;
    }

    public String getLinkIsUserStatisfied() {
        return linkIsUserStatisfied;
    }

    public void setLinkIsUserStatisfied(String linkIsUserStatisfied) {
        this.linkIsUserStatisfied = linkIsUserStatisfied;
    }

    public String getLinkNoConfirmReason() {
        return linkNoConfirmReason;
    }

    public void setLinkNoConfirmReason(String linkNoConfirmReason) {
        this.linkNoConfirmReason = linkNoConfirmReason;
    }

    public String getLinkNoContactReason() {
        return linkNoContactReason;
    }

    public void setLinkNoContactReason(String linkNoContactReason) {
        this.linkNoContactReason = linkNoContactReason;
    }

    public Date getLinkPlanSolveDate() {
        return linkPlanSolveDate;
    }

    public void setLinkPlanSolveDate(Date linkPlanSolveDate) {
        this.linkPlanSolveDate = linkPlanSolveDate;
    }

    public String getLinkPlanSolveType() {
        return linkPlanSolveType;
    }

    public void setLinkPlanSolveType(String linkPlanSolveType) {
        this.linkPlanSolveType = linkPlanSolveType;
    }

    public String getLinkQuality() {
        return linkQuality;
    }

    public void setLinkQuality(String linkQuality) {
        this.linkQuality = linkQuality;
    }

    public String getLinkReasonType() {
        return linkReasonType;
    }

    public void setLinkReasonType(String linkReasonType) {
        this.linkReasonType = linkReasonType;
    }

    public String getLinkReciveFaultId() {
        return linkReciveFaultId;
    }

    public void setLinkReciveFaultId(String linkReciveFaultId) {
        this.linkReciveFaultId = linkReciveFaultId;
    }

    public String getLinkRepeatComplaintType() {
        return linkRepeatComplaintType;
    }

    public void setLinkRepeatComplaintType(String linkRepeatComplaintType) {
        this.linkRepeatComplaintType = linkRepeatComplaintType;
    }

    public String getLinkReplayPhone() {
        return linkReplayPhone;
    }

    public void setLinkReplayPhone(String linkReplayPhone) {
        this.linkReplayPhone = linkReplayPhone;
    }

    public String getLinkReplyPerson() {
        return linkReplyPerson;
    }

    public void setLinkReplyPerson(String linkReplyPerson) {
        this.linkReplyPerson = linkReplyPerson;
    }

    public String getLinkSpecialty() {
        return linkSpecialty;
    }

    public void setLinkSpecialty(String linkSpecialty) {
        this.linkSpecialty = linkSpecialty;
    }

    public String getLinkUserNoStatisfied() {
        return linkUserNoStatisfied;
    }

    public void setLinkUserNoStatisfied(String linkUserNoStatisfied) {
        this.linkUserNoStatisfied = linkUserNoStatisfied;
    }

    public String getLinkPlanSolveTypeparent() {
        return linkPlanSolveTypeparent;
    }

    public void setLinkPlanSolveTypeparent(String linkPlanSolveTypeparent) {
        this.linkPlanSolveTypeparent = linkPlanSolveTypeparent;
    }

    public Date getLinkContactDate() {
        return linkContactDate;
    }

    public void setLinkContactDate(Date linkContactDate) {
        this.linkContactDate = linkContactDate;
    }

    public String getAiNetReasonDesc() {
        return aiNetReasonDesc;
    }

    public void setAiNetReasonDesc(String aiNetReasonDesc) {
        this.aiNetReasonDesc = aiNetReasonDesc;
    }

    public String getAiNetResult() {
        return aiNetResult;
    }

    public void setAiNetResult(String aiNetResult) {
        this.aiNetResult = aiNetResult;
    }

    public String getLinkIfAiNet() {
        return linkIfAiNet;
    }

    public void setLinkIfAiNet(String linkIfAiNet) {
        this.linkIfAiNet = linkIfAiNet;
    }

    public String getLinkIfMobile() {
        return linkIfMobile;
    }

    public void setLinkIfMobile(String linkIfMobile) {
        this.linkIfMobile = linkIfMobile;
    }

    public String getSelectAiNetReason() {
        return selectAiNetReason;
    }

    public void setSelectAiNetReason(String selectAiNetReason) {
        this.selectAiNetReason = selectAiNetReason;
    }
}
