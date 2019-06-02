
package com.boco.eoms.sheet.businessimplementsms.service;

import java.util.HashMap;

import com.boco.eoms.sheet.base.service.IBusinessFlowService;

public interface IBusinessImplementSmsFlowManager extends IBusinessFlowService {
	public String getProcessInstanceCustomPropertyValue(String processId,
			String propertyName,HashMap sessionMap) throws Exception;
	public void setProcessInstanceCustomProperty(String processId,
			String propertyName,String propertyValue,HashMap sessionMap) throws Exception;
	public int querySubProcessTaskNum(String processId,HashMap sessionMap) throws Exception;
	public String queryParentProcessId(String processId,HashMap sessionMap) throws Exception;
}

