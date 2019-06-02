package com.boco.eoms.commons.interfaceMonitoring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfigurationGroup;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderShare;

public interface InterfaceConfigurationDao {
	public Map getConfigurationList(final Integer curPage, final Integer pageSize,String direction);
		
	public void save(InterfaceConfiguration interfaceConfiguration);
	public InterfaceConfiguration getinterfaceConfiguration(String id);

	public void removeInterfaceConfiguration(
			InterfaceConfiguration interfaceConfiguration);
	public List getInterfaceConfigurationList(String nodeid); 
	public  List getInterfaceConfigurationModuleTree(String nodid);
}
