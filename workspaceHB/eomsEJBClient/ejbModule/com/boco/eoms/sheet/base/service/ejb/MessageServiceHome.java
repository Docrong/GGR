package com.boco.eoms.sheet.base.service.ejb;

/**
 * Home interface for Enterprise Bean: MessageService
 */
public interface MessageServiceHome extends javax.ejb.EJBHome {

    /**
     * Creates a default instance of Session Bean: MessageService
     */
    public com.boco.eoms.sheet.base.service.ejb.MessageService create()
            throws javax.ejb.CreateException,
            java.rmi.RemoteException;
}
