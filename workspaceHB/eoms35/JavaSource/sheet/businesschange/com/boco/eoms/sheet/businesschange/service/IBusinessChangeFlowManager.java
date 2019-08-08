
package com.boco.eoms.sheet.businesschange.service;

import java.util.HashMap;

import com.boco.eoms.sheet.base.service.IBusinessFlowService;

public interface IBusinessChangeFlowManager extends IBusinessFlowService {
    public String getProcessInstanceCustomPropertyValue(String processId,
                                                        String propertyName, HashMap sessionMap) throws Exception;

}

