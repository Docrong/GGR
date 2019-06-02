package com.boco.eoms.businessupport.product.model;

import java.util.Date;

public class IPSpecialLine{
	private String id;
	private String orderSheet_Id;
	private String tradeId;					  //crm产品实例唯一标识
   //以下字段为IP专线私有字段
   	private String RCAPDetailAddress;	      //接入端详细地址
   	private String allowConnect;			  //是否具备开通条件
   	private String connectPortLength;		  //与最近的接入点之间的距离
   	private String dealResult;				  //处理结果
	private String dealDesc;				  //处理说明
   	private String ipAddressCount;		      //IP地址数量
   	private String businessBandwidth;		  //业务带宽
	private String businessAmount;		      //业务数量（传输条数）
	private String portANetDeptUser;		  //网络部联系人
	private String portANetDeptPhone;		  //网络部门联系人电话
	private String engineeringUserA;		  //施工联系人
	private String engineeringPhoneA;		  //施工联系人电话	
	private String testReport;				  //测试报告
	private String ip1;						  //IP地址1（账号）
	private String ip2;						  //IP地址2（账号）
	private String cnetCode;				  //子网掩码
	private String gateway;					  //网关	
	private String businessRequireDesc;       //业务需求描述
	private String userEquipmentName;         //用户端设备名称
	private String userEquipmentPort;         //用户端设备端口	
	private String netResourceAbilityAffirm;  //网络资源能力确认
    private String guestProjectAbilityAffirm; //客户端工程能力确认
	private String intendInvest;              //预计投资
	private String planFinishDaysA;			  //预计完成天数
	private String isAlreadyCampON;           //是否已预占
	private String buildingScheme;            //建设方案
	private String remark;			    	  //备注
	private String specialtyType;             //专业类型（预留）
	private Integer deleted;
	private String circuitSheetId;		//电路编号
	private String circuitName;			//电路名称
	//added by liufuyuan shanxi
	private String ipNeedNum;	//IP地址-互联需求数量(个)
	private String ipServerNeedNum;	//IP地址-客户应用服务需求数量（个）
	private String userIsUserNet;	//用户是否用户网站*
	private String userSpecifyDevNeed;	//用户个性化设备需求*
//	机房名称	
	private String apointComputHouseName;	
	private String zpointComputerHorseName;
//	接口类型及型号	电口或光口
	private String portAInterfaceType;
	private String portZInterfaceType;
//	业务设备名称	
	private String portABDeviceName;
	private String portZBDeviceName;
//	客户在当地的配合人	
	private String apointLocalPerson;
	private String zpointLocalPerson;
//	客户在当地的配合人的联系电话	
	private String portAContactPhone;	
	private String portZContactPhone;
//端点详细地址	
	private String portADetailAdd;	
	private String portZDetailAdd;
//	业务接入点客户联系人
	private String interfaceCustomConnPerson;
	private String interfaceCustomConnPersonZ;
//	业务接入点客户联系电话	
	private String interfaceCustomConnPhone;	
	private String interfaceCustomConnPhoneZ;
//	业务接入点客户联系邮箱	
	private String interfaceCustomConnMail;	
	private String interfaceCustomConnMailZ;
//	业务接入点客户联系地址	
	private String interfaceCustomConnAdd;	
	private String interfaceCustomConnAddZ;
//	光纤设备名称	
	private String fiberEquipNameA;
	private String fiberEquipNameZ;
//	光纤设备编号	
	private String fiberEquipCodeA;
	private String fiberEquipCodeZ;
//	站点设备编码
	private String siteEquipCodeA;		
	private String siteEquipCodeZ;	//暂时不用
//	站点名称
	private String siteNameA;	
	private String siteNameZ;
//	接入站点标识	
	private String accessSiteIdenA;	
	private String accessSiteIdenZ;
//	最后一公里光缆长度
	private String fiberLengthA;	
	private String fiberLengthZ;
//	光缆产权	自有/租用
	private String fiberOwnerA;	
	private String fiberOwnerZ;
//	是否熔接
	private String isGetInterfaceA;	
	private String isGetInterfaceZ;
//	熔接序号
	private String getInterfaceNoA;	
	private String getInterfaceNoZ;
//	最后一公里处理意见
	private String theLastOpinionA;	
	private String theLastOpinionZ;
	
	
//	客户端标准地址	
	private String userStardAddA;	
	private String userStardAddZ;
//客户位置纬度	
	private String userSiteHA;	
	private String userSiteHZ;
//客户位置经度	
	private String userSiteAA;	
	private String userSiteAZ;
//客户端是否具有设备	
	private String userIsHaveDivA;	
	private String userIsHaveDivZ;
//是否需要移动采购	
	private String isNeedBuyA;
	private String isNeedBuyZ;
//需要购买的设备	
	private String theDevNeededA;	
	private String theDevNeededZ;
//接入点类型	基站、光交接箱、光分纤箱、城域网节点
	private String interfacePointTypeA;	
	private String interfacePointTypeZ;
//客户端到接入点能否通达	是和否
	private String isOkBetweenUserA;	
	private String isOkBetweenUserZ;
//不能接入的原因	
	private String noInputResonA;	
	private String noInputResonZ;
//建设方式	新建、利旧 
	private String buildMethodA;
	private String buildMethodZ;
//接入点地址	
	private String interfacePointAddA;
	private String interfacePointAddZ;
	
