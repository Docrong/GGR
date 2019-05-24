package com.boco.eoms.eva.mgr.impl;

import java.util.Map;


import com.boco.eoms.eva.dao.IEvaAuditInfoDao;
import com.boco.eoms.eva.model.EvaAuditInfo;
import com.boco.eoms.eva.mgr.IEvaAuditInfoMgr;

public class EvaAuditInfoMgrImpl implements IEvaAuditInfoMgr{

	private IEvaAuditInfoDao auditInfoDao;
	
	public IEvaAuditInfoDao getEvaAuditInfoDao(){
		return auditInfoDao;
	}

	public void setEvaAuditInfoDao(IEvaAuditInfoDao auditInfoDao) {
		this.auditInfoDao = auditInfoDao;
	}

	public EvaAuditInfo getEvaAuditInfo(String id) {
		return auditInfoDao.getEvaAuditInfo(id);
	}
	
	public void saveEvaAuditInfo(EvaAuditInfo evaAuditInfo) {
		auditInfoDao.saveEvaAuditInfo(evaAuditInfo);
	}

	public Map getAuditInfoByTempateId(final Integer curPage,
			final Integer pageSize, final String whereStr){
		return auditInfoDao.getAuditInfoByTempateId(curPage,pageSize,whereStr);
	}
}
