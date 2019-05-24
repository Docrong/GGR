
package com.boco.eoms.sheet.processchange.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="ProcessChangeMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ProcessChangemain"
 */
public class ProcessChangeMain extends BaseMain
{

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainProcessType;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainProcess;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainEditReason;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainEditAdvice;
    
     /**
      * 记录驳回次数
      */
     private Integer mainRejectTimes;

	public Integer getMainRejectTimes() {
		return mainRejectTimes;
	}

	public void setMainRejectTimes(Integer mainRejectTimes) {
		this.mainRejectTimes = mainRejectTimes;
	}

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainProcessType() {
            return mainProcessType;
     }

     public void setMainProcessType(java.lang.String mainProcessType) {
           this.mainProcessType = mainProcessType;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainProcess() {
            return mainProcess;
     }

     public void setMainProcess(java.lang.String mainProcess) {
           this.mainProcess = mainProcess;
     }

	/**
	 * @hibernate.property value="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainEditReason() {
            return mainEditReason;
     }

     public void setMainEditReason(java.lang.String mainEditReason) {
           this.mainEditReason = mainEditReason;
     }

	/**
	 * @hibernate.property value="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainEditAdvice() {
            return mainEditAdvice;
     }

     public void setMainEditAdvice(java.lang.String mainEditAdvice) {
           this.mainEditAdvice = mainEditAdvice;
     }

}
