package com.boco.eoms.commonfaulthj.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:commonfaulthj
 * </p>
 * <p>
 * Description:commonfaulthj
 * </p>
 * <p>
 * Thu Dec 18 15:28:05 CST 2014
 * </p>
 *
 * @moudle.getAuthor() zhoupan
 * @moudle.getVersion() 3.5
 */
public class CommonfaulthjForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 锟斤拷锟�
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * mainid
     */
    private java.lang.String mainid;

    public void setMainid(java.lang.String mainid) {
        this.mainid = mainid;
    }

    public java.lang.String getMainid() {
        return this.mainid;
    }

    /**
     * sheetid
     */
    private java.lang.String sheetid;

    public void setSheetid(java.lang.String sheetid) {
        this.sheetid = sheetid;
    }

    public java.lang.String getSheetid() {
        return this.sheetid;
    }

    /**
     * creater
     */
    private java.lang.String creater;

    public void setCreater(java.lang.String creater) {
        this.creater = creater;
    }

    public java.lang.String getCreater() {
        return this.creater;
    }

    /**
     * savetime
     */
    private java.lang.String savetime;

    public void setSavetime(java.lang.String savetime) {
        this.savetime = savetime;
    }

    public java.lang.String getSavetime() {
        return this.savetime;
    }

    /**
     * updatetime
     */
    private java.lang.String updatetime;

    public void setUpdatetime(java.lang.String updatetime) {
        this.updatetime = updatetime;
    }

    public java.lang.String getUpdatetime() {
        return this.updatetime;
    }

    /**
     * sendyear
     */
    private java.lang.String sendyear;

    public void setSendyear(java.lang.String sendyear) {
        this.sendyear = sendyear;
    }

    public java.lang.String getSendyear() {
        return this.sendyear;
    }

    /**
     * sendmonth
     */
    private java.lang.String sendmonth;

    public void setSendmonth(java.lang.String sendmonth) {
        this.sendmonth = sendmonth;
    }

    public java.lang.String getSendmonth() {
        return this.sendmonth;
    }

    /**
     * sendday
     */
    private java.lang.String sendday;

    public void setSendday(java.lang.String sendday) {
        this.sendday = sendday;
    }

    public java.lang.String getSendday() {
        return this.sendday;
    }

    /**
     * remark
     */
    private java.lang.String remark;

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * add by zhoupan 20160913
     * 核减类型
     */
    private java.lang.String subtractType;

    public java.lang.String getSubtractType() {
        return subtractType;
    }

    public void setSubtractType(java.lang.String subtractType) {
        this.subtractType = subtractType;
    }


}