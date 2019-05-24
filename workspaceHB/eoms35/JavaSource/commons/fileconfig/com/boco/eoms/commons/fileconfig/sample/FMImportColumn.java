package com.boco.eoms.commons.fileconfig.sample;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Mar 26, 2007 11:45:53 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class FMImportColumn {

	/**
	 * 导入/导出的开始行数
	 */
	private Integer startRow;

	/**
	 * 导入/导出的结束行数
	 */
	private Integer endRow;

	private FMImportHeaderMapping headerMapping;

	public FMImportHeaderMapping getHeaderMapping() {
		return headerMapping;
	}

	public void setHeaderMapping(FMImportHeaderMapping headerMapping) {
		this.headerMapping = headerMapping;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

}
