
package com.boco.eoms.sheet.greatevent.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="GreatEventMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="GreatEventmain"
 */
public class GreatEventMain extends BaseMain
{

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
	 *
	 */
     private java.util.Date mainEventStartTime;

    /**
	 *
	 */
     private java.util.Date mainEventEndTime;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainEventDesc;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainInitDealResult;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainEventDealAdvice;

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainNetSortOne() {
            return mainNetSortOne;
     }

     public void setMainNetSortOne(java.lang.String mainNetSortOne) {
           this.mainNetSortOne = mainNetSortOne;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainNetSortTwo() {
            return mainNetSortTwo;
     }

     public void setMainNetSortTwo(java.lang.String mainNetSortTwo) {
           this.mainNetSortTwo = mainNetSortTwo;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainNetSortThree() {
            return mainNetSortThree;
     }

     public void setMainNetSortThree(java.lang.String mainNetSortThree) {
           this.mainNetSortThree = mainNetSortThree;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.util.Date getMainEventStartTime() {
            return mainEventStartTime;
     }

     public void setMainEventStartTime(java.util.Date mainEventStartTime) {
           this.mainEventStartTime = mainEventStartTime;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.util.Date getMainEventEndTime() {
            return mainEventEndTime;
     }

     public void setMainEventEndTime(java.util.Date mainEventEndTime) {
           this.mainEventEndTime = mainEventEndTime;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainEventDesc() {
            return mainEventDesc;
     }

     public void setMainEventDesc(java.lang.String mainEventDesc) {
           this.mainEventDesc = mainEventDesc;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainInitDealResult() {
            return mainInitDealResult;
     }

     public void setMainInitDealResult(java.lang.String mainInitDealResult) {
           this.mainInitDealResult = mainInitDealResult;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainEventDealAdvice() {
            return mainEventDealAdvice;
     }

     public void setMainEventDealAdvice(java.lang.String mainEventDealAdvice) {
           this.mainEventDealAdvice = mainEventDealAdvice;
     }

}
