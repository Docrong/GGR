package com.boco.eoms.infmanage.controller;

import org.apache.struts.action.ActionForm;

import java.util.Collection;

public class TawInfCmdForm
        extends ActionForm {
    // 记录ID
    private int id;

    // 命令所属交换机
    private String cmdSwich;

    // 命令编号
    private String cmdId;

    // 命令名称
    private String cmdName;

    // 命令参数
    private String cmdParam;

    // 参数的取值范围
    private String paramScope;

    // 命令的基本描述
    private String cmdDes;

    private java.util.Collection collectionSwich;

    // 部门ID
    private int deptId;

    public TawInfCmdForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCmdSwich() {
        return cmdSwich;
    }

    public void setCmdSwich(String cmdSwich) {
        this.cmdSwich = cmdSwich;
    }

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public String getCmdParam() {
        return cmdParam;
    }

    public void setCmdParam(String cmdParam) {
        this.cmdParam = cmdParam;
    }

    public String getParamScope() {
        return paramScope;
    }

    public void setParamScope(String paramScope) {
        this.paramScope = paramScope;
    }

    public String getCmdDes() {
        return cmdDes;
    }

    public void setCmdDes(String cmdDes) {
        this.cmdDes = cmdDes;
    }

    public java.util.Collection getCollectionSwich() {
        return collectionSwich;
    }

    public void setCollectionSwich(java.util.Collection collectionSwich) {
        this.collectionSwich = collectionSwich;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

}