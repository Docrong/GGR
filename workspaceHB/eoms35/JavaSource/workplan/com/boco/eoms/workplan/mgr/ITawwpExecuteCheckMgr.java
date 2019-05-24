package com.boco.eoms.workplan.mgr;

import com.boco.eoms.workplan.dao.ITawwpExecuteCheckDao;
import com.boco.eoms.workplan.model.TawwpExecuteCheck;

public interface ITawwpExecuteCheckMgr { 
	/**
	 * 保存执行作业计划检查
	 * 
	 * @param _tawwpExecuteCheck
	 *            TawwpExecuteCheck 执行作业计划检查类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveExecuteCheck(TawwpExecuteCheck _tawwpExecuteCheck) ;

	/**
	 * 删除执行作业计划检查
	 * 
	 * @param _tawwpExecuteCheck
	 *            TawwpExecuteCheck 执行作业计划检查类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteExecuteCheck(TawwpExecuteCheck _tawwpExecuteCheck) ;

	/**
	 * 修改执行作业计划检查
	 * 
	 * @param _tawwpExecuteCheck
	 *            TawwpExecuteCheck 执行作业计划检查类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateExecuteCheck(TawwpExecuteCheck _tawwpExecuteCheck) ;

	/**
	 * 查询执行作业计划检查信息
	 * 
	 * @param id
	 *            String 执行作业计划检查标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpExecuteCheck 执行作业计划检查类
	 */
	public TawwpExecuteCheck loadExecuteCheck(String executeId) ;
}
