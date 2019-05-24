package com.boco.eoms.otherwise.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;

public interface ITawRmInoutRecordDaoJdbc extends Dao {

	/**
	 * 得到待入库记录
	 * @param userId
	 * @return List
	 */
	public List getInRecordList(String ids);
	
	/**
	 * 根据查询条件得到测试卡记录id列表
	 * @param whereStr
	 * @return List
	 */
	public List getIdList(String whereStr);
}
