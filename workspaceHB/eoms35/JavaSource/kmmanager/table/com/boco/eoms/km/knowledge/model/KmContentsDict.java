package com.boco.eoms.km.knowledge.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 13:34:35 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public class KmContentsDict extends BaseObject {

    /**
     * 主键
     */
    private java.lang.String id;

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return this.id;
    }

    /**
     * 模型定义主键
     */
    private String tableId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 知识ID
     */
    private java.lang.String contentId;

    public void setContentId(java.lang.String contentId) {
        this.contentId = contentId;
    }

    public java.lang.String getContentId() {
        return this.contentId;
    }

    /**
     * 字典ID
     */
    private java.lang.String dictId;

    public void setDictId(java.lang.String dictId) {
        this.dictId = dictId;
    }

    public java.lang.String getDictId() {
        return this.dictId;
    }

    /**
     * 字典类型
     */
    private java.lang.String dictType;

    public void setDictType(java.lang.String dictType) {
        this.dictType = dictType;
    }

    public java.lang.String getDictType() {
        return this.dictType;
    }

    public boolean equals(Object o) {
        if (o instanceof KmContentsDict) {
            KmContentsDict kmContentsDict = (KmContentsDict) o;
            if (this.id != null || this.id.equals(kmContentsDict.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}