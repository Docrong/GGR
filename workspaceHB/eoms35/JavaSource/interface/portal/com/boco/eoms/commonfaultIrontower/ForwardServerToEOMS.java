/**
 * ForwardServerToEOMS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commonfaultIrontower;

public interface ForwardServerToEOMS extends java.rmi.Remote {
    public void withdrawWorksheet(java.lang.String opDetail) throws java.rmi.RemoteException;
    public void accessoriesSync(java.lang.String ftpPath, java.util.HashMap valueMap) throws java.rmi.RemoteException;
    public void acceptWorksheet(java.lang.String opDetail) throws java.rmi.RemoteException;
    public void replyWorksheet(java.lang.String opDetail) throws java.rmi.RemoteException;
}
