package com.boco.eoms.km.core.hibernate.dialect;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.core.hibernate.cfg.Environment;

/**
 * 
 * @author zhangxb
 * 
 */
public class Dialect {

	public static Dialect getDialect() {
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			return new InformixDialect();
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			return new OracleDialect();			
		} 
		else if (Environment.DIALECT.equals("INFORMIX")) {
			return new Oracle9Dialect();
		}
		return null;
	}

	public String getLimitString(String querySelect, boolean hasOffset) {
		throw new UnsupportedOperationException("paged queries not supported");
	}

	public String getLimitString(String querySelect, int offset, int limit) {
		boolean hasOffset = offset > 0;
		return getLimitString(querySelect, hasOffset);
	}

	public boolean supportsLimit() {
		return false;
	}

	public boolean supportsLimitOffset() {
		return supportsLimit();
	}

	public boolean useMaxForLimit() {
		return false;
	}

	public boolean bindLimitParametersFirst() {
		return false;
	}

	public boolean bindLimitParametersInReverseOrder() {
		return false;
	}

	public boolean isScrollableResultSetsEnabled() {
		return false;
	}
	
	public boolean addLimitParameters(){
		return true;
	}
}
