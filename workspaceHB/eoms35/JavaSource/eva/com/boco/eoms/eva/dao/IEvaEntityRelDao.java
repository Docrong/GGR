package com.boco.eoms.eva.dao;
import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.eva.model.EvaEntityRel;

public interface IEvaEntityRelDao extends Dao {
	
	public EvaEntityRel getEvaEntityRel(String id);
	
	public void saveEvaEntityRel(EvaEntityRel evaEntityRel);

	public EvaEntityRel getEvaEntityRelByTemplateId(String templateId);
}
