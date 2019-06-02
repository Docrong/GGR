/**
 * 
 */
package com.boco.eoms.commons.statistic.base.config.excel;

import java.io.Serializable;

/**
 * @author lizhenyou
 *
 * sheet中绘制的表
 */
public class Table implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6265003310640399743L;
	/**
	 * table的样式
	 */
	private String id="list-table";
	private String border="1";
	private String cellspacing="1";
	private String cellpadding="1";
	private String classtype = "table_show";
	private String center = "center";
	
	/**
	 * 表格的style属性
	 */
	private String style = null;  
	
	private TableColWidth[]  tableColWidth = null;
	
	private TableContent tableHead = null;
	
	private TableContent tableBody = null;
	
	private TableContent tableFoot = null;
	
	/**
	 * 配置文件table的属性
	 */
	private int width= 0;
	
	private int head_start= 0;
	
	private int head_end= 0;
	
	private int body_start= 0;
	
	private int body_end= 0;
	
	private int foot_start= 0;
	
	private int foot_end= 0;
	
	
	public TableContent getTableBody() {
		return tableBody;
	}

	public void setTableBody(TableContent tableBody) {
		this.tableBody = tableBody;
	}

	public TableContent getTableFoot() {
		return tableFoot;
	}

	public void setTableFoot(TableContent tableFoot) {
		this.tableFoot = tableFoot;
	}

	public TableContent getTableHead() {
		return tableHead;
	}

	public void setTableHead(TableContent tableHead) {
		this.tableHead = tableHead;
	}

	public TableColWidth[] getTableColWidth() {
		return tableColWidth;
	}

	public void setTableColWidth(TableColWidth[] tableColWidth) {
		this.tableColWidth = tableColWidth;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getCellspacing() {
		return cellspacing;
	}

	public void setCellspacing(String cellspacing) {
		this.cellspacing = cellspacing;
	}

	public String getCellpadding() {
		return cellpadding;
	}

	public void setCellpadding(String cellpadding) {
		this.cellpadding = cellpadding;
	}

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHead_start() {
		return head_start;
	}

	public void setHead_start(int head_start) {
		this.head_start = head_start;
	}

	public int getHead_end() {
		return head_end;
	}

	public void setHead_end(int head_end) {
		this.head_end = head_end;
	}

	public int getBody_start() {
		return body_start;
	}

	public void setBody_start(int body_start) {
		this.body_start = body_start;
	}

	public int getBody_end() {
		return body_end;
	}

	public void setBody_end(int body_end) {
		this.body_end = body_end;
	}

	public int getFoot_start() {
		return foot_start;
	}

	public void setFoot_start(int foot_start) {
		this.foot_start = foot_start;
	}

	public int getFoot_end() {
		return foot_end;
	}

	public void setFoot_end(int foot_end) {
		this.foot_end = foot_end;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
