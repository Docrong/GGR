package com.boco.eoms.workplan.model;

/**
 * <p>Title: 作业计划代理人类</p>
 * <p>Description: 作业计划代理人类信息，其中包括代理人，被代理人，代理期限等</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.*;

import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_STUBUSER"
 */

public class TawwpStubUser
        implements Serializable, DataObject {

    private String id; //标识
    private String cruser; //代理人申请人
    private String stubuser; //代理人
    private String startDate; //代理开始时间
    private String endDate; //代理结束时间
    private String content; //描述
    private String checkuser; //审批人
    private String state; //代理标志 0：新建 1：代理中 2：审批中 3：驳回

    public TawwpStubUser() {
    }

    public TawwpStubUser(String _cruser, String _stubuser, String _startDate,
                         String _endDate, String _content, String _checkuser,
                         String _state) {
        this.cruser = _cruser;
        this.stubuser = _stubuser;
        this.startDate = _startDate;
        this.endDate = _endDate;
        this.content = _content;
        this.checkuser = _checkuser;
        this.state = _state;
    }

    /**
     * @hibernate.id column="ID"
     * length="32"
     * unsaved-value="null"
     * generator-class="uuid.hex"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column="CRUSER"
     * length="20"
     * not-null="true"
     * update="false"
     */
    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    /**
     * @hibernate.property column="STUBUSER"
     * length="20"
     * not-null="false"
     * update="true"
     */
    public String getStubuser() {
        return stubuser;
    }

    public void setStubuser(String stubuser) {
        this.stubuser = stubuser;
    }

    /**
     * @hibernate.property column="STARTDATE"
     * length="19"
     * not-null="false"
     * update="true"
     */
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @hibernate.property column="ENDDATE"
     * length="19"
     * not-null="false"
     * update="true"
     */
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @hibernate.property column="STATE"
     * length=1"
     * not-null="true"
     * update="true"
     */
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * @hibernate.property column="CONTENT"
     * length=200"
     * not-null="false"
     * update="true"
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @hibernate.property column="CHECKUSER"
     * length="20"
     * not-null="false"
     * update="true"
     */
    public String getCheckuser() {
        return checkuser;
    }

    public void setCheckuser(String checkuser) {
        this.checkuser = checkuser;
    }

}
