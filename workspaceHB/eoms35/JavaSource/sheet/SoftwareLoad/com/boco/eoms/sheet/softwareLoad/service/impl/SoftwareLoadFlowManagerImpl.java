
package com.boco.eoms.sheet.softwareLoad.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSBusinessFlowAdapterServiceImpl;
import com.boco.eoms.sheet.base.service.impl.BusinessFlowServiceImpl;
import com.boco.eoms.sheet.softwareLoad.service.ISoftwareLoadFlowManager;

public class SoftwareLoadFlowManagerImpl extends BusinessFlowServiceImpl implements ISoftwareLoadFlowManager {
	/**
	 * 在流程实例中取得属性值
	 */
	public String getProcessInstanceCustomPropertyValue(String processId,
			String propertyName,HashMap sessionMap) throws Exception {
		WPSBusinessFlowAdapterServiceImpl service = (WPSBusinessFlowAdapterServiceImpl)this.getBusinessFlowAdapterService();
		return service.getProcessInstanceCustomPropertyValue(processId, propertyName, sessionMap);
	}
	/**
	 * 在流程实例中设置属性
	 */
	public void setProcessInstanceCustomProperty(String processId,
			String propertyName,String propertyValue,HashMap sessionMap) throws Exception {
		WPSBusinessFlowAdapterServiceImpl service = (WPSBusinessFlowAdapterServiceImpl)this.getBusinessFlowAdapterService();
		service.setProcessInstanceCustomProperty(processId, propertyName, propertyValue, sessionMap);
	}
	/**
	 * 根据父流程的processId查询未处理回复通过的任务数
	 * @param processId
	 */
	public int querySubProcessTaskNum(String processId,HashMap sessionMap) throws Exception {
		WPSBusinessFlowAdapterServiceImpl service = (WPSBusinessFlowAdapterServiceImpl)this.getBusinessFlowAdapterService();
		String selectClause = "DISTINCT PROCESS_INSTANCE.PIID";
		String whereClause = "PROCESS_INSTANCE.PARENT_NAME='"+processId+"' AND PROCESS_INSTANCE.STATE<>3";
		HashMap resultMap = service.queryAll(selectClause, whereClause, sessionMap);
		return resultMap.size();
	}
	/**
	 * 根据父流程的processId查询未处理回复通过的任务数
	 * @param processId
	 */
	public String queryParentProcessId(String processId,HashMap sessionMap) throws Exception {
		WPSBusinessFlowAdapterServiceImpl service = (WPSBusinessFlowAdapterServiceImpl)this.getBusinessFlowAdapterService();
		String selectClause = "DISTINCT PROCESS_INSTANCE.PARENT_NAME";
		String whereClause = "PROCESS_INSTANCE.NAME='"+processId+"'";
		HashMap resultMap = service.queryAll(selectClause, whereClause, sessionMap);
		if(resultMap.size()>0)
			return (String)((HashMap)resultMap.get("0")).get("PARENT_NAME");
		else
			return null;
	}
}

