/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.businesspilot.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.businesspilot.dao.IBusinessPilotMainDAO;
import com.boco.eoms.sheet.businesspilot.model.BusinessPilotMain;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BusinessPilotMainDaoHibernate extends MainDAO implements
        IBusinessPilotMainDAO {
    /* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
	 */
	public BaseMain loadSinglePO(String id, Object obj) throws HibernateException {
	    BusinessPilotMain main = (BusinessPilotMain) getHibernateTemplate().get(BusinessPilotMain.class,id);
		return main;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
	 */
	public BaseMain loadSinglePOByProcessId(String processId, Object obj)
			throws HibernateException {
		BusinessPilotMain main = new BusinessPilotMain();
		String sql = "from BusinessPilotMain as main where main.piid ='"
				+ processId + "'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() == 0) {
			main = null;
		} else {
			main = (BusinessPilotMain) list.get(0);
		}
		return main;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
	 */
	public void deleteMain(String id, Object mainObject) throws HibernateException {
	    BusinessPilotMain main = (BusinessPilotMain) getHibernateTemplate().get(BusinessPilotMain.class,id);
		main.setDeleted(new Integer(1));
		getHibernateTemplate().save(main);
	}
	/**
	 * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
	 */
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	 */
	public String getMainName() {
		// TODO Auto-generated method stub
		return "BusinessPilotMain";
	}

	public List showInvokeRelationShipList(String mainId) throws SheetException {
		String sql = "SELECT u from UrgentFaultMain as u , BusinessPilotMain c where c.correlationKey = u.parentCorrelation and c.id ='"+ mainId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

	public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
		String sql = "from TawSystemWorkflow as t where t.name ='"+ flowTemplateName + "'";
		List list = getHibernateTemplate().find(sql);
		return list.size() > 0 ? (TawSystemWorkflow)list.iterator().next() : null;
	}


}
