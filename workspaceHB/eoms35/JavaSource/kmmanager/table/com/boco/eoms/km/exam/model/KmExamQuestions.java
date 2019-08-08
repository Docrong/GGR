package com.boco.eoms.km.exam.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:题库管理
 * </p>
 * <p>
 * Description:题库管理
 * </p>
 * <p>
 * Fri May 08 16:40:11 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmExamQuestions extends BaseObject {


    /**
     * 主键
     */
    private java.lang.String questionID;

    public void setQuestionID(java.lang.String questionID) {
        this.questionID = questionID;
    }

    public java.lang.String getQuestionID() {
        return this.questionID;
    }

    /**
     * 创建人
     */
    private java.lang.String createUser;

    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }

    public java.lang.String getCreateUser() {
        return this.createUser;
    }

    /**
     * 创建时间
     */
    private java.util.Date createTime;

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建部门
     */
    private java.lang.String createDept;

    public void setCreateDept(java.lang.String createDept) {
        this.createDept = createDept;
    }

    public java.lang.String getCreateDept() {
        return this.createDept;
    }

    /**
     * 所属专业
     */
    private java.lang.String specialtyID;

    public void setSpecialtyID(java.lang.String specialtyID) {
        this.specialtyID = specialtyID;
    }

    public java.lang.String getSpecialtyID() {
        return this.specialtyID;
    }

    /**
     * 问题名称
     */
    private java.lang.String questionName;

    public void setQuestionName(java.lang.String questionName) {
        this.questionName = questionName;
    }

    public java.lang.String getQuestionName() {
        return this.questionName;
    }

    /**
     * 题干
     */
    private java.lang.String question;

    public void setQuestion(java.lang.String question) {
        this.question = question;
    }

    public java.lang.String getQuestion() {
        return this.question;
    }

    /**
     * 答案
     */
    private java.lang.String answer;

    public void setAnswer(java.lang.String answer) {
        this.answer = answer;
    }

    public java.lang.String getAnswer() {
        return this.answer;
    }

    /**
     * 问题类型
     */
    private java.lang.String questionType;

    public void setQuestionType(java.lang.String questionType) {
        this.questionType = questionType;
    }

    public java.lang.String getQuestionType() {
        return this.questionType;
    }

    /**
     * 问题难度
     */
    private java.lang.String difficulty;

    public void setDifficulty(java.lang.String difficulty) {
        this.difficulty = difficulty;
    }

    public java.lang.String getDifficulty() {
        return this.difficulty;
    }

    /**
     * 是否已删除
     */
    private java.lang.String isDeleted;

    public void setIsDeleted(java.lang.String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public java.lang.String getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 所属部门
     */
    private java.lang.String deptId;

    public void setDeptId(java.lang.String deptId) {
        this.deptId = deptId;
    }

    public java.lang.String getDeptId() {
        return this.deptId;
    }

    /**
     * 附件
     */
    private java.lang.String accessory;

    public java.lang.String getAccessory() {
        return accessory;
    }

    public void setAccessory(java.lang.String accessory) {
        this.accessory = accessory;
    }


    public boolean equals(Object o) {
        if (o instanceof KmExamQuestions) {
            KmExamQuestions kmExamQuestions = (KmExamQuestions) o;
            if (this.questionID != null || this.questionID.equals(kmExamQuestions.getQuestionID())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


}