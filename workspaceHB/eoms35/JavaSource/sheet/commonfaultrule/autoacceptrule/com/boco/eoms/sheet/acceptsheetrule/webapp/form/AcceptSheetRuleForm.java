package com.boco.eoms.sheet.acceptsheetrule.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:自动接单规则配置
 * </p>
 * <p>
 * Description:自动接单规则配置
 * </p>
 * <p>
 * Wed Apr 22 09:19:35 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 史闯科
 * @moudle.getVersion() 3.5
 */
public class AcceptSheetRuleForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 锟斤拷锟�
     */
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 地域
     */
    protected java.lang.String faultArea;

    public void setFaultArea(java.lang.String faultArea) {
        this.faultArea = faultArea;
    }

    public java.lang.String getFaultArea() {
        return this.faultArea;
    }

    /**
     * 网络分类（一级）
     */
    protected java.lang.String netSortOne;

    public void setNetSortOne(java.lang.String netSortOne) {
        this.netSortOne = netSortOne;
    }

    public java.lang.String getNetSortOne() {
        return this.netSortOne;
    }

    /**
     * 网络分类（二级）
     */
    protected java.lang.String netSortTwo;

    public void setNetSortTwo(java.lang.String netSortTwo) {
        this.netSortTwo = netSortTwo;
    }

    public java.lang.String getNetSortTwo() {
        return this.netSortTwo;
    }

    /**
     * 网络分类（三级）
     */
    protected java.lang.String netSortThree;

    public void setNetSortThree(java.lang.String netSortThree) {
        this.netSortThree = netSortThree;
    }

    public java.lang.String getNetSortThree() {
        return this.netSortThree;
    }

    /**
     * 设备厂家
     */
    protected java.lang.String equipmentFactory;

    public void setEquipmentFactory(java.lang.String equipmentFactory) {
        this.equipmentFactory = equipmentFactory;
    }

    public java.lang.String getEquipmentFactory() {
        return this.equipmentFactory;
    }

    /**
     * 所选人员
     */
    protected java.lang.String dealHuman;

    public void setDealHuman(java.lang.String dealHuman) {
        this.dealHuman = dealHuman;
    }

    public java.lang.String getDealHuman() {
        return this.dealHuman;
    }

    protected java.lang.String deleted;

    public java.lang.String getDeleted() {
        return deleted;
    }

    public void setDeleted(java.lang.String deleted) {
        this.deleted = deleted;
    }

    /**
     * 人员所属角色
     */
    protected java.lang.String subRole;

    public java.lang.String getSubRole() {
        return subRole;
    }

    public void setSubRole(java.lang.String subRole) {
        this.subRole = subRole;
    }
}