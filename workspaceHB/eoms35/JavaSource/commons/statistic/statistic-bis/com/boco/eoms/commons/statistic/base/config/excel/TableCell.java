/**
 * 
 */
package com.boco.eoms.commons.statistic.base.config.excel;

import java.awt.Color;
import java.io.Serializable;

/**
 * @author lizhenyou
 *
 * sheet中绘制的表单元格
 */
public class TableCell  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5958660906376575445L;

	/**
	 * 字体
	 */
	private TableCellFont tableCellFont = null;
	
	/**
	 * 背景色
	 */
	private Color backgroundColor = null;
	
	/**
	 * 垂直对齐方式
	 */
	private String vAlign=null;
	
	/**
	 * 水平对齐方式
	 */
	private String hAlign=null;
	
	/**
	 * 单元格合并列数
	 */
	private String colSpan=null;
	
	/**
	 * 单元格合并行数
	 */
	private String rowSpan=null;
	
	/**
	 * td的样式
	 */
	private String classtype = "td_show"; 
	
	/**
	 * 单元格超连接
	 */
	private TableCellLink tableCellLink = null;

	public TableCellLink getTableCellLink() {
		return tableCellLink;
	}

	public void setTableCellLink(TableCellLink tableCellLink) {
		this.tableCellLink = tableCellLink;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public TableCellFont getTableCellFont() {
		return tableCellFont;
	}

	public void setTableCellFont(TableCellFont tableCellFont) {
		this.tableCellFont = tableCellFont;
	}

	public String getColSpan() {
		return colSpan;
	}

	public void setColSpan(String colSpan) {
		this.colSpan = colSpan;
	}

	public String getHAlign() {
		return hAlign;
	}

	public void setHAlign(String align) {
		hAlign = align;
	}

	public String getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(String rowSpan) {
		this.rowSpan = rowSpan;
	}

	public String getVAlign() {
		return vAlign;
	}

	public void setVAlign(String align) {
		vAlign = align;
	}

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

//	public float getCellWidth() {
//		return cellWidth;
//	}
//
//	public void setCellWidth(float cellWidth) {
//		this.cellWidth = cellWidth;
//	}
	
	
}
