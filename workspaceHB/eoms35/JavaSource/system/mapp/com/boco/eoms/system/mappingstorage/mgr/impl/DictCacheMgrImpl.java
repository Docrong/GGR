package com.boco.eoms.system.mappingstorage.mgr.impl;

import com.boco.eoms.system.mappingstorage.dao.DictCacheDao;
import com.boco.eoms.system.mappingstorage.mgr.DictCacheMgr;

public class DictCacheMgrImpl implements DictCacheMgr{
	
	private DictCacheDao dictCacheDao;

	public String getDictData() {
		
		return dictCacheDao.getDictData();
	}

}
