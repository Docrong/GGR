/*
 * Created on 2007-11-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.util;

import java.util.List;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleFilter {
	private String businessName;
	private String name;
	private String dictId;
	private String sub;
	private String htmlType;
	private String multiple;
	private String size;
	private List sheetInfo;
	/**
	 * @return Returns the sheetInfos.
	 */
	public List getSheetInfo() {
		return sheetInfo;
	}
	/**
	 * @param sheetInfos The sheetInfos to set.
	 */
	public void setSheetInfo(List sheetInfo) {
		this.sheetInfo = sheetInfo;
	}
	/**
	 * @return Returns the multiple.
	 */
	public String getMultiple() {
		return multiple;
	}
	/**
	 * @param multiple The multiple to set.
	 */
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}
	/**
	 * @return Returns the size.
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size The size to set.
	 */
	public void setSize(String size) {
		this.size = size;
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
	 * @return Returns the dictId.
	 */
	public String getDictId() {
		return dictId;
	}
	/**
	 * @param dictId The dictId to set.
	 */
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	/**
	 * @return Returns the htmlType.
	 */
	public String getHtmlType() {
		return htmlType;
	}
	/**
	 * @param htmlType The htmlType to set.
	 */
	public void setHtmlType(String htmlType) {
		this.htmlType = htmlType;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}  
	/**
	 * @return Returns the sub.
	 */
	public String getSub() {
		return sub;
	}
	/**
	 * @param sub The sub to set.
	 */
	public void setSub(String sub) {
		this.sub = sub;
	}
}
