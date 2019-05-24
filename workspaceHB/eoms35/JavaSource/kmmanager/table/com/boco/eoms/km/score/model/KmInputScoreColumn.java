package com.boco.eoms.km.score.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:知识管理导入积分动态字段
 * </p>
 * <p>
 * Description:知识管理导入积分动态字段
 * </p>
 * <p>
 * 2009-08-04
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
public class KmInputScoreColumn extends BaseObject {

	/**
	 *
	 * 主键
	 *
	 */
	private java.lang.String id;
  
	public void setId(java.lang.String id){
		this.id= id;       
	}
  
	public java.lang.String getId(){
		return this.id;
	}
	
	/**
	 * 对应字段
	 */
	private java.lang.String columnId;
	
	public java.lang.String getColumnId() {
		return columnId;
	}

	public void setColumnId(java.lang.String columnId) {
		this.columnId = columnId;
	}
	
	/**
	 * 字段名称
	 */
	private String columnName;
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 *
	 * 显示标志位
	 *
	 */
	private java.lang.String showFlag;
   
	public void setShowFlag(java.lang.String showFlag){
		this.showFlag= showFlag;       
	}
   
	public java.lang.String getShowFlag(){
		return this.showFlag;
	}

	/**
	 *
	 * 显示排序
	 *
	 */
	private java.lang.String orderFlag;
   
	public void setOrderFlag(java.lang.String orderFlag){
		this.orderFlag= orderFlag;       
	}
   
	public java.lang.String getOrderFlag(){
		return this.orderFlag;
	}

	public boolean equals(Object o) {
		if( o instanceof KmInputScoreColumn ) {
			KmInputScoreColumn kmInputScoreColumn=(KmInputScoreColumn)o;
			if (this.id != null || this.id.equals(kmInputScoreColumn.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}