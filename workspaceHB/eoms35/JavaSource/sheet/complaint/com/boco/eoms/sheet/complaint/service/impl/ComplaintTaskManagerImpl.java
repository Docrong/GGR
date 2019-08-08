/*
 * Created on 2008-4-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
import com.boco.eoms.sheet.base.util.QuerySqlInit;
import com.boco.eoms.sheet.complaint.service.IComplaintTaskManager;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintTaskManagerImpl extends TaskServiceImpl implements IComplaintTaskManager {
    public HashMap getUndoTaskByOverTime(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length) throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        condition.put("userId", userId);
        condition.put("deptId", deptId);
        String sql = QuerySqlInit.getUndoTaskSql(condition, flowName);
        sql = sql + " and main.status ='0'";
        if (!condition.get("ifAgent").toString().equals("")) {
            if (condition.get("ifAgent").toString().equals("1"))
                sql = sql + " and decode(main.ifAgent ,'', '1',main.ifAgent) =1";
            else
                sql = sql + " and main.ifAgent='" + condition.get("ifAgent").toString() + "'";
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

    public HashMap getUndoTaskByOverTimeAll(Map condition, String userId, String deptId, String flowName, Integer pageIndex, Integer pageSize) {
        // TODO Auto-generated method stub
        return null;
    }
}
