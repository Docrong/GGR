package com.boco.eoms.workplan.dao;

import com.boco.eoms.workplan.model.TawwpModelDispatch;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 11:58:05 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpModelDispatchDao {
	/**
	 * 保存作业计划模板派发信息
	 * 
	 * @param _tawwpModelDispatch
	 *            TawwpModelDispatch 作业计划模板派发信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveModelDispatch(TawwpModelDispatch _tawwpModelDispatch);

	/**
	 * 删除作业计划模板派发信息
	 * 
	 * @param _tawwpModelDispatch
	 *            TawwpModelDispatch 作业计划模板派发信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteModelDispatch(TawwpModelDispatch _tawwpModelDispatch);

	/**
	 * 修改作业计划模板派发信息
	 * 
	 * @param _tawwpModelDispatch
	 *            TawwpModelDispatch 作业计划模板派发信息类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateModelDispatch(TawwpModelDispatch _tawwpModelDispatch);

	/**
	 * 查询作业计划模板派发信息
	 * 
	 * @param id
	 *            String 作业计划模板派发信息标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpModelPlan 作业计划模板派发信息类
	 */
	public TawwpModelDispatch loadModelDispatch(String id);
}
