
package com.boco.eoms.sheet.emergencydrill.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.base.model.BaseLink;

import com.boco.eoms.sheet.emergencydrill.dao.IEmergencyDrillLinkDAO;
import com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillLink;

public class EmergencyDrillLinkDAOHibernate extends LinkDAO implements IEmergencyDrillLinkDAO {

	 /*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadSinglePO(java.lang.String)
	 */
	public BaseLink loadSinglePO(String id) throws HibernateException {
		EmergencyDrillLink link = (EmergencyDrillLink) getHibernateTemplate().get(EmergencyDrillLink.class,id);
		return link;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#listAllLinkOfWorkSheet(java.lang.String)
	 */
	public List listAllLinkOfWorkSheet(String id) throws HibernateException {
		String sql = "from "+"EmergencyDrillLink" +" as link where link.mainId ='" + id
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
		String sql = "from "+"EmergencyDrillLink"+" as link where link.piid ='"
				+ processId + "' and link.activeTempleteId = '" + stepId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}
}
