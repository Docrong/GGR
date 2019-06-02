package com.boco.eoms.eva.mgr.impl;

import com.boco.eoms.eva.dao.IEvaEntityRelDao;
import com.boco.eoms.eva.model.EvaEntityRel;

public class EvaEntityRelMgrImpl {

	private IEvaEntityRelDao entityRelDao;

	public IEvaEntityRelDao getEvaEntityRelDao() {
		return entityRelDao;
	}

	public void setEvaEntityRelDao(IEvaEntityRelDao entityRelDao) {
		this.entityRelDao = entityRelDao;
	}

	public EvaEntityRel getEvaEntityRel(String id){
		return entityRelDao.getEvaEntityRel(id);
	}
	
	public void saveEvaEntityRel(EvaEntityRel evaEntityRel){
		entityRelDao.saveEvaEntityRel(evaEntityRel);
	}

	public EvaEntityRel getEvaEntityRelByTemplateId(String templateId){
		return entityRelDao.getEvaEntityRelByTemplateId(templateId);
	}
}
