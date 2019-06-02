package com.boco.eoms.testcard.dao;

/**
 * <p>Title:�¼�����ģ���ֵ������ݿ���� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author �����
 * @version 1.0
 */

import java.sql.*;
import java.util.*;
import javax.sql.*; 
import com.boco.eoms.common.util.*;
import com.boco.eoms.common.dao.*; 
import com.boco.eoms.commons.system.dept.*;
import com.boco.eoms.testcard.model.TawEventDic;

public class TawEventDicDAO extends DAO {

  public TawEventDicDAO(com.boco.eoms.db.util.ConnectionPool  ds) {
    super(ds);
  }
  public TawEventDicDAO() {
  }

  public TawEventDic retrieve(int id) throws SQLException {
   TawEventDic tawEventDic = null;
   com.boco.eoms.db.util.BocoConnection conn=null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   try {
     conn = ds.getConnection();
      String sql = "SELECT id,type_id,parent_id,name,base,remark FROM taw_eventdic WHERE deleted=0 and id="+id;
     pstmt = conn.prepareStatement(sql);
     rs = pstmt.executeQuery();
     if (rs.next()) {
       tawEventDic = new TawEventDic();
       populate(tawEventDic, rs);
       tawEventDic.setType_id(rs.getString(2));
     }
     close(rs);
     close(pstmt);
   } catch (SQLException e) {
     close(rs);
     close(pstmt);
     e.printStackTrace();
   } finally {
   close(conn);
   }
   return tawEventDic;
 }

 public int findParent_id(int id) throws SQLException {
   int parent_id=0;
   com.boco.eoms.db.util.BocoConnection conn=null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   try {
     conn = ds.getConnection();
     String sql = "SELECT parent_id FROM taw_eventdic where id="+id;
     System.out.println(sql);
     pstmt = conn.prepareStatement(sql);
     rs = pstmt.executeQuery();
       while(rs.next()) {
       parent_id=rs.getInt(1);
     }
   } catch (SQLException e) {
     close(rs);
     close(pstmt);
     e.printStackTrace();
   } finally {
           close(conn);
   }
   return parent_id;
  }

