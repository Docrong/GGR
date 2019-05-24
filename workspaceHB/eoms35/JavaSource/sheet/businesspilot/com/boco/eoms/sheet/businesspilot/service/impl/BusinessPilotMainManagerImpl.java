/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.businesspilot.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.businesspilot.dao.IBusinessPilotMainDAO;
import com.boco.eoms.sheet.businesspilot.service.IBusinessPilotMainManager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BusinessPilotMainManagerImpl extends MainService implements
        IBusinessPilotMainManager {

	public List showInvokeRelationShipList(String mainId) throws SheetException {
		IBusinessPilotMainDAO iBusinessPilotMainDAO = (IBusinessPilotMainDAO)this.getMainDAO();
		return iBusinessPilotMainDAO.showInvokeRelationShipList(mainId);
	}

	public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
		IBusinessPilotMainDAO iBusinessPilotMainDAO = (IBusinessPilotMainDAO)this.getMainDAO();
		return iBusinessPilotMainDAO.getTawSystemWorkflowByFlowTemplateName(flowTemplateName);
	}

}
