package com.boco.eoms.sheet.industrysms.model;

import com.boco.eoms.sheet.base.model.BaseLink;

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

public class IndustrySmsLink extends BaseLink {

    /**
     * 审核结果
     */
    private java.lang.String approveResult;

    /**
     * 审核意见
     */
    private java.lang.String approveOpinion;

    /**
     * 数据制作结果
     */
    private java.lang.String dataResult;

    /**
     * 数据制作失败原因
     */
    private java.lang.String failureInfo;

    /**
     * 保存派发对象
     */
    private java.lang.String linkSendObject;

    public void setApproveResult(java.lang.String approveResult) {
        this.approveResult = approveResult;
    }

    public java.lang.String getApproveResult() {
        return this.approveResult;
    }

    public void setApproveOpinion(java.lang.String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    public java.lang.String getApproveOpinion() {
        return this.approveOpinion;
    }


    public java.lang.String getLinkSendObject() {
        return linkSendObject;
    }

    public void setLinkSendObject(java.lang.String linkSendObject) {
        this.linkSendObject = linkSendObject;
    }

    public java.lang.String getDataResult() {
        return dataResult;
    }

    public void setDataResult(java.lang.String dataResult) {
        this.dataResult = dataResult;
    }

    public java.lang.String getFailureInfo() {
        return failureInfo;
    }

    public void setFailureInfo(java.lang.String failureInfo) {
        this.failureInfo = failureInfo;
    }
}