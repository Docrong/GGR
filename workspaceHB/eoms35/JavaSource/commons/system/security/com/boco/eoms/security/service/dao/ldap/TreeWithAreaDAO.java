package com.boco.eoms.security.service.dao.ldap;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.security.service.dao.ITreeWithAreaDAO;
import com.boco.eoms.security.service.model.TreeWithArea;

public class TreeWithAreaDAO extends BaseDaoJdbc implements ITreeWithAreaDAO {
	public void insert(TreeWithArea treeWithArea) {
		String sql = "insert into tree_with_area (area_id,area_name,notes,parentarea_id,deleted) "
				+ "values('"
				+ treeWithArea.getAreaId()
				+ "','"
				+ treeWithArea.getAreaName()
				+ "','"
				+ treeWithArea.getNotes()
				+ "','"
				+ treeWithArea.getParentAreaId()
				+ "','"
				+ treeWithArea.getDeleted() + "')";
		getJdbcTemplate().execute(sql);
	}

	public void logicDelete(String areaId) {
		String sql = " update tree_with_area set deleted=1 where area_id = '"
				+ areaId + "'";
		getJdbcTemplate().execute(sql);

	}

	public void physicsDelete(String areaId) {
		String sql = " delete from tree_with_area where area_id = '" + areaId
				+ "'";
		getJdbcTemplate().execute(sql);

	}

	public List selectByAreaId(String areaId, int deleted) {
		List tempList = null;
		List list = new ArrayList();
		String sql = " select area_id,area_name,notes,parentarea_id,deleted from tree_with_area "
				+ "where area_id='"
				+ areaId
				+ "' "
				+ " and deleted='"
				+ deleted + "'";
		tempList = getJdbcTemplate().queryForList(sql);

		if (tempList != null) {

			for (int i = 0; i < tempList.size(); i++) {
				TreeWithArea result = new TreeWithArea();
				Object[] obj = (Object[]) tempList.get(i);

				result.setAreaId(StaticMethod.nullObject2String(obj[0]));
				result.setAreaName(StaticMethod.nullObject2String(obj[1]));
				result.setNotes(StaticMethod.nullObject2String(obj[2]));
				result.setParentAreaId(StaticMethod.nullObject2String(obj[3]));
				result.setDeleted(StaticMethod.nullObject2int(obj[4]));

				list.add(result);
			}

		}
		return list;
	}

	public List selectByParentareaid(String parentareaid, int deleted) {
		List list = new ArrayList();
		List tempList = null;
		String sql = " select area_id,area_name,notes,parentarea_id,deleted from tree_with_area "
				+ "where parentarea_id='"
				+ parentareaid
				+ "' "
				+ " and deleted='" + deleted + "'";
		tempList = getJdbcTemplate().queryForList(sql);

		if (tempList != null) {

			for (int i = 0; i < tempList.size(); i++) {
				TreeWithArea result = new TreeWithArea();
				Object[] obj = (Object[]) tempList.get(i);

				result.setAreaId(StaticMethod.nullObject2String(obj[0]));
				result.setAreaName(StaticMethod.nullObject2String(obj[1]));
				result.setNotes(StaticMethod.nullObject2String(obj[2]));
				result.setParentAreaId(StaticMethod.nullObject2String(obj[3]));
				result.setDeleted(StaticMethod.nullObject2int(obj[4]));

				list.add(result);
			}

		}
		return list;
	}

	public List selectByNotes(String notes, int deleted) {
		List list = new ArrayList();
		List tempList = null;
		String sql = " select area_id,area_name,notes,parentarea_id,deleted from tree_with_area "
				+ "where notes='"
				+ notes
				+ "' "
				+ " and deleted='"
				+ deleted
				+ "'";
		tempList = getJdbcTemplate().queryForList(sql);

		if (tempList != null) {

			for (int i = 0; i < tempList.size(); i++) {
				TreeWithArea result = new TreeWithArea();
				Object[] obj = (Object[]) tempList.get(i);

				result.setAreaId(StaticMethod.nullObject2String(obj[0]));
				result.setAreaName(StaticMethod.nullObject2String(obj[1]));
				result.setNotes(StaticMethod.nullObject2String(obj[2]));
				result.setParentAreaId(StaticMethod.nullObject2String(obj[3]));
				result.setDeleted(StaticMethod.nullObject2int(obj[4]));

				list.add(result);
			}

		}
		return list;
	}

	public List selectByAreaName(String areaName, int deleted) {
		List list = new ArrayList();
		List tempList = null;
		String sql = " select area_id,area_name,notes,parentarea_id,deleted from tree_with_area "
				+ "where area_Name='"
				+ areaName
				+ "' "
				+ " and deleted='"
				+ deleted + "'";
		tempList = getJdbcTemplate().queryForList(sql);

		if (tempList != null) {

			for (int i = 0; i < tempList.size(); i++) {
				TreeWithArea result = new TreeWithArea();
				Object[] obj = (Object[]) tempList.get(i);

				result.setAreaId(StaticMethod.nullObject2String(obj[0]));
				result.setAreaName(StaticMethod.nullObject2String(obj[1]));
				result.setNotes(StaticMethod.nullObject2String(obj[2]));
				result.setParentAreaId(StaticMethod.nullObject2String(obj[3]));
				result.setDeleted(StaticMethod.nullObject2int(obj[4]));

				list.add(result);
			}

		}
		return list;

	}
	
	public List selectAllArea(int deleted) {
		List list = new ArrayList();
		List tempList = null;
		String sql = " select area_id,area_name,notes,parentarea_id,deleted from tree_with_area "
				+ "where  deleted='"
				+ deleted + "'";
		tempList = getJdbcTemplate().queryForList(sql);

		if (tempList != null) {

			for (int i = 0; i < tempList.size(); i++) {
				TreeWithArea result = new TreeWithArea();
				Object[] obj = (Object[]) tempList.get(i);

				result.setAreaId(StaticMethod.nullObject2String(obj[0]));
				result.setAreaName(StaticMethod.nullObject2String(obj[1]));
				result.setNotes(StaticMethod.nullObject2String(obj[2]));
				result.setParentAreaId(StaticMethod.nullObject2String(obj[3]));
				result.setDeleted(StaticMethod.nullObject2int(obj[4]));

				list.add(result);
			}

		}
		return list;

	}
	
	public String getMaxSubAreaid(String parentAreaId) {
        String maxSubAreatid = parentAreaId+"00";
		String sql ="select MAX(area_id) from tree_with_area where "+maxSubAreatid+"<=area_id and dept_id<="+maxSubAreatid+"+99 and parentarea_id = '"+parentAreaId+"'";
		List tempList = getJdbcTemplate().queryForList(sql);
		if (tempList != null) {
			Object[] obj=(Object[])tempList.get(0);
			maxSubAreatid = StaticMethod.nullObject2String(obj[0]);
		}
		return maxSubAreatid;
	}

}
