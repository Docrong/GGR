package com.boco.eoms.eva.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.eva.dao.IEvaTaskDao;
import com.boco.eoms.eva.mgr.IEvaTaskMgr;
import com.boco.eoms.eva.mgr.IEvaTemplateMgr;
import com.boco.eoms.eva.model.EvaTask;
import com.boco.eoms.eva.model.EvaTemplate;

public class EvaTaskMgrImpl implements IEvaTaskMgr {

	private IEvaTaskDao evaTaskDao;

	public IEvaTaskDao getEvaTaskDao() {
		return evaTaskDao;
	}

	public void setEvaTaskDao(IEvaTaskDao evaTaskDao) {
		this.evaTaskDao = evaTaskDao;
	}

	public EvaTask getTask(String id) {
		return evaTaskDao.getTask(id);
	}

	public void removeTaskOfTemplate(String templateId) {
		List list = listTaskOfTpl(templateId);
		for (Iterator it = list.iterator(); it.hasNext();) {
			EvaTask task = (EvaTask) it.next();
			evaTaskDao.removeTask(task);
		}
	}

	public EvaTask getTaskByTplAndOrg(String tplId, String orgId) {
		return evaTaskDao.getTaskByTplAndOrg(tplId, orgId);
	}

	public boolean isTaskExists(String tplId, String orgId) {
		boolean flag = false;
		EvaTask task = getTaskByTplAndOrg(tplId, orgId);
		if (null != task.getId() && !"".equals(task.getId())) {
			flag = true;
		}
		return flag;
	}

	public List listTaskOfOrg(String orgId, String activated) {
		return evaTaskDao.listTaskOfOrg(orgId, activated);
	}
	/**
	 * 查询某模板分类、某组织的全部任务（按生成时间倒序排）
	 * 
	 * @param orgId
	 *            组织Id
	 * @param activated
	 *            模板激活状态（不区分状态请传入空字符串）
	 * @return
	 */
	public List listTaskOfOrg(String orgId, String activated, String templateTypeId){
		return evaTaskDao.listTaskOfOrg(orgId, activated, templateTypeId);
	}

	public List listTaskOfTpl(String tplId) {
		return evaTaskDao.listTaskOfTpl(tplId);
	}

	public List listTemplateOfOrg(String orgId, String activated) {
		List tplList = new ArrayList();
		List taskList = listTaskOfOrg(orgId, activated);
		IEvaTemplateMgr tplMgr = (IEvaTemplateMgr) ApplicationContextHolder
				.getInstance().getBean("IevaTemplateMgr");
		for (Iterator taskIt = taskList.iterator(); taskIt.hasNext();) {
			EvaTask task = (EvaTask) taskIt.next();
			EvaTemplate tpl = tplMgr.getTemplate(task.getTemplateId());
			if (null != tpl.getId() && !"".equals(tpl.getId())) {
				tplList.add(tpl);
			}
		}
		return tplList;
	}

	public void saveTask(EvaTask task) {
		evaTaskDao.saveTask(task);
	}
	/**
	 * 根据主键获得考核任务
	 * 王思轩 09-1-22
	 * @param id
	 *            考核任务主键
	 */
	public EvaTask getTaskById(String id){
		return evaTaskDao.getTaskById(id);
	}

}
