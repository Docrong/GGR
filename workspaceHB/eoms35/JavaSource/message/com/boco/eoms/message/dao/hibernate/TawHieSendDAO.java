package com.boco.eoms.message.dao.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.boco.eoms.common.dao.DAO;

/**
 * <p>
 * Title: ���ɹ�E-OMS����
 * </p>
 * <p>
 * Description: ��¼�����ύ������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: BOCO Inter-Telcome
 * </p>
 *
 * @author ����
 * @version 2.7
 */
public class TawHieSendDAO extends DAO {
	public TawHieSendDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}
	/**
	 * add by lixiaoming
	 * @return
	 * @throws SQLException
	 */
	public TawHieSendDAO() {
		super();
	}

	/**
	 * add by lixiaoming
	 * @return
	 * @throws SQLException
	 */
	public int selectHeader() throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int header = 0;
		try {
			conn = ds.getConnection();
			String sql = "select * from taw_hie_header";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				header = rs.getInt(1);
			}
			close(pstmt);
			conn.commit();
		} catch (SQLException e) {
			close(pstmt);

			e.printStackTrace();
		} finally {
			close(conn);
		}
		return header;
	}
	/**
	 * add by lixiaoming
	 * @return
	 * @throws SQLException
	 */
	public void updateHeader(int i) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
                 System.out.println("--111i---"+i);
		i++;
                System.out.println("--222i---"+i);
		if(i > 255){
			i = 0;
		}
		try {
			conn = ds.getConnection();
			sql = "update taw_hie_header set header = " + i;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			conn.commit();
                        close(pstmt);
		} catch (SQLException e) {
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}
	}
}
