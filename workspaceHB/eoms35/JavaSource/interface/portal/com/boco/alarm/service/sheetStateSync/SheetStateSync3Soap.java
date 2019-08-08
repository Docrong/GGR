/**
 * SheetStateSync3Soap.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.alarm.service.sheetStateSync;

public interface SheetStateSync3Soap extends java.rmi.Remote {
    public java.lang.String reqAlarm(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;

    public java.lang.String syncSheetState(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;

    public java.lang.String syncBatchSheetState(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;

    public java.lang.String isAlive() throws java.rmi.RemoteException;
}
