package com.boco.eoms.commons.system.user.cache;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.cache.exception.CacheKeyCallbackException;
import com.boco.eoms.commons.cache.support.ICacheKeyCallback;
import com.boco.eoms.commons.cache.util.CacheUtil;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;

/*
 * 
 * @author panlong
 * Feb 29, 2008 9:00:01 AM
 */
public class UserCacheKeyCallback implements ICacheKeyCallback {

	public String[] execute(String clsName, String methodName,
			Object[] arguments) throws CacheKeyCallbackException {
		String targetName = clsName + "." + methodName;
		if ("com.boco.eoms.commons.system.user.service.impl.TawSystemUserManagerImpl.saveTawSystemUser"
				.equals(targetName)) {
			TawSystemUser sysuser = (TawSystemUser) arguments[0];
			// 拼写参数
			Object[] targetArgs = new Object[] { sysuser.getDeptid() };
			Object[] targetid2name = new Object[] { sysuser.getUserid(),
					"ItawSystemUserManagerFlush" };

			// 返回key值
			return new String[] {
					CacheUtil
							.getCacheKey(
									"com.boco.eoms.commons.system.user.service.impl.TawSystemUserManagerImpl",
									"getUserBydeptids", targetArgs),
					CacheUtil
							.getCacheKey(
									"com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name",
									"id2Name", targetid2name) };
		} else if ("com.boco.eoms.commons.system.user.service.impl.TawSystemUserManagerImpl.removeTawSystemUser"
				.equals(targetName)) {
			String id = (String) arguments[0];
			ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("itawSystemUserManager");
			TawSystemUser sysuser = mgr.getTawSystemUser(id);

			// 拼写参数
			Object[] targetArgs = new Object[] { sysuser.getDeptid() };
			Object[] targetid2name = new Object[] { sysuser.getUserid(),
					"ItawSystemUserManagerFlush" };

			// 返回key值
			return new String[] {
					CacheUtil
							.getCacheKey(
									"com.boco.eoms.commons.system.user.service.impl.TawSystemUserManagerImpl",
									"getUserBydeptids", targetArgs),
					CacheUtil
							.getCacheKey(
									"com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name",
									"id2Name", targetid2name) };
		}
		return null;
	}

}
