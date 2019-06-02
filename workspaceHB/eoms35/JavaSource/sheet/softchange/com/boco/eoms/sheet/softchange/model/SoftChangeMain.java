
package com.boco.eoms.sheet.softchange.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="SoftChangeMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="SoftChangemain"
 */
public class SoftChangeMain extends BaseMain
{

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainNetTypeOne;
     private java.lang.String mainNetTypeTwo;
     private java.lang.String mainNetTypeThree;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainIsSecurity;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainIsConnect;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainFactory;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainApplyReason;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainCellInfo;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainSoftCDKey;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainSoftPatchKey;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainSoftDetail;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainIsAllow;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainAllowKey;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainIsBack;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainChangeSource;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainParentProcessName;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainIsNeedDesign;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String mainDesignId;
     
     private java.lang.Integer mainrejecttimes;
     
     private String mainExecuteEndDate;
     
     private Integer mainifrecord; 
     
     /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainIfDemonstrateCase;
     
     /**
	 *
	 * @texttype
	 */
     private java.lang.String mainCaseKeywords;
     
     /**
      * 新增设计专业字段
      */
     private java.lang.String mainAssortSpeciality;
     //方案制定步骤的字段
     private Date linkCompleteLimitTime;
     private java.lang.String linkDesignKey;
     private java.lang.String linkDesignComment;
     private java.lang.String linkInvolvedProvince;
     private java.lang.String linkInvolvedCity;
     private java.lang.String linkRiskEstimate;
     private java.lang.String linkEffectAnalyse;
     private java.lang.String firstNodeAccessories;
     
     
     private String mainSoftCDKeyOne;
     private String mainSoftCDKeyTwo;
     private String mainSoftPatchKeyOne;
     private String mainSoftPatchKeyTwo;
     
     
     private String maincellinfo1;         
     private String maincellinfo2;         
     private String mainsoftcdkey1;        
     private String mainsoftcdkey2;        
     private String mainsoftpatchkey1;     
     private String mainsoftpatchkey2;     
     private String mainisallow1;          
     private String mainisallow2;          
     private String mainallowkey1;         
     private String mainallowkey2;         
     private String mainsoftdetail1;       
     private String mainsoftdetail2; 

	public String getMainSoftCDKeyOne() {
		return mainSoftCDKeyOne;
	}

	public void setMainSoftCDKeyOne(String mainSoftCDKeyOne) {
		this.mainSoftCDKeyOne = mainSoftCDKeyOne;
	}

	public String getMainSoftCDKeyTwo() {
		return mainSoftCDKeyTwo;
	}

	public void setMainSoftCDKeyTwo(String mainSoftCDKeyTwo) {
		this.mainSoftCDKeyTwo = mainSoftCDKeyTwo;
	}

	public String getMainSoftPatchKeyOne() {
		return mainSoftPatchKeyOne;
	}

	public void setMainSoftPatchKeyOne(String mainSoftPatchKeyOne) {
		this.mainSoftPatchKeyOne = mainSoftPatchKeyOne;
	}

	public String getMainSoftPatchKeyTwo() {
		return mainSoftPatchKeyTwo;
	}

	public void setMainSoftPatchKeyTwo(String mainSoftPatchKeyTwo) {
		this.mainSoftPatchKeyTwo = mainSoftPatchKeyTwo;
	}

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainIsSecurity() {
            return mainIsSecurity;
     }

