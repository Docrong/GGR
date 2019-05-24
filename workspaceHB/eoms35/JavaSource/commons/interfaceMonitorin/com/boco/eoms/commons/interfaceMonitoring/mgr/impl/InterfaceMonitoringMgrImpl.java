package com.boco.eoms.commons.interfaceMonitoring.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceConfigurationDao;
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceConfigurationGroupDao;
import com.boco.eoms.commons.interfaceMonitoring.dao.InterfaceMonitoringDao;
import com.boco.eoms.commons.interfaceMonitoring.mgr.InterfaceMonitoringMgr;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfigurationGroup;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceMonitoring;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;
import com.boco.eoms.workbench.netdisk.dao.ITawWorkbenchNetDiskDao;

public class InterfaceMonitoringMgrImpl implements InterfaceMonitoringMgr {
	private InterfaceMonitoringDao interfaceMonitoringDao;
	private InterfaceConfigurationDao interfaceConfigurationDao;
	private InterfaceConfigurationGroupDao interfaceConfigurationGroupDao;
	public Map getMonitoringLogFind(final Integer curPage,
			final Integer pageSize, InterfaceMonitoringForm form) {

		Map map = interfaceMonitoringDao.getMonitoringLog(curPage, pageSize,
				form);
		return map;
	}

	public InterfaceMonitoringDao getInterfaceMonitoringDao() {
		return interfaceMonitoringDao;
	}

	public void setInterfaceMonitoringDao(
			InterfaceMonitoringDao interfaceMonitoringDao) {
		this.interfaceMonitoringDao = interfaceMonitoringDao;
	}

	public Map getConfigurationList(final Integer curPage,
			final Integer pageSize, String direction) {
		Map map = interfaceConfigurationDao.getConfigurationList(curPage,
				pageSize, direction);
		return map;
	}

	public InterfaceConfigurationDao getInterfaceConfigurationDao() {
		return interfaceConfigurationDao;
	}

	public void setInterfaceConfigurationDao(
			InterfaceConfigurationDao interfaceConfigurationDao) {
		this.interfaceConfigurationDao = interfaceConfigurationDao;
	}

	public void saveInterfaceConfiguration(
			InterfaceConfiguration interfaceConfiguration) {
		interfaceConfigurationDao.save(interfaceConfiguration);

	}

	public InterfaceMonitoring getInterfaceMonitoring(String id) {
		// TODO Auto-generated method stub
		InterfaceMonitoring interfaceMonitoring = new InterfaceMonitoring();
		interfaceMonitoring = interfaceMonitoringDao.getInterfaceMonitoring(id);
		return interfaceMonitoring;
	}

	public void saveInterfaceMonitoring(InterfaceMonitoring interfaceMonitoring) {
		interfaceMonitoringDao.save(interfaceMonitoring);

	}

	public void saveInterfaceConfigurationGroup(
			InterfaceConfigurationGroup interfaceConfigurationGroup) {
		interfaceConfigurationGroupDao.save(interfaceConfigurationGroup);
		
	}

	public InterfaceConfigurationGroupDao getInterfaceConfigurationGroupDao() {
		return interfaceConfigurationGroupDao;
	}

	public void setInterfaceConfigurationGroupDao(
			InterfaceConfigurationGroupDao interfaceConfigurationGroupDao) {
		this.interfaceConfigurationGroupDao = interfaceConfigurationGroupDao;
	}

	public List getModuleTree(String nodid) {
		List list=new ArrayList();
		list=interfaceConfigurationGroupDao.getModuleTree(nodid);
		return list;
	}

	public InterfaceConfigurationGroup getInterfaceConfigurationGroup(String id) {
		InterfaceConfigurationGroup interfaceConfigurationGroup=new InterfaceConfigurationGroup();
		interfaceConfigurationGroup=interfaceConfigurationGroupDao.getInterfaceConfigurationGroup(id);
		return interfaceConfigurationGroup;
	}

	public void removeInterfaceConfigurationGroup(
			InterfaceConfigurationGroup interfaceConfigurationGroup) {
		interfaceConfigurationGroupDao.removeInterfaceConfigurationGroup(interfaceConfigurationGroup);
		
	}

	public List getInterfaceConfigurationList(String nodeid) {
		List list=new ArrayList();
		list=interfaceConfigurationDao.getInterfaceConfigurationList(nodeid);
		return list;
	}

	public void removeInterfaceConfiguration(
			InterfaceConfiguration interfaceConfiguration) {
		interfaceConfigurationDao.removeInterfaceConfiguration(interfaceConfiguration);
		
	}

	public InterfaceConfiguration getinterfaceConfiguration(String id) {
		InterfaceConfiguration interfaceConfiguration=new InterfaceConfiguration();
		interfaceConfiguration=interfaceConfigurationDao.getinterfaceConfiguration(id);
		return interfaceConfiguration;
	}

	public List getInterfaceConfigurationModuleTree(String nodid) {
		List list=new ArrayList();
		list=interfaceConfigurationDao.getInterfaceConfigurationModuleTree(nodid);
		return list;
	}

}
