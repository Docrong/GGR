package com.boco.eoms.workplan.mgr.impl;

import java.util.List;

import com.boco.eoms.workplan.dao.ITawwpExecuteCheckDao;
import com.boco.eoms.workplan.mgr.ITawwpExecuteCheckMgr;
import com.boco.eoms.workplan.model.TawwpExecuteCheck;

public class TawwpExecuteCheckMgrImpl implements ITawwpExecuteCheckMgr{
	
	private ITawwpExecuteCheckDao tawwpExecuteCheckDao;
	/**
	 * 保存执行作业计划检查
	 * 
	 * @param _tawwpExecuteCheck
	 *            TawwpExecuteCheck 执行作业计划检查类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveExecuteCheck(TawwpExecuteCheck _tawwpExecuteCheck) {
		tawwpExecuteCheckDao.saveExecuteCheck(_tawwpExecuteCheck);
	}

	/**
	 * 删除执行作业计划检查
	 * 
	 * @param _tawwpExecuteCheck
	 *            TawwpExecuteCheck 执行作业计划检查类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteExecuteCheck(TawwpExecuteCheck _tawwpExecuteCheck) {
		tawwpExecuteCheckDao.deleteExecuteCheck(_tawwpExecuteCheck);

	}

	/**
	 * 修改执行作业计划检查
	 * 
	 * @param _tawwpExecuteCheck
	 *            TawwpExecuteCheck 执行作业计划检查类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateExecuteCheck(TawwpExecuteCheck _tawwpExecuteCheck) {
		tawwpExecuteCheckDao.updateExecuteCheck(_tawwpExecuteCheck);
	}

	/**
	 * 查询执行作业计划检查信息
	 * 
	 * @param id
	 *            String 执行作业计划检查标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpExecuteCheck 执行作业计划检查类
	 */
	public TawwpExecuteCheck loadExecuteCheck(String id) {
		 
		return tawwpExecuteCheckDao.loadExecuteCheck(id);
	}

	public void setTawwpExecuteCheckDao(ITawwpExecuteCheckDao tawwpExecuteCheckDao) {
		this.tawwpExecuteCheckDao = tawwpExecuteCheckDao;
	}

	
}
