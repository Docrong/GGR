package com.boco.eoms.sheet.base.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.qo.DataBaseTypeAdapterQO;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

/**
 * @author Administrator
 */

public class QuerySqlInit {
    public static String getUndoTaskSql(Map condition, String flowName) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        System.out.println("---------undoTasksql-------------+flowName+" + flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String tmpOvertimeClm;
        String ruternColumnName;
        String hql = "";
        String mainObjectName = mainObject.getClass().getName();

        System.out.println("-----------querysql----" + mainObject.getClass().getName());
        for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",") {
            tmpOvertimeClm = (String) it.next();
        }
        if (!ruternColumnName.equals("")) {
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        }
        if ("CommonFaultMainFlowProcess".equals(flowName) || "commonFaultMainFlowProcess".equals(flowName)) {
            hql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " +
                    "where " +
                    " ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')" +
                    "  $$$ifHaveSubRoleIds$$$ ) " +
                    " and (task.taskStatus=" + "2" + " or task.taskStatus=" + "8" + " or task.taskStatus=" + "1" + ")" + " and task.taskDisplayName<>'草稿'" +
                    " and main.id=task.sheetKey and main.deleted<>'1' and main.status in ('0','1') and (main.netOptimization = '3' or main.netOptimization is null)" +
                    " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "')))";

        } else {

            hql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " +
                    "where " +
                    " ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')" +
                    "  $$$ifHaveSubRoleIds$$$ ) " +
                    " and (task.taskStatus=" + "2" + " or task.taskStatus=" + "8" + " or task.taskStatus=" + "1" + ")" + " and task.taskDisplayName<>'草稿'" +
                    " and main.id=task.sheetKey and main.deleted<>'1' and main.status in ('0','1')" +
                    " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "')))";
        }


        ITawSystemUserRefRoleManager userrefroleManager = (ITawSystemUserRefRoleManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
        List userrefroleList = userrefroleManager.getUserRefRoleByuserid(userId);
        StringBuffer subRoleIds = new StringBuffer();
        String subRoleId = "";
        String finalSubRoleId = "";
        if ((userrefroleList != null) && (userrefroleList.size() > 0)) {
            subRoleIds.append(" (");
            for (int i = 0; i < userrefroleList.size(); i++) {
                TawSystemUserRefRole userrefrole = (TawSystemUserRefRole) userrefroleList.get(i);
                subRoleId = StaticMethod.nullObject2String(userrefrole.getSubRoleid());
                subRoleIds.append("'" + subRoleId + "',");
            }
            finalSubRoleId = subRoleIds.toString().substring(0, subRoleIds.toString().length() - 1) + ") ";
            System.out.println(" getUndoTaskSql== finalSubRoleId ===" + finalSubRoleId);
            hql = hql.replace("$$$ifHaveSubRoleIds$$$", " or task.taskOwner in " + finalSubRoleId + " ");
        } else {
            hql = hql.replace("$$$ifHaveSubRoleIds$$$", " ");
        }
        System.out.println(" getUndoTaskSql== hql === " + hql);

        return hql;
    }