	private String portZBDevicePort;		  //端点Z业务设备端口
	
//	  A接入点站点名称 
	private String 	interfaceSiteNameA;
//	  A接入点设备编码   
	private String 	interfaceEquipCodeA;

// Z接入点站点名称 
	private String 	interfaceSiteNameZ;
//z接入点设备编码 
	private String 	interfaceEquipCodeZ;
	
//	传输容量是否满足开通
	private String isDeviceAllowOpenA;
	private String isDeviceAllowOpenZ;	
//是否需要添加板卡	
	private String isNeedAddCardA;
	private String isNeedAddCardZ;
//如果选需要增加板卡,显示下面两个字段：
//	板卡类型	
	private String cardTypeA;
	private String cardTypeZ;
//	板卡数量	
	private String cardNumA;
	private String cardNumZ;
//	A纤芯个数
	private String fiberAcount;
//A光缆路由描述            
	private String fiberAroute;

//Z纤芯个数      
	private String fiberZcount;
	//Z光缆路由描述     
	private String fiberZroute;
// 网络部承诺的开通时间:      
	private Date promise;  
	// 建设工期预计（工作日）：  
	private String whours;
// 	客户端接入方式  
	private String linkDesinTypeA;
	private String linkDesinTypeZ;

	
//	  接入点设备信息描述       A光纤设备名称   
	private String fiberOwnerAfiberEquipNameA;
	private String fiberOwnerAfiberEquipNameZ;
//	A敷设方式：		
	private String buildTypeA;
//	Z敷设方式：		
	private String buildTypeZ;
//	A设备类型：			
	private String portABDeviceType;
//	Z设备类型：		
	private String portZBDeviceType;
	

//	业务需求区域		
	private String requirmentArea;
//	端口数量			
	private String portNum;
//	端口类型			
	private String portType;



