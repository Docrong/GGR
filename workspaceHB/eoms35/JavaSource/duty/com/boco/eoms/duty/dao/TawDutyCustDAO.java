package com.boco.eoms.duty.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.dao.SaveSessionBeanDAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.duty.model.TawDutyCust;

public class TawDutyCustDAO extends DAO {
	public TawDutyCustDAO(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public List list(String userId, int flag) throws SQLException {
		ArrayList list = new ArrayList();
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TawDutyCust tawDutyCust = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT id,user_id, flag_id, flag_name, flag FROM taw_duty_cust where user_id = ? and flag = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, flag);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tawDutyCust = new TawDutyCust();
				populate(tawDutyCust, rs);
				list.add(tawDutyCust);
				tawDutyCust = null;
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

	public void insert(TawDutyCust tawDutyCust) throws SQLException {
		String sql;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			/*
			if (StaticMethod.getDbType().equals(StaticVariable.ORACLE)) {
				sql = "alter session set NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS'";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();
				conn.commit();
			}*/
			sql = "INSERT INTO taw_duty_cust (user_id,flag_id, flag_name, flag) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tawDutyCust.getUserId());
			pstmt.setInt(2, tawDutyCust.getFlagId());
			pstmt.setString(3, tawDutyCust.getFlagName());
			pstmt.setInt(4, tawDutyCust.getFlag());
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
		}
	}

