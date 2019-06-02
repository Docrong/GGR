package com.boco.eoms.commons.statistic.base.excelutil.mgr;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.DyTableInfo;


public interface DyTableInfos {
	
	DyTableInfo[] getDyTableInfos();
	
	DyTableInfo getDyTableInfoById(String id);
}
