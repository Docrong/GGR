
package com.boco.eoms.sheet.greatevent.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="GreatEventLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="GreatEventlink"
 */
public class GreatEventLink extends BaseLink
{

    /**
	 * @texttype
	 */
     private java.lang.String linkPermisObject;

    /**
	 * @texttype
	 */
     private java.lang.String linkResReadyResult;

    /**
	 * @accesstype
	 */
     private java.lang.String linkGreatSecurityProgram;

    /**
	 * @dicttype
	 */
     private java.lang.String linkAuditAdvice;

    /**
	 * @textarea
	 */
     private java.lang.String linkAuditResult;

    /**
	 * @dicttype
	 */
     private java.lang.String linkPermisAdvice;

    /**
	 * @textarea
	 */
     private java.lang.String linkPermisResult;

    /**
	 * @textarea
	 */
     private java.lang.String linkSencePerformSummary;

    /**
	 * @accesstype
	 */
     private java.lang.String linkSencePerformReport;

    /**
	 * @dicttype
	 */
     private java.lang.String linkIfStartChangeProcess;
     
     /**
 	 * @dicttype
 	 */
      private java.lang.String linkIfModifyPlans;

    /**
	 * @accesstype
	 */
     private java.lang.String linkSenceSecuritySummary;

    /**
	 * @accesstype
	 */
     private java.lang.String linkSenceSecurityReport;

    /**
	 * @accesstype
	 */
     private java.lang.String linkAssessReport;

    /**
	 * @textarea
	 */
     private java.lang.String linkAmendPlanHelp;
     
    /**
	 * @accesstype
	 */
     private java.lang.String linkEmergencyPlan;

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkPermisObject() {
            return linkPermisObject;
     }

     public void setLinkPermisObject(java.lang.String linkPermisObject) {
           this.linkPermisObject = linkPermisObject;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkResReadyResult() {
            return linkResReadyResult;
     }

     public void setLinkResReadyResult(java.lang.String linkResReadyResult) {
           this.linkResReadyResult = linkResReadyResult;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkGreatSecurityProgram() {
            return linkGreatSecurityProgram;
     }

     public void setLinkGreatSecurityProgram(java.lang.String linkGreatSecurityProgram) {
           this.linkGreatSecurityProgram = linkGreatSecurityProgram;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAuditAdvice() {
            return linkAuditAdvice;
     }

     public void setLinkAuditAdvice(java.lang.String linkAuditAdvice) {
           this.linkAuditAdvice = linkAuditAdvice;
     }

	/**
	 * @hibernate.property length=""
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
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkPermisAdvice() {
            return linkPermisAdvice;
     }

     public void setLinkPermisAdvice(java.lang.String linkPermisAdvice) {
           this.linkPermisAdvice = linkPermisAdvice;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkPermisResult() {
            return linkPermisResult;
     }

     public void setLinkPermisResult(java.lang.String linkPermisResult) {
           this.linkPermisResult = linkPermisResult;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkSencePerformSummary() {
            return linkSencePerformSummary;
     }

     public void setLinkSencePerformSummary(java.lang.String linkSencePerformSummary) {
           this.linkSencePerformSummary = linkSencePerformSummary;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkSencePerformReport() {
            return linkSencePerformReport;
     }

     public void setLinkSencePerformReport(java.lang.String linkSencePerformReport) {
           this.linkSencePerformReport = linkSencePerformReport;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIfStartChangeProcess() {
            return linkIfStartChangeProcess;
     }

     public void setLinkIfStartChangeProcess(java.lang.String linkIfStartChangeProcess) {
           this.linkIfStartChangeProcess = linkIfStartChangeProcess;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkSenceSecuritySummary() {
            return linkSenceSecuritySummary;
     }

     public void setLinkSenceSecuritySummary(java.lang.String linkSenceSecuritySummary) {
           this.linkSenceSecuritySummary = linkSenceSecuritySummary;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkSenceSecurityReport() {
            return linkSenceSecurityReport;
     }

     public void setLinkSenceSecurityReport(java.lang.String linkSenceSecurityReport) {
           this.linkSenceSecurityReport = linkSenceSecurityReport;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAssessReport() {
            return linkAssessReport;
     }

     public void setLinkAssessReport(java.lang.String linkAssessReport) {
           this.linkAssessReport = linkAssessReport;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAmendPlanHelp() {
            return linkAmendPlanHelp;
     }

     public void setLinkAmendPlanHelp(java.lang.String linkAmendPlanHelp) {
           this.linkAmendPlanHelp = linkAmendPlanHelp;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkEmergencyPlan() {
            return linkEmergencyPlan;
     }

     public void setLinkEmergencyPlan(java.lang.String linkEmergencyPlan) {
           this.linkEmergencyPlan = linkEmergencyPlan;
     }

	public java.lang.String getLinkIfModifyPlans() {
		return linkIfModifyPlans;
	}

	public void setLinkIfModifyPlans(java.lang.String linkIfModifyPlans) {
		this.linkIfModifyPlans = linkIfModifyPlans;
	}



}
