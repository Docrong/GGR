package com.boco.eoms.km.expert.mgr;

import java.util.List;

public interface KmExpertTreeMgr {

	public List getUserBydeptidsNoSelf(String deptid, String userid);

	public List getUserBydeptids(String deptid);
	
	public List getUserByKmExpertBasicField(final String fieldName, final String value);
}
