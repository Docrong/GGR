/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：交接班时间的DAO。</p>
 */


package com.boco.eoms.km.configInfo.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.km.configInfo.model.*;
import com.boco.eoms.common.dao.*;

public class KmexchangeDAO extends DAO {

    public KmexchangeDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param room_id
     * @return
     * @throws SQLException
     * @see 得到机房ID对应交接班时间列表
     */
    public Vector getVectorExchangTime(int room_id) throws SQLException {
        Vector vectorExchangTime = new Vector();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();

            String sql = "SELECT exchangetime,id  FROM km_exchange where room_id=? order by id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, room_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vectorExchangTime.add(rs.getString(1));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            rs.close();
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return vectorExchangTime;
    }


    /**
     * @param roomId
     * @throws SQLException
     * @see 删除机房ID对应的交接班时间记录
     */
    public void delete_room(int roomId) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM km_exchange WHERE room_id=? ";
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
        }
    }


    /**
     * @param roomId
     * @return
     * @throws SQLException
     * @see 得到机房ID对应每天的班次数量
     */
    public int get_team_num(int roomId) throws SQLException {
        int team_num = 0;
        String sql;
        sql = "SELECT count(*) from km_exchange where room_id = ?";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                team_num = rs.getInt(1);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
        }
        return team_num;
    }
    //

    /**
     * @param tawRmExchange
     * @throws SQLException
     * @see 插入交接班时间记录
     */
    public void insert(Kmexchange tawRmExchange) throws SQLException {
        String sql;
        sql = "INSERT INTO km_exchange (room_id, id, exchangetime) VALUES (?, ?, ?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawRmExchange.getRoomId());
            pstmt.setShort(2, tawRmExchange.getId());
            pstmt.setString(3, tawRmExchange.getExchangetime());
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
     * @param tawRmExchange
     * @throws SQLException
     * @see 更新交接班时间记录
     */
    public void update(Kmexchange tawRmExchange) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "UPDATE km_exchange SET exchangetime=? WHERE room_id=? AND id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawRmExchange.getExchangetime());
            pstmt.setInt(2, tawRmExchange.getRoomId());
            pstmt.setShort(3, tawRmExchange.getId());
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
     * @param roomId
     * @param id
     * @throws SQLException
     * @see 删除机房ID和交接班序列ID对应的交接班时间记录
     */
    public void delete(int roomId, short id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM km_exchange WHERE room_id=? AND id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setShort(2, id);
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
     * @param roomId
     * @param id
     * @return
     * @throws SQLException
     * @see 通过机房ID和交接班时间序列ID得到交接班时间对象
     */
    public Kmexchange retrieve(int roomId, short id) throws SQLException {
        Kmexchange tawRmExchange = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM km_exchange WHERE room_id=? AND id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setShort(2, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmExchange = new Kmexchange();
                populate(tawRmExchange, rs);
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
        return tawRmExchange;
    }

    /**
     * @return
     * @throws SQLException
     * @see 得到交接班时间列表
     */
    public List list() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT exchangetime, room_id, id FROM km_exchange";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Kmexchange tawRmExchange = new Kmexchange();
                populate(tawRmExchange, rs);
                list.add(tawRmExchange);
                tawRmExchange = null;
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
     * @return
     * @throws SQLException
     * @see 得到交接班时间记录列表
     */
    public List list(int offset, int limit) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT exchangetime, room_id, id FROM km_exchange";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                Kmexchange tawRmExchange = new Kmexchange();
                populate(tawRmExchange, rs);
                list.add(tawRmExchange);
                tawRmExchange = null;
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