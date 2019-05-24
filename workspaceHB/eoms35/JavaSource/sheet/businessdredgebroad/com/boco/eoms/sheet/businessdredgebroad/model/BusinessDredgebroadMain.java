
package com.boco.eoms.sheet.businessdredgebroad.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="BusinessDredgebroadbroadMain.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessDredgebroadbroadmain"
 */
public class BusinessDredgebroadMain extends BaseMain
{

	private String urgentDegree;

    private String businesstype1;

    private String bdeptContact;

    private String bdeptContactPhone;

    private String customNo;

    private String customName;	

    private String customContact;

    private String customContactPhone;

    private String customContactAdd;

    private String customContactPost;

    private String customTrade;

    private String customContactEmail;

    private String customLevel;

    private String brequirementDesc;

    private String factureArea;

    private String apnName;
	
    private String ipAddressAssign;
	
    private String apnRouterMode;

    private String apnIPPool;

    private String transferMode;
	
    private String appServerIPAdd;

    private String doubleGGSN;
	
    private String secondIPPool;
	
    private String isRadiusValidate;

    private String radiusValidateIPAdd;

    private String simHLR;

    private String terminalNum;

    private String numDetail;
	
    private String volumeAssessment;
	
   
	
	//彩信开始  

    private String bcode;

    private String siName;

    private String siEnterpriseCode;

    private String siServerCode;

    private String siConnectMMSGatewayID;

    private String siIPAdd;

    private String siUplinkUrl;

    private String isConnectMISC;

    private String comProtocol;

    private String connectGatewayBandwidth;

    private String maxConnections;

    private String maxUnderFlow;

    private String maxUplinkFlow;

    private String portRateIsDown;

    private String flowControlPriority;   

    private String appProgramme;

    private String nameListSetType;

    private String smsSigned;

    private String enterpriseCode;

    private String serverCode;

    private String connectPoint;

    private String hostIPAdd;

    private String connectGateway;

    private String gatewayCode;
    
    private String factureTime;
    private String authenticationModel;
    private String singleWordsBit;
	
	//短信结束  -->
	
	//传输专线开始 -->

    private String cityA;

    private String cityZ;

    private String bandwidth;

    private String amount;
    private String portAContactPhone;
    private String portA;
    private String portAInterfaceType;
    private String portADetailAdd;
    private String portABDeviceName;
    private String portABDevicePort;
    private String portZ;
    private String portZInterfaceType;
    private String portZBDeviceName;
    private String portZBDevicePort;
    private String portZContactPhone;
    
