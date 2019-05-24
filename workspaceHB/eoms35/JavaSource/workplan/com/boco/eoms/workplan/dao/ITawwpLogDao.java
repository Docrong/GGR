package com.boco.eoms.workplan.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.workplan.model.TawwpLog;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 11:43:57 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpLogDao {
	/**
	 * 保存日志
	 * 
	 * @param _tawwpLog
	 *            TawwpLog 日志类
	 * @throws Exception
	 *             操作异常
	 */
	public void saveLog(TawwpLog _tawwpLog);

	/**
	 * 删除日志
	 * 
	 * @param _tawwpLog
	 *            TawwpLog 日志类
	 * @throws Exception
	 *             操作异常
	 */
	public void deleteLog(TawwpLog _tawwpLog);

	/**
	 * 修改日志
	 * 
	 * @param _tawwpLog
	 *            TawwpLog 日志类
	 * @throws Exception
	 *             操作异常
	 */
	public void updateLog(TawwpLog _tawwpLog);

	/**
	 * 查询日志信息
	 * 
	 * @param id
	 *            String 日志标识
	 * @throws Exception
	 *             操作异常
	 * @return TawwpLog 日志类
	 */
	public TawwpLog loadLog(String id);

	public List listLog(int[] pagePra);

	/**
	 * 组合查询日志信息,分页
	 * 
	 * @param _map
	 *            Map 查询条件
	 * @param _pagePra
	 *            int[] 分页信息
	 * @throws Exception
	 *             操作异常
	 * @return List 日志信息列表
	 */
	public List searchLog(Map _map, int[] _pagePra);

	/**
	 * 组合查询日志信息
	 * 
	 * @param _map
	 *            Map 查询条件
	 * @throws Exception
	 *             操作异常
	 * @return List 日志信息列表
	 */
	public List searchLog(Map _map);

	/**
	 * 按日期查询日志信息
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _enddate
	 *            String 结束时间
	 * @throws Exception
	 *             操作异常
	 * @return List 日志信息列表
	 */
	public List searchLog(String _startDate, String _enddate);
}
