package com.boco.eoms.sparepart.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.boco.eoms.sparepart.model.TawStorage;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.sparepart.controller.TawStorageForm;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawStorageDAO extends DAO{

    public TawStorageDAO(ConnectionPool ds){
        super(ds);
    }

    /**
     * @ ���زֿ��id+1,Ϊ�½��ֿ��ṩid
     * @return int id
     * @throws SQLException
     */
    public int getStorageId() throws SQLException{
        int id=0;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select max(id)+1 from taw_sp_storage";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                id=rs.getInt(1);
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
        if(id==0){
            id=1;
        }
        return id;
    }

    public int getStorageId(String storagename) throws SQLException{
      int id=0;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql="select id from taw_sp_storage where storagename ='"+storagename+"'";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                id=rs.getInt(1);
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
        return id;
    }

    /**
  * @ ���زֿ��id
  * @return int id
  * @throws SQLException
  */
 public int getStorageIdByName(String storagename) throws SQLException{
   int id=0;
     com.boco.eoms.db.util.BocoConnection conn=null;
     PreparedStatement pstmt=null;
     ResultSet rs=null;
     try{
         conn=ds.getConnection();
         String sql="select id from taw_sp_storage where storagename ='"+storagename+"'";
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         while(rs.next()){
             id=rs.getInt(1);
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
     return id;
 }


    public List getList() throws SQLException{
        List list=getRoleList("");
        return list;
    }

    public List getRoleList(String sumId) throws SQLException{
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT  taw_sp_storage.id ,taw_sp_storage.note,taw_sp_storage.storagename,taw_dept.deptid,taw_dept.deptname FROM taw_sp_storage taw_sp_storage,taw_system_dept taw_dept where taw_sp_storage.dept_id = taw_dept.deptid"+sumId;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                TawStorage storage=new TawStorage();
                populate(storage,rs);
                list.add(storage);
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

    public List getStorageListByDeptId(String deptId) throws SQLException{
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT  taw_sp_storage.id ,taw_sp_storage.note,taw_sp_storage.storagename,taw_dept.deptid,taw_dept.deptname FROM taw_sp_storage taw_sp_storage,taw_system_dept taw_dept where taw_sp_storage.dept_id = taw_dept.deptid"+
                  " and taw_sp_storage.dept_id="+deptId;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                TawStorage storage=new TawStorage();
                populate(storage,rs);
                list.add(storage);
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

    public TawStorage getList(String id){
        TawStorage storage=new TawStorage();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT  taw_sp_storage.id ,taw_sp_storage.note,taw_sp_storage.storagename,taw_dept.deptid,taw_dept.deptname FROM taw_sp_storage taw_sp_storage,taw_system_dept taw_dept where taw_sp_storage.dept_id = taw_dept.deptid and taw_sp_storage.id = ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id);

            rs=pstmt.executeQuery();
            while(rs.next()){
                populate(storage,rs);
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
        return storage;
    }

    /**
     **�½��ֿ⣬����ݿ��������һ���¼
     * @param name
     * @param note
     * @return int id ���ڵ������id
     * @throws SQLException
     */
    public int insert(String name,String note,String deptId) throws SQLException{
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;
        int id=0;
        try{
            id=this.getStorageId();
        }
        catch(SQLException ex){
        }
        String sql=
              "INSERT INTO taw_sp_storage (storagename,note,id,dept_id) VALUES (?,?,?,?)";

        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,note);
            pstmt.setInt(3,id);
            pstmt.setString(4,deptId);
            pstmt.executeUpdate();
            conn.commit();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
            rollback(conn);
        }
        finally{
            close(pstmt);
            close(conn);
        }
        return id;
    }

    /**
     ** �ֿ���޸�
     * @param name
     * @param note
     * @param id
     * @throws SQLException
     */
    public void updateStroage(String name,String note,int id,String deptId) throws
          SQLException{
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        String sql=
              "update taw_sp_storage set storagename=?,note=?,dept_id=? where id = ?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,note);
            pstmt.setString(3,deptId);
            pstmt.setInt(4,id);
            pstmt.executeUpdate();
            conn.commit();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        finally{
            rollback(conn);
            close(pstmt);
            close(conn);
        }
    }

    /**
     ** �ֿ��ɾ��
     * @param id
     * @throws SQLException
     */
    public void deleteStroage(int id) throws SQLException{
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        String sql="delete from taw_sp_storage where id = ?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
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
     ** ������Ĳ��룬�����id ͬ�ֿ��id,�Ǻ��½��ֿ�ͬ����
     * @param id
     * @param name
     */
    public void insertThirdDom(int id,String name){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql=
              "INSERT INTO taw_third_dom (domain_id,domain_type,domain_name)"+
              " VALUES (?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            pstmt.setInt(2,17);
            pstmt.setString(3,name);

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

    public void updateThirdDom(int id,String name){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="update taw_third_dom set domain_name=? where domain_id=?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setInt(2,id);
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
     * @param stroageId
     * @throws SQLException
     */
    public void deleteThirdDom(int stroageId) throws SQLException{
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        String sql="delete from taw_third_dom where domain_id = ?";
        try{
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,stroageId);
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


    public TawStorage getTawStorage(String name){
        TawStorage storage=new TawStorage();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT  id ,note,storagename,dept_id FROM taw_sp_storage where storagename=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);

            rs=pstmt.executeQuery();
            while(rs.next()){
                populate(storage,rs);
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
        return storage;
    }
}
