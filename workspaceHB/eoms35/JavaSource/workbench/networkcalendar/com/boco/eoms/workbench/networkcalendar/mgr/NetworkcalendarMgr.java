package com.boco.eoms.workbench.networkcalendar.mgr;

import java.util.List;
import java.util.Map;



import com.boco.eoms.workbench.networkcalendar.model.Schedule;

public interface NetworkcalendarMgr {
	public String save(Schedule schedule);
	public Map getNetworkcalendarList(final Integer curPage, final Integer pageSize,
			String date,String userId); 
	public void removeSchedule(String id);
	public Schedule getSchedule(String id);
	public Map getNetworkcalendarHistoryList(final Integer curPage,
			final Integer pageSize, String date,String userId);
}
