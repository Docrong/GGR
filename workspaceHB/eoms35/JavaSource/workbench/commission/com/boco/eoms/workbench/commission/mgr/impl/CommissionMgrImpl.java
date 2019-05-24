package com.boco.eoms.workbench.commission.mgr.impl;

import java.util.List;

import com.boco.eoms.workbench.commission.mgr.ICommissionMgr;

import com.boco.eoms.workbench.commission.mgr.ITawWorkbenchCommissionInstanceMgr;

public class CommissionMgrImpl implements ICommissionMgr {
	
	private ITawWorkbenchCommissionInstanceMgr tawWorkbenchCommissionInstanceMgr;
	
	/**
	 * @param tawWorkbenchCommissionInstanceMgr the tawWorkbenchCommissionInstanceMgr to set
	 */
	public void setTawWorkbenchCommissionInstanceMgr(
			ITawWorkbenchCommissionInstanceMgr tawWorkbenchCommissionInstanceMgr) {
		this.tawWorkbenchCommissionInstanceMgr = tawWorkbenchCommissionInstanceMgr;
	}

	public List listUsersByModuleId(String userId, String moduleId) {
		return tawWorkbenchCommissionInstanceMgr.listUsersByModuleId(userId,moduleId);
	}

	public List listUsersBySubRoleId(String userId, String subRoleId) {
		return tawWorkbenchCommissionInstanceMgr.listUsersBySubRoleId(userId,subRoleId);
	}
	
	public List listTrustorsByModuleId(String userId, String moduleId) {
		 return tawWorkbenchCommissionInstanceMgr.listTrustorsByModuleId(userId, moduleId);
	}
	
	public List listTrustorsBySubRoleId(String userId, String subRoleId){
		 return tawWorkbenchCommissionInstanceMgr.listTrustorsBySubRoleId(userId, subRoleId);
	}

}
