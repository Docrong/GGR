package com.boco.eoms.commons.statistic.base.config.graphic;

public class FieldDefine {
	
	/**
	 * 与算法配置的id对应,标识指标字段的数据id
	 */
	private String id = null;
	
	/**
	 * 标识指标字段的名字，图例的名字
	 */
	private String name = null;
	
	/**
	 * 识该指标需要绘制的图形样式：线，柱，饼。取值范围：line,column,pie
	 */
	private String type = null;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
