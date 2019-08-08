package com.boco.eoms.km.lesson.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:考试课程分析
 * </p>
 * <p>
 * Description:考试课程分析
 * </p>
 * <p>
 * Tue Jul 07 09:44:42 CST 2009
 * </p>
 *
 * @author mosquito
 * @version 1.0
 */
public class LessonAnaysis extends BaseObject {

    /**
     * 主键
     */
    private java.lang.String id;

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 试卷id
     */
    private java.lang.String testID;

    public void setTestID(java.lang.String testID) {
        this.testID = testID;
    }

    public java.lang.String getTestID() {
        return this.testID;
    }

    /**
     * 参加考试人
     */
    private java.lang.String attendUser;

    public void setAttendUser(java.lang.String attendUser) {
        this.attendUser = attendUser;
    }

    public java.lang.String getAttendUser() {
        return this.attendUser;
    }

    /**
     * 参加考试人所在部门
     */
    private java.lang.String attendDept;

    public void setAttendDept(java.lang.String attendDept) {
        this.attendDept = attendDept;
    }

    public java.lang.String getAttendDept() {
        return this.attendDept;
    }

    /**
     * 用户进入考试的实际时间
     */
    private java.lang.String attendTime;

    public void setAttendTime(java.lang.String attendTime) {
        this.attendTime = attendTime;
    }

    public java.lang.String getAttendTime() {
        return this.attendTime;
    }

    /**
     * 用户提交试卷的时间
     */
    private java.lang.String attendOverTime;

    public void setAttendOverTime(java.lang.String attendOverTime) {
        this.attendOverTime = attendOverTime;
    }

    public java.lang.String getAttendOverTime() {
        return this.attendOverTime;
    }

    /**
     * 评卷完成后的总得分
     */
    private java.lang.String score;

    public void setScore(java.lang.String score) {
        this.score = score;
    }

    public java.lang.String getScore() {
        return this.score;
    }

    /**
     * 是否已阅卷
     */
    private java.lang.String isRead;

    public void setIsRead(java.lang.String isRead) {
        this.isRead = isRead;
    }

    public java.lang.String getIsRead() {
        return this.isRead;
    }

    /**
     * 是否发布
     */
    private java.lang.String isPublic;

    public void setIsPublic(java.lang.String isPublic) {
        this.isPublic = isPublic;
    }

    public java.lang.String getIsPublic() {
        return this.isPublic;
    }

    /**
     * 阅卷人
     */
    private java.lang.String readUser;

    public void setReadUser(java.lang.String readUser) {
        this.readUser = readUser;
    }

    public java.lang.String getReadUser() {
        return this.readUser;
    }

    /**
     * 阅卷部门
     */
    private java.lang.String readDept;

    public void setReadDept(java.lang.String readDept) {
        this.readDept = readDept;
    }

    public java.lang.String getReadDept() {
        return this.readDept;
    }

    /**
     * 统计参加考试用户总人数
     */
    private java.lang.String totaluser;

    public java.lang.String getTotaluser() {
        return totaluser;
    }

    public void setTotaluser(java.lang.String totaluser) {
        this.totaluser = totaluser;
    }

    /**
     * 平均成绩
     */
    private java.lang.String avgscore;

    public java.lang.String getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(java.lang.String avgscore) {
        this.avgscore = avgscore;
    }

    public boolean equals(Object o) {
        if (o instanceof LessonAnaysis) {
            LessonAnaysis lessonAnaysis = (LessonAnaysis) o;
            if (this.id != null || this.id.equals(lessonAnaysis.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}