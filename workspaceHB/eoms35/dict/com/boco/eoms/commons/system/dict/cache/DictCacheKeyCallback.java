package com.boco.eoms.commons.system.dict.cache;

import com.boco.eoms.commons.cache.exception.CacheKeyCallbackException;
import com.boco.eoms.commons.cache.support.ICacheKeyCallback;
import com.boco.eoms.commons.cache.util.CacheUtil;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;

/**
 * Date:2008-2-27 16：43:22
 * @author 潘龙
 * @version 1.0
 *  
 */
public class DictCacheKeyCallback implements ICacheKeyCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.cache.support.ICacheKeyCallback#execute(java.lang.String,
	 *      java.lang.String, java.lang.Object[])
	 */
	public String[] execute(String clsName, String methodName,
			Object[] arguments) throws CacheKeyCallbackException {
		String targetName = clsName + "." + methodName;
	   if( "com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name".equals(targetName)){
			String id = (String)arguments[1];
			Object[] targetArgs = new Object[] { id  };
			return new String[] { CacheUtil.getCacheKey(
							"com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name",
							"id2Name", targetArgs) };
		}
		return null;
	}
}
