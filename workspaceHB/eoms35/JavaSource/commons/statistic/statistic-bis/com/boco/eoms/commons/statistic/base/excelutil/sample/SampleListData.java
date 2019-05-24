package com.boco.eoms.commons.statistic.base.excelutil.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.IListData;

public class SampleListData implements IListData {

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
	
	public List getData() {
		Map d1 = new HashMap();
        d1.put("s1", "处理人一");
        d1.put("s2", "无线");
        d1.put("s3", "天津");
        d1.put("f1", "80");
        d1.put("f2", "86");
        d1.put("f3", "14");
        d1.put("f4", "80");
        d1.put("f5", "86");
        d1.put("f6", "14");
        Map d2 = new HashMap();
        d2.put("s1", "处理人三");
        d2.put("s2", "无线");
        d2.put("s3", "南京");
        d2.put("f1", "80");
        d2.put("f2", "86");
        d2.put("f3", "14");
        d2.put("f4", "12");
        d2.put("f5", "89");
        d2.put("f6", "144");
        Map d3 = new HashMap();
        d3.put("s1", "处理人一");
        d3.put("s2", "无线");
        d3.put("s3", "南京");
        d3.put("f1", "80");
        d3.put("f2", "86");
        d3.put("f3", "14");
        d3.put("f4", "10");
        d3.put("f5", "86");
        d3.put("f6", "9");
        Map d4 = new HashMap();
        d4.put("s1", "处理人一");
        d4.put("s2", "无线");
        d4.put("s3", "南京");
        d4.put("f1", "80");
        d4.put("f2", "86");
        d4.put("f3", "14");
        d4.put("f4", "30");
        d4.put("f5", "68");
        d4.put("f6", "19");
        Map d5 = new HashMap();
        d5.put("s1", "处理人四");
        d5.put("s2", "无线");
        d5.put("s3", "北京");
        d5.put("f1", "80");
        d5.put("f2", "86");
        d5.put("f3", "14");
        d5.put("f4", "330");
        d5.put("f5", "70");
        d5.put("f6", "29");
        Map d6 = new HashMap();
        d6.put("s1", "处理人二");
        d6.put("s2", "无线");
        d6.put("s3", "北京");
        d6.put("f1", "80");
        d6.put("f2", "86");
        d6.put("f3", "14");
        d6.put("f4", "30");
        d6.put("f5", "18");
        d6.put("f6", "29");
        
        Map d7 = new HashMap();
        d7.put("s1", "处理人二");
        d7.put("s2", "无线");
        d7.put("s3", "北京");
        d7.put("f1", "80");
        d7.put("f2", "86");
        d7.put("f3", "14");
        d7.put("f4", "30");
        d7.put("f5", "18");
        d7.put("f6", "29");
        
        Map d8 = new HashMap();
        d8.put("s1", "处理人三");
        d8.put("s2", "无线");
        d8.put("s3", "上海");
        d8.put("f1", "80");
        d8.put("f2", "86");
        d8.put("f3", "14");
        d8.put("f4", "30");
        d8.put("f5", "18");
        d8.put("f6", "29");
        
        Map d9 = new HashMap();
        d9.put("s1", "处理人三");
        d9.put("s2", "无线");
        d9.put("s3", "上海");
        d9.put("f1", "80");
        d9.put("f2", "86");
        d9.put("f3", "14");
        d9.put("f4", "30");
        d9.put("f5", "18");
        d9.put("f6", "29");
        
        List listResult = new ArrayList();
        listResult.add(d1);
        listResult.add(d2);
        listResult.add(d3);
        listResult.add(d4);
        listResult.add(d5);
        listResult.add(d6);
        listResult.add(d7);
        listResult.add(d8);
        listResult.add(d9);
        
        return listResult;
	}
}
