package com.boco.eoms.km.core.dataservice.database;

public class InformixDataBase extends RelationalDB {

	protected Object convertVarchar(String aValue) {
		return aValue;
	}

	protected Object convertLongVarchar(String aValue) {
		return aValue;
	}

}
