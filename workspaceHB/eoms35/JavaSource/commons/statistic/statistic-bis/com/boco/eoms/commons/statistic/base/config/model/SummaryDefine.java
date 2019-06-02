/*
 * Created on 2008-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.config.model;

import java.io.Serializable;

/**
 * @author liuxy
 * 
 * 注意有些属性不是简单的get方法,修改时注意.属性mappingProperty
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SummaryDefine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2169777829752724904L;

	private String id;
	
	private String markflg;//是否需要加'单引号 where id = 12345a 转换为 where id = '12345a'

	private int sequence;
	
	private boolean enable=true;
	

	private String column;
	
	private String defValue;

	private String id2nameService="";
	
	private String dictType;

	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	/**
	 * @return Returns the column.
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * @param column The column to set.
	 */
	public void setColumn(String column) {
		this.column = column;
	}



	/**
	 * @return Returns the enable.
	 */
	public boolean isEnable() {
		return enable;
	}
	/**
	 * @param enable The enable to set.
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getId2nameService() {
		return id2nameService;
	}
	public void setId2nameService(String id2nameService) {
		this.id2nameService = id2nameService;
	}
	public String getDefValue() {
		return defValue;
	}
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	public String getMarkflg() {
		return markflg;
	}
	public void setMarkflg(String markflg) {
		this.markflg = markflg;
	}


}
