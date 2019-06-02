package com.boco.eoms.sparepart.dao;


import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sparepart.controller.TawOrderForm;
import com.boco.eoms.sparepart.model.TawOrderDetail;

public class TawOrderDetailDAO extends DAO {

  public TawOrderDetailDAO(ConnectionPool ds){
      super(ds);
  }

  public List getTawOrderDetail(String SQL) throws SQLException{
      ArrayList list=new ArrayList();
      com.boco.eoms.db.util.BocoConnection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      try{
          conn=ds.getConnection();
          String sql=
                "select * from vaw_sp_order_detail "+SQL;
          pstmt=conn.prepareStatement(sql);
          rs=pstmt.executeQuery();
          while(rs.next()){
              TawOrderDetail tawOrderDetail=new TawOrderDetail();
              populate(tawOrderDetail,rs);
              list.add(tawOrderDetail);
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

  public List getTawOrderDetail(String SQL,int offset,int length) throws
        SQLException{
      ArrayList list=new ArrayList();
      com.boco.eoms.db.util.BocoConnection conn=null;
      PreparedStatement pstmt=null;
      ResultSet rs=null;
      String sql = "";
      try{
          conn=ds.getConnection();
          sql =
                "select * from vaw_sp_order_detail " + SQL;
          pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
          rs=pstmt.executeQuery();
          if(offset>0){
              rs.absolute(offset);
          }
          int i=0;
          while((i++<length)&&rs.next()){
              TawOrderDetail tawOrderDetail=new TawOrderDetail();
              populate(tawOrderDetail,rs);
              list.add(tawOrderDetail);
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

}
