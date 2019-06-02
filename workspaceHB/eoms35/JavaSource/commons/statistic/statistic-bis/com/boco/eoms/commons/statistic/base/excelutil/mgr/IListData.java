package com.boco.eoms.commons.statistic.base.excelutil.mgr;

import java.util.List;

public interface IListData extends IStatExcel{

	/**
	 * 最终需要显示的数据模型，List中存放的是0~n个map。z
	 * @return
	 */
	List getData();
	
}
