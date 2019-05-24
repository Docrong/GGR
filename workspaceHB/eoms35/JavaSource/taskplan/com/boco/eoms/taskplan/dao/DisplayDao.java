package com.boco.eoms.taskplan.dao;

import com.boco.eoms.base.dao.Dao;

public interface DisplayDao extends Dao{
	
	public String getUserNameByuserid(String userid);
	public String getDeptNameByuserid(String deptid);
	public String getDictNameBydictid(String dictid);

}
