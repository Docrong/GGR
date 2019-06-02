package com.boco.eoms.sheet.ptnpretreatmentrule.util;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.IPoiWork;

public class PtnPretreatmentRuleIPoiWork implements IPoiWork {

	public String displayName(String key) {
		Map map = new HashMap();
	    map.put("factory", "厂家");
	    map.put("equipmentType", "设备类型");
	    map.put("alarmName", "告警名称");
	    map.put("alatmID", "告警ID");
	    map.put("faultDealDesc", "故障原因及处理措施");
	    map.put("faultReasonSort1", "归因一级");
	    map.put("faultReasonSort2", "归因二级");
	    map.put("faultReasonSort3", "归因三级");
	    map.put("preDealRelation", "预处理对应关系");
	    return String.valueOf(map.get(key));
	}

	public String getBeanName() {
		return "com.boco.eoms.sheet.ptnpretreatmentrule.model.PtnPretreatmentRule";
	}

	public String getsheetName() {
		return "传输自动归档模板";
	}

	public boolean validate(Object obj) {
		return true;
	}

}
