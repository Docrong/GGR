package com.boco.eoms.km.core.hibernate.dialect;

public class InformixDialect extends Dialect {

	public String getLimitString(String querySelect, int offset, int limit) {
		if (offset > 0)
			throw new UnsupportedOperationException("informix has no offset");
		return new StringBuffer(querySelect.length() + 8).append(querySelect)
				.insert(querySelect.toLowerCase().indexOf("select") + 6,
						" first " + limit).toString();
	}

	public String getAddColumnString() {
		return "add";
	}

	public boolean supportsIdentityColumns() {
		return true;
	}

	public boolean hasDataTypeInIdentityColumn() {
		return false;
	}

	public boolean supportsSequences() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public boolean useMaxForLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return false;
	}

	public boolean supportsVariableLimit() {
		return false;
	}

	public boolean supportsCurrentTimestampSelection() {
		return true;
	}

	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}

	public String getCurrentTimestampSelectString() {
		return "select distinct current timestamp from informix.systables";
	}
	
	public boolean addLimitParameters(){
		return false;
	}
}
