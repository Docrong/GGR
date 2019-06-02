/**
 * 
 */
package com.boco.eoms.duty.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.duty.model.TawModelCopy;

/**
 * @author FengShaoHong
 *
 */
public class TawRmModelSetBo extends BO{
	
	public TawRmModelSetBo(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public TawRmModelSetBo() {
		super();
	}
	public void setModel(int roomId,String startTime,String endTime,String workSelect) throws SQLException{
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "";
			 sql = "INSERT INTO TAW_RM_ASSIGN_MODELCOPY ( ID, ROOMID, STARTDATE, ENDDATE, ASSIGNDATE, WORKSELECT) VALUES ('', ?, ?, ?, '', ?)";
			 pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, roomId);
				pstmt.setString(2, startTime);
				pstmt.setString(3, endTime);
				pstmt.setString(4, workSelect);
				 pstmt.executeUpdate();
			      pstmt.close();
			      conn.commit();
		 } catch (SQLException sqle) {
		      pstmt.close();
		      conn.rollback();
		      sqle.printStackTrace();
		      throw sqle;
		    } finally {
		      conn.close();
		    }
	}
	public void deledeModel(int roomId) throws SQLException{
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "";
			sql = "DELETE FROM TAW_RM_ASSIGN_MODELCOPY WHERE ROOMID=? ";
			 pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, roomId);
				 pstmt.executeUpdate();
			      pstmt.close();
			      conn.commit();
		 } catch (SQLException sqle) {
		      pstmt.close();
		      conn.rollback();
		      sqle.printStackTrace();
		      throw sqle;
		    } finally {
		      conn.close();
		    }
	}
	public TawModelCopy getModel(int roomId) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "";
			sql = "SELECT * FROM TAW_RM_ASSIGN_MODELCOPY WHERE roomid=?";
			pstmt = conn.prepareStatement(sql);
			int temp = roomId;
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				TawModelCopy copy = new TawModelCopy();
				copy.setEndDate(rs.getString("ENDDATE"));
				copy.setId(rs.getString("ID"));
				copy.setRoomId(rs.getInt("ROOMID"));
				copy.setStartDate(rs.getString("STARTDATE"));
				copy.setWorkSelect(rs.getString("WORKSELECT"));
				return copy;
			}

			return null;
		} catch (SQLException sqle) {
			conn.rollback();
			sqle.printStackTrace();
			throw sqle;
		} finally {
			rs.close();
			pstmt.close();
			conn.commit();
			conn.close();
		}
	}
	
	public String getAssignDate(int roomId,String workSelect) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "";
			sql = "select max(dutydate) from taw_rm_assignwork";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String date =  rs.getString(1);
				date = TawModelCopyAssignBO.getDate(date, workSelect);
				return TawModelCopyAssignBO.getAddZero(date);
			}

			return null;
		} catch (SQLException sqle) {
			conn.rollback();
			sqle.printStackTrace();
			throw sqle;
		} finally {
			rs.close();
			pstmt.close();
			conn.commit();
			conn.close();
		}
	}
}
