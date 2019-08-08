package com.boco.eoms.sheet.agentmaintenance.service.impl;

import java.util.HashMap;

import com.boco.eoms.sheet.agentmaintenance.service.IAgentMaintenanceFlowManager;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSBusinessFlowAdapterServiceImpl;
import com.boco.eoms.sheet.base.service.impl.BusinessFlowServiceImpl;

public class AgentMaintenanceFlowManagerImpl extends BusinessFlowServiceImpl implements IAgentMaintenanceFlowManager {

    public String getProcessInstanceCustomPropertyValue(String processId, String propertyName, HashMap sessionMap) throws Exception {
        WPSBusinessFlowAdapterServiceImpl service = (WPSBusinessFlowAdapterServiceImpl) this.getBusinessFlowAdapterService();
        return service.getProcessInstanceCustomPropertyValue(processId, propertyName, sessionMap);
    }

    public String queryParentProcessId(String processId, HashMap sessionMap) throws Exception {
        WPSBusinessFlowAdapterServiceImpl service = (WPSBusinessFlowAdapterServiceImpl) this.getBusinessFlowAdapterService();
        String selectClause = "DISTINCT PROCESS_INSTANCE.PARENT_NAME";
        String whereClause = "PROCESS_INSTANCE.NAME='" + processId + "'";
        HashMap resultMap = service.queryAll(selectClause, whereClause, sessionMap);
        if (resultMap.size() > 0)
            return (String) ((HashMap) resultMap.get("0")).get("PARENT_NAME");
        else
            return null;
    }

    public int querySubProcessTaskNum(String processId, HashMap sessionMap) throws Exception {
        WPSBusinessFlowAdapterServiceImpl service = (WPSBusinessFlowAdapterServiceImpl) this.getBusinessFlowAdapterService();
        String selectClause = "DISTINCT PROCESS_INSTANCE.PIID";
        String whereClause = "PROCESS_INSTANCE.PARENT_NAME='" + processId + "' AND PROCESS_INSTANCE.STATE<>3";
        HashMap resultMap = service.queryAll(selectClause, whereClause, sessionMap);
        return resultMap.size();
    }

    public void setProcessInstanceCustomProperty(String processId, String propertyName, String propertyValue, HashMap sessionMap) throws Exception {
        WPSBusinessFlowAdapterServiceImpl service = (WPSBusinessFlowAdapterServiceImpl) this.getBusinessFlowAdapterService();
        service.setProcessInstanceCustomProperty(processId, propertyName, propertyValue, sessionMap);
    }

}
