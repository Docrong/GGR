package com.boco.eoms.sheet.mofficedata.model;

import com.boco.eoms.sheet.base.model.BaseLink;

import java.util.Date;

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

public class MofficeDataLink extends BaseLink {

    /**
     * 审批意见
     */
    private java.lang.String linkAuditSu;

    /**
     * 从归档环节被驳回标记：Mark
     */
    private java.lang.String linkStyle1;

    /**
     * 预留字段二
     */
    private java.lang.String linkStyle2;

    /**
     * 预留字段三
     */
    private java.lang.String linkStyle3;

    /**
     * 预留字段四
     */
    private java.lang.String linkStyle4;

    /**
     * 预留字段五
     */
    private java.lang.String linkStyle5;

    /**
     * 是否需要地市拨测
     */
    private java.lang.String linkIsNeedTest;

    /**
     * 审批意见
     */
    private java.lang.String linkAuditRe;

    /**
     * 内容描述
     */
    private java.lang.String linkRemarkDe;

    /**
     * 驳回原因
     */
    private java.lang.String linkRejectRe;

    /**
     * 处理结果描述
     */
    private java.lang.String linkDealDesc;

    /**
     * 驳回原因
     */
    private java.lang.String linkRejectWh;

    /**
     * 驳回原因
     */
    private java.lang.String linkRejectHt;

    /**
     * 保存派发对象
     */
    private java.lang.String linkSendObject;


    public void setLinkAuditSu(java.lang.String linkAuditSu) {
        this.linkAuditSu = linkAuditSu;
    }

    public java.lang.String getLinkAuditSu() {
        return this.linkAuditSu;
    }

    public void setLinkStyle1(java.lang.String linkStyle1) {
        this.linkStyle1 = linkStyle1;
    }

    public java.lang.String getLinkStyle1() {
        return this.linkStyle1;
    }

    public void setLinkStyle2(java.lang.String linkStyle2) {
        this.linkStyle2 = linkStyle2;
    }

    public java.lang.String getLinkStyle2() {
        return this.linkStyle2;
    }

    public void setLinkStyle3(java.lang.String linkStyle3) {
        this.linkStyle3 = linkStyle3;
    }

    public java.lang.String getLinkStyle3() {
        return this.linkStyle3;
    }

    public void setLinkStyle4(java.lang.String linkStyle4) {
        this.linkStyle4 = linkStyle4;
    }

    public java.lang.String getLinkStyle4() {
        return this.linkStyle4;
    }

    public void setLinkStyle5(java.lang.String linkStyle5) {
        this.linkStyle5 = linkStyle5;
    }

    public java.lang.String getLinkStyle5() {
        return this.linkStyle5;
    }

    public void setLinkIsNeedTest(java.lang.String linkIsNeedTest) {
        this.linkIsNeedTest = linkIsNeedTest;
    }

    public java.lang.String getLinkIsNeedTest() {
        return this.linkIsNeedTest;
    }

    public void setLinkAuditRe(java.lang.String linkAuditRe) {
        this.linkAuditRe = linkAuditRe;
    }

    public java.lang.String getLinkAuditRe() {
        return this.linkAuditRe;
    }

    public void setLinkRemarkDe(java.lang.String linkRemarkDe) {
        this.linkRemarkDe = linkRemarkDe;
    }

    public java.lang.String getLinkRemarkDe() {
        return this.linkRemarkDe;
    }

    public void setLinkRejectRe(java.lang.String linkRejectRe) {
        this.linkRejectRe = linkRejectRe;
    }

    public java.lang.String getLinkRejectRe() {
        return this.linkRejectRe;
    }

    public void setLinkDealDesc(java.lang.String linkDealDesc) {
        this.linkDealDesc = linkDealDesc;
    }

    public java.lang.String getLinkDealDesc() {
        return this.linkDealDesc;
    }

    public void setLinkRejectWh(java.lang.String linkRejectWh) {
        this.linkRejectWh = linkRejectWh;
    }

    public java.lang.String getLinkRejectWh() {
        return this.linkRejectWh;
    }

    public void setLinkRejectHt(java.lang.String linkRejectHt) {
        this.linkRejectHt = linkRejectHt;
    }

    public java.lang.String getLinkRejectHt() {
        return this.linkRejectHt;
    }


    public java.lang.String getLinkSendObject() {
        return linkSendObject;
    }

    public void setLinkSendObject(java.lang.String linkSendObject) {
        this.linkSendObject = linkSendObject;
    }


}