/**
 * @see
 * <p>功能描述：排班子表的DAO。</p>
 * @author 赵川
 * @version 2.0
 */


package com.boco.eoms.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.CacheManager;
//import com.boco.eoms.jbzl.dao.*;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

public class TawRmAssignSubDAO extends DAO  {

  public TawRmAssignSubDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  /**
   * @see
   * @param 排班班次号workserial
   * @return 该班次排班人员ID的列表向量
   * @throws SQLException
   */
  public Vector getDutymanVec(int workserial) throws SQLException {
    Vector getDutymanVec = new Vector();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT dutyman,id FROM taw_rm_assign_sub WHERE workserial=? order by id";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, workserial);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        getDutymanVec.add(StaticMethod.dbNull2String(rs.getString(1)));
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
    return getDutymanVec;
  }

  /**
   * @see
   * @param 排班班次号workserial
   * @return  该班次排班人员姓名拼出来的字符串
   * @throws SQLException
   */
  public String getDutymanStr(int workserial) throws SQLException {
    String DutymanStr = "";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    //  edit by wangheqi 2.7to3.5
	//TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
	//TawSystemUser tawRmUser = null;
	//edit end
    //TawRmUserDAO tawRmUserDAO=null;
    //tawRmUserDAO = new TawRmUserDAO(ds);
    try {
      conn = ds.getConnection();
      String sql = "SELECT a.dutyman,b.username FROM taw_rm_assign_sub a,taw_system_user b WHERE workserial=? and b.userid=a.dutyman";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, workserial);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        if (!StaticMethod.null2String(rs.getString(1)).equals(""))
        	DutymanStr = DutymanStr + " " + rs.getString(2).trim();
        	//DutymanStr = DutymanStr + " " + tawRmUserDAO.getUserName(rs.getString(1).trim());
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
      //tawRmUserDAO=null;
    }
    return DutymanStr;
  }

  /**
   * @see  改变该班次号的排班人员
   * @param 排班班次号workserial
   * @param 原排班人员dutyman_from
   * @param 替班人员dutyman_to
   * @throws SQLException
   */
  public void update_changeduty(int workserial,String dutyman_from,String dutyman_to) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "UPDATE taw_rm_assign_sub SET dutyman=? WHERE workserial=? and dutyman=? ";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(2, workserial);
      pstmt.setString(3, dutyman_from);
      pstmt.setString(1, dutyman_to);
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
   * @see  删除该机房、该日期的排班子表记录
   * @param 机房编号roomId
   * @param 日期dutydate
   * @throws SQLException
   */
  public void delete_room_date(int roomId,String dutydate) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM taw_rm_assign_sub WHERE workserial in (SELECT id FROM taw_rm_assignwork where room_id=? and dutydate=?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, roomId);
      pstmt.setString(2,dutydate) ;
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
   * @see 将排班班次、人员ID插入到排班子表
   * @param 排班号workserial
   * @param 人员ID dutyman
   * @throws SQLException
   */
  public void insert(int workserial,String dutyman) throws SQLException {
    String sql;
    sql = "INSERT INTO taw_rm_assign_sub ( workserial, dutyman) VALUES (?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, workserial);
      pstmt.setString(2, dutyman);
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
   * @see  插入排班子表记录
   * @param 排班子表对象tawRmAssignSub
   * @throws SQLException
   */
  public void insert(TawRmAssignSub tawRmAssignSub) throws SQLException {
    String sql;
    sql = "INSERT INTO taw_rm_assign_sub (id, workserial, dutyman, notes) VALUES (?, ?, ?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmAssignSub.getId());
      pstmt.setInt(2, tawRmAssignSub.getWorkserial());
      pstmt.setString(3, tawRmAssignSub.getDutyman());
      pstmt.setString(4, tawRmAssignSub.getNotes());
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
   * @see  修改排班子表记录
   * @param 排班子表对象tawRmAssignSub
   * @throws SQLException
   */
  public void update(TawRmAssignSub tawRmAssignSub) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "UPDATE taw_rm_assign_sub SET workserial=?, dutyman=?, notes=? WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmAssignSub.getWorkserial());
      pstmt.setString(2, tawRmAssignSub.getDutyman());
      pstmt.setString(3, tawRmAssignSub.getNotes());
      pstmt.setInt(4, tawRmAssignSub.getId());
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
   * @see  删除排班子表ID对应的记录
   * @param 排班子表ID id
   * @throws SQLException
   */
  public void delete(int id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM taw_rm_assign_sub WHERE id=?";
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
   * @see  根据排班子表ID，得到排班子表对象
   * @param 排班子表ID id
   * @return  排班子表对象tawRmAssignSub
   * @throws SQLException
   */
  public TawRmAssignSub retrieve(int id) throws SQLException {
    TawRmAssignSub tawRmAssignSub = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT * FROM taw_rm_assign_sub WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawRmAssignSub = new TawRmAssignSub();
        populate(tawRmAssignSub, rs);
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
    return tawRmAssignSub;
  }

  /**
   * @see 得到排班子表列表
   * @return  排班子表列表
   * @throws SQLException
   */
  public List list() throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TawRmAssignSub tawRmAssignSub =null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT workserial, dutyman, notes, id FROM taw_rm_assign_sub";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        tawRmAssignSub = new TawRmAssignSub();
        populate(tawRmAssignSub, rs);
        list.add(tawRmAssignSub);
        tawRmAssignSub =null;
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
   * @see  得到排班子表列表
   * @param offset
   * @param limit
   * @return
   * @throws SQLException
   */
  public List list(int offset, int limit) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TawRmAssignSub tawRmAssignSub =null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT workserial, dutyman, notes, id FROM taw_rm_assign_sub";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if(offset > 0) rs.absolute(offset);
      int recCount = 0;
      while((recCount++ < limit) && rs.next()) {
        tawRmAssignSub = new TawRmAssignSub();
        populate(tawRmAssignSub, rs);
        list.add(tawRmAssignSub);
        tawRmAssignSub =null;
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
   * @see  得到班次下所有人员id
   * @param workSerial
   * @return
   * @throws SQLException
   */
  public List getUserIdListByWorkSerial(String workSerial) throws SQLException {
    ArrayList list = new ArrayList();
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT dutyman FROM taw_rm_assign_sub where workserial='"+workSerial+"' group by dutyman";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
    	  list.add(rs.getString(1));
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
   * @see  根据个人id和班次好 判断这个人在这个班次内有没有值班计划
   * @param workSerial userid
   * @return boolean
   * @throws SQLException
   */
  public boolean getUserIdEqualsWorkSerial(String workSerial,String userid) throws SQLException {
	   boolean bool = false;
	    com.boco.eoms.db.util.BocoConnection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      conn = ds.getConnection();
	      String sql = "SELECT count(*) as cn FROM taw_rm_assign_sub where workserial='"+workSerial+"'  and dutyman = '"+userid+"'";
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      while(rs.next()) {
	    	 int i = rs.getInt("cn");
	    	 if(i!=0){
	    		bool=true; 
	    	 }
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
	    return bool;
	  }

}