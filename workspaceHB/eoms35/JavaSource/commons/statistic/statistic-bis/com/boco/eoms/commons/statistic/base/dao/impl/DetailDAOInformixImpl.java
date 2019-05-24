package com.boco.eoms.commons.statistic.base.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.support.JdbcUtils;

import com.boco.eoms.commons.statistic.base.dao.IStatDetailDAO;

public class DetailDAOInformixImpl extends DetailDAOBaseImpl implements
		IStatDetailDAO {
	public List getListDetail(String sql, String className, int pageIndex,
			int pageSize) {
		
		Connection conn = getConnection();
		PreparedStatement ps = null;
		try {
			sql=sql.trim();
			StringBuffer str = new StringBuffer(sql)
									.insert(6, " first "+(pageIndex+1)*pageSize+" " );
			sql=str.toString();
//			System.out.println("detail sql2 :"+sql);
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

	//私有方法 informix 数据分页用
	private List getListByRs(ResultSet rs, String className, int pageIndex,
			int pageSize) throws SQLException {
		int beginRow = pageIndex * pageSize;
		List result = new ArrayList();
		for (int i = 0; i < beginRow; i++)
			rs.next();

		for (int iRow = 0; iRow < pageSize; iRow++) {
			if (rs.next()) {

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
		}

		return result;
	}

}
