package com.boco.eoms.km.train.webapp.form;

import java.util.Date;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 15:34:49 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public class TrainRequireForm extends BaseForm implements java.io.Serializable {

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
     * 培训编号
     */
    private String trainNo;

    /**
     * 联系电话
     */
    private String trainTel;

    /**
     * 培训针对的问题
     */
    private String trainQuestion;

    /**
     * 期望的培训内容
     */
    private String trainContent;

    /**
     * 所属部门
     */
    private String trainDept;

    /**
     * 涉及厂家
     */
    private String trainVender;

    /**
     * 涉及专业
     */
    private String trainSpeciality;

    /**
     * 涉及专业
     */
    private String trainType;

    /**
     * 培训提出人
     */
    private String trainUser;

    /**
     * 期望培训时间
     */
    private String trainDate;

    /**
     * 附件名称
     */
    private String trainFile;

    /**
     * 是否删除
     */
    private String isDelete;

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainTel() {
        return trainTel;
    }

    public void setTrainTel(String trainTel) {
        this.trainTel = trainTel;
    }

    public String getTrainQuestion() {
        return trainQuestion;
    }

    public void setTrainQuestion(String trainQuestion) {
        this.trainQuestion = trainQuestion;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    public String getTrainDept() {
        return trainDept;
    }

    public void setTrainDept(String trainDept) {
        this.trainDept = trainDept;
    }

    public String getTrainVender() {
        return trainVender;
    }

    public void setTrainVender(String trainVender) {
        this.trainVender = trainVender;
    }

    public String getTrainSpeciality() {
        return trainSpeciality;
    }

    public void setTrainSpeciality(String trainSpeciality) {
        this.trainSpeciality = trainSpeciality;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainUser() {
        return trainUser;
    }

    public void setTrainUser(String trainUser) {
        this.trainUser = trainUser;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public String getTrainFile() {
        return trainFile;
    }

    public void setTrainFile(String trainFile) {
        this.trainFile = trainFile;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}