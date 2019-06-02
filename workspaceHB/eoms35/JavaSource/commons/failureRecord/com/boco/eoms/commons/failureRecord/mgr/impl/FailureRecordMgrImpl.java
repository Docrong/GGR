package com.boco.eoms.commons.failureRecord.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.failureRecord.dao.FailureRecordDao;
import com.boco.eoms.commons.failureRecord.mgr.FailureRecordMgr;
import com.boco.eoms.commons.failureRecord.model.FailureRecord;
import com.boco.eoms.commons.failureRecord.webapp.form.FailureRecordForm;
import com.boco.eoms.workbench.netdisk.dao.ITawWorkbenchNetDiskDao;

public class FailureRecordMgrImpl implements FailureRecordMgr {
	private FailureRecordDao failureRecordDao;

	public void save(FailureRecord failureRecord) {
		// TODO Auto-generated method stub
		failureRecordDao.save(failureRecord);

	}

	public FailureRecordDao getFailureRecordDao() {
		return failureRecordDao;
	}

	public void setFailureRecordDao(FailureRecordDao failureRecordDao) {
		this.failureRecordDao = failureRecordDao;
	}

	// private InterfaceMonitoringDao interfaceMonitoringDao;
	// private InterfaceConfigurationDao interfaceConfigurationDao;
	// private InterfaceConfigurationGroupDao interfaceConfigurationGroupDao;
	public Map getMonitoringLogFind(final Integer curPage,
			final Integer pageSize, FailureRecordForm form) {

		Map map = failureRecordDao.getMonitoringLog(curPage, pageSize, form);
		return map;
	}

	public FailureRecord getFailureRecord(String id) {
		// TODO Auto-generated method stub
		FailureRecord failureRecord = new FailureRecord();
		failureRecord = failureRecordDao.getFailureRecord(id);
		return failureRecord;
	}

	public void removeFailureRecord(FailureRecord failureRecord) {
		failureRecordDao.removeFailureRecord(failureRecord);
		
	}
	public FailureRecord findFailureRecord(String id) {
		// TODO Auto-generated method stub
		FailureRecord failureRecord = new FailureRecord();
		failureRecord = failureRecordDao.getFailureRecord(id);
		return failureRecord;
	}
	//
	// public InterfaceConfigurationDao getInterfaceConfigurationDao() {
	// return interfaceConfigurationDao;
	// }
	//
	// public void setInterfaceConfigurationDao(
	// InterfaceConfigurationDao interfaceConfigurationDao) {
	// this.interfaceConfigurationDao = interfaceConfigurationDao;
	// }
	//
	// public void saveInterfaceConfiguration(
	// InterfaceConfiguration interfaceConfiguration) {
	// interfaceConfigurationDao.save(interfaceConfiguration);
	//
	// }
	//

	//
	// public void saveInterfaceMonitoring(InterfaceMonitoring
	// interfaceMonitoring) {
	// interfaceMonitoringDao.save(interfaceMonitoring);
	//
	// }
	//
	// public void saveInterfaceConfigurationGroup(
	// InterfaceConfigurationGroup interfaceConfigurationGroup) {
	// interfaceConfigurationGroupDao.save(interfaceConfigurationGroup);
	//		
	// }
	//
	// public InterfaceConfigurationGroupDao getInterfaceConfigurationGroupDao()
	// {
	// return interfaceConfigurationGroupDao;
	// }
	//
	// public void setInterfaceConfigurationGroupDao(
	// InterfaceConfigurationGroupDao interfaceConfigurationGroupDao) {
	// this.interfaceConfigurationGroupDao = interfaceConfigurationGroupDao;
	// }
	//
	// public List getModuleTree(String nodid) {
	// List list=new ArrayList();
	// list=interfaceConfigurationGroupDao.getModuleTree(nodid);
	// return list;
	// }
	//
	// public InterfaceConfigurationGroup getInterfaceConfigurationGroup(String
	// id) {
	// InterfaceConfigurationGroup interfaceConfigurationGroup=new
	// InterfaceConfigurationGroup();
	// interfaceConfigurationGroup=interfaceConfigurationGroupDao.getInterfaceConfigurationGroup(id);
	// return interfaceConfigurationGroup;
	// }
	//
	// public void removeInterfaceConfigurationGroup(
	// InterfaceConfigurationGroup interfaceConfigurationGroup) {
	// interfaceConfigurationGroupDao.removeInterfaceConfigurationGroup(interfaceConfigurationGroup);
	//		
	// }
	//
	// public List getInterfaceConfigurationList(String nodeid) {
	// List list=new ArrayList();
	// list=interfaceConfigurationDao.getInterfaceConfigurationList(nodeid);
	// return list;
	// }
	//
	// public void removeInterfaceConfiguration(
	// InterfaceConfiguration interfaceConfiguration) {
	// interfaceConfigurationDao.removeInterfaceConfiguration(interfaceConfiguration);
	//		
	// }
	//
	// public InterfaceConfiguration getinterfaceConfiguration(String id) {
	// InterfaceConfiguration interfaceConfiguration=new
	// InterfaceConfiguration();
	// interfaceConfiguration=interfaceConfigurationDao.getinterfaceConfiguration(id);
	// return interfaceConfiguration;
	// }
	//
	// public List getInterfaceConfigurationModuleTree(String nodid) {
	// List list=new ArrayList();
	// list=interfaceConfigurationDao.getInterfaceConfigurationModuleTree(nodid);
	//		return list;
	//	}

}
