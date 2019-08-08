package com.boco.eoms.km.ask.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:问答总积分
 * </p>
 * <p>
 * Description:问答总积分
 * </p>
 * <p>
 * Wed Aug 19 10:09:58 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmAskCountScore extends BaseObject {

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
     * 用户名
     */
    private java.lang.String userName;

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    public java.lang.String getUserName() {
        return this.userName;
    }

    /**
     * 用户部门
     */
    private java.lang.String userDept;

    public void setUserDept(java.lang.String userDept) {
        this.userDept = userDept;
    }

    public java.lang.String getUserDept() {
        return this.userDept;
    }

    /**
     * 积分总数
     */
    private java.lang.Integer countScore;

    public void setCountScore(java.lang.Integer countScore) {
        this.countScore = countScore;
    }

    public java.lang.Integer getCountScore() {
        return this.countScore;
    }

    /**
     * 用户头衔
     */
    private java.lang.String role;

    public void setRole(java.lang.String role) {
        this.role = role;
    }

    public java.lang.String getRole() {
        return this.role;
    }

    public boolean equals(Object o) {
        if (o instanceof KmAskCountScore) {
            KmAskCountScore kmAskCountScore = (KmAskCountScore) o;
            if (this.id != null || this.id.equals(kmAskCountScore.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}