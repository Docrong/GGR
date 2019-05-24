package com.boco.eoms.workplan.mgr;

import java.util.List;

import com.boco.eoms.workplan.dao.ITawwpWorkflowDao;
import com.boco.eoms.workplan.model.TawwpWorkflow;

public interface ITawwpWorkflowMgr {
	public void setTawwpWorkflowDao(ITawwpWorkflowDao tawwpWorkflowDao);
	public void saveWorkflow(TawwpWorkflow tawwpWorkflow);
	public void deleteWorkflow(TawwpWorkflow tawwpWorkflow);
	public void update(TawwpWorkflow tawwpWorkflow);
	public TawwpWorkflow loadTawwpWorkflow(String id);
	public List getListByExecute(String executeId);
}
