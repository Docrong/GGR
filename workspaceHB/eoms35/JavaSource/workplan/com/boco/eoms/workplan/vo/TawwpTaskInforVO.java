package com.boco.eoms.workplan.vo;

public class TawwpTaskInforVO {

    private String taskID;//任务编号
    private String taskName;//任务名称
    private String manageState;//任务状态(0:初始化 1:空闲 2: 运行 3:挂起 4:错误)
    private String lastFireTime;//任务上一次调度时间(格式: yyyy-MM-dd HH:mm:ss)
    private String description;//任务描述


    public String getLastFireTime() {
        return lastFireTime;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getManageState() {
        return manageState;
    }

    public String getDescription() {
        return description;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setLastFireTime(String lastFireTime) {
        this.lastFireTime = lastFireTime;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setManageState(String manageState) {
        this.manageState = manageState;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskName() {
        return taskName;
    }

}
