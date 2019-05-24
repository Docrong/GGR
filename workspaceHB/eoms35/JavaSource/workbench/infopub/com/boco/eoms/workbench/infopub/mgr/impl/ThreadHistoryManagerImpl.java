package com.boco.eoms.workbench.infopub.mgr.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.infopub.dao.ThreadHistoryDao;
import com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;

/**
 * 
 * <p>
 * Title:历史信息业务操作类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 5:12:45 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadHistoryManagerImpl extends BaseManager implements
		IThreadHistoryManager {

	/**
	 * 信息历史dao，供查阅使用
	 */
	private ThreadHistoryDao dao;

	/**
	 * 信息业务类
	 */
	private IThreadManager threadManager;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setThreadHistoryDao(ThreadHistoryDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager#getThreadHistorys(com.boco.eoms.workbench.infopub.model.ThreadHistory)
	 */
	public List getThreadHistorys(final ThreadHistory threadHistory) {
		return dao.getThreadHistorys(threadHistory);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager#getThreadHistory(String
	 *      id)
	 */
	public ThreadHistory getThreadHistory(final String id) {
		return dao.getThreadHistory(new String(id));
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager#saveThreadHistory(ThreadHistory
	 *      threadHistory)
	 */
	public void saveThreadHistory(ThreadHistory threadHistory) {
		dao.saveThreadHistory(threadHistory);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager#removeThreadHistory(String
	 *      id)
	 */
	public void removeThreadHistory(final String id) {
		dao.removeThreadHistory(new String(id));
	}

	/**
	 * 
	 */
	public Map getThreadHistorys(final Integer curPage, final Integer pageSize) {
		return dao.getThreadHistorys(curPage, pageSize, null);
	}

	public Map getThreadHistorys(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getThreadHistorys(curPage, pageSize, whereStr);
	}

	public Map getThreadCountHistory(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getThreadCountHistory(curPage, pageSize, whereStr);
	}
	/**
	 * @return the threadManager
	 */
	public IThreadManager getThreadManager() {
		return threadManager;
	}

	/**
	 * @param threadManager
	 *            the threadManager to set
	 */
	public void setThreadManager(IThreadManager threadManager) {
		this.threadManager = threadManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager#removeThreadHistoryByThreadId(java.lang.String)
	 */
	public void removeThreadHistoryByThreadId(String threadId) {
		List list = dao.getThreadHistorysByThreadId(threadId);
		if (list != null) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				ThreadHistory history = (ThreadHistory) it.next();
				// 设为删除标记
				history.setIsDel(Constants.DELETED_FLAG);
				dao.saveThreadHistory(history);
			}
		}
	}

}
