
package com.boco.eoms.sheet.businesschange.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="BusinessChangeMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="BusinessChangemain"
 */
public class BusinessChangeMain extends BaseMain {

    /**
     * @dicttype
     * @texttype
     */
    private int urgentDegree;

    /**
     * @dicttype
     * @texttype
     */
    private java.lang.String businesstype1;

    /**
     * @texttype
     */
    private java.lang.String bdeptContact;

    /**
     * @texttype
     */
    private java.lang.String bdeptContactPhone;

    /**
     * @texttype
     */
    private java.lang.String customNo;

    /**
     * @texttype
     */
    private java.lang.String customName;

    /**
     * @texttype
     */
    private java.lang.String customContact;

    /**
     * @texttype
     */
    private java.lang.String customContactPhone;

    /**
     * @texttype
     */
    private java.lang.String customContactAdd;

    /**
     * @texttype
     */
    private java.lang.String customContactPost;

    /**
     * @texttype
     */
    private java.lang.String customTrade;

    /**
     * @texttype
     */
    private java.lang.String customContactEmail;

    /**
     * @texttype
     */
    private java.lang.String customLevel;

    /**
     * @texttype
     */
    private java.lang.String brequirementDesc;

    /**
     * @texttype
     */
    private java.lang.String factureArea;

    /**
     * @texttype
     */
    private java.lang.String apNName;


    /**
     * @texttype
     */
    private java.lang.String appServerIPAdd;


    /**
     * @dicttype
     * @texttype
     */
    private java.lang.String isRadiusValidate;

    /**
     * @texttype
     */
    private java.lang.String radiusValidateIPAdd;

    /**
     * @texttype
     */
    private java.lang.String terminalNum;

    /**
     * @texttype
     */
    private java.lang.String numDetail;

    /**
     * @texttype
     */
    private java.lang.String changeType;

    /**
     * @accesstype
     * @texttype
     */
    private java.lang.String changeNumDetail;

    /**
     * @texttype
     */
    private java.lang.String bcode;

    /**
     * @texttype
     */
    private java.lang.String siName;

    /**
     * @texttype
     */
    private java.lang.String siEnterpriseCode;

    /**
     * @texttype
     */
    private java.lang.String siServerCode;

    /**
     * @texttype
     */
    private java.lang.String siConnectMMSGatewayID;

    /**
     * @texttype
     */
    private java.lang.String siIPAdd;

    /**
     * @texttype
     */
    private java.lang.String siUplinkUrl;

    /**
     * @texttype
     */
    private java.lang.String isConnectMISC;

    /**
     * @texttype
     */
    private java.lang.String comProtocol;

    /**
     * @texttype
     */
    private java.lang.String connectGatewayBandwidth;

    /**
     * @texttype
     */
    private java.lang.String maxConnections;

    /**
     * @texttype
     */
    private java.lang.String maxUnderFlow;

    /**
     * @texttype
     */
    private java.lang.String maxUplinkFlow;

    /**
     * @texttype
     */
    private java.lang.String portRateIsDown;

    /**
     * @texttype
     */
    private java.lang.String flowControlPriority;

    /**
     * @texttype
     */
    private java.lang.String appProgramme;

    /**
     * @texttype
     */
    private java.lang.String nameListSetType;

    /**
     * @texttype
     */
    private java.lang.String smSSigned;

    /**
     * @texttype
     */
    private java.lang.String enterpriseCode;

    /**
     * @texttype
     */
    private java.lang.String serverCode;

    /**
     * @texttype
     */
    private java.lang.String connectPoint;

    /**
     * @texttype
     */
    private java.lang.String hostIPAdd;

    /**
     * @texttype
     */
    private java.lang.String connectGateway;

    /**
     * @texttype
     */
    private java.lang.String gatewayCode;

    /**
     * @texttype
     */
    private java.lang.String cityA;

    /**
     * @texttype
     */
    private java.lang.String cityZ;

    /**
     * @texttype
     */
    private java.lang.String bandwidth;

    /**
     * @texttype
     */
    private java.lang.String amount;

    /**
     * @texttype
     */
    private java.lang.String portA;

    /**
     * @texttype
     */
    private java.lang.String portAInterfaceType;

    /**
     * @texttype
     */
    private java.lang.String portADetailAdd;

    /**
     * @texttype
     */
    private java.lang.String portABDeviceName;

    /**
     * @texttype
     */
    private java.lang.String portABDevicePort;

    /**
     * @texttype
     */
    private java.lang.String portAContactPhone;
    private java.lang.String portZ;
    private java.lang.String portZContactPhone;
    private java.lang.String portZBDevicePort;
    private java.lang.String portZBDeviceName;
    private java.lang.String portZInterfaceType;


