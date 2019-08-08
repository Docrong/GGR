package com.boco.eoms.km.exam.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:考试信息
 * </p>
 * <p>
 * Description:考试信息
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public class KmExamAttendForm extends BaseForm implements java.io.Serializable {

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
     * 试卷id
     */
    private java.lang.String testId;

    public void setTestId(java.lang.String testId) {
        this.testId = testId;
    }

    public java.lang.String getTestId() {
        return this.testId;
    }

    /**
     * 参加人员
     */
    private java.lang.String attendUser;

    public void setAttendUser(java.lang.String attendUser) {
        this.attendUser = attendUser;
    }

    public java.lang.String getAttendUser() {
        return this.attendUser;
    }

    /**
     * 所属部门
     */
    private java.lang.String attendDept;

    public void setAttendDept(java.lang.String attendDept) {
        this.attendDept = attendDept;
    }

    public java.lang.String getAttendDept() {
        return this.attendDept;
    }

    /**
     * 考试时间
     */
    private java.lang.String attendTime;

    public void setAttendTime(java.lang.String attendTime) {
        this.attendTime = attendTime;
    }

    public java.lang.String getAttendTime() {
        return this.attendTime;
    }

    /**
     * 实际结束时间
     */
    private java.lang.String attendOverTime;

    public void setAttendOverTime(java.lang.String attendOverTime) {
        this.attendOverTime = attendOverTime;
    }

    public java.lang.String getAttendOverTime() {
        return this.attendOverTime;
    }

    /**
     * 成绩
     */
    private java.lang.String score;

    public void setScore(java.lang.String score) {
        this.score = score;
    }

    public java.lang.String getScore() {
        return this.score;
    }

    /**
     * 是否阅卷
     */
    private java.lang.Integer isRead;

    public void setIsRead(java.lang.Integer isRead) {
        this.isRead = isRead;
    }

    public java.lang.Integer getIsRead() {
        return this.isRead;
    }

}