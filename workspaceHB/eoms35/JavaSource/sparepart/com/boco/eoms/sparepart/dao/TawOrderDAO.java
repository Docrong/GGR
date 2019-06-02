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
import com.boco.eoms.sparepart.model.TawOrder;
import com.boco.eoms.sparepart.model.TawOrderPart;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawOrderDAO extends DAO{

    public TawOrderDAO(ConnectionPool ds){
        super(ds);
    }

    public int getId(){
        int id=0;
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select max(id)+1 from taw_sp_order";
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

    public int insertOrder(TawOrder tawOrder){
        int id=0;

        id=this.getId();

        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;
    
        String sql=
              "INSERT INTO taw_sp_order(id,storageid,Operater,Proposer,prop_dept,"+
              "prop_tel,startdate,type,note,state,sheetid,reason,station,fixe,version,serialno,ename,objtype,overgay,advices) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            pstmt.setInt(2,tawOrder.getStorageid());
            pstmt.setString(3,StaticMethod.null2String(tawOrder.getOperater()));
            pstmt.setString(4,StaticMethod.null2String(tawOrder.getProposer()));
            pstmt.setString(5,StaticMethod.null2String(tawOrder.getPropDept()));
            pstmt.setString(6,StaticMethod.null2String(tawOrder.getPropTel()));
            pstmt.setTimestamp(7,StaticMethod.getTimestamp());
            pstmt.setInt(8,tawOrder.getType());
            pstmt.setString(9,StaticMethod.null2String(tawOrder.getNote()));
            pstmt.setInt(10,0);
            pstmt.setString(11,StaticMethod.null2String(tawOrder.getSheetid()));
            pstmt.setString(12, StaticMethod.null2String(tawOrder.getReason()));
            pstmt.setString(13,StaticMethod.null2String(tawOrder.getStation()));
            pstmt.setString(14,StaticMethod.null2String(tawOrder.getFixe()));
            pstmt.setString(15, StaticMethod.null2String(tawOrder.getVersion()));
            pstmt.setString(16,StaticMethod.null2String(tawOrder.getSerialno()));
            pstmt.setString(17,StaticMethod.null2String(tawOrder.getEname()));
            pstmt.setString(18,StaticMethod.null2String(tawOrder.getObjtype()));
            pstmt.setString(19,StaticMethod.null2String(tawOrder.getOvergay()));
            pstmt.setString(20,StaticMethod.null2String(tawOrder.getAdvices()));
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
        return id;
    }
    
    public List getTawOrder(String SQL) throws SQLException{
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn=ds.getConnection();
            String sql=
                  "select id,storageid,operater,proposer,prop_dept,prop_tel,"+
                  "startdate,overdate,state,type,note,reason,station,fixe,version,serialno,ename,objtype,sheetid,advices from taw_sp_order "+SQL;
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                TawOrder tawOrder=new TawOrder();
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

    public List getTawOrder(String SQL,int offset,int length) throws
          SQLException{
        ArrayList list=new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql = "";
        try{
            conn=ds.getConnection();
              /*
            	sql =
                  "select a.storagename,b.id,b.operater,b.proposer,b.prop_dept,b.prop_tel," +
                  "b.startdate,b.overdate,b.state,b.type,b.accessory,b.note,b.sheetid,d.begin_recei_time back_time,e.user_name back_man"+
                  " from taw_sp_order b,taw_sp_storage a,requisitionsheetnode c,requisitionsheetlink d,taw_rm_user e" +
                  " where a.id=b.storageid and b.sheetid = c.sheet_id and c.parent_link_id = d.id "+
                  " and c.node_id = 101001 and c.user_id = e.user_id and " + SQL;
               */
            sql = "select vaw.order_type,vaw.startdate,vaw.proposer,vaw.operater,vaw.prop_dept,vaw.prop_tel,link.end_send_time back_time,u.user_name back_man,vaw.sheetid " +
            	"from vaw_sp_order_detail vaw,requisitionsheetnode node,requisitionsheetlink link,taw_system_user u "+
            	"where vaw.sheetid = node.sheet_id "+
            	"and vaw.sheetid = link.sheet_id "+
            	"and node.user_id = u.userid "+
            	"and node.parent_link_id = link.id "+
            	"and node.node_id = 101001 and "+SQL;
            pstmt=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
            rs=pstmt.executeQuery();
            if(offset>0){
                rs.absolute(offset);
            }
            int i=0;
            while((i++<length)&&rs.next()){
                TawOrder tawOrder=new TawOrder();
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

    public void updateOrder(String SQL){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="update taw_sp_order "+SQL;
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

    public void deletePart(){
    com.boco.eoms.db.util.BocoConnection conn=null;
    conn=ds.getConnection();
    PreparedStatement pstmt=null;

    String sql="delete from taw_sp_order where state=0";
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
     * ��ֹ��������.����ɾ����ʱ��ɵĵ���
     * @param userId
     */
    public void deletePart_byuserId(String userId){
        com.boco.eoms.db.util.BocoConnection conn=null;
        conn=ds.getConnection();
        PreparedStatement pstmt=null;

        String sql="delete from taw_sp_order where state=0 and operater='"+userId+"'";
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
