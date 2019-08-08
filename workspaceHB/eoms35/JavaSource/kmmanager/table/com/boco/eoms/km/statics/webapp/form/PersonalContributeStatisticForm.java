package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识编写人本期（周、月、季、年）知识贡献情况统计表
 * </p>
 * <p>
 * Description:知识编写人本期（周、月、季、年）知识贡献情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:14 CST 2009
 * </p>
 *
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 */
public class PersonalContributeStatisticForm extends BaseForm implements java.io.Serializable {

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
     * 编写知识数
     */
    private java.lang.Long addCount;

    public void setAddCount(java.lang.Long addCount) {
        this.addCount = addCount;
    }

    public java.lang.Long getAddCount() {
        return this.addCount;
    }

    /**
     * 修改知识数
     */
    private java.lang.Long modifyCount;

    public void setModifyCount(java.lang.Long modifyCount) {
        this.modifyCount = modifyCount;
    }

    public java.lang.Long getModifyCount() {
        return this.modifyCount;
    }

    /**
     * 失效知识数
     */
    private java.lang.Long overCount;

    public void setOverCount(java.lang.Long overCount) {
        this.overCount = overCount;
    }

    public java.lang.Long getOverCount() {
        return this.overCount;
    }

    /**
     * 删除知识数
     */
    private java.lang.Long deleteCount;

    public void setDeleteCount(java.lang.Long deleteCount) {
        this.deleteCount = deleteCount;
    }

    public java.lang.Long getDeleteCount() {
        return this.deleteCount;
    }

    /**
     * 上传知识数
     */
    private java.lang.Long upCount;

    public void setUpCount(java.lang.Long upCount) {
        this.upCount = upCount;
    }

    public java.lang.Long getUpCount() {
        return this.upCount;
    }

}