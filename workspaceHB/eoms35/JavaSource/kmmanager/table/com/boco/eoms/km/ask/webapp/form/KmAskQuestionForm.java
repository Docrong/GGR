package com.boco.eoms.km.ask.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

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
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public class KmAskQuestionForm extends BaseForm implements java.io.Serializable {

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
    private java.lang.String answerCount;

    public void setAnswerCount(java.lang.String answerCount) {
        this.answerCount = answerCount;
    }

    public java.lang.String getAnswerCount() {
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
    private java.lang.String askTime;

    public void setAskTime(java.lang.String askTime) {
        this.askTime = askTime;
    }

    public java.lang.String getAskTime() {
        return this.askTime;
    }

    /**
     * 悬赏分
     */
    private java.lang.String score;

    public void setScore(java.lang.String score) {
        this.score = score;
    }

    public java.lang.String getScore() {
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

}