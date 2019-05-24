/*
 * Created on 2008-1-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.util;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CollectInfo {
	private CollectColumn[] columns;
	/**
	 * @return Returns the columns.
	 */
	public CollectColumn[] getColumns() {
		return columns;
	}
	/**
	 * @param columns The columns to set.
	 */
	public void setColumns(CollectColumn[] columns) {
		this.columns = columns;
	}
	
	public CollectColumn getFlowDefineById(String columnName) {
		CollectColumn column = new CollectColumn();
		if (this.getColumns() != null && this.getColumns().length > 0) {
			for (int i = 0; i < this.getColumns().length; i++) {
				if((this.getColumns()[i]).getColumnName().equals(columnName)){
					column = this.getColumns()[i];
					break;
				}
			}
		}
		return column;
	}
}
