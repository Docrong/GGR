/**
 * @see
 * <p>功能描述：排班子表z专家的DAO。</p>
 * @author 冯少宏
 * @version 2.0
 */


package com.boco.eoms.duty.dao;

import java.sql.*;
import java.util.*;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.duty.model.TawExpertRoom;
import com.boco.eoms.common.dao.*;

public class TawExpertRoomDAO extends DAO {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1561859078997962264L;

public TawExpertRoomDAO(com.boco.eoms.db.util.ConnectionPool  ds) {
    super(ds);
  }

  /**
   * @see  得到机房对应的专家ID和姓名的列表
   * @param 机房ID room_id
   * @return
   * @throws SQLException
   */
  public Vector getRoomExpert(int room_id) throws SQLException {
    Vector vectorRoomUser = new Vector();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();

      String sql = "SELECT r.user_id,u.expertName ,r.ordercode FROM taw_expert_room r, taw_expert_info u WHERE r.room_id = ? and r.user_id = u.expertId  order by r.ordercode ";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, room_id);
      rs = pstmt.executeQuery();

      while(rs.next()) {
        vectorRoomUser.add(rs.getString(1));
        vectorRoomUser.add(rs.getString(2));
      }
      rs.close();
      pstmt.close() ;
    } catch (SQLException e) {
      rs.close();
      pstmt.close() ;
      conn.rollback() ;
      e.printStackTrace();
    } finally {
      conn.close() ;
    }
    return vectorRoomUser;
  }


  /**
   * @see  得到机房对应的专家人员ID和姓名的列表，不同于getExpertUser方法的是先插入一个空值
   * @param 机房ID room_id
   * @return
   * @throws SQLException
   */
  public Vector getRoomExpertWithNull(int room_id) throws SQLException {
    //different from TawUserRoomBO.getRoomUser for it insert a "null" first
    Vector vectorRoomUser = new Vector();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    vectorRoomUser.add("");
    vectorRoomUser.add("请选择");
    try {
      conn = ds.getConnection();
      String sql = "SELECT r.user_id,u.expertName ,r.ordercode FROM taw_expert_room r, taw_expert_info u WHERE r.room_id = ? and r.user_id = u.expertId  order by r.ordercode";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, room_id);
      rs = pstmt.executeQuery();

      while(rs.next()) {
        vectorRoomUser.add(StaticMethod.null2String(rs.getString(1)));
        vectorRoomUser.add(StaticMethod.null2String(rs.getString(2)));
      }
      rs.close();
      pstmt.close() ;
    } catch (SQLException e) {
      rs.close();
      pstmt.close() ;
      conn.rollback() ;
      e.printStackTrace();
    } finally {
      conn.close() ;
    }
    return vectorRoomUser;
  }

  /**
   * @see 插入机房对应专家用户
   * @param tawUserRoom
   * @throws SQLException
   */
  public void insert(TawExpertRoom tawExpertRoom) throws SQLException {
    String sql;
    sql = "INSERT INTO taw_expert_room (user_id, room_id, ordercode, flag) VALUES (?, ?, ?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, tawExpertRoom.getUserId());
      pstmt.setInt(2, tawExpertRoom.getRoomId());
      pstmt.setInt(3, tawExpertRoom.getOrdercode());
      pstmt.setInt(4, tawExpertRoom.getFlag());
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
   * @see 修改机房对应专家用户
   * @param tawExpertRoom
   * @throws SQLException
   */
  public void update(TawExpertRoom tawUserRoom) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "UPDATE taw_expert_room SET ordercode=?, flag=? WHERE user_id=? AND room_id=?";
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
   * @see 删除该机房的该专家用户
   * @param userId
   * @param roomId
   * @throws SQLException
   */
  public void delete(String userId, int roomId) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM taw_expert_room WHERE user_id=? AND room_id=?";
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
   * @see 删除该机房所有专家用户
   * @param roomId
   * @throws SQLException
   */
  public void delete( int roomId) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM taw_expert_room WHERE room_id=?";
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
   * @see 根据机房和专家用户得到该记录
   * @param userId
   * @param roomId
   * @return
   * @throws SQLException
   */
  public TawExpertRoom retrieve(String userId, int roomId) throws SQLException {
    TawExpertRoom tawUserRoom = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT * FROM taw_expert_room WHERE user_id=? AND room_id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userId);
      pstmt.setInt(2, roomId);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawUserRoom = new TawExpertRoom();
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
   * @see 根据用户ID，得到该用户对应所有机房列表
   * @param userId
   * @return
   * @throws SQLException
   */
  public Vector retrieveRoom(String userId) throws SQLException {
    Vector vectorRoom = new Vector();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "Select Room_ID from taw_expert_room where user_id = '" +
          userId + "' and Room_ID in (select id from taw_apparatusroom where deleted = 0) order by room_id";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        vectorRoom.add(new Integer(rs.getInt(1)));
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return vectorRoom;
  }


  /**
   * @see 得到专家用户对应的机房名称列表
   * @param userId
   * @return
   * @throws SQLException
   */
  public Vector retrieveRoomName(String userId) throws SQLException {
    Vector vectorRoom = new Vector();
    Vector vectorRoomName = new Vector();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    com.boco.eoms.db.util.BocoConnection connN = null;
    PreparedStatement pstmtN = null;
    ResultSet rsN = null;
    try {
      conn = ds.getConnection();
      String sql = "Select u.Room_ID,r.room_name from taw_expert_room u,taw_apparatusroom r where u.user_id = '" +
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
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
      rollback(conn);
      close(rsN);
      close(pstmtN);
      rollback(connN);
      e.printStackTrace();
    }
    finally {
      close(conn);
      close(connN);
    }
    return vectorRoomName;
  }

  /**
   * @see 得到专家用户对应的机房ID和名称列表
   * @param userId
   * @return
   * @throws SQLException
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

      String sql = "select u.user_id,u.room_id,r.id,r.room_name,r.deleted from taw_user_room u,taw_apparatusroom r where u.user_id = ? and u.room_id = r.id and r.deleted = 0";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, user_id);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        vectorRoomId.add(StaticMethod.null2String(String.valueOf(rs.getInt(3))));
        vectorRoomName.add(StaticMethod.null2String(rs.getString(4)));
      }
      rs.close();
      pstmt.close() ;
    } catch (SQLException e) {
      rs.close();
      pstmt.close() ;
      conn.rollback() ;
      e.printStackTrace();
    } finally {
      conn.close() ;
    }
    vectorUserRoom.add(vectorRoomId);
    vectorUserRoom.add(vectorRoomName);
    return vectorUserRoom;
  }




  /**
   * @see 根据机房id得到机房name
   * @param roomId
   * @return
   * @throws SQLException
   */
  public String getRoomName(int roomId) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String roomName = "";
    try {
      conn = ds.getConnection();
      String sql =
          "Select ID,roomname from taw_system_cptroom where id = " +roomId+ ""; //查询相应的机房名程，roomid转为roomname
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        roomName=StaticMethod.null2String(rs.getString(2));
      }
      close(rs);
      close(pstmt);
    }
    catch (SQLException e) {
      close(rs);
      close(pstmt);
      rollback(conn);
      e.printStackTrace();
    }
    finally {
      close(conn);
    }
    return roomName;
  }

  /**
   * @see 得到用户机房对象列表
   * @return
   * @throws SQLException
   */
  public List list() throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT ordercode, flag, user_id, room_id FROM taw_expert_room";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        TawExpertRoom tawUserRoom = new TawExpertRoom();
        populate(tawUserRoom, rs);
        list.add(tawUserRoom);
        tawUserRoom=null;
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
   * @see 得到用户机房对象列表
   * @return
   * @throws SQLException
   */
  public List list(int offset, int limit) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT ordercode, flag, user_id, room_id FROM taw_expert_room";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if(offset > 0) rs.absolute(offset);
      int recCount = 0;
      while((recCount++ < limit) && rs.next()) {
        TawExpertRoom tawUserRoom = new TawExpertRoom();
        populate(tawUserRoom, rs);
        list.add(tawUserRoom);
        tawUserRoom=null;
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
