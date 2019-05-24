package com.boco.eoms.sparepart.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.sparepart.model.TawBorrow;


public class TawBorrowDAO extends DAO {
  public TawBorrowDAO(ConnectionPool ds) {
    super(ds);
  }

  /**
 * @see insert new sparepart
 * @param form
 */
public void insertPart(String managecode,String supplierid,
                       String objectname,String operator,String note,
                       String storageid,String serialno,String productcode,
                       String[] id,String position,String contract){
    com.boco.eoms.db.util.BocoConnection conn=null;
    conn=ds.getConnection();
    PreparedStatement pstmt=null;

    String sql=
          "INSERT INTO taw_sp_borrow(nettype,managecode,objecttype,necode,"+
          "supplier,objectname,operator,note,storageid,intime,serialno,"+
          " productcode,position,storage,cname,necname,objecttypename,suppliername,contract,subDept)"+
          " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    try{
        pstmt=conn.prepareStatement(sql);

        pstmt.setInt(1,Integer.parseInt(id[0])); //objecttype==objectname
        pstmt.setString(2,managecode);
        pstmt.setInt(3,Integer.parseInt(id[3]));
        pstmt.setInt(4,Integer.parseInt(id[2]));
        pstmt.setInt(20,Integer.parseInt(id[1]));
        pstmt.setInt(5,Integer.parseInt(supplierid));
        pstmt.setString(6,objectname);
        pstmt.setString(7,operator);
        pstmt.setString(8,note);
        pstmt.setInt(9,Integer.parseInt(storageid));
        pstmt.setString(10,
                        StaticMethod.getCurrentDateTime().substring(0,10));
        pstmt.setString(11,serialno);
        pstmt.setString(12,productcode);
        pstmt.setString(13,position);
        pstmt.setString(14,this.getStorage(Integer.parseInt(storageid)));
        pstmt.setString(15,this.getCname(Integer.parseInt(id[0])));
        pstmt.setString(16,this.getCname(Integer.parseInt(id[1])));
        pstmt.setString(17,this.getCname(Integer.parseInt(id[2])));
        pstmt.setString(18,this.getSuppliername(supplierid));
        pstmt.setString(19,contract);
        pstmt.executeUpdate();
        conn.commit();
    }
    catch(SQLException sqle){
        System.out.println(sqle);
        sqle.printStackTrace();
        rollback(conn);
    }
    finally{
        close(pstmt);
        close(conn);
    }
}

  public String getStorage(int _storageid){
    String storage="";
    com.boco.eoms.db.util.BocoConnection conn=null;
    conn=ds.getConnection();
    PreparedStatement pstmt=null;
    String sql="select distinct storagename from taw_sp_storage where id ="+_storageid;
    try {
      pstmt = conn.prepareStatement(sql);
      ResultSet rs = null;
      rs = pstmt.executeQuery();
      while (rs.next()) {
        storage = rs.getString(1);
      }
    }
    catch (SQLException ex) {
      System.out.println(ex);
        ex.printStackTrace();
        rollback(conn);
    }
    finally{
        close(pstmt);
        close(conn);
    }
    return storage;
  }

  public String getCname(int _nettype){
    String storage="";
    com.boco.eoms.db.util.BocoConnection conn=null;
    conn=ds.getConnection();
    PreparedStatement pstmt=null;
    String sql="select distinct cname from taw_sp_tree where id ="+_nettype;
    try {
      pstmt = conn.prepareStatement(sql);
      ResultSet rs = null;
      rs = pstmt.executeQuery();
      while (rs.next()) {
        storage = rs.getString(1);
      }
    }
    catch (SQLException ex) {
      System.out.println(ex);
        ex.printStackTrace();
        rollback(conn);
    }
    finally{
        close(pstmt);
        close(conn);
    }
    return storage;
  }

  public String getSuppliername(String _supplier){
   String storage="";
   com.boco.eoms.db.util.BocoConnection conn=null;
   conn=ds.getConnection();
   PreparedStatement pstmt=null;
   String sql="select distinct cname from taw_sp_classmsg where id ="+_supplier;
   try {
     pstmt = conn.prepareStatement(sql);
     ResultSet rs = null;
     rs = pstmt.executeQuery();
     while (rs.next()) {
       storage = rs.getString(1);
     }
   }
   catch (SQLException ex) {
     System.out.println(ex);
       ex.printStackTrace();
       rollback(conn);
   }
   finally{
       close(pstmt);
       close(conn);
   }
   return storage;
 }



  /**
 * @see
 * @param condition
 * @param offset
 * @param length
 * @return
 */
public List getPartList(String condition,int offset,int length){
    ArrayList list=new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    try{
        conn=ds.getConnection();
        String sql="SELECT  id ,objecttypename,cname,necname,suppliername,"+
              "storage,objectname,intime,operator,note,"+
              "serialno,note,managecode,productcode,position FROM taw_sp_borrow "+
              condition;
        pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
        rs=pstmt.executeQuery();
        if(offset>0){
            rs.absolute(offset);
        }
        int recCount=0;
        while((recCount++<length)&&rs.next()){
            TawBorrow tawBorrow=new TawBorrow();
            this.populate(tawBorrow,rs);
            list.add(tawBorrow);
        }
        close(rs);
        close(pstmt);
    }
    catch(SQLException e){
        close(rs);
        close(pstmt);
        rollback(conn);
        e.printStackTrace();
    }
    finally{
        close(conn);
    }
    return list;

}


}
