package com.boco.eoms.km.train.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:报名信息
 * </p>
 * <p>
 * Description:报名信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public class TrainEnterForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 报名人
     */
    private java.lang.String enterName;

    public void setEnterName(java.lang.String enterName) {
        this.enterName = enterName;
    }

    public java.lang.String getEnterName() {
        return this.enterName;
    }

    /**
     * 所属部门
     */
    private java.lang.String trainEnterDept;

    public void setTrainEnterDept(java.lang.String trainEnterDept) {
        this.trainEnterDept = trainEnterDept;
    }

    public java.lang.String getTrainEnterDept() {
        return this.trainEnterDept;
    }

    /**
     * 联系电话
     */
    private java.lang.String trainEnterTel;

    public void setTrainEnterTel(java.lang.String trainEnterTel) {
        this.trainEnterTel = trainEnterTel;
    }

    public java.lang.String getTrainEnterTel() {
        return this.trainEnterTel;
    }

    /**
     * 报名时间
     */
    private java.lang.String trainEnterTime;

    public void setTrainEnterTime(String trainEnterTime) {
        this.trainEnterTime = trainEnterTime;
    }

    public String getTrainEnterTime() {
        return this.trainEnterTime;
    }

    /**
     * 备注
     */
    private java.lang.String trainEnterRemark;

    public void setTrainEnterRemark(java.lang.String trainEnterRemark) {
        this.trainEnterRemark = trainEnterRemark;
    }

    public java.lang.String getTrainEnterRemark() {
        return this.trainEnterRemark;
    }

    /**
     * 培训计划id
     */
    private java.lang.String trainPlanId;

    public void setTrainPlanId(java.lang.String trainPlanId) {
        this.trainPlanId = trainPlanId;
    }

    public java.lang.String getTrainPlanId() {
        return this.trainPlanId;
    }

}