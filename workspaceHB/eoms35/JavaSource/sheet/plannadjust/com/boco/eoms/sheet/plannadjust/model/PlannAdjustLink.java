package com.boco.eoms.sheet.plannadjust.model;

import com.boco.eoms.sheet.base.model.BaseLink;

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

public class PlannAdjustLink extends BaseLink {

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
     * 审核确认结果
     */
    private java.lang.String checkResult;

    /**
     * 驳回理由
     */
    private java.lang.String reasonRejection;

    /**
     * 保存派发对象
     */
    private java.lang.String linkSendObject;

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

    public void setCheckResult(java.lang.String checkResult) {
        this.checkResult = checkResult;
    }

    public java.lang.String getCheckResult() {
        return this.checkResult;
    }

    public void setReasonRejection(java.lang.String reasonRejection) {
        this.reasonRejection = reasonRejection;
    }

    public java.lang.String getReasonRejection() {
        return this.reasonRejection;
    }


    public java.lang.String getLinkSendObject() {
        return linkSendObject;
    }

    public void setLinkSendObject(java.lang.String linkSendObject) {
        this.linkSendObject = linkSendObject;
    }
}