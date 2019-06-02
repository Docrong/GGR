package com.boco.eoms.sheet.dealtypeconfig.service.impl;

import java.util.HashMap;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.dealtypeconfig.dao.IDealTypeConfigDAO;
import com.boco.eoms.sheet.dealtypeconfig.model.DealTypeConfig;
import com.boco.eoms.sheet.dealtypeconfig.service.IDealTypeConfigManager;

public class DealTypeConfigManagerImpl extends BaseManager implements IDealTypeConfigManager {
	private IDealTypeConfigDAO dao;
	 public IDealTypeConfigDAO getDao() {
		return dao;
	}

	public void setDao(IDealTypeConfigDAO dao) {
		this.dao = dao;
	}
	
	public DealTypeConfig getDealTypeConfig(String id) throws Exception {
		return dao.getDealTypeConfig(id);
	}

	public DealTypeConfig getDealTypeConfigByAdmin(String flowName, String taskName) throws Exception {
		return dao.getDealTypeConfigByAdmin(flowName, taskName);
	}
	public DealTypeConfig getDealTypeConfigByAdmin(String flowName) throws Exception {
		return dao.getDealTypeConfigByAdmin(flowName);
	}
	public List getDealTypeConfigByCondition(String condition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List getDealTypeConfigByCondition(HashMap condition) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public DealTypeConfig getDealTypeConfigByUserId(String flowName, String userId, String taskName) throws Exception {
		return dao.getDealTypeConfigByUserId(flowName, userId, taskName);
	}
	public DealTypeConfig getDealTypeConfigByUserId(String flowName, String userId) throws Exception {
		return dao.getDealTypeConfigByUserId(flowName, userId);
	}
	public void removeDealTypeConfig(String id) throws Exception {
		dao.removeDealTypeConfig(id);
		
	}

	public void saveDealTypeConfig(DealTypeConfig config) throws Exception {
		dao.saveDealTypeConfig(config);
		
	}

}
