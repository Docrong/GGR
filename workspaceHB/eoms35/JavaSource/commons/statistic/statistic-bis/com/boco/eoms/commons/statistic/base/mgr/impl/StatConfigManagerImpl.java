/*
 * Created on 2008-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.mgr.impl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import com.boco.eoms.commons.statistic.base.config.excel.Excel;
import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.report.ReportConfig;
import com.boco.eoms.commons.statistic.base.mgr.IStatConfigManager;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

/**
 * @author liuxy
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StatConfigManagerImpl implements IStatConfigManager {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private String configFilePath; //算法配置
	private String reportConfigFilePath; //展现模板配置
	
	private ExcelConverter excelConverter;//执行解析Excel
	private String excelConfigFilePath; //Excel配置文件路径

	private KpiConfig kpiConfig;
	private ReportConfig reportConfig;
	
	//配置Excel文件路径 key为url传入的参数，value对应文件名称
	private Map excelConfigMap = null;
	
	private Map queryCongigMap = null;
	
 	//管理excel配置文件，如果已经读取过了，就直接使用，不再浪费资源去从新读取配置
	private Map excelConfigManager = new HashMap();
	
	//管理查询配置文件，如果已经读取过了，就直接使用，不再浪费资源去从新读取配置
	private Map quaryConfigManager =  new HashMap();
	
	//是否每次都从新获取配置文件
	private boolean reloadConfig = false;
	
	//总体设置是否重新读取配置文件
//	public static boolean reloadConfigManger = true;
	
	/**
	 * @return Returns the configFilePath.
	 */
	public String getConfigFilePath() {
		return configFilePath;
	}

	/**
	 * @param configFilePath
	 *          The configFilePath to set.
	 */
	public void setConfigFilePath(String configFilePath) {
		
		this.configFilePath =configFilePath;
//		setReportConfigFilePath(configFilePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.statistic.base.mgr.IStatConfigManager#getKpiConfig()
	 */
	public KpiConfig getKpiConfig() throws Exception {
		kpiConfig = reloadKpiConfig(); //注销此行停止 实时刷新配置,
		if (kpiConfig == null) {
			return reloadKpiConfig();
		} else {
			return kpiConfig;
		}
	}
	
	public KpiConfig getKpiConfig(String kpiConfigURL) throws Exception {
		String URL = String.valueOf(queryCongigMap.get(kpiConfigURL));
		if(URL == null || queryCongigMap.get(kpiConfigURL) == null)
		{
			logger.info("\n kpiConfigURL path is " + kpiConfigURL);
			throw new Exception("请在查看Spring配置文件中是否正确配置queryCongigMap");
		}
		kpiConfig = reloadKpiConfig(URL); //注销此行停止 实时刷新配置,
		if (kpiConfig == null) {
			return reloadKpiConfig(URL);
		} else {
			return kpiConfig;
		}
	}

	public ReportConfig getReportConfig() throws Exception {
		reportConfig = reloadReportConfig();//注销此行停止 实时刷新配置,
		if (reportConfig == null) {
			return reloadReportConfig();
		} else {
			return reportConfig;
		}
	}
	
	
	public KpiConfig reloadKpiConfig() throws Exception {
		
		Object obj = quaryConfigManager.get(getConfigFilePath());
		
		if(!reloadConfig && obj != null)
		{
			logger.info("内存中已经存在查询配置，不需要从新读取");
			return (KpiConfig)obj;
		}
		
		try {
			KpiConfig kpiConfig = (KpiConfig) ParseXmlService.create().xml2object(
					KpiConfig.class, StaticMethod.getFilePathForUrl(getConfigFilePath()));
			this.kpiConfig = kpiConfig;
			
			//保存算法配置到內存中，以便下次直接读取。
			this.quaryConfigManager.put(getConfigFilePath(), kpiConfig);
			return kpiConfig;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("读取报表算法配置文件'" + getConfigFilePath() + "'出错!");
		}
	}
	
	public KpiConfig reloadKpiConfig(String KpiConfigURL) throws Exception {
		
		if(KpiConfigURL == null || "".equalsIgnoreCase(KpiConfigURL))
		{
			logger.info("读取默认的excel样式文件");
			return reloadKpiConfig();
		}
		
		Object obj = quaryConfigManager.get(KpiConfigURL);
		
		if(!reloadConfig && obj != null)
		{
			logger.info("内存中已经存在查询配置，不需要从新读取");
			return (KpiConfig)obj;
		}
		
		try {
			KpiConfig kpiConfig = (KpiConfig) ParseXmlService.create().xml2object(
					KpiConfig.class, StaticMethod.getFilePathForUrl(KpiConfigURL));
			this.kpiConfig = kpiConfig;
			
			//保存算法配置到內存中，以便下次直接读取。
			this.quaryConfigManager.put(KpiConfigURL, kpiConfig);
			return kpiConfig;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("读取报表算法配置文件'" + KpiConfigURL + "'出错!");
		}
	}

	public ReportConfig reloadReportConfig() throws Exception {

		try {
			ReportConfig reportConfig = (ReportConfig) ParseXmlService.create().xml2object(
					ReportConfig.class, StaticMethod.getFilePathForUrl(getReportConfigFilePath()));
			this.reportConfig = reportConfig;
			return reportConfig;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("读取报表展现配置文件'" + getReportConfigFilePath()+ "'出错!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.statistic.base.mgr.IStatConfigManager#writeConfigFile(java.lang.String,
	 *      java.lang.Object)
	 */
	public int writeConfigFile(String path, Object cfg) {
		// TODO Auto-generated method stub
		try {
			
			File file = new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			Writer writer = new OutputStreamWriter(new FileOutputStream(file),
					"UTF-8");
			Marshaller marshaller = new Marshaller(writer);
			marshaller.setEncoding("UTF-8");
			marshaller.marshal(cfg);
			
			writer.close();
			writer = null;
		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public String getReportConfigFilePath() {
		return reportConfigFilePath;
	}

	public void setReportConfigFilePath(String reportConfigFilePath) {
		this.reportConfigFilePath = reportConfigFilePath;
	}
	
	public Excel getExcelConfig(String excelConfigURL) throws Exception {
		String URL = String.valueOf(excelConfigMap.get(excelConfigURL));
		if(URL == null || excelConfigMap.get(excelConfigURL) == null)
		{
			logger.info("\n excelConfigURL path is " + excelConfigURL);
			throw new Exception("请在查看Spring配置文件中是否正确配置excelConfigMap");
		}
		Excel excelConfig = reloadExcelConfig(URL);//注销此行停止 实时刷新配置,
		if (excelConfig == null) {
			return reloadExcelConfig(URL);
		} else {
			return excelConfig;
		}
	}
	
	public Excel getExcelConfig(String excelConfigURL,String[] dyColumSelectids,int sheetIndex) throws Exception
	{
		String URL = String.valueOf(excelConfigMap.get(excelConfigURL));
		if(URL == null || excelConfigMap.get(excelConfigURL) == null)
		{
			logger.info("\n excelConfigURL path is " + excelConfigURL);
			throw new Exception("请在查看Spring配置文件中是否正确配置excelConfigMap");
		}
		Excel excelConfig = reloadExcelConfig(URL,dyColumSelectids,sheetIndex);//注销此行停止 实时刷新配置,
		if (excelConfig == null) {
			return reloadExcelConfig(URL);
		} else {
			return excelConfig;
		}
	}

	private Excel reloadExcelConfig(String ExcelConfigURL) throws Exception {
		Object obj = excelConfigManager.get(ExcelConfigURL);
		if(!reloadConfig && obj != null)
		{
			logger.info("内存中已经存在样式配置，不需要从新读取");
			return (Excel)obj;
		}
		
		Excel excelConfig = excelConverter.parseExcelToConfig(StaticMethod.getFilePathForUrl(ExcelConfigURL));
		
		//保存样式配置到內存中，以便下次直接读取。
		this.excelConfigManager.put(ExcelConfigURL, excelConfig);
		return excelConfig;
	}
	
	private Excel reloadExcelConfig(String ExcelConfigURL,String[] dyColumSelectids,int sheetIndex) throws Exception {
		Object obj = excelConfigManager.get(ExcelConfigURL);
		if(!reloadConfig && obj != null)
		{
			logger.info("内存中已经存在样式配置，不需要从新读取");
			return (Excel)obj;
		}
		
		Excel excelConfig = excelConverter.parseExcelToConfig(StaticMethod.getFilePathForUrl(ExcelConfigURL),dyColumSelectids,sheetIndex);
		
		//保存样式配置到內存中，以便下次直接读取。
		this.excelConfigManager.put(ExcelConfigURL, excelConfig);
		return excelConfig;
	}

	public String getExcelConfigFilePath() {
		return this.excelConfigFilePath;
	}

	public void setExcelConfigFilePath(String ExcelConfigFilePath) {
		this.excelConfigFilePath = ExcelConfigFilePath;
	}

	public ExcelConverter getExcelConverter() {
		return excelConverter;
	}

	public void setExcelConverter(ExcelConverter excelConverter) {
		this.excelConverter = excelConverter;
	}

	public boolean isReloadConfig() {
		return reloadConfig;
	}

	public void setReloadConfig(boolean reloadConfig) {
		this.reloadConfig = reloadConfig;
	}

	public Map getExcelConfigMap() {
		return excelConfigMap;
	}

	public void setExcelConfigMap(Map excelConfigMap) {
		this.excelConfigMap = excelConfigMap;
	}

	public Map getQueryCongigMap() {
		return queryCongigMap;
	}

	public void setQueryCongigMap(Map queryCongigMap) {
		this.queryCongigMap = queryCongigMap;
	}
}
