package com.boco.eoms.eva.mgr;

import java.util.Map;

import com.boco.eoms.eva.dao.IEvaAuditInfoDao;
import com.boco.eoms.eva.model.EvaAuditInfo;

public interface IEvaAuditInfoMgr {

	public IEvaAuditInfoDao getEvaAuditInfoDao();
	
	public void setEvaAuditInfoDao(IEvaAuditInfoDao auditInfoDao);
	
	public EvaAuditInfo getEvaAuditInfo(String id);
	
	public void saveEvaAuditInfo(EvaAuditInfo evaAuditInfo);
	
	public Map getAuditInfoByTempateId(final Integer curPage,
			final Integer pageSize, final String whereStr);
}
