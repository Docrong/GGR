package com.boco.eoms.sheet.base.service.ejb;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessSaveDataServiceHomeBean_0be3888c
 */
public class EJSStatelessSaveDataServiceHomeBean_0be3888c extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessSaveDataServiceHomeBean_0be3888c
	 */
	public EJSStatelessSaveDataServiceHomeBean_0be3888c() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public com.boco.eoms.sheet.base.service.ejb.SaveDataService create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.boco.eoms.sheet.base.service.ejb.SaveDataService result = null;
boolean createFailed = false;
try {
	result = (com.boco.eoms.sheet.base.service.ejb.SaveDataService) super.createWrapper(null);
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
