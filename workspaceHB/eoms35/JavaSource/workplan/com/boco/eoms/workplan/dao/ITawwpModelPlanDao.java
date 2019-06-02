package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpModelPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:20:07 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpModelPlanDao {
	/**
	 * 保存作业计划模板
	 * 
	 * @param _tawwpModelPlan
	 *            TawwpModelPlan 作业计划模板类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveModelPlan(TawwpModelPlan _tawwpModelPlan);

	/**
	 * 删除作业计划模板
	 * 
	 * @param _tawwpModelPlan
	 *            TawwpModelPlan 作业计划模板类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteModelPlan(TawwpModelPlan _tawwpModelPlan);

	/**
	 * 修改作业计划模板
	 * 
	 * @param _tawwpModelPlan
	 *            TawwpModelPlan 作业计划模板类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateModelPlan(TawwpModelPlan _tawwpModelPlan);

	/**
	 * 查询作业计划模板信息
	 * 
	 * @param id
	 *            String 作业计划模板标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpModelPlan 作业计划模板类
	 */
	public TawwpModelPlan loadModelPlan(String id);

	/**
	 * 查询所有作业计划模板信息
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 作业计划模板类列表
	 */
	public List listModelPlan();

	/**
	 * 查询所有作业计划模板信息，按网元类别查询
	 * 
	 * @param _netTypeId
	 *            String 网元类别
	 * @throws Exception
	 *             操作异常
	 * @return List 作业计划模板类列表
	 */
	public List listModelPlan(String _netTypeId);

	/**
	 * 通过作业计划模板编号获取作业计划模板对象集合
	 * 
	 * @param _modelPlanIdList
	 *            String 作业计划模板编号字符串
	 * @throws Exception
	 *             操作异常
	 * @return List 作业计划模板类列表
	 */
	public List listModelPlanByModelPlanIds(String _modelPlanIdList);
	
	
	/**
	 * 根据穿过来的字典名称得到字典id为数据库准备数据
	 * 
	 * @param _dictName
	 *            String 字典名称。。中文名称
	 * @throws Exception
	 *             操作异常
	 * @return string 字典id
	 */
	public String getDictIdByName(String dictName);
	
	/**
	 * 根据穿过来的字典名称得到字典id为数据库准备数据 
	 * 
	 *@param _dictName
	 *            String 字典名称。。中文名称
	 *       _sysTypeId
	 *       	   String 系统类型名称
	 * @throws Exception
	 *             操作异常
	 * @return string 字典id
	 */
	public String getDictIdByNameAndSysId(String dictName,String sysTypeId);
	
	
}
