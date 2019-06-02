/**
 * ReplaySheetWorkSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.commonfault.interfaces;

public interface ReplaySheetWorkSoap_PortType extends java.rmi.Remote {
    public java.lang.String helloWorld() throws java.rmi.RemoteException;

    /**
     * EOMS导入接口
     */
    public java.lang.String newWorkSheet(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String calltime, java.lang.String opDetail) throws java.rmi.RemoteException;
}
