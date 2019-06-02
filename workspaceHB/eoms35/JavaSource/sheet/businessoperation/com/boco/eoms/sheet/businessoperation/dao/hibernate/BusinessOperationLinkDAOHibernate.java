
package com.boco.eoms.sheet.businessoperation.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.base.model.BaseLink;

import com.boco.eoms.sheet.businessoperation.dao.IBusinessOperationLinkDAO;
import com.boco.eoms.sheet.businessoperation.model.BusinessOperationLink;

public class BusinessOperationLinkDAOHibernate extends LinkDAO implements IBusinessOperationLinkDAO {

	 /*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadSinglePO(java.lang.String)
	 */
	public BaseLink loadSinglePO(String id, Object linkObject) throws HibernateException {
		BusinessOperationLink link = (BusinessOperationLink) getHibernateTemplate().get(BusinessOperationLink.class,id);
		return link;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#listAllLinkOfWorkSheet(java.lang.String)
	 */
	public List listAllLinkOfWorkSheet(String id, Object linkObject) throws HibernateException {
		String sql = "from "+"BusinessOperationLink" +" as link where link.mainId ='" + id
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
	public List loadLinkOfStep(String processId, String stepId, Object linkObject)
			throws HibernateException {
		String sql = "from "+"BusinessOperationLink"+" as link where link.piid ='"
				+ processId + "' and link.activeTempleteId = '" + stepId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}
}
