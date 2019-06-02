package com.boco.eoms.commons.statistic.base.excelutil.test;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.IPoiWork;

public class SimplePoiWork implements IPoiWork {

	/**
	 * 建立导入导出Excel,Bean
	 * 1.导出Excel模板的表头
	 * 2.导入Excel转换的模型
	 * @return bean实体类
	 */
	public String getBeanName() {
		return "com.boco.eoms.commons.statistic.base.excelutil.test.SimpleModel";
	}

	/**
	 * 生成模板sheet的名称
	 * @return
	 */
	public String getsheetName() {
		return "成绩单";
	}
	
	/**
	 * 验证数据信息,只对导入Excel的数据进行验证，对导出Excel模板无效
	 * @param obj 验证信息
	 * @return
	 */
	public boolean validate(Object obj) {
		return true;
	}

	/**
	 * 属性名称转显示的名称，客户需要看到的是显示的名称才能编辑Excel数据，客户并不希望看到英文的属性名。
	 * @param key
	 * @return
	 */
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
