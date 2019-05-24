
package com.boco.eoms.sheet.processchange.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.base.model.BaseLink;

import com.boco.eoms.sheet.processchange.dao.IProcessChangeLinkDAO;
import com.boco.eoms.sheet.processchange.model.ProcessChangeLink;

public class ProcessChangeLinkDAOHibernate extends LinkDAO implements IProcessChangeLinkDAO {

	 /*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadSinglePO(java.lang.String)
	 */
	public BaseLink loadSinglePO(String id) throws HibernateException {
		ProcessChangeLink link = (ProcessChangeLink) getHibernateTemplate().get(ProcessChangeLink.class,id);
		return link;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#listAllLinkOfWorkSheet(java.lang.String)
	 */
	public List listAllLinkOfWorkSheet(String id) throws HibernateException {
		String sql = "from "+"ProcessChangeLink" +" as link where link.mainId ='" + id
				+ "' order by link.operateTime";
		List list = getHibernateTemplate().find(sql);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadLinkOfStep(java.lang.String,
	 *      java.lang.String)
	 */
	public List loadLinkOfStep(String processId, String stepId)
			throws HibernateException {
		String sql = "from "+"ProcessChangeLink"+" as link where link.piid ='"
				+ processId + "' and link.activeTempleteId = '" + stepId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}
}
