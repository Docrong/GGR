package com.boco.eoms.duty.dao.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.common.util.StaticMethod;

public class TawRmReplaceDaoJDBC extends BaseDaoJdbc {
	private TawRmReplaceDaoJDBC() {

	}

	private static TawRmReplaceDaoJDBC instance = null;

	public static TawRmReplaceDaoJDBC getInstance() {
		if (instance == null) {
			instance = init();
		}
		return instance;
	}

	private static TawRmReplaceDaoJDBC init() {

		instance = new TawRmReplaceDaoJDBC();

		return instance;
	}

	public Vector getreplaceApplyVector(int roomId, String user_id,
			String startdate, String enddate, String flag) throws SQLException {
		Vector getVector = new Vector();
		Vector getWorkserial = new Vector();
		Vector getStarttime = new Vector();
		Vector getDutyman = new Vector();
		Vector getUsername = new Vector();
		List list = null;
		String sql = "";
		try {
			sql = "select a.id,a.starttime_defined,s.dutyman,u.username from taw_rm_assignwork a,taw_rm_assign_sub s,"
					+ "taw_system_user u where a.room_id = '"
					+ roomId
					+ "' and a.id = s.workserial and s.dutyman = '"
					+ user_id
					+ "' and a.dutydate >='"
					+ startdate
					+ "' and a.dutydate <='"
					+ enddate + "'" + " and s.dutyman = u.userid  and a.id not in (select r.workserial from taw_rm_replace r where r.flag in (0,1))" ;

			list = getJdbcTemplate().queryForList(sql.toString());
			System.out.println(sql);
			for (Iterator it = list.iterator(); it.hasNext();) {
				Map mapObj = (Map) it.next();
				getWorkserial.add(StaticMethod.nullObject2String(mapObj
						.get("id")));
				getStarttime.add(StaticMethod.StringReplace(StaticMethod
						.nullObject2String(mapObj.get("starttime_defined")),
						".0", ""));
				getDutyman.add(StaticMethod.nullObject2String(mapObj
						.get("dutyman")));
				getUsername.add(StaticMethod.nullObject2String(mapObj
						.get("username")));
			}
		} catch (Exception ex) {

			return new Vector();

		} finally {
			sql = null;
		}
		getVector.add(getWorkserial);
		getVector.add(getStarttime);
		getVector.add(getDutyman);
		getVector.add(getUsername);
		return getVector;
	}

	public List getreplaceApplyVector(int roomId, String user_id,
			String recever, String startdate, String enddate, String flag)
			throws SQLException {

		List list = new ArrayList();
		String sql = "";
		try {

			sql = "select a.id,a.starttime_defined,s.dutyman,u.username from taw_rm_assignwork a,"
					+ "taw_rm_assign_sub s,taw_system_user u where a.room_id = '"
					+ roomId
					+ "' and a.id = s.workserial and "
					+ "s.workserial in (select distinct workserial from taw_rm_assign_sub where dutyman = '"
					+ user_id
					+ "')  and s.dutyman = '"
					+ recever
					+ "' and a.dutydate >='"
					+ startdate
					+ "' and a.dutydate <='"
					+ enddate + "'" + "and s.dutyman = u.userid";
			System.out.println(sql);
			list = getJdbcTemplate().queryForList(sql.toString());

		} catch (Exception ex) {

			return new ArrayList();

		} finally {
			sql = null;
		}

		return list;
	}

	public boolean isManager(String workid, String userid) {
		boolean bool = false;
		List list = new ArrayList();
		String sql = "";
		try {
			sql = "select * from taw_rm_assignwork where id='" + workid
					+ "' and dutymaster = '" + userid + "'";
			list = getJdbcTemplate().queryForList(sql.toString());
			if (list.size() > 0) {
				bool = true;
			} else {
				bool = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	public boolean updateAssignSub(String userid, String workid, String recevier) {
		try {
			getJdbcTemplate()
					.update(
							"UPDATE taw_rm_assign_sub SET dutyman=? WHERE workserial=? and dutyman=?",
							new Object[] { recevier, workid, userid });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateAssign(String userid, String workid, String recevier) {
		try {
			getJdbcTemplate()
					.update(
							"UPDATE taw_rm_assignwork SET dutymaster=? WHERE id=? and dutymaster=?",
							new Object[] { recevier, workid, userid });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
