package com.boco.eoms.km.ask.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

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
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public class KmAskCountScoreForm extends BaseForm implements java.io.Serializable {

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
    private java.lang.String countScore;

    public void setCountScore(java.lang.String countScore) {
        this.countScore = countScore;
    }

    public java.lang.String getCountScore() {
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

}