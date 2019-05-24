package com.boco.eoms.km.core.dataservice.map;

import java.util.Collection;
import java.util.HashMap;

public class EntityMap {

	private DataBaseMap dbMap;
	private TableMap tableMap;
	private HashMap fieldMapHash = new HashMap();
	private int fieldCount = 0;

	public DataBaseMap getDbMap() {
		return dbMap;
	}

	public void setDbMap(DataBaseMap dbMap) {
		this.dbMap = dbMap;
	}

	public TableMap getTableMap() {
		return tableMap;
	}

	public void setTableMap(TableMap tableMap) {
		this.tableMap = tableMap;
	}

	/**
	 * 返回此映射所包含的值的 collection 视图。
	 * 
	 * @return Collection
	 */
	public Collection fetchFieldList() {
		return this.fieldMapHash.values();
	}

	/**
	 * 返回指定键在此标识哈希映射中所映射的值，如果对于此键来说，映射不包含任何映射关系，则返回 null。
	 * 注意：此映射中“键”是不区分大小写的，用户输入的 aFieldName 值会自动转换成大写。
	 * 
	 * @param aFieldName
	 *            FieldMap 对象的 fieldName 属性值
	 * @return FieldMap
	 */
	public FieldMap fetchFieldMap(String aFieldName) {
		String key = aFieldName.toUpperCase();
		return (FieldMap)this.fieldMapHash.get(key);
	}

	/**
	 * 在此映射中关联指定值与指定键，值为 FieldMap，键为 FieldMap 的 fieldName 属性。
	 * 注意：此映射中“键”是不区分大小写的，用户输入的 aFieldName 值会自动转换成大写。
	 * 
	 * @param anFieldMap
	 *            FieldMap 对象。
	 */
	public void addFieldMap(FieldMap anFieldMap) {
		String key = anFieldMap.getFieldName().toUpperCase();
		this.fieldMapHash.put(key, anFieldMap);
		anFieldMap.setEntityMap(this);
		this.fieldCount = this.fieldMapHash.size();
	}

	public int getFieldCount() {
		return fieldCount;
	}

	/**
	 * 返回此实体对应的表名称
	 * 
	 * @return String
	 */
	public String getTableName() {
		String tableName = this.tableMap.getTableName();
		return (((tableName == null) || (tableName.length() < 1)) ? "Table name not found!"
				: tableName);
	}

	public HashMap fetchKeyFieldMap() {
		FieldMap value = (FieldMap)fieldMapHash.get("ID");
		HashMap keyMapHash = new HashMap();
		keyMapHash.put("ID", value);
		return keyMapHash;
	}
}