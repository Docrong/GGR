package com.boco.eoms.sheet.base.service.ejb;

import java.rmi.RemoteException;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

/**
 * Bean implementation class for Enterprise Bean: RoleService
 */
public class RoleServiceBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}


	public boolean isVirtualRole(String subRoleId) throws RemoteException {
		boolean flag=false;		
		ITawSystemRoleManager mr= 
			(ITawSystemRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemRoleManager");
		ITawSystemSubRoleManager mgr = 
			(ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
		TawSystemSubRole subRole=mgr.getTawSystemSubRole(subRoleId);
		long roleId = subRole.getRoleId();
		TawSystemRole role = mr.getTawSystemRole(roleId);		
		if(String.valueOf(role.getRoleTypeId()).equals(RoleConstants.ROLETYPE_VIRTUAL))
			flag = true;
		return flag;
	}
}