	public String getBuildTypeA() {
		return buildTypeA;
	}
	public void setBuildTypeA(String buildTypeA) {
		this.buildTypeA = buildTypeA;
	}
	public String getBuildTypeZ() {
		return buildTypeZ;
	}
	public void setBuildTypeZ(String buildTypeZ) {
		this.buildTypeZ = buildTypeZ;
	}
	public String getPortABDeviceType() {
		return portABDeviceType;
	}
	public void setPortABDeviceType(String portABDeviceType) {
		this.portABDeviceType = portABDeviceType;
	}
	public String getPortZBDeviceType() {
		return portZBDeviceType;
	}
	public void setPortZBDeviceType(String portZBDeviceType) {
		this.portZBDeviceType = portZBDeviceType;
	}
	public String getFiberAcount() {
		return fiberAcount;
	}
	public void setFiberAcount(String fiberAcount) {
		this.fiberAcount = fiberAcount;
	}
	public String getFiberAroute() {
		return fiberAroute;
	}
	public void setFiberAroute(String fiberAroute) {
		this.fiberAroute = fiberAroute;
	}
	public String getFiberZcount() {
		return fiberZcount;
	}
	public void setFiberZcount(String fiberZcount) {
		this.fiberZcount = fiberZcount;
	}
	public String getFiberZroute() {
		return fiberZroute;
	}
	public void setFiberZroute(String fiberZroute) {
		this.fiberZroute = fiberZroute;
	}
	public Date getPromise() {
		return promise;
	}
	public void setPromise(Date promise) {
		this.promise = promise;
	}
	public String getWhours() {
		return whours;
	}
	public void setWhours(String whours) {
		this.whours = whours;
	}
	public String getCardNumA() {
		return cardNumA;
	}
	public void setCardNumA(String cardNumA) {
		this.cardNumA = cardNumA;
	}
	public String getCardNumZ() {
		return cardNumZ;
	}
	public void setCardNumZ(String cardNumZ) {
		this.cardNumZ = cardNumZ;
	}
	public String getCardTypeA() {
		return cardTypeA;
	}
	public void setCardTypeA(String cardTypeA) {
		this.cardTypeA = cardTypeA;
	}
	public String getCardTypeZ() {
		return cardTypeZ;
	}
	public void setCardTypeZ(String cardTypeZ) {
		this.cardTypeZ = cardTypeZ;
	}
	public String getIsDeviceAllowOpenA() {
		return isDeviceAllowOpenA;
	}
	public void setIsDeviceAllowOpenA(String isDeviceAllowOpenA) {
		this.isDeviceAllowOpenA = isDeviceAllowOpenA;
	}
	public String getIsDeviceAllowOpenZ() {
		return isDeviceAllowOpenZ;
	}
	public void setIsDeviceAllowOpenZ(String isDeviceAllowOpenZ) {
		this.isDeviceAllowOpenZ = isDeviceAllowOpenZ;
	}
	public String getIsNeedAddCardA() {
		return isNeedAddCardA;
	}
	public void setIsNeedAddCardA(String isNeedAddCardA) {
		this.isNeedAddCardA = isNeedAddCardA;
	}
	public String getIsNeedAddCardZ() {
		return isNeedAddCardZ;
	}
	public void setIsNeedAddCardZ(String isNeedAddCardZ) {
		this.isNeedAddCardZ = isNeedAddCardZ;
	}
	public String getInterfaceEquipCodeA() {
		return interfaceEquipCodeA;
	}
	public void setInterfaceEquipCodeA(String interfaceEquipCodeA) {
		this.interfaceEquipCodeA = interfaceEquipCodeA;
	}
	public String getInterfaceEquipCodeZ() {
		return interfaceEquipCodeZ;
	}
	public void setInterfaceEquipCodeZ(String interfaceEquipCodeZ) {
		this.interfaceEquipCodeZ = interfaceEquipCodeZ;
	}
	public String getInterfaceSiteNameA() {
		return interfaceSiteNameA;
	}
	public void setInterfaceSiteNameA(String interfaceSiteNameA) {
		this.interfaceSiteNameA = interfaceSiteNameA;
	}
	public String getInterfaceSiteNameZ() {
		return interfaceSiteNameZ;
	}
	public void setInterfaceSiteNameZ(String interfaceSiteNameZ) {
		this.interfaceSiteNameZ = interfaceSiteNameZ;
	}
	public String getPortZBDevicePort() {
		return portZBDevicePort;
	}
	public void setPortZBDevicePort(String portZBDevicePort) {
		this.portZBDevicePort = portZBDevicePort;
	}
	public String getBuildMethodA() {
		return buildMethodA;
	}
	public void setBuildMethodA(String buildMethodA) {
		this.buildMethodA = buildMethodA;
	}
	public String getBuildMethodZ() {
		return buildMethodZ;
	}
	public void setBuildMethodZ(String buildMethodZ) {
		this.buildMethodZ = buildMethodZ;
	}
	public String getInterfacePointAddA() {
		return interfacePointAddA;
	}
	public void setInterfacePointAddA(String interfacePointAddA) {
		this.interfacePointAddA = interfacePointAddA;
	}
	public String getInterfacePointAddZ() {
		return interfacePointAddZ;
	}
	public void setInterfacePointAddZ(String interfacePointAddZ) {
		this.interfacePointAddZ = interfacePointAddZ;
	}
	public String getInterfacePointTypeA() {
		return interfacePointTypeA;
	}
	public void setInterfacePointTypeA(String interfacePointTypeA) {
		this.interfacePointTypeA = interfacePointTypeA;
	}
	public String getInterfacePointTypeZ() {
		return interfacePointTypeZ;
	}
	public void setInterfacePointTypeZ(String interfacePointTypeZ) {
		this.interfacePointTypeZ = interfacePointTypeZ;
	}
	public String getIsNeedBuyA() {
		return isNeedBuyA;
	}
	public void setIsNeedBuyA(String isNeedBuyA) {
		this.isNeedBuyA = isNeedBuyA;
	}
	public String getIsNeedBuyZ() {
		return isNeedBuyZ;
	}
	public void setIsNeedBuyZ(String isNeedBuyZ) {
		this.isNeedBuyZ = isNeedBuyZ;
	}
	public String getIsOkBetweenUserA() {
		return isOkBetweenUserA;
	}
	public void setIsOkBetweenUserA(String isOkBetweenUserA) {
		this.isOkBetweenUserA = isOkBetweenUserA;
	}
	public String getIsOkBetweenUserZ() {
		return isOkBetweenUserZ;
	}
	public void setIsOkBetweenUserZ(String isOkBetweenUserZ) {
		this.isOkBetweenUserZ = isOkBetweenUserZ;
	}
	public String getNoInputResonA() {
		return noInputResonA;
	}
	public void setNoInputResonA(String noInputResonA) {
		this.noInputResonA = noInputResonA;
	}
	public String getNoInputResonZ() {
		return noInputResonZ;
	}
	public void setNoInputResonZ(String noInputResonZ) {
		this.noInputResonZ = noInputResonZ;
	}
	public String getTheDevNeededA() {
		return theDevNeededA;
	}
	public void setTheDevNeededA(String theDevNeededA) {
		this.theDevNeededA = theDevNeededA;
	}
	public String getTheDevNeededZ() {
		return theDevNeededZ;
	}
	public void setTheDevNeededZ(String theDevNeededZ) {
		this.theDevNeededZ = theDevNeededZ;
	}
	public String getUserIsHaveDivA() {
		return userIsHaveDivA;
	}
	public void setUserIsHaveDivA(String userIsHaveDivA) {
		this.userIsHaveDivA = userIsHaveDivA;
	}
	public String getUserIsHaveDivZ() {
		return userIsHaveDivZ;
	}
	public void setUserIsHaveDivZ(String userIsHaveDivZ) {
		this.userIsHaveDivZ = userIsHaveDivZ;
	}
	public String getUserSiteAA() {
		return userSiteAA;
	}
	public void setUserSiteAA(String userSiteAA) {
		this.userSiteAA = userSiteAA;
	}
	public String getUserSiteAZ() {
		return userSiteAZ;
	}
	public void setUserSiteAZ(String userSiteAZ) {
		this.userSiteAZ = userSiteAZ;
	}
	public String getUserSiteHA() {
		return userSiteHA;
	}
	public void setUserSiteHA(String userSiteHA) {
		this.userSiteHA = userSiteHA;
	}
	public String getUserSiteHZ() {
		return userSiteHZ;
	}
	public void setUserSiteHZ(String userSiteHZ) {
		this.userSiteHZ = userSiteHZ;
	}
	public String getUserStardAddA() {
		return userStardAddA;
	}
	public void setUserStardAddA(String userStardAddA) {
		this.userStardAddA = userStardAddA;
	}
	public String getUserStardAddZ() {
		return userStardAddZ;
	}
	public void setUserStardAddZ(String userStardAddZ) {
		this.userStardAddZ = userStardAddZ;
	}
	public String getAccessSiteIdenA() {
		return accessSiteIdenA;
	}
	public void setAccessSiteIdenA(String accessSiteIdenA) {
		this.accessSiteIdenA = accessSiteIdenA;
	}
	public String getAccessSiteIdenZ() {
		return accessSiteIdenZ;
	}
	public void setAccessSiteIdenZ(String accessSiteIdenZ) {
		this.accessSiteIdenZ = accessSiteIdenZ;
	}
	public String getFiberEquipCodeA() {
		return fiberEquipCodeA;
	}
	public void setFiberEquipCodeA(String fiberEquipCodeA) {
		this.fiberEquipCodeA = fiberEquipCodeA;
	}
	public String getFiberEquipCodeZ() {
		return fiberEquipCodeZ;
	}
	public void setFiberEquipCodeZ(String fiberEquipCodeZ) {
		this.fiberEquipCodeZ = fiberEquipCodeZ;
	}
	public String getFiberEquipNameA() {
		return fiberEquipNameA;
	}
	public void setFiberEquipNameA(String fiberEquipNameA) {
		this.fiberEquipNameA = fiberEquipNameA;
	}
	public String getFiberEquipNameZ() {
		return fiberEquipNameZ;
	}
	public void setFiberEquipNameZ(String fiberEquipNameZ) {
		this.fiberEquipNameZ = fiberEquipNameZ;
	}
	public String getFiberLengthA() {
		return fiberLengthA;
	}
	public void setFiberLengthA(String fiberLengthA) {
		this.fiberLengthA = fiberLengthA;
	}
	public String getFiberLengthZ() {
		return fiberLengthZ;
	}
	public void setFiberLengthZ(String fiberLengthZ) {
		this.fiberLengthZ = fiberLengthZ;
	}
	public String getSiteEquipCodeA() {
		return siteEquipCodeA;
	}
	public void setSiteEquipCodeA(String siteEquipCodeA) {
		this.siteEquipCodeA = siteEquipCodeA;
	}
	public String getSiteEquipCodeZ() {
		return siteEquipCodeZ;
	}
	public void setSiteEquipCodeZ(String siteEquipCodeZ) {
		this.siteEquipCodeZ = siteEquipCodeZ;
	}
	public String getSiteNameA() {
		return siteNameA;
	}
	public void setSiteNameA(String siteNameA) {
		this.siteNameA = siteNameA;
	}
	public String getSiteNameZ() {
		return siteNameZ;
	}
	public void setSiteNameZ(String siteNameZ) {
		this.siteNameZ = siteNameZ;
	}
	public String getGetInterfaceNoA() {
		return getInterfaceNoA;
	}
	public void setGetInterfaceNoA(String getInterfaceNoA) {
		this.getInterfaceNoA = getInterfaceNoA;
	}
	public String getGetInterfaceNoZ() {
		return getInterfaceNoZ;
	}
	public void setGetInterfaceNoZ(String getInterfaceNoZ) {
		this.getInterfaceNoZ = getInterfaceNoZ;
	}
	public String getIsGetInterfaceA() {
		return isGetInterfaceA;
	}
	public void setIsGetInterfaceA(String isGetInterfaceA) {
		this.isGetInterfaceA = isGetInterfaceA;
	}
	public String getIsGetInterfaceZ() {
		return isGetInterfaceZ;
	}
	public void setIsGetInterfaceZ(String isGetInterfaceZ) {
		this.isGetInterfaceZ = isGetInterfaceZ;
	}
	public String getTheLastOpinionA() {
		return theLastOpinionA;
	}
	public void setTheLastOpinionA(String theLastOpinionA) {
		this.theLastOpinionA = theLastOpinionA;
	}
	public String getTheLastOpinionZ() {
		return theLastOpinionZ;
	}
	public void setTheLastOpinionZ(String theLastOpinionZ) {
		this.theLastOpinionZ = theLastOpinionZ;
	}

