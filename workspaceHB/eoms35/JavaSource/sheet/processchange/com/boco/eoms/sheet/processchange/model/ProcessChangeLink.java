
package com.boco.eoms.sheet.processchange.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="ProcessChangeLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ProcessChangelink"
 */
public class ProcessChangeLink extends BaseLink
{

    /**
	 * @dicttype
	 */
     private java.lang.String linkProcessType;

    /**
	 * @dicttype
	 */
     private java.lang.String linkProcess;

    /**
	 * @textarea
	 */
     private java.lang.String linkFrameDesc;

    /**
	 * @accesstype
	 */
     private java.lang.String linkSchemeFrame;

    /**
	 * @textarea
	 */
     private java.lang.String linkSchemeDesc;

    /**
	 * @accesstype
	 */
     private java.lang.String linkChangeScheme;

    /**
	 * @accesstype
	 */
     private java.lang.String linkITChangeScheme;

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
     private java.lang.String linkIfInvoke;

    /**
	 * @textarea
	 */
     private java.lang.String linkReleaseDesc;

    /**
	 * @dicttype
	 */
     private java.lang.String linkOptimizeImpact;

    /**
	 * @textarea
	 */
     private java.lang.String linkEvaluateDesc;

    /**
	 * @accesstype
	 */
     private java.lang.String linkEvaluateAttach;

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkProcessType() {
            return linkProcessType;
     }

     public void setLinkProcessType(java.lang.String linkProcessType) {
           this.linkProcessType = linkProcessType;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkProcess() {
            return linkProcess;
     }

     public void setLinkProcess(java.lang.String linkProcess) {
           this.linkProcess = linkProcess;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkFrameDesc() {
            return linkFrameDesc;
     }

     public void setLinkFrameDesc(java.lang.String linkFrameDesc) {
           this.linkFrameDesc = linkFrameDesc;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkSchemeFrame() {
            return linkSchemeFrame;
     }

     public void setLinkSchemeFrame(java.lang.String linkSchemeFrame) {
           this.linkSchemeFrame = linkSchemeFrame;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkSchemeDesc() {
            return linkSchemeDesc;
     }

     public void setLinkSchemeDesc(java.lang.String linkSchemeDesc) {
           this.linkSchemeDesc = linkSchemeDesc;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkChangeScheme() {
            return linkChangeScheme;
     }

     public void setLinkChangeScheme(java.lang.String linkChangeScheme) {
           this.linkChangeScheme = linkChangeScheme;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkITChangeScheme() {
            return linkITChangeScheme;
     }

     public void setLinkITChangeScheme(java.lang.String linkITChangeScheme) {
           this.linkITChangeScheme = linkITChangeScheme;
     }

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
	 * @hibernate.property length="255"
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
   	 public java.lang.String getLinkIfInvoke() {
            return linkIfInvoke;
     }

     public void setLinkIfInvoke(java.lang.String linkIfInvoke) {
           this.linkIfInvoke = linkIfInvoke;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkReleaseDesc() {
            return linkReleaseDesc;
     }

     public void setLinkReleaseDesc(java.lang.String linkReleaseDesc) {
           this.linkReleaseDesc = linkReleaseDesc;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkOptimizeImpact() {
            return linkOptimizeImpact;
     }

     public void setLinkOptimizeImpact(java.lang.String linkOptimizeImpact) {
           this.linkOptimizeImpact = linkOptimizeImpact;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkEvaluateDesc() {
            return linkEvaluateDesc;
     }

     public void setLinkEvaluateDesc(java.lang.String linkEvaluateDesc) {
           this.linkEvaluateDesc = linkEvaluateDesc;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkEvaluateAttach() {
            return linkEvaluateAttach;
     }

     public void setLinkEvaluateAttach(java.lang.String linkEvaluateAttach) {
           this.linkEvaluateAttach = linkEvaluateAttach;
     }

}
