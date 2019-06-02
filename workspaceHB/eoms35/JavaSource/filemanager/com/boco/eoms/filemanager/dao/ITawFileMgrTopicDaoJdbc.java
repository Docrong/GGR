package com.boco.eoms.filemanager.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;

public interface ITawFileMgrTopicDaoJdbc extends Dao {

	/**
	 * 查询最大的部门ID
	 * 
	 * @param pardeptid
	 * @param len
	 * @param deleted
	 * @return
	 */
	public String getMaxDeptid(String pardeptid, int len, String deleted);

	/**
	 * 得到下一级子部门的部门信息
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecDepts(String pardeptid, String delid);

	/**
	 * 查询最大的部门ID
	 */
	public String getMaxLinkid(String parentLinkId, int len, String deleted);

}
