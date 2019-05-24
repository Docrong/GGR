/**
 * @see
 * <p>功能描述：排班的DAO。</p>
 * @author 赵川
 * @version 2.0
 */


package com.boco.eoms.km.duty.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.duty.model.*;
import com.boco.eoms.km.duty.model.Kmassignwork;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.*;
public class KmassignworkDAO extends DAO {

  public KmassignworkDAO(com.boco.eoms.db.util.ConnectionPool ds) {
    super(ds);
  }

  /**
   * @see 修改该班次排班主表值班班长，用dutyman_to替换dutyman_from
   * @param workserial
   * @param dutyman_from
   * @param dutyman_to
   * @throws SQLException
   */
  public void update_dutymaster(int workserial,String dutyman_from,String dutyman_to) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "UPDATE km_assignwork SET dutymaster=? WHERE id=? and dutymaster=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(2, workserial);
      pstmt.setString(1, dutyman_to);
      pstmt.setString(3, dutyman_from);
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
   * @see 得到排班班次对应的开始时间
   * @param id
   * @return
   * @throws SQLException
   */
  public String getStartTime(int id) throws SQLException {
    String StartTime = "";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT starttime_defined FROM km_assignwork WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        StartTime = StaticMethod.StringReplace(rs.getString(1),".0","") ;
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
    return StartTime;
  }
  /**
   * @see 根据机房ID、排班时间和班次序列号得到排班的班次号
   * @param room_id
   * @param dutydate
   * @param workid
   * @return
   */
  public int get_id(int room_id,String dutydate,int workid){
    int id_get=0;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT id FROM km_assignwork where room_id=? and dutydate=? and workid=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, room_id);
      pstmt.setString(2, dutydate);
      pstmt.setInt(3, workid);
      rs = pstmt.executeQuery();
      if(rs.next()) {
        id_get = rs.getInt(1);
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
    return id_get;
  }

