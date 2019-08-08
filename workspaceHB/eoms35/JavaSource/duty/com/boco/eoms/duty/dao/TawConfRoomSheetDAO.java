package com.boco.eoms.duty.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.util.Properties;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;

import com.boco.eoms.duty.model.TawConfRoomSheet;
import com.boco.eoms.duty.model.TawDutySheet;

/**
 * <p>Title: TawAutoSheetDAO</p>
 * <p>Description:自动表单 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Boco</p>
 *
 * @version 1.0
 * @author: Lihong Qi
 */

public class TawConfRoomSheetDAO extends DAO {
    //public Connection conn = null;
    public TawConfRoomSheetDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    public void insert(TawConfRoomSheet tawConfRoomSheet) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        try {
            PreparedStatement pstmt = null;
            String sql = "";

            conn = ds.getConnection();
            sql = "INSERT INTO taw_dutysheet_dict(sheet_id,room_id,is_not_fault,by_attr,to_attr)" +
                    " Values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawConfRoomSheet.getSheetId());
            pstmt.setInt(2, tawConfRoomSheet.getRoomId());
            pstmt.setString(3, tawConfRoomSheet.getIsNotFault());
            pstmt.setInt(4, tawConfRoomSheet.getByAttr());
            pstmt.setInt(5, tawConfRoomSheet.getToAttr());
            // pstmt.setInt(6,tawConfRoomSheet.getDeptId());

            pstmt.executeUpdate();
            conn.commit();
            pstmt = null;
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) conn = null;
        }
    }


    public List getConfigList() {
        ArrayList retList = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        try {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";

            conn = ds.getConnection();
            sql = "(SELECT a.*,b.sh_cname as sheet_name,c.attr_name as by_attr_name,d.attr_name as to_attr_name,e.room_name " +
                    "FROM taw_dutysheet_dict a,taw_sheetname b,taw_Sheetattr c,taw_sheetattr d,taw_apparatusroom e " +
                    "WHERE a.sheet_id = b.sheet_id AND a.by_attr = c.attr_id AND a.to_attr=d.attr_id AND e.id=a.room_id )";
            sql = sql + " Union All ";

            sql = sql + "(SELECT a.*,b.sh_cname as sheet_name,'' as by_attr_name,'' as to_attr_name,e.room_name " +
                    "FROM taw_dutysheet_dict a,taw_sheetname b,taw_apparatusroom e " +
                    " WHERE a.sheet_id = b.sheet_id AND (a.by_attr =0) AND (a.to_attr =0) AND e.id=a.room_id  )";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TawConfRoomSheet tawConfRoomSheet = new TawConfRoomSheet();
                populate(tawConfRoomSheet, rs);
                retList.add(tawConfRoomSheet);
                tawConfRoomSheet = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retList;
    }

    //根据机房id得到本机房可以填写的值班附加表
    public List getSheetList(int roomId) {
        List retList = new ArrayList();

        try {
            com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";

            conn = ds.getConnection();
            sql = "select a.sheet_id,b.sh_cname as sheet_name " +
                    "from taw_dutysheet_dict a,taw_sheetname b where a.sheet_id = b.sheet_id and a.room_id =?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TawDutySheet tawDutySheet = new TawDutySheet();
                populate(tawDutySheet, rs);
                retList.add(tawDutySheet);
                tawDutySheet = null;
            }
            close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retList;
    }


    public TawConfRoomSheet retrieve(int id) {
        TawConfRoomSheet tawConfRoomSheet = new TawConfRoomSheet();
        com.boco.eoms.db.util.BocoConnection conn = null;
        try {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";

            conn = ds.getConnection();
        /*
        sql = "SELECT a.*,b.sh_cname as sheet_name,c.attr_name as by_attr_name,d.attr_name as to_attr_name,e.room_name,f.dept_name "+
            "FROM taw_dutysheet_dict a,taw_sheetname b,taw_Sheetattr c,taw_sheetattr d,taw_apparatusroom e,taw_dept f "+
            "WHERE a.id=? AND a.sheet_id = b.sheet_id AND a.by_attr = c.attr_id AND a.to_attr=d.attr_id AND e.id=a.room_id AND f.dept_id =a.dept_id";
        */
            sql = "(SELECT a.*,b.sh_cname as sheet_name,c.attr_name as by_attr_name,d.attr_name as to_attr_name,e.room_name " +
                    "FROM taw_dutysheet_dict a,taw_sheetname b,taw_Sheetattr c,taw_sheetattr d,taw_apparatusroom e " +
                    "WHERE a.sheet_id = b.sheet_id AND a.by_attr = c.attr_id AND a.to_attr=d.attr_id AND e.id=a.room_id and a.id=?)";
            sql = sql + " Union All ";

            sql = sql + "(SELECT a.*,b.sh_cname as sheet_name,'' as by_attr_name,'' as to_attr_name,e.room_name " +
                    "FROM taw_dutysheet_dict a,taw_sheetname b,taw_apparatusroom e " +
                    " WHERE a.sheet_id = b.sheet_id AND (a.by_attr =0) AND (a.to_attr =0) AND e.id=a.room_id  and a.id=?)";


            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                populate(tawConfRoomSheet, rs);
            }

        } catch (Exception e) {
            rollback(conn);
            e.printStackTrace();
        }
        return tawConfRoomSheet;
    }


    public boolean verifyExist(int roomId, int sheetId) {
        boolean retBoolean = true;
        com.boco.eoms.db.util.BocoConnection conn = null;
        try {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";

            conn = ds.getConnection();
            sql = "SELECT sheet_id FROM taw_dutysheet_dict WHERE sheet_id=? AND room_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sheetId);
            pstmt.setInt(2, roomId);

            rs = pstmt.executeQuery();
            if (!rs.next()) {
                retBoolean = false;
            }

            close(conn);
            pstmt = null;
            rs = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retBoolean;
    }

    public void update(TawConfRoomSheet tawConfRoomSheet) {
        com.boco.eoms.db.util.BocoConnection conn = null;
        try {
            PreparedStatement pstmt = null;
            String sql = "";

            conn = ds.getConnection();
            sql = "UPDATE taw_dutysheet_dict SET " +
                    "room_id=?,sheet_id=?,is_not_fault=?,by_attr=?,to_attr=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawConfRoomSheet.getRoomId());
            pstmt.setInt(2, tawConfRoomSheet.getSheetId());
            pstmt.setString(3, tawConfRoomSheet.getIsNotFault());
            pstmt.setInt(4, tawConfRoomSheet.getByAttr());
            pstmt.setInt(5, tawConfRoomSheet.getToAttr());
            pstmt.setInt(6, tawConfRoomSheet.getId());

            pstmt.executeUpdate();
            conn.commit();

            pstmt = null;
        } catch (Exception e) {
            rollback(conn);
            e.printStackTrace();
        } finally {
            if (conn != null) close(conn);
        }
    }

    public void delete(int paraId) {
        com.boco.eoms.db.util.BocoConnection conn = null;
        try {
            PreparedStatement pstmt = null;
            String sql = "";

            conn = ds.getConnection();
            sql = "DELETE FROM taw_dutysheet_dict WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, paraId);
            pstmt.executeUpdate();
            conn.commit();

            pstmt = null;
        } catch (Exception e) {
            rollback(conn);
            e.printStackTrace();
        } finally {
            if (conn != null) conn = null;
        }
    }

}
