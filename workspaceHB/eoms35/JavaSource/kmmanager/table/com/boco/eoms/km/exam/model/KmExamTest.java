package com.boco.eoms.km.exam.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:试卷
 * </p>
 * <p>
 * Description:试卷
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmExamTest extends BaseObject {

    /**
     * 主键
     */
    private java.lang.String testID;

    public void setTestID(java.lang.String testID) {
        this.testID = testID;
    }

    public java.lang.String getTestID() {
        return this.testID;
    }

    /**
     * 试卷名称
     */
    private java.lang.String testName;

    public void setTestName(java.lang.String testName) {
        this.testName = testName;
    }

    public java.lang.String getTestName() {
        return this.testName;
    }

    /**
     * 试卷说明
     */
    private java.lang.String testDescription;

    public void setTestDescription(java.lang.String testDescription) {
        this.testDescription = testDescription;
    }

    public java.lang.String getTestDescription() {
        return this.testDescription;
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
     * 考试开始时间
     */
    private java.util.Date testBeginTime;

    public void setTestBeginTime(java.util.Date testBeginTime) {
        this.testBeginTime = testBeginTime;
    }

    public java.util.Date getTestBeginTime() {
        return this.testBeginTime;
    }

    /**
     * 考试结束时间
     */
    private java.util.Date testEndTime;

    public void setTestEndTime(java.util.Date testEndTime) {
        this.testEndTime = testEndTime;
    }

    public java.util.Date getTestEndTime() {
        return this.testEndTime;
    }

    /**
     * 考试总时间
     */
    private java.lang.String testDuration;

    public void setTestDuration(java.lang.String testDuration) {
        this.testDuration = testDuration;
    }

    public java.lang.String getTestDuration() {
        return this.testDuration;
    }

    /**
     * 总分数
     */
    private java.lang.Integer totalScore;

    public void setTotalScore(java.lang.Integer totalScore) {
        this.totalScore = totalScore;
    }

    public java.lang.Integer getTotalScore() {
        return this.totalScore;
    }

    /**
     * 是否删除
     */
    private java.lang.String isDeleted;

    public void setIsDeleted(java.lang.String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public java.lang.String getIsDeleted() {
        return this.isDeleted;
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
     * 所属部门
     */
    private java.lang.String DeptID;

    public void setDeptID(java.lang.String DeptID) {
        this.DeptID = DeptID;
    }

    public java.lang.String getDeptID() {
        return this.DeptID;
    }

    /**
     * 成绩是否已发布
     */
    private java.lang.String isPublic;

    public void setIsPublic(java.lang.String isPublic) {
        this.isPublic = isPublic;
    }

    public java.lang.String getIsPublic() {
        return this.isPublic;
    }

    /**
     * 是否允许超时
     */
    private java.lang.String isPermittedOvertime;

    public void setIsPermittedOvertime(java.lang.String isPermittedOvertime) {
        this.isPermittedOvertime = isPermittedOvertime;
    }

    public java.lang.String getIsPermittedOvertime() {
        return this.isPermittedOvertime;
    }


    public boolean equals(Object o) {
        if (o instanceof KmExamTest) {
            KmExamTest kmExamTest = (KmExamTest) o;
            if (this.specialtyID != null || this.specialtyID.equals(kmExamTest.getSpecialtyID())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}