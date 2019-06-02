/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.netchange.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.netchange.dao.INetChangeMainDAO;
import com.boco.eoms.sheet.netchange.model.NetChangeMain;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetChangeMainDaoHibernate extends MainDAO implements
        INetChangeMainDAO {
    /* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
	 */
	public BaseMain loadSinglePO(String id, Object obj) throws HibernateException {
		NetChangeMain main = (NetChangeMain) getHibernateTemplate().get(NetChangeMain.class,id);	    
		return main;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
	 */
	public BaseMain loadSinglePOByProcessId(String processId, Object obj)
			throws HibernateException {
		NetChangeMain main = new NetChangeMain();
		String sql = "from NetChangeMain as main where main.piid ='"
				+ processId + "'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() == 0) {
			main = null;
		} else {
			main = (NetChangeMain) list.get(0);
		}
		return main;
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
	 */
	public void deleteMain(String id, Object mainObject) throws HibernateException {
		NetChangeMain main = (NetChangeMain) getHibernateTemplate().get(NetChangeMain.class,id);
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
		return "NetChangeMain";
	}

	public List showInvokeRelationShipList(String mainId) throws SheetException {
		String sql = "SELECT u from UrgentFaultMain as u , NetChangeMain c where c.correlationKey = u.parentCorrelation and c.id ='"+ mainId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}
	
	public BaseLink getHasInvokeBaseLink(String mainId) throws SheetException {
		String sql = "SELECT c from NetChangeLink as c where c.operateType = 19 and c.mainId ='"+ mainId + "'";
		List list = getHibernateTemplate().find(sql);
		return list.size() > 0 ? (BaseLink)list.iterator().next() : null;
	}

	public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
		String sql = "from TawSystemWorkflow as t where t.name ='"+ flowTemplateName + "'";
		List list = getHibernateTemplate().find(sql);
		return list.size() > 0 ? (TawSystemWorkflow)list.iterator().next() : null;
	}
	
	/**
	 * 通过告警号获取工单
	 * @param alarmId 告警号
	 * @return
	 * @throws HibernateException
	 */
	public BaseMain getMainByAlarmId(String alarmId)
	throws HibernateException {
		NetChangeMain main = new NetChangeMain();
		String sql = "from NetChangeMain as main where main.mainAlarmNum ='"
				+ alarmId + "'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() == 0) {
			main = null;
		} else {
			main = (NetChangeMain) list.get(0);
		}
		return main;
	} 

}
