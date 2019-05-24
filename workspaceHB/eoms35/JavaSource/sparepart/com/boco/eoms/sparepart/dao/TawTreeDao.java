package com.boco.eoms.sparepart.dao;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.db.util.ConnectionPool;
import java.util.List;
import java.util.*;
import java.sql.*;
import com.boco.eoms.sparepart.model.*;
import com.boco.eoms.sparepart.model.TawTree;
import com.boco.eoms.sparepart.util.*;

/**
 * <p>Title: 备品备件</p>
 * <p>Description: EOMS子系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.7
 */

public class TawTreeDao extends DAO{

    public TawTreeDao(){
    }

    public TawTreeDao(ConnectionPool ds){
        super(ds);
    }

    /**
     * @see 返回默认的专业类型信息
     * @param parentId  专业的默认类型信息的parentId＝0
     * @return  专业类型信息列表
     */
    public List getTree(int parentId,String type){
        List list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT  id ,note,cname,ename,layer,radix,parent_id,deleted "+
                  ",code FROM taw_sp_tree where parent_id=? and cname like ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,parentId);
            pstmt.setString(2,type);

            rs=pstmt.executeQuery();
            while(rs.next()){
                TawTree tawTree=new TawTree();
                populate(tawTree,rs);
                list.add(tawTree);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }
    /**
     * @see 返回默认的专业类型信息
     * @param parentId  专业的默认类型信息的parentId＝0
     * @return  专业类型信息列表
     */
    public List getTree(int parentId){
        List list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT  id ,note,cname,ename,layer,radix,parent_id,deleted "+
                  ",code FROM taw_sp_tree where parent_id=? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,parentId);

            rs=pstmt.executeQuery();
            while(rs.next()){
                TawTree tawTree=new TawTree();
                populate(tawTree,rs);
                list.add(tawTree);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }    
    /**
     * @see 根据第一级ID反回第四级cname的list
     * @param parentId  专业的默认类型信息的parentId＝0
     * @return  专业类型信息列表
     */
    public List getTreeForThree(int parentId){
        List list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
            	"select distinct cname from taw_sp_tree where parent_id in ("+
            			"select id from taw_sp_tree where parent_id in ("+
            			"select id from taw_sp_tree where taw_sp_tree.parent_id=?)" +
            			")";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,parentId);

            rs=pstmt.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1));
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return list;
    }     

    /**
     * @see 由id返回唯一的TawTree
     * @param id 主键
     * @return 唯一的TawTree。作为父信息
     */
    public TawTree getParentMsg(int id){
        TawTree tawTree=null;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT  id ,note,cname,ename,layer,radix,parent_id,deleted "+
                  ",code FROM taw_sp_tree where id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);

            rs=pstmt.executeQuery();
            if(rs.next()){
                tawTree=new TawTree();
                populate(tawTree,rs);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return tawTree;
    }

    /**
     * @see 由两级父信息返回唯一的TawTree
     * @param dept 一级父信息中文名称
     * @param root 二级父信息中文名称
     * @return 唯一的TawTree。作为父信息
     */
    public TawTree getParentMsg(String dept,String root){
        TawTree tawTree=null;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT a.id ,a.note,a.cname,a.ename,a.layer,a.radix,"+
                  "a.parent_id,a.deleted,a.code FROM taw_sp_tree a,taw_sp_tree b "+
                  "where a.cname=? and a.parent_id=b.id and b.cname=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,root);
            pstmt.setString(2,dept);

            rs=pstmt.executeQuery();
            if(rs.next()){
                tawTree=new TawTree();
                populate(tawTree,rs);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return tawTree;
    }

    /**
     * @see 在BO里面封装的数据insert到数据库
     * @param tawTree
     */
    public void insert(TawTree tawTree){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql=
              "INSERT INTO taw_sp_tree(parent_id,layer,radix,cname,note) "+
              " VALUES (?,?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,tawTree.getParentId());
            pstmt.setString(2,tawTree.getLayer());
            pstmt.setInt(3,tawTree.getRadix());
            pstmt.setString(4,tawTree.getCname());
            pstmt.setString(5,tawTree.getNote());

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
     * @see 得到同一级的最大radix
     * @param parentId  父标识
     * @return 最大radix＋1
     */
    public int getMaxRadix(int parentId){
        int maxRadix=0;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "SELECT  max(radix)+1 FROM taw_sp_tree where parent_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,parentId);

            rs=pstmt.executeQuery();
            while(rs.next()){
                maxRadix=rs.getInt(1);
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return maxRadix;
    }

    /**
     * @see 多级树的数据
     * @param layer 树型数据的层数
     * @return
     */
    public myTreeMap getStrTree(int layer){
        myTreeMap myTree=new myTreeMap();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql;
            if(layer==2){
                sql="select layer,cname,radix from taw_sp_tree"+
                      " where PARENT_ID=0 or parent_id in (select id "+
                      " from taw_sp_tree where PARENT_ID=0) order by layer ,radix";
            }else  if(layer==3){      //取第三级以上的所有菜单树
                sql="select layer,cname,radix from taw_sp_tree  where length(layer)<=5  order by layer ,radix ";
            } else{
                sql= "select layer,cname,radix from taw_sp_tree order by layer ,radix";
            }
            pstmt=conn.prepareStatement(sql);

            rs=pstmt.executeQuery();
            while(rs.next()){
                myTree.init(rs.getString(1),rs.getString(2));
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return myTree;
    }

    /**
     * @see 根据id修改cname and note
     * @param id
     * @param cname
     * @param note
     */
    public void update(int id,String cname,String note){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql=
              "update taw_sp_tree set cname=?,note=? where id=?";
        try{
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,cname);
            pstmt.setString(2,note);
            pstmt.setInt(3,id);

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

    public void drop(int id){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="delete from taw_sp_tree where id=?";
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
    public String getTreeThreeId(String department,String subDept,String necode,
                                String objectname){
        String id="";
        System.out.print(department+"_"+subDept+"_"+necode+"_"+objectname );
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select a.id,b.id,c.id,d.id from taw_sp_tree a,taw_sp_tree b, taw_sp_tree c,taw_sp_tree d where a.cname=? and a.id=b.parent_id and  b.cname=? and b.id=c.parent_id and c.cname=? and c.id=d.parent_id and d.cname=? ";
            System.out.println(sql);
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1,department);
            pstmt.setString(2,subDept);
            pstmt.setString(3,necode);
            pstmt.setString(4,objectname);

            rs=pstmt.executeQuery();
            while(rs.next()){
                String a=rs.getString(1);
                String b=rs.getString(2);
                String c=rs.getString(3);
                String d=rs.getString(4);
                id=a+"_"+b+"_"+c+"_"+d;
            }
            close(rs);
            close(pstmt);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            close(conn);
        }
        return id;
    }
}
