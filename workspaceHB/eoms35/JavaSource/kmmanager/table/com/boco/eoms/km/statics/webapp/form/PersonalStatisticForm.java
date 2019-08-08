package com.boco.eoms.km.statics.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:人员知识贡献统计
 * </p>
 * <p>
 * Description:人员知识贡献统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 */
public class PersonalStatisticForm extends BaseForm implements java.io.Serializable {

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
     * 新增知识数
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
     * 引用知识数
     */
    private java.lang.Long utilizationCount;

    public void setUtilizationCount(java.lang.Long utilizationCount) {
        this.utilizationCount = utilizationCount;
    }

    public java.lang.Long getUtilizationCount() {
        return this.utilizationCount;
    }

    /**
     * 知识被引用次数
     */
    private java.lang.Long usedCount;

    public void setUsedCount(java.lang.Long usedCount) {
        this.usedCount = usedCount;
    }

    public java.lang.Long getUsedCount() {
        return this.usedCount;
    }

    /**
     * 上传文件数
     */
    private java.lang.Long upCount;

    public void setUpCount(java.lang.Long upCount) {
        this.upCount = upCount;
    }

    public java.lang.Long getUpCount() {
        return this.upCount;
    }

    /**
     * 下载文件数
     */
    private java.lang.Long downCount;

    public void setDownCount(java.lang.Long downCount) {
        this.downCount = downCount;
    }

    public java.lang.Long getDownCount() {
        return this.downCount;
    }

}