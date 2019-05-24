
package com.boco.eoms.sheet.netdata.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

public interface INetDataLinkManager extends ILinkService {
	/**
	 * 根据operateType取得link记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public List getLinkbyOperateType(String sheetKey,String operateType) throws Exception;
}

