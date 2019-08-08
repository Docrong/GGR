package com.boco.eoms.sheet.agentmaintenance.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceTaskManager;
import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
import com.boco.eoms.sheet.base.util.QuerySqlInit;

public class AgentMaintenanceTaskManagerImpl extends TaskServiceImpl implements
        IAgentMaintenanceTaskManager {
    public HashMap getUndoTaskByOverTime(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length) throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        condition.put("userId", userId);
        condition.put("deptId", deptId);
        String sql = QuerySqlInit.getUndoTaskSql(condition, flowName);
        if (!condition.get("type").toString().equals("")) {
            sql = sql + " and main.type='" + condition.get("type").toString() + "'";
        }
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by " + orderCondition);
            } else {
                hql.append("order by task.createTime desc");
            }
        }
        taskMap = this.getTaskDAO().getTaskListByCondition(hql.toString(), startIndex,
                length);
        return taskMap;
    }
}
