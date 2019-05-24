package com.boco.eoms.workbench.infopub.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;

public interface ThreadAuditHistoryDao extends Dao {

	/**
	 * Retrieves all of the threadAuditHistorys
	 */
	public List getThreadAuditHistorys(ThreadAuditHistory threadAuditHistory);

	/**
	 * Gets threadAuditHistory's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the threadAuditHistory's id
	 * @return threadAuditHistory populated threadAuditHistory object
	 */
	public ThreadAuditHistory getThreadAuditHistory(final String id);

	/**
	 * 通过信息id获取相关审核记录
	 * 
	 * @param threadId
	 *            信息相关id
	 * @return 审核记录
	 */
	public List getAuditHistoryByThreadId(String threadId);

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

	/**
	 * 删除审核记录
	 * 
	 * @param auditHistory
	 *            审核记录
	 */
	public void removeThreadAuditHistory(ThreadAuditHistory auditHistory);

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getThreadAuditHistorys(final Integer curPage,
			final Integer pageSize);

	public Map getThreadAuditHistorys(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 取某信息当前审核记录
	 * 
	 * @param threadId
	 *            信息id
	 * @return 审核记录
	 */
	public ThreadAuditHistory getCurrentThreadAuditHistory(String threadId);

	/**
	 * 通过信息id取该信息审核记录
	 * 
	 * @param threadId
	 *            信息id
	 * @return 该主息的审核记录
	 */
	public List getThreadAuditHistorsByThreadId(String threadId);

}
