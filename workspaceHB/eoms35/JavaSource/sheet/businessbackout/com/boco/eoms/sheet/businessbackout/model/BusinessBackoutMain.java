
package com.boco.eoms.sheet.businessbackout.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="BusinessBackoutMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessBackoutmain"
 */
public class BusinessBackoutMain extends BaseMain
{

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String urgentDegree;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String btype1;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String bdeptContact;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String bdeptContactPhone;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customNo;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customName;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customContact;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customContactPhone;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customContactAdd;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customContactPost;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customTrade;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customContactEmail;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String customLevel;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String apnName;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String backoutCause;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String bcode;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String enterpriseCode;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String siName;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String siEnterpriseCode;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String siServerCode;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String serverCode;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String circuitCode;
     private String isCustomerFlag;
     
     //集团新规范
     private String provinceName;
     private String cityName;
     private String countyName;
     private String cmanagerPhone;
     private String cmanagerContactPhone;
     private String productName;
    

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCmanagerContactPhone() {
		return cmanagerContactPhone;
	}

	public void setCmanagerContactPhone(String cmanagerContactPhone) {
		this.cmanagerContactPhone = cmanagerContactPhone;
	}

	public String getCmanagerPhone() {
		return cmanagerPhone;
	}

	public void setCmanagerPhone(String cmanagerPhone) {
		this.cmanagerPhone = cmanagerPhone;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getUrgentDegree() {
            return urgentDegree;
     }

     public void setUrgentDegree(java.lang.String urgentDegree) {
           this.urgentDegree = urgentDegree;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getBtype1() {
            return btype1;
     }

     public void setBtype1(java.lang.String btype1) {
           this.btype1 = btype1;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getBdeptContact() {
            return bdeptContact;
     }

     public void setBdeptContact(java.lang.String bdeptContact) {
           this.bdeptContact = bdeptContact;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getBdeptContactPhone() {
            return bdeptContactPhone;
     }

     public void setBdeptContactPhone(java.lang.String bdeptContactPhone) {
           this.bdeptContactPhone = bdeptContactPhone;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomNo() {
            return customNo;
     }

     public void setCustomNo(java.lang.String customNo) {
           this.customNo = customNo;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomName() {
            return customName;
     }

     public void setCustomName(java.lang.String customName) {
           this.customName = customName;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomContact() {
            return customContact;
     }

     public void setCustomContact(java.lang.String customContact) {
           this.customContact = customContact;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomContactPhone() {
            return customContactPhone;
     }

     public void setCustomContactPhone(java.lang.String customContactPhone) {
           this.customContactPhone = customContactPhone;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomContactAdd() {
            return customContactAdd;
     }

     public void setCustomContactAdd(java.lang.String customContactAdd) {
           this.customContactAdd = customContactAdd;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomContactPost() {
            return customContactPost;
     }

     public void setCustomContactPost(java.lang.String customContactPost) {
           this.customContactPost = customContactPost;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomTrade() {
            return customTrade;
     }

     public void setCustomTrade(java.lang.String customTrade) {
           this.customTrade = customTrade;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomContactEmail() {
            return customContactEmail;
     }

     public void setCustomContactEmail(java.lang.String customContactEmail) {
           this.customContactEmail = customContactEmail;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCustomLevel() {
            return customLevel;
     }

     public void setCustomLevel(java.lang.String customLevel) {
           this.customLevel = customLevel;
     }


	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getBackoutCause() {
            return backoutCause;
     }

     public void setBackoutCause(java.lang.String backoutCause) {
           this.backoutCause = backoutCause;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getBcode() {
            return bcode;
     }

     public void setBcode(java.lang.String bcode) {
           this.bcode = bcode;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getEnterpriseCode() {
            return enterpriseCode;
     }

     public void setEnterpriseCode(java.lang.String enterpriseCode) {
           this.enterpriseCode = enterpriseCode;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getServerCode() {
            return serverCode;
     }

     public void setServerCode(java.lang.String serverCode) {
           this.serverCode = serverCode;
     }

	/**
	 * @hibernate.property value=""
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCircuitCode() {
            return circuitCode;
     }

     public void setCircuitCode(java.lang.String circuitCode) {
           this.circuitCode = circuitCode;
     }

	public java.lang.String getApnName() {
		return apnName;
	}

	public void setApnName(java.lang.String apnName) {
		this.apnName = apnName;
	}

	public java.lang.String getSiEnterpriseCode() {
		return siEnterpriseCode;
	}

	public void setSiEnterpriseCode(java.lang.String siEnterpriseCode) {
		this.siEnterpriseCode = siEnterpriseCode;
	}

	public java.lang.String getSiName() {
		return siName;
	}

	public void setSiName(java.lang.String siName) {
		this.siName = siName;
	}

	public java.lang.String getSiServerCode() {
		return siServerCode;
	}

	public void setSiServerCode(java.lang.String siServerCode) {
		this.siServerCode = siServerCode;
	}

	public String getIsCustomerFlag() {
		return isCustomerFlag;
	}

	public void setIsCustomerFlag(String isCustomerFlag) {
		this.isCustomerFlag = isCustomerFlag;
	}

	

}
