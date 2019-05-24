/**
 * 
 */
package com.boco.eoms.commons.statistic.base.config.excel;

import java.io.Serializable;

/**
 * @author lizhenyou
 *
 * 字体
 */
public class TableCellFont implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6808605840459504297L;

	/**
	 * 字体
	 */
	private String face = null;
	
	/**
	 * 字号
	 */
	private int size =0;
	
	/**
	 * 颜色
	 */
	private String color = null;
	
	/**
	 * 加粗
	 */
	private String style = null;

	/**
	 * 单元格内容
	 */
	private String showText = null;
	

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
