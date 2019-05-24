package com.boco.eoms.commons.statistic.base.config.model;

import java.io.Serializable;

/**
 * @author liuxy
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FieldDefine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1762349422645881625L;
	private String listSqlParams;
	private String viewName="";

	private String listVoMethod="";
	
//	private String asName;
	
	private String id;

	private int sequence=99;
	
	private String defValue="0";
	
	private String statType="";
	
	private String statSql="";
	
	private String listHsql="";
	
	private String listCountSelectSql = "";
	
	private String listCount = "";
	
	//private String statSqlDicts="";
	
public String getListCount() {
		return listCount;
	}
	public void setListCount(String listCount) {
		this.listCount = listCount;
	}
	//	public String getStatSqlDicts() {
//		return statSqlDicts;
//	}
//	public void setStatSqlDicts(String statSqlDicts) {
//		this.statSqlDicts = statSqlDicts;
//	}
	/**
	 * @return Returns the listHsql.
	 */
	public String getListHsql() {
		return listHsql;
	}
	/**
	 * @param listHsql The listHsql to set.
	 */
	public void setListHsql(String listHsql) {
		this.listHsql = listHsql;
	}
	/**
	 * @return Returns the poName.
	 */
//	public String getAsName() {
//		return asName;
//	}
	/**
	 * @param poName The poName to set.
	 */
//	public void setAsName(String asName) {
//		this.asName = asName;
//	}
	/**
	 * @return Returns the viewName.
	 */
	public String getViewName() {
		return viewName;
	}
	/**
	 * @param viewName The viewName to set.
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	/**
	 * @return Returns the statSql.
	 */
	public String getStatSql() {
		return statSql;
	}
	/**
	 * @param statSql The statSql to set.
	 */
	public void setStatSql(String statSql) {
		this.statSql = statSql;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the statType.
	 */
	public String getStatType() {
		return statType;
	}
	/**
	 * @param statType The statType to set.
	 */
	public void setStatType(String statType) {
		this.statType = statType;
	}
	/**
	 * @return Returns the sequence.
	 */
	public int getSequence() {
		return sequence;
	}
	/**
	 * @param sequence The sequence to set.
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getDefValue() {
		return defValue;
	}
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	public String getListSqlParams() {
		return listSqlParams;
	}
	public void setListSqlParams(String listSqlParams) {
		this.listSqlParams = listSqlParams;
	}
	public String getListVoMethod() {
		return listVoMethod;
	}
	public void setListVoMethod(String listVoMethod) {
		this.listVoMethod = listVoMethod;
	}
	public String getListCountSelectSql() {
		return listCountSelectSql;
	}
	public void setListCountSelectSql(String listCountSelectSql) {
		this.listCountSelectSql = listCountSelectSql;
	}
}