     public void setMainIsSecurity(java.lang.String mainIsSecurity) {
           this.mainIsSecurity = mainIsSecurity;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainIsConnect() {
            return mainIsConnect;
     }

     public void setMainIsConnect(java.lang.String mainIsConnect) {
           this.mainIsConnect = mainIsConnect;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainFactory() {
            return mainFactory;
     }

     public void setMainFactory(java.lang.String mainFactory) {
           this.mainFactory = mainFactory;
     }

	/**
	 * @hibernate.property value="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainApplyReason() {
            return mainApplyReason;
     }

     public void setMainApplyReason(java.lang.String mainApplyReason) {
           this.mainApplyReason = mainApplyReason;
     }

	/**
	 * @hibernate.property value="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainCellInfo() {
            return mainCellInfo;
     }

     public void setMainCellInfo(java.lang.String mainCellInfo) {
           this.mainCellInfo = mainCellInfo;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainSoftCDKey() {
            return mainSoftCDKey;
     }

     public void setMainSoftCDKey(java.lang.String mainSoftCDKey) {
           this.mainSoftCDKey = mainSoftCDKey;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainSoftPatchKey() {
            return mainSoftPatchKey;
     }

     public void setMainSoftPatchKey(java.lang.String mainSoftPatchKey) {
           this.mainSoftPatchKey = mainSoftPatchKey;
     }

	/**
	 * @hibernate.property value="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainSoftDetail() {
            return mainSoftDetail;
     }

     public void setMainSoftDetail(java.lang.String mainSoftDetail) {
           this.mainSoftDetail = mainSoftDetail;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainIsAllow() {
            return mainIsAllow;
     }

     public void setMainIsAllow(java.lang.String mainIsAllow) {
           this.mainIsAllow = mainIsAllow;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainAllowKey() {
            return mainAllowKey;
     }

     public void setMainAllowKey(java.lang.String mainAllowKey) {
           this.mainAllowKey = mainAllowKey;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainIsBack() {
            return mainIsBack;
     }

     public void setMainIsBack(java.lang.String mainIsBack) {
           this.mainIsBack = mainIsBack;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainChangeSource() {
            return mainChangeSource;
     }

     public void setMainChangeSource(java.lang.String mainChangeSource) {
           this.mainChangeSource = mainChangeSource;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainParentProcessName() {
            return mainParentProcessName;
     }

     public void setMainParentProcessName(java.lang.String mainParentProcessName) {
           this.mainParentProcessName = mainParentProcessName;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainIsNeedDesign() {
            return mainIsNeedDesign;
     }

     public void setMainIsNeedDesign(java.lang.String mainIsNeedDesign) {
           this.mainIsNeedDesign = mainIsNeedDesign;
     }

	/**
	 * @hibernate.property value="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getMainDesignId() {
            return mainDesignId;
     }

     public void setMainDesignId(java.lang.String mainDesignId) {
           this.mainDesignId = mainDesignId;
     }


	/**
	 * @return the mainNetTypeOne
	 */
	public java.lang.String getMainNetTypeOne() {
		return mainNetTypeOne;
	}

	/**
	 * @param mainNetTypeOne the mainNetTypeOne to set
	 */
	public void setMainNetTypeOne(java.lang.String mainNetTypeOne) {
		this.mainNetTypeOne = mainNetTypeOne;
	}

	/**
	 * @return the mainNetTypeThree
	 */
	public java.lang.String getMainNetTypeThree() {
		return mainNetTypeThree;
	}

	/**
	 * @param mainNetTypeThree the mainNetTypeThree to set
	 */
	public void setMainNetTypeThree(java.lang.String mainNetTypeThree) {
		this.mainNetTypeThree = mainNetTypeThree;
	}

	/**
	 * @return the mainNetTypeTwo
	 */
	public java.lang.String getMainNetTypeTwo() {
		return mainNetTypeTwo;
	}

	/**
	 * @param mainNetTypeTwo the mainNetTypeTwo to set
	 */
	public void setMainNetTypeTwo(java.lang.String mainNetTypeTwo) {
		this.mainNetTypeTwo = mainNetTypeTwo;
	}

	public java.lang.Integer getMainrejecttimes() {
		return mainrejecttimes;
	}

	public void setMainrejecttimes(java.lang.Integer mainrejecttimes) {
		this.mainrejecttimes = mainrejecttimes;
	}

	public String getMainExecuteEndDate() {
		return mainExecuteEndDate;
	}

	public void setMainExecuteEndDate(String mainExecuteEndDate) {
		this.mainExecuteEndDate = mainExecuteEndDate;
	}

	public Integer getMainifrecord() {
		return mainifrecord;
	}

	public void setMainifrecord(Integer mainifrecord) {
		this.mainifrecord = mainifrecord;
	}

	public java.lang.String getMainCaseKeywords() {
		return mainCaseKeywords;
	}

	public void setMainCaseKeywords(java.lang.String mainCaseKeywords) {
		this.mainCaseKeywords = mainCaseKeywords;
	}

	public java.lang.String getMainIfDemonstrateCase() {
		return mainIfDemonstrateCase;
	}

	public void setMainIfDemonstrateCase(java.lang.String mainIfDemonstrateCase) {
		this.mainIfDemonstrateCase = mainIfDemonstrateCase;
	}

	public java.lang.String getFirstNodeAccessories() {
		return firstNodeAccessories;
	}

	public void setFirstNodeAccessories(java.lang.String firstNodeAccessories) {
		this.firstNodeAccessories = firstNodeAccessories;
	}

	public Date getLinkCompleteLimitTime() {
		return linkCompleteLimitTime;
	}

	public void setLinkCompleteLimitTime(Date linkCompleteLimitTime) {
		this.linkCompleteLimitTime = linkCompleteLimitTime;
	}

	public java.lang.String getLinkDesignComment() {
		return linkDesignComment;
	}

	public void setLinkDesignComment(java.lang.String linkDesignComment) {
		this.linkDesignComment = linkDesignComment;
	}

	public java.lang.String getLinkDesignKey() {
		return linkDesignKey;
	}

	public void setLinkDesignKey(java.lang.String linkDesignKey) {
		this.linkDesignKey = linkDesignKey;
	}

	public java.lang.String getLinkEffectAnalyse() {
		return linkEffectAnalyse;
	}

	public void setLinkEffectAnalyse(java.lang.String linkEffectAnalyse) {
		this.linkEffectAnalyse = linkEffectAnalyse;
	}

	public java.lang.String getLinkInvolvedCity() {
		return linkInvolvedCity;
	}

	public void setLinkInvolvedCity(java.lang.String linkInvolvedCity) {
		this.linkInvolvedCity = linkInvolvedCity;
	}

	public java.lang.String getLinkInvolvedProvince() {
		return linkInvolvedProvince;
	}

	public void setLinkInvolvedProvince(java.lang.String linkInvolvedProvince) {
		this.linkInvolvedProvince = linkInvolvedProvince;
	}

	public java.lang.String getLinkRiskEstimate() {
		return linkRiskEstimate;
	}

	public void setLinkRiskEstimate(java.lang.String linkRiskEstimate) {
		this.linkRiskEstimate = linkRiskEstimate;
	}

	public java.lang.String getMainAssortSpeciality() {
		return mainAssortSpeciality;
	}

	public void setMainAssortSpeciality(java.lang.String mainAssortSpeciality) {
		this.mainAssortSpeciality = mainAssortSpeciality;
	}

	public String getMainallowkey1() {
		return mainallowkey1;
	}

	public void setMainallowkey1(String mainallowkey1) {
		this.mainallowkey1 = mainallowkey1;
	}

	public String getMainallowkey2() {
		return mainallowkey2;
	}

	public void setMainallowkey2(String mainallowkey2) {
		this.mainallowkey2 = mainallowkey2;
	}

	public String getMaincellinfo1() {
		return maincellinfo1;
	}

	public void setMaincellinfo1(String maincellinfo1) {
		this.maincellinfo1 = maincellinfo1;
	}

	public String getMaincellinfo2() {
		return maincellinfo2;
	}

	public void setMaincellinfo2(String maincellinfo2) {
		this.maincellinfo2 = maincellinfo2;
	}

	public String getMainisallow1() {
		return mainisallow1;
	}

	public void setMainisallow1(String mainisallow1) {
		this.mainisallow1 = mainisallow1;
	}

	public String getMainisallow2() {
		return mainisallow2;
	}

	public void setMainisallow2(String mainisallow2) {
		this.mainisallow2 = mainisallow2;
	}

	public String getMainsoftcdkey1() {
		return mainsoftcdkey1;
	}

	public void setMainsoftcdkey1(String mainsoftcdkey1) {
		this.mainsoftcdkey1 = mainsoftcdkey1;
	}

	public String getMainsoftcdkey2() {
		return mainsoftcdkey2;
	}

	public void setMainsoftcdkey2(String mainsoftcdkey2) {
		this.mainsoftcdkey2 = mainsoftcdkey2;
	}

	public String getMainsoftdetail1() {
		return mainsoftdetail1;
	}

	public void setMainsoftdetail1(String mainsoftdetail1) {
		this.mainsoftdetail1 = mainsoftdetail1;
	}

	public String getMainsoftdetail2() {
		return mainsoftdetail2;
	}

	public void setMainsoftdetail2(String mainsoftdetail2) {
		this.mainsoftdetail2 = mainsoftdetail2;
	}

	public String getMainsoftpatchkey1() {
		return mainsoftpatchkey1;
	}

	public void setMainsoftpatchkey1(String mainsoftpatchkey1) {
		this.mainsoftpatchkey1 = mainsoftpatchkey1;
	}

	public String getMainsoftpatchkey2() {
		return mainsoftpatchkey2;
	}

	public void setMainsoftpatchkey2(String mainsoftpatchkey2) {
		this.mainsoftpatchkey2 = mainsoftpatchkey2;
	}

}
