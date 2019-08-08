/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.netoptimize.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.impl.MainService;


import com.boco.eoms.sheet.netoptimize.dao.INetOptimizeMainDAO;
import com.boco.eoms.sheet.netoptimize.service.INetOptimizeMainManager;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetOptimizeMainManagerImpl extends MainService implements
        INetOptimizeMainManager {

    public List showInvokeRelationShipList(String mainId) throws SheetException {
        INetOptimizeMainDAO iNetOptimizeMainDAO = (INetOptimizeMainDAO) this.getMainDAO();
        return iNetOptimizeMainDAO.showInvokeRelationShipList(mainId);
    }

    public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
        INetOptimizeMainDAO iNetOptimizeMainDAO = (INetOptimizeMainDAO) this.getMainDAO();
        return iNetOptimizeMainDAO.getTawSystemWorkflowByFlowTemplateName(flowTemplateName);
    }

}
