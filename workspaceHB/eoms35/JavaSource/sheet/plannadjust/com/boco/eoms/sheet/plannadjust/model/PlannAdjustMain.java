package com.boco.eoms.sheet.plannadjust.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:规划站址调整申请流程
 * </p>
 * <p>
 * Description:规划站址调整申请流程
 * </p>
 * <p>
 * Sat Jun 08 11:16:09 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class PlannAdjustMain extends BaseMain {

    /**
     * 属地分公司
     */
    private java.lang.String territorialBranch;


    /**
     * 行政区域
     */
    private java.lang.String administrativeArea;


    /**
     * 申请时间
     */
    private java.util.Date applicationTime;


    /**
     * 规划编号
     */
    private java.lang.String planningNumber;


    /**
     * 所属规划方案
     */
    private java.lang.String planningScheme;


    /**
     * 规划站经度
     */
    private java.lang.String longitude;


    /**
     * 规划站纬度
     */
    private java.lang.String latitude;


    /**
     * 网元
     */
    private java.lang.String netType;


    /**
     * 所属专业
     */
    private java.lang.String professional;


    /**
     * 调整原因属性
     */
    private java.lang.String reasonAdjust;


    /**
     * 调整原因属性说明
     */
    private java.lang.String illustrate;


    /**
     * 规划起始时间
     */
    private java.util.Date planningStartTime;


    /**
     * 规划截至时间
     */
    private java.util.Date planningEndTime;


    /**
     * 调整申请简要说明
     */
    private java.lang.String briefDescription;


    /**
     * 规划区域内投诉情况
     */
    private java.lang.String planningAreaComplaint;


    /**
     * 规划区域内覆盖需求
     */
    private java.lang.String coverageRequirement;


    /**
     * GSM信号电平值
     */
    private java.lang.String gsmSignalLevel;


    /**
     * WCDMA信号电平值
     */
    private java.lang.String wcdmaSignalLevel;


    /**
     * CDMA信号电平值
     */
    private java.lang.String cdmaSignalLevel;


    /**
     * GSM基站位置
     */
    private java.lang.String gsmStationLocation;


    /**
     * WCDMA基站位置
     */
    private java.lang.String wcdmaStationLocation;


    /**
     * CDMA基站位置
     */
    private java.lang.String cdmaStationLocation;


    /**
     * GSM距离规划区(米)
     */
    private java.lang.String gsmPlanningArea;


    /**
     * WCDMA距离规划区(米)
     */
    private java.lang.String wcdmaPlanningArea;


    /**
     * CDMA距离规划区(米)
     */
    private java.lang.String cdmaPlanningArea;


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

    public void setTerritorialBranch(java.lang.String territorialBranch) {
        this.territorialBranch = territorialBranch;
    }

    public java.lang.String getTerritorialBranch() {
        return this.territorialBranch;
    }


    public void setAdministrativeArea(java.lang.String administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

    public java.lang.String getAdministrativeArea() {
        return this.administrativeArea;
    }


    public void setApplicationTime(java.util.Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public java.util.Date getApplicationTime() {
        return this.applicationTime;
    }


    public void setPlanningNumber(java.lang.String planningNumber) {
        this.planningNumber = planningNumber;
    }

    public java.lang.String getPlanningNumber() {
        return this.planningNumber;
    }


    public void setPlanningScheme(java.lang.String planningScheme) {
        this.planningScheme = planningScheme;
    }

    public java.lang.String getPlanningScheme() {
        return this.planningScheme;
    }


    public void setLongitude(java.lang.String longitude) {
        this.longitude = longitude;
    }

    public java.lang.String getLongitude() {
        return this.longitude;
    }


    public void setLatitude(java.lang.String latitude) {
        this.latitude = latitude;
    }

    public java.lang.String getLatitude() {
        return this.latitude;
    }


    public void setNetType(java.lang.String netType) {
        this.netType = netType;
    }

    public java.lang.String getNetType() {
        return this.netType;
    }


    public void setProfessional(java.lang.String professional) {
        this.professional = professional;
    }

    public java.lang.String getProfessional() {
        return this.professional;
    }


    public void setReasonAdjust(java.lang.String reasonAdjust) {
        this.reasonAdjust = reasonAdjust;
    }

    public java.lang.String getReasonAdjust() {
        return this.reasonAdjust;
    }


    public void setIllustrate(java.lang.String illustrate) {
        this.illustrate = illustrate;
    }

    public java.lang.String getIllustrate() {
        return this.illustrate;
    }


    public void setPlanningStartTime(java.util.Date planningStartTime) {
        this.planningStartTime = planningStartTime;
    }

    public java.util.Date getPlanningStartTime() {
        return this.planningStartTime;
    }


    public void setPlanningEndTime(java.util.Date planningEndTime) {
        this.planningEndTime = planningEndTime;
    }

    public java.util.Date getPlanningEndTime() {
        return this.planningEndTime;
    }


    public void setBriefDescription(java.lang.String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public java.lang.String getBriefDescription() {
        return this.briefDescription;
    }


    public void setPlanningAreaComplaint(java.lang.String planningAreaComplaint) {
        this.planningAreaComplaint = planningAreaComplaint;
    }

    public java.lang.String getPlanningAreaComplaint() {
        return this.planningAreaComplaint;
    }


    public void setCoverageRequirement(java.lang.String coverageRequirement) {
        this.coverageRequirement = coverageRequirement;
    }

    public java.lang.String getCoverageRequirement() {
        return this.coverageRequirement;
    }


    public void setGsmSignalLevel(java.lang.String gsmSignalLevel) {
        this.gsmSignalLevel = gsmSignalLevel;
    }

    public java.lang.String getGsmSignalLevel() {
        return this.gsmSignalLevel;
    }


    public void setWcdmaSignalLevel(java.lang.String wcdmaSignalLevel) {
        this.wcdmaSignalLevel = wcdmaSignalLevel;
    }

    public java.lang.String getWcdmaSignalLevel() {
        return this.wcdmaSignalLevel;
    }


    public void setCdmaSignalLevel(java.lang.String cdmaSignalLevel) {
        this.cdmaSignalLevel = cdmaSignalLevel;
    }

    public java.lang.String getCdmaSignalLevel() {
        return this.cdmaSignalLevel;
    }


    public void setGsmStationLocation(java.lang.String gsmStationLocation) {
        this.gsmStationLocation = gsmStationLocation;
    }

    public java.lang.String getGsmStationLocation() {
        return this.gsmStationLocation;
    }


    public void setWcdmaStationLocation(java.lang.String wcdmaStationLocation) {
        this.wcdmaStationLocation = wcdmaStationLocation;
    }

    public java.lang.String getWcdmaStationLocation() {
        return this.wcdmaStationLocation;
    }


    public void setCdmaStationLocation(java.lang.String cdmaStationLocation) {
        this.cdmaStationLocation = cdmaStationLocation;
    }

    public java.lang.String getCdmaStationLocation() {
        return this.cdmaStationLocation;
    }


    public void setGsmPlanningArea(java.lang.String gsmPlanningArea) {
        this.gsmPlanningArea = gsmPlanningArea;
    }

    public java.lang.String getGsmPlanningArea() {
        return this.gsmPlanningArea;
    }


    public void setWcdmaPlanningArea(java.lang.String wcdmaPlanningArea) {
        this.wcdmaPlanningArea = wcdmaPlanningArea;
    }

    public java.lang.String getWcdmaPlanningArea() {
        return this.wcdmaPlanningArea;
    }


    public void setCdmaPlanningArea(java.lang.String cdmaPlanningArea) {
        this.cdmaPlanningArea = cdmaPlanningArea;
    }

    public java.lang.String getCdmaPlanningArea() {
        return this.cdmaPlanningArea;
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
}