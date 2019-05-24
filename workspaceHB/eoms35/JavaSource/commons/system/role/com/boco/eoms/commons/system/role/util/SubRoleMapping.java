/*
 * Created on 2007-9-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubRoleMapping {
	private String businessName;	//角色区分度的业务名称
	private String pojoName;		//对应的pojo名称
	private String pojoPrimaryKey;	//对应的pojo的主键名
	private String columnName;		//区分度表的pojo字段名
	private Integer num;			//序号

	/**
	 * @return Returns the pojoName.
	 */
	public String getPojoName() {
		return pojoName;
	}
	/**
	 * @param pojoName The pojoName to set.
	 */
	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}
	/**
	 * @return Returns the businessName.
	 */
	public String getBusinessName() {
		return businessName;
	}
	/**
	 * @param businessName The businessName to set.
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
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
	 * @return Returns the num.
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * @param num The num to set.
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	

	/**
	 * @return Returns the pojoPrimaryKey.
	 */
	public String getPojoPrimaryKey() {
		return pojoPrimaryKey;
	}
	/**
	 * @param pojoPrimaryKey The pojoPrimaryKey to set.
	 */
	public void setPojoPrimaryKey(String pojoPrimaryKey) {
		this.pojoPrimaryKey = pojoPrimaryKey;
	}
}
