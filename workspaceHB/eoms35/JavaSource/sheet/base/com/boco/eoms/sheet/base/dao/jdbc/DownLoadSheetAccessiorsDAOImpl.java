package com.boco.eoms.sheet.base.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.dao.IDownLoadSheetAccessiorsDAO;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class DownLoadSheetAccessiorsDAOImpl extends JdbcDaoSupport implements
 IDownLoadSheetAccessiorsDAO {

	public List getSheetAccessoriesList(String sql) throws Exception {

//		sql = "select commonfaul0_.id,commonfaul0_.sheetId,SHEETACCESSORIES,l.NODEACCESSORIES from commonfault_main commonfaul0_  right  join commonfault_link l"
//				+ " on l.MAINID=commonfaul0_.ID "
//				+ " and NODEACCESSORIES is not null "
//				+ " where commonfaul0_.templateFlag<>1 and (commonfaul0_.title is not null) "
//				+ " and commonfaul0_.deleted<>'1' and commonfaul0_.sendTime>=to_date('2009-01-17 22:34:14', 'yyyy-MM-dd HH24:mi:ss')"
//				+ " and commonfaul0_.sendTime<=to_date('2009-06-18 22:34:14', 'yyyy-MM-dd HH24:mi:ss')";
		BocoLog.info(this, "导出附件sql语句="+sql);
		List list = this.getJdbcTemplate().queryForList(sql);
		return list;
	}
	
	public void updateTasks(String sql) throws Exception {
		BocoLog.info(this, "sql语句="+sql);
		this.getJdbcTemplate().execute(sql);
	}	
	
	public boolean executeProcedure(String procedureSql) throws Exception {
		BocoLog.info(this, "sql语句="+procedureSql);
		Connection conn = this.getConnection();
		CallableStatement   cstmt  = conn.prepareCall(procedureSql);
		boolean ifSuccess = cstmt.execute();
		return ifSuccess;
	}

	
}
