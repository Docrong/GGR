package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

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
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 */
public class PersonalApplyStatisticForm extends BaseForm implements java.io.Serializable {

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
    private java.lang.Long applyCount;

    public java.lang.Long getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(java.lang.Long applyCount) {
        this.applyCount = applyCount;
    }


}