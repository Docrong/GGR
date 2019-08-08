package com.boco.eoms.km.exam.webapp.form;

import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:题型内容表
 * </p>
 * <p>
 * Description:题型内容表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 */
public class KmExamTestTypeContentForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private FormFile file;

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
     * 主键
     */
    private java.lang.String typeContentID;

    public void setTypeContentID(java.lang.String typeContentID) {
        this.typeContentID = typeContentID;
    }

    public java.lang.String getTypeContentID() {
        return this.typeContentID;
    }

    /**
     * 题型id
     */
    private java.lang.String testTypeID;

    public void setTestTypeID(java.lang.String testTypeID) {
        this.testTypeID = testTypeID;
    }

    public java.lang.String getTestTypeID() {
        return this.testTypeID;
    }

    /**
     * 分值
     */
    private java.lang.String score;

    public void setScore(java.lang.String score) {
        this.score = score;
    }

    public java.lang.String getScore() {
        return this.score;
    }

    /**
     * 问题ID
     */
    private java.lang.String questionID;

    public void setQuestionID(java.lang.String questionID) {
        this.questionID = questionID;
    }

    public java.lang.String getQuestionID() {
        return this.questionID;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }


}