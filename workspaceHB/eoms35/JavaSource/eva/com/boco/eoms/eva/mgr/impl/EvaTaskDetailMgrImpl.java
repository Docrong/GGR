package com.boco.eoms.eva.mgr.impl;

import java.util.List;

import com.boco.eoms.eva.dao.IEvaTaskDetailDao;
import com.boco.eoms.eva.mgr.IEvaTaskDetailMgr;
import com.boco.eoms.eva.model.EvaTaskDetail;

public class EvaTaskDetailMgrImpl implements IEvaTaskDetailMgr {

	private IEvaTaskDetailDao evaTaskDetailDao;

	public IEvaTaskDetailDao getEvaTaskDetailDao() {
		return evaTaskDetailDao;
	}

	public void setEvaTaskDetailDao(IEvaTaskDetailDao evaTaskDetailDao) {
		this.evaTaskDetailDao = evaTaskDetailDao;
	}

	public int getMaxListNoOfTask(String taskId) {
		return evaTaskDetailDao.getMaxListNoOfTask(taskId);
	}

	public List listDetailOfTaskByListNo(String taskId, String listNo) {
		return evaTaskDetailDao.listDetailOfTaskByListNo(taskId, listNo);
	}

	public void saveTask(EvaTaskDetail taskDetail) {
		evaTaskDetailDao.saveTask(taskDetail);
	}
	
	public EvaTaskDetail getTaskDetailById(String id){
		return evaTaskDetailDao.getTaskDetailById(id);
	}
}
