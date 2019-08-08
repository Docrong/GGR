// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonTaskTaskManagerImplGZ.java

package com.boco.eoms.sheet.commontask.service.impl;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.ITaskDAO;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.commontask.dao.ICommonTaskTaskDAO;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

import java.util.*;

// Referenced classes of package com.boco.eoms.sheet.commontask.service.impl:
//			CommonTaskTaskManagerImpl

public class CommonTaskTaskManagerImplGZ extends CommonTaskTaskManagerImpl {

    public CommonTaskTaskManagerImplGZ() {
    }

    public HashMap getUndoTaskByOverTime(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length)
            throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        ITask taskObject = (ITask) condition.get("taskObject");
        String agentsql = "";
        HashMap cloumnMap = OvertimeTipUtil.getAllMainColumnByMapping(flowName);
        Iterator it = cloumnMap.keySet().iterator();
        String ruternColumnName;
        String tmpOvertimeClm;
        for (ruternColumnName = ""; it.hasNext(); ruternColumnName = ruternColumnName + " main." + (String) cloumnMap.get(tmpOvertimeClm) + ",")
            tmpOvertimeClm = (String) it.next();

        if (!ruternColumnName.equals(""))
            ruternColumnName = "," + ruternColumnName.substring(0, ruternColumnName.length() - 1);
        String sql = "";
        String countsql = "";
        String type = StaticMethod.nullObject2String(condition.get("type"));
        if ("1".equals(type)) {
            sql = "select distinct task" + ruternColumnName + " from " + " CommonTaskTaskUndo as task," + mainObject.getClass().getName() + " as main " + "where main.id =task.sheetKey and main.deleted <> 1 and main.id = task.sheetKey and task.taskStatus in ('2', '8') and task.taskDisplayName <> '�ݸ�' and task.taskDisplayName <> '�׶λظ�' and task.operateRoleId in ('" + userId + "', '" + deptId + "')" + " ";
            if (!condition.get("ifAgent").toString().equals(""))
                if (condition.get("ifAgent").toString().equals("1")) {
                    sql = sql + " and decode(main.ifAgent ,'', '1',main.ifAgent) <> '0' and decode(ifagent,'','1',ifagent) not like '%," + deptId + ",%'";
                    countsql = countsql + " and decode(main.ifAgent ,'', '1',main.ifAgent) <> '0' and decode(ifagent,'','1',ifagent) not like '%," + deptId + ",%'";
                } else {
                    sql = sql + " and (main.ifAgent = '0' or main.ifAgent like '%," + deptId + ",%')";
                    countsql = countsql + " and (main.ifAgent = '0' or main.ifAgent like '%," + deptId + ",%')";
                }
            StringBuffer hql = new StringBuffer(sql);
            if (length.intValue() != -1)
                if (!orderCondition.equals("") && orderCondition != null)
                    hql.append("order by " + orderCondition);
                else
                    hql.append("order by task.createTime desc");
            taskMap = getTaskDAO().getTaskListByCondition(hql.toString(), startIndex, length);
        } else {
            if (!condition.get("ifAgent").toString().equals(""))
                if (condition.get("ifAgent").toString().equals("1")) {
                    agentsql = " and decode(main.ifAgent ,'', '1',main.ifAgent) <> '0' and decode(ifagent,'','1',ifagent) not like '%," + deptId + ",%'";
                } else {
                    agentsql = " and (main.ifAgent = '0' or main.ifAgent like '%," + deptId + ",%')";
                }
            sql = "select distinct decode(task.taskname,'AffirmHumTask',task.sheetkey,task.id) id,task.TASKNAME,task.TASKDISPLAYNAME,NULL CREATETIME,task.TASKSTATUS,'' PROCESSID,task.SHEETKEY,task.SHEETID,task.TITLE title1,task.ACCEPTTIMELIMIT,task.COMPLETETIMELIMIT,task.OPERATEROLEID,task.TASKOWNER,'' PRELINKID,'' FLOWNAME,'' CURRENTLINKID,'' OPERATETYPE,'' PARENTTASKID,'' SUBTASKDEALFALG,task.IFWAITFORSUBTASK,'' SUBTASKFLAG,0 CREATEYEAR,0 CREATEMONTH,0 CREATEDAY,task.SENDTIME,'' PREDEALDEPT,'' PREDEALUSERID,'' PARENTPROCESSNAME,'' preDealRoleId,main.title,main.revert " +
                    " from commontask_task task,(select * From commontask_link  where operatedeptid = '" + deptId + "' and operateuserid <>'" + userId + "') link  ,commontask_main main where " +
                    " main.deleted <> 1 and task.taskowner in ('" + userId + "', '" + deptId + "') and taskstatus in ('2','8') and task.sheetkey = link.mainid(+) and task.taskDisplayName <> '�ݸ�' and task.taskDisplayName <> '�׶λظ�' and mainid is null and task.sheetkey =main.id" + agentsql;
            countsql = " select count(*) from (" + sql + ")";
            StringBuffer hql = new StringBuffer(sql);
            StringBuffer counthql = new StringBuffer(countsql);
            if (length.intValue() != -1)
                if (!orderCondition.equals("") && orderCondition != null) {
                    hql.append(" order by " + orderCondition);
                } else {
                    hql.append(" order by task.sendtime desc");
                }
            Integer total = ((ICommonTaskTaskDAO) getTaskDAO()).getCountTaskBySQL(counthql.toString());
            taskMap = ((ICommonTaskTaskDAO) getTaskDAO()).getTaskListBySQL(hql.toString(), startIndex, length);
            taskMap.put("taskTotal", total);
        }
        return taskMap;
    }
}
