/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.netoptimize.dao;

import java.util.List;

import com.boco.eoms.sheet.base.dao.IMainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface INetOptimizeMainDAO extends IMainDAO {
	
	/**
	 * 互调关系列表
	 * 
	 * @return
	 * @throws SheetException
	 */
	public abstract List showInvokeRelationShipList(String mainId) throws SheetException;
	
	/**
	 * 互调关系列表
	 * 
	 * @return
	 * @throws SheetException
	 */
	public abstract TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException;
	
	
}
