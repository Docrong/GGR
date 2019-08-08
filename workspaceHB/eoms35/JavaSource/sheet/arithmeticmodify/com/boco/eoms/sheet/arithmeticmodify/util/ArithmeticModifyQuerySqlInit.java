package com.boco.eoms.sheet.arithmeticmodify.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

public class ArithmeticModifyQuerySqlInit {

    /**
     * 返回本角色的已被组内其他人员抢单但未处理的工单的SQL
     * 修改为同组处理模式-返回本人、本人所属部门以及本人所属角色所有待办工单， modify by 秦敏 20090909
     *
     * @param condition
     * @param flowName
     * @return
     */
    public static String getAcceptTaskByRole(Map condition) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = StaticMethod.nullObject2String(condition.get("deptId"));
        String flowName = StaticMethod.nullObject2String(condition.get("flowName"));
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String ruternColumnName = "";
        while (it.hasNext()) {
            String tmpOvertimeClm = (String) it.next();
            ruternColumnName += " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",";
        }
        if (!ruternColumnName.equals("")) {
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        }
        String hql = "select task" + ruternColumnName + " from "
                + taskObject.getClass().getName() + " as task,"
                + mainObject.getClass().getName() + " as main "
                + " where task.sheetKey=main.id"
                /**修改同组处理模式， modify by shichuangke 20101105 begin**/
                + " and (task.taskOwner='" + userId + "' or task.operateRoleId='" + userId + "' or task.operateRoleId='" + deptId + "'"
                + " or task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))"
                + " and (task.taskStatus='" + Constants.TASK_STATUS_READY
                + "' or task.taskStatus='" + Constants.TASK_STATUS_CLAIMED
                + "' or task.taskStatus='" + Constants.TASK_STATUS_INACTIVE + "')";
        hql = hql + " and task.taskDisplayName<>'草稿'"
                + " and (main.deleted=0 or main.deleted is null)"
                + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.sheetKey=main.id and task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + Constants.TASK_STATUS_FINISHED + "')))"
        ;

