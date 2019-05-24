
package com.boco.eoms.sheet.netdata.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseSubLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="NetDataLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="NetDatalink"
 */
public class NetDataLink extends BaseLink
{

    /**
	 * @texttype
	 */
     private java.util.Date linkCompleteLimitTime;
     
     
     /**
      * @texttype
      */
     private java.lang.String operaterContact;

    /**
	 * @textarea
	 */
     private java.lang.String linkDesignComment;

    /**
	 * @textarea
	 */
     private java.lang.String linkInvolvedProvince;

    /**
	 * @textarea
	 */
     private java.lang.String linkInvolvedCity;

    /**
	 * @texttype
	 */
     private java.lang.String linkDesignKey;

    /**
	 * @textarea
	 */
     private java.lang.String linkRiskEstimate;

    /**
	 * @textarea
	 */
     private java.lang.String linkEffectAnalyse;

    /**
	 * @dicttype
	 */
     private java.lang.String linkIsCheck;

    /**
	 * @textarea
	 */
     private java.lang.String linkCheckComment;

    /**
	 * @dicttype
	 */
     private java.lang.String linkPermitResult;

    /**
	 * @textarea
	 */
     private java.lang.String linkPermitIdea;

    /**
	 * @texttype
	 */
     private java.lang.String linkManager;

    /**
	 * @texttype
	 */
     private java.lang.String linkContact;

    /**
	 * @texttype
	 */
     private java.util.Date linkPlanStartTime;

    /**
	 * @texttype
	 */
     private java.util.Date linkPlanEndTime;

    /**
	 * @textarea
	 */
     private java.lang.String linkCellInfo;

    /**
	 * @dicttype
	 */
     private java.lang.String linkIsEffectBusiness;

    /**
	 * @textarea
	 */
     private java.lang.String linkEffectCondition;

    /**
	 * @textarea
	 */
     private java.lang.String linkNetManage;

    /**
	 * @dicttype
	 */
     private java.lang.String linkBusinessDept;

    /**
	 * @dicttype
	 */
     private java.lang.String linkIsSendToFront;

    /**
	 * @dicttype
	 */
     private java.lang.String linkCutResult;

    /**
	 * @dicttype
	 */
     private java.lang.String linkIsPlan;

    /**
	 * @textarea
	 */
     private java.lang.String linkCutComment;

    /**
	 * @textarea
	 */
     private java.lang.String linkBusinessComment;

    /**
	 * @dicttype
	 */
     private java.lang.String linkTestResult;

    /**
	 * @textarea
	 */
     private java.lang.String linkAlertRecord;

    /**
	 * @textarea
	 */
     private java.lang.String linkUnnormalComment;

    /**
	 * @textarea
	 */
     private java.lang.String linkCutAnalyse;

    /**
	 * @dicttype
	 */
     private java.lang.String linkIsUpdateWork;

    /**
	 * @textarea
	 */
     private java.lang.String linkWorkUpdateAdvice;

    /**
	 * @textarea
	 */
     private java.lang.String linkSoftVersionUpdate;

    /**
	 * @textarea
	 */
     private java.lang.String linkNextPlan;

    /**
	 */
     private java.lang.String linkIsNeedProject;

    /**
	 * @textarea
	 */
     private java.lang.String linkProjectComment;
     

     /**
 	 * @textarea
 	 */
      private java.lang.String linkFailedReason;
      
      /**
  	 * @dicttype
  	 */
      private java.lang.String linkIfStartOperationTest;
       
       /**
   	 * @texttype
   	 */
      private java.lang.String linkTestPerson;
        
    
      private java.lang.String linkTestReport;
      
      private java.lang.String linkDataCheck;
      
      private java.lang.String linkCheckExplain; 
        
       
       

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.util.Date getLinkCompleteLimitTime() {
            return linkCompleteLimitTime;
     }

