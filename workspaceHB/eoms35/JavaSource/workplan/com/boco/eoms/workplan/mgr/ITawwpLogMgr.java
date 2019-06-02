package com.boco.eoms.workplan.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.workplan.util.TawwpException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 11:28:27 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ITawwpLogMgr {

	/**
	 * 业务逻辑：增加日志（ADD-LOG-001）
	 * 
	 * @param _cruser
	 *            String 操作人
	 * @param _content
	 *            String 操作内容
	 * @param _logType
	 *            String 操作类型
	 */
	public void addLog(String _cruser, String _content, String _logType);

	/**
	 * 业务逻辑：删除日志（DELETE-LOG-001）
	 * 
	 * @param _id
	 *            String 日志标识
	 */
	public void removeLog(String _id);

	/**
	 * 业务逻辑：浏览日志（RECEIVE-LOG-001）
	 * 
	 * @param pagePra
	 *            int[] 分页信息
	 * @throws TawwpException
	 *             异常信息
	 * @return List 日志信息列表
	 */
	public List listLog(int[] pagePra) throws TawwpException;

	/**
	 * 业务逻辑：将日志信息按时间段，导出到xml文件中（BACKUP-LOG-001）
	 * 
	 * @param _startDate
	 *            String 开始时间
	 * @param _enddate
	 *            String 结束时间
	 * @throws TawwpException
	 *             异常信息
	 * @return String 文件路径
	 */
	public String exportLog(String _startDate, String _enddate)
			throws TawwpException;

	/**
	 * 查询日志信息
	 * 
	 * @param _mapQuery
	 *            Map 日志查询条件
	 * @throws TawwpException
	 *             异常信息
	 * @return List 日志信息列表
	 */
	public List searchLog(Map _mapQuery) throws TawwpException;
	
	
}
