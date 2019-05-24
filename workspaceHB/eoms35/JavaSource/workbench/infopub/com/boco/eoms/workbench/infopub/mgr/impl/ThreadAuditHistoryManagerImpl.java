package com.boco.eoms.workbench.infopub.mgr.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.infopub.dao.ThreadAuditHistoryDao;
import com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;

public class ThreadAuditHistoryManagerImpl extends BaseManager implements
		IThreadAuditHistoryManager {
	private ThreadAuditHistoryDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setThreadAuditHistoryDao(ThreadAuditHistoryDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager#getThreadAuditHistorys(com.boco.eoms.workbench.infopub.model.ThreadAuditHistory)
	 */
	public List getThreadAuditHistorys(
			final ThreadAuditHistory threadAuditHistory) {
		return dao.getThreadAuditHistorys(threadAuditHistory);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager#getThreadAuditHistory(String
	 *      id)
	 */
	public ThreadAuditHistory getThreadAuditHistory(final String id) {
		return dao.getThreadAuditHistory(new String(id));
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager#saveThreadAuditHistory(ThreadAuditHistory
	 *      threadAuditHistory)
	 */
	public void saveThreadAuditHistory(ThreadAuditHistory threadAuditHistory) {
		dao.saveThreadAuditHistory(threadAuditHistory);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager#removeThreadAuditHistory(String
	 *      id)
	 */
	public void removeThreadAuditHistory(final String id) {
		dao.removeThreadAuditHistory(new String(id));
	}

	/**
	 * 
	 */
	public Map getThreadAuditHistorys(final Integer curPage,
			final Integer pageSize) {
		return dao.getThreadAuditHistorys(curPage, pageSize, null);
	}

	public Map getThreadAuditHistorys(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getThreadAuditHistorys(curPage, pageSize, whereStr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager#removeAuditHistoryByThreadId(java.lang.String)
	 */
	public void removeAuditHistoryByThreadId(String threadId) {
		List list = dao.getAuditHistoryByThreadId(threadId);
		if (null != list) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				ThreadAuditHistory history = (ThreadAuditHistory) it.next();
				history.setIsDel(Constants.DELETED_FLAG);
				dao.saveThreadAuditHistory(history);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager#getCurrentThreadAuditHistory(java.lang.String)
	 */
	public ThreadAuditHistory getCurrentThreadAuditHistory(String threadId) {
		return dao.getCurrentThreadAuditHistory(threadId);
	}

}
