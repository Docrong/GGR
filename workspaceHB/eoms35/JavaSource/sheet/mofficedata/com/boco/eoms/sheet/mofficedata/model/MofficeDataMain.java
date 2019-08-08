package com.boco.eoms.sheet.mofficedata.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 *
 * @author weichao
 * @version 3.5
 */

public class MofficeDataMain extends BaseMain {

    /**
     * 局数据编号
     */
    private java.lang.String mainDataNo;


    /**
     * 专业
     */
    private java.lang.String mainMajorType;


    /**
     * 网元类型
     */
    private java.lang.String mainNetType;


    /**
     * 设备厂家
     */
    private java.lang.String mainDeviceFa;


    /**
     * 内容描述
     */
    private java.lang.String mainDescRemark;


    /**
     * 局数据业务类型
     */
    private java.lang.String mainStyle1;


    /**
     * 预留字段二
     */
    private java.lang.String mainStyle2;


    /**
     * 预留字段三
     */
    private java.lang.String mainStyle3;


    /**
     * 预留字段四
     */
    private java.lang.String mainStyle4;


    /**
     * 专业科室
     */
    private java.lang.String mainStyle5;

    /**
     * 数据核查执行结果
     */
    private String mainStyle6;
    /**
     * 数据制作执行结果
     */
    private String mainStyle7;
    /**
     * 业务拨测执行结果
     */
    private String mainStyle8;
    /**
     * 信令监测执行结果
     */
    private String mainStyle9;
    /**
     * 话单反查执行结果
     */
    private String mainStyle10;

    /**
     * 预留时间字段1
     */
    private java.util.Date mainStyleTime1;


    /**
     * 预留时间字段2
     */
    private java.util.Date mainStyleTime2;


    /**
     * 保存派发对象
     */
    private java.lang.String sendObject;

    /**
     * 网络一级分类
     */
    private String mainNetTypeOne;


    /**
     * 网络二级分类
     */
    private String mainNetTypeTwo;

    /**
     * 网络三级分类
     */
    private String mainNetTypeThree;

    /**
     * 变更来源
     */
    private String mainChangeSource;

    /**
     * 技术方案关键字
     */
    private String linkDesignKey;
    /**
     * 计划开始时间
     */
    private Date linkPlanStartTime;

    /**
     * 计划结束时间
     */
    private Date linkPlanEndTime;

    private Integer mainSendModeType;

    public void setMainDataNo(java.lang.String mainDataNo) {
        this.mainDataNo = mainDataNo;
    }

    public java.lang.String getMainDataNo() {
        return this.mainDataNo;
    }


    public void setMainMajorType(java.lang.String mainMajorType) {
        this.mainMajorType = mainMajorType;
    }

    public java.lang.String getMainMajorType() {
        return this.mainMajorType;
    }


    public void setMainNetType(java.lang.String mainNetType) {
        this.mainNetType = mainNetType;
    }

    public java.lang.String getMainNetType() {
        return this.mainNetType;
    }


    public void setMainDeviceFa(java.lang.String mainDeviceFa) {
        this.mainDeviceFa = mainDeviceFa;
    }

    public java.lang.String getMainDeviceFa() {
        return this.mainDeviceFa;
    }


    public void setMainDescRemark(java.lang.String mainDescRemark) {
        this.mainDescRemark = mainDescRemark;
    }

    public java.lang.String getMainDescRemark() {
        return this.mainDescRemark;
    }


    public void setMainStyle1(java.lang.String mainStyle1) {
        this.mainStyle1 = mainStyle1;
    }

    public java.lang.String getMainStyle1() {
        return this.mainStyle1;
    }


    public void setMainStyle2(java.lang.String mainStyle2) {
        this.mainStyle2 = mainStyle2;
    }

    public java.lang.String getMainStyle2() {
        return this.mainStyle2;
    }


    public void setMainStyle3(java.lang.String mainStyle3) {
        this.mainStyle3 = mainStyle3;
    }

    public java.lang.String getMainStyle3() {
        return this.mainStyle3;
    }


    public void setMainStyle4(java.lang.String mainStyle4) {
        this.mainStyle4 = mainStyle4;
    }

    public java.lang.String getMainStyle4() {
        return this.mainStyle4;
    }


    public void setMainStyle5(java.lang.String mainStyle5) {
        this.mainStyle5 = mainStyle5;
    }

    public java.lang.String getMainStyle5() {
        return this.mainStyle5;
    }


    public void setMainStyleTime1(java.util.Date mainStyleTime1) {
        this.mainStyleTime1 = mainStyleTime1;
    }

    public java.util.Date getMainStyleTime1() {
        return this.mainStyleTime1;
    }


    public void setMainStyleTime2(java.util.Date mainStyleTime2) {
        this.mainStyleTime2 = mainStyleTime2;
    }

    public java.util.Date getMainStyleTime2() {
        return this.mainStyleTime2;
    }


    public String getMainStyle10() {
        return mainStyle10;
    }

    public void setMainStyle10(String mainStyle10) {
        this.mainStyle10 = mainStyle10;
    }

    public String getMainStyle6() {
        return mainStyle6;
    }

    public void setMainStyle6(String mainStyle6) {
        this.mainStyle6 = mainStyle6;
    }

    public String getMainStyle7() {
        return mainStyle7;
    }

    public void setMainStyle7(String mainStyle7) {
        this.mainStyle7 = mainStyle7;
    }

    public String getMainStyle8() {
        return mainStyle8;
    }

    public void setMainStyle8(String mainStyle8) {
        this.mainStyle8 = mainStyle8;
    }

    public String getMainStyle9() {
        return mainStyle9;
    }

    public void setMainStyle9(String mainStyle9) {
        this.mainStyle9 = mainStyle9;
    }

    public java.lang.String getSendObject() {
        return sendObject;
    }

    public void setSendObject(java.lang.String sendObject) {
        this.sendObject = sendObject;
    }

    public String getMainNetTypeOne() {
        return mainNetTypeOne;
    }

    public void setMainNetTypeOne(String mainNetTypeOne) {
        this.mainNetTypeOne = mainNetTypeOne;
    }

    public String getMainNetTypeThree() {
        return mainNetTypeThree;
    }

    public void setMainNetTypeThree(String mainNetTypeThree) {
        this.mainNetTypeThree = mainNetTypeThree;
    }

    public String getMainNetTypeTwo() {
        return mainNetTypeTwo;
    }

    public void setMainNetTypeTwo(String mainNetTypeTwo) {
        this.mainNetTypeTwo = mainNetTypeTwo;
    }

    public String getMainChangeSource() {
        return mainChangeSource;
    }

    public void setMainChangeSource(String mainChangeSource) {
        this.mainChangeSource = mainChangeSource;
    }

    public String getLinkDesignKey() {
        return linkDesignKey;
    }

    public void setLinkDesignKey(String linkDesignKey) {
        this.linkDesignKey = linkDesignKey;
    }

    public Date getLinkPlanStartTime() {
        return linkPlanStartTime;
    }

    public void setLinkPlanStartTime(Date linkPlanStartTime) {
        this.linkPlanStartTime = linkPlanStartTime;
    }

    public Date getLinkPlanEndTime() {
        return linkPlanEndTime;
    }

    public void setLinkPlanEndTime(Date linkPlanEndTime) {
        this.linkPlanEndTime = linkPlanEndTime;
    }

    public Integer getMainSendModeType() {
        return mainSendModeType;
    }

    public void setMainSendModeType(Integer mainSendModeType) {
        this.mainSendModeType = mainSendModeType;
    }


}