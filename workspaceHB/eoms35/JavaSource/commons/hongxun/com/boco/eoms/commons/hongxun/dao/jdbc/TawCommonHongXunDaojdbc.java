package com.boco.eoms.commons.hongxun.dao.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.hongxun.dao.TawCommonHongXunDao;

public class TawCommonHongXunDaojdbc extends BaseDaoJdbc implements
		TawCommonHongXunDao {

	public void insertRedUser(String com_name, String xiaozhu_name,
			String name, String tel, String zhize, String remark) {
		if (name == null) {
			name = "";
		}
		if (tel == null) {
			tel = "";
		}
		if (zhize == null) {
			zhize = "";
		}
		if (remark == null) {
			remark = "";
		}
		String sql = "";
		sql = "insert into hongxun (com_name,xiaozu_name,name,tel,zhize,remark) values ('"
				+ com_name.trim()
				+ "','"
				+ xiaozhu_name.trim()
				+ "','"
				+ name.trim()
				+ "','"
				+ tel.trim()
				+ "','"
				+ zhize.trim()
				+ "','" + remark.trim() + "')";
		getJdbcTemplate().execute(sql);
	}

	public List getRedUser(String com_name, String xiaozhu_name, String name,
			String tel, String zhize, String remark) {

		List userDuty = new ArrayList();

		String sql = "SELECT * from hongxun where 1=1";
		if (!com_name.equals("")) {
			sql += " and com_name='" + com_name.trim() + "'";
		}
		if (!xiaozhu_name.equals("")) {
			sql += " and xiaozu_name='" + xiaozhu_name.trim() + "'";
		}
		if (!name.equals("")) {
			sql += " and name='" + name.trim() + "'";
		}
		if (!tel.equals("")) {
			sql += " and tel='" + tel.trim() + "'";
		}
		if (!zhize.equals("")) {
			sql += " and zhize='" + zhize.trim() + "'";
		}
		if (!remark.equals("")) {
			sql += " and remark='" + remark.trim() + "'";
		}
		sql += " order by com_name";
		userDuty = getJdbcTemplate().queryForList(sql);

		return userDuty;
	}

	public List getOneRedUser(String id) {

		List userDuty = new ArrayList();

		String sql = "SELECT * from hongxun where id=" + id.trim();

		userDuty = getJdbcTemplate().queryForList(sql);

		return userDuty;
	}

	public void redDel(String id) {

		String sql = "delete from hongxun where id='" + id.trim() + "'";

		getJdbcTemplate().execute(sql);

	}

	public void redDoUpdate(String id, String com_name, String xiaozu_name,
			String name, String tel, String zhize, String remark) {

		String sql = "UPDATE hongxun SET com_name='" + com_name.trim()
				+ "',xiaozu_name='" + xiaozu_name.trim() + "',name='"
				+ name.trim() + "',tel='" + tel.trim() + "',zhize='"
				+ zhize.trim() + "',remark='" + remark.trim() + "' where id="
				+ id.trim();

		getJdbcTemplate().update(sql);
	}

	public List getTwoRedUser(String com_name, String xiaozhu_name,
			String name, String tel, String zhize, String remark, String model) {

		List userDuty = new ArrayList();

		List List = new ArrayList();
		List List1 = new ArrayList();

		String sql = "SELECT * from hongxun where 1=1";
		String sql1 = "SELECT * from hongxun where 1=1";
		if (!com_name.equals("")) {
			sql += " and com_name in (" + com_name.trim() + ")";
		}
		if (!xiaozhu_name.equals("")) {
			sql += " and xiaozu_name='" + xiaozhu_name.trim() + "'";
		}
		if (!name.equals("")) {
			sql += " and name='" + name.trim() + "'";
		}
		if (!tel.equals("")) {
			sql += " and tel='" + tel.trim() + "'";
		}
		if (!zhize.equals("")) {
			sql += " and zhize='" + zhize.trim() + "'";
		}
		if (!remark.equals("")) {
			sql += " and remark='" + remark.trim() + "'";
		}
		sql += " order by com_name";

		sql1 += " and com_name not in (" + com_name.trim()
				+ ") order by com_name";
		List = getJdbcTemplate().queryForList(sql);
		Iterator _objIterator = List.iterator();
		while (_objIterator.hasNext()) {
			List userTemp = new ArrayList();
			Map _objMap = (Map) _objIterator.next();
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("id")));
			userTemp.add(StaticMethod
					.nullObject2String(_objMap.get("com_name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap
					.get("xiaozu_name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("tel")));
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("zhize")));
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("remark")));
			userDuty.add(userTemp);
		}
		List1 = getJdbcTemplate().queryForList(sql1);
		Iterator _objIterator1 = List1.iterator();
		while (_objIterator1.hasNext()) {
			List userTemp = new ArrayList();
			Map _objMap = (Map) _objIterator1.next();
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("id")));
			userTemp.add(StaticMethod
					.nullObject2String(_objMap.get("com_name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap
					.get("xiaozu_name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("tel")));
			userTemp.add(model);
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("remark")));
			userDuty.add(userTemp);
		}

		return userDuty;
	}

	public List getThreeRedUser(String com_name, String xiaozhu_name,
			String name, String tel, String zhize, String remark, String model) {
		List list = new ArrayList();

		List userDuty = new ArrayList();

		String sql = "SELECT * from hongxun where 1=1";
		if (!com_name.equals("")) {
			sql += " and com_name='" + com_name.trim() + "'";
		}
		if (!xiaozhu_name.equals("")) {
			sql += " and xiaozu_name='" + xiaozhu_name.trim() + "'";
		}
		if (!name.equals("")) {
			sql += " and name='" + name.trim() + "'";
		}
		if (!tel.equals("")) {
			sql += " and tel='" + tel.trim() + "'";
		}
		if (!zhize.equals("")) {
			sql += " and zhize='" + zhize.trim() + "'";
		}
		if (!remark.equals("")) {
			sql += " and remark='" + remark.trim() + "'";
		}
		sql += " order by com_name";
		list = getJdbcTemplate().queryForList(sql);

		Iterator _objIterator = list.iterator();
		while (_objIterator.hasNext()) {
			List userTemp = new ArrayList();
			Map _objMap = (Map) _objIterator.next();
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("id")));
			userTemp.add(StaticMethod
					.nullObject2String(_objMap.get("com_name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap
					.get("xiaozu_name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("name")));
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("tel")));
			userTemp.add(model);
			userTemp.add(StaticMethod.nullObject2String(_objMap.get("remark")));
			userDuty.add(userTemp);
		}

		return userDuty;
	}

	public void insertCom(String com_name) {

		String sql = "";
		sql = "insert into com_name (com_name) values ('" + com_name.trim()
				+ "')";
		getJdbcTemplate().execute(sql);
	}

	public void insertXiaozu(String xiaozu_name) {

		String sql = "";
		sql = "insert into xiaozu_name (xiaozu_name) values ('"
				+ xiaozu_name.trim() + "')";
		getJdbcTemplate().execute(sql);
	}

	public List getComList() {

		List userDuty = new ArrayList();

		String sql = "SELECT * from com_name ";

		sql += " order by com_name";
		userDuty = getJdbcTemplate().queryForList(sql);

		return userDuty;
	}

	public List getXiaozuList() {

		List userDuty = new ArrayList();

		String sql = "SELECT * from xiaozu_name ";

		sql += " order by xiaozu_name";
		userDuty = getJdbcTemplate().queryForList(sql);

		return userDuty;
	}

	public List getComAll() {
		List list = new ArrayList();

		List userDuty = new ArrayList();

		String sql = "SELECT * from com_name ";

		sql += " order by com_name";
		list = getJdbcTemplate().queryForList(sql);

		Iterator _objIterator = list.iterator();
		while (_objIterator.hasNext()) {

			Map _objMap = (Map) _objIterator.next();

			userDuty.add(StaticMethod
					.nullObject2String(_objMap.get("com_name")));
		}

		return userDuty;
	}

	public List getXiaozuAll() {
		List list = new ArrayList();

		List userDuty = new ArrayList();

		String sql = "SELECT * from xiaozu_name ";

		sql += " order by xiaozu_name";
		list = getJdbcTemplate().queryForList(sql);

		Iterator _objIterator = list.iterator();
		while (_objIterator.hasNext()) {

			Map _objMap = (Map) _objIterator.next();

			userDuty.add(StaticMethod
					.nullObject2String(_objMap.get("xiaozu_name")));
		}

		return userDuty;
	}
	
	public String getDictName(String dictId) {
		List list = new ArrayList();

		String userDuty = "";

		String sql = "select * from taw_system_dicttype where dictid='"+dictId.trim()+"'";

		//sql += " order by com_name";
		list = getJdbcTemplate().queryForList(sql);

		Iterator _objIterator = list.iterator();
		while (_objIterator.hasNext()) {

			Map _objMap = (Map) _objIterator.next();

			userDuty=(String)_objMap.get("dictname");
		}

		return userDuty;
	}
}