        return hql;
    }

    /**
     * 返回本角色的已被组内其他人员抢单但未处理的工单的SQL
     * 修改为同组处理模式-返回本人、本人所属部门以及本人所属角色所有待办工单， modify by 秦敏 20090909
     *
     * @param condition
     * @param flowName
     * @return
     */
    public static String getAcceptTaskByRoleNoCheck(Map condition) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = StaticMethod.nullObject2String(condition.get("deptId"));
        String flowName = StaticMethod.nullObject2String(condition.get("flowName"));
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String ruternColumnName = "";
        while (it.hasNext()) {
            String tmpOvertimeClm = (String) it.next();
            ruternColumnName += " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",";
        }
        if (!ruternColumnName.equals("")) {
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        }
        String hql = "select task" + ruternColumnName + " from "
                + taskObject.getClass().getName() + " as task,"
                + mainObject.getClass().getName() + " as main "
                + " where task.sheetKey=main.id"
                /**修改同组处理模式， modify by shichuangke 20101105 begin**/
                + " and (task.taskOwner='" + userId + "' or task.operateRoleId='" + userId + "' or task.operateRoleId='" + deptId + "'"
                + " or task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))"
                + " and (task.taskStatus='" + Constants.TASK_STATUS_READY
                + "' or task.taskStatus='" + Constants.TASK_STATUS_CLAIMED
                + "' or task.taskStatus='" + Constants.TASK_STATUS_INACTIVE + "')";
        hql = hql + " and task.taskDisplayName<>'草稿' and task.taskName<>'CheckTask'"
                + " and (main.deleted=0 or main.deleted is null)"
                + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.sheetKey=main.id and task1.subTaskFlag='true' and task1.taskName<>'CheckTask' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + Constants.TASK_STATUS_FINISHED + "')))"
        ;

        return hql;
    }

    /**
     * 返回本角色的已被组内其他人员抢单但未处理的工单的SQL
     * 修改为同组处理模式-返回本人、本人所属部门以及本人所属角色所有待办工单， modify by 秦敏 20090909
     *
     * @param condition
     * @param flowName
     * @return
     */
    public static String getAcceptTaskByRoleCheck(Map condition) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = StaticMethod.nullObject2String(condition.get("deptId"));
        String flowName = StaticMethod.nullObject2String(condition.get("flowName"));
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String ruternColumnName = "";
        while (it.hasNext()) {
            String tmpOvertimeClm = (String) it.next();
            ruternColumnName += " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",";
        }
        if (!ruternColumnName.equals("")) {
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        }
        String hql = "select task" + ruternColumnName + " from "
                + taskObject.getClass().getName() + " as task,"
                + mainObject.getClass().getName() + " as main "
                + " where task.sheetKey=main.id"
                /**修改同组处理模式， modify by shichuangke 20101105 begin**/
                + " and (task.taskOwner='" + userId + "' or task.operateRoleId='" + userId + "' or task.operateRoleId='" + deptId + "'"
                + " or task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))"
                + " and (task.taskStatus='" + Constants.TASK_STATUS_READY
                + "' or task.taskStatus='" + Constants.TASK_STATUS_CLAIMED
                + "' or task.taskStatus='" + Constants.TASK_STATUS_INACTIVE + "')";
        hql = hql + " and task.taskDisplayName<>'草稿' and task.taskName='CheckTask'"
                + " and (main.deleted=0 or main.deleted is null)"
                + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.sheetKey=main.id and task1.subTaskFlag='true' and task1.taskName<>'CheckTask' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + Constants.TASK_STATUS_FINISHED + "')))"
        ;

        return hql;
    }

    /**
     * 返回待办工单的SQL语句，不含排序条件。
     *
     * @param condition
     * @param flowName
     * @return
     */
    public static String getUndoTaskSqlNoCheck(Map condition, String flowName) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String ruternColumnName = "";
        while (it.hasNext()) {
            String tmpOvertimeClm = (String) it.next();
            ruternColumnName += " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",";
        }
        if (!ruternColumnName.equals("")) {
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        }
        String hql = "select task" + ruternColumnName + " from "
                + taskObject.getClass().getName() + " as task,"
                + mainObject.getClass().getName() + " as main "
                + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')"
                + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))"
                + " and (task.taskStatus=" + Constants.TASK_STATUS_READY
                + " or task.taskStatus=" + Constants.TASK_STATUS_CLAIMED
                + " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + ")"
                + " and task.taskDisplayName<>'草稿' and task.taskName<>'CheckTask'"
                + " and main.id=task.sheetKey and (main.deleted=0 or main.deleted is null) and main.status=0"
                + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.subTaskFlag='true' and task1.taskName<>'CheckTask' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + Constants.TASK_STATUS_FINISHED + "')))";
        return hql;
    }

    /**
     * 返回待质检待办工单的SQL语句，不含排序条件。
     *
     * @param condition
     * @param flowName
     * @return
     */
    public static String getUndoTaskSqlCheck(Map condition, String flowName) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String ruternColumnName = "";
        while (it.hasNext()) {
            String tmpOvertimeClm = (String) it.next();
            ruternColumnName += " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",";
        }
        if (!ruternColumnName.equals("")) {
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        }
        String hql = "select task" + ruternColumnName + " from "
                + taskObject.getClass().getName() + " as task,"
                + mainObject.getClass().getName() + " as main "
                + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')"
                + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))"
                + " and (task.taskStatus=" + Constants.TASK_STATUS_READY
                + " or task.taskStatus=" + Constants.TASK_STATUS_CLAIMED
                + " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + ")"
                + " and task.taskDisplayName<>'草稿' and task.taskName='CheckTask'"
                + " and main.id=task.sheetKey and (main.deleted=0 or main.deleted is null) and main.status=0"
                + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.subTaskFlag='true' and task1.taskName<>'CheckTask' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + Constants.TASK_STATUS_FINISHED + "')))";
        return hql;
    }
}
