package com.boco.eoms.sheet.equipmentsecurityresponse.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Description:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Tue Apr 25 11:45:21 CST 2017
 * </p>
 *
 * @author liuyonggnag
 * @version 3.6
 */

public class EquipmentSecurityResponseMain extends BaseMain {

    /**
     * ISMP工单流水号
     */
    private java.lang.String mainISMPSheetId;


    /**
     * 用户账号
     */
    private java.lang.String mainUserId;


    /**
     * 最长接受时间
     */
    private java.util.Date mainAcceptTime;


    /**
     * 最长处理时间
     */
    private java.util.Date mainDealTime;


    /**
     * 省份
     */
    private java.lang.String mainProvince;


    /**
     * 地市
     */
    private java.lang.String mainCity;


    /**
     * 派单方式
     */
    private java.lang.String mainSendWay;


    /**
     * 告警标题
     */
    private java.lang.String mainALarmTitle;


    /**
     * 告警时间
     */
    private java.util.Date mainALarmTime;


    /**
     * 告警类型
     */
    private java.lang.String mainALarmType;


    /**
     * 告警级别名称
     */
    private java.lang.String mainALarmName;


    /**
     * 告警ID
     */
    private java.lang.String mainALarmID;


    /**
     * 原来源资产ID
     */
    private java.lang.String mainOrigSourceID;


    /**
     * 原来目的资产ID
     */
    private java.lang.String mainOrigPurposeID;


    /**
     * 现在源资产ID
     */
    private java.lang.String mainNowSourceID;


    /**
     * 现在目的资产ID
     */
    private java.lang.String mainNowPurposeID;


    /**
     * 告警内容
     */
    private java.lang.String mainALarmContent;


    /**
     * 预留字段1
     */
    private java.lang.String mainExtend1;


    /**
     * 预留字段2
     */
    private java.lang.String mainExtend2;


    /**
     * 预留字段3
     */
    private java.lang.String mainExtend3;


    /**
     * 预留字段4
     */
    private java.lang.String mainExtend4;


    /**
     * 预留字段5
     */
    private java.lang.String mainExtend5;


    /**
     * 保存派发对象
     */
    private java.lang.String sendObject;

    public void setMainISMPSheetId(java.lang.String mainISMPSheetId) {
        this.mainISMPSheetId = mainISMPSheetId;
    }

    public java.lang.String getMainISMPSheetId() {
        return this.mainISMPSheetId;
    }


    public void setMainUserId(java.lang.String mainUserId) {
        this.mainUserId = mainUserId;
    }

    public java.lang.String getMainUserId() {
        return this.mainUserId;
    }


    public void setMainAcceptTime(java.util.Date mainAcceptTime) {
        this.mainAcceptTime = mainAcceptTime;
    }

    public java.util.Date getMainAcceptTime() {
        return this.mainAcceptTime;
    }


    public void setMainDealTime(java.util.Date mainDealTime) {
        this.mainDealTime = mainDealTime;
    }

    public java.util.Date getMainDealTime() {
        return this.mainDealTime;
    }


    public void setMainProvince(java.lang.String mainProvince) {
        this.mainProvince = mainProvince;
    }

    public java.lang.String getMainProvince() {
        return this.mainProvince;
    }


    public void setMainCity(java.lang.String mainCity) {
        this.mainCity = mainCity;
    }

    public java.lang.String getMainCity() {
        return this.mainCity;
    }


    public void setMainSendWay(java.lang.String mainSendWay) {
        this.mainSendWay = mainSendWay;
    }

    public java.lang.String getMainSendWay() {
        return this.mainSendWay;
    }


    public void setMainALarmTitle(java.lang.String mainALarmTitle) {
        this.mainALarmTitle = mainALarmTitle;
    }

    public java.lang.String getMainALarmTitle() {
        return this.mainALarmTitle;
    }


    public void setMainALarmTime(java.util.Date mainALarmTime) {
        this.mainALarmTime = mainALarmTime;
    }

    public java.util.Date getMainALarmTime() {
        return this.mainALarmTime;
    }


    public void setMainALarmType(java.lang.String mainALarmType) {
        this.mainALarmType = mainALarmType;
    }

    public java.lang.String getMainALarmType() {
        return this.mainALarmType;
    }


    public void setMainALarmName(java.lang.String mainALarmName) {
        this.mainALarmName = mainALarmName;
    }

    public java.lang.String getMainALarmName() {
        return this.mainALarmName;
    }


    public void setMainALarmID(java.lang.String mainALarmID) {
        this.mainALarmID = mainALarmID;
    }

    public java.lang.String getMainALarmID() {
        return this.mainALarmID;
    }


    public void setMainOrigSourceID(java.lang.String mainOrigSourceID) {
        this.mainOrigSourceID = mainOrigSourceID;
    }

    public java.lang.String getMainOrigSourceID() {
        return this.mainOrigSourceID;
    }


    public void setMainOrigPurposeID(java.lang.String mainOrigPurposeID) {
        this.mainOrigPurposeID = mainOrigPurposeID;
    }

    public java.lang.String getMainOrigPurposeID() {
        return this.mainOrigPurposeID;
    }


    public void setMainNowSourceID(java.lang.String mainNowSourceID) {
        this.mainNowSourceID = mainNowSourceID;
    }

    public java.lang.String getMainNowSourceID() {
        return this.mainNowSourceID;
    }


    public void setMainNowPurposeID(java.lang.String mainNowPurposeID) {
        this.mainNowPurposeID = mainNowPurposeID;
    }

    public java.lang.String getMainNowPurposeID() {
        return this.mainNowPurposeID;
    }


    public void setMainALarmContent(java.lang.String mainALarmContent) {
        this.mainALarmContent = mainALarmContent;
    }

    public java.lang.String getMainALarmContent() {
        return this.mainALarmContent;
    }


    public void setMainExtend1(java.lang.String mainExtend1) {
        this.mainExtend1 = mainExtend1;
    }

    public java.lang.String getMainExtend1() {
        return this.mainExtend1;
    }


    public void setMainExtend2(java.lang.String mainExtend2) {
        this.mainExtend2 = mainExtend2;
    }

    public java.lang.String getMainExtend2() {
        return this.mainExtend2;
    }


    public void setMainExtend3(java.lang.String mainExtend3) {
        this.mainExtend3 = mainExtend3;
    }

    public java.lang.String getMainExtend3() {
        return this.mainExtend3;
    }


    public void setMainExtend4(java.lang.String mainExtend4) {
        this.mainExtend4 = mainExtend4;
    }

    public java.lang.String getMainExtend4() {
        return this.mainExtend4;
    }


    public void setMainExtend5(java.lang.String mainExtend5) {
        this.mainExtend5 = mainExtend5;
    }

    public java.lang.String getMainExtend5() {
        return this.mainExtend5;
    }


    public java.lang.String getSendObject() {
        return sendObject;
    }

    public void setSendObject(java.lang.String sendObject) {
        this.sendObject = sendObject;
    }
}