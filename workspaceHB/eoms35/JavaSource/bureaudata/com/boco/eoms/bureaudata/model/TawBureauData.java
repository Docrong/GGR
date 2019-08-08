package com.boco.eoms.bureaudata.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="TawBureauData.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_bureaudata"
 */
public class TawBureauData extends BaseObject {

    private String id; // id

    private String bigNet; // 大网元

    private String net; // 小网元

    private String cruser; // 执行人

    private String crusdata;// 执行时间

    private String factory; // 厂商

    private String type; // 类型

    private String data; // 日期

    private String title; // 主题

    private String content; // 执行情况

    private String producer;// 制作人

    private String auditman;// 核查人

    private String remark; // 备注

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="核查人"
     */
    public String getAuditman() {
        return auditman;
    }

    public void setAuditman(String auditman) {
        this.auditman = auditman;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="大网元"
     */
    public String getBigNet() {
        return bigNet;
    }

    public void setBigNet(String bigNet) {
        this.bigNet = bigNet;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="执行情况"
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="执行时间"
     */
    public String getCrusdata() {
        return crusdata;
    }

    public void setCrusdata(String crusdata) {
        this.crusdata = crusdata;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="执行人"
     */
    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="日期"
     */
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="厂商"
     */
    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
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
     * @eoms.cn name="小网元"
     */
    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="制作人"
     */
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
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
     * @eoms.cn name="标题"
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="类型"
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
/*
  CREATE TABLE informix.taw_bureaudata(
	id              VARCHAR(32) NOT NULL PRIMARY KEY ,
	bigNet			VARCHAR(50),
	net           	VARCHAR(50),
	cruser          VARCHAR(50),
	crusdata		VARCHAR(50),
	factory			VARCHAR(50),
	type			VARCHAR(50),
	title			VARCHAR(255),
	data			VARCHAR(50),
	content			VARCHAR(255),
	producer		VARCHAR(50),
	auditman		VARCHAR(50),
	remark			VARCHAR(255)
)

 */

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }
}
