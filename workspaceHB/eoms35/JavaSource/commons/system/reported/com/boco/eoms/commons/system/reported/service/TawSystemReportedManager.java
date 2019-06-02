package com.boco.eoms.commons.system.reported.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.reported.model.TawSystemReported;

public interface TawSystemReportedManager extends Manager {
	public void saveTawSystemReported(TawSystemReported tawSystemReported,List reportedOrgs);
	public Map getTawSystemReportedPage(Integer curPage, Integer pageSize,
			String whereStr);
	public void updateTawSystemReported(TawSystemReported tawSystemReported,List reportedOrgs);
	public void removeTawSystemReported(final String id);
	public List getTawSystemReportedUsersByReportedId(String reportId);
	public TawSystemReported getTawSystemReported(final String id);	
}

