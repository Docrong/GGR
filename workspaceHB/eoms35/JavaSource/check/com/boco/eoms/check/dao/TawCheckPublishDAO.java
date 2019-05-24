package com.boco.eoms.check.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.boco.eoms.check.model.TawCheckModelAttatch;
import com.boco.eoms.check.model.TawCheckSumDefine;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.db.util.ConnectionPool;

public class TawCheckPublishDAO extends DAO {
	public TawCheckPublishDAO(ConnectionPool ds){
        super(ds);
    }
	public void save(String year,String month,String zhuanye,String names[],String values[]){
		com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="delete from taw_check_publish where publish_year=? and publish_month=? and publish_type=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, year);
            pstmt.setString(2, month);
            pstmt.setString(3, zhuanye);
            pstmt.executeUpdate();
            //close(rs);
            close(pstmt);
            for(int i=0;i<names.length;i++){
           	sql="insert into taw_check_publish (publish_year,publish_month,publish_type,publish_value,area_name) values(?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, year);
            pstmt.setString(2, month);
            pstmt.setString(3, zhuanye);
            pstmt.setString(4, values[i]);
            pstmt.setString(5, names[i]);
            pstmt.executeUpdate();
            }
            conn.commit();
           // close(pstmt);
        }catch(SQLException ex){
        	rollback(conn);
        	ex.printStackTrace();
        }
        finally{
        	close(pstmt);
        	close(conn);
        }
	}
	public String[] load(String year,String month,String zhuanye,String names[]){
		com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        String values[]=new String[names.length];
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select * from taw_check_publish where publish_year=? and publish_month=? and publish_type=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, year);
            pstmt.setString(2, month);
            pstmt.setString(3, zhuanye);
            rs=pstmt.executeQuery();
            while(rs.next()){
            	String name=rs.getString("area_name");
            	String value=rs.getString("publish_value");
            	for(int i=0;i<names.length;i++){
            		if(name.equals(names[i])){
            			values[i]=value;
            		}
            	}
            }

        }catch(SQLException ex){
        	//rollback(conn); liquan modify
        	ex.printStackTrace();
        }
        finally{close(rs);
        	close(pstmt);
        	close(conn);
        }
        return values;
	}
}
