
package com.boco.eoms.sheet.itrequirement.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="ITRequirementLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ITRequirementlink"
 */
public class ITRequirementLink extends BaseLink
{

    /**
	 * @dicttype
	 */
     private java.lang.String linkAuditResult;

    /**
	 * @textarea
	 */
     private java.lang.String linkAuditDesc;

    /**
	 * @dicttype
	 */
     private java.lang.String linkChangeType;

    /**
	 * @dicttype
	 */
     private java.lang.String linkAnalysisResult;

    /**
	 * @textarea
	 */
     private java.lang.String linkAnalysisDesc;

    /**
	 * @accesstype
	 */
     private java.lang.String linkTechnicalprogram;

    /**
	 * @texttype
	 */
     private java.lang.String linkDevAmount;

    /**
	 * @texttype
	 */
     private java.lang.String linkAuditPer;

    /**
	 * @textarea
	 */
     private java.lang.String linkHardWareExp;

    /**
	 * @textarea
	 */
     private java.lang.String linkSystemImpact;

    /**
	 * @textarea
	 */
     private java.lang.String linkOtherImpact;

    /**
	 * @textarea
	 */
     private java.lang.String linkRequirementDesc;

    /**
	 * @accesstype
	 */
     private java.lang.String linkRequirementDetail;

    /**
	 * @textarea
	 */
     private java.lang.String linkCompleteDesc;

    /**
	 * @textarea
	 */
     private java.lang.String linkReplyDesc;

    /**
	 * @textarea
	 */
     private java.lang.String linkTestDesc;

    /**
	 * @textarea
	 */
     private java.lang.String linkTempSaveDesc;

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAuditResult() {
            return linkAuditResult;
     }

     public void setLinkAuditResult(java.lang.String linkAuditResult) {
           this.linkAuditResult = linkAuditResult;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAuditDesc() {
            return linkAuditDesc;
     }

     public void setLinkAuditDesc(java.lang.String linkAuditDesc) {
           this.linkAuditDesc = linkAuditDesc;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkChangeType() {
            return linkChangeType;
     }

     public void setLinkChangeType(java.lang.String linkChangeType) {
           this.linkChangeType = linkChangeType;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAnalysisResult() {
            return linkAnalysisResult;
     }

     public void setLinkAnalysisResult(java.lang.String linkAnalysisResult) {
           this.linkAnalysisResult = linkAnalysisResult;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAnalysisDesc() {
            return linkAnalysisDesc;
     }

     public void setLinkAnalysisDesc(java.lang.String linkAnalysisDesc) {
           this.linkAnalysisDesc = linkAnalysisDesc;
     }

	/**
	 * @hibernate.property length="300"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkTechnicalprogram() {
            return linkTechnicalprogram;
     }

     public void setLinkTechnicalprogram(java.lang.String linkTechnicalprogram) {
           this.linkTechnicalprogram = linkTechnicalprogram;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkDevAmount() {
            return linkDevAmount;
     }

     public void setLinkDevAmount(java.lang.String linkDevAmount) {
           this.linkDevAmount = linkDevAmount;
     }

	/**
	 * @hibernate.property length="300"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAuditPer() {
            return linkAuditPer;
     }

     public void setLinkAuditPer(java.lang.String linkAuditPer) {
           this.linkAuditPer = linkAuditPer;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkHardWareExp() {
            return linkHardWareExp;
     }

     public void setLinkHardWareExp(java.lang.String linkHardWareExp) {
           this.linkHardWareExp = linkHardWareExp;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkSystemImpact() {
            return linkSystemImpact;
     }

     public void setLinkSystemImpact(java.lang.String linkSystemImpact) {
           this.linkSystemImpact = linkSystemImpact;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkOtherImpact() {
            return linkOtherImpact;
     }

     public void setLinkOtherImpact(java.lang.String linkOtherImpact) {
           this.linkOtherImpact = linkOtherImpact;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkRequirementDesc() {
            return linkRequirementDesc;
     }

     public void setLinkRequirementDesc(java.lang.String linkRequirementDesc) {
           this.linkRequirementDesc = linkRequirementDesc;
     }

	/**
	 * @hibernate.property length="300"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkRequirementDetail() {
            return linkRequirementDetail;
     }

     public void setLinkRequirementDetail(java.lang.String linkRequirementDetail) {
           this.linkRequirementDetail = linkRequirementDetail;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkCompleteDesc() {
            return linkCompleteDesc;
     }

     public void setLinkCompleteDesc(java.lang.String linkCompleteDesc) {
           this.linkCompleteDesc = linkCompleteDesc;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkReplyDesc() {
            return linkReplyDesc;
     }

     public void setLinkReplyDesc(java.lang.String linkReplyDesc) {
           this.linkReplyDesc = linkReplyDesc;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkTestDesc() {
            return linkTestDesc;
     }

     public void setLinkTestDesc(java.lang.String linkTestDesc) {
           this.linkTestDesc = linkTestDesc;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkTempSaveDesc() {
            return linkTempSaveDesc;
     }

     public void setLinkTempSaveDesc(java.lang.String linkTempSaveDesc) {
           this.linkTempSaveDesc = linkTempSaveDesc;
     }

}
