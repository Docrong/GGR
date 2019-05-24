package com.boco.eoms.eva.dao;

import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.eva.model.EvaAuditInfo;

public interface IEvaAuditInfoDao extends Dao{

	public EvaAuditInfo getEvaAuditInfo(String id);
	
	public void saveEvaAuditInfo(EvaAuditInfo evaAuditInfo);
	
	public Map getAuditInfoByTempateId(final Integer curPage,
			final Integer pageSize, final String whereStr);
}
