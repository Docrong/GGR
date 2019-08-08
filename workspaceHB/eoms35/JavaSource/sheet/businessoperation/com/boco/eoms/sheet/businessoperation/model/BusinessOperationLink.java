
package com.boco.eoms.sheet.businessoperation.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="BusinessOperationLink.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessOperationlink"
 */
public class BusinessOperationLink extends BaseLink {

    /**
     * @textarea
     */
    private java.lang.String linkBusinessDesc;

    /**
     *
     */
    private Date linkOperStartTime;

    /**
     *
     */
    private Date linkOperEndTime;

    /**
     * @accesstype
     */
    private java.lang.String linkOperateScheme;

    /**
     * @dicttype
     */
    private java.lang.String linkTestResult;

    /**
     * @dicttype
     */
    private java.lang.String linkNetType;

    /**
     * @accesstype
     */
    private java.lang.String linkAlterationAcc;

    /**
     * @accesstype
     */
    private java.lang.String linkTGPolicyAcc;

    /**
     * @textarea
     */
    private java.lang.String linkDataFile;

    /**
     * @textarea
     */
    private java.lang.String linkVerify;

    /**
     * @textarea
     */
    private java.lang.String linkPassMan;

    /**
     * @textarea
     */
    private java.lang.String linkReport;

    /**
     * @textarea
     */
    private java.lang.String linkWorkplan;

    /**
     * @accesstype
     */
    private java.lang.String linkMeetingAcc;

    /**
     * @dicttype
     */
    private java.lang.String linkIsSuccess;

    /**
     * @accesstype
     */
    private java.lang.String linkTGReportAcc;

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkBusinessDesc() {
        return linkBusinessDesc;
    }

    public void setLinkBusinessDesc(java.lang.String linkBusinessDesc) {
        this.linkBusinessDesc = linkBusinessDesc;
    }


    public java.lang.String getLinkOperateScheme() {
        return linkOperateScheme;
    }

    public void setLinkOperateScheme(java.lang.String linkOperateScheme) {
        this.linkOperateScheme = linkOperateScheme;
    }

    public Date getLinkOperStartTime() {
        return linkOperStartTime;
    }

    public void setLinkOperStartTime(Date linkOperStartTime) {
        this.linkOperStartTime = linkOperStartTime;
    }

    public Date getLinkOperEndTime() {
        return linkOperEndTime;
    }

    public void setLinkOperEndTime(Date linkOperEndTime) {
        this.linkOperEndTime = linkOperEndTime;
    }

    /**
     * @return
     * @hibernate.property length=""
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
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkNetType() {
        return linkNetType;
    }

    public void setLinkNetType(java.lang.String linkNetType) {
        this.linkNetType = linkNetType;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkAlterationAcc() {
        return linkAlterationAcc;
    }

    public void setLinkAlterationAcc(java.lang.String linkAlterationAcc) {
        this.linkAlterationAcc = linkAlterationAcc;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkTGPolicyAcc() {
        return linkTGPolicyAcc;
    }

    public void setLinkTGPolicyAcc(java.lang.String linkTGPolicyAcc) {
        this.linkTGPolicyAcc = linkTGPolicyAcc;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkDataFile() {
        return linkDataFile;
    }

    public void setLinkDataFile(java.lang.String linkDataFile) {
        this.linkDataFile = linkDataFile;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkVerify() {
        return linkVerify;
    }

    public void setLinkVerify(java.lang.String linkVerify) {
        this.linkVerify = linkVerify;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkPassMan() {
        return linkPassMan;
    }

    public void setLinkPassMan(java.lang.String linkPassMan) {
        this.linkPassMan = linkPassMan;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkReport() {
        return linkReport;
    }

    public void setLinkReport(java.lang.String linkReport) {
        this.linkReport = linkReport;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkWorkplan() {
        return linkWorkplan;
    }

    public void setLinkWorkplan(java.lang.String linkWorkplan) {
        this.linkWorkplan = linkWorkplan;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkMeetingAcc() {
        return linkMeetingAcc;
    }

    public void setLinkMeetingAcc(java.lang.String linkMeetingAcc) {
        this.linkMeetingAcc = linkMeetingAcc;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkIsSuccess() {
        return linkIsSuccess;
    }

    public void setLinkIsSuccess(java.lang.String linkIsSuccess) {
        this.linkIsSuccess = linkIsSuccess;
    }

    /**
     * @return
     * @hibernate.property length=""
     * @eoms.show
     */
    public java.lang.String getLinkTGReportAcc() {
        return linkTGReportAcc;
    }

    public void setLinkTGReportAcc(java.lang.String linkTGReportAcc) {
        this.linkTGReportAcc = linkTGReportAcc;
    }

}