    public static String getDraftTaskSql(Map condition, String flowName) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String tmpOvertimeClm;
        String ruternColumnName;
        for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",") {
            tmpOvertimeClm = (String) it.next();
        }
        if (!ruternColumnName.equals(""))
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        String hql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " + "where task.sheetKey=main.id " + " and ( task.taskStatus=" + "2" + " or task.taskStatus=" + "8" + " or task.taskStatus=" + "1" + " ) " + " and task.taskDisplayName = '草稿'" + " and main.sendUserId = '" + userId + "'" + " and task.ifWaitForSubTask='" + "false" + "'" + " and main.deleted<>'1'";
        return hql;
    }

    public static String getDoneTaskSql(Map condition) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        String beanName = StaticMethod.nullObject2String(condition.get("beanName"));
        String variables = getAllDictItemsName(beanName);
        String hql = " select distinct " + variables + " from " + mainObject.getClass().getName() + " as main, " + taskObject.getClass().getName() + " as task" + " where task.sheetKey=main.id and ((task.taskOwner='" + userId + "'" + " or task.taskOwner='" + deptId + "')" + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and (task.taskStatus='" + "5" + "' or task.ifWaitForSubTask='true' )" + " and main.deleted<>'1' and main.status=" + Constants.SHEET_RUN + " ";
        return hql;
    }

    public static String deferAppListSql(Map condition) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        String hql = " select distinct main from " + mainObject.getClass().getName() + " as main, " + taskObject.getClass().getName() + " as task" + " where task.sheetKey=main.id and ((task.taskOwner='" + userId + "'" + " or task.taskOwner='" + deptId + "')" + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and main.deleted<>'1' and main.mainDelayFlag=1" + " and main.t1maindeferappdate >= " + new DataBaseTypeAdapterQO().getDateTypeAdapter(StaticMethod.date2String(new Date()));
        return hql;
    }

    public static String getAcceptTaskByRole(Map condition) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = StaticMethod.nullObject2String(condition.get("deptId"));
        String flowName = StaticMethod.nullObject2String(condition.get("flowName"));
        String taskName = StaticMethod.nullObject2String(condition.get("taskName"));
        String operateType = StaticMethod.nullObject2String(condition.get("operateType"));
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String tmpOvertimeClm;
        String ruternColumnName;
        for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",") {
            tmpOvertimeClm = (String) it.next();
        }
        if (!ruternColumnName.equals(""))
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        String hql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " + " where task.sheetKey=main.id" + " and (task.taskOwner<>'" + userId + "'" + " and task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and task.taskStatus=" + "8" + " and task.taskName='" + taskName + "'" + " and task.taskDisplayName<>'�ݸ�'" + " and main.deleted<>'1'" + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "')))";
        return hql;
    }

    public static String getUnHoldTaskSql(Map condition, String flowName) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        String holdTaskName = StaticMethod.nullObject2String(condition.get("holdTaskName"));
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String tmpOvertimeClm;
        String ruternColumnName;
        for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",") {
            tmpOvertimeClm = (String) it.next();
        }
        if (!ruternColumnName.equals(""))
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        String hql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " +
                "where " +
                " ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')" +
                "  $$$ifHaveSubRoleIds$$$ ) " +
                " and (task.taskStatus=" + "2" + " or task.taskStatus=" + "8" + " or task.taskStatus=" + "1" + ")" +
                " and task.taskDisplayName<>'草稿'" + " and main.id=task.sheetKey and main.deleted<>'1'" +
                " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "'))) ";

        if (!holdTaskName.equals("")) {
            if (holdTaskName.indexOf(",") > 0)
                hql = hql + " and task.taskName in ('" + holdTaskName.replaceAll(",", "','") + "')";
            else {
                hql = hql + " and task.taskName = '" + holdTaskName + "'";
            }
        }

        ITawSystemUserRefRoleManager userrefroleManager = (ITawSystemUserRefRoleManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
        List userrefroleList = userrefroleManager.getUserRefRoleByuserid(userId);
        StringBuffer subRoleIds = new StringBuffer();
        String subRoleId = "";
        String finalSubRoleId = "";
        if ((userrefroleList != null) && (userrefroleList.size() > 0)) {
            subRoleIds.append(" (");
            for (int i = 0; i < userrefroleList.size(); i++) {
                TawSystemUserRefRole userrefrole = (TawSystemUserRefRole) userrefroleList.get(i);
                subRoleId = StaticMethod.nullObject2String(userrefrole.getSubRoleid());
                subRoleIds.append("'" + subRoleId + "',");
            }
            finalSubRoleId = subRoleIds.toString().substring(0, subRoleIds.toString().length() - 1) + ") ";
            System.out.println(" getUnHoldTaskSql== finalSubRoleId ===" + finalSubRoleId);
            hql = hql.replace("$$$ifHaveSubRoleIds$$$", " or task.taskOwner in " + finalSubRoleId + " ");
        } else {
            hql = hql.replace("$$$ifHaveSubRoleIds$$$", " ");
        }
        System.out.println(" getUnHoldTaskSql== hql === " + hql);

        return hql;
    }

    public static String getUndoListByFilterSql(Map condition, String flowName) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        String filterName = StaticMethod.nullObject2String(condition.get("filterName"));
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String tmpOvertimeClm;
        String ruternColumnName;
        for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",") {
            tmpOvertimeClm = (String) it.next();
        }
        if (!ruternColumnName.equals(""))
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        String hql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')" + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and (task.taskStatus=" + "2" + " or task.taskStatus=" + "8" + " or task.taskStatus=" + "1" + ")" + " and task.taskDisplayName<>'�ݸ�'" + " and main.id=task.sheetKey and main.deleted<>'1'" + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskObject.getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + "5" + "'))) ";
        if (!filterName.equals(""))
            if (filterName.indexOf(",") > 0)
                hql = hql + " and task.taskName not in ('" + filterName.replaceAll(",", "','") + "')";
            else
                hql = hql + " and task.taskName <> '" + filterName + "'";
        return hql;
    }

    public static String getUndoTaskByRoleSql(Map condition, String flowName) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String tmpOvertimeClm;
        String ruternColumnName;
        for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",") {
            tmpOvertimeClm = (String) it.next();
        }
        if (!ruternColumnName.equals(""))
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        String hql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " + " where task.operateRoleId=task.taskOwner and task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "')" + " and task.taskStatus=" + "2" + " and task.taskDisplayName<>'�ݸ�'" + " and main.id=task.sheetKey and main.deleted<>'1'";
        return hql;
    }

    public static String getDoneTaskByRoleSql(Map condition) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        String beanName = StaticMethod.nullObject2String(condition.get("beanName"));
        String taskName = StaticMethod.nullObject2String(condition.get("taskName"));
        String variables = getAllDictItemsName(beanName);
        String hql = " select distinct main from " + mainObject.getClass().getName() + " as main, " + " where task.sheetKey=main.id" + " and main.id not in (select task1.sheetKey from " + taskObject.getClass().getName() + " as task1 where task1.taskOwner = '" + userId + "')" + " and task.operateRoleId in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "')" + " and (task.taskStatus=" + "5" + " or task.taskStatus=" + "8" + ")" + " and task.taskDisplayName<>'�ݸ�'" + " and main.deleted<>'1'";
        if ((taskName != null) && (!taskName.equals("")))
            hql = hql + " and task.taskName='" + taskName + "'";
        return hql;
    }

    public static String getAllDictItemsName(String beanName) {
        IDictService service = (IDictService) ApplicationContextHolder.getInstance().getBean("DictService");
        List commonList = new ArrayList();
        List privateList = new ArrayList();
        StringBuffer variables = new StringBuffer();
        try {
            commonList = service.getDictItems("dict-mainlistquery-common#common");
        } catch (DictServiceException e) {
            BocoLog.info(QuerySqlInit.class.getClass(), "没有配置已处理工单列表公共查询字段！");
        }
        for (int i = 0; (commonList != null) && (!commonList.isEmpty()) && (i < commonList.size()); i++) {
            DictItemXML dictItemXML = (DictItemXML) commonList.get(i);
            if (i == 0)
                variables.append(dictItemXML.getItemName());
            else {
                variables.append("," + dictItemXML.getItemName());
            }
        }
        if (!beanName.equals("")) {
            try {
                privateList = service.getDictItems("dict-mainlistquery-common#" + beanName);
            } catch (DictServiceException e) {
                BocoLog.info(QuerySqlInit.class.getClass(), "该工单没有配置特殊选查询字段！");
            }
            for (int i = 0; (privateList != null) && (!privateList.isEmpty()) && (i < privateList.size()); i++) {
                DictItemXML privatedictItemXML = (DictItemXML) privateList.get(i);
                variables.append("," + privatedictItemXML.getItemName());
            }
        }

        return variables.toString();
    }

    public static String getHoldedListForUserSql(Map condition) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        String beanName = StaticMethod.nullObject2String(condition.get("beanName"));
        String variables = getAllDictItemsName(beanName);
        String hql = " select distinct " + variables + " from " + mainObject.getClass().getName() + " as main, " + taskObject.getClass().getName() + " as task" + " where task.sheetKey=main.id and ((task.taskOwner='" + userId + "'" + " or task.taskOwner='" + deptId + "')" + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and main.deleted<>'1' and main.status=" + Constants.SHEET_HOLD + " ";
        return hql;
    }

    public static String getUndoTaskSqlCount(Map condition, String flowName) {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String userId = (String) condition.get("userId");
        String deptId = (String) condition.get("deptId");
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String tmpOvertimeClm;
        String ruternColumnName;
        for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",") {
            tmpOvertimeClm = (String) it.next();
        }
        if (!ruternColumnName.equals(""))
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        String hql = "select distinct task" + ruternColumnName + " from " + taskObject.getClass().getName() + " as task," + mainObject.getClass().getName() + " as main " + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')" + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))" + " and (task.taskStatus='" + "2" + "'" + " or task.taskStatus='" + "8" + "'" + " or task.taskStatus='" + "1" + "')" + " and task.taskDisplayName<>'�ݸ�'" + " and main.id=task.sheetKey and main.deleted<>'1'";
        return hql;
    }
}
