package com.boco.eoms.message.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="sms_monitor"
 */
public class VoiceMonitor extends BaseObject {
    private String id;

    private String applyId;

    private String buizid;

    private String serviceId;

    private Date dispatchTime;

    private Date allocTime;

    private Date finishTime;

    private String senderId;

    private String senderNum;

    private String receiverId;

    private String receiverNum;

    private String dutyman;

    private String dutymanNum;

    private String content;

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
     * @hibernate.property length="500" not-null="true" unique="true"
     * @eoms.show
     * @eoms.cn name="发送内容"
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
     * @hibernate.property length="30" not-null="true" unique="true"
     * @eoms.show
     * @eoms.cn name="接单人userID"
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
     * @hibernate.property length="50" not-null="true" unique="false"
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
     * @hibernate.property length="30" not-null="true" unique="false"
     * @eoms.show
     * @eoms.cn name="派单时间"
     */

    public Date getAllocTime() {
        return allocTime;
    }

    public void setAllocTime(Date allocTime) {
        this.allocTime = allocTime;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="30" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="工单直接责任人userId"
     */
    public String getDutyman() {
        return dutyman;
    }

    public void setDutyman(String dutyman) {
        this.dutyman = dutyman;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="36" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="工单直接责任人电话"
     */
    public String getDutymanNum() {
        return dutymanNum;
    }

    public void setDutymanNum(String dutymanNum) {
        this.dutymanNum = dutymanNum;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="30" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="要求结束时间"
     */
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="36" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="工单接收人电话"
     */
    public String getReceiverNum() {
        return receiverNum;
    }

    public void setReceiverNum(String receiverNum) {
        this.receiverNum = receiverNum;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="30" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="派单人userId"
     */
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="36" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="派单人电话"
     */
    public String getSenderNum() {
        return senderNum;
    }

    public void setSenderNum(String senderNum) {
        this.senderNum = senderNum;
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