    private java.lang.String ipAddressAssign;
    private java.lang.String apnRouterMode;
    private java.lang.String apnIPPool;
    private java.lang.String transferMode;
    private java.lang.String doubleGGSN;
    private java.lang.String secondIPPool;
    private java.lang.String simHLR;
    private java.lang.String volumeAssessment;
    private java.lang.String isCustomerFlag;

    private String factureTime;
    private String authenticationModel;
    private String singleWordsBit;


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


    public java.lang.String getPortZBDeviceName() {
        return portZBDeviceName;
    }

    public void setPortZBDeviceName(java.lang.String portZBDeviceName) {
        this.portZBDeviceName = portZBDeviceName;
    }

    public java.lang.String getPortZBDevicePort() {
        return portZBDevicePort;
    }

    public void setPortZBDevicePort(java.lang.String portZBDevicePort) {
        this.portZBDevicePort = portZBDevicePort;
    }

    public java.lang.String getPortZContactPhone() {
        return portZContactPhone;
    }

    public void setPortZContactPhone(java.lang.String portZContactPhone) {
        this.portZContactPhone = portZContactPhone;
    }

    public java.lang.String getPortZInterfaceType() {
        return portZInterfaceType;
    }

    public void setPortZInterfaceType(java.lang.String portZInterfaceType) {
        this.portZInterfaceType = portZInterfaceType;
    }

    /**
     * @return
     * @hibernate.property value="10"
     * @eoms.show
     */
    public int getUrgentDegree() {
        return urgentDegree;
    }

    public void setUrgentDegree(int urgentDegree) {
        this.urgentDegree = urgentDegree;
    }

    /**
     * @return
     * @hibernate.property value="10"
     * @eoms.show
     */
    public java.lang.String getBusinesstype1() {
        return businesstype1;
    }

    public void setBusinesstype1(java.lang.String businesstype1) {
        this.businesstype1 = businesstype1;
    }

    /**
     * @return
     * @hibernate.property value="30"
     * @eoms.show
     */
    public java.lang.String getBdeptContact() {
        return bdeptContact;
    }

    public void setBdeptContact(java.lang.String bdeptContact) {
        this.bdeptContact = bdeptContact;
    }

    /**
     * @return
     * @hibernate.property value="30"
     * @eoms.show
     */
    public java.lang.String getBdeptContactPhone() {
        return bdeptContactPhone;
    }

    public void setBdeptContactPhone(java.lang.String bdeptContactPhone) {
        this.bdeptContactPhone = bdeptContactPhone;
    }


    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getCustomName() {
        return customName;
    }

