package com.boco.eoms.sheet.base.service.ejb;

import java.rmi.RemoteException;
import java.util.List;
;

/**
 * Remote interface for Enterprise Bean: RoleService
 */
public interface RoleService extends javax.ejb.EJBObject {
    public boolean isVirtualRole(String subRoleId) throws RemoteException;
}
