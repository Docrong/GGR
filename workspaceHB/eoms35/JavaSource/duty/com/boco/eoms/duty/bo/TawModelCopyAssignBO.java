/**
 * 
 */
package com.boco.eoms.duty.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.common.bo.BO;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
import com.boco.eoms.duty.model.TawModelCopy;
import com.boco.eoms.duty.model.TawRmAssignwork;

/**
 * @author FengShaoHong
 *
 */
public class TawModelCopyAssignBO extends BO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6252794298392867497L;

	public TawModelCopyAssignBO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public TawModelCopyAssignBO() {
		super();
	}
	/*
	 * 查询start和end之间的排班进行复制
	 */
	public boolean modelCopy(int roomId,String start,String end,String assignDate) throws Exception{
		com.boco.eoms.db.util.BocoConnection conn = null;
	//	PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
	//	PreparedStatement pstmt_id=null;
		TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
	//	ResultSet rs_id = null;
		ResultSet rs2 = null;
		String allDate=assignDate;
		String temp="";
		TawRmModelSetBo trmsb = new TawRmModelSetBo();
		String workSelect = trmsb.getModel(roomId).getWorkSelect();
	//	String sql;
	  //  sql = "INSERT INTO taw_rm_assignwork (region_id, room_id, dutydate, workid, flag, dutymaster, starttime_defined, endtime_defined, smsflag, notes) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    String sql_assign="";
		sql_assign ="select a.id,a.region_id,a.room_id,a.dutydate,a.workid,a.flag,a.dutymaster,a.starttime_defined,a.endtime_defined,a.notes,a.smsflag from taw_rm_assignwork a WHERE a.room_id = ? and (a.dutydate between ? and ? ) order by a.starttime_defined ";
	   
		int count = 0;
		int workserial = 0;
	//	int id=0;
		try {
			conn = ds.getConnection();
			pstmt2 = conn.prepareStatement(sql_assign);
		    pstmt2.setInt(1, roomId);
		    pstmt2.setString(2, start);
		    pstmt2.setString(3, end);
		    rs2 = pstmt2.executeQuery();
	//	    pstmt = conn.prepareStatement(sql);
		    while(rs2.next()){
		    	count++;
		    	TawRmAssignwork tawRmAssignwork = new TawRmAssignwork();
		    	tawRmAssignwork.setId(Integer.parseInt(rs2.getString("ID")));
		    	tawRmAssignwork.setRegionId(rs2.getInt("region_id"));
		    	tawRmAssignwork.setRoomId(rs2.getInt("room_id"));
		    	tawRmAssignwork.setWorkid(rs2.getInt("workid"));
		    	tawRmAssignwork.setFlag(rs2.getInt("flag"));
		    	tawRmAssignwork.setDutymaster(rs2.getString("dutymaster"));
		    	tawRmAssignwork.setNotes(rs2.getString("dutymaster"));
		    	tawRmAssignwork.setSmsflag(rs2.getInt("smsflag"));
		        String	startDate = rs2.getString("starttime_defined");
		    	String endDate = rs2.getString("endtime_defined");
		    	String dutyDate = rs2.getString("dutydate");
		    	if("".equals(temp)){
		    		temp = dutyDate;
		    	}
		    	else{
		    		if(!temp.equals(dutyDate)){
		    			temp = dutyDate;
		    			allDate = getDate(allDate,workSelect);
		    		}
		    	}
		    	tawRmAssignwork.setDutydate(allDate);
	    		tawRmAssignwork.setStarttimeDefined(newDate(allDate,startDate));
	    		tawRmAssignwork.setEndtimeDefined(newDate(allDate,endDate));
	    		tawRmAssignworkDAO.insert(tawRmAssignwork.getRoomId(), getAddZero(tawRmAssignwork.getDutydate()), tawRmAssignwork.getWorkid(), tawRmAssignwork.getDutymaster(), getAddZero(tawRmAssignwork.getStarttimeDefined()), getAddZero(tawRmAssignwork.getEndtimeDefined())) ; 
	    		    /* 
	    		      pstmt.setInt(1, tawRmAssignwork.getRegionId());
	    		      pstmt.setInt(2, tawRmAssignwork.getRoomId());
	    		      pstmt.setString(3, getAddZero(tawRmAssignwork.getDutydate()));
	    		      pstmt.setInt(4, tawRmAssignwork.getWorkid());
	    		      pstmt.setInt(5, tawRmAssignwork.getFlag());
	    		      pstmt.setString(6, tawRmAssignwork.getDutymaster());
	    		      pstmt.setString(7, getAddZero(tawRmAssignwork.getStarttimeDefined()));
	    		      pstmt.setString(8, getAddZero(tawRmAssignwork.getEndtimeDefined()));
	    		      pstmt.setInt(9, tawRmAssignwork.getSmsflag());
	    		      pstmt.setString(10, tawRmAssignwork.getNotes());
	    		      pstmt.setInt(11, id);
	    		      pstmt.executeUpdate();*/
	    		workserial = tawRmAssignworkDAO
				.get_id(roomId, getAddZero(tawRmAssignwork.getDutydate()), tawRmAssignwork.getWorkid());
	    		
      
	    		      otherCopy(tawRmAssignwork.getId(),workserial);
	    		   
		    }
		   // conn.commit();
		    if(count>0){
		    trmsb.deledeModel(roomId);
		    trmsb.setModel(roomId, assignDate, getAddZero(allDate), workSelect);
			return true;
		    }else{
		    	return false;
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return false;
		} finally {
	    	
		//	pstmt.close();
			rs2.close();
			pstmt2.close();
			conn.close();
		}
	}
	/*
	 * 对其它职员进行复制
	 */
	public void otherCopy(int oldId,int newId) throws Exception{
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt_in = null;
		PreparedStatement pstmt_out = null;
		ResultSet rs_out = null;
		String sql_in;
	    sql_in = "INSERT INTO taw_rm_assign_sub (workserial,dutyman, notes) VALUES ( ?, ?, ? )";
	    String sql_out="";
		sql_out ="select a.workserial,a.dutyman,a.notes from taw_rm_assign_sub a WHERE a.workserial = ? ";
		try {
			conn = ds.getConnection();
			pstmt_out = conn.prepareStatement(sql_out);
		    pstmt_out.setInt(1, oldId);
		    rs_out = pstmt_out.executeQuery();
		    pstmt_in = conn.prepareStatement(sql_in);
		    while(rs_out.next()){
	          
		    	String dutyman = rs_out.getString("dutyman");
		    	String notes = rs_out.getString("notes");
		    	pstmt_in.setInt(1, newId);
		    	pstmt_in.setString(2, dutyman);
		    	pstmt_in.setString(3, notes);
		    	pstmt_in.executeUpdate();
		    }
		    conn.commit();
		    
		}
		catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			rs_out.close();
			pstmt_in.close();
			pstmt_out.close();
			conn.close();
		}
	}
