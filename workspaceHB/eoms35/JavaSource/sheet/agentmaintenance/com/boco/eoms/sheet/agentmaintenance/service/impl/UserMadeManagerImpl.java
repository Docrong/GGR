package com.boco.eoms.sheet.agentmaintenance.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.agentmaintenance.dao.UserMadeDAO;
import com.boco.eoms.sheet.agentmaintenance.model.UserMade;
import com.boco.eoms.sheet.agentmaintenance.service.UserMadeManager;

public class UserMadeManagerImpl extends BaseManager implements UserMadeManager {

	UserMadeDAO userMadeDao;
	
	public UserMadeDAO getUserMadeDao() {
		return userMadeDao;
	}

	public void setUserMadeDao(UserMadeDAO userMadeDao) {
		this.userMadeDao = userMadeDao;
	}

	public void save(UserMade useMade) {
		userMadeDao.save(useMade);
	}
	
	public Map getObjectByUser(String user){
		return userMadeDao.getObjectByUser(user);
	}

	public void remove(String id) {
		userMadeDao.remove(id);
	}

	public List getAllUser() {
		return userMadeDao.getAllUser();
	}

	public UserMade getDataById(String id) {
		return userMadeDao.getDataById(id);
	}
	
}