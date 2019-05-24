package com.boco.eoms.commons.failureRecord.mgr;

import java.util.List;
import java.util.Map;


import com.boco.eoms.commons.failureRecord.model.FailureRecord;
import com.boco.eoms.commons.failureRecord.webapp.form.FailureRecordForm;
public interface FailureRecordMgr {
	public Map getMonitoringLogFind(final Integer curPage, final Integer pageSize,FailureRecordForm failureRecordForm);
	public FailureRecord getFailureRecord(String id);
//	public Map getConfigurationList(final Integer curPage, final Integer pageSize,String direction);	
//	
//	public void saveInterfaceConfiguration(InterfaceConfiguration interfaceConfiguration);
//	public InterfaceMonitoring getInterfaceMonitoring(final String id); 
//	public void saveInterfaceMonitoring(InterfaceMonitoring interfaceMonitoring); 
//	public void saveInterfaceConfigurationGroup(InterfaceConfigurationGroup interfaceConfigurationGroup);
//	public  List getModuleTree(String nodid);
//	public InterfaceConfigurationGroup getInterfaceConfigurationGroup(String id);
//	public void removeInterfaceConfigurationGroup(
//			InterfaceConfigurationGroup interfaceConfigurationGroup);
//	public List getInterfaceConfigurationList(String nodeid);
//	public void removeInterfaceConfiguration(
//			InterfaceConfiguration interfaceConfiguration);
//	public InterfaceConfiguration getinterfaceConfiguration(String id);
//	public  List getInterfaceConfigurationModuleTree(String nodid);
	public void save(FailureRecord failureRecord);
	public void removeFailureRecord(FailureRecord failureRecord); 
	public FailureRecord findFailureRecord(String id);
}
