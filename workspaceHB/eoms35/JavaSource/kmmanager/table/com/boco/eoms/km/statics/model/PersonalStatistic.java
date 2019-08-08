package com.boco.eoms.km.statics.model;

import com.boco.eoms.base.model.BaseObject;

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
 * @author ljt
 * @version 0.1
 */
public class PersonalStatistic extends BaseObject {

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
    private java.lang.Integer addCount;

    public void setAddCount(java.lang.Integer addCount) {
        this.addCount = addCount;
    }

    public java.lang.Integer getAddCount() {
        return this.addCount;
    }

    /**
     * 修改知识数
     */
    private java.lang.Integer modifyCount;

    public void setModifyCount(java.lang.Integer modifyCount) {
        this.modifyCount = modifyCount;
    }

    public java.lang.Integer getModifyCount() {
        return this.modifyCount;
    }

    /**
     * 失效知识数
     */
    private java.lang.Integer overCount;

    public void setOverCount(java.lang.Integer overCount) {
        this.overCount = overCount;
    }

    public java.lang.Integer getOverCount() {
        return this.overCount;
    }

    /**
     * 删除知识数
     */
    private java.lang.Integer deleteCount;

    public void setDeleteCount(java.lang.Integer deleteCount) {
        this.deleteCount = deleteCount;
    }

    public java.lang.Integer getDeleteCount() {
        return this.deleteCount;
    }

    /**
     * 引用知识数
     */
    private java.lang.Integer utilizationCount;

    public void setUtilizationCount(java.lang.Integer utilizationCount) {
        this.utilizationCount = utilizationCount;
    }

    public java.lang.Integer getUtilizationCount() {
        return this.utilizationCount;
    }

    /**
     * 知识被引用次数
     */
    private java.lang.Integer usedCount;

    public void setUsedCount(java.lang.Integer usedCount) {
        this.usedCount = usedCount;
    }

    public java.lang.Integer getUsedCount() {
        return this.usedCount;
    }

    /**
     * 上传文件数
     */
    private java.lang.Integer upCount;

    public void setUpCount(java.lang.Integer upCount) {
        this.upCount = upCount;
    }

    public java.lang.Integer getUpCount() {
        return this.upCount;
    }

    /**
     * 下载文件数
     */
    private java.lang.Integer downCount;

    public void setDownCount(java.lang.Integer downCount) {
        this.downCount = downCount;
    }

    public java.lang.Integer getDownCount() {
        return this.downCount;
    }

    public boolean equals(Object o) {
        if (o instanceof PersonalStatistic) {
            PersonalStatistic personalStatistic = (PersonalStatistic) o;
            if (this.id != null || this.id.equals(personalStatistic.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}