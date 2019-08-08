package com.boco.eoms.sparepart.dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.dao.DAO;

import java.util.*;

import com.boco.eoms.sparepart.model.TawPart;

public class TawStatDAO extends DAO {
    public TawStatDAO() {
    }

    //统计仓库中关于state分类的数量
    public int getStatNum(int _state, String _storageid) {
        int StatNum = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "select count(*) from taw_part where storageid = " + "'" + _storageid +
                    "'" + "and borrow_state <> 2 and stateid = " + _state;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StatNum = rs.getInt(1);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return StatNum;
    }

    //统计仓库中关于state分类的数量
    public int getStatNum(String statelist, String _storageid, int partType) {
        int StatNum = 0;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "select count(*) from taw_part where storageid = " + "'" + _storageid +
                    "'" + "and parttype = " + partType + " and stateid in " + statelist;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StatNum = rs.getInt(1);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return StatNum;
    }

    public List getStorageIdList() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT distinct id FROM taw_sp_storage ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getObject(1));
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return list;
    }

    public String getStorage(int _storageid) throws SQLException {
        String storagename = "";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT storagename FROM taw_sp_storage where id= " + _storageid;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                storagename = (String) rs.getObject(1);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return storagename;
    }

    public List getSparepartList(int _state, int _storageid) {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM taw_part " +
                    "where borrow_state<>2 and stateid=" + _state +
                    " and storageid = " + _storageid;
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TawPart tawPart = new TawPart();
                this.populate(tawPart, rs);
                list.add(tawPart);
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
     * @param _state
     * @param _storageid
     * @param partType
     * @return
     */
    public List getSparepartList(String _state, int _storageid, int partType) {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM taw_part " +
                    "where borrow_state<>2 and stateid in " + _state +
                    " and storageid = " + _storageid + "" +
                    " and parttype = " + partType;
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TawPart tawPart = new TawPart();
                this.populate(tawPart, rs);
                list.add(tawPart);
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
