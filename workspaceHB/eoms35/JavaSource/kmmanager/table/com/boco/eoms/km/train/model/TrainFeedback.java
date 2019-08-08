package com.boco.eoms.km.train.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:反馈信息
 * </p>
 * <p>
 * Description:反馈信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainFeedback extends BaseObject {

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
     * 反馈人
     */
    private java.lang.String feedbackUser;

    public void setFeedbackUser(java.lang.String feedbackUser) {
        this.feedbackUser = feedbackUser;
    }

    public java.lang.String getFeedbackUser() {
        return this.feedbackUser;
    }

    /**
     * 所属部门
     */
    private java.lang.String trainFeedbackDept;

    public void setTrainFeedbackDept(java.lang.String trainFeedbackDept) {
        this.trainFeedbackDept = trainFeedbackDept;
    }

    public java.lang.String getTrainFeedbackDept() {
        return this.trainFeedbackDept;
    }

    /**
     * 联系电话
     */
    private java.lang.String trainFeedbackTel;

    public void setTrainFeedbackTel(java.lang.String trainFeedbackTel) {
        this.trainFeedbackTel = trainFeedbackTel;
    }

    public java.lang.String getTrainFeedbackTel() {
        return this.trainFeedbackTel;
    }

    /**
     * 反馈内容
     */
    private java.lang.String trainFeedbackContent;

    public void setTrainFeedbackContent(java.lang.String trainFeedbackContent) {
        this.trainFeedbackContent = trainFeedbackContent;
    }

    public java.lang.String getTrainFeedbackContent() {
        return this.trainFeedbackContent;
    }

    /**
     * 反馈时间
     */
    private java.util.Date trainFeedbackTime;

    public void setTrainFeedbackTime(java.util.Date trainFeedbackTime) {
        this.trainFeedbackTime = trainFeedbackTime;
    }

    public java.util.Date getTrainFeedbackTime() {
        return this.trainFeedbackTime;
    }

    /**
     * 备注
     */
    private java.lang.String trainFeedbackRemark;

    public void setTrainFeedbackRemark(java.lang.String trainFeedbackRemark) {
        this.trainFeedbackRemark = trainFeedbackRemark;
    }

    public java.lang.String getTrainFeedbackRemark() {
        return this.trainFeedbackRemark;
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

    public boolean equals(Object o) {
        if (o instanceof TrainFeedback) {
            TrainFeedback trainFeedback = (TrainFeedback) o;
            if (this.id != null || this.id.equals(trainFeedback.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}