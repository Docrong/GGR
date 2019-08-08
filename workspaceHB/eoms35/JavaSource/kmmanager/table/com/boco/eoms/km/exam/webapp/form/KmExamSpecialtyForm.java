package com.boco.eoms.km.exam.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:专业表
 * </p>
 * <p>
 * Description:专业表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 */
public class KmExamSpecialtyForm extends BaseForm implements java.io.Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private java.lang.String specialtyID;

    public void setSpecialtyID(java.lang.String specialtyID) {
        this.specialtyID = specialtyID;
    }

    public java.lang.String getSpecialtyID() {
        return this.specialtyID;
    }

    /**
     * 专业名称
     */
    private java.lang.String specialtyName;

    public void setSpecialtyName(java.lang.String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public java.lang.String getSpecialtyName() {
        return this.specialtyName;
    }

    /**
     * 是否删除
     */
    private java.lang.String isDeleted;

    public void setIsDeleted(java.lang.String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public java.lang.String getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * 节点Id（按规则生成）
     */
    private String nodeId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 父节点Id
     */
    private String parentNodeId;

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    /**
     * 是否叶节点
     */
    private String leaf;

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }
}