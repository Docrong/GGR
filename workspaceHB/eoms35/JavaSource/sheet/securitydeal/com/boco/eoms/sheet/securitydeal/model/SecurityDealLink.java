
package com.boco.eoms.sheet.securitydeal.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="SecurityDealLink.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="SecurityDeallink"
 */
public class SecurityDealLink extends BaseLink {

    /**
     * @textarea
     */
    private java.lang.String linkSecurityInproveAdvice;

    /**
     * @accesstype
     */
    private java.lang.String linkSecurityInproveProgram;

    /**
     * @textarea
     */
    private java.lang.String linkAuditViews;

    /**
     * @textarea
     */
    private java.lang.String linkAuditResult;

    /**
     * @textarea
     */
    private java.lang.String linkPerformResult;

    /**
     * @textarea
     */
    private java.lang.String linkIfStartChangeProcess;

    /**
     * @hibernate.property length="2000"
     * @eoms.show
     * @return
     */

    private java.lang.String operaterContact;

    public java.lang.String getOperaterContact() {
        return operaterContact;
    }

    public void setOperaterContact(java.lang.String operaterContact) {
        this.operaterContact = operaterContact;
    }

    public java.lang.String getLinkSecurityInproveAdvice() {
        return linkSecurityInproveAdvice;
    }

    public void setLinkSecurityInproveAdvice(java.lang.String linkSecurityInproveAdvice) {
        this.linkSecurityInproveAdvice = linkSecurityInproveAdvice;
    }

    /**
     * @return
     * @hibernate.property length="255"
     * @eoms.show
     */
    public java.lang.String getLinkSecurityInproveProgram() {
        return linkSecurityInproveProgram;
    }

    public void setLinkSecurityInproveProgram(java.lang.String linkSecurityInproveProgram) {
        this.linkSecurityInproveProgram = linkSecurityInproveProgram;
    }

    /**
     * @return
     * @hibernate.property length="30"
     * @eoms.show
     */
    public java.lang.String getLinkAuditViews() {
        return linkAuditViews;
    }

    public void setLinkAuditViews(java.lang.String linkAuditViews) {
        this.linkAuditViews = linkAuditViews;
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
     * @hibernate.property length="2000"
     * @eoms.show
     */
    public java.lang.String getLinkPerformResult() {
        return linkPerformResult;
    }

    public void setLinkPerformResult(java.lang.String linkPerformResult) {
        this.linkPerformResult = linkPerformResult;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getLinkIfStartChangeProcess() {
        return linkIfStartChangeProcess;
    }

    public void setLinkIfStartChangeProcess(java.lang.String linkIfStartChangeProcess) {
        this.linkIfStartChangeProcess = linkIfStartChangeProcess;
    }

}