    public void setCustomName(java.lang.String customName) {
        this.customName = customName;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getCustomContact() {
        return customContact;
    }

    public void setCustomContact(java.lang.String customContact) {
        this.customContact = customContact;
    }


    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getCustomContactAdd() {
        return customContactAdd;
    }

    public void setCustomContactAdd(java.lang.String customContactAdd) {
        this.customContactAdd = customContactAdd;
    }


    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getCustomTrade() {
        return customTrade;
    }

    public void setCustomTrade(java.lang.String customTrade) {
        this.customTrade = customTrade;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getCustomContactEmail() {
        return customContactEmail;
    }

    public void setCustomContactEmail(java.lang.String customContactEmail) {
        this.customContactEmail = customContactEmail;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getCustomLevel() {
        return customLevel;
    }

    public void setCustomLevel(java.lang.String customLevel) {
        this.customLevel = customLevel;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getBrequirementDesc() {
        return brequirementDesc;
    }

    public void setBrequirementDesc(java.lang.String brequirementDesc) {
        this.brequirementDesc = brequirementDesc;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getFactureArea() {
        return factureArea;
    }

    public void setFactureArea(java.lang.String factureArea) {
        this.factureArea = factureArea;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getApNName() {
        return apNName;
    }

    public void setApNName(java.lang.String apNName) {
        this.apNName = apNName;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getAppServerIPAdd() {
        return appServerIPAdd;
    }

    public void setAppServerIPAdd(java.lang.String appServerIPAdd) {
        this.appServerIPAdd = appServerIPAdd;
    }


    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getIsRadiusValidate() {
        return isRadiusValidate;
    }

    public void setIsRadiusValidate(java.lang.String isRadiusValidate) {
        this.isRadiusValidate = isRadiusValidate;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getRadiusValidateIPAdd() {
        return radiusValidateIPAdd;
    }

    public void setRadiusValidateIPAdd(java.lang.String radiusValidateIPAdd) {
        this.radiusValidateIPAdd = radiusValidateIPAdd;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(java.lang.String terminalNum) {
        this.terminalNum = terminalNum;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getNumDetail() {
        return numDetail;
    }

    public void setNumDetail(java.lang.String numDetail) {
        this.numDetail = numDetail;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getChangeType() {
        return changeType;
    }

    public void setChangeType(java.lang.String changeType) {
        this.changeType = changeType;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getChangeNumDetail() {
        return changeNumDetail;
    }

    public void setChangeNumDetail(java.lang.String changeNumDetail) {
        this.changeNumDetail = changeNumDetail;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getBcode() {
        return bcode;
    }

    public void setBcode(java.lang.String bcode) {
        this.bcode = bcode;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getSiName() {
        return siName;
    }

    public void setSiName(java.lang.String siName) {
        this.siName = siName;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getSiEnterpriseCode() {
        return siEnterpriseCode;
    }

    public void setSiEnterpriseCode(java.lang.String siEnterpriseCode) {
        this.siEnterpriseCode = siEnterpriseCode;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getSiServerCode() {
        return siServerCode;
    }

    public void setSiServerCode(java.lang.String siServerCode) {
        this.siServerCode = siServerCode;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getSiConnectMMSGatewayID() {
        return siConnectMMSGatewayID;
    }

    public void setSiConnectMMSGatewayID(java.lang.String siConnectMMSGatewayID) {
        this.siConnectMMSGatewayID = siConnectMMSGatewayID;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getSiIPAdd() {
        return siIPAdd;
    }

    public void setSiIPAdd(java.lang.String siIPAdd) {
        this.siIPAdd = siIPAdd;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getSiUplinkUrl() {
        return siUplinkUrl;
    }

    public void setSiUplinkUrl(java.lang.String siUplinkUrl) {
        this.siUplinkUrl = siUplinkUrl;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getIsConnectMISC() {
        return isConnectMISC;
    }

    public void setIsConnectMISC(java.lang.String isConnectMISC) {
        this.isConnectMISC = isConnectMISC;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getComProtocol() {
        return comProtocol;
    }

    public void setComProtocol(java.lang.String comProtocol) {
        this.comProtocol = comProtocol;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getConnectGatewayBandwidth() {
        return connectGatewayBandwidth;
    }

    public void setConnectGatewayBandwidth(java.lang.String connectGatewayBandwidth) {
        this.connectGatewayBandwidth = connectGatewayBandwidth;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(java.lang.String maxConnections) {
        this.maxConnections = maxConnections;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getMaxUnderFlow() {
        return maxUnderFlow;
    }

    public void setMaxUnderFlow(java.lang.String maxUnderFlow) {
        this.maxUnderFlow = maxUnderFlow;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getMaxUplinkFlow() {
        return maxUplinkFlow;
    }

    public void setMaxUplinkFlow(java.lang.String maxUplinkFlow) {
        this.maxUplinkFlow = maxUplinkFlow;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getPortRateIsDown() {
        return portRateIsDown;
    }

    public void setPortRateIsDown(java.lang.String portRateIsDown) {
        this.portRateIsDown = portRateIsDown;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getFlowControlPriority() {
        return flowControlPriority;
    }

    public void setFlowControlPriority(java.lang.String flowControlPriority) {
        this.flowControlPriority = flowControlPriority;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getAppProgramme() {
        return appProgramme;
    }

    public void setAppProgramme(java.lang.String appProgramme) {
        this.appProgramme = appProgramme;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getNameListSetType() {
        return nameListSetType;
    }

    public void setNameListSetType(java.lang.String nameListSetType) {
        this.nameListSetType = nameListSetType;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getSmSSigned() {
        return smSSigned;
    }

    public void setSmSSigned(java.lang.String smSSigned) {
        this.smSSigned = smSSigned;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(java.lang.String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getServerCode() {
        return serverCode;
    }

    public void setServerCode(java.lang.String serverCode) {
        this.serverCode = serverCode;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getConnectPoint() {
        return connectPoint;
    }

    public void setConnectPoint(java.lang.String connectPoint) {
        this.connectPoint = connectPoint;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getHostIPAdd() {
        return hostIPAdd;
    }

    public void setHostIPAdd(java.lang.String hostIPAdd) {
        this.hostIPAdd = hostIPAdd;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getConnectGateway() {
        return connectGateway;
    }

    public void setConnectGateway(java.lang.String connectGateway) {
        this.connectGateway = connectGateway;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getGatewayCode() {
        return gatewayCode;
    }

    public void setGatewayCode(java.lang.String gatewayCode) {
        this.gatewayCode = gatewayCode;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getCityA() {
        return cityA;
    }

    public void setCityA(java.lang.String cityA) {
        this.cityA = cityA;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getCityZ() {
        return cityZ;
    }

    public void setCityZ(java.lang.String cityZ) {
        this.cityZ = cityZ;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(java.lang.String bandwidth) {
        this.bandwidth = bandwidth;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getPortA() {
        return portA;
    }

    public void setPortA(java.lang.String portA) {
        this.portA = portA;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getPortAInterfaceType() {
        return portAInterfaceType;
    }

    public void setPortAInterfaceType(java.lang.String portAInterfaceType) {
        this.portAInterfaceType = portAInterfaceType;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getPortADetailAdd() {
        return portADetailAdd;
    }

    public void setPortADetailAdd(java.lang.String portADetailAdd) {
        this.portADetailAdd = portADetailAdd;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getPortABDeviceName() {
        return portABDeviceName;
    }

    public void setPortABDeviceName(java.lang.String portABDeviceName) {
        this.portABDeviceName = portABDeviceName;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getPortABDevicePort() {
        return portABDevicePort;
    }

    public void setPortABDevicePort(java.lang.String portABDevicePort) {
        this.portABDevicePort = portABDevicePort;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getPortAContactPhone() {
        return portAContactPhone;
    }

    public void setPortAContactPhone(java.lang.String portAContactPhone) {
        this.portAContactPhone = portAContactPhone;
    }

    public java.lang.String getPortZ() {
        return portZ;
    }

    public void setPortZ(java.lang.String portZ) {
        this.portZ = portZ;
    }

    public java.lang.String getAmount() {
        return amount;
    }

    public void setAmount(java.lang.String amount) {
        this.amount = amount;
    }

    public java.lang.String getApnIPPool() {
        return apnIPPool;
    }

    public void setApnIPPool(java.lang.String apnIPPool) {
        this.apnIPPool = apnIPPool;
    }

    public java.lang.String getApnRouterMode() {
        return apnRouterMode;
    }

    public void setApnRouterMode(java.lang.String apnRouterMode) {
        this.apnRouterMode = apnRouterMode;
    }

    public java.lang.String getDoubleGGSN() {
        return doubleGGSN;
    }

    public void setDoubleGGSN(java.lang.String doubleGGSN) {
        this.doubleGGSN = doubleGGSN;
    }

    public java.lang.String getIpAddressAssign() {
        return ipAddressAssign;
    }

    public void setIpAddressAssign(java.lang.String ipAddressAssign) {
        this.ipAddressAssign = ipAddressAssign;
    }

    public java.lang.String getSecondIPPool() {
        return secondIPPool;
    }

    public void setSecondIPPool(java.lang.String secondIPPool) {
        this.secondIPPool = secondIPPool;
    }

    public java.lang.String getSimHLR() {
        return simHLR;
    }

    public void setSimHLR(java.lang.String simHLR) {
        this.simHLR = simHLR;
    }

    public java.lang.String getTransferMode() {
        return transferMode;
    }

    public void setTransferMode(java.lang.String transferMode) {
        this.transferMode = transferMode;
    }

    public java.lang.String getVolumeAssessment() {
        return volumeAssessment;
    }

    public void setVolumeAssessment(java.lang.String volumeAssessment) {
        this.volumeAssessment = volumeAssessment;
    }

    public java.lang.String getIsCustomerFlag() {
        return isCustomerFlag;
    }

    public void setIsCustomerFlag(java.lang.String isCustomerFlag) {
        this.isCustomerFlag = isCustomerFlag;
    }

    public String getAuthenticationModel() {
        return authenticationModel;
    }

    public void setAuthenticationModel(String authenticationModel) {
        this.authenticationModel = authenticationModel;
    }

    public String getFactureTime() {
        return factureTime;
    }

    public void setFactureTime(String factureTime) {
        this.factureTime = factureTime;
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

    public String getConfCRMTicketNo() {
        return confCRMTicketNo;
    }

    public void setConfCRMTicketNo(String confCRMTicketNo) {
        this.confCRMTicketNo = confCRMTicketNo;
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

    public String getSiconnectMMSGatewayName() {
        return siconnectMMSGatewayName;
    }

    public void setSiconnectMMSGatewayName(String siconnectMMSGatewayName) {
        this.siconnectMMSGatewayName = siconnectMMSGatewayName;
    }

    public String getTransfBusiness() {
        return transfBusiness;
    }

    public void setTransfBusiness(String transfBusiness) {
        this.transfBusiness = transfBusiness;
    }

    public java.lang.String getCustomContactPhone() {
        return customContactPhone;
    }

    public void setCustomContactPhone(java.lang.String customContactPhone) {
        this.customContactPhone = customContactPhone;
    }

    public java.lang.String getCustomContactPost() {
        return customContactPost;
    }

    public void setCustomContactPost(java.lang.String customContactPost) {
        this.customContactPost = customContactPost;
    }

    public java.lang.String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(java.lang.String customNo) {
        this.customNo = customNo;
    }

}
