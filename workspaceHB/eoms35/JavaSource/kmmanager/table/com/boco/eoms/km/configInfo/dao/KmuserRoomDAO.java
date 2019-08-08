/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：排班子表的DAO。</p>
 */


package com.boco.eoms.km.configInfo.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.km.configInfo.model.KmuserRoom;
import com.boco.eoms.common.dao.*;

public class KmuserRoomDAO extends DAO {

    public KmuserRoomDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param 机房ID                            room_id
     * @param 机房对应人员标识flag(是否参加机房排班，0参加，1不参加)
     * @return
     * @throws SQLException
     * @see 得到机房和标识对应的人员ID和姓名的列表
     */
    public Vector getRoomUser(int room_id, int flag) throws SQLException {
        Vector vectorRoomUser = new Vector();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();

            String sql = "SELECT r.user_id,u.username ,r.ordercode FROM km_user_room r, taw_system_user u WHERE r.room_id = ? and r.user_id = u.userid and u.deleted=0 and r.flag=? order by r.ordercode ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, room_id);
            pstmt.setInt(2, flag);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                vectorRoomUser.add(rs.getString(1));
                vectorRoomUser.add(rs.getString(2));
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
        return vectorRoomUser;
    }


    /**
     * @param 机房ID                            room_id
     * @param 机房对应人员标识flag(是否参加机房排班，0参加，1不参加)
     * @return
     * @throws SQLException
     * @see 得到机房和标识对应的人员ID和姓名的列表，不同于getRoomUser方法的是先插入一个空值
     */
    public Vector getRoomUserWithNull(int room_id, int flag) throws SQLException {
        //different from TawUserRoomBO.getRoomUser for it insert a "null" first
        Vector vectorRoomUser = new Vector();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        vectorRoomUser.add("");
        vectorRoomUser.add("空");
        try {
            conn = ds.getConnection();

            //String sql = "SELECT r.user_id,u.user_name ,r.ordercode FROM taw_user_room r, taw_rm_user u WHERE r.room_id = ? and r.user_id = u.user_id and u.deleted=0 and r.flag=? order by r.ordercode";
            String sql = "SELECT r.user_id,u.username ,r.ordercode FROM km_user_room r, taw_system_user u WHERE r.room_id = ? and r.user_id = u.userid and u.deleted=0 and r.flag=? order by r.ordercode";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, room_id);
            pstmt.setInt(2, flag);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                vectorRoomUser.add(StaticMethod.null2String(rs.getString(1)));
                vectorRoomUser.add(StaticMethod.null2String(rs.getString(2)));
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
        return vectorRoomUser;
    }

