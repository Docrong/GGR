package com.boco.eoms.km.lesson.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:课程创建
 * </p>
 * <p>
 * Description:课程创建
 * </p>
 * <p>
 * Wed Jul 01 15:12:51 CST 2009
 * </p>
 *
 * @author zhangxb
 * @version 1.0
 */
public class KmLesson extends BaseObject {

    /**
     *
     */
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
    private java.util.Date startTime;

    /**
     * 结束时间
     */
    private java.util.Date endTime;

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

    public boolean equals(Object o) {
        if (o instanceof KmLesson) {
            KmLesson kmLesson = (KmLesson) o;
            if (this.id != null || this.id.equals(kmLesson.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

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

    public java.lang.String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(java.lang.String isDelete) {
        this.isDelete = isDelete;
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

    public java.util.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
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


}