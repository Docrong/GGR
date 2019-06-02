package com.boco.eoms.workbench.infopub.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;

public interface IThreadAuditHistoryManager extends Manager {
	/**
	 * Retrieves all of the threadAuditHistorys
	 */
	public List getThreadAuditHistorys(ThreadAuditHistory threadAuditHistory);

	/**
	 * Gets threadAuditHistory's information based on id.
	 * 
	 * @param id
	 *            the threadAuditHistory's id
	 * @return threadAuditHistory populated threadAuditHistory object
	 */
	public ThreadAuditHistory getThreadAuditHistory(final String id);

	/**
	 * Saves a threadAuditHistory's information
	 * 
	 * @param threadAuditHistory
	 *            the object to be saved
	 */
	public void saveThreadAuditHistory(ThreadAuditHistory threadAuditHistory);

	/**
	 * Removes a threadAuditHistory from the database by id
	 * 
	 * @param id
	 *            the threadAuditHistory's id
	 */
	public void removeThreadAuditHistory(final String id);

	public Map getThreadAuditHistorys(final Integer curPage,
			final Integer pageSize);

	public Map getThreadAuditHistorys(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 通过信息id删除相关审核记录
	 * 
	 * @param threadId
	 *            信息相关id
	 */
	public void removeAuditHistoryByThreadId(String threadId);

	/**
	 * 取某信息当前审核记录
	 * 
	 * @param threadId
	 *            信息id
	 * @return 审核历史记录
	 */
	public ThreadAuditHistory getCurrentThreadAuditHistory(String threadId);

}
