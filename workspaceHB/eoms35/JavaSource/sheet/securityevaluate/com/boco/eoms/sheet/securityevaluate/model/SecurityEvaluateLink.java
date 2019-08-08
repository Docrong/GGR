
package com.boco.eoms.sheet.securityevaluate.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="SecurityEvaluateLink.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="SecurityEvaluatelink"
 */
public class SecurityEvaluateLink extends BaseLink {

    /**
     * @texttype
     */
    private java.lang.String linkAuditObject;

    /**
     * @textarea
     */
    private java.lang.String linkAnalysisResult;

    /**
     * @accesstype
     */
    private java.lang.String linkAnalysisReport;

    /**
     * @texttype
     */
    private java.lang.String linkAuditOpinion;

    /**
     * @texttype
     */
    private java.lang.String linkAuditResult;

    /**
     * @texttype
     */
    private java.lang.String linkIsStartProcess;

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkAuditObject() {
        return linkAuditObject;
    }

    public void setLinkAuditObject(java.lang.String linkAuditObject) {
        this.linkAuditObject = linkAuditObject;
    }

    /**
     * @return
     * @hibernate.property length="2000"
     * @eoms.show
     */
    public java.lang.String getLinkAnalysisResult() {
        return linkAnalysisResult;
    }

    public void setLinkAnalysisResult(java.lang.String linkAnalysisResult) {
        this.linkAnalysisResult = linkAnalysisResult;
    }

    /**
     * @return
     * @hibernate.property length="255"
     * @eoms.show
     */
    public java.lang.String getLinkAnalysisReport() {
        return linkAnalysisReport;
    }

    public void setLinkAnalysisReport(java.lang.String linkAnalysisReport) {
        this.linkAnalysisReport = linkAnalysisReport;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     */
    public java.lang.String getLinkAuditOpinion() {
        return linkAuditOpinion;
    }

    public void setLinkAuditOpinion(java.lang.String linkAuditOpinion) {
        this.linkAuditOpinion = linkAuditOpinion;
    }

    /**
     * @return
     * @hibernate.property length="2000"
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
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getLinkIsStartProcess() {
        return linkIsStartProcess;
    }

    public void setLinkIsStartProcess(java.lang.String linkIsStartProcess) {
        this.linkIsStartProcess = linkIsStartProcess;
    }

}
