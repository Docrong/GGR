/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.urgentfault.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.urgentfault.dao.IUrgentFaultMainDAO;
import com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UrgentFaultMainDaoHibernate extends MainDAO implements
        IUrgentFaultMainDAO {
    /* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
	 */
	public BaseMain loadSinglePO(String id, Object obj) throws HibernateException {
	    UrgentFaultMain main = (UrgentFaultMain) getHibernateTemplate().get(UrgentFaultMain.class,id);
		return main;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
	 */
	public BaseMain loadSinglePOByProcessId(String processId, Object obj)
			throws HibernateException {
		UrgentFaultMain main = new UrgentFaultMain();
		String sql = "from UrgentFaultMain as main where main.piid ='"
				+ processId + "'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() == 0) {
			main = null;
		} else {
			main = (UrgentFaultMain) list.get(0);
		}
		return main;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
	 */
	public void deleteMain(String id, Object mainObject) throws HibernateException {
	    UrgentFaultMain main = (UrgentFaultMain) getHibernateTemplate().get(UrgentFaultMain.class,id);
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
		return "UrgentFaultMain";
	}

	public List showInvokeRelationShipList(String mainId) throws SheetException {
		String sql = "SELECT c from UrgentFaultMain as u , CommonFaultMain c where u.parentCorrelation = c.correlationKey and u.id ='"+ mainId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

	public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
		String sql = "from TawSystemWorkflow as t where t.name ='"+ flowTemplateName + "'";
		List list = getHibernateTemplate().find(sql);
		return list.size() > 0 ? (TawSystemWorkflow)list.iterator().next() : null;
	}

	public BaseLink getHasInvokeBaseLink(String mainId) throws SheetException {
		String sql = "SELECT c from  CommonFaultLink c where c.operateType = 19 and c.mainId ='"+ mainId + "'";
		List list = getHibernateTemplate().find(sql);
		return list.size() > 0 ? (BaseLink)list.iterator().next() : null;
	}



}
