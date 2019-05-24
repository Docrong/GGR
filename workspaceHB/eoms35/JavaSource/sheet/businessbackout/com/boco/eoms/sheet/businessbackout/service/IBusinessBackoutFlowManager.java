
package com.boco.eoms.sheet.businessbackout.service;

import java.util.HashMap;

import com.boco.eoms.sheet.base.service.IBusinessFlowService;

public interface IBusinessBackoutFlowManager extends IBusinessFlowService {
	public String getProcessInstanceCustomPropertyValue(String processId,
			String propertyName,HashMap sessionMap) throws Exception;
}

