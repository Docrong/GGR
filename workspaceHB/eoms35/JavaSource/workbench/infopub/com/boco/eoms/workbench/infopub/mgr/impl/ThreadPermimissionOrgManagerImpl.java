package com.boco.eoms.workbench.infopub.mgr.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao;
import com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;

/**
 * 
 * <p>
 * Title:记录信息可否操作，组织结构（谁）权限mgr
 * </p>
 * <p>
 * Description:谁可看贴，谁可发贴
 * </p>
 * <p>
 * Date:May 24, 2008 6:02:03 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadPermimissionOrgManagerImpl extends BaseManager implements
		IThreadPermimissionOrgManager {
	private ThreadPermimissionOrgDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setThreadPermimissionOrgDao(ThreadPermimissionOrgDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager#getThreadPermimissionOrgs(com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg)
	 */
	public List getThreadPermimissionOrgs(
			final ThreadPermimissionOrg threadPermimissionOrg) {
		return dao.getThreadPermimissionOrgs(threadPermimissionOrg);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager#getThreadPermimissionOrg(String
	 *      id)
	 */
	public ThreadPermimissionOrg getThreadPermimissionOrg(final String id) {
		return dao.getThreadPermimissionOrg(new String(id));
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager#saveThreadPermimissionOrg(ThreadPermimissionOrg
	 *      threadPermimissionOrg)
	 */
	public void saveThreadPermimissionOrg(
			ThreadPermimissionOrg threadPermimissionOrg) {
		dao.saveThreadPermimissionOrg(threadPermimissionOrg);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager#removeThreadPermimissionOrg(String
	 *      id)
	 */
	public void removeThreadPermimissionOrg(final String id) {
		dao.removeThreadPermimissionOrg(new String(id));
	}

	/**
	 * 
	 */
	public Map getThreadPermimissionOrgs(final Integer curPage,
			final Integer pageSize) {
		return dao.getThreadPermimissionOrgs(curPage, pageSize, null);
	}

	public Map getThreadPermimissionOrgs(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getThreadPermimissionOrgs(curPage, pageSize, whereStr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager#delPermissionByThreadId(java.lang.String)
	 */
	public void delPermissionByThreadId(String threadId) {
		List list = dao.getThreadPermissionOrgsByThreadId(threadId);
		if (null != list) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				ThreadPermimissionOrg org = (ThreadPermimissionOrg) it.next();
				dao.removeThreadPermimissionOrg(org);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager#getThreadPermissionOrgsByThreadId(java.lang.String)
	 */
	public List getThreadPermissionOrgsByThreadId(String threadId) {
		return dao.getThreadPermissionOrgsByThreadId(threadId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IThreadPermimissionOrgManager#removeThreadPermissionOrgByThreadId(java.lang.String)
	 */
	public void removeThreadPermissionOrgByThreadId(String threadId) {
		List list = dao.getThreadPermissionOrgsByThreadId(threadId);
		if (null != list) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				ThreadPermimissionOrg org = (ThreadPermimissionOrg) it.next();
				org.setIsDel(Constants.DELETED_FLAG);
				dao.saveThreadPermimissionOrg(org);
			}
		}
	}

}
