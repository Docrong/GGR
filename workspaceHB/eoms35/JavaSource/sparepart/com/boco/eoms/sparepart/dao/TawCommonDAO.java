package com.boco.eoms.sparepart.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.model.PartRemind;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawCommonDAO extends DAO{

    public TawCommonDAO(ConnectionPool ds){
        super(ds);
    }

    /**
     * @see 校验属性是否重名
     * @param sql
     * @return
     * @throws SQLException
     */
    public boolean checkName(String tableName,String colName,String colValue){
        boolean flag=true;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select * from "+tableName+" where "+colName+"=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,colValue);
            rs=pstmt.executeQuery();
            if(rs.next()){
                flag=false;
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
        return flag;
    }

    /**
     * @see 根据仓库id返回库存设置和库存信息
     * @param storageId 仓库id
     * @return  视图PartRemind信息，库存设置和库存数据，为库存比较提供数据
     */
    public List getPartRemind(String storageId){
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select storageid,object,upperlimit,lowerlimit,"+
                  "nowdata from part_remind where storageid=? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,storageId);

            rs=pstmt.executeQuery();
            while(rs.next()){
                PartRemind partRemind=new PartRemind();
                populate(partRemind,rs);
                list.add(partRemind);
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

    public boolean checkName(String tableName,String colName,String colValue,String colNumber,int numberValue){
        boolean flag=true;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select * from "+tableName+" where "+colName+"=? and "+colNumber+"=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,colValue);
            pstmt.setInt(2,numberValue);
            rs=pstmt.executeQuery();
            if(rs.next()){
                flag=false;
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
        return flag;
    }

}
