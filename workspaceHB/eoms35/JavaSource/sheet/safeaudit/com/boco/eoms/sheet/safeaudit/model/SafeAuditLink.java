
package com.boco.eoms.sheet.safeaudit.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="SafeAuditLink.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="SafeAuditlink"
 */
public class SafeAuditLink extends BaseLink {

    /**
     * @textarea
     */
    private java.lang.String linkPresentDescribe;

    /**
     * @textarea
     */
    private java.lang.String linkPresentReport;

    /**
     * @texttype
     */
    private java.lang.String linkCheckObject;

    /**
     * @accesstype
     */
    private java.lang.String linkAuditReport;

    /**
     * @dicttype
     */
    private java.lang.String linkCheckIdeas;

    /**
     * @textarea
     */
    private java.lang.String linkCheckResult;

    /**
     * @textarea
     */
    private java.lang.String linkUntreadReason;

    /**
     * @textarea
     */
    private java.lang.String LinkIfStartSecurityDeal;

    public java.lang.String getLinkIfStartSecurityDeal() {
        return LinkIfStartSecurityDeal;
    }

    public void setLinkIfStartSecurityDeal(java.lang.String linkIfStartSecurityDeal) {
        LinkIfStartSecurityDeal = linkIfStartSecurityDeal;
    }

    /**
     * @return
     * @hibernate.property length="2000"
     * @eoms.show
     */
    public java.lang.String getLinkPresentDescribe() {
        return linkPresentDescribe;
    }

    public void setLinkPresentDescribe(java.lang.String linkPresentDescribe) {
        this.linkPresentDescribe = linkPresentDescribe;
    }

    /**
     * @return
     * @hibernate.property length="2000"
     * @eoms.show
     */
    public java.lang.String getLinkPresentReport() {
        return linkPresentReport;
    }

    public void setLinkPresentReport(java.lang.String linkPresentReport) {
        this.linkPresentReport = linkPresentReport;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     */
    public java.lang.String getLinkCheckObject() {
        return linkCheckObject;
    }

    public void setLinkCheckObject(java.lang.String linkCheckObject) {
        this.linkCheckObject = linkCheckObject;
    }

    /**
     * @return
     * @hibernate.property length="300"
     * @eoms.show
     */
    public java.lang.String getLinkAuditReport() {
        return linkAuditReport;
    }

    public void setLinkAuditReport(java.lang.String linkAuditReport) {
        this.linkAuditReport = linkAuditReport;
    }

    /**
     * @return
     * @hibernate.property length="5"
     * @eoms.show
     */
    public java.lang.String getLinkCheckIdeas() {
        return linkCheckIdeas;
    }

    public void setLinkCheckIdeas(java.lang.String linkCheckIdeas) {
        this.linkCheckIdeas = linkCheckIdeas;
    }

    /**
     * @return
     * @hibernate.property length="300"
     * @eoms.show
     */
    public java.lang.String getLinkCheckResult() {
        return linkCheckResult;
    }

    public void setLinkCheckResult(java.lang.String linkCheckResult) {
        this.linkCheckResult = linkCheckResult;
    }

    /**
     * @return
     * @hibernate.property length="3000"
     * @eoms.show
     */
    public java.lang.String getLinkUntreadReason() {
        return linkUntreadReason;
    }

    public void setLinkUntreadReason(java.lang.String linkUntreadReason) {
        this.linkUntreadReason = linkUntreadReason;
    }

}
