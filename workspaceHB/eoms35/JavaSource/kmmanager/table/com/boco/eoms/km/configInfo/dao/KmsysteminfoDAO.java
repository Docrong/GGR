/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：机房参数设置的DAO。</p>
 */

package com.boco.eoms.km.configInfo.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.km.configInfo.model.*;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.km.cptroom.bo.KmsystemCptroomBo;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;

public class KmsysteminfoDAO extends DAO {

    public KmsysteminfoDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param tawRmSysteminfo
     * @throws SQLException
     * @see 插入机房参数信息对象
     */
    public void insert(Kmsysteminfo tawRmSysteminfo) throws SQLException {
        // edit by wangheqi
        KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
        KmsystemCptroom tawApparatusroom = cptroomBO.getKmsystemCptroomById(
                new Integer(tawRmSysteminfo.getRoomId()), 0);

        // TawApparatusroomDAO tawApparatusroomDAO = new
        // TawApparatusroomDAO(ds);
        // TawApparatusroom tawApparatusroom =
        // tawApparatusroomDAO.retrieve(tawRmSysteminfo.getRoomId()) ;
        int deptId = 0;
        if (tawApparatusroom != null && !tawApparatusroom.getDeptid().equals("")) {
            deptId = Integer.parseInt(tawApparatusroom.getDeptid());
        }
        String sql;
        sql = "INSERT INTO km_systeminfo (room_id, dept_id, maxerrortime, maxdutynum, ex_request, ex_answer, duty_inform,exchange_flag,cycle_time,staggertime) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawRmSysteminfo.getRoomId());
            pstmt.setInt(2, deptId);
            pstmt.setInt(3, tawRmSysteminfo.getMaxerrortime());
            pstmt.setInt(4, tawRmSysteminfo.getMaxdutynum());
            pstmt.setInt(5, tawRmSysteminfo.getExRequest());
            pstmt.setInt(6, tawRmSysteminfo.getExAnswer());
            pstmt.setInt(7, tawRmSysteminfo.getDutyInform());
            pstmt.setInt(8, tawRmSysteminfo.getExchangeFlag());
            pstmt.setInt(9, tawRmSysteminfo.getCycleTime());
            pstmt.setInt(10, tawRmSysteminfo.getStaggertime());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            close(pstmt);
            rollback(conn);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
            sql = null;
        }
    }

    /**
     * @param tawRmSysteminfo
     * @throws SQLException
     * @see 更新机房参数
     */
    public void update(Kmsysteminfo tawRmSysteminfo) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        // edit by wangheqi
        KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
        KmsystemCptroom tawApparatusroom = cptroomBO.getKmsystemCptroomById(
                new Integer(tawRmSysteminfo.getRoomId()), 0);
        // TawApparatusroomDAO tawApparatusroomDAO = new
        // TawApparatusroomDAO(ds);
        // TawApparatusroom tawApparatusroom =
        // tawApparatusroomDAO.retrieve(tawRmSysteminfo.getRoomId()) ;
        int deptId = 0;
        if (tawApparatusroom != null) {
            deptId = Integer.parseInt(tawApparatusroom.getDeptid());
        }
        String sql = "UPDATE km_systeminfo SET dept_id=?, maxerrortime=?, maxdutynum=?, ex_request=?, ex_answer=?, duty_inform=?,exchange_flag=? ,cycle_time = ? ,staggertime = ? WHERE room_id=?";
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deptId);
            pstmt.setInt(2, tawRmSysteminfo.getMaxerrortime());
            pstmt.setInt(3, tawRmSysteminfo.getMaxdutynum());
            pstmt.setInt(4, tawRmSysteminfo.getExRequest());
            pstmt.setInt(5, tawRmSysteminfo.getExAnswer());
            pstmt.setInt(6, tawRmSysteminfo.getDutyInform());
            pstmt.setInt(7, tawRmSysteminfo.getExchangeFlag());
            pstmt.setInt(8, tawRmSysteminfo.getCycleTime());
            pstmt.setInt(9, tawRmSysteminfo.getStaggertime());
            pstmt.setInt(10, tawRmSysteminfo.getRoomId());
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
        }
    }

    /**
     * @param roomId
     * @throws SQLException
     * @see 根据机房ID，删除机房参数记录
     */
    public void delete(int roomId) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "DELETE FROM taw_rm_systeminfo WHERE room_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
        }
    }

    /**
     * @param roomId
     * @return
     * @throws SQLException
     * @see 根据机房ID，得到机房参数对象
     */
    public Kmsysteminfo retrieve(String roomId) throws SQLException {
        Kmsysteminfo tawRmSysteminfo = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT * FROM km_systeminfo WHERE room_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, roomId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmSysteminfo = new Kmsysteminfo();
                populate(tawRmSysteminfo, rs);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
        }
        return tawRmSysteminfo;
    }

    /**
     * @return
     * @throws SQLException
     * @see 得到机房列表
     */
    public List list() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT dept_id, maxerrortime, maxdutynum, ex_request, ex_answer, duty_inform,exchange_flag room_id ,cycle_time,staggertime FROM taw_rm_systeminfo";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Kmsysteminfo tawRmSysteminfo = new Kmsysteminfo();
                populate(tawRmSysteminfo, rs);
                list.add(tawRmSysteminfo);
                tawRmSysteminfo = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
        }
        return list;
    }

    /**
     * @return
     * @throws SQLException
     * @see 得到机房列表
     */
    public List list(int offset, int limit) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT dept_id, maxerrortime, maxdutynum, ex_request, ex_answer, duty_inform, room_id,exhange_flag ,cycle_time,staggertime FROM taw_rm_systeminfo";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0)
                rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                Kmsysteminfo tawRmSysteminfo = new Kmsysteminfo();
                populate(tawRmSysteminfo, rs);
                list.add(tawRmSysteminfo);
                tawRmSysteminfo = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
        }
        return list;
    }

}