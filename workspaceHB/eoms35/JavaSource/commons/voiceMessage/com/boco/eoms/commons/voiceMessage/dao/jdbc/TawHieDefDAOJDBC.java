package com.boco.eoms.commons.voiceMessage.dao.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.base.util.StaticMethod;

import com.boco.eoms.commons.voiceMessage.dao.TawHieDefDAO;
import com.boco.eoms.commons.voiceMessage.webapp.form.TawHieApplyForm;

public class TawHieDefDAOJDBC extends BaseDaoJdbc implements TawHieDefDAO {
	public int getUserId() {
		int userId = 0;

		List userDuty = new ArrayList();
		List List = new ArrayList();
		String sql = "SELECT max(userid) userid FROM t_user_info";
		List = getJdbcTemplate().queryForList(sql);
		Iterator _objIterator = List.iterator();
		while (_objIterator.hasNext()) {

			Map _objMap = (Map) _objIterator.next();
			userDuty.add(StaticMethod.nullObject2String(_objMap.get("userid")));

		}
		String userIds = userDuty.get(0).toString();
		if (!userIds.equals("")) {
			userId = Integer.parseInt(userIds);
		}

		userId += 1;

		return userId;

	}

	public List getAllUser(TawHieApplyForm form) {
		List userDuty = new ArrayList();
		List list = new ArrayList();
		String userId = null2String(form.getUserId());
		String userName = null2String(form.getUserName());
		String userTel = null2String(form.getUserTel());
		String userType = null2String(form.getUserType());
		String userCode = null2String(form.getUserCode());

		String sql = "SELECT * from t_user_info where 1=1";
		if (!userName.equals("")) {
			sql += " and userName like '" + userName.trim() + "%'";
		}
		if (!userTel.equals("")) {
			sql += " and userTel='" + userTel.trim() + "'";
		}
		if (!userCode.equals("")) {
			sql += " and user_code='" + userCode.trim() + "'";
		}
		if (!userId.equals("")) {
			int user = Integer.parseInt(userId);
			sql += " and userId=" + user + "";
		}
		if (!userType.equals("")) {
			int type = Integer.parseInt(userType);
			sql += " and user_Type=" + type + "";
		}

		sql += " order by userName";
		list = getJdbcTemplate().queryForList(sql);

		Iterator _objIterator = list.iterator();
		while (_objIterator.hasNext()) {
			List userTemp = new ArrayList();
			Map _objMap = (Map) _objIterator.next();
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("userId")));
			userTemp.add(StaticMethod
					.nullObject2String(_objMap.get("userName")));
			userTemp
					.add(StaticMethod.nullObject2String(_objMap.get("userTel")));
			userTemp.add(StaticMethod.nullObject2String(_objMap
					.get("user_Type")));
			userTemp.add(StaticMethod.nullObject2String(_objMap
					.get("user_code")));
			if (_objMap.get("user_Type").toString().equals("0")) {
				userTemp.add("维护调度人员");
			} else if (_objMap.get("user_Type").toString().equals("1")) {
				userTemp.add("维护工程师");
			} else if (_objMap.get("user_Type").toString().equals("2")) {
				userTemp.add("维护支持人员");
			} else if (_objMap.get("user_Type").toString().equals("3")) {
				userTemp.add("协查员");
			} else if (_objMap.get("user_Type").toString().equals("4")) {
				userTemp.add("协查请求人");
			} else if (_objMap.get("user_Type").toString().equals("5")) {
				userTemp.add("VIP客户");
			}

			userDuty.add(userTemp);
		}

		return userDuty;
	}

	public List getOneRedUser(String id) {

		List userDuty = new ArrayList();

		String sql = "SELECT * from t_user_info where userid=" + id.trim();

		userDuty = getJdbcTemplate().queryForList(sql);

		return userDuty;
	}

	public void redDel(String id) {

		String sql = "delete from t_user_info where userid=" + id.trim();

		getJdbcTemplate().execute(sql);

	}

	public void redDoUpdate(String id, String username, String usertel,
			String userType, String userCode) {

		String sql = "UPDATE t_user_info SET username='" + username.trim()
				+ "',usertel='" + usertel.trim() + "',user_type='"
				+ userType.trim() + "',user_code='" + userCode.trim()
				+ "' where userid=" + id.trim();

		getJdbcTemplate().update(sql);
	}

	public List getOrgName() {
		List list = new ArrayList();
		ArrayList listUser = new ArrayList();

		String sql = "SELECT username FROM t_user_info order by username";
		list = getJdbcTemplate().queryForList(sql);

		Iterator _objIterator = list.iterator();
		while (_objIterator.hasNext()) {

			Map _objMap = (Map) _objIterator.next();
			listUser.add(StaticMethod
					.nullObject2String(_objMap.get("userName")));
		}

		return listUser;
	}

	public List getConfName() {
		ArrayList list = new ArrayList();
		List listTem = new ArrayList();
		String sql = "SELECT conf_no FROM t_conference_info";
		listTem = getJdbcTemplate().queryForList(sql);

		Iterator _objIterator = listTem.iterator();
		while (_objIterator.hasNext()) {
			Map _objMap = (Map) _objIterator.next();
			list.add(new Integer(_objMap.get("conf_no").toString()));
		}

		return list;
	}

	public static String null2String(String s) {
		return s == null ? "" : s;
	}
}