	public void deleted(int id) throws SQLException {
		String sql;
		com.boco.eoms.db.util.BocoConnection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			sql = "delete from taw_duty_cust where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
		}
	}

	public void save(String strRoomId,String userId) throws SQLException {
		TawApparatusroomDAO tawApparatusroomDAO = new TawApparatusroomDAO(ds);
		TawDutyCust tawDutyCust = new TawDutyCust();
		try {
			StringTokenizer st = new StringTokenizer(strRoomId,",");
			while(st.hasMoreTokens()){
				int roomId = Integer.parseInt(st.nextToken());
				String roomName = tawApparatusroomDAO.getAppName(roomId);

				tawDutyCust.setFlag(1);
				tawDutyCust.setFlagId(roomId);
				tawDutyCust.setFlagName(roomName);
				tawDutyCust.setUserId(userId);
				insert(tawDutyCust);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {

		}
	}

	public String getSelRoomId(List list) throws SQLException {
		String selRoomId = "";
		TawDutyCust tawDutyCust = null;
		try {
			for (int i = 0; i < list.size(); i++) {
				tawDutyCust = (TawDutyCust) list.get(i);
				selRoomId += tawDutyCust.getFlagId()+",";
			}
			if(selRoomId.length()>1){
				selRoomId = selRoomId.substring(0,selRoomId.length()-1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return selRoomId;
	}

	 /**
	   * @see ������ţ���ʼʱ�䣬���ز�ѯ�Ű���һ�����vector��vector
	   * @param roomId
	   * @param time_from
	   * @param time_to
	   * @return
	   * @throws SQLException
	   */
	  public Vector getDutyCustVector(int roomId,String time_from)  throws SQLException {
	    Vector getVector = new Vector();
	    Vector getDutydate = new Vector();
	    Vector getStarttime = new Vector();
	    Vector getEndtime = new Vector();
	    Vector getDutymaster = new Vector();
	    Vector getDutyman = new Vector();
	    Vector getRoomName =new Vector();
	    String strDutydate = "";
	    Vector vectDutydate = new Vector();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    PreparedStatement pstmt1 = null;
	    ResultSet rs = null;
	    String sql = "";

	    try {
	      conn = ds.getConnection();

	      String sql1 = "select a.id,a.dutydate,a.starttime_defined,a.endtime_defined, "+
	      		"a.dutymaster,b.user_name,b.mobile,a.room_id "+
	      		"from taw_rm_assignwork a ,taw_rm_user b   "+
	      		"WHERE a.dutymaster = b.user_id and  a.room_id = ? and a.dutydate = ? "+
	      		"order by a.starttime_defined";
	      pstmt = conn.prepareStatement(sql1);
	      pstmt.setInt(1, roomId);
	      pstmt.setString(2,time_from);
	      rs = pstmt.executeQuery();
	      while(rs.next()) {
	        strDutydate = rs.getString(2).trim();
	        vectDutydate = StaticMethod.getVector (strDutydate," ");
	        strDutydate = String.valueOf(vectDutydate.elementAt(0)).trim();
	        getDutydate.add(strDutydate);
	        getStarttime.add(StaticMethod.StringReplace(rs.getString(3).trim(),".0",""));
	        getEndtime.add(StaticMethod.StringReplace(rs.getString(4).trim(),".0",""));
	        String userName = StaticMethod.dbNull2String(rs.getString(6));
	        String mobile = StaticMethod.dbNull2String(rs.getString(7));
	        /*
	        if(mobile.length()>5){
	        	getDutymaster.add(userName+"$"+mobile);

	        }else{
	        	getDutymaster.add(userName+"$null");
	        }*/
	        getDutymaster.add(userName+"("+mobile+")");
	        getDutyman.add(getDutymanCust(rs.getInt(1)));
	      }
	      rs.close();
	      pstmt.close() ;
	      } catch (SQLException e) {
	      rs.close();
	      pstmt.close() ;
	   //rollback(conn); liquan modify
	      e.printStackTrace();
	      } finally {
	        conn.close() ;
	      }

	     TawApparatusroomDAO tawApparatusroomDAO = new TawApparatusroomDAO(ds);
	      String roomName = tawApparatusroomDAO.getAppName(roomId);
	    	getRoomName.add(roomName);

	    getVector.add(getDutydate) ;
	    getVector.add(getStarttime) ;
	    getVector.add(getEndtime) ;
	    getVector.add(getDutymaster) ;
	    getVector.add(getDutyman) ;
	    getVector.add(getRoomName) ;

	    //null
	    rs=null;
	    getDutydate=null;
	    getStarttime=null;
	    getEndtime=null;
	    getDutymaster=null;
	    getDutyman=null;
	    strDutydate=null;
	    vectDutydate=null;
	    getRoomName = null;
	    return getVector;
	  }

	  /**
	   * @see ������ţ���ʼʱ�䣬���ز�ѯ�Ű���һ�����vector��vector
	   * @param roomId
	   * @param time_from
	   * @param time_to
	   * @return
	   * @throws SQLException
	   */
//	  public Vector getDutyCustVectorBAK(String strRoomId,String time_from)  throws SQLException {
//	    Vector getVector = new Vector();
//	    Vector getDutydate = new Vector();
//	    Vector getStarttime = new Vector();
//	    Vector getEndtime = new Vector();
//	    Vector getDutymaster = new Vector();
//	    Vector getDutyman = new Vector();
//	    Vector getRoomName =new Vector();
//	    String strDutydate = "";
//	    Vector vectDutydate = new Vector();
//	    Connection conn = null;
//	    PreparedStatement pstmt = null;
//	    PreparedStatement pstmt1 = null;
//	    ResultSet rs = null;
//	    String sql = "";
//
//	    try {
//	      conn = ds.getConnection();
//
//	      String sql1 = "select a.id,a.dutydate,a.starttime_defined,a.endtime_defined, "+
//	      		"a.dutymaster,b.user_name,b.mobile,a.room_id "+
//	      		"from taw_rm_assignwork a ,taw_rm_user b   "+
//	      		"WHERE a.dutymaster = b.user_id and  a.room_id in("+strRoomId+") and a.dutydate = ? "+
//	      		"order by a.room_id,a.starttime_defined";
//	      pstmt = conn.prepareStatement(sql1);
//	      pstmt.setString(1,time_from);
//	      rs = pstmt.executeQuery();
//	      while(rs.next()) {
//	        strDutydate = rs.getString(2).trim();
//	        vectDutydate = StaticMethod.getVector (strDutydate," ");
//	        strDutydate = String.valueOf(vectDutydate.elementAt(0)).trim();
//	        getDutydate.add(strDutydate);
//	        getStarttime.add(StaticMethod.StringReplace(rs.getString(3).trim(),".0",""));
//	        getEndtime.add(StaticMethod.StringReplace(rs.getString(4).trim(),".0",""));
//	        String userName = StaticMethod.dbNull2String(rs.getString(6));
//	        String mobile = StaticMethod.dbNull2String(rs.getString(7));
//	        /*
//	        if(mobile.length()>5){
//	        	getDutymaster.add(userName+"$"+mobile);
//
//	        }else{
//	        	getDutymaster.add(userName+"$null");
//	        }*/
//	        getDutymaster.add(userName+"("+mobile+")");
//	        getDutyman.add(getDutymanCust(rs.getInt(1)));
//	      }
//	      rs.close();
//	      pstmt.close() ;
//	      } catch (SQLException e) {
//	      rs.close();
//	      pstmt.close() ;
//	     //rollback(conn); liquan modify
//	      e.printStackTrace();
//	      } finally {
//	        conn.close() ;
//	      }
//	      if(strRoomId.length()>1){
//	     TawApparatusroomDAO tawApparatusroomDAO = new TawApparatusroomDAO(ds);
//	      StringTokenizer st = new StringTokenizer(strRoomId,",");
//	      while(st.hasMoreTokens()){
//	    	int roomId = Integer.parseInt(st.nextToken());
//	    	String roomName = tawApparatusroomDAO.getAppName(roomId);
//	    	getRoomName.add(roomName);
//	      }
//	      }
//	    getVector.add(getDutydate) ;
//	    getVector.add(getStarttime) ;
//	    getVector.add(getEndtime) ;
//	    getVector.add(getDutymaster) ;
//	    getVector.add(getDutyman) ;
//	    getVector.add(getRoomName) ;
//
//	    //null
//	    rs=null;
//	    getDutydate=null;
//	    getStarttime=null;
//	    getEndtime=null;
//	    getDutymaster=null;
//	    getDutyman=null;
//	    strDutydate=null;
//	    vectDutydate=null;
//	    getRoomName = null;
//	    return getVector;
//	  }
	  /**
	   * @see ������ţ���ʼʱ�䣬���ز�ѯ�Ű���һ�����vector��vector
	   * @param roomId
	   * @param time_from
	   * @param time_to
	   * @return
	   * @throws SQLException
	   */
	  public Vector getDutyCustVector(String strRoomId,String time_from)  throws SQLException {
	    Vector strVector = new Vector();

	    try {
	    	if(strRoomId.length()>1){
	    		StringTokenizer st = new StringTokenizer(strRoomId,",");
	    		while(st.hasMoreTokens()){
	    			int roomId = Integer.parseInt(st.nextToken());
	    			Vector tVector = getDutyCustVector(roomId,time_from);
	    			strVector.add(tVector);
	    		}
	    	}
	    } catch (SQLException e) {
	          e.printStackTrace();
	      } finally {

	      }

	         return strVector;
	  }
	  public String getDutymanCust(int workserial) throws SQLException {
		    String DutymanStr = "";
		    com.boco.eoms.db.util.BocoConnection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
//		    TawRmUserDAO tawRmUserDAO=null;
//		    tawRmUserDAO = new TawRmUserDAO(ds);
		    try {
		      conn = ds.getConnection();
		      String sql = "SELECT a.dutyman,b.user_name,b.mobile "+
		      			"FROM taw_rm_assign_sub a,taw_rm_user b "+
		      			"WHERE a.dutyman = b.user_id and a.workserial=?";
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setInt(1, workserial);
		      rs = pstmt.executeQuery();
		      while (rs.next()) {
		        String dutyman = StaticMethod.dbNull2String(rs.getString(1));
		        String userName = StaticMethod.dbNull2String(rs.getString(2));
		        String mobile = StaticMethod.dbNull2String(rs.getString(3));
		        if (!dutyman.equals("")){
		        	/*
		        	if(!mobile.equals("")){
		        		DutymanStr += userName+"("+mobile+")";
		        	}else{
		        		DutymanStr += userName+"()";
		        	}*/
		        	DutymanStr += userName+"("+mobile+")  ";
		        }
		      }
		      close(rs);
		      close(pstmt);
		    } catch (SQLException e) {
		      close(rs);
		      close(pstmt);
		     //rollback(conn); liquan modify
		      e.printStackTrace();
		    } finally {
		      close(conn);
//		      tawRmUserDAO=null;
		    }
		    return DutymanStr;
		  }
	  /**
	   * @see ����USER_ID ��ȡ�������õ�ROOM_ID
	   * @param userId
	   * @return
	   * @throws SQLException
	   */
	  public String getDuryCustRoomId(String userId, int flag) throws SQLException {
			ArrayList list = new ArrayList();
			com.boco.eoms.db.util.BocoConnection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String strRoomId = "";
			try {
				conn = ds.getConnection();
				String sql = "SELECT flag_id FROM taw_duty_cust where user_id = ? and flag = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				pstmt.setInt(2, flag);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					strRoomId += rs.getString(1)+",";
				}
				close(rs);
				close(pstmt);
				if(strRoomId.length()>0){
					strRoomId = strRoomId.substring(0,strRoomId.length()-1);
				}
			} catch (SQLException e) {
				close(rs);
				close(pstmt);
				e.printStackTrace();
			} finally {
				close(conn);
			}
			return strRoomId;
		}
}
