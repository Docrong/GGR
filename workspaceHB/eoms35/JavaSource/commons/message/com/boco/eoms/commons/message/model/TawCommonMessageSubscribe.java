package com.boco.eoms.commons.message.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="TawCommonMessageOpertype.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_messagesubscribe"
 */
public class TawCommonMessageSubscribe extends BaseObject implements
        Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String userid;

    private String receivertype;

    private String revecer;

    private String messageid;

    private String startday;

    private String starthour;

    private String startmin;

    private String endday;

    private String endhour;

    private String endmin;

    private String sendcount;

    private String sendcuttime;

    private String remark;

    private String remarktwo;

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="消息失效的天"
     */
    public String getEndday() {
        return endday;
    }

    public void setEndday(String endday) {
        this.endday = endday;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="消息失效的小时"
     */
    public String getEndhour() {
        return endhour;
    }

    public void setEndhour(String endhour) {
        this.endhour = endhour;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="消息失效的分"
     */
    public String getEndmin() {
        return endmin;
    }

    public void setEndmin(String endmin) {
        this.endmin = endmin;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="服务ID 需要获取服务进行显示让客户选择"
     */
    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="接收者类型 人员 部门 机房 岗位"
     */
    public String getReceivertype() {
        return receivertype;
    }

    public void setReceivertype(String receivertype) {
        this.receivertype = receivertype;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="备注"
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="备注"
     */
    public String getRemarktwo() {
        return remarktwo;
    }

    public void setRemarktwo(String remarktwo) {
        this.remarktwo = remarktwo;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="接收者userid"
     */
    public String getRevecer() {
        return revecer;
    }

    public void setRevecer(String revecer) {
        this.revecer = revecer;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="在规定时间内消息发送次数"
     */
    public String getSendcount() {
        return sendcount;
    }

    public void setSendcount(String sendcount) {
        this.sendcount = sendcount;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="消息生效的天"
     */
    public String getStartday() {
        return startday;
    }

    public void setStartday(String startday) {
        this.startday = startday;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="消息生效的小时"
     */
    public String getStarthour() {
        return starthour;
    }

    public void setStarthour(String starthour) {
        this.starthour = starthour;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="消息生效的分"
     */
    public String getStartmin() {
        return startmin;
    }

    public void setStartmin(String startmin) {
        this.startmin = startmin;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="订阅者的userid"
     */
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="发送次数的时间间隔"
     */
    public String getSendcuttime() {
        return sendcuttime;
    }

    public void setSendcuttime(String sendcuttime) {
        this.sendcuttime = sendcuttime;
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
