
package com.boco.eoms.sheet.commontask.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;
 
/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="CommonTaskLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="CommonTasklink"
 */
public class CommonTaskLink extends BaseLink
{

    /**
	 * @textarea
	 */
     private java.lang.String linkAuditResult;

    /**
	 * @textarea
	 */
     private java.lang.String linkAuditIdea;

    /**
	 * @textarea
	 */
     private java.lang.String linkTaskComplete;

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
   	 public java.lang.String getLinkAuditIdea() {
            return linkAuditIdea;
     }

     public void setLinkAuditIdea(java.lang.String linkAuditIdea) {
           this.linkAuditIdea = linkAuditIdea;
     }

	/**
	 * @hibernate.property length=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkTaskComplete() {
            return linkTaskComplete;
     }

     public void setLinkTaskComplete(java.lang.String linkTaskComplete) {
           this.linkTaskComplete = linkTaskComplete;
     }

}
