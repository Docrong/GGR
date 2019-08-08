package com.boco.eoms.km.score.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:积分权重
 * </p>
 * <p>
 * Description:积分权重表
 * </p>
 * <p>
 * Fri Aug 21 09:06:28 CST 2009
 * </p>
 *
 * @moudle.getAuthor() me
 * @moudle.getVersion() 1.0
 */
public class KmScoreWeightForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 锟斤拷锟�
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 功能名称
     */
    private java.lang.String powerName;

    public void setPowerName(java.lang.String powerName) {
        this.powerName = powerName;
    }

    public java.lang.String getPowerName() {
        return this.powerName;
    }

    /**
     * 动作名称
     */
    private java.lang.String actionName;

    public void setActionName(java.lang.String actionName) {
        this.actionName = actionName;
    }

    public java.lang.String getActionName() {
        return this.actionName;
    }

    /**
     * 动作权重
     */
    private java.lang.Integer actionWeight;

    public void setActionWeight(java.lang.Integer actionWeight) {
        this.actionWeight = actionWeight;
    }

    public java.lang.Integer getActionWeight() {
        return this.actionWeight;
    }

    /**
     * 功能权重
     */
    private java.lang.Integer powerWeight;

    public void setPowerWeight(java.lang.Integer powerWeight) {
        this.powerWeight = powerWeight;
    }

    public java.lang.Integer getPowerWeight() {
        return this.powerWeight;
    }

    /**
     * 是否删除
     */
    private java.lang.Integer isDeleted;

    public void setIsDeleted(java.lang.Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public java.lang.Integer getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 地域信息
     */
    private java.lang.String area;

    public void setArea(java.lang.String area) {
        this.area = area;
    }

    public java.lang.String getArea() {
        return this.area;
    }

    /**
     * 备注
     */
    private java.lang.String remark;

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 节点
     */
    private java.lang.String nodeId;

    public void setNodeId(java.lang.String nodeId) {
        this.nodeId = nodeId;
    }

    public java.lang.String getNodeId() {
        return this.nodeId;
    }


}