    private String isCustomerFlag;
    
    
    //集团新规范
    private String provinceName;
    private String cityName;
    private String countyName;
    private String cmanagerPhone;
    private String cmanagerContactPhone;
    private String productName;
    //GPRS
    private String numDetailFile;
    //短信、彩信
    private String enterpriseType;
    //短信
    private String connectGatewayName;
    private String connectGatewayID;
    //彩信
    private String siconnectMMSGatewayName;
    //传输专线
    private String confCRMTicketNo;
    private String transfBusiness;
    //端点Z业务设备名称(6月份新规范添加字段，陕西本地加)
 	private String portZDeviceName;   
    //传输专线下的子业务类型（陕西本地加）
 	private String zxType;
    //厂商编码(陕西本地加)
 	private String mainFactoryCode;
    //业务类型（陕西本地加）
 	private String mainBusinessType;
    //SI工程师姓名（陕西本地加）
 	private String siEngineerName;
    //SI工程师联系电话（陕西本地加）
 	private String siEngineerPhone;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getApnIPPool() {
		return apnIPPool;
	}
	public void setApnIPPool(String apnIPPool) {
		this.apnIPPool = apnIPPool;
	}
	public String getApnName() {
		return apnName;
	}
	public void setApnName(String apnName) {
		this.apnName = apnName;
	}
	public String getApnRouterMode() {
		return apnRouterMode;
	}
	public void setApnRouterMode(String apnRouterMode) {
		this.apnRouterMode = apnRouterMode;
	}
	public String getAppProgramme() {
		return appProgramme;
	}
	public void setAppProgramme(String appProgramme) {
		this.appProgramme = appProgramme;
	}
	public String getAppServerIPAdd() {
		return appServerIPAdd;
	}
	public void setAppServerIPAdd(String appServerIPAdd) {
		this.appServerIPAdd = appServerIPAdd;
	}
	public String getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}
	public String getBcode() {
		return bcode;
	}
	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	public String getBdeptContact() {
		return bdeptContact;
	}
	public void setBdeptContact(String bdeptContact) {
		this.bdeptContact = bdeptContact;
	}
	public String getBdeptContactPhone() {
		return bdeptContactPhone;
	}
	public void setBdeptContactPhone(String bdeptContactPhone) {
		this.bdeptContactPhone = bdeptContactPhone;
	}
	public String getBrequirementDesc() {
		return brequirementDesc;
	}
	public void setBrequirementDesc(String brequirementDesc) {
		this.brequirementDesc = brequirementDesc;
	}
	public String getBusinesstype1() {
		return businesstype1;
	}
	public void setBusinesstype1(String businesstype1) {
		this.businesstype1 = businesstype1;
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
	public String getComProtocol() {
		return comProtocol;
	}
	public void setComProtocol(String comProtocol) {
		this.comProtocol = comProtocol;
	}
	public String getConnectGateway() {
		return connectGateway;
	}
	public void setConnectGateway(String connectGateway) {
		this.connectGateway = connectGateway;
	}
	public String getConnectGatewayBandwidth() {
		return connectGatewayBandwidth;
	}
	public void setConnectGatewayBandwidth(String connectGatewayBandwidth) {
		this.connectGatewayBandwidth = connectGatewayBandwidth;
	}
	public String getConnectPoint() {
		return connectPoint;
	}
	public void setConnectPoint(String connectPoint) {
		this.connectPoint = connectPoint;
	}
	
	public String getCustomContact() {
		return customContact;
	}
	public void setCustomContact(String customContact) {
		this.customContact = customContact;
	}
	public String getCustomContactAdd() {
		return customContactAdd;
	}
	public void setCustomContactAdd(String customContactAdd) {
		this.customContactAdd = customContactAdd;
	}
	public String getCustomContactEmail() {
		return customContactEmail;
	}
	public void setCustomContactEmail(String customContactEmail) {
		this.customContactEmail = customContactEmail;
	}
	public String getCustomContactPhone() {
		return customContactPhone;
	}
	public void setCustomContactPhone(String customContactPhone) {
		this.customContactPhone = customContactPhone;
	}
	public String getCustomContactPost() {
		return customContactPost;
	}
	public void setCustomContactPost(String customContactPost) {
		this.customContactPost = customContactPost;
	}
	public String getCustomLevel() {
		return customLevel;
	}
	public void setCustomLevel(String customLevel) {
		this.customLevel = customLevel;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getCustomNo() {
		return customNo;
	}
	public void setCustomNo(String customNo) {
		this.customNo = customNo;
	}
	public String getCustomTrade() {
		return customTrade;
	}
	public void setCustomTrade(String customTrade) {
		this.customTrade = customTrade;
	}
	public String getDoubleGGSN() {
		return doubleGGSN;
	}
	public void setDoubleGGSN(String doubleGGSN) {
		this.doubleGGSN = doubleGGSN;
	}
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	public String getFactureArea() {
		return factureArea;
	}
	public void setFactureArea(String factureArea) {
		this.factureArea = factureArea;
	}
	public String getFlowControlPriority() {
		return flowControlPriority;
	}
	public void setFlowControlPriority(String flowControlPriority) {
		this.flowControlPriority = flowControlPriority;
	}
	public String getGatewayCode() {
		return gatewayCode;
	}
	public void setGatewayCode(String gatewayCode) {
		this.gatewayCode = gatewayCode;
	}
	public String getHostIPAdd() {
		return hostIPAdd;
	}
	public void setHostIPAdd(String hostIPAdd) {
		this.hostIPAdd = hostIPAdd;
	}
	
	public String getIpAddressAssign() {
		return ipAddressAssign;
	}
	public void setIpAddressAssign(String ipAddressAssign) {
		this.ipAddressAssign = ipAddressAssign;
	}
	public String getIsConnectMISC() {
		return isConnectMISC;
	}
	public void setIsConnectMISC(String isConnectMISC) {
		this.isConnectMISC = isConnectMISC;
	}
	public String getIsRadiusValidate() {
		return isRadiusValidate;
	}
	public void setIsRadiusValidate(String isRadiusValidate) {
		this.isRadiusValidate = isRadiusValidate;
	}
	public String getMaxConnections() {
		return maxConnections;
	}
	public void setMaxConnections(String maxConnections) {
		this.maxConnections = maxConnections;
	}
	public String getMaxUnderFlow() {
		return maxUnderFlow;
	}
	public void setMaxUnderFlow(String maxUnderFlow) {
		this.maxUnderFlow = maxUnderFlow;
	}
	public String getMaxUplinkFlow() {
		return maxUplinkFlow;
	}
	public void setMaxUplinkFlow(String maxUplinkFlow) {
		this.maxUplinkFlow = maxUplinkFlow;
	}
	public String getNameListSetType() {
		return nameListSetType;
	}
	public void setNameListSetType(String nameListSetType) {
		this.nameListSetType = nameListSetType;
	}
	public String getNumDetail() {
		return numDetail;
	}
	public void setNumDetail(String numDetail) {
		this.numDetail = numDetail;
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
	public String getPortRateIsDown() {
		return portRateIsDown;
	}
	public void setPortRateIsDown(String portRateIsDown) {
		this.portRateIsDown = portRateIsDown;
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
	public String getPortZInterfaceType() {
		return portZInterfaceType;
	}
	public void setPortZInterfaceType(String portZInterfaceType) {
		this.portZInterfaceType = portZInterfaceType;
	}
	public String getRadiusValidateIPAdd() {
		return radiusValidateIPAdd;
	}
	public void setRadiusValidateIPAdd(String radiusValidateIPAdd) {
		this.radiusValidateIPAdd = radiusValidateIPAdd;
	}
	public String getSecondIPPool() {
		return secondIPPool;
	}
	public void setSecondIPPool(String secondIPPool) {
		this.secondIPPool = secondIPPool;
	}
	public String getServerCode() {
		return serverCode;
	}
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}
	public String getSiConnectMMSGatewayID() {
		return siConnectMMSGatewayID;
	}
	public void setSiConnectMMSGatewayID(String siConnectMMSGatewayID) {
		this.siConnectMMSGatewayID = siConnectMMSGatewayID;
	}
	public String getSiEnterpriseCode() {
		return siEnterpriseCode;
	}
	public void setSiEnterpriseCode(String siEnterpriseCode) {
		this.siEnterpriseCode = siEnterpriseCode;
	}
	public String getSiIPAdd() {
		return siIPAdd;
	}
	public void setSiIPAdd(String siIPAdd) {
		this.siIPAdd = siIPAdd;
	}
	public String getSimHLR() {
		return simHLR;
	}
	public void setSimHLR(String simHLR) {
		this.simHLR = simHLR;
	}
	public String getSiName() {
		return siName;
	}
	public void setSiName(String siName) {
		this.siName = siName;
	}
	public String getSiServerCode() {
		return siServerCode;
	}
	public void setSiServerCode(String siServerCode) {
		this.siServerCode = siServerCode;
	}
	public String getSiUplinkUrl() {
		return siUplinkUrl;
	}
	public void setSiUplinkUrl(String siUplinkUrl) {
		this.siUplinkUrl = siUplinkUrl;
	}
	public String getSmsSigned() {
		return smsSigned;
	}
	public void setSmsSigned(String smsSigned) {
		this.smsSigned = smsSigned;
	}
	public String getTerminalNum() {
		return terminalNum;
	}
	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}
	public String getTransferMode() {
		return transferMode;
	}
	public void setTransferMode(String transferMode) {
		this.transferMode = transferMode;
	}
	public String getUrgentDegree() {
		return urgentDegree;
	}
	public void setUrgentDegree(String urgentDegree) {
		this.urgentDegree = urgentDegree;
	}
	public String getVolumeAssessment() {
		return volumeAssessment;
	}
	public void setVolumeAssessment(String volumeAssessment) {
		this.volumeAssessment = volumeAssessment;
	}
	public String getPortAContactPhone() {
		return portAContactPhone;
	}
	public void setPortAContactPhone(String portAContactPhone) {
		this.portAContactPhone = portAContactPhone;
	}
	public String getIsCustomerFlag() {
		return isCustomerFlag;
	}
	public void setIsCustomerFlag(String isCustomerFlag) {
		this.isCustomerFlag = isCustomerFlag;
	}
	public String getFactureTime() {
		return factureTime;
	}
	public void setFactureTime(String factureTime) {
		this.factureTime = factureTime;
	}
	public String getAuthenticationModel() {
		return authenticationModel;
	}
	public void setAuthenticationModel(String authenticationModel) {
		this.authenticationModel = authenticationModel;
	}
	public String getSingleWordsBit() {
		return singleWordsBit;
	}
	public void setSingleWordsBit(String singleWordsBit) {
		this.singleWordsBit = singleWordsBit;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getConfCRMTicketNo() {
		return confCRMTicketNo;
	}
	public void setConfCRMTicketNo(String confCRMTicketNo) {
		this.confCRMTicketNo = confCRMTicketNo;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getEnterpriseType() {
		return enterpriseType;
	}
	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	public String getNumDetailFile() {
		return numDetailFile;
	}
	public void setNumDetailFile(String numDetailFile) {
		this.numDetailFile = numDetailFile;
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
	public String getTransfBusiness() {
		return transfBusiness;
	}
	public void setTransfBusiness(String transfBusiness) {
		this.transfBusiness = transfBusiness;
	}
	public String getConnectGatewayID() {
		return connectGatewayID;
	}
	public void setConnectGatewayID(String connectGatewayID) {
		this.connectGatewayID = connectGatewayID;
	}
	public String getConnectGatewayName() {
		return connectGatewayName;
	}
	public void setConnectGatewayName(String connectGatewayName) {
		this.connectGatewayName = connectGatewayName;
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
	public String getSiconnectMMSGatewayName() {
		return siconnectMMSGatewayName;
	}
	public void setSiconnectMMSGatewayName(String siconnectMMSGatewayName) {
		this.siconnectMMSGatewayName = siconnectMMSGatewayName;
	}
	public String getPortZDeviceName() {
		return portZDeviceName;
	}
	public void setPortZDeviceName(String portZDeviceName) {
		this.portZDeviceName = portZDeviceName;
	}
	public String getZxType() {
		return zxType;
	}
	public void setZxType(String zxType) {
		this.zxType = zxType;
	}
	public String getMainBusinessType() {
		return mainBusinessType;
	}
	public void setMainBusinessType(String mainBusinessType) {
		this.mainBusinessType = mainBusinessType;
	}
	public String getMainFactoryCode() {
		return mainFactoryCode;
	}
	public void setMainFactoryCode(String mainFactoryCode) {
		this.mainFactoryCode = mainFactoryCode;
	}
	public String getSiEngineerName() {
		return siEngineerName;
	}
	public void setSiEngineerName(String siEngineerName) {
		this.siEngineerName = siEngineerName;
	}
	public String getSiEngineerPhone() {
		return siEngineerPhone;
	}
	public void setSiEngineerPhone(String siEngineerPhone) {
		this.siEngineerPhone = siEngineerPhone;
	}
	

    
//传输专线结束  -->

	

}
