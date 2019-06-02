/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.urgentfault.service;

import java.util.List;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IUrgentFaultMainManager extends IMainService {
	/**
	 * 互调关系列表
	 * 
	 * @return
	 * @throws SheetException
	 */
	public abstract List showInvokeRelationShipList(String mainId) throws SheetException;
	/**
	 * 找到有互调环节的处理工单
	 * @param mainId
	 * @return
	 * @throws SheetException
	 */
	public  BaseLink getHasInvokeBaseLink(String mainId) throws SheetException;
	/**
	 * 取流程的名称
	 * 
	 * @return
	 * @throws SheetException
	 */
	public abstract TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException;


}
