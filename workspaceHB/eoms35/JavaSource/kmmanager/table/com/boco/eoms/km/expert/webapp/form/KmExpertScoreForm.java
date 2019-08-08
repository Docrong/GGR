package com.boco.eoms.km.expert.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;


/**
 * <p>
 * Title:知识库专家积分
 * </p>
 * <p>
 * Description:知识库专家积分
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 *
 * @author daizhigang
 * @version 1.0
 */
public class KmExpertScoreForm extends BaseForm implements java.io.Serializable {

    /**
     *
     */
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
     * 创建时间
     */
    private java.lang.String createTime;

    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getCreateTime() {
        return this.createTime;
    }

    /**
     * 创建人记录类型（day-日记录；sum-汇总）
     */
    private String typeFlag;

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    public String getTypeFlag() {
        return this.typeFlag;
    }

    /**
     * 专家ID
     */
    private String expertUserId;

    public void setExpertUserId(String expertUserId) {
        this.expertUserId = expertUserId;
    }

    public String getExpertUserId() {
        return this.expertUserId;
    }

    /**
     * 课题数
     */
    private java.lang.String problemNum;

    public void setProblemNum(java.lang.String problemNum) {
        this.problemNum = problemNum;
    }

    public java.lang.String getProblemNum() {
        return this.problemNum;
    }

    /**
     * 回答次数
     */
    private String answerNum;

    public void setAnswerNum(String answerNum) {
        this.answerNum = answerNum;
    }

    public String getAnswerNum() {
        return this.answerNum;
    }

    /**
     * 回答满意等级（累计值）(不满意-0;一般-1;满意-2;非常满意-3)
     */
    private String answerSati;

    public void setAnswerSati(String answerSati) {
        this.answerSati = answerSati;
    }

    public String getAnswerSati() {
        return this.answerSati;
    }

    /**
     * 处理CASE数
     */
    private String caseNum;

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public String getCaseNum() {
        return this.caseNum;
    }

    /**
     * 专家基础分
     */
    private String baseScore;

    public void setBaseScore(String baseScore) {
        this.baseScore = baseScore;
    }

    public String getBaseScore() {
        return this.baseScore;
    }

    /**
     * 知识贡献分
     */
    private String knowledgeScore;

    public void setKnowledgeScore(String knowledgeScore) {
        this.knowledgeScore = knowledgeScore;
    }

    public String getKnowledgeScore() {
        return this.knowledgeScore;
    }

    /**
     * 专家坐诊分
     */
    private String answerScore;

    public void setAnswerScore(String answerScore) {
        this.answerScore = answerScore;
    }

    public String getAnswerScore() {
        return this.answerScore;
    }

    /**
     * 专家主观积分
     */
    private String inputScore;

    public void setInputScore(String inputScore) {
        this.inputScore = inputScore;
    }

    public String getInputScore() {
        return this.inputScore;
    }


    public boolean equals(Object o) {
        if (o instanceof KmExpertScoreForm) {
            KmExpertScoreForm kmExpertScoreForm = (KmExpertScoreForm) o;
            if (this.id != null || this.id.equals(kmExpertScoreForm.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}