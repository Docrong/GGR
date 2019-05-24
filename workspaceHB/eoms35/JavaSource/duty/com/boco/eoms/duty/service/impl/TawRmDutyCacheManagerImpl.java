package com.boco.eoms.duty.service.impl;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.duty.service.ITawRmDutyCacheManager;
import com.boco.eoms.duty.dao.jdbc.TawRmDutyCacheDaoJDBC;

public class TawRmDutyCacheManagerImpl  extends BaseManager implements ITawRmDutyCacheManager{

	private TawRmDutyCacheDaoJDBC jdbcCache;
	
	public Map getCacheData(){
		jdbcCache = new TawRmDutyCacheDaoJDBC();
		return jdbcCache.getCache();
	}

	public Map getDutyManCacheData(){
		jdbcCache = new TawRmDutyCacheDaoJDBC();
		return jdbcCache.getAllDutyMan();
	}
}
