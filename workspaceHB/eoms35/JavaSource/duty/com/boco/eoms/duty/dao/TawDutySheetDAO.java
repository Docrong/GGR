package com.boco.eoms.duty.dao;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.util.Properties;
import java.util.Vector;
import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.duty.model.TawDutySheet;

/**
 * <p>Title: TawAutoSheetDAO</p>
 * <p>Description:值班附加表 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Boco</p>
 * @author: Lihong Qi
 * @version 1.0
 */

public class TawDutySheetDAO extends DAO{
  public TawDutySheetDAO(com.boco.eoms.db.util.ConnectionPool ds) {
     super(ds);
  }
  public TawDutySheetDAO() {
	    super();
	  }
  public void insert (TawDutySheet tawDutySheet) throws SQLException{
    com.boco.eoms.db.util.BocoConnection conn = null;
    try{
      PreparedStatement pstmt = null;
      String sql = "";
      sql = "INSERT INTO taw_dutysheet_rec(workserial,sheet_id,boco_id,oper_id,oper_time,url) "+
          " VALUES(?,?,?,?,?,?)";

      conn = ds.getConnection() ;
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1,tawDutySheet.getWorkserial());
      pstmt.setInt(2,tawDutySheet.getSheetId());
      pstmt.setInt(3,tawDutySheet.getBoco_Id());
      pstmt.setString(4,tawDutySheet.getOper_Id());
      pstmt.setString(5,tawDutySheet.getOper_Time());
      pstmt.setString(6, tawDutySheet.getUrl());

      pstmt.executeUpdate();
      conn.commit();

  } catch (SQLException e) {
    rollback(conn);
    e.printStackTrace();
    throw e;
  } finally {
    close(conn);
  }

  }

  /**
   * @see 根据班次得到表单id,name的Vector
   * @param workSerial
   * @return
   */
  public Vector getSheetListByWorkSerial(int workSerial){
    Vector retVector = new Vector();
    com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
    PreparedStatement pstmt = null;
    ResultSet  rs = null;
    try{
      String sql= "SELECT DISTINCT a.sheet_id,b.name AS name,a.url  FROM taw_dutysheet_rec a,taw_rm_addons b "+
         " WHERE a.sheet_id=b.id AND a.workserial=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1,workSerial);
      rs = pstmt.executeQuery();

      while (rs.next()){

        TawDutySheet tawDutySheet = new TawDutySheet();
        populate(tawDutySheet,rs);
        retVector.add(tawDutySheet);

        tawDutySheet = null;
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }

    return retVector;
  }

  //根据sheet_id得到附加表中的记录Id
  public String  getBocoIdsBySheetId(int sheetId,int workSerial){
  String strBocoIds = "";
  com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
  PreparedStatement pstmt = null;
  ResultSet  rs = null;
  try{
    String sql= "SELECT boco_id FROM taw_dutysheet_rec WHERE sheet_id=? And workserial=? ORDER BY boco_id";
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1,sheetId);
    pstmt.setInt(2,workSerial);
    rs = pstmt.executeQuery();

    while (rs.next()){
      if(strBocoIds.equalsIgnoreCase("")){
        strBocoIds = String.valueOf(rs.getInt("boco_id"));
      }else{
        strBocoIds = strBocoIds+","+ String.valueOf(rs.getInt("boco_id"));
      }
    }
    close(rs);
    close(pstmt);
  }
  catch (SQLException e) {
    close(rs);
    close(pstmt);
    e.printStackTrace();
  }
  finally {
    close(conn);
  }

  return strBocoIds;
}


//根据sql串得到遗留问题
 public String  getHangQuesByCond(String sql){
 String strHQ = "";
 com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
 PreparedStatement pstmt = null;
 ResultSet  rs = null;
 try{
   //String sql= "SELECT boco_id FROM taw_dutysheet_rec WHERE sheet_id=? ORDER BY boco_id";
   pstmt = conn.prepareStatement(sql);
   rs = pstmt.executeQuery();

   while (rs.next()){
     if(strHQ.equalsIgnoreCase("")){
       strHQ = String.valueOf(rs.getString(1));
     }else{
       strHQ = strHQ+"\r"+ String.valueOf(rs.getString(1));
     }
   }
   close(rs);
   close(pstmt);
 }
 catch (SQLException e) {
   close(rs);
   close(pstmt);
   e.printStackTrace();
 }
 finally {
   close(conn);
 }

 return strHQ;
}

