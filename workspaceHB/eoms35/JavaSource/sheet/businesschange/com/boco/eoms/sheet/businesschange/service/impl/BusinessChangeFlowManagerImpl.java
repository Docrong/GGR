
package com.boco.eoms.sheet.businesschange.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSBusinessFlowAdapterServiceImpl;
import com.boco.eoms.sheet.base.service.impl.BusinessFlowServiceImpl;
import com.boco.eoms.sheet.businesschange.service.IBusinessChangeFlowManager;

public class BusinessChangeFlowManagerImpl extends BusinessFlowServiceImpl implements IBusinessChangeFlowManager {
	/** 
	 * 流程回调
	 */
	public void reInvokeProcess(String piid, String operationName,
                       HashMap parameters, HashMap sessionMap) throws Exception {
		try {
			
			 BocoLog.debug(this, "工单回调进入piid is="+piid+"+++operationName is ="+operationName);
			 Map map = this.getBusinessFlowAdapterService().getVariable(piid, "selfCorrKey", sessionMap);
			 Map link = (HashMap)parameters.get("link");
			 link.put("correlationKey", map.get("selfCorrKey"));
			 BocoLog.debug(this, "工单回调进入link.selfCorrKey is ="+map.get("selfCorrKey"));
			 parameters.put("link", link);
			 this.getBusinessFlowAdapterService().reInvokeProcess(piid,operationName, parameters, sessionMap);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("工单回调报错，请联系管理员！");
		}
		
	}

	public String getProcessInstanceCustomPropertyValue(String processId, String propertyName, HashMap sessionMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}

