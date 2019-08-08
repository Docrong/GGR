package com.boco.eoms.sheet.circuitcontrol.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:电路调度申请工单
 * </p>
 * <p>
 * Description:电路调度申请工单
 * </p>
 * <p>
 * Sun Sep 29 16:51:14 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class CircuitControlLink extends BaseLink {

    /**
     * 完成情况
     */
    private java.lang.String linkGroupComplete;

    /**
     * 保存派发对象
     */
    private java.lang.String linkSendObject;

    public void setLinkGroupComplete(java.lang.String linkGroupComplete) {
        this.linkGroupComplete = linkGroupComplete;
    }

    public java.lang.String getLinkGroupComplete() {
        return this.linkGroupComplete;
    }


    public java.lang.String getLinkSendObject() {
        return linkSendObject;
    }

    public void setLinkSendObject(java.lang.String linkSendObject) {
        this.linkSendObject = linkSendObject;
    }
}