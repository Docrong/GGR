package com.boco.eoms.km.lesson.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:课程创建
 * </p>
 * <p>
 * Description:课程创建
 * </p>
 * <p>
 * Wed Jul 01 15:12:52 CST 2009
 * </p>
 *
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 */
public class KmLessonForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private java.lang.String id;

    /**
     * 创建者
     */
    private java.lang.String createUser;

    /**
     * 创建时间
     */
    private java.lang.String createTime;

    /**
     * 课题名称
     */
    private java.lang.String lessonName;

    /**
     * 主题
     */
    private java.lang.String lessonTheme;

    /**
     * 业务类别
     */
    private java.lang.String lessonClass;

    /**
     * 课题内容
     */
    private java.lang.String lessonContents;

    /**
     * 开始时间
     */
    private java.lang.String startTime;

    /**
     * 结束时间
     */
    private java.lang.String endTime;

    /**
     * 时长
     */
    private java.lang.String timeLength;

    /**
     * 课件
     */
    private java.lang.String attachment;

    /**
     * 是否删除
     */
    private java.lang.String isDelete;

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return this.id;
    }

    public java.lang.String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }

    public java.lang.String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getLessonName() {
        return lessonName;
    }

    public void setLessonName(java.lang.String lessonName) {
        this.lessonName = lessonName;
    }

    public java.lang.String getLessonTheme() {
        return lessonTheme;
    }

    public void setLessonTheme(java.lang.String lessonTheme) {
        this.lessonTheme = lessonTheme;
    }

    public java.lang.String getLessonClass() {
        return lessonClass;
    }

    public void setLessonClass(java.lang.String lessonClass) {
        this.lessonClass = lessonClass;
    }

    public java.lang.String getLessonContents() {
        return lessonContents;
    }

    public void setLessonContents(java.lang.String lessonContents) {
        this.lessonContents = lessonContents;
    }

    public java.lang.String getStartTime() {
        return startTime;
    }

    public void setStartTime(java.lang.String startTime) {
        this.startTime = startTime;
    }

    public java.lang.String getEndTime() {
        return endTime;
    }

    public void setEndTime(java.lang.String endTime) {
        this.endTime = endTime;
    }

    public java.lang.String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(java.lang.String timeLength) {
        this.timeLength = timeLength;
    }

    public java.lang.String getAttachment() {
        return attachment;
    }

    public void setAttachment(java.lang.String attachment) {
        this.attachment = attachment;
    }

    public java.lang.String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(java.lang.String isDelete) {
        this.isDelete = isDelete;
    }

}