  /**
   * @see 根据机房ID、起止时间，得到自动排班需要的数据
   * @param room_id
   * @param str_start
   * @param str_end
   * @return
   */
  public Vector getvecAssignAuto(int room_id,String str_start,String str_end){
    Vector vecAssignAuto = new Vector();
    Vector vecDutydate = new Vector();
    //Vector vecWorkId = new Vector();
    Vector vecUser = new Vector();
    String strDutydate ="";
    //String strWorkId ="";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    KmassignSubDAO tawRmAssignSubDAO=null;
    ResultSet rs = null;
    try {
      SaveSessionBeanDAO saveSessionBeanDAO = new SaveSessionBeanDAO(ds);
      saveSessionBeanDAO.alterDateFormat();
      saveSessionBeanDAO=null;
      tawRmAssignSubDAO = new KmassignSubDAO(ds);
      conn = ds.getConnection();
      String sql = "SELECT ID,ROOM_ID,starttime_defined,DUTYDATE,WORKID  FROM km_ASSIGNWORK where ROOM_ID=? and DUTYDATE >= ? and DUTYDATE <=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, room_id);
      pstmt.setString(2, str_start);
      pstmt.setString(3, str_end);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        strDutydate = StaticMethod.null2String(rs.getString(3));
        //strDutydate = StaticMethod.StringReplace(strDutydate," 00:00:00.0","");
        strDutydate = StaticMethod.StringReplace(strDutydate,"-0","-");
        vecDutydate.add(strDutydate);
        //strWorkId = StaticMethod.null2String(String.valueOf(rs.getInt(4)));
        //vecWorkId.add(strWorkId);
        vecUser.add(tawRmAssignSubDAO.getDutymanVec(rs.getInt(1)));
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
      tawRmAssignSubDAO=null;
    }
    vecAssignAuto.add(vecDutydate);
    //vecAssignAuto.add(vecWorkId);
    vecAssignAuto.add(vecUser);
    return vecAssignAuto;
  }
  /**
   * @see 插入排班主表
   * @param room_id
   * @param dutydate
   * @param workid
   * @param dutymaster
   * @param starttime_defined
   * @param endtime_defined
   * @throws SQLException
   */
  public void insert(int room_id,String dutydate,int workid,String dutymaster,String starttime_defined,String endtime_defined) throws SQLException {
    String sql;
    sql = "INSERT INTO km_assignwork ( room_id, dutydate, workid, dutymaster, starttime_defined, endtime_defined) VALUES (?, ?, ?, ?, ?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {   
      conn = ds.getConnection();
      
       
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, room_id); 
      pstmt.setString(2, StaticMethod.getAddZero(dutydate));
      pstmt.setInt(3, workid);
      pstmt.setString(4, dutymaster);
      pstmt.setString(5, StaticMethod.getAddZero(starttime_defined));
      pstmt.setString(6, StaticMethod.getAddZero(endtime_defined));
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
   * @see 根据机房ID和日期，删除对应的排班记录
   * @param roomId
   * @param dutydate
   * @throws SQLException
   */
  public void delete_room_date(int roomId,String dutydate) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM km_assignwork WHERE room_id=? and dutydate=?";
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
   * @see 插入排班记录
   * @param tawRmAssignwork
   * @throws SQLException
   */
  public void insert(Kmassignwork tawRmAssignwork) throws SQLException {
    String sql;
    sql = "INSERT INTO km_assignwork (id, region_id, room_id, dutydate, workid, flag, dutymaster, starttime_defined, endtime_defined, smsflag, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmAssignwork.getId());
      pstmt.setInt(2, tawRmAssignwork.getRegionId());
      pstmt.setInt(3, tawRmAssignwork.getRoomId());
      pstmt.setString(4, StaticMethod.getAddZero(tawRmAssignwork.getDutydate()));
      pstmt.setInt(5, tawRmAssignwork.getWorkid());
      pstmt.setInt(6, tawRmAssignwork.getFlag());
      pstmt.setString(7, tawRmAssignwork.getDutymaster());
      pstmt.setString(8, StaticMethod.getAddZero(tawRmAssignwork.getStarttimeDefined()));
      pstmt.setString(9, StaticMethod.getAddZero(tawRmAssignwork.getEndtimeDefined()));
      pstmt.setInt(10, tawRmAssignwork.getSmsflag());
      pstmt.setString(11, tawRmAssignwork.getNotes());
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
   * @see 修改排班记录
   * @param tawRmAssignwork
   * @throws SQLException
   */
  public void update(Kmassignwork tawRmAssignwork) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "UPDATE km_assignwork SET region_id=?, room_id=?, dutydate=?, workid=?, flag=?, dutymaster=?, starttime_defined=?, endtime_defined=?, smsflag=?, notes=? WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, tawRmAssignwork.getRegionId());
      pstmt.setInt(2, tawRmAssignwork.getRoomId());
      pstmt.setString(3, StaticMethod.getAddZero(tawRmAssignwork.getDutydate()));
      pstmt.setInt(4, tawRmAssignwork.getWorkid());
      pstmt.setInt(5, tawRmAssignwork.getFlag());
      pstmt.setString(6, tawRmAssignwork.getDutymaster());
      pstmt.setString(7, StaticMethod.getAddZero(tawRmAssignwork.getStarttimeDefined()));
      pstmt.setString(8, StaticMethod.getAddZero(tawRmAssignwork.getEndtimeDefined()));
      pstmt.setInt(9, tawRmAssignwork.getSmsflag());
      pstmt.setString(10, tawRmAssignwork.getNotes());
      pstmt.setInt(11, tawRmAssignwork.getId());
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
   * @see 删除排班记录
   * @param id
   * @throws SQLException
   */
  public void delete(int id) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = ds.getConnection();
      String sql = "DELETE FROM km_assignwork WHERE id=?";
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
   * @see 得到排班记录对象
   * @param id
   * @return
   * @throws SQLException
   */
  public Kmassignwork retrieve(int id) throws SQLException {
    Kmassignwork tawRmAssignwork = null;
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT * FROM km_assignwork WHERE id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        tawRmAssignwork = new Kmassignwork();
        populate(tawRmAssignwork, rs);
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
    return tawRmAssignwork;
  }

  /**
   * @see 按指定的日期得到一个月里每天的班次数
   * @param id
   * @return
   * @throws SQLException
   */
  public Vector getWorkNumofDayByMonth(String YearMonth,int roomId) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Vector WorkNum=new Vector();
    try {
      conn = ds.getConnection();
      String conf="to_char(dutydate) >='"+YearMonth+"-01' and to_char(dutydate)<='"+YearMonth+"-31'  and room_id='"+roomId+"'";
      String sql = "SELECT dutydate,count(dutydate) FROM km_assignwork WHERE "+conf+"  group by dutydate order by dutydate ";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      int i=0;
      while (rs.next()) {

        if(i==0)
        {
          String date = rs.getString(1);
          date = date.substring(0, date.indexOf(" ", 0));
          i++;
          int start = Integer.valueOf(date.substring(date.length() - 2)).
              intValue();
          WorkNum.add(new Integer(start));
        }
          /*
          if (start > 1) {
            for (int j = 0; j < start; j++)
              WorkNum.add(new Integer(0));
          }
        }
        */
        WorkNum.add(new Integer(rs.getInt(2)));
      }
      /*
      int Year=Integer.valueOf(YearMonth.substring(0,4)).intValue();
      int Month=Integer.valueOf(YearMonth.substring(YearMonth.length()-2)).intValue();
      int daynum=DayofMonth(Year,Month);
      if(WorkNum.size()<daynum)
      {
      for(int k=WorkNum.size();k<daynum;k++)
        WorkNum.add(new Integer(0));
      }
      */
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
     return WorkNum;
  }


  /**
   * @see 按指定的日期得到一个月里所有的班次
   * @param id
   * @return
   * @throws SQLException
   */
  public Vector getWorkSerialofMonth(String YearMonth,int roomId) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Vector WorkNum=new Vector();
    try {
      conn = ds.getConnection();
      String conf="to_char(dutydate) >='"+YearMonth+"-01' and to_char(dutydate)<='"+YearMonth+"-31'";
      String sql = "SELECT id,Workid,StartTime_defined,Endtime_defined FROM km_assignwork WHERE "+conf+" and room_id='"+roomId+"' order by StartTime_defined";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      Kmassignwork  tawRmAssignwork=null;
      while (rs.next()) {
      tawRmAssignwork=new Kmassignwork();
      populate(tawRmAssignwork,rs);
      WorkNum.add(tawRmAssignwork);
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
     return WorkNum;
  }


  /**
   * @see 按指定的日期得到一个月里所有的班次
   * @param id
   * @return
   * @throws SQLException
   */
  public Vector getWorkSerialofDay(String Day,int roomId) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Vector WorkNum=new Vector();
    try {
      conn = ds.getConnection();
      String conf="dutydate ='"+Day+"'";
      String sql = "SELECT id,Workid,StartTime_defined,Endtime_defined FROM km_assignwork WHERE "+conf+" and room_id='"+roomId+"' order by StartTime_defined";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      Kmassignwork  tawRmAssignwork=null;
      while (rs.next()) {
      tawRmAssignwork=new Kmassignwork();
      populate(tawRmAssignwork,rs);
      WorkNum.add(tawRmAssignwork);
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
     return WorkNum;
  }

  /**
   * 取出一个机房的最大排班时间
   * @param roomId
   * @return
   * @throws SQLException
   */
  public String  getMaxDutydate(int roomId) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String  Maxduty=null;
    try {
      conn = ds.getConnection();
      String sql = "SELECT max(dutydate) FROM km_assignwork WHERE  room_id= "+roomId+" ";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      //Kmassignwork  tawRmAssignwork=null;
      if (rs.next()) {
        Maxduty=rs.getString(1);
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
     return Maxduty;
  }


  /**
   * @see :判断一个机房在一个日期里是否已经排班
   * @param roomId
   * @return
   * @throws SQLException
   */
  public boolean  IsAssignWork(int roomId,String dutydate) throws SQLException {
    com.boco.eoms.db.util.BocoConnection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean  have=false;
    try {
      conn = ds.getConnection();
      String sql = "SELECT dutydate FROM km_assignwork WHERE  room_id= "+roomId+" and dutydate='"+dutydate+"'";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        have=true;
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
     return have;
  }



  /**
   * @see 得到排班对象列表
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
      String sql = "SELECT region_id, room_id, dutydate, workid, flag, dutymaster, starttime_defined, endtime_defined, smsflag, notes, id FROM km_assignwork";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        Kmassignwork tawRmAssignwork = new Kmassignwork();
        populate(tawRmAssignwork, rs);
        list.add(tawRmAssignwork);
        tawRmAssignwork=null;
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
   * @see 得到排班对象列表
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
    try {
      conn = ds.getConnection();
      String sql = "SELECT region_id, room_id, dutydate, workid, flag, dutymaster, starttime_defined, endtime_defined, smsflag, notes, id FROM km_assignwork";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if(offset > 0) rs.absolute(offset);
      int recCount = 0;
      while((recCount++ < limit) && rs.next()) {
        Kmassignwork tawRmAssignwork = new Kmassignwork();
        populate(tawRmAssignwork, rs);
        list.add(tawRmAssignwork);
        tawRmAssignwork=null;
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
  public int DayofMonth(int year,int month)
  {
  Calendar cal = new GregorianCalendar(year, month-1, 1);
  // Get the number of days in that month
  return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
  }
  
  // 根据用户机房id 用户id 和当前时间得到当前的值班id
  public String getAssignWork(String roomid,String userid,String date) throws SQLException {
	  
	  String workid = "";
	  	com.boco.eoms.db.util.BocoConnection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      conn = ds.getConnection();
	      String sql = "SELECT id FROM km_assignwork where room_id = '"+roomid+"' and  dutymaster = '"+userid+"' and starttime_defined<='"+date+"' and endtime_defined >= '"+date+"'";
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();	      
	      
	      while( rs.next()) {	       
	        workid = rs.getString(1);    
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
	  return workid;
  }
	
	

	/**
	 * @see 根据workSerial查询值班起始时间
	 * @param workSerial
	 * @return
	 * @throws SQLException
	 */
	public String getTimeByWorkSerial(String workSerial)
			throws SQLException {
		String result="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select starttime_defined,endtime_defined from km_assignwork where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(workSerial));
			rs = pstmt.executeQuery();
			// null
			sql = null;
			if (rs.next()) {
				result=rs.getString(1)+"@"+rs.getString(2);
			}
			rs.close();
			pstmt.close();
			// null
			rs = null;
		} catch (SQLException e) {
			rs.close();
			pstmt.close();
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return result;
	}
	
	/**
	 * 根据时间得到机房班次
	 * @param userId
	 * @return List
	 */
	public List getDutyAssignwork(String roomId, String dataTime) {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT a.* FROM km_assignwork a "
					+ " where a.room_id=" + roomId
					+ " and starttime_defined <='" + dataTime + "' "
					+ " and endtime_defined >='" + dataTime + "' "
					+ " order by starttime_defined ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Kmassignwork tawRmAssignwork = new Kmassignwork();
				populate(tawRmAssignwork, rs);
				list.add(tawRmAssignwork);
				tawRmAssignwork = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return list;
	}
}