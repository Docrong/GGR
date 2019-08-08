package com.boco.eoms.km.table.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识字段字典
 * </p>
 * <p>
 * Description:知识字段字典
 * </p>
 * <p>
 * Mon Mar 02 14:55:43 CST 2009
 * </p>
 *
 * @author 吕卫华
 * @version 1.0
 */
public class KmTableDict extends BaseObject {

    /**
     * 知识字典主键
     */
    private java.lang.String id;

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 分类名称
     */
    private java.lang.String dictName;

    public void setDictName(java.lang.String dictName) {
        this.dictName = dictName;
    }

    public java.lang.String getDictName() {
        return this.dictName;
    }

    /**
     * 父分类id
     */
    private java.lang.String parentId;

    public void setParentId(java.lang.String parentId) {
        this.parentId = parentId;
    }

    public java.lang.String getParentId() {
        return this.parentId;
    }

    /**
     * 是否删除
     */
    private java.lang.Integer isDeleted;

    public void setIsDeleted(java.lang.Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public java.lang.Integer getIsDeleted() {
        return this.isDeleted;
    }

    public boolean equals(Object o) {
        if (o instanceof KmTableDict) {
            KmTableDict kmTableDict = (KmTableDict) o;
            if (this.id != null || this.id.equals(kmTableDict.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}