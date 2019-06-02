package com.boco.eoms.businessupport.product.webapp.form;

import java.sql.Date;


import com.boco.eoms.base.webapp.form.BaseForm;

public class LanguageSpecialLineForm extends BaseForm implements java.io.Serializable{
	
	private String id;
	
	private String tradeId;					  //crm产品实例唯一标识
//	用来和主表关联
	private String orderSheet_Id;
//	平台呼入
//	接入号码         
	private String inputNumber;
//	位长             
	private String bitLength;
//	业务类型         
	private String businessType;
//	主叫用户开放品牌 
	private String callUserOpenBrand;
//	主叫用户归属地    
	private String callUserLocalZone;
//	业务开放地       
	private String businessOpenZone;
//	拨号方式          
	private String callNumberType;
//	是否开通省内长途来话
	private String isOpenPronicePhone;
//	是否开通省际长途来话
	private String isOpenProniceBeteen;
//	是否开通国际长途来话
	private String isOpenContryPhone;
//
//	平台呼出
//	客户主叫号码      
	private String userCallNumber;
//	主叫号码是否透传   
	private String userCallIsTouChuan;
//	主叫号码修改方式   
	private String userCallModifyType;
//	呼出范围          
	private String userCallScope;
//	呼出号码形式     
	private String userCallType;
//	信令方式        
	private String infoType;
//	信令点编码      
	private String infoPointCode;
//	信令协议        
	private String infoProtocal;
//	中继数         
	private String infoBetweenNumber;
//	计费说明        
	private String feeDesc;
//	客户机房地址     
	private String userComputerHoseAdd;
//	客户端设备品牌   
	private String userPointDevBrand;
//	型号             
	private String userComputerType;
//	产权             
	private String userComputerRight;
//	是否网间短号转网内
	private String isNumberTrasiNet;
//
//	备注            
	private String yuyinRemark;
//	申请产品名称     
	private String applyProductName;
//	客户需求描述     
	private String userNeedDesc;
//	拟生效时间       
	private Date planOenDate;
//	产品编号         
	private String productCode;
//
//	传输电路调度反馈
//	(签收)
//	签收人          
	private String appliedPerson;
//	联系电话        
	private String connectPhone;
//	签收部门        
	private String appliedDept;
//	签收时间        
	private Date appliedDate;
//	是否签收        
	private String isApplied;
//	不签收意见         
	private String isNotAppledOpinition;
//	(电路调度反馈)
//	处理人            
	private String dealPersonCircuit;
//	处理部门         
	private String dealDeptCircuit;
//	联系电话         
	private String dealConPhoneCirsuit;
//	处理时间         
	private Date dealDateCircuit;
//	电路调度结果     
	private String dealResultCircuit;
//	不成功原因       
	private String dealNoSuccessReason;
//	电路编号         ;
	private String dealCircuitCode;
//	回复意见         
	private String dealBackOpinition;
	 //   城市A* 
	private String cityA;
    // 城市Z* 
	private String cityZ;
   //  A站点名称
	private String siteNameA;
  //   Z站点名称
	private String siteNameZ;
   // 端口A
	private String portA;
   // 端口Z
	private String portZ;
   // A接口类型及型号
	private String portAInterfaceType;
  // Z接口类型及型号
	private String portZInterfaceType;
   	//A端点详细地址
	private String portADetailAdd;
   // Z端点详细地址/
	private String portZDetailAdd;
  	//端点A业务设备名称
	private String portABDeviceName;
	   //	端点Z业务设备名称*
	private String portZBDeviceName;
	  	//端点A业务设备端口
	private String portABDevicePort;
	   //	端点Z业务设备端口*
	private String portZBDevicePort;
	  	//A客户端联系人
	private String apointLocalPerson;
   // Z客户端联系人
	private String zpointLocalPerson;
    // A客户端联系电话
	private String portAContactPhone;
   //  Z客户端联系电话
	private String portZContactPhone;
    //带宽*
	private String bandwidth;
   //电路速率*
	private String circuitRate;
   	//用户是否有用户网站*
	
