
package com.boco.eoms.sheet.netdata.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.netdata.dao.INetDataLinkDAO;
import com.boco.eoms.sheet.netdata.service.INetDataLinkManager;

public class NetDataLinkManagerImpl extends LinkServiceImpl implements INetDataLinkManager {
	/**
	 * 根据operateType取得link记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public List getLinkbyOperateType(String sheetKey,String operateType) throws Exception{
		INetDataLinkDAO dao = (INetDataLinkDAO)this.getLinkDAO();
		return dao.getLinkbyOperateType(sheetKey, operateType);
	}
}
