package com.boco.eoms.sheet.numberapply.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 *
 * @author liuyang
 * @version 3.5
 */

public class NumberApplyMain extends BaseMain {

    /**
     * 受理时间
     */
    private java.util.Date mainAcceptTime;


    /**
     * 处理时限
     */
    private java.util.Date mainDealLimit;


    /**
     * 申请资源类型
     */
    private java.lang.String mainResourceType;

    //add by zhouzhe 2010-10-14  begin
    /**
     * HLR资源类型
     */
    private java.lang.String mainHLRResource;

    /**
     * MSC、GMSC资源类型
     */
    private java.lang.String mainMSCResource;

    //add by zhouzhe 2010-10-14  end

    /**
     * 申请内容描述
     */
    private java.lang.String mainContentDescribe;


    /**
     * 附件
     */
    private java.lang.String mainAppendix;


    /**
     * 保存派发对象
     */
    private java.lang.String sendObject;

    public void setMainAcceptTime(java.util.Date mainAcceptTime) {
        this.mainAcceptTime = mainAcceptTime;
    }

    public java.util.Date getMainAcceptTime() {
        return this.mainAcceptTime;
    }


    public void setMainDealLimit(java.util.Date mainDealLimit) {
        this.mainDealLimit = mainDealLimit;
    }

    public java.util.Date getMainDealLimit() {
        return this.mainDealLimit;
    }

    public void setMainResourceType(java.lang.String mainResourceType) {
        this.mainResourceType = mainResourceType;
    }

    public java.lang.String getMainResourceType() {
        return this.mainResourceType;
    }


    public java.lang.String getMainHLRResource() {
        return mainHLRResource;
    }

    public void setMainHLRResource(java.lang.String mainHLRResource) {
        this.mainHLRResource = mainHLRResource;
    }

    public java.lang.String getMainMSCResource() {
        return mainMSCResource;
    }

    public void setMainMSCResource(java.lang.String mainMSCResource) {
        this.mainMSCResource = mainMSCResource;
    }

    public void setMainContentDescribe(java.lang.String mainContentDescribe) {
        this.mainContentDescribe = mainContentDescribe;
    }

    public java.lang.String getMainContentDescribe() {
        return this.mainContentDescribe;
    }


    public void setMainAppendix(java.lang.String mainAppendix) {
        this.mainAppendix = mainAppendix;
    }

    public java.lang.String getMainAppendix() {
        return this.mainAppendix;
    }


    public java.lang.String getSendObject() {
        return sendObject;
    }

    public void setSendObject(java.lang.String sendObject) {
        this.sendObject = sendObject;
    }
}