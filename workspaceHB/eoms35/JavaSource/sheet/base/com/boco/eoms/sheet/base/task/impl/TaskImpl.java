/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.task.impl;


import java.util.Date;

import com.boco.eoms.sheet.base.task.ITask;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * String:2007-8-3 16:15:26
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class TaskImpl implements ITask {
    /**
     * 任务ID
     */
    private String id;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务中文名称
     */
    private String taskDisplayName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 流程实例ID
     */
    private String processId;
    /**
     * 工单主键ID
     */
    private String sheetKey;
    /**
     * 工单流水号
     */
    private String sheetId;
    /**
     * 工单主题
     */
    private String title;
    /**
     * 接收时限
     */
    private Date acceptTimeLimit;
    /**
     * 完成时限
     */
    private Date completeTimeLimit;
    /**
     * 操作角色ID
     */
    private String operateRoleId;
    /**
     * 任务所有者
     */
    private String taskOwner;
    /**
     * 父LinkId
     */
    private String preLinkId;
    /**
     * 流程名称
     */
    private String flowName;
    /**
     * 当前LinkId
     */
    private String currentLinkId;
    /**
     * 子任务标识
     */
    private String subTaskFlag;
    /**
     * 操作者类型
     */
    private String operateType;
    /**
     * 父任务ID
     */
    private String parentTaskId;

    /**
     * 子任务是否被处理回复，是：true，否：false
     */
    private String subTaskDealFalg;
    /**
     * 是否存在subTask
     */
    private String ifWaitForSubTask;
    /**
     * 任务产生时间中的年份
     */
    private int createYear;
    /**
     * 任务产生时间中的月份
     */
    private int createMonth;
    /**
     * 任务产生时间中的天
     */
    private int createDay;
    /**
     * 工单派单时间，和main表中值一致
     */
    private Date sendTime;
    /**
     * 工单超时类型：0-未超时 1-已超时 2-将要超时
     */
    private String overtimeType;
    /**
     * 任务是否有效
     */
    private String deleted;

    /**
     * 父流程名
     */
    private String parentProcessName;
    /**
     * 上一级处理部门
     */
    private String preDealDept;

    /**
     * 上一级处理人员
     */
    private String preDealUserId;

    /**
     * 上一级处理角色
     */
    private String preDealRoleId;

    public String getPreDealDept() {
        return preDealDept;
    }

    public void setPreDealDept(String preDealDept) {
        this.preDealDept = preDealDept;
    }

    public String getPreDealRoleId() {
        return preDealRoleId;
    }

    public void setPreDealRoleId(String preDealRoleId) {
        this.preDealRoleId = preDealRoleId;
    }

    public String getPreDealUserId() {
        return preDealUserId;
    }

    public void setPreDealUserId(String preDealUserId) {
        this.preDealUserId = preDealUserId;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return Returns the taskDisplayName.
     */
    public String getTaskDisplayName() {
        return taskDisplayName;
    }

    /**
     * @param taskDisplayName The taskDisplayName to set.
     */
    public void setTaskDisplayName(String taskDisplayName) {
        this.taskDisplayName = taskDisplayName;
    }

    /**
     * @return Returns the acceptTimeLimit.
     */
    public Date getAcceptTimeLimit() {
        return acceptTimeLimit;
    }

    /**
     * @param acceptTimeLimit The acceptTimeLimit to set.
     */
    public void setAcceptTimeLimit(Date acceptTimeLimit) {
        this.acceptTimeLimit = acceptTimeLimit;
    }

    /**
     * @return Returns the completeTimeLimit.
     */
    public Date getCompleteTimeLimit() {
        return completeTimeLimit;
    }

    /**
     * @param completeTimeLimit The completeTimeLimit to set.
     */
    public void setCompleteTimeLimit(Date completeTimeLimit) {
        this.completeTimeLimit = completeTimeLimit;
    }

    /**
     * @return Returns the createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime The createTime to set.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the processId.
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * @param processId The processId to set.
     */
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    /**
     * @return Returns the sheetId.
     */
    public String getSheetId() {
        return sheetId;
    }

    /**
     * @param sheetId The sheetId to set.
     */
    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    /**
     * @return Returns the sheetKey.
     */
    public String getSheetKey() {
        return sheetKey;
    }

    /**
     * @param sheetKey The sheetKey to set.
     */
    public void setSheetKey(String sheetKey) {
        this.sheetKey = sheetKey;
    }

    /**
     * @return Returns the taskName.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName The taskName to set.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return Returns the taskStatus.
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * @param taskStatus The taskStatus to set.
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * @return Returns the operateRoleId.
     */
    public String getOperateRoleId() {
        return operateRoleId;
    }

    /**
     * @param operateRoleId The operateRoleId to set.
     */
    public void setOperateRoleId(String operateRoleId) {
        this.operateRoleId = operateRoleId;
    }

    /**
     * @return Returns the preLinkId.
     */
    public String getPreLinkId() {
        return preLinkId;
    }

    /**
     * @param preLinkId The preLinkId to set.
     */
    public void setPreLinkId(String preLinkId) {
        this.preLinkId = preLinkId;
    }

    /**
     * @return Returns the taskOwner.
     */
    public String getTaskOwner() {
        return taskOwner;
    }

    /**
     * @param taskOwner The taskOwner to set.
     */
    public void setTaskOwner(String taskOwner) {
        this.taskOwner = taskOwner;
    }

    /**
     * @return Returns the flowName.
     */
    public String getFlowName() {
        return flowName;
    }

    /**
     * @param flowName The flowName to set.
     */
    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    /**
     * @return Returns the currentLinkId.
     */
    public String getCurrentLinkId() {
        return currentLinkId;
    }

    /**
     * @param currentLinkId
     */
    public void setCurrentLinkId(String currentLinkId) {
        this.currentLinkId = currentLinkId;
    }

    public String getSubTaskFlag() {
        return subTaskFlag;
    }

    public void setSubTaskFlag(String subTaskFlag) {
        this.subTaskFlag = subTaskFlag;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getSubTaskDealFalg() {
        return subTaskDealFalg;
    }

    public void setSubTaskDealFalg(String subTaskDealFalg) {
        this.subTaskDealFalg = subTaskDealFalg;
    }

    public String getIfWaitForSubTask() {
        return ifWaitForSubTask;
    }

    public void setIfWaitForSubTask(String ifWaitForSubTask) {
        this.ifWaitForSubTask = ifWaitForSubTask;
    }

    public int getCreateDay() {
        return createDay;
    }

    public void setCreateDay(int createDay) {
        this.createDay = createDay;
    }

    public int getCreateMonth() {
        return createMonth;
    }

    public void setCreateMonth(int createMonth) {
        this.createMonth = createMonth;
    }

    public int getCreateYear() {
        return createYear;
    }

    public void setCreateYear(int createYear) {
        this.createYear = createYear;
    }

    public String getOvertimeType() {
        return overtimeType;
    }

    public void setOvertimeType(String overtimeType) {
        this.overtimeType = overtimeType;
    }

    public String getParentProcessName() {
        return parentProcessName;
    }

    public void setParentProcessName(String parentProcessName) {
        this.parentProcessName = parentProcessName;
    }
}
