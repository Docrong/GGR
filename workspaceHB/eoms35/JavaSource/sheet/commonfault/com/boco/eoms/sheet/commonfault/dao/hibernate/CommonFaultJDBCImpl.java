package com.boco.eoms.sheet.commonfault.dao.hibernate;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultJDBC;

public class CommonFaultJDBCImpl extends BaseDaoJdbc implements ICommonFaultJDBC {

	public void updateSql(String sql){
		getJdbcTemplate().execute(sql);
	}
}
