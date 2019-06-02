package com.boco.eoms.infmanage.dao;

import java.sql.*;
import java.util.*;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.controller.*;
import com.boco.eoms.common.util.StaticMethod;

public class CollsheetDAO  extends DAO{
  public CollsheetDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }


  public void update(Collsheet collsheet)throws SQLException
  {
      com.boco.eoms.db.util.BocoConnection conn = null;
      conn = ds.getConnection();
      conn.setAutoCommit(true);
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      String sql = "";
      sql = "update worksheet_coll_okb set worksheet_type=?,region_code=?,key_word=?,fault_description=?,fault_anolyize=?,resovl_process=?,accessories=?"
          + " where id=?";

      try
      {
          pstmt = conn.prepareStatement(sql);
          System.out.println(sql);

          pstmt.setInt(1, collsheet.getWorksheet_type());
          pstmt.setString(2, collsheet.getRegion_code());
          pstmt.setString(3,collsheet.getKey_word());
          pstmt.setString(4, collsheet.getFault_description());
          pstmt.setString(5, collsheet.getFault_anolyize());
          pstmt.setString(6, collsheet.getResovl_process());
          pstmt.setString(7, collsheet.getAccessories());
          pstmt.setInt(8, collsheet.getId());
          pstmt.executeUpdate();
          close(pstmt);
          conn.setAutoCommit(false);
      }
      catch (Exception sqle)
      {
        //  conn.rollback();
       //   sqle.printStackTrace();
      }
      finally
      {
          close(conn);
      }
  }

  public void del(Collsheet collsheet)throws SQLException
   {
       com.boco.eoms.db.util.BocoConnection conn = null;
       conn = ds.getConnection();
       conn.setAutoCommit(true);
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       String sql = "";
       sql = "delete from worksheet_coll_okb "
           + " where id=?";

       try
       {
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           pstmt.setInt(1, collsheet.getId());
           pstmt.executeUpdate();
           close(pstmt);
           conn.setAutoCommit(false);
       }
       catch (Exception sqle)
       {
         //  conn.rollback();
        //   sqle.printStackTrace();
       }
       finally
       {
           close(conn);
       }
   }

  /**
   * 在综合经验库表中插入记录
   * @param tawInfInfo
   * @throws SQLException
   */
  public void insert(Collsheet collsheet) throws SQLException
  {
      com.boco.eoms.db.util.BocoConnection conn = null;
      conn = ds.getConnection();
      conn.setAutoCommit(true);
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      String sql = "";
      sql = "INSERT INTO worksheet_coll_okb (worksheet_type,region_code,achieve_person,achieve_time,key_word,fault_description,fault_anolyize,resovl_process,accessories)"
          + " VALUES (?,?,?,?,?,?,?,?,?)";

      try
      {
          pstmt = conn.prepareStatement(sql);
          System.out.println(sql);

          pstmt.setInt(1, collsheet.getWorksheet_type());
          pstmt.setString(2, collsheet.getRegion_code());
          pstmt.setString(3, collsheet.getAchieve_person());
          pstmt.setString(4,collsheet.getAchieve_time());
          pstmt.setString(5,collsheet.getKey_word());
          pstmt.setString(6, collsheet.getFault_description());
          pstmt.setString(7, collsheet.getFault_anolyize());
          pstmt.setString(8, collsheet.getResovl_process());
          pstmt.setString(9, collsheet.getAccessories());
          pstmt.executeUpdate();
          close(pstmt);
          conn.setAutoCommit(false);
      }
      catch (Exception sqle)
      {
        //  conn.rollback();
       //   sqle.printStackTrace();
      }
      finally
      {
          close(conn);
      }
  }


  public Collsheet getById(int id){
    Collsheet collsheet = new Collsheet();
    com.boco.eoms.db.util.BocoConnection conn = null;
    conn = ds.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "select a.*,b.user_name from worksheet_coll_okb a, taw_rm_user b where a.id=? and a.achieve_person=b.user_id order by a.id";
         try
         {
           pstmt = conn.prepareStatement(sql);
           System.out.println(sql);
           pstmt.setInt(1, id);
             rs = pstmt.executeQuery();
             while (rs.next())
             {
                 collsheet.setId(rs.getInt("id"));
                 collsheet.setWorksheet_type(rs.getInt("worksheet_type"));
                 collsheet.setRegion_code(StaticMethod.dbNull2String(rs.getString("region_code")));
                 collsheet.setAchieve_person(StaticMethod.dbNull2String(rs.getString("user_name")));
                 collsheet.setAchieve_time(StaticMethod.dbNull2String(rs.getString("achieve_time")));
                 collsheet.setkey_word(StaticMethod.dbNull2String(rs.getString("key_word")));
                 collsheet.setFault_description(StaticMethod.dbNull2String(rs.getString("fault_description")));
                 collsheet.setFault_anolyize(StaticMethod.dbNull2String(rs.getString("fault_anolyize")));
                 collsheet.setResovl_process(StaticMethod.dbNull2String(rs.getString("resovl_process")));
                 collsheet.setAccessories(StaticMethod.dbNull2String(rs.getString("accessories")));
             }
         }
         catch (SQLException e)
         {
            // e.printStackTrace();
         }
         finally
         {
             close(rs);
             close(pstmt);
             close(conn);
         }
         return collsheet;
  }

  public List getList(String condition,int offset, int length){
       ArrayList list=new ArrayList();
       com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = conn.prepareStatement(condition);
            System.out.println(condition);
            rs = pstmt.executeQuery();
            if (offset > 0)
            {
                int i = 0;
                while (i++ < offset)
                {
                    rs.next();
                }
            }

            int recCount = 0;
            while ( (recCount++ < length) && rs.next())
            {
                Collsheet collsheet = new Collsheet();
                collsheet.setId(rs.getInt("id"));
                collsheet.setWorksheet_type(rs.getInt("worksheet_type"));
                collsheet.setRegion_code(StaticMethod.dbNull2String(rs.getString("region_code")));
                collsheet.setAchieve_person(StaticMethod.dbNull2String(rs.getString("user_name")));
                collsheet.setAchieve_time(StaticMethod.dbNull2String(rs.getString("achieve_time")));
                collsheet.setkey_word(StaticMethod.dbNull2String(rs.getString("key_word")));
                collsheet.setFault_description(StaticMethod.dbNull2String(rs.getString("fault_description")));
                collsheet.setFault_anolyize(StaticMethod.dbNull2String(rs.getString("fault_anolyize")));
                collsheet.setResovl_process(StaticMethod.dbNull2String(rs.getString("resovl_process")));
                list.add(collsheet);
            }
        }
        catch (SQLException e)
        {
           // e.printStackTrace();
        }
        finally
        {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return list;
  }

  public int getSize(String condition){
        int size=0;
        com.boco.eoms.db.util.BocoConnection conn = null;
         conn = ds.getConnection();
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try
         {
             pstmt = conn.prepareStatement(condition);
             System.out.println(condition);
             rs = pstmt.executeQuery();
             rs.next();
            size = rs.getInt(1);

           }
         catch (SQLException e)
         {
            // e.printStackTrace();
         }
         finally
         {
           if (rs != null)
          {
              rs = null;
          }
          if (pstmt != null)
          {
              pstmt = null;
          }
          if (conn != null)
          {
              conn = null;
          }

         }
         return size;
   }

}
