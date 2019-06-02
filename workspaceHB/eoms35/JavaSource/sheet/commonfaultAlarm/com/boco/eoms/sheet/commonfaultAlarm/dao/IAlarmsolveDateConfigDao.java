package com.boco.eoms.sheet.commonfaultAlarm.dao;

import java.util.List;

public interface IAlarmsolveDateConfigDao {
	public List findall(int issended); 
	public void saveorupdate(Object obj); 
}
