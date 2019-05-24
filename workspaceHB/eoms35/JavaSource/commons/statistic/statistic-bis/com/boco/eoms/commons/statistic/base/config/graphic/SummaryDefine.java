package com.boco.eoms.commons.statistic.base.config.graphic;

public class SummaryDefine {
	
	/**
	 * 与算法配置的id对应，标识分组字段的数据id
	 */
	private String id = null;
	
	/**
	 * 标识分组字段的名字
	 */
	private String name = null;
	
	/**
	 * 字典的类型
	 */
	private String dictType = null;
	
	/**
	 * id 转Name ,beanid
	 */
	private String id2nameService;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId2nameService() {
		return id2nameService;
	}

	public void setId2nameService(String id2nameService) {
		this.id2nameService = id2nameService;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
}
