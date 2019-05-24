package com.boco.eoms.businessupport.product.webapp.form;

import java.util.Date;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:短彩信
 * </p>
 * <p>
 * Description:彩信报工单
 * </p>
 * <p>
 * Wed Jun 02 19:47:25 CST 2010
 * </p>
 * 
 * @moudle.getAuthor() 李志刚
 * @moudle.getVersion() 3.5
 * 
 */
public class SmsspeciallineForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private String tradeId;					  //crm产品实例唯一标识
	
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

//	接入方式 
	private String inputMethodDuanXin;

//	协议   
	private String protocolDuanXin;
//	品牌范围 
	private String brandScope;

	//是否计费  
	private String isfee;
//	企业中文实名
	private String enterpriseCNName;
	//企业英文实名  ;
	private String enterpriseENname;
	//企业流控忙时
	private String enterpriseStreamBusy;
//	企业流控闲时
	private String enterpriseStreamLazy;
	//企业流控优先级 
	private String erterpriseLevel;

//	客户级别 
	private String customLevel;

//	是否要支持上行彩信流量 
	private String isSupportUpCaiXinStream;

//	限制发送和接收范围   
	private String limitSendAndRecScope;
//	主机地址          
	private String computerAdd;

//	客户网络情况=======
//	用户是否具有网络  
	private String userIsHaveNet;
//	客户业务安装地址 
	private String userBusinessIntallAdd;
//	客户是否需要MAS服务器 
	private String userIsNeedMasServer;

//	业务信息============

//	产品类型 
	private String productType;
//	产品实例名称  
	private String productInstanceName;
//	业务售后SLA维护登记 
	private String businessSLABook;
//	接入设备编号或名称
	private String inputDevNoAndName;
//	客户占用接入设备端口
	private String userUseDevPort;
//	接入设备端口状态  
	private String inputDevPortStatue;

//	服务号码类型     
	private String serverNumberType;


//	品牌范围  
	//private String brandScope;

//	是否接入DSMP
	private String isinputDSMP;

//	是否计费 
	private String isgetFee;

//	接入方式 
//	private String inputMethodDuanXin;
//
//	限制发送和接收范围  
//	private String limitSendAndRecScope;
//	接入等级号        
	private String inputLevelNumber;

//	最大接口数        
	private String lagerinterfaceNumber;
//	企业中文实名 
//	private String enterpriseCNName;
//	企业英文实名 
//	private String enterpriseENname;
//	短信签名     
	private String duanxinSing;
//	地域制作范围 
	private String scopeMakeScope;
//	时间制作范围 
	private String timeMakeScope;
//	接入点       
	private String inputDot;

//	主机IP地址   
	private String computerIpAdd;


	//接入网关带宽  
	private String inputNetWayBandWidth;



//	最大下发流量  
	private String lagerDownStream;
//	最大上行流量   
	private String lagerUptStream;
//	端口速率自适应下调 
	private String portSpeed;
//	鉴权模式     
	private String rightModule;
//	话单位长      
	private String feeBit;
