package com.boco.eoms.km.ask.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:问题
 * </p>
 * <p>
 * Description:问题
 * </p>
 * <p>
 * Tue Aug 04 15:17:03 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmAskQuestion extends BaseObject {

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
     * 问题名字
     */
    private java.lang.String name;

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 问题内容
     */
    private java.lang.String question;

    public void setQuestion(java.lang.String question) {
        this.question = question;
    }

    public java.lang.String getQuestion() {
        return this.question;
    }

    /**
     * 问题类型
     */
    private java.lang.String speciality;

    public void setSpeciality(java.lang.String speciality) {
        this.speciality = speciality;
    }

    public java.lang.String getSpeciality() {
        return this.speciality;
    }

    /**
     * 回答总数
     */
    private java.lang.Integer answerCount;

    public void setAnswerCount(java.lang.Integer answerCount) {
        this.answerCount = answerCount;
    }

    public java.lang.Integer getAnswerCount() {
        return this.answerCount;
    }

    /**
     * 问题状态
     */
    private java.lang.String questionStatus;

    public void setQuestionStatus(java.lang.String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public java.lang.String getQuestionStatus() {
        return this.questionStatus;
    }

    /**
     * 提问人
     */
    private java.lang.String askUser;

    public void setAskUser(java.lang.String askUser) {
        this.askUser = askUser;
    }

    public java.lang.String getAskUser() {
        return this.askUser;
    }

    /**
     * 提问部门
     */
    private java.lang.String askDept;

    public void setAskDept(java.lang.String askDept) {
        this.askDept = askDept;
    }

    public java.lang.String getAskDept() {
        return this.askDept;
    }

    /**
     * 提问时间
     */
    private java.util.Date askTime;

    public void setAskTime(java.util.Date askTime) {
        this.askTime = askTime;
    }

    public java.util.Date getAskTime() {
        return this.askTime;
    }

    /**
     * 悬赏分
     */
    private java.lang.Integer score;

    public void setScore(java.lang.Integer score) {
        this.score = score;
    }

    public java.lang.Integer getScore() {
        return this.score;
    }

    /**
     * 问题补充
     */
    private java.lang.String questionSupply;

    public void setQuestionSupply(java.lang.String questionSupply) {
        this.questionSupply = questionSupply;
    }

    public java.lang.String getQuestionSupply() {
        return this.questionSupply;
    }

    /**
     * 有效期
     */
    private java.lang.String userfulTime;

    public void setUserfulTime(java.lang.String userfulTime) {
        this.userfulTime = userfulTime;
    }

    public java.lang.String getUserfulTime() {
        return this.userfulTime;
    }

    /**
     * 是否提高悬赏
     */
    private java.lang.String isAddScore;

    public void setIsAddScore(java.lang.String isAddScore) {
        this.isAddScore = isAddScore;
    }

    public java.lang.String getIsAddScore() {
        return this.isAddScore;
    }

    /**
     * 是否关闭
     */
    private java.lang.String isClosed;

    public void setIsClosed(java.lang.String isClosed) {
        this.isClosed = isClosed;
    }

    public java.lang.String getIsClosed() {
        return this.isClosed;
    }

    /**
     * 是否匿名
     */
    private java.lang.String isAnonymity;

    public void setIsAnonymity(java.lang.String isAnonymity) {
        this.isAnonymity = isAnonymity;
    }

    public java.lang.String getIsAnonymity() {
        return this.isAnonymity;
    }

    public boolean equals(Object o) {
        if (o instanceof KmAskQuestion) {
            KmAskQuestion kmAskQuestion = (KmAskQuestion) o;
            if (this.id != null || this.id.equals(kmAskQuestion.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}