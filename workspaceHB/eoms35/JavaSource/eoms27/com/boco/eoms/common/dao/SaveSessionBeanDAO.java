//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawUserRoomDAO.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.common.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;

import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.DAO;



public class SaveSessionBeanDAO extends DAO {

  public SaveSessionBeanDAO(com.boco.eoms.db.util.ConnectionPool  ds) {
    super(ds);
  }

  public void alterDateFormat() throws SQLException {
    com.boco.eoms.db.util.BocoConnection  conn = null;
    PreparedStatement pstmt = null;
    try {
      /*if(StaticMethod.getDbType().equals(StaticVariable.ORACLE)){
        conn = ds.getConnection();
        String sql = "alter session set NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS'";
        pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        pstmt.close();
        conn.commit();
        conn.close();
      }*/
    }
    catch (Exception e) {
    }
    finally{
      if(conn != null)
        conn = null;
      pstmt = null;
    }
  }
}
