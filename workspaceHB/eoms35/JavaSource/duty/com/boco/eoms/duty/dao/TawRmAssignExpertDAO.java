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
import com.boco.eoms.duty.model.TawRmAssignExpert;
import com.boco.eoms.duty.webapp.form.TawRmAssignExpertForm;
import com.boco.eoms.common.dao.*;

public class TawRmAssignExpertDAO extends DAO {


/**
	 * 
	 */
	private static final long serialVersionUID = -8394580935289247250L;

public TawRmAssignExpertDAO(com.boco.eoms.db.util.ConnectionPool  ds) {
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
    String sql = "SELECT expert,id FROM taw_rm_assign_expert WHERE workserial=? order by id";
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
public String getExpertStr(int workserial) throws SQLException {
  String DutymanStr = "";
  com.boco.eoms.db.util.BocoConnection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  try {
    conn = ds.getConnection();
    String sql = "SELECT a.expert,b.username FROM taw_rm_assign_expert a,taw_system_user b WHERE workserial=? and b.userid=a.expert";
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, workserial);
    rs = pstmt.executeQuery();
    while (rs.next()) {
      if (!StaticMethod.null2String(rs.getString(1)).equals(""))
      	DutymanStr = DutymanStr + " " + rs.getString(2).trim();
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
    String sql = "UPDATE taw_rm_assign_expert SET expert=? WHERE workserial=? and expert=? ";
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
    String sql = "DELETE FROM taw_rm_assign_expert WHERE workserial in (SELECT id FROM taw_rm_assignwork where room_id=? and dutydate=?)";
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
  sql = "INSERT INTO taw_rm_assign_expert ( workserial, expert) VALUES (?, ?)";
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
public void insert(TawRmAssignExpert tawRmAssignExpert) throws SQLException {
  String sql;
  sql = "INSERT INTO taw_rm_assign_expert (id, workserial, expert, notes) VALUES (?, ?, ?, ?)";
  com.boco.eoms.db.util.BocoConnection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  try {
    conn = ds.getConnection();
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, tawRmAssignExpert.getId());
    pstmt.setInt(2, tawRmAssignExpert.getWorkserial());
    pstmt.setString(3, tawRmAssignExpert.getDutyman());
    pstmt.setString(4, tawRmAssignExpert.getNotes());
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
public void update(TawRmAssignExpert tawRmAssignExpert) throws SQLException {
  com.boco.eoms.db.util.BocoConnection conn = null;
  PreparedStatement pstmt = null;
  try {
    conn = ds.getConnection();
    String sql = "UPDATE taw_rm_assign_expert SET workserial=?, expert=?, notes=? WHERE id=?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, tawRmAssignExpert.getWorkserial());
    pstmt.setString(2, tawRmAssignExpert.getDutyman());
    pstmt.setString(3, tawRmAssignExpert.getNotes());
    pstmt.setInt(4, tawRmAssignExpert.getId());
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
    String sql = "DELETE FROM taw_rm_assign_expert WHERE id=?";
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
public TawRmAssignExpert retrieve(int id) throws SQLException {
  TawRmAssignExpert tawRmAssignSub = null;
  com.boco.eoms.db.util.BocoConnection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  try {
    conn = ds.getConnection();
    String sql = "SELECT * FROM taw_rm_assign_expert WHERE id=?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();
    if (rs.next()) {
      tawRmAssignSub = new TawRmAssignExpert();
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
  TawRmAssignExpert tawRmAssignSub =null;
  try {
    conn = ds.getConnection();
    String sql = "SELECT workserial, expert, notes, id FROM taw_rm_assign_expert";
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    while(rs.next()) {
      tawRmAssignSub = new TawRmAssignExpert();
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
  TawRmAssignExpert tawRmAssignSub =null;
  try {
    conn = ds.getConnection();
    String sql = "SELECT workserial, expert, notes, id FROM taw_rm_assign_expert";
    pstmt = conn.prepareStatement(sql);
    rs = pstmt.executeQuery();
    if(offset > 0) rs.absolute(offset);
    int recCount = 0;
    while((recCount++ < limit) && rs.next()) {
      tawRmAssignSub = new TawRmAssignExpert();
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
    String sql = "SELECT expert FROM taw_rm_assign_expert where workserial='"+workSerial+"' group by expert";
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
	      String sql = "SELECT count(*) as cn FROM taw_rm_assign_expert where workserial='"+workSerial+"'  and expert = '"+userid+"'";
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
public List getExpertbyCondition(String room,String expert,String startTime,String endTime) throws SQLException {
	  ArrayList list = new ArrayList();
	  com.boco.eoms.db.util.BocoConnection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  try {
	    conn = ds.getConnection();
	    String sql = "select a.room_id,a.dutydate,a.starttime_defined,a.endtime_defined,ae.expert,info.expertName " 
	    	+" from taw_rm_assign_expert ae ,taw_rm_assignwork a ,taw_expert_info info where ae.workserial=a.id and a.room_id=?" +
	    			"and ae.expert=info.expertId";
	    if(!"".equals(expert)){
	    	sql=sql+" and ae.expert ='"+expert+"'";
	    }
	    if(!"".equals(startTime)){
	    	sql=sql+" and a.dutydate>'"+startTime+"'";
	    }
	    if(!"".equals(endTime)){
	        sql=sql+" and a.dutydate<'"+endTime+"'";
	    }
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, Integer.parseInt(room));
	    pstmt.executeUpdate();
	    rs = pstmt.executeQuery();
	    while(rs.next()) {
	    	TawRmAssignExpertForm tawRmAssignExpertForm = new TawRmAssignExpertForm();
	    	tawRmAssignExpertForm.setDutyTime(rs.getString(2));
	    	tawRmAssignExpertForm.setRoom(String.valueOf(rs.getInt(1)));
	    	tawRmAssignExpertForm.setStartTime(rs.getString(3));
	    	tawRmAssignExpertForm.setEndTime(rs.getString(4));
	    	tawRmAssignExpertForm.setNotes(rs.getString(5));
	    	tawRmAssignExpertForm.setExpert(rs.getString(6));
	  	  list.add(tawRmAssignExpertForm);
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
