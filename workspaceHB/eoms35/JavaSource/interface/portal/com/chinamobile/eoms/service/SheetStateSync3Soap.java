/**
 * SheetStateSync3Soap.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package com.chinamobile.eoms.service;

public interface SheetStateSync3Soap extends java.rmi.Remote {
    public java.lang.String isAlive() throws java.rmi.RemoteException;

    public java.lang.String syncSheetState(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;
}
