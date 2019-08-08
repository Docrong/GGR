package com.boco.eoms.message.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="mms_monitor"
 */
public class MmsMonitor extends BaseObject {
    private String id;

    private String applyId;

    private String buizid;

    private String serviceId;

    private String receiverId;

    private String ccId;

    private String bccId;

    private String isSendImediat;

    private String regetData;

    private String mobile;

    private Date dispatchTime;

    private String subject;

    private String isCycleSend;

    private String deleted;

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="true" unique="true"
     * @eoms.show
     * @eoms.cn name="订阅服务ID"
     */
    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="200" not-null="true" unique="true"
     * @eoms.show
     * @eoms.cn name="发送主题"
     */
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="20" not-null="true" unique="true"
     * @eoms.show
     * @eoms.cn name="发送时间点"
     */
    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    /**
     * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="100" not-null="true" unique="true"
     * @eoms.show
     * @eoms.cn name="发送手机号码"
     */
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="30" not-null="true" unique="true"
     * @eoms.show
     * @eoms.cn name="接收者ID"
     */
    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="业务ID"
     */
    public String getBuizid() {
        return buizid;
    }

    public void setBuizid(String buizid) {
        this.buizid = buizid;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="服务ID"
     */
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="10" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="是否立即发送"
     */
    public String getIsSendImediat() {
        return isSendImediat;
    }

    public void setIsSendImediat(String isSendImediat) {
        this.isSendImediat = isSendImediat;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="删除标志"
     */
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="1"
     * @eoms.show
     * @eoms.cn name="是否重新采集数据" not-null="false" unique="true"
     */
    public String getRegetData() {
        return regetData;
    }

    public void setRegetData(String regetData) {
        this.regetData = regetData;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5"
     * @eoms.show
     * @eoms.cn name="是否循环发送" not-null="false" unique="true"
     */
    public String getIsCycleSend() {
        return isCycleSend;
    }

    public void setIsCycleSend(String isCycleSend) {
        this.isCycleSend = isCycleSend;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="抄送方" not-null="false" unique="true"
     */

    public String getBccId() {
        return bccId;
    }

    public void setBccId(String bccId) {
        this.bccId = bccId;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="密送方" not-null="false" unique="false"
     */
    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}
