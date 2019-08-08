package com.boco.eoms.sheet.netownership.webapp.form;

import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * @author weichao
 */
public class NetOwnershipForm extends BaseForm implements java.io.Serializable {

    /**
     * 主键id
     */
    private java.lang.String id;

    /**
     * 网元id
     */
    private java.lang.String netId;

    /**
     * 网元名称
     */
    private java.lang.String netName;

    /**
     * 网元类型
     */
    private java.lang.String netType;

    /**
     * 地市
     */
    private java.lang.String city;

    /**
     * 区县
     */
    private java.lang.String county;

    /**
     * 入库时间
     */
    private java.lang.String saveTime;

    /**
     * 创建人
     */
    private java.lang.String createUserId;

    /**
     * 创建人所属部门
     */
    private java.lang.String createDeptId;

    /**
     * 创建时间
     */
    private java.lang.String createTime;

    /**
     * 维护班组ID
     */
    private java.lang.String teamRoleId;

    /**
     * 运维中心Id
     */
    private java.lang.String centerId;

    /**
     * 删除标志位 ，0:有效,1:删除
     */
    private java.lang.Integer deleted;


    private String zhuanye;


    public java.lang.String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(java.lang.String saveTime) {
        this.saveTime = saveTime;
    }

    public java.lang.String getCenterId() {
        return centerId;
    }

    public void setCenterId(java.lang.String centerId) {
        this.centerId = centerId;
    }

    public java.lang.String getCity() {
        return city;
    }

    public void setCity(java.lang.String city) {
        this.city = city;
    }

    public java.lang.String getCounty() {
        return county;
    }

    public void setCounty(java.lang.String county) {
        this.county = county;
    }

    public java.lang.String getCreateDeptId() {
        return createDeptId;
    }

    public void setCreateDeptId(java.lang.String createDeptId) {
        this.createDeptId = createDeptId;
    }

    public java.lang.String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(java.lang.String createUserId) {
        this.createUserId = createUserId;
    }

    public java.lang.Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(java.lang.Integer deleted) {
        this.deleted = deleted;
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getNetId() {
        return netId;
    }

    public void setNetId(java.lang.String netId) {
        this.netId = netId;
    }

    public java.lang.String getNetName() {
        return netName;
    }

    public void setNetName(java.lang.String netName) {
        this.netName = netName;
    }

    public java.lang.String getNetType() {
        return netType;
    }

    public void setNetType(java.lang.String netType) {
        this.netType = netType;
    }

    public java.lang.String getTeamRoleId() {
        return teamRoleId;
    }

    public void setTeamRoleId(java.lang.String teamRoleId) {
        this.teamRoleId = teamRoleId;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

}