package com.boco.eoms.workbench.infopub.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.workbench.infopub.dao.ThreadDao;
import com.boco.eoms.workbench.infopub.mgr.IThreadAuditHistoryManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadHistoryManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadManager;
import com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.model.ThreadAuditHistory;
import com.boco.eoms.workbench.infopub.model.ThreadHistory;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.util.InfopubUtil;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadAuditHistoryForm;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;

/**
 * 
 * <p>
 * Title:信息（贴子）管理mgr
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:06:38 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadManagerImpl extends BaseManager implements IThreadManager {
	/**
	 * 信息dao
	 */
	private ThreadDao dao;

	/**
	 * 信息历史mgr
	 */
	private IThreadAuditHistoryManager threadAuditHistoryManager;

	/**
	 * 信息权限
	 */
	private IThreadPermimissionOrgManager threadPermimissionOrgManager;

	/**
	 * 信息访问历史
	 */
	private IThreadHistoryManager threadHistoryManager;

	/**
	 * @param threadHistoryManager
	 *            the threadHistoryManager to set
	 */
	public void setThreadHistoryManager(
			IThreadHistoryManager threadHistoryManager) {
		this.threadHistoryManager = threadHistoryManager;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setThreadDao(ThreadDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#getThreads(com.boco.eoms.workbench.infopub.model.Thread)
	 */
	public List getThreads(final Thread thread) {
		return dao.getThreads(thread);
	}

	// add by gongyufeng
	public List getThreadList(final String whereStr) {
		return dao.getThreadList(whereStr);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#getThread(String
	 *      id)
	 */
	public Thread getThread(final String id) {
		return dao.getThread(new String(id));
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#saveThread(Thread
	 *      thread)
	 */
	public void saveThread(Thread thread) {
		dao.saveThread(thread);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#saveThread(com.boco.eoms.workbench.infopub.model.Thread,
	 *      com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg,
	 *      com.boco.eoms.workbench.infopub.model.ThreadAuditHistory)
	 */
	/*
	 * public void saveThread(Thread thread, List threadPermimissionOrgs,
	 * ThreadAuditHistory threadAuditHistory) { // 发布信息及保存发布范围
	 * saveThread(thread, threadPermimissionOrgs); // 若为修改信息则先删除审核历史 if (null ==
	 * thread.getId() || "".equals(thread.getId())) { // TODO 加入权限
	 * threadAuditHistoryManager.removeAuditHistoryByThreadId(thread .getId());
	 * ThreadAuditHistory history = threadAuditHistoryManager
	 * .getCurrentThreadAuditHistory(thread.getId());
	 * 
	 * history.setIsCurrent(InfopubConstants.FALSE_STR); } // 设置信息关联
	 * threadAuditHistory.setThreadId(thread.getId()); // 保存审核历史记录
	 * threadAuditHistoryManager.saveThreadAuditHistory(threadAuditHistory); }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#saveThread(com.boco.eoms.workbench.infopub.model.Thread,
	 *      com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg)
	 */
	public void saveThread(Thread thread, List threadPermimissionOrgs) {
		dao.saveThread(thread);
		// 若为修改贴子则删除权限
		if (!"".equals(thread.getId())) {
			// 删除权限
			threadPermimissionOrgManager
					.delPermissionByThreadId(thread.getId());
		}
		if (null != threadPermimissionOrgs && !threadPermimissionOrgs.isEmpty()) {
			for (Iterator it = threadPermimissionOrgs.iterator(); it.hasNext();) {
				// 信息可读人员
				ThreadPermimissionOrg tpo = (ThreadPermimissionOrg) it.next();
				// 2009-04-08 设置是否包括子部门
				tpo.setIsIncludeSubDept(thread.getIsIncludeSubDept());
				// 设置关联
				tpo.setThreadId(thread.getId());
				// 保存可读权限
				threadPermimissionOrgManager.saveThreadPermimissionOrg(tpo);
			}
                 //	发送短信
			if (InfopubConstants.NO_AUDIT.equals(thread.getStatus())) {
				if ("1".equals(thread.getIsSend())) {
					sendMessage(threadPermimissionOrgs, thread.getId());
				}
			}
		}
	}

	// 发送短信
	private void sendMessage(List threadPermimissionOrgs,String threadId) {
		ITawSystemDeptManager dMgr = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		List sonlist = new ArrayList();
		MsgServiceImpl msgService = new MsgServiceImpl();
		String time = StaticMethod.getCurrentDateTime("yyyy-MM-dd hh:mm:ss");
		String serviceId = "8aa0813d21800ef301218016af4b000e";
		if (null != threadPermimissionOrgs && !threadPermimissionOrgs.isEmpty()) {
			for (Iterator it = threadPermimissionOrgs.iterator(); it.hasNext();) {
				ThreadPermimissionOrg tpo = (ThreadPermimissionOrg) it.next();
				String type = tpo.getOrgType();
				if (StaticVariable.PRIV_ASSIGNTYPE_USER.equals(type)) {
					msgService.sendMsg(serviceId, "您有新的信息发布消息，请注意查收", threadId,
							"1," + tpo.getOrgId(), time);
				} else if (StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(type)) {
					if ("1".equals(tpo.getIsIncludeSubDept())) {
						sonlist = dMgr.getALLSondept(tpo.getOrgId(), "0");
						for (int w = 0; w < sonlist.size(); w++) {
							msgService
									.sendMsg(serviceId, "您有新的信息发布消息，请注意查收",threadId, "2,"
											+ ((TawSystemDept) sonlist.get(w))
													.getDeptId(),
													time);
						}
					}
					msgService.sendMsg(serviceId, "您有新的信息发布消息，请注意查收", threadId,
							"2," + tpo.getOrgId(), time);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#saveThreadHistor(com.boco.eoms.workbench.infopub.model.Thread,
	 *      com.boco.eoms.workbench.infopub.model.ThreadHistory)
	 */
	public void saveThreadHistory(Thread thread, ThreadHistory threadHistory) {
		// 将阅读数量加1
		thread.setThreadCount(InfopubUtil.add1(thread.getThreadCount()));
		saveThread(thread);
		threadHistoryManager.saveThreadHistory(threadHistory);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#removeThread(String
	 *      id)
	 */
	public void removeThread(final String id) {
		// 根据主键获取信息
		Thread thread = this.getThread(id);
		// 设为已删除
		thread.setIsDel(Constants.DELETED_FLAG);
		// 修改贴子删除标记
		dao.saveThread(thread);
		threadAuditHistoryManager.removeAuditHistoryByThreadId(id);
		threadHistoryManager.removeThreadHistory(id);
		threadPermimissionOrgManager.removeThreadPermissionOrgByThreadId(id);
		// dao.removeThread(new String(id));
	}

	/**
	 * 
	 */
	public Map getThreads(final Integer curPage, final Integer pageSize) {
		return dao.getThreads(curPage, pageSize, null);
	}

	public Map getThreads(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getThreads(curPage, pageSize, whereStr);
	}

	public Map getThreads(final String whereStr) {
		return dao.getThreads(whereStr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#getUnreadThreads(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String,java.lang.String)
	 */
	public Map getUnreadThreads(Integer curPage, Integer pageSize,
			String userId, String whereStr) {
		return dao.getUnreadThreads(curPage, pageSize, userId, whereStr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#sort(java.lang.String[],
	 *      java.lang.String)
	 */
	public void sort(String[] ids, String forumsId) {
		dao.sort(ids, forumsId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#delete(java.lang.String[])
	 */
	public void removeThread(String[] ids) {
		if (null != ids) {
			for (int i = 0; i < ids.length; i++) {
				this.removeThread(ids[i]);
			}
		}
	}

	/**
	 * @param threadAuditHistoryManager
	 *            the threadAuditHistoryManager to set
	 */
	public void setThreadAuditHistoryManager(
			IThreadAuditHistoryManager threadAuditHistoryManager) {
		this.threadAuditHistoryManager = threadAuditHistoryManager;
	}

	/**
	 * @param threadPermimissionOrgManager
	 *            the threadPermimissionOrgManager to set
	 */
	public void setThreadPermimissionOrgManager(
			IThreadPermimissionOrgManager threadPermimissionOrgManager) {
		this.threadPermimissionOrgManager = threadPermimissionOrgManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#getThreads4Draft(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String)
	 */
	public Map getThreads4Draft(Integer curPage, Integer pageSize, String userId) {
		return dao.getThreads4audit(curPage, pageSize, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#saveThreadAuditHistory(com.boco.eoms.workbench.infopub.model.ThreadAuditHistory)
	 */
	public void saveThreadAuditHistoryDraft(
			ThreadAuditHistory threadAuditHistory) {
		// 根据id取信息对象，将信息对象状态设为审核中
		Thread thread = getThread(threadAuditHistory.getThreadId());
		thread.setStatus(InfopubConstants.AUDIT_WITHOUT_SUBMIT);
		saveThread(thread);
		// 设置当前记录为审核
		threadAuditHistory.setIsCurrent(InfopubConstants.TRUE_STR);
		// 设置提交时间
		threadAuditHistory.setSubmitTime(new Date());
		// 设置审核状态
		threadAuditHistory.setStatus(InfopubConstants.AUDIT_WITHOUT_SUBMIT);
		// 保存审核记录
		threadAuditHistoryManager.saveThreadAuditHistory(threadAuditHistory);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#saveThreadAuditHistoryAudit(com.boco.eoms.workbench.infopub.model.ThreadAuditHistory)
	 */
	public void saveThreadAuditHistoryAudit(
			ThreadAuditHistory threadAuditHistory) {
		// 根据id取信息对象，将信息对象状态设为审核中
		Thread thread = getThread(threadAuditHistory.getThreadId());
		thread.setStatus(InfopubConstants.ADUITING);
		saveThread(thread);
		// 设置当前记录为审核
		threadAuditHistory.setIsCurrent(InfopubConstants.TRUE_STR);
		// 设置提交时间
		threadAuditHistory.setSubmitTime(new Date());
		// 设置审核状态
		threadAuditHistory.setStatus(InfopubConstants.ADUITING);
		// 保存审核记录
		threadAuditHistoryManager.saveThreadAuditHistory(threadAuditHistory);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#auditThread(com.boco.eoms.workbench.infopub.webapp.form.ThreadAuditHistoryForm)
	 */
	public void auditThread(ThreadAuditHistoryForm threadAuditHistoryForm) {
		IThreadPermimissionOrgManager orgMgr = (IThreadPermimissionOrgManager) ApplicationContextHolder
		.getInstance().getBean("IthreadPermimissionOrgManager");
		
		// 取所有要审核的id
		if (threadAuditHistoryForm.getIds() != null) {
			for (int i = 0; i < threadAuditHistoryForm.getIds().length; i++) {
				ThreadAuditHistory history = threadAuditHistoryManager
						.getThreadAuditHistory(threadAuditHistoryForm.getIds()[i]);
				// 设置审核状态
				history.setStatus(threadAuditHistoryForm.getStatus());
				// 设置审核意见
				history.setOpinion(threadAuditHistoryForm.getOpinion());
				// 设置审核时间
				history.setAuditTime(new Date());
				// 取信息
				Thread thread = dao.getThread(history.getThreadId());
				// 设置信息状态
				thread.setStatus(threadAuditHistoryForm.getStatus());
				// 若审核通过，则当前没有待执行的审核
				if (InfopubConstants.AUDIT_PASS.equals(threadAuditHistoryForm
						.getStatus())) {
					history.setIsCurrent(InfopubConstants.FALSE_STR);
					List pOrgs = orgMgr.getThreadPermissionOrgsByThreadId(thread.getId());
					sendMessage(pOrgs, thread.getId());
				}
				// 保存审核历史信息
				threadAuditHistoryManager.saveThreadAuditHistory(history);
				// 保存信息
				dao.saveThread(thread);
			}
		} else {
			// FIXME 不可抛出运时异常
			throw new RuntimeException("no choose");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#submitAudit4noaudit(com.boco.eoms.workbench.infopub.model.ThreadAuditHistory)
	 */
	public void submitAudit4noaudit(ThreadAuditHistory threadAuditHistory) {
		Thread thread = dao.getThread(threadAuditHistory.getThreadId());
		// 信息状态改为审核中
		thread.setStatus(InfopubConstants.ADUITING);
		// 并设置新提交的审核记录执行状态为true（正在执行）
		threadAuditHistory.setIsCurrent(InfopubConstants.TRUE_STR);
		// 设置为审核中状态
		threadAuditHistory.setStatus(InfopubConstants.ADUITING);
		// 设置提交时间
		threadAuditHistory.setSubmitTime(new Date());
		dao.saveThread(thread);
		threadAuditHistoryManager.saveThreadAuditHistory(threadAuditHistory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#submitAudit4nopass(com.boco.eoms.workbench.infopub.model.ThreadAuditHistory)
	 */
	public void submitAudit4nopass(ThreadAuditHistory threadAuditHistory) {
		ThreadAuditHistory history = threadAuditHistoryManager
				.getCurrentThreadAuditHistory(threadAuditHistory.getThreadId());
		// 设置审核未通过的状态此审核记录为false，并设置新提交的审核记录为true
		history.setIsCurrent(InfopubConstants.FALSE_STR);
		threadAuditHistory.setIsCurrent(InfopubConstants.TRUE_STR);
		// TODO 状态设置重构与submitAudit4noaudit
		// 设置父节点为上一步审核未通过的节点
		threadAuditHistory.setParentId(history.getId());
		// 设置为审核中状态
		threadAuditHistory.setStatus(InfopubConstants.ADUITING);
		// 设置提交时间
		threadAuditHistory.setSubmitTime(new Date());
		Thread thread = dao.getThread(threadAuditHistory.getThreadId());
		// 信息状态改为审核中
		thread.setStatus(InfopubConstants.ADUITING);
		dao.saveThread(thread);
		threadAuditHistoryManager.saveThreadAuditHistory(threadAuditHistory);
		threadAuditHistoryManager.saveThreadAuditHistory(history);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadManager#searchThreads(java.lang.Integer,
	 *      java.lang.Integer,
	 *      com.boco.eoms.workbench.infopub.webapp.form.ThreadForm,
	 *      java.lang.String)
	 */
	public Map searchThreads(Integer curPage, Integer pageSize,
			ThreadForm threadForm, String deptStr) {
		return dao.listThreads(curPage, pageSize, threadForm, deptStr);
	}

	public Thread getSerialNoToThread(String serialNo) {
		Thread thread = new Thread();
		thread = dao.getSerialNoToThread(serialNo);
		return thread;
	}

	public String getMaxSerialNo(String serialNo) {

		return dao.getMaxSerialNo(serialNo);
	}

	/*
	 * public boolean InterfaceFromWorksheet(String deptId){ boolean flag =
	 * false; List orgList = null; orgList.add(new
	 * ThreadPermimissionOrg(deptId,StaticVariable.PRIV_ASSIGNTYPE_DEPT,
	 * getUser(request) .getDeptname()));
	 * 
	 * return flag; }
	 */

}
