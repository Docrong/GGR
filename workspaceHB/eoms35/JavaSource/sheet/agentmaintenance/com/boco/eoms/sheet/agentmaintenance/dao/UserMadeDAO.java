package com.boco.eoms.sheet.agentmaintenance.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.agentmaintenance.model.UserMade;

public interface UserMadeDAO {
	public void save(UserMade useMade);
	
	public List getAllUser();

	public Map getObjectByUser(String user);
	
	public UserMade getDataById(String id);
	
	public void remove(String id);
}
