package com.boco.eoms.extra.supplierkpi.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

public class TawSupplierkpiDict extends BaseObject implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 字典Id
     */
    private String dictId;

    /**
     * 名称
     */
    private String dictName;

    /**
     * 备注
     */
    private String dictRemark;

    /**
     * 父节点字典Id
     */
    private String parentDictId;

    /**
     * 是否叶子节点（1是0否）
     */
    private String leaf;

    /**
     * 节点类型（服务，专业，项目分类，评估指标）
     */
    private String nodeType;

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictRemark() {
        return dictRemark;
    }

    public void setDictRemark(String dictRemark) {
        this.dictRemark = dictRemark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getParentDictId() {
        return parentDictId;
    }

    public void setParentDictId(String parentDictId) {
        this.parentDictId = parentDictId;
    }

    public boolean equals(Object o) {
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }
}
