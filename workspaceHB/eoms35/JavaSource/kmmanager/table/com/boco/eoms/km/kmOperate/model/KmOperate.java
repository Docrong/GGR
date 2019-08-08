package com.boco.eoms.km.kmOperate.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识管理权限配置
 * </p>
 * <p>
 * Description:知识管理权限配置
 * </p>
 * <p>
 * Fri May 22 14:03:33 CST 2009
 * </p>
 *
 * @author 戴志刚
 * @version 1.0
 */
public class KmOperate extends BaseObject {

    /**
     * 锟斤拷锟�
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 目录节点
     */
    private java.lang.String nodeId;

    public void setNodeId(java.lang.String nodeId) {
        this.nodeId = nodeId;
    }

    public java.lang.String getNodeId() {
        return this.nodeId;
    }

    /**
     * 节点所属：内容管理,附件管理
     */
    private java.lang.String nodeType;

    public void setNodeType(java.lang.String nodeType) {
        this.nodeType = nodeType;
    }

    public java.lang.String getNodeType() {
        return this.nodeType;
    }

    /**
     * 对应的权限标识
     */
    private java.lang.String operateType;

    public void setOperateType(java.lang.String operateType) {
        this.operateType = operateType;
    }

    public java.lang.String getOperateType() {
        return this.operateType;
    }

    /**
     * 权限赋予对象类型
     */
    private java.lang.String orgType;

    public void setOrgType(java.lang.String orgType) {
        this.orgType = orgType;
    }

    public java.lang.String getOrgType() {
        return this.orgType;
    }

    /**
     * 权限赋予对象ID
     */
    private java.lang.String orgId;

    public void setOrgId(java.lang.String orgId) {
        this.orgId = orgId;
    }

    public java.lang.String getOrgId() {
        return this.orgId;
    }


    public boolean equals(Object o) {
        if (o instanceof KmOperate) {
            KmOperate kmOperate = (KmOperate) o;
            if (this.id != null || this.id.equals(kmOperate.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}