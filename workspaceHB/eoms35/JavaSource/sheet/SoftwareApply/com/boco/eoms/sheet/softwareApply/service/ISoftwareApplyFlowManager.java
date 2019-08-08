
package com.boco.eoms.sheet.softwareApply.service;

import java.util.HashMap;

import com.boco.eoms.sheet.base.service.IBusinessFlowService;

public interface ISoftwareApplyFlowManager extends IBusinessFlowService {
    public String getProcessInstanceCustomPropertyValue(String processId,
                                                        String propertyName, HashMap sessionMap) throws Exception;

    public void setProcessInstanceCustomProperty(String processId,
                                                 String propertyName, String propertyValue, HashMap sessionMap) throws Exception;

    public int querySubProcessTaskNum(String processId, HashMap sessionMap) throws Exception;

    public String queryParentProcessId(String processId, HashMap sessionMap) throws Exception;
}

