package com.boco.eoms.workplan.dao;

import com.boco.eoms.workplan.model.TawwpModelExecute;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:02:40 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpModelExecuteDao {
	/**
	 * 保存作业计划执行内容模板
	 * 
	 * @param _tawwpModelExecute
	 *            TawwpModelExecute 作业计划模板执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveModelExecute(TawwpModelExecute _tawwpModelExecute);

	/**
	 * 删除作业计划执行内容模板
	 * 
	 * @param _tawwpModelExecut
	 *            TawwpModelExecute 作业计划模板执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteModelExecute(TawwpModelExecute _tawwpModelExecut);

	/**
	 * 修改作业计划执行内容模板
	 * 
	 * @param _tawwpModelExecut
	 *            TawwpModelExecute 作业计划模板执行内容类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateModelExecute(TawwpModelExecute _tawwpModelExecut);

	/**
	 * 查询作业计划执行内容模板
	 * 
	 * @param id
	 *            String 作业计划模板执行内容标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpModelPlan 作业计划模板执行内容类
	 */
	public TawwpModelExecute loadModelExecute(String id);
}
