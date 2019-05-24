package com.boco.eoms.commons.statistic.base.excelutil.mgr;

/**
 * 导入导出Excel接口
 * @author lizhenyou
 *
 */
public interface IPoiWork  extends IValidate{

	/**
	 * 建立导入导出Excel,Bean
	 * 1.导出Excel模板的表头
	 * 2.导入Excel转换的模型
	 * @return bean实体类
	 */
	String getBeanName();
	
	/**
	 * 生成模板sheet的名称
	 * @return
	 */
	String getsheetName();
	
	/**
	 * 属性名称转显示的名称，客户需要看到的是显示的名称才能编辑Excel数据，客户并不希望看到英文的属性名。
	 * @param key
	 * @return
	 */
	String displayName(String key);
}
