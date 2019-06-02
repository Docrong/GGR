
package com.boco.eoms.sheet.businesschange.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>s
 * <a href="BusinessChangeLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessChangelink"
 */
public class BusinessChangeLink extends BaseLink
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
     private java.lang.String loginUserNam;

    /**
	 * @texttype
	 */
     private java.lang.String loginUserPas;

    /**
	 * @texttype
	 */
     private java.lang.String netResCapaci;

    /**
	 * @texttype
	 */
     private java.lang.String expectFinish;

    /**
	 * @texttype
	 */
     private java.lang.String circuitCode;

    /**
	 * @texttype
	 */
     private java.lang.String clientPgmCap;
     private java.lang.String rejectReason ;
     private java.lang.String auditResult;
     private java.lang.String apnID;
     private java.lang.String linkNetType;
//   新集团规范
 	//传输
 	private String testReport;
	public String getTestReport() {
		return testReport;
	}

	public void setTestReport(String testReport) {
		this.testReport = testReport;
	}

	public java.lang.String getApnID() {
		return apnID;
	}

	public void setApnID(java.lang.String apnID) {
		this.apnID = apnID;
	}

	public java.lang.String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(java.lang.String auditResult) {
		this.auditResult = auditResult;
	}

	public java.lang.String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(java.lang.String rejectReason) {
		this.rejectReason = rejectReason;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getNdeptContact() {
            return ndeptContact;
     }

     public void setNdeptContact(java.lang.String ndeptContact) {
           this.ndeptContact = ndeptContact;
     }

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getNdeptContactPhone() {
            return ndeptContactPhone;
     }

     public void setNdeptContactPhone(java.lang.String ndeptContactPhone) {
           this.ndeptContactPhone = ndeptContactPhone;
     }

	/**
	 * @hibernate.property length="100"
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
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getDealDesc() {
            return dealDesc;
     }

     public void setDealDesc(java.lang.String dealDesc) {
           this.dealDesc = dealDesc;
     }

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLoginUserNam() {
            return loginUserNam;
     }

     public void setLoginUserNam(java.lang.String loginUserNam) {
           this.loginUserNam = loginUserNam;
     }

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLoginUserPas() {
            return loginUserPas;
     }

     public void setLoginUserPas(java.lang.String loginUserPas) {
           this.loginUserPas = loginUserPas;
     }

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getNetResCapaci() {
            return netResCapaci;
     }

     public void setNetResCapaci(java.lang.String netResCapaci) {
           this.netResCapaci = netResCapaci;
     }

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getExpectFinish() {
            return expectFinish;
     }

     public void setExpectFinish(java.lang.String expectFinish) {
           this.expectFinish = expectFinish;
     }

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCircuitCode() {
            return circuitCode;
     }

     public void setCircuitCode(java.lang.String circuitCode) {
           this.circuitCode = circuitCode;
     }

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getClientPgmCap() {
            return clientPgmCap;
     }

     public void setClientPgmCap(java.lang.String clientPgmCap) {
           this.clientPgmCap = clientPgmCap;
     }

	public java.lang.String getLinkNetType() {
		return linkNetType;
	}

	public void setLinkNetType(java.lang.String linkNetType) {
		this.linkNetType = linkNetType;
	}

}
