package com.boco.eoms.workbench.networkcalendar.mgr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.workbench.networkcalendar.dao.NetworkcalendarDao;
import com.boco.eoms.workbench.networkcalendar.mgr.NetworkcalendarMgr;
import com.boco.eoms.workbench.networkcalendar.model.Schedule;

public class NetworkcalendarMgrImpl implements NetworkcalendarMgr {
	private NetworkcalendarDao networkcalendarDao;

	public NetworkcalendarDao getNetworkcalendarDao() {
		return networkcalendarDao;
	}

	public void setNetworkcalendarDao(NetworkcalendarDao networkcalendarDao) {
		this.networkcalendarDao = networkcalendarDao;
	}

	public String save(Schedule schedule) {
		String id=networkcalendarDao.save(schedule);
		return id;
	}

	public Map getNetworkcalendarList(Integer curPage, Integer pageSize,
			String date,String userId) {
		Map map =networkcalendarDao.getNetworkcalendarList(curPage, pageSize, date,userId);
		return map;
	}
	public void removeSchedule(String id) {
		
		networkcalendarDao.removeSchedule(networkcalendarDao.getSchedule(id));
	}
	
	public Schedule getSchedule(String id) {
		Schedule schedule =new Schedule();
		schedule=networkcalendarDao.getSchedule(id);
		return schedule;

	}
	public Map getNetworkcalendarHistoryList(final Integer curPage,
			final Integer pageSize, String date,String userId)
	 {
			Map map =networkcalendarDao.getNetworkcalendarHistoryList(curPage, pageSize, date, userId);
			return map;
		}
	
	




	





	

















}
