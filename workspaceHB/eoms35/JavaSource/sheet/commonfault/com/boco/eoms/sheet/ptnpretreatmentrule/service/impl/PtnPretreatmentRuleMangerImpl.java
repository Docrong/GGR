package com.boco.eoms.sheet.ptnpretreatmentrule.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.ptnpretreatmentrule.dao.IPtnPretreatmentRuleDAO;
import com.boco.eoms.sheet.ptnpretreatmentrule.model.PtnPretreatmentRule;
import com.boco.eoms.sheet.ptnpretreatmentrule.service.IPtnPretreatmentRuleManger;

public class PtnPretreatmentRuleMangerImpl extends BaseManager implements
		IPtnPretreatmentRuleManger {
	
	private IPtnPretreatmentRuleDAO dao;

	public IPtnPretreatmentRuleDAO getDao() {
		return dao;
	}

	public void setDao(IPtnPretreatmentRuleDAO dao) {
		this.dao = dao;  
	}

	public Map getRuleListByCondition(final Integer pageSize, final Integer pageIndex, final String condition) {
		return this.dao.getRuleListByCondition(pageSize, pageIndex, condition);
	}

	public Object getObject(Class clazz, Serializable id) {
		return this.dao.getObject(clazz, id);
	}

	public List getObjects(Class clazz) {
		return this.dao.getObjects(clazz);
	}

	public void saveObject(Object o) {
		this.dao.saveObject(o);
	}

	public void deleteObjectById(String id) {
		this.dao.removeObject(PtnPretreatmentRule.class, id);
	}

	public List getListByCondition(String condition) {
		return this.dao.getListByCondition(condition);
	}
	
}
