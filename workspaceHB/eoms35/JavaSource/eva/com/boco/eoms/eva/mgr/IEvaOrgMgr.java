package com.boco.eoms.eva.mgr;

import java.util.List;

import com.boco.eoms.eva.dao.IEvaOrgDao;
import com.boco.eoms.eva.model.EvaOrg;

public interface IEvaOrgMgr {

	public IEvaOrgDao getEvaOrgDao();

	public void setEvaOrgDao(IEvaOrgDao orgDao);

	public void saveEvaOrg(EvaOrg evaOrg);

	public EvaOrg getEvaOrg(String id);

	public void removeEvaOrg(EvaOrg evaOrg);

	public List getOrgsByTempletId(String templateId);

	public void removeOrgOfTemplate(String templateId);

	public List getTempletByUserId(String userId, String actionType,
			String status);
	
	public List getTaskByConditions(String conditions);
	
	public EvaOrg getLatestTaskByTaskId(String taskId);

}
