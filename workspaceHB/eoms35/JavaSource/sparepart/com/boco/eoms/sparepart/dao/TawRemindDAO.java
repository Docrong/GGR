package com.boco.eoms.sparepart.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.model.TawRemind;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawRemindDAO
      extends DAO{

    public TawRemindDAO(){
    }

    public TawRemindDAO(ConnectionPool ds){
        super(ds);
    }

    public void insertRemind(TawRemind tawRemind){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql=
              "INSERT INTO taw_sp_remind(storageid,object,type,upperlimit,lowerlimit,"+
              "limitdate,sendmsg) VALUES (?,?,?,?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,tawRemind.getStorageid());
            pstmt.setString(2,tawRemind.getObject());
            pstmt.setInt(3,tawRemind.getType());
            pstmt.setString(4,tawRemind.getUpperlimit());
            pstmt.setString(5,tawRemind.getLowerlimit());
            pstmt.setString(6,tawRemind.getLimitdate());
            pstmt.setString(7,tawRemind.getSendmsg());

            pstmt.executeUpdate();
            conn.commit();
        }
        catch(SQLException ex){
            rollback(conn);
            ex.printStackTrace();
        }
        finally{
            close(pstmt);
            close(conn);
        }
    }

    public List getTawRemind(String SQL){
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select id,storageid,object,type,upperlimit,lowerlimit,"+
                  "limitdate,sendmsg,note from taw_sp_remind "+SQL;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                TawRemind tawOrder=new TawRemind();
                populate(tawOrder,rs);
                list.add(tawOrder);
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