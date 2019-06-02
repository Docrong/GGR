
package com.boco.eoms.sheet.netdata.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.base.model.BaseLink;

import com.boco.eoms.sheet.netdata.dao.INetDataLinkDAO;
import com.boco.eoms.sheet.netdata.model.NetDataLink;

public class NetDataLinkDAOHibernate extends LinkDAO implements INetDataLinkDAO {

	 /*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadSinglePO(java.lang.String)
	 */
	public BaseLink loadSinglePO(String id, Object linkObject) throws HibernateException {
		NetDataLink link = (NetDataLink) getHibernateTemplate().get(NetDataLink.class,id);
		return link;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.dao.ILinkDAO#listAllLinkOfWorkSheet(java.lang.String)
	 */
	public List listAllLinkOfWorkSheet(String id, Object linkObject) throws HibernateException {
		String sql = "from "+"NetDataLink" +" as link where link.mainId ='" + id
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
		String sql = "from "+"NetDataLink"+" as link where link.piid ='"
				+ processId + "' and link.activeTempleteId = '" + stepId + "'";
		List list = getHibernateTemplate().find(sql);
		return list;
	}
	/**
	 * 根据operateType取得link记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public List getLinkbyOperateType(String sheetKey,String operateType) throws HibernateException {
		String sql = "from " + "NetDataLink" + " as link where link.mainId ='"
				+ sheetKey + "' and link.operateType = " + operateType + " order by link.operateTime desc";
		List list = getHibernateTemplate().find(sql);
		return list;
	}
}
