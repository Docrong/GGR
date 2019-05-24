/**
 * @see
 * <p>功能描述：交接班遗留问题的DAO。</p>
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
public class TawRmHangoverDAO extends DAO {

  public TawRmHangoverDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  /**
   * @see 插入交接班遗留问题对象
   * @param tawRmHangover
   * @throws SQLException
   */
  public void insert(TawRmHangover tawRmHangover) throws SQLException {
    String sql;
    sql = "INSERT INTO taw_rm_hangover ( hang_workserial, receive_workserial, hang_question, dealer, flag , notes) VALUES (?, ?, ?, ?, ?,?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmHangover.getHangWorkserial());
      pstmt.setInt(2, tawRmHangover.getReceiveWorkserial());
      pstmt.setString(3, tawRmHangover.getHangQuestion());
      pstmt.setString(4, tawRmHangover.getDealer());
      pstmt.setInt(5, tawRmHangover.getFlag());
      pstmt.setString(6, tawRmHangover.getNotes());

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
   * @see 更新遗留问题
   * @param tawRmHangover
   * @throws SQLException
   */
  public void update(TawRmHangover tawRmHangover) throws SQLException {
  	com.boco.eoms.db.util.BocoConnection conn = null;
  	PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "UPDATE taw_rm_hangover SET hang_workserial=?, receive_workserial=?, hang_question=?, dealer=?, flag=?, notes=? where hang_workserial = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmHangover.getHangWorkserial());
      pstmt.setInt(2, tawRmHangover.getReceiveWorkserial());
      pstmt.setString(3, tawRmHangover.getHangQuestion());
      pstmt.setString(4, tawRmHangover.getDealer());
      pstmt.setInt(5, tawRmHangover.getFlag());
      pstmt.setString(6, tawRmHangover.getNotes());
      pstmt.setInt(7, tawRmHangover.getHangWorkserial());
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
   * @see 删除遗留问题记录
   * @param id
   * @throws SQLException
   */
  public void delete(int id) throws SQLException {
  	com.boco.eoms.db.util.BocoConnection conn = null;
  	PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM taw_rm_hangover WHERE id=?";
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
   * @see 得到遗留问题对象
   * @param id
   * @return
   * @throws SQLException
   */
  public TawRmHangover retrieve(int id) throws SQLException {
    TawRmHangover tawRmHangover = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT * FROM taw_rm_hangover WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawRmHangover = new TawRmHangover();
        populate(tawRmHangover, rs);
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
    return tawRmHangover;
  }

  /**
   * @see 得到遗留问题对象
   * @param workserial
   * @param flag
   * @return
   * @throws SQLException
   */
  public TawRmHangover retrieve(int workserial,int flag) throws SQLException {
    TawRmHangover tawRmHangover = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "";
      if (flag == 1){
        sql = "SELECT * FROM taw_rm_hangover WHERE hang_workserial=?";
      }
      else if (flag == 0){
        sql = "SELECT * FROM taw_rm_hangover WHERE receive_workserial=?";
      }
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, workserial);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawRmHangover = new TawRmHangover();
        populate(tawRmHangover, rs);
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
    return tawRmHangover;
  }

  /**
   * @see 得到遗留问题列表
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
      String sql = "SELECT hang_workserial, receive_workserial, hang_question, dealer, flag, notes, id FROM taw_rm_hangover";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        TawRmHangover tawRmHangover = new TawRmHangover();
        populate(tawRmHangover, rs);
        list.add(tawRmHangover);
        tawRmHangover=null;
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
   * @see 得到遗留问题列表
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
    try {
      conn = ds.getConnection();
      String sql = "SELECT hang_workserial, receive_workserial, hang_question, dealer, flag, notes, id FROM taw_rm_hangover";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if(offset > 0) rs.absolute(offset);
      int recCount = 0;
      while((recCount++ < limit) && rs.next()) {
        TawRmHangover tawRmHangover = new TawRmHangover();
        populate(tawRmHangover, rs);
        list.add(tawRmHangover);
        tawRmHangover=null;
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

}