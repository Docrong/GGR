package com.boco.eoms.extra.supplierkpi.report.statistic.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.boco.eoms.extra.supplierkpi.report.common.Parameter;
import com.boco.eoms.extra.supplierkpi.report.common.Report;
import com.boco.eoms.extra.supplierkpi.report.common.ReportsBO;
import com.boco.eoms.extra.supplierkpi.report.statistic.util.GenXml;


public class StatisticManager {
	private Logger logger=Logger.getLogger(this.getClass().getName());
	private String fileName="";
	/**
	 * 系统初次运行时生成所有的report文件,可以在系统初次启动时调用
	 * @author lzp
	 */
	public void init(){
		logger.info("Flash report Init start");
		String[] fileName=this.fileName.split(",");
		ReportsBO bo=ReportsBO.getInstance();
		Map reports=bo.getReports();
		Set reportSet=reports.keySet();
		List fileList=new ArrayList();
		for(Iterator iterator=reportSet.iterator();iterator.hasNext();){
			Report report=(Report)reports.get(iterator.next().toString());
			Parameter[] parameters=report.getReportInfo().getParameters();
			for(int i=0;i<fileName.length;i++){
				if(fileName[i].equals(parameters[0].getValue())){
					fileList.add(report);
					break;
				}
			}
		}
		String path=bo.getRealPath();
		GenXml.genPortletObjectRef(fileList, path);
		logger.info("Flash report Init end");
	}
	/**
	 * 定时生成统计图表
	 * @author lzp
	 */
	public  void  doAuth(){
		logger.info("Test Start!");
		ReportsBO bo=ReportsBO.getInstance();
		bo.refresh();
		Map reports=bo.getReports();
		Set reportSet=reports.keySet();
		List fileList=new ArrayList();
		for(Iterator iterator=reportSet.iterator();iterator.hasNext();){
			Report report=(Report)reports.get(iterator.next().toString());

			Parameter[] parameters=report.getReportInfo().getParameters();
			if("Flash".equalsIgnoreCase(parameters[parameters.length-1].getValue())){				
				if("Flash".equalsIgnoreCase(parameters[parameters.length-1].getValue())){
					if(!report.getReportInfo().isRealtime()){
						fileList.add(report);
					}
                }
			}
		}
		String path=bo.getRealPath();
		GenXml.genPortletObjectRef(fileList,path);
		logger.info("Test end!");
	}
	/**
	 * 即时生成数据统计文件
	 * @author lzp
	 */
	public  void  doRealtime(){
		logger.info("Test start!");
		ReportsBO bo=ReportsBO.getInstance();
		bo.refresh();
		Map reports=bo.getReports();
		Set reportSet=reports.keySet();
		List fileList=new ArrayList();
		for(Iterator iterator=reportSet.iterator();iterator.hasNext();){
			Report report=(Report)reports.get(iterator.next().toString());
			Parameter[] parameters=report.getReportInfo().getParameters();
			if("Flash".equalsIgnoreCase(parameters[parameters.length-1].getValue())){
				if(report.getReportInfo().isRealtime()){
					fileList.add(report);
				}
			}
		}
		String path=bo.getRealPath();
		logger.info(path);
		GenXml.genNopStatisticXml(fileList,path);
		logger.info("Test end!");		
	}
		
}
