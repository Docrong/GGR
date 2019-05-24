
package com.boco.eoms.sheet.businessplan.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="BusinessPlanLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessPlanlink"
 */
public class BusinessPlanLink extends BaseLink
{


    /**
	 * @texttype
	 */
     private java.lang.String linkAuditPer;

    /**
	 * @textarea
	 */
     private java.lang.String linkAnalyse;

    /**
	 * @accesstype
	 */
     private java.lang.String linkreport;

    /**
	 * @dicttype
	 */
     private java.lang.String linkIsKx;

    /**
	 * @dicttype
	 */
     private java.lang.String linkAuditResult;

    /**
	 * @textarea
	 */
     private java.lang.String linkAuditDesc;

    /**
	 * @textarea
	 */
     private java.lang.String linkOpion;

    /**
	 * @accesstype
	 */
     private java.lang.String linkOpionReport;

 
	/**
	 * @hibernate.property length=""
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
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAnalyse() {
            return linkAnalyse;
     }

     public void setLinkAnalyse(java.lang.String linkAnalyse) {
           this.linkAnalyse = linkAnalyse;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkreport() {
            return linkreport;
     }

     public void setLinkreport(java.lang.String linkreport) {
           this.linkreport = linkreport;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIsKx() {
            return linkIsKx;
     }

     public void setLinkIsKx(java.lang.String linkIsKx) {
           this.linkIsKx = linkIsKx;
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
   	 public java.lang.String getLinkAuditDesc() {
            return linkAuditDesc;
     }

     public void setLinkAuditDesc(java.lang.String linkAuditDesc) {
           this.linkAuditDesc = linkAuditDesc;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkOpion() {
            return linkOpion;
     }

     public void setLinkOpion(java.lang.String linkOpion) {
           this.linkOpion = linkOpion;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkOpionReport() {
            return linkOpionReport;
     }

     public void setLinkOpionReport(java.lang.String linkOpionReport) {
           this.linkOpionReport = linkOpionReport;
     }

}
