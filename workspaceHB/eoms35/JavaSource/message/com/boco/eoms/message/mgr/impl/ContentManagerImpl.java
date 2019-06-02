package com.boco.eoms.message.mgr.impl;

import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.dao.IContentDao;
import com.boco.eoms.message.mgr.IContentManager;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-3-11 下午02:55:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class ContentManagerImpl extends BaseManager implements
		IContentManager {
	private IContentDao dao;

	public IContentDao getSendContentDao() {
		return dao;
	}

	public void setSendContentDao(IContentDao dao) {
		this.dao = dao;
	}

	public String getSendContent(Map infoMap) {
		return dao.getSendContent(infoMap);
	}

	

	
}
