package com.boco.eoms.workbench.infopub.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;

/**
 * 
 * <p>
 * Title:信息阅读历史记录dao的接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 5:28:02 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ThreadHistoryDao extends Dao {

	/**
	 * Retrieves all of the threadHistorys
	 */
	public List getThreadHistorys(ThreadHistory threadHistory);

	/**
	 * Gets threadHistory's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
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

	public Map getThreadHistorys(final Integer curPage, final Integer pageSize);

	public Map getThreadHistorys(final Integer curPage, final Integer pageSize,
			final String whereStr);

	public Map getThreadCountHistory(final Integer curPage, final Integer pageSize,
			final String whereStr);
	/**
	 * 通过信息id取该信息查看记录
	 * 
	 * @param threadId
	 *            信息id
	 * @return 该信息的查看记录
	 */
	public List getThreadHistorysByThreadId(String threadId);

}
