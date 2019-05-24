package com.boco.eoms.message.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.message.model.SmsApply;
import com.boco.eoms.message.model.SmsLog;
import com.boco.eoms.message.model.SmsMonitor;
import com.boco.eoms.message.model.SmsService;

public class MsgJdbc extends BaseDAO {
	private static MsgJdbc instance = null;

	public static MsgJdbc getInstance() {
		if (instance == null) {
			instance = init();
		}
		return instance;
	}

	private static MsgJdbc init() {

		instance = new MsgJdbc();

		return instance;
	}

	public static TawSystemUser getUserByuserid(Connection connection,
			String userid) {
		TawSystemUser user = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "  select * from taw_system_user where userid ='"
					+ userid + "'";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new TawSystemUser();
				populate(user, rs);
				user = null;
			}
			connection.close();
			pstmt = null;
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static List getUserBydeptids(Connection connection, String deptid) {
		TawSystemUser user = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List list = new ArrayList();
		try {
			String sql = " select userid from Taw_System_User systemuser where systemuser.deptid='"
					+ deptid
					+ "' and systemuser.userid !='admin' order by username";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new TawSystemUser();
				populate(user, rs);
				list.add(user);
			}
			connection.close();
			pstmt = null;
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List getUserbySubRoleid(Connection connection,
			String subRoleid) {
		TawSystemUser user = null;
		List userList = new ArrayList();
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			String hql = " select user.* from Taw_System_User user,Taw_System_UserRefRole refrole where refrole.subRoleid='"
					+ subRoleid + "' and user.userid=refrole.userid";
			pstmt = connection.prepareStatement(hql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new TawSystemUser();
				populate(user, rs);
				userList.add(user);
			}
			connection.close();
			pstmt = null;
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public static SmsService getSmsService(Connection connection,
			String subRoleid) {
		SmsService smsService = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String hql = " select * from sms_service where id = '" + subRoleid
					+ "'";
			pstmt = connection.prepareStatement(hql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				smsService = new SmsService();
				populate(smsService, rs);
			}
			connection.close();
			pstmt = null;
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return smsService;
	}

	public static void saveSmsMonitor(Connection connection, SmsMonitor monitor) {

		PreparedStatement pstmt = null;
		try {

			String sql = "INSERT INTO sms_monitor(applyId,buizid,content,dispatchTime,mobile,receiverId,isSendImediat,serviceId,regetData,deleted)"
					+ " Values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, monitor.getApplyId());
			pstmt.setString(2, monitor.getBuizid());
			pstmt.setString(3, monitor.getContent());
			pstmt.setString(4, monitor.getDispatchTime().toString());
			pstmt.setString(5, monitor.getMobile());
			pstmt.setString(6, monitor.getReceiverId());
			pstmt.setString(7, monitor.getIsSendImediat());
			pstmt.setString(8, monitor.getServiceId());
			pstmt.setString(9, monitor.getRegetData());
			pstmt.setString(10, monitor.getDeleted());

			pstmt.executeUpdate();
			connection.commit();
			pstmt = null;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (connection != null)
				connection = null;
		}

	}

	public static void saveSmsLog(Connection connection, SmsLog smsLog) {
		PreparedStatement pstmt = null;
		try {

			String sql = "INSERT INTO sms_log (applyId,buizid,content,dispatchTime,mobile,receiverId,isSendImediat,serviceId,regetData,msgType,email,deleted)"
					+ " Values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, smsLog.getApplyId());
			pstmt.setString(2, smsLog.getBuizid());
			pstmt.setString(3, smsLog.getContent());
			pstmt.setString(4, smsLog.getDispatchTime().toString());
			pstmt.setString(5, smsLog.getMobile());
			pstmt.setString(6, smsLog.getReceiverId());
			pstmt.setString(7, smsLog.getIsSendImediat());
			pstmt.setString(8, smsLog.getServiceId());
			pstmt.setString(9, smsLog.getRegetData());
			pstmt.setString(10, smsLog.getMsgType());
			pstmt.setString(11, smsLog.getEmail());
			pstmt.setString(12, smsLog.getDeleted());

			pstmt.executeUpdate();
			connection.commit();
			pstmt = null;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (connection != null)
				connection = null;
		}

	}

	public static SmsApply getSimpleApply(Connection connection,
			String serviceId, String receiverId, String deleted) {
		SmsApply apply = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String hql = " select * from Sms_Apply where  serviceId='"
					+ serviceId + "' and receiverId='" + receiverId
					+ "' and deleted='" + deleted + "'";
			pstmt = connection.prepareStatement(hql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				apply = new SmsApply();
				populate(apply, rs);
			}
			connection.close();
			pstmt = null;
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apply;
	}

	public static List getSmsMonitor(Connection connection, String serviceId,
			String id) {
		List list = new ArrayList();
		SmsMonitor smsMonitor = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String hql = " select * from  Sms_Monitor where serviceId='"
					+ serviceId + "' and buizid='" + id + "'";
			pstmt = connection.prepareStatement(hql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				smsMonitor = new SmsMonitor();
				populate(smsMonitor, rs);
				list.add(smsMonitor);
			}
			connection.close();
			pstmt = null;
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean closeMsg(Connection connection, String id) {
		boolean bool = false;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			sql = "DELETE FROM Sms_Monitor WHERE id=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			close(pstmt);
			connection.commit();
			connection.close();
			bool = true;
		} catch (SQLException e) {
			close(pstmt);
			e.printStackTrace();
		} finally {
			sql = null;
		}
		return bool;
	}
}