	private String userIsUserNet;
	     // 用户个性化设备需求*
	private String userSpecifyDevNeed;
	 //备用
	  private Date    oneDate;

//		A接入点机房
	  private String  apointComputHouseName;
//		  A接入点地址</td>
	  private String 	      interfacePointAddA;
		   
//	      A接入点站点名称（接入基站）</td>
	  private String    interfaceSiteNameA;
//	      A接入点设备编码</td>
	  private String    interfaceEquipCodeA;
//	      A光纤设备名称</td>
	  private String   fiberEquipNameA;
//	     A光纤设备编号</td>
	  private String    fiberEquipCodeA;
//	     A纤芯个数*</td>
	  private String    fiberAcount;
//	      A光缆路由描述*</td>
	  private String   fiberAroute;
	   
//	      A接入点类型</td>
	      
	  private String   interfacePointTypeA;
//    生效日期
	  private String planImplStringDate;
	  //0608
//	用户端设备端口类型
	  private String portABDeviceType;
//传输线路勘查信
//	  最后一公里光缆长度
	  private String fiberLengthA;
//A光缆产权
	  private String fiberOwnerA;
//	  敷设方式
	  private String buildTypeA;
	  //客户端到接入点能否通达
	  private String isOkBetweenUserA;
	  //不能接入的原因
	  private String noInputResonA;
//传输容量勘查信息
	  //传输容量是否满足开通
	  private String isDeviceAllowOpenA;
	  //是否需要添加板卡
	  private String isNeedAddCardA;
	  //板卡类型
	  private String cardTypeA;
	  //板卡数量
	  private String cardNumA;
	  
	  private String isGetInterfaceA;

	  private String getInterfaceNoA;

	  private String theLastOpinionA;

		private String circuitSheetId;	
		
		private String circuitName;    //电路名称
		private String deleted;
		
