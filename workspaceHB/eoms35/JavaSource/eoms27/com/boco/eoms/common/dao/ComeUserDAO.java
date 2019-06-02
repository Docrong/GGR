package com.boco.eoms.common.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.log.BocoLog;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;

public class ComeUserDAO extends DAO {
  public ComeUserDAO() {
     super();
   }
  public ComeUserDAO(com.boco.eoms.db.util.ConnectionPool  ds) {
    super(ds);
  }
  public void insertComeTable(String user_id,String user_name,int dept_id,String dept_name,
                                 String come_time,String come_ip)
    throws Exception{
  com.boco.eoms.db.util.BocoConnection conn = null;
  PreparedStatement pstmt = null;
  String sql="";
 // user_name=new String(para.getBytes("GB2312"), "ISO-8859-1")
  try{
  user_name=new String(user_name.getBytes("GB2312"),"ISO-8859-1");
  dept_name=new String(dept_name.getBytes("GB2312"),"ISO-8859-1");
  }
  catch (Exception e)  {
    e.printStackTrace();
    user_name="";
    dept_name="";
            }
  try {
    //pstmt = null; add
    conn=ds.getConnection();

    sql = "INSERT INTO taw_welcome(user_id,user_name,dept_id,dept_name,user_come_time,";
        sql = sql +"come_ip)   VALUES (?,?,?,?,?,?)";
     pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, user_id);
      pstmt.setString(2, user_name);
      pstmt.setInt(3, dept_id);
      pstmt.setString(4, dept_name);
      pstmt.setString(5, come_time);
      pstmt.setString(6, come_ip);
      pstmt.executeUpdate();
      pstmt.close();
      conn.commit();
  }
  catch (Exception sqle) {
    //close(pstmt);
    BocoLog.error(this,0,"插入登陆表数据报错!",sqle);
    close(pstmt);
   sqle.printStackTrace();
   throw sqle;
  }

  finally {
           sql=null;
           close(conn);
         }
}

 public List getComelogList(String sql) throws SQLException,
      UnsupportedEncodingException {
    List listrs = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        listrs.add(rs.getString("user_name"));
        listrs.add(rs.getString("dept_name"));
        listrs.add(rs.getString("user_trust"));
        listrs.add(rs.getString("user_come_time"));
        listrs.add(rs.getString("come_ip"));
        }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return listrs;
  }
}