     public void setLinkCompleteLimitTime(java.util.Date linkCompleteLimitTime) {
           this.linkCompleteLimitTime = linkCompleteLimitTime;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkDesignComment() {
            return linkDesignComment;
     }

     public void setLinkDesignComment(java.lang.String linkDesignComment) {
           this.linkDesignComment = linkDesignComment;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkInvolvedProvince() {
            return linkInvolvedProvince;
     }

     public void setLinkInvolvedProvince(java.lang.String linkInvolvedProvince) {
           this.linkInvolvedProvince = linkInvolvedProvince;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkInvolvedCity() {
            return linkInvolvedCity;
     }

     public void setLinkInvolvedCity(java.lang.String linkInvolvedCity) {
           this.linkInvolvedCity = linkInvolvedCity;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkDesignKey() {
            return linkDesignKey;
     }

     public void setLinkDesignKey(java.lang.String linkDesignKey) {
           this.linkDesignKey = linkDesignKey;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkRiskEstimate() {
            return linkRiskEstimate;
     }

     public void setLinkRiskEstimate(java.lang.String linkRiskEstimate) {
           this.linkRiskEstimate = linkRiskEstimate;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkEffectAnalyse() {
            return linkEffectAnalyse;
     }

     public void setLinkEffectAnalyse(java.lang.String linkEffectAnalyse) {
           this.linkEffectAnalyse = linkEffectAnalyse;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIsCheck() {
            return linkIsCheck;
     }

     public void setLinkIsCheck(java.lang.String linkIsCheck) {
           this.linkIsCheck = linkIsCheck;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkCheckComment() {
            return linkCheckComment;
     }

     public void setLinkCheckComment(java.lang.String linkCheckComment) {
           this.linkCheckComment = linkCheckComment;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkPermitResult() {
            return linkPermitResult;
     }

     public void setLinkPermitResult(java.lang.String linkPermitResult) {
           this.linkPermitResult = linkPermitResult;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkPermitIdea() {
            return linkPermitIdea;
     }

     public void setLinkPermitIdea(java.lang.String linkPermitIdea) {
           this.linkPermitIdea = linkPermitIdea;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkManager() {
            return linkManager;
     }

     public void setLinkManager(java.lang.String linkManager) {
           this.linkManager = linkManager;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkContact() {
            return linkContact;
     }

     public void setLinkContact(java.lang.String linkContact) {
           this.linkContact = linkContact;
     }

	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.util.Date getLinkPlanStartTime() {
            return linkPlanStartTime;
     }

     public void setLinkPlanStartTime(java.util.Date linkPlanStartTime) {
           this.linkPlanStartTime = linkPlanStartTime;
     }

	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.util.Date getLinkPlanEndTime() {
            return linkPlanEndTime;
     }

     public void setLinkPlanEndTime(java.util.Date linkPlanEndTime) {
           this.linkPlanEndTime = linkPlanEndTime;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkCellInfo() {
            return linkCellInfo;
     }

     public void setLinkCellInfo(java.lang.String linkCellInfo) {
           this.linkCellInfo = linkCellInfo;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIsEffectBusiness() {
            return linkIsEffectBusiness;
     }

     public void setLinkIsEffectBusiness(java.lang.String linkIsEffectBusiness) {
           this.linkIsEffectBusiness = linkIsEffectBusiness;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkEffectCondition() {
            return linkEffectCondition;
     }

     public void setLinkEffectCondition(java.lang.String linkEffectCondition) {
           this.linkEffectCondition = linkEffectCondition;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkNetManage() {
            return linkNetManage;
     }

     public void setLinkNetManage(java.lang.String linkNetManage) {
           this.linkNetManage = linkNetManage;
     }

	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkBusinessDept() {
            return linkBusinessDept;
     }

     public void setLinkBusinessDept(java.lang.String linkBusinessDept) {
           this.linkBusinessDept = linkBusinessDept;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIsSendToFront() {
            return linkIsSendToFront;
     }

     public void setLinkIsSendToFront(java.lang.String linkIsSendToFront) {
           this.linkIsSendToFront = linkIsSendToFront;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkCutResult() {
            return linkCutResult;
     }

     public void setLinkCutResult(java.lang.String linkCutResult) {
           this.linkCutResult = linkCutResult;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIsPlan() {
            return linkIsPlan;
     }

     public void setLinkIsPlan(java.lang.String linkIsPlan) {
           this.linkIsPlan = linkIsPlan;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkCutComment() {
            return linkCutComment;
     }

     public void setLinkCutComment(java.lang.String linkCutComment) {
           this.linkCutComment = linkCutComment;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkBusinessComment() {
            return linkBusinessComment;
     }

     public void setLinkBusinessComment(java.lang.String linkBusinessComment) {
           this.linkBusinessComment = linkBusinessComment;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkTestResult() {
            return linkTestResult;
     }

     public void setLinkTestResult(java.lang.String linkTestResult) {
           this.linkTestResult = linkTestResult;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkAlertRecord() {
            return linkAlertRecord;
     }

     public void setLinkAlertRecord(java.lang.String linkAlertRecord) {
           this.linkAlertRecord = linkAlertRecord;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkUnnormalComment() {
            return linkUnnormalComment;
     }

     public void setLinkUnnormalComment(java.lang.String linkUnnormalComment) {
           this.linkUnnormalComment = linkUnnormalComment;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkCutAnalyse() {
            return linkCutAnalyse;
     }

     public void setLinkCutAnalyse(java.lang.String linkCutAnalyse) {
           this.linkCutAnalyse = linkCutAnalyse;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIsUpdateWork() {
            return linkIsUpdateWork;
     }

     public void setLinkIsUpdateWork(java.lang.String linkIsUpdateWork) {
           this.linkIsUpdateWork = linkIsUpdateWork;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkWorkUpdateAdvice() {
            return linkWorkUpdateAdvice;
     }

     public void setLinkWorkUpdateAdvice(java.lang.String linkWorkUpdateAdvice) {
           this.linkWorkUpdateAdvice = linkWorkUpdateAdvice;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkSoftVersionUpdate() {
            return linkSoftVersionUpdate;
     }

     public void setLinkSoftVersionUpdate(java.lang.String linkSoftVersionUpdate) {
           this.linkSoftVersionUpdate = linkSoftVersionUpdate;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkNextPlan() {
            return linkNextPlan;
     }

     public void setLinkNextPlan(java.lang.String linkNextPlan) {
           this.linkNextPlan = linkNextPlan;
     }

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkIsNeedProject() {
            return linkIsNeedProject;
     }

     public void setLinkIsNeedProject(java.lang.String linkIsNeedProject) {
           this.linkIsNeedProject = linkIsNeedProject;
     }

	/**
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getLinkProjectComment() {
            return linkProjectComment;
     }

     public void setLinkProjectComment(java.lang.String linkProjectComment) {
           this.linkProjectComment = linkProjectComment;
     }

	public java.lang.String getOperaterContact() {
		return operaterContact;
	}

	public void setOperaterContact(java.lang.String operaterContact) {
		this.operaterContact = operaterContact;
	}

	/**
	 * @hibernate.property length="4000"
	 * @eoms.show
	 * @return
	 */
	public java.lang.String getLinkFailedReason() {
		return linkFailedReason;
	}

	public void setLinkFailedReason(java.lang.String linkFailedReason) {
		this.linkFailedReason = linkFailedReason;
	}



	public java.lang.String getLinkCheckExplain() {
		return linkCheckExplain;
	}

	public void setLinkCheckExplain(java.lang.String linkCheckExplain) {
		this.linkCheckExplain = linkCheckExplain;
	}

	public java.lang.String getLinkDataCheck() {
		return linkDataCheck;
	}

	public void setLinkDataCheck(java.lang.String linkDataCheck) {
		this.linkDataCheck = linkDataCheck;
	}

	public java.lang.String getLinkTestPerson() {
		return linkTestPerson;
	}

	public void setLinkTestPerson(java.lang.String linkTestPerson) {
		this.linkTestPerson = linkTestPerson;
	}

	public java.lang.String getLinkTestReport() {
		return linkTestReport;
	}

	public void setLinkTestReport(java.lang.String linkTestReport) {
		this.linkTestReport = linkTestReport;
	}

	public java.lang.String getLinkIfStartOperationTest() {
		return linkIfStartOperationTest;
	}

	public void setLinkIfStartOperationTest(
			java.lang.String linkIfStartOperationTest) {
		this.linkIfStartOperationTest = linkIfStartOperationTest;
	}


	

}