//	黑白名单设置方式  
	private String nameSheetMethod;	
	
	
	/**
	 *
	 * 彩信接入的方式
	 *
	 */
	private java.lang.String cableModem;
   
	public void setCableModem(java.lang.String cableModem){
		this.cableModem= cableModem;       
	}
   
	public java.lang.String getCableModem(){
		return this.cableModem;
	}

	/**
	 *
	 * 业务范围
	 *
	 */
	private java.lang.String scopeOfBusiness;
   
	public void setScopeOfBusiness(java.lang.String scopeOfBusiness){
		this.scopeOfBusiness= scopeOfBusiness;       
	}
   
	public java.lang.String getScopeOfBusiness(){
		return this.scopeOfBusiness;
	}

	/**
	 *
	 * 主机地址
	 *
	 */
	private java.lang.String hostAddress;
   
	public void setHostAddress(java.lang.String hostAddress){
		this.hostAddress= hostAddress;       
	}
   
	public java.lang.String getHostAddress(){
		return this.hostAddress;
	}

	/**
	 *
	 * 上行URL
	 *
	 */
	private java.lang.String upUrl;
   
	public void setUpUrl(java.lang.String upUrl){
		this.upUrl= upUrl;       
	}
   
	public java.lang.String getUpUrl(){
		return this.upUrl;
	}

	/**
	 *
	 * ProvisionURL
	 *
	 */
	private java.lang.String provisionURL;
   
	public void setProvisionURL(java.lang.String provisionURL){
		this.provisionURL= provisionURL;       
	}
   
	public java.lang.String getProvisionURL(){
		return this.provisionURL;
	}

	/**
	 *
	 * 是否接入MISC/DSMP
	 *
	 */
	private java.lang.String ifCable;
   
	public void setIfCable(java.lang.String ifCable){
		this.ifCable= ifCable;       
	}
   
	public java.lang.String getIfCable(){
		return this.ifCable;
	}

	/**
	 *
	 * 使用协议
	 *
	 */
	private java.lang.String useAccord;
   
	public void setUseAccord(java.lang.String useAccord){
		this.useAccord= useAccord;       
	}
   
	public java.lang.String getUseAccord(){
		return this.useAccord;
	}

	/**
	 *
	 * 业务流向限制
	 *
	 */
	private java.lang.String businessLimit;
   
	public void setBusinessLimit(java.lang.String businessLimit){
		this.businessLimit= businessLimit;       
	}
   
	public java.lang.String getBusinessLimit(){
		return this.businessLimit;
	}

	/**
	 *
	 * 业务名称
	 *
	 */
	private java.lang.String businessName;
   
	public void setBusinessName(java.lang.String businessName){
		this.businessName= businessName;       
	}
   
	public java.lang.String getBusinessName(){
		return this.businessName;
	}

	/**
	 *
	 * 业务代码
	 *
	 */
	private java.lang.String businessCode;
   
	public void setBusinessCode(java.lang.String businessCode){
		this.businessCode= businessCode;       
	}
   
	public java.lang.String getBusinessCode(){
		return this.businessCode;
	}

	/**
	 *
	 * 终端IP地址类型
	 *
	 */
	private java.lang.String ipAdressType;
   
	public void setIpAdressType(java.lang.String ipAdressType){
		this.ipAdressType= ipAdressType;       
	}
   
	public java.lang.String getIpAdressType(){
		return this.ipAdressType;
	}

	/**
	 *
	 * 终端IP地址
	 *
	 */
	private java.lang.String ipAdress;
   
	public void setIpAdress(java.lang.String ipAdress){
		this.ipAdress= ipAdress;       
	}
   
	public java.lang.String getIpAdress(){
		return this.ipAdress;
	}

	/**
	 *
	 * 计费类型
	 *
	 */
	private java.lang.String billingType;
   
	public void setBillingType(java.lang.String billingType){
		this.billingType= billingType;       
	}
   
	public java.lang.String getBillingType(){
		return this.billingType;
	}

	/**
	 *
	 * 信息费
	 *
	 */
	private java.lang.String informationFeeds;
   
	public void setInformationFeeds(java.lang.String informationFeeds){
		this.informationFeeds= informationFeeds;       
	}
   
	public java.lang.String getInformationFeeds(){
		return this.informationFeeds;
	}

	/**
	 *
	 * 说明
	 *
	 */
	private java.lang.String illustrate;
   
	public void setIllustrate(java.lang.String illustrate){
		this.illustrate= illustrate;       
	}
   
	public java.lang.String getIllustrate(){
		return this.illustrate;
	}

	/**
	 *
	 * 产品编号
	 *
	 */
	private java.lang.String productNum;
   
	public void setProductNum(java.lang.String productNum){
		this.productNum= productNum;       
	}
   
	public java.lang.String getProductNum(){
		return this.productNum;
	}
	
	private String orderSheet_Id;

	public String getOrderSheet_Id() {
		return orderSheet_Id;
	}

	public void setOrderSheet_Id(String orderSheet_Id) {
		this.orderSheet_Id = orderSheet_Id;
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

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	public String getFiberOwnerA() {
		return fiberOwnerA;
	}

	public void setFiberOwnerA(String fiberOwnerA) {
		this.fiberOwnerA = fiberOwnerA;
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

	public String getFiberOwnerZ() {
		return fiberOwnerZ;
	}

	public void setFiberOwnerZ(String fiberOwnerZ) {
		this.fiberOwnerZ = fiberOwnerZ;
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

	public String getPortABDeviceName() {
		return portABDeviceName;
	}

	public void setPortABDeviceName(String portABDeviceName) {
		this.portABDeviceName = portABDeviceName;
	}

	public String getPortABDeviceType() {
		return portABDeviceType;
	}

	public void setPortABDeviceType(String portABDeviceType) {
		this.portABDeviceType = portABDeviceType;
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

	public String getPortZBDeviceType() {
		return portZBDeviceType;
	}

	public void setPortZBDeviceType(String portZBDeviceType) {
		this.portZBDeviceType = portZBDeviceType;
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

	public Date getPromise() {
		return promise;
	}

	public void setPromise(Date promise) {
		this.promise = promise;
	}

	public String getRequirmentArea() {
		return requirmentArea;
	}

	public void setRequirmentArea(String requirmentArea) {
		this.requirmentArea = requirmentArea;
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

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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

	public String getUserIsUserNet() {
		return userIsUserNet;
	}

	public void setUserIsUserNet(String userIsUserNet) {
		this.userIsUserNet = userIsUserNet;
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

	public String getUserSpecifyDevNeed() {
		return userSpecifyDevNeed;
	}

	public void setUserSpecifyDevNeed(String userSpecifyDevNeed) {
		this.userSpecifyDevNeed = userSpecifyDevNeed;
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

	public String getWhours() {
		return whours;
	}

	public void setWhours(String whours) {
		this.whours = whours;
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



	public String getBrandScope() {
		return brandScope;
	}

	public void setBrandScope(String brandScope) {
		this.brandScope = brandScope;
	}

	public String getCustomLevel() {
		return customLevel;
	}

	public void setCustomLevel(String customLevel) {
		this.customLevel = customLevel;
	}

	public String getEnterpriseCNName() {
		return enterpriseCNName;
	}

	public void setEnterpriseCNName(String enterpriseCNName) {
		this.enterpriseCNName = enterpriseCNName;
	}

	public String getEnterpriseENname() {
		return enterpriseENname;
	}

	public void setEnterpriseENname(String enterpriseENname) {
		this.enterpriseENname = enterpriseENname;
	}

	public String getEnterpriseStreamBusy() {
		return enterpriseStreamBusy;
	}

	public void setEnterpriseStreamBusy(String enterpriseStreamBusy) {
		this.enterpriseStreamBusy = enterpriseStreamBusy;
	}

	public String getEnterpriseStreamLazy() {
		return enterpriseStreamLazy;
	}

	public void setEnterpriseStreamLazy(String enterpriseStreamLazy) {
		this.enterpriseStreamLazy = enterpriseStreamLazy;
	}

	public String getErterpriseLevel() {
		return erterpriseLevel;
	}

	public void setErterpriseLevel(String erterpriseLevel) {
		this.erterpriseLevel = erterpriseLevel;
	}

	public String getInputMethodDuanXin() {
		return inputMethodDuanXin;
	}

	public void setInputMethodDuanXin(String inputMethodDuanXin) {
		this.inputMethodDuanXin = inputMethodDuanXin;
	}

	public String getIsfee() {
		return isfee;
	}

	public void setIsfee(String isfee) {
		this.isfee = isfee;
	}

	public String getProtocolDuanXin() {
		return protocolDuanXin;
	}

	public void setProtocolDuanXin(String protocolDuanXin) {
		this.protocolDuanXin = protocolDuanXin;
	}

	public String getBusinessSLABook() {
		return businessSLABook;
	}

	public void setBusinessSLABook(String businessSLABook) {
		this.businessSLABook = businessSLABook;
	}

	public String getComputerAdd() {
		return computerAdd;
	}

	public void setComputerAdd(String computerAdd) {
		this.computerAdd = computerAdd;
	}

	public String getComputerIpAdd() {
		return computerIpAdd;
	}

	public void setComputerIpAdd(String computerIpAdd) {
		this.computerIpAdd = computerIpAdd;
	}

	public String getDuanxinSing() {
		return duanxinSing;
	}

	public void setDuanxinSing(String duanxinSing) {
		this.duanxinSing = duanxinSing;
	}

	public String getFeeBit() {
		return feeBit;
	}

	public void setFeeBit(String feeBit) {
		this.feeBit = feeBit;
	}

	public String getInputDevNoAndName() {
		return inputDevNoAndName;
	}

	public void setInputDevNoAndName(String inputDevNoAndName) {
		this.inputDevNoAndName = inputDevNoAndName;
	}

	public String getInputDevPortStatue() {
		return inputDevPortStatue;
	}

	public void setInputDevPortStatue(String inputDevPortStatue) {
		this.inputDevPortStatue = inputDevPortStatue;
	}

	public String getInputDot() {
		return inputDot;
	}

	public void setInputDot(String inputDot) {
		this.inputDot = inputDot;
	}

	public String getInputLevelNumber() {
		return inputLevelNumber;
	}

	public void setInputLevelNumber(String inputLevelNumber) {
		this.inputLevelNumber = inputLevelNumber;
	}

	public String getInputNetWayBandWidth() {
		return inputNetWayBandWidth;
	}

	public void setInputNetWayBandWidth(String inputNetWayBandWidth) {
		this.inputNetWayBandWidth = inputNetWayBandWidth;
	}

	public String getIsgetFee() {
		return isgetFee;
	}

	public void setIsgetFee(String isgetFee) {
		this.isgetFee = isgetFee;
	}

	public String getIsinputDSMP() {
		return isinputDSMP;
	}

	public void setIsinputDSMP(String isinputDSMP) {
		this.isinputDSMP = isinputDSMP;
	}

	public String getIsSupportUpCaiXinStream() {
		return isSupportUpCaiXinStream;
	}

	public void setIsSupportUpCaiXinStream(String isSupportUpCaiXinStream) {
		this.isSupportUpCaiXinStream = isSupportUpCaiXinStream;
	}

	public String getLagerDownStream() {
		return lagerDownStream;
	}

	public void setLagerDownStream(String lagerDownStream) {
		this.lagerDownStream = lagerDownStream;
	}

	public String getLagerinterfaceNumber() {
		return lagerinterfaceNumber;
	}

	public void setLagerinterfaceNumber(String lagerinterfaceNumber) {
		this.lagerinterfaceNumber = lagerinterfaceNumber;
	}

	public String getLagerUptStream() {
		return lagerUptStream;
	}

	public void setLagerUptStream(String lagerUptStream) {
		this.lagerUptStream = lagerUptStream;
	}

	public String getLimitSendAndRecScope() {
		return limitSendAndRecScope;
	}

	public void setLimitSendAndRecScope(String limitSendAndRecScope) {
		this.limitSendAndRecScope = limitSendAndRecScope;
	}



	public String getPortSpeed() {
		return portSpeed;
	}

	public String getNameSheetMethod() {
		return nameSheetMethod;
	}

	public void setNameSheetMethod(String nameSheetMethod) {
		this.nameSheetMethod = nameSheetMethod;
	}

	public void setPortSpeed(String portSpeed) {
		this.portSpeed = portSpeed;
	}

	public String getProductInstanceName() {
		return productInstanceName;
	}

	public void setProductInstanceName(String productInstanceName) {
		this.productInstanceName = productInstanceName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getRightModule() {
		return rightModule;
	}

	public void setRightModule(String rightModule) {
		this.rightModule = rightModule;
	}

	public String getScopeMakeScope() {
		return scopeMakeScope;
	}

	public void setScopeMakeScope(String scopeMakeScope) {
		this.scopeMakeScope = scopeMakeScope;
	}

	public String getServerNumberType() {
		return serverNumberType;
	}

	public void setServerNumberType(String serverNumberType) {
		this.serverNumberType = serverNumberType;
	}

	public String getTimeMakeScope() {
		return timeMakeScope;
	}

	public void setTimeMakeScope(String timeMakeScope) {
		this.timeMakeScope = timeMakeScope;
	}

	public String getUserBusinessIntallAdd() {
		return userBusinessIntallAdd;
	}

	public void setUserBusinessIntallAdd(String userBusinessIntallAdd) {
		this.userBusinessIntallAdd = userBusinessIntallAdd;
	}

	public String getUserIsHaveNet() {
		return userIsHaveNet;
	}

	public void setUserIsHaveNet(String userIsHaveNet) {
		this.userIsHaveNet = userIsHaveNet;
	}

	public String getUserIsNeedMasServer() {
		return userIsNeedMasServer;
	}

	public void setUserIsNeedMasServer(String userIsNeedMasServer) {
		this.userIsNeedMasServer = userIsNeedMasServer;
	}

	public String getUserUseDevPort() {
		return userUseDevPort;
	}

	public void setUserUseDevPort(String userUseDevPort) {
		this.userUseDevPort = userUseDevPort;
	}

}