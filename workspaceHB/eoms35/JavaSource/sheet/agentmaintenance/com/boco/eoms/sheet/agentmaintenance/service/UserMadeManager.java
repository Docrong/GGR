package com.boco.eoms.sheet.agentmaintenance.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.sheet.agentmaintenance.model.UserMade;

public interface UserMadeManager extends Manager{
	
	public void save(UserMade useMade);

	public Map getObjectByUser(String user);
	
	public List getAllUser();
	
	public UserMade getDataById(String id);
	
	public void remove(String id);
}
