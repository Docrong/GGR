package com.boco.eoms.commons.statistic.base.excelutil.mgr;

import java.util.Map;


public interface IStatExcel{

	/**
	 * 处理表头表尾信息
	 * @return
	 */
	Map getInfoMap();
	
	/**
	 * excel样式模板的绝对路径
	 * @return
	 */
	String getExcelPath();
	
	/**
	 * excel样式模板中使用sheet的名称
	 * @return
	 */
	String getSheetName();
}
