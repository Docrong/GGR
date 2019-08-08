package com.boco.eoms.km.ask.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author lvweihua
 * @version 1.0
 */
public class KmAskScore extends BaseObject {

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

    public boolean equals(Object o) {
        if (o instanceof KmAskScore) {
            KmAskScore kmAskScore = (KmAskScore) o;
            if (this.id != null || this.id.equals(kmAskScore.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}