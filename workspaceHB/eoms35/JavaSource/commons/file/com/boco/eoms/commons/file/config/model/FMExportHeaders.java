package com.boco.eoms.commons.file.config.model;

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
 * Mar 27, 2007 11:15:27 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMExportHeaders {

	// /**
	// * 开始列
	// */
	// private Integer startCol;
	//
	// /**
	// * 结束列
	// */
	// private Integer endCol;

	/**
	 * 开始行
	 */
	private Integer startRow;

	/**
	 * 结束行
	 */
	private Integer endRow;

	/**
	 * header列表
	 */
	private List header = new ArrayList();

	// public Integer getEndCol() {
	// return endCol;
	// }
	//
	// public void setEndCol(Integer endCol) {
	// this.endCol = endCol;
	// }
	// public Integer getStartCol() {
	// return startCol;
	// }
	//
	// public void setStartCol(Integer startCol) {
	// this.startCol = startCol;
	// }

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public List getHeader() {
		return header;
	}

	public void setHeader(List header) {
		this.header = header;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

}
