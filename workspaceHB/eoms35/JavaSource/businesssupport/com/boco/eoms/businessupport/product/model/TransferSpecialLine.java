package com.boco.eoms.businessupport.product.model;

import java.util.Date;

public class TransferSpecialLine {
    private String id;
    private String orderSheet_Id;             //定单id
    private String tradeId;                      //crm产品实例唯一标识
    //以下字段为Transfer专线私有字段
    private String cityA;                      //城市A
    private String cityZ;                      //城市Z
    private String bandwidth;                  //带宽
    private String amount;                      //数量
    private String portA;                      //端口A
    private String portAInterfaceType;          //端点A接口类型
    private String portADetailAdd;              //端点A详细地址
    private String portABDeviceName;          //端点A业务设备名称
    private String portABDevicePort;          //端点A业务设备端口
    private String portAContactPhone;          //端点A联系人及电话
    private String portZ;                      //端口Z
    private String portZInterfaceType;        //端点Z接口类型
    private String portZDetailAdd;            //端点Z详细地址
    private String portZBDeviceName;          //端点Z业务设备名称
    private String portZBDevicePort;          //端点Z业务设备端口
    private String portZContactPhone;          //端点Z联系人及电话
    private String isBeforehandOccupy;          //是否预占
    private String specialtyType;             //专业类型（预留）
    private Integer deleted;                  //删除标识：0，未删除；1，已删除
    private String circuitCode;               //传输专线电路代号
    private String circuitName;               //电路名称
    private String circuitRate;               //电路速率
    private String circuitSheetId;        //电路编号
    //added by liufuyuan shanxi
    private String interfaceType;        //接口类型及型号
    private String stationInfo;    //站点信息(动态增加，可批量导入导出，标绿色字段在需求申请后修改)
    private String opeation;        //操作
    private String apointComputHouseName;        //A端机房名称
    private String interfaceCustomConnPerson;        //业务接入点客户联系人
    private String interfaceCustomConnPhone;        //业务接入点客户联系电话
    private String interfaceCustomConnMail;        //业务接入点客户联系邮箱
    private String interfaceCustomConnAdd;        //业务接入点客户联系地址
    private String zpointComputerHorseName;        //Z端机房名称
    private String zpointLocalPerson;        //z端客户当地配合人

    private String apointLocalPerson;        //A端客户当地配合人

    private String userIsUserNet;    //用户是否用户网站*
    private String userSpecifyDevNeed;    //用户个性化设备需求*


//	业务接入点客户联系人  trasition az

    private String interfaceCustomConnPersonZ;
//	业务接入点客户联系电话	

    private String interfaceCustomConnPhoneZ;
//	业务接入点客户联系邮箱	

    private String interfaceCustomConnMailZ;
//	业务接入点客户联系地址	

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

    //	  A接入点站点名称
    private String interfaceSiteNameA;
    //	  A接入点设备编码
    private String interfaceEquipCodeA;

    // Z接入点站点名称
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
    //	A敷设方式：
    private String buildTypeA;
    //	Z敷设方式：
    private String buildTypeZ;
    //	A设备类型：
    private String portABDeviceType;
    //	Z设备类型：
    private String portZBDeviceType;


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

    public String getCircuitRate() {
        return circuitRate;
    }

    public void setCircuitRate(String circuitRate) {
        this.circuitRate = circuitRate;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getCircuitCode() {
        return circuitCode;
    }

    public void setCircuitCode(String circuitCode) {
        this.circuitCode = circuitCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsBeforehandOccupy() {
        return isBeforehandOccupy;
    }

    public void setIsBeforehandOccupy(String isBeforehandOccupy) {
        this.isBeforehandOccupy = isBeforehandOccupy;
    }

    public String getOrderSheet_Id() {
        return orderSheet_Id;
    }

    public void setOrderSheet_Id(String orderSheet_Id) {
        this.orderSheet_Id = orderSheet_Id;
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

    public String getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(String specialtyType) {
        this.specialtyType = specialtyType;
    }


    public String getApointLocalPerson() {
        return apointLocalPerson;
    }

    public void setApointLocalPerson(String apointLocalPerson) {
        this.apointLocalPerson = apointLocalPerson;
    }

    public String getCircuitSheetId() {
        return circuitSheetId;
    }

    public void setCircuitSheetId(String circuitSheetId) {
        this.circuitSheetId = circuitSheetId;
    }

    public String getInterfaceCustomConnAdd() {
        return interfaceCustomConnAdd;
    }

    public void setInterfaceCustomConnAdd(String interfaceCustomConnAdd) {
        this.interfaceCustomConnAdd = interfaceCustomConnAdd;
    }

    public String getInterfaceCustomConnMail() {
        return interfaceCustomConnMail;
    }

    public void setInterfaceCustomConnMail(String interfaceCustomConnMail) {
        this.interfaceCustomConnMail = interfaceCustomConnMail;
    }

    public String getInterfaceCustomConnPerson() {
        return interfaceCustomConnPerson;
    }

    public void setInterfaceCustomConnPerson(String interfaceCustomConnPerson) {
        this.interfaceCustomConnPerson = interfaceCustomConnPerson;
    }

    public String getInterfaceCustomConnPhone() {
        return interfaceCustomConnPhone;
    }

    public void setInterfaceCustomConnPhone(String interfaceCustomConnPhone) {
        this.interfaceCustomConnPhone = interfaceCustomConnPhone;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getOpeation() {
        return opeation;
    }

    public void setOpeation(String opeation) {
        this.opeation = opeation;
    }

    public String getStationInfo() {
        return stationInfo;
    }

    public void setStationInfo(String stationInfo) {
        this.stationInfo = stationInfo;
    }

    public String getApointComputHouseName() {
        return apointComputHouseName;
    }

    public void setApointComputHouseName(String apointComputHouseName) {
        this.apointComputHouseName = apointComputHouseName;
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

    public String getInterfaceCustomConnAddZ() {
        return interfaceCustomConnAddZ;
    }

    public void setInterfaceCustomConnAddZ(String interfaceCustomConnAddZ) {
        this.interfaceCustomConnAddZ = interfaceCustomConnAddZ;
    }

    public String getInterfaceCustomConnMailZ() {
        return interfaceCustomConnMailZ;
    }

    public void setInterfaceCustomConnMailZ(String interfaceCustomConnMailZ) {
        this.interfaceCustomConnMailZ = interfaceCustomConnMailZ;
    }

    public String getInterfaceCustomConnPersonZ() {
        return interfaceCustomConnPersonZ;
    }

    public void setInterfaceCustomConnPersonZ(String interfaceCustomConnPersonZ) {
        this.interfaceCustomConnPersonZ = interfaceCustomConnPersonZ;
    }

    public String getInterfaceCustomConnPhoneZ() {
        return interfaceCustomConnPhoneZ;
    }

    public void setInterfaceCustomConnPhoneZ(String interfaceCustomConnPhoneZ) {
        this.interfaceCustomConnPhoneZ = interfaceCustomConnPhoneZ;
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

    public Date getPromise() {
        return promise;
    }

    public void setPromise(Date promise) {
        this.promise = promise;
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
