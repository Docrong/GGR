package com.boco.eoms.duty.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.boco.eoms.common.dao.DAO;

import com.boco.eoms.duty.model.TawDutySheet;
import com.boco.eoms.duty.model.TawRmCycleTableSub;

public class TawRmCycleTableSubDAO extends DAO {
	public TawRmCycleTableSubDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public TawRmCycleTableSubDAO() {
		super();
	}

	public String getCycleUrl(String id, String workid, String cycle) {

		com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = "";
		try {

			String sql = "select * from taw_rm_cycleTable_sub where sheet_id='"
					+ id + "' and workserial='" + workid + "' and cycle='"
					+ cycle + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				url = rs.getString("url");
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return url;
	}

	public void insert(TawRmCycleTableSub tawRmCycleTableSub)
			throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		try {
			PreparedStatement pstmt = null;
			String sql = "";
			sql = "INSERT INTO taw_rm_cycleTable_sub(workserial,sheet_id,boco_id,oper_id,oper_time,url,cycle) "
					+ " VALUES(?,?,?,?,?,?,?)";

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tawRmCycleTableSub.getWorkserial());
			pstmt.setInt(2, tawRmCycleTableSub.getSheetId());
			pstmt.setInt(3, tawRmCycleTableSub.getBoco_Id());
			pstmt.setString(4, tawRmCycleTableSub.getOper_Id());
			pstmt.setString(5, tawRmCycleTableSub.getOper_Time());
			pstmt.setString(6, tawRmCycleTableSub.getUrl());
			pstmt.setInt(7, tawRmCycleTableSub.getCycle());

			pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

	}

	/**
	 * @see 根据班次得到表单id,name的Vector
	 * @param workSerial
	 * @return
	 */
	public Vector getSheetListByWorkSerial(int workSerial) {
		Vector retVector = new Vector();
		com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT DISTINCT a.sheet_id,b.name AS name,a.url ,a.cycle FROM taw_rm_cycleTable_sub a,taw_rm_cycleTable b "
					+ " WHERE a.sheet_id=b.id AND a.workserial=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, workSerial);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				TawRmCycleTableSub tawRmCycleTableSub = new TawRmCycleTableSub();
				populate(tawRmCycleTableSub, rs);
				retVector.add(tawRmCycleTableSub);

				tawRmCycleTableSub = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return retVector;
	}
}
