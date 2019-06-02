package com.boco.eoms.km.core.dataservice.database;

import com.boco.eoms.km.core.dataservice.util.DataUtil;

public class OracleDataBase extends RelationalDB {

	protected Object convertVarchar(String aValue) {
		if (aValue.trim().equals(""))
			return null;
		if (DataUtil.nativeLength(aValue) >= 1280) {
			char[] chars = new char[aValue.length()];
			aValue.getChars(0, chars.length, chars, 0);
			return chars;
		}
		return aValue;
	}

	protected Object convertLongVarchar(String aValue) {
		if (aValue.trim().equals(""))
			return null;
		char[] chars = new char[aValue.length()];
		aValue.getChars(0, chars.length, chars, 0);
		return chars;
	}

}
