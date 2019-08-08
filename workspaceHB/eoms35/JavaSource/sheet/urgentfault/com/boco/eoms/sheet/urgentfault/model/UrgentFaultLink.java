/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.urgentfault.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UrgentFaultLink extends BaseLink {

    private String linkIfGreatFault;
    private String linkFaultGenerantPlace;
    private Date linkFaultAvoidTime;
    private String linkAffectTimeLength;
    private String linkAffectOperationDesc;
    private String linkFaultAvoidReport;

    private String linkGreatFaultId;
    private String linkIfAffirm;
    private String linkFaultReportDesc;

    private String linkFaultReason;
    private String linkFaultSubReason;

    public String getLinkAffectOperationDesc() {
        return linkAffectOperationDesc;
    }

    public void setLinkAffectOperationDesc(String linkAffectOperationDesc) {
        this.linkAffectOperationDesc = linkAffectOperationDesc;
    }

    public String getLinkAffectTimeLength() {
        return linkAffectTimeLength;
    }

    public void setLinkAffectTimeLength(String linkAffectTimeLength) {
        this.linkAffectTimeLength = linkAffectTimeLength;
    }

    public String getLinkFaultAvoidReport() {
        return linkFaultAvoidReport;
    }

    public void setLinkFaultAvoidReport(String linkFaultAvoidReport) {
        this.linkFaultAvoidReport = linkFaultAvoidReport;
    }

    public Date getLinkFaultAvoidTime() {
        return linkFaultAvoidTime;
    }

    public void setLinkFaultAvoidTime(Date linkFaultAvoidTime) {
        this.linkFaultAvoidTime = linkFaultAvoidTime;
    }

    public String getLinkFaultGenerantPlace() {
        return linkFaultGenerantPlace;
    }

    public void setLinkFaultGenerantPlace(String linkFaultGenerantPlace) {
        this.linkFaultGenerantPlace = linkFaultGenerantPlace;
    }

    public String getLinkFaultReportDesc() {
        return linkFaultReportDesc;
    }

    public void setLinkFaultReportDesc(String linkFaultReportDesc) {
        this.linkFaultReportDesc = linkFaultReportDesc;
    }

    public String getLinkGreatFaultId() {
        return linkGreatFaultId;
    }

    public void setLinkGreatFaultId(String linkGreatFaultId) {
        this.linkGreatFaultId = linkGreatFaultId;
    }

    public String getLinkIfAffirm() {
        return linkIfAffirm;
    }

    public void setLinkIfAffirm(String linkIfAffirm) {
        this.linkIfAffirm = linkIfAffirm;
    }

    public String getLinkIfGreatFault() {
        return linkIfGreatFault;
    }

    public void setLinkIfGreatFault(String linkIfGreatFault) {
        this.linkIfGreatFault = linkIfGreatFault;
    }

    public String getLinkFaultReason() {
        return linkFaultReason;
    }

    public void setLinkFaultReason(String linkFaultReason) {
        this.linkFaultReason = linkFaultReason;
    }

    public String getLinkFaultSubReason() {
        return linkFaultSubReason;
    }

    public void setLinkFaultSubReason(String linkFaultSubReason) {
        this.linkFaultSubReason = linkFaultSubReason;
    }


}
