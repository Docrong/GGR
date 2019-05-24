package com.boco.eoms.commons.statistic.base.ehcahe;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;

/**
 * 缓存
 * 
 * @author lizhenyou
 *
 */
public class StatEhCacheBean {

	/**
	 * 统计报表显示Detial的URL部分连接
	 */
	String dataUrl = "";
	
	/**
	 * Excel配置文件的路径
	 */
	String excelConfigURL = "";
	
	/**
	 * 数据结果集
	 */
	List listResult = null;
	
	/**
	 * Excel配置文件
	 */
	Sheet sheet = null;
	
	/**
	 * 页面输入条件
	 */
	Map conditionMap = null;
	
	/**
	 * 算法配置
	 */
	KpiDefine kpiDefine = null;
	
	public void setAll(List listResult,Sheet sheet ,Map conditionMap,KpiDefine kpiDefine,String dataUrl,String excelConfigURL)
	{
		this.listResult = listResult;
		this.sheet = sheet;
		this.conditionMap = conditionMap;
		this.kpiDefine = kpiDefine;
		this.dataUrl = dataUrl;
		this.excelConfigURL = excelConfigURL;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public List getListResult() {
		return listResult;
	}

	public void setListResult(List listResult) {
		this.listResult = listResult;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Map getConditionMap() {
		return conditionMap;
	}

	public void setConditionMap(Map conditionMap) {
		this.conditionMap = conditionMap;
	}

	public KpiDefine getKpiDefine() {
		return kpiDefine;
	}

	public void setKpiDefine(KpiDefine kpiDefine) {
		this.kpiDefine = kpiDefine;
	}

	public String getExcelConfigURL() {
		return excelConfigURL;
	}

	public void setExcelConfigURL(String excelConfigURL) {
		this.excelConfigURL = excelConfigURL;
	}
}
