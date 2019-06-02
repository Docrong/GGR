
package com.boco.eoms.sheet.businessplan.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="BusinessPlanMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessPlanmain"
 */
public class BusinessPlanMain extends BaseMain
{

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainProductType;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainProductName;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainProductCode;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainReqType;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainTecDesc;

    /**
	 *
	 * @accesstype
	 */
     private java.lang.String mainStandard;
     
     private String mainProductTypeTwo;


    private Integer mainifrecord; 
    
	public Integer getMainifrecord() {
		return mainifrecord;
	}

	public void setMainifrecord(Integer mainifrecord) {
		this.mainifrecord = mainifrecord;
	}

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainProductType() {
            return mainProductType;
     }

     public void setMainProductType(java.lang.String mainProductType) {
           this.mainProductType = mainProductType;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainProductName() {
            return mainProductName;
     }

     public void setMainProductName(java.lang.String mainProductName) {
           this.mainProductName = mainProductName;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainProductCode() {
            return mainProductCode;
     }

     public void setMainProductCode(java.lang.String mainProductCode) {
           this.mainProductCode = mainProductCode;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainReqType() {
            return mainReqType;
     }

     public void setMainReqType(java.lang.String mainReqType) {
           this.mainReqType = mainReqType;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainTecDesc() {
            return mainTecDesc;
     }

     public void setMainTecDesc(java.lang.String mainTecDesc) {
           this.mainTecDesc = mainTecDesc;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainStandard() {
            return mainStandard;
     }

     public void setMainStandard(java.lang.String mainStandard) {
           this.mainStandard = mainStandard;
     }

	public String getMainProductTypeTwo() {
		return mainProductTypeTwo;
	}

	public void setMainProductTypeTwo(String mainProductTypeTwo) {
		this.mainProductTypeTwo = mainProductTypeTwo;
	}

}
