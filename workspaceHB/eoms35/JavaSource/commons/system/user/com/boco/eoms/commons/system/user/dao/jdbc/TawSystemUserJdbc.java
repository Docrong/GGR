package com.boco.eoms.commons.system.user.dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.exolab.castor.util.Iterator;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

/**
 * panlong 2008 4:19:33 PM
 */
public class TawSystemUserJdbc extends BaseDaoJdbc {

	private TawSystemUserJdbc() {

	}

	private static TawSystemUserJdbc instance = null;

	public static TawSystemUserJdbc getInstance() {
		if (instance == null) {
			instance = init();
		}
		return instance;
	}

	private static TawSystemUserJdbc init() {

		instance = new TawSystemUserJdbc();

		return instance;
	}

	/**
	 * 通过一些用户 查询这些用户中属于某部门的用户
	 * 
	 * @param userids
	 * @param deptid
	 * @return String useid
	 */
	public List getUserBydeptidAndUserids(String userids, String deptid) {
		String sql = "  select userid from taw_system_user  where userid in("
				+ userids + ") and deptid='" + deptid + "'";
		List list = new ArrayList();
		list = (ArrayList) getJdbcTemplate().queryForMap(sql);
		if (list != null && list.size() > 0) {

			java.util.Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Map map = (Map) iter.next();
				String userid = map.get("userid").toString();
				list.add(userid);
			}
		}
		return list;
	}
	/**
	 * 通过一些用户 查询这些用户中属于某部门的用户
	 * 
	 * @param userids
	 * @param deptid
	 * @return String useid
	 */
	public int getUserExist(String userids) {
		String sql = "select count(*) from taw_system_user  where userid ='"
				+ userids + "'";
		List list = new ArrayList();
		int i  = (int) getJdbcTemplate().queryForInt(sql);
		int count = 0;
		if (list != null && list.size() > 0) {
			count=1;
		} 
		return count;
	}
}
