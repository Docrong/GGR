/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.task;

import java.util.Date;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Timestamp:2007-8-3 16:15:16
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public interface ITask {
    //任务主键
    public abstract String getId();

    public abstract void setId(String id);

    //任务节点名称
    public abstract String getTaskName();

    public abstract void setTaskName(String taskName);

    //任务节点名称（中文）
    public abstract String getTaskDisplayName();

    public abstract void setTaskDisplayName(String taskDisplayName);

    //任务建立时间
    public abstract Date getCreateTime();

    public abstract void setCreateTime(Date createTime);

    //任务状态
    public abstract String getTaskStatus();

    public abstract void setTaskStatus(String taskStatus);

    //任务对应的工单主键
    public abstract String getSheetKey();

    public abstract void setSheetKey(String sheetKey);

    //任务对应的工单流水号
    public abstract String getSheetId();

    public abstract void setSheetId(String sheetId);

    //任务接单时限
    public abstract Date getAcceptTimeLimit();

    public abstract void setAcceptTimeLimit(Date acceptTimeLimit);

    //任务回复时限
    public abstract Date getCompleteTimeLimit();

    public abstract void setCompleteTimeLimit(Date completeTimeLimit);

    //任务对应的工单标题
    public abstract String getTitle();

    public abstract void setTitle(String title);

    //任务对应的流程实例号
    public abstract String getProcessId();

    public abstract void setProcessId(String processId);

    //当前处理角色ID
    public abstract String getOperateRoleId();

    public void setOperateRoleId(String operateRoleId);

    //上一条link的ID
    public abstract String getPreLinkId();

    public void setPreLinkId(String preLinkId);

    //任务所有者
    public String getTaskOwner();

    public void setTaskOwner(String taskOwner);

    //流程名称
    public String getFlowName();

    public void setFlowName(String flowName);

    //当前link的id
    public String getCurrentLinkId();

    public void setCurrentLinkId(String currentLinkId);

    //当前子任务标识
    public String getSubTaskFlag();

    public void setSubTaskFlag(String subTaskFlag);

    //操作者类型
    public abstract String getOperateType();

    public abstract void setOperateType(String operateType);

    //父任务ID
    public abstract String getParentTaskId();

    public abstract void setParentTaskId(String parentTaskId);

    public abstract String getSubTaskDealFalg();

    public abstract void setSubTaskDealFalg(String subTaskDealFalg);

    //是否存在subTask
    public String getIfWaitForSubTask();

    public void setIfWaitForSubTask(String ifWaitForSubTask);

    //任务产生时间中的年份
    public int getCreateDay();

    public void setCreateDay(int createDay);

    //任务产生时间中的月份
    public int getCreateMonth();

    public void setCreateMonth(int createMonth);

    //任务产生时间中的天
    public int getCreateYear();

    public void setCreateYear(int createYear);

    //工单派单时间，和main表中值一致
    public Date getSendTime();

    public void setSendTime(Date sendTime);

    /**
     * 工单超时类型：0-未超时 1-已超时 2-将要超时
     */
    public String getOvertimeType();

    public void setOvertimeType(String overtimeType);

    /**
     * 该任务是否有效
     */
    public String getDeleted();

    public void setDeleted(String deleted);

    /**
     * 父类流程Piid
     *
     * @return
     */
    public abstract String getParentProcessName();

    public abstract void setParentProcessName(String parentProcessName);

    //	上一级处理部门
    public String getPreDealDept();

    public void setPreDealDept(String preDealDept);

    //上一级处理角色
    public String getPreDealRoleId();

    public void setPreDealRoleId(String preDealRoleId);

    //上一级处理人员
    public String getPreDealUserId();

    public void setPreDealUserId(String preDealUserId);
}
