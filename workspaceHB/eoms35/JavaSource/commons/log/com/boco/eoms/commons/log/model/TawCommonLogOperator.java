package com.boco.eoms.commons.log.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="TawCommonLogOperator.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_logoperator"
 */
public class TawCommonLogOperator extends BaseObject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


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

    private String id;

    private String userid;

    private String username;

    private String deptid;

    private String deptname;

    private String operid;

    private String opername;

    private String areaid;

    private String areaname;

    private String modelid;

    private String modelname;

    private String operremark;

    private String remoteip;

    private String issucess;

    private String beginnotetime;

    private String notemessage;

    private String currentday;

    private String currentmonth;

    private String operatetime;

    private String bzremark;

    private String url;

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="业务触发时间"
     */
    public String getBeginnotetime() {
        return beginnotetime;
    }

    public void setBeginnotetime(String beginnotetime) {
        this.beginnotetime = beginnotetime;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="业务描述"
     */
    public String getBzremark() {
        return bzremark;
    }

    public void setBzremark(String bzremark) {
        this.bzremark = bzremark;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="操作所在的天"
     */
    public String getCurrentday() {
        return currentday;
    }

    public void setCurrentday(String currentday) {
        this.currentday = currentday;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="操作所在的月"
     */
    public String getCurrentmonth() {
        return currentmonth;
    }

    public void setCurrentmonth(String currentmonth) {
        this.currentmonth = currentmonth;
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
     * @hibernate.property length="2"
     * @eoms.show
     * @eoms.cn name="是否成功"
     */
    public String getIssucess() {
        return issucess;
    }

    public void setIssucess(String issucess) {
        this.issucess = issucess;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="操作所属模块ID"
     */
    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="操作所在模块NAME"
     */
    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="操作信息记录"
     */
    public String getNotemessage() {
        return notemessage;
    }

    public void setNotemessage(String notemessage) {
        this.notemessage = notemessage;
    }

    /**
     * @return
     * @hibernate.property length="20"
     * @eoms.show
     * @eoms.cn name="操作发生的具体时间"
     */
    public String getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(String operatetime) {
        this.operatetime = operatetime;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="操作的业务ID"
     */
    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="操作业务名称ACTIONNAME"
     */
    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="操作的描述"
     */
    public String getOperremark() {
        return operremark;
    }

    public void setOperremark(String operremark) {
        this.operremark = operremark;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="操作的IP地址"
     */
    public String getRemoteip() {
        return remoteip;
    }

    public void setRemoteip(String remoteip) {
        this.remoteip = remoteip;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="操作执行的路径"
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="操作者ID"
     */
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }
}
