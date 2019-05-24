package com.boco.eoms.workbench.commission.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.workbench.commission.dao.ITawWorkbenchCommissionInstanceDao;
import com.boco.eoms.workbench.commission.mgr.ITawWorkbenchCommissionInstanceMgr;
import com.boco.eoms.workbench.commission.model.TawWorkbenchCommissionInstance;

public class TawWorkbenchCommissionInstanceMgrImpl implements
		ITawWorkbenchCommissionInstanceMgr {

	private ITawWorkbenchCommissionInstanceDao tawWorkbenchCommissionInstanceDao;

	public ITawWorkbenchCommissionInstanceDao getTawWorkbenchCommissionInstanceDao() {
		return tawWorkbenchCommissionInstanceDao;
	}

	public void setTawWorkbenchCommissionInstanceDao(
			ITawWorkbenchCommissionInstanceDao tawWorkbenchCommissionInstanceDao) {
		this.tawWorkbenchCommissionInstanceDao = tawWorkbenchCommissionInstanceDao;
	}

	public TawWorkbenchCommissionInstance getCommissionInstance(String id) {
		return tawWorkbenchCommissionInstanceDao.getCommissionInstance(id);
	}

	public List listCommissionInstances(String userId, String moduleId) {
		return tawWorkbenchCommissionInstanceDao.listCommissionInstances(
				userId, moduleId);
	}

	public List listCommissionInstancesByPeriod(String startTime, String endTime) {
		return tawWorkbenchCommissionInstanceDao
				.listCommissionInstancesByPeriod(startTime, endTime);
	}

	public void saveCommissionInstance(
			TawWorkbenchCommissionInstance commissionInstance) {
		tawWorkbenchCommissionInstanceDao
				.saveCommissionInstance(commissionInstance);
	}

	public Map listCommissionInstances(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return tawWorkbenchCommissionInstanceDao.listCommissionInstances(
				curPage, pageSize, whereStr);
	}

	public void delCommissionInstance(
			TawWorkbenchCommissionInstance commissionInstance) {
		tawWorkbenchCommissionInstanceDao
				.delCommissionInstance(commissionInstance);
	}

	public List listUsersByModuleId(String trustorId, String moduleId) {
		List userList = new ArrayList();
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		List instanceList = tawWorkbenchCommissionInstanceDao
				.listCommissionInstancesByTrustorId(trustorId, moduleId);
		for (Iterator it = instanceList.iterator(); it.hasNext();) {
			TawWorkbenchCommissionInstance instance = (TawWorkbenchCommissionInstance) it
					.next();
			String sysUserId = instance.getUserId();
			TawSystemUser sysUser = userMgr.getUserByuserid(sysUserId);
			if (null != sysUser.getId() && !"".equals(sysUser.getId())) {
				userList.add(sysUser);
			}
		}
		return userList;
	}

	public List listUsersBySubRoleId(String trustorId, String subRoleId) {
		List userList = new ArrayList();
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		List instanceList = tawWorkbenchCommissionInstanceDao
				.listCommissionInstancesByTrustorId(trustorId);
		for (Iterator it = instanceList.iterator(); it.hasNext();) {
			TawWorkbenchCommissionInstance instance = (TawWorkbenchCommissionInstance) it
					.next();
			String sysUserId = instance.getUserId();
			if (userRefRoleMgr.isExist(subRoleId, sysUserId)) {
				TawSystemUser sysUser = userMgr.getUserByuserid(sysUserId);
				if (null != sysUser.getId() && !"".equals(sysUser.getId())) {
					userList.add(sysUser);
				}
			}
		}
		return userList;
	}
	//2009-01-16 由被代理人得到代理人列表
	public List listTrustorsByModuleId(String userId, String moduleId) {
		List trustorsList = new ArrayList();
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		List instanceList = tawWorkbenchCommissionInstanceDao
				.listCommissionInstancesByUserId(userId, moduleId);
		for (Iterator it = instanceList.iterator(); it.hasNext();) {
			TawWorkbenchCommissionInstance instance = (TawWorkbenchCommissionInstance) it
					.next();
			String trustorId = instance.getTrustorId();
			TawSystemUser sysUser = userMgr.getUserByuserid(trustorId);
			if (null != sysUser.getId() && !"".equals(sysUser.getId())) {
				trustorsList.add(sysUser);
			}
		}
		return trustorsList;
	}

	public List listTrustorsBySubRoleId(String userId, String subRoleId) {
		List trustorsList = new ArrayList();
		ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserRefRoleManager");
		List instanceList = tawWorkbenchCommissionInstanceDao
				.listCommissionInstancesByUserId(userId);
		for (Iterator it = instanceList.iterator(); it.hasNext();) {
			TawWorkbenchCommissionInstance instance = (TawWorkbenchCommissionInstance) it
					.next();
			String trustorId = instance.getTrustorId();
			if (userRefRoleMgr.isExist(subRoleId, trustorId)) {
				TawSystemUser sysUser = userMgr.getUserByuserid(trustorId);
				if (null != sysUser.getId() && !"".equals(sysUser.getId())) {
					trustorsList.add(sysUser);
				}
			}
		}
		return trustorsList;
	}

}
