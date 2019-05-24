package com.boco.eoms.workplan.mgr.impl;

import java.util.List;

import com.boco.eoms.workplan.mgr.ITawwpWorkflowMgr;
import com.boco.eoms.workplan.model.TawwpWorkflow;
import com.boco.eoms.workplan.dao.ITawwpWorkflowDao;
public class TawwpWorkflowMgrImpl implements  ITawwpWorkflowMgr{

	private ITawwpWorkflowDao tawwpWorkflowDao;

	public void setTawwpWorkflowDao(ITawwpWorkflowDao tawwpWorkflowDao) {
		this.tawwpWorkflowDao = tawwpWorkflowDao;
	}
	public void saveWorkflow(TawwpWorkflow tawwpWorkflow){
		tawwpWorkflowDao.saveWorkflow(tawwpWorkflow);
	}
	public void deleteWorkflow(TawwpWorkflow tawwpWorkflow){
		tawwpWorkflowDao.deleteWorkflow(tawwpWorkflow);
	}
	public void update(TawwpWorkflow tawwpWorkflow){
		tawwpWorkflowDao.update(tawwpWorkflow);
	}
	public TawwpWorkflow loadTawwpWorkflow(String id){
		return tawwpWorkflowDao.loadTawwpWorkflow(id);
	}
	public List getListByExecute(String executeId){
		return tawwpWorkflowDao.getListByExecute(executeId);
	}
}
