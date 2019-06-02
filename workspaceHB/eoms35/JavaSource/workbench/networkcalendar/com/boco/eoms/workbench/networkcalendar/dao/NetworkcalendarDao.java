package com.boco.eoms.workbench.networkcalendar.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.workbench.networkcalendar.model.Schedule;


public interface NetworkcalendarDao {
//	public Map getMonitoringLog(final Integer curPage, final Integer pageSize,InterfaceMonitoringForm form);
//		
//	public InterfaceMonitoring getInterfaceMonitoring(final String id); 
	public String save(Schedule schedule);
	public Map getNetworkcalendarList(final Integer curPage, final Integer pageSize,
			String date,String userId); 
	public void removeSchedule(Schedule schedule);
	public Schedule getSchedule(String id);
	public Map getNetworkcalendarHistoryList(final Integer curPage,
			final Integer pageSize, String date,String userId);


}
