package com.boco.eoms.businessupport.product.webapp.form;

import java.util.Date;

import com.boco.eoms.base.webapp.form.BaseForm;

public class GprsSpecialLineForm extends BaseForm implements java.io.Serializable {

    protected String id;

    protected String orderSheet_Id;
    protected String longitudeA;
    protected String latitudeA;
    protected String detailAddGPRS;
    protected String contactUserGPRS;
    protected String contactPhoneGPRS;
    protected String longitudeGPRS;
    protected String latitudeGPRS;
    protected String businessBandwidth;
    protected String businessAmount;
    protected String connectType;
    protected String tunnelType;
    protected String groupCustomIP;
    protected String radiusAddress;
    protected String groupCustomIPDetail;
    protected String apnName;
    protected String ipAddressAssign;
    protected String connectPortNameA;
    protected String connectPortLengthA;
    protected String allowConnectA;
    protected String planFinishDaysA;
    protected String portANetDeptUserA;
    protected String portANetDeptPhoneA;
    protected String portADealDesc;
    protected String remark;
    protected String specialtyType;
    protected Integer deleted;

//added by liufuyuan shanxi


    private String apnScopeName;            //APN域名
    private String apnType;            //APN类型


    private String endIDDividType;            //终端IP地址分配方式
    private String isDoubleGGSN;            //是否双GGSN
    private String apnRootMethod;            //APN路由方式


    private String transferInputMethod;            //传输接入方式
    private String manYouProperty;            //漫游属性
    private String planExeEndPointNum;         //计划开通终端数
    private String firstApnAddAndNum;            //第一个APN的地址池和掩码
    private String secondApnAddAndNum;            //第二个APN的地址池和掩码
    private String greIpAdd;            //GRE的企业端IP地址 n
    private String simAdnHlrScope;            //SIM卡HLR制作范围
    private String applyPurpose;            //申请用途
    private String gprsBusinessTechCase;            //GPRS业务接入技术方案

    private String userInputRouterIP;            //用户接入路由器IP
    private String userAppServerIP;            //用户应用服务器IP

    private String userIsRadiusValid;            //用户端是否进行RADIUS验证
    private String userRadiusValidateIPAdd;            //如用户端进行RADIUS验证，请提供用户端RADIUS服务器IP地址


    private String endPortNum;            //终端数量
    private String numberDecrpt;            //号码明细
    private String businessNumAssessment;            //业务量评估
    private String apnIndex;            //	APNID(APN索引号)


    private String userIsUserNet;    //用户是否用户网站*
    private String userSpecifyDevNeed;    //用户个性化设备需求*

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
    private String siteEquipCodeZ;
    //	站点名称203
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

    private String circuitName;
    private String circuitSheetId;


    //	客户端标准地址
    private String userStardAddA;
    private String userStardAddZ;
    //客户位置纬度
    private String userSiteHA;
    private String userSiteHZ;
    //客户位置经度	20 2
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

    //	  A接入点站点名称
    private String interfaceSiteNameA;
    //	  A接入点设备编码
    private String interfaceEquipCodeA;

    // Z接入点站点名称 20
    private String interfaceSiteNameZ;
    //z接入点设备编码
    private String interfaceEquipCodeZ;

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

    private String portZBDevicePort;          //端点Z业务设备端口
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

    private String tradeId;                      //crm产品实例唯一标识


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

    public String getPortZBDeviceType() {
        return portZBDeviceType;
    }

    public void setPortZBDeviceType(String portZBDeviceType) {
        this.portZBDeviceType = portZBDeviceType;
    }

    public String getRequirmentArea() {
        return requirmentArea;
    }

    public void setRequirmentArea(String requirmentArea) {
        this.requirmentArea = requirmentArea;
    }

    public String getPortZBDevicePort() {
        return portZBDevicePort;
    }

