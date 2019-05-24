package com.boco.eoms.eva.mgr;

import com.boco.eoms.eva.dao.IEvaEntityRelDao;
import com.boco.eoms.eva.model.EvaEntityRel;

public interface IEvaEntityRelMgr {

	public IEvaEntityRelDao getEvaEntityDao();
	
	public void setEvaEntityDao(IEvaEntityRelDao entityDao);
	
	public EvaEntityRel getEvaEntityRel(String id);
	
	public void saveEvaEntityRel(EvaEntityRel evaEntityRel);

	public EvaEntityRel getEvaEntityRelByTemplateId(String templateId);
}
