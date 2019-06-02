package com.boco.eoms.commons.fileconfig.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 11:49:23 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMImportSheet {

	/**
	 * 类名
	 */
	private String className;

	/**
	 * sheetNum,标签页
	 */
	private Integer num;

	/**
	 * 列列表
	 */
	private List column = new ArrayList();

	public List getColumn() {
		return column;
	}

	public void setColumn(List column) {
		this.column = column;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
