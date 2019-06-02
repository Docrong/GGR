package com.boco.eoms.commons.file.config.model;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 27, 2007 11:14:16 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMExportTitle {
	/**
	 * 开始列
	 */
	private Integer startCol;

	/**
	 * 结束列
	 */
	private Integer endCol;

	/**
	 * 开始行
	 */
	private Integer startRow;

	/**
	 * 结束行
	 */
	private Integer endRow;

	/**
	 * 文字样式
	 */
	private FMExportFont font;

	public Integer getEndCol() {
		return endCol;
	}

	public void setEndCol(Integer endCol) {
		this.endCol = endCol;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public FMExportFont getFont() {
		return font;
	}

	public void setFont(FMExportFont font) {
		this.font = font;
	}

	public Integer getStartCol() {
		return startCol;
	}

	public void setStartCol(Integer startCol) {
		this.startCol = startCol;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

}
