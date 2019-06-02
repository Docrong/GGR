package com.boco.eoms.commons.statistic.base.excelutil.sample;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.IPoiWork;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.ValidateBaseImpl;

public class SamplePoiWork extends ValidateBaseImpl implements IPoiWork {

	public String getBeanName() {
		return "com.boco.eoms.commons.statistic.base.excelutil.sample.SampleModel";
	}

	public String getsheetName() {
		return "成绩单";
	}
	
//	public boolean validate(Object obj) {
//		boolean f = false;
//		SimpleModel sm = (SimpleModel)obj;
//		if(!"b".equalsIgnoreCase(sm.getAttribute()))
//		{
//			f = true;
//		}
//		return f;
//	}

	public String displayName(String key) {
		Map map = new HashMap();
		
		map.put("name", "名称");
		map.put("attribute", "属性");
		map.put("profession", "专业");
		map.put("chinese", "语文");
		map.put("math", "数学");
		map.put("english", "英语");
		map.put("sum", "总和");
		return String.valueOf(map.get(key));
	}
}
