package com.boco.eoms.workplan.dao;

import java.util.List;

import com.boco.eoms.workplan.model.TawwpMonthCheck;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:25:14 AM
 * </p>
 * 
 * @author 曲静汿
 * @version 3.5.1
 * 
 */
public interface ITawwpMonthCheckDao {
	/**
	 * 保存月度作业计划审批信息
	 * 
	 * @param _tawwpMonthCheck
	 *            TawwpMonthCheck 月度作业计划审批信息篿
	 * @throws Exception
	 *             操作异常
	 */
	public void saveMonthCheck(TawwpMonthCheck _tawwpMonthCheck);

	/**
	 * 删除月度作业计划审批信息
	 * 
	 * @param _tawwpMonthCheck
	 *            TawwpMonthCheck 月度作业计划审批信息篿
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteMonthCheck(TawwpMonthCheck _tawwpMonthCheck);

	/**
	 * 修改月度作业计划审批信息
	 * 
	 * @param _tawwpMonthCheck
	 *            TawwpMonthCheck 月度作业计划审批信息篿
	 * @throws Exception
	 *             操作异常
	 */
	public void updateMonthCheck(TawwpMonthCheck _tawwpMonthCheck);

	/**
	 * 查询月度作业计划审批信息
	 * 
	 * @param id
	 *            String 月度作业计划审批信息标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpMonthCheck 月度作业计划审批信息篿
	 */
	public TawwpMonthCheck loadMonthCheck(String id);

	/**
	 * 查询需要当前用户进行审批的月度作业计划,相应的审批信忿
	 * 
	 * @param _userId
	 *            String 用户
	 * @throws Exception
	 *             操作异常
	 * @return List 月度作业计划审批信息类列蟿
	 */
	public List listMonthCheck(String _userId);
	
	public List listMonthCheck(String _userId,String _monthPlanId);
	
	/**
	 * 查询需要审批的月度作业计划,相应的审批信忿
	 * 
	 * @throws Exception
	 *             操作异常
	 * @return List 月度作业计划审批信息类列蟿
	 */
	public List listUnPassMonthCheck();
}