    public void setPortZBDevicePort(String portZBDevicePort) {
        this.portZBDevicePort = portZBDevicePort;
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

    public String getApnIndex() {
        return apnIndex;
    }

    public void setApnIndex(String apnIndex) {
        this.apnIndex = apnIndex;
    }

    public String getApnRootMethod() {
        return apnRootMethod;
    }

    public void setApnRootMethod(String apnRootMethod) {
        this.apnRootMethod = apnRootMethod;
    }

    public String getApnScopeName() {
        return apnScopeName;
    }

    public void setApnScopeName(String apnScopeName) {
        this.apnScopeName = apnScopeName;
    }

    public String getApnType() {
        return apnType;
    }

    public void setApnType(String apnType) {
        this.apnType = apnType;
    }

    public String getApplyPurpose() {
        return applyPurpose;
    }

    public void setApplyPurpose(String applyPurpose) {
        this.applyPurpose = applyPurpose;
    }

    public String getBusinessNumAssessment() {
        return businessNumAssessment;
    }

    public void setBusinessNumAssessment(String businessNumAssessment) {
        this.businessNumAssessment = businessNumAssessment;
    }

    public String getEndIDDividType() {
        return endIDDividType;
    }

    public void setEndIDDividType(String endIDDividType) {
        this.endIDDividType = endIDDividType;
    }

    public String getEndPortNum() {
        return endPortNum;
    }

    public void setEndPortNum(String endPortNum) {
        this.endPortNum = endPortNum;
    }

    public String getFirstApnAddAndNum() {
        return firstApnAddAndNum;
    }

    public void setFirstApnAddAndNum(String firstApnAddAndNum) {
        this.firstApnAddAndNum = firstApnAddAndNum;
    }

    public String getGprsBusinessTechCase() {
        return gprsBusinessTechCase;
    }

    public void setGprsBusinessTechCase(String gprsBusinessTechCase) {
        this.gprsBusinessTechCase = gprsBusinessTechCase;
    }

    public String getGreIpAdd() {
        return greIpAdd;
    }

    public void setGreIpAdd(String greIpAdd) {
        this.greIpAdd = greIpAdd;
    }

    public String getIsDoubleGGSN() {
        return isDoubleGGSN;
    }

    public void setIsDoubleGGSN(String isDoubleGGSN) {
        this.isDoubleGGSN = isDoubleGGSN;
    }

    public String getManYouProperty() {
        return manYouProperty;
    }

    public void setManYouProperty(String manYouProperty) {
        this.manYouProperty = manYouProperty;
    }

    public String getNumberDecrpt() {
        return numberDecrpt;
    }

    public void setNumberDecrpt(String numberDecrpt) {
        this.numberDecrpt = numberDecrpt;
    }

    public String getPlanExeEndPointNum() {
        return planExeEndPointNum;
    }

    public void setPlanExeEndPointNum(String planExeEndPointNum) {
        this.planExeEndPointNum = planExeEndPointNum;
    }

    public String getSecondApnAddAndNum() {
        return secondApnAddAndNum;
    }

    public void setSecondApnAddAndNum(String secondApnAddAndNum) {
        this.secondApnAddAndNum = secondApnAddAndNum;
    }

    public String getSimAdnHlrScope() {
        return simAdnHlrScope;
    }

    public void setSimAdnHlrScope(String simAdnHlrScope) {
        this.simAdnHlrScope = simAdnHlrScope;
    }

    public String getTransferInputMethod() {
        return transferInputMethod;
    }

    public void setTransferInputMethod(String transferInputMethod) {
        this.transferInputMethod = transferInputMethod;
    }

    public String getUserAppServerIP() {
        return userAppServerIP;
    }

    public void setUserAppServerIP(String userAppServerIP) {
        this.userAppServerIP = userAppServerIP;
    }

    public String getUserInputRouterIP() {
        return userInputRouterIP;
    }

    public void setUserInputRouterIP(String userInputRouterIP) {
        this.userInputRouterIP = userInputRouterIP;
    }

    public String getUserIsRadiusValid() {
        return userIsRadiusValid;
    }

    public void setUserIsRadiusValid(String userIsRadiusValid) {
        this.userIsRadiusValid = userIsRadiusValid;
    }

    public String getUserIsUserNet() {
        return userIsUserNet;
    }

    public void setUserIsUserNet(String userIsUserNet) {
        this.userIsUserNet = userIsUserNet;
    }

    public String getUserRadiusValidateIPAdd() {
        return userRadiusValidateIPAdd;
    }

    public void setUserRadiusValidateIPAdd(String userRadiusValidateIPAdd) {
        this.userRadiusValidateIPAdd = userRadiusValidateIPAdd;
    }

    public String getUserSpecifyDevNeed() {
        return userSpecifyDevNeed;
    }

    public void setUserSpecifyDevNeed(String userSpecifyDevNeed) {
        this.userSpecifyDevNeed = userSpecifyDevNeed;
    }

    public String getAllowConnectA() {
        return allowConnectA;
    }

    public void setAllowConnectA(String allowConnectA) {
        this.allowConnectA = allowConnectA;
    }

    public String getApnName() {
        return apnName;
    }

    public void setApnName(String apnName) {
        this.apnName = apnName;
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

    public String getConnectPortLengthA() {
        return connectPortLengthA;
    }

    public void setConnectPortLengthA(String connectPortLengthA) {
        this.connectPortLengthA = connectPortLengthA;
    }

    public String getConnectPortNameA() {
        return connectPortNameA;
    }

    public void setConnectPortNameA(String connectPortNameA) {
        this.connectPortNameA = connectPortNameA;
    }

    public String getConnectType() {
        return connectType;
    }

    public void setConnectType(String connectType) {
        this.connectType = connectType;
    }

    public String getContactPhoneGPRS() {
        return contactPhoneGPRS;
    }

    public void setContactPhoneGPRS(String contactPhoneGPRS) {
        this.contactPhoneGPRS = contactPhoneGPRS;
    }

    public String getContactUserGPRS() {
        return contactUserGPRS;
    }

    public void setContactUserGPRS(String contactUserGPRS) {
        this.contactUserGPRS = contactUserGPRS;
    }

    public String getDetailAddGPRS() {
        return detailAddGPRS;
    }

    public void setDetailAddGPRS(String detailAddGPRS) {
        this.detailAddGPRS = detailAddGPRS;
    }

    public String getGroupCustomIP() {
        return groupCustomIP;
    }

    public void setGroupCustomIP(String groupCustomIP) {
        this.groupCustomIP = groupCustomIP;
    }

    public String getGroupCustomIPDetail() {
        return groupCustomIPDetail;
    }

    public void setGroupCustomIPDetail(String groupCustomIPDetail) {
        this.groupCustomIPDetail = groupCustomIPDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddressAssign() {
        return ipAddressAssign;
    }

    public void setIpAddressAssign(String ipAddressAssign) {
        this.ipAddressAssign = ipAddressAssign;
    }

    public String getLatitudeA() {
        return latitudeA;
    }

    public void setLatitudeA(String latitudeA) {
        this.latitudeA = latitudeA;
    }

    public String getLatitudeGPRS() {
        return latitudeGPRS;
    }

    public void setLatitudeGPRS(String latitudeGPRS) {
        this.latitudeGPRS = latitudeGPRS;
    }

    public String getLongitudeA() {
        return longitudeA;
    }

    public void setLongitudeA(String longitudeA) {
        this.longitudeA = longitudeA;
    }

    public String getLongitudeGPRS() {
        return longitudeGPRS;
    }

    public void setLongitudeGPRS(String longitudeGPRS) {
        this.longitudeGPRS = longitudeGPRS;
    }

    public String getPlanFinishDaysA() {
        return planFinishDaysA;
    }

    public void setPlanFinishDaysA(String planFinishDaysA) {
        this.planFinishDaysA = planFinishDaysA;
    }

    public String getPortADealDesc() {
        return portADealDesc;
    }

    public void setPortADealDesc(String portADealDesc) {
        this.portADealDesc = portADealDesc;
    }

    public String getPortANetDeptPhoneA() {
        return portANetDeptPhoneA;
    }

    public void setPortANetDeptPhoneA(String portANetDeptPhoneA) {
        this.portANetDeptPhoneA = portANetDeptPhoneA;
    }

    public String getPortANetDeptUserA() {
        return portANetDeptUserA;
    }

    public void setPortANetDeptUserA(String portANetDeptUserA) {
        this.portANetDeptUserA = portANetDeptUserA;
    }

    public String getRadiusAddress() {
        return radiusAddress;
    }

    public void setRadiusAddress(String radiusAddress) {
        this.radiusAddress = radiusAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTunnelType() {
        return tunnelType;
    }

    public void setTunnelType(String tunnelType) {
        this.tunnelType = tunnelType;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getOrderSheet_Id() {
        return orderSheet_Id;
    }

    public void setOrderSheet_Id(String orderSheet_Id) {
        this.orderSheet_Id = orderSheet_Id;
    }

    public String getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(String specialtyType) {
        this.specialtyType = specialtyType;
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

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

}
