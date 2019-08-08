/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.groupcomplaint.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupComplaintLink extends BaseLink {


    private String ndeptContact;
    private String ndeptContactPhone;
    private String compProp;
    private String isReplied;
    private String isCorrect;
    private String dealResult;
    private String dealDesc;
    private String affectedAreas;
    private Date issueEliminatTime;
    private String issueEliminatReason;

    private String linkCheckResult;
    private String linkCheckIdea;
    private String linkUntreadReason;
    private String linkTransmitContent;
    private String linkExamineContent;
    private String linkIfDeferResolve;
    private String linkIfInvokeCaseDatabase;
    private String linkTransmitReason;
    private String faultClassification;
    private String faultReason;
    private String gradeSLA;
    private String electricCode;
    private String linkCity;
    private String linkArea;

    private String linkIfElectricCode;//是否存在产品实例标识
    private String linkNoReason;//不存在原因

    private String linkIsNeedBomcAssist;//是否转派业务支撑中心


    public String getAffectedAreas() {
        return affectedAreas;
    }

    public String getLinkArea() {
        return linkArea;
    }

    public void setLinkArea(String linkArea) {
        this.linkArea = linkArea;
    }

    public String getLinkCity() {
        return linkCity;
    }

    public void setLinkCity(String linkCity) {
        this.linkCity = linkCity;
    }

    public void setAffectedAreas(String affectedAreas) {
        this.affectedAreas = affectedAreas;
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

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(String isReplied) {
        this.isReplied = isReplied;
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

    public String getFaultClassification() {
        return faultClassification;
    }

    public void setFaultClassification(String faultClassification) {
        this.faultClassification = faultClassification;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public void setElectricCode(String electricCode) {
        this.electricCode = electricCode;
    }

    public String getFaultReason() {
        return faultReason;
    }

    public void setFaultReason(String faultReason) {
        this.faultReason = faultReason;
    }

    public String getGradeSLA() {
        return gradeSLA;
    }

    public void setGradeSLA(String gradeSLA) {
        this.gradeSLA = gradeSLA;
    }

    public String getLinkIfElectricCode() {
        return linkIfElectricCode;
    }

    public void setLinkIfElectricCode(String linkIfElectricCode) {
        this.linkIfElectricCode = linkIfElectricCode;
    }

    public String getLinkNoReason() {
        return linkNoReason;
    }

    public void setLinkNoReason(String linkNoReason) {
        this.linkNoReason = linkNoReason;
    }

    public String getLinkIsNeedBomcAssist() {
        return linkIsNeedBomcAssist;
    }

    public void setLinkIsNeedBomcAssist(String linkIsNeedBomcAssist) {
        this.linkIsNeedBomcAssist = linkIsNeedBomcAssist;
    }


}
