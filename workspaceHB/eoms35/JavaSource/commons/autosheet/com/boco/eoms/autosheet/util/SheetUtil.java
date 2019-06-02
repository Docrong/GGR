package com.boco.eoms.autosheet.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import java.io.*;

import com.boco.eoms.common.util.*;
import com.boco.eoms.db.util.*;
import com.boco.eoms.autosheet.util.*;
//import com.boco.eoms.log.bo.logBO;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.dao.*;


public class SheetUtil{
  private ConnectionPool ds = ConnectionPool.getInstance();

  static public int OPE_AUTOSHEET_ADD = 1900;

  static public int OPE_AUTOSHEET_DEL = 1901;

  static public int OPE_AUTOSHEET_EDIT = 1902;

   static public int OPE_AUTOSHEET_RENEW = 1903;

  public SheetUtil() {

  }
  public int getIndex(String sheetID){
     int index =0;
     int attr_index =0;
     int value_index =0;
     String sql ="select max(index1) from taw_sheetattr where sheet_id ="+sheetID;
     String sql1 ="select max(index1) from taw_sheetvalue where sheet_id ="+sheetID;
      com.boco.eoms.db.util.BocoConnection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs= null;
      try {
        conn = ds.getConnection();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        if(rs.next())
          attr_index =rs.getInt(1);
        pstmt.close();

        pstmt = conn.prepareStatement(sql1);
        rs = pstmt.executeQuery();
        if(rs.next())
          value_index =rs.getInt(1);
        pstmt.close();

      }
      catch (SQLException sqle) {
      try{
        pstmt.close();

      }
      catch(Exception e){
          e.printStackTrace();
      }
       sqle.printStackTrace();
      }
      finally {
       conn.close();
      }
     if(value_index>attr_index)
       index =value_index+1;
     else
       index =attr_index+1;
     return index;
   }

   public String getAppName(String appId){
     String name ="";
     String sql="select app_name from taw_application where app_id ='"+appId+"'";
     com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs= null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if(rs.next())
        name =rs.getString(1);
      pstmt.close();

    }
    catch (SQLException sqle) {
    try{
      pstmt.close();

    }
    catch(Exception e){
        e.printStackTrace();
    }
     sqle.printStackTrace();
    }
    finally {
     conn.close();
    }

     return StaticMethod.dbNull2String(name);
   }

   public String getAttrId(String sheetID) {
     String attr_id = "";
     int value_num = 0;
     String sql = "select count(*) from taw_sheetvalue where sheet_id =" + sheetID;
     com.boco.eoms.db.util.BocoConnection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     try {
       conn = ds.getConnection();
       pstmt = conn.prepareStatement(sql);
       rs = pstmt.executeQuery();

       if (rs.next())
         value_num = rs.getInt(1);
       pstmt.close();

     }
     catch (SQLException sqle) {
       try {
         pstmt.close();

       }
       catch (Exception e) {
         e.printStackTrace();
       }
       sqle.printStackTrace();
     }
     finally {
       conn.close();
     }

     sql = "select attr_id from taw_sheetattr where sheet_id =" + sheetID;
     pstmt = null;
     rs = null;
     try {
       conn = ds.getConnection();
       pstmt = conn.prepareStatement(sql);
       rs = pstmt.executeQuery();
       int i=0;
       while(rs.next()){
         attr_id = rs.getString(1);
         i++;
         if(i==value_num+1)
           break;
       }
       pstmt.close();

     }
     catch (SQLException sqle) {
       try {
         pstmt.close();

       }
       catch (Exception e) {
         e.printStackTrace();
       }
       sqle.printStackTrace();
     }
     finally {
       conn.close();
     }

     return attr_id;
   }

   public String[] getUT(String sheetID, String id) {
     String[] ut = new String[2];
     String sql = "select user_id,systime from boco_"+sheetID+" where id =" + id + "";
     com.boco.eoms.db.util.BocoConnection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     try {
       conn = ds.getConnection();
       pstmt = conn.prepareStatement(sql);
       rs = pstmt.executeQuery();

       if (rs.next()) {
         ut[0] = rs.getString(1);
         ut[1] = rs.getString(2);
       }
       pstmt.close();

     }
     catch (SQLException sqle) {
       try {
         pstmt.close();
       }
       catch (Exception e) {
         e.printStackTrace();
       }
       sqle.printStackTrace();
     }
     finally {
       conn.close();
     }
     return ut;
  }
}
