package com.boco.eoms.km.core.hibernate.dialect;

public class OracleDialect extends Oracle9Dialect {

	public String getLimitString(String sql, boolean hasOffset) {

		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );		
		if (hasOffset) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
			pagingSelect.append(sql);
			pagingSelect.append(" ) row_ ) where rownum_ <= ? and rownum_ > ?");
		}
		else {
			pagingSelect.append("select * from ( ");
			pagingSelect.append(sql);
			pagingSelect.append(" ) where rownum <= ?");
		}

		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		
		return pagingSelect.toString();
	}
}
