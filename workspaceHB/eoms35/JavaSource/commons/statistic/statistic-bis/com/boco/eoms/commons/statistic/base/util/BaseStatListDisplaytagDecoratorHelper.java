package com.boco.eoms.commons.statistic.base.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.model.CustomSatatisticInfo;

public class BaseStatListDisplaytagDecoratorHelper extends TableDecorator {
	
	public String getReportType() {
		CustomSatatisticInfo vo = (CustomSatatisticInfo) getCurrentRowObject();
		return CustomStatUtil.typeToName(vo.getReportType());
	}
	
//	
//	public String getCustomDescribe()
//	{
//		CustomSatatisticInfo vo = (CustomSatatisticInfo) getCurrentRowObject();
//		Map conditionMap = vo.getConditionMap();
//		System.out.println("conditionMap " + conditionMap);
//		String[] condSql = String.valueOf(conditionMap.get("cityDeptId")).split(",");
//		
////		cityDeptId=14,1562,1566,1568
//		//conditionMap.get(key);
//		return "";
//	}
	
}
