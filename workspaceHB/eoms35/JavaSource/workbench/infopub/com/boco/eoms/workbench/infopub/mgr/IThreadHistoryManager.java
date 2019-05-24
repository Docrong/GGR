package com.boco.eoms.workbench.infopub.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;

/**
 * 
 * <p>
 * Title:阅读信息历史记录mgr
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 5:25:50 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface IThreadHistoryManager extends Manager {
	/**
	 * Retrieves all of the threadHistorys
	 */
	public List getThreadHistorys(ThreadHistory threadHistory);

	/**
	 * Gets threadHistory's information based on id.
	 * 
	 * @param id
	 *            the threadHistory's id
	 * @return threadHistory populated threadHistory object
	 */
	public ThreadHistory getThreadHistory(final String id);

	/**
	 * Saves a threadHistory's information
	 * 
	 * @param threadHistory
	 *            the object to be saved
	 */
	public void saveThreadHistory(ThreadHistory threadHistory);

	/**
	 * Removes a threadHistory from the database by id
	 * 
	 * @param id
	 *            the threadHistory's id
	 */
	public void removeThreadHistory(final String id);

	/**
	 * 分页取阅读信息历史记录
	 * 
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public Map getThreadHistorys(final Integer curPage, final Integer pageSize);

	/**
	 * 条件分页取阅读信息历史记录
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map getThreadHistorys(final Integer curPage, final Integer pageSize,
			final String whereStr);

	/**
	 * 根据记录类型取出阅读记录或评阅信息（分页）
	 * 
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map getThreadCountHistory(final Integer curPage, final Integer pageSize,
			final String whereStr);	
	/**
	 * 删除与信息id关联的历史信息
	 * 
	 * @param threadId
	 *            信息id
	 */
	public void removeThreadHistoryByThreadId(String threadId);

}
