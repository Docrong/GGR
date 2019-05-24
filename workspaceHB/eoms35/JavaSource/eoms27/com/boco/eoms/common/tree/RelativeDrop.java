package com.boco.eoms.common.tree;

import java.sql.*;
import java.io.*;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.*;
import java.util.*;

public class RelativeDrop {
  String strTree = "";
  ConnectionPool pool = ConnectionPool.getInstance();
  Connection conn = null;

  public RelativeDrop() {
  }

  public String strRelativeDrop(String sDeptIds) {
    String strwktree = "";
    String strTree1 = "";
    Hashtable hash = new Hashtable();

    try {
      strwktree = this.getUser(sDeptIds);
    }
    catch (Exception e) {
    }

    return strwktree;
  }

  public String getUser(String sDeptIds) {
    String user = "";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = pool.getConnection();
      String sql = null;
      /*if (sDeptIds.equals(""))
        sql = "SELECT dept_id,user_name,user_id FROM taw_rm_user  " +
            "WHERE deleted=" + StaticVariable.UNDELETED +
            " ORDER BY dept_id,user_name ";
      else
        sql = "SELECT dept_id,user_name,user_id FROM taw_rm_user  " +
            "WHERE deleted=" + StaticVariable.UNDELETED +
            " AND dept_id IN (" + sDeptIds + ") ORDER BY dept_id,user_name ";*/
      if (sDeptIds.equals(""))
          sql = "SELECT deptid,username,userid FROM taw_system_user  " +
              "WHERE deleted=" + StaticVariable.UNDELETED +
              " ORDER BY deptid,username ";
        else
          sql = "SELECT deptid,username,userid FROM taw_system_user  " +
              "WHERE deleted=" + StaticVariable.UNDELETED +
              " AND deptd IN (" + sDeptIds + ") ORDER BY deptid,username ";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      ArrayList lst = new ArrayList();
      int flag = StaticVariable.defnull;
      while (rs.next()) {
        int iTp = rs.getInt(1);
        if (flag == iTp) {
          user += "'" + rs.getString(2) + "','" +
              rs.getString(3) + "'" + ",";
        }
        else {
          if (user.endsWith(","))
            user = user.substring(0, user.length() - 1);
          user += "); User[" + iTp + "]=" + " new Array('" +
              rs.getString(2) + "','" +
             rs.getString(3) + "'" + ",";
          flag = iTp;
        }
      }
      user = user.substring(2);
      if (user.endsWith(","))
        user = user.substring(0, user.length() - 1);
      user += ");";

      rs.close();
      pstmt.close();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (rs != null)
          rs.close();
        if (pstmt != null)
          pstmt.close();
        if (conn != null)
          conn.close();
      }
      catch (Exception e) {
      }
    }
    return user;
  }
}