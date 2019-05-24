package com.boco.eoms.commons.system.area.dao;

import com.boco.eoms.base.dao.Dao;

/**
 *
 *panlong
 *下午03:58:05
 */
public interface TawSystemAreaDaoJdbc extends Dao {
    /*
     * 得到最大的地域ID
     */
	public String getMaxAreaId(String parareaid,int len);
}

