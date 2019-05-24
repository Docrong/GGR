package com.boco.eoms.infmanage.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.dao.*;
import org.apache.struts.util.LabelValueBean;
import com.boco.eoms.infmanage.controller.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import java.util.Vector;

public class TawInfGroupDAO
    extends DAO {
  /**
   * @see   构造方法
   * @see  获得数据库的连接
   * @param ds  DataSource 数据源（由Struts框架所提供）
   */
  public TawInfGroupDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  public void insert(TawInfGroup tawInfGroup) throws SQLException {
    String sql;
    sql = "INSERT INTO taw_inf_Group (group_name, user_id) " +
        "VALUES (?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, tawInfGroup.getGroupName());

      pstmt.setString(2, tawInfGroup.getUserId());

      pstmt.executeUpdate();
      pstmt.close();
      conn.commit();
    }
    catch (SQLException sqle) {
   //   close(pstmt);
    //  rollback(conn);
    //  sqle.printStackTrace();
    }
    finally {
      close(conn);
    }
  }

  public List selectByCondition(int offset, int limit, String condition) throws
      SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT * FROM taw_inf_Group " +
          condition;
      System.out.println(sql);

      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if (offset > 0) {
        int i = 0;
        while (i++ < offset) {
          rs.next();
        }
      }

      int recCount = 0;
      while ( (recCount++ < limit) && rs.next()) {
        TawInfGroup tawInfGroup = new TawInfGroup();
        //populate(tawInfGroup, rs);

        tawInfGroup.setGroupId(rs.getInt("group_id"));
        tawInfGroup.setGroupName(StaticMethod.dbNull2String(rs.getString(
            "group_name")).
                                 trim());
        tawInfGroup.setUserId(
            StaticMethod.dbNull2String(rs.getString(
            "user_id")).trim());

        list.add(tawInfGroup);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
    //  close(rs);
   //   close(pstmt);
   ////   rollback(conn);
   //   e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return list;
  }

  public int getSize(String tableName, String condition) {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
int size = 0;
    try {
      String sql = "";
      //-------------------------
      //当条件语句中有order by的时候，在informix中无法计算count，所以要去掉排序的部分
      //------------------------
      if (condition.lastIndexOf("order by") != -1) {
        condition = condition.substring(0,
                                        condition.lastIndexOf(
            "order by"));
      }
      sql = "SELECT count(*) FROM " + tableName + " " + condition;

      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
     if(rs.next())
     { size = rs.getInt(1);}

    }
    catch (SQLException sqle) {
     // sqle.printStackTrace();
     // throw sqle;
    }
    finally {
      close(conn);
    }

    return size;
  }

  public TawInfGroup getById(int id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    TawInfGroup tawInfGroup = new TawInfGroup();
    try {
      String sql = "";
      sql = "SELECT * FROM taw_inf_Group WHERE group_id=? ";
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) {

        //populate(tawInfGroup,rs);

        tawInfGroup.setGroupId(rs.getInt("group_id"));
        tawInfGroup.setGroupName(StaticMethod.dbNull2String(rs.getString(
            "group_name")).
                                 trim());
        tawInfGroup.setUserId(
            StaticMethod.dbNull2String(rs.getString(
            "user_id")).trim());
      }
    }
    catch (Exception e) {
     // e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return tawInfGroup;
  }

  public void delete(int id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    conn = ds.getConnection();
    PreparedStatement pstmt = null;

    try {
      String sql = "";
      sql = "DELETE FROM taw_inf_Group WHERE group_id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);

      pstmt.executeUpdate();
      conn.commit();
    }
    catch (Exception e) {
     // conn.rollback();
     // e.printStackTrace();
    }
    finally {
     close(conn);
    }
  }

  public void update(TawInfGroup tawInfGroup) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    conn = ds.getConnection();
    PreparedStatement pstmt = null;
    try {
      String sql = "";
      sql =
          "UPDATE taw_inf_Group SET  group_name=? " +
          "WHERE group_id=?";

      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, StaticMethod.null2String(tawInfGroup.getGroupName().
                                                  trim()));
      pstmt.setInt(2, tawInfGroup.getGroupId());
      System.out.println(sql);
      pstmt.executeUpdate();
      conn.commit();
    }
    catch (Exception e) {
   //   conn.rollback();
   //   e.printStackTrace();
    }
    finally {
      close(conn);
    }
  }

  public Vector getAllGroupName(String userId) throws SQLException {
    Vector allGroupName = new Vector();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    TawInfAddressBookForm tawInfAddressBookForm = new TawInfAddressBookForm();
    try {
      String sql = "";
      sql = "SELECT * from taw_inf_group WHERE user_id='" + userId + "'";
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        allGroupName.add(StaticMethod.dbNull2String(rs.getString("group_name")).trim());
      }
    }
    catch (Exception e) {
     // e.printStackTrace();
    }
    finally {
     close(conn);
    }
    return allGroupName;
  }

}
