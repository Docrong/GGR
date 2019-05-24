package com.boco.eoms.filemanager.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.filemanager.dao.ITawFileMgrTopicDaoJdbc;

public class TawFileMgrTopicDaoJdbcImpl extends BaseDaoJdbc implements
		ITawFileMgrTopicDaoJdbc {

	/**
	 * 查询最大的部门ID
	 */
	public String getMaxDeptid(String pardeptid, int len, String deleted) {
		String sql = " select max(deptid) as deptid from taw_system_dept where deptid like '"
				+ pardeptid
				+ "%' and length(deptid)<='"
				+ len
				+ "' and deleted='" + deleted + "'";
		String maxdeptid = "";

		maxdeptid = String.valueOf(getJdbcTemplate().queryForMap(sql).get(
				"deptid"));
		return maxdeptid;
	}

	/**
	 * 查询最大的部门ID
	 */
	public String getMaxLinkid(String parentLinkId, int len, String deleted) {
		String sql = " select max(linkid) as linkid from taw_system_dept where linkid like '"
				+ parentLinkId
				+ "%' and length(linkid)<='"
				+ len
				+ "' and deleted='" + deleted + "'";
		String maxLinkId = "";

		Object linkid = getJdbcTemplate().queryForMap(sql).get("linkid");
		if (linkid != null) {
			maxLinkId = String.valueOf(linkid);
		} else {
			maxLinkId = "0";
		}
		return maxLinkId;
	}

	/**
	 * 得到下一级子部门的部门信息
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecDepts(String pardeptid, String delid) {

		String hql = " select * from taw_system_dept  where parentDeptid='"
				+ pardeptid + "'" + " and deleted='" + delid
				+ "' order by substr(deptname,'0','1')";
		List list = new ArrayList();
		list = getJdbcTemplate().queryForList(hql);
		return list;
	}
}
