package com.boco.eoms.km.expert.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;

public interface KmExpertTreeDao extends Dao {

	public List getUserBydeptids(String deptid);

	public List getUserBydeptidsNoSelf(String deptid, String userid);
	
	public List getUserByKmExpertBasicField(final String fieldName, final String value);
}
