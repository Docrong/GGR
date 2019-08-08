
package com.boco.eoms.sheet.itsoftchange.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="ITSoftChangeLink.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ITSoftChangelink"
 */
public class ITSoftChangeLink extends BaseLink {

    /**
     * @texttype
     */
    private java.lang.String linkDevContacter;

    /**
     * @textarea
     */
    private java.lang.String linkCompleteDesc;

    /**
     * @accesstype
     */
    private java.lang.String linkTestGuide;

    /**
     * @dicttype
     */
    private java.lang.String linkTestResult;

    /**
     * @dicttype
     */
    private java.lang.String linkTestSatisfaction;

    /**
     * @textarea
     */
    private java.lang.String linkTestDesc;

    /**
     * @accesstype
     */
    private java.lang.String linkTestNote;

    /**
     *
     */
    private java.util.Date linkOnlineTime;

    /**
     * @textarea
     */
    private java.lang.String linkOnlineDesc;

    /**
     * @accesstype
     */
    private java.lang.String linkUserNoteDesc;

    /**
     * @dicttype
     */
    private java.lang.String linkAuditResult;

    /**
     * @texttype
     */
    private java.lang.String linkAnalysisCount;

    /**
     * @texttype
     */
    private java.lang.String linkTestCount;

    /**
     * @texttype
     */
    private java.lang.String linkDevCount;

    /**
     * @textarea
     */
    private java.lang.String linkMakerTestResult;

    /**
     * @textarea
     */
    private java.lang.String linkImpactElseDCL;

    /**
     * @textarea
     */
    private java.lang.String linkIfNotifyInterfixSystem;

    /**
     * @textarea
     */
    private java.lang.String linkSoftEdition;

    /**
     * @textarea
     */
    private java.lang.String linkSoftEditionDCL;

    /**
     * @textarea
     */
    private java.lang.String linkUserNoteChangeDCL;

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkDevContacter() {
        return linkDevContacter;
    }

    public void setLinkDevContacter(java.lang.String linkDevContacter) {
        this.linkDevContacter = linkDevContacter;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     */
    public java.lang.String getLinkCompleteDesc() {
        return linkCompleteDesc;
    }

    public void setLinkCompleteDesc(java.lang.String linkCompleteDesc) {
        this.linkCompleteDesc = linkCompleteDesc;
    }

    /**
     * @return
     * @hibernate.property length="300"
     * @eoms.show
     */
    public java.lang.String getLinkTestGuide() {
        return linkTestGuide;
    }

    public void setLinkTestGuide(java.lang.String linkTestGuide) {
        this.linkTestGuide = linkTestGuide;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkTestResult() {
        return linkTestResult;
    }

    public void setLinkTestResult(java.lang.String linkTestResult) {
        this.linkTestResult = linkTestResult;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkTestSatisfaction() {
        return linkTestSatisfaction;
    }

    public void setLinkTestSatisfaction(java.lang.String linkTestSatisfaction) {
        this.linkTestSatisfaction = linkTestSatisfaction;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     */
    public java.lang.String getLinkTestDesc() {
        return linkTestDesc;
    }

    public void setLinkTestDesc(java.lang.String linkTestDesc) {
        this.linkTestDesc = linkTestDesc;
    }

    /**
     * @return
     * @hibernate.property length="300"
     * @eoms.show
     */
    public java.lang.String getLinkTestNote() {
        return linkTestNote;
    }

    public void setLinkTestNote(java.lang.String linkTestNote) {
        this.linkTestNote = linkTestNote;
    }


    public java.util.Date getLinkOnlineTime() {
        return linkOnlineTime;
    }

    public void setLinkOnlineTime(java.util.Date linkOnlineTime) {
        this.linkOnlineTime = linkOnlineTime;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     */
    public java.lang.String getLinkOnlineDesc() {
        return linkOnlineDesc;
    }

    public void setLinkOnlineDesc(java.lang.String linkOnlineDesc) {
        this.linkOnlineDesc = linkOnlineDesc;
    }

    /**
     * @return
     * @hibernate.property length="300"
     * @eoms.show
     */
    public java.lang.String getLinkUserNoteDesc() {
        return linkUserNoteDesc;
    }

    public void setLinkUserNoteDesc(java.lang.String linkUserNoteDesc) {
        this.linkUserNoteDesc = linkUserNoteDesc;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkAuditResult() {
        return linkAuditResult;
    }

    public void setLinkAuditResult(java.lang.String linkAuditResult) {
        this.linkAuditResult = linkAuditResult;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkAnalysisCount() {
        return linkAnalysisCount;
    }

    public void setLinkAnalysisCount(java.lang.String linkAnalysisCount) {
        this.linkAnalysisCount = linkAnalysisCount;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkTestCount() {
        return linkTestCount;
    }

    public void setLinkTestCount(java.lang.String linkTestCount) {
        this.linkTestCount = linkTestCount;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkDevCount() {
        return linkDevCount;
    }

    public void setLinkDevCount(java.lang.String linkDevCount) {
        this.linkDevCount = linkDevCount;
    }

    public java.lang.String getLinkIfNotifyInterfixSystem() {
        return linkIfNotifyInterfixSystem;
    }

    public void setLinkIfNotifyInterfixSystem(
            java.lang.String linkIfNotifyInterfixSystem) {
        this.linkIfNotifyInterfixSystem = linkIfNotifyInterfixSystem;
    }

    public java.lang.String getLinkImpactElseDCL() {
        return linkImpactElseDCL;
    }

    public void setLinkImpactElseDCL(java.lang.String linkImpactElseDCL) {
        this.linkImpactElseDCL = linkImpactElseDCL;
    }

    public java.lang.String getLinkMakerTestResult() {
        return linkMakerTestResult;
    }

    public void setLinkMakerTestResult(java.lang.String linkMakerTestResult) {
        this.linkMakerTestResult = linkMakerTestResult;
    }

    public java.lang.String getLinkSoftEdition() {
        return linkSoftEdition;
    }

    public void setLinkSoftEdition(java.lang.String linkSoftEdition) {
        this.linkSoftEdition = linkSoftEdition;
    }

    public java.lang.String getLinkSoftEditionDCL() {
        return linkSoftEditionDCL;
    }

    public void setLinkSoftEditionDCL(java.lang.String linkSoftEditionDCL) {
        this.linkSoftEditionDCL = linkSoftEditionDCL;
    }

    public java.lang.String getLinkUserNoteChangeDCL() {
        return linkUserNoteChangeDCL;
    }

    public void setLinkUserNoteChangeDCL(java.lang.String linkUserNoteChangeDCL) {
        this.linkUserNoteChangeDCL = linkUserNoteChangeDCL;
    }

}
