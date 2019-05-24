package com.boco.eoms.sparepart.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.controller.TawSparepartForm;
import com.boco.eoms.sparepart.model.TawTempPart;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawTempPartDAO
      extends DAO{

    public TawTempPartDAO(){
    }

    public TawTempPartDAO(ConnectionPool ds){
        super(ds);
    }

    /**
     *
     * @param SQL
     */
    public void deleteTempPart(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="delete from taw_sp_temp_part "+SQL;
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.commit();
        }
        catch(SQLException sqle){
            rollback(conn);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }

    /**
     *
     * @param id
     * @param user_id
     * @see insert choosed id of sparepart
     */
    public void insertTempPart(String id,String user_id,String order_id){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="";
        sql="INSERT INTO taw_sp_temp_part(sparepart_id,user_id,order_id)"+
              " VALUES (?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,id);
            pstmt.setString(2,user_id);
            pstmt.setString(3,order_id);

            pstmt.executeUpdate();
            conn.commit();
        }
        catch(SQLException sqle){
            rollback(conn);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }

    /**
     *
     * @param form
     * @return
     * @throws SQLException
     */
    public List getTempPart(String userId,String orderId) throws SQLException{
        ArrayList list=new ArrayList();

        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select t1.order_id, t1.sparepart_id, t2.org_serial_no org_serial_no,t2.serialno new_serial_no  "+
                  "from taw_sp_temp_part t1,taw_sp_sparepart t2 where t1.sparepart_id = t2.id "+
                  " and t1.user_id=? and t1.order_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,orderId);
            rs=pstmt.executeQuery();
            while(rs.next()){
                TawTempPart tawTempPart=new TawTempPart();
                populate(tawTempPart,rs);
                list.add(tawTempPart);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }

    /**
     * @see 获得临时表中的备件id字符串之和
     * @param SQL
     * @return 备件id字符串之和
     * @throws SQLException
     */
    public String getTempPartId(String SQL) throws SQLException{
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String SumId="";
        try{
            conn=ds.getConnection();
            String sql="select sparepart_id from taw_sp_temp_part "+SQL;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                String Id=rs.getString(1);
                SumId=SumId+","+Id;
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        if(SumId.length()>=1){
            SumId=SumId.substring(1,SumId.length());
        }
        return SumId;
    }

    //将要转库的备件ID和执行该操作的用户ID存放到临时表taw_remove_temp_part表中
    public void insertRemovePart(String sparepart_id,String user_id){
      com.boco.eoms.db.util.BocoConnection conn=null;
      conn=ds.getConnection();
      PreparedStatement pstmt=null;

      String sql="";
      sql="INSERT INTO taw_remove_temp_part(sparepart_id,user_id)"+
            " VALUES (?,?)";
      try{
          pstmt=conn.prepareStatement(sql);
          pstmt.setString(1,sparepart_id);
          pstmt.setString(2,user_id);

          pstmt.executeUpdate();
          conn.commit();
      }
      catch(SQLException sqle){
          rollback(conn);
          sqle.printStackTrace();
      }
      finally{
          close(pstmt);
          close(conn);
      }
    }

    //从临时表中按照用户名，取出准备进行转库操作的备件ID
    public List getRemovePartId(String userId){
      ArrayList RemoveList = new ArrayList();
      com.boco.eoms.db.util.BocoConnection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      try{
          conn=ds.getConnection();
          String sql="select sparepart_id "+
                "from taw_remove_temp_part where user_id=?";
          pstmt=conn.prepareStatement(sql);
          pstmt.setString(1,userId);
          rs=pstmt.executeQuery();
          while(rs.next()){
              RemoveList.add(rs.getString(1));
          }
          close(rs);
          close(pstmt);
      }
      catch(SQLException e){
          close(rs);
          close(pstmt);
          e.printStackTrace();
      }
      finally{
          close(conn);
      }
      return RemoveList;
    }

    public String getRemoveTempPartId(String SQL) throws SQLException{
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String SumId="";
        try{
            conn=ds.getConnection();
            String sql="select sparepart_id from taw_remove_temp_part "+SQL;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                String Id=rs.getString(1);
                SumId=SumId+","+Id;
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            close(rs);
            close(pstmt);
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        if(SumId.length()>=1){
            SumId=SumId.substring(1,SumId.length());
        }
        return SumId;
    }
    /**
     *
     * @param SQL
     */
    public void deleteRemoveTempPart(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="delete from taw_remove_temp_part "+SQL;
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.commit();
        }
        catch(SQLException sqle){
            rollback(conn);
            sqle.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }
}
