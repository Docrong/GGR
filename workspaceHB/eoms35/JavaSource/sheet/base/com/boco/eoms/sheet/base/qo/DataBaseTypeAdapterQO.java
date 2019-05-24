package com.boco.eoms.sheet.base.qo;

import com.boco.eoms.base.util.ApplicationContextHolder;

public class DataBaseTypeAdapterQO {
	
	public String getDateTypeAdapter(String columnNameValue) {
		String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
		
		if (hqlDialect.equals("org.hibernate.dialect.OracleDialect")) {
			return columnNameValue = "to_date('" + columnNameValue + "','yyyy-MM-dd HH24:mi:ss')";
		}  else {
			return "'" + columnNameValue + "'";
		}	
		
	}

}
