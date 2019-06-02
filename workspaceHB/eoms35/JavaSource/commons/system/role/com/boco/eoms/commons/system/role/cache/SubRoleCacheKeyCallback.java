package com.boco.eoms.commons.system.role.cache;

import com.boco.eoms.commons.cache.exception.CacheKeyCallbackException;
import com.boco.eoms.commons.cache.support.ICacheKeyCallback;
import com.boco.eoms.commons.cache.util.CacheUtil;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;

/**
 * 
 * <p>
 * Title:角色返回key值的callback实现类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-1-22 9:55:18
 * </p>
 * 
 * @author xqz
 * @version 1.0
 *  
 */
public class SubRoleCacheKeyCallback implements ICacheKeyCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.cache.support.ICacheKeyCallback#execute(java.lang.String,
	 *      java.lang.String, java.lang.Object[])
	 */
	public String[] execute(String clsName, String methodName,
			Object[] arguments) throws CacheKeyCallbackException {
		String targetName = clsName + "." + methodName;
		
		System.out.println("targetName="+targetName);
		//执行的saveTawSystemRole方法，添加角色方法
		if ("com.boco.eoms.commons.system.role.service.impl.TawSystemSubRoleManagerImpl.saveTawSystemSubRole"
				.equals(targetName)) {
			System.out.println("targetName2="+targetName);
			TawSystemSubRole role = (TawSystemSubRole) arguments[0];
			//拼写参数
			Object[] targetArgs = new Object[] { new Long(role.getRoleId()), new Long(0)};
			Object[] targetid2name = new Object[] { new Long(role.getRoleId()), "ItawSystemSubRoleManagerFlush" };

			//返回key值
			return new String[] { CacheUtil
					.getCacheKey(
							"com.boco.eoms.commons.system.role.service.impl.TawSystemRoleManagerImpl",
							"getTawSystemSubRoles", targetArgs),CacheUtil.getCacheKey("com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name", "id2Name", targetid2name)};
		}
		return null;
	}
}
