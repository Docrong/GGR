package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:操作积分定义表
 * </p>
 * <p>
 * Description:操作积分定义表
 * </p>
 * <p>
 * Thu Mar 26 15:57:40 CST 2009
 * </p>
 *
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 */
public class KmOperateScoreForm extends BaseForm implements java.io.Serializable {

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
     * 操作名称
     */
    private java.lang.String operateName;

    public void setOperateName(java.lang.String operateName) {
        this.operateName = operateName;
    }

    public java.lang.String getOperateName() {
        return this.operateName;
    }

    /**
     * 操作积分
     */
    private java.lang.Long score;

    public void setScore(java.lang.Long score) {
        this.score = score;
    }

    public java.lang.Long getScore() {
        return this.score;
    }

    /**
     * 操作Id
     */
    private java.lang.Long operateId;

    public void setOperateId(java.lang.Long operateId) {
        this.operateId = operateId;
    }

    public java.lang.Long getOperateId() {
        return this.operateId;
    }

}