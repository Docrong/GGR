/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：出入机房登记的DAO。</p>
 */


package com.boco.eoms.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import java.text.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.StaticMethod;

public class TawRmGuestformDAO extends DAO {

    public TawRmGuestformDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param tawRmGuestform
     * @throws SQLException
     * @see 插入出入机房登记
     */
    public void insert(TawRmGuestform tawRmGuestform) throws SQLException {
        String sql;
        sql = "INSERT INTO taw_rm_guestform (room_id, inputdate, guestname, company, sender, department, dutyman, starttime, endtime, purpose, concerned, affection, flag, notes) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1, tawRmGuestform.getId());
            pstmt.setInt(1, tawRmGuestform.getRoomId());
            //pstmt.setTimestamp(3, tawRmGuestform.getInputdate());
            pstmt.setString(2, tawRmGuestform.getInputdate());
            pstmt.setString(3, tawRmGuestform.getGuestname());
            pstmt.setString(4, tawRmGuestform.getCompany());
            pstmt.setString(5, tawRmGuestform.getSender());
            pstmt.setString(6, tawRmGuestform.getDepartment());
            pstmt.setString(7, tawRmGuestform.getDutyman());
            //pstmt.setTimestamp(9, tawRmGuestform.getStarttime());
            //pstmt.setTimestamp(10, tawRmGuestform.getEndtime());
            pstmt.setString(8, tawRmGuestform.getStarttime());
            pstmt.setString(9, tawRmGuestform.getEndtime());
            pstmt.setString(10, tawRmGuestform.getPurpose());
            pstmt.setString(11, tawRmGuestform.getConcerned());
            pstmt.setString(12, tawRmGuestform.getAffection());
            pstmt.setInt(13, tawRmGuestform.getFlag());
            pstmt.setString(14, tawRmGuestform.getNotes());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            rollback(conn);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
        }
    }

    /**
     * @param tawRmGuestform
     * @throws SQLException
     * @see 更新出入机房登记
     */
    public void update(TawRmGuestform tawRmGuestform) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            //String sql = "UPDATE taw_rm_guestform SET room_id=?, inputdate=?, guestname=?, company=?, sender=?, department=?, dutyman=?, starttime=?, endtime=?, purpose=?, concerned=?, affection=?, flag=?, notes=? WHERE id=?";
            String sql = "UPDATE taw_rm_guestform SET endtime=?, flag=?, notes=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
      /*
      pstmt.setInt(1, tawRmGuestform.getRoomId());
      //pstmt.setTimestamp(2, tawRmGuestform.getInputdate());
      pstmt.setString(2, tawRmGuestform.getInputdate());
      pstmt.setString(3, tawRmGuestform.getGuestname());
      pstmt.setString(4, tawRmGuestform.getCompany());
      pstmt.setString(5, tawRmGuestform.getSender());
      pstmt.setString(6, tawRmGuestform.getDepartment());
      pstmt.setString(7, tawRmGuestform.getDutyman());
      //pstmt.setTimestamp(8, tawRmGuestform.getStarttime());
      //pstmt.setTimestamp(9, tawRmGuestform.getEndtime());
      pstmt.setString(8, tawRmGuestform.getStarttime());

      pstmt.setString(9, tawRmGuestform.getEndtime());

      pstmt.setString(10, tawRmGuestform.getPurpose());
      pstmt.setString(11, tawRmGuestform.getConcerned());
      pstmt.setString(12, tawRmGuestform.getAffection());
      pstmt.setInt(13, tawRmGuestform.getFlag());

      pstmt.setString(14, tawRmGuestform.getNotes());
      pstmt.setInt(15, tawRmGuestform.getId());
      */
            pstmt.setString(1, tawRmGuestform.getEndtime());
            System.out.println("flag=" + tawRmGuestform.getFlag());
            System.out.println("id=" + tawRmGuestform.getId());

            pstmt.setInt(2, 1);
            pstmt.setString(3, tawRmGuestform.getNotes());
            pstmt.setInt(4, tawRmGuestform.getId());
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    /**
     * @param id
     * @throws SQLException
     * @see 删除出入机房登记记录
     */
    public void delete(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM taw_rm_guestform WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     * @see 通过ID得到出入机房登记对象
     */
    public TawRmGuestform retrieve(int id) throws SQLException {
        TawRmGuestform tawRmGuestform = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM taw_rm_guestform WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmGuestform = new TawRmGuestform();
                populate(tawRmGuestform, rs);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tawRmGuestform.setInputdate(dateFormat.format(rs.getDate("inputdate")));
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return tawRmGuestform;
    }

    /**
     * @return
     * @throws SQLException
     * @see 得到出入机房登记对象列表
     */
    public List list() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT room_id, inputdate, guestname, company, sender, department, dutyman, starttime, endtime, purpose, concerned, affection, flag, notes, id FROM taw_rm_guestform";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TawRmGuestform tawRmGuestform = new TawRmGuestform();
                populate(tawRmGuestform, rs);
                list.add(tawRmGuestform);
                tawRmGuestform = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return list;
    }

    /**
     * @param offset
     * @param limit
     * @param strCondition
     * @return
     * @throws SQLException
     * @see 得到出入机房登记对象列表
     */
    public List search(int offset, int limit, String strCondition) throws SQLException {
        ArrayList search = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql;
            sql = "SELECT * FROM taw_rm_guestform " + strCondition;
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (offset > 0) {
                rs.next();
                offset--;
            }
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                TawRmGuestform tawRmGuestform = new TawRmGuestform();
                populate(tawRmGuestform, rs);
                String strInpudate = tawRmGuestform.getInputdate().replaceAll("\\.0", "");
                strInpudate = strInpudate.replaceAll("00.00.00", "");
                tawRmGuestform.setInputdate(strInpudate);
                search.add(tawRmGuestform);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return search;
    }

    /**
     * @param offset
     * @param limit
     * @return
     * @throws SQLException
     * @see 得到出入机房登记对象列表
     */
    public List list(int offset, int limit) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT room_id, inputdate, guestname, company, sender, department, dutyman, starttime, endtime, purpose, concerned, affection, flag, notes, id FROM taw_rm_guestform";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                TawRmGuestform tawRmGuestform = new TawRmGuestform();
                populate(tawRmGuestform, rs);
                list.add(tawRmGuestform);
                tawRmGuestform = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return list;
    }

}