	public String getApointComputHouseName() {
		return apointComputHouseName;
	}
	public void setApointComputHouseName(String apointComputHouseName) {
		this.apointComputHouseName = apointComputHouseName;
	}
	public String getApointLocalPerson() {
		return apointLocalPerson;
	}
	public void setApointLocalPerson(String apointLocalPerson) {
		this.apointLocalPerson = apointLocalPerson;
	}

	public String getFiberOwnerA() {
		return fiberOwnerA;
	}
	public void setFiberOwnerA(String fiberOwnerA) {
		this.fiberOwnerA = fiberOwnerA;
	}
	public String getFiberOwnerZ() {
		return fiberOwnerZ;
	}
	public void setFiberOwnerZ(String fiberOwnerZ) {
		this.fiberOwnerZ = fiberOwnerZ;
	}
	public String getInterfaceCustomConnAdd() {
		return interfaceCustomConnAdd;
	}
	public void setInterfaceCustomConnAdd(String interfaceCustomConnAdd) {
		this.interfaceCustomConnAdd = interfaceCustomConnAdd;
	}
	public String getInterfaceCustomConnAddZ() {
		return interfaceCustomConnAddZ;
	}
	public void setInterfaceCustomConnAddZ(String interfaceCustomConnAddZ) {
		this.interfaceCustomConnAddZ = interfaceCustomConnAddZ;
	}
	public String getInterfaceCustomConnMail() {
		return interfaceCustomConnMail;
	}
	public void setInterfaceCustomConnMail(String interfaceCustomConnMail) {
		this.interfaceCustomConnMail = interfaceCustomConnMail;
	}
	public String getInterfaceCustomConnMailZ() {
		return interfaceCustomConnMailZ;
	}
	public void setInterfaceCustomConnMailZ(String interfaceCustomConnMailZ) {
		this.interfaceCustomConnMailZ = interfaceCustomConnMailZ;
	}
	public String getInterfaceCustomConnPerson() {
		return interfaceCustomConnPerson;
	}
	public void setInterfaceCustomConnPerson(String interfaceCustomConnPerson) {
		this.interfaceCustomConnPerson = interfaceCustomConnPerson;
	}
	public String getInterfaceCustomConnPersonZ() {
		return interfaceCustomConnPersonZ;
	}
	public void setInterfaceCustomConnPersonZ(String interfaceCustomConnPersonZ) {
		this.interfaceCustomConnPersonZ = interfaceCustomConnPersonZ;
	}
	public String getInterfaceCustomConnPhone() {
		return interfaceCustomConnPhone;
	}
	public void setInterfaceCustomConnPhone(String interfaceCustomConnPhone) {
		this.interfaceCustomConnPhone = interfaceCustomConnPhone;
	}
	public String getInterfaceCustomConnPhoneZ() {
		return interfaceCustomConnPhoneZ;
	}
	public void setInterfaceCustomConnPhoneZ(String interfaceCustomConnPhoneZ) {
		this.interfaceCustomConnPhoneZ = interfaceCustomConnPhoneZ;
	}
	public String getPortABDeviceName() {
		return portABDeviceName;
	}
	public void setPortABDeviceName(String portABDeviceName) {
		this.portABDeviceName = portABDeviceName;
	}
	public String getPortAContactPhone() {
		return portAContactPhone;
	}
	public void setPortAContactPhone(String portAContactPhone) {
		this.portAContactPhone = portAContactPhone;
	}
	public String getPortADetailAdd() {
		return portADetailAdd;
	}
	public void setPortADetailAdd(String portADetailAdd) {
		this.portADetailAdd = portADetailAdd;
	}
	public String getPortAInterfaceType() {
		return portAInterfaceType;
	}
	public void setPortAInterfaceType(String portAInterfaceType) {
		this.portAInterfaceType = portAInterfaceType;
	}
	public String getPortZBDeviceName() {
		return portZBDeviceName;
	}
	public void setPortZBDeviceName(String portZBDeviceName) {
		this.portZBDeviceName = portZBDeviceName;
	}
	public String getPortZContactPhone() {
		return portZContactPhone;
	}
	public void setPortZContactPhone(String portZContactPhone) {
		this.portZContactPhone = portZContactPhone;
	}
	public String getPortZDetailAdd() {
		return portZDetailAdd;
	}
	public void setPortZDetailAdd(String portZDetailAdd) {
		this.portZDetailAdd = portZDetailAdd;
	}
	public String getPortZInterfaceType() {
		return portZInterfaceType;
	}
	public void setPortZInterfaceType(String portZInterfaceType) {
		this.portZInterfaceType = portZInterfaceType;
	}

