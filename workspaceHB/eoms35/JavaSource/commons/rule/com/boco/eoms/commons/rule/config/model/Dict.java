package com.boco.eoms.commons.rule.config.model;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * May 10, 2007 4:46:07 PM
 * </p>
 * 
 * @author ��
 * @version 1.0
 * 
 */
public class Dict {

	/**
	 * 通过moduleId取所有所属类型id,name
	 */
	private String dictSql;

	/**
	 * 取type的所有id，类型
	 */
	private String dictTypeSql;

	public String getDictTypeSql() {
		return dictTypeSql;
	}

	public void setDictTypeSql(String dictTypeSql) {
		this.dictTypeSql = dictTypeSql;
	}

	public String getDictSql() {
		return dictSql;
	}

	public void setDictSql(String dictSql) {
		this.dictSql = dictSql;
	}

}
