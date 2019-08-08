package com.boco.eoms.sheet.agentmaintenance.model;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;

public class AgentMaintenanceTask extends TaskImpl implements ITask {


    private String preDealDept;        //上一级处理部门

    private String preDealUserId;    //上一级处理人员

    public String getPreDealDept() {
        return preDealDept;
    }

    public void setPreDealDept(String preDealDept) {
        this.preDealDept = preDealDept;
    }

    public String getPreDealUserId() {
        return preDealUserId;
    }

    public void setPreDealUserId(String preDealUserId) {
        this.preDealUserId = preDealUserId;
    }


}
