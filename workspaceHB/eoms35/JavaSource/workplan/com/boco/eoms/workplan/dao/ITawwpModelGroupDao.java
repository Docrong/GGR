package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpModelGroup;
import com.boco.eoms.workplan.model.TawwpModelPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:06:09 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpModelGroupDao {
	/**
	 * 保存作业计划组织信息模板
	 * 
	 * @param _tawwpModelGroup
	 *            TawwpModelGroup 作业计划模板组织信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveModelGroup(TawwpModelGroup _tawwpModelGroup);

	/**
	 * 删除作业计划组织信息模板
	 * 
	 * @param _tawwpModelGroup
	 *            TawwpModelGroup 作业计划模板组织信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteModelGroup(TawwpModelGroup _tawwpModelGroup);

	/**
	 * 修改作业计划组织信息模板
	 * 
	 * @param _tawwpModelGroup
	 *            TawwpModelGroup 作业计划模板组织信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateModelGroup(TawwpModelGroup _tawwpModelGroup);

	/**
	 * 查询作业计划组织信息模板
	 * 
	 * @param id
	 *            String 作业计划模板组织信息标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpModelGroup 作业计划模板组织信息类
	 */
	public TawwpModelGroup loadModelGroup(String id);

	/**
	 * 获取某个作业计划组织的子类组织
	 * 
	 * @param _tawwpModelGroup
	 *            TawwpModelGroup 作业计划组织
	 * @throws Exception
	 *             操作异常
	 * @return List 作业计划模板组织信息类
	 */
	public List filterTawwpModelGroup(TawwpModelGroup _tawwpModelGroup);

	/**
	 * 获取某个作业计划组织的子类组织
	 * 
	 * @param _tawwpModelPlan
	 *            TawwpModelPlan 作业计划模版
	 * @throws Exception
	 *             操作异常
	 * @return List 作业计划模板组织信息类
	 */
	public List filterTawwpModelGroup(TawwpModelPlan _tawwpModelPlan);
}
