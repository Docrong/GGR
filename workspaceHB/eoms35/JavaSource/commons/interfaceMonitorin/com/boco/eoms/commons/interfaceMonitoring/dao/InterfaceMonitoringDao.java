package com.boco.eoms.commons.interfaceMonitoring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceConfiguration;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceMonitoring;
import com.boco.eoms.commons.interfaceMonitoring.webapp.form.InterfaceMonitoringForm;


public interface InterfaceMonitoringDao {
	public Map getMonitoringLog(final Integer curPage, final Integer pageSize,InterfaceMonitoringForm form);
		
	public InterfaceMonitoring getInterfaceMonitoring(final String id); 
	public void save(InterfaceMonitoring interfaceMonitoring);


}
