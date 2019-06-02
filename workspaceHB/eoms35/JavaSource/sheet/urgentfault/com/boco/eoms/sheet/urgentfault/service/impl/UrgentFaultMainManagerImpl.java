/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.urgentfault.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.urgentfault.dao.IUrgentFaultMainDAO;
import com.boco.eoms.sheet.urgentfault.service.IUrgentFaultMainManager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UrgentFaultMainManagerImpl extends MainService implements
        IUrgentFaultMainManager {

	public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
		IUrgentFaultMainDAO iUrgentFaultMainDAO = (IUrgentFaultMainDAO)this.getMainDAO();
		return iUrgentFaultMainDAO.getTawSystemWorkflowByFlowTemplateName(flowTemplateName);
	}

	public List showInvokeRelationShipList(String mainId) throws SheetException {
		
		IUrgentFaultMainDAO iUrgentFaultMainDAO = (IUrgentFaultMainDAO)this.getMainDAO();
		return iUrgentFaultMainDAO.showInvokeRelationShipList(mainId);
	}
	
	public BaseLink getHasInvokeBaseLink(String mainId) throws SheetException {
		IUrgentFaultMainDAO iUrgentFaultMainDAO = (IUrgentFaultMainDAO)this.getMainDAO();
		return iUrgentFaultMainDAO.getHasInvokeBaseLink(mainId);
	}
	


}
