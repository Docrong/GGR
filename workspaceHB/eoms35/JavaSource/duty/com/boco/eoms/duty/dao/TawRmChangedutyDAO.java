/**
 * @see
 * <p>功能描述：交换班的DAO。</p>
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
import com.boco.eoms.common.util.*;
// import com.boco.eoms.jbzl.dao.*;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

public class TawRmChangedutyDAO extends DAO {

	public TawRmChangedutyDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	/**
	 * @see 得到管理员需审批的交换班信息的数据
	 * @param roomId
	 * @return
	 * @throws SQLException
	 */
	public Vector managerQueryVector(int roomId) throws SQLException {
		Vector getId = null;
		Vector getWorkserial1 = null;
		Vector getWorkserial2 = null;
		Vector getHander = null;
		Vector getReceiver = null;
		Vector getFlag = null;
		Vector getReason = null;
		// edit by wangheqi 2.7to3.5
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		TawSystemUser tawRmUser = null;
		// edit end
		// TawRmUserDAO tawRmUserDAO=null;
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		String sql = null;

		Vector getVector = new Vector();
		getId = new Vector();
		getWorkserial1 = new Vector();
		getWorkserial2 = new Vector();
		getHander = new Vector();
		getReceiver = new Vector();
		getFlag = new Vector();
		getReason = new Vector();
		// tawRmUserDAO = new TawRmUserDAO(ds);
		tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			sql = "select id, workserial1, workserial2,  hander, receiver, flag , reason  from taw_rm_changeduty where room_id = ? and flag in (1,2,4,5) order by id desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				getId.add(String.valueOf(rs.getInt(1)));
				getWorkserial1.add(tawRmAssignworkDAO
						.getStartTime(rs.getInt(2)));
				getWorkserial2.add(tawRmAssignworkDAO
						.getStartTime(rs.getInt(3)));
				getHander.add(userbo
						.getUsernameByUserid(rs.getString(4).trim()));
				getReceiver.add(userbo.getUsernameByUserid(rs.getString(5)
						.trim()));
				// getHander.add(tawRmUserDAO.getUserName(rs.getString(4).trim()));
				// getReceiver.add(tawRmUserDAO.getUserName(rs.getString(5).trim()));
				getFlag.add(String.valueOf(rs.getInt(6)));
				getReason.add(StaticMethod.null2String(rs.getString(7)));
			}
			rs.close();
			pstmt.close();

			getVector.add(getId);
			getVector.add(getWorkserial1);
			getVector.add(getWorkserial2);
			getVector.add(getHander);
			getVector.add(getReceiver);
			getVector.add(getFlag);
			getVector.add(getReason);

		} catch (SQLException e) {
			rs.close();
			pstmt.close();
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
			getId = null;
			getWorkserial1 = null;
			getWorkserial2 = null;
			getHander = null;
			getReceiver = null;
			getFlag = null;
			getReason = null;
			userbo = null;
			// tawRmUserDAO=null;
			tawRmAssignworkDAO = null;
			sql = null;
			rs = null;
		}
		return getVector;
	}

	/**
	 * @see 得到本人发出的交换班申请信息的数据
	 * @param roomId
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public Vector sentQueryVector(int roomId, String user_id)
			throws SQLException {
		Vector getId = null;
		Vector getWorkserial1 = null;
		Vector getWorkserial2 = null;
		Vector getHander = null;
		Vector getReceiver = null;
		Vector getFlag = null;
		Vector getReason = null;
		// edit by wangheqi 2.7to3.5
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		TawSystemUser tawRmUser = null;
		// edit end
		// TawRmUserDAO tawRmUserDAO=null;
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		String sql = null;

		Vector getVector = new Vector();
		getId = new Vector();
		getWorkserial1 = new Vector();
		getWorkserial2 = new Vector();
		getHander = new Vector();
		getReceiver = new Vector();
		getFlag = new Vector();
		getReason = new Vector();
		// tawRmUserDAO = new TawRmUserDAO(ds);
		tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			sql = "select id, workserial1, workserial2,  hander, receiver, flag , reason  from taw_rm_changeduty where room_id = ? and hander = ? order by id desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				getId.add(String.valueOf(rs.getInt(1)));
				getWorkserial1.add(tawRmAssignworkDAO
						.getStartTime(rs.getInt(2)));
				getWorkserial2.add(tawRmAssignworkDAO
						.getStartTime(rs.getInt(3)));
				getHander.add(userbo
						.getUsernameByUserid(rs.getString(4).trim()));
				getReceiver.add(userbo.getUsernameByUserid(rs.getString(5)
						.trim()));
				// getHander.add(tawRmUserDAO.getUserName(rs.getString(4).trim()));
				// getReceiver.add(tawRmUserDAO.getUserName(rs.getString(5).trim()));
				getFlag.add(String.valueOf(rs.getInt(6)));
				getReason.add(StaticMethod.null2String(rs.getString(7)));
			}
			rs.close();
			pstmt.close();

			getVector.add(getId);
			getVector.add(getWorkserial1);
			getVector.add(getWorkserial2);
			getVector.add(getHander);
			getVector.add(getReceiver);
			getVector.add(getFlag);
			getVector.add(getReason);

		} catch (SQLException e) {
			rs.close();
			pstmt.close();
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
			getId = null;
			getWorkserial1 = null;
			getWorkserial2 = null;
			getHander = null;
			getReceiver = null;
			getFlag = null;
			getReason = null;
			userbo = null;
			// tawRmUserDAO=null;
			tawRmAssignworkDAO = null;
			sql = null;
			rs = null;
		}
		return getVector;
	}

	/**
	 * @see 得到本人收到的交换班申请信息的数据
	 * @param roomId
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public Vector receiveQueryVector(int roomId, String user_id)
			throws SQLException {
		// edit by wangheqi 2.7to3.5
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		TawSystemUser tawRmUser = null;
		// edit end
		// TawRmUserDAO tawRmUserDAO=null;
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		Vector getVector = new Vector();
		Vector getId = new Vector();
		Vector getWorkserial1 = new Vector();
		Vector getWorkserial2 = new Vector();
		Vector getHander = new Vector();
		Vector getReceiver = new Vector();
		Vector getFlag = new Vector();
		Vector getReason = new Vector();
		// tawRmUserDAO = new TawRmUserDAO(ds);
		tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "";
			sql = "select id, workserial1, workserial2,  hander, receiver, flag , reason  from taw_rm_changeduty where room_id = ? and receiver = ? order by id desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, user_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				getId.add(String.valueOf(rs.getInt(1)));
				getWorkserial1.add(tawRmAssignworkDAO
						.getStartTime(rs.getInt(2)));
				getWorkserial2.add(tawRmAssignworkDAO
						.getStartTime(rs.getInt(3)));
				getHander.add(userbo.getUsernameByUserid(rs.getString(4)));
				getReceiver.add(userbo.getUsernameByUserid(rs.getString(5)));
				// getHander.add(tawRmUserDAO.getUserName(rs.getString(4)));
				// getReceiver.add(tawRmUserDAO.getUserName(rs.getString(5)));
				getFlag.add(String.valueOf(rs.getInt(6)));
				getReason.add(StaticMethod.null2String((rs.getString(7))));
			}
			rs.close();
			pstmt.close();
			getVector.add(getId);
			getVector.add(getWorkserial1);
			getVector.add(getWorkserial2);
			getVector.add(getHander);
			getVector.add(getReceiver);
			getVector.add(getFlag);
			getVector.add(getReason);
		} catch (SQLException e) {
			e.printStackTrace();
			rs.close();
			pstmt.close();
			conn.rollback();
		} finally {
			conn.close();
			userbo = null;
			// tawRmUserDAO=null;
			tawRmAssignworkDAO = null;
			getId = null;
			getWorkserial1 = null;
			getWorkserial2 = null;
			getHander = null;
			getReceiver = null;
			getFlag = null;
			getReason = null;
		}
		return getVector;
	}

	/**
	 * @see 得到交换班的历史记录列表
	 * @param roomId
	 * @param inputdate_from
	 * @param inputdate_to
	 * @return
	 * @throws SQLException
	 */
	public Vector allQueryVector(int roomId, String inputdate_from,
			String inputdate_to) throws SQLException {
		// edit by wangheqi 2.7to3.5
		TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
		TawSystemUser tawRmUser = null;
		// edit end
		// TawRmUserDAO tawRmUserDAO=null;
		TawRmAssignworkDAO tawRmAssignworkDAO = null;
		Vector getVector = new Vector();
		Vector getInputdate = new Vector();
		Vector getWorkserial1 = new Vector();
		Vector getWorkserial2 = new Vector();
		Vector getHander = new Vector();
		Vector getReceiver = new Vector();
		Vector getFlag = new Vector();
		Vector getReason = new Vector();
		// tawRmUserDAO = new TawRmUserDAO(ds);
		tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "";
			sql = "select inputdate, workserial1, workserial2,  hander, receiver, flag , reason,id  from taw_rm_changeduty where room_id = ? and inputdate >=  ? and inputdate <=  ? order by id desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, inputdate_from);
			pstmt.setString(3, inputdate_to);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				getInputdate.add(String.valueOf(rs.getString(1)).replaceAll(
						" 00:00:00.0", ""));
				getWorkserial1.add(tawRmAssignworkDAO
						.getStartTime(rs.getInt(2)));
				getWorkserial2.add(tawRmAssignworkDAO
						.getStartTime(rs.getInt(3)));
				getHander.add(userbo.getUsernameByUserid(rs.getString(4)));
				getReceiver.add(userbo.getUsernameByUserid(rs.getString(5)));
				// getHander.add(tawRmUserDAO.getUserName(rs.getString(4)));
				// getReceiver.add(tawRmUserDAO.getUserName(rs.getString(5)));
				getFlag.add(String.valueOf(rs.getInt(6)));
				getReason.add(StaticMethod.null2String(rs.getString(7)));
			}
			rs.close();
			pstmt.close();
			getVector.add(getInputdate);
			getVector.add(getWorkserial1);
			getVector.add(getWorkserial2);
			getVector.add(getHander);
			getVector.add(getReceiver);
			getVector.add(getFlag);
			getVector.add(getReason);

		} catch (SQLException e) {
			rs.close();
			pstmt.close();
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
			userbo = null;
			// tawRmUserDAO=null;
			tawRmAssignworkDAO = null;
			getInputdate = null;
			getWorkserial1 = null;
			getWorkserial2 = null;
			getHander = null;
			getReceiver = null;
			getFlag = null;
			getReason = null;
		}
		return getVector;
	}

	/**
	 * @see flag=0代表可选择的源换班班次，flag=1代表可选择的目标换班班次
	 * @see 显示该机房，该日期，该用户的班次列表
	 * @see 如果某班次有该人存在，可作为源班次，如果该班次无该人存在，可作为目标班次
	 * @param roomId
	 * @param user_id
	 * @param dutydate
	 * @param flag
	 * @return
	 * @throws SQLException
	 */
	public Vector getApplyVector(int roomId, String user_id, String dutydate,
			int flag) throws SQLException {
		Vector getVector = new Vector();
		Vector getWorkserial = new Vector();
		Vector getStarttime = new Vector();
		Vector getDutyman = new Vector();
		Vector getUsername = new Vector();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "";
			if (flag == 0) {
				sql = "select a.id,a.starttime_defined,s.dutyman,u.username from taw_rm_assignwork a,taw_rm_assign_sub s,taw_system_user u where a.room_id = ? and a.id = s.workserial and s.dutyman = ? and a.dutydate = ? and s.dutyman = u.userid";
			}
			if (flag == 1) {
				sql = "select a.id,a.starttime_defined,s.dutyman,u.username from taw_rm_assignwork a,taw_rm_assign_sub s,taw_system_user u where a.room_id = ? and a.id = s.workserial and s.workserial not in (select distinct workserial from taw_rm_assign_sub where dutyman = ?) and a.dutydate = ? and s.dutyman = u.userid";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, user_id);
			pstmt.setString(3, dutydate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				getWorkserial.add(String.valueOf(rs.getInt(1)));
				getStarttime.add(StaticMethod.StringReplace(rs.getString(2),
						".0", ""));
				getDutyman.add(rs.getString(3));
				getUsername.add(rs.getString(4));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			rs.close();
			pstmt.close();
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		getVector.add(getWorkserial);
		getVector.add(getStarttime);
		getVector.add(getDutyman);
		getVector.add(getUsername);
		return getVector;
	}

	/**
	 * @see flag=0代表可选择的源换班班次，flag=1代表可选择的目标换班班次
	 * @see 显示该机房，该日期，该用户的班次列表
	 * @see 如果某班次有该人存在，可作为源班次，如果该班次无该人存在，可作为目标班次
	 * @param roomId
	 * @param user_id
	 * @param dutydate
	 * @param flag
	 * @return
	 * @throws SQLException
	 */
	public Vector getApplyVector(int roomId, String user_id, String startdate,
			String enddate, String flag) throws SQLException {
		Vector getVector = new Vector();
		Vector getWorkserial = new Vector();
		Vector getStarttime = new Vector();
		Vector getDutyman = new Vector();
		Vector getUsername = new Vector();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "";
			if (flag.equals("1")) {
				sql = "select a.id,a.starttime_defined,s.dutyman,u.username from taw_rm_assignwork a,"
						+ "taw_rm_assign_sub s,taw_system_user u where a.room_id = ? and a.id = s.workserial and "
						+ "s.workserial not in (select distinct workserial from taw_rm_assign_sub where dutyman = ?) and a.dutydate >? and a.dutydate <? "
						+ "and s.dutyman = u.userid";
			} else {
				sql = "select a.id,a.starttime_defined,s.dutyman,u.username from taw_rm_assignwork a,taw_rm_assign_sub s,"
						+ "taw_system_user u where a.room_id = ? and a.id = s.workserial and s.dutyman = ? and a.dutydate >? and a.dutydate <? "
						+ " and s.dutyman = u.userid";
			}
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, user_id);
			pstmt.setString(3, startdate);
			pstmt.setString(4, enddate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				getWorkserial.add(String.valueOf(rs.getInt(1)));
				getStarttime.add(StaticMethod.StringReplace(rs.getString(2),
						".0", ""));
				getDutyman.add(rs.getString(3));
				getUsername.add(rs.getString(4));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			rs.close();
			pstmt.close();
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		getVector.add(getWorkserial);
		getVector.add(getStarttime);
		getVector.add(getDutyman);
		getVector.add(getUsername);
		return getVector;
	}
	 
	/**
	 * @see 得到自己收到的换班申请的数量，用于短信提醒
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public int getApplyNum(String user_id) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int intApplyNum = 0;
		try {
			conn = ds.getConnection();
			String sql = "";
			sql = "select count(*) from  taw_rm_changeduty where receiver = ? and flag = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				intApplyNum = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			rs.close();
			pstmt.close();
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return intApplyNum;
	}

	/**
	 * @see 修改交换班状态
	 * @param id
	 * @param transact_flag
	 * @throws SQLException
	 */
	public void update(int id, int transact_flag) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "UPDATE taw_rm_changeduty SET  flag=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, transact_flag);
			pstmt.setInt(2, id);
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
	 * @see 插入交换班记录
	 * @param roomId
	 * @param inputdate
	 * @param workserial1
	 * @param workserial2
	 * @param expireddate
	 * @param hander
	 * @param receiver
	 * @param flag
	 * @param reason
	 * @throws SQLException
	 */
	public void insert(int roomId, String inputdate, int workserial1,
			int workserial2, String expireddate, String hander,
			String receiver, int flag, String reason) throws SQLException {
		String sql;
		sql = "INSERT INTO taw_rm_changeduty (room_id,inputdate,workserial1, workserial2, expireddate,hander, receiver, flag,reason) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, inputdate);
			pstmt.setInt(3, workserial1);
			pstmt.setInt(4, workserial2);
			pstmt.setString(5, expireddate);
			pstmt.setString(6, hander);
			pstmt.setString(7, receiver);
			pstmt.setInt(8, flag);
			pstmt.setString(9, reason);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(rs);
			close(pstmt);
			rollback(conn);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			close(conn);
		}
	}

	/**
	 * @see 插入交换班记录
	 * @param tawRmChangeduty
	 * @throws SQLException
	 */
	public void insert(TawRmChangeduty tawRmChangeduty) throws SQLException {
		String sql;
		sql = "INSERT INTO taw_rm_changeduty ( room_id, inputdate, workserial1, workserial2, expireddate, hander, receiver, flag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tawRmChangeduty.getRoomId());
			pstmt.setString(2, tawRmChangeduty.getInputdate());
			pstmt.setInt(3, tawRmChangeduty.getWorkserial1());
			pstmt.setInt(4, tawRmChangeduty.getWorkserial2());
			pstmt.setString(5, tawRmChangeduty.getExpireddate());
			pstmt.setString(6, tawRmChangeduty.getHander());
			pstmt.setString(7, tawRmChangeduty.getReceiver());
			pstmt.setInt(8, tawRmChangeduty.getFlag());
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
		} catch (SQLException sqle) {
			close(rs);
			close(pstmt);
			rollback(conn);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			close(conn);
		}
	}

	/**
	 * @see 删除已过期的换班记录（如想用2号换5号，而今天已经是10号了），目前已经不用该方法了
	 * @param today
	 * @throws SQLException
	 */
	public void delete(String today) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "DELETE FROM taw_rm_changeduty WHERE expireddate <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, today);
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
	 * @see 更新交换班记录
	 * @param tawRmChangeduty
	 * @throws SQLException
	 */
	public void update(TawRmChangeduty tawRmChangeduty) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "UPDATE taw_rm_changeduty SET room_id=?, inputdate=?, workserial1=?, workserial2=?, expireddate=?, hander=?, receiver=?, flag=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tawRmChangeduty.getRoomId());
			pstmt.setString(2, tawRmChangeduty.getInputdate());
			pstmt.setInt(3, tawRmChangeduty.getWorkserial1());
			pstmt.setInt(4, tawRmChangeduty.getWorkserial2());
			pstmt.setString(5, tawRmChangeduty.getExpireddate());
			pstmt.setString(6, tawRmChangeduty.getHander());
			pstmt.setString(7, tawRmChangeduty.getReceiver());
			pstmt.setInt(8, tawRmChangeduty.getFlag());
			pstmt.setInt(9, tawRmChangeduty.getId());
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
	 * @see 删除交换班记录
	 * @param id
	 * @throws SQLException
	 */
	public void delete(int id) throws SQLException {
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			String sql = "DELETE FROM taw_rm_changeduty WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
	 * @see 得到交换班记录对象
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public TawRmChangeduty retrieve(int id) throws SQLException {
		TawRmChangeduty tawRmChangeduty = null;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM taw_rm_changeduty WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tawRmChangeduty = new TawRmChangeduty();
				populate(tawRmChangeduty, rs);
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
		return tawRmChangeduty;
	}

	/**
	 * @see 得到交换班对象列表
	 * @return
	 * @throws SQLException
	 */
	public List list() throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TawRmChangeduty tawRmChangeduty = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT room_id, inputdate, workserial1, workserial2, expireddate, hander, receiver, flag, id FROM taw_rm_changeduty";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tawRmChangeduty = new TawRmChangeduty();
				populate(tawRmChangeduty, rs);
				list.add(tawRmChangeduty);
				tawRmChangeduty = null;
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
	 * @see 得到交换班对象列表
	 * @param offset
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	public List list(int offset, int limit) throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TawRmChangeduty tawRmChangeduty = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT room_id, inputdate, workserial1, workserial2, expireddate, hander, receiver, flag, id FROM taw_rm_changeduty";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (offset > 0)
				rs.absolute(offset);
			int recCount = 0;
			while ((recCount++ < limit) && rs.next()) {
				tawRmChangeduty = new TawRmChangeduty();
				populate(tawRmChangeduty, rs);
				list.add(tawRmChangeduty);
				tawRmChangeduty = null;
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
	 * @see 得到符合条件的交换班记录列表
	 * @param offset
	 * @param limit
	 * @param strCondition
	 * @return
	 * @throws SQLException
	 */
	public List search(int offset, int limit, String strCondition)
			throws SQLException {
		ArrayList search = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql;
			sql = "SELECT * FROM taw_rm_changeduty " + strCondition;
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (offset > 0) {
				rs.next();
				offset--;
			}
			int recCount = 0;
			while ((recCount++ < limit) && rs.next()) {
				TawRmChangeduty tawRmChangeduty = new TawRmChangeduty();
				populate(tawRmChangeduty, rs);
				String strInpudate = tawRmChangeduty.getInputdate().replaceAll(
						"\\.0", "");
				strInpudate = strInpudate.replaceAll("00.00.00", "");
				tawRmChangeduty.setInputdate(strInpudate);
				search.add(tawRmChangeduty);
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
		return search;
	}

}