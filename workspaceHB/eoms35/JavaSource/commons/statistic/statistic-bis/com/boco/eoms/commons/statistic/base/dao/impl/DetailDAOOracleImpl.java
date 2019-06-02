package com.boco.eoms.commons.statistic.base.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.support.JdbcUtils;

import com.boco.eoms.commons.statistic.base.dao.IStatDetailDAO;

public class DetailDAOOracleImpl extends DetailDAOBaseImpl implements
		IStatDetailDAO {
	
	public List getListDetail(String sql, String className, int pageIndex,
			int pageSize) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		try {
			sql = sql.trim();
			// int len=sql.length();
			// StringBuffer str = new StringBuffer(sql).insert(len+1, "and
			// ROWNUM<= "+(pageIndex+1)*pageSize+" " );
			// sql=str.toString();
			
			//String sqll = sql + " and ROWNUM<=" + (pageIndex + 1) * pageSize;
			sql = "SELECT * FROM(SELECT A.*, ROWNUM RN FROM ("+sql+") A WHERE ROWNUM <= "+(pageIndex + 1) * pageSize+")WHERE RN > "+ (pageIndex * pageSize); 
//			System.out.println("detail sql :" + sql);
			logger.info("\n分页detail sql :" + sql);
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			return getListByRs(rs, className, pageIndex, pageSize);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.closeStatement(ps);
			releaseConnection(conn);
		}

		return null;
	}

	//oracle数据库 分页用
	private List getListByRs(ResultSet rs, String className, int pageIndex,
			int pageSize) throws SQLException {
		List result = new ArrayList();

		while(rs.next())
		{
				try {
					Object vo = Class.forName(className).newInstance();
					populate(vo, rs);
					result.add(vo);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
		}

		return result;
	}
}
