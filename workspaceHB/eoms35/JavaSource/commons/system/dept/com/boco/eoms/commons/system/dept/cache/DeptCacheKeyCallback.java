package com.boco.eoms.commons.system.dept.cache;

import com.boco.eoms.commons.cache.exception.CacheKeyCallbackException;
import com.boco.eoms.commons.cache.support.ICacheKeyCallback;
import com.boco.eoms.commons.cache.util.CacheUtil;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;

/**
 * 
 * <p>
 * Title:部门返回key值的callback实现类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-1-22 9:55:18
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class DeptCacheKeyCallback implements ICacheKeyCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.cache.support.ICacheKeyCallback#execute(java.lang.String,
	 *      java.lang.String, java.lang.Object[])
	 */
	public String[] execute(String clsName, String methodName,
			Object[] arguments) throws CacheKeyCallbackException {
		String targetName = clsName + "." + methodName;
		
		// 执行的saveTawSystemDept方法，添加部门方法
		if ("com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl.saveTawSystemDept"
				.equals(targetName)) {
			TawSystemDept dept = (TawSystemDept) arguments[0];
			System.out.println("dept.getParentDeptid():"+dept.getParentDeptid());
			System.out.println("dept.getDeptId():"+dept.getDeptId());
			System.out.println("dept.getOldparentDeptid():"+dept.getOldparentDeptid());
			System.out.println("dept.getOlddeptId():"+dept.getOlddeptId());
			// 拼写参数
			Object[] targetArgs = new Object[] { dept.getParentDeptid(), "0" };
			Object[] targetid2name = new Object[] { dept.getDeptId(),
					"ItawSystemDeptManagerFlush" };
			if (dept.getOldparentDeptid() != null
					&& !"".equals(dept.getOldparentDeptid())) {

				Object[] oldtargetArgs = new Object[] {
						dept.getOldparentDeptid(), "0" };
				Object[] oldtargetid2name = new Object[] { dept.getOlddeptId(),
						"ItawSystemDeptManagerFlush" };
				return new String[] {
						CacheUtil
								.getCacheKey(
										"com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl",
										"getNextLevecDepts", targetArgs),
						CacheUtil
								.getCacheKey(
										"com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name",
										"id2Name", targetid2name),
						CacheUtil
								.getCacheKey(
										"com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl",
										"getNextLevecDepts", oldtargetArgs),
						CacheUtil
								.getCacheKey(
										"com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name",
										"id2Name", oldtargetid2name) };
			} else {
				return new String[] {
						CacheUtil
								.getCacheKey(
										"com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl",
										"getNextLevecDepts", targetArgs),
						CacheUtil
								.getCacheKey(
										"com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name",
										"id2Name", targetid2name) };
			}

			// 返回key值

		} else if ("com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl.removeTawSystemDeptforCatch"
				.equals(targetName)) {
			// 删除部门方法
			TawSystemDept dept = (TawSystemDept) arguments[2];
			String parentdeptid = (String) arguments[1];
			Object[] targetArgs = new Object[] { parentdeptid, "0" };
			Object[] targetid2name = new Object[] { dept.getDeptId(),
					"ItawSystemDeptManagerFlush" };
			return new String[] {
					CacheUtil
							.getCacheKey(
									"com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl",
									"getNextLevecDepts", targetArgs),
					CacheUtil
							.getCacheKey(
									"com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceImpl.id2Name",
									"id2Name", targetid2name) };
		}

		return null;
	}
}
