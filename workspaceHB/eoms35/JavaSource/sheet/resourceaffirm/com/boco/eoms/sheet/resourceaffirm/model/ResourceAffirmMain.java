
package com.boco.eoms.sheet.resourceaffirm.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="ResourceAffirmMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ResourceAffirmmain"
 */
public class ResourceAffirmMain extends BaseMain
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
	 * @textarea
	 */
     private java.lang.String brequirementDesc;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String cityA;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String cityZ;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String bandwidth;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String numbere;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portA;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portAInterfaceType;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portADetailAdd;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portABDeviceName;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portABDevicePort;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portAContactPhone;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portZ;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portZInterfaceType;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portZBDeviceName;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portZBDevicePort;

    /**
	 *
	 * @texttype
	 */
     private java.lang.String portZContactPhone;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String isBeforehandOccupy;
     private String isCustomerFlag;
     
     //集团新规范
     private String provinceName;
     private String cityName;
     private String countyName;
     private String cmanagerPhone;
     private String cmanagerContactPhone;
     private String productName;
     //传输
     private String applyInfo;
     private String transfBusiness;
     
     private int mainAdviceNum; //催办次数
     private String mainAdviceContent; //催办提醒内容
    

	public String getApplyInfo() {
		return applyInfo;
	}

	public void setApplyInfo(String applyInfo) {
		this.applyInfo = applyInfo;
	}

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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
	 * @hibernate.property value="10"
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
 	 * @hibernate.property value="10"
 	 * @eoms.show
 	 * @return
 	 */
	public java.lang.String getBrequirementDesc() {
		return brequirementDesc;
	}

	public void setBrequirementDesc(java.lang.String brequirementDesc) {
		this.brequirementDesc = brequirementDesc;
	}

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCityA() {
            return cityA;
     }

     public void setCityA(java.lang.String cityA) {
           this.cityA = cityA;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getCityZ() {
            return cityZ;
     }

     public void setCityZ(java.lang.String cityZ) {
           this.cityZ = cityZ;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getBandwidth() {
            return bandwidth;
     }

     public void setBandwidth(java.lang.String bandwidth) {
           this.bandwidth = bandwidth;
     }

	

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortA() {
            return portA;
     }

     public void setPortA(java.lang.String portA) {
           this.portA = portA;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortAInterfaceType() {
            return portAInterfaceType;
     }

     public void setPortAInterfaceType(java.lang.String portAInterfaceType) {
           this.portAInterfaceType = portAInterfaceType;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortADetailAdd() {
            return portADetailAdd;
     }

     public void setPortADetailAdd(java.lang.String portADetailAdd) {
           this.portADetailAdd = portADetailAdd;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortABDeviceName() {
            return portABDeviceName;
     }

     public void setPortABDeviceName(java.lang.String portABDeviceName) {
           this.portABDeviceName = portABDeviceName;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortABDevicePort() {
            return portABDevicePort;
     }

     public void setPortABDevicePort(java.lang.String portABDevicePort) {
           this.portABDevicePort = portABDevicePort;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortAContactPhone() {
            return portAContactPhone;
     }

     public void setPortAContactPhone(java.lang.String portAContactPhone) {
           this.portAContactPhone = portAContactPhone;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortZ() {
            return portZ;
     }

     public void setPortZ(java.lang.String portZ) {
           this.portZ = portZ;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortZInterfaceType() {
            return portZInterfaceType;
     }

     public void setPortZInterfaceType(java.lang.String portZInterfaceType) {
           this.portZInterfaceType = portZInterfaceType;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortZBDeviceName() {
            return portZBDeviceName;
     }

     public void setPortZBDeviceName(java.lang.String portZBDeviceName) {
           this.portZBDeviceName = portZBDeviceName;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortZBDevicePort() {
            return portZBDevicePort;
     }

     public void setPortZBDevicePort(java.lang.String portZBDevicePort) {
           this.portZBDevicePort = portZBDevicePort;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getPortZContactPhone() {
            return portZContactPhone;
     }

     public void setPortZContactPhone(java.lang.String portZContactPhone) {
           this.portZContactPhone = portZContactPhone;
     }

	/**
	 * @hibernate.property value="10"
	 * @eoms.show
	 * @return
	 */
   	 public java.lang.String getIsBeforehandOccupy() {
            return isBeforehandOccupy;
     }

     public void setIsBeforehandOccupy(java.lang.String isBeforehandOccupy) {
           this.isBeforehandOccupy = isBeforehandOccupy;
     }

     /**
 	 * @hibernate.property value="10"
 	 * @eoms.show
 	 * @return
 	 */
	public java.lang.String getNumbere() {
		return numbere;
	}

	public void setNumbere(java.lang.String numbere) {
		this.numbere = numbere;
	}

	public String getIsCustomerFlag() {
		return isCustomerFlag;
	}

	public void setIsCustomerFlag(String isCustomerFlag) {
		this.isCustomerFlag = isCustomerFlag;
	}

	public String getTransfBusiness() {
		return transfBusiness;
	}

	public void setTransfBusiness(String transfBusiness) {
		this.transfBusiness = transfBusiness;
	}

	public String getMainAdviceContent() {
		return mainAdviceContent;
	}

	public void setMainAdviceContent(String mainAdviceContent) {
		this.mainAdviceContent = mainAdviceContent;
	}

	public int getMainAdviceNum() {
		return mainAdviceNum;
	}

	public void setMainAdviceNum(int mainAdviceNum) {
		this.mainAdviceNum = mainAdviceNum;
	}

}
