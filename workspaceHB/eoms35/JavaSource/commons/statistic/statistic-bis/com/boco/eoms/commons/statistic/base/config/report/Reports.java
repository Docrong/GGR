package com.boco.eoms.commons.statistic.base.config.report;

import java.io.Serializable;

public class Reports implements Serializable{
	
	private Report reports[] = null;

	public Report[] getReports() {
		return reports;
	}

	public void setReports(Report[] reports) {
		this.reports = reports;
	}
	
	public Report getReportbyIndex(String id)
	{
		Report re = null;
		for(int i=0;i<reports.length;i++)
		{
			Report r = reports[i];
			if(r.getId()!=null && r.getId().equalsIgnoreCase(id))
			{
				return r;
			}
		}
		
		if(reports.length!=0)
		{
			re = reports[0];
		}
		
		return re;
	}
}
