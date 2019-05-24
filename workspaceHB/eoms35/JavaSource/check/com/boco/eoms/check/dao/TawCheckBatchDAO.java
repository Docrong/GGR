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

public class TawCheckBatchDAO extends DAO {
	public TawCheckBatchDAO(ConnectionPool ds){
        super(ds);
    }
	public void save(String batchYear,String batchMonth,String pathFileName,String zhuanye){
		com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int cnt=0;
        try{
            conn=ds.getConnection();
            String sql="select count(*) from taw_check_batch where batch_year=? and batch_month=? and batch_type=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, batchYear);
            pstmt.setString(2, batchMonth);
            pstmt.setString(3, zhuanye);
            rs=pstmt.executeQuery();
            while(rs.next()){
                cnt=rs.getInt(1);
            }
            close(rs);
            close(pstmt);
            if(cnt==0){
            	sql="insert into taw_check_batch (path_name,batch_year,batch_month,batch_type) values(?,?,?,?)";
            }else{
            	sql="update taw_check_batch set path_name=? where batch_year=? and batch_month=? and batch_type=?";
            }
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, pathFileName);
            pstmt.setString(2, batchYear);
            pstmt.setString(3, batchMonth);
            pstmt.setString(4, zhuanye);
            pstmt.executeUpdate();
            conn.commit();
            close(pstmt);
        }catch(SQLException ex){
        	rollback(conn);
        	ex.printStackTrace();
        }
        finally{
        	close(pstmt);
        	close(conn);
        }
	}
	public String getPathName(String batchYear,String batchMonth,String zhuanye){
		String filePathName="";
		com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select path_name from taw_check_batch where batch_year=? and batch_month=? and batch_type=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, batchYear);
            pstmt.setString(2, batchMonth);
            pstmt.setString(3, zhuanye);
            rs=pstmt.executeQuery();
            while(rs.next()){
            	filePathName=rs.getString(1);
            }

           // close(pstmt);
        }catch(SQLException ex){
        	//rollback(conn); liquan modify
        	ex.printStackTrace();
        }
        finally{
               close(rs);
        	close(pstmt);
        	close(conn);
        }
		return filePathName;
	}
	public List getSumDefine(String typeId){
		com.boco.eoms.db.util.BocoConnection conn=null;
		List list =new ArrayList(0);
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select * from taw_check_sum_define where typeid=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(typeId));
            rs=pstmt.executeQuery();
            while(rs.next()){
            	TawCheckSumDefine define=new TawCheckSumDefine();
            	define.setColName(rs.getString("col_name"));
            	define.setColName1(rs.getString("col_name1"));
            	define.setTableName(rs.getString("table_name"));
            	define.setModelflag(rs.getString("modelflag"));
            	define.setScale(rs.getDouble("scale"));
            	list.add(define);
            }

           // close(pstmt);
        }catch(SQLException ex){
        	//rollback(conn); liquan modify
        	ex.printStackTrace();
        }
        finally{
                close(rs);
        	close(pstmt);
        	close(conn);
        }
        return list;
	}
	public void save(Map map){
		String batchYear=StaticMethod.nullObject2String(map.get("iYear"));
		String batchMonth=StaticMethod.nullObject2String(map.get("iMonth"));
		String modelType=StaticMethod.nullObject2String(map.get("modelType"));
		String accessories=StaticMethod.nullObject2String(map.get("accessories"));
		com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int cnt=0;
        try{
            conn=ds.getConnection();
            String sql="select count(*) from taw_check_model_attatch where model_id=? and model_year=? and model_month=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, modelType);
            pstmt.setString(2, batchYear);
            pstmt.setString(3, batchMonth);
            rs=pstmt.executeQuery();
            while(rs.next()){
                cnt=rs.getInt(1);
            }
            close(rs);
            close(pstmt);
            if(cnt==0){
            	sql="insert into taw_check_model_attatch (model_attatch,model_id,model_year,model_month) values(?,?,?,?)";
            }else{
            	sql="update taw_check_model_attatch set model_attatch=? where model_id=? and model_year=? and model_month=?";
            }
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, accessories);
            pstmt.setString(2, modelType);
            pstmt.setString(3, batchYear);
            pstmt.setString(4, batchMonth);
            pstmt.executeUpdate();
            conn.commit();
            close(pstmt);
        }catch(SQLException ex){
        	rollback(conn);
        	ex.printStackTrace();
        }
        finally{
        	close(pstmt);
        	close(conn);
        }
	}
	public TawCheckModelAttatch getFile(Map map){
		String batchYear=StaticMethod.nullObject2String(map.get("iYear"));
		String batchMonth=StaticMethod.nullObject2String(map.get("iMonth"));
		String modelType=StaticMethod.nullObject2String(map.get("modelType"));
		com.boco.eoms.db.util.BocoConnection conn=null;
		TawCheckModelAttatch model=new TawCheckModelAttatch();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        int cnt=0;
        try{
            conn=ds.getConnection();
            String sql="select * from taw_check_model_attatch where model_id=? and model_year=? and model_month=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, modelType);
            pstmt.setString(2, batchYear);
            pstmt.setString(3, batchMonth);
            rs=pstmt.executeQuery();
            while(rs.next()){
                model.setModelId(rs.getString("model_id"));
                model.setModelYear(rs.getString("model_year"));
                model.setModelMonth(rs.getString("model_month"));
                model.setAccessories(rs.getString("model_attatch"));
            }

         //   close(pstmt);
        }catch(SQLException ex){
        	//rollback(conn); liquan modify
        	ex.printStackTrace();
        }
        finally{
                close(rs);
        	close(pstmt);
        	close(conn);
        }
        return model;
	}

}
