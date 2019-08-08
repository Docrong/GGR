package com.boco.eoms.sheet.localCommonTask.model;

import com.boco.eoms.sheet.base.model.BaseLink;

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

public class LocalCommonTaskLink extends BaseLink {
    /**
     * @textarea
     */
    private java.lang.String linkAuditResult;

    /**
     * @textarea
     */
    private java.lang.String linkAuditIdea;

    /**
     * @textarea
     */
    private java.lang.String linkTaskComplete;

    private java.lang.String linkTransmitContent;

    public java.lang.String getLinkAuditIdea() {
        return linkAuditIdea;
    }

    public void setLinkAuditIdea(java.lang.String linkAuditIdea) {
        this.linkAuditIdea = linkAuditIdea;
    }

    public java.lang.String getLinkAuditResult() {
        return linkAuditResult;
    }

    public void setLinkAuditResult(java.lang.String linkAuditResult) {
        this.linkAuditResult = linkAuditResult;
    }

    public java.lang.String getLinkTaskComplete() {
        return linkTaskComplete;
    }

    public void setLinkTaskComplete(java.lang.String linkTaskComplete) {
        this.linkTaskComplete = linkTaskComplete;
    }

    public java.lang.String getLinkTransmitContent() {
        return linkTransmitContent;
    }

    public void setLinkTransmitContent(java.lang.String linkTransmitContent) {
        this.linkTransmitContent = linkTransmitContent;
    }
}