	public String getDeleted() {
			return deleted;
		}
		public void setDeleted(String deleted) {
			this.deleted = deleted;
		}
	public String getCircuitName() {
			return circuitName;
		}
		public void setCircuitName(String circuitName) {
			this.circuitName = circuitName;
		}
	public String getApointComputHouseName() {
		return apointComputHouseName;
	}
	public void setApointComputHouseName(String apointComputHouseName) {
		this.apointComputHouseName = apointComputHouseName;
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
	public String getFiberEquipCodeA() {
		return fiberEquipCodeA;
	}
	public void setFiberEquipCodeA(String fiberEquipCodeA) {
		this.fiberEquipCodeA = fiberEquipCodeA;
	}
	public String getFiberEquipNameA() {
		return fiberEquipNameA;
	}
	public void setFiberEquipNameA(String fiberEquipNameA) {
		this.fiberEquipNameA = fiberEquipNameA;
	}
	public String getInterfaceEquipCodeA() {
		return interfaceEquipCodeA;
	}
	public void setInterfaceEquipCodeA(String interfaceEquipCodeA) {
		this.interfaceEquipCodeA = interfaceEquipCodeA;
	}
	public String getInterfacePointAddA() {
		return interfacePointAddA;
	}
	public void setInterfacePointAddA(String interfacePointAddA) {
		this.interfacePointAddA = interfacePointAddA;
	}
	public String getInterfacePointTypeA() {
		return interfacePointTypeA;
	}
	public void setInterfacePointTypeA(String interfacePointTypeA) {
		this.interfacePointTypeA = interfacePointTypeA;
	}
	public String getInterfaceSiteNameA() {
		return interfaceSiteNameA;
	}
	public void setInterfaceSiteNameA(String interfaceSiteNameA) {
		this.interfaceSiteNameA = interfaceSiteNameA;
	}
	public String getApointLocalPerson() {
		return apointLocalPerson;
	}
	public void setApointLocalPerson(String apointLocalPerson) {
		this.apointLocalPerson = apointLocalPerson;
	}
	public String getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}
	public String getCircuitRate() {
		return circuitRate;
	}
	public void setCircuitRate(String circuitRate) {
		this.circuitRate = circuitRate;
	}
	public String getCityA() {
		return cityA;
	}
	public void setCityA(String cityA) {
		this.cityA = cityA;
	}
	public String getCityZ() {
		return cityZ;
	}
	public void setCityZ(String cityZ) {
		this.cityZ = cityZ;
	}
	public Date getOneDate() {
		return oneDate;
	}
	public void setOneDate(Date oneDate) {
		this.oneDate = oneDate;
	}
	public String getPortA() {
		return portA;
	}
	public void setPortA(String portA) {
		this.portA = portA;
	}
	public String getPortABDeviceName() {
		return portABDeviceName;
	}
	public void setPortABDeviceName(String portABDeviceName) {
		this.portABDeviceName = portABDeviceName;
	}
	public String getPortABDevicePort() {
		return portABDevicePort;
	}
	public void setPortABDevicePort(String portABDevicePort) {
		this.portABDevicePort = portABDevicePort;
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
	public String getPortZ() {
		return portZ;
	}
	public void setPortZ(String portZ) {
		this.portZ = portZ;
	}
	public String getPortZBDeviceName() {
		return portZBDeviceName;
	}
	public void setPortZBDeviceName(String portZBDeviceName) {
		this.portZBDeviceName = portZBDeviceName;
	}
	public String getPortZBDevicePort() {
		return portZBDevicePort;
	}
	public void setPortZBDevicePort(String portZBDevicePort) {
		this.portZBDevicePort = portZBDevicePort;
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
	public String getZpointLocalPerson() {
		return zpointLocalPerson;
	}
	public void setZpointLocalPerson(String zpointLocalPerson) {
		this.zpointLocalPerson = zpointLocalPerson;
	}
	public Date getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getAppliedDept() {
		return appliedDept;
	}
	public void setAppliedDept(String appliedDept) {
		this.appliedDept = appliedDept;
	}
	public String getAppliedPerson() {
		return appliedPerson;
	}
	public void setAppliedPerson(String appliedPerson) {
		this.appliedPerson = appliedPerson;
	}
	public String getApplyProductName() {
		return applyProductName;
	}
	public void setApplyProductName(String applyProductName) {
		this.applyProductName = applyProductName;
	}
	public String getBitLength() {
		return bitLength;
	}
	public void setBitLength(String bitLength) {
		this.bitLength = bitLength;
	}
	public String getBusinessOpenZone() {
		return businessOpenZone;
	}
	public void setBusinessOpenZone(String businessOpenZone) {
		this.businessOpenZone = businessOpenZone;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getCallNumberType() {
		return callNumberType;
	}
	public void setCallNumberType(String callNumberType) {
		this.callNumberType = callNumberType;
	}
	public String getCallUserLocalZone() {
		return callUserLocalZone;
	}
	public void setCallUserLocalZone(String callUserLocalZone) {
		this.callUserLocalZone = callUserLocalZone;
	}
	public String getCallUserOpenBrand() {
		return callUserOpenBrand;
	}
	public void setCallUserOpenBrand(String callUserOpenBrand) {
		this.callUserOpenBrand = callUserOpenBrand;
	}
	public String getConnectPhone() {
		return connectPhone;
	}
	public void setConnectPhone(String connectPhone) {
		this.connectPhone = connectPhone;
	}
	public String getDealBackOpinition() {
		return dealBackOpinition;
	}
	public void setDealBackOpinition(String dealBackOpinition) {
		this.dealBackOpinition = dealBackOpinition;
	}
	public String getDealCircuitCode() {
		return dealCircuitCode;
	}
	public void setDealCircuitCode(String dealCircuitCode) {
		this.dealCircuitCode = dealCircuitCode;
	}
	public String getDealConPhoneCirsuit() {
		return dealConPhoneCirsuit;
	}
	public void setDealConPhoneCirsuit(String dealConPhoneCirsuit) {
		this.dealConPhoneCirsuit = dealConPhoneCirsuit;
	}
	public Date getDealDateCircuit() {
		return dealDateCircuit;
	}
	public void setDealDateCircuit(Date dealDateCircuit) {
		this.dealDateCircuit = dealDateCircuit;
	}
	public String getDealDeptCircuit() {
		return dealDeptCircuit;
	}
	public void setDealDeptCircuit(String dealDeptCircuit) {
		this.dealDeptCircuit = dealDeptCircuit;
	}
	public String getDealNoSuccessReason() {
		return dealNoSuccessReason;
	}
	public void setDealNoSuccessReason(String dealNoSuccessReason) {
		this.dealNoSuccessReason = dealNoSuccessReason;
	}
	public String getDealPersonCircuit() {
		return dealPersonCircuit;
	}
	public void setDealPersonCircuit(String dealPersonCircuit) {
		this.dealPersonCircuit = dealPersonCircuit;
	}
	public String getDealResultCircuit() {
		return dealResultCircuit;
	}
	public void setDealResultCircuit(String dealResultCircuit) {
		this.dealResultCircuit = dealResultCircuit;
	}
	public String getFeeDesc() {
		return feeDesc;
	}
	public void setFeeDesc(String feeDesc) {
		this.feeDesc = feeDesc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoBetweenNumber() {
		return infoBetweenNumber;
	}
	public void setInfoBetweenNumber(String infoBetweenNumber) {
		this.infoBetweenNumber = infoBetweenNumber;
	}
	public String getInfoPointCode() {
		return infoPointCode;
	}
	public void setInfoPointCode(String infoPointCode) {
		this.infoPointCode = infoPointCode;
	}
	public String getInfoProtocal() {
		return infoProtocal;
	}
	public void setInfoProtocal(String infoProtocal) {
		this.infoProtocal = infoProtocal;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getInputNumber() {
		return inputNumber;
	}
	public void setInputNumber(String inputNumber) {
		this.inputNumber = inputNumber;
	}
	public String getIsApplied() {
		return isApplied;
	}
	public void setIsApplied(String isApplied) {
		this.isApplied = isApplied;
	}
	public String getIsNotAppledOpinition() {
		return isNotAppledOpinition;
	}
	public void setIsNotAppledOpinition(String isNotAppledOpinition) {
		this.isNotAppledOpinition = isNotAppledOpinition;
	}
	public String getIsNumberTrasiNet() {
		return isNumberTrasiNet;
	}
	public void setIsNumberTrasiNet(String isNumberTrasiNet) {
		this.isNumberTrasiNet = isNumberTrasiNet;
	}
	public String getIsOpenContryPhone() {
		return isOpenContryPhone;
	}
	public void setIsOpenContryPhone(String isOpenContryPhone) {
		this.isOpenContryPhone = isOpenContryPhone;
	}
	public String getIsOpenProniceBeteen() {
		return isOpenProniceBeteen;
	}
	public void setIsOpenProniceBeteen(String isOpenProniceBeteen) {
		this.isOpenProniceBeteen = isOpenProniceBeteen;
	}
	public String getIsOpenPronicePhone() {
		return isOpenPronicePhone;
	}
	public void setIsOpenPronicePhone(String isOpenPronicePhone) {
		this.isOpenPronicePhone = isOpenPronicePhone;
	}
	public String getOrderSheet_Id() {
		return orderSheet_Id;
	}
	public void setOrderSheet_Id(String orderSheet_Id) {
		this.orderSheet_Id = orderSheet_Id;
	}
	public Date getPlanOenDate() {
		return planOenDate;
	}
	public void setPlanOenDate(Date planOenDate) {
		this.planOenDate = planOenDate;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getUserCallIsTouChuan() {
		return userCallIsTouChuan;
	}
	public void setUserCallIsTouChuan(String userCallIsTouChuan) {
		this.userCallIsTouChuan = userCallIsTouChuan;
	}
	public String getUserCallModifyType() {
		return userCallModifyType;
	}
	public void setUserCallModifyType(String userCallModifyType) {
		this.userCallModifyType = userCallModifyType;
	}
	public String getUserCallNumber() {
		return userCallNumber;
	}
	public void setUserCallNumber(String userCallNumber) {
		this.userCallNumber = userCallNumber;
	}
	public String getUserCallScope() {
		return userCallScope;
	}
	public void setUserCallScope(String userCallScope) {
		this.userCallScope = userCallScope;
	}
	public String getUserCallType() {
		return userCallType;
	}
	public void setUserCallType(String userCallType) {
		this.userCallType = userCallType;
	}
	public String getUserComputerHoseAdd() {
		return userComputerHoseAdd;
	}
	public void setUserComputerHoseAdd(String userComputerHoseAdd) {
		this.userComputerHoseAdd = userComputerHoseAdd;
	}
	public String getUserComputerRight() {
		return userComputerRight;
	}
	public void setUserComputerRight(String userComputerRight) {
		this.userComputerRight = userComputerRight;
	}
	public String getUserComputerType() {
		return userComputerType;
	}
	public void setUserComputerType(String userComputerType) {
		this.userComputerType = userComputerType;
	}
	public String getUserNeedDesc() {
		return userNeedDesc;
	}
	public void setUserNeedDesc(String userNeedDesc) {
		this.userNeedDesc = userNeedDesc;
	}
	public String getUserPointDevBrand() {
		return userPointDevBrand;
	}
	public void setUserPointDevBrand(String userPointDevBrand) {
		this.userPointDevBrand = userPointDevBrand;
	}
	public String getYuyinRemark() {
		return yuyinRemark;
	}
	public void setYuyinRemark(String yuyinRemark) {
		this.yuyinRemark = yuyinRemark;
	}
	public String getPlanImplStringDate() {
		return planImplStringDate;
	}
	public void setPlanImplStringDate(String planImplStringDate) {
		this.planImplStringDate = planImplStringDate;
	}
	public String getBuildTypeA() {
		return buildTypeA;
	}
	public void setBuildTypeA(String buildTypeA) {
		this.buildTypeA = buildTypeA;
	}
	public String getCardNumA() {
		return cardNumA;
	}
	public void setCardNumA(String cardNumA) {
		this.cardNumA = cardNumA;
	}
	public String getCardTypeA() {
		return cardTypeA;
	}
	public void setCardTypeA(String cardTypeA) {
		this.cardTypeA = cardTypeA;
	}
	public String getFiberLengthA() {
		return fiberLengthA;
	}
	public void setFiberLengthA(String fiberLengthA) {
		this.fiberLengthA = fiberLengthA;
	}
	public String getFiberOwnerA() {
		return fiberOwnerA;
	}
	public void setFiberOwnerA(String fiberOwnerA) {
		this.fiberOwnerA = fiberOwnerA;
	}
	public String getIsDeviceAllowOpenA() {
		return isDeviceAllowOpenA;
	}
	public void setIsDeviceAllowOpenA(String isDeviceAllowOpenA) {
		this.isDeviceAllowOpenA = isDeviceAllowOpenA;
	}
	public String getIsNeedAddCardA() {
		return isNeedAddCardA;
	}
	public void setIsNeedAddCardA(String isNeedAddCardA) {
		this.isNeedAddCardA = isNeedAddCardA;
	}
	public String getIsOkBetweenUserA() {
		return isOkBetweenUserA;
	}
	public void setIsOkBetweenUserA(String isOkBetweenUserA) {
		this.isOkBetweenUserA = isOkBetweenUserA;
	}
	public String getNoInputResonA() {
		return noInputResonA;
	}
	public void setNoInputResonA(String noInputResonA) {
		this.noInputResonA = noInputResonA;
	}
	public String getPortABDeviceType() {
		return portABDeviceType;
	}
	public void setPortABDeviceType(String portABDeviceType) {
		this.portABDeviceType = portABDeviceType;
	}
	public String getCircuitSheetId() {
		return circuitSheetId;
	}
	public void setCircuitSheetId(String circuitSheetId) {
		this.circuitSheetId = circuitSheetId;
	}
	public String getGetInterfaceNoA() {
		return getInterfaceNoA;
	}
	public void setGetInterfaceNoA(String getInterfaceNoA) {
		this.getInterfaceNoA = getInterfaceNoA;
	}
	public String getIsGetInterfaceA() {
		return isGetInterfaceA;
	}
	public void setIsGetInterfaceA(String isGetInterfaceA) {
		this.isGetInterfaceA = isGetInterfaceA;
	}
	public String getTheLastOpinionA() {
		return theLastOpinionA;
	}
	public void setTheLastOpinionA(String theLastOpinionA) {
		this.theLastOpinionA = theLastOpinionA;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
}
