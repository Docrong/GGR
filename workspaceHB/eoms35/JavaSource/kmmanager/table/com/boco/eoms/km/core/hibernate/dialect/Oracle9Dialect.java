package com.boco.eoms.km.core.hibernate.dialect;

public class Oracle9Dialect extends Dialect {

	public String getLimitString(String sql, boolean hasOffset) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (hasOffset) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
			pagingSelect.append(sql);
			pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
		} else {
			pagingSelect.append("select * from ( ");
			pagingSelect.append(sql);
			pagingSelect.append(" ) where rownum <= ?");
		}

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}

	public String getCascadeConstraintsString() {
		return " cascade constraints";
	}

	public boolean dropConstraints() {
		return false;
	}

	public String getForUpdateNowaitString() {
		return " for update nowait";
	}

	public boolean supportsSequences() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public boolean bindLimitParametersInReverseOrder() {
		return true;
	}

	public boolean useMaxForLimit() {
		return true;
	}

	public boolean forUpdateOfColumns() {
		return true;
	}

	public String getQuerySequencesString() {
		return "select sequence_name from user_sequences";
	}

	public String getSelectGUIDString() {
		return "select rawtohex(sys_guid()) from dual";
	}

	public boolean supportsUnionAll() {
		return true;
	}

	public boolean supportsCommentOn() {
		return true;
	}

	public boolean supportsTemporaryTables() {
		return true;
	}

	public String getCreateTemporaryTableString() {
		return "create global temporary table";
	}

	public String getCreateTemporaryTablePostfix() {
		return "on commit delete rows";
	}

	public boolean dropTemporaryTableAfterUse() {
		return false;
	}

	public boolean supportsCurrentTimestampSelection() {
		return true;
	}

	public String getCurrentTimestampSelectString() {
		return "select systimestamp from dual";
	}

	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}
}
