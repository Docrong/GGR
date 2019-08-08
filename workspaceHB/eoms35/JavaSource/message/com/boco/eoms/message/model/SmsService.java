package com.boco.eoms.message.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="sms_service"
 */
public class SmsService extends BaseObject {
    private String id;

    private String name;

    private String parentId;

    private String msgType;

    private Integer count;

    private String interval;

    private String isSendImediat;

    private String isSendNight;

    private Date startTime;

    private Date endTime;

    private String sendDay;

    private String sendHour;

    private String sendMin;

    private String sendStatus;

    private String isCycleSend;

    private String cycleStatus;

    private String cycleMonth;

    private String cycleDay;

    private String cycleHour;

    private String regetData;

    private String regetAddr;

    private String regetProtocol;

    private String userId;

    private String usersId;

    private String status;

    private String selStatus;

    private String deleted;

    private String leaf;

    private String remark;

    //2009-03-31 add  非值班状态是否发送,1发送，非1不发送
    private String isSendUnDuty;

    public String getIsSendUnDuty() {
        return isSendUnDuty;
    }

    public void setIsSendUnDuty(String isSendUnDuty) {
        this.isSendUnDuty = isSendUnDuty;
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

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="1" not-null="false" unique="true"
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
     * @hibernate.property length="1" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="是否允许晚上发送"
     */
    public String getIsSendNight() {
        return isSendNight;
    }

    public void setIsSendNight(String isSendNight) {
        this.isSendNight = isSendNight;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="1" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="消息类型"
     */
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="100" not-null="true" unique="true"
     * @eoms.show
     * @eoms.cn name="服务名称"
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="备注" not-null="false" unique="true"
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="消息发送次数" not-null="false" unique="true"
     */
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5"
     * @eoms.show
     * @eoms.cn name="循环天数" not-null="false" unique="true"
     */
    public String getCycleDay() {
        return cycleDay;
    }

    public void setCycleDay(String cycleDay) {
        this.cycleDay = cycleDay;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5"
     * @eoms.show
     * @eoms.cn name="循环小时数" not-null="false" unique="true"
     */
    public String getCycleHour() {
        return cycleHour;
    }

    public void setCycleHour(String cycleHour) {
        this.cycleHour = cycleHour;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5"
     * @eoms.show
     * @eoms.cn name="循环小时数" not-null="false" unique="true"
     */
    public String getCycleMonth() {
        return cycleMonth;
    }

    public void setCycleMonth(String cycleMonth) {
        this.cycleMonth = cycleMonth;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="25"
     * @eoms.show
     * @eoms.cn name="服务结束时间" not-null="false" unique="true"
     */
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = str2Date(endTime);
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="消息发送间隔" not-null="false" unique="true"
     */
    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5"
     * @eoms.show
     * @eoms.cn name="叶子节点" not-null="false" unique="true"
     */
    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
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
     * @eoms.cn name="催办天数" not-null="false" unique="true"
     */
    public String getSendDay() {
        return sendDay;
    }

    public void setSendDay(String sendDay) {
        this.sendDay = sendDay;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5"
     * @eoms.show
     * @eoms.cn name="催办小时数" not-null="false" unique="true"
     */
    public String getSendHour() {
        return sendHour;
    }

    public void setSendHour(String sendHour) {
        this.sendHour = sendHour;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5"
     * @eoms.show
     * @eoms.cn name="催办分钟数" not-null="false" unique="true"
     */
    public String getSendMin() {
        return sendMin;
    }

    public void setSendMin(String sendMin) {
        this.sendMin = sendMin;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="1"
     * @eoms.show
     * @eoms.cn name="消息发送时间标志" not-null="false" unique="true"
     */
    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="25"
     * @eoms.show
     * @eoms.cn name="服务开始时间" not-null="false" unique="true"
     */
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = str2Date(startTime);
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="30" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="当前用户名称"
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="循环标志"
     */
    public String getCycleStatus() {
        return cycleStatus;
    }

    public void setCycleStatus(String cycleStatus) {
        this.cycleStatus = cycleStatus;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="是否循环发送"
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
     * @hibernate.property length="32" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="父节点ID"
     */
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5" not-null="false" unique="true"
     * @eoms.show
     * @eoms.cn name="模块或者业务"
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="100" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="重新采集地址"
     */
    public String getRegetAddr() {
        return regetAddr;
    }

    public void setRegetAddr(String regetAddr) {
        this.regetAddr = regetAddr;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="30" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="重新采集数据协议"
     */
    public String getRegetProtocol() {
        return regetProtocol;
    }

    public void setRegetProtocol(String regetProtocol) {
        this.regetProtocol = regetProtocol;
    }

    /**
     * @return
     * @struts.validator type="required"
     * @hibernate.property length="5" not-null="false" unique="false"
     * @eoms.show
     * @eoms.cn name="正反标志"
     */
    public String getSelStatus() {
        return selStatus;
    }

    public void setSelStatus(String selStatus) {
        this.selStatus = selStatus;
    }

    /**
     * 字符串转为一定格式的时间
     *
     * @param str
     * @return
     * @author mengxianyu
     */
    private Date str2Date(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeTemp = null;
        try {
            timeTemp = formatter.parse(str);
        } catch (ParseException e) {
            System.out.println("字符串转时间出错： " + e.getMessage());
        }
        return timeTemp;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }


}
