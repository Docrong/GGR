// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TawSystemRoleWorkflow.java

package com.boco.eoms.roleWorkflow.model;

import com.boco.eoms.base.model.BaseObject;

public class TawSystemRoleWorkflow extends BaseObject {

    private Long id;
    private String name;
    private String flowId;
    private String sheetid;
    private String mainServiceBeanId;
    private String remark;
    private String roleId;

    public TawSystemRoleWorkflow() {
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSheetid() {
        return sheetid;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid;
    }

    public String getMainServiceBeanId() {
        return mainServiceBeanId;
    }

    public void setMainServiceBeanId(String mainServiceBeanId) {
        this.mainServiceBeanId = mainServiceBeanId;
    }

    public String toString() {
        return null;
    }

    public boolean equals(Object o) {
        return false;
    }

    public int hashCode() {
        return 0;
    }
}
