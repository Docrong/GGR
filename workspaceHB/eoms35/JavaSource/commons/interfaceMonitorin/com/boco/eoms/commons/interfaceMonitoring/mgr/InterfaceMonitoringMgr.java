package com.boco.eoms.commons.interfaceMonitoring.mgr;

import java.util.List;
import java.util.Map;


import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfigurationGroup;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceMonitoring;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;

public interface InterfaceMonitoringMgr {
	public Map getMonitoringLogFind(final Integer curPage, final Integer pageSize,InterfaceMonitoringForm form);
	public Map getConfigurationList(final Integer curPage, final Integer pageSize,String direction);	
	
	public void saveInterfaceConfiguration(InterfaceConfiguration interfaceConfiguration);
	public InterfaceMonitoring getInterfaceMonitoring(final String id); 
	public void saveInterfaceMonitoring(InterfaceMonitoring interfaceMonitoring); 
	public void saveInterfaceConfigurationGroup(InterfaceConfigurationGroup interfaceConfigurationGroup);
	public  List getModuleTree(String nodid);
	public InterfaceConfigurationGroup getInterfaceConfigurationGroup(String id);
	public void removeInterfaceConfigurationGroup(
			InterfaceConfigurationGroup interfaceConfigurationGroup);
	public List getInterfaceConfigurationList(String nodeid);
	public void removeInterfaceConfiguration(
			InterfaceConfiguration interfaceConfiguration);
	public InterfaceConfiguration getinterfaceConfiguration(String id);
	public  List getInterfaceConfigurationModuleTree(String nodid);
}