//	 日期加1 如果為周六則加2。周日加1
	public static String getDate(String baseDate, String workSelect) {
	
		String date = "";
		date = baseDate;
			date = getDateAddString(date, 1);
			if (week(date) == 0 && workSelect.equals("0")) {
				date = getDateAddString(date, 1);
			}
			if (week(date) == 6 && workSelect.equals("0")) {
				date = getDateAddString(date, 2);
			}
		
		return date;
	}
/**
 * @see 将源时间加上date_add天，变成目标时间串；例如输入2003－6－1，3，返回2003－6－4
 * @param old_date
 * @param date_add
 * @return
 */
	public static String getDateAddString(String old_date, int date_add) {
		String new_date = "";
		GregorianCalendar cal = new GregorianCalendar();
		Vector time_vector = StaticMethod.getVector(old_date, " ");
		Vector date_vector = StaticMethod.getVector(String.valueOf(
				time_vector.elementAt(0)).trim(), "-");
		int year = Integer.parseInt(String.valueOf(date_vector.elementAt(0))
				.trim());
		int month = Integer.parseInt(String.valueOf(date_vector.elementAt(1))
				.trim());
		int day = Integer.parseInt(String.valueOf(date_vector.elementAt(2))
				.trim());
		java.util.Date date = new java.util.Date(year - 1900, month - 1,
				day - 0);
		cal.setTime(date);
		cal.add(5, date_add);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DATE);
		new_date = String.valueOf(year).trim() + "-"
				+ String.valueOf(month).trim() + "-"
				+ String.valueOf(day).trim();
		if (time_vector.size() > 1) {
			new_date = new_date + " " + time_vector.elementAt(1);
		}
		// null
		cal = null;
		time_vector = null;
		date_vector = null;
		date = null;
		return new_date;
	}
