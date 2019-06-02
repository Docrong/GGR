/**
 * GroupFault_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commonfaultfccos;

public interface GroupFault_PortType extends java.rmi.Remote {
    public java.lang.String queryGroupFault(java.lang.String request) throws java.rmi.RemoteException;
    public java.lang.String queryGroupFaultvillage(java.lang.String request) throws java.rmi.RemoteException;
    public java.lang.String getBasicInfo(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String getStageFeedback(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String getCoveryInfo(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;
}
