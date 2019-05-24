package com.boco.eoms.duty.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.duty.model.AttemperLog;

/**
 * <p>
 * Title:网调日志记录
 * </p>
 * <p>
 * Description:网调日志记录
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
 public interface AttemperLogMgr {
 
	/**
	 *
	 * 取网调日志记录 列表
	 * @return 返回网调日志记录列表
	 */
	public List getAttemperLogs();
    
	/**
	 * 根据主键查询网调日志记录
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public AttemperLog getAttemperLog(final String id);
    
	/**
	 * 保存网调日志记录
	 * @param attemperLog 网调日志记录
	 */
	public void saveAttemperLog(AttemperLog attemperLog);
    
	/**
	 * 根据主键删除网调日志记录
	 * @param id 主键
	 */
	public void removeAttemperLog(final String id);
    
	/**
	 * 根据条件分页查询网调日志记录
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回网调日志记录的分页列表
	 */
	public Map getAttemperLogs(final Integer curPage, final Integer pageSize,
			final String whereStr);
			
}