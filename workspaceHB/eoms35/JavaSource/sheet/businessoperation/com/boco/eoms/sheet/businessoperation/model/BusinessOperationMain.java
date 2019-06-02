
package com.boco.eoms.sheet.businessoperation.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="BusinessOperationMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessOperationmain"
 */
public class BusinessOperationMain extends BaseMain
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
     private java.lang.String mainIsGF;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainDesignSheetId;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainSheetId;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainTask;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainIsGC;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainSummarize;

    /**
	 *
	 * @accesstype
	 */
     private java.lang.String mainExtendAcc;

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
     
     private java.lang.String mainIsSuccess;
     
     private String mainProductTypeTwo;
     
     private Integer mainifrecord; 
     
 	public Integer getMainifrecord() {
 		return mainifrecord;
 	}

 	public void setMainifrecord(Integer mainifrecord) {
 		this.mainifrecord = mainifrecord;
 	}
     
   	 public java.lang.String getMainIsSuccess() {
		return mainIsSuccess;
	}

	public void setMainIsSuccess(java.lang.String mainIsSuccess) {
		this.mainIsSuccess = mainIsSuccess;
	}

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
   	 public java.lang.String getMainIsGF() {
            return mainIsGF;
     }

     public void setMainIsGF(java.lang.String mainIsGF) {
           this.mainIsGF = mainIsGF;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainDesignSheetId() {
            return mainDesignSheetId;
     }

     public void setMainDesignSheetId(java.lang.String mainDesignSheetId) {
           this.mainDesignSheetId = mainDesignSheetId;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainSheetId() {
            return mainSheetId;
     }

     public void setMainSheetId(java.lang.String mainSheetId) {
           this.mainSheetId = mainSheetId;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainTask() {
            return mainTask;
     }

     public void setMainTask(java.lang.String mainTask) {
           this.mainTask = mainTask;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainIsGC() {
            return mainIsGC;
     }

     public void setMainIsGC(java.lang.String mainIsGC) {
           this.mainIsGC = mainIsGC;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainSummarize() {
            return mainSummarize;
     }

     public void setMainSummarize(java.lang.String mainSummarize) {
           this.mainSummarize = mainSummarize;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainExtendAcc() {
            return mainExtendAcc;
     }

     public void setMainExtendAcc(java.lang.String mainExtendAcc) {
           this.mainExtendAcc = mainExtendAcc;
     }

	public String getMainProductTypeTwo() {
		return mainProductTypeTwo;
	}

	public void setMainProductTypeTwo(String mainProductTypeTwo) {
		this.mainProductTypeTwo = mainProductTypeTwo;
	}

}