//	 判斷是否為周末
	public static int week(String old_date) {
		int i = 0;
		Vector time_vector = StaticMethod.getVector(old_date, " ");
		Vector date_vector = StaticMethod.getVector(String.valueOf(
				time_vector.elementAt(0)).trim(), "-");
		int year = Integer.parseInt(String.valueOf(date_vector.elementAt(0))
				.trim());
		int month = Integer.parseInt(String.valueOf(date_vector.elementAt(1))
				.trim());
		int day = Integer.parseInt(String.valueOf(date_vector.elementAt(2))
				.trim());
		java.util.Date date = new java.util.Date(year - 1900, month - 1,
				day - 0);
		i = date.getDay();
		return i;
	}
	public String newDate(String baseDate,String dateTime){
		String riqi = baseDate;
		   String date = dateTime;
		   String datesub =riqi+ date.substring(10, date.length());
		   return datesub;
	}
	
	/**
	   * @see 插入排班记录
	   * @param tawRmAssignwork
	   * @throws SQLException
	   */
	  public void insert(TawRmAssignwork tawRmAssignwork) throws SQLException {
	    String sql;
	    sql = "INSERT INTO taw_rm_assignwork ( region_id, room_id, dutydate, workid, flag, dutymaster, starttime_defined, endtime_defined, smsflag, notes) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    com.boco.eoms.db.util.BocoConnection conn = null;
	    PreparedStatement pstmt = null;
	  
	    try {
	      conn = ds.getConnection();
	      pstmt = conn.prepareStatement(sql);
	   //   pstmt.setInt(1, tawRmAssignwork.getId());
	      pstmt.setInt(1, tawRmAssignwork.getRegionId());
	      pstmt.setInt(2, tawRmAssignwork.getRoomId());
	      String sss =  StaticMethod.getAddZero(tawRmAssignwork.getDutydate());
	      pstmt.setString(3, StaticMethod.getAddZero(tawRmAssignwork.getDutydate()));
	      pstmt.setInt(4, tawRmAssignwork.getWorkid());
	      pstmt.setInt(5, tawRmAssignwork.getFlag());
	      pstmt.setString(6, tawRmAssignwork.getDutymaster());
	      pstmt.setString(7, StaticMethod.getAddZero(tawRmAssignwork.getStarttimeDefined()));
	      pstmt.setString(8, StaticMethod.getAddZero(tawRmAssignwork.getEndtimeDefined()));
	      pstmt.setInt(9, tawRmAssignwork.getSmsflag());
	      pstmt.setString(10, tawRmAssignwork.getNotes());
	      pstmt.executeUpdate();
	      pstmt.close();
	      conn.commit();
	    } catch (SQLException sqle) {
	     
	      conn.rollback();
	      sqle.printStackTrace();
	      throw sqle;
	    } finally {
	    	
		     pstmt.close();
	      conn.close();
	    }
	  }
	  public static String getAddZero(String str) {
			String time = "";

			try {
				
					String[] strArray = str.split(" ");
					String dataStr = strArray[0];

					String[] time_ = dataStr.split("-");

					int month = Integer.parseInt(time_[1]);
					String month_ = "";
					if (month < 10)
						month_ = "0" + month;
					else
						month_ = month + "";

					int day = Integer.parseInt(time_[2]);
					String day_ = "";
					if (day < 10)
						day_ = "0" + day;
					else
						day_ = day + "";

					if (str.indexOf(" ") > 0)
						time = time_[0] + "-" + month_ + "-" + day_ + " "
								+ strArray[1];
					else
						time = time_[0] + "-" + month_ + "-" + day_;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return time;
		}
}
