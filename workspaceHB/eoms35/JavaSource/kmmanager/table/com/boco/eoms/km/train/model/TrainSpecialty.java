package com.boco.eoms.km.train.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:专业分类
 * </p>
 * <p>
 * Description:专业分类
 * </p>
 * <p>
 * Thu Jul 09 10:50:06 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainSpecialty extends BaseObject {

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
     * 专业名字
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
    private java.lang.String isDelete;

    public void setIsDelete(java.lang.String isDelete) {
        this.isDelete = isDelete;
    }

    public java.lang.String getIsDelete() {
        return this.isDelete;
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

    public boolean equals(Object o) {
        if (o instanceof TrainSpecialty) {
            TrainSpecialty trainSpecialty = (TrainSpecialty) o;
            if (this.id != null || this.id.equals(trainSpecialty.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}