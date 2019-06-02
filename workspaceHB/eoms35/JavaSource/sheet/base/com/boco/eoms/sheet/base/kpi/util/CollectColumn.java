/*
 * Created on 2008-1-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.util;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CollectColumn {
	private String columnName;
	private String hql;
	private String defaultValue;
	private String columnValue;
	private int compareValue;
	private String muliType;
	/**
	 * @return Returns the columnName.
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName The columnName to set.
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return Returns the columnValue.
	 */
	public String getColumnValue() {
		return columnValue;
	}
	/**
	 * @param columnValue The columnValue to set.
	 */
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
	
	/**
	 * @return Returns the compareValue.
	 */
	public int getCompareValue() {
		return compareValue;
	}
	/**
	 * @param compareValue The compareValue to set.
	 */
	public void setCompareValue(int compareValue) {
		this.compareValue = compareValue;
	}
	/**
	 * @return Returns the defaultValue.
	 */
	public String getDefaultValue() {
		return defaultValue;
	}
	/**
	 * @param defaultValue The defaultValue to set.
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * @return Returns the hql.
	 */
	public String getHql() {
		return hql;
	}
	/**
	 * @param hql The hql to set.
	 */
	public void setHql(String hql) {
		this.hql = hql;
	}
	/**
	 * @return Returns the muliType.
	 */
	public String getMuliType() {
		return muliType;
	}
	/**
	 * @param muliType The muliType to set.
	 */
	public void setMuliType(String muliType) {
		this.muliType = muliType;
	}

}
