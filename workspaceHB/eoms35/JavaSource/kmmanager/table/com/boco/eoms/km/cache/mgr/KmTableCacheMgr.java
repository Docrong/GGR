package com.boco.eoms.km.cache.mgr;

import com.boco.eoms.km.core.dataservice.map.EntityMap;

public interface KmTableCacheMgr {
	/**
	 * 根据主键查询模型信息表
	 * 
	 * @param id 主键
	 * @return 返回某id的对象
	 * @author zhangxb
	 */
	public EntityMap getKmEntityMapByTableId(final String TABLE_ID);

	/**
	 * 根据模型分类themeId查询模型信息表
	 * 
	 * @param themeId 模型分类
	 * @return 返回某模型分类themeId的对象
	 * @author zhangxb
	 */
	public EntityMap getKmEntityMapByThemeId(final String THEME_ID);
}
