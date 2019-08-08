package com.boco.eoms.workplan.model;

/**
 * <p>Title: 作业计划模板派发类</p>
 * <p>Description: 作业计划模板派发信息，其中包括派发部门，状态等信息</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import java.util.*;

import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_MODELDISPATCH"
 */

public class TawwpModelDispatch
        implements Serializable, DataObject {

    private String id; //标识
    private String sendDeptId; //派发部门
    private String sendCrtime; //派发时间
    private String sendUser; //派发人
    private String inceptDeptId; //接收部门
    private String inceptCrtime; //接收时间
    private String inceptUser; //接收人
    private String state; //派发状态 0：待接收 1：接收
    private String content; //派发信息
    private String yearFlag; //年度标识

    private TawwpModelDispatch tawwpModelDispatch; //父作业计划模板派发
    private Set childModelDispatch = new HashSet(); //子作业计划模板派发
    private TawwpModelPlan tawwpModelPlan; //作业计划模板对象 如果是初始派发，才有作业计划模板记录

    public TawwpModelDispatch() {
    }

    public TawwpModelDispatch(String _sendDeptId, String _sendCrtime, String _sendUser,
                              String _inceptDeptId, String _inceptCrtime, String _inceptUser,
                              String _state, String _content, String _yearFlag,
                              TawwpModelDispatch _tawwpModelDispatch,
                              Set _childModelDispatch,
                              TawwpModelPlan _tawwpModelPlan) {
        this.sendDeptId = _sendDeptId;
        this.sendCrtime = _sendCrtime;
        this.sendUser = _sendUser;
        this.inceptDeptId = _inceptDeptId;
        this.inceptCrtime = _inceptCrtime;
        this.inceptUser = _inceptUser;
        this.state = _state;
        this.content = _content;
        this.yearFlag = _yearFlag;
        this.tawwpModelDispatch = _tawwpModelDispatch;
        this.childModelDispatch = _childModelDispatch;
        this.tawwpModelPlan = _tawwpModelPlan;
    }

    public TawwpModelDispatch(String _sendDeptId, String _sendCrtime, String _sendUser,
                              String _inceptDeptId, String _inceptCrtime, String _inceptUser,
                              String _state, String _content, String _yearFlag,
                              TawwpModelDispatch _tawwpModelDispatch,
                              TawwpModelPlan _tawwpModelPlan) {
        this.sendDeptId = _sendDeptId;
        this.sendCrtime = _sendCrtime;
        this.sendUser = _sendUser;
        this.inceptDeptId = _inceptDeptId;
        this.inceptCrtime = _inceptCrtime;
        this.inceptUser = _inceptUser;
        this.state = _state;
        this.content = _content;
        this.yearFlag = _yearFlag;
        this.tawwpModelDispatch = _tawwpModelDispatch;
        this.tawwpModelPlan = _tawwpModelPlan;
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
     * @hibernate.property column="SENDDEPTID"
     * length="10"
     * not-null="true"
     * update="true"
     */
    public String getSendDeptId() {
        return sendDeptId;
    }

    public void setSendDeptId(String sendDeptId) {
        this.sendDeptId = sendDeptId;
    }

    /**
     * @hibernate.property column="SENDCRTIME"
     * length="19"
     * not-null="true"
     * update="true"
     */
    public String getSendCrtime() {
        return sendCrtime;
    }

    public void setSendCrtime(String sendCrtime) {
        this.sendCrtime = sendCrtime;
    }

    /**
     * @hibernate.property column="SENDUSER"
     * length="20"
     * not-null="false"
     * update="true"
     */
    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    /**
     * @hibernate.property column="INCEPTDEPTID"
     * length="10"
     * not-null="false"
     * update="true"
     */
    public String getInceptDeptId() {
        return inceptDeptId;
    }

    public void setInceptDeptId(String inceptDeptId) {
        this.inceptDeptId = inceptDeptId;
    }

    /**
     * @hibernate.property column="INCEPTCRTIME"
     * length="19"
     * not-null="true"
     * update="true"
     */
    public String getInceptCrtime() {
        return inceptCrtime;
    }

    public void setInceptCrtime(String inceptCrtime) {
        this.inceptCrtime = inceptCrtime;
    }

    /**
     * @hibernate.property column="INCEPTUSER"
     * length="20"
     * not-null="false"
     * update="true"
     */
    public String getInceptUser() {
        return inceptUser;
    }

    public void setInceptUser(String inceptUser) {
        this.inceptUser = inceptUser;
    }


    /**
     * @hibernate.property column="STATE"
     * length="2"
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
     * length="200"
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
     * @hibernate.property column="YEARFLAG"
     * length="4"
     * not-null="true"
     * update="true"
     */
    public String getYearFlag() {
        return yearFlag;
    }

    public void setYearFlag(String yearFlag) {
        this.yearFlag = yearFlag;
    }


    /**
     * @hibernate.many-to-one column="PARENT_MODEL_DISPATCH_ID"
     * cascade="none"
     * not-null="false"
     */
    public TawwpModelDispatch getTawwpModelDispatch() {
        return tawwpModelDispatch;
    }

    public void setTawwpModelDispatch(TawwpModelDispatch tawwpModelDispatch) {
        this.tawwpModelDispatch = tawwpModelDispatch;
    }

    /**
     * @hibernate.set inverse="true"
     * lazy="true"
     * cascade="save-update"
     * @hibernate.collection-key column="PARENT_MODEL_DISPATCH_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpModelDispatch"
     */
    public Set getChildModelDispatch() {
        return childModelDispatch;
    }

    public void setChildModelDispatch(Set childModelDispatch) {
        this.childModelDispatch = childModelDispatch;
    }

    /**
     * @hibernate.many-to-one column="MODEL_PLAN_ID"
     * cascade="none"
     * not-null="true"
     */
    public TawwpModelPlan getTawwpModelPlan() {
        return tawwpModelPlan;
    }

    public void setTawwpModelPlan(TawwpModelPlan tawwpModelPlan) {
        this.tawwpModelPlan = tawwpModelPlan;
    }

}