	public String getZpointComputerHorseName() {
		return zpointComputerHorseName;
	}
	public void setZpointComputerHorseName(String zpointComputerHorseName) {
		this.zpointComputerHorseName = zpointComputerHorseName;
	}
	public String getZpointLocalPerson() {
		return zpointLocalPerson;
	}
	public void setZpointLocalPerson(String zpointLocalPerson) {
		this.zpointLocalPerson = zpointLocalPerson;
	}
	public String getCircuitName() {
		return circuitName;
	}
	public void setCircuitName(String circuitName) {
		this.circuitName = circuitName;
	}
	public String getCircuitSheetId() {
		return circuitSheetId;
	}
	public void setCircuitSheetId(String circuitSheetId) {
		this.circuitSheetId = circuitSheetId;
	}
	public String getAllowConnect() {
		return allowConnect;
	}
	public void setAllowConnect(String allowConnect) {
		this.allowConnect = allowConnect;
	}
	public String getBuildingScheme() {
		return buildingScheme;
	}
	public void setBuildingScheme(String buildingScheme) {
		this.buildingScheme = buildingScheme;
	}
	public String getBusinessAmount() {
		return businessAmount;
	}
	public void setBusinessAmount(String businessAmount) {
		this.businessAmount = businessAmount;
	}
	public String getBusinessBandwidth() {
		return businessBandwidth;
	}
	public void setBusinessBandwidth(String businessBandwidth) {
		this.businessBandwidth = businessBandwidth;
	}
	public String getBusinessRequireDesc() {
		return businessRequireDesc;
	}
	public void setBusinessRequireDesc(String businessRequireDesc) {
		this.businessRequireDesc = businessRequireDesc;
	}
	public String getCnetCode() {
		return cnetCode;
	}
	public void setCnetCode(String cnetCode) {
		this.cnetCode = cnetCode;
	}
	public String getConnectPortLength() {
		return connectPortLength;
	}
	public void setConnectPortLength(String connectPortLength) {
		this.connectPortLength = connectPortLength;
	}
	public String getDealDesc() {
		return dealDesc;
	}
	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}
	public String getDealResult() {
		return dealResult;
	}
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public String getEngineeringPhoneA() {
		return engineeringPhoneA;
	}
	public void setEngineeringPhoneA(String engineeringPhoneA) {
		this.engineeringPhoneA = engineeringPhoneA;
	}
	public String getEngineeringUserA() {
		return engineeringUserA;
	}
	public void setEngineeringUserA(String engineeringUserA) {
		this.engineeringUserA = engineeringUserA;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getGuestProjectAbilityAffirm() {
		return guestProjectAbilityAffirm;
	}
	public void setGuestProjectAbilityAffirm(String guestProjectAbilityAffirm) {
		this.guestProjectAbilityAffirm = guestProjectAbilityAffirm;
	}
	public String getIntendInvest() {
		return intendInvest;
	}
	public void setIntendInvest(String intendInvest) {
		this.intendInvest = intendInvest;
	}
	public String getIp1() {
		return ip1;
	}
	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}
	public String getIp2() {
		return ip2;
	}
	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}
	public String getIpAddressCount() {
		return ipAddressCount;
	}
	public void setIpAddressCount(String ipAddressCount) {
		this.ipAddressCount = ipAddressCount;
	}
	public String getIsAlreadyCampON() {
		return isAlreadyCampON;
	}
	public void setIsAlreadyCampON(String isAlreadyCampON) {
		this.isAlreadyCampON = isAlreadyCampON;
	}
	public String getNetResourceAbilityAffirm() {
		return netResourceAbilityAffirm;
	}
	public void setNetResourceAbilityAffirm(String netResourceAbilityAffirm) {
		this.netResourceAbilityAffirm = netResourceAbilityAffirm;
	}
	public String getOrderSheet_Id() {
		return orderSheet_Id;
	}
	public void setOrderSheet_Id(String orderSheet_Id) {
		this.orderSheet_Id = orderSheet_Id;
	}
	public String getPortANetDeptPhone() {
		return portANetDeptPhone;
	}
	public void setPortANetDeptPhone(String portANetDeptPhone) {
		this.portANetDeptPhone = portANetDeptPhone;
	}
	public String getPortANetDeptUser() {
		return portANetDeptUser;
	}
	public void setPortANetDeptUser(String portANetDeptUser) {
		this.portANetDeptUser = portANetDeptUser;
	}
	public String getRCAPDetailAddress() {
		return RCAPDetailAddress;
	}
	public void setRCAPDetailAddress(String detailAddress) {
		RCAPDetailAddress = detailAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTestReport() {
		return testReport;
	}
	public void setTestReport(String testReport) {
		this.testReport = testReport;
	}
	public String getUserEquipmentName() {
		return userEquipmentName;
	}
	public void setUserEquipmentName(String userEquipmentName) {
		this.userEquipmentName = userEquipmentName;
	}
	public String getUserEquipmentPort() {
		return userEquipmentPort;
	}
	public void setUserEquipmentPort(String userEquipmentPort) {
		this.userEquipmentPort = userEquipmentPort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanFinishDaysA() {
		return planFinishDaysA;
	}
	public void setPlanFinishDaysA(String planFinishDaysA) {
		this.planFinishDaysA = planFinishDaysA;
	}
	public String getSpecialtyType() {
		return specialtyType;
	}
	public void setSpecialtyType(String specialtyType) {
		this.specialtyType = specialtyType;
	}
	public String getIpNeedNum() {
		return ipNeedNum;
	}
	public void setIpNeedNum(String ipNeedNum) {
		this.ipNeedNum = ipNeedNum;
	}
	public String getIpServerNeedNum() {
		return ipServerNeedNum;
	}
	public void setIpServerNeedNum(String ipServerNeedNum) {
		this.ipServerNeedNum = ipServerNeedNum;
	}
	public String getUserIsUserNet() {
		return userIsUserNet;
	}
	public void setUserIsUserNet(String userIsUserNet) {
		this.userIsUserNet = userIsUserNet;
	}
	public String getUserSpecifyDevNeed() {
		return userSpecifyDevNeed;
	}
	public void setUserSpecifyDevNeed(String userSpecifyDevNeed) {
		this.userSpecifyDevNeed = userSpecifyDevNeed;
	}
	public String getFiberOwnerAfiberEquipNameA() {
		return fiberOwnerAfiberEquipNameA;
	}
	public void setFiberOwnerAfiberEquipNameA(String fiberOwnerAfiberEquipNameA) {
		this.fiberOwnerAfiberEquipNameA = fiberOwnerAfiberEquipNameA;
	}
	public String getFiberOwnerAfiberEquipNameZ() {
		return fiberOwnerAfiberEquipNameZ;
	}
	public void setFiberOwnerAfiberEquipNameZ(String fiberOwnerAfiberEquipNameZ) {
		this.fiberOwnerAfiberEquipNameZ = fiberOwnerAfiberEquipNameZ;
	}
	public String getLinkDesinTypeA() {
		return linkDesinTypeA;
	}
	public void setLinkDesinTypeA(String linkDesinTypeA) {
		this.linkDesinTypeA = linkDesinTypeA;
	}
	public String getLinkDesinTypeZ() {
		return linkDesinTypeZ;
	}
	public void setLinkDesinTypeZ(String linkDesinTypeZ) {
		this.linkDesinTypeZ = linkDesinTypeZ;
	}
	public String getPortNum() {
		return portNum;
	}
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}
	public String getPortType() {
		return portType;
	}
	public void setPortType(String portType) {
		this.portType = portType;
	}
	public String getRequirmentArea() {
		return requirmentArea;
	}
	public void setRequirmentArea(String requirmentArea) {
		this.requirmentArea = requirmentArea;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

}
