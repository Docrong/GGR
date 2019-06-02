package com.boco.eoms.sheet.tool.access.dao;

import com.boco.eoms.base.dao.Dao;

public interface ITawSheetAccessDaoJdbc extends Dao {

	 /*
     * 得到最大的地域ID
     */
	public String getMaxAccessId(String parareaid,int len);
}
