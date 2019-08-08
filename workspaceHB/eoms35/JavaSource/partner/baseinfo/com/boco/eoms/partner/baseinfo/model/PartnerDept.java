package com.boco.eoms.partner.baseinfo.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:����
 * </p>
 * <p>
 * Description:����
 * </p>
 * <p>
 * Mon Feb 09 10:57:12 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public class PartnerDept extends BaseObject {

    /**
     * 主键���
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 合作伙伴名称������
     */
    private java.lang.String name;

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 合作伙伴企业性质��������
     */
    private java.lang.String type;

    public void setType(java.lang.String type) {
        this.type = type;
    }

    public java.lang.String getType() {
        return this.type;
    }

    /**
     * 合作伙伴资质����
     */
    private java.lang.String aptitude;

    public void setAptitude(java.lang.String aptitude) {
        this.aptitude = aptitude;
    }

    public java.lang.String getAptitude() {
        return this.aptitude;
    }

    /**
     * 资质有效期
     */
    private java.lang.String deadline;

    public void setDeadline(java.lang.String deadline) {
        this.deadline = deadline;
    }

    public java.lang.String getDeadline() {
        return this.deadline;
    }

    /**
     * 合作伙伴法人������Ա
     */
    private java.lang.String manager;

    public void setManager(java.lang.String manager) {
        this.manager = manager;
    }

    public java.lang.String getManager() {
        return this.manager;
    }

    /**
     * 区域ID
     */
    private java.lang.String areaId;

    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }

    public java.lang.String getAreaId() {
        return this.areaId;
    }

    /**
     * 公司地址������
     */
    private java.lang.String areaName;

    public void setAreaName(java.lang.String areaName) {
        this.areaName = areaName;
    }

    public java.lang.String getAreaName() {
        return this.areaName;
    }

    /**
     * 注册资金（万）���
     */
    private java.lang.String fund;

    public void setFund(java.lang.String fund) {
        this.fund = fund;
    }

    public java.lang.String getFund() {
        return this.fund;
    }

    /**
     * �联系电话
     */
    private java.lang.String phone;

    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    public java.lang.String getPhone() {
        return this.phone;
    }

    /**
     * 公司地址
     */
    private java.lang.String address;

    public void setAddress(java.lang.String address) {
        this.address = address;
    }

    public java.lang.String getAddress() {
        return this.address;
    }

    /**
     * 传真号码����
     */
    private java.lang.String fax;

    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }

    public java.lang.String getFax() {
        return this.fax;
    }

    /**
     * 开户银行�ʻ�
     */
    private String bank;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 银行帐号�ʻ�
     */
    private java.lang.String account;

    public void setAccount(java.lang.String account) {
        this.account = account;
    }

    public java.lang.String getAccount() {
        return this.account;
    }

    /**
     * 第三方服务合同信息
     */
    private java.lang.String third;

    public void setThird(java.lang.String third) {
        this.third = third;
    }

    public java.lang.String getThird() {
        return this.third;
    }

    /**
     * ���
     */
    private java.lang.String accessory;

    public void setAccessory(java.lang.String accessory) {
        this.accessory = accessory;
    }

    public java.lang.String getAccessory() {
        return this.accessory;
    }

    /**
     * ��ע
     */
    private java.lang.String remark;

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 对应树节点id
     */
    private String treeId;

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    /**
     * 对应树节点nodeId
     */
    private String treeNodeId;

    public String getTreeNodeId() {
        return treeNodeId;
    }

    public void setTreeNodeId(String treeNodeId) {
        this.treeNodeId = treeNodeId;
    }

    public boolean equals(Object o) {
        if (o instanceof PartnerDept) {
            PartnerDept partnerDept = (PartnerDept) o;
            if (this.id != null || this.id.equals(partnerDept.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}