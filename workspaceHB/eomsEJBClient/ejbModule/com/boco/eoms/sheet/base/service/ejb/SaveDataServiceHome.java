package com.boco.eoms.sheet.base.service.ejb;
/**
 * Home interface for Enterprise Bean: SaveDataService
 */
public interface SaveDataServiceHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: SaveDataService
	 */
	public com.boco.eoms.sheet.base.service.ejb.SaveDataService create()
		throws javax.ejb.CreateException,
		java.rmi.RemoteException;
}
