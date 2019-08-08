package com.boco.eoms.workplan.flow.model;

/**
 * <p>Title: 审批流程步骤类</p>
 * <p>Description: 定义一个流程的步骤信息，包括各步骤信息以及流程的名称等</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: boco</p>
 *
 * @author not attributable
 * @version 1.0
 */

import java.util.List;

public class Step {
    private String serial; //步骤编号	编号在该流程中不能重复（从0开始）
    private String name; //步骤名称 在相同流程中的步骤名称不能重复
    private int flag; //范围标志 0：当前部门 1：当前部门及下属部门 2：当前地市 3：上级部门 4：指定部门
    private String deptId; //部门编号 当flag＝4时，表示指定部门
    private String checkuser; // 审批人 具体指定实际审批人
    private String roles; //权限 具有指定权限的人员
    private int type; //选择类型 执行提交选择方式 0:直接生成（给多人）1：通过界面选择（给一人）
    private int flowSerial; //流程名称

    private List checkUserList; //审批人列表
    private String checkUserIdStr; //当前审批人标识字符串，用“，”分隔
    private String checkUserNameStr;//当前审批人名称字符串，用“，”分隔

    public Step() {
    }

    public Step(String _serial, String _name, int _flag, String _deptId,
                String _checkuser, String _roles, int _type) {
        this.serial = _serial;
        this.name = _name;
        this.flag = _flag;
        this.deptId = _deptId;
        this.checkuser = _checkuser;
        this.roles = _roles;
        this.type = _type;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getCheckuser() {
        return checkuser;
    }

    public void setCheckuser(String checkuser) {
        this.checkuser = checkuser;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List getCheckUserList() {
        return checkUserList;
    }

    public void setCheckUserList(List checkUserList) {
        this.checkUserList = checkUserList;
    }

    public int getFlowSerial() {
        return flowSerial;
    }

    public void setFlowSerial(int flowSerial) {
        this.flowSerial = flowSerial;
    }

    public String getCheckUserNameStr() {
        return checkUserNameStr;
    }

    public void setCheckUserNameStr(String checkUserNameStr) {
        this.checkUserNameStr = checkUserNameStr;
    }

    public String getCheckUserIdStr() {
        return checkUserIdStr;
    }

    public void setCheckUserIdStr(String checkUserIdStr) {
        this.checkUserIdStr = checkUserIdStr;
    }

}
