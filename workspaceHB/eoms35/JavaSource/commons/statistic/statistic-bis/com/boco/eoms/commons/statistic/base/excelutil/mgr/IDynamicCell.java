package com.boco.eoms.commons.statistic.base.excelutil.mgr;

import java.util.Map;

public interface IDynamicCell {

	/**
	 * 动态单元格的信息
	 * key:字典的名称 例如：00100
	 * value:显示的名称 例如：北京
	 * @return
	 */
	Map getDynamicCell();
}
