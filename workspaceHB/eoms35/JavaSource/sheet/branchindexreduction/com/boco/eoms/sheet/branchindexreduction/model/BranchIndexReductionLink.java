package com.boco.eoms.sheet.branchindexreduction.model;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * <p>
 * Title:分公司指标核减流程
 * </p>
 * <p>
 * Description:分公司指标核减流程
 * </p>
 * <p>
 * Fri Jul 28 15:47:19 CST 2017
 * </p>
 *
 * @author wangmingming
 * @version 1.0
 */

public class BranchIndexReductionLink extends BaseLink {

    /**
     * 联系人
     */
    private java.lang.String linkMan;

    /**
     * 联系人电话
     */
    private java.lang.String linkManTelephone;

    /**
     * 初审说明
     */
    private java.lang.String linkTrialIllustrate;

    /**
     * 保存派发对象
     */
    private java.lang.String linkSendObject;

    public void setLinkMan(java.lang.String linkMan) {
        this.linkMan = linkMan;
    }

    public java.lang.String getLinkMan() {
        return this.linkMan;
    }

    public void setLinkManTelephone(java.lang.String linkManTelephone) {
        this.linkManTelephone = linkManTelephone;
    }

    public java.lang.String getLinkManTelephone() {
        return this.linkManTelephone;
    }

    public void setLinkTrialIllustrate(java.lang.String linkTrialIllustrate) {
        this.linkTrialIllustrate = linkTrialIllustrate;
    }

    public java.lang.String getLinkTrialIllustrate() {
        return this.linkTrialIllustrate;
    }


    public java.lang.String getLinkSendObject() {
        return linkSendObject;
    }

    public void setLinkSendObject(java.lang.String linkSendObject) {
        this.linkSendObject = linkSendObject;
    }
}