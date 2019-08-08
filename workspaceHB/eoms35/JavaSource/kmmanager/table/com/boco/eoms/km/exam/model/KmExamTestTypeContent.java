package com.boco.eoms.km.exam.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author hsl
 * @version 1.0
 */
public class KmExamTestTypeContent extends BaseObject {


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


    public boolean equals(Object o) {
        if (o instanceof KmExamTestTypeContent) {
            KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent) o;
            if (this.testTypeID != null || this.testTypeID.equals(kmExamTestTypeContent.getTestTypeID())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public java.lang.String getScore() {
        return score;
    }

    public void setScore(java.lang.String score) {
        this.score = score;
    }
}