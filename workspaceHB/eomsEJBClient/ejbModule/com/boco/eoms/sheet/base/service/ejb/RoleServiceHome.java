package com.boco.eoms.sheet.base.service.ejb;

/**
 * Home interface for Enterprise Bean: RoleService
 */
public interface RoleServiceHome extends javax.ejb.EJBHome {
    /**
     * Creates a default instance of Session Bean: RoleService
     */
    public com.boco.eoms.sheet.base.service.ejb.RoleService create()
            throws javax.ejb.CreateException,
            java.rmi.RemoteException;
}