  public int findBase(int id) throws SQLException {
    int base=0;
    com.boco.eoms.db.util.BocoConnection conn=null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT base FROM taw_eventdic where id="+id;
      System.out.println(sql);
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
        while(rs.next()) {
        base=rs.getInt(1);
      }
    } catch (SQLException e) {
      close(rs);
      close(pstmt);
      e.printStackTrace();
    } finally {
            close(conn);
    }
    return base;
   }

  public Vector list(String type_id) throws SQLException {
    Vector list=new Vector();
     com.boco.eoms.db.util.BocoConnection conn=null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     TawEventDic tawEventDic = null;
     try {
       conn = ds.getConnection();
       String sql = "SELECT id,type_id,parent_id,name FROM taw_eventdic where parent_id=(select distinct id from taw_eventdic where type_id='"+type_id+"') and deleted=0 ORDER BY name";
       //System.out.println(sql);
       pstmt = conn.prepareStatement(sql);
       rs = pstmt.executeQuery();
         while(rs.next()) {
         tawEventDic = new TawEventDic();
         populate(tawEventDic, rs);
         String device=rs.getString(4);
         tawEventDic.setName(device);
         tawEventDic.setId(rs.getInt(1));
         list.add(tawEventDic);
  }
       close(rs);
       close(pstmt);
     } catch (SQLException e) {
       close(rs);
       close(pstmt);
       e.printStackTrace();
     } finally {
       tawEventDic=null;
       close(conn);
     }
     return list;
   }

   public Vector list(String type_id,int deptid) throws SQLException {
   Vector list=new Vector();
    com.boco.eoms.db.util.BocoConnection conn=null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TawEventDic tawEventDic = null;
    String sql="";
    try {
      conn = ds.getConnection();
      if (deptid==1){
        sql = "SELECT id,type_id,parent_id,name FROM taw_eventdic where parent_id=(select distinct id from taw_eventdic where type_id='" +
            type_id + "') and deleted=0 ORDER BY name";
      }else{
        sql = "SELECT id,type_id,parent_id,name FROM taw_eventdic where deleted=0 and type_id='" + deptid +
            "' ORDER BY name";
      }
      //System.out.println(sql);
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
        while(rs.next()) {
        tawEventDic = new TawEventDic();
        populate(tawEventDic, rs);
        String device=rs.getString(4);
        tawEventDic.setName(device);
        tawEventDic.setId(rs.getInt(1));
        list.add(tawEventDic);
 }
      close(rs);
      close(pstmt);
    } catch (SQLException e) {
      close(rs);
      close(pstmt);
      e.printStackTrace();
    } finally {
      tawEventDic=null;
      close(conn);
    }
    return list;
  }


   public Vector listarr(String type_id) throws SQLException {
     Vector list=new Vector();
      com.boco.eoms.db.util.BocoConnection conn=null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      TawEventDic tawEventDic =null;
      try {
        conn = ds.getConnection();
        String Sql="select id from taw_eventdic where type_id='"+type_id+"'";
        pstmt=conn.prepareStatement(Sql);
        rs=pstmt.executeQuery();
        int id=0;
        while(rs.next())
        {
          id=rs.getInt(1);
        }
        String sql = "SELECT id,type_id,parent_id,name FROM taw_eventdic where parent_id=(select id from taw_eventdic where parent_id=(SELECT id FROM taw_eventdic where parent_id="+id+")) ORDER BY name";
        //System.out.println(sql);
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
          while(rs.next()) {
          tawEventDic = new TawEventDic();
          populate(tawEventDic, rs);
          String device=rs.getString(4);
          tawEventDic.setName(StaticMethod.dbNull2String(device));
          tawEventDic.setId(rs.getInt(1));
          list.add(tawEventDic);
   }
        close(rs);
        close(pstmt);
      } catch (SQLException e) {
        close(rs);
        close(pstmt);
        e.printStackTrace();
      } finally {
        tawEventDic=null;
        close(conn);
      }
      return list;
    }
   public String findName(String id) throws SQLException {
       String name="";
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
          conn = ds.getConnection();
          String sql = "SELECT name FROM taw_eventdic where id="+Integer.parseInt(id);
          pstmt = conn.prepareStatement(sql);
          rs = pstmt.executeQuery();
      while(rs.next()) {
            name=rs.getString(1);
     }
          close(rs);
          close(pstmt);
    } catch (SQLException e) {
          close(rs);
          close(pstmt);
          e.printStackTrace();
    } finally {
      close(conn);
    }
      return name;
  }
   public String findId(String name) throws SQLException {
       String id="";
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
          conn = ds.getConnection();
          String sql = "SELECT id FROM taw_eventdic where name='"+name+"'";
          pstmt = conn.prepareStatement(sql);
          rs = pstmt.executeQuery();
      while(rs.next()) {
            id=rs.getString(1);
     }
          close(rs);
          close(pstmt);
    } catch (SQLException e) {
          close(rs);
          close(pstmt);
          e.printStackTrace();
    } finally {
      close(conn);
    }
      return id;
  }
  public void insert(TawEventDic tawEventDic) throws SQLException {
      String sql="";
      com.boco.eoms.db.util.BocoConnection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String list_id="";
      try {
        sql="select max(list_id) from taw_eventdic where parent_id="+tawEventDic.getId();
        conn = ds.getConnection();
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next())
        {
          try{
            list_id = String.valueOf(Integer.parseInt(rs.getString(1)) + 1);
          }
          catch(Exception e){
            sql="select list_id from taw_eventdic where id="+tawEventDic.getId();
            conn = ds.getConnection();
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next()){
              list_id = rs.getString(1) + "01";
            }
          }
        }
        sql="select max(id) from taw_eventdic ";
        conn = ds.getConnection();
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        int id=0;
        if(rs.next()){
          id = rs.getInt(1) + 1;
        }
        sql = "insert into taw_eventdic(id,type_id,parent_id,name,base,remark,deleted,list_id) VALUES (?,?,?,?,?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        pstmt.setString(2,tawEventDic.getType_id());
        pstmt.setInt(3, tawEventDic.getId());
        pstmt.setString(4, tawEventDic.getName());
        pstmt.setInt(5,tawEventDic.getBase());
        pstmt.setString(6,tawEventDic.getRemark());
        pstmt.setInt(7,0);
        pstmt.setString(8,list_id);
        pstmt.executeUpdate();
        pstmt.close();
        conn.commit();
      } catch (SQLException sqle) {
        close(pstmt);
        sqle.printStackTrace();
        throw sqle;
      } finally {
        sql=null;
        tawEventDic=null;
        close(conn);
      }
  }

  public void update(TawEventDic tawEventDic) throws SQLException {
      String sql="";
      com.boco.eoms.db.util.BocoConnection conn = null;
      PreparedStatement pstmt = null;
      try {
        sql = "update taw_eventdic set type_id=?,name=?,base=?,remark=? where id=?";
        conn = ds.getConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,tawEventDic.getType_id());
        pstmt.setString(2, tawEventDic.getName());
        pstmt.setInt(3,tawEventDic.getBase());
        pstmt.setString(4,tawEventDic.getRemark());
        pstmt.setInt(5,tawEventDic.getId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.commit();
      } catch (SQLException sqle) {
        close(pstmt);
        sqle.printStackTrace();
        throw sqle;
      } finally {
        sql=null;
        tawEventDic=null;
        close(conn);
      }
  }
  public void deleted(int id)throws SQLException{
    String sql="";
    com.boco.eoms.db.util.BocoConnection conn=null;
    PreparedStatement pstmt=null;
    try{
      sql="update taw_eventdic set deleted=1 where id="+id+" or parent_id="+id;
      conn=ds.getConnection();
      pstmt=conn.prepareStatement(sql);
      pstmt.executeQuery();
      pstmt.close();
      conn.commit();
    }catch(SQLException sqle){
      close(pstmt);
      sqle.printStackTrace();
      throw sqle;
    }finally{
      sql=null;
      close(conn);
    }
  }
  public int getId(int id) throws SQLException {
    int ret = 0;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT id FROM taw_eventdic WHERE id =" + id;
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        ret = rs.getInt(1);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
    }
    finally {
      close(conn);
    }
    return ret;
  }

  public int getId(String name) throws SQLException {
    int ret = 0;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql =
          "SELECT id FROM taw_eventdic WHERE name ='" + name+"'";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        ret = rs.getInt(1);
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
    }
    finally {
      close(conn);
    }
    return ret;
  }
    /*
   * add by duanbin
   */
  public int isprovince (int _deptid) throws SQLException {
    int deptid=-1;
   com.boco.eoms.db.util.BocoConnection conn=null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   try {
     conn = ds.getConnection();
     String sql = "SELECT type_id FROM taw_eventdic where parent_id='11' and deleted=0 ORDER BY name";
     pstmt = conn.prepareStatement(sql);
     rs = pstmt.executeQuery();
       while(rs.next()) {
       int temp = rs.getInt(1);
       if(temp == _deptid){
         deptid=_deptid;
         break;
       }
}
     close(rs);
     close(pstmt);
   } catch (SQLException e) {
     close(rs);
     close(pstmt);
     e.printStackTrace();
   } finally {
     close(conn);
   }
   return deptid;
 }

/*
 ��ѯ����ʡ�ĵ��зֹ�˾
 */
public List findfiliale ()throws SQLException{
  com.boco.eoms.db.util.BocoConnection conn = null;
  List filiale = new ArrayList();
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  String sql="select name from taw_testcard_tree where layer='0_0_10'";
  try {
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    while (rs.next()) {
      filiale.add(rs.getString(1));
    }
    close(rs);
    close(pstmt);
  }
  catch (SQLException ex) {
    close(rs);
    close(pstmt);
    ex.printStackTrace();
  }finally {
     close(conn);
   }

  return filiale;
}

}
