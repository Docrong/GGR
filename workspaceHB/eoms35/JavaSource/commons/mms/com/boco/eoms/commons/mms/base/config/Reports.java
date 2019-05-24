package com.boco.eoms.commons.mms.base.config;

import java.util.ArrayList;
import java.util.List;

public class Reports {
	
	private Report reports[] = null;

	public Report[] getReports() {
		return reports;
	}

	public void setReports(Report[] reports) {
		this.reports = reports;
	}
	
	public Sheet getSheetById(String id)
	{
		Sheet sheet = null;
		for(int i=0;i<reports.length;i++)
		{
			Report report = reports[i];
			Sheet[] sheets = report.getSheet();
			for(int j=0;j<sheets.length;j++)
			{
				if(id.equalsIgnoreCase(sheets[j].getId()))
				{
					sheet = sheets[j];
					break;
				}
			}
		}
		
		return sheet;
	}
	
	public List getAllSheet()
	{
		List sheetList = new ArrayList();
		for(int i=0;i<reports.length;i++)
		{
			Report report = reports[i];
			Sheet[] sheets = report.getSheet();
			for(int j=0;j<sheets.length;j++)
			{
				sheetList.add(sheets[j]);
			}
		}
		
		return sheetList;
	}
}
