/**
 * 
 */
package com.boco.eoms.commons.statistic.base.config.excel;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizhenyou
 *
 * sheet中绘制的表行
 */
public class TableRow implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8172522173069948980L;

	private TableCell[] tableCells = null;
	
	private float cellHeight = 0;
	
	/**
	 * tr的样式
	 */
	private String classtype = "tr_show";

	public float getCellHeight() {
		return cellHeight;
	}

	public void setCellHeight(float cellHeight) {
		this.cellHeight = cellHeight;
	}

	public TableCell[] getTableCells() {
		return tableCells;
	}

	public void setTableCells(TableCell[] tableCells) {
		this.tableCells = tableCells;
	}
	
	public void setTableCells(List tableCellslist)
	{
		TableCell[] tmp = new TableCell[tableCellslist.size()];
		for(int f=0;f<tableCellslist.size();f++)
		{
			tmp[f] = (TableCell)tableCellslist.get(f);
		}
		this.tableCells = tmp;
	}

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
}
