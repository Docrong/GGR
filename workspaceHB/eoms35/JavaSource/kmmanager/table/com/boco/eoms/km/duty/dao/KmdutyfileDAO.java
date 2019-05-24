/**
 * @see
 * <p>功能描述：值班日值附件的DAO。</p>
 * @author 赵川
 * @version 2.0
 */


package com.boco.eoms.km.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.km.duty.model.Kmdutyfile;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
public class KmdutyfileDAO extends DAO {

  public KmdutyfileDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  /**
   * @see 得到值班班次对应附件列表
   * @param workserial
   * @return
   * @throws SQLException
   */
  public Vector getDutyFile(int workserial) throws SQLException {
    Vector vecDutyFile = new Vector();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT id FROM km_dutyfile WHERE workserial=? order by id";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, workserial);
      rs = pstmt.executeQuery();
      KmdutyfileDAO tawRmDutyfileDAO = new KmdutyfileDAO(ds);
      while (rs.next()) {
      Kmdutyfile tawRmDutyfile  = new Kmdutyfile();
        tawRmDutyfile = tawRmDutyfileDAO.retrieve(rs.getInt(1));
        if (tawRmDutyfile!=null){
          vecDutyFile.add(tawRmDutyfile);
        }
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
    return vecDutyFile;
  }

  /**
   * @see 插入值班日值附件
   * @param tawRmDutyfile
   * @throws SQLException
   */
  public void insert(Kmdutyfile tawRmDutyfile) throws SQLException {
    String sql;
    sql = "INSERT INTO km_dutyfile ( workserial, filename, encodename, dutyman, transtime, class_no, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmDutyfile.getWorkserial());
      pstmt.setString(2, tawRmDutyfile.getFilename());
      pstmt.setString(3, tawRmDutyfile.getEncodename());
      pstmt.setString(4, tawRmDutyfile.getDutyman());
      pstmt.setString(5, tawRmDutyfile.getTranstime());
      pstmt.setInt(6, tawRmDutyfile.getClass_no());
      pstmt.setString(7, tawRmDutyfile.getNotes());
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
   * @see 更新值班日值附件
   * @param tawRmDutyfile
   * @throws SQLException
   */
  public void update(Kmdutyfile tawRmDutyfile) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "UPDATE km_dutyfile SET workserial=?, filename=?, encodename=?, dutyman=?, transtime=?, class_no=?, notes=? WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmDutyfile.getWorkserial());
      pstmt.setString(2, tawRmDutyfile.getFilename());
      pstmt.setString(3, tawRmDutyfile.getEncodename());
      pstmt.setString(4, tawRmDutyfile.getDutyman());
      pstmt.setString(5, tawRmDutyfile.getTranstime());
      pstmt.setInt(6, tawRmDutyfile.getClass_no());
      pstmt.setString(7, tawRmDutyfile.getNotes());
      pstmt.setInt(8, tawRmDutyfile.getId());
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
   * @see 删除值班日值附件
   * @param id
   * @throws SQLException
   */
  public void delete(int id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM km_dutyfile WHERE id=?";
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
   * @see 删除选择的id中的值班日值附件
   * @param selected_id
   * @throws SQLException
   */
  public void delete(String selected_id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM km_dutyfile WHERE id in "+selected_id+"";
      pstmt = conn.prepareStatement(sql);
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
   * @see 得到值班日值附件对象
   * @param id
   * @return
   * @throws SQLException
   */
  public Kmdutyfile retrieve(int id) throws SQLException {
    Kmdutyfile tawRmDutyfile = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT * FROM km_dutyfile WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawRmDutyfile = new Kmdutyfile();
        populate(tawRmDutyfile, rs);
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
    return tawRmDutyfile;
  }
  /**
   * @see 得到值班日值附件列表
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
      String sql = "SELECT workserial, filename, encodename, dutyman, transtime, class_no, notes, id FROM km_dutyfile";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        Kmdutyfile tawRmDutyfile = new Kmdutyfile();
        populate(tawRmDutyfile, rs);
        list.add(tawRmDutyfile);
        tawRmDutyfile=null;
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
   * @see 得到值班日值附件列表
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
      String sql = "SELECT workserial, filename, encodename, dutyman, transtime, class_no, notes, id FROM km_dutyfile";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if(offset > 0) rs.absolute(offset);
      int recCount = 0;
      while((recCount++ < limit) && rs.next()) {
        Kmdutyfile tawRmDutyfile = new Kmdutyfile();
        populate(tawRmDutyfile, rs);
        list.add(tawRmDutyfile);
        tawRmDutyfile=null;
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