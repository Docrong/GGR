
package com.boco.eoms.sheet.emergencydrill.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="EmergencyDrillLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="EmergencyDrilllink"
 */
public class EmergencyDrillLink extends BaseLink
{

    /**
	 * @texttype
	 */
     private java.lang.String linkAuditObject;

    /**
	 * @textarea
	 */
     private java.lang.String linkEmergencyDrillNote;

    /**
	 * @accesstype
	 */
     private java.lang.String linkEmergencyDrill;

    /**
	 * @dicttype
	 */
     private java.lang.String linkAuditOpinion;

    /**
	 * @texttype
	 */
     private java.lang.String linkAuditResult;

    /**
	 * @texttype
	 */
     private java.lang.String linkImplementResult;

    /**
	 * @accesstype
	 */
     private java.lang.String linkDrillReport;

    /**
	 * @texttype
	 */
     private java.lang.String linkImplementSummary;

    /**
	 * @accesstype
	 */
     private java.lang.String linkDrillSummaryReport;

    /**
	 * @dicttype
	 */
     private java.lang.String linkIsAmendmentDrill;

    /**
	 * @texttype
	 */
     private java.lang.String linkAmendmentDrillNote;

    /**
	 * @accesstype
	 */
     private java.lang.String linkEmergencyPreparedness;

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAuditObject() {
            return linkAuditObject;
     }

     public void setLinkAuditObject(java.lang.String linkAuditObject) {
           this.linkAuditObject = linkAuditObject;
     }

	/**
	 * @hibernate.property length="500"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkEmergencyDrillNote() {
            return linkEmergencyDrillNote;
     }

     public void setLinkEmergencyDrillNote(java.lang.String linkEmergencyDrillNote) {
           this.linkEmergencyDrillNote = linkEmergencyDrillNote;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkEmergencyDrill() {
            return linkEmergencyDrill;
     }

     public void setLinkEmergencyDrill(java.lang.String linkEmergencyDrill) {
           this.linkEmergencyDrill = linkEmergencyDrill;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAuditOpinion() {
            return linkAuditOpinion;
     }

     public void setLinkAuditOpinion(java.lang.String linkAuditOpinion) {
           this.linkAuditOpinion = linkAuditOpinion;
     }

	/**
	 * @hibernate.property length="500"
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
	 * @hibernate.property length="500"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkImplementResult() {
            return linkImplementResult;
     }

     public void setLinkImplementResult(java.lang.String linkImplementResult) {
           this.linkImplementResult = linkImplementResult;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkDrillReport() {
            return linkDrillReport;
     }

     public void setLinkDrillReport(java.lang.String linkDrillReport) {
           this.linkDrillReport = linkDrillReport;
     }

	/**
	 * @hibernate.property length="500"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkImplementSummary() {
            return linkImplementSummary;
     }

     public void setLinkImplementSummary(java.lang.String linkImplementSummary) {
           this.linkImplementSummary = linkImplementSummary;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkDrillSummaryReport() {
            return linkDrillSummaryReport;
     }

     public void setLinkDrillSummaryReport(java.lang.String linkDrillSummaryReport) {
           this.linkDrillSummaryReport = linkDrillSummaryReport;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIsAmendmentDrill() {
            return linkIsAmendmentDrill;
     }

     public void setLinkIsAmendmentDrill(java.lang.String linkIsAmendmentDrill) {
           this.linkIsAmendmentDrill = linkIsAmendmentDrill;
     }

	/**
	 * @hibernate.property length="500"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAmendmentDrillNote() {
            return linkAmendmentDrillNote;
     }

     public void setLinkAmendmentDrillNote(java.lang.String linkAmendmentDrillNote) {
           this.linkAmendmentDrillNote = linkAmendmentDrillNote;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkEmergencyPreparedness() {
            return linkEmergencyPreparedness;
     }

     public void setLinkEmergencyPreparedness(java.lang.String linkEmergencyPreparedness) {
           this.linkEmergencyPreparedness = linkEmergencyPreparedness;
     }

}
