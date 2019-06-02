
package com.boco.eoms.sheet.businessbackout.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="BusinessBackoutLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessBackoutlink"
 */
public class BusinessBackoutLink extends BaseLink
{

    /**
	 * @texttype
	 */
     private java.lang.String ndeptContact;

    /**
	 * @texttype
	 */
     private java.lang.String ndeptContactPhone;

    /**
	 * @textarea
	 */
     private java.lang.String dealResult;

    /**
	 * @textarea
	 */
     private java.lang.String dealDesc;
     
     private java.lang.String auditResult;
     
     private java.lang.String rejectReason;
     
     private java.lang.String linkNetType;
     

	public java.lang.String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(java.lang.String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public java.lang.String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(java.lang.String auditResult) {
		this.auditResult = auditResult;
	}

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getDealResult() {
            return dealResult;
     }

     public void setDealResult(java.lang.String dealResult) {
           this.dealResult = dealResult;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getDealDesc() {
            return dealDesc;
     }

     public void setDealDesc(java.lang.String dealDesc) {
           this.dealDesc = dealDesc;
     }

	public java.lang.String getNdeptContact() {
		return ndeptContact;
	}

	public void setNdeptContact(java.lang.String ndeptContact) {
		this.ndeptContact = ndeptContact;
	}

	public java.lang.String getNdeptContactPhone() {
		return ndeptContactPhone;
	}

	public void setNdeptContactPhone(java.lang.String ndeptContactPhone) {
		this.ndeptContactPhone = ndeptContactPhone;
	}

	public java.lang.String getLinkNetType() {
		return linkNetType;
	}

	public void setLinkNetType(java.lang.String linkNetType) {
		this.linkNetType = linkNetType;
	}


 

}
