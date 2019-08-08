package com.boco.eoms.commons.message.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="TawCommonMessageAddService.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_messageaddservice"
 */
public class TawCommonMessageAddService extends BaseObject implements
        Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String modelid;

    private String operid;

    private String urgency;

    private String opername;

    private String modelname;

    private String messagetype;

    private String issendimediat;

    private String issendnight;

    private String remark;

    private String modeloperid;

    private String userid;

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="用户ID"
     */
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="业务服务ID"
     */
    public String getModeloperid() {
        return modeloperid;
    }

    public void setModeloperid(String modeloperid) {
        this.modeloperid = modeloperid;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="是否立即发送"
     */
    public String getIssendimediat() {
        return issendimediat;
    }

    public void setIssendimediat(String issendimediat) {
        this.issendimediat = issendimediat;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="是否晚上发送"
     */
    public String getIssendnight() {
        return issendnight;
    }

    public void setIssendnight(String issendnight) {
        this.issendnight = issendnight;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="消息类型"
     */
    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="模块ID"
     */
    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="模块NAME"
     */
    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="业务ID"
     */
    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="业务名称"
     */
    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername;
    }

    /**
     * @return
     * @hibernate.property length="100"
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
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="紧急程度"
     */
    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
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
