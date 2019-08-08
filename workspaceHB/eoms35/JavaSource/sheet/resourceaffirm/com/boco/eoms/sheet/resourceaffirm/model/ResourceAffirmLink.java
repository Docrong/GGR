
package com.boco.eoms.sheet.resourceaffirm.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="ResourceAffirmLink.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ResourceAffirmlink"
 */
public class ResourceAffirmLink extends BaseLink {

    /**
     * @texttype
     */
    private java.lang.String ndeptContact;

    /**
     * @texttype
     */
    private java.lang.String ndeptContactPhone;

    /**
     * @texttype
     */
    private java.lang.String dealResult;

    /**
     * @texttype
     */
    private java.lang.String dealDesc;

    /**
     * @texttype
     */
    private java.lang.String netResCapacity;

    /**
     * @texttype
     */
    private java.lang.String expectFinishdays;

    /**
     * @texttype
     */
    private java.lang.String circuitCode;

    /**
     * @texttype
     */
    private java.lang.String clientPgmCapacity;


    private java.lang.String auditResult;
    private java.lang.String linkNetType;

    //集团新规范
    //传输回复
    private java.lang.String investEvaluate;
    private java.lang.String isOccupied;

    public java.lang.String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(java.lang.String auditResult) {
        this.auditResult = auditResult;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getNdeptContact() {
        return ndeptContact;
    }

    public void setNdeptContact(java.lang.String ndeptContact) {
        this.ndeptContact = ndeptContact;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getNdeptContactPhone() {
        return ndeptContactPhone;
    }

    public void setNdeptContactPhone(java.lang.String ndeptContactPhone) {
        this.ndeptContactPhone = ndeptContactPhone;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getDealResult() {
        return dealResult;
    }

    public void setDealResult(java.lang.String dealResult) {
        this.dealResult = dealResult;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getDealDesc() {
        return dealDesc;
    }

    public void setDealDesc(java.lang.String dealDesc) {
        this.dealDesc = dealDesc;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getNetResCapacity() {
        return netResCapacity;
    }

    public void setNetResCapacity(java.lang.String netResCapacity) {
        this.netResCapacity = netResCapacity;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getExpectFinishdays() {
        return expectFinishdays;
    }

    public void setExpectFinishdays(java.lang.String expectFinishdays) {
        this.expectFinishdays = expectFinishdays;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getCircuitCode() {
        return circuitCode;
    }

    public void setCircuitCode(java.lang.String circuitCode) {
        this.circuitCode = circuitCode;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     */
    public java.lang.String getClientPgmCapacity() {
        return clientPgmCapacity;
    }

    public void setClientPgmCapacity(java.lang.String clientPgmCapacity) {
        this.clientPgmCapacity = clientPgmCapacity;
    }

    public java.lang.String getLinkNetType() {
        return linkNetType;
    }

    public void setLinkNetType(java.lang.String linkNetType) {
        this.linkNetType = linkNetType;
    }

    public java.lang.String getInvestEvaluate() {
        return investEvaluate;
    }

    public void setInvestEvaluate(java.lang.String investEvaluate) {
        this.investEvaluate = investEvaluate;
    }

    public java.lang.String getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(java.lang.String isOccupied) {
        this.isOccupied = isOccupied;
    }


}
