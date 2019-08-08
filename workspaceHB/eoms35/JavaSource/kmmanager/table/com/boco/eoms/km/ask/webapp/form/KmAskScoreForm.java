package com.boco.eoms.km.ask.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:问答积分配置
 * </p>
 * <p>
 * Description:问答积分配置
 * </p>
 * <p>
 * Wed Aug 19 16:41:06 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public class KmAskScoreForm extends BaseForm implements java.io.Serializable {

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
     * 操作名字
     */
    private java.lang.String operateName;

    public void setOperateName(java.lang.String operateName) {
        this.operateName = operateName;
    }

    public java.lang.String getOperateName() {
        return this.operateName;
    }

    /**
     * 分数
     */
    private java.lang.Integer score;

    public void setScore(java.lang.Integer score) {
        this.score = score;
    }

    public java.lang.Integer getScore() {
        return this.score;
    }

    /**
     * 操作id
     */
    private java.lang.Integer operateId;

    public void setOperateId(java.lang.Integer operateId) {
        this.operateId = operateId;
    }

    public java.lang.Integer getOperateId() {
        return this.operateId;
    }

}