    /**
     * @param tawUserRoom
     * @throws SQLException
     * @see 插入机房对应用户
     */
    public void insert(KmuserRoom tawUserRoom) throws SQLException {
        String sql;
        sql = "INSERT INTO km_user_room (user_id, room_id, ordercode, flag) VALUES (?, ?, ?, ?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawUserRoom.getUserId());
            pstmt.setInt(2, tawUserRoom.getRoomId());
            pstmt.setInt(3, tawUserRoom.getOrdercode());
            pstmt.setInt(4, tawUserRoom.getFlag());
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
     * @param tawUserRoom
     * @throws SQLException
     * @see 修改机房对应用户
     */
    public void update(KmuserRoom tawUserRoom) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "UPDATE km_user_room SET ordercode=?, flag=? WHERE user_id=? AND room_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawUserRoom.getOrdercode());
            pstmt.setInt(2, tawUserRoom.getFlag());
            pstmt.setString(3, tawUserRoom.getUserId());
            pstmt.setInt(4, tawUserRoom.getRoomId());
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
     * @param userId
     * @param roomId
     * @throws SQLException
     * @see 删除该机房的该用户
     */
    public void delete(String userId, int roomId) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM km_user_room WHERE user_id=? AND room_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setInt(2, roomId);
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
     * @throws SQLException
     * @see 删除该机房所有用户
     */
    public void delete(int roomId) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM km_user_room WHERE room_id=?";
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
     * @param userId
     * @param roomId
     * @return
     * @throws SQLException
     * @see 根据机房和用户得到该记录
     */
    public KmuserRoom retrieve(String userId, int roomId) throws SQLException {
        KmuserRoom tawUserRoom = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM km_user_room WHERE user_id=? AND room_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setInt(2, roomId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawUserRoom = new KmuserRoom();
                populate(tawUserRoom, rs);
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
        return tawUserRoom;
    }

//得到此人的机房属性

    /**
     * @param userId
     * @return
     * @throws SQLException
     * @see 根据用户ID，得到该用户对应所有机房列表
     */
    public Vector retrieveRoom(String userId) throws SQLException {
        Vector vectorRoom = new Vector();
        int roomId;
        KmuserRoom tawUserRoom = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "Select Room_ID from km_user_room where user_id = '" +
                    userId + "' and Room_ID in (select id from taw_apparatusroom where deleted = 0) order by room_id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vectorRoom.add(new Integer(rs.getInt(1)));
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
        return vectorRoom;
    }


    /**
     * @param userId
     * @return
     * @throws SQLException
     * @see 得到用户对应的机房ID和名称列表
     */
    public Vector retrieveRoomName(String userId) throws SQLException {
        Vector vectorRoom = new Vector();
        Vector vectorRoomName = new Vector();
        int roomId;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        com.boco.eoms.db.util.BocoConnection connN = null;
        PreparedStatement pstmtN = null;
        ResultSet rsN = null;
        try {
            conn = ds.getConnection();
            String sql = "Select u.Room_ID,r.room_name from km_user_room u,taw_apparatusroom r where u.user_id = '" +
                    userId + "' and u.room_id = r.id and u.Room_ID in (select id from taw_apparatusroom where deleted = 0) order by u.room_id";
            pstmt = conn.prepareStatement(sql);
            //System.out.println(sql);
            rs = pstmt.executeQuery();
            connN = ds.getConnection();
            while (rs.next()) {
                vectorRoom.add(new Integer(rs.getInt(1)));
                vectorRoomName.add(StaticMethod.null2String(rs.getString(2)));
            }
            close(rs);
            close(pstmt);
            close(rsN);
            close(pstmtN);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            close(rsN);
            close(pstmtN);
            rollback(connN);
            e.printStackTrace();
        } finally {
            close(conn);
            close(connN);
        }
        return vectorRoomName;
    }

    /**
     * @param userId
     * @return
     * @throws SQLException
     * @see 得到用户对应的机房ID和名称列表
     */
    public Vector getUserRoom(String user_id) throws SQLException {
        Vector vectorUserRoom = new Vector();
        Vector vectorRoomId = new Vector();
        Vector vectorRoomName = new Vector();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();

            String sql = "select u.user_id,u.room_id,r.id,r.room_name,r.deleted from km_user_room u,taw_apparatusroom r where u.user_id = ? and u.room_id = r.id and r.deleted = 0";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vectorRoomId.add(StaticMethod.null2String(String.valueOf(rs.getInt(3))));
                vectorRoomName.add(StaticMethod.null2String(rs.getString(4)));
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
        vectorUserRoom.add(vectorRoomId);
        vectorUserRoom.add(vectorRoomName);
        return vectorUserRoom;
    }


    /**
     * @param roomId
     * @return
     * @throws SQLException
     * @see 根据机房id得到机房name
     */
    public String getRoomName(int roomId) throws SQLException {
        KmuserRoom tawUserRoom = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String roomName = "";
        try {
            conn = ds.getConnection();
            String sql =
                    "Select ID,roomname from km_system_cptroom where id = " + roomId + ""; //查询相应的机房名程，roomid转为roomname
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                roomName = StaticMethod.null2String(rs.getString(2));
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
        return roomName;
    }

    /**
     * @return
     * @throws SQLException
     * @see 得到用户机房对象列表
     */
    public List list() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT ordercode, flag, user_id, room_id FROM km_user_room";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                KmuserRoom tawUserRoom = new KmuserRoom();
                populate(tawUserRoom, rs);
                list.add(tawUserRoom);
                tawUserRoom = null;
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
     * @return
     * @throws SQLException
     * @see 得到用户机房对象列表
     */
    public List list(int offset, int limit) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT ordercode, flag, user_id, room_id FROM km_user_room";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                KmuserRoom tawUserRoom = new KmuserRoom();
                populate(tawUserRoom, rs);
                list.add(tawUserRoom);
                tawUserRoom = null;
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
