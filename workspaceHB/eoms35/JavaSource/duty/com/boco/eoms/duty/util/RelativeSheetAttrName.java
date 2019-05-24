package com.boco.eoms.duty.util;

import java.sql.*;
import java.io.*;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.*;
import java.util.*;

/* 选择定制的附加表的字段属性名称  */
public class RelativeSheetAttrName {
  //String strTree = "";
  ConnectionPool pool = ConnectionPool.getInstance();
  Connection conn = null;

  public RelativeSheetAttrName() {
    try {
      conn = pool.getConnection();
    }
    catch(Exception ex) {
    }
  }

  public String strRelativeDrop() {
    String strwktree = "";
    String strTree1 = "";
    Hashtable hash = new Hashtable();
    int moduleId = 11;  //值班模块
    try {
      strwktree = this.getAttrNameValue(moduleId);
    }
    catch (Exception e) {
    }

    return strwktree;
  }

  public String getAttrNameValue(int moduleId){
    String user = "";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try{
      String sql = null;
  //    sql = "SELECT dept_id,user_name,user_id FROM taw_rm_user  "+
   //           "WHERE deleted="+ StaticVariable.UNDELETED+" ORDER BY dept_id,user_name ";
      sql = "SELECT sheet_id,attr_name,attr_id FROM taw_sheetattr "+
          "WHERE sheet_id in (SELECT sheet_id FROM taw_sheetname WHERE module_id=?)";

      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1,moduleId);

      rs = pstmt.executeQuery();
      ArrayList lst = new ArrayList();
      int flag = StaticVariable.defnull;   // StaticVariable.defnull = -10

      while(rs.next()) {
        int iTp = rs.getInt(1);
        if(flag == iTp){
          user +="'" + StaticMethod.dbNull2String(rs.getString(2)) + "','" +
                   StaticMethod.dbNull2String(rs.getString(3)) + "'" + ",";
        }
        else{
          if(user.endsWith(","))
            user =  user.substring(0,user.length() - 1);
          user += "); arrSheetAttrName["+iTp+"]=" +" new Array('"+StaticMethod.dbNull2String(rs.getString(2)) + "','" +
                   StaticMethod.dbNull2String(rs.getString(3)) + "'" + ",";
          flag = iTp;
        }
      }
      user = user.substring(2);
      if(user.endsWith(","))
        user = user.substring(0,user.length() - 1);
      user += ");";

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try{
        if(rs != null)
          rs.close();
        if(pstmt != null)
          pstmt.close();
        if(conn != null)
          conn.close();
        conn = null;
      }
      catch(Exception e){
      }
    }
    return user;
  }
}
