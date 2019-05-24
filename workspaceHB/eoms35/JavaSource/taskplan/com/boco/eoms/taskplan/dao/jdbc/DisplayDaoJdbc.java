package com.boco.eoms.taskplan.dao.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.taskplan.dao.DisplayDao;

public class DisplayDaoJdbc extends BaseDaoJdbc implements DisplayDao{

	public String getUserNameByuserid(String userid){
		String ret = "";

		List userDuty = new ArrayList();
		List list = new ArrayList();

			String sql = "select username from taw_system_user where userid='" + userid.trim()+"'";
			list = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = list.iterator();
				while (_objIterator.hasNext()) {
					
					Map _objMap = (Map) _objIterator.next();
					userDuty.add(StaticMethod
							.nullObject2String(_objMap.get("username")));
				}
				ret = userDuty.get(0).toString();

			
		return ret;
	}
	
	public String getDeptNameByuserid(String deptid){
		String ret = "";

		List userDuty = new ArrayList();
		List list = new ArrayList();
		
			String sql = "select deptname from taw_system_dept where deptid='" + deptid.trim()+"'";
			list = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = list.iterator();
				while (_objIterator.hasNext()) {
					
					Map _objMap = (Map) _objIterator.next();
					userDuty.add(StaticMethod
							.nullObject2String(_objMap.get("deptname")));
				}
				ret = userDuty.get(0).toString();

			
		return ret;
	}
	
	public String getDictNameBydictid(String dictid){
		String ret = "";

		List userDuty = new ArrayList();
		List list = new ArrayList();
	
			String sql = "select dictname from taw_system_dicttype where dictid='" + dictid.trim()+"'";
			list = getJdbcTemplate().queryForList(sql);
			 Iterator _objIterator = list.iterator();
				while (_objIterator.hasNext()) {
					
					Map _objMap = (Map) _objIterator.next();
					userDuty.add(StaticMethod
							.nullObject2String(_objMap.get("dictname")));
				}
				ret = userDuty.get(0).toString();

			
		return ret;
	}
}
