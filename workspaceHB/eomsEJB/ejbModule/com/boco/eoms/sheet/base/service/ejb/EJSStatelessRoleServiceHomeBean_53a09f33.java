package com.boco.eoms.sheet.base.service.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessRoleServiceHomeBean_53a09f33
 */
public class EJSStatelessRoleServiceHomeBean_53a09f33 extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessRoleServiceHomeBean_53a09f33
	 */
	public EJSStatelessRoleServiceHomeBean_53a09f33() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public com.boco.eoms.sheet.base.service.ejb.RoleService create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.boco.eoms.sheet.base.service.ejb.RoleService result = null;
boolean createFailed = false;
try {
	result = (com.boco.eoms.sheet.base.service.ejb.RoleService) super.createWrapper(null);
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
}
