
package com.boco.eoms.sheet.emergencydrill.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="EmergencyDrillMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="EmergencyDrillmain"
 */
public class EmergencyDrillMain extends BaseMain
{

    /**
	 *
	 */
     private java.lang.String mainEmergencySortOne;

    /**
	 *
	 */
     private java.lang.String mainEmergencySortTwo;

    /**
	 *
	 * @texttype
	 */
     private Date mainCompleteTime;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainRemarks;

    /**
	 *
	 * @accesstype
	 */
     private java.lang.String mainEmergencyPlan;

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
     /**
	 *
	 * @texttype
	 */
     private java.lang.String mainNetSortOne;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainNetSortTwo;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainNetSortThree;
     
     /**
      * KPI增加字段
      */
     private Integer mainRejectTimes;
 

	public Integer getMainRejectTimes() {
		return mainRejectTimes;
	}

	public void setMainRejectTimes(Integer mainRejectTimes) {
		this.mainRejectTimes = mainRejectTimes;
	}

	public java.lang.String getMainNetSortOne() {
		return mainNetSortOne;
	}

	public void setMainNetSortOne(java.lang.String mainNetSortOne) {
		this.mainNetSortOne = mainNetSortOne;
	}

	public java.lang.String getMainNetSortThree() {
		return mainNetSortThree;
	}

	public void setMainNetSortThree(java.lang.String mainNetSortThree) {
		this.mainNetSortThree = mainNetSortThree;
	}

	public java.lang.String getMainNetSortTwo() {
		return mainNetSortTwo;
	}

	public void setMainNetSortTwo(java.lang.String mainNetSortTwo) {
		this.mainNetSortTwo = mainNetSortTwo;
	}

	public java.lang.String getMainEmergencySortOne() {
            return mainEmergencySortOne;
     }

     public void setMainEmergencySortOne(java.lang.String mainEmergencySortOne) {
           this.mainEmergencySortOne = mainEmergencySortOne;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainEmergencySortTwo() {
            return mainEmergencySortTwo;
     }

     public void setMainEmergencySortTwo(java.lang.String mainEmergencySortTwo) {
           this.mainEmergencySortTwo = mainEmergencySortTwo;
     }

	/**
	 * @hibernate.property value="35"
	 * @eoms.show
	 * @return
	 */
   	 public Date getMainCompleteTime() {
            return mainCompleteTime;
     }

     public void setMainCompleteTime(Date mainCompleteTime) {
           this.mainCompleteTime = mainCompleteTime;
     }

	/**
	 * @hibernate.property value="500"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainRemarks() {
            return mainRemarks;
     }

     public void setMainRemarks(java.lang.String mainRemarks) {
           this.mainRemarks = mainRemarks;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainEmergencyPlan() {
            return mainEmergencyPlan;
     }

     public void setMainEmergencyPlan(java.lang.String mainEmergencyPlan) {
           this.mainEmergencyPlan = mainEmergencyPlan;
     }

}
