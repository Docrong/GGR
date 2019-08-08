package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识申请人本期（周、月、季、年）申请知识情况统计表
 * </p>
 * <p>
 * Description:知识申请人本期（周、月、季、年）申请知识情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class PersonalApplyStatistic extends BaseObject {

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
     * 用户姓名
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
     * 申请知识数
     */
    private java.lang.Integer applyCount;

    public java.lang.Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(java.lang.Integer applyCount) {
        this.applyCount = applyCount;
    }

    public boolean equals(Object o) {
        if (o instanceof PersonalApplyStatistic) {
            PersonalApplyStatistic personalUseStatistic = (PersonalApplyStatistic) o;
            if (this.id != null || this.id.equals(personalUseStatistic.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}