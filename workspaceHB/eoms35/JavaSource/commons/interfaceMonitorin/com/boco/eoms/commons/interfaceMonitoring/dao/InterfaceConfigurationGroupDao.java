package com.boco.eoms.commons.interfaceMonitoring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfigurationGroup;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;
import com.boco.eoms.workbench.netdisk.model.TawWorkbenchNetDiskFolderShare;

public interface InterfaceConfigurationGroupDao {

	public void save(InterfaceConfigurationGroup interfaceConfigurationGroup);

	public List getModuleTree(String nodid);

	public void removeInterfaceConfigurationGroup(
			InterfaceConfigurationGroup interfaceConfigurationGroup);

	public InterfaceConfigurationGroup getInterfaceConfigurationGroup(String id);
}
