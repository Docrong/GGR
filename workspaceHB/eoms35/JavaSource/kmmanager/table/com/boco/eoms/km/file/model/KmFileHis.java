package com.boco.eoms.km.file.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:文件管理
 * </p>
 * <p>
 * Description:文件管理
 * </p>
 * <p>
 * Wed Mar 25 11:32:18 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmFileHis extends BaseObject {

    private static final long serialVersionUID = 3731162777379472903L;
    /**
     * 主键
     */
    private String hisId;

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    /**
     * kmFile的主键
     */
    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * 文件名
     */
    private java.lang.String fileName;

    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }

    public java.lang.String getFileName() {
        return this.fileName;
    }

    /**
     * 文件后缀名
     */
    private java.lang.String expand;

    public void setExpand(java.lang.String expand) {
        this.expand = expand;
    }

    public java.lang.String getExpand() {
        return this.expand;
    }

    /**
     * 用户ID
     */
    private java.lang.String userId;

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    /**
     * 上传部门
     */
    private java.lang.String deptId;

    public java.lang.String getDeptId() {
        return deptId;
    }

    public void setDeptId(java.lang.String deptId) {
        this.deptId = deptId;
    }

    /**
     * 联系电话
     */
    private java.lang.String phone;

    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    public java.lang.String getPhone() {
        return this.phone;
    }

    /**
     * 上传时间
     */
    private java.lang.String uploadTime;

    public void setUploadTime(java.lang.String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public java.lang.String getUploadTime() {
        return this.uploadTime;
    }

    /**
     * 文件大小
     */
    private java.lang.String fileSize;

    public void setFileSize(java.lang.String fileSize) {
        this.fileSize = fileSize;
    }

    public java.lang.String getFileSize() {
        return this.fileSize;
    }

    /**
     * 文件级别
     */
    private java.lang.String fileGrade;

    public void setFileGrade(java.lang.String fileGrade) {
        this.fileGrade = fileGrade;
    }

    public java.lang.String getFileGrade() {
        return this.fileGrade;
    }

    /**
     * 关键字
     */
    private java.lang.String keywords;

    public void setKeywords(java.lang.String keywords) {
        this.keywords = keywords;
    }

    public java.lang.String getKeywords() {
        return this.keywords;
    }

    /**
     * 摘要
     */
    private java.lang.String fileAbstract;

    public void setFileAbstract(java.lang.String fileAbstract) {
        this.fileAbstract = fileAbstract;
    }

    public java.lang.String getFileAbstract() {
        return this.fileAbstract;
    }

    /**
     * 文件状态
     */
    private java.lang.String fileState;

    public void setFileState(java.lang.String fileState) {
        this.fileState = fileState;
    }

    public java.lang.String getFileState() {
        return this.fileState;
    }

    /**
     * 点击数
     */
    private java.lang.Integer clickCount;

    public void setClickCount(java.lang.Integer clickCount) {
        this.clickCount = clickCount;
    }

    public java.lang.Integer getClickCount() {
        return this.clickCount;
    }

    private String nodeId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 记录状态
     */
    private java.lang.String state;

    public void setState(java.lang.String state) {
        this.state = state;
    }

    public java.lang.String getState() {
        return this.state;
    }

    /**
     * 版本号
     */
    private java.lang.String version;

    public void setVersion(java.lang.String version) {
        this.version = version;
    }

    public java.lang.String getVersion() {
        return this.version;
    }

    private String mappingName;

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }

    public boolean equals(Object o) {
        if (o instanceof KmFileHis) {
            KmFileHis file = (KmFileHis) o;
            if (this.hisId != null || this.hisId.equals(file.getHisId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


}