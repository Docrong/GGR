package com.boco.eoms.commons.statistic.base.excelutil.sample;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.IXmlData;
import com.boco.eoms.commons.statistic.base.util.FileUtil;

public class SampleXmlData implements IXmlData {

	public String getData() {
		String path = "D:/poi/report.xml";
		return FileUtil.readFile(path);
	}

	public String getExcelPath() {
		return "D:/poi/statistic-config-excel-commonfault_T_resolve_KPI4_oracle.xls";
	}

	public Map getInfoMap() {
		Map map = new HashMap();
		map.put("lasttime", "2009-8-9 22:50:00");
		map.put("begintime", "2001-8-6 22:10:00");
		map.put("endtime", "2005-5-3 22:00:00");
		return map;
	}

	public String getSheetName() {
		return "故障解决-按处理人查询";
	}
}
