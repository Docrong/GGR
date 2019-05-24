package com.boco.eoms.sheet.ptnpretreatmentrule.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IPtnPretreatmentRuleManger {

	/**
	 * 分页查询
	 * @param pageSize
	 * @param pageIndex
	 * @param condition
	 * @return
	 */
	public abstract Map getRuleListByCondition(final Integer pageSize,final Integer pageIndex, String condition);
	
	/**
	 * 保存或更新
	 * @param obj
	 */
	public abstract void saveObject(Object obj);
	
	/**
	 * 根据id查询
	 * @param clazz
	 * @param id
	 * @return
	 */
	public abstract Object getObject(Class clazz, Serializable id);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public abstract void deleteObjectById(String id);
	
	/**
	 * 根据条件查询
	 * @param condition
	 * @return
	 */
	public abstract List getListByCondition(String condition);
	
}
