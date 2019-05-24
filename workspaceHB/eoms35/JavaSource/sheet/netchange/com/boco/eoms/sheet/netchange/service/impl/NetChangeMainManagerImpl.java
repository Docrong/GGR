/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.netchange.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.netchange.dao.INetChangeMainDAO;
import com.boco.eoms.sheet.netchange.service.INetChangeMainManager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetChangeMainManagerImpl extends MainService implements
        INetChangeMainManager {

	public List showInvokeRelationShipList(String mainId) throws SheetException {
		INetChangeMainDAO iNetChangeMainDAO = (INetChangeMainDAO)this.getMainDAO();
		return iNetChangeMainDAO.showInvokeRelationShipList(mainId);
	}
	
	public BaseLink getHasInvokeBaseLink(String mainId) throws SheetException {
		INetChangeMainDAO iNetChangeMainDAO = (INetChangeMainDAO)this.getMainDAO();
		return iNetChangeMainDAO.getHasInvokeBaseLink(mainId);
	}

	public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
		INetChangeMainDAO iNetChangeMainDAO = (INetChangeMainDAO)this.getMainDAO();
		return iNetChangeMainDAO.getTawSystemWorkflowByFlowTemplateName(flowTemplateName);
	}
	/**
	 * 通过告警号获取工单
	 * @param alarmId 告警号
	 * @return
	 * @throws HibernateException
	 */
	public BaseMain getMainByAlarmId(String alarmId){
		INetChangeMainDAO iNetChangeMainDAO = (INetChangeMainDAO)this.getMainDAO();
		return iNetChangeMainDAO.getMainByAlarmId(alarmId);
	}
	public BaseMain loadSinglePO(String id) {
		INetChangeMainDAO iNetChangeMainDAO = (INetChangeMainDAO)this.getMainDAO();
		return iNetChangeMainDAO.loadSinglePO(id, this.getMainObject());
    }

}
