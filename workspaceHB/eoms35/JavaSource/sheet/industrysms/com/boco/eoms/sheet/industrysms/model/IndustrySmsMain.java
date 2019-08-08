package com.boco.eoms.sheet.industrysms.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:行业短信开通删除工单
 * </p>
 * <p>
 * Description:行业短信开通删除工单
 * </p>
 * <p>
 * Mon Mar 04 17:27:01 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class IndustrySmsMain extends BaseMain {

    /**
     * ADC/MAS地域
     */
    private java.lang.String regional;


    /**
     * EC/SI类型
     */
    private java.lang.String ecsiType;


    /**
     * EC/SI简称
     */
    private java.lang.String abbreviation;


    /**
     * 企业代码
     */
    private java.lang.String enterpriseCode;


    /**
     * EC/SI服务代码
     */
    private java.lang.String serviceCode;


    /**
     * 客户服务器IP
     */
    private java.lang.String cilentServerIpAddr;


    /**
     * 是否为多IP
     */
    private java.lang.String cilentServerIpAddrs;


    /**
     * 登录网关用户名
     */
    private java.lang.String userValue;


    /**
     * 登录网关密码
     */
    private java.lang.String passwordValue;


    /**
     * 最大连接数
     */
    private java.lang.String maxConnections;


    /**
     * 最大下发流量
     */
    private java.lang.String maxInBytes;


    /**
     * 最大上行流量
     */
    private java.lang.String maxOutBytes;


    /**
     * EC/SI使用协议
     */
    private java.lang.String protocol;


    /**
     * 是否M模块鉴权
     */
    private java.lang.String isAuthentication;


    /**
     * 业务联系人
     */
    private java.lang.String seviceContact;


    /**
     * 业务联系电话
     */
    private java.lang.String sevicePhone;


    /**
     * 客户联系人
     */
    private java.lang.String customerContact;


    /**
     * 客户联系电话
     */
    private java.lang.String customerPhone;


    /**
     * 业务数据申请单位
     */
    private java.lang.String unit;


    /**
     * 业务数据据申请日期
     */
    private java.util.Date applyDate;


    /**
     * 硬件型号
     */
    private java.lang.String hardwareModel;


    /**
     * 软件版本
     */
    private java.lang.String softVersion;


    /**
     * 集成商
     */
    private java.lang.String integrator;


    /**
     * 是否为加急流程
     */
    private java.lang.String isUrgent;


    /**
     * 集团批复工单号
     */
    private java.lang.String groupNumber;


    /**
     * MASID
     */
    private java.lang.String masId;


    /**
     * 工单回复时限
     */
    private java.util.Date timeLimit;


    /**
     * EC/SI简称(新)
     */
    private java.lang.String abbreviationNew;


    /**
     * 客户服务器IP(新)
     */
    private java.lang.String cilentServerIpAddrNew;


    /**
     * 是否为多IP(新)
     */
    private java.lang.String cilentServerIpAddrsNew;


    /**
     * 登录网关密码(新)
     */
    private java.lang.String passwordNew;


    /**
     * 最大连接数(新)
     */
    private java.lang.String maxConnectionsNew;


    /**
     * 最大下发流量(新)
     */
    private java.lang.String maxInBytesNew;


    /**
     * 最大上行流量(新)
     */
    private java.lang.String maxOutBytesNew;


    /**
     * EC/SI使用协议(新)
     */
    private java.lang.String protocolNew;


    /**
     * spareOne
     */
    private java.lang.String spareOne;


    /**
     * spareTwo
     */
    private java.lang.String spareTwo;


    /**
     * spareThree
     */
    private java.lang.String spareThree;


    /**
     * 保存派发对象
     */
    private java.lang.String sendObject;

    public void setRegional(java.lang.String regional) {
        this.regional = regional;
    }

    public java.lang.String getRegional() {
        return this.regional;
    }


    public void setEcsiType(java.lang.String ecsiType) {
        this.ecsiType = ecsiType;
    }

    public java.lang.String getEcsiType() {
        return this.ecsiType;
    }


    public void setAbbreviation(java.lang.String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public java.lang.String getAbbreviation() {
        return this.abbreviation;
    }


    public void setEnterpriseCode(java.lang.String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public java.lang.String getEnterpriseCode() {
        return this.enterpriseCode;
    }


    public void setServiceCode(java.lang.String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public java.lang.String getServiceCode() {
        return this.serviceCode;
    }


    public void setCilentServerIpAddr(java.lang.String cilentServerIpAddr) {
        this.cilentServerIpAddr = cilentServerIpAddr;
    }

    public java.lang.String getCilentServerIpAddr() {
        return this.cilentServerIpAddr;
    }


    public void setCilentServerIpAddrs(java.lang.String cilentServerIpAddrs) {
        this.cilentServerIpAddrs = cilentServerIpAddrs;
    }

    public java.lang.String getCilentServerIpAddrs() {
        return this.cilentServerIpAddrs;
    }


    public void setMaxConnections(java.lang.String maxConnections) {
        this.maxConnections = maxConnections;
    }

    public java.lang.String getMaxConnections() {
        return this.maxConnections;
    }


    public void setMaxInBytes(java.lang.String maxInBytes) {
        this.maxInBytes = maxInBytes;
    }

    public java.lang.String getMaxInBytes() {
        return this.maxInBytes;
    }


    public void setMaxOutBytes(java.lang.String maxOutBytes) {
        this.maxOutBytes = maxOutBytes;
    }

    public java.lang.String getMaxOutBytes() {
        return this.maxOutBytes;
    }


    public void setProtocol(java.lang.String protocol) {
        this.protocol = protocol;
    }

    public java.lang.String getProtocol() {
        return this.protocol;
    }


    public void setIsAuthentication(java.lang.String isAuthentication) {
        this.isAuthentication = isAuthentication;
    }

    public java.lang.String getIsAuthentication() {
        return this.isAuthentication;
    }


    public void setSeviceContact(java.lang.String seviceContact) {
        this.seviceContact = seviceContact;
    }

    public java.lang.String getSeviceContact() {
        return this.seviceContact;
    }


    public void setSevicePhone(java.lang.String sevicePhone) {
        this.sevicePhone = sevicePhone;
    }

    public java.lang.String getSevicePhone() {
        return this.sevicePhone;
    }


    public void setCustomerContact(java.lang.String customerContact) {
        this.customerContact = customerContact;
    }

    public java.lang.String getCustomerContact() {
        return this.customerContact;
    }


    public void setCustomerPhone(java.lang.String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public java.lang.String getCustomerPhone() {
        return this.customerPhone;
    }


    public void setUnit(java.lang.String unit) {
        this.unit = unit;
    }

    public java.lang.String getUnit() {
        return this.unit;
    }


    public void setApplyDate(java.util.Date applyDate) {
        this.applyDate = applyDate;
    }

    public java.util.Date getApplyDate() {
        return this.applyDate;
    }


    public void setHardwareModel(java.lang.String hardwareModel) {
        this.hardwareModel = hardwareModel;
    }

    public java.lang.String getHardwareModel() {
        return this.hardwareModel;
    }


    public void setSoftVersion(java.lang.String softVersion) {
        this.softVersion = softVersion;
    }

    public java.lang.String getSoftVersion() {
        return this.softVersion;
    }


    public void setIntegrator(java.lang.String integrator) {
        this.integrator = integrator;
    }

    public java.lang.String getIntegrator() {
        return this.integrator;
    }


    public void setIsUrgent(java.lang.String isUrgent) {
        this.isUrgent = isUrgent;
    }

    public java.lang.String getIsUrgent() {
        return this.isUrgent;
    }


    public void setGroupNumber(java.lang.String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public java.lang.String getGroupNumber() {
        return this.groupNumber;
    }


    public void setMasId(java.lang.String masId) {
        this.masId = masId;
    }

    public java.lang.String getMasId() {
        return this.masId;
    }


    public void setTimeLimit(java.util.Date timeLimit) {
        this.timeLimit = timeLimit;
    }

    public java.util.Date getTimeLimit() {
        return this.timeLimit;
    }


    public void setAbbreviationNew(java.lang.String abbreviationNew) {
        this.abbreviationNew = abbreviationNew;
    }

    public java.lang.String getAbbreviationNew() {
        return this.abbreviationNew;
    }


    public void setCilentServerIpAddrNew(java.lang.String cilentServerIpAddrNew) {
        this.cilentServerIpAddrNew = cilentServerIpAddrNew;
    }

    public java.lang.String getCilentServerIpAddrNew() {
        return this.cilentServerIpAddrNew;
    }


    public void setCilentServerIpAddrsNew(java.lang.String cilentServerIpAddrsNew) {
        this.cilentServerIpAddrsNew = cilentServerIpAddrsNew;
    }

    public java.lang.String getCilentServerIpAddrsNew() {
        return this.cilentServerIpAddrsNew;
    }


    public void setPasswordNew(java.lang.String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public java.lang.String getPasswordNew() {
        return this.passwordNew;
    }


    public void setMaxConnectionsNew(java.lang.String maxConnectionsNew) {
        this.maxConnectionsNew = maxConnectionsNew;
    }

    public java.lang.String getMaxConnectionsNew() {
        return this.maxConnectionsNew;
    }


    public void setMaxInBytesNew(java.lang.String maxInBytesNew) {
        this.maxInBytesNew = maxInBytesNew;
    }

    public java.lang.String getMaxInBytesNew() {
        return this.maxInBytesNew;
    }


    public void setMaxOutBytesNew(java.lang.String maxOutBytesNew) {
        this.maxOutBytesNew = maxOutBytesNew;
    }

    public java.lang.String getMaxOutBytesNew() {
        return this.maxOutBytesNew;
    }


    public void setProtocolNew(java.lang.String protocolNew) {
        this.protocolNew = protocolNew;
    }

    public java.lang.String getProtocolNew() {
        return this.protocolNew;
    }


    public void setSpareOne(java.lang.String spareOne) {
        this.spareOne = spareOne;
    }

    public java.lang.String getSpareOne() {
        return this.spareOne;
    }


    public void setSpareTwo(java.lang.String spareTwo) {
        this.spareTwo = spareTwo;
    }

    public java.lang.String getSpareTwo() {
        return this.spareTwo;
    }


    public void setSpareThree(java.lang.String spareThree) {
        this.spareThree = spareThree;
    }

    public java.lang.String getSpareThree() {
        return this.spareThree;
    }


    public java.lang.String getSendObject() {
        return sendObject;
    }

    public void setSendObject(java.lang.String sendObject) {
        this.sendObject = sendObject;
    }

    public java.lang.String getUserValue() {
        return userValue;
    }

    public void setUserValue(java.lang.String userValue) {
        this.userValue = userValue;
    }

    public java.lang.String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(java.lang.String passwordValue) {
        this.passwordValue = passwordValue;
    }
}