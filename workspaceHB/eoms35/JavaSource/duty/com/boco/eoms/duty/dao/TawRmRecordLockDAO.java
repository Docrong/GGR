//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmRecordSubDAO.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.common.util.*;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
public class TawRmRecordLockDAO extends DAO {

  public TawRmRecordLockDAO(com.boco.eoms.db.util.ConnectionPool  ds) {
    super(ds);
  }

  public void insert_lock(int workserial, String change_user_id,String change_user_name) throws SQLException {

    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TawRmRecordLockDAO tawRmRecordLockDAO = null;
    try {
      conn = ds.getConnection();
      String sql = "select dutyman from taw_rm_assign_sub where workserial = ? and dutyman <> ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, workserial);
      pstmt.setString(2, change_user_id);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        tawRmRecordLockDAO = new TawRmRecordLockDAO(ds);
        tawRmRecordLockDAO.insert(StaticMethod.dbNull2String(rs.getString(1)),change_user_name);
        tawRmRecordLockDAO = null;
      }
      close(rs);
      close(pstmt);
    } catch (SQLException e) {
      close(rs);
      close(pstmt);
      //rollback(conn);
      e.printStackTrace();
    } finally {
            close(conn);
    }
}


  public void insert(String user_id,String change_user_name) throws SQLException {

    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "";
    sql = "INSERT INTO  taw_rm_record_lock (user_id, change_user_name) VALUES (?, ?)";
    try {
      conn = ds.getConnection();
      //conn.setAutoCommit(true);
      //String sql = "insert into  taw_rm_record_lock (user_id, change_user_name) values ('"+user_id+"','"+change_user_name+"')";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString (1, user_id);
      pstmt.setString (2, change_user_name);
      pstmt.executeUpdate();
      close(pstmt);
      //conn.setAutoCommit(false);
      conn.commit();
    }  catch (SQLException e) {
      close(rs);
      close(pstmt);
      //rollback(conn);
      e.printStackTrace();
    } finally {
            close(conn);
    }
}





  public void delete(String  user_id) throws SQLException {
  	com.boco.eoms.db.util.BocoConnection conn = null;
  	PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      //conn.setAutoCommit(true);
      String sql = "DELETE FROM taw_rm_record_lock WHERE user_id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString (1, user_id);
      pstmt.executeUpdate();
      close(pstmt);
      //conn.setAutoCommit(false);
      conn.commit();
    } catch (SQLException e) {
      close(pstmt);
      //rollback(conn);
      e.printStackTrace();
    } finally {
    	close(conn);
    }
  }


  public int count(String user_id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int i = 0;
    try {
      conn = ds.getConnection();
      String sql = "SELECT count(id) from taw_rm_record_lock where user_id = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, user_id);
      rs = pstmt.executeQuery();
      if(rs.next()) {
        i = rs.getInt(1);
      }
      close(rs);
      close(pstmt);
    } catch (SQLException e) {
      close(rs);
      close(pstmt);
      //rollback(conn);
      e.printStackTrace();
    } finally {
            close(conn);
    }
    return i;
  }



  public String  message(String user_id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null ;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String message = "您好，您的同事";
    try {
      conn = ds.getConnection();
      String sql = "SELECT distinct change_user_name from taw_rm_record_lock where user_id = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, user_id);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        message += StaticMethod.dbNull2String(rs.getString(1))+"  ";
      }
      //message=StaticMethod.PageNStrRev(message);
      close(rs);
      close(pstmt);
         message += "修改了值班日志，您是否要刷新？";
    } catch (SQLException e) {
      close(rs);
      close(pstmt);
      //rollback(conn);
      e.printStackTrace();
    } finally {
            close(conn);
    }
    return message;
  }

}