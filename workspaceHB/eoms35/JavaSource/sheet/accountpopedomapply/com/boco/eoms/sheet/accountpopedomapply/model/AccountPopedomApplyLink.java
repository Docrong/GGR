
package com.boco.eoms.sheet.accountpopedomapply.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="AccountPopedomApplyLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="AccountPopedomApplylink"
 */
public class AccountPopedomApplyLink extends BaseLink
{

    /**
	 * @textarea
	 */
     private java.lang.String applyAttitude;

    /**
	 * @textarea
	 */
     private java.lang.String applyResult;

    /**
	 * @textarea
	 */
     private java.lang.String deResult;

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getApplyAttitude() {
            return applyAttitude;
     }

     public void setApplyAttitude(java.lang.String applyAttitude) {
           this.applyAttitude = applyAttitude;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getApplyResult() {
            return applyResult;
     }

     public void setApplyResult(java.lang.String applyResult) {
           this.applyResult = applyResult;
     }

	/**
	 * @hibernate.property length="2000"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getDeResult() {
            return deResult;
     }

     public void setDeResult(java.lang.String deResult) {
           this.deResult = deResult;
     }

}
