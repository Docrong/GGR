package com.boco.eoms.commons.statistic.customstat.mgr.impl;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.mgr.IStatConfigManager;
import com.boco.eoms.commons.statistic.base.mgr.impl.StatConfigManagerImpl;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.boco.eoms.commons.statistic.customstat.config.CustomConfig;
import com.boco.eoms.commons.statistic.customstat.config.Report;
import com.boco.eoms.commons.statistic.customstat.config.ReportConfig;


public class StatCustomConfigManager extends StatConfigManagerImpl {
	
	private String customConfigFilePath = null; //算法配置

	public String getCustomConfigFilePath() {
		return customConfigFilePath;
	}

	public void setCustomConfigFilePath(String customConfigFilePath) {
		this.customConfigFilePath = customConfigFilePath;
	}
	
	/**
    * 从新读取配置文件，在增加定制报表的时候需要刷新内存
    * @param mapping
    * @param form
    * @param request
    * @param response
    */
    public void reLoadCustomConfig() throws Exception
    {
    	logger.info("\n重新读取配置");
    	
    	StatCustomConfigManager statCustomConfigManager = (StatCustomConfigManager)ApplicationContextHolder.getInstance().getBean("statCustomConfigManager");
    	
    	String customstatConfigURL = statCustomConfigManager.getCustomConfigFilePath();//"classpath:config/statistic/customstat-config/statistic-custom-config.xml";
    	customstatConfigURL = StaticMethod.getFilePathForUrl(customstatConfigURL);
    	
    	CustomConfig customConfig = (CustomConfig) ParseXmlService.create().xml2object(
    			CustomConfig.class, customstatConfigURL);
    	
    	Map excelConfigMap = new HashMap();
    	Map queryCongigMap = new HashMap();
    	ReportConfig reportConfig = customConfig.getReportConfig();
    	Report[] Reports = reportConfig.getReports();
    	for (int i = 0; i < Reports.length; i++) {
    		Report report = Reports[i];
    		String beanId = report.getBeanid();
    		IStatConfigManager statConfigManager = (IStatConfigManager)ApplicationContextHolder.getInstance().getBean(beanId);
    		excelConfigMap.putAll(statConfigManager.getExcelConfigMap());
    		queryCongigMap.putAll(statConfigManager.getQueryCongigMap());
		}
		
//	    	IStatConfigManager statCustomConfigManager = (IStatConfigManager)ApplicationContextHolder.getInstance().getBean("statCustomConfigManager");
    	statCustomConfigManager.setExcelConfigMap(excelConfigMap);
    	statCustomConfigManager.setQueryCongigMap(queryCongigMap);
    	
    	logger.info("\nExcel样式配置文件 excelConfigMap is "+ excelConfigMap);
    	logger.info("\nQuary算法配置文件 queryCongigMap is " + queryCongigMap);
    	logger.info("\n重新读取配置成功！！！！");
    }
	
}
