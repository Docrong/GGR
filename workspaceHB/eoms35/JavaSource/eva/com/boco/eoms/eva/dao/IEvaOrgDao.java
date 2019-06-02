package com.boco.eoms.eva.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.eva.model.EvaOrg;

public interface IEvaOrgDao extends Dao{

	public EvaOrg getEvaOrg(String id);
	
	public void saveEvaOrg(EvaOrg evaOrg);
	
	public void removeEvaOrg(EvaOrg evaOrg);
	
	// 根据模板ID获取组织列表
	public List getOrgsByTempletId(String templateId);

	// 根据模板ID删除相关组织
	public void removeOrgOfTemplate(String templateId);

	// 根据条件获取模板列表
	public List getTempletByOrgId(String where);
	
	// 根据模板ID和动作类型删除相关组织
	public void removeOrgOfTemplateAndActionType(String templateId,String actionType);
}