//根据字段id得到字段名称
 public String  getFieldNameById(int attrId){
 String strFieldName = "";
 com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
 PreparedStatement pstmt = null;
 ResultSet  rs = null;
 try{
   String sql= "SELECT v_name FROM taw_sheetvalue WHERE attr_id=?";
   pstmt = conn.prepareStatement(sql);
   pstmt.setInt(1,attrId);
   rs = pstmt.executeQuery();

   if (rs.next()){
     strFieldName = rs.getString("v_name");
   }
   close(rs);
   close(pstmt);
 }
 catch (SQLException e) {
   close(rs);
   close(pstmt);
   e.printStackTrace();
 }
 finally {
   close(conn);
 }

 return strFieldName;
}

//根据sheet_id和机房id得到 by_Attr，to_Attr的属性Id
 public int  getAttrId(int sheetId,int roomId, int markType){
   //markType = 0  by_attr
   //markType = 1  to_attr
 int retAttrId = 0;
 com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
 PreparedStatement pstmt = null;
 ResultSet  rs = null;
 try{
   String sql = "";
   switch(markType){
     case 0:
          sql= "SELECT by_attr FROM taw_dutysheet_dict WHERE sheet_id=? AND room_id=?";
       break;
     case 1:
          sql= "SELECT to_attr FROM taw_dutysheet_dict WHERE sheet_id=? AND room_id=?";
       break;
   }

   pstmt = conn.prepareStatement(sql);
   pstmt.setInt(1,sheetId);
   pstmt.setInt(2,roomId);
   rs = pstmt.executeQuery();

   if (rs.next()){
     retAttrId = rs.getInt(1);
   }
   close(rs);
   close(pstmt);
 }
 catch (SQLException e) {
   close(rs);
   close(pstmt);
   e.printStackTrace();
 }
 finally {
   close(conn);
 }

 return retAttrId;
}


//判断是否是需要进入遗留问题的故障表
 public boolean verifyHangQuestion(int sheetId,int roomId){
  boolean retBoolean = false;
  com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
  PreparedStatement pstmt = null;
  ResultSet  rs = null;
 try{
   String iso="是";
   iso = StaticMethod.strFromPageToDB(iso);

   String sql= "SELECT id FROM taw_dutysheet_dict WHERE sheet_id=? AND room_Id=? "+
      " AND is_not_fault='"+iso+"' AND by_attr IS NOT NULL  AND to_attr IS NOT NULL";
   pstmt = conn.prepareStatement(sql);
   pstmt.setInt(1,sheetId);
   pstmt.setInt(2,roomId);
   rs = pstmt.executeQuery();

   if (rs.next()){
    retBoolean = true;
   }
   close(rs);
   close(pstmt);
 }
 catch (SQLException e) {
   close(rs);
   close(pstmt);
   e.printStackTrace();
 }
 finally {
   close(conn);
 }

 return retBoolean;
}
 public String getUrl(String id,String workid){
	  
	  com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
	  PreparedStatement pstmt = null;
	  ResultSet  rs = null;
	  String url = "";
	 try{
	  

	   String sql= "select * from taw_dutysheet_rec where sheet_id='"+id+"' and workserial='"+workid+"' ";
	   pstmt = conn.prepareStatement(sql);
	   rs = pstmt.executeQuery();

	   if (rs.next()){
		   url = rs.getString("url");
	   }
	   close(rs);
	   close(pstmt);
	 }
	 catch (SQLException e) {
	   close(rs);
	   close(pstmt);
	   e.printStackTrace();
	 }
	 finally {
	   close(conn);
	 }

	 return url;
	}
 
 public String getIdByUrl(String url){
	  
	  com.boco.eoms.db.util.BocoConnection conn = ds.getConnection();
	  PreparedStatement pstmt = null;
	  ResultSet  rs = null;
	  String id = "";
	 try{
	  

	   String sql= "select id from taw_dutysheet_rec where rul='"+url+"' ";
	   pstmt = conn.prepareStatement(sql);
	   rs = pstmt.executeQuery();

	   if (rs.next()){
		   id = rs.getString("id");
	   }
	   close(rs);
	   close(pstmt);
	 }
	 catch (SQLException e) {
	   close(rs);
	   close(pstmt);
	   e.printStackTrace();
	 }
	 finally {
	   close(conn);
	 }

	 return id;
	}
  
}
