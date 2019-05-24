package com.boco.eoms.commons.statistic.base.config.graphic;


/**
 * 图形报表配置
 * @author lizhenyou
 *
 */
public class GraphicConfig {
	private GraphicReport[] graphicReports = null;

	public GraphicReport[] getGraphicReports() {
		return graphicReports;
	}

	public void setGraphicReports(GraphicReport[] graphicReports) {
		this.graphicReports = graphicReports;
	}
	
	public void setGraphicReports(GraphicReport graphicReport,String name)
	{
		for (int i = 0; i < graphicReports.length; i++)
		{
			String reportName = graphicReports[i].getName();
			if(name.equalsIgnoreCase(reportName))
			{
				graphicReports[i] = graphicReport;
			}
		}
	}
	
	
	public GraphicReport getGraphicReports(String name) throws Exception
	{
		GraphicReport graphicReport = null;
		boolean isDefined = false;
		for (int i = 0; i < graphicReports.length; i++) {
			if (graphicReports[i].getName().equals(name)) {
				if (isDefined == true) {
					throw new Exception("重复定义GraphicReport的Name!");
				}
				isDefined = true;
				graphicReport = graphicReports[i];
			}
		}
		if (isDefined == false) {
			throw new Exception("没有定义GraphicReport的Name!");
		}

		return graphicReport;
	}
}
