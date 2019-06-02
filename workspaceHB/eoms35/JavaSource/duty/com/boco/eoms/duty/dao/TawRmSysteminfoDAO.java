/**
 * @see
 * <p>功能描述：机房参数设置的DAO。</p>
 * @author 赵川
 * @version 2.0
 */

package com.boco.eoms.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
// import com.boco.eoms.jbzl.dao.TawApparatusroomDAO;
// import com.boco.eoms.jbzl.model.TawApparatusroom;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;

public class TawRmSysteminfoDAO extends DAO {

	public TawRmSysteminfoDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	/**
	 * @see 插入机房参数信息对象
	 * @param tawRmSysteminfo
	 * @throws SQLException
	 */
	public void insert(TawRmSysteminfo tawRmSysteminfo) throws SQLException {
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = cptroomBO.getTawSystemCptroomById(
				new Integer(tawRmSysteminfo.getRoomId()), 0);

		// TawApparatusroomDAO tawApparatusroomDAO = new
		// TawApparatusroomDAO(ds);
		// TawApparatusroom tawApparatusroom =
		// tawApparatusroomDAO.retrieve(tawRmSysteminfo.getRoomId()) ;
		int deptId = 0;
		if (tawApparatusroom != null && !tawApparatusroom.getDeptid().equals("") ) {
			deptId = Integer.parseInt(tawApparatusroom.getDeptid());
		}
		String sql;
		sql = "INSERT INTO taw_rm_systeminfo (room_id, dept_id, maxerrortime, maxdutynum, ex_request, ex_answer, duty_inform,exchange_flag,cycle_time,staggertime) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tawRmSysteminfo.getRoomId());
			pstmt.setInt(2, deptId);
			pstmt.setInt(3, tawRmSysteminfo.getMaxerrortime());
			pstmt.setInt(4, tawRmSysteminfo.getMaxdutynum());
			pstmt.setInt(5, tawRmSysteminfo.getExRequest());
			pstmt.setInt(6, tawRmSysteminfo.getExAnswer());
			pstmt.setInt(7, tawRmSysteminfo.getDutyInform());
			pstmt.setInt(8, tawRmSysteminfo.getExchangeFlag());
			pstmt.setInt(9, tawRmSysteminfo.getCycleTime());
			pstmt.setInt(10, tawRmSysteminfo.getStaggertime());
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(pstmt);
			rollback(conn);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			close(conn);
		}
	}

	/**
	 * @see 更新机房参数
	 * @param tawRmSysteminfo
	 * @throws SQLException
	 */
	public void update(TawRmSysteminfo tawRmSysteminfo) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		// edit by wangheqi
		TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
		TawSystemCptroom tawApparatusroom = cptroomBO.getTawSystemCptroomById(
				new Integer(tawRmSysteminfo.getRoomId()), 0);
		// TawApparatusroomDAO tawApparatusroomDAO = new
		// TawApparatusroomDAO(ds);
		// TawApparatusroom tawApparatusroom =
		// tawApparatusroomDAO.retrieve(tawRmSysteminfo.getRoomId()) ;
		int deptId = 0;
		if (tawApparatusroom != null) {
			deptId = Integer.parseInt(tawApparatusroom.getDeptid());
		}
		String sql = "UPDATE taw_rm_systeminfo SET dept_id=?, maxerrortime=?, maxdutynum=?, ex_request=?, ex_answer=?, duty_inform=?,exchange_flag=? ,cycle_time = ? ,staggertime = ? WHERE room_id=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptId);
			pstmt.setInt(2, tawRmSysteminfo.getMaxerrortime());
			pstmt.setInt(3, tawRmSysteminfo.getMaxdutynum());
			pstmt.setInt(4, tawRmSysteminfo.getExRequest());
			pstmt.setInt(5, tawRmSysteminfo.getExAnswer());
			pstmt.setInt(6, tawRmSysteminfo.getDutyInform());
			pstmt.setInt(7, tawRmSysteminfo.getExchangeFlag());
			pstmt.setInt(8, tawRmSysteminfo.getCycleTime());
			pstmt.setInt(9, tawRmSysteminfo.getStaggertime());
			pstmt.setInt(10, tawRmSysteminfo.getRoomId());
			pstmt.executeUpdate();
			close(pstmt);
			conn.commit();
		} catch (SQLException e) {
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
	}

	/**
	 * @see 根据机房ID，删除机房参数记录
	 * @param roomId
	 * @throws SQLException
	 */
	public void delete(int roomId) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "DELETE FROM taw_rm_systeminfo WHERE room_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.executeUpdate();
			close(pstmt);
			conn.commit();
		} catch (SQLException e) {
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
	}

	/**
	 * @see 根据机房ID，得到机房参数对象
	 * @param roomId
	 * @return
	 * @throws SQLException
	 */
	public TawRmSysteminfo retrieve(String roomId) throws SQLException {
		TawRmSysteminfo tawRmSysteminfo = null;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM taw_rm_systeminfo WHERE room_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tawRmSysteminfo = new TawRmSysteminfo();
				populate(tawRmSysteminfo, rs);
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return tawRmSysteminfo;
	}

	/**
	 * @see 得到机房列表
	 * @return
	 * @throws SQLException
	 */
	public List list() throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT dept_id, maxerrortime, maxdutynum, ex_request, ex_answer, duty_inform,exchange_flag room_id ,cycle_time,staggertime FROM taw_rm_systeminfo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TawRmSysteminfo tawRmSysteminfo = new TawRmSysteminfo();
				populate(tawRmSysteminfo, rs);
				list.add(tawRmSysteminfo);
				tawRmSysteminfo = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}

	/**
	 * @see 得到机房列表
	 * @return
	 * @throws SQLException
	 */
	public List list(int offset, int limit) throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT dept_id, maxerrortime, maxdutynum, ex_request, ex_answer, duty_inform, room_id,exhange_flag ,cycle_time,staggertime FROM taw_rm_systeminfo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (offset > 0)
				rs.absolute(offset);
			int recCount = 0;
			while ((recCount++ < limit) && rs.next()) {
				TawRmSysteminfo tawRmSysteminfo = new TawRmSysteminfo();
				populate(tawRmSysteminfo, rs);
				list.add(tawRmSysteminfo);
				tawRmSysteminfo = null;
			}
			close(rs);
			close(pstmt);
		} catch (SQLException e) {
			close(rs);
			close(pstmt);
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return list;
	}
	public Vector getVectordutysheet(int room_id) throws SQLException {
	    Vector vectorExchangTime = new Vector();
	    com.boco.eoms.db.util.BocoConnection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      conn = ds.getConnection();

	      String sql = "SELECT dutysheet FROM taw_rm_dutysheet where room_id=? order by id";
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1, room_id);
	      rs = pstmt.executeQuery();
	      while(rs.next()) {
	        vectorExchangTime.add(rs.getString(1));
	      }
	      rs.close();
	      pstmt.close() ;
	    } catch (SQLException e) {
	      rs.close();
	      pstmt.close() ;
	      conn.rollback() ;
	      e.printStackTrace();
	    } finally {
	      conn.close() ;
	    }
	    return vectorExchangTime;
	  }
}