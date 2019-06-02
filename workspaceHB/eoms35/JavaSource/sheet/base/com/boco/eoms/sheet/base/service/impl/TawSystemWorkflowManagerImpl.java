/*
 * Created on 2007-11-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.sheet.base.dao.ITawSystemWorkflowDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSystemWorkflowManagerImpl extends BaseManager implements ITawSystemWorkflowManager{
	private ITawSystemWorkflowDAO dao;

	/**
	 * @return Returns the dao.
	 */
	public ITawSystemWorkflowDAO getITawSystemWorkflowDAO() {
		return dao;
	}
	/**
	 * @param dao The dao to set.
	 */
	public void setITawSystemWorkflowDAO(ITawSystemWorkflowDAO dao) {
		this.dao = dao;
	}
	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager#getTawSystemWorkflows()
	 */
	public List getTawSystemWorkflows() {
		// TODO Auto-generated method stub
		return dao.getTawSystemWorkflows();
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager#getTawSystemWorkflow(long)
	 */
	public TawSystemWorkflow getTawSystemWorkflow(long id) {
		// TODO Auto-generated method stub
		return dao.getTawSystemWorkflow(id);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager#saveTawSystemWorkflow(com.boco.eoms.sheet.base.model.TawSystemWorkflow)
	 */
	public void saveTawSystemWorkflow(TawSystemWorkflow tawSystemWorkflow) {
		// TODO Auto-generated method stub
		dao.saveTawSystemWorkflow(tawSystemWorkflow);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager#removeTawSystemWorkflow(long)
	 */
	public void removeTawSystemWorkflow(long id) throws Exception {
		// TODO Auto-generated method stub
		dao.removeTawSystemWorkflow(id);
	}

	/**
     * 根据流程名称查询流程信息
     * @param name 流程名称
     * @return
     * @throws Exception
     */
	public TawSystemWorkflow getTawSystemWorkflowByName(String name) throws SheetException {
		return dao.getTawSystemWorkflowByName(name);
	}
	public TawSystemWorkflow getTawSystemWorkflowByBeanId(String mainBeanId) throws SheetException {
		// TODO Auto-generated method stub
		return dao.getTawSystemWorkflowByBeanId(mainBeanId);
	}
}
