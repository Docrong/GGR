package com.boco.eoms.commons.file.config.model;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 11:08:39 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMImportHeader {

	/**
	 * 行，标识导入/出时行顺序，xml配置中为属性
	 */
	private Integer col;

	/**
	 * excel,pdf,word列名称
	 */
	private String headerName;

	/**
	 * 对应的sql字段名
	 */
	private String fieldName;

	/**
	 * 字段类型
	 */
	private String type;

	/**
	 * 字段长度
	 */
	private Integer length;

	/**
	 * 是否为空
	 */
	private String isNull;

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
