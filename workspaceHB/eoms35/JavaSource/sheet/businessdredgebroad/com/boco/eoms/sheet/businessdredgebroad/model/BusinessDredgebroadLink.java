
package com.boco.eoms.sheet.businessdredgebroad.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="BusinessDredgebroadbroadLink.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessDredgebroadbroadlink"
 */
public class BusinessDredgebroadLink extends BaseLink {

    private String ndeptContact;

    private String ndeptContactPhone;

    private String dealResult;

    private String dealDesc;

    private String apnID;

    private String loginUserName;

    private String loginUserPassWord;

    private String netResCapacity;

    private String expectFinishdays;

    private String circuitCode;

    private String clientPgmCapacity;
    private String auditResult;

    private String rejectReason;
    //新集团规范
    //传输
    private String testReport;

    private java.lang.String linkNetType;

    //从接单到回单时长
    private String swichConfTime;

    public String getApnID() {
        return apnID;
    }

    public void setApnID(String apnID) {
        this.apnID = apnID;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getCircuitCode() {
        return circuitCode;
    }

    public void setCircuitCode(String circuitCode) {
        this.circuitCode = circuitCode;
    }

    public String getClientPgmCapacity() {
        return clientPgmCapacity;
    }

    public void setClientPgmCapacity(String clientPgmCapacity) {
        this.clientPgmCapacity = clientPgmCapacity;
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

    public String getExpectFinishdays() {
        return expectFinishdays;
    }

    public void setExpectFinishdays(String expectFinishdays) {
        this.expectFinishdays = expectFinishdays;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginUserPassWord() {
        return loginUserPassWord;
    }

    public void setLoginUserPassWord(String loginUserPassWord) {
        this.loginUserPassWord = loginUserPassWord;
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

    public String getNetResCapacity() {
        return netResCapacity;
    }

    public void setNetResCapacity(String netResCapacity) {
        this.netResCapacity = netResCapacity;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public java.lang.String getLinkNetType() {
        return linkNetType;
    }

    public void setLinkNetType(java.lang.String linkNetType) {
        this.linkNetType = linkNetType;
    }

    public String getTestReport() {
        return testReport;
    }

    public void setTestReport(String testReport) {
        this.testReport = testReport;
    }

    public String getSwichConfTime() {
        return swichConfTime;
    }

    public void setSwichConfTime(String swichConfTime) {
        this.swichConfTime = swichConfTime;
    }

}
