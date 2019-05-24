/**
 * 
 */
package com.boco.eoms.commons.statistic.base.config.excel;

import java.io.Serializable;

/**
 * @author lizhneyou
 *
 * 表格的列宽
 */
public class TableColWidth  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2651426427254831960L;
	/**
	 * 列宽
	 */
	float colwidth = 0;

	public float getColwidth() {
		return colwidth;
	}

	public void setColwidth(float colwidth) {
		this.colwidth = colwidth;
	}
}
