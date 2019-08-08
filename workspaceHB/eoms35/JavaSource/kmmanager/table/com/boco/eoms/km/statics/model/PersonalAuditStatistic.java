package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p> Title:知识审核情况统计 </p>
 * <p> Description:知识审核情况统计 </p>
 * <p> Mon Mar 30 14:39:15 CST 2009 </p>
 *
 * @author zhangxiaobo
 * @version 0.1
 */

public class PersonalAuditStatistic extends BaseObject {

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
     * 审核知识次数
     */
    private java.lang.Integer auditCount;

    public void setAuditCount(java.lang.Integer auditCount) {
        this.auditCount = auditCount;
    }

    public java.lang.Integer getAuditCount() {
        int ok = StaticMethod.nullObject2int(auditOkCount);
        int back = StaticMethod.nullObject2int(auditBackCount);
        auditCount = new Integer(ok + back);
        return this.auditCount;
    }

    /**
     * 知识审核通过次数
     */
    private java.lang.Integer auditOkCount;

    public void setAuditOkCount(java.lang.Integer auditOkCount) {
        this.auditOkCount = auditOkCount;
    }

    public java.lang.Integer getAuditOkCount() {
        return this.auditOkCount;
    }

    /**
     * 知识审核驳回次数
     */
    private java.lang.Integer auditBackCount;

    public void setAuditBackCount(java.lang.Integer auditBackCount) {
        this.auditBackCount = auditBackCount;
    }

    public java.lang.Integer getAuditBackCount() {
        return this.auditBackCount;
    }

    /**
     * 知识审核通过率
     */
    private java.lang.String auditOkRatio;

    public String getAuditOkRatio() {
        if (auditCount != null && auditOkCount != null && auditCount.intValue() != 0 && auditOkCount.intValue() != 0) {
            auditOkRatio = (new java.text.DecimalFormat("0.0").format(this.auditOkCount.intValue() * 100.0 / this.auditCount.intValue())).toString() + "%";
        } else {
            auditOkRatio = "0%";
        }

        return this.auditOkRatio;
    }

    public boolean equals(Object o) {
        if (o instanceof PersonalAuditStatistic) {
            PersonalAuditStatistic personalAuditStatistic = (PersonalAuditStatistic) o;
            if (this.id != null || this.id.equals(personalAuditStatistic.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}