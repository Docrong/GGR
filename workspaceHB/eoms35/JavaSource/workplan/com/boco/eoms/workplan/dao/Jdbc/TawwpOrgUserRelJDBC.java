package com.boco.eoms.workplan.dao.Jdbc;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.workplan.dao.ITawwpOrgUserRelJDBC;

public class TawwpOrgUserRelJDBC extends BaseDaoJdbc implements
		ITawwpOrgUserRelJDBC {

	private TawwpOrgUserRelJDBC() {
	}

	// 取出所有机房和人员的对应关系，存放到MAP中。
	public Map getRoomUserRel() {
		Map map = new HashMap();
		String sql = "select REGIONID,OBJECTID from taw_system_priv_region where ISAPP='1' and OBJECTTYPE='2' order by REGIONID";
		List list = getJdbcTemplate().queryForList(sql);
		String users = "";
		String roomId = "";
		for (int i = 0; i < list.size(); i++) {
			Map mapObj = (Map) list.get(i);
			roomId = StaticMethod.nullObject2String(mapObj.get("REGIONID"));
			if (i == list.size() - 1) {
				users += StaticMethod.nullObject2String(mapObj.get("OBJECTID"));
				map.put(roomId, users);
			} else {
				Map mapObjTemp = (Map) list.get(i + 1);
				String roomIdTemp = StaticMethod.nullObject2String(mapObjTemp
						.get("REGIONID"));
				if (roomIdTemp.equals(roomId)) {
					users += StaticMethod.nullObject2String(mapObj
							.get("OBJECTID"))
							+ ",";
				} else {
					if (!users.equals("")) {
						users = users
								+ StaticMethod.nullObject2String(mapObj
										.get("OBJECTID"));
						map.put(roomId, users);
						users = "";
					} else {
						users = StaticMethod.nullObject2String(mapObj
								.get("OBJECTID"));
						map.put(roomId, users);
						users = "";
					}
				}
			}
		}
		return map;
	}

	// 取出所有部门和人员的对应关系（不包含子部门，只是部门直属人员），存放到MAP中。
	public Map getDeptUserRel() {
		HashMap map = new HashMap();
		String sql = "select deptid,userid from taw_system_user order by deptid";
		List list = getJdbcTemplate().queryForList(sql);
		String users = "";
		for (int i = 0; i < list.size(); i++) {
			Map mapObj = (Map) list.get(i);
			String deptId = StaticMethod
					.nullObject2String(mapObj.get("deptid"));
			if (i == list.size() - 1) {
				users += StaticMethod.nullObject2String(mapObj.get("userid"))
						+ ",";
				users = users.substring(0, users.length() - 1);
				map.put(deptId, users);
			} else {
				Map mapObjTemp = (Map) list.get(i + 1);
				String deptIdTemp = StaticMethod.nullObject2String(mapObjTemp
						.get("deptid"));
				if (deptIdTemp.equals(deptId)) {
					users += StaticMethod.nullObject2String(mapObj
							.get("userid"))
							+ ",";
				} else {
					if (!users.equals("")) {
						users = users
								+ StaticMethod.nullObject2String(mapObj
										.get("OBJECTID"));
						map.put(deptId, users);
						users = new String();
					} else {
						users = StaticMethod.nullObject2String(mapObj
								.get("userid"));
						map.put(deptId, users);
						users = new String();
					}
				}
			}
		}
		return map;
	}
}
