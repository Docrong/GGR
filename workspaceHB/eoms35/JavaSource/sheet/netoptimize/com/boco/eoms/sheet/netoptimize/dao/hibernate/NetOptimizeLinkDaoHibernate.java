/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.netoptimize.dao.hibernate;


import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.businesspilot.dao.IBusinessPilotLinkDAO;

import com.boco.eoms.sheet.netoptimize.dao.INetOptimizeLinkDAO;
import com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetOptimizeLinkDaoHibernate extends LinkDAO implements
        INetOptimizeLinkDAO {

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadSinglePO(java.lang.String)
	 */
	public BaseLink loadSinglePO(String id, Object linkObject) throws HibernateException {
		NetOptimizeLink link = (NetOptimizeLink) getHibernateTemplate().get(NetOptimizeLink.class,id);
		return link;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#listAllLinkOfWorkSheet(java.lang.String)
	 */
	public List listAllLinkOfWorkSheet(String id, Object linkObject) throws HibernateException {
		String sql = "from NetOptimizeLink as link where link.mainId ='" + id
				+ "' order by link.operateTime,link.activeTemplateId";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadLinkOfStep(java.lang.String,
	 *      java.lang.String)
	 */
	public List loadLinkOfStep(String processId, String stepId, Object linkObject)
			throws HibernateException {
		String sql = "from NetOptimizeLink as link where link.piid ='"
				+ processId + "' and link.activeTempleteId = '" + stepId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

}
