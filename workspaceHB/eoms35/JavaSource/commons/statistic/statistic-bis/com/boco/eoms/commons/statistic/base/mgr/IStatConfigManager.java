/*
 * Created on 2008-1-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.mgr;

import java.util.Map;

import com.boco.eoms.commons.statistic.base.config.excel.Excel;
import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.report.ReportConfig;
import com.boco.eoms.commons.statistic.base.mgr.impl.ExcelConverter;

/**
 * @author liuxy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public interface IStatConfigManager {
	public String getConfigFilePath();
	
	public void setConfigFilePath(String configFilePath);

	public KpiConfig getKpiConfig()throws Exception;
	public KpiConfig getKpiConfig(String KpiConfigURL) throws Exception;
	
	public KpiConfig reloadKpiConfig()throws Exception;
	
	public int writeConfigFile(String path, Object cfg);

	public String getReportConfigFilePath() ;

	public void setReportConfigFilePath(String reportConfigFilePath);
	public ReportConfig reloadReportConfig() throws Exception ;
	public ReportConfig getReportConfig() throws Exception ;
	
	public Excel getExcelConfig(String excelConfigURL) throws Exception;
	
	public Excel getExcelConfig(String excelConfigURL,String[] dyColumSelectids,int sheetIndex) throws Exception;
	
	public String getExcelConfigFilePath() ;
	public void setExcelConfigFilePath(String reportConfigFilePath);
	
	public ExcelConverter getExcelConverter() ;

	public void setExcelConverter(ExcelConverter excelConverter);
	
	public Map getExcelConfigMap();

	public void setExcelConfigMap(Map excelConfigMap);
	
	public Map getQueryCongigMap();

	public void setQueryCongigMap(Map queryCongigMap);

}
